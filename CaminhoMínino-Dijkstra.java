import java.util.*;
import java.lang.*;
import java.io.*;
 
public class CaminhoMinimo
{
    // Função para encontrar o vértice com menor valor de distância
    // do conjunto de vértices ainda não incluído na árvore
	
    static final int V=9;
    int menorDistancia(int dist[], Boolean sptSet[])
    {
        // Inicializando valor minimo
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    // Função para imprimir o array de distância construído
    
    void printSolucao(int dist[], int n)
    {
        System.out.println("Vertice    Distancia da origem");
        for (int i = 0; i < V; i++)
            System.out.println(i+" -------------> "+dist[i]);
    }
 
    // Função que implementa o algoritmo de Dijkstra para um grafo
    // representado usando matriz de adjacencia

    void dijkstra(int graph[][], int src)
    {
    	// O array de saída dist[i] guarda a menor distancia da origem até i
        int dist[] = new int[V];
 
        // sptSet[i] é true se o vértice i for incluído na árvore de menor caminho
        // ou quando a menor distância da origem até i for encontrada
        Boolean sptSet[] = new Boolean[V];
 
        // Inicializando todas as distancias como infintas e sptSet[] como falso
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
 
        // Distancia da origem a si mesma é sempre 0
        dist[src] = 0;
 
        // Encontra o caminho mais curto para todos os vértices
        for (int count = 0; count < V-1; count++)
        {
            // Escolhe o vértice de menor distância do conjunto de vértices
            // não processados. u é sempre igual a origem na primeira iteração
        	
            int u = menorDistancia(dist, sptSet);
 
            // Marca o vértice escolhido como processado
            sptSet[u] = true;
 
            // Atualiza o valor de dist dos vértices adjacentes ao vértice escolhido
            for (int v = 0; v < V; v++)
 
                // Atualiza dist[v] se não estiver em sptSet, há uma aresta de u para v
                // e o peso total do caminho da origem até v através de u
                // é menor que o atual valor de dist[v]
                if (!sptSet[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
 
        // Imprime o array construido
        printSolucao(dist, V);
    }
 
    // Main
    public static void main (String[] args)
    {
        /* Exemplo do slide */
       int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                                  {4, 0, 8, 0, 0, 0, 0, 11, 0},
                                  {0, 8, 0, 7, 0, 4, 0, 0, 2},
                                  {0, 0, 7, 0, 9, 14, 0, 0, 0},
                                  {0, 0, 0, 9, 0, 10, 0, 0, 0},
                                  {0, 0, 4, 14, 10, 0, 2, 0, 0},
                                  {0, 0, 0, 0, 0, 2, 0, 1, 6},
                                  {8, 11, 0, 0, 0, 0, 1, 0, 7},
                                  {0, 0, 2, 0, 0, 0, 6, 7, 0}
                                 };
        CaminhoMinimo t = new CaminhoMinimo();
        t.dijkstra(graph, 0);
    }
}
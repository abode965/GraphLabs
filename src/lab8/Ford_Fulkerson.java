package lab8;
//@ِAbdulrahman Alabrash
//HomeWork#3 20-Nov-17
//"You can't depend on your eyes when your imagination is out of focus."
import java.util.logging.Level;
import java.util.logging.Logger;
import lab5.*;

public class Ford_Fulkerson {

    static WeightedGraph<String> residualGraph;
    static WeightedGraph<String> graph;
    static int sink = 5;
    static int source = 0;
    static int size = 0;

    public static void main(String[] args) {
        String[] vertices = {"s", "v1", "v2", "v3", "v4", "t"};
        int[][] edges = {{0, 1, 16}, {0, 2, 13}, {1, 3, 12}, {2, 1, 4}, {2, 4, 14}, {3, 2, 9}, {3, 5, 20}, {4, 3, 7}, {4, 5, 4}};
        graph = new WeightedGraph<>(vertices, edges);
        residualGraph = new WeightedGraph<>(vertices, edges);
        size = graph.getSize();
        try {
            System.out.println(maxFlow(source, sink));
        } catch (Exception ex) {
            Logger.getLogger(Ford_Fulkerson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static boolean bfs(int s, int t, WeightedGraph<String> g, int[] parent) {

        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1; // Initialize parent[i] to -1
        }
        java.util.LinkedList<Integer> queue
                = new java.util.LinkedList<>(); // list used as a queue
        boolean[] isVisited = new boolean[g.getSize()];
        queue.offer(s); // Enqueue v
        isVisited[s] = true; // Mark it visited

        while (!queue.isEmpty()) {
            int u = queue.poll(); // Dequeue to u
            for (int v = 0; v < g.getSize(); v++) {
                try {
                    if (isVisited[v] == false && g.getWeight(u, v) > 0) {
                        queue.add(v);
                        parent[v] = u;
                        isVisited[v] = true;
                    }
                } catch (Exception ex) {
                    //Logger.getLogger(Ford_Fulkerson.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return (isVisited[t] == true);
    }

    static int maxFlow(int s, int t) throws Exception  {

        int parent[] = new int[size];
        int max_flow = 0;
        while (bfs(s, t, residualGraph, parent)) {

            int flow = Integer.MAX_VALUE;
            int j;
            for (int i = t; i != s; i = parent[i]) {
                j = parent[i];
                if (isEdge(j, i)) {
                    flow = Math.min(flow, residualGraph.getWeight(j, i));
                }
            }
            for (int i = t; i != s; i = parent[i]) {
                j = parent[i];
                if (isEdge(j, i)) {
                    try {
                        residualGraph.setWeight(j, i, residualGraph.getWeight(j, i) - flow);
                        residualGraph.setWeight(i, j, residualGraph.getWeight(i, j) + flow);
                    } catch (Exception ex) {
                      //  Logger.getLogger(Ford_Fulkerson.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }

            max_flow += flow;
        }

        return max_flow;
    }

    static boolean isEdge(int u, int v) {
        return graph.getNeighbors(u).contains(v);
    }
}

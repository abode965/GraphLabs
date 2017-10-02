package lab2;

import java.util.List;



/**
 *
 * @author FSMBLM1
 */
public class UnweightedGraph extends AbstractGraph{

    public UnweightedGraph() {
    }

    public UnweightedGraph(Object[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public UnweightedGraph(List vertices, List edges) {
        super(vertices, edges);
    }

    public UnweightedGraph(List edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public UnweightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }
    
   
}

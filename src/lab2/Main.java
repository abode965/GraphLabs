package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author FSMBLM1
 */
public class Main {
    public static void main(String[] args) {
        String path = "src//lab2//text.txt";
        Graph g = readGraphFromFile(path);
        g.printEdges();
       // System.out.println(isConnected1(g));
        System.out.println( abdulrahmanAlabrashGraph.getConnectedComponents(g));
        
        //System.out.println(isConnected(g));
    }


    public static Graph readGraphFromFile(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            ArrayList<Integer> vertices = new ArrayList<>();
            scanner.nextLine();
            while (scanner.hasNext()) {
                vertices.add(scanner.nextInt());
                scanner.nextLine();
            }
            ArrayList<AbstractGraph.Edge> edge = new ArrayList<>();
            scanner = new Scanner(new File(path));
            scanner.nextLine();
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(" ");
                for (int i = 1; i < line.length; i++) {
                    edge.add(new AbstractGraph.Edge(Integer.parseInt(line[0]), Integer.parseInt(line[i])));
                }

            }
            return new UnweightedGraph(vertices, edge);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static boolean isConnected(Graph g) {
        for (int i = 0; i < g.getVertices().size(); i++) {
            List<Integer> temp = g.getNeighbors((int) g.getVertices().get(i));
            if (temp.size() == 1) {
                System.out.println(g.getVertices().get(i) + "\tis not Connected with Graph");
                return false;
            }
            System.out.println(g.getVertices().get(i) + "\tVertex is Connected with\t" + temp);
        }
        return true;
    }
    public static boolean isConnected1(Graph g){
        return g.getVertices().size()==g.bsf(0).getNumberOfVerticesFound();
    }



}

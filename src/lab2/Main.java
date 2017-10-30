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
        UnweightedGraph g = (UnweightedGraph) readGraphFromFile(path);
       g.printEdges();
        System.out.println("isConnected(g)\t"+isConnected(g));
         System.out.println("isConnected1(g)/t"+isConnected1(g));
        System.out.println("abdulrahmanAlabrashGraph.getConnectedComponents(g)\t"+abdulrahmanAlabrashGraph.getConnectedComponents(g));
        System.out.println("isEulerian(g)\t"+isEulerian(g));
        System.out.println("hasEulerTrail(g)\t"+hasEulerTrail(g));
        System.out.println("isHamiltonian(g)\t"+isHamiltonian(g));
       
    }

    public static boolean isEulerian(UnweightedGraph g) {
        boolean isConnected = g.getVertices().size() == g.bfs(0).getNumberOfVerticesFound();
        for (int i = 0; i < g.getSize(); i++) {
            if (g.getDegree(i) % 2 != 0) {
                return false;
            }
        }
        return isConnected && true;
    }

    public static boolean hasEulerTrail(UnweightedGraph g) {
        boolean isConnected = g.getVertices().size() == g.bfs(0).getNumberOfVerticesFound();
        int count = 0;
        for (int i = 0; i < g.getSize(); i++) {
            if (g.getDegree(i) % 2 != 0) {
                count++;
            }
        }
        if (count <= 2 && isConnected) {
            return true;
        }
        return false;
    }

    public static boolean isHamiltonian(UnweightedGraph g) {
        ArrayList<Integer> degreeSquences = new ArrayList<>();
        //ArrayList<Integer>m = new ArrayList<>();
        for (int i = 0; i < g.getSize(); i++) {
            degreeSquences.add(g.getDegree(i));
        }
        Collections.sort(degreeSquences);
        double m = (double) g.getSize() / 2;
        System.out.println("degreeSquences\t" + degreeSquences+"\t m= "+m+"\tsize"+degreeSquences.size());
        
            for (int j = 1; j < m; j++) {
                if (degreeSquences.get(j-1) <= j) {//dm<=m
                    if (degreeSquences.get(degreeSquences.size() - j-1) < degreeSquences.size() - j){ //d(n-m)<n-m
                        System.out.print("d(n-m)"+degreeSquences.get(degreeSquences.size() - j)+"\tn-m");
                        System.out.println(degreeSquences.size() - j);
                        return false;
                    }
                }
            }

        return true;
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

    public static boolean isConnected1(Graph g) {
        return g.getVertices().size() == g.bsf(0).getNumberOfVerticesFound();
    }

}

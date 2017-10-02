package lab2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author FSMBLM1
 */
public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner= new Scanner (new File ("C:\\Users\\EDAAT\\Documents\\NetBeansProjects\\GraphLabs\\src\\lab2\\text.txt"));
            ArrayList<Integer>vertices =new ArrayList<>();
            
            int verticsNumber = scanner.nextInt();
            while(scanner.hasNext()){
               vertices.add(scanner.nextInt());
               scanner.nextLine();
            }
            ArrayList<AbstractGraph.Edge> edge = new ArrayList<>();
            scanner= new Scanner (new File ("C:\\Users\\EDAAT\\Documents\\NetBeansProjects\\GraphLabs\\src\\lab2\\text.txt"));
            int lineNum=0;
            scanner.nextLine();
            while(scanner.hasNext()){
               String [] line = scanner.nextLine().split(" "); 
                for (int i = 1; i < line.length; i++) {
                    edge.add(new AbstractGraph.Edge(Integer.parseInt(line[0]), Integer.parseInt(line[i])));
                }
               
            }
            Graph g = new UnweightedGraph(vertices,edge);
            g.printEdges();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

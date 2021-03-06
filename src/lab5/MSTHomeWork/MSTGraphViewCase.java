package lab5.MSTHomeWork;

import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import lab4.*;

/*
 * @author Abdulrahman Alabrash 
 * 1421221032
 */
public class MSTGraphViewCase extends Pane {

    private Graph<? extends MSTDisplayTree.City> graph;
    //to do: add a new data field tree with a set method
    private AbstractGraph.Tree tree;

  //to do: Construct a GraphViewCase with a specified tree and graph
    //to do: Construct a GraphViewCase with a specified graph
    public MSTGraphViewCase(Graph<? extends MSTDisplayTree.City> graph) {
        this.graph = graph;
    }

    //to do: implement setTree method
    public void setTree(AbstractGraph.Tree tree) {
        this.tree = tree;
    }

    public void repaint() {
        getChildren().clear();

        // Draw vertices
        List<? extends Displayable> vertices = graph.getVertices();

        for (int i = 0; i < graph.getSize(); i++) {
            int x = vertices.get(i).getX();
            int y = vertices.get(i).getY();
            String name = vertices.get(i).getName();

            getChildren().addAll(new Circle(x, y, 8),
                    new Text(x - 12, y - 12, name));
        }

        // Draw edges
        for (int i = 0; i < graph.getSize(); i++) {
            List<Integer> edges = graph.getNeighbors(i);
            for (int j = 0; j < edges.size(); j++) {
                int v = edges.get(j);
                int x1 = graph.getVertex(i).getX();
                int y1 = graph.getVertex(i).getY();
                int x2 = graph.getVertex(v).getX();
                int y2 = graph.getVertex(v).getY();
                try {
                    getChildren().addAll(new Line(x1, y1, x2, y2),
                            new Text((x1 + x2) / 2, (y1 + y2) / 2 - 6, ((lab5.WeightedGraph) graph).getWeight(i, v) + ""));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }

        if (tree != null) {
            tree.printTree();
            for (int i = 1; i < tree.getSearchOrder().size(); i++) {
                List<Integer> list = tree.getSearchOrder();

                MSTDisplayTree.City city = (MSTDisplayTree.City) graph.getVertex(list.get(i));

                int parentIndex = tree.getParent(list.get(i));

                MSTDisplayTree.City parentCity = (MSTDisplayTree.City) graph.getVertex(parentIndex);

                drawArrowLine(parentCity.getX(), parentCity.getY(), city.getX(), city.getY());

            }
        }

        //to do: Highlight the edges in the spanning tree
        //invoke drawArrowLine method
    }

    public void drawArrowLine(int x1, int y1,
            int x2, int y2) {

        Line line1 = new Line(x1, y1, x2, y2);
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(5);
        getChildren().add(line1);

        // find slope of this line
        double slope = ((((double) y1) - (double) y2))
                / (((double) x1) - (((double) x2)));

        double arctan = Math.atan(slope);

        // This will flip the arrow 45 off of a
        // perpendicular line at pt x2
        double set45 = 1.57 / 2;

        // arrows should always point towards i, not i+1
        if (x1 < x2) {
            // add 90 degrees to arrow lines
            set45 = -1.57 * 1.5;
        }

        // set length of arrows
        int arrlen = 15;

        // draw arrows on line
        Line line2 = new Line(x2, y2, x2 + Math.cos(arctan + set45) * arrlen,
                y2 + Math.sin(arctan + set45) * arrlen);
        line2.setStroke(Color.RED);
        line2.setStrokeWidth(5);
        getChildren().add(line2);

        Line line3 = new Line(x2, y2, (int) ((x2 + (Math.cos(arctan - set45) * arrlen))),
                (int) (((y2)) + (Math.sin(arctan - set45) * arrlen)));
        line3.setStroke(Color.RED);
        line3.setStrokeWidth(5);
        getChildren().add(line3);
    }
}

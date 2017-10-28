package lab5.ShortPath;


import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lab4.Displayable;
import lab5.WeightedGraph;

/**
 *
 * @author Abdulrahman Alabrash 
 * 1421221032
 */
public class ShortPathDisplayTree extends Application {

    private TextField tfStartCity = new TextField();
    private TextField tfEndCity = new TextField();
    private Button btDisplaySP = new Button("Display Shortest Path");
    private Label lblStatus = new Label();

    private City[] vertices = {new City("Seattle", 75, 50),
        new City("San Francisco", 50, 210), new City("Los Angeles", 75, 275),
        new City("Denver", 275, 175), new City("Kansas City", 400, 245),
        new City("Chicago", 450, 100), new City("Boston", 700, 80),
        new City("New York", 675, 120), new City("Atlanta", 575, 295),
        new City("Miami", 600, 400), new City("Dallas", 408, 325),
        new City("Houston", 450, 360)};

    int[][] edges = {
        {0, 1, 807}, {0, 3, 1331}, {0, 5, 2097},
        {1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
        {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
        {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599},
        {3, 5, 1003},
        {4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260},
        {4, 8, 864}, {4, 10, 496},
        {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533},
        {5, 6, 983}, {5, 7, 787},
        {6, 5, 983}, {6, 7, 214},
        {7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888},
        {8, 4, 864}, {8, 7, 888}, {8, 9, 661},
        {8, 10, 781}, {8, 11, 810},
        {9, 8, 661}, {9, 11, 1187},
        {10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239},
        {11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
    };
    WeightedGraph<City> graph1 = new WeightedGraph<>(vertices,edges);
    private ShortPathGraphViewCase view = new ShortPathGraphViewCase(graph1);

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        pane.setCenter(view);

        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.getChildren().addAll(new Label("Starting City:"), tfStartCity,
                new Label("Ending City"),tfEndCity, btDisplaySP);
        hBox.setAlignment(Pos.CENTER);

        pane.setBottom(hBox);
        pane.setTop(lblStatus);
        BorderPane.setAlignment(lblStatus, Pos.CENTER);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 750, 500);
        primaryStage.setTitle("Shortest Path "); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        view.repaint();

        btDisplaySP.setOnAction(e -> {
            String ScityName = tfStartCity.getText().trim();
            String EcityName=tfEndCity.getText().trim();
            
            int ScityIndex = graph1.getIndex(new City(ScityName, 0, 0));
            int EcityIndex = graph1.getIndex(new City(EcityName, 0, 0));
            List<City> shortestPath=null;
            if (ScityIndex == -1 || EcityIndex == -1) {
                view.setTree(null);
                lblStatus.setText("ERROR ERROR");
            } else {
                lblStatus.setText("");
                WeightedGraph<City>.ShortestPathTree tree = 
                     graph1.getShortestPath(ScityIndex);
               shortestPath=tree.getPath(EcityIndex);
               Collections.reverse(shortestPath);
                view.setTree(tree);
              

            }
            view.repaintSPT(shortestPath);

        });

       
    }

    /**
     * The main method is only needed for the IDE with limited JavaFX support.
     * Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

    static class City implements Displayable {

        private int x, y;
        private String name;

        City(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;

        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

        public boolean equals(Object o) {
            return ((City) o).name.equals(this.name);
        }
    }
}

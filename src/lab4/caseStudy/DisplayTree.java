/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.caseStudy;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lab4.*;

/**
 *
 * @author Berna
 */
public class DisplayTree extends Application {
  private TextField tfStartCity = new TextField();
  private Button btDisplayDFS = new Button("Display DFS Tree");
  private Button btDisplayBFS = new Button("Display BFS Tree");
  private Label lblStatus = new Label();
  
  private City[] vertices = {new City("Seattle", 75, 50),
    new City("San Francisco", 50, 210), new City("Los Angeles", 75, 275),
    new City("Denver", 275, 175), new City("Kansas City", 400, 245),
    new City("Chicago", 450, 100), new City("Boston", 700, 80),
    new City("New York", 675, 120), new City("Atlanta", 575, 295),
    new City("Miami", 600, 400), new City("Dallas", 408, 325),
    new City("Houston", 450, 360)};

  // Edge array for graph in Figure 27.1
  private int[][] edges = {
    {0, 1}, {0, 3}, {0, 5},
    {1, 0}, {1, 2}, {1, 3},
    {2, 1}, {2, 3}, {2, 4}, {2, 10},
    {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
    {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
    {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
    {6, 5}, {6, 7},
    {7, 4}, {7, 5}, {7, 6}, {7, 8},
    {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
    {9, 8}, {9, 11},
    {10, 2}, {10, 4}, {10, 8}, {10, 11},
    {11, 8}, {11, 9}, {11, 10}
  };

  private Graph<City> graph1 = new UnweightedGraph<>(vertices, edges);
  private GraphViewCase view = new GraphViewCase(graph1);
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();
    pane.setCenter(view);

    HBox hBox = new HBox(5);
    hBox.setPadding(new Insets(5, 5, 5, 5));
    hBox.getChildren().addAll(new Label("Starting City:"), tfStartCity,
            btDisplayDFS, btDisplayBFS);
    hBox.setAlignment(Pos.CENTER);

    pane.setBottom(hBox);
    pane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 750, 450);
    primaryStage.setTitle("BFS/DFS"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    view.repaint();

    
    btDisplayDFS.setOnAction(e -> {
      String cityName = tfStartCity.getText();

      int cityIndex = graph1.getIndex(new City(cityName,0,0));

      if(cityIndex == -1){
        view.setTree(null);
        lblStatus.setText("ERROR ERROR");
      }else{
        lblStatus.setText("");
        view.setTree(graph1.dfs(cityIndex));

      }
      view.repaint();
      
    });

    btDisplayBFS.setOnAction(e -> {
      String cityName = tfStartCity.getText();

      int cityIndex = graph1.getIndex(new City(cityName,0,0));


      if(cityIndex == -1){
        view.setTree(null);
        lblStatus.setText("ERROR ERROR");
      }else{
        lblStatus.setText("");
        view.setTree(graph1.bfs(cityIndex));

      }
      view.repaint();
		//to be implemented
		//take the city name
		//display the BFS tree starting from a specified city by invoking setTree method
	  /*If a city not in the map is entered, the program displays an error message in the label*/
    });
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
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
package lab1.JavaFxBasics;
import  javafx.application.Application;
import  javafx.event.ActionEvent;
import  javafx.event.EventHandler;
import javafx.scene.Scene;
import  javafx.scene.control.Button;
import  javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application /*implements EventHandler<ActionEvent>*/ {
    Button button;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //main javafx code
        //entire window is called the stage , inside ur stage that the scene where we're going to putting the components like buttons,widgets
        //first thing we should do is make a layout then add the components
        // then we should implement the scene
        primaryStage.setTitle("Graph Labs ");
        StackPane layout = new StackPane();
        button = new Button("Click me ");
        layout.getChildren().add(button);
        button.setOnAction(e ->
                System.out.println("Hi ")
        );
        Scene scene = new Scene(layout,300,250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    @Override
//    public void handle(ActionEvent event) {
//    if(event.getSource()==button)
//    {
//        System.out.println("Hiiiiiiiiiiiii");
//    }
//    }
}

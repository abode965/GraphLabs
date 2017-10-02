package lab1.JavaFxBasics;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlretBoxes extends Application {
    Stage window ;
    Scene scene;
    Button button;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
    window = primaryStage;
    window.setTitle("Alret Boxes");
    button = new Button("Click me ");
  //  button.setOnAction(e-> Alret.Display("Title of window","Here we Are dude  awesome"));
    button.setOnAction(e->{
        boolean result = Confrim.Display("Title ","Are you sure you want to go on");
        System.out.println(result);});
    StackPane layout = new StackPane();
    layout.getChildren().add(button);
    scene = new Scene(layout,200,200);
    window.setScene(scene);
    window.show();
    }
}
class Alret {
    public static  void Display(String title,String message) {

        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Button closeButton = new Button("close the window");
        closeButton.setOnAction(e->window.close());
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,200,200);
        window.setScene(scene);
        window.showAndWait();

    }
}

class Confrim {

    static boolean answer;
    public static Boolean Display(String title, String message) {

        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label(message);
        Button yesButton=new Button("yes");
        Button noButton=new Button("No");

        yesButton.setOnAction(e->
        {
            answer=true;
            window.close();
        }
        );
        noButton.setOnAction(e->
                {
                    answer=false;
                    window.close();
                }
        );

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,yesButton,noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,200,200);
        window.setScene(scene);
        window.showAndWait();
    return answer;
    }
}
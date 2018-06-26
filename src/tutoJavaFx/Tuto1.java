package tutoJavaFx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Tuto1 extends Application{

    //Button button1;
    Button button2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Title of the Window");
        //button1 = new Button();
        button2 = new Button("Don't click me bruv");
        //button1.setText("Click me bruh");

        /*
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Well done bruh !");
            }
        });
        */

        button2.setOnAction(e ->System.out.println("Lambda Expression Power"));

        StackPane layout = new StackPane();
        //layout.getChildren().add(button1);
        layout.getChildren().add(button2);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

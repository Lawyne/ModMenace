package fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

public class TestFX extends Application{

    Button buttonGoldenVertex;
    Button buttonGoldenEdge;
    Button buttonGoldenRemoveVertex;
    Button buttonGoldenRemoveEdge;

    Button buttonVertex;
    Button buttonEdge;
    Button buttonRemoveVertex;
    Button buttonRemoveEdge;
    Button buttonSync;

    Button buttonVertex1;
    Button buttonEdge1;
    Button buttonRemoveVertex1;
    Button buttonRemoveEdge1;
    Button buttonSync1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MEntity A = new MEntity("A");
        MEntity B = new MEntity("B");
        MEntity R = new MEntity("R");
        MEntity I = new MEntity("I");

        MLink AB = new MLink(B);
        MLink AI = new MLink(I);
        MLink AR = new MLink(R);
        A.addLink(AB);
        A.addLink(AI);
        A.addLink(AR);

        MLink BA = new MLink(A);
        MLink BI = new MLink(I);
        MLink BR = new MLink(R);
        B.addLink(BA);
        B.addLink(BI);
        B.addLink(BR);

        MLink IA = new MLink(A);
        MLink IB = new MLink(B);
        I.addLink(IA);
        I.addLink(IB);

        MLink RA = new MLink(A);
        MLink RB = new MLink(B);
        MLink RI = new MLink(I);
        R.addLink(RA);
        R.addLink(RB);
        R.addLink(RI);

        MSystem sys = new MSystem();
        sys.addEntity(A);
        sys.addEntity(B);
        sys.addEntity(R);
        sys.addEntity(I);

        sys.addLink(AB);
        sys.addLink(BA);
        sys.addLink(AR);
        sys.addLink(RA);
        sys.addLink(AI);
        sys.addLink(IA);
        sys.addLink(BR);
        sys.addLink(RB);
        sys.addLink(BI);
        sys.addLink(IB);
        sys.addLink(RI);

        MView atkView = new MView(sys);
        MView defView = new MView(sys);

        MManager controller = new MManager(sys,atkView,defView);
        ///FXController controller = new FXController(setSystem);

        FXView goldenView = new FXView(sys,controller.getLocations());
        controller.addObserver(goldenView);

        FXView fxView = new FXView(atkView,controller.getLocations());
        controller.addObserver(fxView,1,atkView);
        FXView fxView1 = new FXView(defView,controller.getLocations());
        controller.addObserver(fxView1,2,defView);

        /*
        for (int i = 0; i < 1000 ; i++) {
            fxSystem.addVertex();
            fxSystem.addEdge();
            fxSystem.addEdge();
        }
        */

        buttonGoldenEdge = new Button("New edge");
        buttonGoldenEdge.setOnAction(e ->goldenView.addEdge());

        buttonGoldenVertex = new Button("New vertex");
        buttonGoldenVertex.setOnAction(e ->goldenView.addVertex());

        buttonGoldenRemoveEdge = new Button("Remove edge");
        buttonGoldenRemoveEdge.setOnAction(e ->goldenView.removeEdge());

        buttonGoldenRemoveVertex = new Button("Remove vertex");
        buttonGoldenRemoveVertex.setOnAction(e ->goldenView.removeVertex());


        buttonEdge = new Button("New edge");
        buttonEdge.setOnAction(e ->fxView.addEdge());

        buttonVertex = new Button("New vertex");
        buttonVertex.setOnAction(e ->fxView.addVertex());

        buttonRemoveEdge = new Button("Remove edge");
        buttonRemoveEdge.setOnAction(e ->fxView.removeEdge());

        buttonRemoveVertex = new Button("Remove vertex");
        buttonRemoveVertex.setOnAction(e ->fxView.removeVertex());

        buttonSync = new Button("Synchro");
        buttonSync.setOnAction(e->fxView.synchronize());


        buttonEdge1 = new Button("New edge");
        buttonEdge1.setOnAction(e ->fxView1.addEdge());

        buttonVertex1 = new Button("New vertex");
        buttonVertex1.setOnAction(e ->fxView1.addVertex());

        buttonRemoveEdge1 = new Button("Remove edge");
        buttonRemoveEdge1.setOnAction(e ->fxView1.removeEdge());

        buttonRemoveVertex1 = new Button("Remove vertex");
        buttonRemoveVertex1.setOnAction(e ->fxView1.removeVertex());

        buttonSync1 = new Button("Synchro");
        buttonSync1.setOnAction(e->fxView1.synchronize());

        /*
        HBox buttons = new HBox();
        buttons.getChildren().add(buttonVertex);
        buttons.getChildren().add(buttonEdge);
        buttons.getChildren().add(new Text("         "));
        buttons.getChildren().add(buttonGoldenVertex);
        buttons.getChildren().add(buttonGoldenEdge);
        buttons.getChildren().add(new Text("         "));
        buttons.getChildren().add(buttonVertex1);
        buttons.getChildren().add(buttonEdge1);

        BorderPane root = new BorderPane(goldenView, null, fxView, buttons, fxView1);
        root.setMargin(fxView,new Insets(FXConstants.MARGIN,FXConstants.MARGIN,FXConstants.MARGIN,FXConstants.MARGIN));
        root.setMargin(goldenView,new Insets(FXConstants.MARGIN,FXConstants.MARGIN,FXConstants.MARGIN,FXConstants.MARGIN));
        root.setMargin(fxView1,new Insets(FXConstants.MARGIN,FXConstants.MARGIN,FXConstants.MARGIN,FXConstants.MARGIN));

        Scene scene = new Scene(root, 3*FXConstants.WIDTH + (4 * FXConstants.MARGIN), FXConstants.HEIGHT + (4 * FXConstants.MARGIN));
        */


        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().add(buttonVertex);
        buttons.getChildren().add(buttonEdge);
        buttons.getChildren().add(buttonRemoveVertex);
        buttons.getChildren().add(buttonRemoveEdge);
        buttons.getChildren().add(buttonSync);
        HBox goldenButtons = new HBox();
        goldenButtons.setAlignment(Pos.CENTER);
        goldenButtons.getChildren().add(buttonGoldenVertex);
        goldenButtons.getChildren().add(buttonGoldenEdge);
        goldenButtons.getChildren().add(buttonGoldenRemoveVertex);
        goldenButtons.getChildren().add(buttonGoldenRemoveEdge);
        HBox buttons1 = new HBox();
        buttons1.setAlignment(Pos.CENTER);
        buttons1.getChildren().add(buttonVertex1);
        buttons1.getChildren().add(buttonEdge1);
        buttons1.getChildren().add(buttonRemoveVertex1);
        buttons1.getChildren().add(buttonRemoveEdge1);
        buttons1.getChildren().add(buttonSync1);

        GridPane root = new GridPane();
        root.setHgap(20);
        root.setVgap(20);
        root.setAlignment(Pos.CENTER);
        root.add(fxView,0,0);
        root.add(goldenView,1,0);
        root.add(fxView1,2,0);
        root.add(buttons,0,1);
        root.add(goldenButtons,1,1);
        root.add(buttons1,2,1);
        root.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");

        Scene scene = new Scene(root, 3*FXConstants.WIDTH + (6 * FXConstants.MARGIN), FXConstants.HEIGHT + (6 * FXConstants.MARGIN));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

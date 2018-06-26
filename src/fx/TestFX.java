package fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.MEntity;
import model.MLink;
import model.MSystem;

public class TestFX extends Application{

    Button buttonVertex;
    Button buttonEdge;

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

        FXSystem fxSystem = new FXSystem(sys);

        for (int i = 0; i < 1000 ; i++) {
            fxSystem.addVertex();
            fxSystem.addEdge();
            fxSystem.addEdge();
        }

        buttonEdge = new Button("New edge");
        buttonEdge.setOnAction(e ->fxSystem.addEdge());

        buttonVertex = new Button("New vertex");
        buttonVertex.setOnAction(e ->fxSystem.addVertex());

        HBox buttons = new HBox();
        buttons.getChildren().add(buttonVertex);
        buttons.getChildren().add(buttonEdge);

        BorderPane root = new BorderPane(fxSystem, null, null, buttons, null);
        root.setMargin(fxSystem,new Insets(FXConstants.MARGIN,FXConstants.MARGIN,FXConstants.MARGIN,FXConstants.MARGIN));

        Scene scene = new Scene(root, FXConstants.WIDTH + (2 * FXConstants.MARGIN), FXConstants.HEIGHT + (2 * FXConstants.MARGIN));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

package ueb.ueb04.a03;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderPaneChanged extends Application
{
    public void start(Stage primaryStage)
    {
        BorderPane root = new BorderPane();
        Insets ins = new Insets(10);
        root.setPadding(ins);
        Button b;
        b = new Button("Top");
        BorderPane.setAlignment(b, Pos.CENTER);
        b.setMaxWidth(Double.MAX_VALUE);
        root.setTop(b);
        b = new Button("Bottom");
        BorderPane.setAlignment(b, Pos.CENTER);
        b.setMaxWidth(Double.MAX_VALUE);
        root.setBottom(b);
        b = new Button("Left");
        BorderPane.setAlignment(b, Pos.CENTER);
        b.setMaxHeight(Double.MAX_VALUE);
        root.setLeft(b);
        b = new Button("Right");
        BorderPane.setAlignment(b, Pos.CENTER);
        b.setMaxHeight(Double.MAX_VALUE);
        root.setRight(b);
        b = new Button("Center");
        BorderPane.setAlignment(b, Pos.CENTER);
        b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        root.setCenter(b);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BorderPane-Übung");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

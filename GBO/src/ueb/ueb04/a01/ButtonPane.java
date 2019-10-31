package ueb.ueb04.a01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ButtonPane extends Application
{

    public void start(Stage primaryStage)
    {
        Pane root = new Pane();
        for (int i = 1; i <= 10; i++)
        {
            Button b = new Button("Button " + i);
            b.setLayoutX((i - 1) * 20);
            b.setLayoutY((i - 1) * 20);
            root.getChildren().add(b);
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pane-Beispiel");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

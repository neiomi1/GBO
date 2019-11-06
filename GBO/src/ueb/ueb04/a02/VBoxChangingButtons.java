package ueb.ueb04.a02;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxChangingButtons extends Application
{
    // Analog zu HBox
    public void start(Stage primaryStage)
    {
        VBox root = new VBox(10);
        Insets ins = new Insets(10);
        root.setPadding(ins);
        root.setAlignment(Pos.TOP_RIGHT);
        root.setFillWidth(true);

        // Unchanging Buttons
        Button b3 = new Button("unchanging");
        Button b5 = new Button("unchanging 2");

        // Width changing Button
        Button b1 = new Button("changing width");
        b1.setMaxWidth(Double.MAX_VALUE);

        // Height changing Button
        Button b2 = new Button("changing height");
        b2.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(b2, Priority.ALWAYS);

        // Changing both
        Button b4 = new Button("changing both");
        b4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(b4, Priority.ALWAYS);

        root.getChildren().addAll(b1, b2, b3, b4, b5);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("VBox-Beispiel");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

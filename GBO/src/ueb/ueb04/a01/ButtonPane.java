package ueb.ueb04.a01;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
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

        // Dynamisch anpassende Buttons durch Height & Width Properties. 10
        // buttons, 1ster fix -> /9
        ReadOnlyDoubleProperty height = root.heightProperty();
        ReadOnlyDoubleProperty width = root.widthProperty();

        width.addListener((p, oldValue, newValue) ->
        {
            double newWidth = (double) newValue;
            double widthNum = root.getChildren().get(9).prefWidth(0);
            for (int i = 0; i < 10; i++)
            {
                root.getChildren().get(i).setLayoutX((newWidth - widthNum) / 9 * i);
            }
        });

        height.addListener((p, oldValue, newValue) ->
        {
            double newHeight = (double) newValue;
            double heightNum = root.getChildren().get(9).prefHeight(0);
            for (int i = 0; i < 10; i++)
            {
                // System.out.println((newHeight - heightNum) / 10 * i + " " +
                // ((newHeight - heightNum)));
                root.getChildren().get(i).setLayoutY((newHeight - heightNum) / 9 * i);
            }
        });

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

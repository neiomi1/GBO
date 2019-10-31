package gui.shapes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Bezier extends Application
{
    public void start(Stage primaryStage)
    {
        Pane root = new Pane();
        drawBezier(root);

        primaryStage.setTitle("Bezier-Kurve");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    private void drawBezier(Pane root)
    {
        root.getChildren().add(new Circle(50, 50, 5, Color.RED));
        root.getChildren().add(new Circle(100, 200, 5, Color.RED));
        root.getChildren().add(new Circle(250, 200, 5, Color.RED));
        root.getChildren().add(new Circle(300, 50, 5, Color.RED));

        CubicCurve bezier = new CubicCurve(50, 50, 100, 200,
                                           250, 200, 300, 50);
        bezier.setStroke(Color.BLACK);
        bezier.setFill(null);
        bezier.setStrokeWidth(3);
        root.getChildren().add(bezier);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

package code;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Test extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // TODO Auto-generated method stub

        primaryStage.setTitle("Immortal Journey");
        Pane root = new Pane();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(400, 200);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
        gc.setFont(theFont);
        gc.fillText("Hello, World!", 60, 50);
        gc.strokeText("Hello, World!", 60, 50);

        Image test = new Image("test.jpg");
        gc.drawImage(test, 100, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

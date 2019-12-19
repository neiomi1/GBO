package ueb.ueb11.a03;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class FilledPolygon extends Application
{

    private Pane root;

    private Polygon poly;

    private double sceneX, sceneY;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new Pane();

        poly = new Polygon();
        poly.setFill(Color.LIGHTBLUE);
        poly.setStroke(Color.BLACK);
        poly.setStrokeWidth(2);
        root.getChildren().add(poly);
        addPoints();
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Filled Polygon");
        primaryStage.show();
    }

    public void addPoints()
    {
        Double[] points = new Double[]
        { 40.0, 30.0, 60.0, 90.0, 150.0, 110.0, 50.0, 200.0, 55.0, 300.0, 100.0, 290.0, 150.0, 300.0, 200.0, 100.0, 200.0, 400.0, 400.0, 350.0, 250.0, 100.0, 70.0 };
        for (int i = 0; i < points.length - 1; i += 2)
        {
            Circle tempCircle = new Circle(points[i], points[i + 1], 5, Color.RED);
            Label tempLabel = new Label(String.valueOf(i / 2));
            tempLabel.setLayoutX(points[i] + 10);
            tempLabel.setLayoutY(points[i + 1] - 5);
            tempCircle.setOnMousePressed(e ->
            {
                sceneX = e.getSceneX();
                sceneY = e.getSceneY();
            });

            tempCircle.setOnMouseDragged(e ->
            {
                tempLabel.setLayoutX(tempLabel.getLayoutX() + e.getSceneX() - sceneX);
                tempLabel.setLayoutY(tempLabel.getLayoutY() + e.getSceneY() - sceneY);
                tempCircle.setLayoutX(tempCircle.getLayoutX() + e.getSceneX() - sceneX);
                tempCircle.setLayoutY(tempCircle.getLayoutY() + e.getSceneY() - sceneY);
                double tempX = poly.getPoints().get(Integer.valueOf(tempLabel.getText()) * 2);
                double tempY = poly.getPoints().get(Integer.valueOf(tempLabel.getText()) * 2 + 1);

                poly.getPoints().set(Integer.valueOf(tempLabel.getText()) * 2, tempX + e.getSceneX() - sceneX);
                poly.getPoints().set(Integer.valueOf(tempLabel.getText()) * 2 + 1, tempY + e.getSceneY() - sceneY);
                sceneX = e.getSceneX();
                sceneY = e.getSceneY();
            });

            tempLabel.setOnMousePressed(e ->
            {
                sceneX = e.getSceneX();
                sceneY = e.getSceneY();
            });

            tempLabel.setOnMouseDragged(e ->
            {
                tempLabel.setLayoutX(tempLabel.getLayoutX() + e.getSceneX() - sceneX);
                tempLabel.setLayoutY(tempLabel.getLayoutY() + e.getSceneY() - sceneY);
                tempCircle.setLayoutX(tempCircle.getLayoutX() + e.getSceneX() - sceneX);
                tempCircle.setLayoutY(tempCircle.getLayoutY() + e.getSceneY() - sceneY);
                double tempX = poly.getPoints().get(Integer.valueOf(tempLabel.getText()) * 2);
                double tempY = poly.getPoints().get(Integer.valueOf(tempLabel.getText()) * 2 + 1);

                poly.getPoints().set(Integer.valueOf(tempLabel.getText()) * 2, tempX + e.getSceneX() - sceneX);
                poly.getPoints().set(Integer.valueOf(tempLabel.getText()) * 2 + 1, tempY + e.getSceneY() - sceneY);
                sceneX = e.getSceneX();
                sceneY = e.getSceneY();
            });

            root.getChildren().addAll(tempCircle, tempLabel);
            poly.getPoints().addAll(points[i], points[i + 1]);
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

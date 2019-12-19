package ueb.ueb11.a02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;

public class Bezier extends Application
{
    private Label startLabel, endLabel, controlPointOneLabel,
                    controlPointTwoLabel;

    private Circle startCircle, endCircle, controlPointOneCircle,
                    controlPointTwoCircle;

    private double sceneX, sceneY;

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
        CubicCurve bezier = new CubicCurve(50, 50, 100, 200, 250, 200, 300, 50);

        bezier.setStroke(Color.BLACK);
        bezier.setFill(null);
        bezier.setStrokeWidth(3);
        root.getChildren().add(bezier);

        startLabel = new Label("Start");
        startLabel.setTextFill(Color.RED);
        startLabel.setLayoutX(60);
        startLabel.setLayoutY(45);
        startLabel.setOnMousePressed(e ->
        {
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });

        startLabel.setOnMouseDragged(e ->
        {
            bezier.setStartX(bezier.getStartX() + e.getSceneX() - sceneX);
            bezier.setStartY(bezier.getStartY() + e.getSceneY() - sceneY);
            startLabel.setLayoutX(startLabel.getLayoutX() + e.getSceneX() - sceneX);
            startLabel.setLayoutY(startLabel.getLayoutY() + e.getSceneY() - sceneY);
            startCircle.setLayoutX(startCircle.getLayoutX() + e.getSceneX() - sceneX);
            startCircle.setLayoutY(startCircle.getLayoutY() + e.getSceneY() - sceneY);
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });

        controlPointOneLabel = new Label("Control Point 1");
        controlPointOneLabel.setLayoutX(110);
        controlPointOneLabel.setLayoutY(195);
        controlPointOneLabel.setTextFill(Color.RED);
        controlPointOneLabel.setOnMousePressed(e ->
        {
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });

        controlPointOneLabel.setOnMouseDragged(e ->
        {
            bezier.setControlX1(bezier.getControlX1() + e.getSceneX() - sceneX);
            bezier.setControlY1(bezier.getControlY1() + e.getSceneY() - sceneY);
            controlPointOneLabel.setLayoutX(controlPointOneLabel.getLayoutX() + e.getSceneX() - sceneX);
            controlPointOneLabel.setLayoutY(controlPointOneLabel.getLayoutY() + e.getSceneY() - sceneY);
            controlPointOneCircle.setLayoutX(controlPointOneCircle.getLayoutX() + e.getSceneX() - sceneX);
            controlPointOneCircle.setLayoutY(controlPointOneCircle.getLayoutY() + e.getSceneY() - sceneY);
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });

        controlPointTwoLabel = new Label("Control Point 2");
        controlPointTwoLabel.setLayoutX(260);
        controlPointTwoLabel.setLayoutY(195);
        controlPointTwoLabel.setTextFill(Color.RED);
        controlPointTwoLabel.setOnMousePressed(e ->
        {
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });

        controlPointTwoLabel.setOnMouseDragged(e ->
        {
            bezier.setControlX2(bezier.getControlX2() + e.getSceneX() - sceneX);
            bezier.setControlY2(bezier.getControlY2() + e.getSceneY() - sceneY);
            controlPointTwoLabel.setLayoutX(controlPointTwoLabel.getLayoutX() + e.getSceneX() - sceneX);
            controlPointTwoLabel.setLayoutY(controlPointTwoLabel.getLayoutY() + e.getSceneY() - sceneY);
            controlPointTwoCircle.setLayoutX(controlPointTwoCircle.getLayoutX() + e.getSceneX() - sceneX);
            controlPointTwoCircle.setLayoutY(controlPointTwoCircle.getLayoutY() + e.getSceneY() - sceneY);
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });
        endLabel = new Label("End");
        endLabel.setLayoutX(310);
        endLabel.setLayoutY(45);
        endLabel.setTextFill(Color.RED);
        endLabel.setOnMousePressed(e ->
        {
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });

        endLabel.setOnMouseDragged(e ->
        {
            bezier.setEndX(bezier.getEndX() + e.getSceneX() - sceneX);
            bezier.setEndY(bezier.getEndY() + e.getSceneY() - sceneY);
            endLabel.setLayoutX(endLabel.getLayoutX() + e.getSceneX() - sceneX);
            endLabel.setLayoutY(endLabel.getLayoutY() + e.getSceneY() - sceneY);
            endCircle.setLayoutX(endCircle.getLayoutX() + e.getSceneX() - sceneX);
            endCircle.setLayoutY(endCircle.getLayoutY() + e.getSceneY() - sceneY);
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });
        startCircle = new Circle(50, 50, 5, Color.RED);
        startCircle.setOnMousePressed(e ->
        {
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });

        startCircle.setOnMouseDragged(e ->
        {
            bezier.setStartX(bezier.getStartX() + e.getSceneX() - sceneX);
            bezier.setStartY(bezier.getStartY() + e.getSceneY() - sceneY);
            startLabel.setLayoutX(startLabel.getLayoutX() + e.getSceneX() - sceneX);
            startLabel.setLayoutY(startLabel.getLayoutY() + e.getSceneY() - sceneY);
            startCircle.setLayoutX(startCircle.getLayoutX() + e.getSceneX() - sceneX);
            startCircle.setLayoutY(startCircle.getLayoutY() + e.getSceneY() - sceneY);
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });

        controlPointOneCircle = new Circle(100, 200, 5, Color.RED);
        controlPointOneCircle.setOnMousePressed(e ->
        {
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });

        controlPointOneCircle.setOnMouseDragged(e ->
        {
            bezier.setControlX1(bezier.getControlX1() + e.getSceneX() - sceneX);
            bezier.setControlY1(bezier.getControlY1() + e.getSceneY() - sceneY);
            controlPointOneLabel.setLayoutX(controlPointOneLabel.getLayoutX() + e.getSceneX() - sceneX);
            controlPointOneLabel.setLayoutY(controlPointOneLabel.getLayoutY() + e.getSceneY() - sceneY);
            controlPointOneCircle.setLayoutX(controlPointOneCircle.getLayoutX() + e.getSceneX() - sceneX);
            controlPointOneCircle.setLayoutY(controlPointOneCircle.getLayoutY() + e.getSceneY() - sceneY);
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });
        controlPointTwoCircle = new Circle(250, 200, 5, Color.RED);
        controlPointTwoCircle.setOnMousePressed(e ->
        {
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });

        controlPointTwoCircle.setOnMouseDragged(e ->
        {
            bezier.setControlX2(bezier.getControlX2() + e.getSceneX() - sceneX);
            bezier.setControlY2(bezier.getControlY2() + e.getSceneY() - sceneY);
            controlPointTwoLabel.setLayoutX(controlPointTwoLabel.getLayoutX() + e.getSceneX() - sceneX);
            controlPointTwoLabel.setLayoutY(controlPointTwoLabel.getLayoutY() + e.getSceneY() - sceneY);
            controlPointTwoCircle.setLayoutX(controlPointTwoCircle.getLayoutX() + e.getSceneX() - sceneX);
            controlPointTwoCircle.setLayoutY(controlPointTwoCircle.getLayoutY() + e.getSceneY() - sceneY);
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });
        endCircle = new Circle(300, 50, 5, Color.RED);
        endCircle.setOnMousePressed(e ->
        {
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });

        endCircle.setOnMouseDragged(e ->
        {
            bezier.setEndX(bezier.getEndX() + e.getSceneX() - sceneX);
            bezier.setEndY(bezier.getEndY() + e.getSceneY() - sceneY);
            endLabel.setLayoutX(endLabel.getLayoutX() + e.getSceneX() - sceneX);
            endLabel.setLayoutY(endLabel.getLayoutY() + e.getSceneY() - sceneY);
            endCircle.setLayoutX(endCircle.getLayoutX() + e.getSceneX() - sceneX);
            endCircle.setLayoutY(endCircle.getLayoutY() + e.getSceneY() - sceneY);
            sceneX = e.getSceneX();
            sceneY = e.getSceneY();
        });

        root.getChildren().addAll(startLabel, controlPointOneLabel, controlPointTwoLabel, endLabel);
        root.getChildren().add(startCircle);
        root.getChildren().add(controlPointOneCircle);
        root.getChildren().add(controlPointTwoCircle);
        root.getChildren().add(endCircle);

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

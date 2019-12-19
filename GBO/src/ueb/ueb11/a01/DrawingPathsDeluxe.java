package ueb.ueb11.a01;

import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class DrawingPathsDeluxe extends Application
{

    private int strokeWidth = 2;

    private VBox root;

    private Pane drawingPane;

    private HBox buttons;

    private ComboBox<Integer> widthSelector;

    private ComboBox<ColorName> colorPicker;

    private Button delete;

    private Color strokeColor;

    private Path currentPath;

    public void start(Stage primaryStage)
    {
        root = new VBox();

        buttons = new HBox();
        widthSelector = new ComboBox<Integer>(FXCollections.observableList(Arrays.asList(new Integer[]
        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 })));
        widthSelector.valueProperty().addListener((property, oldValue, newValue) ->
        {
            strokeWidth = newValue;
        });
        widthSelector.getSelectionModel().select(1);
        ObservableList<ColorName> colors = FXCollections.observableArrayList(new ColorName(Color.BLACK, "schwarz"));
        colorPicker = new ComboBox<ColorName>(colors);
        colorPicker.valueProperty().addListener((property, oldValue, newValue) ->
        {
            strokeColor = newValue.getColor();
        });
        colorPicker.getSelectionModel().selectFirst();
        delete = new Button("L\u00F6schen");
        delete.setOnAction(e -> drawingPane.getChildren().clear());

        buttons.getChildren().addAll(widthSelector, colorPicker, delete);

        drawingPane = new Pane();
        drawingPane.setPrefSize(330, 250);
        drawingPane.setOnMousePressed(e -> mousePressed(e.getX(), e.getY()));
        drawingPane.setOnMouseDragged(e -> mouseDragged(e.getX(), e.getY()));
        drawingPane.setOnMouseReleased(e -> mouseReleased());

        root.getChildren().addAll(buttons, drawingPane);

        colors.add(new ColorName(Color.BLUE, "blau"));
        colors.add(new ColorName(Color.RED, "rot"));
        colors.add(new ColorName(Color.YELLOW, "gelb"));
        colors.add(new ColorName(Color.VIOLET, "violett"));
        colors.add(new ColorName(Color.GREEN, "gr\u00fcn"));

        primaryStage.setTitle("FreihandzeichnenDeluxe");
        primaryStage.setScene(new Scene(root, 330, 300));
        primaryStage.show();
    }

    private void mousePressed(double x, double y)
    {
        // System.out.println(String.format("Mouse Pressed at %f %f", x, y));
        currentPath = new Path();
        currentPath.setStroke(strokeColor);
        currentPath.setStrokeWidth(strokeWidth);
        currentPath.getElements().add(new MoveTo(x, y));
        drawingPane.getChildren().add(currentPath);
    }

    private void mouseDragged(double x, double y)
    {
        currentPath.getElements().add(new LineTo(x, y));
    }

    private void mouseReleased()
    {
        currentPath = null;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

package ueb.ueb06.a02;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SliderListener extends Application
{
    public void start(Stage primaryStage)
    {
        Slider slider = new Slider(0, 100, 50);
        Label label = new Label("Slider wurde noch nicht bewegt.    ");

        slider.valueProperty().addListener((p, oldVal, newVal) ->
        {
            double diff = Math.round((double) oldVal - (double) newVal);
            System.out.println(diff);
            if (diff != 0)
            {
                slider.setValue((double) newVal);
            }
            label.setText(String.format("Änderung des Sliders um %f", diff));
        });

        GridPane grid = new GridPane();
        grid.add(slider, 0, 0);
        grid.add(label, 0, 1);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        Scene scene = new Scene(grid, 480, 80);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Slider mit Listener");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
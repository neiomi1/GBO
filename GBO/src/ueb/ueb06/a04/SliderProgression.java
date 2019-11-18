package ueb.ueb06.a04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SliderProgression extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        VBox root = new VBox();
        HBox progress = new HBox();

        root.setPadding(new Insets(10, 10, 10, 10));
        Slider slide = new Slider(-100, 100, 12);
        slide.valueProperty().addListener((p, oldVal, newVal) ->
        {
            System.out.println(newVal);
        });

        ProgressBar bar = new ProgressBar();
        ProgressIndicator indicator = new ProgressIndicator();
        bar.progressProperty().bind(Bindings.divide(slide.valueProperty(), 100));
        indicator.progressProperty().bind(Bindings.divide(slide.valueProperty(), 100));

        progress.getChildren().addAll(bar, indicator);
        root.getChildren().addAll(slide, progress);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Slider und Progress");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

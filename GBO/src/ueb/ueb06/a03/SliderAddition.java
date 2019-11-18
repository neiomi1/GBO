package ueb.ueb06.a03;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SliderAddition extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        VBox root = new VBox();
        HBox summands = new HBox();

        Slider sum1 = new Slider();
        Slider sum2 = new Slider();
        Slider sum3 = new Slider();
        Slider total = new Slider();

        total.valueProperty().bind(Bindings.divide((Bindings.add(sum1.valueProperty(), Bindings.add(sum2.valueProperty(), sum3.valueProperty()))), 3.0));

        summands.getChildren().addAll(sum1, sum2, sum3);
        root.getChildren().addAll(summands, total);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SliderAdd");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}

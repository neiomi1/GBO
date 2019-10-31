package ueb.ueb04.a05;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// Wachsende Buttons durch Scene Builder
public class CalculatorReforged extends Application
{
    public void start(Stage primaryStage)
    {
        try
        {
            Pane root = FXMLLoader.load(getClass().getResource("GridCalculator.fxml"));

            Scene scene = new Scene(root, 200, 335);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Grid Calc");
            primaryStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}

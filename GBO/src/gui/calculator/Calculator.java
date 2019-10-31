package gui.calculator;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Calculator extends Application
{
    public void start(Stage primaryStage)
    {
        try
        {
            Pane root = FXMLLoader.load(getClass().getResource("Calculator.fxml"));

            Scene scene = new Scene(root, 200, 335);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hello World");
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

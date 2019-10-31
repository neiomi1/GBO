package gui.plusminus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PlusMinus extends Application
{
    public void start(Stage primaryStage)
    {
        Button plus = new Button("+");
        Button minus = new Button("-");
        plus.setId("plus");
        minus.setId("minus");
        GridPane root = new GridPane();
        Label l = new Label();
        l.setId("counterL");
        root.add(plus, 0, 0);
        root.add(l, 0, 1);
        root.add(minus, 0, 2);
        l.setText("0");
        plus.setOnAction(e ->
        {
            l.setText(Integer.toString(Integer.parseInt(l.getText()) + 1));
        });
        minus.setOnAction(e ->
        {
            l.setText(Integer.toString(Integer.parseInt(l.getText()) - 1));
        });
        Scene scene = new Scene(root, 310, 70);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PlusMinus");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}

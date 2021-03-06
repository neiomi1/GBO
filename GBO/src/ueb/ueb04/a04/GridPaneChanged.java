package ueb.ueb04.a04;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

// Analog zum 1. Calculator. Col & Row Constraints in FXML
public class GridPaneChanged extends Application
{
    public void start(Stage primaryStage)
    {
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        Insets ins = new Insets(10);
        root.setPadding(ins);

        // RowConstraints r = new RowConstraints();
        //
        // root.getRowConstraints().add(r);
        // Button test = new Button("test");
        // Button test2 = new Button("test");
        // root.add(test, 1, 1);
        // root.add(test2, 1, 2);
        // for (int j = 1; j <= root.getRowCount(); j++)
        // {
        // System.out.println(j);
        // System.out.println(root.getRowCount());
        // RowConstraints rw = new RowConstraints();
        // System.out.println("-constraint- " + root.getRowCount());
        // root.getRowConstraints().add(rw);
        // System.out.println("-constraint add- " + root.getRowCount());
        // rw.setVgrow(Priority.ALWAYS);
        // }

        for (int i = 0; i < 6; i++)
        {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100 / 5);
            // column.setHgrow(Priority.ALWAYS);
            root.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100 / 5);
            // row.setVgrow(Priority.ALWAYS);
            root.getRowConstraints().add(row);
        }

        for (int i = 1; i <= 5; i++)
        {
            for (int j = 1; j <= 5; j++)
            {
                if (i < 3 || j < 3)
                {
                    Button b = new Button("Button " + i + "/" + j);

                    root.add(b, i - 1, j - 1);
                }
            }
        }
        //
        // for (int j = 1; j <= root.getRowCount(); j++)
        // {
        // System.out.println(j);
        // System.out.println(root.getRowCount());
        // RowConstraints rw = new RowConstraints();
        // System.out.println("-constraint- " + root.getRowCount());
        // rw.setMaxHeight(Double.MAX_VALUE);
        // root.getRowConstraints().add(rw);
        // System.out.println("-constraint add- " + root.getRowCount());
        // rw.setVgrow(Priority.ALWAYS);
        // }

        Button b;
        b = new Button("Button 6/*");
        root.setHalignment(b, HPos.CENTER);
        // b.setMaxHeight(Double.MAX_VALUE);
        root.add(b, 5, 0, 1, 4);
        b = new Button("Button */6");
        root.setHalignment(b, HPos.CENTER);
        root.setValignment(b, VPos.CENTER);
        // b.setMaxWidth(Double.MAX_VALUE);
        root.add(b, 0, 5, 3, 1);
        b = new Button("Button 3/3");
        // b.setMaxHeight(Double.MAX_VALUE);
        // b.setMaxWidth(Double.MAX_VALUE);
        root.add(b, 2, 2, 3, 3);

        root.setGridLinesVisible(true);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GridPane-Beispiel");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

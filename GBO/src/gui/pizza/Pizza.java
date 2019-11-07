package gui.pizza;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Pizza extends Application
{
    private final ToggleGroup sizeSelection = new ToggleGroup();

    private final ObservableSet<CheckBox> toppingSelection = FXCollections.observableSet();

    private final List<String> selectedToppings = new ArrayList<String>();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Configuration config = ParameterConverter.createConfiguration(this.getParameters().getNamed());
        Pane root = new Pane();
        GridPane toppingPane = new GridPane();
        // toppingPane.setGridLinesVisible(true);
        toppingPane.setHgap(10);
        toppingPane.setVgap(5);
        HBox sizePane = new HBox();
        sizePane.setSpacing(10);
        VBox optionPane = new VBox();
        optionPane.setSpacing(10);
        optionPane.getChildren().addAll(toppingPane, sizePane);
        root.getChildren().add(optionPane);
        // System.out.println(config.getSizeNames() + " " +
        // config.getSizePrices() + " " + config.getToppingNames() + " " +
        // config.getToppingPrices() + " " +
        // config.getNumberOfDefaultToppings());
        for (int i = 0; i < config.getToppingNames().length; i++)
        {
            int n = i % 2;
            int x = i;
            if (n == 1)
            {
                x -= 1;
            }
            CheckBox temp = new CheckBox(config.getToppingNames()[i]);
            // System.out.println(config.getToppingNames()[i] + " " + n + " " +
            // i);
            if (i < config.getNumberOfDefaultToppings())
            {
                temp.setSelected(true);
                temp.setDisable(true);
            }
            toppingSelection.add(temp);
            toppingPane.add(temp, n, x);
        }
        for (int i = 0; i < config.getSizeNames().length; i++)
        {
            RadioButton temp = new RadioButton(config.getSizeNames()[i]);
            if (i == 0)
            {
                temp.setSelected(true);
            }
            temp.setToggleGroup(sizeSelection);
            sizePane.getChildren().add(temp);

        }

        Button bestellen = new Button("Bestellen!");
        optionPane.getChildren().add(bestellen);

        TextArea bestelltext = new TextArea();
        bestelltext.setId("bestelltext");
        bestelltext.setEditable(false);

        bestellen.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent e)
            {
                int price = 0;
                String bestellung = "Sie haben eine Pizza bestellt.\nZutaten: ";
                for (int i = 0; i < toppingPane.getChildren().size(); i++)
                {
                    CheckBox temp = (CheckBox) toppingPane.getChildren().get(i);
                    if (temp.isSelected())
                    {
                        // System.out.println(temp.getText() +
                        // config.getToppingPrices()[i]);
                        bestellung += temp.getText() + ", ";
                        price += config.getToppingPrices()[i];
                        // System.out.println(bestellung);
                    }
                }
                bestellung = bestellung.substring(0, bestellung.length() - 2);
                // System.out.println(bestellung);
                for (int i = 0; i < sizePane.getChildren().size(); i++)
                {
                    RadioButton temp = (RadioButton) sizePane.getChildren().get(i);
                    if (temp.isSelected())
                    {
                        // System.out.println(temp.getText() +
                        // config.getSizePrices()[i]);
                        price += config.getSizePrices()[i];
                        bestellung += "\nDie Größe ist: " + temp.getText();
                    }
                }
                bestellung += String.format("\nDer Preis beträgt: %.2f €\nVielenDank", (double) price / 100);
                bestelltext.setText(bestellung);
            }

        });

        optionPane.getChildren().add(bestelltext);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pizza");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}

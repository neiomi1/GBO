package ueb.ueb06.a01;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CountryInfo extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        VBox layout = new VBox();
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);

        ObservableList<Country> countryList = FXCollections.observableArrayList(new Country("Belgien", "Brüssel", 10839905, 30528));

        ComboBox countrySelector = new ComboBox(countryList);
        // TODO: countrySelector.setCellFactory(new CellFactory())

        CheckBox exactNumbers = new CheckBox();

        GridPane labelgrid = new GridPane();
        Label countryName = new Label();
        countryName.setId("countryName");
        labelgrid.add(countryName, 1, 0);

        layout.getChildren().addAll(countrySelector, exactNumbers, labelgrid);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

package tutorial.woche_07;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CountryTableView extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        ObservableList<Country> data = FXCollections.observableArrayList();
        data.add(new Country("Deutschland", "Berlin", 83019000, 357578));
        data.add(new Country("Frankreich", "Paris", 66993000, 543965));
        data.add(new Country("Italien", "Rom", 6036000, 301338));
        data.add(new Country("Belgien", "Brüssel", 11376000, 306888));
        data.add(new Country("Spanien", "Madrid", 4672300, 505970));
        data.add(new Country("Niederlande", "Amsterdam", 17291000, 41543));

        TableView<Country> tv = new TableView<Country>(data);
        TableColumn<Country, String> nameCol = new TableColumn<Country, String>("Name");
        nameCol.setCellValueFactory(cdf -> cdf.getValue().getName());
        tv.getColumns().add(nameCol);

        TableColumn<Country, String> capitalCol = new TableColumn<Country, String>("Hauptstadt");
        capitalCol.setCellValueFactory(cdf -> cdf.getValue().getCapital());
        tv.getColumns().add(capitalCol);

        TableColumn<Country, Number> peopleCol = new TableColumn<Country, Number>("Einwohner");
        peopleCol.setCellValueFactory(cdf -> cdf.getValue().getPeople());
        tv.getColumns().add(peopleCol);

        TableColumn<Country, Number> areaCol = new TableColumn<Country, Number>("Fläche");
        areaCol.setCellValueFactory(cdf -> cdf.getValue().getArea());
        tv.getColumns().add(areaCol);

        TableColumn<Country, Number> densityCol = new TableColumn<Country, Number>("Einwohnerdichte");
        densityCol.setCellValueFactory(cdf -> cdf.getValue().getPeople().divide(cdf.getValue().getArea()));
        tv.getColumns().add(densityCol);

        Scene scene = new Scene(tv);
        stage.setTitle("Länder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

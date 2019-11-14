package gui.country.combo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CountryInfo extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15, 15, 15, 15));
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);

        ObservableList<Country> countryList = FXCollections.observableArrayList(new Country("Belgien", "Brüssel", 10839905, 30528));

        ComboBox<Country> countrySelector = new ComboBox<Country>(countryList);
        countrySelector.setId("countrySelector");

        countrySelector.setPromptText("Keine L\u00e4nder vorhanden");
        countrySelector.setPlaceholder(new Label("Keine L\u00e4nder vorhanden"));
        countrySelector.getSelectionModel().selectFirst();

        // countrySelector.setConverter(new StringConverter<Country>()
        // {
        //
        // @Override
        // public Country fromString(String countryName)
        // {
        // for (Country c : countryList)
        // {
        // if (c.getName().contentEquals(countryName))
        // {
        // return c;
        // }
        // }
        // return null;
        // }
        //
        // @Override
        // public String toString(Country country)
        // {
        // if (country != null)
        // {
        // return country.getName();
        // }
        // System.out.println(countrySelector.getPlaceholder() + "" + ((Label)
        // countrySelector.getPlaceholder()).getText());
        // String s = ((Label) countrySelector.getPlaceholder()).getText();
        // return s;
        // }
        //
        // });

        CheckBox exactNumbers = new CheckBox("exakte Angaben");
        exactNumbers.setId("exactValues");

        GridPane labelgrid = new GridPane();
        labelgrid.setVgap(5);
        labelgrid.setHgap(5);
        Label land = new Label("Land: ");
        labelgrid.add(land, 1, 0);
        Label hauptstadt = new Label("Hauptstadt: ");
        labelgrid.add(hauptstadt, 1, 1);
        Label einwohner = new Label("Einwohner: ");
        labelgrid.add(einwohner, 1, 2);
        Label flaeche = new Label("Fläche (in qkm): ");
        labelgrid.add(flaeche, 1, 3);
        Label bevoelkerungsdichte = new Label("Bevölkerungsdichte (in Personen pro qkm): ");
        labelgrid.add(bevoelkerungsdichte, 1, 4);

        Country init = countrySelector.getSelectionModel().getSelectedItem();
        Label countryName = new Label(init.getName());
        countryName.setId("countryName");
        labelgrid.add(countryName, 2, 0);
        Label capital = new Label(init.getCapital());
        capital.setId("capital");
        labelgrid.add(capital, 2, 1);
        Label population = new Label(kuerzen(init.getPeople()));
        population.setId("population");
        labelgrid.add(population, 2, 2);
        Label area = new Label(kuerzen(init.getArea()));
        area.setId("area");
        labelgrid.add(area, 2, 3);
        Label density = new Label(kuerzen(init.getPopulationDensity()));
        density.setId("density");
        labelgrid.add(density, 2, 4);

        exactNumbers.selectedProperty().addListener((property, oldValue, newValue) ->
        {
            try
            {
                kurzLang(newValue, countrySelector.getSelectionModel().getSelectedItem(), population, area, density);
            }
            catch (NullPointerException e1)
            {

            }
        });

        countrySelector.valueProperty().addListener((prop, oldVal, newVal) ->
        {
            try
            {
                countryName.setText(newVal.getName());
                capital.setText(newVal.getCapital());
                if (exactNumbers.isSelected())
                {
                    population.setText(formatNum(newVal.getPeople()));
                    area.setText(formatNum(newVal.getArea()));
                    density.setText(formatNum(newVal.getPopulationDensity()));
                }
                else
                {
                    population.setText(kuerzen(newVal.getPeople()));
                    area.setText(kuerzen(newVal.getArea()));
                    density.setText(kuerzen(newVal.getPopulationDensity()));
                }
            }
            catch (NullPointerException nE2)
            {
                System.out.println("del");
                // countrySelector.setPromptText("Keine L\u00e4nder vorhanden
                // ");
                countryName.setText("");
                capital.setText("");
                density.setText("");
                population.setText("");
                area.setText("");
            }

        });

        HBox addBox = new HBox();
        TextField landInsert = new TextField();
        landInsert.setPromptText("Land");
        landInsert.setId("countryField");
        TextField hauptstadtInsert = new TextField();
        hauptstadtInsert.setPromptText("Hauptstadt");
        hauptstadtInsert.setId("capitalField");
        TextField einwohnerInsert = new TextField();
        einwohnerInsert.setPromptText("Einwohner");
        einwohnerInsert.setId("populationField");
        TextField flaecheInsert = new TextField();
        flaecheInsert.setPromptText("Fl\u00e4che");
        flaecheInsert.setId("areaField");
        Button addCountry = new Button("Hinzuf\u00fcgen");
        addCountry.setId("add");

        addCountry.setOnAction(new EventHandler<>()
        {

            @Override
            public void handle(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                String tempLand = landInsert.getText();
                landInsert.clear();
                String tempHaupt = hauptstadtInsert.getText();
                hauptstadtInsert.clear();
                String tempEinw = einwohnerInsert.getText();
                einwohnerInsert.clear();
                String tempFlaeche = flaecheInsert.getText();
                flaecheInsert.clear();
                if (!tempLand.isBlank() && !tempHaupt.isBlank() && !tempEinw.isBlank() && !tempFlaeche.isBlank())
                {
                    try
                    {
                        System.out.println(tempLand + "" + tempHaupt + "" + tempEinw + "" + tempFlaeche);
                        Long einw = Long.parseLong(tempEinw);
                        Long flaecheLong = Long.parseLong(tempFlaeche);
                        if (einw > 0 && flaecheLong > 0)
                        {
                            System.out.println(tempLand + "" + tempHaupt + "" + einw + "" + flaecheLong);
                            countryList.add(new Country(tempLand, tempHaupt, einw, flaecheLong));
                        }
                    }
                    catch (NumberFormatException e)
                    {
                    }
                    if (countryList.size() == 1)
                    {
                        countrySelector.getSelectionModel().select(0);
                    }
                }

            }

        });
        addBox.getChildren().addAll(landInsert, hauptstadtInsert, einwohnerInsert, flaecheInsert, addCountry);

        Button loeschen = new Button("L\u00f6schen");
        loeschen.setId("delete");
        loeschen.setOnAction(e ->
        {
            try
            {
                countryList.remove(countrySelector.getSelectionModel().getSelectedIndex());
            }
            catch (IndexOutOfBoundsException iE)
            {
                System.out.println("hit");

                // countrySelector.setPlaceholder(new Label("Keine L\u00e4nder
                // vorhanden"));
            }
            countrySelector.getSelectionModel().select(0);
        });
        layout.getChildren().addAll(countrySelector, exactNumbers, labelgrid, addBox, loeschen);
        primaryStage.setTitle("L\u00e4nder-Informationen");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void kurzLang(Boolean trunctuate, Country c, Label population, Label area, Label density)
    {
        if (!trunctuate)
        {
            population.setText(kuerzen(c.getPeople()));
            area.setText(kuerzen(c.getArea()));
            density.setText(kuerzen(c.getPopulationDensity()));
        }
        else
        {
            population.setText(formatNum(c.getPeople()));
            area.setText(formatNum(c.getArea()));
            density.setText(formatNum(c.getPopulationDensity()));
        }
    }

    public String formatNum(Long n)
    {
        return String.format("%,d", n).replace(",", ".");
    }

    public String kuerzen(Long n)
    {
        if (n / 1000000 > 0)
        {
            return (String.format("%d Mill.", Math.round((n / 1000000.0))));
        }
        else if (n > 1000)
        {
            return String.format("%,d", (Math.round(n / 1000.0) * 1000)).replace(",", ".");

        }
        return (String.format("%d", Math.round(n)));
    }
}

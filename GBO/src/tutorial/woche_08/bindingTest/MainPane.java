package tutorial.woche_08.bindingTest;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainPane extends Application
{

    private Pane root, topArea, contentArea;

    private DoubleProperty contentWidth, topWidth, topHeight;

    private ReadOnlyDoubleProperty parentWidth, parentHeight;

    // Der Grundlegende Aufbau sind zwei Panes in einer HauptPane, ähnlich einer
    // BorderPane
    // Beide sollen sich per Binding an die Größe der HauptPane (root) anpassen.

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new Pane();
        parentHeight = root.heightProperty();
        parentWidth = root.widthProperty();

        topArea = new Pane();
        topArea.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
        topHeight = topArea.prefHeightProperty();
        topWidth = topArea.prefWidthProperty();

        topHeight.bind(parentHeight.divide(8));

        topHeight.addListener((prop, oldVal, newVal) ->
        {
            System.out.println("-----------------------------------");
            System.out.println("topHeight change:");
            System.out.println(oldVal + " " + newVal);
            contentArea.setLayoutY((double) newVal * 1.5);
        });

        topWidth.bind(parentWidth);

        topWidth.addListener((prop, oldVal, newVal) ->
        {
            // Hier liegt das Problem: contentArea.getWidth() gibt 0 zurück bis
            // das Fenster in der Breite verändert wird.
            // Ich kann aufgrund der Bindings den Wert anfangs nicht festlegen
            // und will dies eigentlich auch nicht, da
            // ich die Fenstergröße gerne aus einer Property Datei einlesen
            // würde, dies also nicht Hardcoden will.

            contentArea.setLayoutX(((double) newVal - contentArea.getWidth()) / 2);
            System.out.println("****************************content width*********************");
            Pane p = (Pane) contentArea.getChildren().get(0);
            System.out.println(p.getWidth());

        });

        contentArea = new Pane();
        contentArea.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        VBox content = new VBox();

        HBox searchBar = new HBox(10);

        searchBar.getChildren().add(new Label("Search:"));
        TextField searchField = new TextField();
        searchField.setPrefColumnCount(20);
        searchBar.getChildren().add(searchField);

        Button searchButton = new Button("Suchen");
        searchBar.getChildren().add(searchButton);

        Button addEntry = new Button("Add Entry");
        searchBar.getChildren().add(addEntry);
        content.getChildren().add(searchBar);
        contentArea.getChildren().setAll(content);

        root.getChildren().addAll(topArea, contentArea);

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bindings-Test");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}

package ueb.ueb05.a01;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HyperlinkCounter extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        VBox root = new VBox();
        Hyperlink link = new Hyperlink("Dies ist ein ziemlich langer Hyperlink mit ziemlich viel Text");
        Label counter = new Label("Hallo Welt zum 0.");
        counter.setLayoutY(50);
        CheckBox visit = new CheckBox("Hyperlink besucht");
        visit.setLayoutY(75);
        CheckBox underline = new CheckBox("Unterstrichen");
        underline.setLayoutY(100);
        CheckBox newline = new CheckBox("Zeilenumbruch");
        newline.setLayoutY(125);

        BooleanProperty checkboxVisited = visit.selectedProperty();
        BooleanProperty linkVisited = link.visitedProperty();

        BooleanProperty textWrapper = newline.selectedProperty();
        BooleanProperty textWrapperHyperlink = link.wrapTextProperty();

        // textWrapperHyperlink.bindBidirectional(textWrapper);
        textWrapper.bindBidirectional(textWrapperHyperlink);
        checkboxVisited.bindBidirectional(linkVisited);

        IntegerProperty count = new SimpleIntegerProperty(0);
        count.set(0);

        count.addListener((p, oldValue, newValue) -> counter.setText("Hallo Welt zum " + newValue + "."));

        underline.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                link.underlineProperty().set(!link.underlineProperty().get());
            }
        });

        // newline.setOnAction(new EventHandler<ActionEvent>()
        // {
        // public void handle(ActionEvent e)
        // {
        // link.wrapTextProperty().set(!link.wrapTextProperty().get());
        // }
        // });
        link.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                // count.add(1);
                count.set(count.get() + 1);
            }
        });

        root.getChildren().addAll(link, counter, underline, newline, visit);

        Scene scene = new Scene(root, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("A");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

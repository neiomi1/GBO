package ueb.ueb07.a02;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class View
{

    private VBox root;

    private Label welcome;

    private Label vocable;

    private TextField translation;

    private Button check;

    private Button next;

    private Label status;

    public void initView(Presenter p)
    {
        root = new VBox(10);
        root.setMaxWidth(Double.MAX_VALUE);
        root.setPadding(new Insets(10));
        welcome = new Label("Bitte übersetzen sie:");
        HBox translations = new HBox(10);
        vocable = new Label();
        vocable.setPrefWidth(100);
        vocable.setId("vocable");
        translation = new TextField();
        translation.setPrefWidth(200);
        translation.setId("translation");
        HBox buttons = new HBox(10);
        next = new Button("Weiter");
        next.setId("next");
        next.setOnAction(e -> p.nextVocab());
        check = new Button("Überprüfen");
        check.setId("check");
        check.setOnAction(e -> p.checkVocab(translation.getText()));
        status = new Label();
        status.setPrefWidth(350);

        translations.getChildren().addAll(vocable, translation);
        buttons.getChildren().addAll(check, next);
        root.getChildren().addAll(welcome, translations, buttons, status);

        p.initVocab();
    }

    public Pane getUI()
    {
        return root;
    }

    public void updateStatus(String stat)
    {
        this.status.setText(stat);
    }

    public void updateVocab(String vocab)
    {
        vocable.setText(vocab);
    }
}

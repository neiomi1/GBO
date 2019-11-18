package tutorial.woche_06;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class View
{

    // private Presenter presenter;

    private VBox root;

    private Label counterLabel;

    private ProgressBar progressBar; // immer auf 100% da kein MaxValue zu vgl;

    public void initView(Presenter presenter, int initValue)
    {
        // this.presenter = presenter;
        root = new VBox(10);
        root.setPadding(new Insets(10));
        counterLabel = new Label("" + initValue);
        progressBar = new ProgressBar(initValue / 100.0);
        progressBar.setMaxWidth(Double.MAX_VALUE);

        HBox buttons = new HBox(10);
        Button plus = new Button("+");
        plus.setOnAction(e -> presenter.plus());
        Button minus = new Button("-");
        minus.setOnAction(e -> presenter.minus());
        Button reset = new Button("0");
        reset.setOnAction(e -> presenter.setZero());

        buttons.getChildren().addAll(plus, minus, reset);
        root.getChildren().addAll(counterLabel, buttons, progressBar);
    }

    public Pane getUI()
    {
        return root;
    }

    public void update(int newValue)
    {
        counterLabel.setText("" + newValue);
        progressBar.setProgress(newValue / 100.0);
    }
}

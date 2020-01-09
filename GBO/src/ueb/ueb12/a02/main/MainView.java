package ueb.ueb12.a02.main;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainView extends BorderPane
{
    private MainPresenter presenter;

    private HBox topButtons;

    private Button startQuiz;

    private Button continueQuiz;

    private Button answerOverview;

    public MainView()
    {
        topButtons = new HBox(10);

        startQuiz = new Button("Quiz starten!");
        startQuiz.setOnAction(e -> presenter.startQuiz());

        continueQuiz = new Button("Quiz fortsetzen!");
        continueQuiz.setOnAction(e -> presenter.continueQuiz());

        answerOverview = new Button("Überblick!");
        answerOverview.setId("overview");
        answerOverview.setOnAction(e -> presenter.showAnswers());

        topButtons.getChildren().addAll(startQuiz, continueQuiz, answerOverview);
        setTop(topButtons);
    }

    public void setMainPresenter(MainPresenter mainPresenter)
    {
        this.presenter = mainPresenter;
    }

    public void setContent(Pane content)
    {
        setCenter(content);
    }
}

package gui.mvp.editquiz.main;

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

    private Button editQuiz;

    public MainView()
    {
        topButtons = new HBox(10);

        startQuiz = new Button("Quiz starten!");
        startQuiz.setOnAction(e -> presenter.startQuiz());

        continueQuiz = new Button("Quiz fortsetzen!");
        continueQuiz.setOnAction(e -> presenter.continueQuiz());

        answerOverview = new Button("\u00dcberblick!");
        answerOverview.setId("overview");
        answerOverview.setOnAction(e -> presenter.showAnswers());

        editQuiz = new Button("Quiz editieren!");
        editQuiz.setOnAction(e -> presenter.editQuiz());

        topButtons.getChildren().addAll(startQuiz, continueQuiz, answerOverview, editQuiz);
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

    public void enableContinueButton()
    {
        this.continueQuiz.setDisable(false);
    }

    public void disableContinueButton()
    {
        this.continueQuiz.setDisable(true);
    }
}

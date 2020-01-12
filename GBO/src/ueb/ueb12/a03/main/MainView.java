package ueb.ueb12.a03.main;

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

    private Button undoChange;

    private Button redoChange;

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

        editQuiz = new Button("Quiz editieren!");
        editQuiz.setOnAction(e -> presenter.editQuiz());

        undoChange = new Button("R\u00fcckgängig!");
        undoChange.setId("undo");
        undoChange.setOnAction(e -> presenter.undo());
        undoChange.setDisable(true);

        redoChange = new Button("Wiederholen!");
        redoChange.setId("redo");
        redoChange.setOnAction(e -> presenter.redo());
        redoChange.setDisable(true);

        topButtons.getChildren().addAll(startQuiz, continueQuiz, answerOverview, editQuiz, undoChange, redoChange);
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

    public void enableUndoButton()
    {
        this.undoChange.setDisable(false);
    }

    public void disableUndoButton()
    {
        this.undoChange.setDisable(true);
    }

    public void enableRedoButton()
    {
        this.redoChange.setDisable(false);
    }

    public void disableRedoButton()
    {
        this.redoChange.setDisable(true);
    }

    public void disableContinueButton()
    {
        this.continueQuiz.setDisable(true);
    }
}

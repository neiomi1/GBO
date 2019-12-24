package gui.mvp.undoredoquiz.main;

import gui.mvp.undoredoquiz.editor.EditorPresenter;
import gui.mvp.undoredoquiz.game.QuizPresenter;
import gui.mvp.undoredoquiz.model.Model;
import gui.mvp.undoredoquiz.overview.OverviewPresenter;
import javafx.scene.layout.Pane;

public class MainPresenter
{

    private MainView mainView;

    private OverviewPresenter overviewPresenter;

    private QuizPresenter quizPresenter;

    private EditorPresenter editorPresenter;

    private Model model;

    public MainPresenter()
    {
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public void setMainView(MainView mainView)
    {
        this.mainView = mainView;
        this.quizPresenter.setCurrentQuestion();
        this.mainView.setContent(this.quizPresenter.getCurrentQuestion());
    }

    public void setOverviewPresenter(OverviewPresenter overviewPresenter)
    {
        this.overviewPresenter = overviewPresenter;
    }

    public void setQuizPresenter(QuizPresenter quizPresenter)
    {
        this.quizPresenter = quizPresenter;
    }

    public void setEditorPresenter(EditorPresenter editorPresenter)
    {
        this.editorPresenter = editorPresenter;
    }

    public void startQuiz()
    {
        model.newQuiz();
        mainView.enableContinueButton();
        quizPresenter.enableButton();
        this.quizPresenter.setCurrentQuestion();
        mainView.setContent(quizPresenter.getCurrentQuestion());
    }

    public void continueQuiz()
    {
        mainView.setContent(quizPresenter.getCurrentQuestion());
    }

    public void showAnswers()
    {
        mainView.setContent(overviewPresenter.getStatistics());
    }

    public void editQuiz()
    {
        checkButtonStatus();
        mainView.disableContinueButton();
        mainView.setContent(editorPresenter.getView());
    }

    public void undo()
    {
        mainView.setContent(editorPresenter.getView());
        mainView.disableContinueButton();
        editorPresenter.undo();
        checkButtonStatus();
    }

    public void redo()
    {
        mainView.setContent(editorPresenter.getView());
        mainView.disableContinueButton();
        editorPresenter.redo();
        checkButtonStatus();
    }

    public void enableUndoButton()
    {
        mainView.enableUndoButton();
    }

    public Pane getView()
    {
        return this.mainView;
    }

    public void checkButtonStatus()
    {
        if (!editorPresenter.canUndo())
        {
            mainView.disableUndoButton();
        }
        else
        {
            mainView.enableUndoButton();
        }
        if (!editorPresenter.canRedo())
        {
            mainView.disableRedoButton();
        }
        else
        {
            mainView.enableRedoButton();
        }
    }

}

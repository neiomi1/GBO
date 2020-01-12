package ueb.ueb12.a03.main;

import javafx.scene.layout.Pane;
import ueb.ueb12.a03.editor.EditorPresenter;
import ueb.ueb12.a03.game.QuizPresenter;
import ueb.ueb12.a03.model.Model;
import ueb.ueb12.a03.overview.OverviewPresenter;

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
        mainView.disableRedoButton();
        mainView.disableUndoButton();
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
        editorPresenter.undo();
        checkButtonStatus();
    }

    public void redo()
    {
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

    private void checkButtonStatus()
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

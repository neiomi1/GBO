package ueb.ueb12.a02.main;

import javafx.scene.layout.Pane;
import ueb.ueb12.a02.game.QuizPresenter;
import ueb.ueb12.a02.model.Model;
import ueb.ueb12.a02.overview.OverviewPresenter;

public class MainPresenter
{

    private MainView mainView;

    private OverviewPresenter overviewPresenter;

    private QuizPresenter quizPresenter;

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

    public void startQuiz()
    {
        model.newQuiz();
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

    public Pane getView()
    {
        return this.mainView;
    }

}

package ueb.ueb12.a01.undoredoquiz.game;

import javafx.scene.layout.Pane;
import ueb.ueb12.a01.undoredoquiz.model.Model;
import ueb.ueb12.a01.undoredoquiz.model.Question;

public class QuizPresenter
{

    private Model model;

    private QuizView quizView;

    private Question question;

    public QuizPresenter()
    {

    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public void setQuizView(QuizView quizView)
    {
        this.quizView = quizView;
    }

    public Pane getNextQuestion()
    {
        this.question = model.getNextQuestion();
        if (this.question == null)
        {
            quizView.disableButton();
            quizView.showEnd();

        }
        else
        {
            quizView.setQuestion(question.getQuestion(), question.getPossibleAnswers());
        }
        return quizView;
    }

    public void setCurrentQuestion()
    {
        this.question = model.getCurrentQuestion();
        quizView.setQuestion(question.getQuestion(), question.getPossibleAnswers());
    }

    public Pane getCurrentQuestion()
    {

        return quizView;
    }

    public void checkAnswer()
    {
        model.checkAnswer(question, quizView.getSelectedAnswer());
        getNextQuestion();
    }

    public void enableButton()
    {
        quizView.enableButton();
    }

    public int getSelectedId()
    {
        return quizView.getSelectedAnswer();
    }

    public void setSelectedId(int id)
    {
        quizView.setSelectedAnswer(id);
    }
}

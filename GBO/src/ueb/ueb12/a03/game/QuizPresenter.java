package ueb.ueb12.a03.game;

import java.util.List;

import javafx.scene.layout.Pane;
import ueb.ueb12.a03.model.Model;
import ueb.ueb12.a03.model.Question;

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
            quizView.setQuestion(question.getQuestion(), question.getPossibleAnswers(), question.getIndexOfCorrectAnswer() < 0);
        }
        return quizView;
    }

    public void setCurrentQuestion()
    {
        this.question = model.getCurrentQuestion();
        System.out.println(question.toString());
        boolean flag = question.getIndexOfCorrectAnswer() < 0;
        quizView.setQuestion(question.getQuestion(), question.getPossibleAnswers(), flag);
    }

    public Pane getCurrentQuestion()
    {

        return quizView;
    }

    public void checkAnswer()
    {
        model.checkAnswer(question, quizView.getSelectedAnswers(), quizView.getSelectedAnswer());
        getNextQuestion();
    }

    public void enableButton()
    {
        quizView.enableButton();
    }

    public List<String> getSelectedAnswers()
    {
        return quizView.getSelectedAnswers();
    }

    public void setSelectedId(List<String> selectedAnswers)
    {
        quizView.setSelectedAnswers(selectedAnswers);
    }
}

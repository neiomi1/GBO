package ueb.ueb12.a02.model;

import java.util.ArrayList;
import java.util.List;

public class Model
{
    private int counter;

    private List<Question> list;

    public Model()
    {
        list = new ArrayList<Question>();
    }

    public void addQuestion(Question question)
    {
        list.add(question);
    }

    public void resetHistory()
    {
        for (Question q : list)
        {
            q.resetAnswers();
        }
    }

    public void newQuiz()
    {
        counter = 0;
    }

    public Question getCurrentQuestion()
    {
        return list.get(counter);
    }

    public Question getNextQuestion()
    {
        counter++;
        if (counter == list.size())
        {
            return null;
        }
        return getCurrentQuestion();
    }

    public void checkAnswer(Question question, List<String> answer)
    {
        if (answer.size() == question.getNumberOfAnswers())
        {
            question.check(answer);
        }
    }

    public List<Question> getQuestions()
    {
        return this.list;
    }
}

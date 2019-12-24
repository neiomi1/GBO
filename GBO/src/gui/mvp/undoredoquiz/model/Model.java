package gui.mvp.undoredoquiz.model;

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

    public void checkAnswer(Question question, int answer)
    {
        if (answer != -1)
        {
            question.check(answer);
        }
    }

    public List<Question> getQuestions()
    {
        return this.list;

    }

    public void removeQuestion(Question question)
    {
        this.list.remove(question);
    }

    public int getIndex(Question q)
    {
        return this.list.indexOf(q);
    }

    public void replaceQuestion(Question oldQuestion, Question newQuestion, int index)
    {
        if (index < list.size() && oldQuestion != null)
        {
            this.list.remove(index);
        }
        for (Question q : list)
        {
            System.err.println(q);
        }
        this.list.add(index, newQuestion);
        for (Question q : list)
        {
            System.err.println(q);
        }
    }
}

package ueb.ueb12.a02.model;

import java.util.Arrays;
import java.util.List;

public class Question
{
    private String question;

    private String[] possibleAnswers;

    private int answers;

    private int correctAnswers;

    public Question(String question, String[] possibleAnswers)
    {
        if (possibleAnswers.length > 1 && !question.isBlank())
        {
            this.question = question;
            this.possibleAnswers = possibleAnswers;
        }
    }

    public String getQuestion()
    {
        return question;
    }

    public String[] getPossibleAnswers()
    {
        return possibleAnswers;
    }

    public void check(List<String> answer)
    {
        boolean flag = true;
        this.answers++;
        for (int i = 0; i < possibleAnswers.length; i++)
        {
            if (!possibleAnswers[i].contentEquals(answer.get(i)))
            {
                flag = false;
                break;
            }
        }
        if (flag)
        {
            correctAnswers += 1;
        }
    }

    public int getNumberOfAnswers()
    {
        return this.possibleAnswers.length;
    }

    public void resetAnswers()
    {
        this.answers = 0;
        this.correctAnswers = 0;

    }

    @Override
    public String toString()
    {
        System.err.println(Arrays.deepToString(possibleAnswers));
        return String.format("%s (Antworten: %d, davon richtig: %d)", question, answers, correctAnswers);
    }

}

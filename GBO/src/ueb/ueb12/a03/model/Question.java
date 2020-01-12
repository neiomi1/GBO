package ueb.ueb12.a03.model;

import java.util.Arrays;
import java.util.List;

public class Question
{
    private String question;

    private String[] possibleAnswers;

    private int indexOfCorrectAnswer;

    private int answers;

    private int correctAnswers;

    public Question(String question, String[] possibleAnswers, int indexOfCorrectAsnwer)
    {
        if (possibleAnswers.length > 1 && !question.isBlank() && indexOfCorrectAsnwer < possibleAnswers.length)
        {
            this.indexOfCorrectAnswer = indexOfCorrectAsnwer;
            this.question = question;
            this.possibleAnswers = possibleAnswers;
        }
    }

    public String getQuestion()
    {
        return question;
    }

    public int getIndexOfCorrectAnswer()
    {
        return indexOfCorrectAnswer;
    }

    public String[] getPossibleAnswers()
    {
        return possibleAnswers;
    }

    public void check(List<String> answer, int indexOfAnswer)
    {
        if (this.indexOfCorrectAnswer < 0)
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
                correctAnswers++;
            }
        }
        else
        {
            if (this.indexOfCorrectAnswer == indexOfAnswer)
            {
                correctAnswers++;
            }
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

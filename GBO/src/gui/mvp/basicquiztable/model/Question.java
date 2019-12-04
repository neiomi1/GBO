package gui.mvp.basicquiztable.model;

public class Question
{
    private String question;

    private String[] possibleAnswers;

    private int indexOfCorrectAnswer;

    public int getAnswers()
    {
        return answers;
    }

    public int getCorrectAnswers()
    {
        return correctAnswers;
    }

    private int answers;

    private int correctAnswers;

    public Question(String question, String[] possibleAnswers, int indexOfCorrectAnswer)
    {
        if (possibleAnswers.length <= indexOfCorrectAnswer || indexOfCorrectAnswer < 0)
        {
            throw new IllegalArgumentException("Die richtige Antwort existiert nicht.");
        }
        this.question = question;
        this.possibleAnswers = possibleAnswers;

    }

    public String getQuestion()
    {
        return question;
    }

    public String[] getPossibleAnswers()
    {
        return possibleAnswers;
    }

    public int getIndexOfCorrectAnswer()
    {
        return indexOfCorrectAnswer;
    }

    public void check(int answer)
    {
        this.answers++;
        if (this.indexOfCorrectAnswer == answer)
        {
            this.correctAnswers++;
        }
    }

    public void resetAnswers()
    {
        this.answers = 0;
        this.correctAnswers = 0;

    }

    @Override
    public String toString()
    {
        return String.format("%s (Antworten: %d, davon richtig: %d)", question, answers, correctAnswers);
    }

}

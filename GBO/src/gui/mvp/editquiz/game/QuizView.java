package gui.mvp.editquiz.game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class QuizView extends VBox
{
    private QuizPresenter quizPresenter;

    private ToggleGroup possibleAnswers;

    private Label title;

    private Button next;

    public QuizView()
    {
        next = new Button("=>");
        next.setOnAction(e -> quizPresenter.checkAnswer());

        possibleAnswers = new ToggleGroup();

        title = new Label("");
        title.setId("question");

        getChildren().addAll(title, next);
    }

    public void setQuizPresenter(QuizPresenter quizPresenter)
    {
        this.quizPresenter = quizPresenter;
    }

    public void setQuestion(String question, String[] possibleQuestionAnswers)
    {
        possibleAnswers.getToggles().clear();
        // getChildren().clear();
        getChildren().setAll(title, next);
        this.title.setText(question);
        for (String s : possibleQuestionAnswers)
        {
            RadioButton r = new RadioButton(s);
            r.setToggleGroup(this.possibleAnswers);
            getChildren().add(getChildren().size() - 1, r);
        }
    }

    public int getSelectedAnswer()
    {
        return possibleAnswers.getToggles().indexOf(possibleAnswers.getSelectedToggle());
    }

    public void showEnd()
    {
        this.title.setText("Ende des Quiz");
        getChildren().setAll(title, next);
    }

    public void disableButton()
    {
        this.next.setDisable(true);
    }

    public void enableButton()
    {
        this.next.setDisable(false);
    }

    public void setSelectedAnswer(int id)
    {
        possibleAnswers.selectToggle(possibleAnswers.getToggles().get(id));
    }
}

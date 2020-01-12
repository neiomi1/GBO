package ueb.ueb12.a03.overview;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import ueb.ueb12.a03.model.Question;

public class OverviewView extends VBox
{
    private Label greet;

    private OverviewPresenter presenter;

    private ListView<Question> questions;

    private Button deleteAnswers;

    public OverviewView()
    {
        greet = new Label("Übersicht");

        questions = new ListView<Question>();
        questions.setId("overviewList");

        deleteAnswers = new Button("Ergebnisse l�schen");
        deleteAnswers.setId("deleteHistory");

        deleteAnswers.setOnAction(e -> presenter.clear());
        getChildren().addAll(greet, questions, deleteAnswers);
    }

    public void setOverviewPresenter(OverviewPresenter overviewPresenter)
    {
        this.presenter = overviewPresenter;
    }

    public void showStats(List<Question> list)
    {
        this.questions.getItems().setAll(list);
    }

}

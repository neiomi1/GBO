package gui.mvp.basicquiz.overview;

import java.util.List;

import gui.mvp.basicquiz.model.Question;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class OverviewView extends VBox
{
    private Label greet;

    private OverviewPresenter presenter;

    private ListView<Question> questions;

    private Button deleteAnswers;

    public OverviewView()
    {
        greet = new Label("�bersicht");

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

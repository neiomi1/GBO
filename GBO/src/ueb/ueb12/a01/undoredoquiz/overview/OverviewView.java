package ueb.ueb12.a01.undoredoquiz.overview;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import ueb.ueb12.a01.undoredoquiz.model.Question;

public class OverviewView extends VBox
{
    private Label greet;

    private OverviewPresenter presenter;

    private TableView<Question> questions;

    private ObservableList<Question> data;

    private Button deleteAnswers;

    public OverviewView()
    {
        data = FXCollections.observableArrayList();
        greet = new Label("\u00dcbersicht");

        questions = new TableView<Question>(data);
        questions.setId("overviewTable");

        TableColumn<Question, String> nameCol = new TableColumn<Question, String>("Frage");
        nameCol.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        questions.getColumns().add(nameCol);
        nameCol.setId("questionCol");

        TableColumn<Question, Number> answerCol = new TableColumn<Question, Number>("Antworten");
        answerCol.setCellValueFactory(new PropertyValueFactory<Question, Number>("answers"));
        questions.getColumns().add(answerCol);
        answerCol.setId("totalAnswerCol");

        TableColumn<Question, Number> correctAnswerCol = new TableColumn<Question, Number>("Richtige Antworten");
        correctAnswerCol.setCellValueFactory(new PropertyValueFactory<Question, Number>("correctAnswers"));
        questions.getColumns().add(correctAnswerCol);
        correctAnswerCol.setId("correctAnswerCol");

        deleteAnswers = new Button("Ergebnisse l\u00f6schen");
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
        this.data.clear();
        System.out.println("List größe");
        System.out.println(list.size());
        for (Question q : list)
        {
            this.data.add(q);
        }
    }

}

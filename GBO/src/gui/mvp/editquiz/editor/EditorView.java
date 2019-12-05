package gui.mvp.editquiz.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import gui.mvp.editquiz.model.Question;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class EditorView extends VBox
{

    private EditorPresenter editorPresenter;

    private Label useless;

    private ListView<Question> questions;

    private HBox buttons;

    private Button addQuestion;

    private Button editQuestion;

    private Button removeQuestion;

    private Alert alert;

    private Dialog<Question> dialog = new Dialog<Question>();

    private TextArea questionText = new TextArea();

    private VBox answers;

    private Button addAnswer;

    private VBox dialogBox;

    private ToggleGroup correctAnswerToggle;

    private HBox editButtons;

    private Button change, cancel;

    private int userdataCount;

    private int correctAnswerIndex;

    public EditorView()
    {
        this.useless = new Label("Editor");

        this.buttons = new HBox();

        this.questions = new ListView<Question>();
        this.questions.setCellFactory(new Callback<ListView<Question>, ListCell<Question>>()
        {

            @Override
            public ListCell<Question> call(ListView<Question> arg0)
            {
                ListCell<Question> cell = new ListCell<Question>()
                {
                    protected void updateItem(Question item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (item != null)
                        {
                            setText(item.getQuestion());
                        }
                    }
                };
                return cell;
            }

        });

        this.addQuestion = new Button("Frage hinzufügen");
        this.addQuestion.setId("addQuestion");
        this.addQuestion.setOnAction(e -> addDialog());

        this.editQuestion = new Button("Frage editieren");
        this.editQuestion.setId("editQuestion");
        this.editQuestion.setOnAction(e -> editDialog());

        this.removeQuestion = new Button("Frage löschen");
        this.removeQuestion.setId("removeQuestion");
        this.removeQuestion.setOnAction(e -> removeDialog());

        this.buttons.getChildren().addAll(this.addQuestion, this.editQuestion, this.removeQuestion);

        getChildren().addAll(this.useless, this.questions, this.buttons);

        this.addAnswer = new Button("Antwort hinzuf\u00fcgen");
        this.addAnswer.setOnAction(e -> addAnswerFrame(""));

        this.dialogBox = new VBox();
        this.correctAnswerToggle = new ToggleGroup();
        this.answers = new VBox();
        this.alert = new Alert(AlertType.CONFIRMATION);

        editButtons = new HBox();
        editButtons.setAlignment(Pos.BASELINE_RIGHT);

        this.change = new Button("ndern");
        this.change.setOnAction(e -> editorPresenter.saveQuestion(dialogBox));

        this.cancel = new Button("Abbrechen");
        this.cancel.setOnAction(e -> dialog.close());

        editButtons.getChildren().addAll(cancel, change);

        dialogBox.getChildren().addAll(useless, questionText, addAnswer, answers, editButtons);

        dialog.getDialogPane().setContent(dialogBox);

    }

    public void setEditorPresenter(EditorPresenter editorPresenter)
    {
        this.editorPresenter = editorPresenter;
    }

    public void updateList(List<Question> list)
    {
        this.questions.getItems().setAll(list);
    }

    public void addDialog()
    {
    };

    public void editDialog()
    {
        Question selectedItem = this.questions.getSelectionModel().getSelectedItem();

        editorPresenter.editQuestion(selectedItem);

    };

    public void removeDialog()
    {
        editorPresenter.removeQuestion(this.questions.getSelectionModel().getSelectedItem());
    };

    public void showNoSelectedQuestionAlert()
    {
        alert.setAlertType(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("");
        alert.setContentText("Es muss eine Frage ausgew\u00e4hlt werden!");

        alert.showAndWait();
    }

    public ButtonType showDeleteQuestionAlert()
    {
        alert.setAlertType(AlertType.CONFIRMATION);
        alert.setTitle("Frage");
        alert.setHeaderText("");
        alert.setContentText("Soll diese Frage wirklich gel\u00f6scht werden?");

        Optional<ButtonType> option = alert.showAndWait();

        return option.get();

    }

    public void showEditDialog(Question question)
    {
        correctAnswerIndex = editorPresenter.getCorrectAnswer();
        System.out.println(correctAnswerIndex);

        userdataCount = 0;
        correctAnswerToggle.getToggles().clear();
        answers.getChildren().clear();

        useless.setText("Frage: ");
        questionText.setText(question.getQuestion());

        for (String answer : question.getPossibleAnswers())
        {
            addAnswerFrame(answer);
        }
        dialog.showAndWait();
    }

    public void addAnswerFrame(String s)
    {
        HBox frame = new HBox();
        RadioButton button = new RadioButton();
        button.setUserData(String.format("%d", userdataCount));
        if (correctAnswerIndex == userdataCount)
        {
            button.setSelected(true);
        }
        button.setToggleGroup(correctAnswerToggle);
        TextField answ = new TextField(s);
        Button del = new Button("L\u00f6schen");
        del.setOnAction(e -> answers.getChildren().remove(frame));

        frame.getChildren().addAll(button, answ, del);
        answers.getChildren().add(frame);
        userdataCount++;
    }

    public Question getSelected()
    {
        return questions.getSelectionModel().getSelectedItem();
    }

    public String getQuestionText()
    {
        return questionText.getText();
    }

    public String[] getpossibleAnswers()
    {
        ArrayList<String> temp = new ArrayList<String>();
        for (Node n : answers.getChildren())
        {
            HBox h = (HBox) n;
            temp.add(((TextField) h.getChildren().get(1)).getText());
        }
        return temp.toArray(new String[0]);
    }

    public int getCorrectAnswer()
    {
        System.out.println(correctAnswerToggle.getSelectedToggle().getUserData());
        return Integer.parseInt(correctAnswerToggle.getSelectedToggle().getUserData().toString());
    }
}

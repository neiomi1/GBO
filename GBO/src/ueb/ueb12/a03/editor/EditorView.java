package ueb.ueb12.a03.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import ueb.ueb12.a03.model.Question;

public class EditorView extends VBox
{

    private static final String[] ANSWER_TYPES = new String[]
    { "DragAndDrop", "Select" };

    private GridPane typeSelector;

    private EditorPresenter editorPresenter;

    private Label useless;

    private ListView<Question> questions;

    private HBox buttons;

    private Button addQuestion;

    private Button editQuestion;

    private Button removeQuestion;

    private Alert alert;

    // !! RESULTCONVERTER + Dialog<Question>
    private Dialog<ButtonType> dialog = new Dialog<ButtonType>();

    private TextArea questionText = new TextArea();

    private VBox answers;

    private Button addAnswer;

    private VBox dialogBox;

    private ToggleGroup correctAnswerToggle, answerTypes;

    private HBox editButtons;

    private ButtonType update, add, cancel, accept;

    private int userdataCount;

    private int correctAnswerIndex;

    private boolean isDrag;

    private String tempString;

    public EditorView()
    {
        this.useless = new Label("Editor");

        this.buttons = new HBox();

        this.questions = new ListView<Question>();
        this.questions.setId("editorList");
        this.questions.setCellFactory(new Callback<ListView<Question>, ListCell<Question>>()
        {

            @Override
            public ListCell<Question> call(ListView<Question> self)
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
                        else
                        {
                            setText("");
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
        this.removeQuestion.setId("deleteQuestion");
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

        this.questionText.setId("dialogQuestion");

        this.add = new ButtonType("Hinzuf\u00fcgen", ButtonData.APPLY);
        this.update = new ButtonType("\u00c4ndern", ButtonData.APPLY);
        this.cancel = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);

        // editButtons.getChildren().addAll(cancel, change);

        dialogBox.getChildren().addAll(useless, questionText, addAnswer, answers);

        dialog.getDialogPane().setContent(dialogBox);
        dialog.setResizable(true);

        typeSelector = new GridPane();
        answerTypes = new ToggleGroup();
        for (int i = 0; i < ANSWER_TYPES.length; i++)
        {
            RadioButton b = new RadioButton();
            b.setToggleGroup(answerTypes);
            Label l = new Label(ANSWER_TYPES[i]);
            typeSelector.add(b, 0, 2 + i * 2);
            typeSelector.add(l, 1, 2 + 2 * i);
        }
        Label command = new Label("W\u00e4hlen sie einen Fragetyp aus: ");
        typeSelector.add(command, 1, 0);
        accept = new ButtonType("Ausw\u00e4hlen", ButtonData.APPLY);

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
        this.editorPresenter.addQuestion();
    };

    public ButtonType addQuestionTypeDialog()
    {
        if (answerTypes.getSelectedToggle() != null)
        {
            answerTypes.getSelectedToggle().setSelected(false);
        }
        Dialog<ButtonType> questionTypeDialog = new Dialog<ButtonType>();
        questionTypeDialog.setResizable(true);
        questionTypeDialog.setTitle("Fragetyp Auswahl");
        questionTypeDialog.getDialogPane().setContent(typeSelector);
        questionTypeDialog.getDialogPane().getButtonTypes().addAll(cancel, accept);
        Optional<ButtonType> b = questionTypeDialog.showAndWait();
        if (b.isPresent())
        {
            return b.get();
        }
        return null;
    }

    public String getSelectedQuestionType()
    {
        if (answerTypes.getSelectedToggle() != null)
        {
            return ANSWER_TYPES[answerTypes.getToggles().indexOf(answerTypes.getSelectedToggle())];
        }
        else
        {
            return null;
        }
    }

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
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("");
        alert.setContentText("Es muss eine Frage ausgew\u00e4hlt werden!");

        Optional<ButtonType> b = alert.showAndWait();
        if (b.isPresent() && b.get() == ButtonType.OK)
        {
            alert.close();
        }
    }

    public ButtonType showDeleteQuestionAlert()
    {
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Frage");
        alert.setHeaderText("");
        alert.setContentText("Soll diese Frage wirklich gel\u00f6scht werden?");

        Optional<ButtonType> b = alert.showAndWait();
        if (b.isPresent())
        {
            return b.get();
        }
        else
        {
            return null;
        }

    }

    public ButtonType showAddDialog()
    {
        if (getSelectedQuestionType().contentEquals("DragAndDrop"))
        {
            isDrag = true;
        }
        else
        {
            isDrag = false;
        }
        userdataCount = 0;
        correctAnswerToggle.getToggles().clear();
        answers.getChildren().clear();
        useless.setText("Frage: ");
        dialog.getDialogPane().getButtonTypes().setAll(add, cancel);
        Optional<ButtonType> b = dialog.showAndWait();
        if (b.isPresent())
        {
            return b.get();
        }
        else
        {
            return null;
        }
    }

    public ButtonType showEditDialog(Question question)
    {
        isDrag = false;
        if (question.getIndexOfCorrectAnswer() < 0)
        {
            isDrag = true;
        }
        correctAnswerIndex = editorPresenter.getCorrectAnswer();
        System.out.println(correctAnswerIndex);

        userdataCount = 0;
        correctAnswerToggle.getToggles().clear();
        answers.getChildren().clear();

        useless.setText("Frage: ");
        questionText.setText(question.getQuestion());
        dialog.getDialogPane().getButtonTypes().setAll(update, cancel);
        for (String answer : question.getPossibleAnswers())
        {
            addAnswerFrame(answer);
        }
        Optional<ButtonType> b = dialog.showAndWait();
        if (b.isPresent())
        {
            return b.get();
        }
        else
        {
            return null;
        }

    }

    public void addAnswerFrame(String s)
    {

        HBox frame = new HBox();
        TextField answ = new TextField(s);
        answ.setFont(Font.font("Arial", 15));
        if (isDrag)
        {
            Label number = new Label(String.format("%d.", userdataCount + 1));
            number.setFont(Font.font("Arial", 20));
            frame.getChildren().add(number);
            answ.setOnDragDetected(e -> onDragDetected(e));
            answ.setOnDragEntered(e -> onDragEntered(e));
            answ.setOnDragOver(e -> onDragOver(e));
            answ.setOnDragExited(e -> onDragExited(e));
            answ.setOnDragDropped(e -> onDragDropped(e));
            answ.setOnDragDone(e -> onDragDone(e));
        }
        else
        {
            RadioButton button = new RadioButton();
            button.setUserData(String.format("%d", userdataCount));
            if (correctAnswerIndex == userdataCount)
            {
                button.setSelected(true);
            }
            button.setToggleGroup(correctAnswerToggle);
            frame.getChildren().add(button);
        }

        Button del = new Button("L\u00f6schen");
        del.setOnAction(e -> answers.getChildren().remove(frame));

        frame.getChildren().addAll(answ, del);
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
        try
        {
            return Integer.parseInt(correctAnswerToggle.getSelectedToggle().getUserData().toString());
        }
        catch (NullPointerException e)
        {
            return -1;
        }
    }

    private void onDragDetected(MouseEvent e)
    {
        TextField source = (TextField) e.getSource();
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(source.getText());
        db.setContent(content);
    }

    private void onDragOver(DragEvent e)
    {
        TextField target = (TextField) e.getSource();
        if (e.getGestureSource() != target && e.getDragboard().hasString())
        {
            e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
    }

    private void onDragEntered(DragEvent e)
    {

    }

    private void onDragExited(DragEvent e)
    {

    }

    private void onDragDropped(DragEvent e)
    {
        TextField target = (TextField) e.getSource();
        Dragboard db = e.getDragboard();
        tempString = target.getText();
        target.setText(db.getString());
        e.setDropCompleted(true);
    }

    private void onDragDone(DragEvent e)
    {
        System.out.println("Drag done");
        TextField source = (TextField) e.getSource();
        if (e.getTransferMode() == TransferMode.MOVE)
        {
            source.setText(tempString);
        }
    }

}

package gui.mvp.dndquiz.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class QuizView extends VBox
{
    private QuizPresenter quizPresenter;

    private GridPane answerBoard;

    private GridPane answerContainer;

    private Label title;

    private Button next;

    public QuizView()
    {
        setPadding(new Insets(0, 20, 0, 20));
        setSpacing(20);

        next = new Button("=>");
        next.setOnAction(e -> quizPresenter.checkAnswer());

        answerBoard = new GridPane();
        answerBoard.setGridLinesVisible(true);
        ColumnConstraints column1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        ColumnConstraints column2 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        column2.setMaxWidth(Double.MAX_VALUE);
        column2.setHgrow(Priority.ALWAYS);
        answerBoard.getColumnConstraints().addAll(column1, column2);

        answerContainer = new GridPane();
        answerContainer.setGridLinesVisible(true);

        title = new Label("");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setId("question");

        getChildren().addAll(title, answerContainer, answerBoard, next);
    }

    public void setQuizPresenter(QuizPresenter quizPresenter)
    {
        this.quizPresenter = quizPresenter;
    }

    public void setQuestion(String question, String[] possibleQuestionAnswers)
    {
        getChildren().setAll(title, answerContainer, answerBoard, next);
        List<Node> toRemove = new ArrayList<Node>();
        answerBoard.getChildren().stream().forEach(e ->
        {
            if (e instanceof Label)
            {
                if (!((Label) e).getText().isEmpty())
                {
                    toRemove.add(e);
                }
            }
        });
        answerBoard.getChildren().removeAll(toRemove);
        toRemove.clear();

        answerContainer.getChildren().parallelStream().forEach(e ->
        {
            if (e instanceof Label)
            {
                if (!((Label) e).getText().isEmpty())
                {
                    toRemove.add(e);
                }
            }
        });
        answerContainer.getChildren().removeAll(toRemove);

        title.setText(question);
        String[] answerTexts = new String[possibleQuestionAnswers.length];
        for (int i = 0; i < answerTexts.length; i++)
        {
            answerTexts[i] = possibleQuestionAnswers[i];
        }

        Collections.shuffle(Arrays.asList(answerTexts));
        for (int i = 0; i < possibleQuestionAnswers.length; i++)
        {
            Label answerLabel = new Label(answerTexts[i]);
            Label answerSlot = new Label("");
            Label answerNumeration = new Label((i + 1) + ".");
            answerSlot.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            // answerSlot.setBackground(new Background(new
            // BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));

            answerLabel.setFont(Font.font("Arial", 15));
            answerLabel.setMinSize(30, 30);
            answerNumeration.setFont(Font.font("Arial", 15));
            answerNumeration.setMinSize(30, 30);
            answerSlot.setFont(Font.font("Arial", 15));

            answerContainer.add(answerLabel, i, 0);
            GridPane.setMargin(answerLabel, new Insets(5, 5, 5, 5));
            answerBoard.add(answerNumeration, 0, i);
            answerBoard.add(answerSlot, 1, i);

            answerLabel.setOnDragDetected(e -> onDragDetected(e));
            answerLabel.setOnDragDropped(e -> onDragDropped(e));
            answerLabel.setOnDragOver(e -> onDragOver(e));
            answerLabel.setOnDragExited(e -> onDragExited(e));
            answerLabel.setOnDragEntered(e -> onDragEntered(e));
            answerLabel.setOnDragDone(e -> onDragDone(e));

            answerSlot.setOnDragDetected(e -> onDragDetected(e));
            answerSlot.setOnDragDropped(e -> onDragDropped(e));
            answerSlot.setOnDragOver(e -> onDragOver(e));
            answerSlot.setOnDragExited(e -> onDragExited(e));
            answerSlot.setOnDragEntered(e -> onDragEntered(e));
            answerSlot.setOnDragDone(e -> onDragDone(e));
        }

    }

    public List<String> getSelectedAnswers()
    {
        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < answerBoard.getRowCount(); i++)
        {
            for (Node n : answerBoard.getChildren())
            {
                n = (Node) n;
                if (n instanceof Label)
                {
                    Label tempLabel = (Label) n;
                    if (GridPane.getRowIndex(tempLabel) == i && GridPane.getColumnIndex(tempLabel) == 1)
                    {
                        temp.add(tempLabel.getText());
                    }
                }
            }
        }
        return temp;
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

    public void setSelectedAnswers(List<String> selectedAnswers)
    {
        selectedAnswers.stream().forEachOrdered(answerText ->
        {
            answerContainer.getChildren().stream().forEach(e ->
            {
                Label temp = (Label) e;
                if (temp.getText().contentEquals(answerText))
                {
                    answerBoard.add(temp, 1, selectedAnswers.indexOf(answerText) + 1);
                    answerContainer.getChildren().remove(e);
                }
            });
        });
    }

    public void onDragDetected(MouseEvent e)
    {
        System.out.println("Drag detected");
        Label source = (Label) e.getSource();
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(source.getText());
        db.setContent(content);
    }

    private void onDragEntered(DragEvent e)
    {
        System.out.println("onDragEntered");
        Label target = (Label) e.getSource();
        System.out.println(e.getDragboard().getString());
        if (e.getGestureSource() != target && e.getDragboard().hasString())
        {
            target.setTextFill(Color.RED);
        }
    }

    public void onDragOver(DragEvent e)
    {
        System.out.println("Drag over");
        Label target = (Label) e.getSource();
        if (e.getGestureSource() != target && e.getDragboard().hasString())
        {
            e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
    }

    public void onDragDropped(DragEvent e)
    {
        System.out.println("Drag dropped");
        Label target = (Label) e.getSource();
        Dragboard db = e.getDragboard();
        boolean success = false;
        if (db.hasString() && target.getText().isBlank())
        {
            System.out.println("Has string");
            target.setText(db.getString());
            success = true;
        }
        System.out.println("Has no string");
        e.setDropCompleted(success);
    }

    public void onDragDone(DragEvent e)
    {
        System.out.println("Drag done");
        Label source = (Label) e.getSource();
        if (e.getTransferMode() == TransferMode.MOVE)
        {
            source.setText("");
        }
    }

    public void onDragExited(DragEvent e)
    {
        System.out.println("Drag exited");
        Label target = (Label) e.getSource();
        target.setTextFill(Color.BLACK);
    }

}

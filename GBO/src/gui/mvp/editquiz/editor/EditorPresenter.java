package gui.mvp.editquiz.editor;

import gui.mvp.editquiz.model.Model;
import gui.mvp.editquiz.model.Question;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class EditorPresenter
{

    private EditorView editorView;

    private Model model;

    public EditorPresenter()
    {

    }

    public void setEditorView(EditorView editorView)
    {
        this.editorView = editorView;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public Pane getView()
    {
        this.editorView.updateList(model.getQuestions());
        return this.editorView;
    }

    public void editQuestion(Question selectedItem)
    {
        if (selectedItem != null)
        {
            editorView.showEditDialog(selectedItem);
        }
        else
        {
            editorView.showNoSelectedQuestionAlert();
        }
    }

    public void removeQuestion(Question selectedItem)
    {
        if (selectedItem != null)
        {
            ButtonType action = editorView.showDeleteQuestionAlert();
            if (action == ButtonType.OK)
            {
                model.removeQuestion(selectedItem);
                editorView.updateList(model.getQuestions());
            }
        }
        else
        {
            editorView.showNoSelectedQuestionAlert();
        }
    }

    public void saveQuestion(VBox dialogContent)
    {
        Question question = editorView.getSelected();

        question.setPossibleAnswers(editorView.getpossibleAnswers());
        question.setQuestion(editorView.getQuestionText());
        question.setIndexOfCorrectAnswer(editorView.getCorrectAnswer());
    }

    public void addQuestion()
    {
    }

    public int getCorrectAnswer()
    {
        Question question = editorView.getSelected();
        return question.getIndexOfCorrectAnswer();
    }

}

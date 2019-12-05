package gui.mvp.editquiz.editor;

import gui.mvp.editquiz.model.Model;
import gui.mvp.editquiz.model.Question;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

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
            ButtonType action = editorView.showEditDialog(selectedItem);
            System.out.println(action);
            if (action != null && action.getButtonData() == ButtonData.APPLY)
            {
                updateQuestion();
                editorView.updateList(model.getQuestions());
            }
            else
            {

            }
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

    public void saveQuestion()
    {
        int right = editorView.getCorrectAnswer();
        String quest = editorView.getQuestionText();
        String[] answers = editorView.getpossibleAnswers();
        boolean empty = false;
        for (String s : answers)
        {
            if (s.isBlank())
            {
                empty = true;
            }
        }
        if (right == -1 || quest.isBlank() || empty)
        {

        }
        else
        {
            model.addQuestion(new Question(quest, answers, right));
        }
    }

    public void updateQuestion()
    {
        Question question = editorView.getSelected();
        question.setPossibleAnswers(editorView.getpossibleAnswers());
        question.setQuestion(editorView.getQuestionText());
        question.setIndexOfCorrectAnswer(editorView.getCorrectAnswer());

    }

    public void addQuestion()
    {
        ButtonType action = editorView.showAddDialog();
        if (action != null && action.getButtonData() == ButtonData.APPLY)
        {
            saveQuestion();
        }
        editorView.updateList(model.getQuestions());
    }

    public int getCorrectAnswer()
    {
        Question question = editorView.getSelected();
        return question.getIndexOfCorrectAnswer();
    }

}

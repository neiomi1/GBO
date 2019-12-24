package gui.mvp.undoredoquiz.editor;

import gui.mvp.undoredoquiz.main.MainPresenter;
import gui.mvp.undoredoquiz.main.UndoRedoManager;
import gui.mvp.undoredoquiz.model.Model;
import gui.mvp.undoredoquiz.model.Question;
import gui.mvp.undoredoquiz.model.UndoRedoQuestion;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

public class EditorPresenter
{

    private MainPresenter mainPresenter;

    private EditorView editorView;

    private Model model;

    private UndoRedoManager undoRedoManager;

    public EditorPresenter()
    {
        undoRedoManager = new UndoRedoManager();
    }

    public void setMainPresenter(MainPresenter mainPresenter)
    {
        this.mainPresenter = mainPresenter;
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
            System.out.println(selectedItem.toString());
            System.err.println(selectedItem.hashCode());
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
                mainPresenter.enableUndoButton();
                undoRedoManager.addAction(new UndoRedoQuestion(model, editorView, selectedItem, null, model.getIndex(selectedItem)));
                model.removeQuestion(selectedItem);
                editorView.updateList(model.getQuestions());
                mainPresenter.checkButtonStatus();
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
            Question temp = new Question(quest, answers, right);
            mainPresenter.enableUndoButton();
            model.addQuestion(temp);
            undoRedoManager.addAction(new UndoRedoQuestion(model, editorView, null, temp, model.getIndex(temp)));
            mainPresenter.checkButtonStatus();
        }
    }

    public void updateQuestion()
    {
        Question question = editorView.getSelected();
        System.out.println(question.toString());
        Question temp = new Question(editorView.getQuestionText(), editorView.getpossibleAnswers(), editorView.getCorrectAnswer());
        System.out.println(temp.toString());
        System.err.println(question.hashCode() + "  " + temp.hashCode());
        mainPresenter.enableUndoButton();
        undoRedoManager.addAction(new UndoRedoQuestion(model, editorView, question, temp, model.getIndex(question)));
        model.replaceQuestion(question, temp, model.getIndex(question));
        mainPresenter.checkButtonStatus();

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

    public void undo()
    {
        undoRedoManager.undo();
    }

    public void redo()
    {
        undoRedoManager.redo();
    }

    public boolean canUndo()
    {
        return undoRedoManager.canUndo();
    }

    public boolean canRedo()
    {
        return undoRedoManager.canRedo();
    }
}

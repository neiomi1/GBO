package gui.mvp.undoredoquiz.model;

import gui.mvp.undoredoquiz.editor.EditorView;

public class UndoRedoQuestion implements gui.mvp.undoredoquiz.UndoableRedoableAction
{
    private Model model;

    private EditorView view;

    private int index;

    private Question oldQuestion;

    private Question newQuestion;

    public UndoRedoQuestion(Model model, EditorView view, Question oldQuestion, Question newQuestion, int index)
    {
        this.index = index;
        this.model = model;
        this.view = view;
        this.oldQuestion = oldQuestion;
        this.newQuestion = newQuestion;
    }

    @Override
    public void undo()
    {
        if (newQuestion == null)
        {
            model.replaceQuestion(newQuestion, oldQuestion, index);

        }
        else if (oldQuestion == null)
        {
            model.removeQuestion(newQuestion);
        }
        else
        {
            model.replaceQuestion(newQuestion, oldQuestion, index);
        }
        view.updateList(model.getQuestions());

    }

    @Override
    public void redo()
    {
        if (oldQuestion == null)
        {
            model.replaceQuestion(oldQuestion, newQuestion, index);

        }
        else if (newQuestion == null)
        {
            model.removeQuestion(oldQuestion);
        }
        else
        {
            model.replaceQuestion(oldQuestion, newQuestion, index);

        }
        view.updateList(model.getQuestions());
    }

}

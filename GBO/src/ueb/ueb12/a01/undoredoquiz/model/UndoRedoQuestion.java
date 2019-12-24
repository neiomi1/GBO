package ueb.ueb12.a01.undoredoquiz.model;

import ueb.ueb12.a01.undoredoquiz.editor.EditorView;

public class UndoRedoQuestion implements ueb.ueb12.a01.UndoableRedoableAction
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
        if (newQuestion != null)
        {
            model.replaceQuestion(newQuestion, oldQuestion, index);
        }
        else
        {
            model.removeQuestion(oldQuestion);
        }
        view.updateList(model.getQuestions());

    }

    @Override
    public void redo()
    {
        if (oldQuestion != null)
        {
            model.replaceQuestion(oldQuestion, newQuestion, index);
        }
        else
        {
            model.addQuestion(newQuestion);
        }
        view.updateList(model.getQuestions());
    }

}

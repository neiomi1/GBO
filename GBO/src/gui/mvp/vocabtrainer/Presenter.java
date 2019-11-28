package gui.mvp.vocabtrainer;

public class Presenter
{
    private Model model;

    private View view;

    public void setModelAndView(Model m, View v)
    {
        this.model = m;
        this.view = v;
    }

    public void nextVocab()
    {
        view.updateVocab(model.nextVocab());
        view.updateStatus("");
        view.clearField();
    }

    public void checkVocab(String input)
    {
        if (!model.checkTranslation(input))
        {
            view.updateStatus("Die Lösung war falsch. Sie können es gerne nochmals versuchen.");
        }
        else
        {
            view.updateStatus("Die Lösung war richtig.");
        }
    }

    public void initVocab()
    {
        view.updateVocab(model.getVocab());
    }
}

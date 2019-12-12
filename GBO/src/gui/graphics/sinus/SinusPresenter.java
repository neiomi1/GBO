package gui.graphics.sinus;

import javafx.scene.layout.Pane;

public class SinusPresenter
{
    private SinusView sinusView;

    private SinusModel sinusModel;

    public SinusPresenter()
    {
    }

    public void initView()
    {
        sinusView.setFormula(sinusModel.getFormula());
        sinusView.updateCurve(sinusModel.getPoints());
    }

    public void setSinusView(SinusView sinusView)
    {
        this.sinusView = sinusView;
    }

    public void setSinusModel(SinusModel sinusModel)
    {
        this.sinusModel = sinusModel;
    }

    public Pane getView()
    {
        return sinusView;
    }

    public void updateFrequency(double frequency)
    {
        sinusModel.setFrequency(frequency);
        sinusView.setFormula(sinusModel.getFormula());
        sinusView.updateCurve(sinusModel.getPoints());
    }

    public void updateAmplitude(double amplitude)
    {
        sinusModel.setAmplitude(amplitude);
        sinusView.setFormula(sinusModel.getFormula());
        sinusView.updateCurve(sinusModel.getPoints());
    }

    public void updateRange(int range)
    {
        sinusModel.setRange(range);
        sinusView.setFormula(sinusModel.getFormula());
        sinusView.updateCurve(sinusModel.getPoints());
    }

    public void updatePhase(double phase)
    {
        sinusModel.setPhase(phase);
        sinusView.setFormula(sinusModel.getFormula());
        sinusView.updateCurve(sinusModel.getPoints());
    }

}

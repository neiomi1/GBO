package gui.mvp.basicquiz.overview;

import gui.mvp.basicquiz.model.Model;
import javafx.scene.layout.Pane;

public class OverviewPresenter
{

    private OverviewView overviewView;

    private Model model;

    public OverviewPresenter()
    {
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public void setOverviewView(OverviewView overviewView)
    {
        this.overviewView = overviewView;
    }

    public Pane getStatistics()
    {
        this.overviewView.showStats(model.getQuestions());
        return this.overviewView;
    }

    public void clear()
    {
        model.resetHistory();
        this.overviewView.showStats(model.getQuestions());
    }

}

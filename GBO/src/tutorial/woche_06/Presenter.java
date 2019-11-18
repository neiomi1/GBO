package tutorial.woche_06;

public class Presenter
{

    private Model model;

    private View view;

    public void setModelAndView(Model m, View v)
    {
        this.model = m;
        this.view = v;
    }

    public void plus()
    {
        view.update(model.increment());

    }

    public void minus()
    {
        view.update(model.decrement());
    }

    public void setZero()
    {
        view.update(model.reset());
    }
}

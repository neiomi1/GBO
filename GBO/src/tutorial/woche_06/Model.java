package tutorial.woche_06;

public class Model
{

    private int counter;

    public Model(int initValue)
    {
        counter = initValue;
    }

    public Model()
    {
        // Optional:
        this(0);
    }

    public int increment()
    {
        counter++;
        return counter;
    }

    public int decrement()
    {
        counter--;
        return counter;
    }

    public int reset()
    {
        counter = 0;
        return counter;
    }
}

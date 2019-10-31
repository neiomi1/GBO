package gui.counter;

public class Incrementer
{
    private int zahl;

    public Incrementer(int zahl)
    {
        this.zahl = zahl;
    }

    public void increment()
    {
        this.zahl++;
    }

    public int getValue()
    {
        return this.zahl;
    }

}

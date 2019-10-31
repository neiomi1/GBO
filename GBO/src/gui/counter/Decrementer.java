package gui.counter;

public class Decrementer
{
    private int zahl;

    public Decrementer(int zahl)
    {
        this.zahl = zahl;
    }

    public void decrement()
    {
        this.zahl--;
    }

    public int getValue()
    {
        return this.zahl;
    }
}

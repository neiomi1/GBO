package ueb.ueb02.a02;

public class ListenerExample implements ChangeListener
{

    @Override
    public void change(VerySimpleIntegerProperty p, int oldValue, int newValue)
    {

        System.out.println("Property: " + p + " oldValue: " + oldValue + " newValue: " + newValue);
        // sysout -> ctrl + space
    }

}

package ueb.ueb02.a06;

import java.util.ArrayList;
import java.util.List;

public class VerySimpleIntegerProperty
{
    private int value;

    private List<ChangeListener> listeners;

    public VerySimpleIntegerProperty()
    {
        this.listeners = new ArrayList<>();
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        if (this.value != value)
        {
            int temp = this.value;
            this.value = value;
            for (ChangeListener l : this.listeners)
            {
                l.change(this, temp, this.value);
            }
        }
    }

    public void addListener(ChangeListener l)
    {
        this.listeners.add(l);
    }

    public void removeListener(ChangeListener l)
    {
        this.listeners.remove(l);
    }

    public void dropAnonymous(List<ChangeListener> l)
    {
        List<ChangeListener> id = new ArrayList<>();
        for (ChangeListener n : this.listeners)
        {
            if (!(l.contains(n)))
            {
                id.add(n);
            }
        }
        this.listeners.removeAll(id);
    }

    public static void main(String args[])
    {
        ListenerExample l1 = new ListenerExample();
        VerySimpleIntegerProperty p = new VerySimpleIntegerProperty();
        p.addListener(l1);
        for (int i = 1; i < 5; i++)
        {
            System.out.println("==============");
            p.addListener((VerySimpleIntegerProperty prop, int oldValue, int newValue) -> System.out.println("Lamda: " + oldValue + " change: " + newValue));
            p.setValue(i);
        }
        p.setValue(4);

        List<ChangeListener> l = List.of(l1);
        p.dropAnonymous(l);
        System.out.println("===========");
        p.setValue(5);
        System.out.println("===========");
        p.removeListener(l1);
        p.setValue(6);
        System.out.println(p.getValue());

    }

}

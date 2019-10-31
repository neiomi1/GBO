package ueb.ueb03.a01;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public class Bindingtests
{
    // Code cleanup
    private static void addListenersfromList(List<SimpleIntegerProperty> l)
    {
        for (SimpleIntegerProperty x : l)
        {
            x.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> System.out.println("changed from " + oldValue + " to " + newValue));
        }

    }

    private static void a()
    {
        // Test auf die Verkettung von mehreren Properties hintereinander
        SimpleIntegerProperty prop1 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop2 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop3 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop4 = new SimpleIntegerProperty();
        List<SimpleIntegerProperty> l = List.of(prop1, prop2, prop3, prop4);

        addListenersfromList(l);

        prop1.bind(prop2);
        prop2.bind(prop3);
        prop3.bind(prop4);

        for (SimpleIntegerProperty x : l)
        {
            try
            {
                System.out.println("____________________");
                x.set(3);
            }
            catch (RuntimeException e)
            {
                System.out.println("Set is disabled");
            }
        }
    }

    private static void b()
    {
        // Test auf Abhängigkeit einer Property von mehreren Properties
        SimpleIntegerProperty prop1 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop2 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop3 = new SimpleIntegerProperty();

        List<SimpleIntegerProperty> l = List.of(prop1);
        addListenersfromList(l);

        prop1.bind(prop2);
        prop1.bind(prop3);
        prop2.set(3);
        prop3.set(4);

    }

    private static void c()
    {
        // Test auf Abhängigkeit zweier Properties von der selben Property
        SimpleIntegerProperty prop1 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop2 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop3 = new SimpleIntegerProperty();

        List<SimpleIntegerProperty> l = List.of(prop1, prop2);
        addListenersfromList(l);

        prop1.bind(prop3);
        prop2.bind(prop3);
        prop3.set(3);
    }

    private static void d()
    {
        SimpleIntegerProperty prop1 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop2 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop3 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop4 = new SimpleIntegerProperty();
        List<SimpleIntegerProperty> l = List.of(prop1, prop2, prop3, prop4);
        addListenersfromList(l);

        try
        {
            prop1.bind(prop2);
            prop2.bind(prop3);
            prop3.bind(prop4);
            prop4.bind(prop1);
        }
        catch (StackOverflowError e)
        {
            System.out.println("Stackoverflow durch unidirektionalen Kreis");
        }
    }

    private static void e()
    {
        // Test auf die Verkettung von mehreren Properties hintereinander
        SimpleIntegerProperty prop1 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop2 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop3 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop4 = new SimpleIntegerProperty();
        List<SimpleIntegerProperty> l = List.of(prop1, prop2, prop3, prop4);

        addListenersfromList(l);

        // Diesmal bidirektional!
        prop1.bindBidirectional(prop2);
        prop2.bindBidirectional(prop3);
        prop3.bindBidirectional(prop4);

        int i = 1;
        for (SimpleIntegerProperty x : l)
        {
            try
            {
                System.out.println("____________________");
                x.set(i);
                i++;
            }
            catch (RuntimeException e)
            {
                System.out.println("Set is disabled");
            }
        }

    }

    private static void f()
    {
        SimpleIntegerProperty prop1 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop2 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop3 = new SimpleIntegerProperty();
        SimpleIntegerProperty prop4 = new SimpleIntegerProperty();
        List<SimpleIntegerProperty> l = List.of(prop1, prop2, prop3, prop4);
        addListenersfromList(l);

        try
        {
            prop1.bindBidirectional(prop2);
            prop2.bindBidirectional(prop3);
            prop3.bindBidirectional(prop4);
            prop4.bindBidirectional(prop1);

        }
        catch (StackOverflowError e)
        {
            System.out.println("Stackoverflow durch unidirektionalen Kreis");
        }

        int i = 1;
        for (SimpleIntegerProperty x : l)
        {
            System.out.println("____________________");
            x.set(i);
            i++;
        }
    }

    public static void main(String[] args)
    {
        a();
        // Note: Ja, geht. Nur die Oberste 'Vater-Property' ist noch veränderbar
        System.out.println("=======================");
        b();
        // Note: Nein, geht nicht. Das zweite bind überschreibt das erste.
        System.out.println("=======================");
        c();
        // Note: Ja, geht.
        System.out.println("=======================");
        d();
        // Note: Nein. Bitte nicht zuhause nachmachen.
        System.out.println("=======================");
        e();
        // Note: Keines der Sets ist diesmal disabled.
        System.out.println("=======================");
        f();

    }

}

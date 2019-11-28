package tutorial.woche_07;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Country
{

    private SimpleStringProperty name, capital;

    private SimpleIntegerProperty people, area;

    public Country(String n, String c, int p, int a)
    {
        name = new SimpleStringProperty(n);
        capital = new SimpleStringProperty(c);
        people = new SimpleIntegerProperty(p);
        area = new SimpleIntegerProperty(a);
    }

    public SimpleStringProperty getName()
    {
        return name;
    }

    public SimpleStringProperty getCapital()
    {
        return capital;
    }

    public SimpleIntegerProperty getPeople()
    {
        return people;
    }

    public SimpleIntegerProperty getArea()
    {
        return area;
    }

}

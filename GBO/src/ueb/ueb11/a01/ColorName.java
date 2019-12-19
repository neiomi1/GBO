package ueb.ueb11.a01;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

public class ColorName
{

    private Color color;

    private StringProperty name;

    public ColorName(Color color, String name)
    {
        this.color = color;

        this.name = new SimpleStringProperty(name);
    }

    public Color getColor()
    {
        return color;
    }

    public StringProperty getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name.get();
    }
}

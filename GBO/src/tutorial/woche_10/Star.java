package tutorial.woche_10;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class Star extends Group
{

    private SimpleDoubleProperty x, y;

    private SimpleIntegerProperty numberOfSpikes;

    private SimpleDoubleProperty radius1, radius2, angle;

    public Star(double x, double y, int numberOfSpikes, double radius1, double radius2, double angle, Paint strokeColor, Paint fillColor)
    {
        super();
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.numberOfSpikes = new SimpleIntegerProperty(numberOfSpikes);
        this.radius1 = new SimpleDoubleProperty(radius1);
        this.radius2 = new SimpleDoubleProperty(radius2);
        this.angle = new SimpleDoubleProperty(angle);

        ChangeListener<Number> listener = (obs, oldValue, newValue) -> update();
        this.x.addListener(listener);
        this.y.addListener(listener);
        this.radius1.addListener(listener);
        this.radius2.addListener(listener);
        this.angle.addListener(listener);
        this.numberOfSpikes.addListener(listener);

        Polygon poly = new Polygon();
        poly.setStroke(strokeColor);
        poly.setFill(fillColor);
        update();
        getChildren().add(poly);

    }

    private void update()
    {
        Polygon poly = (Polygon) getChildren().get(0);

        double currentAngle = angle.get() / 180.0 * Math.PI;
        double angleIncrement = Math.PI / numberOfSpikes.get();

        for (int i = 0; i < numberOfSpikes.get(); i++)
        {
            double point1X = x.get() + radius1.get() * Math.cos(currentAngle);
            double point1Y = y.get() - radius1.get() * Math.sin(currentAngle);

            currentAngle += angleIncrement;

            double point2X = x.get() + radius2.get() * Math.cos(currentAngle);
            double point2Y = y.get() - radius2.get() * Math.sin(currentAngle);

            currentAngle += angleIncrement;

            poly.getPoints().addAll(point1X, point1Y, point2X, point2Y);
        }
    }

    public double getRadius1()
    {
        return radius1.get();
    }

    public double getRadius2()
    {
        return radius2.get();
    }

    public SimpleDoubleProperty radius1Property()
    {
        return radius1;
    }

    public SimpleDoubleProperty radius2Property()
    {
        return radius2;
    }

    public SimpleDoubleProperty xProperty()
    {
        return x;
    }

    public SimpleDoubleProperty yProperty()
    {
        return y;
    }

    public SimpleDoubleProperty angleProperty()
    {
        return angle;
    }

    public SimpleIntegerProperty numberOfSpikesProperty()
    {
        return numberOfSpikes;
    }

    public ObjectProperty<Paint> strokeColorProperty()
    {
        Polygon poly = (Polygon) getChildren().get(0);
        return poly.strokeProperty();
    }

    public ObjectProperty<Paint> fillColorProperty()
    {
        Polygon poly = (Polygon) getChildren().get(0);
        return poly.fillProperty();
    }
}

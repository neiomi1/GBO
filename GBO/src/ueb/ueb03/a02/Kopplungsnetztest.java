package ueb.ueb03.a02;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public class Kopplungsnetztest
{
    public static void main(String[] args)
    {
        SimpleIntegerProperty propa = new SimpleIntegerProperty();
        SimpleIntegerProperty propb = new SimpleIntegerProperty();
        SimpleIntegerProperty proptotal = new SimpleIntegerProperty();

        proptotal.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> System.out.println("changed from " + oldValue + " to " + propa.get() + "² + " + propb.get() + "² = " + newValue));
        proptotal.bind(Bindings.add(propa.multiply(propa), propb.multiply(propb)));
        propa.set(3);
        System.out.println("=============");
        propb.set(4);

    }

}

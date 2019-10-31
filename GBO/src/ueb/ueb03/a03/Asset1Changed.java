package ueb.ueb03.a03;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;

public class Asset1Changed
{

    private static final double FACTOR = 1.1;

    private DoubleProperty accountBalanceEuro;

    private ReadOnlyDoubleWrapper accountBalanceDollar;

    public Asset1Changed(double initialValue)
    {
        accountBalanceEuro = new SimpleDoubleProperty(initialValue);
        accountBalanceDollar = new ReadOnlyDoubleWrapper();
        accountBalanceDollar.bind(Bindings.multiply(accountBalanceEuro, FACTOR));
    }

    public double getAccountBalanceEuro()
    {
        return accountBalanceEuro.get();
    }

    public void setAccountBalanceEuro(double newValue)
    {
        accountBalanceEuro.set(newValue);
    }

    public DoubleProperty accountBalanceEuroProperty()
    {
        return accountBalanceEuro;
    }

    public double getAccountBalanceDollar()
    {
        return accountBalanceDollar.get();
    }

    public ReadOnlyDoubleProperty accountBalanceDollarProperty()
    {
        return accountBalanceDollar.getReadOnlyProperty();
    }
}

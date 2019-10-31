package ueb.ueb03.a03;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;

public class Asset1ChangedLazy
{
    private static final double FACTOR = 1.1;

    private double accountBalance;

    private double accountDollar;

    private DoubleProperty accountBalanceEuroProperty;

    private ReadOnlyDoubleWrapper accountBalanceDollarProperty;

    public Asset1ChangedLazy(double initialValue)
    {
        accountBalance = initialValue;
        accountDollar = initialValue * FACTOR;
    }

    public double getAccountBalanceEuro()
    {
        if (accountBalanceEuroProperty == null)
        {
            return accountBalance;
        }
        else
        {
            return accountBalanceEuroProperty.get();
        }
    }

    public double getAccountBalanceDollar()
    {
        if (accountBalanceDollarProperty == null)
        {
            return accountDollar;
        }
        else
        {
            return accountBalanceDollarProperty.get();
        }
    }

    public void setAccountBalance(double newValue)
    {
        if (accountBalanceEuroProperty == null)
        {
            accountBalance = newValue;

        }
        else
        {
            accountBalanceEuroProperty.set(newValue);
        }
    }

    public DoubleProperty accountBalanceEuroProperty()
    {
        if (accountBalanceEuroProperty == null)
        {
            accountBalanceEuroProperty = new SimpleDoubleProperty(accountBalance);
            accountBalanceDollarProperty = new ReadOnlyDoubleWrapper();
            accountBalanceDollarProperty.bind(Bindings.multiply(accountBalanceEuroProperty, FACTOR));

        }
        return accountBalanceEuroProperty;
    }

    public ReadOnlyDoubleProperty readOnlyaccountBalanceDollarProperty()
    {
        if (accountBalanceDollarProperty == null)
        {
            accountBalanceEuroProperty = new SimpleDoubleProperty(accountBalance);
            accountBalanceDollarProperty = new ReadOnlyDoubleWrapper();
            accountBalanceDollarProperty.bind(Bindings.multiply(accountBalanceEuroProperty, FACTOR));
        }
        return accountBalanceDollarProperty.getReadOnlyProperty();
    }

}

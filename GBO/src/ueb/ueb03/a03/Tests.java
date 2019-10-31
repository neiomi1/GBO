package ueb.ueb03.a03;

import java.util.List;

import javafx.beans.property.ReadOnlyDoubleProperty;

public class Tests
{
    private static void asset1Test()
    {
        Asset1 a = new Asset1(10);
        List<ReadOnlyDoubleProperty> l = List.of(a.accountBalanceDollarProperty(), a.accountBalanceEuroProperty());

        for (ReadOnlyDoubleProperty x : l)
        {
            System.out.println(x.doubleValue());
        }
        System.out.println("________________________________");
        a.accountBalanceDollarProperty().add(1);
        System.out.println(a.getAccountBalanceEuro());
        System.out.println(a.getAccountBalanceDollar());
        System.out.println("________________________________");
        a.accountBalanceEuroProperty().set(13);
        System.out.println(a.getAccountBalanceEuro());
        System.out.println(a.getAccountBalanceDollar());
        System.out.println("________________________________");
        a.setAccountBalanceEuro(14);
        System.out.println(a.getAccountBalanceEuro());
        System.out.println(a.getAccountBalanceDollar());
        System.out.println("________________________________");

    }

    private static void asset1ChangedTest()
    {
        Asset1Changed a = new Asset1Changed(10);
        List<ReadOnlyDoubleProperty> l = List.of(a.accountBalanceDollarProperty(), a.accountBalanceEuroProperty());

        for (ReadOnlyDoubleProperty x : l)
        {
            System.out.println(x.doubleValue());
        }
        System.out.println("________________________________");
        a.accountBalanceDollarProperty().add(1);
        System.out.println(a.getAccountBalanceEuro());
        System.out.println(a.getAccountBalanceDollar());
        System.out.println("________________________________");
        a.accountBalanceEuroProperty().set(13);
        System.out.println(a.getAccountBalanceEuro());
        System.out.println(a.getAccountBalanceDollar());
        System.out.println("________________________________");
        a.setAccountBalanceEuro(14);
        System.out.println(a.getAccountBalanceEuro());
        System.out.println(a.getAccountBalanceDollar());
        System.out.println("________________________________");

    }

    public static void main(String[] args)
    {
        asset1Test();
        // Daten sind NICHT! konsistent.
        System.out.println("===================================================");
        asset1ChangedTest();
        // Daten sind konsistent. :)

    }
}

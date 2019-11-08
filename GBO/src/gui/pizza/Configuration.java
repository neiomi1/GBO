package gui.pizza;

public class Configuration
{
    private String[] sizeNames, toppingNames;

    private int[] sizePrices, toppingPrices;

    private int numberOfDefaultToppings;

    public String[] getSizeNames()
    {
        return sizeNames;
    }

    public String[] getToppingNames()
    {
        return toppingNames;
    }

    public int[] getSizePrices()
    {
        return sizePrices;
    }

    public int[] getToppingPrices()
    {
        return toppingPrices;
    }

    public int getNumberOfDefaultToppings()
    {
        return numberOfDefaultToppings;
    }

    public Configuration(String[] sizeNames, int[] sizePrices, String[] toppingNames, int[] toppingPrices, int numberOfDefaultToppings) throws IllegalArgumentException
    {
        if (sizeNames.length == sizePrices.length)
        {
            this.sizeNames = sizeNames;
            this.sizePrices = sizePrices;
        }
        else
        {
            throw new IllegalArgumentException("Length of Input does not match");
        }
        if (toppingNames.length == toppingPrices.length && numberOfDefaultToppings <= toppingNames.length)
        {
            this.toppingNames = toppingNames;
            this.toppingPrices = toppingPrices;
            this.numberOfDefaultToppings = numberOfDefaultToppings;
        }
        else
        {
            throw new IllegalArgumentException("Length of Input does not match");
        }

    }
}

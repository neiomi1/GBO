package ueb.ueb03.a05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ChangeListenerTests
{

    public static void main(String[] args)
    {
        List<String> list = new ArrayList<String>();
        ObservableList<String> observableList = FXCollections.observableList(list);
        observableList.addListener(new ListChangeListener<String>()
        {
            public void onChanged(ListChangeListener.Change<? extends String> change)
            {
                while (change.next())
                {
                    if (change.wasUpdated())
                    {
                        System.out.println("Updated between Index " + change.getFrom() + " and " + change.getTo());
                    }
                    else if (change.wasReplaced())
                    {
                        System.out.println("List of replaced strings: " + change.getRemoved());
                    }
                    else if (change.wasRemoved())
                    {
                        System.out.println("List of removed strings: " + change.getRemoved().toString());
                    }
                    else if (change.wasAdded())
                    {
                        System.out.println("List of added strings: " + change.getAddedSubList().toString());
                    }
                    else if (change.wasPermutated())
                    {
                        for (int n = 0; n < change.getTo(); n++)
                        {
                            System.out.println("Permutation: Old Index: " + n + " New Index: " + change.getPermutation(n));
                        }
                    }
                }
            }
        });

        observableList.add("Element 1");
        observableList.add("Element 1");
        observableList.remove(0);
        observableList.replaceAll((String a) -> a + "HI");
        observableList.add("XYZ");
        observableList.add("Element 1");
        observableList.add("Z");
        observableList.forEach((String a) -> System.out.println(a));
        observableList.sort(new Comparator<String>()
        {
            public int compare(String a, String b)
            {
                return a.compareTo(b);
            }
        });
        observableList.forEach((String a) -> System.out.println(a));

        // System.out.println("Größe: " + observableList.size());
    }

}

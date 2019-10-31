package gui.collections;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ObservableListDemo
{
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<String>();
        ObservableList<String> observableList = FXCollections.observableList(list);
        observableList.addListener(new ListChangeListener<String>()
        {
            public void onChanged(ListChangeListener.Change<? extends String> change)
            {
                System.out.println("Liste wurde geändert");
            }
        });

        observableList.add("Element 1");
        System.out.println("Größe: " + observableList.size());

        // ändert auch observableList, erzeugt aber kein Ereignis
        list.add("Element 2");
        System.out.println("Größe: " + observableList.size());
    }
}
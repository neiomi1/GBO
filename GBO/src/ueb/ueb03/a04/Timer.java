package ueb.ueb03.a04;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

public class Timer
{
    private static long addAtEndTest(List<Integer> list, int reps)
    {
        long start = System.currentTimeMillis();
        for (int n = 0; n < reps; n++)
        {
            list.add(n);
        }
        return System.currentTimeMillis() - start;
    }

    private static long addAtStartTest(List<Integer> list, int reps)
    {
        long start = System.currentTimeMillis();
        for (int n = 0; n < reps; n++)
        {
            list.add(0, n);
        }
        return System.currentTimeMillis() - start;
    }

    private static long removeAtStartTest(List<Integer> list, int reps)
    {

        long start = System.currentTimeMillis();
        for (int n = 0; n < reps; n++)
        {
            list.remove(0);
        }
        return System.currentTimeMillis() - start;
    }

    private static long removeAtEndTest(List<Integer> list, int reps)
    {
        long start = System.currentTimeMillis();
        for (int n = 0; n < reps; n++)
        {
            list.remove(list.size() - 1);
        }
        return System.currentTimeMillis() - start;
    }

    private static long getFirstTest(List<Integer> list, int reps)
    {
        long start = System.currentTimeMillis();
        for (int n = 0; n < reps; n++)
        {
            list.get(0);
        }
        return System.currentTimeMillis() - start;
    }

    private static long getLastTest(List<Integer> list, int reps)
    {
        long start = System.currentTimeMillis();
        for (int n = 0; n < reps; n++)
        {
            list.get(list.size() - 1);
        }
        return System.currentTimeMillis() - start;

    }

    private static long getMiddleTest(List<Integer> list, int reps)
    {
        long start = System.currentTimeMillis();
        for (int n = 0; n < reps; n++)
        {
            list.get(list.size() / 2);
        }
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args)
    {

        Supplier<List<Integer>> supp = ArrayList::new;
        ObjIntConsumer<List<Integer>> oic = List::add;
        BiConsumer<List<Integer>, List<Integer>> bicons = (a, b) -> a.addAll(b);

        List<Integer> link = new LinkedList<Integer>();
        link.addAll(java.util.stream.IntStream.range(0, 900).collect(supp, oic, bicons));
        List<Integer> array = new ArrayList<Integer>();
        array.addAll(java.util.stream.IntStream.range(0, 900).collect(supp, oic, bicons));
        // System.out.println(link.size() + " | " + array.size());
        List<List<Integer>> l = List.of(link, array);
        int repetitions = 100000;
        for (List<Integer> list : l)
        {
            if (list instanceof ArrayList)
            {
                System.out.println("---------------ArrayList tests----------------");
                System.out.println("Iterationen: " + repetitions);
            }
            else
            {
                System.out.println("---------------LinkedList tests-----------------");
                System.out.println("Iterationen: " + repetitions);
            }
            System.out.println("Anfügen an den Anfang\n Zeit: " + addAtStartTest(list, repetitions));
            System.out.println("Anfügen an das Ende\n Zeit: " + addAtEndTest(list, repetitions));
            System.out.println("Größe der Liste: " + list.size());
            System.out.println("Suchen des ersten Objekts\n Zeit: " + getFirstTest(list, repetitions));
            System.out.println("Suchen des mittleren Objekts\n Zeit: " + getMiddleTest(list, repetitions));
            System.out.println("Suchen des letzten Objekts\n Zeit: " + getLastTest(list, repetitions));
            System.out.println("Entfernen des ersten Objekts\n Zeit: " + removeAtStartTest(list, repetitions));
            System.out.println("Entfernen des letzten Objekts\n Zeit: " + removeAtEndTest(list, repetitions));
        }
    }
}

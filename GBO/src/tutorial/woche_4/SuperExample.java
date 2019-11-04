package tutorial.woche_4;

import java.util.ArrayList;
import java.util.List;

class ElemA
{
    public void a()
    {
    }
}

class ElemB extends ElemA
{
    public void b()
    {
    }
}

class ElemC extends ElemB
{
    public void c()
    {
    }
}

interface Executor<T>
{
    public void execute(T t);
}

class ExecutorAImpl implements Executor<ElemA>
{
    public void execute(ElemA elem)
    {
        elem.a();
    }
}

class ExecutorBImpl implements Executor<ElemB>
{
    public void execute(ElemB elem)
    {
        elem.b();
    }
}

class ExecutorObjectImpl implements Executor<Object>
{
    public void execute(Object elem)
    {
        System.out.println(elem);
    }
}

class ExecutorCImpl implements Executor<ElemC>
{
    public void execute(ElemC elem)
    {
        elem.c();
    }
}

public class SuperExample
{
    private static void workOnAllElements(List<? extends ElemB> list, Executor<? super ElemB> exe)
    {
        for (ElemB elem : list)
        {
            exe.execute(elem);
        }

    }

    public static void main(String[] args)
    {
        List<ElemB> list = new ArrayList<ElemB>();
        list.add(new ElemB());
        list.add(new ElemC());
        list.add(new ElemB());
        workOnAllElements(list, new ExecutorObjectImpl());

    }
}

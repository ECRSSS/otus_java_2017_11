import CustomList.CustomList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Administrator on 02.12.2017.
 */
public class Main {
    public static void main(String[] args)
    {

        CustomList<Integer> cl1=new CustomList<Integer>();
        cl1.add(5);
        cl1.add(2);
        cl1.add(3);
        cl1.add(3);
        cl1.add(3);
        CustomList<Integer> cl2=new CustomList<Integer>(5);
        Collections.copy(cl2,cl1);
        Collections.addAll(cl2,1,2,3);
        cl2.sort((a, b) -> a.compareTo(b));
        Collections.sort(cl1);
        System.out.println(cl1);
        System.out.println(cl2);

        CustomList<Integer> cl=new CustomList<Integer>(4);
        System.out.println(cl);
        cl.add(5);
        System.out.println(cl);
        cl.add(5);
        System.out.println(cl);
        cl.add(5);
        System.out.println(cl);


    }
}

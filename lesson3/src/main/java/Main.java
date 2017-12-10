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




        CustomList<Integer> list1=new CustomList<Integer>();
        boolean result = list1.add(null);
        System.out.println(result);
        System.out.println(list1);



        CustomList<Integer> list=new CustomList<Integer>();
        list.add(3);
        list.add(2);
        list.add(1);

        for(Integer n : list){
            System.out.print(n + " ");
        }
        System.out.println();
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);

        Collections.addAll(list,1,2,3);
        Collections.sort(list);
        System.out.println(list);
        System.out.println("-----");
        CustomList<Integer> cl=new CustomList<Integer>(list.size());
        cl.add(4);
        cl.add(4);
        cl.add(4);
        cl.add(4);
        cl.add(4);
        cl.add(4);
        Collections.copy(cl,list);

        System.out.println(cl);
    }
}

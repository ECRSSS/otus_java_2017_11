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
        CustomList<Integer> customList=new CustomList<>();

        Collections.addAll(customList,2,1,3);

        Collections.sort(customList, (Integer o1,Integer o2) ->o1.compareTo(o2));

        CustomList<Integer> customList2=new CustomList<>(customList.size());
        Collections.copy(customList2,customList);

        System.out.println(customList);
        System.out.println(customList2);

    }
}

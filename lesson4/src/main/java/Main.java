import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 09.12.2017.
 */
public class Main {
    public static void main(String[] args)
    {
        ArrayList<Random> ar=new ArrayList<>();
        for(long i=0;i<Long.MAX_VALUE;i++)
        {
            ar.add(new Random());
            ar.add(new Random());
            ar.remove(i-1);
            System.out.println(ar.size());
        }
    }
}

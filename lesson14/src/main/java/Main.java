import java.util.Random;

/**
 * Created by WE on 02.04.2018.
 */
public class Main {

    public static void main(String[] args)
    {
        int i=1000;
        int[] array=new int[i];
        Random random=new Random(System.currentTimeMillis());
        for(int j=0;j<i;j++)
        {
            array[j]=random.nextInt(1000);
            System.out.println(array[j]);
        }
        ArrayMultiThreadSort arrayMultiThreadSort=new ArrayMultiThreadSort(array);
        int[] sortedArray=arrayMultiThreadSort.sort();
        System.out.println("------------------------");
        for(int k=0;k<i;k++)
        {
            System.out.println(sortedArray[k]);
        }

    }
}

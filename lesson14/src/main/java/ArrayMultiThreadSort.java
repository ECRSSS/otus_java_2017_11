import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by WE on 02.04.2018.
 */
public class ArrayMultiThreadSort {


    private int[] arrayBeforeSort;

    private ArrayList<Thread> threads = new ArrayList<>();
    
    
    private static final int NUM_OF_THREADS = 4;

    public ArrayMultiThreadSort (int[] array) {
        this.arrayBeforeSort = array;
    }

    public int[] sort() {
        if (arrayBeforeSort.length<8) {
            Arrays.sort(arrayBeforeSort);
            return this.arrayBeforeSort;
        }

        int[][] parts = split();
        for (int i = 0; i < parts.length; i++) {
            int f = i;
            Thread thread = new Thread(() -> Arrays.sort(parts[f]));
            threads.add(thread);
        }

        startTreads();

        int[] result = parts[0];
        for (int i = 0; i < parts.length-1; i++) {
            result = combine(result, parts[i+1]);
        }



        return result;
    }

    private void startTreads() {
        for(Thread thread : threads)
        {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int[][] split() {
        int s = arrayBeforeSort.length / NUM_OF_THREADS;
        int[][] arrParts = new int[NUM_OF_THREADS][];

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            int from = i * s;
            int to = (i + 1) * s;
            if (i == NUM_OF_THREADS - 1) {
                to = arrayBeforeSort.length;
            }
            arrParts[i] = Arrays.copyOfRange(arrayBeforeSort, from, to);
        }
        return arrParts;
    }


    private int[] combine(int[] arr1, int[] arr2) {
        int i1 = 0;
        int i2 = 0;
        int[] result = new int[arr1.length + arr2.length];

        for (int i = 0; i < result.length; i++) {
            if (i2 >= arr1.length || (i1 < arr2.length && arr2[i1] <= arr1[i2])) {
                result[i] = arr2[i1];
                i1++;
            } else {
                result[i] = arr1[i2];
                i2++;
            }
        }
        return result;
    }
}

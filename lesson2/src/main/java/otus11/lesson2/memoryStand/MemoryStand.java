package otus11.lesson2.memoryStand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 26.11.2017.
 */
public class MemoryStand {
    private final int mb = 1024 * 1024;
    private long freeMemoryPrev;
    private Runtime runtime;

    public MemoryStand() {
        this.runtime = Runtime.getRuntime();
        freeMemoryPrev = runtime.freeMemory();
        System.out.println("Start -free memory: " + getMemoryString(freeMemoryPrev));
    }
    public void calculatePrimitiveTypesMemory(Class cl)
    {
        System.out.println("for " + cl.getName().replace("java.lang.",""));
        int size=1_000_000;
        switch (cl.getName())
        {
            case "java.lang.Float":
                float a[]=new float[size];
                for (int i=0;i<size;i++)
                {
                    a[i]=0;
                }
                showCurrentAllocatedMemory(size);
                showMemoryForObject(size);
                break;
            case "java.lang.Boolean":
                boolean b[]=new boolean[size];
                for (int i=0;i<size;i++)
                {
                    b[i]=false;
                }
                showCurrentAllocatedMemory(size);
                showMemoryForObject(size);
                break;
            case "java.lang.Byte":
                byte c[]=new byte[size];
                for (int i=0;i<size;i++)
                {
                    c[i]=0;
                }
                showCurrentAllocatedMemory(size);
                showMemoryForObject(size);
                break;
            case "java.lang.Short":
                short d[]=new short[size];
                for (int i=0;i<size;i++)
                {
                    d[i]=0;
                }
                showCurrentAllocatedMemory(size);
                showMemoryForObject(size);
                break;
            case "java.lang.Integer":
                int f[]=new int[size];
                for (int i=0;i<size;i++)
                {
                    f[i]=0;
                }
                showCurrentAllocatedMemory(size);
                showMemoryForObject(size);
                break;
            case "java.lang.Long":
                long e[]=new long[size];
                for (int i=0;i<size;i++)
                {
                    e[i]=0;
                }
                showCurrentAllocatedMemory(size);
                showMemoryForObject(size);
                break;
            case "java.lang.Character":
                char t[]=new char[size];
                for (int i=0;i<size;i++)
                {
                    t[i]='a';
                }
                showCurrentAllocatedMemory(size);
                showMemoryForObject(size);
                break;
            case "java.lang.Double":
                double y[]=new double[size];
                for (int i=0;i<size;i++)
                {
                    y[i]=0;
                }
                showCurrentAllocatedMemory(size);
                showMemoryForObject(size);
                break;
            default:
                System.out.println("Для ссылочных типов используйте функцию calculateReferenceTypesMemory");
                break;
        }
        System.out.println("----------------");

    }

    public void calculateReferenceTypesMemory(Class cl)
    {
        try {
            System.out.println("for " + cl);
            int size = 1000000;
            Object[] array = new Object[size];
            for (int i = 0; i < size; i++) {
                array[i] = cl.newInstance();
            }
            showCurrentAllocatedMemory(size);
            showMemoryForObject(size);
            System.out.println("------------------");
        }catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }catch (InstantiationException e)
        {
            System.out.println("Ошибка: Передайте класс ссылочного типа");
        }

    }

    private void showCurrentAllocatedMemory(long objects) {
        long freeMemoryNow = runtime.freeMemory();
        long allocatedMemory = freeMemoryPrev - freeMemoryNow;
        System.out.println("Allocate: " + getMemoryString(allocatedMemory) + " for "+objects+" objects");
    }

    private void showMemoryForObject(long objects)
    {
        long freeMemoryNow = runtime.freeMemory();
        long allocatedMemory = freeMemoryPrev - freeMemoryNow;
        System.out.println("for one object: "+getMemoryString(allocatedMemory/objects));
    }

    public void refresh()
    {
        try {
            System.gc();
            Thread.sleep(1000);
            freeMemoryPrev = runtime.freeMemory();
        }catch (InterruptedException e)
        {e.printStackTrace();}
    }

    private String getMemoryString(long memory)
    {
        return String.format("%.8f", ((double)memory)/mb)+"mb("+memory+"byte)";
    }
}

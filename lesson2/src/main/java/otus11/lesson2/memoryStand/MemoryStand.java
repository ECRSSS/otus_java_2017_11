package otus11.lesson2.memoryStand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 26.11.2017.
 */
public class MemoryStand {
    private final int mb = 1024 * 1024;
    private Runtime runtime;

    public MemoryStand() {
        this.runtime = Runtime.getRuntime();
        System.out.println("Start -allocated memory: " + getMemoryString(runtime.totalMemory()-runtime.freeMemory()));
    }
    public void calculatePrimitiveTypesMemory(Class cl)
    {
        long memoryPrev = runtime.totalMemory()-runtime.freeMemory();
        try
        {
            System.gc();
            Thread.sleep(500);
        }catch (InterruptedException e)
        {
            e.printStackTrace();return;
        }
        System.out.println("for " + cl.getName().replace("java.lang.",""));
        int size=1_000_000;
        long allocatedMemory=0;
        switch (cl.getName())
        {
            case "java.lang.Float":
                float a[]=new float[size];
                for (int i=0;i<size;i++)
                {
                    a[i]=0;
                }
                allocatedMemory = (runtime.totalMemory()-runtime.freeMemory())-memoryPrev;
                showCurrentAllocatedMemory(size,allocatedMemory);
                showMemoryForObject(size,allocatedMemory);
                break;
            case "java.lang.Boolean":
                boolean b[]=new boolean[size];
                for (int i=0;i<size;i++)
                {
                    b[i]=false;
                }
                allocatedMemory = (runtime.totalMemory()-runtime.freeMemory())-memoryPrev;
                showCurrentAllocatedMemory(size,allocatedMemory);
                showMemoryForObject(size,allocatedMemory);
                break;
            case "java.lang.Byte":
                byte c[]=new byte[size];
                for (int i=0;i<size;i++)
                {
                    c[i]=0;
                }
                allocatedMemory = (runtime.totalMemory()-runtime.freeMemory())-memoryPrev;
                showCurrentAllocatedMemory(size,allocatedMemory);
                showMemoryForObject(size,allocatedMemory);
                break;
            case "java.lang.Short":
                short d[]=new short[size];
                for (int i=0;i<size;i++)
                {
                    d[i]=0;
                }
                allocatedMemory = (runtime.totalMemory()-runtime.freeMemory())-memoryPrev;
                showCurrentAllocatedMemory(size,allocatedMemory);
                showMemoryForObject(size,allocatedMemory);
                break;
            case "java.lang.Integer":
                int f[]=new int[size];
                for (int i=0;i<size;i++)
                {
                    f[i]=0;
                }
                allocatedMemory = (runtime.totalMemory()-runtime.freeMemory())-memoryPrev;
                showCurrentAllocatedMemory(size,allocatedMemory);
                showMemoryForObject(size,allocatedMemory);
                break;
            case "java.lang.Long":
                long e[]=new long[size];
                for (int i=0;i<size;i++)
                {
                    e[i]=0;
                }
                allocatedMemory = (runtime.totalMemory()-runtime.freeMemory())-memoryPrev;
                showCurrentAllocatedMemory(size,allocatedMemory);
                showMemoryForObject(size,allocatedMemory);
                break;
            case "java.lang.Character":
                char t[]=new char[size];
                for (int i=0;i<size;i++)
                {
                    t[i]='a';
                }
                allocatedMemory = (runtime.totalMemory()-runtime.freeMemory())-memoryPrev;
                showCurrentAllocatedMemory(size,allocatedMemory);
                showMemoryForObject(size,allocatedMemory);
                break;
            case "java.lang.Double":
                double y[]=new double[size];
                for (int i=0;i<size;i++)
                {
                    y[i]=0;
                }
                allocatedMemory = (runtime.totalMemory()-runtime.freeMemory())-memoryPrev;
                showCurrentAllocatedMemory(size,allocatedMemory);
                showMemoryForObject(size,allocatedMemory);
                break;
            default:
                System.out.println("Для ссылочных типов используйте функцию calculateReferenceTypesMemory");
                break;
        }
        try
        {
            System.gc();
            Thread.sleep(500);
        }catch (InterruptedException e)
        {
            e.printStackTrace();return;
        }
        System.out.println("----------------");

    }

    public void calculateReferenceTypesMemory(Class cl)
    {
        long memoryPrev = runtime.totalMemory()-runtime.freeMemory();
        try
        {
            System.gc();
            Thread.sleep(500);
        }catch (InterruptedException e)
        {
            e.printStackTrace();return;
        }
        try {
            System.out.println("for " + cl);
            int size = 1000000;
            Object[] array = new Object[size];
            for (int i = 0; i < size; i++) {
                array[i] = cl.newInstance();
            }
            long allocatedMemory = (runtime.totalMemory()-runtime.freeMemory())-memoryPrev;
            showCurrentAllocatedMemory(size,allocatedMemory);
            showMemoryForObject(size,allocatedMemory);
            showMemoryForDifferentSizes(size,allocatedMemory);
            System.out.println("------------------");
        }catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }catch (InstantiationException e)
        {
            System.out.println("Ошибка: Передайте класс ссылочного типа");
        }

        try
        {
            System.gc();
            Thread.sleep(500);
        }catch (InterruptedException e)
        {
            e.printStackTrace();return;
        }

    }

    private void showCurrentAllocatedMemory(long objects,long allocatedMemory) {
        System.out.println("Allocate: " + getMemoryString(allocatedMemory) + " for "+objects+" objects");
    }

    private void showMemoryForDifferentSizes(long objects,long allocatedMemory) {
        long memOneObj=allocatedMemory/objects;
        System.out.println("Allocate: " + getMemoryString(memOneObj*5) + " for 5 objects");
        System.out.println("Allocate: " + getMemoryString(memOneObj*10) + " for 10 objects");
        System.out.println("Allocate: " + getMemoryString(memOneObj*25) + " for 25 objects");
        System.out.println("Allocate: " + getMemoryString(memOneObj*100) + " for 100 objects");
        System.out.println("Allocate: " + getMemoryString(memOneObj*200) + " for 200 objects");

    }

    private void showMemoryForObject(long objects,long allocatedMemory)
    {
        System.out.println("for one object: "+getMemoryString(allocatedMemory/objects));
    }

    private String getMemoryString(long memory)
    {
        return String.format("%.8f", ((double)memory)/mb)+"mb("+memory+"byte)";
    }
    public void calcAndShowMemoryOfCollections()
    {
        for(int i=1;i<2000;i+=200) {
            long mem = runtime.totalMemory() - runtime.freeMemory();
            String[] arr = new String[i];
            long mem2 = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("for collection of "+i+"elements allocates: " + (mem2 - mem) + " bytes");
            System.gc();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-----------------");
        for(int i=1;i<2000;i+=200) {
            long mem = runtime.totalMemory() - runtime.freeMemory();
            Object[] arr = new Object[i];
            long mem2 = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("for collection of "+i+"elements allocates: " + (mem2 - mem) + " bytes");
            System.gc();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-----------------");
        for(int i=1;i<2000;i+=200) {
            long mem = runtime.totalMemory() - runtime.freeMemory();
            Random[] arr = new Random[i];
            long mem2 = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("for collection of "+i+" elements allocates: " + (mem2 - mem) + " bytes");
            System.gc();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-----------------");

    }
}

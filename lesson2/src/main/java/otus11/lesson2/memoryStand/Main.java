package otus11.lesson2.memoryStand;

import java.util.InputMismatchException;
import java.util.Random;

/**
 * Created by Administrator on 26.11.2017.
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException,InstantiationException
    {
        MemoryStand memoryStand=new MemoryStand();
        memoryStand.calculatePrimitiveTypesMemory(Boolean.class);
        memoryStand.refresh();
        memoryStand.calculatePrimitiveTypesMemory(Integer.class);
        memoryStand.refresh();
        memoryStand.calculatePrimitiveTypesMemory(Short.class);
        memoryStand.refresh();
        memoryStand.calculatePrimitiveTypesMemory(Double.class);
        memoryStand.refresh();
        memoryStand.calculatePrimitiveTypesMemory(Byte.class);
        memoryStand.refresh();
        memoryStand.calculatePrimitiveTypesMemory(Character.class);
        memoryStand.refresh();
        memoryStand.calculatePrimitiveTypesMemory(Long.class);
        memoryStand.refresh();
        memoryStand.calculatePrimitiveTypesMemory(Float.class);
        memoryStand.refresh();
        memoryStand.calculateReferenceTypesMemory(Object.class);
        memoryStand.refresh();
        memoryStand.calculateReferenceTypesMemory(String.class);
        memoryStand.refresh();
        memoryStand.calculateReferenceTypesMemory(Random.class);

    }
}


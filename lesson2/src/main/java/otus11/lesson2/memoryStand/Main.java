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
        memoryStand.calculatePrimitiveTypesMemory(Integer.class);
        memoryStand.calculatePrimitiveTypesMemory(Short.class);
        memoryStand.calculatePrimitiveTypesMemory(Double.class);
        memoryStand.calculatePrimitiveTypesMemory(Byte.class);
        memoryStand.calculatePrimitiveTypesMemory(Character.class);
        memoryStand.calculatePrimitiveTypesMemory(Long.class);
        memoryStand.calculatePrimitiveTypesMemory(Float.class);

        memoryStand.calculateReferenceTypesMemory(Object.class);
        memoryStand.calculateReferenceTypesMemory(String.class);
        memoryStand.calculateReferenceTypesMemory(Random.class);

    }
}


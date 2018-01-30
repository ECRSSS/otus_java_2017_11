package Serializer.SampleClasses;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 28.01.2018.
 */
public class Class2 {
    private int simple=5;
    private String desc="34";
    ArrayList<Class1> arrayList=new ArrayList<>();
    {
        arrayList.add(new Class1());
        arrayList.add(new Class1());
    }
}

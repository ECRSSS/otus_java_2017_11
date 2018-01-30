package Serializer;

import Serializer.SampleClasses.Class1;
import Serializer.SampleClasses.Class2;
import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.json.simple.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 28.01.2018.
 */
public class Main {

    public static void main(String[] args)
    {

        Gson gson=new Gson();
        JsonSerializer jsonSerializer=new JsonSerializer();


        int[] ints=new int[]{1,43,523,645};
        String[] strings=new String[]{"dsfdsf","dsfsdf","qweqwe"};
        Class1 class1=new Class1();
        Class2 class2=new Class2();



        System.out.println(gson.toJson(ints));
        System.out.println(gson.toJson(strings));
        System.out.println(gson.toJson(class1));
        System.out.println(gson.toJson(class2));

        System.out.println("-------");

        System.out.println(jsonSerializer.toJson(ints));
        System.out.println(jsonSerializer.toJson(strings));
        System.out.println(jsonSerializer.toJson(class1));
        System.out.println(jsonSerializer.toJson(class2));
       // jsonSerializer.handleObject(new Class1());

    }
}

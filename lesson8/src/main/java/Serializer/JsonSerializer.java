package Serializer;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Administrator on 28.01.2018.
 */
public class JsonSerializer {


    private ArrayList<Field> getObjectFields(Object object)
    {
        ArrayList<Field> allfields=new ArrayList<>(Arrays.asList(object.getClass().getDeclaredFields()));
        ArrayList<Field> notstaticfields = new ArrayList<>();

        for (Field field : allfields) {
            if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                notstaticfields.add(field);
            }
        }
        return notstaticfields;
    }

    public String toJson(Object object)
    {
        return checkObject(object);
    }


    private String checkObject(Object object)
    {
        if(isArray(object))
        {
            return handleArray(object);
        }
        else if(isCollection(object))
        {
            return handleCollection(object);
        }
            return handleObject(object);

    }
    private String handleCollection(Object object)
    {
        StringBuilder json=new StringBuilder();
        json.append("[");
        int length= CollectionUtils.size(object);
        for(int i=0;i<length;i++)
        {
            json.append(handleObject(CollectionUtils.get(object,i)));
            json.append(",");
        }
        json.setLength(json.length()-1);
        json.append("]");
        return json.toString();
    }
    private boolean isArray(Object object)
    {
        return object.getClass().isArray();
    }
    private boolean isCollection(Object object)
    {
        return Collection.class.isAssignableFrom(object.getClass());
    }
    private String handleArray(Object object)
    {
        StringBuilder json=new StringBuilder();
        json.append("[");
        int length=Array.getLength(object);
        for(int i=0;i<length;i++)
        {
            json.append(handleObject(Array.get(object,i)));
            json.append(",");
        }
        json.setLength(json.length()-1);
        json.append("]");
        return json.toString();
    }
    private String handleObject(Object object)
    {
        Class clazz=object.getClass();
        String value=new String();
        if(isCollection(object))
        {
            return handleCollection(object);
        }else if(isArray(object))
        {
            return handleArray(object);
        }else if(Number.class.isAssignableFrom(clazz))
        {
            if(clazz.equals(Byte.class))
            {
                    value = Byte.toString((Byte) (object));
            }
            if(clazz.equals(Integer.class))
            {
                    value = Integer.toString((Integer) (object));
            }
            if(clazz.equals(Double.class))
            {
                    value = Double.toString((Double) (object));
            }
            if(clazz.equals(Short.class))
            {
                    value = Short.toString((Short) (object));
            }
            if(clazz.equals(Float.class))
            {
                    value = Float.toString((Float) (object));
            }
            if(clazz.equals(Long.class))
            {
                    value = Long.toString((Long) (object));
            }
        }else if(object.getClass().equals(String.class))
        {
            value = '"' + String.valueOf(object) + '"';
        }else if(object.getClass().equals(Character.class))
        {
            value = new String("" +'"' + Character.valueOf((Character) object) + '"');
        }else if(object.getClass().equals(Boolean.class))
        {
                value = Boolean.toString((Boolean) (object));
        }else {
            ArrayList<Field> fields=getObjectFields(object);
            StringBuilder json=new StringBuilder();
            json.append("{");
            for(Field field : fields)
            {
                json.append('"'+field.getName()+'"'+':');
                json.append(getFieldValue(field,object));
                json.append(',');
            }
            json.setLength(json.length()-1);
            json.append('}');
            value=json.toString();
        }
        return value;

    }
    private String getFieldValue(Field field,Object object)
    {
        field.setAccessible(true);
        String value=new String();
        Class clazz=field.getType();
        if(clazz.equals(boolean.class)||clazz.equals(byte.class)||clazz.equals(short.class)||clazz.equals(int.class)
                ||clazz.equals(long.class)||clazz.equals(float.class)||clazz.equals(double.class))
        {
            try {
                value = field.get(object).toString();
            }catch (IllegalAccessException e){e.printStackTrace();}
        }else if(clazz.equals(String.class)||clazz.equals(Character.class)||clazz.equals(char.class))
        {
            try{
                value = '"' + field.get(object).toString() + '"';
            }catch (IllegalAccessException e){e.printStackTrace();}

        }else if(Number.class.isAssignableFrom(clazz))
        {
            if(clazz.equals(Byte.class))
            {
                try {
                    value = Byte.toString((Byte) field.get(object));
                }catch (IllegalAccessException e){e.printStackTrace();}
            }
            if(clazz.equals(Integer.class))
            {
                try {
                    value = Integer.toString((Integer) field.get(object));
                }catch (IllegalAccessException e){e.printStackTrace();}
            }
            if(clazz.equals(Double.class))
            {
                try {
                    value = Double.toString((Double) field.get(object));
                }catch (IllegalAccessException e){e.printStackTrace();}
            }
            if(clazz.equals(Short.class))
            {
                try {
                    value = Short.toString((Short) field.get(object));
                }catch (IllegalAccessException e){e.printStackTrace();}
            }
            if(clazz.equals(Float.class))
            {
                try {
                    value = Float.toString((Float) field.get(object));
                }catch (IllegalAccessException e){e.printStackTrace();}
            }
            if(clazz.equals(Long.class))
            {
                try {
                    value = Long.toString((Long) field.get(object));
                }catch (IllegalAccessException e){e.printStackTrace();}
            }
        }
        else if(clazz.equals(Boolean.class))
        {
            try {
                value = Boolean.toString((Boolean) field.get(object));
            }catch (IllegalAccessException e){e.printStackTrace();}
        }
        else
        {
            try {
                value = handleObject(field.get(object));
            }catch (IllegalAccessException e){e.printStackTrace();}
        }
        return value;
    }

}

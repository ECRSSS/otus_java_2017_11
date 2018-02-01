package MyORM.Parser;

import MyORM.Annotations.Column;
import MyORM.Annotations.Table;
import MyORM.DataSet;
import Support.NumericValidator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2/1/2018.
 */
public class Parser {

    public static <T extends DataSet> String parseInsert(T  user)
    {
        Class clazz=user.getClass();
        Table table = (Table) clazz.getAnnotation(Table.class);
        if(table==null)
        {
            System.out.println("Объект не является носителем данных");
            return null;
        }
        String DBtable=table.value();

        Field[] allFields=getFields(clazz);

        StringBuilder columns=new StringBuilder();
        StringBuilder values=new StringBuilder();

        for(Field field:allFields) {
            if(field.isAnnotationPresent(Column.class))
            {
                field.setAccessible(true);
                columns.append(field.getName()+',');
                try {
                    values.append('"');
                    values.append(field.get(user));
                    values.append('"');
                    values.append(',');
                }catch (IllegalAccessException e){e.printStackTrace();}
            }
        }
        columns.setLength(columns.length()-1);
        values.setLength(values.length()-1);

        String sql="INSERT INTO "+DBtable+" ("+columns.toString()+") VALUES ("+values.toString()+");";
        System.out.println(sql);
        return sql;

    }

    public static <T extends DataSet>T parseIntoObject(ResultSet resultSet,Class clazz)
    {
        Field[] allFields=getFields(clazz);
        Class[] constructorFields=new Class[allFields.length];
        for(int i=0;i<allFields.length;i++)
        {
            constructorFields[i]=allFields[i].getType();
        }
        try {

            while (resultSet.next()) {
                try {
                    Object[] parameters=new Object[constructorFields.length];
                    for(int i=0;i<parameters.length;i++)
                    {
                        String param=resultSet.getString(allFields[i].getAnnotation(Column.class).value());
                        if(NumericValidator.tryParseInt(param))
                        {
                            parameters[i]=new Integer(Integer.parseInt(param));
                            continue;
                        }else
                        {
                            parameters[i]=param;
                        }

                    }
                    Constructor constructor=clazz.getConstructor(constructorFields);
                    return (T)constructor.newInstance(parameters);
                }catch (InstantiationException|NoSuchMethodException|IllegalAccessException|InvocationTargetException e){e.printStackTrace();}
            }

        }catch (SQLException e){e.printStackTrace();}
        return null;
    }

    private static Field[] getFields(Class clazz)
    {
        Field[] parentFields=clazz.getSuperclass().getDeclaredFields();
        Field[] fields=clazz.getDeclaredFields();

        Field[] allFields= new Field[parentFields.length+fields.length];
        System.arraycopy(parentFields, 0, allFields, 0, parentFields.length);
        System.arraycopy(fields, 0, allFields, parentFields.length, fields.length);
        return allFields;
    }

}

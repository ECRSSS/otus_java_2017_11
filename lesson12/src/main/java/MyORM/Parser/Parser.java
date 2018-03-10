package MyORM.Parser;

import MyORM.Annotations.Column;
import MyORM.Annotations.Table;
import MyORM.DataSet;
import Support.NumericValidator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2/1/2018.
 */
public class Parser {

    public static <T extends DataSet> PreparedStatement parseInsert(T  user,Connection connection)
    {
        PreparedStatement preparedStatement=null;
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
        ArrayList<Field> flds=new ArrayList<>();
        for(Field field:allFields) {
            if(field.isAnnotationPresent(Column.class))
            {
                field.setAccessible(true);
                columns.append(field.getName()+',');
                values.append("?,");
                flds.add(field);
            }
        }
        columns.setLength(columns.length()-1);
        values.setLength(values.length()-1);

        String sql="INSERT INTO "+DBtable+" ("+columns.toString()+") VALUES ("+values.toString()+");";
        System.out.println(sql);
        try {
            preparedStatement = connection.prepareStatement(sql);
            for(int i=0;i<flds.size();i++)
            {
                preparedStatement.setString(i+1,flds.get(i).get(user).toString());
            }
        }catch (SQLException|IllegalAccessException e){e.printStackTrace();}
        return preparedStatement;

    }


    public static <T extends DataSet>T parseIntoObject(ResultSet resultSet,Class clazz)
    {
        Field[] allFields=getFields(clazz);
        ArrayList<Class> cFields=new ArrayList<>();
        for(int i=0;i<allFields.length;i++)
        {
            if(allFields[i].isAnnotationPresent(Column.class))
            {cFields.add(allFields[i].getType());}
        }
        Class[] constructorFields=cFields.toArray(new Class[cFields.size()]);
        try {

            while (resultSet.next()) {
                try {
                    ArrayList<Object> parameters=new ArrayList<>();
                    for(Field field:allFields)
                    {
                        if(field.isAnnotationPresent(Column.class)) {
                            String param = resultSet.getString(field.getAnnotation(Column.class).value());
                            if (NumericValidator.tryParseInt(param)) {
                                parameters.add(new Integer(Integer.parseInt(param)));
                                continue;
                            } else {
                                parameters.add(param);
                            }
                        }

                    }
                    Object[] prs=parameters.toArray(new Object[parameters.size()]);
                    Constructor constructor=clazz.getConstructor(constructorFields);
                    return (T)constructor.newInstance(prs);
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

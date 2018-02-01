import MyORM.Annotations.Table;
import MyORM.DataSet;
import MyORM.Parser.Parser;
import MyORM.UserDataSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2/1/2018.
 */
public class Executor {
        private final Connection connection;

        public Executor(Connection connection)
        {
            this.connection=connection;
        }

        public <T extends DataSet> void save(T user){ //сохранение в базу
            try {
                Statement statem = connection.createStatement();
                String sql = Parser.parseInsert(user);
                if (sql == null) {
                    return;
                }

                statem.execute(sql);
                statem.close();
                connection.close();
            }catch (SQLException e){e.printStackTrace();}
        }

        public <T extends DataSet>T load(long id, Class<T> clazz)
        {
            T t=null;
           try
           {

               Statement statem = connection.createStatement();
               Table table = clazz.getAnnotation(Table.class);
               if(table==null)
               {
                   System.out.println("Класс не подходит для хранения данных");
                   return null;
               }
               String tableName=table.value();
               String sql="SELECT * from "+tableName+" WHERE id="+id+";";
               statem.execute(sql);
               ResultSet resultSet=statem.getResultSet();

               t = Parser.parseIntoObject(resultSet, clazz);


               resultSet.close();
               statem.close();
               connection.close();
           }catch (SQLException e){e.printStackTrace();}
           return t;
        }
}

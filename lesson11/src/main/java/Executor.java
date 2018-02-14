import Cache.CacheWorker;
import MyORM.Annotations.Table;
import MyORM.DataSet;
import MyORM.Parser.Parser;
import MyORM.UserDataSet;

import java.sql.*;

/**
 * Created by Administrator on 2/1/2018.
 */
public class Executor {
        private final Connection connection;
        private CacheWorker cache;

        public Executor(Connection connection)
        {
            this.connection=connection;
            this.cache=CacheWorker.getInstance();
        }

        public void closeConnection()
        {
            try {
                connection.close();
            }catch (SQLException e){e.printStackTrace();}
        }

        public <T extends DataSet> void save(T user){ //сохранение в базу
            try {
                try(Statement statem = connection.createStatement()) {
                    PreparedStatement preparedStatement = Parser.parseInsert(user,connection);
                    if (preparedStatement == null) {
                        return;
                    }

                    preparedStatement.execute();
                    cache.putIntoCache(user);
                }
            }catch (SQLException e){e.printStackTrace();}
        }

        public <T extends DataSet>T load(long id, Class<T> clazz)
        {
            T t=null;
           try
           {

               try(Statement statem = connection.createStatement()) {
                   Table table = clazz.getAnnotation(Table.class);
                   if (table == null) {
                       System.out.println("Класс не подходит для хранения данных");
                       return null;
                   }

                   DataSet fromCache=cache.getFromCache(id);
                   if(fromCache!=null)
                   {
                       return (T)fromCache;
                   }else {
                       String tableName = table.value();
                       String sql = "SELECT * from " + tableName + " WHERE id= ?;";
                       PreparedStatement preparedStatement = connection.prepareStatement(sql);
                       preparedStatement.setLong(1, id);
                       preparedStatement.execute();
                       try (ResultSet resultSet = preparedStatement.getResultSet()) {

                           t = Parser.parseIntoObject(resultSet, clazz);
                       }
                   }

               }
           }catch (SQLException e){e.printStackTrace();}
           return t;
        }
}

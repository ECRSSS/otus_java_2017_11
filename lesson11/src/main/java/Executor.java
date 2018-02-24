import Cache.CacheEngine;
import MyORM.Annotations.Table;
import MyORM.DataSet;
import MyORM.Parser.Parser;

import java.sql.*;

/**
 * Created by Administrator on 2/1/2018.
 */
public class Executor {
        private final Connection connection;
        private CacheEngine cache;

        public Executor(Connection connection)
        {
            this.connection=connection;
            this.cache=new CacheEngine(5,1000,0,false);
        }

        public void closeConnection()
        {
            try {
                connection.close();
                cache.dispose();
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
                    cache.put(user,user.getId());
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

                   DataSet fromCache=cache.get(id);
                   if(fromCache!=null)
                   {
                       fromCache.setAccessed();
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

    public int getHitCount() {
        return cache.getHitCount();
    }

    public int getMissCount() {
        return cache.getMissCount();
    }
}

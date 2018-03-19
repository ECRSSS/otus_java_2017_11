package Otus.Support;

import Otus.Cache.CacheEngine;
import Otus.Connection.ConnectionHelper;
import Otus.MyORM.Annotations.Table;
import Otus.MyORM.DataSet;
import Otus.MyORM.Parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

/**
 * Created by Administrator on 2/1/2018.
 */
@Service
public class DBExecutor {
    private final Connection connection;

    @Autowired
    private CacheEngine cache;

    public DBExecutor() {
        connection=ConnectionHelper.getConnection();
    }

    public void closeConnection() {
        try {
            connection.close();
            cache.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T extends DataSet> void save(T user) { //сохранение в базу
        try {
            try (Statement statem = connection.createStatement()) {
                PreparedStatement preparedStatement = Parser.parseInsert(user, connection);
                if (preparedStatement == null) {
                    return;
                }

                preparedStatement.execute();
                cache.put(user, user.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) {
        T t = null;
        try {

            try (Statement statem = connection.createStatement()) {
                Table table = clazz.getAnnotation(Table.class);
                if (table == null) {
                    System.out.println("Класс не подходит для хранения данных");
                    return null;
                }

                DataSet fromCache = cache.get(id);
                if (fromCache != null) {
                    fromCache.setAccessed();
                    return (T) fromCache;
                } else {
                    String tableName = table.value();
                    String sql = "SELECT * from " + tableName + " WHERE id= ?;";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setLong(1, id);
                    preparedStatement.execute();
                    try (ResultSet resultSet = preparedStatement.getResultSet()) {

                        t = Parser.parseIntoObject(resultSet, clazz);
                    }
                    cache.put(t, t.getId());
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    public int getHitCount() {
        return cache.getHitCount();
    }

    public int getMissCount() {
        return cache.getMissCount();
    }

    public CacheEngine getCache() {
        return cache;
    }
}

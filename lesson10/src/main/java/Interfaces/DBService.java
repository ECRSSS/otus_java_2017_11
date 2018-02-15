package Interfaces;

import DataSets.UserDataSet;

import java.util.List;

/**
 * Created by Administrator on 2/13/2018.
 */
public interface DBService {
    String getLocalStatus();

    boolean save(UserDataSet dataSet);

    UserDataSet read(long id);

    UserDataSet readByName(String name);

    List<UserDataSet> readAll();

    void shutdown();
}
package Interfaces;

import DataSets.UserDataSet;

import java.util.List;

/**
 * Created by Administrator on 2/13/2018.
 */
public interface DBService {
    String getLocalStatus();

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    UserDataSet readByName(String name);

    List<UserDataSet> readAll();

    void shutdown();
}
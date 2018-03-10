package Cache;

import MyORM.DataSet;

/**
 * Created by Administrator on 01.03.2018.
 */
public interface CacheEngineInterface<T extends DataSet> {

    void put(T element,Long key);

    T get(Long key);

    int getHitCount();

    int getMissCount();

    void dispose();
}
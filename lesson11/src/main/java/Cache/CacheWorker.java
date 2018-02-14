package Cache;

import MyORM.DataSet;
import MyORM.UserDataSet;
import javafx.scene.chart.PieChart;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2/14/2018.
 */
public class CacheWorker {
    private static CacheWorker singleton;

    private Map<Long, SoftReference<DataSet>> cache = new LinkedHashMap<>(100);

    private CacheWorker(){}
    public static CacheWorker getInstance()
    {
        if(singleton==null)
        {
            singleton=new CacheWorker();
        }
        return singleton;
    }

    public void putIntoCache(DataSet userDataSet)
    {
        SoftReference<DataSet> sr=new SoftReference<>(userDataSet);
        cache.put(userDataSet.getId(),sr);
        System.out.println("Запись в кэш-----"+"id:"+userDataSet.getId());
    }
    public DataSet getFromCache(long id)
    {
        DataSet dataSet=null;
        SoftReference<DataSet> sr=cache.get(id);
        if(sr!=null) {
            dataSet = sr.get();
        }
        if(dataSet==null)
        {
            System.out.println("В кэше нет такого объекта, читаем из базы");
        }
        else {System.out.println("Взят объект из кэша");}
        return dataSet;
    }


}

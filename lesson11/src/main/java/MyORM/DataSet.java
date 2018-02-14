package MyORM;

import MyORM.Annotations.Column;

/**
 * Created by Administrator on 2/1/2018.
 */
public abstract class DataSet {
    @Column("id")
    private long id;
    public long getId(){return id;}


    DataSet(long id){this.id=id;}
}

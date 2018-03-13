package Otus.MyORM;

import Otus.MyORM.Annotations.Column;

/**
 * Created by Administrator on 2/1/2018.
 */
public abstract class DataSet {
    @Column("id")
    private long id;
    public long getId(){return id;}

    private final long creationTime;
    private long lastAccessTime;


    DataSet(long id){this.id=id;
        this.creationTime = System.currentTimeMillis();
        this.lastAccessTime = System.currentTimeMillis();
    }

    public void setAccessed() {
        lastAccessTime = System.currentTimeMillis();
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

}

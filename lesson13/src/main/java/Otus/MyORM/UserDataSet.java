package Otus.MyORM;

import Otus.MyORM.Annotations.Column;
import Otus.MyORM.Annotations.Table;

/**
 * Created by Administrator on 2/1/2018.
 */
@Table("OTUS")
public class UserDataSet extends DataSet {
    
    @Column("name")
    private String name;

    @Column("age")
    private int age;

    public int getAge(){return age;}
    public String getName(){return name;}

    public UserDataSet(long id, String name, int age)
    {
        super(id);
        this.name=name;
        this.age=age;
    }
}

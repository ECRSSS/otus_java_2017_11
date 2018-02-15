package DataSets;


import javax.persistence.*;


/**
 * Created by Administrator on 2/13/2018.
 */
@Entity
@Table(name = "OTUS")
public class UserDataSet extends DataSet {

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet addressDataSet;


    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;


    public UserDataSet() {
    }

    public UserDataSet(String name, int age,String street) {
        this.setId(-1);
        this.name = name;
        this.age = age;
        addressDataSet=new AddressDataSet(street);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return new String("id:"+super.getId()+" name:"+getName()+" age:"+getAge()+" street: "+addressDataSet.getStreet());
    }

}
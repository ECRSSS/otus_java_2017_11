package DataSets;

import javax.persistence.*;

/**
 * Created by Administrator on 2/13/2018.
 */
@Entity
@Table(name="address")
public class AddressDataSet{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="street")
    private String street;

    public String getStreet()
    {return street;}

    public AddressDataSet() {
    }

    public AddressDataSet(String street) {
        this.street = street;
    }
}

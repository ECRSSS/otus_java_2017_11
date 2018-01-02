package Banknotes;

import java.util.ArrayList;

/**
 * Created by Administrator on 01.01.2018.
 */
public enum Nominal {
    B50(50),B100(100),B500(500),B1000(1000),B5000(5000);

    private int nominal;

    private Nominal(int nominal)
    {
        this.nominal=nominal;
    }

    public int getNominal(){return nominal;}
}

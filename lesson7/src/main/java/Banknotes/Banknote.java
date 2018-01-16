package Banknotes;

/**
 * Created by Administrator on 01.01.2018.
 */
public class Banknote {

    private Nominal nominal;
    public Nominal getNominal(){return nominal;}

    public Banknote(Nominal nominal)
    {
        this.nominal=nominal;
    }
}

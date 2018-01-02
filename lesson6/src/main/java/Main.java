import ATM.AtmSingleton;
import Banknotes.Banknote;
import Banknotes.Nominal;

import javax.net.ssl.SSLContext;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 01.01.2018.
 */
public class Main {
    public static void main(String[] args)
    {
        AtmSingleton atm=AtmSingleton.getInstance();
        ArrayList<Banknote> banknotes=new ArrayList<>();
        banknotes.add(new Banknote(Nominal.B100));
        banknotes.add(new Banknote(Nominal.B100));
        banknotes.add(new Banknote(Nominal.B100));
        banknotes.add(new Banknote(Nominal.B100));
        banknotes.add(new Banknote(Nominal.B100));
        banknotes.add(new Banknote(Nominal.B100));
        banknotes.add(new Banknote(Nominal.B100));

        banknotes.add(new Banknote(Nominal.B500));

        banknotes.add(new Banknote(Nominal.B1000));

        banknotes.add(new Banknote(Nominal.B5000));
        banknotes.add(new Banknote(Nominal.B50));


        atm.addMoney(banknotes);

        atm.getState();

        atm.getMoney(500);
        atm.getState();
    }
}

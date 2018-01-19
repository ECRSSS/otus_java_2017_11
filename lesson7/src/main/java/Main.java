import ATM.Atm;
import AtmDepartment.AtmDep;
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
        AtmDep atmDep=AtmDep.getAtmDepartmentInstance();
        atmDep.addAtmWithMoney(4,2,2,1,1);
       // atmDep.addAtmWithMoney(3,1,4,2,5);
       // atmDep.addAtmWithMoney(4,9,2,1,0);

        Atm atm1=atmDep.getAtm(0);
        atm1.getState();
        atm1.getMoney(45000);
        atmDep.getCurrentMoneyState();
       // atmDep.setAllAtmsToStartState();
       // atmDep.getCurrentMoneyState();
    }
}

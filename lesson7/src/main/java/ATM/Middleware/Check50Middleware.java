package ATM.Middleware;

import ATM.Atm;
import ATM.Parts.BanknotesCell;

import java.util.ArrayList;

/**
 * Created by Administrator on 19.01.2018.
 */
public class Check50Middleware extends Middleware {

    public boolean check(int money, ArrayList<BanknotesCell> cells, Atm atm)
    {
        if(money%50==0)
        {
            return checkNext(money,cells,atm);
        }
        else
        {
            System.out.println("Сумма не кратна 50");
            return false;
        }
    }
}

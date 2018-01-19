package ATM.Middleware;

import ATM.Atm;
import ATM.Money.NeededMoney;
import ATM.Parts.BanknotesCell;

import java.util.ArrayList;

/**
 * Created by Administrator on 19.01.2018.
 */
public class CheckMoneyIsOpenMiddleware extends Middleware {
    public boolean check(int money, ArrayList<BanknotesCell> cells, Atm atm)
    {
        NeededMoney neededMoney=new NeededMoney(money);
        for(BanknotesCell cell : cells)
        {
            cell.getMoney(neededMoney);
        }
        if(neededMoney.isOpen())
        {
            atm.addMoney(neededMoney.getBanknotes());
            System.out.println("Сумма не может быть выдана из-за нехватки банкнот");
            return false;
        }
        return checkNext(money, cells, atm);
    }
}

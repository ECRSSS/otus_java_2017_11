package ATM.Middleware;

import ATM.Atm;
import ATM.Parts.BanknotesCell;

import java.util.ArrayList;

/**
 * Created by Administrator on 19.01.2018.
 */
public abstract class Middleware {
    private Middleware next;

    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(int money, ArrayList<BanknotesCell> cells, Atm atm);


    protected boolean checkNext(int money, ArrayList<BanknotesCell> cells, Atm atm) {
        if (next == null) {
            return true;
        }
        return next.check(money, cells, atm);
    }
}

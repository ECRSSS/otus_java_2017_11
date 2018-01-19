package ATM;

import ATM.Middleware.Check50Middleware;
import ATM.Middleware.CheckMoneyIsOpenMiddleware;
import ATM.Middleware.Middleware;
import ATM.Money.NeededMoney;
import ATM.Parts.BanknotesCell;
import Banknotes.Banknote;
import Banknotes.Nominal;

import java.util.ArrayList;

/**
 * Created by Administrator on 01.01.2018.
 */
public class Atm implements Cloneable {

    private ArrayList<BanknotesCell> cells;

    public Atm clone()
    {
        Atm cloneAtm=new Atm();
        ArrayList<Banknote> banknotes=new ArrayList<>();
        for (BanknotesCell cell:cells) {
            for(int i=0;i<cell.getNumOfBanknotes();i++)
            {
                banknotes.add(new Banknote(cell.getTypeNominal()));
            }
        }
        cloneAtm.addMoney(banknotes);
        return cloneAtm;
    }

    public Atm()
    {
        cells=new ArrayList<>();
        cells.add(new BanknotesCell(Nominal.B5000));
        cells.add(new BanknotesCell(Nominal.B1000));
        cells.add(new BanknotesCell(Nominal.B500));
        cells.add(new BanknotesCell(Nominal.B100));
        cells.add(new BanknotesCell(Nominal.B50));
    }

    private BanknotesCell findNeededCell(Nominal nominal)
    {
        for(BanknotesCell cell:cells)
        {
            if(cell.getNominal()==nominal.getNominal())
            {
                return cell;
            }
        }
        return null;
    }
    public void addMoney(ArrayList<Banknote> banknotes)
    {
        for(Banknote banknote : banknotes)
        {
            findNeededCell(banknote.getNominal()).add(banknote);
        }
    }
    public void getMoney(int money)
    {
        Middleware middleware=new Check50Middleware();
        middleware.linkWith(new CheckMoneyIsOpenMiddleware());
        middleware.check(money,cells,this);
    }

    public int getState()
    {
        String message="";
        int ostatok=0;
        for(BanknotesCell cell:cells)
        {
            message+=cell.getNominal()+" rur: "+cell.getNumOfBanknotes()+"/";
            ostatok+=cell.getNumOfBanknotes()*cell.getNominal();
        }
        System.out.println(message);
        System.out.println(ostatok+" rur");
        return ostatok;

    }
}

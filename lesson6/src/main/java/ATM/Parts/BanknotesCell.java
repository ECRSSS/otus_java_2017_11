package ATM.Parts;

import ATM.Money.NeededMoney;
import Banknotes.Banknote;
import Banknotes.Nominal;

import java.util.ArrayList;

/**
 * Created by Administrator on 01.01.2018.
 */
public class BanknotesCell {
    private ArrayList<Banknote> banknotes=new ArrayList<>();
    private Nominal nominal;

    public BanknotesCell(Nominal nominal)
    {
        this.nominal=nominal;
    }
    public void add(Banknote banknote)
    {
        banknotes.add(banknote);
    }

    public int getNominal(){return nominal.getNominal();}
    public int getNumOfBanknotes(){return banknotes.size();}
    public void getMoney(NeededMoney neededMoney)
    {
        if(neededMoney.isOpen())
        {
            int money=neededMoney.getMoney();
            int needed=money/getNominal();
            if(getNumOfBanknotes()>needed)
            {
                for(int i=0;i<needed;i++)
                {
                    neededMoney.addMoney(banknotes.remove(0));
                }
            }
            else
            {
                for(int i=0;i<getNumOfBanknotes();i++)
                {
                    neededMoney.addMoney(banknotes.remove(0));
                }
            }

        }
    }
}

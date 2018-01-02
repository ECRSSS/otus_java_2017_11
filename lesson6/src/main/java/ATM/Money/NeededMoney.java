package ATM.Money;

import Banknotes.Banknote;
import Banknotes.Nominal;

import java.util.ArrayList;

/**
 * Created by Administrator on 01.01.2018.
 */
public class NeededMoney {
    private int money;
    private boolean isOpen=false;
    ArrayList<Banknote> banknotes=new ArrayList<>();

    public NeededMoney(int money)
    {
        if(!(money%50==0))
        {
            System.out.println("Ошибка, сумма не кратна 50");
            return;
        }
        this.money=money;
        if(money>0)
        {
            isOpen=true;
        }
    }
    public boolean isOpen()
    {
        return isOpen;
    }
    public int getMoney()
    {
        return money;
    }
    public void addMoney(Banknote banknote)
    {
        banknotes.add(banknote);
        money-=banknote.getNominal().getNominal();
        if(money==0)
        {
            isOpen=false;
        }
    }
    public ArrayList<Banknote> getBanknotes()
    {return banknotes;}


}

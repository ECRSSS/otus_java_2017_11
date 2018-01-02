package ATM;

import ATM.Money.NeededMoney;
import ATM.Parts.BanknotesCell;
import Banknotes.Banknote;
import Banknotes.Nominal;

import java.util.ArrayList;

/**
 * Created by Administrator on 01.01.2018.
 */
public class AtmSingleton {

    private static AtmSingleton atmInstance;

    private ArrayList<BanknotesCell> cells;

    private AtmSingleton()
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
        NeededMoney neededMoney=new NeededMoney(money);
        for(BanknotesCell cell : cells)
        {
            cell.getMoney(neededMoney);
        }
        if(neededMoney.isOpen())
        {
            atmInstance.addMoney(neededMoney.getBanknotes());
            System.out.println("Сумма не может быть выдана из-за нехватки банкнот");
        }
    }

    public static AtmSingleton getInstance()
    {
        if(atmInstance==null)
        {
            atmInstance=new AtmSingleton();
        }
        return atmInstance;
    }

    public void getState()
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

    }
}

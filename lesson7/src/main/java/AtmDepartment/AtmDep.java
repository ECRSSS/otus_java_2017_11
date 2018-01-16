package AtmDepartment;

import ATM.Atm;
import Banknotes.Banknote;
import Banknotes.Nominal;
import Support.Support;

import java.util.ArrayList;

/**
 * Created by Administrator on 16.01.2018.
 */
public class AtmDep {
    private ArrayList<Atm> atms=new ArrayList<Atm>();

    private ArrayList<Atm> atmsBeginState=new ArrayList<Atm>();

    private static AtmDep AtmDepartment;

    private AtmDep(){}

    public static AtmDep getAtmDepartmentInstance()
    {
        if(AtmDepartment==null)
        {
            AtmDepartment=new AtmDep();
        }
        return AtmDepartment;
    }
    public void addAtmWithMoney(int b5000,int b1000,int b500,int b100,int b50)
    {

        Atm atm=new Atm();
        ArrayList<Banknote> banknotes=new ArrayList<>();
        for(int i=0;i<b5000;i++)
        {
            banknotes.add(new Banknote(Nominal.B5000));
        }
        for(int i=0;i<b1000;i++)
        {
            banknotes.add(new Banknote(Nominal.B1000));
        }
        for(int i=0;i<b500;i++)
        {
            banknotes.add(new Banknote(Nominal.B500));
        }
        for(int i=0;i<b100;i++)
        {
            banknotes.add(new Banknote(Nominal.B100));
        }
        for(int i=0;i<b50;i++)
        {
            banknotes.add(new Banknote(Nominal.B50));
        }
        atm.addMoney(banknotes);
        atmsBeginState.add(atm.clone());
        atms.add(atm);
    }
    public void setAtmToStartState(int index)
    {
        atms.set(index,atmsBeginState.get(index).clone());
    }
    public void setAllAtmsToStartState()
    {
        for (int i=0;i<atms.size();i++) {
            atms.set(i,atmsBeginState.get(i).clone());
        }
    }
    public void getCurrentMoneyState()
    {
        int money=0;
        for (Atm atm:atms)
        {
            money+=atm.getState();
        }
        System.out.println("Остаток на всех банкоматах: "+money);
    }
    public Atm getAtm(int index)
    {
       return atms.get(index);
    }

}

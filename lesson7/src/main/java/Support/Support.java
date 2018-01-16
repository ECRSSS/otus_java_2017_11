package Support;

/**
 * Created by Administrator on 16.01.2018.
 */
public class Support {
    public static boolean moneyCheck(int money)
    {
        if(money%50==0)
        {
            return true;
        }
        else
        {
            System.out.println("Сумма не кратна 50");
            return false;
        }

    }
}

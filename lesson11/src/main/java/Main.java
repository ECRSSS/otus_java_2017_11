import Connection.ConnectionHelper;
import MyORM.DataSet;
import MyORM.Parser.Parser;
import MyORM.UserDataSet;

/**
 * Created by Administrator on 2/1/2018.
 */
public class Main {

    public static void main(String[] args)
    {
        Executor executor=new Executor(ConnectionHelper.getConnection());

        for(int i=402;i<500;i++)
        {
            executor.save(new UserDataSet(i,"John",44));

            System.out.println(i+"--------->");
            System.out.println("Hit: "+executor.getHitCount());
            System.out.println("Miss: "+executor.getMissCount());

            try{Thread.sleep(100);}
            catch (InterruptedException e){e.printStackTrace();}
            DataSet ds=executor.load(i,UserDataSet.class);

        }
        executor.closeConnection();

    }
}

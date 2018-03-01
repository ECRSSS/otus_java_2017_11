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

        for(int i=802;i<900;i++)
        {
            executor.save(new UserDataSet(i,"John",44));

            System.out.println(i+"--------->");
            System.out.println("Hit: "+executor.getHitCount());
            System.out.println("Miss: "+executor.getMissCount());
            DataSet ds=executor.load(i,UserDataSet.class);

        }
        for(int i=602;i<700;i++)
        {
            System.out.println(i+"--------->");
            System.out.println("Hit: "+executor.getHitCount());
            System.out.println("Miss: "+executor.getMissCount());
            DataSet ds=executor.load(i,UserDataSet.class);

        }
        executor.closeConnection();

    }
}

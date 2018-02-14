import Connection.ConnectionHelper;
import MyORM.Parser.Parser;
import MyORM.UserDataSet;

/**
 * Created by Administrator on 2/1/2018.
 */
public class Main {

    public static void main(String[] args)
    {
        Executor executor=new Executor(ConnectionHelper.getConnection());
        executor.save(new UserDataSet(100,"John",44));

        UserDataSet dataSet= executor.load(100,UserDataSet.class);
        executor.closeConnection();

        System.out.println(dataSet.getName());
        System.out.println(dataSet.getAge());
        System.out.println(dataSet.getId());
    }
}

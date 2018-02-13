import DataSets.UserDataSet;
import Interfaces.DBService;
import Service.DBServiceImpl;

import java.util.List;

/**
 * Created by Administrator on 2/13/2018.
 */
public class Main {
    public static void main(String[] args)
    {
        DBService dbService = new DBServiceImpl();

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);

        dbService.save(new UserDataSet("Otus",123,"Newyorkskaya"));
        dbService.save(new UserDataSet("John",23,"hhhh"));
        dbService.save(new UserDataSet("Jane",18,"fdfsr"));


        UserDataSet dataSet = dbService.read(1);
        System.out.println(dataSet);

        dataSet = dbService.readByName("John");
        System.out.println(dataSet.getName());

        List<UserDataSet> dataSets = dbService.readAll();
        for (UserDataSet userDataSet : dataSets) {
            System.out.println(userDataSet);
        }

        dbService.shutdown();
    }
}

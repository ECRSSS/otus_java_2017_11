package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 2/1/2018.
 */
public class ConnectionHelper {

   public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            String url = "jdbc:mysql://" +
                    "130.211.80.126:" +
                    "3306/" +
                    "test?" +
                    "user=root&" +
                    "password=1&" +

                    "useSSL=false";


            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
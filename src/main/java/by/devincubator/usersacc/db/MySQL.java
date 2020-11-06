package by.devincubator.usersacc.db;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class MySQL {

    private static String userName;
    private static String password;
    private static String connectionUrl;

   static {
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream("config.properties")) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            prop.load(input);
            userName = prop.getProperty("mysql_user");
            password = prop.getProperty("mysql_pass");
            connectionUrl = prop.getProperty("mysql_url");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnect() {
        Connection connection = null;
        try {
            connection = getConnection(connectionUrl, userName, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}

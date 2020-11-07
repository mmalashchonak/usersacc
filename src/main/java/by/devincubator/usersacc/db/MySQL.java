package by.devincubator.usersacc.db;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class MySQL {

    private static String userName = "root";
    private static String password = "1234";
    private static String connectionUrl = "jdbc:mysql://localhost:3306/usersacc?serverTimezone=Europe/Minsk";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
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

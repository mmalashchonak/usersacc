package by.devincubator.usersacc.db;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class MySQL {

    private static final String userName = "root";
    private static final String password = "1234";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/usersacc?serverTimezone=Europe/Minsk";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnect() throws SQLException {
        Connection connection = getConnection(connectionUrl, userName, password);
        return connection;
    }
}

package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class Util {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "ещддшлут");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}

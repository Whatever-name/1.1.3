package jm.task.core.jdbc.dao;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.a.result.NativeResultset;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.*;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS USERS(ID INTEGER NOT NULL AUTO_INCREMENT" +
                    ", FIRSTNAME VARCHAR(255), LASTNAME VARCHAR(255)," +
                    "AGE INTEGER(3), PRIMARY KEY (ID))");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS USERS");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection(); PreparedStatement statement =
                connection.prepareStatement("INSERT INTO USERS (FIRSTNAME, LASTNAME, AGE) VALUES(?,?,?);")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection(); PreparedStatement statement =
                connection.prepareStatement("DELETE FROM USERS WHERE ID = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultset = statement.executeQuery("SELECT * FROM USERS");
            while (resultset.next()) {
                User user = new User(resultset.getString(2), resultset.getString(3),
                        resultset.getByte(4));
                user.setId(resultset.getLong(1));
                users.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE USERS");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


package jm.task.core.jdbc.dao;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.a.result.NativeResultset;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.*;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS USERS(ID INTEGER NOT NULL AUTO_INCREMENT" +
                    ", FIRSTNAME VARCHAR(255), LASTNAME VARCHAR(255)," +
                    "AGE INTEGER(3), PRIMARY KEY (ID))");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS USERS");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try ( PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO USERS (FIRSTNAME, LASTNAME, AGE) VALUES(?,?,?);")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("DELETE FROM USERS WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try (Statement statement = connection.createStatement()) {
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
        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE USERS");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


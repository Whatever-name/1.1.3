package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


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

    public static SessionFactory getSessionfactory() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "ещддшлут");
        properties.setProperty("connection.driver_class", "com.mysql.jdbc.Driver");
//      properties.setProperty("hibernate.show_sql", "true");
//      properties.setProperty("hibernate.hbm2ddl.auto", "update");
//      properties.setProperty("hibernate.format_sql", "true");

        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        configuration.setProperties(properties);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);

    }
}









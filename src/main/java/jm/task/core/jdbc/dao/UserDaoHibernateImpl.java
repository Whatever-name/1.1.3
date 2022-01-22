package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionfactory();
    }

    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS USERS" +
                    "(ID INTEGER NOT NULL AUTO_INCREMENT" +
                    ", NAME VARCHAR(255), LASTNAME VARCHAR(255)," +
                    "AGE INTEGER(3), PRIMARY KEY (ID))");
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void dropUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS USERS");
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем " + user.getName() + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createQuery("From User").list();
        return users;
    }


    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE USERS");
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

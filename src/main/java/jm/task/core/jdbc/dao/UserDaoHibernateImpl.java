package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    SessionFactory sf = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS usersHib " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";
        Query query = session.createSQLQuery(sql).addEntity(User.class);

        query.executeUpdate();    // что в начале, экзекью апдейт или коммит??
        transaction.commit();
        session.close();
        System.out.println("Successfully created!");
    }

    @Override
    public void dropUsersTable() {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS usersHib";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        System.out.println("Successfully DELETED!");

        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sf.openSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();

        //System.out.println("Added successfully");

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }

}

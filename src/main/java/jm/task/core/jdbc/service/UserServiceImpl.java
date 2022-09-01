package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    //UserDao userDaoJDBC = new UserDaoJDBCImpl();
    UserDao userDaoHib = new UserDaoHibernateImpl();


    public void createUsersTable() throws SQLException {
        userDaoHib.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        userDaoHib.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoHib.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        userDaoHib.removeUserById(id);

    }

    public List<User> getAllUsers() throws SQLException {
        return userDaoHib.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        userDaoHib.cleanUsersTable();
    }
}

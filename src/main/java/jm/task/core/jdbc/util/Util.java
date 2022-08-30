package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "5q81LrDcI7w15!";

    public static Connection getConnection() throws SQLException {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //System.out.println("Соединение с БД установлено");

            /*if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
            connection.close();

             */
        } catch (SQLException e) {

        }
        return connection;
    }
    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory () {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                //без XML файла
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/sys?useSSL=false");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "5q81LrDcI7w15!");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

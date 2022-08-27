package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "5q81LrDcI7w15!";

    public static Connection getConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с БД установлено");
            /*if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
            //connection.close();

             */
        } catch (SQLException e) {
            System.out.println("Соединение с БД не установлено");
        }
        return connection;
    }


}

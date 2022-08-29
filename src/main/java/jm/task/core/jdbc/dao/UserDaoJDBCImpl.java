package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    //Connection connection = getConnection();  // Удалил, т.к. ментор попросил инициализировать connection
                                                // внутри методов



    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        String sqlCommand = "CREATE TABLE UsersNew (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), lastname VARCHAR(40),age INT(3))";

        /*try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sqlCommand);
            //System.out.println("Таблица создана!");

         */
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
            //System.out.println("Таблица создана!");
        } catch (SQLException e) {
            //System.out.println("Error in creation of table");
        }

    }

    public void dropUsersTable() throws SQLException {   //Удаление таблицы User(ов) (нет исключение, если таблицы нет)
        String sqlComand = "DROP TABLE UsersNew";

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlComand);
            //System.out.println("Table successfully deleted");
        } catch (SQLException e) {
            //System.out.println("Error in deleted of table");
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO usersnew (name, lastname, age) VALUES (?, ?, ?)";


        try (Connection connection = getConnection()) {
            //preparedStatement = connection.prepareStatement(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            //System.out.println("Ошибка в добавлении юзера");
        }
        // Добавление User в таблицу (ВРОДЕ ОК)
    }

    public void removeUserById(long id) throws SQLException { // Удаление User из таблицы ( по id )

        String sql = "DELETE FROM usersnew where Id = ?";


        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //System.out.println("Ошибка при удалении пользователя по ID");
        }   //////// ВРОДЕ ОК
    }

    
    public List<User> getAllUsers() throws SQLException {

        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM USERSNEW";

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
        } catch (SQLException e) {
            //System.out.println("Error in List");
        }
        return userList;
    }  // Получение всех User(ов) из таблицы (ВРОДЕ ОК)

    
    
    public void cleanUsersTable() throws SQLException {   // Очистка содержания таблицы
        String sql = "Truncate table usersnew";

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            //System.out.println("Таблица очищена");
        } catch (SQLException e) {
            //System.out.println("что-то не так при чистке таблицы");
        }

    }
}

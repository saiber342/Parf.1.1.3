package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl extends Util implements UserDao {


    public UserDaoJDBCImpl()  {

    }

    public void createUsersTable() {
        Statement statement;
        String SQL_TABLE = "CREATE TABLE users " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(45), " +
                " lastName VARCHAR (45), " +
                " age INTEGER, " +
                " PRIMARY KEY (id))";
        try (Connection connection = getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(SQL_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Statement statement;
        String SQL_drop = "DROP TABLE IF EXISTS users";
        try (Connection connection = getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate((SQL_drop));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql_UPDATE = "INSERT INTO users (name, lastName, age)"
                + "VALUES(?,?,?)";
        PreparedStatement statement = null;
        try (Connection connection = getConnection()) {
            statement = connection.prepareStatement(sql_UPDATE);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void removeUserById(long id) {

        PreparedStatement statement = null;
        String SQL_DELETE = "DELETE FROM users WHERE id = ?;";
        try(Connection connection = getConnection()) {
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

        public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String SQL_SELECT = "SELECT * FROM users";

        Statement statement;
        try (Connection connection = getConnection()) {
             statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT);
             while (resultSet.next()) {
                 User user = new User();
                 user.setId(resultSet.getLong("id"));
                 user.setName(resultSet.getString("name"));
                 user.setLastName(resultSet.getString("lastName"));
                 user.setAge(resultSet.getByte("age"));

                 userList.add(user);
             }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            return userList;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        String SQL_TRUNCATE = "TRUNCATE TABLE users;";
        try(Connection connection = getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(SQL_TRUNCATE);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

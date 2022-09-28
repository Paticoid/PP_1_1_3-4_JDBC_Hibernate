package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable()  {
        try (Connection connection = Util.getMySQLConnection();){
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS User(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,name VARCHAR(100) NOT NULL ,lastname VARCHAR(100) NOT NULL,age TINYINT NOT NULL)");
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException|ClassNotFoundException d) {
            throw new RuntimeException(d);
        }

    }


    public void dropUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate("DROP TABLE IF EXISTS User");
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch(SQLException|ClassNotFoundException d){
            throw  new RuntimeException(d);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMySQLConnection()) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User(name,lastname,age) VALUES(?,?,?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        }catch (SQLException|ClassNotFoundException d){
            throw new RuntimeException(d);
        }


    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getMySQLConnection()) {
            try {
                PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM User WHERE id = ?");
                prepareStatement.setLong(1, id);
                prepareStatement.executeUpdate();
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        }catch (SQLException|ClassNotFoundException d){
            throw  new RuntimeException(d);
        }

    }

    public List<User> getAllUsers() {
        try (Connection connection = Util.getMySQLConnection()) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM User");
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    User user = new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age"));
                    users.add(user);
                }
                connection.commit();
                return users;
            } catch (Exception e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        }catch (SQLException | ClassNotFoundException d){
            throw new RuntimeException();
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM User");
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        }catch (SQLException|ClassNotFoundException d) {
            throw new RuntimeException(d);
        }

    }
}

package by.devincubator.usersacc.dao;


import by.devincubator.usersacc.db.MySQL;
import by.devincubator.usersacc.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {

    @Override
    public void putIntoDB(User user) {
        try (Connection connection = MySQL.getConnect()) {
            String sql = "INSERT INTO user (name, sureName) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSureName());
            preparedStatement.executeUpdate();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User getById(int id) {
        User user = null;
        try (Connection connection = MySQL.getConnect()) {
            String sql = "SELECT * FROM user WHERE userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt(("userId")), resultSet.getString("sureName"), resultSet.getString("name"));
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = MySQL.getConnect()) {
            String sql = "SELECT * FROM user";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(("userId")), resultSet.getString("sureName"), resultSet.getString("name")));
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(User user) {
        try (Connection connection = MySQL.getConnect()) {
            String sql = "UPDATE user SET name = ?, sureName = ? WHERE userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSureName());
            preparedStatement.setInt(3, user.getUserId());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = MySQL.getConnect()) {
            String sql = "DELETE FROM user WHERE userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was deleted successfully!");
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

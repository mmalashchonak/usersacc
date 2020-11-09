package by.devincubator.usersacc.dao;


import by.devincubator.usersacc.db.MySQL;
import by.devincubator.usersacc.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {

    private static final String putUserIntoDB = "INSERT INTO user (name, sureName) VALUES (?, ?)";
    private static final String getUserById = "SELECT * FROM user WHERE userId = ?";
    private static final String getAllUsers = "SELECT * FROM user";
    private static final String updateUser = "UPDATE user SET name = ?, sureName = ? WHERE userId = ?";
    private static final String deleteUser = "DELETE FROM user WHERE userId = ?";

    @Override
    public void putIntoDB(User user) {
        try (Connection connection = MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(putUserIntoDB);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSureName());
            preparedStatement.executeUpdate();
        } catch (
                SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public User getById(int id) {
        User user = null;
        try (Connection connection = MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(getUserById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt(("userId")), resultSet.getString("sureName"), resultSet.getString("name"));
            }
        } catch (
                SQLException throwable) {
            throwable.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(getAllUsers);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(("userId")), resultSet.getString("sureName"), resultSet.getString("name")));
            }
        } catch (
                SQLException throwable) {
            throwable.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(User user) {
        try (Connection connection = MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(updateUser);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSureName());
            preparedStatement.setInt(3, user.getUserId());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (
                SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteUser);
            preparedStatement.setInt(1, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was deleted successfully!");
            }
        } catch (
                SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}

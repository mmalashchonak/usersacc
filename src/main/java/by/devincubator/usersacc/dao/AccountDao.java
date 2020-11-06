package by.devincubator.usersacc.dao;

import by.devincubator.usersacc.db.MySQL;
import by.devincubator.usersacc.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao implements Dao<Account> {

    @Override
    public void putIntoDB(Account account) {
        try (Connection connection = MySQL.getConnect()) {
            String sql = "INSERT INTO account (account, userId) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getAccount());
            preparedStatement.setInt(2, account.getUserId());
            preparedStatement.executeUpdate();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Account getById(int id) {
        Account account = null;
        try (Connection connection = MySQL.getConnect()) {
            String sql = "SELECT * FROM account WHERE accountId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account(resultSet.getInt(("accountId")), resultSet.getInt("account"), resultSet.getInt("userId"));
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = MySQL.getConnect()) {
            String sql = "SELECT * FROM account";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                accounts.add(new Account(resultSet.getInt(("accountId")), resultSet.getInt("account"), resultSet.getInt("userId")));
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void update(Account account) {
        try (Connection connection = MySQL.getConnect()) {
            String sql = "UPDATE account SET account = ?, userId = ? WHERE accountId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getAccount());
            preparedStatement.setInt(2, account.getUserId());
            preparedStatement.setInt(3, account.getAccountId());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing account was updated successfully!");
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = MySQL.getConnect()) {
            String sql = "DELETE FROM account WHERE accountId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing account was deleted successfully!");
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


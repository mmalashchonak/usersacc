package by.devincubator.usersacc.dao;

import by.devincubator.usersacc.db.MySQL;
import by.devincubator.usersacc.entity.Account;
import by.devincubator.usersacc.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Dao<T> {

    void putIntoDB(T object);

    T getById(int id);

    List<T> getAll();

    void update(T object);

    void delete(int id);
}

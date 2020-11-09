package by.devincubator.usersacc.dao;

import java.util.List;

public interface Dao<T> {

    void putIntoDB(T object);

    T getById(int id);

    List<T> getAll();

    void update(T object);

    void delete(int id);
}

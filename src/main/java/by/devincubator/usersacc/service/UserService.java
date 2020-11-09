package by.devincubator.usersacc.service;

import by.devincubator.usersacc.dao.Dao;
import by.devincubator.usersacc.entity.Account;
import by.devincubator.usersacc.entity.User;

import java.util.List;
import java.util.NoSuchElementException;

public class UserService {

    private Dao<User> userDao;

    public UserService(Dao<User> userDao) {
        this.userDao = userDao;
    }

    public void putIntoDB(User user) {
        userDao.putIntoDB(user);
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(int id) {
        userDao.delete(id);
    }

    public User getUserByAccount(Account account) {
        User user = getById(account.getUserId());
        if (user != null) {
            return user;
        }
        throw new NoSuchElementException("User ID is invalid!");
    }
}

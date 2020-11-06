package by.devincubator.usersacc.service;

import by.devincubator.usersacc.dao.UserDao;
import by.devincubator.usersacc.entity.Account;
import by.devincubator.usersacc.entity.User;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class UserService {

    private UserDao userDao = new UserDao();

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

    public User getRichestUser() {
        AccountService accountService = new AccountService();
        Account richestAccount = accountService.getAll()
                .stream()
                .max(Comparator.comparing(Account::getAccount))
                .orElseThrow(NoSuchElementException::new);

                User richestUser = getById(richestAccount.getUserId());
        return richestUser;
    }
}

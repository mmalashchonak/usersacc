package by.devincubator.usersacc.service;

import by.devincubator.usersacc.dao.Dao;
import by.devincubator.usersacc.entity.Account;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class AccountService {

    private Dao<Account> accountDao;

    public AccountService(Dao<Account> accountDao) {
        this.accountDao = accountDao;
    }

    public void putIntoDB(Account account) {
        accountDao.putIntoDB(account);
    }

    public Account getById(int id) {
        return accountDao.getById(id);
    }

    public List<Account> getAll() {
        return accountDao.getAll();
    }

    public void update(Account account) {
        accountDao.update(account);
    }

    public void delete(int id) {
        accountDao.delete(id);
    }

    public int getAccountSum() {
        return getAll()
                .stream()
                .mapToInt(Account::getAccount)
                .reduce(0, Integer::sum);
    }

    public Account getRichestAccount() {
        return getAll()
                .stream()
                .max(Comparator.comparing(Account::getAccount))
                .orElseThrow(NoSuchElementException::new);
    }
}

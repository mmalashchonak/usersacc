package by.devincubator.usersacc.service;

import by.devincubator.usersacc.dao.AccountDao;
import by.devincubator.usersacc.entity.Account;
import by.devincubator.usersacc.entity.User;

import java.util.List;

public class AccountService {

    private AccountDao accountDao = new AccountDao();

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
}

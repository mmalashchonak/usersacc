import by.devincubator.usersacc.dao.AccountDao;
import by.devincubator.usersacc.dao.UserDao;
import by.devincubator.usersacc.entity.Account;
import by.devincubator.usersacc.entity.User;
import by.devincubator.usersacc.service.AccountService;
import by.devincubator.usersacc.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AccountServiceTest {

    @Mock
    private AccountDao accountDao;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAccountService() {
        AccountService accountService = new AccountService(accountDao);
        accountService.getById(1);
        Mockito.verify(accountDao).getById(1);
    }

    @Test
    public void testGetAll() {
        AccountService accountService = new AccountService(accountDao);
        accountService.getAll();
        Mockito.verify(accountDao).getAll();
    }

    @Test
    public void testGetById() {
        AccountService accountService = new AccountService(accountDao);
        Mockito.when(accountDao.getById(6)).thenReturn(createTestListOfAccounts()
                .stream()
                .filter(x -> x.getAccountId() == 6)
                .findFirst().orElse(null));
        Account actual = accountService.getById(6);
        Assert.assertEquals(6, actual.getAccount());
        Assert.assertEquals(6, actual.getUserId());
        Mockito.verify(accountDao).getById(6);
    }

    @Test
    public void testAccountSum() {
        AccountService accountService = new AccountService(accountDao);
        Mockito.when(accountDao.getAll()).thenReturn(createTestListOfAccounts());
        int actualSum = accountService.getAccountSum();
        Assert.assertEquals(21, actualSum);
        Mockito.verify(accountDao).getAll();
    }

    @Test
    public void testGetRichestAccount() {
        AccountService accountService = new AccountService(accountDao);
        Mockito.when(accountDao.getAll()).thenReturn(createTestListOfAccounts());
        Account actualRichestAccount = accountService.getRichestAccount();
        Assert.assertEquals(6, actualRichestAccount.getAccountId());
        Mockito.verify(accountDao).getAll();
    }

    private List<Account> createTestListOfAccounts() {
        List<Account> list = new ArrayList<>();
        list.add(new Account(1, 1, 1));
        list.add(new Account(2, 2, 2));
        list.add(new Account(3, 3, 3));
        list.add(new Account(4, 4, 4));
        list.add(new Account(5, 5, 5));
        list.add(new Account(6, 6, 6));
        return list;
    }
}

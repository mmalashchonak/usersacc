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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserService() {
        UserService userService = new UserService(userDao);
        userService.getById(1);
        Mockito.verify(userDao).getById(1);
    }

    @Test
    public void testGetAll() {
        UserService userService = new UserService(userDao);
        userService.getAll();
        Mockito.verify(userDao).getAll();
    }

    @Test
    public void testGetUserById() {
        UserService userService = new UserService(userDao);
        Mockito.when(userDao.getById(6)).thenReturn(createTestListOfUsers()
                .stream()
                .filter(x -> x.getUserId() == 6)
                .findFirst().orElse(null));
        User actual = userService.getById(6);
        Assert.assertEquals("My name6", actual.getName());
        Assert.assertEquals("My surename6", actual.getSureName());
        Mockito.verify(userDao).getById(6);
    }

    @Test
    public void testGetUserByAccount() {
        UserService userService = new UserService(userDao);
        Account testAccount = createTestAccount();
        Mockito.when(userDao.getById(testAccount.getUserId())).
                thenReturn(createTestListOfUsers()
                        .stream()
                        .filter(x -> x.getUserId() == testAccount.getUserId())
                        .findFirst().orElse(null));
        User actualUser = userService.getUserByAccount(testAccount);
        Assert.assertEquals(6, actualUser.getUserId());
        Mockito.verify(userDao).getById(testAccount.getUserId());
    }

    private Account createTestAccount() {
        Account account = new Account();
        account.setAccountId(6);
        account.setAccount(6);
        account.setUserId(6);
        return account;
    }

    private List<User> createTestListOfUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "My name1", "My surename1"));
        list.add(new User(2, "My name2", "My surename2"));
        list.add(new User(3, "My name3", "My surename3"));
        list.add(new User(4, "My name4", "My surename4"));
        list.add(new User(5, "My name5", "My surename5"));
        list.add(new User(6, "My name6", "My surename6"));
        return list;
    }
}

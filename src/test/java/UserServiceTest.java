import by.devincubator.usersacc.dao.UserDao;
import by.devincubator.usersacc.entity.Account;
import by.devincubator.usersacc.entity.User;
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
import java.util.List;
import java.util.NoSuchElementException;

public class UserServiceTest {

    private static final int testAccount = 1;
    private static final int testAccountId = 1;
    private static final int testUserId = 1;
    private static final String testUserName = "My name1";
    private static final String testUserSureName = "My surename1";

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
        userService.getById(testUserId);
        Mockito.verify(userDao).getById(testUserId);
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
        Mockito.when(userDao.getById(testUserId)).thenReturn(createTestListOfUsers()
                .stream()
                .filter(x -> x.getUserId() == testUserId)
                .findFirst().orElseThrow(NoSuchElementException::new));
        User actual = userService.getById(testUserId);
        Assert.assertEquals(testUserName, actual.getName());
        Assert.assertEquals(testUserSureName, actual.getSureName());
        Mockito.verify(userDao).getById(testUserId);
    }

    @Test
    public void testGetUserByAccount() {
        UserService userService = new UserService(userDao);
        Account testAccount = createTestAccount();
        Mockito.when(userDao.getById(testAccount.getUserId())).
                thenReturn(createTestListOfUsers()
                        .stream()
                        .filter(x -> x.getUserId() == testAccount.getUserId())
                        .findFirst().orElseThrow(NoSuchElementException::new));
        User actualUser = userService.getUserByAccount(testAccount);
        Assert.assertEquals(testUserId, actualUser.getUserId());
        Mockito.verify(userDao).getById(testAccount.getUserId());
    }

    private Account createTestAccount() {
        Account account = new Account();
        account.setAccountId(testAccountId);
        account.setAccount(testAccount);
        account.setUserId(testUserId);
        return account;
    }

    private List<User> createTestListOfUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User(testUserId, testUserName, testUserSureName));
        list.add(new User(2, "My name2", "My surename2"));
        list.add(new User(3, "My name3", "My surename3"));
        list.add(new User(4, "My name4", "My surename4"));
        list.add(new User(5, "My name5", "My surename5"));
        list.add(new User(6, "My name6", "My surename6"));
        return list;
    }
}

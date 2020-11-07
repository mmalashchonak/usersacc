package by.devincubator.usersacc.servlet;

import by.devincubator.usersacc.dao.AccountDao;
import by.devincubator.usersacc.dao.UserDao;
import by.devincubator.usersacc.entity.Account;
import by.devincubator.usersacc.entity.User;
import by.devincubator.usersacc.service.AccountService;
import by.devincubator.usersacc.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/GetRichestUserServlet"})
public class GetRichestUserServlet extends HttpServlet {

    private UserService userService;

    private AccountService accountService;

    public GetRichestUserServlet() {
        userService = new UserService(new UserDao());
        accountService = new AccountService(new AccountDao());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account richestAccount = accountService.getRichestAccount();
        User richestUser = userService.getUserByAccount(richestAccount);
        request.setAttribute("richestUser", richestUser.getName() + " " + richestUser.getSureName());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
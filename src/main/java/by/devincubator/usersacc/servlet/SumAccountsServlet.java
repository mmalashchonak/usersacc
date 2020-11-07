package by.devincubator.usersacc.servlet;

import by.devincubator.usersacc.dao.AccountDao;
import by.devincubator.usersacc.service.AccountService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/SumAccountsServlet"})
public class SumAccountsServlet extends HttpServlet {

    private AccountService accountService;

    public SumAccountsServlet() {
        accountService = new AccountService(new AccountDao());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("sum", accountService.getAccountSum());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
package by.devincubator.usersacc.servlet;

import by.devincubator.usersacc.dao.AccountDao;
import by.devincubator.usersacc.entity.Account;
import by.devincubator.usersacc.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/AccountServlet"})
public class AccountServlet extends HttpServlet {

    private AccountService accountService;

    public AccountServlet() {
        accountService = new AccountService(new AccountDao());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("action") != null) {
            switch (request.getParameter("action")) {
                case "delete": {
                    doDelete(request, response);
                    return;
                }
                case "update": {
                    doPut(request, response);
                    return;
                }
            }
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<a href='index.jsp'>Back to Main page</a>");
        out.println("<h1>Accounts list</h1>");
        out.println("<h3>Add New Account</h1>" +
                "<form action=\"AccountServlet\" method=\"post\">" +
                "    <table>" +
                "        <tr><td>Account:</td><td><input id=\"account\" type=\"text\" name=\"account\"/></td></tr>" +
                "        <tr><td>UserId:</td><td><input id=\"userId\" type=\"test\" name=\"userId\"/></td></tr>" +
                "        </td></tr>" +
                "        <tr><td colspan=\"2\"><input id=\"save\" type=\"submit\" value=\"Save Account\"/></td></tr>" +
                "    </table>" +
                "</form>");

        printAccountsTable(out);
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accountStr = request.getParameter("account");
        int account = Integer.parseInt(accountStr);
        String userIdStr = request.getParameter("userId");
        int userId = Integer.parseInt(userIdStr);
        Account acc = new Account(account, userId);

        if (request.getParameter("accountId") != null) {
            acc.setAccountId((Integer.parseInt(request.getParameter("accountId"))));
            accountService.update(acc);
        } else {
            accountService.putIntoDB(acc);
        }
        response.sendRedirect("AccountServlet");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountIdStr = request.getParameter("accountId");
        int accountId = Integer.parseInt(accountIdStr);
        accountService.delete(accountId);
        response.sendRedirect("AccountServlet");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountIdStr = request.getParameter("accountId");
        int accountId = Integer.parseInt(accountIdStr);
        Account acc = accountService.getById(accountId);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<a href='index.jsp'>Back to Main page</a>");
        out.println("<h1>Accounts list</h1>");
        out.println("<h3>Edit Account</h1>" +
                "<form action=\"AccountServlet\" method=\"post\">" +
                "    <table>" +
                "        <tr><td></td><td><input type='hidden' name='accountId' value='" + acc.getAccountId() + "'/></td></tr>" +
                "        <tr><td>Account:</td><td><input id=\"account\" type=\"text\" name=\"account\" value='" + acc.getAccount() + "'/></td></tr>" +
                "        <tr><td>UserId:</td><td><input id=\"userId\" type=\"test\" name=\"userId\" value='" + acc.getUserId() + "'/></td></tr>" +
                "        </td></tr>" +
                "        <tr><td colspan=\"2\"><input id=\"save\" type=\"submit\" value=\"Edit & Save\"/></td></tr>\n" +
                "    </table>" +
                "</form>");

        printAccountsTable(out);
        out.close();
    }

    private void printAccountsTable(PrintWriter out) {
        List<Account> list = accountService.getAll();

        out.print("<table border='1'");
        out.print("<tr><th>AccountId</th><th>Account</th><th>UsedId</th><th>Edit</th><th>Delete</th></tr>");
        for (Account account : list) {
            out.print("<tr><td>" + account.getAccountId() + "</td><td>" + account.getAccount() + "</td><td>" + account.getUserId() + "</td>" +
                    "<td><a href='AccountServlet?action=update&accountId=" + account.getAccountId() + "'>edit</a></td>" +
                    "<td><a href='AccountServlet?action=delete&accountId=" + account.getAccountId() + "'>delete</a></td>");
        }
        out.print("</table>");
    }
}
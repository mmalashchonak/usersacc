package by.devincubator.usersacc.servlet;

import by.devincubator.usersacc.dao.UserDao;
import by.devincubator.usersacc.entity.User;
import by.devincubator.usersacc.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/UserServlet"})
public class UserServlet extends HttpServlet {

    private UserService userService;

    public UserServlet() {
        userService = new UserService(new UserDao());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

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
        out.println("<h1>Users list</h1>");
        out.println("<h3>Add New User</h1>" +
                "<form action=\"UserServlet\" method=\"post\">" +
                "    <table>" +
                "        <tr><td>Name:</td><td><input id=\"name\" type=\"text\" name=\"name\"/></td></tr>" +
                "        <tr><td>SureName:</td><td><input id=\"surename\" type=\"test\" name=\"surename\"/></td></tr>" +
                "        </td></tr>" +
                "        <tr><td colspan=\"2\"><input id=\"save\" type=\"submit\" value=\"Save User\"/></td></tr>" +
                "    </table>" +
                "</form>");

        printUsersTable(out);
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String sureName = request.getParameter("surename");
        User user = new User(name, sureName);

        if (request.getParameter("userId") != null) {
            user.setUserId(Integer.parseInt(request.getParameter("userId")));
            userService.update(user);
        } else {
            userService.putIntoDB(user);
        }
        response.sendRedirect("UserServlet");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userIdStr = request.getParameter("userId");
        int userId = Integer.parseInt(userIdStr);
        userService.delete(userId);
        response.sendRedirect("UserServlet");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userIdStr = request.getParameter("userId");
        int userId = Integer.parseInt(userIdStr);
        User user = userService.getById(userId);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<a href='index.jsp'>Back to Main page</a>");
        out.println("<h1>Users list</h1>");
        out.println("<h3>Edit User</h1>" +
                "<form action=\"UserServlet\" method=\"post\">" +
                "    <table>" +
                "        <tr><td></td><td><input type='hidden' name='userId' value='" + user.getUserId() + "'/></td></tr>" +
                "        <tr><td>Name:</td><td><input id=\"name\" type=\"text\" name=\"name\" value='" + user.getName() + "'/></td></tr>" +
                "        <tr><td>SureName:</td><td><input id=\"surename\" type=\"test\" name=\"surename\" value='" + user.getSureName() + "'/></td></tr>" +
                "        </td></tr>" +
                "        <tr><td colspan=\"2\"><input id=\"save\" type=\"submit\" value=\"Edit & Save\"/></td></tr>\n" +
                "    </table>" +
                "</form>");

        printUsersTable(out);
        out.close();
    }

    private void printUsersTable(PrintWriter out) {
        List<User> list = userService.getAll();

        out.print("<table border='1'");
        out.print("<tr><th>UserId</th><th>Name</th><th>Surename</th><th>Edit</th><th>Delete</th></tr>");
        for (User user : list) {
            out.print("<tr><td>" + user.getUserId() + "</td><td>" + user.getName() + "</td><td>" + user.getSureName() + "</td>" +
                    "<td><a href='UserServlet?action=update&userId=" + user.getUserId() + "'>edit</a></td>" +
                    "<td><a href='UserServlet?action=delete&userId=" + user.getUserId() + "'>delete</a></td>");
        }
        out.print("</table>");
    }
}
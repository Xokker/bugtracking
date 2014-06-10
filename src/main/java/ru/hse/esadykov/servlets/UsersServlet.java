package ru.hse.esadykov.servlets;

import ru.hse.esadykov.dao.UserDao;
import ru.hse.esadykov.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
public class UsersServlet {
    private UserDao userDao;
    private Pattern pattern;

//    @Override
    public void init() throws ServletException {
//        super.init();
        userDao = new UserDao();
        pattern = Pattern.compile(".*(\\d+).*");
    }

    private int extractUserId(String pathInfo) {
        Matcher matcher = pattern.matcher(pathInfo);
        boolean found = matcher.find();

        return Integer.parseInt(matcher.group(1));
    }

//    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message;

        int userId = Integer.parseInt(req.getParameter("user_id"));
        try {
            userDao.deleteUser(userId);
            message = "User #" + userId + " successfully deleted";
        } catch (SQLException e) {
            message = "Error deleting user #" + userId;
            e.printStackTrace();
        }
        req.setAttribute("message", message);

        doGet(req, resp);
    }

//    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message;

        String username = req.getParameter("username");
        String fullName = req.getParameter("full_name");
        String email = req.getParameter("email");
        User user = new User(null, username, fullName, email);

        try {
            userDao.addUser(user);
            message = "User " + username + " successfully created";
        } catch (SQLException e) {
            message = "Error creating user " + username;
            e.printStackTrace();
        }
        req.setAttribute("message", message);

        doGet(req, resp);
    }

//    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo.contains("delete")) {
            doDelete(req, resp);
            return;
        }
        if (pathInfo.contains("add")) {
            doPut(req, resp);
            return;
        }

        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsp/users.jsp");
        view.forward(req, resp);
    }

//    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = null;
        try {
            users = userDao.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("users", users);

        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsp/users.jsp");
        view.forward(req, resp);
    }

//    @Override
    public void destroy() {
        userDao = null;
        pattern = null;
//        super.destroy();
    }
}

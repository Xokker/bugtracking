package ru.hse.esadykov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.hse.esadykov.dao.UserDao;
import ru.hse.esadykov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
@Controller
public class UsersServlet {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    protected String doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    protected String doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        return "users";
    }

    @RequestMapping(value = "/users")
    protected String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = null;
        try {
            users = userDao.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("users", users);

        return "users";
    }
}

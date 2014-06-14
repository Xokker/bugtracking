package ru.hse.esadykov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.hse.esadykov.dao.UserDao;
import ru.hse.esadykov.model.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
@Controller
public class UsersController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    protected String doDelete(@RequestParam(value = "username") String username,
                              RedirectAttributes attributes) throws ServletException, IOException {
        String message;

        try {
            userDao.deleteUser(username);
            message = "User " + username + " successfully deleted";
        } catch (DataAccessException e) {
            message = "Error deleting user " + username;
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", message);

        return "redirect:/users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    protected String doPut(@RequestParam(value = "username") String username,
                           @RequestParam(value = "full_name", required = false) String fullName,
                           @RequestParam(value = "email", required = false) String email,
                           @RequestParam(value = "password") String password,
                           RedirectAttributes attributes) throws ServletException, IOException {

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, username, fullName, email, encodedPassword);

        String message;
        try {
            userDao.saveUser(user);
            message = "User " + username + " successfully created";
        } catch (DataAccessException e) {
            message = "Error creating user " + username;
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", message);

        return "redirect:/users";
    }

    @RequestMapping(value = "/users")
    protected ModelAndView doGet() throws ServletException, IOException {
        List<User> users = null;
        try {
            users = userDao.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ModelAndView("users", "users", users);
    }
}

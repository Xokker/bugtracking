package ru.hse.esadykov.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.hse.esadykov.dao.UserDao;
import ru.hse.esadykov.exception.ResourceNotFoundException;
import ru.hse.esadykov.model.User;

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
                              RedirectAttributes attributes) {
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

    @RequestMapping(value = "/users/update/{id}", method = RequestMethod.POST)
    protected ModelAndView updateUser(@RequestParam(value = "fullName", required = false) String fullName,
                                      @RequestParam(value = "email", required = false) String email,
                                      @RequestParam(value = "password") String password,
                                      @PathVariable("id") Integer userId) {
        ModelMap mm = new ModelMap();
        String encodedPassword = StringUtils.isNotBlank(password) ? passwordEncoder.encode(password) : null;
        User user = new User(userId, null, fullName, email, encodedPassword);

        try {
            userDao.updateUser(user);
            user = userDao.getUser(userId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        mm.addAttribute("user", user);

        return new ModelAndView("user", mm);
    }

    @RequestMapping(value = "/users/update/{id}", method = RequestMethod.GET)
    protected ModelAndView showUser(@PathVariable("id") Integer userId) {
        ModelMap mm = new ModelMap();
        try {
            User user = userDao.getUser(userId);
            mm.addAttribute("user", user);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }
        return new ModelAndView("user", mm);
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    protected String doPut(@RequestParam(value = "username") String username,
                           @RequestParam(value = "full_name", required = false) String fullName,
                           @RequestParam(value = "email", required = false) String email,
                           @RequestParam(value = "password") String password,
                           RedirectAttributes attributes) {

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
    protected ModelAndView doGet() {
        List<User> users = null;
        try {
            users = userDao.getUsers();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return new ModelAndView("users", "users", users);
    }
}

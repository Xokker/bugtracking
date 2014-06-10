package ru.hse.esadykov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.hse.esadykov.dao.BugDao;
import ru.hse.esadykov.dao.UserDao;
import ru.hse.esadykov.model.Bug;
import ru.hse.esadykov.model.BugStatus;
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
public class BugListController {
    @Autowired
    private BugDao bugDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/bugs", method = RequestMethod.GET)
    protected String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Bug> bugs = null;
        List<User> users = null;
        try {
            bugs = bugDao.getBugs();
            users = userDao.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("bugs", bugs);
        req.setAttribute("users", users);

        return "bugs";
    }

    @RequestMapping(value = "/bugs/add", method = RequestMethod.POST)
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer priority;
        try {
            priority = Integer.parseInt(req.getParameter("priority"));
        } catch (NumberFormatException e) {
            priority = 100;
        }
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Integer responsibleId = Integer.parseInt(req.getParameter("responsible_id"));

        String message;
        try {
            bugDao.addBug(new Bug(null, null, priority, title, description, responsibleId, BugStatus.NEW));
            message = "Bug '" + title + "' was successfully added";
        } catch (SQLException e) {
            message = "Error during saving the bug";
            e.printStackTrace();
        }

        req.setAttribute("message", message);
        doGet(req, resp);
    }
}

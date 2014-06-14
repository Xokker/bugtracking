package ru.hse.esadykov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hse.esadykov.dao.BugDao;
import ru.hse.esadykov.dao.UserDao;
import ru.hse.esadykov.model.Bug;
import ru.hse.esadykov.model.BugPriority;
import ru.hse.esadykov.model.BugStatus;
import ru.hse.esadykov.model.User;
import ru.hse.esadykov.utils.UserService;

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

    @Autowired
    private UserService userService;

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
        req.setAttribute("priorities", BugPriority.values());

        return "bugs";
    }

    @RequestMapping(value = "/bugs/add", method = RequestMethod.POST)
    protected String doPut(@RequestParam(value = "priority_id", defaultValue = "4") Integer priority,
                                 @RequestParam(value = "title") String title,
                                 @RequestParam(value = "description") String description,
                                 @RequestParam(value = "responsible_id") Integer responsibleId,
                                 ModelMap model) throws IOException {
        int creatorId = userService.getCurrentUserId().getId();
        BugPriority bugPriority = BugPriority.values()[priority - 1];
        String message;
        try {
            bugDao.addBug(new Bug(null, null, null, title, description, responsibleId, creatorId, BugStatus.NEW, bugPriority));
            message = "Bug '" + title + "' was successfully added";
        } catch (SQLException e) {
            message = "Error during saving the bug";
            e.printStackTrace();
        }

        model.addAttribute("message", message);

        return "redirect:/bugs";
    }
}

package ru.hse.esadykov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.hse.esadykov.dao.BugDao;
import ru.hse.esadykov.dao.ProjectDao;
import ru.hse.esadykov.dao.UserDao;
import ru.hse.esadykov.model.*;
import ru.hse.esadykov.utils.UserService;

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

    @Autowired
    private ProjectDao projectDao;

    @RequestMapping(value = "/bugs", method = RequestMethod.GET)
    protected ModelAndView doGet(@RequestParam(value = "project_id", required = false) Integer projectId,
                                 @RequestParam(value = "showclosed", required = false) Boolean showClosed) {
        List<Bug> bugs = null;
        List<User> users = null;
        List<Project> projects = null;
        try {
            bugs = bugDao.getBugs(projectId, showClosed, null);
            for (Bug bug : bugs) {
                Project project = projectDao.getProject(bug.getProjectId());
                User responsible = userDao.getUser(bug.getResponsibleId());
                bug.setProject(project);
                bug.setResponsible(responsible);
            }
            users = userDao.getUsers();
            projects = projectDao.getProjects(true);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        ModelMap model = new ModelMap();
        model.addAttribute("bugs", bugs);
        model.addAttribute("users", users);
        model.addAttribute("projects", projects);

        return new ModelAndView("bugs", model);
    }

    @RequestMapping(value = "/bugs/add", method = RequestMethod.GET)
    public ModelAndView addBugForm() {
        ModelMap model = new ModelMap();
        model.addAttribute("users", userDao.getUsers());
        model.addAttribute("projects", projectDao.getProjects(true));
        model.addAttribute("priorities", BugPriority.values());
        model.addAttribute("types", IssueType.values());
        model.addAttribute("statuses", BugStatus.values());

        return new ModelAndView("add_bug", model);
    }

    @RequestMapping(value = "/bugs/add", method = RequestMethod.POST)
    protected String doPut(@RequestParam(value = "priority", defaultValue = "MAJOR") BugPriority bugPriority,
                           @RequestParam(value = "title") String title,
                           @RequestParam(value = "description") String description,
                           @RequestParam(value = "responsible_id") Integer responsibleId,
                           @RequestParam(value = "project_id") Integer projectId,
                           @RequestParam(value = "issue_type", required = false, defaultValue = "BUG") IssueType issueType) {
        int creatorId = userService.getCurrentUser().getId();
        if (responsibleId == 0) {
            responsibleId = userService.getCurrentUser().getId();
        }
        try {
            Bug bug = new Bug(null, null, null, title, description, responsibleId,
                    creatorId, BugStatus.NEW, bugPriority, issueType, projectId, null);
            bugDao.saveBug(bug);
            bugDao.addObserver(bug, userService.getCurrentUser());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return "redirect:/bugs?project_id=" + projectId;
    }
}

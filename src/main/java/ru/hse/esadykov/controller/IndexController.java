package ru.hse.esadykov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.hse.esadykov.dao.BugDao;
import ru.hse.esadykov.dao.CommentDao;
import ru.hse.esadykov.dao.ProjectDao;
import ru.hse.esadykov.model.Bug;
import ru.hse.esadykov.model.Project;
import ru.hse.esadykov.utils.UserService;

import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 15.06.2014
 */
@Controller
public class IndexController {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private BugDao bugDao;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/index", "/"})
    public ModelAndView getIndex() {

        ModelMap model = new ModelMap();
        model.addAttribute("comments", commentDao.getComments(userService.getCurrentUser().getId(), 5));
        // TODO: [FIXME] don't fetch all bugs
        List<Bug> allBugs = bugDao.getBugs();
        model.addAttribute("bugs", allBugs.subList(0, allBugs.size() >= 5 ? 5 : allBugs.size()));
        List<Project> projects = projectDao.getProjects();
        model.addAttribute("projects", projects.subList(0, projects.size() >= 5 ? 5 : projects.size()));

        return new ModelAndView("index", model);
    }
}

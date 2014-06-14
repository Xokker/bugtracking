package ru.hse.esadykov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.hse.esadykov.dao.ProjectDao;
import ru.hse.esadykov.dao.UserDao;
import ru.hse.esadykov.model.Project;
import ru.hse.esadykov.model.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * @author Anton Galaev
 * @since 14.06.2014
 */
@Controller
public class ProjectsController {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/projects")
    protected ModelAndView doGet(ModelMap model) throws ServletException, IOException {
        List<Project> projects = null;
        List<User> users = null;
        try {
            projects = projectDao.getProjects();
            users = userDao.getUsers();

            for (Project project : projects) {
                Integer managerId = project.getManagerId();
                if (managerId != null) {
                    User manager = userDao.getUser(managerId);
                    project.setManager(manager);
                }
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        model.addAttribute("projects", projects);
        model.addAttribute("users", users);
        return new ModelAndView("projects", model);
    }

    @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
    protected String doPut(@RequestParam(value = "name") String name,
                           @RequestParam(value = "description", required = false) String description,
                           @RequestParam(value = "manager_id", required = false) int managerId,
                           RedirectAttributes attributes) throws ServletException, IOException {

        Project project = new Project(null, name, description, managerId);

        String message;
        try {
            projectDao.addProject(project);
            message = "Project " + name + " successfully created";
        } catch (DataAccessException e) {
            message = "Error creating project " + name;
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", message);

        return "redirect:/projects";
    }


    @RequestMapping(value = "/projects/delete", method = RequestMethod.POST)
    protected String doDelete(@RequestParam(value = "name") String name,
                              RedirectAttributes attributes) throws ServletException, IOException {
        String message;

        try {
            projectDao.deleteProject(name);
            message = "Project " + name + " successfully deleted";
        } catch (DataAccessException e) {
            message = "Error deleting project " + name;
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", message);

        return "redirect:/projects";
    }
}

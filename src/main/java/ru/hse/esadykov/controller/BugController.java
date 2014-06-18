package ru.hse.esadykov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.hse.esadykov.dao.BugDao;
import ru.hse.esadykov.dao.CommentDao;
import ru.hse.esadykov.dao.ProjectDao;
import ru.hse.esadykov.dao.UserDao;
import ru.hse.esadykov.model.*;
import ru.hse.esadykov.utils.MailService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
@Controller
public class BugController {

    @Autowired
    private BugDao bugDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private MailService mailService;


    @RequestMapping(value = "/bug/{id}", method = RequestMethod.POST)
    protected ModelAndView doPost(@PathVariable("id") String id,
                                  @RequestParam(value = "username") String username,
                                  @RequestParam(value = "body") String body,
                                  ModelMap model,
                                  HttpServletResponse response) throws IOException {
        int bugId;
        try {
            bugId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        Comment comment = new Comment();
        User user = new User();
        user.setUsername(username);
        comment.setAuthor(user);
        comment.setBody(body);
        comment.setBugId(bugId);
        try {
            commentDao.saveComment(comment);
            Bug bug = bugDao.getBug(bugId);
            bug.sendMessages(mailService, "Notification message: project " + bug.getProjectName(),
                    "Added comment to bug #" + bug.getId() + " " + bug.getTitle());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return doGet(model, response, bugId);
    }

    @RequestMapping(value = "/bug/{id}", method = RequestMethod.GET)
    protected ModelAndView doGet(ModelMap model,
                                 HttpServletResponse response,
                                 @PathVariable("id") Integer bugId) throws IOException {

        try {
            Bug bug = bugDao.getBug(bugId);
            if (bug == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
            User responsible = userDao.getUser(bug.getResponsibleId());
            bug.setResponsible(responsible);
            User creator = userDao.getUser(bug.getCreatorId());
            bug.setCreator(creator);
            model.addAttribute("bug", bug);
            model.addAttribute("statuses", BugStatus.values());
            model.addAttribute("priorities", BugPriority.values());
            model.addAttribute("users", userDao.getUsers());
            Project project = projectDao.getProject(bug.getProjectId());
            bug.setProject(project);

            List<Comment> comments = commentDao.getComments(bugId, true);
            model.addAttribute("comments", comments);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return new ModelAndView("bug", model);
    }

    @RequestMapping(value = "/bug/{id}/edit", method = RequestMethod.POST)
    protected String doClose(HttpServletResponse response,
                             @PathVariable("id") String id,
                             @RequestParam(value = "status") BugStatus status,
                             @RequestParam(value = "priority") BugPriority priority,
                             @RequestParam(value = "responsible_id") String responsible) throws IOException {
        int bugId;
        int responsibleId;
        try {
            bugId = Integer.parseInt(id);
            responsibleId = Integer.parseInt(responsible);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        bugDao.updateBug(new Bug(bugId, null, null, null, null, responsibleId, null, status, priority, null, null, null));
        final Bug bug = bugDao.getBug(bugId);
        bug.sendMessages(mailService, "Notification message: project " + bug.getProjectName(),
                "Bug #" + bug.getId() + " " + bug.getTitle() + " was modified");
        return "redirect:/bugs";
    }

    // TODO: change to POST
    @RequestMapping(value = "/bug/{id}/add/{id1}", method = RequestMethod.GET)
    protected String addDependency(HttpServletResponse response,
                                         @PathVariable("id") Integer bugId,
                                         @PathVariable("id1") Integer bug1Id) throws IOException {

        try {
            Bug bug = bugDao.getBug(bugId);
            Bug bug1 = bugDao.getBug(bug1Id);
            if (bug == null || bug1 == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
            User responsible = userDao.getUser(bug.getResponsibleId());
            bug.setResponsible(responsible);
            bug.addDependency(bug1);
            bugDao.addDependency(bug, bug1);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return "redirect:/bug/" + bugId;
    }

    // TODO: change to post
    @RequestMapping(value = "/bug/{id}/remove/{id1}", method = RequestMethod.GET)
    protected String removeDependency(HttpServletResponse response,
                                            @PathVariable("id") Integer id,
                                            @PathVariable("id1") Integer id1) throws IOException {

        try {
            Bug bug = bugDao.getBug(id);
            Bug bug1 = bugDao.getBug(id1);
            if (bug == null || bug1 == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
            User responsible = userDao.getUser(bug.getResponsibleId());
            bug.setResponsible(responsible);
            bug.removeDependency(bug1);
            bugDao.removeDependency(bug, bug1);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return "redirect:/bug/" + id;
    }

    // TODO: change to POST
    @RequestMapping(value = "/bug/{bug_id}/observer", method = RequestMethod.POST)
    protected String addObserver(HttpServletResponse response,
                                   @PathVariable("bug_id") Integer bugId,
                                   @RequestParam(value  = "observer_id") Integer observerId,
                                   @RequestParam(value  = "is_add") Boolean isAdd) throws IOException {

        try {
            Bug bug = bugDao.getBug(bugId);
            User observer = userDao.getUser(observerId);
            if (bug == null || observer == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
            if (isAdd) {
                bug.addObserver(observer);
                bugDao.addObserver(bug, observer);
            } else {
                bug.removeObserver(observer);
                bugDao.removeObserver(bug, observer);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return "redirect:/bug/" + bugId;
    }
}

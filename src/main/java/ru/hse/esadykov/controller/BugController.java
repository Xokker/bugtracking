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
import ru.hse.esadykov.exception.ResourceNotFoundException;
import ru.hse.esadykov.model.*;
import ru.hse.esadykov.utils.MailService;
import ru.hse.esadykov.utils.UserService;

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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/bug/{id}", method = RequestMethod.POST)
    protected ModelAndView doPost(@PathVariable("id") Integer bugId,
                                  @RequestParam(value = "body") String body,
                                  ModelMap model)  {

        Comment comment = new Comment();
        comment.setAuthor(userService.getCurrentUser());
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

        return doGet(model, bugId);
    }

    @RequestMapping(value = "/bug/{id}", method = RequestMethod.GET)
    protected ModelAndView doGet(ModelMap model,
                                 @PathVariable("id") Integer bugId) {

        try {
            Bug bug = bugDao.getBug(bugId);
            if (bug == null) {
                throw new ResourceNotFoundException();
            }
            User responsible = userDao.getUser(bug.getResponsibleId());
            bug.setResponsible(responsible);
            User creator = userDao.getUser(bug.getCreatorId());
            bug.setCreator(creator);
            model.addAttribute("bug", bug);
            model.addAttribute("statuses", BugStatus.values());
            model.addAttribute("priorities", BugPriority.values());
            model.addAttribute("users", userDao.getUsers());
            model.addAttribute("is_current_user_observer", bug.isUserObserver(userService.getCurrentUser()));
            model.addAttribute("bugs", bugDao.getBugs());

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

        try {
            bugDao.updateBug(new Bug(bugId, null, null, null, null, responsibleId, null, status, priority, null, null, null));
            bugDao.addObserver(bugId, responsibleId);
        } catch (DataAccessException dat) {
            dat.printStackTrace();
        }

        final Bug bug = bugDao.getBug(bugId);
        bug.sendMessages(mailService, "Notification message: project " + bug.getProjectName(),
                "Bug #" + bug.getId() + " " + bug.getTitle() + " was modified");

        return "redirect:/bugs";
    }

    // TODO: change to POST
    @RequestMapping(value = "/bug/{id}/dependency", method = RequestMethod.POST)
    protected String changeDependency(HttpServletResponse response,
                                         @PathVariable("id") Integer bugId,
                                         @RequestParam(value = "bug_id") Integer bug1Id,
                                         @RequestParam(value = "is_add") Boolean isAdd) throws IOException {

        try {
            Bug bug = bugDao.getBug(bugId);
            Bug bug1 = bugDao.getBug(bug1Id);
            if (bug == null || bug1 == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
            if (isAdd) {
                bugDao.addDependency(bug, bug1);
            } else {
                bugDao.removeDependency(bug, bug1);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return "redirect:/bug/" + bugId;
    }

    @RequestMapping(value = "/bug/{bug_id}/observer", method = RequestMethod.POST)
    protected String changeObserver(HttpServletResponse response,
                                   @PathVariable("bug_id") Integer bugId,
                                   @RequestParam(value  = "is_add") Boolean isAdd) throws IOException {

        try {
            Bug bug = bugDao.getBug(bugId);
            User observer = userService.getCurrentUser();
            if (bug == null || observer == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
            if (isAdd) {
                bugDao.addObserver(bug, observer);
            } else {
                bugDao.removeObserver(bug, observer);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return "redirect:/bug/" + bugId;
    }
}

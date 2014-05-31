package ru.hse.esadykov.servlets;

import ru.hse.esadykov.dao.BugDao;
import ru.hse.esadykov.dao.CommentDao;
import ru.hse.esadykov.dao.UserDao;
import ru.hse.esadykov.model.Bug;
import ru.hse.esadykov.model.Comment;
import ru.hse.esadykov.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
public class BugServlet extends HttpServlet {

    private BugDao bugDao;
    private CommentDao commentDao;
    private UserDao userDao;
    private Pattern pattern;

    @Override
    public void init() throws ServletException {
        super.init();
        bugDao = new BugDao();
        commentDao = new CommentDao();
        userDao = new UserDao();
        pattern = Pattern.compile("/(\\d+)/?");
    }

    private int getBugIdFromPathInfo(String pathInfo) {
        Matcher matcher = pattern.matcher(pathInfo);
        boolean found = matcher.find(); // TODO: 404 if not found

        if (!found) {
            return -1;
        }
        return Integer.parseInt(matcher.group(1));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bugId = getBugIdFromPathInfo(request.getPathInfo());
        if (bugId == -1) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String username = request.getParameter("username");
        String body = request.getParameter("body");

        Comment comment = new Comment();
        User user = new User();
        user.setUsername(username);
        comment.setAuthor(user);
        comment.setBody(body);
        comment.setBugId(bugId);
        try {
            commentDao.saveComment(comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bugId = getBugIdFromPathInfo(request.getPathInfo());

        try {
            Bug bug = bugDao.getBug(bugId);
            if (bug == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            User responsible = userDao.getUser(bug.getResponsibleId());
            bug.setResponsible(responsible);
            request.setAttribute("bug", bug);
            List<Comment> comments = commentDao.getComments(bugId);
            request.setAttribute("comments", comments);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/jsp/bug.jsp");
        view.forward(request, response);
    }

    @Override
    public void destroy() {
        bugDao = null;
        commentDao = null;
        userDao = null;
        pattern = null;
        super.destroy();
    }
}

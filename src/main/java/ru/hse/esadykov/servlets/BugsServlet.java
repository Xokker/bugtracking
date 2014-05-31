package ru.hse.esadykov.servlets;

import ru.hse.esadykov.dao.BugDao;
import ru.hse.esadykov.dao.CommentDao;
import ru.hse.esadykov.model.Bug;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
public class BugsServlet extends HttpServlet {
    private BugDao bugDao;
    private CommentDao commentDao;

    @Override
    public void init() throws ServletException {
        super.init();
        bugDao = new BugDao();
        commentDao = new CommentDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Bug> bugs = null;
        try {
            bugs = bugDao.getBugs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("bugs", bugs);

        RequestDispatcher view = req.getRequestDispatcher("/jsp/bugs.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        bugDao = null;
        commentDao = null;
        super.destroy();
    }
}

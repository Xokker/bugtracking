package ru.hse.esadykov.servlets;

import ru.hse.esadykov.ConnectionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
public class DBConnectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsp/dbconnection.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String host = req.getParameter("host");
        String port = req.getParameter("port");
        String user = req.getParameter("user");
        String password = req.getParameter("password");

        boolean success = true;
        try {
            ConnectionFactory.setUpPool(host, port, user, password);
        } catch (RuntimeException e) {
            success = false;
        }

        req.setAttribute("success", success);

        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsp/dbconnection.jsp");
        view.forward(req, resp);
    }
}

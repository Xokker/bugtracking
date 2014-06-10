package ru.hse.esadykov.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hse.esadykov.ConnectionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
@Controller
public class DBConnectionServlet {
    @RequestMapping(value = "/connection")
    protected String doGet(HttpServletRequest req, HttpServletResponse resp) {
//        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsp/dbconnection.jsp");
//        view.forward(req, resp);
        return "dbconnection";
    }

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

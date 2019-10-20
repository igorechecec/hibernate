package com.nixsolutions.servlets.admin;

import com.nixsolutions.dao.JdbcUserDao;
import com.nixsolutions.entity.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class AdminHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI() + req.getQueryString();
        if (req.getParameter("edit") != null) {
            req.getRequestDispatcher("/edit").forward(req, resp);
            return;
        } else {
            req.setAttribute("user_name", req.getSession(false).getAttribute("auth_admin"));
            JdbcUserDao userDao = new JdbcUserDao();
            List<User> users = userDao.findAll();
            req.setAttribute("users_list", users);
            req.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JdbcUserDao userDao = new JdbcUserDao();
        List<User> users = userDao.findAll();
        req.setAttribute("users_list", users);
    }
}

package com.nixsolutions.servlets.admin;

import com.nixsolutions.dao.HibernateUserDao;
import com.nixsolutions.entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class AdminDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateUserDao userDao = new HibernateUserDao();
        if (req.getParameter("id") != null) {
            Long id = Long.valueOf(req.getParameter("id"));
            User user = userDao.findById(id);
            if (user != null) {
                userDao.remove(user);
                resp.sendRedirect("/admin");
            } else {
                req.getSession(false).setAttribute("error", "User is not exists");
                resp.sendRedirect("/error");
            }
        } else {
            req.getSession(false).setAttribute("error", "Invalid id parameter");
            resp.sendRedirect("/error");
        }
    }
}

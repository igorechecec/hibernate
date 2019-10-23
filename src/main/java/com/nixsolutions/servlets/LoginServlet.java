package com.nixsolutions.servlets;

import com.nixsolutions.dao.HibernateUserDao;
import com.nixsolutions.entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.setMaxInactiveInterval(10*60);
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HibernateUserDao userDao = new HibernateUserDao();
        User user = userDao.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            if (user.getRole().getName().equals("Admin")) {
                session.setAttribute("auth_admin", user.getLogin());
                resp.sendRedirect("/admin");
            } else if (user.getRole().getName().equals("User")) {
                session.setAttribute("auth_user", user.getLogin());
                resp.sendRedirect("/");
            }
        } else {
            req.setAttribute("message", "User data incorrect");
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }
}

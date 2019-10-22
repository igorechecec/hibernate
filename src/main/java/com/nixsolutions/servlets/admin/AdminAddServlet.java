package com.nixsolutions.servlets.admin;

import com.nixsolutions.dao.HibernateRoleDao;
import com.nixsolutions.dao.HibernateUserDao;
import com.nixsolutions.dao.JdbcRoleDao;
import com.nixsolutions.dao.JdbcUserDao;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;
import com.nixsolutions.utils.FormUtils;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/add")
public class AdminAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user_name", req.getSession(false).getAttribute("auth_admin"));
        req.getRequestDispatcher("WEB-INF/jsp/add_user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!FormUtils.checkForm(req)) {
            resp.sendRedirect("/error");
            return;
        }
        HttpSession session = req.getSession(false);
        HibernateRoleDao roleDao = new HibernateRoleDao();
        HibernateUserDao userDao = new HibernateUserDao();
        if (!req.getParameter("password").equals(req.getParameter("password-again"))) {
            session.setAttribute("error", "You passed different password!");
            resp.sendRedirect("/error");
            return;
        }
        String login = req.getParameter("login");
        if (userDao.findByLogin(login) != null) {
            session.setAttribute("error", "User already exists");
            resp.sendRedirect("/error");
            return;
        }
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));
        java.util.Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirthday(new Date(date.getTime()));
        user.setRole(roleDao.findByName(req.getParameter("role")));

        userDao.create(user);
        resp.sendRedirect("/admin");
    }
}

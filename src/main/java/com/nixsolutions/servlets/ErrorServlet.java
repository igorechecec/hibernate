package com.nixsolutions.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session.getAttribute("error") == null) {
            resp.sendError(404);
        } else {
            req.setAttribute("message", session.getAttribute("error"));
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }


    }
}

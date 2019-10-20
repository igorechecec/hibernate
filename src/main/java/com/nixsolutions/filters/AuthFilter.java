package com.nixsolutions.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(AuthFilter.class.getSimpleName());
    FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        String path = req.getRequestURI();
        String fullPath = getUriWithParams(req);
        String[] adminPages = {"/admin", "/add", "/edit", "/delete"};

        if (fullPath.startsWith("/resources/")) {
            logger.trace("resource filter");
            filterChain.doFilter(req, resp);
            return;
        }
        if (session == null || (session.getAttribute("auth_admin") == null
            && session.getAttribute("auth_user") == null)) {
            logger.trace("login filter");
            if (fullPath.equals("/login")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (fullPath.equals("/")){
                resp.sendRedirect("/login");
                return;
            } else {
                resp.sendError(404);
                return;
            }
        } else if (fullPath.equals("/logout")) {
            logger.trace("logout filter");
            filterChain.doFilter(req, resp);
        } else if (session.getAttribute("auth_admin") != null) {
            logger.trace("admin filter");
            for (String url: adminPages) {
                if (path.equals(url)) {
                    req.getRequestDispatcher(fullPath).forward(req, resp);
                    return;
                } else if (path.equals("/")) {
                    resp.sendRedirect("/admin");
                    return;
                }
            }
            if (fullPath.equals("/error")) {
                req.getRequestDispatcher(fullPath).forward(req, resp);
            } else {
                resp.sendError(404);
            }
        } else if (session.getAttribute("auth_user") != null) {
            logger.trace("user filter");
            if (fullPath.equals("/user")) {
                req.getRequestDispatcher(fullPath).forward(req, resp);
                return;
            } else if (fullPath.equals("/")) {
                resp.sendRedirect("/user");
                return;
            }
            if (fullPath.equals("/error")) {
                req.getRequestDispatcher(fullPath).forward(req, resp);
                return;
            }
            req.setAttribute("user_name", session.getAttribute("auth_user"));
            resp.sendRedirect("/user");
        } else {
            logger.trace("error filter");
            resp.sendError(404);
        }
    }

    @Override
    public void destroy() {
        config = null;
    }

    private String getUriWithParams(HttpServletRequest req) {
        String path = req.getRequestURI();
        if (req.getQueryString() != null) {
            return path + "?" + req.getQueryString();
        } else {
            return path;
        }
    }
}

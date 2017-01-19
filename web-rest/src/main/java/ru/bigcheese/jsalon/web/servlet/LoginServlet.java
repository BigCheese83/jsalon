package ru.bigcheese.jsalon.web.servlet;

import ru.bigcheese.jsalon.dao.UserDao;
import ru.bigcheese.jsalon.model.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {""})
public class LoginServlet extends HttpServlet {

    @Inject
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession(true).getAttribute("user");
        if (user == null) {
            user = userDao.findByUsername(req.getRemoteUser());
            req.getSession().setAttribute("user", user);
        }
        resp.sendRedirect(req.getContextPath() + "/page");
    }

}

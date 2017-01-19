package ru.bigcheese.jsalon.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ContentDispatcherServlet", urlPatterns = {"/page/*"})
public class ContentDispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageName = req.getPathInfo().substring(req.getPathInfo().lastIndexOf('/') + 1) + ".jsp";
        req.getRequestDispatcher("/WEB-INF/pages/" + pageName).forward(req, resp);
    }
}

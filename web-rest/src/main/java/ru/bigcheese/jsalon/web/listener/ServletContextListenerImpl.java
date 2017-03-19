package ru.bigcheese.jsalon.web.listener;

import com.google.common.collect.ImmutableList;
import ru.bigcheese.jsalon.model.enums.GroupName;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("allGroups", ImmutableList.copyOf(GroupName.values()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}

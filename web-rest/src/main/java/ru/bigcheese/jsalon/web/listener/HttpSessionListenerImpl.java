package ru.bigcheese.jsalon.web.listener;

import ru.bigcheese.jsalon.model.enums.GroupName;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        session.setAttribute("allGroups", GroupName.values());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {}
}

package ru.bigcheese.jsalon.web.ui;

import ru.bigcheese.jsalon.model.User;
import ru.bigcheese.jsalon.model.enums.GroupName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MenuBuilder {

    private GroupName groupName;
    private String contextPath;

    private MenuBuilder(GroupName groupName, String contextPath) {
        this.groupName = groupName;
        this.contextPath = contextPath;
    }

    public static MenuBuilder of(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getGroup() == null) {
            throw new IllegalArgumentException("Group not found for user");
        }
        return new MenuBuilder(user.getGroup().getName(), request.getContextPath());
    }

    public String build() {
        StringBuilder sb = new StringBuilder("<ul id=\"menu\">");
        switch (groupName) {
            case ADMIN:
                sb.append(buildItem("Запись", "/register", "fa-edit"))
                  .append(buildItem("Расписание", "/schedule", "fa-calendar"))
                  .append(buildItem("Пользователи", "/admin/users", "fa-users"))
                  .append(buildItem("Скидки", "/admin/discounts", "fa-star-o"))
                  .append(buildItem("Должности", "/admin/posts", "fa-address-book-o"))
                  .append(buildItem("Услуги", "/admin/services", "fa-scissors"))
                  .append(buildItem("Мастера", "/admin/masters", "fa-user"))
                  .append(buildItem("Клиенты", "/clients", "fa-user-o"))
                  .append(buildItem("Статистика", "/statistics", "fa-bar-chart"))
                  .append(buildItem("О системе", "/sysinfo", "fa-info-circle"));
                break;
            case USER:
                sb.append(buildItem("Запись", "/register", "fa-edit"))
                  .append(buildItem("Расписание", "/schedule", "fa-calendar"))
                  .append(buildItem("Клиенты", "/clients", "fa-user-o"))
                  .append(buildItem("Статистика", "/statistics", "fa-bar-chart"))
                  .append(buildItem("О системе", "/sysinfo", "fa-info-circle"));
                ;
                break;
        }
        sb.append("</ul>");
        return sb.toString();
    }

    private String buildItem(String title, String href, String icon) {
        StringBuilder sb = new StringBuilder();
        String link = (href == null || href.isEmpty()) ? "#" : href;
        String iconTag = (icon == null || icon.isEmpty()) ? "" : ("<i class=\"fa " + icon + "\" aria-hidden=\"true\"></i> ");
        sb.append("<li>")
                .append("<a href=\"")
                .append(contextPath)
                .append("/page")
                .append(link)
                .append("\">")
                .append(iconTag)
                .append(title)
                .append("</a></li>");
        return sb.toString();
    }
}

<%@ page import="static ru.bigcheese.jsalon.core.Constants.*" %>
<%@ page import="ru.bigcheese.jsalon.web.ui.MenuBuilder" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${param.title} - <%=SOFTWARE_NAME%></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datatables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jsalon.css">
</head>
<body class="ui-widget">
<!-- Header -->
<header id="header" class="ui-widget-header row">
    <div class="col-8"><%=SOFTWARE_NAME%></div>
    <div class="col-4">
        <div class="header-table">
            <div><%="Версия " + VERSION_NUMBER + " от " + VERSION_DATE%></div>
            <div>
                <a href="${pageContext.request.contextPath}/page/profile">
                    <i class="fa fa-user-o" aria-hidden="true"></i> ${sessionScope.user.username} [${sessionScope.user.shortFIO}]
                </a>
                <a href="${pageContext.request.contextPath}/logout" style="margin-left: 10px"><i class="fa fa-sign-out" aria-hidden="true"></i></a>
            </div>
        </div>
    </div>
</header>
<!-- Menu -->
<div class="menu-content">
<%=MenuBuilder.of(session, request).build()%>
</div>
<!-- Page content -->
<div id="content" class="ui-widget-content">
    <jsp:include page="/WEB-INF/pages/${param.content}.jsp"/>
    <!-- Footer -->
    <div id="footer"><%=COPYRIGHT_STRING%></div>
</div>
<!-- Spinner -->
<div id="floatingCirclesG">
    <div class="f_circleG" id="frotateG_01"></div>
    <div class="f_circleG" id="frotateG_02"></div>
    <div class="f_circleG" id="frotateG_03"></div>
    <div class="f_circleG" id="frotateG_04"></div>
    <div class="f_circleG" id="frotateG_05"></div>
    <div class="f_circleG" id="frotateG_06"></div>
    <div class="f_circleG" id="frotateG_07"></div>
    <div class="f_circleG" id="frotateG_08"></div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/datatables.js"></script>
<script src="${pageContext.request.contextPath}/js/jsalon.js"></script>
<c:if test="${not empty param.script}">
<script src="${pageContext.request.contextPath}/js/jsalon.${param.script}.js"></script>
</c:if>
</body>
</html>
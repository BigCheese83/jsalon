<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>${param.title}</h3>
<form id="form">
    <div class="block" id="msgContainer" style="display: none">
        <div id="msgResult"></div>
    </div>
    <div class="block">
        <div class="centered w180"><label>Логин</label></div>
        <div class="centered"><span class="form-label-value">${sessionScope.user.username}</span></div>
        <input type="hidden" name="username" value="${sessionScope.user.username}">
    </div>
    <div class="block">
        <div class="centered w180"><label>Группа</label></div>
        <div class="centered"><span class="form-label-value">${sessionScope.user.group.name.label}</span></div>
    </div>
    <div class="block">
        <div class="centered w180"><label for="surname">Фамилия</label></div>
        <div class="centered">
            <input type="text" value="${sessionScope.user.surname}" class="jq-button" id="surname" name="surname">
        </div>
    </div>
    <div class="block">
        <div class="centered w180"><label for="name">Имя</label></div>
        <div class="centered">
            <input type="text" value="${sessionScope.user.name}" class="jq-button" id="name" name="name">
        </div>
    </div>
    <div class="block">
        <div class="centered w180"><label for="patronymic">Отчество</label></div>
        <div class="centered">
            <input type="text" value="${sessionScope.user.patronymic}" class="jq-button" id="patronymic" name="patronymic">
        </div>
    </div>
    <div class="block">
        <div class="centered w180"><label for="newPassword">Новый пароль</label></div>
        <div class="centered">
            <input type="password" class="jq-button" id="newPassword" name="newPassword">
        </div>
    </div>
    <div class="block">
        <div class="centered w180"><label for="confirmpwd">Подтвердить пароль</label></div>
        <div class="centered">
            <input type="password" class="jq-button" id="confirmpwd">
        </div>
    </div>
    <div class="block">
        <div class="centered w180"><label for="password">Пароль</label></div>
        <div class="centered">
            <input type="password" class="jq-button" id="password" name="password" required>
        </div>
    </div>
    <div class="block">
        <div class="centered w180"></div>
        <div class="centered">
            <button class="jq-button" id="sendButton">Сохранить</button>
        </div>
    </div>
</form>
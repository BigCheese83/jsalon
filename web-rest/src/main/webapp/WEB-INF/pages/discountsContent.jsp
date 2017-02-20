<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<h3>${param.title}</h3>
<div id="tableErrors" class="err-container"></div>
<div class="crud-buttonset">
    <button id="create-btn"><i class="fa fa-plus"></i> Создать</button>
    <button id="edit-btn"><i class="fa fa-edit"></i> Редактировать</button>
    <button id="delete-btn"><i class="fa fa-remove"></i> Удалить</button>
</div>
<table id="table" class="display">
    <thead>
    <tr>
        <th>ID</th>
        <th>Наименование</th>
        <th>Значение, %</th>
        <th>Описание</th>
    </tr>
    </thead>
</table>
<div id="dialog">
    <div id="dialogErrors" class="err-container" style="padding: 0"></div>
    <form id="dialog-form">
        <fieldset>
            <label for="name">Наименование</label>
            <input type="text" name="name" id="name" class="ui-widget-content ui-corner-all" required>
            <label for="value">Значение, %</label>
            <input type="number" name="value" id="value" class="ui-widget-content ui-corner-all" required>
            <label for="description">Описание</label>
            <textarea name="description" id="description" class="ui-widget-content ui-corner-all"></textarea>
            <input type="submit" tabindex="-1" id="submit" style="display: none">
            <input type="hidden" name="id" id="id">
        </fieldset>
    </form>
</div>
<div id="confirm">
    <p>
        <i class="fa fa-exclamation-triangle fa-2x dialog-icon" aria-hidden="true"></i>
        <span id="confirm-msg"></span>
    </p>
</div>
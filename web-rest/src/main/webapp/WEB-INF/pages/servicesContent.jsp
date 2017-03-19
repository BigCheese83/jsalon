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
        <th>Категория</th>
        <th>Наименование</th>
        <th>Цена, руб</th>
        <th>Время, мин</th>
        <th>Должности</th>
        <th>Описание</th>
    </tr>
    </thead>
</table>
<div id="dialog">
    <form id="dialog-form">
        <div>
            <div class="form-cell">
                <label for="category">Категория</label>
                <input type="text" name="category" id="category" class="ui-widget-content ui-corner-all" required>
            </div>
            <div class="form-cell">
                <label for="name">Наименование</label>
                <input type="text" name="name" id="name" class="ui-widget-content ui-corner-all" required>
            </div>
        </div>
        <div>
            <div class="form-cell">
                <label for="cost">Цена, руб</label>
                <input type="number" name="cost" id="cost" class="ui-widget-content ui-corner-all" required>
            </div>
            <div class="form-cell">
                <label for="duration">Время, мин</label>
                <input type="number" name="duration" id="duration" class="ui-widget-content ui-corner-all" min="1" required>
            </div>
        </div>
        <div>
            <div class="form-cell">
                <label for="posts">Должности</label>
                <select id="posts" name="posts" class="select2" multiple></select>
            </div>
            <div class="form-cell">
                <label for="description">Описание</label>
                <textarea name="description" id="description" class="ui-widget-content ui-corner-all"></textarea>
            </div>
        </div>
        <input type="submit" tabindex="-1" id="submit" style="display: none">
        <input type="hidden" name="id" id="id">
    </form>
</div>
<div id="confirm">
    <p>
        <i class="fa fa-exclamation-triangle fa-2x dialog-icon" aria-hidden="true"></i>
        <span id="confirm-msg"></span>
    </p>
</div>
<div id="showDialog">
    <div class="loading center">
        <i class="fa fa-spinner fa-pulse fa-4x fa-fw"></i><span class="sr-only">Загрузка...</span>
    </div>
    <div id="dialog-content"></div>
</div>
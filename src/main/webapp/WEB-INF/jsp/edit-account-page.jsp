<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Редактирование профиля</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f7f9fc;
            color: #333;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        header {
            background-color: #2d3e50;
            color: white;
            text-align: center;
            padding: 15px 0;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .nav-bar {
            background-color: #e1e8f0;
            padding: 10px 20px;
            text-align: left;
        }

        .nav-bar button {
            padding: 7px 15px;
            background-color: #2d3e50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #2d3e50;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        .submit-btn {
            width: 100%;
            padding: 10px;
            background-color: #1abc9c;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .submit-btn:hover {
            background-color: #16a085;
        }

        footer {
            background-color: #2d3e50;
            color: white;
            text-align: center;
            padding: 10px 0;
            margin-top: auto;
            font-size: 14px;
        }
    </style>
</head>
<body>
<header>
    <h1>Редактирование профиля</h1>
</header>

<div class="nav-bar">
    <form action="Controller" method="GET">
        <button type="submit" name="command" value="GO_TO_INDEX_PAGE">На главную</button>
    </form>
</div>

<div class="container">
    <h2>Редактирование профиля</h2>

    <!-- Вывод сообщения об отсутствии изменений -->
    <c:if test="${not empty param.message}">
        <p style="color: red; font-weight: bold; text-align: center; padding: 10px; border: 1px solid red; background-color: #ffe6e6; margin-bottom: 20px;">
            <c:out value="${param.message}" />
        </p>
    </c:if>

    <form action="Controller" method="post">
        <input type="hidden" name="userId" value="<c:out value='${user.id}' />">

        <div class="form-group">
            <label>Логин</label>
            <input type="text" name="login" value="<c:out value='${user.login}' />" required>
        </div>

        <div class="form-group">
            <label>Имя</label>
            <input type="text" name="name" value="<c:out value='${user.name}' />" required>
        </div>

        <div class="form-group">
            <label>Фамилия</label>
            <input type="text" name="surname" value="<c:out value='${user.surname}' />" required>
        </div>

        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" value="<c:out value='${user.email}' />" required>
        </div>

        <div class="form-group">
            <label>Дата рождения</label>
            <input type="date" name="birthdayDate" value="<c:out value='${user.birthdayDate}' />">
        </div>

        <div class="form-group">
            <label>Адрес</label>
            <input type="text" name="address" value="<c:out value='${user.address}' />">
        </div>

        <div class="form-group">
            <label>Для сохранения изменений введите пароль</label>
            <input type="password" name="currentPassword" required>
        </div>

        <button class="submit-btn" type="submit" name="command" value="DO_EDIT_ACCOUNT_PAGE">Сохранить</button>
    </form>
</div>

<footer>
    <p>&copy; 2025 Offliner</p>
</footer>
</body>
</html>



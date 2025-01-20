<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <style>
        :root {
            --primary-color: #2d3e50;
            --secondary-color: #e1e8f0;
            --accent-color: #0066cc;
            --button-secondary-color: #1abc9c; /* Новый зелёный цвет кнопки */
            --text-color: #333;
            --background-color: #f7f9fc;
        }

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            font-family: Arial, sans-serif;
            background-color: var(--background-color);
            color: var(--text-color);
        }

        header {
            background-color: var(--primary-color);
            color: white;
            text-align: center;
            padding: 15px 0;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        header h1 {
            font-size: 24px;
            margin: 0;
        }

        /* Полоска с кнопками */
        .nav-bar {
            background-color: var(--secondary-color);
            display: flex;
            justify-content: flex-start;
            padding: 10px 20px;
            z-index: 1;
            position: relative;
        }

        .nav-bar button {
            padding: 7px 15px;
            background-color: var(--primary-color);
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s;
        }

        .nav-bar button:hover {
            background-color: #1b2838;
        }

        .container {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px 0;
            margin-top: -50px; /* Убираем transform */
        }

        .auth-container {
            background-color: var(--secondary-color);
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }

        .auth-container h2 {
            margin-bottom: 20px;
            color: var(--primary-color);
            font-size: 20px;
        }

        .auth-container input[type="text"],
        .auth-container input[type="password"],
        .auth-container input[type="email"],
        .auth-container input[type="date"],
        .auth-container select {
            width: 100%;
            padding: 8px 10px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .auth-container button.register-btn {
            padding: 10px 20px;
            width: 100%;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            background-color: var(--button-secondary-color); /* Зелёный цвет */
            color: white;
            transition: background-color 0.3s;
            margin-top: 16px; /* Отступ от предыдущего поля */
        }

        .auth-container button.register-btn:hover {
            background-color: #16a085; /* Тёмно-зелёный при наведении */
        }

        footer {
            background-color: var(--primary-color);
            color: white;
            text-align: center;
            padding: 10px 0;
            font-size: 14px;
        }

        .error-message {
            color: red;
            font-size: 16px;
            display: block;
        }
    </style>
</head>
<body>

<header>
    <h1>Портал управления новостями</h1>
</header>

<!-- Полоска с кнопками -->
<div class="nav-bar">
    <!-- Кнопка "На главную" -->
    <form action="Controller" method="Get" style="margin: 0;">
        <button type="submit" name="command" value="GO_TO_INDEX_PAGE">На главную</button>
    </form>
</div>

<div class="container">
    <div class="auth-container">
        <h2>Регистрация</h2>
        <div class="error-message" id="error-message">
            <c:if test="${not empty requestScope.registerError}">
                <c:out value="${requestScope.registerError}" />
            </c:if>
        </div>
<%--        <div class="error-message" id="error-message">--%>
<%--            <c:if test="${not empty param.registerError}">--%>
<%--                <c:out value="${param.registerError}" />--%>
<%--            </c:if>--%>
<%--        </div>--%>
        <form action="Controller" method="post">
            <input type="text" name="login" placeholder="Логин" required>
            <input type="password" name="password" placeholder="Пароль" required>
            <input type="password" name="confirmPassword" placeholder="Повторите пароль" required>
            <input type="text" name="name" placeholder="Имя" required>
            <input type="text" name="surname" placeholder="Фамилия" required>
            <input type="email" name="email" placeholder="Почта" required>
            <select name="role" required>
                <option value="">Выберите роль</option>
                <option value="admin">Админ</option>
                <option value="author">Автор</option>
                <option value="user">Пользователь</option>
            </select>
            <input type="date" name="birthDate" placeholder="Дата рождения" required>
            <input type="text" name="address" placeholder="Адрес" required>
            <button class="register-btn" type="submit" name="command" value="do_registration">Зарегистрироваться</button>
        </form>
    </div>
</div>

<footer>
    <p>&copy; 2025 Портал управления новостями</p>
</footer>

</body>
</html>

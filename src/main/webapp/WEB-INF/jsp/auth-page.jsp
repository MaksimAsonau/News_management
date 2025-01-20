<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
    <style>
        :root {
            --primary-color: #2d3e50;
            --secondary-color: #e1e8f0;
            --accent-color: #0066cc;
            --button-secondary-color: #1abc9c; /* Новый цвет для кнопки регистрации */
            --text-color: #333;
            --background-color: #f7f9fc;
        }

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        /* Основной контейнер */
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            font-family: Arial, sans-serif;
            background-color: var(--background-color);
            color: var(--text-color);
        }

        /* Хидер */
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
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
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

        /* Центрирование формы */
        .container {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px 0;
            transform: translateY(-100px); /* Поднимаем форму немного выше */
        }

        /* Стиль формы */
        .auth-container {
            background-color: var(--secondary-color);
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px; /* Фиксированная ширина формы */
            text-align: center;
        }

        .auth-container h2 {
            margin-bottom: 20px;
            color: var(--primary-color);
            font-size: 20px;
        }

        .auth-container input[type="text"],
        .auth-container input[type="password"] {
            width: 100%;
            padding: 8px 10px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .auth-container button.login-btn {
            padding: 10px 20px;
            width: 100%;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            background-color: var(--primary-color);
            color: white;
            transition: background-color 0.3s;
        }

        .auth-container button.login-btn:hover {
            background-color: #1b2838;
        }

        .line-container {
            margin: 15px 0;
            border-top: 1px solid #ccc;
            padding-top: 10px;
            font-size: 14px;
            color: #555;
        }

        .auth-container button.register-btn {
            background-color: var(--button-secondary-color);
            color: white;
            margin: 10px 0;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
            font-size: 16px;
        }

        .auth-container button.register-btn:hover {
            background-color: #16a085;
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

<!-- Хидер -->
<header>
    <h1>Портал управления новостями</h1>
</header>

<!-- Полоска с кнопками -->
<div class="nav-bar" style="width: 100%; box-sizing: border-box; position: relative; z-index: 1;">
    <!-- Кнопка "На главную" -->
    <form action="Controller" method="Get" style="margin: 0;">
        <button type="submit" name="command" value="GO_TO_INDEX_PAGE"
                style="padding: 7px 15px; background-color: #2d3e50; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 14px;">
            На главную
        </button>
    </form>
</div>

<div class="container">
    <div class="auth-container">
        <h2>Авторизация</h2>

        <div class="error-message" id="error-message">
            <!-- Вывод сообщения об успешной регистрации -->

            <c:if test="${not empty param.successMessage}">
                <span style="color: green; font-size: 16px;">
                    <c:out value="${param.successMessage}"/>
                </span>
            </c:if>

            <!-- Вывод ошибки авторизации -->
            <c:if test="${not empty param.authError}">
                <c:out value="${param.authError}"/>
            </c:if>
        </div>

        <form action="Controller" method="post">
            <input type="text" id="login" name="login" placeholder="Введите логин" required>
            <input type="password" id="password" name="password" placeholder="Введите пароль" required>

            <button class="login-btn" type="submit" name="command" value="do_auth">Войти</button>
        </form>

        <div class="line-container">
            Нет аккаунта?
        </div>

        <form action="Controller" method="get">
            <button class="register-btn" type="submit" name="command" value="go_to_registration_page">
                Зарегистрироваться
            </button>
        </form>
    </div>
</div>

<!-- Футер -->
<footer>
    <p>&copy; 2025 Портал управления новостями</p>
</footer>

</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Создание новости</title>
    <style>
        :root {
            --primary-color: #2d3e50;
            --secondary-color: #e1e8f0;
            --accent-color: #0066cc;
            --button-secondary-color: #1abc9c;
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
            justify-content: space-between;
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

        .form-container {
            background-color: var(--secondary-color);
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 600px; /* Увеличена ширина формы */
            max-width: 80%; /* Ограничение ширины для маленьких экранов */
            margin: 20px auto;
            text-align: center;
        }

        .form-container h2 {
            margin-bottom: 20px;
            color: var(--primary-color);
            font-size: 20px;
        }

        .form-container input[type="text"],
        .form-container select,
        .form-container textarea {
            width: 100%;
            padding: 10px 12px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-family: Arial, sans-serif;
            font-size: 16px;
            line-height: 1.5;
        }

        .form-container textarea {
            resize: none; /* Убираем возможность изменения размера */
        }

        .form-container button.submit-btn {
            padding: 10px 20px;
            width: 100%;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            background-color: var(--button-secondary-color);
            color: white;
            transition: background-color 0.3s;
        }

        .form-container button.submit-btn:hover {
            background-color: #16a085;
        }

        footer {
            background-color: var(--primary-color);
            color: white;
            text-align: center;
            padding: 10px 0;
            font-size: 14px;
            margin-top: auto;
        }
    </style>
</head>
<body>

<header>
    <h1>Offliner. Главный новостной портал.</h1>
</header>

<!-- Полоска с кнопками -->
<div class="nav-bar">
    <!-- Кнопка "На главную" -->
    <form action="Controller" method="Get" style="margin: 0;">
        <button type="submit" name="command" value="GO_TO_INDEX_PAGE">На главную</button>
    </form>

    <!-- Приветствие и кнопка "Личный кабинет" -->
    <div>
        <span>Добро пожаловать, <strong><c:out value="${sessionScope.login}" /></strong>!</span>
        <span style="margin-left: 10px;"></span>
        <form action="Controller" method="Get" style="display: inline;">
            <button type="submit" name="command" value="GO_TO_ACCOUNT_PAGE">Личный кабинет</button>
        </form>
    </div>
</div>

<div class="container">
    <div class="form-container">
        <h2>Создание новости</h2>
        <form action="Controller" method="post">
            <!-- Поле для заголовка -->
            <input type="text" name="title" placeholder="Заголовок новости" maxlength="255" required>

            <!-- Поле для брифа -->
            <textarea name="brief" placeholder="Бриф новости" rows="3" maxlength="255" required></textarea>

            <!-- Поле для основного текста -->
            <textarea name="content" placeholder="Текст новости" rows="5" maxlength="255" required></textarea>

            <!-- Выбор категории -->
            <select name="categoryId" required>
                <option value="">Выберите категорию</option>
                <option value="1">IT</option>
                <option value="2">Sport</option>
                <option value="3">Cars</option>
            </select>

            <!-- Кнопка отправки -->
            <button class="submit-btn" type="submit" name="command" value="DO_ADD_NEWS">Отправить</button>
        </form>
    </div>
</div>

<footer>
    <p>&copy; 2025 Offliner</p>
</footer>

</body>
</html>

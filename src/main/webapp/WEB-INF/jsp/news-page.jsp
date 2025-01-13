<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Портал управления новостями</title>
    <style>
        /* Базовые стили */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        html, body {
            height: 100%;
            font-family: Arial, sans-serif;
            background-color: #f7f9fc;
            color: #333;
            display: flex;
            flex-direction: column;
        }

        /* Стили для хидера и футера */
        header {
            background-color: #2d3e50;
            color: white;
            text-align: center;
            padding: 15px 0;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        header h1 {
            font-size: 24px;
            margin: 0;
        }

        footer {
            background-color: #2d3e50;
            color: white;
            text-align: center;
            padding: 10px 0;
            box-shadow: 0 -2px 4px rgba(0, 0, 0, 0.1);
            font-size: 14px;
            margin-top: auto;
        }

        /* Стили блока авторизации */
        .auth {
            background-color: #e1e8f0;
            padding: 15px;
            display: flex;
            justify-content: flex-end;
            align-items: center;
        }

        .auth button {
            padding: 7px 15px;
            background-color: #2d3e50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }

        .auth button:hover {
            background-color: #1b2838;
        }

        /* Основной блок */
        main {
            flex: 1;
            padding: 20px;
            max-width: 1000px;
            margin: 0 auto;
        }

        .news-container {
            display: flex;
            flex-direction: row;
            gap: 20px;
            margin-bottom: 20px;
        }

        .news-image {
            flex-shrink: 0;
            width: 400px;
            height: auto;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .news-text {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .news-title {
            font-size: 24px;
            font-weight: bold;
            color: #2d3e50;
            margin-bottom: 10px;
        }

        .news-brief {
            font-size: 18px;
            color: #666;
            margin-bottom: 15px;
        }

        .news-content {
            font-size: 16px;
            line-height: 1.6;
            margin-bottom: 20px;
        }

        .news-meta {
            font-size: 14px;
            color: #666;
            border-top: 1px solid #ccc;
            padding-top: 10px;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<!-- Хидер -->
<header>
    <h1>Полная новость</h1>
</header>

<!-- Приветствие пользователя -->
<div class="auth" style="justify-content: space-between;">
    <!-- Кнопка "На главную" -->
    <form action="Controller" method="Get" style="display: inline;">
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

<!-- Основной контент -->
<main>
    <c:choose>
        <c:when test="${not empty news}">
            <div class="news-container">
                <!-- Картинка новости -->
                <img src="<c:out value='${news.imageUrl}' />" alt="News Image" class="news-image"/>

                <!-- Блок текста -->
                <div class="news-text">
                    <!-- Заголовок новости -->
                    <h1 class="news-title"><c:out value="${news.title}" /></h1>

                    <!-- Краткое описание -->
                    <p class="news-brief"><c:out value="${news.brief}" /></p>

                    <!-- Полный контент -->
                    <p class="news-content"><c:out value="${news.content}" /></p>
                </div>
            </div>

            <!-- Информация о новости -->

            <div class="news-meta" style="text-align: right;">
                Автор новости: <strong><c:out value="${news.loginOfAuthor}" /></strong>,
                Категория новости: <strong><c:out value="${news.categoryName}" /></strong>,
                Дата публикации: <strong><c:out value="${news.publishDate}" /></strong>
            </div>
        </c:when>

        <c:otherwise>
            <p>Новость не найдена. Попробуйте снова.</p>
        </c:otherwise>
    </c:choose>
</main>

<footer>
    <p>&copy; 2025 Портал управления новостями</p>
</footer>

</body>
</html>



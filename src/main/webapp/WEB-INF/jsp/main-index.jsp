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

        /* Стили для основного контента */
        main {
            flex: 1;
            padding: 20px;
            max-width: 800px;
            margin: 0 auto;
        }

        /* Футер фиксируется внизу страницы */
        footer {
            background-color: #2d3e50;
            color: white;
            text-align: center;
            padding: 10px 0;
            box-shadow: 0 -2px 4px rgba(0, 0, 0, 0.1);
            font-size: 14px;
        }

        /* Стили для блока авторизации */
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

        .auth .warning {
            color: red;
            margin-right: 10px;
            font-size: 14px;
        }

        /* Стили для новостей */
        .news-item {
            border-bottom: 1px solid #ccc;
            padding: 20px 0;
            display: flex;
            align-items: flex-start;
        }

        .news-item img {
            width: 225px;
            height: auto;
            margin-right: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .news-item h2 {
            margin-top: 0;
            color: #2d3e50;
            font-size: 18px;
        }

        .news-item p {
            color: #666;
            margin: 5px 0 10px;
            line-height: 1.6;
        }

        .news-item a {
            text-decoration: none;
            color: #0066cc;
            font-weight: bold;
        }

        .news-item a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<!-- Хидер -->
<header>
    <h1>Портал управления новостями</h1>
</header>

<!-- Кнопка "вход/регистрации" или кнопка "Войти в личный кабинет" -->
<div class="auth">
    <c:choose>
        <c:when test="${not empty sessionScope.login}">
            <span>Добро пожаловать, <strong><c:out value="${sessionScope.login}" /></strong>!</span>
            <span style="margin-left: 10px;"></span>
            <form action="Controller" method="Get" style="display: inline;">
                <button type="submit" name="command" value="GO_TO_ACCOUNT_PAGE">Личный кабинет</button>
            </form>
        </c:when>

        <c:otherwise>
            <!-- Выводим предупреждение, если оно установлено -->
            <c:if test="${not empty sessionScope.warningMessage}">
                <span class="warning"><c:out value="${sessionScope.warningMessage}" /></span>
                <!-- Удаляем предупреждение после отображения -->
                <c:set var="warningMessage" value="" scope="session" />
            </c:if>
            <form action="Controller" method="Get">
                <button type="submit" name="command" value="GO_TO_AUTHENTICATION_PAGE">Войти / Зарегистрироваться</button>
            </form>
        </c:otherwise>
    </c:choose>
</div>

<!-- Последние новости -->
<main>
    <c:forEach var="news" items="${allNews}">
        <article class="news-item">
            <!-- Картинка новости -->
            <img src="<c:out value='${news.imageUrl}' />" alt="News Image" class="news-image" />

            <!-- Текстовый блок -->
            <div>
                <!-- Заголовок новости -->
                <h2><c:out value="${news.title}" /></h2>

                <!-- Краткое описание -->
                <p><c:out value="${news.brief}" /></p>

                <!-- Ссылка на полную новость -->
                <a href="Controller?command=go_to_news_page&newsId=<c:out value='${news.newsId}' />">Читать далее</a>
            </div>
        </article>
    </c:forEach>
</main>

<!-- Футер -->
<footer>
    <p>&copy; 2025 Портал управления новостями</p>
</footer>

</body>
</html>





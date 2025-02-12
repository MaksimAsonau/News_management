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

        /* Основной контент */
        main {
            flex: 1;
            padding: 20px;
            max-width: 800px;
            margin: 0 auto;
        }

        footer {
            background-color: #2d3e50;
            color: white;
            text-align: center;
            padding: 10px 0;
            font-size: 14px;
        }

        .auth {
            background-color: #e1e8f0;
            padding: 15px;
            display: flex;
            align-items: center;
            position: relative;
        }

        .auth-left {
            margin-right: auto;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .auth-center {
            position: absolute;
            left: 50%;
            transform: translateX(-50%);
            display: flex;
            gap: 10px;
        }

        .auth-right {
            margin-left: auto;
            display: flex;
            align-items: center;
            gap: 10px;
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

        .auth button.add-news {
            background-color: #28a745; /* Зеленый цвет кнопки "Добавить новость" */
        }

        .auth button:hover {
            background-color: #1b2838;
        }

        .auth button.add-news:hover {
            background-color: #218838; /* Более темный зеленый при наведении */
        }

        .auth button.category {
            background-color: #6c757d; /* Стандартный цвет кнопок */
        }

        .auth button.category.active {
            background-color: #28a745; /* Зелёный цвет для активной кнопки */
            cursor: default; /* Запрещаем клик */
        }

        .auth button.category:hover:not(.active) {
            background-color: #5a6268;
        }

        .auth .warning {
            color: red;
            font-size: 15px;
        }

        .news-item {
            border-bottom: 1px solid #ccc;
            padding: 20px 0;
            display: flex;
            align-items: flex-start;
        }

        .news-item img {
            width: 225px;
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
            margin: 5px 0 10px;
        }

        .news-item a {
            color: #0066cc;
            font-weight: bold;
        }
    </style>
</head>
<body>

<header>
    <h1>Offliner. Главный новостной портал.</h1>
</header>

<div class="auth">
    <!-- Левая часть: кнопка "Добавить новость" -->
    <div class="auth-left">
        <c:if test="${not empty sessionScope.role && (sessionScope.role == 'author' || sessionScope.role == 'admin')}">
            <form action="Controller" method="Get">
                <button type="submit" name="command" value="GO_TO_ADD_NEWS_PAGE" class="add-news">Добавить новость</button>
            </form>
        </c:if>
    </div>

    <div class="auth-center">
        <!-- Кнопки категорий -->
        <form action="Controller" method="Get">
            <input type="hidden" name="command" value="go_to_news_by_category_page" />
            <button type="submit" name="categoryId" value="1" class="category <c:if test='${param.categoryId == "1"}'>active</c:if>">IT News</button>
        </form>
        <form action="Controller" method="Get">
            <input type="hidden" name="command" value="go_to_news_by_category_page" />
            <button type="submit" name="categoryId" value="2" class="category <c:if test='${param.categoryId == "2"}'>active</c:if>">Sport News</button>
        </form>
        <form action="Controller" method="Get">
            <input type="hidden" name="command" value="go_to_news_by_category_page" />
            <button type="submit" name="categoryId" value="3" class="category <c:if test='${param.categoryId == "3"}'>active</c:if>">Cars News</button>
        </form>

        <!-- Кнопка "My News" -->
        <c:if test="${not empty sessionScope.role && (sessionScope.role == 'author' || sessionScope.role == 'admin')}">
            <form action="Controller" method="Get">
                <button type="submit" name="command" value="GO_TO_MY_NEWS_PAGE" class="category <c:if test='${param.command == "GO_TO_MY_NEWS_PAGE"}'>active</c:if>">My News</button>
            </form>
        </c:if>
    </div>

    <div class="auth-right">
        <c:choose>
            <c:when test="${not empty sessionScope.login}">
                <span>Добро пожаловать, <strong><c:out value="${sessionScope.login}" /></strong>!</span>
                <form action="Controller" method="Get">
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

                <c:if test="${not empty param.successMessage}">
                    <p style="color: green;"><c:out value="${param.successMessage}" /></p>
                </c:if>

                <form action="Controller" method="Get">
                    <button type="submit" name="command" value="GO_TO_AUTHENTICATION_PAGE">Войти / Зарегистрироваться</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<main>
    <!-- Блок сообщения -->
    <c:if test="${not empty param.message}">
        <p style="color: green; font-weight: bold; text-align: center; padding: 10px; border: 1px solid green; background-color: #e6ffe6;">
            <c:out value="${param.message}" />
        </p>
    </c:if>

    <!-- Список новостей -->
    <c:forEach var="news" items="${allNews}">
        <article class="news-item">
            <img src="<c:out value='${news.imageUrl}' />" alt="News Image" />
            <div>
                <h2><c:out value="${news.title}" /></h2>
                <p><c:out value="${news.brief}" /></p>
                <a href="Controller?command=go_to_news_page&newsId=<c:out value='${news.newsId}' />">Читать далее</a>
            </div>
        </article>
    </c:forEach>
</main>

<footer>
    <p>&copy; 2025 Offliner</p>
</footer>

</body>
</html>





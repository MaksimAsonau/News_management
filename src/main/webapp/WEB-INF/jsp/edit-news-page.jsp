<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Редактирование новости</title>
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
        }

        .form-container {
            background-color: var(--secondary-color);
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 600px;
            max-width: 80%;
            margin: 20px auto;
            text-align: center;
        }

        .form-container h2 {
            margin-bottom: 20px;
            color: var(--primary-color);
        }

        .form-container input[type="text"],
        .form-container select,
        .form-container textarea {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
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
    <h1>Редактирование новости</h1>
</header>

<div class="nav-bar">
    <form action="Controller" method="Get">
        <button type="submit" name="command" value="GO_TO_INDEX_PAGE">На главную</button>
    </form>
    <div>
        <span>Добро пожаловать, <strong><c:out value="${sessionScope.login}"/></strong>!</span>
        <form action="Controller" method="Get" style="display: inline;">
            <button type="submit" name="command" value="GO_TO_ACCOUNT_PAGE">Личный кабинет</button>
        </form>
    </div>
</div>

<div class="form-container">
    <h2>Редактирование новости</h2>
    <form action="Controller" method="post">
        <input type="hidden" name="newsId" value="<c:out value='${news.newsId}' />">

        <input type="text" name="title" value="<c:out value='${news.title}' />" required>
        <textarea name="brief" rows="3" required><c:out value="${news.brief}"/></textarea>
        <textarea name="content" rows="5" required><c:out value="${news.content}"/></textarea>

        <select name="categoryId" required>
                        <option value="1" <c:if test="${news.categoryId == 1}">selected</c:if>>IT</option>
                        <option value="2" <c:if test="${news.categoryId == 2}">selected</c:if>>Sport</option>
                        <option value="3" <c:if test="${news.categoryId == 3}">selected</c:if>>Cars</option>
<%--            <option value="1" <c:if test="${news.categoryName eq 'IT'}">selected</c:if>>IT</option>--%>
<%--            <option value="2" <c:if test="${news.categoryName eq 'Sport'}">selected</c:if>>Sport</option>--%>
<%--            <option value="3" <c:if test="${news.categoryName eq 'Cars'}">selected</c:if>>Cars</option>--%>

        </select>

        <button class="submit-btn" type="submit" name="command" value="DO_EDIT_NEWS">Сохранить изменения</button>
    </form>
</div>

<footer>
    <p>&copy; 2025 Offliner</p>
</footer>

</body>
</html>

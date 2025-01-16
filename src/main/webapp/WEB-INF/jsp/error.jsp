<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Ошибка</title>
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
      justify-content: center;
      align-items: center;
    }

    .error-container {
      background-color: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      max-width: 600px;
      width: 100%;
      text-align: center;
    }

    .error-title {
      font-size: 24px;
      color: #e74c3c;
      margin-bottom: 15px;
    }

    .error-message {
      font-size: 18px;
      color: #333;
      margin-bottom: 20px;
    }

    .back-button {
      padding: 10px 20px;
      background-color: #2d3e50;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px;
      text-decoration: none;
    }

    .back-button:hover {
      background-color: #1b2838;
    }
  </style>
</head>
<body>

<div class="error-container">
  <h1 class="error-title">Произошла ошибка</h1>
  <p class="error-message">
    <%-- Выводим сообщение об ошибке из атрибута --%>
    <c:out value="${errorMessage}" />
  </p>
  <a href="Controller?command=go_to_index_page" class="back-button">Вернуться на главную</a>
</div>

</body>
</html>

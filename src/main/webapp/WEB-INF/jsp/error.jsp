<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Ошибка</title>
  <style>
    :root {
      --primary-color: #2d3e50;
      --secondary-color: #e1e8f0;
      --error-color: #e74c3c;
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
      align-items: center;
      padding: 10px 20px;
    }

    .container {
      flex: 1;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 20px 0;
      transform: translateY(-50px);
    }

    .error-container {
      background-color: var(--secondary-color);
      padding: 25px;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
      width: 100%;
      max-width: 400px;
      text-align: center;
    }

    .error-container h2 {
      margin-bottom: 15px;
      color: var(--error-color);
      font-size: 22px;
    }

    .error-message {
      color: var(--error-color);
      font-size: 16px;
      font-weight: bold;
      margin-bottom: 15px;
      display: block;
    }

    .back-button {
      display: inline-block;
      padding: 10px 20px;
      margin-top: 10px;
      background-color: var(--primary-color);
      color: white;
      text-decoration: none;
      border-radius: 4px;
      font-size: 16px;
      transition: background-color 0.3s;
    }

    .back-button:hover {
      background-color: #1b2838;
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

<!-- Хидер -->
<header>
  <h1>Offliner. Главный новостной портал.</h1>
</header>

<!-- Полоска с кнопками -->
<div class="nav-bar"></div>

<!-- Контейнер с ошибкой -->
<div class="container">
  <div class="error-container">
    <h2>Ошибка</h2>

    <div class="error-message">
      <c:out value="${param.errorMessage}" default="Произошла неизвестная ошибка." />
    </div>

    <a href="Controller?command=go_to_index_page" class="back-button">Вернуться на главную</a>
  </div>
</div>

<!-- Футер -->
<footer>
  <p>&copy; 2025 Offliner</p>
</footer>

</body>
</html>

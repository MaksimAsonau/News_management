<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="ru">--%>
<%--<head>--%>
<%--  <meta charset="UTF-8">--%>
<%--  <title>Личный кабинет</title>--%>
<%--  <style>--%>
<%--    /* Базовые стили */--%>
<%--    * {--%>
<%--      box-sizing: border-box;--%>
<%--      margin: 0;--%>
<%--      padding: 0;--%>
<%--    }--%>

<%--    html, body {--%>
<%--      height: 100%;--%>
<%--      font-family: Arial, sans-serif;--%>
<%--      background-color: #f7f9fc;--%>
<%--      color: #333;--%>
<%--      display: flex;--%>
<%--      flex-direction: column;--%>
<%--    }--%>

<%--    header {--%>
<%--      background-color: #2d3e50;--%>
<%--      color: white;--%>
<%--      text-align: center;--%>
<%--      padding: 15px 0;--%>
<%--      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);--%>
<%--    }--%>

<%--    header h1 {--%>
<%--      font-size: 24px;--%>
<%--      margin: 0;--%>
<%--    }--%>

<%--    footer {--%>
<%--      background-color: #2d3e50;--%>
<%--      color: white;--%>
<%--      text-align: center;--%>
<%--      padding: 10px 0;--%>
<%--      box-shadow: 0 -2px 4px rgba(0, 0, 0, 0.1);--%>
<%--      font-size: 14px;--%>
<%--      margin-top: auto;--%>
<%--    }--%>

<%--    .auth {--%>
<%--      background-color: #e1e8f0;--%>
<%--      padding: 15px;--%>
<%--      display: flex;--%>
<%--      justify-content: space-between;--%>
<%--      align-items: center;--%>
<%--    }--%>

<%--    .auth button {--%>
<%--      padding: 7px 15px;--%>
<%--      background-color: #2d3e50;--%>
<%--      color: white;--%>
<%--      border: none;--%>
<%--      border-radius: 4px;--%>
<%--      cursor: pointer;--%>
<%--      font-size: 14px;--%>
<%--    }--%>

<%--    .auth button:hover {--%>
<%--      background-color: #1b2838;--%>
<%--    }--%>

<%--    main {--%>
<%--      flex: 1;--%>
<%--      padding: 20px;--%>
<%--      max-width: 800px;--%>
<%--      margin: 60px auto 0; /* Увеличили отступ сверху */--%>
<%--      display: flex;--%>
<%--      justify-content: center;--%>
<%--      align-items: flex-start;--%>
<%--    }--%>

<%--    .profile-container {--%>
<%--      display: flex;--%>
<%--      flex-direction: column;--%>
<%--      align-items: center;--%>
<%--      background-color: white;--%>
<%--      padding: 20px;--%>
<%--      border-radius: 8px;--%>
<%--      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);--%>
<%--      max-width: 700px;--%>
<%--      width: 100%;--%>
<%--    }--%>

<%--    .profile-image {--%>
<%--      width: 300px;--%>
<%--      height: 300px;--%>
<%--      object-fit: cover;--%>
<%--      margin-bottom: 20px;--%>
<%--      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);--%>
<%--    }--%>

<%--    .profile-info {--%>
<%--      width: 100%;--%>
<%--      margin-top: 10px;--%>
<%--    }--%>

<%--    .profile-info table {--%>
<%--      width: 100%;--%>
<%--      border-collapse: collapse;--%>
<%--      font-size: 16px;--%>
<%--      color: #333;--%>
<%--    }--%>

<%--    .profile-info table td {--%>
<%--      padding: 8px;--%>
<%--      border: 1px solid #ccc;--%>
<%--      vertical-align: top;--%>
<%--    }--%>

<%--    .profile-info table td:first-child {--%>
<%--      font-weight: bold;--%>
<%--      color: #2d3e50;--%>
<%--      width: 40%;--%>
<%--    }--%>

<%--    .profile-info table td:last-child {--%>
<%--      color: #666;--%>
<%--    }--%>

<%--    .edit-profile {--%>
<%--      margin-top: 20px;--%>
<%--    }--%>

<%--    .edit-profile button {--%>
<%--      padding: 10px 20px;--%>
<%--      background-color: #1abc9c;--%>
<%--      color: white;--%>
<%--      border: none;--%>
<%--      border-radius: 4px;--%>
<%--      cursor: pointer;--%>
<%--      font-size: 16px;--%>
<%--      margin-bottom: 10px; /* Отступ между кнопками */--%>
<%--    }--%>

<%--    .edit-profile button:hover {--%>
<%--      background-color: #16a085;--%>
<%--    }--%>
<%--  </style>--%>
<%--</head>--%>
<%--<body>--%>

<%--<!-- Хидер -->--%>
<%--<header>--%>
<%--  <h1>Личный кабинет</h1>--%>
<%--</header>--%>

<%--<!-- Полоска навигации -->--%>
<%--<div class="auth">--%>
<%--  <form action="Controller" method="Get" style="margin: 0;">--%>
<%--    <button type="submit" name="command" value="GO_TO_INDEX_PAGE">На главную</button>--%>
<%--  </form>--%>
<%--</div>--%>

<%--<!-- Основной контент -->--%>
<%--<main>--%>
<%--  <c:if test="${not empty user}">--%>
<%--    <div class="profile-container">--%>
<%--      <!-- Фото пользователя -->--%>
<%--      <img src="<c:out value='${user.imageUrl}' />" alt="User Image" class="profile-image" />--%>

<%--      <!-- Информация о пользователе -->--%>
<%--      <div class="profile-info">--%>
<%--        <table>--%>
<%--          <tr>--%>
<%--            <td>Логин:</td>--%>
<%--            <td><c:out value="${user.login}" /></td>--%>
<%--          </tr>--%>
<%--          <tr>--%>
<%--            <td>Имя:</td>--%>
<%--            <td><c:out value="${user.name}" /></td>--%>
<%--          </tr>--%>
<%--          <tr>--%>
<%--            <td>Фамилия:</td>--%>
<%--            <td><c:out value="${user.surname}" /></td>--%>
<%--          </tr>--%>
<%--          <tr>--%>
<%--            <td>Email:</td>--%>
<%--            <td><c:out value="${user.email}" /></td>--%>
<%--          </tr>--%>
<%--          <tr>--%>
<%--            <td>Роль:</td>--%>
<%--            <td><c:out value="${user.roleName}" /></td>--%>
<%--          </tr>--%>
<%--          <tr>--%>
<%--            <td>Дата рождения:</td>--%>
<%--            <td><c:out value="${user.birthdayDate}" /></td>--%>
<%--          </tr>--%>
<%--          <tr>--%>
<%--            <td>Дата регистрации:</td>--%>
<%--            <td><c:out value="${user.registrationDate}" /></td>--%>
<%--          </tr>--%>
<%--          <tr>--%>
<%--            <td>Адрес:</td>--%>
<%--            <td><c:out value="${user.address}" /></td>--%>
<%--          </tr>--%>
<%--        </table>--%>
<%--      </div>--%>

<%--      <!-- Кнопки редактирования и выхода -->--%>
<%--      <div class="edit-profile">--%>
<%--        <form action="Controller" method="Get">--%>
<%--          <button type="submit" name="command" value="GO_TO_EDIT_PROFILE">Редактировать профиль</button>--%>
<%--        </form>--%>
<%--        <form action="Controller" method="Get">--%>
<%--          <button type="submit" name="command" value="LOGOUT">Выйти из профиля</button>--%>
<%--        </form>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--  </c:if>--%>
<%--</main>--%>

<%--<footer>--%>
<%--  <p>&copy; 2025 Портал управления новостями</p>--%>
<%--</footer>--%>

<%--</body>--%>
<%--</html>--%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Личный кабинет</title>
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

    .auth {
      background-color: #e1e8f0;
      padding: 15px;
      display: flex;
      justify-content: space-between;
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

    main {
      flex: 1;
      padding: 20px;
      max-width: 800px;
      margin: 20px auto 0; /* Опустили блок ниже */
      display: flex;
      justify-content: center;
      align-items: flex-start;
    }

    .profile-container {
      display: flex;
      flex-direction: column;
      align-items: center;
      background-color: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      max-width: 700px;
      width: 100%;
    }

    .profile-image {
      width: 300px;
      height: 300px;
      object-fit: cover;
      margin-bottom: 20px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }

    .profile-info {
      width: 100%;
      margin-top: 10px;
    }

    .profile-info table {
      width: 100%;
      border-collapse: collapse;
      font-size: 16px;
      color: #333;
    }

    .profile-info table td {
      padding: 8px;
      border: 1px solid #ccc;
      vertical-align: top;
    }

    .profile-info table td:first-child {
      font-weight: bold;
      color: #2d3e50;
      width: 40%;
    }

    .profile-info table td:last-child {
      color: #666;
    }

    .edit-profile {
      margin-top: 20px;
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 10px;
    }

    .edit-profile button {
      padding: 10px 20px;
      background-color: #1abc9c;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px;
    }

    .edit-profile button.logout {
      background-color: #e74c3c;
    }

    .edit-profile button.logout:hover {
      background-color: #c0392b;
    }

    .edit-profile button:hover {
      background-color: #16a085;
    }
  </style>
</head>
<body>

<!-- Хидер -->
<header>
  <h1>Личный кабинет</h1>
</header>

<!-- Полоска навигации -->
<div class="auth">
  <form action="Controller" method="Get" style="margin: 0;">
    <button type="submit" name="command" value="GO_TO_INDEX_PAGE">На главную</button>
  </form>
</div>

<!-- Основной контент -->
<main>
  <c:if test="${not empty user}">
    <div class="profile-container">
      <!-- Фото пользователя -->
      <img src="<c:out value='${user.imageUrl}' />" alt="User Image" class="profile-image" />

      <!-- Информация о пользователе -->
      <div class="profile-info">
        <table>
          <tr>
            <td>Логин:</td>
            <td><c:out value="${user.login}" /></td>
          </tr>
          <tr>
            <td>Имя:</td>
            <td><c:out value="${user.name}" /></td>
          </tr>
          <tr>
            <td>Фамилия:</td>
            <td><c:out value="${user.surname}" /></td>
          </tr>
          <tr>
            <td>Email:</td>
            <td><c:out value="${user.email}" /></td>
          </tr>
          <tr>
            <td>Роль:</td>
            <td><c:out value="${user.roleName}" /></td>
          </tr>
          <tr>
            <td>Дата рождения:</td>
            <td><c:out value="${user.birthdayDate}" /></td>
          </tr>
          <tr>
            <td>Дата регистрации:</td>
            <td><c:out value="${user.registrationDate}" /></td>
          </tr>
          <tr>
            <td>Адрес:</td>
            <td><c:out value="${user.address}" /></td>
          </tr>
        </table>
      </div>

      <!-- Кнопки редактирования и выхода -->
      <div class="edit-profile">
        <form action="Controller" method="Get">
          <button type="submit" name="command" value="GO_TO_EDIT_PROFILE">Редактировать профиль</button>
        </form>
        <form action="Controller" method="Get">
          <button type="submit" name="command" value="LOGOUT" class="logout">Выйти из профиля</button>
        </form>
      </div>
    </div>
  </c:if>
</main>

<footer>
  <p>&copy; 2025 Портал управления новостями</p>
</footer>

</body>
</html>

package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.controller.concrete.Command;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class GoToAddNewsPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем сессию без ее создания
        HttpSession session = request.getSession(false);

        // Проверяем, есть ли сессия и атрибут role
        if (session == null || session.getAttribute("role") == null) {
            // Если нет сессии или роль не задана, перенаправляем на главную страницу с ошибкой
            response.sendRedirect("Controller?command=go_to_index_page&errorMessage=Access+denied");
            return;
        }

        // Получаем роль пользователя
        String userRole = (String) session.getAttribute("role");

        // Проверяем роль пользователя
        if (!userRole.equalsIgnoreCase("admin") && !userRole.equalsIgnoreCase("author")) {
            // Если роль не соответствует требованиям, перенаправляем на главную страницу
            response.sendRedirect("Controller?command=go_to_index_page&errorMessage=Access+denied");
            return;
        }

        // Если все проверки пройдены, форвардим на страницу добавления новости
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/add-news-page.jsp");
        dispatcher.forward(request, response);
    }
}

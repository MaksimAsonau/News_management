package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.controller.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GenerateError implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Симуляция ошибки
            throw new RuntimeException("Это тестовая ошибка для проверки error.jsp");
        } catch (Exception e) {
            // Логирование ошибки (опционально)
            e.printStackTrace();
            // Передача сообщения об ошибке
            request.setAttribute("errorMessage", e.getMessage());

            // Перенаправление на страницу error.jsp
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.controller.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Logout implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getSession(false) != null) {
                request.getSession().invalidate();
            }

            String successMessage = "Вы успешно вышли из профиля.";
            String encodedMessage = URLEncoder.encode(successMessage, StandardCharsets.UTF_8);
            response.sendRedirect("Controller?command=go_to_index_page&successMessage=" + encodedMessage);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Произошла ошибка при выходе из профиля.");
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}

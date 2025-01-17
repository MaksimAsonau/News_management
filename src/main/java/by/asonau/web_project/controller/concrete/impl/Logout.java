package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.controller.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Logout implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getSession(false) != null) {
                request.getSession().invalidate();
            }
            response.sendRedirect("Controller?command=go_to_index_page");
        } catch (Exception e) {
            // Логирование ошибки
            e.printStackTrace();
            request.setAttribute("errorMessage", "Произошла ошибка при выходе из профиля.");
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}

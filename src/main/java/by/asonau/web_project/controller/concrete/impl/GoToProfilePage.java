package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.bean.User;
import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToProfilePage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServiceException, IOException {

        User user = (User) request.getSession().getAttribute("tempUser"); // Достаем временного пользователя
        if (user != null) {
            request.setAttribute("user", user); // Передаем пользователя в JSP
            request.getSession().removeAttribute("tempUser"); // Удаляем временного пользователя из сессии
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/account-page.jsp");
        dispatcher.forward(request, response);
    }
}

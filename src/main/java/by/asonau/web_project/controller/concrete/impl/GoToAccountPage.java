package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.bean.User;
import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.IUserService;
import by.asonau.web_project.service.ServiceException;
import by.asonau.web_project.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAccountPage implements Command {

    private final IUserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("id"); // ID пользователя из сессии

        try {
            User user = userService.getUserInfoById(userId); // Получаем данные из базы
            if (user != null) {
                request.setAttribute("user", user); // Передаем пользователя в JSP
            }
            // Форвард на страницу личного кабинета
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/account-page.jsp");
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            // В случае ошибки перенаправляем на страницу ошибки
            response.sendRedirect("Controller?command=go_to_error_page");
        }
    }
}

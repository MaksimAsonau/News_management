package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.bean.User;
import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.IUserService;
import by.asonau.web_project.service.ServiceException;
import by.asonau.web_project.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAccountPage implements Command {

    private final IUserService userService = ServiceProvider.getInstance().getUserService();

    public GoToAccountPage() throws ServiceException {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServiceException, IOException {
        int userId = (int) request.getSession().getAttribute("id"); // ID пользователя из сессии
        try {
            User user = userService.getUserInfoById(userId); // Получаем данные из базы
            request.getSession().setAttribute("tempUser", user); // Временно кладем пользователя в сессию
            response.sendRedirect("Controller?command=go_to_profile_page"); // Редирект
        } catch (ServiceException e) {
            response.sendRedirect("Controller?command=go_to_error_page");
        }
    }
}

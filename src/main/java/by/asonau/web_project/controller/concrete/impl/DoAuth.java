package by.asonau.web_project.controller.concrete.impl;

import java.io.IOException;

import by.asonau.web_project.bean.Auth;
import by.asonau.web_project.bean.User;
import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.IUserService;
import by.asonau.web_project.service.ServiceException;
import by.asonau.web_project.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoAuth implements Command {

    private final IUserService userService = ServiceProvider.getInstance().getUserService();

    public DoAuth() throws ServiceException {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            response.sendRedirect("Controller?command=go_to_authentication_page&authError=Email+и+пароль+не+могут+быть+пустыми");
            return;
        }

        try {
            // Проверка авторизации
            User authorizedUser = userService.checkAuth(login, password);

            if (authorizedUser == null) {
                // Логин или пароль неверны
                response.sendRedirect("Controller?command=go_to_authentication_page&authError=Invalid+login+or+password");
                return;
            }

            // Авторизация успешна, создаем объект Auth
            Auth auth = new Auth();
            auth.setId(authorizedUser.getId());
            auth.setRole(authorizedUser.getUserRole());
            auth.setLogin(authorizedUser.getLogin());

            // Сохраняем авторизацию в сессии
            request.getSession().setAttribute("auth", auth);
            request.getSession().setAttribute("id", auth.getId());
            request.getSession().setAttribute("role", auth.getRole().name().toLowerCase());
            request.getSession().setAttribute("login", auth.getLogin());

            response.sendRedirect("Controller?command=go_to_index_page");

        } catch (ServiceException e) {
            // Ошибка на уровне сервиса
            response.sendRedirect("Controller?command=go_to_authentication_page&authError=Service+error+occurred");
        }
    }
}

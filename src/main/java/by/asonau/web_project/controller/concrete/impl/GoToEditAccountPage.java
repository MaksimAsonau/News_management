package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.bean.Auth;
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

public class GoToEditAccountPage implements Command {
    private final IUserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Auth auth = (Auth) request.getSession().getAttribute("auth");

        if (auth == null) {
            request.getSession().setAttribute("warningMessage", "Для редактирования профиля войдите в систему.");
            response.sendRedirect("Controller?command=go_to_index_page");
            return;
        }

        int userId = auth.getId();

        try {
            User user = userService.getUserInfoForEdit(userId);

            request.setAttribute("user", user);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/edit-account-page.jsp");
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            response.sendRedirect("Controller?command=go_to_error_page&errorMessage=Server+error");
        }
    }
}

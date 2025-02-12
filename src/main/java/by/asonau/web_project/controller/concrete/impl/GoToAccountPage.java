package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.bean.News;
import by.asonau.web_project.bean.User;
import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.INewsService;
import by.asonau.web_project.service.IUserService;
import by.asonau.web_project.service.ServiceException;
import by.asonau.web_project.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class GoToAccountPage implements Command {

    private final IUserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object sessionUserId = request.getSession().getAttribute("id");
        if (!(sessionUserId instanceof Integer)) {
            response.sendRedirect("Controller?command=go_to_error_page&error=invalid_session");
            return;
        }

        int userId = (int) sessionUserId;

        try {
            User user = userService.getUserInfoById(userId);

            if (user != null) {
                request.setAttribute("user", user);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/account-page.jsp");
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            response.sendRedirect("Controller?command=go_to_error_page&errorMessage=Server+error");
        }
    }
}

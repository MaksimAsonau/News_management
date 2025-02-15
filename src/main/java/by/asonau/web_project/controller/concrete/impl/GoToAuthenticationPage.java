package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.bean.Auth;
import by.asonau.web_project.controller.concrete.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAuthenticationPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Auth auth = (Auth) request.getSession(true).getAttribute("auth");

        if (auth != null) {
            System.out.println("Запрос страницы авторазации авторизированным пользователем");
            response.sendRedirect("Controller?command=go_to_account_page");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/auth-page.jsp");
        dispatcher.forward(request, response);
    }
}


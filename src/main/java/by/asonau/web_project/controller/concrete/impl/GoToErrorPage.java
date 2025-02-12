package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.controller.concrete.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToErrorPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMessage = request.getParameter("errorMessage");
        request.setAttribute("errorMessage", errorMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
        dispatcher.forward(request, response);
    }
}
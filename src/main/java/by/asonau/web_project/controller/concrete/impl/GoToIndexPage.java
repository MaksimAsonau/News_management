package by.asonau.web_project.controller.concrete.impl;

import java.io.IOException;
import java.util.List;

import by.asonau.web_project.bean.Auth;
import by.asonau.web_project.bean.News;
import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.INewsService;
import by.asonau.web_project.service.ServiceException;
import by.asonau.web_project.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToIndexPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Auth auth = null;
        if (request.getSession(false) != null) {
            auth = (Auth) request.getSession(false).getAttribute("auth");
        }

        INewsService newsService = ServiceProvider.getInstance().getNewsService();

        try {
            List<News> allNewsList = newsService.getNewsList();
            request.setAttribute("allNews", allNewsList);

            if (auth != null) {
                System.out.println("V seti user: " + auth);
                System.out.println("User's role: " + auth.getRole().name().toLowerCase());
            } else {
                System.out.println("V seti net zaregistrirovannogo usera");
            }

        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect("Controller?command=go_to_error_page&errorMessage=Server+error");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main-index.jsp");
        dispatcher.forward(request, response);
	}
}

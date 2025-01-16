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

        // Проверяем существование сессии
        Auth auth = null;
        if (request.getSession(false) != null) {
            auth = (Auth) request.getSession(false).getAttribute("auth");
        }

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        INewsService newsService = serviceProvider.getNewsService();

        try {
            List<News> allNewsList = newsService.getNewsList();
            request.setAttribute("allNews", allNewsList);

            if (auth != null) {
                System.out.println("V seti user " + auth);
            } else {
                System.out.println("V seti net zaregistrirovannogo usera");
            }

        } catch (ServiceException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Oshibka pri poluchenii novostei.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main-index.jsp");
        dispatcher.forward(request, response);
	}
}

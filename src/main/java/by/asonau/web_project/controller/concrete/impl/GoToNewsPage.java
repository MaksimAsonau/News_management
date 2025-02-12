package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.bean.Auth;
import by.asonau.web_project.bean.News;
import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.INewsService;
import by.asonau.web_project.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToNewsPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Auth auth = (Auth) request.getSession().getAttribute("auth");

        if (auth == null) {
            request.getSession().setAttribute("warningMessage", "Для просмотра новости войдите или зарегистрируйтесь.");
            response.sendRedirect("Controller?command=go_to_index_page");
            return;
        }

        INewsService newsService = ServiceProvider.getInstance().getNewsService();

        String newsId = request.getParameter("newsId");
        News news = null;

        try {
            news = newsService.getNewsFromDatabaseById(Integer.parseInt(newsId));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Controller?command=go_to_error_page&errorMessage=Server+error");
        }

        if (news != null) {
            request.setAttribute("news", news);
        } else {
            request.setAttribute("errorMessage", "Новость не найдена.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/news-page.jsp");
        dispatcher.forward(request, response);
    }
}

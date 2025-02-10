package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.INewsService;
import by.asonau.web_project.service.ServiceException;
import by.asonau.web_project.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoDeleteNews implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newsIdParam = request.getParameter("newsId");

        if (newsIdParam == null || newsIdParam.isEmpty()) {
            response.sendRedirect("Controller?command=go_to_index_page&error=Missing+news+ID");
            return;
        }

        int newsId;

        try {
            newsId = Integer.parseInt(newsIdParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("Controller?command=go_to_index_page&error=Invalid+news+ID");
            return;
        }

        try {
            boolean isDeleted = newsService.deleteNewsFromDatabase(newsId);

            if (isDeleted) {
                response.sendRedirect("Controller?command=go_to_index_page&message=News+deleted+successfully");
            } else {
                response.sendRedirect("Controller?command=go_to_news_page&newsId=" + newsId + "&error=Failed+to+delete+news");
            }
        } catch (ServiceException e) {
            response.sendRedirect("Controller?command=go_to_news_page&newsId=" + newsId + "&error=Server+error");
        }
    }
}

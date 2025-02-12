package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.bean.News;
import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.INewsService;
import by.asonau.web_project.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class GoToNewsByCategoryPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        INewsService newsService = ServiceProvider.getInstance().getNewsService();

        String categoryId = request.getParameter("categoryId");
        System.out.println("Category ID: " + categoryId);

        List<News> newsListByCategory = null;
        try {
            newsListByCategory = newsService.getNewsListByCategoryId(Integer.parseInt(categoryId));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Controller?command=go_to_error_page&errorMessage=Server+error");
        }

        if (newsListByCategory != null) {
            request.setAttribute("newsListByCategory", newsListByCategory);
        } else {
            request.setAttribute("errorMessage", "Ошибка при получении новостей данной категории.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/news-by-category-page.jsp");
        dispatcher.forward(request, response);
    }
}
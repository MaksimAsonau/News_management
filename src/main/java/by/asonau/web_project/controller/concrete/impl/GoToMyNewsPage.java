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

public class GoToMyNewsPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Получаем роль пользователя из сессии
        String userRole = (String) request.getSession().getAttribute("role");

        // Проверяем роль пользователя
        if (userRole == null || (!userRole.equals("admin") && !userRole.equals("author"))) {
            // Перенаправляем на главную с сообщением о недостатке прав
            response.sendRedirect("Controller?command=go_to_index_page&errorMessage=Access+denied");
            return;
        }

        // Получаем ID пользователя из сессии
        Integer userId = (Integer) request.getSession().getAttribute("id");

        if (userId == null) {
            // Если ID отсутствует, перенаправляем на страницу авторизации
            response.sendRedirect("Controller?command=go_to_authentication_page&authError=Not+authorized");
            return;
        }

        try {
            // Получаем список новостей пользователя
            INewsService newsService = ServiceProvider.getInstance().getNewsService();
            List<News> myNews = newsService.getAuthorNewsList(userId);

            // Устанавливаем атрибуты для отображения на странице
            request.setAttribute("newsListByCategory", myNews);
            request.setAttribute("activeTab", "myNews");

            // Форвард на страницу новостей
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/news-by-category-page.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // Логируем ошибку и перенаправляем на страницу ошибки
            e.printStackTrace();
            response.sendRedirect("Controller?command=go_to_error_page");
        }
    }
}


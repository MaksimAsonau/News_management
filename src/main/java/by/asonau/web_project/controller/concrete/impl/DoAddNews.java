package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.bean.News;
import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.INewsService;
import by.asonau.web_project.service.ServiceException;
import by.asonau.web_project.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

public class DoAddNews implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем сессию без создания
        var session = request.getSession(false);

        // Проверяем, есть ли сессия и атрибут role
        if (session == null || session.getAttribute("role") == null) {
            // Если нет сессии или роли, перенаправляем на главную
            response.sendRedirect("Controller?command=go_to_index_page&errorMessage=Access+denied");
            return;
        }

        // Получаем роль пользователя
        String userRole = (String) session.getAttribute("role");

        // Проверяем роль
        if (!userRole.equalsIgnoreCase("admin") && !userRole.equalsIgnoreCase("author")) {
            // Если роль неподходящая, редиректим на главную
            response.sendRedirect("Controller?command=go_to_index_page&errorMessage=Access+denied");
            return;
        }

        // Если проверка роли пройдена, продолжаем обработку добавления новости
        try {
            String title = request.getParameter("title");
            String brief = request.getParameter("brief");
            String content = request.getParameter("content");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int idOfAuthor = (int) session.getAttribute("id"); // ID автора из сессии
            LocalDate publishDate = LocalDate.now();

            // Создаем объект News
            News news = new News(title, brief, content, publishDate, categoryId, idOfAuthor);

            // Вызываем метод сервиса для добавления новости
            INewsService newsService = ServiceProvider.getInstance().getNewsService();
            int newsId = newsService.addNewsToDatabase(news);

            if (newsId > 0) {
                // Успешное добавление, редирект на страницу новости
                response.sendRedirect("Controller?command=go_to_news_page&newsId=" + newsId
                        + "&message=News+added+successfully");
            } else {
                // Если новость не добавлена, редирект на страницу ошибки
                response.sendRedirect("Controller?command=go_to_error_page&errorMessage=Failed+to+add+news");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect("Controller?command=go_to_error_page&errorMessage=Failed+to+add+news");
        }
    }
}

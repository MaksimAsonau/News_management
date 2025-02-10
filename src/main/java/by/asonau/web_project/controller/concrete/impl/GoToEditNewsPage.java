package by.asonau.web_project.controller.concrete.impl;

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

import java.io.IOException;

public class GoToEditNewsPage implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Auth auth = (Auth) request.getSession().getAttribute("auth");

        if (auth == null) {
            request.getSession().setAttribute("warningMessage", "Для редактирования новости войдите в систему.");
            response.sendRedirect("Controller?command=go_to_index_page");
            return;
        }

        String newsIdParam = request.getParameter("newsId");

        try {
            int newsId = Integer.parseInt(newsIdParam);
            News news = newsService.getNewsFromDatabaseById(newsId);

            // Проверка прав
            if (!auth.getRole().name().equalsIgnoreCase("ADMIN") && !auth.getLogin().equals(news.getLoginOfAuthor())) {
                request.getSession().setAttribute("errorMessage", "У вас нет прав на редактирование этой новости.");
                response.sendRedirect("Controller?command=go_to_news_page&newsId=" + newsId);
                return;
            }


            request.setAttribute("news", news);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/edit-news-page.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "Некорректный ID новости.");
            response.sendRedirect("Controller?command=go_to_index_page");
        } catch (ServiceException e) {
            request.getSession().setAttribute("errorMessage", "Ошибка сервера при загрузке новости.");
            response.sendRedirect("Controller?command=go_to_index_page");
        }
    }
}


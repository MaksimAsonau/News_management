package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.bean.Auth;
import by.asonau.web_project.bean.News;
import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.INewsService;
import by.asonau.web_project.service.ServiceException;
import by.asonau.web_project.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoEditNews implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Auth auth = (Auth) request.getSession().getAttribute("auth");
        if (auth == null) {
            request.getSession().setAttribute("warningMessage", "Для редактирования новости войдите в систему.");
            response.sendRedirect("Controller?command=go_to_index_page");
            return;
        }

        try {
            int newsId = Integer.parseInt(request.getParameter("newsId"));
            String title = request.getParameter("title");
            String brief = request.getParameter("brief");
            String content = request.getParameter("content");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            News existingNews = newsService.getNewsFromDatabaseById(newsId);

            if (!auth.getRole().name().equalsIgnoreCase("ADMIN") &&
                    !auth.getLogin().equals(existingNews.getLoginOfAuthor())) {
                request.getSession().setAttribute("errorMessage", "У вас нет прав на редактирование этой новости.");
                response.sendRedirect("Controller?command=go_to_news_page&newsId=" + newsId);
                return;
            }

            News updatedNews = new News(newsId, title, brief, content, categoryId);
            boolean isUpdated = newsService.updateNewsInDataBase(updatedNews);

            if (isUpdated) {
                response.sendRedirect("Controller?command=go_to_news_page&newsId=" + newsId + "&message=News+updated+successfully");
            } else {
                response.sendRedirect("Controller?command=go_to_edit_news_page&newsId=" + newsId + "&error=Failed+to+update+news");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("Controller?command=go_to_index_page&error=Invalid+news+ID+or+category+ID");
        } catch (ServiceException e) {
            response.sendRedirect("Controller?command=go_to_index_page&error=Server+error");
        }
    }
}

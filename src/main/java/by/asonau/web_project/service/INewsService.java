package by.asonau.web_project.service;

import by.asonau.web_project.bean.News;
import by.asonau.web_project.bean.User;

import java.util.List;

public interface INewsService {

    int addNewsToDatabase(News news) throws ServiceException;

    boolean deleteNewsFromDatabase(int newsId) throws ServiceException;

    News getNewsFromDatabaseById(int newsId) throws ServiceException;

    List<News> getNewsList() throws ServiceException;

    List<News> getAuthorNewsList(int authorId) throws ServiceException;

    List<News> getNewsListByCategoryId(int newsId) throws ServiceException;

    boolean updateNewsInDataBase(News news) throws ServiceException;
}

package by.asonau.web_project.service.impl;

import by.asonau.web_project.bean.News;
import by.asonau.web_project.dao.DAOException;
import by.asonau.web_project.dao.DAOProvider;
import by.asonau.web_project.dao.INewsDAO;
import by.asonau.web_project.service.INewsService;
import by.asonau.web_project.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class NewsServiceImpl implements INewsService {

    private final INewsDAO newsDAO = DAOProvider.getInstance().getNewsDAO();

    public NewsServiceImpl () {
    }

    @Override
    public News getNewsFromDatabaseById(int newsId) throws ServiceException {
        try {
            return newsDAO.getNewsById(newsId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> getNewsList() throws ServiceException {
        try {
            return newsDAO.getAllNews();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> getAuthorNewsList(int authorId) throws ServiceException {
        try {
            return newsDAO.getNewsByUserId(authorId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> getNewsListByCategoryId(int newsId) throws ServiceException {
        try {
            return newsDAO.getNewsByCategoryId(newsId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int addNewsToDatabase(News news) throws ServiceException {
        try {
            return newsDAO.addNews(news);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

//    @Override
//    public boolean deleteNewsFromDatabase(int newsId) throws ServiceException {
//        return false;
//    }

//    @Override
//    public boolean changeFieldData(int newsId, News news) throws ServiceException {
//        return false;
//    }
}

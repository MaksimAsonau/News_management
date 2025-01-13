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

    private List<News> regularNewsList = new ArrayList<>();
    private List<News> topNewsList = new ArrayList<>();
    private List<News> breakingNewsList = new ArrayList<>();

    private final DAOProvider daoProvider;
    private final INewsDAO newsTool;

    public NewsServiceImpl() throws ServiceException {
        try {
            daoProvider = DAOProvider.getInstance();
            newsTool = daoProvider.getNewsDAO();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

//
//    @Override
//    public int addNewsToDatabase(News news) throws ServiceException {
//        return 0;
//    }
//
//    @Override
//    public boolean deleteNewsFromDatabase(int newsId) throws ServiceException {
//        return false;
//    }
//
    @Override
    public News getNewsFromDatabaseById(int newsId) throws ServiceException {
        try {
            return newsTool.getNewsById(newsId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> getNewsList() throws ServiceException {
        try {
            return newsTool.getAllNews();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
//
//    @Override
//    public List<News> getAuthorNewsList(int authorId) throws ServiceException {
//        return List.of();
//    }
//
//    @Override
//    public List<User> getAuthorByNewsId(int newsId) throws ServiceException {
//        return List.of();
//    }
//
//    @Override
//    public boolean changeFieldData(int newsId, News news) throws ServiceException {
//        return false;
//    }
//
//    @Override
//    public boolean addAuthorToNews(int newsId, int authId) throws ServiceException {
//        return false;
//    }
//
//    @Override
//    public boolean addCoauthorToNews(int coauthorId, int newsId) throws ServiceException {
//        return false;
//    }
}

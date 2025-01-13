package by.asonau.web_project.dao;

import by.asonau.web_project.bean.News;

import java.util.List;

public interface INewsDAO {

    int addNews(News news) throws DAOException;

    boolean deleteNews(int newsId) throws DAOException;

    List<News> getAllNews() throws DAOException;

    News getNewsById(int id) throws DAOException;




}

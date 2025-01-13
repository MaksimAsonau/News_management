package by.asonau.web_project.dao;

import by.asonau.web_project.bean.NewsCategory;

public interface INewsCategoryDAO {

    void addCategory(NewsCategory category) throws DAOException;
    void changeCategory(NewsCategory category) throws DAOException;
    boolean deleteCategory(int newsCategoryId) throws DAOException;
}

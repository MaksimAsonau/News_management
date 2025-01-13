package by.asonau.web_project.dao.impl;

import by.asonau.web_project.bean.NewsCategory;
import by.asonau.web_project.dao.DAOException;
import by.asonau.web_project.dao.INewsCategoryDAO;

public class NewsCategoryDAOImpl implements INewsCategoryDAO {
    public void addCategory(NewsCategory category) throws DAOException {
        System.out.println(category);
    }

    public void changeCategory(NewsCategory category) throws DAOException {
        System.out.println(category);
    }

    public boolean deleteCategory(int newsCategoryId) throws DAOException {
        return true;
    }
}

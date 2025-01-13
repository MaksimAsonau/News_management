package by.asonau.web_project.dao;

import by.asonau.web_project.dao.impl.NewsCategoryDAOImpl;
import by.asonau.web_project.dao.impl.NewsDAOImpl;
import by.asonau.web_project.dao.impl.UserDAOImpl;

public class DAOProvider {

    private static DAOProvider instance;

    // Экземпляры DAO объектов, инициализируются по мере необходимости

    private IUserDAO sqlUserImpl;
    private INewsCategoryDAO sqlNewsCategoryImpl;
    private INewsDAO sqlNewsImpl;

    private DAOProvider() {
    }

    // Метод для получения синглтон-экземпляра DAOFactory
    public static DAOProvider getInstance() {
        if (instance == null) {
            instance = new DAOProvider();
        }
        return instance;
    }

    // Методы для получения DAO объектов
    public IUserDAO getUserDAO() throws DAOException {
        if (sqlUserImpl == null) {
            sqlUserImpl = new UserDAOImpl();
        }
        return sqlUserImpl;
    }

    public INewsCategoryDAO getNewsCategoryDAO() throws DAOException {
        if (sqlNewsCategoryImpl == null) {
            sqlNewsCategoryImpl = new NewsCategoryDAOImpl();
        }
        return sqlNewsCategoryImpl;
    }

    public INewsDAO getNewsDAO() throws DAOException {
        if (sqlNewsImpl == null) {
            sqlNewsImpl = new NewsDAOImpl();
        }
        return sqlNewsImpl;
    }
}

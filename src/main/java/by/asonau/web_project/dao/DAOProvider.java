package by.asonau.web_project.dao;

import by.asonau.web_project.dao.impl.NewsDAOImpl;
import by.asonau.web_project.dao.impl.UserDAOImpl;

public class DAOProvider {

    private static DAOProvider instance;

    private IUserDAO sqlUserImpl;
    private INewsDAO sqlNewsImpl;

    private DAOProvider() {
    }

    public static DAOProvider getInstance() {
        if (instance == null) {
            instance = new DAOProvider();
        }
        return instance;
    }

    public IUserDAO getUserDAO() throws DAOException {
        if (sqlUserImpl == null) {
            sqlUserImpl = new UserDAOImpl();
        }
        return sqlUserImpl;
    }

    public INewsDAO getNewsDAO() throws DAOException {
        if (sqlNewsImpl == null) {
            sqlNewsImpl = new NewsDAOImpl();
        }
        return sqlNewsImpl;
    }
}

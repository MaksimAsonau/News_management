package by.asonau.web_project.service.impl;

import by.asonau.web_project.bean.User;
import by.asonau.web_project.bean.UserRole;
import by.asonau.web_project.dao.DAOException;
import by.asonau.web_project.dao.DAOProvider;
import by.asonau.web_project.dao.IUserDAO;
import by.asonau.web_project.service.IUserService;
import by.asonau.web_project.service.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO = DAOProvider.getInstance().getUserDAO();

    // Возвращает юзера для личного кабинета
    @Override
    public User getUserInfoById(int id) throws ServiceException {
        try {
            return userDAO.getUserInfoById(id); // Может вернуть null
        } catch (DAOException e) {
            throw new ServiceException("Ошибка при авторизации пользователя", e);
        }
    }

    @Override
    public User checkAuth(String login, String password) throws ServiceException {
        try {
            return userDAO.logIn(login, password); // Может вернуть null
        } catch (DAOException e) {
            throw new ServiceException("Ошибка при авторизации пользователя", e);
        }
    }

    @Override
    public int checkUserReg(String name, String email, String password) throws ServiceException {
        try {
            return userDAO.registerUserInDatabase(name, email, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkLoginExistsInDB(HttpServletRequest request, String login) throws ServiceException {
        try {
            return userDAO.doesLoginExistInDB(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getRoleId(UserRole role) throws ServiceException {
        return 0;
    }
}

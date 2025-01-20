package by.asonau.web_project.service.impl;

import by.asonau.web_project.bean.User;
import by.asonau.web_project.bean.UserRole;
import by.asonau.web_project.dao.DAOException;
import by.asonau.web_project.dao.DAOProvider;
import by.asonau.web_project.dao.IUserDAO;
import by.asonau.web_project.service.IUserService;
import by.asonau.web_project.service.ServiceException;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO = DAOProvider.getInstance().getUserDAO();

    // Возвращает юзера для личного кабинета
    @Override
    public User getUserInfoById(int id) throws ServiceException {
        try {
            return userDAO.getUserInfoById(id); // Может вернуть null
        } catch (DAOException e) {
            throw new ServiceException("Ошибка на уровне DAO при получении информации о пользователе", e);
        }
    }

    @Override
    public User checkAuth(String login, String password) throws ServiceException {
        try {
            return userDAO.logIn(login, password); // Может вернуть null
        } catch (DAOException e) {
            throw new ServiceException("Ошибка на уровне DAO при авторизации пользователя", e);
        }
    }

    @Override
    public int checkUserReg(User user) throws ServiceException {
        try {
            return userDAO.registerUserInDatabase(user);
        } catch (DAOException e) {
            throw new ServiceException("Ошибка на уровне DAO при регистрации пользователя.", e);
        }
    }

    @Override
    public boolean checkLoginExistsInDB(String login) throws ServiceException {
        try {
            return userDAO.doesLoginExistInDB(login);
        } catch (DAOException e) {
            throw new ServiceException("Ошибка на уровне DAO при проверке уникальности логина.", e);
        }
    }

    @Override
    public boolean checkEmailExistsInDB(String login) throws ServiceException {
        try {
            return userDAO.doesEmailExistInDB(login);
        } catch (DAOException e) {
            throw new ServiceException("Ошибка на уровне DAO при проверке уникальности почты.", e);
        }
    }

    @Override
    public int getRoleId(UserRole role) throws ServiceException {
        return 0;
    }
}

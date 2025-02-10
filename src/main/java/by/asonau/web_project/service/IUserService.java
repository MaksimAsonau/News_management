package by.asonau.web_project.service;

import by.asonau.web_project.bean.Auth;
import by.asonau.web_project.bean.User;
import by.asonau.web_project.bean.UserRole;
import jakarta.servlet.http.HttpServletRequest;

public interface IUserService {

    Auth checkAuth (String login, String password) throws ServiceException;

    User getUserInfoById (int id) throws ServiceException;

    User getUserInfoForEdit (int id) throws ServiceException;

    int checkUserReg(User user) throws ServiceException;

    boolean checkLoginExistsInDB(String login) throws ServiceException;

    boolean checkEmailExistsInDB(String login) throws ServiceException;

    boolean updateUserInDatabase(User user) throws ServiceException;

}

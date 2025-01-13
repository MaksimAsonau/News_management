package by.asonau.web_project.service;

import by.asonau.web_project.bean.User;
import by.asonau.web_project.bean.UserRole;
import jakarta.servlet.http.HttpServletRequest;

public interface IUserService {

    User checkAuth (String login, String password) throws ServiceException;

    User getUserInfoById (int id) throws ServiceException;

    int checkUserReg(String name, String login, String password) throws ServiceException;

    boolean checkLoginExistsInDB(HttpServletRequest request, String login) throws ServiceException;

    int getRoleId (UserRole role) throws ServiceException;
}

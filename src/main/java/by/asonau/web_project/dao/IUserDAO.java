package by.asonau.web_project.dao;

import by.asonau.web_project.bean.Auth;
import by.asonau.web_project.bean.User;

public interface IUserDAO {

    Auth logIn(String login, String password) throws DAOException;

    boolean doesLoginExistInDB(String login) throws DAOException;

    boolean doesEmailExistInDB(String email) throws DAOException;

    User getUserInfoById(int id) throws DAOException;

    User getUserInfoForEdit(int id) throws DAOException;

    int registerUserInDatabase(User user)  throws DAOException;

    boolean updateUser(User user) throws DAOException;
}

package by.asonau.web_project.dao.impl;

import by.asonau.web_project.bean.User;
import by.asonau.web_project.bean.UserRole;
import by.asonau.web_project.dao.DAOException;
import by.asonau.web_project.dao.IUserDAO;
import by.asonau.web_project.dao.dbmanager.ConnectionPool;
import by.asonau.web_project.dao.dbmanager.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserDAOImpl implements IUserDAO {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Метод авторизации
     *
     * @param login     передан по цепочке "форма авторизации -> слой сервисов"
     * @param password  передан по цепочке "форма авторизации -> слой сервисов"
     * @return
     * @throws DAOException
     */
    @Override
    public User logIn(String login, String password) throws DAOException {

        String query = "SELECT u.id, u.login, r.name AS role_name " +
                "FROM news_management_v20.user u " +
                "JOIN news_management_v20.role r ON u.role_id = r.id " +
                "WHERE u.login = ? AND u.password = ?";

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String user_login = resultSet.getString("login");
                    String roleName = resultSet.getString("role_name");
                    UserRole userRole = UserRole.valueOf(roleName.toUpperCase());
                    return new User(id, user_login, userRole);
                } else {
                    return null; // Возвращаем null, если пользователь не найден
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Ошибка при выполнении авторизации", e);
        }
    }

    @Override
    public boolean doesLoginExistInDB(String login) throws DAOException {
        String query = "SELECT 1 FROM news_management_v20.user WHERE login = ?";
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public User getUserInfoById(int id) {
        String query = """
        SELECT u.id, u.login, r.name AS role_name, u.name AS first_name, u.surname, u.email,
               r.image_path AS role_image, ud.date_birthday, ud.date_registration, ud.address
        FROM user u
        JOIN role r ON u.role_id = r.id
        LEFT JOIN user_details ud ON u.id = ud.user_id
        WHERE u.id = ?
    """;

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Устанавливаем значение ID
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Извлекаем данные из ResultSet
                    int userId = resultSet.getInt("id");
                    String login = resultSet.getString("login");
                    String roleName = resultSet.getString("role_name");
                    UserRole userRole = UserRole.valueOf(roleName.toUpperCase());
                    String name = resultSet.getString("first_name");
                    String surname = resultSet.getString("surname");
                    String email = resultSet.getString("email");
                    String roleImage = resultSet.getString("role_image");
                    LocalDate birthdayDate = resultSet.getDate("date_birthday") != null
                            ? resultSet.getDate("date_birthday").toLocalDate() : null;
                    LocalDate registrationDate = resultSet.getDate("date_registration") != null
                            ? resultSet.getDate("date_registration").toLocalDate() : null;
                    String address = resultSet.getString("address");

                    // Создаем и возвращаем объект User
                    return new User(userId, login, userRole, name, surname, email, roleName, roleImage,
                            birthdayDate, registrationDate, address);
                } else {
                    // Если пользователь не найден, выбрасываем исключение
                    throw new DAOException("User with ID " + id + " not found.");
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error retrieving user info by ID: " + id, e);
        }
    }

    @Override
    public int registerUserInDatabase(String name, String login, String password) throws DAOException {
        return 0;
    }
}

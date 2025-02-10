package by.asonau.web_project.dao.impl;

import by.asonau.web_project.bean.Auth;
import by.asonau.web_project.bean.User;
import by.asonau.web_project.bean.UserRole;
import by.asonau.web_project.dao.DAOException;
import by.asonau.web_project.dao.IUserDAO;
import by.asonau.web_project.dao.dbmanager.ConnectionPool;
import by.asonau.web_project.dao.dbmanager.ConnectionPoolException;

import java.sql.*;
import java.time.LocalDate;

public class UserDAOImpl implements IUserDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String QUERY_LOG_IN = "SELECT u.id, u.login, r.name AS role_name " +
            "FROM news_management_v20.user u " +
            "JOIN news_management_v20.role r ON u.role_id = r.id " +
            "WHERE u.login = ? AND u.password = ?";

    @Override
    public Auth logIn(String login, String password) throws DAOException {

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LOG_IN)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String user_login = resultSet.getString("login");
                    String roleName = resultSet.getString("role_name");
                    UserRole userRole = UserRole.valueOf(roleName.toUpperCase());
                    return new Auth(id, user_login, userRole);
                } else {
                    return null; // Возвращаем null, если пользователь не найден
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Ошибка при выполнении авторизации", e);
        }
    }

    private static final String QUERY_DOES_LOGIN_EXIST_IN_DB =
            "SELECT 1 FROM news_management_v20.user WHERE login = ?";

    @Override
    public boolean doesLoginExistInDB(String login) throws DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DOES_LOGIN_EXIST_IN_DB)) {
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Если есть хотя бы одна запись, возвращаем true
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Ошибка при проверке логина в базе данных.", e);
        }
    }

    private static final String QUERY_DOES_EMAIL_EXIST_IN_DB =
            "SELECT 1 FROM news_management_v20.user WHERE email = ?";

    @Override
    public boolean doesEmailExistInDB(String email) throws DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DOES_EMAIL_EXIST_IN_DB)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Если есть хотя бы одна запись, возвращаем true
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Ошибка при проверке почты в базе данных.", e);
        }
    }

    private static final String QUERY_GET_USER_INFO_BY_ID =
            "SELECT u.id, u.login, r.name AS role_name, u.name AS first_name, u.surname, u.email,\n" +
            "               r.image_path AS role_image, ud.date_birthday, ud.date_registration, ud.address\n" +
            "        FROM user u\n" +
            "        JOIN role r ON u.role_id = r.id\n" +
            "        LEFT JOIN user_details ud ON u.id = ud.user_id\n" +
            "        WHERE u.id = ?";

    @Override
    public User getUserInfoById(int id) {

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_USER_INFO_BY_ID)) {

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
                    return new User(userId, login, userRole, name, surname, email, roleImage,
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

    private static final String QUERY_INSERT_USER =
            "INSERT INTO news_management_v20.user (login, password, name, surname, email, role_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String QUERY_INSERT_USER_DETAILS =
            "INSERT INTO news_management_v20.user_details (user_id, date_birthday, date_registration, address) VALUES (?, ?, ?, ?)";

    @Override
    public int registerUserInDatabase(User user) throws DAOException {
        try (Connection connection = connectionPool.takeConnection()) {
            connection.setAutoCommit(false); // Начинаем транзакцию

            try (PreparedStatement userStatement = connection.prepareStatement(QUERY_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement detailsStatement = connection.prepareStatement(QUERY_INSERT_USER_DETAILS)) {

                // Вставка в таблицу user
                userStatement.setString(1, user.getLogin());
                userStatement.setString(2, user.getPassword());
                userStatement.setString(3, user.getName());
                userStatement.setString(4, user.getSurname());
                userStatement.setString(5, user.getEmail());
                userStatement.setInt(6, user.getUserRole().ordinal() + 1); // Преобразуем роль в ID

                userStatement.executeUpdate();

                // Получаем сгенерированный ID пользователя
                try (ResultSet generatedKeys = userStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);

                        // Вставка в таблицу user_details
                        detailsStatement.setInt(1, userId);
                        detailsStatement.setDate(2, Date.valueOf(user.getBirthdayDate()));
                        detailsStatement.setDate(3, Date.valueOf(user.getRegistrationDate()));
                        detailsStatement.setString(4, user.getAddress());

                        detailsStatement.executeUpdate();
                    } else {
                        connection.rollback();
                        throw new DAOException("Не удалось получить ID нового пользователя.");
                    }
                }

                connection.commit();
                return 1; // Регистрация успешна

            } catch (SQLException e) {
                connection.rollback();
                throw new DAOException("Ошибка при регистрации пользователя.", e);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Ошибка подключения к базе данных.", e);
        }
    }

    private static final String QUERY_GET_USER_INFO_FOR_EDIT =
            "SELECT u.id, u.login, u.password, u.name AS first_name, u.surname, u.email, " +
                    "ud.date_birthday, ud.address " +
                    "FROM user u " +
                    "LEFT JOIN user_details ud ON u.id = ud.user_id " +
                    "WHERE u.id = ?";

    @Override
    public User getUserInfoForEdit(int id) {

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_USER_INFO_FOR_EDIT)) {

            // Устанавливаем параметр ID
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Извлекаем данные из ResultSet
                    int userId = resultSet.getInt("id");
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password"); // Теперь включен пароль
                    String name = resultSet.getString("first_name");
                    String surname = resultSet.getString("surname");
                    String email = resultSet.getString("email");
                    LocalDate birthdayDate = resultSet.getDate("date_birthday") != null
                            ? resultSet.getDate("date_birthday").toLocalDate() : null;
                    String address = resultSet.getString("address");

                    // Создаем объект User (без роли)
                    return new User(userId, login, password, name, surname, email, birthdayDate, address);
                } else {
                    throw new DAOException("User with ID " + id + " not found.");
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error retrieving user info for editing by ID: " + id, e);
        }
    }

    private static final String QUERY_UPDATE_USER =
            "UPDATE user SET login = ?, name = ?, surname = ?, email = ? WHERE id = ?";
    private static final String QUERY_UPDATE_USER_DETAILS =
            "UPDATE user_details SET date_birthday = ?, address = ? WHERE user_id = ?";

    @Override
    public boolean updateUser(User user) throws DAOException {
        try (Connection connection = connectionPool.takeConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement updateUserStmt = connection.prepareStatement(QUERY_UPDATE_USER);
                 PreparedStatement updateUserDetailsStmt = connection.prepareStatement(QUERY_UPDATE_USER_DETAILS)) {

                // Обновляем данные в таблице user
                updateUserStmt.setString(1, user.getLogin());
                updateUserStmt.setString(2, user.getName());
                updateUserStmt.setString(3, user.getSurname());
                updateUserStmt.setString(4, user.getEmail());
                updateUserStmt.setInt(5, user.getId());

                int affectedRowsUser = updateUserStmt.executeUpdate();

                // Обновляем данные в таблице user_details
                updateUserDetailsStmt.setObject(1, user.getBirthdayDate() != null ? user.getBirthdayDate() : null);
                updateUserDetailsStmt.setString(2, user.getAddress());
                updateUserDetailsStmt.setInt(3, user.getId());

                int affectedRowsDetails = updateUserDetailsStmt.executeUpdate();

                // Фиксируем изменения
                connection.commit();
                return affectedRowsUser > 0 || affectedRowsDetails > 0;

            } catch (SQLException e) {
                connection.rollback();
                throw new DAOException("Ошибка при обновлении профиля пользователя", e);
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Ошибка соединения при обновлении профиля", e);
        }
    }


}

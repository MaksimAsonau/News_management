package by.asonau.web_project.dao.impl;

import by.asonau.web_project.bean.News;
import by.asonau.web_project.dao.DAOException;
import by.asonau.web_project.dao.INewsDAO;
import by.asonau.web_project.dao.dbmanager.ConnectionPool;
import by.asonau.web_project.dao.dbmanager.ConnectionPoolException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewsDAOImpl implements INewsDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();


    private static final String QUERY_DELETE_NEWS = "DELETE FROM news WHERE id = ?";

    @Override
    public boolean deleteNews(int newsId) throws DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE_NEWS)) {

            preparedStatement.setInt(1, newsId);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Ошибка при удалении новости с ID: " + newsId, e);
        }
    }

    private static final String QUERY_ADD_NEWS =
            "INSERT INTO news (title, brief, content, date_publication, category_id, user_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public int addNews(News news) throws DAOException {

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_NEWS, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getBrief());
            preparedStatement.setString(3, news.getContent());
            preparedStatement.setDate(4, Date.valueOf(news.getPublishDate()));
            preparedStatement.setInt(5, news.getCategoryId());
            preparedStatement.setInt(6, news.getIdOfAuthor());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Adding news failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new DAOException("Adding news failed, no ID obtained.");
                }
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error while adding news", e);
        }
    }

    private static final String QUERY_GET_ALL_NEWS =
            "SELECT n.id, n.title, n.brief, c.image_path " +
                    "FROM news_management_v20.news n " +
                    "JOIN news_management_v20.category c ON n.category_id = c.id " +
                    "ORDER BY n.date_publication DESC";

    @Override
    public List<News> getAllNews() throws DAOException {

        List<News> newsList = new ArrayList<>();

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_ALL_NEWS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String brief = resultSet.getString("brief");
                String imageUrl = resultSet.getString("image_path");

                News news = new News(id, imageUrl, brief, title);
                newsList.add(news);
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Failed to fetch news for homepage", e);
        }
        return newsList;
    }

    private static final String QUERY_GET_NEWS_BY_ID =
            "SELECT n.id AS news_id, n.title, n.brief, n.content, n.date_publication, " +
            "       c.name AS category_name, " +
            "       c.image_path AS category_image, " +
            "       u.id AS author_id, u.login AS author_login " +
            "FROM news_management_v20.news n " +
            "JOIN news_management_v20.category c ON n.category_id = c.id " +
            "JOIN news_management_v20.user u ON n.user_id = u.id " +
            "WHERE n.id = ?";

    @Override
    public News getNewsById(int id) throws DAOException {

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_NEWS_BY_ID)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Извлекаем данные из ResultSet
                    int newsId = resultSet.getInt("news_id");
                    String title = resultSet.getString("title");
                    String brief = resultSet.getString("brief");
                    String content = resultSet.getString("content");
                    LocalDate publishDate = resultSet.getDate("date_publication").toLocalDate();
                    String categoryName = resultSet.getString("category_name");
                    String imageUrl = resultSet.getString("category_image");
                    String authorLogin = resultSet.getString("author_login");

                    return new News(newsId, title, brief, content, imageUrl, publishDate, categoryName, authorLogin);
                } else {
                    return null;
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error retrieving news by ID: " + id, e);
        }
    }

    private static final String QUERY_GET_NEWS_BY_USER_ID =
            "SELECT n.id AS news_id, n.title, n.brief, c.image_path " +
                    "FROM news n " +
                    "JOIN category c ON n.category_id = c.id " +
                    "WHERE n.user_id = ? " +
                    "ORDER BY n.date_publication DESC";

    @Override
    public List<News> getNewsByUserId(int userId) throws DAOException {
        List<News> newsList = new ArrayList<>();

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_NEWS_BY_USER_ID)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("news_id");
                    String title = resultSet.getString("title");
                    String brief = resultSet.getString("brief");
                    String imageUrl = resultSet.getString("image_path");

                    News news = new News(id, imageUrl, brief, title);
                    newsList.add(news);
                }
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Failed to fetch news for user with ID: " + userId, e);
        }
        return newsList;
    }

    private static final String QUERY_GET_NEWS_BY_CATEGORY_ID =
            "SELECT n.id, n.title, n.brief, c.image_path " +
                    "FROM news_management_v20.news n " +
                    "JOIN news_management_v20.category c ON n.category_id = c.id " +
                    "WHERE c.id = ? " +
                    "ORDER BY n.date_publication DESC";

    @Override
    public List<News> getNewsByCategoryId(int categoryId) throws DAOException {
        List<News> newsList = new ArrayList<>();

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_NEWS_BY_CATEGORY_ID)) {

            preparedStatement.setInt(1, categoryId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String brief = resultSet.getString("brief");
                    String imageUrl = resultSet.getString("image_path");

                    News news = new News(id, imageUrl, brief, title);
                    newsList.add(news);
                }
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Failed to fetch news for category ID: " + categoryId, e);
        }

        return newsList;
    }

    private static final String QUERY_UPDATE_NEWS =
            "UPDATE news SET title = ?, brief = ?, content = ?, category_id = ? WHERE id = ?";

    @Override
    public boolean updateNews(News news) throws DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_NEWS)) {

            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getBrief());
            preparedStatement.setString(3, news.getContent());
            preparedStatement.setInt(4, news.getCategoryId());
            preparedStatement.setInt(5, news.getNewsId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0; // Возвращает true, если хотя бы одна строка была обновлена

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Ошибка при обновлении новости с ID: " + news.getNewsId(), e);
        }
    }

}

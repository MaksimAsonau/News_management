package by.asonau.web_project.dao.impl;

import by.asonau.web_project.bean.News;
import by.asonau.web_project.dao.DAOException;
import by.asonau.web_project.dao.INewsDAO;
import by.asonau.web_project.dao.dbmanager.ConnectionPool;
import by.asonau.web_project.dao.dbmanager.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewsDAOImpl implements INewsDAO {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public int addNews(News news) throws DAOException {
        return 0;
    }

    @Override
    public boolean deleteNews(int newsId) throws DAOException {
        return false;
    }

    @Override
    public List<News> getAllNews() throws DAOException {

        String query = "SELECT n.id, n.title, n.brief, c.image_path " +
                "FROM news_management_v20.news n " +
                "JOIN news_management_v20.category c ON n.category_id = c.id " +
                "ORDER BY n.date_publication DESC";

        List<News> newsList = new ArrayList<>();

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String brief = resultSet.getString("brief");
                String imageUrl = resultSet.getString("image_path");

//                 Отладочный вывод
//                System.out.println("News ID: " + id + ", Image Path: " + imageUrl);

                // Используем упрощенный конструктор News
                News news = new News(id, imageUrl, brief, title);
                newsList.add(news);
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Failed to fetch news for homepage", e);
        }
        return newsList;
    }

    @Override
    public News getNewsById(int id) throws DAOException {
        String query = "SELECT n.id AS news_id, n.title, n.brief, n.content, n.date_publication, " +
                "       c.name AS category_name, " + // Извлекаем имя категории вместо ID
                "       c.image_path AS category_image, " +
                "       u.id AS author_id, u.login AS author_login " +
                "FROM news_management_v20.news n " +
                "JOIN news_management_v20.category c ON n.category_id = c.id " +
                "JOIN news_management_v20.user u ON n.user_id = u.id " +
                "WHERE n.id = ?";


        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id); // Устанавливаем ID новости

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

//                    // Отладочный вывод
//                    System.out.println("newsId: " + newsId);
//                    System.out.println("title: " + title);
//                    System.out.println("brief: " + brief);
//                    System.out.println("content: " + content);
//                    System.out.println("publishDate: " + publishDate);
//                    System.out.println("categoryName: " + categoryName);
//                    System.out.println("imageUrl: " + imageUrl);
//                    System.out.println("authorLogin: " + authorLogin);

                    // Возвращаем объект News
                    return new News(newsId, title, brief, content, imageUrl, publishDate, categoryName, authorLogin);
                } else {
                    return null; // Если новость не найдена
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error retrieving news by ID: " + id, e);
        }
    }
}

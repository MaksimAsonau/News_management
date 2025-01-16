package by.asonau.web_project.dao.impl;

import by.asonau.web_project.bean.User;
import by.asonau.web_project.dao.dbmanager.ConnectionPool;
import by.asonau.web_project.dao.dbmanager.ConnectionPoolException;
//import by.asonau.web_project.dao.IDatabaseConnectionDAO;

public class UserDAOTest2 {

    public static void main(String[] args) throws ConnectionPoolException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.initPoolData();

        NewsDAOImpl newsDAO = new NewsDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();

        User user = userDAO.getUserInfoById(1);
        System.out.println(user);



//        UserDAOImpl check = new UserDAOImpl();
//        boolean svetikCheck = check.doesLoginExistInDB("svetik");
//        System.out.println(svetikCheck);
//
//        User user1 = check.logIn("svetik", "dada");
//        System.out.println(user1);
//
//        User user2 = check.logIn("vano", "asd");
//        System.out.println(user2);
//
//        User user3 = check.logIn("petro", "123");
//        System.out.println(user3);


//        List<News> allNews = newsDAO.getAllNews();

//        int size = allNews.size();
//        System.out.println(size);
//        System.out.println("_______________________");
//        for (News allNew : allNews) {
//
//            System.out.println(allNew.getNewsId());
//            System.out.println(allNew.getBrief());
//            System.out.println(allNew.getTitle());
//            System.out.println(allNew.getImageUrl());
//        }

//        News news7 = newsDAO.getNewsById(7);
//        System.out.println(news7);


    }
}

package by.asonau.web_project.service;

import by.asonau.web_project.service.impl.NewsServiceImpl;
import by.asonau.web_project.service.impl.UserServiceImpl;

public final class ServiceProvider {

    // Создание единственного экземпляра при загрузке класса
    private static final ServiceProvider instance = new ServiceProvider();

    // Сервисы инициализируются сразу
    private final IUserService userService = new UserServiceImpl();
    private final INewsService newsService = new NewsServiceImpl();

    // Приватный конструктор
    private ServiceProvider() {
    }

    // Метод для получения экземпляра ServiceProvider
    public static ServiceProvider getInstance() {
        return instance;
    }

    // Возвращаем уже инициализированные сервисы
    public INewsService getNewsService() {
        return newsService;
    }

    public IUserService getUserService() {
        return userService;
    }
}

//public final class ServiceProvider {
//
//    private static ServiceProvider instance;
//
//    // Сервисы, инициализируемые при первом доступе
//
//    private IUserService userService;
//    private INewsService newsService;
//
//    // Статический блок для инициализации фабрики
//    static {
//        try {
//            instance = new ServiceProvider();
//        } catch (ServiceException e) {
//            System.err.println("Ошибка инициализации ServiceFactory: " + e.getMessage());
//            e.printStackTrace();
//            //в статическом блоке нельзя вызывать checked исключения
//            throw new ExceptionInInitializerError(e);
//        }
//    }
//
//    private ServiceProvider() throws ServiceException {
//    }
//
//    // Метод для получения экземпляра фабрики
//    public static ServiceProvider getInstance() {
//        if (instance == null) {
//            throw new IllegalStateException("ServiceFactory не был инициализирован.");
//        }
//        return instance;
//    }
//
//    // Ленивая инициализация сервисов
//    public INewsService getNewsService() throws ServiceException {
//        if (newsService == null) {
//            newsService = new NewsServiceImpl();
//        }
//        return newsService;
//    }
//
//    public IUserService getUserService() throws ServiceException {
//        if (userService == null) {
//            userService = new UserServiceImpl();
//        }
//        return userService;
//    }
//}
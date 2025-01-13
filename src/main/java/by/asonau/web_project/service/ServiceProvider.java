package by.asonau.web_project.service;

import by.asonau.web_project.service.impl.NewsServiceImpl;
import by.asonau.web_project.service.impl.UserServiceImpl;

public final class ServiceProvider {

    private static ServiceProvider instance;

    // Сервисы, инициализируемые при первом доступе

//    private ICheckService checkService;
//    private IChangeProfileService changeProfileService;

    private IUserService userService;
    private INewsService newsService;

    // Статический блок для инициализации фабрики
    static {
        try {
            instance = new ServiceProvider();
        } catch (ServiceException e) {
            System.err.println("Ошибка инициализации ServiceFactory: " + e.getMessage());
            e.printStackTrace();
            //в статическом блоке нельзя вызывать checked исключения
            throw new ExceptionInInitializerError(e);
        }
    }

    private ServiceProvider() throws ServiceException {
    }

    // Метод для получения экземпляра фабрики
    public static ServiceProvider getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ServiceFactory не был инициализирован.");
        }
        return instance;
    }

    // Ленивая инициализация сервисов
    public INewsService getNewsService() throws ServiceException {
        if (newsService == null) {
            newsService = new NewsServiceImpl();
        }
        return newsService;
    }

    public IUserService getUserService() throws ServiceException {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

//    public IChangeProfileService getChangeProfileService() throws ServiceException {
//        if (changeProfileService == null) {
//            changeProfileService = new ChangeProfileServiceImpl();
//        }
//        return changeProfileService;
//    }


//    public ICheckService getCheckService() throws ServiceException {
//        if (checkService == null) {
//            checkService = new CheckServiceImpl();
//        }
//        return checkService;
//    }
}
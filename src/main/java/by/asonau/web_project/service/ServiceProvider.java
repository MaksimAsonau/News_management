package by.asonau.web_project.service;

import by.asonau.web_project.service.impl.NewsServiceImpl;
import by.asonau.web_project.service.impl.UserServiceImpl;

public final class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private final IUserService userService = new UserServiceImpl();
    private final INewsService newsService = new NewsServiceImpl();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public INewsService getNewsService() {
        return newsService;
    }

    public IUserService getUserService() {
        return userService;
    }
}

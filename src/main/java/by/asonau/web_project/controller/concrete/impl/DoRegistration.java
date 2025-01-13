package by.asonau.web_project.controller.concrete.impl;

import java.io.IOException;

import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.IUserService;
import by.asonau.web_project.service.ServiceException;
import by.asonau.web_project.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	private final IUserService logicForRegistration = ServiceProvider.getInstance().getUserService();
//	private final ICheckService check = serviceFactory.getCheckService();

	public DoRegistration() throws ServiceException {
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String roleParam = request.getParameter("role"); // Роль из выпадающего списка
		String birthDate = request.getParameter("birthDate");

		// отладка
		System.out.println(login + "\n" + password + "\n" + name  + "\n" + surname + "\n" + email + "\n" + roleParam + "\n" + birthDate);




//		try {
//			// Получаем данные из формы
//			String login = request.getParameter("login");
//			String password = request.getParameter("password");
//			String name = request.getParameter("name");
//			String surname = request.getParameter("surname");
//			String email = request.getParameter("email");
//			String roleParam = request.getParameter("role"); // Роль из выпадающего списка
//			String birthDate = request.getParameter("birthDate");
//
//
//
//		} catch (ServiceException e) {
//			e.printStackTrace();
//			request.getSession().setAttribute("regError", "Произошла ошибка при регистрации. Попробуйте позже.");
//			redirectToRegistrationPage(request, response);
//		}
	}
}

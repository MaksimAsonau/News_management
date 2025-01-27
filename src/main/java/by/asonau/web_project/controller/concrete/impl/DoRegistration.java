package by.asonau.web_project.controller.concrete.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import by.asonau.web_project.bean.User;
import by.asonau.web_project.bean.UserRole;
import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.IUserService;
import by.asonau.web_project.service.ServiceException;
import by.asonau.web_project.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	private final IUserService userService = ServiceProvider.getInstance().getUserService();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String birthDate = request.getParameter("birthDate");
		String address = request.getParameter("address");

		try {
			// Проверка совпадения паролей
			if (!password.equals(confirmPassword)) {
				request.setAttribute("registerError", "Пароли не совпадают.");
				request.getRequestDispatcher("WEB-INF/jsp/reg-page.jsp").forward(request, response);
				return;
			}
			// Проверка логина
			if (userService.checkLoginExistsInDB(login)) {
				request.setAttribute("registerError", "Пользователь с таким логином уже существует.");
				request.getRequestDispatcher("WEB-INF/jsp/reg-page.jsp").forward(request, response);
				return;
			}
			// Проверка почты
			if (userService.checkEmailExistsInDB(email)) {
				request.setAttribute("registerError", "Пользователь с такой почтой уже существует.");
				request.getRequestDispatcher("WEB-INF/jsp/reg-page.jsp").forward(request, response);
				return;
			}

			// Создание объекта User
			UserRole userRole = UserRole.valueOf(role.toUpperCase());
			LocalDate birthLocalDate = LocalDate.parse(birthDate);
			LocalDate registrationDate = LocalDate.now();

			User newUser = new User(
					login,
					password,
					userRole,
					name,
					surname,
					email,
					birthLocalDate,
					registrationDate,
					address
			);

			// Регистрация пользователя
			int result = userService.checkUserReg(newUser);
			if (result > 0) {
				String successMessage = "Поздравляем с успешной регистрацией. Войдите в ваш аккаунт.";
				String encodedMessage = URLEncoder.encode(successMessage, StandardCharsets.UTF_8);
				response.sendRedirect("Controller?command=go_to_authentication_page&successMessage=" + encodedMessage);
			} else {
				throw new ServiceException("Не удалось зарегистрировать пользователя.");
			}

		} catch (ServiceException e) {
			request.setAttribute("registerError", "Ошибка регистрации: " + e.getMessage());
			request.getRequestDispatcher("WEB-INF/jsp/reg-page.jsp").forward(request, response);
		}
	}
}

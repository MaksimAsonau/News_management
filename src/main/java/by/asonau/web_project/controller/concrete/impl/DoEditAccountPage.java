package by.asonau.web_project.controller.concrete.impl;

import by.asonau.web_project.bean.Auth;
import by.asonau.web_project.bean.User;
import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.service.IUserService;
import by.asonau.web_project.service.ServiceException;
import by.asonau.web_project.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class DoEditAccountPage implements Command {
    private final IUserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Auth auth = (Auth) request.getSession().getAttribute("auth");

        if (auth == null) {
            request.getSession().setAttribute("warningMessage", "Войдите в систему, чтобы редактировать профиль.");
            response.sendRedirect("Controller?command=go_to_index_page");
            return;
        }

        int userId = auth.getId();
        String currentPassword = request.getParameter("currentPassword");

        try {
            // Получаем старые данные пользователя из БД
            User oldUser = userService.getUserInfoForEdit(userId);

            // Проверяем пароль
            if (!Objects.equals(currentPassword, oldUser.getPassword())) {
                response.sendRedirect("Controller?command=go_to_edit_account_page&message=Invalid+password");
                return;
            }

            // Получаем новые данные из формы
            String login = request.getParameter("login");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            LocalDate birthdayDate = request.getParameter("birthdayDate") != null
                    && !request.getParameter("birthdayDate").isEmpty()
                    ? LocalDate.parse(request.getParameter("birthdayDate"))
                    : null;

            // Проверяем, есть ли изменения
            if (Objects.equals(login, oldUser.getLogin()) &&
                    Objects.equals(name, oldUser.getName()) &&
                    Objects.equals(surname, oldUser.getSurname()) &&
                    Objects.equals(email, oldUser.getEmail()) &&
                    Objects.equals(address, oldUser.getAddress()) &&
                    Objects.equals(birthdayDate, oldUser.getBirthdayDate())) {

                response.sendRedirect("Controller?command=go_to_edit_account_page&message=No+changes+were+made");
                return;
            }

            if (!Objects.equals(login, oldUser.getLogin()) && userService.checkLoginExistsInDB(login)) {
                response.sendRedirect("Controller?command=go_to_edit_account_page&message=This+login+is+already+taken");
                return;
            }

            if (!Objects.equals(email, oldUser.getEmail()) && userService.checkEmailExistsInDB(email)) {
                response.sendRedirect("Controller?command=go_to_edit_account_page&message=This+email+is+already+taken");
                return;
            }

            User newUser = new User(userId, login, oldUser.getPassword(), name, surname, email, birthdayDate, address);

            boolean isUpdated = userService.updateUserInDatabase(newUser);

            if (isUpdated) {
                response.sendRedirect("Controller?command=go_to_account_page&message=Profile+updated+successfully");
            } else {
                response.sendRedirect("Controller?command=go_to_edit_account_page&message=Failed+to+update+profile");
            }

        } catch (ServiceException e) {
            response.sendRedirect("Controller?command=go_to_error_page&error=Server+error");
        }
    }
}

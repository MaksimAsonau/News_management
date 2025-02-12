package by.asonau.web_project.controller.concrete.impl;

import java.io.IOException;

import by.asonau.web_project.controller.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NoSuchCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Controller?command=go_to_error_page&errorMessage=Unknown+command");
	}
}

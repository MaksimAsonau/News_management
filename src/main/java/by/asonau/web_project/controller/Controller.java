package by.asonau.web_project.controller;

import by.asonau.web_project.dao.DAOException;
import by.asonau.web_project.service.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import by.asonau.web_project.controller.concrete.Command;
import by.asonau.web_project.controller.concrete.CommandProvider;


public class Controller extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private final CommandProvider provider = new CommandProvider();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userCommand = request.getParameter("command");
		System.out.println("Received command: " + userCommand);

		Command command = provider.takeCommand(userCommand);
		command.execute(request, response);
	}


//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		try {
//			doRequest(request, response);
//		} catch (ServiceException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		try {
//			doRequest(request, response);
//		} catch (ServiceException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	private void doRequest(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException, ServiceException {
//		String userCommand = request.getParameter("command");
//
//		System.out.println("Received command: " + userCommand);
//
//		Command command = provider.takeCommand(userCommand);
//		command.execute(request, response);
//	}
}




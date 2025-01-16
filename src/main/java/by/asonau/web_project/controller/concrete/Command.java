package by.asonau.web_project.controller.concrete;

import java.io.IOException;

import by.asonau.web_project.service.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
	
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException;
}

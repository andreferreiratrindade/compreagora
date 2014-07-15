package br.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletError
 */
public class ErrorController extends HttpServlet {
	/**
     *
     */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Throwable throwable = (Throwable) req
				.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) req
				.getAttribute("javax.servlet.error.status_code");

		req.setAttribute("errorType", throwable);
		req.setAttribute("statusCode", statusCode);

		RequestDispatcher requestDispatcher = req
				.getRequestDispatcher("/error.jsp");
		requestDispatcher.forward(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
}

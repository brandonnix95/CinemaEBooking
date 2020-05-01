package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityhandlers.UserSessionHandler;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserSessionHandler sessionHandler = new UserSessionHandler(email, password);
		
		if(sessionHandler.isSuccessful()) {
			sessionHandler.registerSession(request.getSession());
			String destination;
			
			switch(sessionHandler.getType()) {
				case ADMIN:
					destination = "admin.jsp";
					break;
				case CUSTOMER:
					destination = "searchMovies.jsp";
					break;
				case EMPLOYEE:
					destination = "searchMovies.jsp";
					break;
				default:
					destination = "editProfile.jsp";
					break;
			} // switch
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(destination); 
			dispatcher.forward(request, response);
		} else {
			System.out.println("LOGIN FAILED");
			request.setAttribute("errMessage", "Login Failed: email or password is invalid");
			String destination = "login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(destination); 
			dispatcher.forward(request, response);
		} // if/else
	} // doGet
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} // doPost
} // LoginServlet

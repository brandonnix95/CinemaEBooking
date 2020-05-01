package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityhandlers.UserSessionHandler;
import movies.Category;

@WebServlet("/MovieQueryServlet")
public class MovieQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 3L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("searchMovies.jsp"); 
		dispatcher.forward(request, response);
	} // doGet
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String date = request.getParameter("date");
		Category category = Category.fromName(request.getParameter("category"));
		String availability = request.getParameter("availability");
		if(title == null) title = "";
		if(date == null) date = "";
		if(availability == null) availability = "";
		
		UserSessionHandler.registerQuery(request.getSession(), title, date, category, availability);
		
		doGet(request, response);
	} // doPost
}

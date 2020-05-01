package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityhandlers.UserSessionHandler;
import movies.Movie;

@WebServlet("/BookMovieServlet")
public class BookMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 4183555456735782345L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(UserSessionHandler.loggedIn(request.getSession())) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("pickTickets.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("bookBtn");
		@SuppressWarnings("unchecked")
		List<Movie> query = (List<Movie>) request.getSession().getAttribute("query");
		Movie movieToBook = null;
		for(Movie movie: query) if(movie.getTitle().equals(title)) movieToBook = movie;
		request.getSession().setAttribute("movie", movieToBook);
		request.getSession().setAttribute("tickets", null);
		request.getSession().setAttribute("promotion", null);
		
		doGet(request, response);
	}
}

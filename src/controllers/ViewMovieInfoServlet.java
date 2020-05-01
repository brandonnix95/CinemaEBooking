package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movies.Movie;

@WebServlet("/ViewMovieInfoServlet")
public class ViewMovieInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 4L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewMovie.jsp"); 
		dispatcher.forward(request, response);
	} // doGet
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("viewBtn");
		List<Movie> query = (List<Movie>) request.getSession().getAttribute("query");
		Movie movieToView = null;
		for(Movie movie: query) if(movie.getTitle().equals(title)) movieToView = movie;
		request.getSession().setAttribute("movie", movieToView);
		
		doGet(request, response);
	} // doPost
} // class
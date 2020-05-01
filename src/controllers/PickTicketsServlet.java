package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movies.Movie;
import movies.Showtime;

@WebServlet("/PickTicketsServlet")
public class PickTicketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1127295203098583447L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("pickTickets2.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Movie movie = (Movie) request.getSession().getAttribute("movie");
		String showtimeAsString = (String) request.getParameter("showTime");
		Showtime showtime = Showtime.fromString(showtimeAsString, movie);
		request.getSession().setAttribute("showtime", showtime);
		
		doGet(request, response);
	}
}

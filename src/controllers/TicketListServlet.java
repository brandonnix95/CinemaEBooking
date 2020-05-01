package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booking.Ticket;
import booking.TicketType;
import movies.Movie;
import movies.Seat;
import movies.Showtime;

@WebServlet("/TicketListServlet")
public class TicketListServlet extends HttpServlet {
	private static final long serialVersionUID = -8785517941596446622L;
	
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
		Showtime showtime = (Showtime) request.getSession().getAttribute("showtime");
		Seat seat = showtime.getSeatByNumber(Integer.parseInt(request.getParameter("seatnumber")));
		TicketType type = TicketType.fromString(request.getParameter("tickettype"));
		
		@SuppressWarnings("unchecked")
		List<Ticket> tickets = (List<Ticket>) request.getSession().getAttribute("tickets");
		
		if(tickets == null) tickets = new ArrayList<Ticket>();
		
		if(seat.isAvailible()) {
			tickets.add(new Ticket(seat.getSeatNumber(), movie, showtime, type));
			seat.setAvailible(false);
		}
		
		request.getSession().setAttribute("tickets", tickets);
		
		doGet(request, response);
	}
}

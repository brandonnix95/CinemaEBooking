package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booking.Ticket;
import movies.Seat;
import movies.Showtime;

@WebServlet("/DeleteTicketListServlet")
public class DeleteTicketListServlet extends HttpServlet {
	private static final long serialVersionUID = 7772329988253550224L;

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
		@SuppressWarnings("unchecked")
		List<Ticket> tickets = (List<Ticket>) request.getSession().getAttribute("tickets");
		Showtime showtime = (Showtime) request.getSession().getAttribute("showtime");
		Seat seat = showtime.getSeatByNumber(Integer.parseInt(request.getParameter("ticketBtn")));
		
		tickets.removeIf(ticket -> (ticket.getSeatNumber() == seat.getSeatNumber()));
		seat.setAvailible(true);
		
		request.getSession().setAttribute("tickets", tickets);
		
		doGet(request, response);
	}
}

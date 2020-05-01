package controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booking.Booking;
import booking.Promotion;
import booking.Ticket;
import entityhandlers.UserSessionHandler;
import mail.Mail;
import users.Card;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = -5355393538172220974L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("ordConfirmation.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		@SuppressWarnings("unchecked")
		List<Ticket> tickets = (List<Ticket>) request.getSession().getAttribute("tickets");
		Promotion promotion = (Promotion) request.getSession().getAttribute("promotion");
		
		UserSessionHandler handler = UserSessionHandler.getFromSession(request.getSession());
		Card card = handler.getCustomer().getCard();
		if(card == null) card = new Card("examplecard", "5555555555555555", "333", new Date(2020, 8, 1), "30609");
		
		Booking booking;
		if(promotion != null) booking = new Booking(tickets, card, promotion, handler.getCustomer());
		else booking = new Booking(tickets, card, handler.getCustomer());
		
		request.getSession().setAttribute("booking", booking);
		Mail.sendMail(email, "Confirmed Order", "Confirmed order number "+booking.getTickets().size()+" for $"+booking.getTotal()+" at "+booking.getTickets().get(0).getShowtime().getDate().toString() + "-"
				+ booking.getTickets().get(0).getShowtime().getStartTime() + " with seat numbers "+booking.getSeatsAsString()+"for "+booking.getTickets().get(0).getMovie().getTitle() + ".");
		
		doGet(request, response);
	}
}

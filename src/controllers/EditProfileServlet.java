package controllers;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import users.Address;
import users.Card;

import entityhandlers.UserSessionHandler;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3041931080909935407L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "editProfile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(destination); 
		dispatcher.forward(request, response);
	} // doGet
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		
		boolean changePassword = oldPassword.length() > 0 && newPassword.length() > 0;
		
		String address = request.getParameter("address"); // street address
		String address2 = request.getParameter("address2"); // suite
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		Address addressObject = new Address(address, address2, city, state, zip);
		
		boolean enrollForPromotions = request.getParameter("enrollForPromotions") != null;
		
		String cardName = request.getParameter("cc-name");
		String cardNumber = request.getParameter("cc-number");
		String cardZip = request.getParameter("cc-zip");
		String expDateString = request.getParameter("exp-month"); // "yyyy-mm"
		if(!expDateString.matches("\\d\\d\\d\\d-\\d\\d")) expDateString = "2020-08";
		int year = Integer.parseInt(expDateString.substring(0,4));
		int month = Integer.parseInt(expDateString.substring(5));
		@SuppressWarnings("deprecation")
		Date date = new Date(year, month, 1);
		String cvv = request.getParameter("cvv");
		Card card = new Card(cardName, cardNumber, cvv, date, cardZip);

		UserSessionHandler handler = (UserSessionHandler) request.getSession().getAttribute("handler");
		boolean verifyPassword = handler.verifyPassword(oldPassword);
		
		if(verifyPassword || !changePassword) {
			if(changePassword) {
				System.out.println("CHang password");
				handler.updateCustomer(newPassword, firstName, lastName, email, phone, enrollForPromotions, addressObject, card);
			} else {
				System.out.println("boring");
				handler.updateCustomer(firstName, lastName, email, phone, enrollForPromotions, addressObject, card);
			} // if/else
			request.getSession().setAttribute("wrongpassword", false);
		} else {
			System.out.println("WRONG PASSWORD!!");
			request.getSession().setAttribute("wrongpassword", true);
		} // if/else
		
		handler.registerSession(request.getSession());
		
		doGet(request, response);
	} // doPost
}

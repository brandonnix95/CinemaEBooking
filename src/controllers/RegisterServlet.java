package controllers;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityhandlers.UserSessionHandler;
import mail.Mail;
import users.Address;
import users.Card;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String phone = request.getParameter("phone");
		
		String address = request.getParameter("address");
		String address2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		Address addressObject = new Address(address, address2, city, state, zip);
		
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
		
		boolean enrollForPromotions = request.getParameter("enrollForPromotions") != null;
		
		// TODO ensure passwords match and password strength is good enough
		
		
		boolean passMatch = true;
		UserSessionHandler customerHandler;
		if (!password.equals(password2 )) {
			passMatch = false;
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp"); 
			dispatcher.forward(request, response);
			return;
		}
		else {
			 customerHandler = new UserSessionHandler(firstName, lastName, email, phone, password, enrollForPromotions, addressObject, card);
		}
		if(customerHandler.isSuccessful() && passMatch == true) {
			Mail.send(email, customerHandler.getConfirmCode());
			customerHandler.registerSession(request.getSession());
			String destination;

			switch(customerHandler.getType()) {
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
					destination = "homepage.jsp";
					break;
			} // switch
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(destination); 
			dispatcher.forward(request, response);
		} else {
			String destination = "register.jsp";
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
} // RegisterServlet
package entityhandlers;

import java.util.List;

import javax.servlet.http.HttpSession;

import booking.Booking;
import booking.Promotion;
import datahandlers.BookingDataHandler;
import datahandlers.CustomerDataHandler;
import datahandlers.MovieQueryDataHandler;
import datahandlers.PromotionDataHandler;
import movies.Category;
import movies.Movie;
import users.Address;
import users.Card;
import users.Customer;
import users.UserType;

public class UserSessionHandler {
	private Customer customer;
	
	public static UserSessionHandler getFromSession(HttpSession session) {
		return (UserSessionHandler) session.getAttribute("handler");
	} // getFromSession
	
	public static void confirm(String code) {
		CustomerDataHandler.confirm(code);
	} // confirm
	
	public static void registerQuery(HttpSession session, String title, String date, Category category, String availability) {
		MovieQueryDataHandler handler = new MovieQueryDataHandler(title, date, category, availability);
		List<Movie> query = handler.getQuery();
		session.setAttribute("query", query);
	} // registerQuery
	
	public static void applyPromotion(HttpSession session, String code) {
		Promotion promotion = PromotionDataHandler.getPromotion(code);
		if(promotion != null) session.setAttribute("promotion", promotion);
		else session.setAttribute("promotion", "invalid");
	} // applyPromotion
	
	public static boolean loggedIn(HttpSession session) {
		return session.getAttribute("handler") != null;
	} // loggedIn
	
	/**
	 * Loads a customer's information
	 * @param email
	 * @param password
	 */
	public UserSessionHandler(String email, String password) {
		customer = new Customer(email, password);
	} // constructor (load)
	
	public List<Booking> getBookings() {
		return BookingDataHandler.getBookings(customer);
	}
	
	/**
	 * Registers a new customer account
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param password
	 */
	public UserSessionHandler(String firstName, String lastName, String email, String phone, String password, boolean enrollForPromotions) {
		customer = new Customer(firstName, lastName, email, phone, password, enrollForPromotions);
	} // constructor (create)
	
	/**
	 * Registers a new customer account (updated with address and card)
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param password
	 * @param address
	 * @param card
	 */
	public UserSessionHandler(String firstName, String lastName, String email, String phone, String password, boolean enrollForPromotions, Address address, Card card) {
		customer = new Customer(firstName, lastName, email, phone, password, enrollForPromotions, address, card);
	} // constructor (create)
	
	public boolean verifyPassword(String password) {
		return customer.verifyPassword(password);
	} // verifyPassword
	
	public void updateCustomer(String firstName, String lastName, String email, String phone, boolean enrollForPromotions, Address address, Card card) {
		customer.setFirstName(firstName, false);
		customer.setLastName(lastName, false);
		customer.setEmail(email, false);
		customer.setPhone(phone, false);
		customer.setEnrollForPromotions(enrollForPromotions, false);
		customer.setAddress(address);
		customer.setCard(card);
		
		customer.update();
	} // updateCustomer
	
	public void updateCustomer(String newPassword, String firstName, String lastName, String email, String phone, boolean enrollForPromotions, Address address, Card card) {
		updateCustomer(firstName, lastName, email, phone, enrollForPromotions, address, card);
		customer.updatePassword(newPassword);
	} // updateCustomer
	
	public boolean isSuccessful() { return customer.isSuccessful(); }
	public UserType getType() { return customer.getType(); }
	public String getConfirmCode() { return customer.getConfirmCode(); }
	
	public Customer getCustomer() { return customer; }
	
	public void logout(HttpSession session) {
		session.setAttribute("handler", null);
	}

	/**
	 * Registers a session with the CustomerHandler
	 * @param session HttpSession
	 */
	public void registerSession(HttpSession session) {
		session.setAttribute("handler", this);
		session.setAttribute("firstName", customer.getFirstName());
		session.setAttribute("lastName", customer.getLastName());
		session.setAttribute("email", customer.getEmail());
		session.setAttribute("phone", customer.getPhone());
		session.setAttribute("status", customer.getStatus().toString());
		session.setAttribute("enrollForPromotions", customer.isEnrolledForPromotions());
		session.setAttribute("type", customer.getType().toString());
		session.setAttribute("address", customer.getAddress());
		session.setAttribute("card", customer.getCard());
	} // registerSession
} // CustomerHandler
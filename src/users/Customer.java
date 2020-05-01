package users;

import datahandlers.CustomerDataHandler;

public class Customer {
	private boolean enrollForPromotions, successful;
	private String firstName, lastName, email, phone;
	private CustomerStatus status;
	private UserType userType;
	private CustomerDataHandler datahandler;
	private Address address;
	private Card card;
	
	public int getID() { return datahandler.getID(); }
	
	/**
	 * Creates a Customer object and loads info from DB with email/password combination
	 * @param email String email
	 * @param password String password
	 */
	public Customer(String email, String password) {
		datahandler = new CustomerDataHandler(this, email, password);
		successful = datahandler.isSuccessful();
	} // constructor (load)
	
	public Customer(String firstName, String lastName, String email, String phone, String password, boolean enrollForPromotions) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.enrollForPromotions = enrollForPromotions;
		status = CustomerStatus.INACTIVE;
		userType = UserType.CUSTOMER;
		datahandler = new CustomerDataHandler(this, password);
		successful = datahandler.isSuccessful();
	} // constructor (create)
	
	public boolean verifyPassword(String password) {
		return datahandler.verifyPassword(password);
	} // verifyPassword
	
	public void updatePassword(String password) {
		datahandler.updatePassword(password);
	}
	
	public void update() {
		datahandler.update();
	}
	
	public Address getAddress() {
		return address;
	}

	public Card getCard() {
		return card;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Customer(String firstName, String lastName, String email, String phone, String password, boolean enrollForPromotions, Address address, Card card) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.enrollForPromotions = enrollForPromotions;
		this.address = address;
		if(card.getType() != CardType.NONE) this.card = card;
		status = CustomerStatus.INACTIVE;
		userType = UserType.CUSTOMER;
		datahandler = new CustomerDataHandler(this, password);
		successful = datahandler.isSuccessful();
	} // constructor with address and card
	
	// Getters
	public boolean isEnrolledForPromotions() { return enrollForPromotions; }
	public int getUserID() { return datahandler.getUserID(); }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getEmail() { return email; }
	public String getPhone() { return phone; }
	public CustomerStatus getStatus() { return status; }
	public UserType getType() { return userType; }
	public boolean isSuccessful() { return successful; }
	public String getConfirmCode() { return datahandler.getConfirmCode(); }
	
	// Setters
	public void setEnrollForPromotions(boolean value, boolean update) { enrollForPromotions = value; if(update) datahandler.update(); }
	public void setFirstName(String value, boolean update) { firstName = value; if(update)  datahandler.update(); }
	public void setLastName(String value, boolean update) { lastName = value; if(update)  datahandler.update(); }
	public void setEmail(String value, boolean update) { email = value; if(update)  datahandler.update(); }
	public void setPhone(String value, boolean update) { phone = value; if(update)  datahandler.update(); }
	public void setStatus(CustomerStatus value, boolean update) { status = value; if(update)  datahandler.update(); }
	public void setType(UserType value, boolean update) { userType = value; if(update)  datahandler.update(); }
} // Customer

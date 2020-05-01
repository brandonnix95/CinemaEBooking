package booking;

import java.util.List;

import datahandlers.BookingDataHandler;
import users.Card;
import users.Customer;

public class Booking {
	private int num;
	private List<Ticket> tickets;
	private Card card;
	private double subtotal;
	private double total;
	private Promotion promotion;
	private BookingDataHandler handler;
	
	public Booking(List<Ticket> tickets, Card card, Promotion promotion, Customer customer) {
		this.tickets = tickets;
		this.card = card;
		this.promotion = promotion;
		
		subtotal = generateSubtotal();
		total = generateTotal();
		
		handler = new BookingDataHandler(this, customer);
	}
	
	public void remove() { handler.remove(); }
	
	public Booking(List<Ticket> tickets, Card card,  Customer customer) {
		this.tickets = tickets;
		this.card = card;
		
		subtotal = generateSubtotal();
		total = generateTotal();
		
		handler = new BookingDataHandler(this, customer);
	}
	
	public Booking(List<Ticket> tickets, Card card, Promotion promotion, int num) {
		this.tickets = tickets;
		this.card = card;
		this.promotion = promotion;
		this.num = num;
		
		subtotal = generateSubtotal();
		total = generateTotal();
		
		handler = new BookingDataHandler(this);
	}
	
	private double generateSubtotal() {
		double subtotal = 0.0;
		
		for(Ticket ticket: tickets) subtotal += ticket.getPrice();
		if(promotion != null) subtotal -= subtotal * (promotion.getPercent());
		
		return subtotal;
	}
	
	private double generateTotal() {
		double total = generateSubtotal();
		
		total += total * Ticket.TAX_RATE;
		
		return total;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}
	
	public String getSeatsAsString() {
		String seats = "";
		for(Ticket ticket: tickets) seats += ticket.getSeatNumber() + " ";
		return seats;
	} // getSeatsAsString

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}

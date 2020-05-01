package booking;

import java.util.List;

import datahandlers.TicketDataHandler;
import movies.Movie;
import movies.Showtime;

public class Ticket {
	public static final double TAX_RATE = 0.07;
	
	private int seatid;
	private int seatNumber;
	private Movie movie;
	private Showtime showtime;
	private TicketType type;
	private double price;
	
	public static double getSubtotal(List<Ticket> tickets) {
		double price = 0.0;
		for(Ticket ticket: tickets) price += ticket.getPrice();
		return price;
	}
	
	public static double getTotal(List<Ticket> tickets) {
		double price = 0.0;
		for(Ticket ticket: tickets) price += ticket.getPrice();
		return price + price * TAX_RATE;
	}
	
	public Ticket(int seatNumber, Movie movie, Showtime showtime, TicketType type) {
		this.seatNumber = seatNumber;
		this.movie = movie;
		this.showtime = showtime;
		this.type = type;
		price = TicketDataHandler.getPrice(this);
	} // Ticket
	
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Showtime getShowtime() {
		return showtime;
	}
	public void setShowtime(Showtime showtime) {
		this.showtime = showtime;
	}
	public TicketType getType() {
		return type;
	}
	public void setType(TicketType type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public int getSeatId() {
		return seatid;
	}

	public void setSeatId(int id) {
		this.seatid = id;
	}
} // Ticket
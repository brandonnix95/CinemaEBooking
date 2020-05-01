package movies;

import java.sql.Date;
import java.util.List;

public class Showtime {
	private String startTime;
	private String endTime;
	private Date date;
	private List<Seat> seats;
	
	public static Showtime fromString(String string, Movie movie) {
		String[] arr = string.split("/");
		String date = arr[0];
		String startTime = arr[1];
		
		for(Showtime showtime: movie.getShowtimes()) {
			if(showtime.getDate().toString().equals(date) && showtime.getStartTime().equals(startTime)) return showtime;
		}
		
		return null;
	} // fromString
	
	public Showtime(String start, String end, Date date, List<Seat> seats) {
		startTime = start;
		endTime = end;
		this.date = date;
		this.seats = seats;
	} // constructor
	
	public Showtime(String start, String end, Date date) {
		startTime = start;
		endTime = end;
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String toString() {
		return date.toString() + "/" + startTime;
	} // toString

	public List<Seat> getSeats() {
		return seats;
	}
	
	public Seat getSeatByNumber(int num) {
		for(Seat seat: seats) if(seat.getSeatNumber() == num) return seat;
		return null;
	} // getSeatByNumber

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
} // Showtime

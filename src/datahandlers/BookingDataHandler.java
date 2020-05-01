package datahandlers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import booking.Booking;
import booking.Promotion;
import booking.Ticket;
import booking.TicketType;
import movies.Movie;
import movies.Showtime;
import users.Card;
import users.Customer;

public class BookingDataHandler {
	private int bookingID;
	
	public BookingDataHandler(Booking booking) {
		bookingID = booking.getNum();
	}
	
	public static List<Booking> getBookings(Customer customer) {
		List<Booking> bookings = new ArrayList<Booking>();
		
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		PreparedStatement st3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root&password=root");
			
			st = conn.prepareStatement("SELECT * FROM users WHERE email=?");
			st.setString(1, customer.getEmail());
			rs = st.executeQuery();
			rs.next();
			int userID = rs.getInt("UserID");
			byte[] salt = rs.getBytes("salt");
			st.close();
			rs.close();
			
			st = conn.prepareStatement("SELECT * FROM booking WHERE userID=?");
			st.setInt(1, userID);
			rs = st.executeQuery();
			
			while(rs.next()) {
				int bookingID = rs.getInt("bookingID");
				
				List<Ticket> tickets = new ArrayList<Ticket>();
				
				st2 = conn.prepareStatement("SELECT * FROM ticket WHERE bookingID=?");
				st2.setInt(1, bookingID);
				rs2 = st2.executeQuery();
				
				while(rs2.next()) {
					int seatID = rs2.getInt("seatID");
					int showID = rs2.getInt("showID");
					int typeID = rs2.getInt("typeID");
					
					st3 = conn.prepareStatement("SELECT * FROM seat WHERE seatID=?");
					st3.setInt(1, seatID);
					rs3 = st3.executeQuery();
					rs3.next();
					int seatNumber = rs3.getInt("seatNumber");
					st3.close();
					rs3.close();
					
					st3 = conn.prepareStatement("SELECT * FROM showtimes WHERE showID=?");
					st3.setInt(1, showID);
					rs3 = st3.executeQuery();
					rs3.next();
					int movieID = rs3.getInt("movieID");
					int startID = rs3.getInt("startID");
					String endTime = rs3.getString("endTime");
					Date date = rs3.getDate("date");
					st3.close();
					rs3.close();
					
					st3 = conn.prepareStatement("SELECT * FROM showstarts WHERE StartID=?");
					st3.setInt(1, startID);
					rs3 = st3.executeQuery();
					rs3.next();
					String startTime = rs3.getString("StartTime");
					Showtime showtime = new Showtime(startTime, endTime, date);
					st3.close();
					rs3.close();
					
					st3 = conn.prepareStatement("SELECT * FROM movie WHERE movieID=?");
					st3.setInt(1, movieID);
					rs3 = st3.executeQuery();
					rs3.next();
					Movie movie = new Movie(rs3.getString("movieTitle"));
					st3.close();
					rs3.close();
					
					st3 = conn.prepareStatement("SELECT * FROM ticketprice WHERE typeID=?");
					st3.setInt(1, typeID);
					rs3 = st3.executeQuery();
					rs3.next();
					TicketType type = TicketType.fromString(rs3.getString("type"));
					st3.close();
					rs3.close();
					
					tickets.add(new Ticket(seatNumber, movie, showtime, type));
				} // while
				
				st2.close();
				rs2.close();
				
				byte[] ccnum = rs.getBytes("ccNumber");
				
				st2 = conn.prepareStatement("SELECT * FROM creditcard WHERE ccNumber=?");
				st2.setBytes(1, ccnum);
				rs2 = st2.executeQuery();
				rs2.next();
				String name = rs2.getString("ccName");
				Date date = rs2.getDate("expDate");
				SecretKeySpec key = new SecretKeySpec(salt, "AES");
				String number = CustomerDataHandler.decrypt(rs2.getBytes("ccNumber"), key);
				String zipCode = CustomerDataHandler.decrypt(rs2.getBytes("zipCode"), key);
				String cvv = CustomerDataHandler.decrypt(rs2.getBytes("cvv"), key);
				Card card = new Card(name, number, cvv, date, zipCode);
				rs2.close();
				st2.close();
				
				Promotion promotion = null;
				int promoID = rs.getInt("promoID");
				if(promoID != 0) {
					st2 = conn.prepareStatement("SELECT * FROM promotion WHERE promoID=?");
					st2.setInt(1, promoID);
					rs2 = st2.executeQuery();
					rs2.next();
					String code = rs2.getString("code");
					double percent = rs2.getDouble("percent");
					Date expdate = rs2.getDate("expDate");
					promotion = new Promotion(code, expdate, percent, promoID);
				}
				
				bookings.add(new Booking(tickets, card, promotion, bookingID));
			} // while
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		} finally {
			try {
				if(st != null) st.close();
				if(st2 != null) st2.close();
				if(st3 != null) st3.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
				if(rs2 != null) rs2.close();
				if(rs3 != null) rs3.close();
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} // try/catch
		} // try/catch/catch/finally
		
		return bookings;
	} // getBookings
	
	public void remove() {
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root&password=root");
			
			st = conn.prepareStatement("DELETE FROM booking WHERE bookingID=?");
			st.setInt(1, bookingID);
			st.execute();
			
			st.close();
			st = conn.prepareStatement("SELECT * FROM ticket WHERE bookingID=?");
			st.setInt(1, bookingID);
			rs = st.executeQuery();

			st2 = conn.prepareStatement("UPDATE seat SET `Status`='a' WHERE seatID=?");
			while(rs.next()) {
				int seatID = rs.getInt("seatID");
				
				st2.setInt(1, seatID);
				st2.execute();
			}
			
			st.close();
			st2.close();
			
			st = conn.prepareStatement("DELETE FROM ticket WHERE bookingID=?");
			st.setInt(1, bookingID);
			st.execute();
			
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		} finally {
			try {
				if(st != null) st.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} // try/catch
		} // try/catch/catch/finally
	}
	
	public BookingDataHandler(Booking booking, Customer customer) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			int userID = customer.getID();
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root&password=root");
			st = conn.prepareStatement("SELECT * FROM creditcard WHERE userID=?");
			st.setInt(1, userID);
			rs = st.executeQuery();
			rs.next();
			
			byte[] ccnum = rs.getBytes("ccNumber");
			int promoID = booking.getPromotion().getId();
			double total = booking.getTotal();
			Date date = booking.getTickets().get(0).getShowtime().getDate();
			int noOfTickets = booking.getTickets().size();
			
			st.close();
			
			st = conn.prepareStatement("INSERT INTO booking (userID, promoID, ccNumber, total, date, noOfTickets) VALUES (?, ?, ?, ?, ?, ?)");
			st.setInt(1, userID);
			st.setInt(2, promoID);
			st.setBytes(3, ccnum);
			st.setDouble(4, total);
			st.setDate(5, date);
			st.setInt(6, noOfTickets);
			
			st.execute();
			
			st.close();
			rs.close();
			
			st = conn.prepareStatement("SELECT MAX(bookingID) FROM booking");
			rs = st.executeQuery();
			rs.next();
			bookingID = rs.getInt("MAX(bookingID)");
			booking.setNum(bookingID);
			
			st.close();
			rs.close();
			
			
			Showtime showtime = booking.getTickets().get(0).getShowtime();
			st = conn.prepareStatement("SELECT * FROM showtimes WHERE endTime=? AND date=?");
			st.setString(1, showtime.getEndTime());
			st.setDate(2, showtime.getDate());
			rs = st.executeQuery();
			rs.next();
			
			int showID = rs.getInt("showID");
			
			st.close();
			rs.close();
			
			for(Ticket ticket: booking.getTickets()) {
				st = conn.prepareStatement("UPDATE seat SET `Status` = 'u' WHERE showID=? AND seatNumber=?");
				st.setInt(1, showID);
				st.setInt(2, ticket.getSeatNumber());
				st.execute();
				st.close();
				
				st = conn.prepareStatement("SELECT * FROM seat WHERE showID=? AND seatNumber=?");
				st.setInt(1, showID);
				st.setInt(2, ticket.getSeatNumber());
				rs = st.executeQuery();
				rs.next();
				ticket.setSeatId(rs.getInt("seatID"));
				st.close();
				rs.close();
			} // for
			
			st = conn.prepareStatement("INSERT INTO ticket (bookingID, seatID, showID, typeID) VALUES (?, ?, ?, ?)");
			st.setInt(1, bookingID);
			st.setInt(3, showID);
			
			for(Ticket ticket: booking.getTickets()) {
				st.setInt(2, ticket.getSeatId());
				st.setInt(4, ticket.getType().index);
				st.execute();
			} // for
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		} finally {
			try {
				if(st != null) st.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} // try/catch
		} // try/catch/catch/finally
	}
}

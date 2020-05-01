package datahandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import booking.Ticket;

public class TicketDataHandler {
	public static double getPrice(Ticket ticket) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		
		double price = 0.0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root&password=root");
			prst = conn.prepareStatement("SELECT * FROM ticketprice WHERE type=?");
			prst.setString(1, ticket.getType().name);
			rs = prst.executeQuery();
			if(rs.next()) {
				price = rs.getDouble("price");
			} // if
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		} finally {
			try {
				if(prst != null) prst.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} // try/catch
		} // try/catch/catch/finally
		
		return price;
	} // getPrice
} // TicketDataHandler

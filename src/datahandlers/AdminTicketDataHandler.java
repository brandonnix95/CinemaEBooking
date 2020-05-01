package datahandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminTicketDataHandler {
	private Connection connection;
	private ResultSet results;
	public AdminTicketDataHandler(String dbName, String uname, String pwd ) {
		
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, uname, pwd);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ResultSet getTickets () {
		String query = "SELECT * from ticketprice";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			results = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public void updatePrices (double price, String type) {
		String query = "UPDATE ticketprice SET price = ? where type = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setDouble(1, price);
			ps.setString(2, type);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

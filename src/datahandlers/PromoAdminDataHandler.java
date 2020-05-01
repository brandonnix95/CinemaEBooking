package datahandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PromoAdminDataHandler {
	private Connection connection;
	private ResultSet results;
	
	
	public PromoAdminDataHandler(String dbName, String uname, String pwd ) {
		
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, uname, pwd);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ResultSet getPromos () {
		String query = "SELECT * from Promotion";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			this.results = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public void createPromo (String code, double percent, String expDate) {
		String query = "INSERT INTO PROMOTION(Code, percent, expDATE) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, code);
			ps.setDouble(2, percent);
			ps.setString(3, expDate);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletePromo (String promoID) {
		String query = "DELETE FROM PROMOTION WHERE promoID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, promoID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet getPromo (String promoID) {
		String query = "SELECT * FROM promotion WHERE promoID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, promoID);
			results = ps.executeQuery();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public void updatePromo (String promoID, String code, double percent, String date) {
		String query = "UPDATE PROMOTION SET code = ?, percent = ?, expdate = ? WHERE PromoID = ?";
		try {
			System.out.println(code);
			System.out.println(percent);
			System.out.println(date);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString (1,code);
			ps.setDouble(2, percent);
			ps.setString(3, date);
			ps.setString(4,promoID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

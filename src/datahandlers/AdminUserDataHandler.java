package datahandlers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminUserDataHandler {
	private Connection connection;
	private ResultSet results;
	
	
	public AdminUserDataHandler(String dbName, String uname, String pwd ) {
		
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, uname, pwd);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ResultSet getUsers() {
		String query = "SELECT * FROM users";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			results = ps.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
			
			
		}
		return results;
	}
	
	public ResultSet getUser (int userID) {
		String query = "SELECT * FROM USERS WHERE USERID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, userID);
			results = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	public void unSuspend (int userID) {
		String query = "UPDATE USERS SET STATUS = 'ACTIVE' WHERE USERID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, userID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	public void Suspend (int userID) {
		String query = "UPDATE USERS SET STATUS= 'SUSPENDED' WHERE USERID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, userID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser (int userID) {
		String query = "DELETE FROM users WHERE USERID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, userID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void setEmployee (int userID) {
		String query = "UPDATE users SET userType = '3' WHERE USERID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, userID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
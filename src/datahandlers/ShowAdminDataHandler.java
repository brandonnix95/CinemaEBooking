package datahandlers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowAdminDataHandler {
	private Connection connection;
	private ResultSet results;
	
	
	public ShowAdminDataHandler(String dbName, String uname, String pwd ) {
		
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, uname, pwd);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ResultSet getShows (int id) {
		String query = "SELECT * from showtimes where MovieID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			this.results = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	public ResultSet getLength (int id) {
		String query = "SELECT length from movie where MovieID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			this.results = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public void deleteShow (int id) {
		String query = "DELETE FROM showtimes where ShowID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
		    ps.executeUpdate();
		    
		    PreparedStatement prst = connection.prepareStatement("DELETE FROM seat WHERE showID=?");
		    prst.setInt(1, id);
		    prst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getStartTimes () {
		String query = "SELECT StartTime from showstarts";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			this.results = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
		
	}
	public void addShow (int movieID, int startID, String endTime, Date date) {
		String query = "INSERT INTO showtimes (movieID, startID, endTime, date) VALUES (?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, movieID);
			ps.setInt(2, startID);
			ps.setString(3, endTime);
			ps.setDate(4,date);
			ps.executeUpdate();
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(showID) FROM showtimes");
			rs.next();
			int showID = rs.getInt("MAX(showID)");
			
			PreparedStatement insertprst = connection.prepareStatement("INSERT INTO seat (showID, seatNumber, Status) VALUES (?, ?, ?)");
			insertprst.setInt(1, showID);
			insertprst.setString(3, "a");
			for(int i = 1; i <= 30; i ++) {
				insertprst.setInt(2, i);
				insertprst.execute();
			} // for
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getStartID(String startTime) {
		String query = "SELECT startID from showstarts WHERE StartTime = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, startTime);
			this.results = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public ResultSet getShow (int showID) {
		String query = "SELECT * from showtimes where showID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, showID);
			this.results=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public void updateShow (int showID, int startID,  String endTime, Date date) {
		String query = "UPDATE showtimes SET startID = ?, endTime = ?, date = ? WHERE showID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, startID);
			ps.setString(2,endTime);
			ps.setDate(3, date);
			ps.setInt(4, showID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


}

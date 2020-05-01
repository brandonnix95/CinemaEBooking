package datahandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieDataHandler {
	private Connection connection;
	private ResultSet movies;
	
	
	public MovieDataHandler(String dbName, String uname, String pwd ) {
		
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, uname, pwd);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ResultSet getAll () {
		String query = "SELECT * FROM movie";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			this.movies = ps.executeQuery();
			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return movies;
}
	
	public void deleteMovie (int id) {
		String query = "DELETE FROM movie WHERE movieID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet getMovieInfo (int id) {
		String query = "SELECT * FROM movie WHERE movieID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			this.movies = ps.executeQuery();
		} catch (SQLException e) {
				e.printStackTrace();
			}
		return movies;
		
	}
	public void updateMovie(int id, String title, int category, String cast, String director, String synopsis, String reviews, String trailer, int rating, int length) {
		String query = "UPDATE MOVIE SET movieTitle = ?, categoryID = ?, cast = ?, director = ?, synopsis = ?, reviews = ?, trailer = ?, ratingID = ?, length = ? WHERE movieID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, title);
			ps.setInt(2, category);
			ps.setString(3, cast);
			ps.setString(4, director);
			ps.setString(5, synopsis);
			ps.setString(6, reviews);
			ps.setString(7, trailer);
			ps.setInt(8, rating);
			ps.setInt(9, length);
			ps.setInt(10, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet getCategories() {
		String query = "SELECT category FROM category";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			this.movies = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}
	public ResultSet getRatings() {
		String query = "SELECT rating FROM rating";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			this.movies = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}
	
	public void AddMovie(String title, int categoryID, String cast, String director, String synopsis, String reviews, String trailer, int ratingID, int length, String isShowing) {
		String query = "INSERT INTO MOVIE(movieTitle, categoryID, `cast`, director, synopsis, reviews, trailer, ratingID, length, isShowing) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, title);
			ps.setInt(2, categoryID);
			ps.setString(3, cast);
			ps.setString(4, director);
			ps.setString(5, synopsis);
			ps.setString(6, reviews);
			ps.setString(7, trailer);
			ps.setInt(8, ratingID);
			ps.setInt(9, length);
			ps.setString(10, isShowing);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
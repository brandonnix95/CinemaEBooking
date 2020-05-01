package datahandlers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import movies.Category;
import movies.Movie;
import movies.Rating;
import movies.Seat;
import movies.Showtime;

public class MovieQueryDataHandler {
	
	private List<Movie> query;

	public MovieQueryDataHandler(String title, String date, Category category, String availability) {
		// NOTE: DATE IS IGNORED FOR THE MOMENT
		query = new ArrayList<>();
		if(availability.equalsIgnoreCase("now")) { 
			availability = "True";
		} else if(availability.equalsIgnoreCase("soon")) {
			availability = "False";
		} // if/else if
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement prst = null;
		ResultSet prstrs = null;
		Statement st2 = null;
		ResultSet rs2 = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root&password=root");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM movie");
			
			
			while(rs.next()) {
				int entryID = rs.getInt("movieID");
				String entryTitle = rs.getString("movieTitle");
				Category entryCategory = Category.fromNum(rs.getInt("categoryID"));
				String entryAvailability = rs.getString("isShowing");
				
				
				if((entryCategory == category || category == Category.ALL) && entryTitle.matches("(?i:.*"+title+".*)") 
						&& (entryAvailability.equals(availability) || availability.equals(""))) {
					String cast = rs.getString("cast");
					String director = rs.getString("director");
					String synopsis = rs.getString("synopsis");
					String reviews = rs.getString("reviews");
					String trailer = rs.getString("trailer");
					Rating rating = Rating.fromNum(rs.getInt("ratingID"));
					String length = rs.getString("length");
					boolean isShowing = rs.getString("isShowing").equals("True");
					
					List<Showtime> showtimes = new ArrayList<Showtime>();
					prst = conn.prepareStatement("SELECT * FROM showtimes WHERE movieID=?");
					prst.setInt(1, entryID);
					prstrs = prst.executeQuery();
					while(prstrs.next()) {
						int showID = prstrs.getInt("showID");
						int startID = prstrs.getInt("startID");
						
						st2 = conn.createStatement();
						rs2 = st2.executeQuery("SELECT * FROM showstarts WHERE StartID="+startID);
						rs2.next();
						String startTime = rs2.getString("StartTime");
						
						String endTime = prstrs.getString("endTime");
						Date showDate = prstrs.getDate("date");
						
						PreparedStatement prst2 = conn.prepareStatement("SELECT * FROM seat WHERE showID=?");
						prst2.setInt(1, showID);
						ResultSet rs3 = prst2.executeQuery();
						List<Seat> seats = new ArrayList<Seat>();
						while(rs3.next()) {
							int seatNumber = rs3.getInt("seatNumber");
							boolean availible = rs3.getString("Status").equals("a");
							Seat seat = new Seat(seatNumber, availible);
							seats.add(seat);
						}
						
						showtimes.add(new Showtime(startTime, endTime, showDate, seats));
					} // while
					
					query.add(new Movie(entryTitle, entryCategory, cast, director, synopsis, reviews, trailer, rating, length, isShowing, showtimes));
				} // if
			} // while
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(rs2 != null) rs2.close();
				if(st2 != null) st2.close();
				if(conn != null) conn.close();
				if(prstrs != null) prstrs.close();
				if(prst != null) prst.close();
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} // try/catch
		} // try/catch/catch/finally
	} // constructor
	
	public List<Movie> getQuery() { return query; }

} // class

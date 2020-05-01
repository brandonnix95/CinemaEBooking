package datahandlers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import booking.Promotion;

public class PromotionDataHandler {
	public static Promotion getPromotion(String code) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		Promotion promotion = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?user=root&password=root");
			prst = conn.prepareStatement("SELECT * FROM promotion WHERE code=?");
			prst.setString(1, code);
			rs = prst.executeQuery();
			if(rs.next()) {
				double percent = rs.getDouble("percent");
				Date date = rs.getDate("expDate");
				int id = rs.getInt("promoID");
				
				promotion = new Promotion(code, date, percent, id);
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
		
		return promotion;
	} // getPromotion
} // PromotionDataHandler

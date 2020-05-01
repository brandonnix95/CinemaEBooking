package datahandlers;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDataHandler {
	private Connection connection;
	private ResultSet results;
public ReportDataHandler(String dbName, String uname, String pwd ) {
		
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, uname, pwd);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
public List<String> GetData() {
	 List<String> arrayRslts = new ArrayList<>();
	String query = "SELECT bookingID, total, date, noOfTickets FROM BOOKING";
	try {
		StringBuilder stringBuilder2 = new StringBuilder();
		PreparedStatement ps = connection.prepareStatement(query);
		results = ps.executeQuery();
       int cols = results.getMetaData().getColumnCount();
       for (int i = 1; i <=cols; i++) {
    	 stringBuilder2.append(String.format(String.valueOf(results.getMetaData().getColumnName(i))) + ",");
       }
       arrayRslts.add(stringBuilder2.toString());
	   while (results.next() ) {
		StringBuilder stringBuilder = new StringBuilder();
		   for (int i = 1; i <= cols; i++) {
		        stringBuilder.append(String.format(String.valueOf(results.getString(i))) + ",");

		    }
		   
		    arrayRslts.add(stringBuilder.toString());
	} }catch (SQLException e) {
		e.printStackTrace();
	}
	
	return arrayRslts;
}

public static void makeCSV(List<String> arrayList) throws Exception {
	File csv = new File ("C:\\6050 Project\\results.csv");
	FileWriter writer = new FileWriter (csv, false);
	for (String test : arrayList) {
		writer.write(test+"\n");
		
	}
	writer.close();
}

}

package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datahandlers.ShowAdminDataHandler;

/**
 * Servlet implementation class AddShowServlet
 */
@WebServlet({ "/AddShowServlet", "/AddShow" })
public class AddShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int movieID = Integer.parseInt(request.getParameter("movieID"));
		String dateString = request.getParameter("date");
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateUtil = null;
		request.setAttribute("movieID", movieID);
		try {
			dateUtil = sdf.parse(dateString);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		java.sql.Date date = new java.sql.Date(dateUtil.getTime());
		
		
		
		
		
		request.setAttribute("movieID",movieID);
		ShowAdminDataHandler addHandler = new ShowAdminDataHandler ("cinema", "root", "root");
		Date time = null;
		String startTime = request.getParameter("startTime");
		System.out.println(startTime);
		SimpleDateFormat df = new SimpleDateFormat ("HH:mm");
		
		 
			try {
				time = df.parse(startTime);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		
		 Calendar calendar = Calendar.getInstance();
		 ResultSet lengthRS = addHandler.getLength(movieID);
		 int length = 0;
		 try {
				if (lengthRS.next()) {
					length = lengthRS.getInt(1);
	}
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } 
		 
		 
		 calendar.setTime(time);
		
		 calendar.add(Calendar.MINUTE, length);
		 String endTime = df.format(calendar.getTime());
		 int startID = 0;
		 ResultSet stRS = addHandler.getStartID(startTime);
		 try {
			if (stRS.next()) {
				 try {
					startID = stRS.getInt(1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String destination = "ShowFetch2";
		 addHandler.addShow(movieID, startID, endTime, date);
		 RequestDispatcher dispatcher = request.getRequestDispatcher(destination); 
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

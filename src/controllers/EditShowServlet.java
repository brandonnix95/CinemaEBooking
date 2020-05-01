
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
 * Servlet implementation class EditShowServlet
 */
@WebServlet({ "/EditShowServlet", "/EditShow" })
public class EditShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int movieID = Integer.parseInt(request.getParameter("movieID"));
		int showID = Integer.parseInt(request.getParameter("showID"));
		String startTime = request.getParameter("startTime");
		ShowAdminDataHandler editHandler = new ShowAdminDataHandler ("cinema", "root", "root");
		String dateString = request.getParameter("date");
		System.out.println(startTime);
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
		Calendar calendar = Calendar.getInstance();
		 ResultSet lengthRS = editHandler.getLength(movieID);
		 int length = 0;
		 try {
				if (lengthRS.next()) {
					length = lengthRS.getInt(1);
	}
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } 
		 Date time = null;
		SimpleDateFormat df = new SimpleDateFormat ("HH:mm");
			
		 try {
				time = df.parse(startTime);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
		 calendar.setTime(time);
		
		 calendar.add(Calendar.MINUTE, length);
		 String endTime = df.format(calendar.getTime());
		 int startID = 0;
		 ResultSet stRS = editHandler.getStartID(startTime);
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
		 String destination = "ShowFetchServlet";
		 editHandler.updateShow(showID, startID, endTime, date);
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

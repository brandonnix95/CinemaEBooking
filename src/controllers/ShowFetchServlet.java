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

import datahandlers.MovieDataHandler;
import datahandlers.ShowAdminDataHandler;

/**
 * Servlet implementation class ShowFetchServlet
 */
@WebServlet({ "/ShowFetchServlet", "/ShowFetch" })
public class ShowFetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowFetchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("movieID"));
		String destination = "scheduleMovies.jsp";
		ShowAdminDataHandler retrievalHandler = new ShowAdminDataHandler ("cinema", "root", "root");
		ResultSet results= retrievalHandler.getShows(id);
		request.setAttribute("movieID", id);
		Date time = null;
		 /*
		 SimpleDateFormat df = new SimpleDateFormat ("HH:mm");
		 
			time = df.parse(startTime);
	
		
		 Calendar calendar = Calendar.getInstance();
		 ResultSet lengthRS = retrievalHandler.getLength(id);
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
		 String endTime = df.format(calendar.getTime());*/
		 request.setAttribute("results", results);
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

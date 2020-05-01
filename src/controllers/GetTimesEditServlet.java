package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datahandlers.ShowAdminDataHandler;

/**
 * Servlet implementation class GetTimesServlet
 */
@WebServlet("/GetTimesEditServlet")
public class GetTimesEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTimesEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ShowAdminDataHandler retrievalHandler = new ShowAdminDataHandler ("cinema", "root", "root");
		int id= Integer.parseInt(request.getParameter("movieID"));
		int showID = Integer.parseInt(request.getParameter("showID"));
		request.setAttribute("movieID", id);
		request.setAttribute("showID", showID);
		String startTime = request.getParameter("startTime");
		/*
		String dateString = request.getParameter("date");
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateUtil = null;*/
		ResultSet results = retrievalHandler.getShow(showID);
		ResultSet startTimes = retrievalHandler.getStartTimes(); 
		request.setAttribute("results", results);
		request.setAttribute("starts", startTimes);
		String destination = "EditShow.jsp";
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

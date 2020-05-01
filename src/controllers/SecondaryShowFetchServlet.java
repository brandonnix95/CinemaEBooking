package controllers;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datahandlers.ShowAdminDataHandler;

/**
 * Servlet implementation class SecondaryShowFetchServlet
 */
@WebServlet({ "/SecondaryShowFetchServlet", "/ShowFetch2" })
public class SecondaryShowFetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecondaryShowFetchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int movieID = (Integer) request.getAttribute("movieID");
	    String destination = "scheduleMovies.jsp";
	    ShowAdminDataHandler retrievalHandler = new ShowAdminDataHandler ("cinema", "root", "root");
	    ResultSet results= retrievalHandler.getShows(movieID);
	    request.setAttribute("movieID", movieID);
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

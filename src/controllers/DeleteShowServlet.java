package controllers;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datahandlers.MovieDataHandler;
import datahandlers.ShowAdminDataHandler;

/**
 * Servlet implementation class DeleteShowServlet
 */
@WebServlet({ "/DeleteShowServlet", "/DeleteShow" })
public class DeleteShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ShowAdminDataHandler deletionHandler = new ShowAdminDataHandler ("cinema", "root", "root");
		int showID = Integer.parseInt(request.getParameter("showID"));
		int movieID = Integer.parseInt(request.getParameter("movieID"));
		deletionHandler.deleteShow(showID);
		String destination ="ShowFetch2";
		request.setAttribute("movieID", movieID);
	    RequestDispatcher dispatcher = request.getRequestDispatcher(destination); 
	    dispatcher.forward(request, response);
		// response.sendRedirect(destination);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

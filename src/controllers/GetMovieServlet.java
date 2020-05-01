package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datahandlers.MovieDataHandler;

/**
 * Servlet implementation class GetMovieServlet
 */
@WebServlet(
		description = "Get info about a specific movie.", 
		urlPatterns = { 
				"/GetMovieServlet", 
				"/GetMovie"
		})
public class GetMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("movieID"));
		response.getWriter().append("Served at: ").append(request.getContextPath());
		MovieDataHandler retrievalHandler = new MovieDataHandler ("cinema", "root", "root");
		ResultSet results= retrievalHandler.getMovieInfo(id);
		String genre = "";
        String rating ="";
        try {
			if (results.next()) {
				
						switch (results.getInt("categoryID")) {
						case 1:
							genre = "Action";
							break;
						case 2:
							genre = "Sci-Fi";
							break;
						case 3: 
							genre = "Horror";
							break;
						case 4:
							genre = "Comedy";
							break;
						case 5: 
							genre = "Drama";
							break;
						default: 
							genre = "No genre found.";	
						}
					
						switch(results.getInt("ratingID")) {
						case 1:
							rating = "G";
						    break;
						case 2:
							rating = "PG";
							break;
						case 3: 
							rating = "PG-13";
							break;
						case 4:
							rating = "R";
							break;
						case 5:
							rating = "NC-17";
							break;
						case 6:
							rating = "NR";
							break;
						case 7:
							rating = "This movie has not yet been rated.";
							break;
						default:
							rating = "This movie has not yet been rated.";

					} 
			}
						
			}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        request.setAttribute("genre",genre);
        request.setAttribute("categories", retrievalHandler.getCategories());
        request.setAttribute("ratings", retrievalHandler.getRatings());
        request.setAttribute("rating", rating);
        request.setAttribute("result", results);
        String destination ="EditMovie.jsp";
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

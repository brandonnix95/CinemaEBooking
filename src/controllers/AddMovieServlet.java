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

/**
 * Servlet implementation class AddMovieServlet
 */
@WebServlet({ "/AddMovieServlet", "/AddMovie" })
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    String isShowing = request.getParameter("isShowing");
		    String name = request.getParameter("name");
		    String genre = request.getParameter("genre");
		    genre = genre.toLowerCase();
		    switch (genre) {
	    	case "action":
				genre = "1";
				break;
			case "sci-fi":
				genre = "2";
				break;
			case "horror": 
				genre = "3";
				break;
			case "comedy":
				genre = "4";
				break;
			case "drama": 
				genre = "5";
				break;
				default: 
					genre ="1";
			}
		    int categoryID = Integer.parseInt(genre);
		    String cast = request.getParameter("cast");
		    String director = request.getParameter("director");
		    String synopsis = request.getParameter("synopsis");
		    String reviews = request.getParameter("reviews");
		    String trailer = request.getParameter("trailer");
		    String rating = request.getParameter("rating");
		    rating = rating.toLowerCase();
		    switch(rating) {
	    	case "g":
				rating = "1";
		        break;
			case "pg":
				rating = "2";
				break;
			case "pg-13": 
				rating = "3";
				break;
			case "r":
				rating = "4";
				break;
			case "nc-17":
				rating = "5";
				break;
			case "nr":
				rating = "6";
				break;
			case "this movie has not yet been rated.":
				rating = "7";
				break;
			default:
				rating = "7";
	    	}
		    int ratingID = Integer.parseInt(rating);
		    int length = Integer.parseInt(request.getParameter("length"));
		    MovieDataHandler addHandler = new MovieDataHandler ("cinema", "root", "root");
		    addHandler.AddMovie(name, categoryID, cast, director, synopsis, reviews, trailer, ratingID, length, isShowing);
		    String destination ="Manage";
			RequestDispatcher dispatcher = request.getRequestDispatcher(destination); 
			response.sendRedirect(destination);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

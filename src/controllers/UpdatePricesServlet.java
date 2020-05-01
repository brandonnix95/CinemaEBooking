package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datahandlers.AdminTicketDataHandler;

/**
 * Servlet implementation class UpdatePricesServlet
 */
@WebServlet("/UpdatePricesServlet")
public class UpdatePricesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePricesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		AdminTicketDataHandler dh = new AdminTicketDataHandler("cinema", "root", "root");
		System.out.println(Double.parseDouble(request.getParameter("Child")));
		double childPrice = Double.parseDouble(request.getParameter("Child"));
		dh.updatePrices(childPrice, "Child");
		double adultPrice = Double.parseDouble(request.getParameter("Adult"));
		dh.updatePrices(adultPrice, "Adult");
		double seniorPrice = Double.parseDouble(request.getParameter("Senior"));
		dh.updatePrices(seniorPrice, "Senior");
		String destination = "admin.jsp";
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

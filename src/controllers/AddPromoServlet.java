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

import datahandlers.AdminUserDataHandler;
import datahandlers.PromoAdminDataHandler;
import mail.Mail;

/**
 * Servlet implementation class AddMovieServlet
 */
	@WebServlet({ "/AddPromoServlet", "/AddPromo" })
	public class AddPromoServlet  extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public AddPromoServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String expDate = request.getParameter("expDate");
			String code = request.getParameter("code");
			double percent = Double.parseDouble(request.getParameter("percent"));
			response.getWriter().append("Served at: ").append(request.getContextPath());
			PromoAdminDataHandler dh = new PromoAdminDataHandler("cinema", "root", "root");
			AdminUserDataHandler dh2 = new AdminUserDataHandler("cinema", "root", "root");
			dh.createPromo(code, percent, expDate);
			
			ResultSet results = dh.getPromos();
			ResultSet results2 = dh2.getUsers();
			try {
				while (results2.next()) {
					if (results2.getString("enrollForPromotions").equalsIgnoreCase("True")) {
						Mail.sendPromo(results2.getString("email"),code, percent, expDate);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("results", results);
			String destination = "ViewPromos";
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

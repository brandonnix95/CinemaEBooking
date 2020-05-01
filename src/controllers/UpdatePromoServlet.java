package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datahandlers.PromoAdminDataHandler;

/**
 * Servlet implementation class UpdatePromoServlet
 */
@WebServlet({ "/UpdatePromoServlet", "/UpdatePromo" })
public class UpdatePromoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePromoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String expDate = request.getParameter("expDate");
		String promoID = request.getParameter("promoID");
		String code = request.getParameter("code");
		double percent = Double.parseDouble(request.getParameter("percent"));
		PromoAdminDataHandler dh = new PromoAdminDataHandler("cinema", "root", "root");
		dh.updatePromo(promoID, code, percent, expDate);
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

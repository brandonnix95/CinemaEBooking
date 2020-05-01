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

/**
 * Servlet implementation class SuspendServlet
 */
@WebServlet({ "/SuspendServlet", "/suspend" })
public class SuspendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuspendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userID = Integer.parseInt(request.getParameter("userID"));
		response.getWriter().append("Served at: ").append(request.getContextPath());
		AdminUserDataHandler dh = new AdminUserDataHandler ("cinema", "root", "root");
		ResultSet status = dh.getUser(userID);
		try {
			while (status.next()) {
				if (status.getString("Status").equalsIgnoreCase("SUSPENDED")) {
					dh.unSuspend(userID);
				}
				else { 
					dh.Suspend(userID);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String destination ="getUsers";
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

package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datahandlers.ResetPasswordData;

/**
 * Servlet implementation class UpdatePasswordServlet
 */
@WebServlet({ "/UpdatePasswordServlet", "/UpdatePass" })
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String code = request.getParameter("code");
		System.out.println(code);
		boolean passMatch = false;
		String destination ="resetPassword.jsp";
		if (password.equals(password2)) {
			passMatch = true;
			destination = "changedPassword.jsp";
			ResetPasswordData reset = new ResetPasswordData("cinema", "root","root");
			reset.resetPassword(code, password2);
		}
		else {
			destination = "resetPassword.jsp";
		}
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

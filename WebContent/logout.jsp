<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "javax.servlet.http.HttpServlet"%>
<%@ page import= "javax.servlet.http.HttpServletRequest"%>
<%@ page import= "javax.servlet.http.HttpServletResponse"%>

<%@ page session="false"%>
<%
	if(request.getSession(false) != null) request.getSession().invalidate(); 
	response.sendRedirect("homepage.jsp");
%>
<body></body>
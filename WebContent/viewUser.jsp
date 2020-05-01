<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.sql.ResultSet" %>
<%@ page import= "javax.servlet.http.HttpServlet"%>
<%@ page import= "javax.servlet.http.HttpServletRequest"%>
<%@ page import= "javax.servlet.http.HttpServletResponse"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="#" rel="stylesheet">
<title>View User</title>
</head>
<body>
<%
	String destination;
	String text;
	if(session.getAttribute("handler") != null) {
		destination = "editProfile.jsp";
		text = "Account";
	} else {
		destination = "login.jsp";
		text = "Sign in";
	} // if/else
	
	ResultSet results = (ResultSet) request.getAttribute("results");
%>
 <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="css/homepage.css" rel="stylesheet">

        <link rel="icon" href="clapperboard.png">
    </head>
    <body class="bg-light">
        <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
            <img src="clapperboard.png" width="50" height="50" class="mr-3">
            <h5 class="my-0 mr-md-auto font-weight-normal">Cinema E-Booking</h5>
            <nav class="my-2 my-md-0 mr-md-3">
                <a class="p-2 text-dark" href="homepage.jsp">Home</a>
                <a class="p-2 text-dark" href="searchMovies.jsp">See A Movie</a>
                <a class="p-2 text-dark" href="#">Our Theatres</a>
                <a class="p-2 text-dark" href="#">Extras</a>
            </nav>
            <a class="btn btn-outline-secondary mr-3" href="#">Cart</a>
            <a class="btn btn-outline-primary" href='<%=destination%>'><%=text%></a>
        </div>
        <h1> User Info</h1>
        
        <div class="container text-center mt-4">
<% while (results.next()) { %>
<p> User ID: <%=results.getInt("userID")%></p>
<p> First Name: <%=results.getString("firstName") %></p>
<p> Last Name: <%=results.getString("lastName") %> </p>
<p> Phone: <%=results.getString("phone") %></p>
<p> Status: <%=results.getString("status") %></p>
<p> Enrolled for Promotions: <%=results.getString("enrollForPromotions") %>
<p> User Type (1 = Customer, 2 = Admin, 3 = Employee): <%=results.getInt("userType") %></p>
<p> Address: <%=results.getString("address") %> </p>
<p> Address 2: <%=results.getString("addressSuite") %>
<p> City: <%=results.getString("city") %> </p>
<p> State: <%=results.getString("state") %> </p>
<p> ZIP: <%=results.getString("zip") %> </p>



<%} %>
<form action = "getUsers" method = "GET"><input type = submit Value = "Back to Users"></form>


</body>
</html>
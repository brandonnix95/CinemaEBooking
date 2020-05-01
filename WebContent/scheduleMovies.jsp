<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.sql.ResultSet" %>
<%@ page import= "javax.servlet.http.HttpServlet"%>
<%@ page import= "javax.servlet.http.HttpServletRequest"%>
<%@ page import= "javax.servlet.http.HttpServletResponse"%>
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
%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="#" rel="stylesheet">

        <title>Movies</title>
        <link rel="icon" href="clapperboard.png">
    </head>
    <% 
    ResultSet rs = (ResultSet) request.getAttribute("results"); 
    int movieID = (Integer) request.getAttribute("movieID");
    %>
    <body class="bg-light">
        <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
            <img src="clapperboard.png" width="50" height="50" class="mr-3">
            <h5 class="my-0 mr-md-auto font-weight-normal">Cinema E-Booking</h5>
            <nav class="my-2 my-md-0 mr-md-3">
                <a class="p-2 text-dark" href="homepage.jsp">Home</a>
                <a class="p-2 text-dark" href="searchMovies.jsp">See A Movie</a>
                <a class="p-2 text-dark" href="#">Our Theatres</a>
                <a class="p-2 text-dark" href="admin.jsp">Extras</a>
            </nav>
            <a class="btn btn-outline-secondary mr-3" href="#">Cart</a>
        </div>

        <div class="container text-center">

            <h1>Shows</h1>
            <p class "lead"> If your newly added/edited show does not appear, there is another show scheduled for same time/date!</p>
            <p> If you accidentally delete, you can backspace to undo. Press the button at the bottom when finished.</p>

            <div class="row">
                <div class="col-md-2 mb-3">
                    <a class="btn btn-primary btn-md btn-block" href="GetTimesServlet?movieID=<%=movieID%>">Add Show</a>
                </div>
            </div>

            <table class="table">
                <thead>
                    <tr>
                    <th scope="col">Show#</th>
                    <th scope="col">Start Time</th>
                    <th scope="col">End Time</th>
                    <th scope = "col">Date</th>
                    <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                 <% String startTime = "";
                 while (rs.next()) {
					
							switch (rs.getInt("startID")) {
							case 1:
								startTime = "12:00";
								break;
							case 2:
								startTime = "16:15";
								break;
							case 3: 
								startTime = "20:30";
								break;
							default: 
								startTime = "No time found.";	
							} %>
                 <tr name = i>
                    <th scope="row"><%=rs.getInt("showID")%></th>
                    <td><%=startTime%></td>
                    <td><%=rs.getString("endTime")%></td>
                    <td><%=rs.getDate("Date") %>
                    <td> 
                        <a class="btn btn-primary btn-sm" href ="GetTimesEditServlet?showID=<%=rs.getInt("showID")%>&movieID=<%=rs.getInt("movieID")%>">Edit</a>
                        <a class="btn btn-danger btn-sm" href ="DeleteShow?showID=<%=rs.getInt("showID")%>&movieID=<%=rs.getInt("movieID")%>">Delete</a>
                    </td>
                    </tr>
                <%} %>
                    </tbody>
                    </table>
                    <form action = "Manage" method = "GET">
                    <input type = "submit" value = "Back to movies">
                    </form>
                    
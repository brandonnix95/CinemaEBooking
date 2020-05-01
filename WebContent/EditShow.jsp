<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "javax.servlet.http.HttpServlet"%>
<%@ page import= "javax.servlet.http.HttpServletRequest"%>
<%@ page import= "javax.servlet.http.HttpServletResponse"%>
<%@ page import= "java.sql.ResultSet" %>
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
        <link href="css/editProfile.css" rel="stylesheet">

        <title>Edit Show</title>
        <link rel="icon" href="clapperboard.png">
    </head>
    <%
    ResultSet starts = (ResultSet) request.getAttribute("starts");
    ResultSet results = (ResultSet) request.getAttribute ("results");
    int movieID = (Integer) request.getAttribute("movieID");
    int showID = (Integer) request.getAttribute("showID");
    %>
     <body >
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
            <a class="btn btn-outline-primary" href="logout.jsp">Logout</a>
        </div>
        <div class="container">
            <div class="py-5 text-center">
                <img class="mb-4" src="clapperboard.png" alt="" width="72" height="72">
                <h2>Edit Movie</h2>
                 <p class="lead">Please enter a value for each field.</p>
                  <p class "lead"> If your newly edited show does not appear, there is another show scheduled for same time/date!</p>
            </div>
           <form class="needs-validation" action = "EditShow">
                <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName">Show Start</label>
                     <select name="startTime" required>
  <% while (starts.next())  { %>
  <option value='<%=starts.getString(1)%>'><%=starts.getString(1) %></option>
  <%} %>
</select> 
                    <div class="invalid-feedback">
                        Show start time is required.
                    </div>
                    </div>
                    </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName">Show Date</label><br/>
                    <%while (results.next()) { %>
                      <input type="date" class="form-control" name ="date" id="movieName" placeholder="" value='<%=results.getDate("date")%>'>
                      <%} %>
                      <input type = "hidden" name = "movieID" value = '<%=movieID %>'>
                      <input type = "hidden" name = "showID" value = '<%=showID %>'>
                      </div>
                      </div>
                       <button class="btn btn-lg btn-primary btn-block" type="submit" value="submit">Submit Changes</button>
                    	 </form>
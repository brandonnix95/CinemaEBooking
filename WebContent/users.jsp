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
		ResultSet results = (ResultSet) request.getAttribute("results");
	ResultSet results2 = (ResultSet) request.getAttribute("results2");
%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="#" rel="stylesheet">

        <title>Users</title>
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
                <a class="p-2 text-dark" href="admin.jsp">Extras</a>
            </nav>
            <a class="btn btn-outline-secondary mr-3" href="#">Cart</a>
            <a class="btn btn-outline-primary" href='<%=destination%>'><%=text%></a>
        </div>

        <div class="container text-center">

            <h1>Users</h1>
<form action ="AddEmp" method = "POST">
            <div class="row">
                <div class="col-md-2 mb-3">
                <label for="userID">User ID</label>
                <select name = "id" class="form-control" id="userID" placeholder="" >
                <% while (results.next()) { %>
                <option value = '<%=results.getInt("userID")%>'> <%=results.getInt("userID") %></option>
                <%} %>
                </select>
                
                </div>
                <div class="col-md-2 mb-3">
                    <br>
                    <input type = "submit" class="btn btn-primary btn-md btn-block" name = id value = "Make Employee">
                </div>
            </div>
            </form>

            <table class="table">
                <thead>
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">Last Name</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Status</th>
                    <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                <%while (results2.next()) {%>
                    <tr>
                    <th scope="row"><%=results2.getInt("UserID") %></th>
                    <td><%=results2.getString("lastName") %></td>
                    <td><%=results2.getString("firstName") %></td>
                    <td><%=results2.getString("status") %> </td>
                    <td>
                    <% if (results2.getString("status").equalsIgnoreCase("SUSPENDED")) { %>
                        <a class="btn btn-warning btn-sm" href="suspend?userID=<%=results2.getInt("UserID")%>">Unsuspend</a>
                        <% } else {%>
                        <a class="btn btn-secondary btn-sm" href="suspend?userID=<%=results2.getInt("UserID")%>">Suspend</a>
                        <%} %>
                        <a class="btn btn-primary btn-sm" href="ViewUser?userID=<%=results2.getInt("UserID")%>">View</a>
                        <a class="btn btn-danger btn-sm" href="DeleteUser?userID=<%=results2.getInt("UserID")%>">Delete</a>
                    </td>
                    <%} %>
                    </tr>
                   
                </tbody>
            </table>
            <p><i>All user attributes can be seen in the "View" page.</i></p>
            <br>

        </div>
        
        <div class="container">
            <footer class="pt-4 my-md-5 border-top">
                <div class="row">
                    <div class="col-12 col-md">
                    <img class="mb-2" src="clapperboard.png" alt="" width="24" height="24">
                    <small class="d-block mb-3 text-muted">&copy; Section B - Team 2</small>
                    </div>
                    <div class="col-6 col-md">
                    <h5>Our Company</h5>
                    <ul class="list-unstyled text-small">
                        <li><a class="text-muted" href="#">Our Brands</a></li>
                        <li><a class="text-muted" href="#">Contact Us</a></li>
                        <li><a class="text-muted" href="#">FAQs</a></li>
                        <li><a class="text-muted" href="#">Corporate Information</a></li>
                        <li><a class="text-muted" href="#">Careers</a></li>
                        <li><a class="text-muted" href="#">Terms & Conditions</a></li>
                    </ul>
                    </div>
                    <div class="col-6 col-md">
                    <h5>Movies</h5>
                    <ul class="list-unstyled text-small">
                        <li><a class="text-muted" href="#">Movies</a></li>
                        <li><a class="text-muted" href="#">Theatres</a></li>
                        <li><a class="text-muted" href="#">Ratings Information</a></li>
                        <li><a class="text-muted" href="#">IMAX</a></li>
                    </ul>
                    </div>
                    <div class="col-6 col-md">
                    <h5>More</h5>
                    <ul class="list-unstyled text-small">
                        <li><a class="text-muted" href="#">Offers & Promotions</a></li>
                        <li><a class="text-muted" href="#">Gift Cards</a></li>
                        <li><a class="text-muted" href="#">Mobile App</a></li>
                        <li><a class="text-muted" href="#">Request Refund</a></li>
                    </ul>
                    </div>
                </div>
            </footer>
        </div>
    </body>
</html>
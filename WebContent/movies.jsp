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
    ResultSet rs = (ResultSet) request.getAttribute("movies");
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

            <h1>Movies</h1>
            <p> You must delete all showtimes before deleting a movie.</p>

            <div class="row">
                <div class="col-md-2 mb-3">
                    <a class="btn btn-primary btn-md btn-block" href="ListFetch">Add Movie</a>
                </div>
            </div>

            <table class="table">
                <thead>
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">Title</th>
                    <th scope="col">Genre</th>
                    <th scope="col">Cast</th>
                    <th scope="col">Reviews</th>
                    <th scope="col">Rating</th>
                    <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                <%String genre = "";
                String rating ="";
                while (rs.next()) {
                	switch (rs.getInt("categoryID")) {
                	case 1:
    					genre = "Action";
    					break;
    				case 2:
    					genre = "Sci-Fi";
    					break;
    				case 3: 
    					genre = "Horror";
    					break;
    				case 4:
    					genre = "Comedy";
    					break;
    				case 5: 
    					genre = "Drama";
    					break;
    				default: 
    					genre = "No genre found.";	
    				}
                	switch(rs.getInt("ratingID")) {
                	case 1:
    					rating = "G";
    			        break;
    				case 2:
    					rating = "PG";
    					break;
    				case 3: 
    					rating = "PG-13";
    					break;
    				case 4:
    					rating = "R";
    					break;
    				case 5:
    					rating = "NC-17";
    					break;
    				case 6:
    					rating = "NR";
    					break;
    				case 7:
    					rating = "TBR";
    					break;
    				default:
    					rating = "TBR";
                	}
                	
                %>
                    <tr name = i>
                    <th scope="row"><%=rs.getInt("movieID")%></th>
                    <td><%=rs.getString("movieTitle").trim()%></td>
                    <td><%=genre%></td>
                    <td><%=rs.getString("cast").trim() %></td>
                    <td><%=rs.getString("reviews").trim() %></td>
                    <td><%=rating%></td>
                    <td> 
                        <a class="btn btn-secondary btn-sm" href ="GetMovie?movieID=<%=rs.getInt("movieID")%>">Edit</a>
                        <a class="btn btn-primary btn-sm" href ="ShowFetch?movieID=<%=rs.getInt("movieID")%>">Schedule</a>
                        <a class="btn btn-danger btn-sm" href ="DeleteMovie?movieID=<%=rs.getInt("movieID")%>">Delete</a>
                    </td>
                    </tr>
                      <%}%>
                </tbody>
            </table>
            <p><i>All movie attributes can be seen in the "View" and "Edit" pages.</i></p>
            <p><i>"Add Movie" will redirect the system to a generic form.</i></p>
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
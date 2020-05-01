<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "movies.Movie"%>
<%@ page import= "movies.Showtime"%>

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
	
	Movie movie = (Movie) session.getAttribute("movie");
%>
    
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="css/homepage.css" rel="stylesheet">

        <title>Search</title>
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
        
        <div class="container text-center mt-4">

            <div class="row">

                <div class="col-md-4">

                    <iframe class="" width="350" height="500" src='<%=movie.getTrailer()%>'></iframe>

                </div>

                <div class="col-md-8 text-left">
                
                	<h1><i><%=movie.getTitle()%></i></h1><br>
                	
                	<div class="row">
                	
                	<div class="col-md-6">
                
	                <h4>Category</h4>
					<p><%=movie.getCategory().name%></p>
					
					<h4>Cast</h4>
					<p><%=movie.getCast()%></p>
					
					<h4>Director</h4>
					<p><%=movie.getDirector()%></p>
					
					<h4>Synopsis</h4>
					<p><%=movie.getSynopsis()%></p>
					
					</div>
					<div class="col-md-6">
					
					<h4>Reviews</h4>
					<p><a href='<%=movie.getReviews()%>'><%=movie.getReviews()%></a></p>
					
					<h4>Rating</h4>
					<p><%=movie.getRating().name%></p>
					
					<h4>Length</h4>
					<p><%=movie.getLength() + " mins"%></p>
					
					</div>
					</div>
					<br>

					<% if(movie.isShowing()) { %>
                    <table class="table">
                        <thead>
                            <tr>
                            <th scope="col">Date</th>
                            <th scope="col">Start Time</th>
                            <th scope="col">End Time</th>
                            </tr>
                        </thead>
                        <% for(Showtime showtime: movie.getShowtimes()) { %>
                        <tbody>
                            <tr>
                            <td><%=showtime.getDate().toString()%></td>
                            <td><%=showtime.getStartTime()%></td>
                            <td><%=showtime.getEndTime()%></td>
                            </tr>
                        </tbody>
                        <% } %>
                    </table>
                    
                    <br>
                    <form action="BookMovieServlet" method=POST>
                    	<button type="submit" name="bookBtn" value='<%=movie.getTitle()%>' class="btn btn-primary btn-lg">Book</button>
                    </form>
                    <% } %>

                </div>

            </div>

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
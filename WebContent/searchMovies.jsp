<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "entityhandlers.UserSessionHandler"%>
<%@ page import= "controllers.MovieQueryServlet"%>
<%@ page import= "movies.Movie"%>
<%@ page import= "java.util.List"%>
<%@ page import= "java.util.ArrayList"%>
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
	
	List<Movie> query;		

	if(session.getAttribute("query") != null) {
		query = (List<Movie>) session.getAttribute("query");
	} else {
		query = new ArrayList<Movie>();
	}
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
                <a class="p-2 text-dark" href="admin.jsp">Extras</a>
            </nav>
            <a class="btn btn-outline-secondary mr-3" href="#">Cart</a>
            <a class="btn btn-outline-primary" href='<%=destination%>'><%=text%></a>
        </div>

        <div class="text-center"><h1>See A Movie</h1></div>

		<form class="form-signin" action="MovieQueryServlet" method=POST>
        <div class="container">
            <div class="row">
                <div class="col-md-4 mb-3">
                <label for="title">Title</label>
                <input type="text" class="form-control" id="title" placeholder="" name="title">
                </div>
                <!-- <div class="col-md-2 mb-3">
                <label for="date">Date</label>
                <select class="custom-select d-block w-100" id="date" name="date">
                    <option value="">Choose...</option>
                    <option value="today">Today</option>
                    <option value="tomorrow">Tomorrow</option>
                </select>
                <div class="invalid-feedback">
                    Please select a valid date.
                </div>
                </div> -->
                <div class="col-md-2 mb-3">
                <label for="category">Category</label>
                <select class="custom-select d-block w-100" id="category" name="category">
                    <option value="">Choose...</option>
                    <option value="Action">Action</option>
                    <option value="Sci-Fi">Sci-Fi</option>
                    <option value="Horror">Horror</option>
                    <option value="Comedy">Comedy</option>
                    <option value="Drama">Drama</option>
                </select>
                <div class="invalid-feedback">
                    Please select a valid time.
                </div>
                </div>
                <div class="col-md-2 mb-3">
                <label for="category">Availability</label>
                <select class="custom-select d-block w-100" id="category" name="availability">
                    <option value="">Choose...</option>
                    <option value="now">Playing now</option>
                    <option value="soon">Coming soon</option>
                </select>
                <div class="invalid-feedback">
                    Please select a valid time.
                </div>
                </div>
                <div class="col-md-2 mb-3">
                	<br>
                    <button class="btn btn-lg btn-primary btn-block" type="submit" value="submit">Filter</button>
                </div>
            </div>
        </div>
        </form>

        <div class="album py-5 bg-light">
            <div class="container">
                <div class="row">
                <%
                	for(Movie movie: query) {
                %>
                    <div class="col-md-4">
                        <div class="card mb-4 box-shadow">
                        <iframe class="card-img-top" width="640" height="360" src="<%=movie.getTrailer()%>"></iframe>
                        <div class="card-body">
                        	<h3><%=movie.getTitle()%></h3>
                            <p><i><%=movie.getCategory().name%></i></p>
                            <p class="card-text"><%=movie.getSynopsis()%></p>
                            <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <form action="ViewMovieInfoServlet" method=POST>
                                	<button type="submit" name="viewBtn" value='<%=movie.getTitle()%>' class="btn btn-sm btn-outline-secondary">View</button>
                                </form>
                                <% if(movie.isShowing()) { %>
                                <form action="BookMovieServlet" method=POST>
                                	<button type="submit" name="bookBtn" value='<%=movie.getTitle()%>' class="btn btn-sm btn-outline-secondary">Book</button>
                                </form>
                                <% } %>
                            </div>
                            <small class="text-muted"><%=movie.getLength()%></small>
                            </div>
                        </div>
                        </div>
                    </div>
                <%
            		}
                %>
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
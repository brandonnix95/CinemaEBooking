<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import= "entityhandlers.UserSessionHandler"%>
<%@ page import= "java.util.List"%>
<%@ page import= "movies.Movie"%>
<%@ page import= "movies.Category"%>

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
	
	UserSessionHandler.registerQuery(session, "", "", Category.ALL, "");
	List<Movie> query = (List<Movie>) session.getAttribute("query");
%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="css/homepage.css" rel="stylesheet">

        <title>Homepage</title>
        <link rel="icon" href="clapperboard.png">
    </head>
    <body class="bg-light">
        <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
            <img src="clapperboard.png" width="50" height="50" class="mr-3">
            <h5 class="my-0 mr-md-auto font-weight-normal">Cinema E-Booking</h5>
            <nav class="my-2 my-md-0 mr-md-3">
                <a class="p-2 text-dark" href="homepage.jsp">Home</a>
                <a class="p-2 text-dark" href="#">Our Theatres</a>
                <a class="p-2 text-dark" href="admin.jsp">Extras</a>
            </nav>
            <a class="btn btn-outline-secondary mr-3" href="#">Cart</a>
            <a class="btn btn-outline-primary" href='<%=destination%>'><%=text%></a>
        </div>

        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading"><i>Mainpage</i></h1>
                <p class="lead text-muted">This jumbotron will be used for annoucements.</p>
                <form action="MovieQueryServlet" method=POST>
                	<button class="btn btn-lg btn-primary my-2" type="submit" value="submit">Search Movies</button>
                </form>
            </div>
        </section>

        <div class="album py-5 bg-light">
            <div class="container">
                <h1 class="text-center"><i>Showing Now</i></h1>
                <div class="row">
                <%
                	for(Movie movie: query) {
                		if (movie.isShowing()) {
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
                                <form action="BookMovieServlet" method=POST>
                                	<button type="submit" name="bookBtn" value='<%=movie.getTitle()%>' class="btn btn-sm btn-outline-secondary">Book</button>
                                </form>
                            </div>
                            <small class="text-muted"><%=movie.getLength() + " mins"%></small>
                            </div>
                        </div>
                        </div>
                    </div>
                <%
                		}
                	}
          		%>
                </div>
                
                <h1 class="text-center"><i>Coming Soon</i></h1>
                <div class="row">
                <%
                	for(Movie movie: query) {
                		if (!movie.isShowing()) {
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
                            </div>
                            <small class="text-muted"><%=movie.getLength() + " mins"%></small>
                            </div>
                        </div>
                        </div>
                    </div>
               	<%
                		}
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
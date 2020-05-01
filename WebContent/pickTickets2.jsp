<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "movies.Movie"%>
<%@ page import= "movies.Showtime"%>
<%@ page import= "movies.Seat"%>
<%@ page import= "booking.Ticket"%>
<%@ page import= "java.util.List"%>
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
	Showtime showtime = (Showtime) session.getAttribute("showtime");
	List<Seat> seats = showtime.getSeats();
	List<Ticket> tickets = (List<Ticket>) session.getAttribute("tickets");
%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        
        <title>Tickets</title>
        <link rel="icon" href="clapperboard.png">
    </head>
    <body class="bg-light">
        <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
            <img src="clapperboard.png" width="50" height="50" class="mr-3">
            <h5 class="my-0 mr-md-auto font-weight-normal">Cinema E-Booking</h5>
            <nav class="my-2 my-md-0 mr-md-3">
                <a class="p-2 text-dark" href="homepage.jsp">Home</a>
                <a class="p-2 text-dark" href="searchMovies.jsp">See A Movie</a>
                <a class="p-2 text-dark" href="#">Extras</a>
            </nav>
            <a class="btn btn-outline-secondary mr-3" href="#">Cart</a>
            <a class="btn btn-outline-primary" href='<%=destination%>'><%=text%></a>
        </div>

        <div class="container text-center">
            <h1>Ticket(s) for <%=movie.getTitle()%></h1>
            <h3>at <%=showtime.getDate().toString()%>: <%=showtime.getStartTime()%></h3>
            <img src="res/SeatingChart.png">
            <br><br>
            
            <% if (tickets != null) {
           	 	for(Ticket ticket: tickets) { %>
               		<div class="row">
               		<div class="col-md-1"></div>
                    <div class="col-md-3 pt-4">
                        <h4>Seat <%=ticket.getSeatNumber()%></h4>
                    </div>
                    <div class="col-md-3 pt-4">
                        <h4><%=ticket.getType().name%></h4>
                    </div>
                    <div class="col-md-2 pt-4">
                        <h4>$<%=ticket.getPrice()%>0</h4>
                    </div>
                    <div class="col-md-1">
                        <br>
                        <form action="DeleteTicketListServlet" method=POST>
                        <button type="submit" name="ticketBtn" value="<%=ticket.getSeatNumber()%>" class="btn btn-danger btn-md">Delete</button>
                        </form>
                    </div>
            		</div>
            		<hr>
            <% 	} 
           	 }%>
            
           	<form action="TicketListServlet" method=POST>
            <div class="row">
                    <div class="col-md-3"><br><h2>New Seat</h2></div>
                    <div class="col-md-3">
                        <label for="seatNumber">Seat Number</label>
                        <select class="custom-select d-block w-100" id="seatnumber" name="seatnumber">
                        <% for(Seat seat: seats) { 
                        		if (seat.isAvailible()) {%>
                            		<option value='<%=seat.getSeatNumber()%>'><%=seat.getSeatNumber()%></option>
                        <%  	}
                        	} %>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label for="age">Age</label>
                        <select class="custom-select d-block w-100" id="tickettype" name="tickettype">
                        <option value='Child'>Child</option>
                        <option value='Adult'>Adult</option>
                        <option value='Senior'>Senior</option>
                        </select>
                    </div>
                    <div class="col-md-1">
                        <br>
                        <button type="submit" name="ticketBtn" class="btn btn-secondary btn-md">Add</button>
                    </div>
            </div>
            </form>

            <p><i>Another seat can be added after clicking the "Add" seat button.</i></p>
            <br>
            <% if (tickets != null) { %>
            	<a class="btn btn-primary btn-lg" href="orderSummary.jsp">Continue</a>
            <% } %>

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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "movies.Movie"%>
<%@ page import= "movies.Showtime"%>
<%@ page import= "movies.Seat"%>
<%@ page import= "booking.Ticket"%>
<%@ page import= "booking.Promotion"%>
<%@ page import= "users.Address"%>
<%@ page import= "users.Card"%>
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
	double total = Ticket.getTotal(tickets);
	double subtotal = Ticket.getSubtotal(tickets);
	
	String firstName = (String) session.getAttribute("firstName");
	String lastName = (String) session.getAttribute("lastName");
	String email = (String) session.getAttribute("email");
	Address address = (Address) session.getAttribute("address");
	Card card = (Card) session.getAttribute("card");
	
	String month = "";
	if(card != null) {
		month = "" + card.getExp().getMonth();
		if(month.length() == 1) month = "0" + month;
	} // if
	
	Promotion promotion = null;
	boolean invalid = false;
	double difference = 0;
	if(session.getAttribute("promotion") instanceof Promotion) {
		promotion = (Promotion) session.getAttribute("promotion");
		difference = Math.round(total * promotion.getPercent() * 100.0) / 100.0;
		total -= difference;
	} else if(session.getAttribute("promotion") instanceof String) {
		invalid = true;
	}
%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="#" rel="stylesheet">

        <title>Checkout</title>
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

        <div class="container">
        <div class="container text-center"><h1>Checkout</h1><br></div>    
        <div class="row">
            <div class="col-md-4 order-md-2 mb-4">
              <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">Your cart</span>
              </h4>
              <ul class="list-group mb-3">
              <% int ticketNumber = 1;
                    for(Ticket ticket: tickets) { %>
                <li class="list-group-item d-flex justify-content-between lh-condensed">
                  <div>
                    <h6 class="my-0">Ticket <%=ticketNumber%></h6>
                    <small class="text-muted">Seat <%=ticket.getSeatNumber()%> - <%=ticket.getType().name%></small>
                  </div>
                  <span class="text-muted">$<%=ticket.getPrice()%>0</span>
                </li>
                <% 	ticketNumber++; } %>
                <li class="list-group-item d-flex justify-content-between lh-condensed">
                  <div>
                    <h6 class="my-0">Subtotal</h6>
                  </div>
                  <span class="text-muted">$<%=subtotal%>0</span>
                </li>
                <li class="list-group-item d-flex justify-content-between lh-condensed">
                  <div>
                    <h6 class="my-0">Tax</h6>
                  </div>
                  <span class="text-muted">$<%=(Math.round(subtotal*Ticket.TAX_RATE * 100.0) / 100.0)%></span>
                </li>
                <% if (!invalid && promotion != null) { %>
                <li class="list-group-item d-flex justify-content-between bg-light">
                  <div class="text-success">
                    <h6 class="my-0">Promo code</h6>
                    <small><%=promotion.getCode()%></small>
                  </div>
                  <span class="text-success">-$<%=difference%></span>
                </li>
                <% } %>
                <li class="list-group-item d-flex justify-content-between">
                  <span>Total (USD)</span>
                  <strong>$<%=total %></strong>
                </li>
              </ul>
        
              <form class="card p-2" action="ApplyPromotionServlet" method=POST>
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="Promo code" name="promocode">
                  <div class="input-group-append">
                    <button type="submit" class="btn btn-secondary" >Redeem</button>
                    <% if (invalid) { %>
                    <p class="text-danger">Promo entered is not valid.</p>
                    <% } %>
                  </div>
                </div>
              </form>

              <br>
              <a class="btn btn-danger btn-lg btn-block" href="homepage.jsp">Cancel Order</a>
            </div>
            <div class="col-md-8 order-md-1">
              <h4 class="mb-3">Billing Info</h4>
              <form class="needs-validation" action="CheckoutServlet" method=POST novalidate>
                <div class="row">
                  <div class="col-md-6 mb-3">
                    <label for="firstName">First name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" value='<%=firstName%>' required>
                  </div>
                  <div class="col-md-6 mb-3">
                    <label for="lastName">Last name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="" value='<%=lastName%>' required>
                  </div>
                </div>
        
                <div class="mb-3">
                  <label for="email">Email</label>
                  <input type="email" class="form-control" id="email" name="email" value='<%=email%>' required>
                </div>
        
        		<% if (address != null) {%>
        
                <div class="mb-3">
                  <label for="address">Address</label>
                  <input type="text" class="form-control" id="address" name="address" value='<%=address.getAddress()%>' required>
                </div>
        
                <div class="mb-3">
                  <label for="address2">Address 2</label>
                  <input type="text" class="form-control" id="address2" name="address2" placeholder="Apartment or suite" value='<%=address.getSuite()%>'>
                </div>
        
                <div class="row">
                    <div class="col-md-5 mb-3">
                    <label for="city">City</label>
                    <input type="text" class="form-control" id="city" name="city" placeholder="City" value='<%=address.getCity()%>'>
                    </div>
                  <div class="col-md-4 mb-3">
                    <label for="state">State</label>
                    <input type="text" class="form-control" id="state" name="state" placeholder="State" value='<%=address.getState()%>'>
                  </div>
                  <div class="col-md-3 mb-3">
                    <label for="zip">Zip</label>
                    <input type="number" class="form-control" id="zip" name="zip" max="99999" placeholder="#####" value='<%=address.getZip()%>'>
                  </div>
                </div>
                <% } else { %>
                
                <div class="mb-3">
                  <label for="address">Address</label>
                  <input type="text" class="form-control" id="address" name="address" placeholder="1234 Main St" required>
                </div>
        
                <div class="mb-3">
                  <label for="address2">Address 2</label>
                  <input type="text" class="form-control" id="address2" name="address2" placeholder="Apartment or suite">
                </div>
        
                <div class="row">
                    <div class="col-md-5 mb-3">
                    <label for="city">City</label>
                    <input type="text" class="form-control" id="city" name="city" placeholder="City">
                    </div>
                  <div class="col-md-4 mb-3">
                    <label for="state">State</label>
                    <input type="text" class="form-control" id="state" name="state" placeholder="State">
                  </div>
                  <div class="col-md-3 mb-3">
                    <label for="zip">Zip</label>
                    <input type="number" class="form-control" id="zip" name="zip" max="99999" placeholder="#####">
                  </div>
                </div>
                
                <% } %>
                <hr class="mb-4">
        
                <h4 class="mb-3">Payment</h4>
        
                <% if (card != null) {%>
        
                <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="cc-name">Name on card</label>
                    <input type="text" class="form-control" id="cc-name" name="cc-name" placeholder="" value='<%=card.getName()%>'>
                    <small class="text-muted">Full name as displayed on card</small>
                    </div>
                    <div class="col-md-6 mb-3">
                    <label for="cc-number">Credit card number</label>
                    <input type="text" class="form-control" id="cc-number" name="cc-number" placeholder="" value='<%=card.getNumber()%>'>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 mb-3 mr-5">
                    <label for="cc-expiration">Expiration</label>
                    <input type="month" class="form-control" id="exp-month" name="exp-month" style="width:200px" placeholder="" value='<%=card.getExp().getYear() + "-" + month%>'> 
                    </div>
                    <div class="col-md-3 mb-3">
                    <label for="cc-cvv">CVV</label>
                    <input type="number" class="form-control" id="cvv" name="cvv" max="9999" placeholder="###" value='<%=card.getCvv()%>'>
                    </div>
                    <div class="col-md-3 mb-3">
                    <label for="cc-zip">Zip</label>
                    <input type="number" class="form-control" id="cc-zip" name="cc-zip" max="99999" placeholder="#####" value='<%=card.getZip()%>'>
                    </div>
                </div>
                
                <% } else { %>
                
                <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="cc-name">Name on card</label>
                    <input type="text" class="form-control" id="cc-name" name="cc-name" placeholder="" >
                    <small class="text-muted">Full name as displayed on card</small>
                    </div>
                    <div class="col-md-6 mb-3">
                    <label for="cc-number">Credit card number</label>
                    <input type="text" class="form-control" id="cc-number" name="cc-number" placeholder="" >
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 mb-3 mr-5">
                    <label for="cc-expiration">Expiration</label>
                    <input type="month" class="form-control" id="exp-month" name="exp-month" style="width:200px" placeholder="" > 
                    </div>
                    <div class="col-md-3 mb-3">
                    <label for="cc-cvv">CVV</label>
                    <input type="number" class="form-control" id="cvv" name="cvv" max="9999" placeholder="###" >
                    </div>
                    <div class="col-md-3 mb-3">
                    <label for="cc-zip">Zip</label>
                    <input type="number" class="form-control" id="cc-zip" name="cc-zip" max="99999" placeholder="#####" >
                    </div>
                </div>
                
                <% } %>
                <hr class="mb-4">
                <button type="submit" class="btn btn-primary btn-lg btn-block">Complete Order</button>
              </form>
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
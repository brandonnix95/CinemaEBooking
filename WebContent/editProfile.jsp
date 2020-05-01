<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "javax.servlet.http.HttpServlet"%>
<%@ page import= "javax.servlet.http.HttpServletRequest"%>
<%@ page import= "javax.servlet.http.HttpServletResponse"%>
<%@ page import= "entityhandlers.UserSessionHandler"%>
<%@ page import= "users.Address"%>
<%@ page import= "users.Card"%>
<%@ page import= "booking.Booking"%>
<%@ page import= "java.util.List"%>
<%@ page import= "java.util.ArrayList"%>

<%
String destination;
String text;
List<Booking> bookings = new ArrayList<Booking>();
if(session.getAttribute("handler") != null) {
	destination = "editProfile.jsp";
	text = "Account";
	
	UserSessionHandler handler = (UserSessionHandler) session.getAttribute("handler");
	bookings = handler.getBookings();
} else {
	destination = "login.jsp";
	text = "Sign in";
} // if/else
	
	String firstName = (String) session.getAttribute("firstName");
	String lastName = (String) session.getAttribute("lastName");
	String email = (String) session.getAttribute("email");
	String phone = (String) session.getAttribute("phone");
	boolean enroll = ((Boolean) session.getAttribute("enrollForPromotions")).booleanValue();
	boolean wrongPassword = session.getAttribute("wrongpassword") != null ? ((Boolean) session.getAttribute("wrongpassword")).booleanValue(): false;
	
	System.out.println(wrongPassword);
	
	Address address = (Address) session.getAttribute("address");
	Card card = (Card) session.getAttribute("card");
	
	String month = "";
	if(card != null) {
		month = "" + card.getExp().getMonth();
		if(month.length() == 1) month = "0" + month;
	} // if
%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="css/editProfile.css" rel="stylesheet">

        <title>Edit Profile</title>
        <link rel="icon" href="clapperboard.png">
        
        
    </head>
    <body >
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
            <a class="btn btn-outline-primary" href="logout.jsp">Logout</a>
        </div>
        <div class="container">
            <div class="py-5 text-center">
                <img class="mb-4" src="clapperboard.png" alt="" width="72" height="72">
                <h2>Edit Profile</h2>
                <p class="lead">This page allows registered users to edit their profile.</p>
            </div>
            <form class="needs-validation" action="EditProfileServlet" method=POST novalidate>
                <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName">First name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="" value='<%=firstName%>' required>
                    </div>
                    <div class="col-md-6 mb-3">
                    <label for="lastName">Last name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="" value='<%=lastName%>' required>
                    </div>
                </div>
        
                <div class="mb-3">
                    <label for="email">Email </label>
                    <input type="email" class="form-control" id="email" name="email" value='<%=email%>' required>
                </div>
                
                <div class="mb-3">
                    <label for="phone">Phone Number </label>
                    <input type="phone" class="form-control" id="phone" value='<%=phone%>' name="phone" required>
                    <div class="invalid-feedback">
                    Please enter a valid email address for movie updates.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="password">Current Password </label>
                    <input type="password" class="form-control" id="password" name="oldPassword" required>
                    <p id="wrong"></p>
                </div>
                
                <script>

            	var check = function() {
		           	  if (document.getElementById('newPassword').value == document.getElementById('newPassword2').value && document.getElementById('newPassword').value.length > 0) {
		           	    document.getElementById('message').style.color = 'green';
		           	    document.getElementById('message').innerHTML = 'Passwords Match';
		           	    document.getElementById('submit').disabled = false;
		           	  } else if(document.getElementById('newPassword').value == document.getElementById('newPassword2').value) {
		           		  document.getElementById('message').style.color = 'red';
		             	    document.getElementById('message').innerHTML = 'Password cannot be empty';
		             	    document.getElementById('submit').disabled = true;
		           	  } else {
		           	    document.getElementById('message').style.color = 'red';
		           	    document.getElementById('message').innerHTML = 'Passwords do not match';
		           	    document.getElementById('submit').disabled = true;
		           	  }
            	}
            	
            	
   
				</script>

                <div class="mb-3">
                    <label for="password">New Password </label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword" required onkeyup='check();'>
                    <p id='message'></p>
                </div>

                <div class="mb-3">
                    <label for="password2">New Password Confirmation </label>
                    <input type="password" class="form-control" id="newPassword2" name="newPassword2" required onkeyup='check();'>
                </div>
                
                <% if (address != null) {%>
        
                <div class="mb-3">
                    <label for="address">Address <span class="text-muted">(Optional)</span></label>
                    <input type="text" class="form-control" id="address" name="address" placeholder="1234 Main St" value='<%=address.getAddress()%>'>
                </div>
        
                <div class="mb-3">
                    <label for="address2">Address 2 </label>
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
                    <label for="address">Address <span class="text-muted">(Optional)</span></label>
                    <input type="text" class="form-control" id="address" name="address" placeholder="1234 Main St">
                </div>
        
                <div class="mb-3">
                    <label for="address2">Address 2 </label>
                    <input type="text" class="form-control" id="address2" name="address2" placeholder="Apartment or suite" >
                </div>
        
                <div class="row">
                    <div class="col-md-5 mb-3">
                    <label for="city">City</label>
                    <input type="text" class="form-control" id="city" name="city" placeholder="City" >
                    </div>
                    <div class="col-md-4 mb-3">
                    <label for="state">State</label>
                    <input type="text" class="form-control" id="state" name="state" placeholder="State" >
                    </div>
                    <div class="col-md-3 mb-3">
                    <label for="zip">Zip</label>
                    <input type="number" class="form-control" id="zip" name="zip" max="99999" placeholder="#####" >
                    </div>
                </div>
                
                <% } %>
                <!-- <hr class="mb-4">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="same-address">
                    <label class="custom-control-label" for="same-address">Shipping address is the same as my billing address</label>
                </div>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="save-info">
                    <label class="custom-control-label" for="save-info">Save this information for next time</label>
                </div> -->
                <hr class="mb-4">
                
                <h4 class="mb-3">Enroll for Promotions</h4>
                
                <% if(enroll) { %>
                
                <div class="d-block my-3">
                    <div class="custom-control custom-radio">
                    <input id="yes" name="enroll" type="radio" class="custom-control-input" name="enrollForPromotions" checked >
                    <label class="custom-control-label" for="yes">Yes, I would like to receive promotional material including coupons and discounts</label>
                    </div>
                    <div class="custom-control custom-radio">
                    <input id="no" name="enroll" type="radio" class="custom-control-input" >
                    <label class="custom-control-label" for="no">No, I would not like to receive promotional material including coupons and discounts</label>
                    </div>
                </div>
                
                <% } else { %>
                
                <div class="d-block my-3">
                    <div class="custom-control custom-radio">
                    <input id="yes" name="enroll" type="radio" class="custom-control-input" name="enrollForPromotions"  >
                    <label class="custom-control-label" for="yes">Yes, I would like to receive promotional material including coupons and discounts</label>
                    </div>
                    <div class="custom-control custom-radio">
                    <input id="no" name="enroll" type="radio" class="custom-control-input" checked >
                    <label class="custom-control-label" for="no">No, I would not like to receive promotional material including coupons and discounts</label>
                    </div>
                </div>
                
                <% } %>
                
                <hr class="mb-4">
        
                <h4 class="mb-3">Payment Information <span class="text-muted">(Optional)</span></h4>
        
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
                <!-- <button class="btn btn-primary btn-lg btn-block" type="submit">Create account</button> -->
                <button class="btn btn-primary btn-lg btn-block" id="submit" type="submit">Save Changes</button>
            </form>
            <br><br>
            
        </div>
        
        <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8 text-center">
        
        <h2>Order History</h2>
          <form action="DeleteBookingServlet" method=POST>
          <table class="table">
			  <thead>
			    <tr>
			      <th scope="col">#</th>
			      <th scope="col">Date</th>
			      <th scope="col">Number of Tickets</th>
			      <th scope="col">Seats</th>
			      <th scope="col">Total Price</th>
			      <th scope="col">Movie</th>
			      <th scope="col"></th>
			    </tr>
			  </thead>
			  <tbody>
			  <% for(Booking booking: bookings) { 
				 	String seats = "";
			  		for (int i = 0; i < booking.getTickets().size(); i++) {
			  			seats += booking.getTickets().get(i).getSeatNumber();
			  			if (i != booking.getTickets().size()-1) {
			  				seats += ", ";
			  			}
			  		}
			  
			  %>
			    <tr>
			      <th><%=booking.getNum()%></th>
			      <td><%=booking.getTickets().get(0).getShowtime().getDate().toString()%> at <%=booking.getTickets().get(0).getShowtime().getStartTime()%></td>
			      <td><%=booking.getTickets().size()%></td>
			      <td><%=seats%></td>
			      <td>$<%=(Math.round(booking.getTotal() * 100.0) / 100.0)%></td>
			      <td><%=booking.getTickets().get(0).getMovie().getTitle()%></td>
			      <td><button type="submit" value='<%=booking.getNum()%>' name="refundBtn" class="btn btn-sm btn-danger">Refund</button></td>
			    </tr>
			    <% } %>
			  </tbody>
			</table>
			</form>
			<br><br>
        </div>
        <div class="col-md-2"></div>
        </div>
        
        
        
        <script>
    		if (<%=wrongPassword%> == true) {
    			document.getElementById('wrong').style.color = 'red';
           	    document.getElementById('wrong').innerHTML = 'Please enter a valid password.';
    		}
        </script>
    </body>
</html>
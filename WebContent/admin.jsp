<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		String type = (String) session.getAttribute("type");
%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="#" rel="stylesheet">

        <title>Admin</title>
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
            <a class="btn btn-outline-primary" href='<%=destination%>'><%=text%></a>
            
        </div>

        <div class="container text-center">

            <h1>Administrative Access</h1>
            <br>
<%if (type.equalsIgnoreCase("Admin")) { %>
            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                    <h4 class="my-0 font-weight-normal">Movies</h4>
                    </div>
                    <div class="card-body">
                    <p>Add, edit, delete, and manage movies. <br/>
                    
                    </p>
                    <a class="btn btn-lg btn-block btn-primary" href="Manage">Manage</a>
                    </div>
                </div>

            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                    <h4 class="my-0 font-weight-normal">Users</h4>
                    </div>
                    <div class="card-body">
                    <p>View user info, add users to the system, and suspend/unsuspend users.</p>
                    <a class="btn btn-lg btn-block btn-primary" href="getUsers">Manage</a>
                    </div>
                </div>
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                    <h4 class="my-0 font-weight-normal">Promotions</h4>
                    </div>
                    <div class="card-body">
                    <p>Add promotions.
                    <br/>Edit promotions.
                    <br/> Delete promotions.</p>
                    
                    
                    <a class="btn btn-lg btn-block btn-primary" href="ViewPromos">Manage</a>
                    </div>
                </div>
                
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                    <h4 class="my-0 font-weight-normal">Ticket Prices</h4>
                    </div>
                    <div class="card-body">
                    <p>Change Ticket Prices.
                    <br/>This is permanent, use promos for temporary offers. </p>
                    
                    
                    <a class="btn btn-lg btn-block btn-primary" href="TicketPrices">Manage</a>
                    </div>
                </div>
                <%} %>
                <%if (type.equalsIgnoreCase("Employee") || type.equalsIgnoreCase("Admin")){ %>
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                    <h4 class="my-0 font-weight-normal">Reports</h4>
                    </div>
                    <div class="card-body">
                    <p>Generate reports.</p>
                    <a class="btn btn-lg btn-block btn-primary" href="Report">Download</a>
                    <%}%>
                    </div>
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
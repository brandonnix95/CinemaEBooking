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
	ResultSet results = (ResultSet) request.getAttribute("results");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="css/editProfile.css" rel="stylesheet">
<title>Ticket Prices</title>
</head>
<body>
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
                <h2>Set Prices</h2>
                 <p class="lead">Please enter a value for each field.</p>
            </div>
            <form class="needs-validation" action = "UpdatePricesServlet">
            <%while (results.next()) { %>
                <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName"><%= results.getString("type")%>:</label>
                    <input type="number" class="form-control" min = 0 name = '<%=results.getString("type")%>' id="movieName" step = 0.01 placeholder="" value='<%=results.getDouble("price")%>' required>
                    <div class="invalid-feedback">
                        Price is required
                    </div>
                    </div>
                    </div>
                    <%} %>
                     <button class="btn btn-lg btn-primary btn-block" type="submit" value="submit">Submit Changes</button>
                     </form>

</body>
</html>
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
%>
<!doctype html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="css/editProfile.css" rel="stylesheet">

        <title>Add Movie</title>
        <link rel="icon" href="clapperboard.png">
    </head>
    <% 
    ResultSet categories = (ResultSet) request.getAttribute("categories");
    ResultSet ratings = (ResultSet) request.getAttribute("ratings");
    %>
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
                <h2>Add Movie</h2>
                 <p class="lead">Please enter a value for each field.</p>
            </div>
            <form class="needs-validation" action = "AddMovie">
                <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName">Movie Name</label>
                    <input type="text" class="form-control" name = "name" id="movieName" placeholder="" value='Movie Name' required>
                    <div class="invalid-feedback">
                        Movie name is required.
                    </div>
                    </div>
                    </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName">Movie Genre</label><br/>
                     <select name="genre" required>
  <% while (categories.next())  { %>
  <option value='<%=categories.getString(1)%>'><%=categories.getString(1) %></option>
  <%} %>
</select> 
                    </div>
                    	</div>
                    <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName">Movie Cast</label>
                    <input type="text" class="form-control" name ="cast" id="movieName" placeholder="" value='Movie Cast' required>
                    <div class="invalid-feedback">
                        Movie cast is required.
                    </div>
                    	</div>
                    	</div>
                    	 <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName">Movie Director</label>
                    <input type="text" class="form-control" name = "director" id="movieName" placeholder="" value='Director Name' required>
                    <div class="invalid-feedback">
                        Movie cast is required.
                    </div>
                    	</div>
                    </div>
                     <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName">Movie Synopsis</label>
                    <input type="text" class="form-control" name = "synopsis" id="movieName" placeholder="" value='Synopsis' required>
                    <div class="invalid-feedback">
                        Movie synopsis is required.
                    </div>
                    	</div>
                    	</div>
                    	 <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName">Movie Reviews</label>
                    <input type="text" class="form-control" name = "reviews" id="movieName" placeholder="" value='Reviews' required>
                    <div class="invalid-feedback">
                        Movie reviews are required.
                    </div>
                    	</div>
                    	</div>
                    	 <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName">Movie Trailer(enter URL)</label>
                    <input type="text" class="form-control" name = "trailer" id="movieName" placeholder="" value='Trailer URL' required>
                    <div class="invalid-feedback">
                        Movie trailer is required.
                    </div>
                    	</div>
                    	</div>
                    	 <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName">Movie Rating (TBR= To Be Rated)</label><br/>
                   <select name="rating" required>
  <% while (ratings.next()) { 
  %>
  <option value='<%=ratings.getString(1)%>'><%=ratings.getString(1) %></option>
  <%}%>
</select> 
                    	</div>
                    	</div>
              
                    	 <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName">Movie Length (Minutes)</label>
                    <input type="number" class="form-control" name = "length" id="movieName" placeholder="" value='0' required>
                    <div class="invalid-feedback">
                        Movie length is required.
                    </div>
                    	</div>
                    	</div>
                    	 <label for="firstName">Currently showing?</label>
                    	 <select name="isShowing" required>
                    	 <option value = "True"> True </option>
                    	 <option value = "False"> False </option>
                    	 </select>
                    	 <div>
                    	 </div>
                    	 <button class="btn btn-lg btn-primary btn-block" type="submit" value="submit">Submit Changes</button>
                    	 </form>
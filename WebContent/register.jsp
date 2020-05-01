<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="css/register.css" rel="stylesheet">

        <title>Create Account</title>
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
                <a class="p-2 text-dark" href="#">Extras</a>
            </nav>
            <a class="btn btn-outline-secondary mr-3" href="#">Cart</a>
            <a class="btn btn-outline-primary" href="login.jsp">Sign in</a>
        </div>
        <div class="container">
            <div class="py-5 text-center">
                <img class="mb-4" src="clapperboard.png" alt="" width="72" height="72">
                <h2>Create Account</h2>
                <p class="lead">Fill out the form below to create your free account!</p>
            </div>
            <script>

            var check = function() {
            	  if (document.getElementById('password').value == document.getElementById('password2').value && document.getElementById('password').value.length > 0) {
            	    document.getElementById('message').style.color = 'green';
            	    document.getElementById('message').innerHTML = 'Passwords Match';
            	    document.getElementById('submit').disabled = false;
            	  } else if(document.getElementById('password').value == document.getElementById('password2').value) {
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
            
            <form class="needs-validation"  action="RegisterServlet" method=POST>
                <div class="row">
                    <div class="col-md-6 mb-3">
                    <label for="firstName">First name</label>
                    <input type="text" class="form-control" id="firstName" placeholder="" value="John" name="firstName" required>
                    <div class="invalid-feedback">
                        Valid first name is required.
                    </div>
                    </div>
                    <div class="col-md-6 mb-3">
                    <label for="lastName">Last name</label>
                    <input type="text" class="form-control" id="lastName" placeholder="" value="Doe" name="lastName" required>
                    <div class="invalid-feedback">
                        Valid last name is required.
                    </div>
                    </div>
                </div>
        
                <!-- <div class="mb-3">
                    <label for="username">Username</label>
                    <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">@</span>
                    </div>
                    <input type="text" class="form-control" id="username" placeholder="Username" required>
                    <div class="invalid-feedback" style="width: 100%;">
                        Your username is required.
                    </div>
                    </div>
                </div> -->
        
                <div class="mb-3">
                    <label for="email">Email </label>
                    <input type="email" class="form-control" id="email" placeholder="you@example.com" name="email" required>
                    <div class="invalid-feedback">
                    Please enter a valid email address for movie updates.
                    </div>
                </div>
                
                <div class="mb-3">
                    <label for="phone">Phone Number </label>
                    <input type="phone" class="form-control" id="phone" placeholder="555-555-5555" name="phone" required>
                    <div class="invalid-feedback">
                    Please enter a valid email address for movie updates.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="password">Password </label>
                    <input type="password" class="form-control" id="password"  name="password" onkeyup = 'check();'required>
                    <div class="invalid-feedback">
                    Please enter a valid password.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="password2">Password Confirmation </label>
                    <input type="password" class="form-control" id="password2" name="password2" onkeyup= 'check();' required>
                    <p id='message'></p>
                </div>
        
                <div class="mb-3">
                    <label for="address">Address <span class="text-muted">(Optional)</span></label>
                    <input type="text" class="form-control" id="address" name="address" placeholder="1234 Main St" >
                </div>
        
                <div class="mb-3">
                    <label for="address2">Address 2 </label>
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
                    <input type="number" class="form-control" id="zip" name="zip" max="99999" placeholder="#####" >
                    </div>
                </div>
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
                
                <hr class="mb-4">
        
                <h4 class="mb-3">Payment Information <span class="text-muted">(Optional)</span></h4>
        
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
                    <input type="month" class="form-control" id="exp-month" name="exp-month" style="width:200px" placeholder="">
                    </div>
                    <div class="col-md-3 mb-3">
                    <label for="cc-cvv">CVV</label>
                    <input type="number" class="form-control" id="cvv" name="cvv" max="9999" placeholder="###">
                    </div>
                    <div class="col-md-3 mb-3">
                    <label for="cc-zip">Zip</label>
                    <input type="number" class="form-control" id="cc-zip" name="cc-zip" max="99999" placeholder="#####">
                    </div>
                </div>
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" id="submit" type="submit">Create account</button>
            </form>
            <p class="text-center">Have an account? <a href="login.jsp">Log In</a> </p>
        </div>
    </body>
</html>
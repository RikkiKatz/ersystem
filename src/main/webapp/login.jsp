<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Sign in Page</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"	crossorigin="anonymous"></script>

<!-- Personalized Style Sheet -->
<link rel="stylesheet" href="styles.css" type="text/css">

</head>
<body>
	<div class="container">
		<div class= "row sign-in-header text-center">
			<h1>Welcome to ERS!</h1>
		</div>
		<div class = "row sign-in-container"></div>
		<div class="col-lg-6 col-lg-offset-3">
			<div class="jumbotron text-center">
				<form class="form-signin" action = "login.do" method="post">
					<h2 class="form-signin-heading">Please sign in</h2>
					<label for="inputUsername" class="sr-only">Username</label> 
					<input type="username" id="inputUsername" name="username"
						class="form-control" placeholder="Username" required="" autofocus=""> 
					<label for="inputPassword" class="sr-only">Password</label> 
					<input type="password" id="inputPassword" name="password" 
							class="form-control" placeholder="Password" required="">
					<div class="checkbox">
						<label> <input type="checkbox" value="remember-me">
							Remember me
						</label>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Sign	in</button>
					<p class="text-danger">
						</br>
						<c:out value="${ loginErrorMessage }"/>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
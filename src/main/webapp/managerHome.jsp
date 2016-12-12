<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Home Page</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<!-- JQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.2/js/umd/dropdown.js"></script>
<!-- more JS plugins at https://cdnjs.com/libraries/twitter-bootstrap/4.0.0-alpha.2 -->

<!-- Personalized Style Sheet -->
<link rel="stylesheet" href="styles.css" type="text/css">
</head>
<body>
	<div class="col-lg-8 col-lg-offset-2">
		<div class="employee-table-title">
			<h2>Expense Reimbursement Requests</h2>
		</div>
		<div data-example-id="bordered-table">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th>Date Submitted</th>
						<th>Name</th>
						<th>Request Type</th>
						<th>Amount</th>
						<th>Description</th>
						<th>Status</th>
						<th>Date Resolved</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">1</th>
						<td>10/1/2016</td>
						<td>Dan Pickles</td>
						<td>Travel</td>
						<td>5000.00</td>
						<td>First class ticket to Hawaii</td>
						<td>
							<div class="form-group form-inline">
									<select class="form-control" placeholder="status">
										<option value="" disabled selected>Status</option>
										<option>Approved</option>
										<option>Denied</option>
										<option>Pending</option>
									</select>
								</div>
						</td>
						<td></td>
					</tr>
					<tr>
						<th scope="row">2</th>
						<td>1/10/2016</td>
						<td>Dan Pickles</td>
						<td>Other</td>
						<td>10.00</td>
						<td>Enthuware</td>
						<td><div class="form-group form-inline">
								<select class="form-control" placeholder="status">
									<option value="" disabled selected>Status</option>
									<option>Approved</option>
									<option>Denied</option>
									<option>Pending</option>
								</select>
							</div></td>
						<td></td>
					</tr>
					<tr>
						<th scope="row">3</th>
						<td>10/1/2015</td>
						<td>Dan Pickles</td>
						<td>Lodging</td>
						<td>100.00</td>
						<td>Holiday Inn Express</td>
						<td><div class="form-group form-inline">
								<select class="form-control" placeholder="status">
									<option value="" disabled selected>Status</option>
									<option>Approved</option>
									<option>Denied</option>
									<option>Pending</option>
								</select>
							</div></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Submit New Expense Reimbursement Request</title>
<!-- Latest compiled and minified jQuery -->
<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous"></script>
  
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
	
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
<!-- Personalized Style Sheet -->
<link rel="stylesheet" href="styles.css" type="text/css">
</head>
<body>
	<%@ include file="nav.jsp" %>
	<div class="col-lg-8 col-lg-offset-2">
		<div class="employee-table-title">
			<h2>New Reimbursement Request</h2>
		</div>
	
		<form id= "newRequestForm" action="insert.do" method="post">
			<div class="form-group form-inline">
				<select name="type" class="form-control" placeholder="type" required>
					<option value="" disabled selected>Type</option>
					<c:forEach var="type" items="${types}">
						<option value="${type.type_id}">
							<c:out value="${type.type}" />
						</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="form-group form-inline">
				<label class="sr-only" for="inputAmount" >Amount (in dollars)</label>
				<div class="input-group">
					<div class="input-group-addon">$</div>
					<input name= "amount" min="0" type="number" step=".01" class="form-control" placeholder="Amount" required>
				</div>
			</div>
			<div class = "row">
				<div class="form-group col-md-6 col-sm-6">
					<textarea name="description" class="form-control" form="newRequestForm" placeholder="Description" rows="6" required></textarea>
				</div>
			</div>
			<div class="form-group">
				<label>Upload Receipt</label> 
				<input name="receipt" type="file" id="receipt">
			</div>
			<input type="submit" class="btn btn-primary" value="Submit" />
		</form>
	</div>
</body>
</html>
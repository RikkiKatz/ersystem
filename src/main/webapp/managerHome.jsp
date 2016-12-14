<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Home Page</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"	crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"	crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"	crossorigin="anonymous"></script>
<!-- JQuery -->
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.2/js/umd/dropdown.js"></script>
<!-- Personalized Style Sheet -->
<link rel="stylesheet" href="styles.css" type="text/css">
</head>

<body>
	<%@ include file="nav.jsp" %>
	<div class="col-lg-8 col-lg-offset-2">
		<div class="manager-table-title">
			<h2>Expense Reimbursement Requests</h2>
		</div>
		<div id="manager-table">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>	Date Submitted	</th>
						<th>	Name			</th>
						<th>	Request Type	</th>
						<th>	Description		</th>
						<th>	Amount			</th>
						<th>	Status			</th>
						<th>	Date Resolved	</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reimb" items="${reimb}">
		            	<tr>
			                <td>	<c:out value="${reimb.date_submitted}">			</c:out>	</td>
							<td>	<c:out value="${reimb.author_id.fullName}">		</c:out>	</td>
			                <td>	<c:out value="${reimb.typeId.type}">			</c:out>	</td>
							<td>	<c:out value="${reimb.description}">			</c:out>	</td>
							<td>	<c:out value="${reimb.amount}">					</c:out>	</td>
							<td>	<div class="form-group form-inline">
										<select class="form-control" placeholder="status">
											<option value="" disabled selected>Status</option>
											<c:forEach var="variable" items="${status}">
												<option><c:out value="${variable}"></c:out></option>
											</c:forEach>
										</select>	
									</div>
							</td>
							<td>	<c:out value="${reimb.date_resolved}">			</c:out>	</td>
			        	</tr>
			        </c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
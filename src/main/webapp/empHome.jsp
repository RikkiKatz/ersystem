<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Employee Home Page</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"	crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"	crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="jquery-3.1.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="$jQuery.js"/></script>
<!-- Personalized Style Sheet -->
<link rel="stylesheet" href="styles.css" type="text/css">
</head>

<body>
	<%@ include file="nav.jsp" %>
	<div class="col-lg-8 col-lg-offset-2">
		<div class="employee-table-title">
			<h2>Expense Reimbursement Requests</h2>
		</div>
		<div id="employee-table">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>	Date Submitted	</th>
						<th>	Request Type	</th>
						<th>	Description		</th>
						<th>	Amount			</th>
						<th>	Status			</th>
						<th>	Date Resolved	</th>
						<th>	Resolver Name	</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reimb" items="${reimb}">
			            <tr>
			                <td>	<c:out value="${reimb.submitted}">			</c:out>	</td>
							<td>	<c:out value="${reimb.typeId.type}">		</c:out>	</td>
							<td>	<c:out value="${reimb.description}">		</c:out>	</td>
							<td>	<c:out value="${reimb.amount}">				</c:out>	</td>
							<td>	<c:out value="${reimb.statusId.status}">	</c:out>	</td>
							<td>	<c:out value="${reimb.resolved}">			</c:out>	</td>
							<td>	<c:out value="${reimb.resolver.fullName}">	</c:out>	</td>
			            </tr>
			        </c:forEach>		
				</tbody>
			</table>
		</div>
	
		<div>
			<a href="newRequest.do" class="btn btn-primary" role="button">Submit New Request</a>
		</div>
	</div>
</body>
</html>
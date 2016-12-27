<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Home Page</title>
<!-- Personalized Style Sheet -->
<link rel="stylesheet" href="/styles.css" type="text/css">

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
	
<style>
.manager-table-title{
	padding-bottom: 10px;
}

.btn-approve {
	color: green;
	border-color: green;
}

.btn-deny {
	color: red;
	border-color: red;
}
</style>
</head>

<body>
	<%@ include file="nav.jsp" %>
	
	<div class="col-lg-10 col-lg-offset-1">
		<div class="manager-table-title">
			<h2>Expense Reimbursement Requests</h2>
		</div>
		
		<div id="manager-table">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>	Date Submitted	</th>
							<th>	Employee Name	</th>
							<th>	Request Type	</th>
							<th>	Description		</th>
							<th>	Amount			</th>
							<th>	Status			</th>
							<th>	Update Status	</th>
							<th>	Date Resolved	</th>
							<th>	Resolver Name	</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="reimb" items="${reimbs}">
			            	<tr>
				                <td>	<fmt:formatDate type="date" dateStyle="long" 
					                		value="${reimb.date_submitted}" />						</td>
								<td>	<c:out value="${reimb.author_id.fullName}">		</c:out>	</td>
								<td>	<c:out value="${reimb.type_id.type}">			</c:out>	</td>
								<td>	<c:out value="${reimb.description}">			</c:out>	</td>
								<td>	<fmt:setLocale value="en_US"/>
										<fmt:formatNumber type ="currency" 
											value="${reimb.amount}"/>								</td>
								<td>	<c:out value="${reimb.status_id.status}" />					</td>	
									
								<td>	<c:if test="${reimb.status_id.status == 'Pending'}">
						                	<form action="approve.do" method = "POST">
							             		<button type="submit" name="approveReimb" class="btn btn-default btn-sm btn-block btn-approve" 
							             			onclick="statusClick(this.id);" value="<c:out value="${reimb.id}"/>">Approve</button>
											</form>
											
											<form action="deny.do" method = "POST">
												<button type="submit" name="denyReimb" class="btn btn-default btn-sm btn-block btn-deny" 
													onclick="statusClick(this.id);" value="<c:out value="${reimb.id}"/>">Deny</button>
							             	</form>									
										</c:if>	
										<c:if test="${reimb.status_id.status == 'Denied'}">
						                	<form action="approve.do" method = "POST">
							             		<button type="submit" name="approveReimb" class="btn btn-default btn-sm btn-block btn-approve" 
							             			onclick="statusClick(this.id);" value="<c:out value="${reimb.id}"/>">Approve</button>
											</form>										
										</c:if>														
										<c:if test="${reimb.status_id.status == 'Approved'}">
											<form action="deny.do" method = "POST">
												<button type="submit" name="denyReimb" class="btn btn-default btn-sm btn-block btn-deny" 
													onclick="statusClick(this.id);" value="<c:out value="${reimb.id}"/>">Deny</button>
							             	</form>									
										</c:if>														</td>		
																	
								<td>	<c:if test="${reimb.resolver_id.first_name != null}">
					        				<fmt:formatDate type="date" dateStyle="long" value="${reimb.date_resolved}" />						
					                	</c:if>														</td>
					            
				        		<td>	<c:if test="${reimb.resolver_id.first_name != null}">
				        					<c:out value="${reimb.resolver_id.fullName}">	</c:out>
										</c:if>														</td>
				        	</tr>
				        </c:forEach>
					</tbody>
				</table>
		</div>
	</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript">
	function statusClick(clicked_id)
	{   document.getElementById(clicked_id).session_start();}
</script>
</body>
</html>
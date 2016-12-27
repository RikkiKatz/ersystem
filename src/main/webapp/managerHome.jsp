<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Home Page</title>
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
	<div class="col-lg-10 col-lg-offset-1">
		
		<div class="manager-table-title">
			<h2>Expense Reimbursement Requests</h2>
		</div>
		
		<div id="manager-table">
			<form action = "updateStatus" method = "POST">
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
							<th>	Resolver Name	</th>
						</tr>
					</thead>
					<tbody>
						<% int i=0; %>
						<c:forEach var="reimb" items="${reimbs}">
			            	<tr>
				                <td>	<fmt:formatDate type="date" dateStyle="long" 
					                		value="${reimb.date_submitted}" />						</td>
								<td>	<c:out value="${reimb.author_id.first_name} 
													${reimb.author_id.last_name}">		</c:out>	</td>
								<td>	<c:out value="${reimb.type_id.type}">			</c:out>	</td>
								<td>	<c:out value="${reimb.description}">			</c:out>	</td>
								<td>	<fmt:setLocale value="en_US"/>
										<fmt:formatNumber type ="currency" 
											value="${reimb.amount}"/>								</td>
								<td>	<div class="form-group form-inline">
											<select name="status" class="form-control" placeholder="status">
												<option value="" disabled selected>Status</option>
												<c:forEach var="status" items="${statuses}">
													<option value="${status.status_id}">
														<c:out value="${status.status}"></c:out>
													</option>
												</c:forEach>										
											</select>	
										</div>														</td>
																							
									<%--											
									<c:if test="${reimb.status_id.status == 'Pending'}">
						                <input type="hidden" name="userId" value="${reimb.author_id.user_id}">
						                <input type="hidden" name="rid" value="${reimb.id}">
						             	<input  id="test<%=i%>" name="status" class="btn btn-warning btn-sm btn-block" 
						             			onclick="statusClick(this.id);" value="${reimb.status_id.status}" readonly>
										<%i++;%>
									</c:if>
									<c:if test="${reimb.status_id.status == 'Approved'}">
						             	<input  id="test<%=i%>" name="status" class="btn btn-success btn-sm btn-block" 
						             			onclick="statusClick(this.id);" value="${reimb.status_id.status}" disabled >
										<%i++;%>
									</c:if>
									<c:if test="${reimb.status_id.status == 'Denied'}">
						             	<input  id="test<%=i%>" name="status" class="btn btn-danger btn-sm btn-block" 
						             			onclick="statusClick(this.id);" value="${reimb.status_id.status}" disabled>
										<%i++;%>
									</c:if> --%>
																	
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
			<input type="submit" class="btn btn-primary btn-sm" value="Submit"/> 
			</form>
		</div>
	</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript">
	function statusClick(clicked_id)
	{
	    if(document.getElementById(clicked_id).value == "Pending"){
			document.getElementById(clicked_id).setAttribute('value', 'Approved');
			document.getElementById(clicked_id).className = "btn btn-success btn-sm btn-block";
		}else if(document.getElementById(clicked_id).value == "Approved"){
			document.getElementById(clicked_id).setAttribute('value', 'Denied');
			document.getElementById(clicked_id).className = "btn btn-danger btn-sm btn-block";
		}else if(document.getElementById(clicked_id).value == "Denied"){
			document.getElementById(clicked_id).setAttribute('value', 'Pending');
			document.getElementById(clicked_id).className = "btn btn-warning btn-sm btn-block";
		}	
	}
</script>
</body>
</html>
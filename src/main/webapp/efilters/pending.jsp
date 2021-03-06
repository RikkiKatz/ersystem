<%@ include file="header.jsp"%>
<c:forEach var="reimb" items="${reimbs}">

	<c:if test="${reimb.status_id.status == 'Pending'}">
		<tr>
			<td><fmt:formatDate type="date" dateStyle="long"
					value="${reimb.date_submitted}" /></td>
			<td><c:out value="${reimb.type_id.type}">
				</c:out></td>
			<td><c:out value="${reimb.description}">
				</c:out></td>
			<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
					type="currency" value="${reimb.amount}" /></td>
			<td><c:out value="${reimb.status_id.status}">
				</c:out></td>
			<td><fmt:formatDate type="date" dateStyle="long"
					value="${reimb.date_resolved}" /></td>
			<td><c:if test="${reimb.resolver_id.user_id !=0}">
					<c:out value="${reimb.resolver_id.fullName}">
					</c:out>
				</c:if></td>
		</tr>
	</c:if>
</c:forEach>
<%@ include file="footer.jsp"%>
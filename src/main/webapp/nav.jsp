<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"> Welcome <c:out
					value="${user.getFullName()}" />!
			</a>
		</div>
		<div class="navbar-collapse collapse">
			<c:if test="${user.role_id.id == 2}">
				<ul class="nav navbar-nav">
					<li><a href="/ers/empHome.jsp" class="active">Home</a></li>
					<li><a href="newRequest.do">New Request</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/ers/efilters/pending.jsp">Pending</a></li>
					<li><a href="/ers/efilters/approved.jsp">Approved</a></li>
					<li><a href="/ers/efilters/denied.jsp">Denied</a></li>
					<li><a href="logout.do">Logout</a>
				</ul>
			</c:if>

			<c:if test="${user.role_id.id == 1}">
				<ul class="nav navbar-nav">
					<li><a href="/ers/managerHome.jsp" class="active">Home</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/ers/mfilters/pending.jsp">Pending</a></li>
					<li><a href="/ers/mfilters/approved.jsp">Approved</a></li>
					<li><a href="/ers/mfilters/denied.jsp">Denied</a></li>
					<li><a href="logout.do">Logout</a>
				</ul>
			</c:if>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>
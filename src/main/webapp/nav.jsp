<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="empHome.jsp">
				Welcome <c:out value="${user.getFullName()}"/>!
			</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="empHome.jsp" class="active">Home</a></li>
				<li><a href="newRequest.do">New Request</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Pending</a></li>
				<li><a href="#">Approved</a></li>
				<li><a href="#">Denied</a></li>
				<li><a href="logout.do">Logout</a>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>
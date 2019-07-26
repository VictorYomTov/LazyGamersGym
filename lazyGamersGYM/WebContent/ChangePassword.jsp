<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>LazyGamers GYM</title>
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-chache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setHeader("Expires", "0"); // Proxies

		if (session.getAttribute("username") == null)
			response.sendRedirect("login.jsp");
	%>
	<div data-role="page">
		<div data-role="header" data-theme="c"
			style="background-color: #00CED1;">
			<h4>LazyGamers GYM</h4>
		</div>
		<!-- /header -->
		<div role="main" class="ui-content">
			<h1 align="center">
				<u><b> USER: <%
					out.println((String) session.getAttribute("username"));
				%> , Change your password
				</b></u>
			</h1>
			<div>
				<form method="POST"
					action="/lazyGamersGYM/controller/changePassword">
					<table cellpadding="3pt">
						<tr>
							<td>User:</td>
							<td><%=session.getAttribute("username")%></td>
						</tr>
						<tr>
							<td>Old Password:</td>
							<td><input type="text" name="password" size="30"></td>
						</tr>
						<tr>
							<td>New Password:</td>
							<td><input type="password" name="password1" size="30"></td>
						</tr>
						<tr>
							<td><input type="submit" value="Change Password"></td>
						</tr>
					</table>
				</form>
				<form method="GET" action="/lazyGamersGYM/controller/toHome">
					<input type="submit" value="Back">
				</form>
			</div>
		</div>
		<div data-role="footer" data-theme="c"
			style="background-color: #00CED1;">
			<h4>LazyGamers GYM</h4>
		</div>
		<!-- /footer -->


	</div>
	<!-- /page -->
</body>
</html>
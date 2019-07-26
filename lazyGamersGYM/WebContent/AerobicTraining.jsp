<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
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
	<div data-role="page" id="Aerobic Training">
		<div data-role="header" data-theme="c"
			style="background-color: #00CED1;">
			<h4>LazyGamers GYM</h4>
			<br>
		</div>
		<!-- /header -->

		<div role="main" class="ui-content">
			<h5 align="left">
				User:
				<%
				out.println((String) session.getAttribute("username"));
			%>
			</h5>
			<h1 align=center>
				<b><u>Aerobic Training</u></b>
			</h1>
			<div>
				<p>
					<b>Exercise List1:</b>
				</p>
				<form
					action="/lazyGamersGYM/controller/toAerobicEx1"
					method="GET">
					<input type="submit" value="RunningOnTreadmill">
				</form>
				<form
					action="/lazyGamersGYM/controller/toAerobicEx2"
					method="GET">
					<input type="submit" value="StairStepper">
				</form>
				<form
					action="/lazyGamersGYM/controller/toAerobicEx3"
					method="GET">
					<input type="submit" value="StationaryBike">
				</form>
				<form method="GET"
					action="/lazyGamersGYM/controller/toReportsAerobic">
					<input type="submit" value="Aerobic Reports">
				</form>
				<form method="GET"
					action="/lazyGamersGYM/controller/toHome">
					<input type="submit" value="Back">
				</form>
			</div>
		</div>
		<!-- /content -->
		<div data-role="footer" data-theme="c"
			style="background-color: #00CED1;">
			<h4>LazyGamers GYM</h4>
		</div>
		<!-- /footer -->
	</div>
	<!-- /page -->
</body>
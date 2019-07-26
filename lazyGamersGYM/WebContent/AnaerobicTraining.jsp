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
		

		if (session.getAttribute("username") == null)
			response.sendRedirect("login.jsp");
	%>
	<div data-role="page" id="Anaerobic Training">
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
				<b><u>Anaerobic Training</u></b>
			</h1>
			<div>
				<p>
					<b>Exercise List:</b>
				</p>
				<form
					action="/lazyGamersGYM/controller/toAnaerobicEx1"
					method="GET">
					<input type="submit" value="Squat">
				</form>
				<form
					action="/lazyGamersGYM/controller/toAnaerobicEx2"
					method="GET">
					<input type="submit" value="Chest Press">
				</form>
				<form
					action="/lazyGamersGYM/controller/toAnaerobicEx3"
					method="GET">
					<input type="submit" value="Shoulder Press">
				</form>
				<form method="GET"
					action="/lazyGamersGYM/controller/toReportsAnaerobic">
					<input type="submit" value="Anaerobic Reports">
				</form>
				<form method="GET"
					action="/lazyGamersGYM/controller/toHome">
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

</body>
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
	<div data-role="page" id="StairStepper">
		<div data-role="header" data-theme="c"
			style="background-color: #00CED1;">
			<h1>LazyGamers GYM</h1>
		</div>
		<!-- /header -->
		<div Role="main" class="ui-content">
			<h5 align="left">
				User:
				<%
				out.println((String) session.getAttribute("username"));
			%>
			</h5>
			<h1 align=center>
				<u><b>StairStepper</b></u>
			</h1>
			<div data-role="content">
				<br>
				<p>Rest your hands or better yet, your fingertips lightly on the
					bar in front of you or on the side rails. Stand upright with a
					slight forward lean at the hips. Take even, moderately deep steps.
					Keep your entire foot on the pedal.</p>
				<p>
					<u><b>Recommended Time:</b></u>
				</p>
				<p>30 Minutes</p>
				<p>
					<u><b>Please input the time: </b></u>
				</p>
				<form method="POST"
					action="/lazyGamersGYM/controller/createActivity/StairStepper">
					<table cellpadding="3pt">
						<tr>
							<td>Time:</td>
							<td><input type="text" name="time" size="30"></td>
						</tr>
						<tr>
							<td><a href=AerobicTraining.jsp data-role="button">Back</a></td>
							<td><input type="submit" value="Add"></td>
						</tr>
					</table>
				</form>
			</div>
			<!-- /content -->
		</div>
		<!-- /main -->
		<div data-role="footer" data-theme="c"
			style="background-color: #00CED1;">
			<h1>LazyGamers GYM</h1>
		</div>
		<!-- /footer -->
	</div>
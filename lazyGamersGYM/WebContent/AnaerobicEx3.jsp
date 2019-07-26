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
	<div data-role="page" id="Shoulder Press">
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
				<u><b>ShoulderPress</b></u>
			</h1>
			<div data-role="content">
				<br>
				<p>Lie face down on the glideboard, knees bent and grasp the
					press-up bars with your hands, elbows bent. Slide the glideboard up
					by extending your arms (pushing yourself up) and allow yourself
					back down after a short pause. Breathe out while pushing and
					breathe in while returning to starting position.</p>
				<p>
					<u><b>Recommended number of sets:</b></u>
				</p>
				<p>2</p>
				<p>
					<u><b>Recommended number of repeats:</b></u>
				</p>
				<p>3</p>
				<p>
					<u><b>Please input the number of sets and reps you have
							made: </b></u>
				</p>
				<form method="POST"
					action="/lazyGamersGYM/controller/createActivity/ShoulderPress">
					<table cellpadding="3pt">
						<tr>
							<td>Sets:</td>
							<td><input type="text" name="set" size="30"></td>
						</tr>
						<tr>
							<td>Reps:</td>
							<td><input type="text" name="rep" size="30"></td>
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
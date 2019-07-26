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
	<div data-role="page">
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
				<u><b>RunningOnTreadmill</b></u>
			</h1>
			<div data-role="content">
				<br>
				<p>Running on a treadmill is a great form of aerobic exercise,
					and can help you burn calories and improve your overall health.
					While it might seem that a treadmill simply involves running, you
					should learn how to safely use one before you begin your workout.
					Using the proper form, knowing that your gait and stride are a
					little different when running on a treadmill, and using safety
					features will help you avoid serious injury. Finding more creative
					routines can help you maximize your workout and keep you engaged in
					your exercise plan. Make sure you talk to your doctor before
					beginning any exercise routine.</p>
				<p>
					<u><b>Recommended Time: </b></u>
				</p>
				<p>45 minutes</p>
				<p>
					<u><b>Please input the time: </b></u>
				</p>
				<form method="POST"
					action="/lazyGamersGYM/controller/createActivity/RunningOnTreadmill">
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
	<!-- /page -->
</body>
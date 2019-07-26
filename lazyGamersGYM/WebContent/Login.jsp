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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

	<div data-role="page">
		<div data-role="header" data-theme="c"
			style="background-color: #00CED1;">
			<h1>LazyGamers GYM</h1>
		</div>
		<!-- /header -->
		<div role="main" class="ui-content">
			<h1>
				<u><b>Login</b></u>
			</h1>
			<form method="POST"
				action="http://192.168.1.2:8080/lazyGamersGYM/controller/loginUser">
				<table cellpadding="3pt">
					<tr>
						<td>User Name</td>
						<td><input type="text" name="username" size="30"></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="password" size="30"></td>
					</tr>
					<tr>
						<td><input type="submit" value="login"></td>
					</tr>
				</table>
			</form>
			<form
				action="http://192.168.1.2:8080/lazyGamersGYM/controller/toRegistration"
				method="GET">
				<table cellpadding="3pt">
					<tr>
						<td><input type="submit" value="Registration"></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-role="footer" data-theme="c"
			style="background-color: #00CED1;">
			<h1>LazyGamers GYM</h1>
		</div>
		<!-- /footer -->
	</div>
	<!-- /page -->

</body>
</html>
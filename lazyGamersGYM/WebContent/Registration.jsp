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
	<div data-role="page">
		<div data-role="header" data-theme="c"
			style="background-color: #00CED1;">
			<h1>LazyGamers GYM</h1>
		</div>
		<!-- /header -->
		<div role="main" class="ui-content">
			<h1>
				<b>Registration Form</b>
			</h1>
			<form method="POST"
				action="/lazyGamersGYM/controller/createUser">
				<table cellpadding="3pt">
					<tr>
						<td>User Name:</td>
						<td><input type="text" name="username" size="30" /></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" size="30" /></td>
					</tr>
					<tr>
						<td><a href="Login.jsp" data-role="button">Login</a></td>
						<td><input type="submit" value="Register" /></td>
					</tr>
				</table>
			</form>
		</div>

		<div data-role="footer" data-theme="c"
			style="background-color: #00CED1;">
			<h4>LazyGamers GYM</h4>
		</div>
		<!-- /footer -->

	</div>
	<!-- /content -->
	<!-- /page -->
</body>
</html>
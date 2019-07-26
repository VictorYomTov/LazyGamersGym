<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.model.Activity"%>
<%@ page import="com.model.User"%>
<%@ page import="com.model.api.HibernateGymDAO"%>
<%@ page import="javax.swing.JOptionPane"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>REPORTS</title>
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

		<div role="main" class="ui-content" style="background-color: #FFA07A;">
			<h5 align="left">
				User:
				<%
				out.println((String) session.getAttribute("username"));
			%>
			</h5>
			<h1>
				<u><b>REPORTS PAGE</b></u>
			</h1>
		</div>
		<div>
			<table cellpadding="3pt">
				<%
					System.out.println("Reports->Start");
					List<Activity> fullActivitiesReports = new ArrayList<Activity>();
					List<Activity> aerobicActivitiesReports = new ArrayList<Activity>();
					HibernateGymDAO hgd = HibernateGymDAO.getInstance();
					String userName = (String) session.getAttribute("username");
					System.out.println("Reports->userName: " + userName);
					User curUser = hgd.getUserByUsername(userName);
					fullActivitiesReports = hgd.getAllUserActivities(curUser.getId());
					System.out.println("activitiesReports->: " + fullActivitiesReports.toString());
					if (fullActivitiesReports != null) {
						for (Activity tempActivity : fullActivitiesReports) {
							System.out.println("tempActivity->: " + tempActivity.toString());
							if (tempActivity != null)
								if (tempActivity.getAerobic() != null) {
									aerobicActivitiesReports.add(tempActivity);
								}
						}
					}
					for (Activity tempActivity : aerobicActivitiesReports) {
				%>
				<tr>
					<td><b>ID:</b></td>
					<td><%=tempActivity.getActivityId()%></td>
					<td><b>Aerobic Name:</b></td>
					<td><%=tempActivity.getAerobic()%></td>
					<td><b>Time:</b></td>
					<td><%=tempActivity.getTime()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<div>
			<form method="POST"
				action="/lazyGamersGYM/controller/updateActivity">
				<table cellpadding="3pt">
					<tr>
						<td>ActivityID:</td>
						<td><input type="text" name="activityid" size="30"></td>
						<td>New Time Value:</td>
						<td><input type="text" name="time" size="30"></td>
						<td><input type="submit" value="Update"></td>
					</tr>
				</table>
			</form>
			<form method="POST"
				action="/lazyGamersGYM/controller/removeActivity">
				<table cellpadding="3pt">
					<tr>
						<td>ActivityID:</td>
						<td><input type="text" name="activityid" size="30"></td>
						<td><input type="submit" value="Delete"></td>
					</tr>
				</table>
			</form>
		</div>
		<div>
			<form
				action="http://localhost:8080/lazyGamersGYM/controller/toGraphAerobic"
				method="GET">
				<input type="submit" value="To Graph">
			</form>
		</div>
		<div>
			<form
				action="/lazyGamersGYM/controller/toAerobicTraining"
				method="GET">
				<input type="submit" value="To AerobicTraining">
			</form>
		</div>
		<div>
			<form action="/lazyGamersGYM/controller/toHome"
				method="GET">
				<input type="submit" value="To Home">
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
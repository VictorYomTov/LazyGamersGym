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
		String message = (String) request.getAttribute("alertMsg");
		if (message != null)
			if (message.contains("success")) {
				JOptionPane.showMessageDialog(null, message);
			}
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
					System.out.println("Reports2->Start");
					List<Activity> activitiesReportsTest = new ArrayList<Activity>();
					List<Activity> activitiesReports = new ArrayList<Activity>();
					HibernateGymDAO hgd = HibernateGymDAO.getInstance();
					String userName = (String) session.getAttribute("username");
					System.out.println("Reports2->userName: " + userName);
					User curUser = hgd.getUserByUsername(userName);
					activitiesReportsTest = hgd.getAllUserActivities(curUser.getId());
					System.out.println("activitiesReports->: " + activitiesReportsTest.toString());
					if (activitiesReports != null)
						for (Activity tempActivity : activitiesReportsTest) {
							System.out.println("tempActivity->: " + tempActivity.toString());
							if (tempActivity != null)
								if (tempActivity.getAnaerobic() != null) {
									System.out.println("Adding..");
									activitiesReports.add(tempActivity);
									System.out.println("Added!");
								}
						}

					for (Activity tempActivity : activitiesReports) {
				%>
				<tr>
					<td><b>ID:</b></td>
					<td><%=tempActivity.getActivityId()%></td>
					<td><b>Anaerobic Name:</b></td>
					<td><%=tempActivity.getAnaerobic()%></td>
					<td><b>Sets:</b></td>
					<td><%=tempActivity.getSets()%></td>
					<td><b>Reps:</b></td>
					<td><%=tempActivity.getReps()%></td>
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
						<td>New Reps:</td>
						<td><input type="text" name="rep" size="30"></td>
						<td>New Sets:</td>
						<td><input type="text" name="set" size="30"></td>
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
				action="/lazyGamersGYM/controller/toGraphAnaerobic"
				method="GET">
				<input type="submit" value="To Graph">
			</form>
		</div>
		<div>
			<form
				action="/lazyGamersGYM/controller/toAnaerobicTraining"
				method="GET">
				<input type="submit" value="To AnaerobicTraining">
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
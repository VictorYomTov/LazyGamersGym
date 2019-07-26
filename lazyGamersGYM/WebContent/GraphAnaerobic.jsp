<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.model.Activity"%>
<%@ page import="com.model.User"%>
<%@ page import="com.model.api.HibernateGymDAO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>GraphAnaerobic</title>
</head>
<body>
 <% response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
 response.setHeader("Pragma","no-cache"); //HTTP 1.0 
 response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
%>
<%
		System.out.println("Graph->Start");
		List<Activity> fullActivitiesReports = new ArrayList<Activity>();
		List<Activity> anaerobicActivitiesReports = new ArrayList<Activity>();
		HibernateGymDAO hgd = HibernateGymDAO.getInstance();
		String userName = (String) session.getAttribute("username");
		System.out.println("Graph->userName: " + userName);
		User curUser = hgd.getUserByUsername(userName);
		fullActivitiesReports = hgd.getAllUserActivities(curUser.getId());
		System.out.println("activitiesReports->: " + fullActivitiesReports.toString());
		if (fullActivitiesReports != null) {
			for (Activity tempActivity : fullActivitiesReports) {
				System.out.println("tempActivity->: " + tempActivity.toString());
				if (tempActivity != null)
					if (tempActivity.getAnaerobic() != null) {
						anaerobicActivitiesReports.add(tempActivity);
					}
			}
		} 
		Integer sumAnaerobicSetsEx1 = 0;
		Integer sumAnaerobicRepsEx1 = 0;
		Integer sumAnaerobicSetsEx2 = 0;
		Integer sumAnaerobicRepsEx2 = 0;
		Integer sumAnaerobicSetsEx3 = 0;
		Integer sumAnaerobicRepsEx3 = 0;
		for (Activity tempActivity : anaerobicActivitiesReports) {
			if (tempActivity.getAnaerobic().toString().contains("Squat")){
				System.out.println("Start 1 -> " );
				sumAnaerobicSetsEx1 += tempActivity.getSets();
				sumAnaerobicRepsEx1 += tempActivity.getReps();
				System.out.println("sumAnaerobicSetsEx1 is: " + sumAnaerobicSetsEx1);
				System.out.println("sumAnaerobicRepsEx1 is: " + sumAnaerobicRepsEx1);
				System.out.println("finish 1 -> " );
			}
			
			else if (tempActivity.getAnaerobic().toString().contains("ChestPress")){
				System.out.println("Start 2 -> " );
				sumAnaerobicSetsEx2 += tempActivity.getSets();
				sumAnaerobicRepsEx2 += tempActivity.getReps();
				System.out.println("sumAnaerobicSetsEx2is: " + sumAnaerobicSetsEx2);
				System.out.println("sumAnaerobicRepsEx2 is: " + sumAnaerobicRepsEx2);
				System.out.println("finish 2 -> " );
			}
			else if (tempActivity.getAnaerobic().toString().contains("ShoulderPress")){
				System.out.println("Start 3 -> " );
				sumAnaerobicSetsEx3 += tempActivity.getSets();
				sumAnaerobicRepsEx3 += tempActivity.getReps();
				System.out.println("sumAnaerobicSetsEx3is: " + sumAnaerobicSetsEx3);
				System.out.println("sumAnaerobicRepsEx3 is: " + sumAnaerobicRepsEx3);
				System.out.println("finish 3 -> " );
			}
		}

		System.out.println("sumAnaerobicSetsEx1 is: " + sumAnaerobicSetsEx1);
		System.out.println("sumAnaerobicRepsEx1 is: " + sumAnaerobicRepsEx1);
		System.out.println("sumAnaerobicSetsEx2is: " + sumAnaerobicSetsEx2);
		System.out.println("sumAnaerobicRepsEx2 is: " + sumAnaerobicRepsEx2);
		System.out.println("sumAnaerobicSetsEx3 is: " + sumAnaerobicSetsEx3);
		System.out.println("sumAnaerobicRepsEx3 is: " + sumAnaerobicRepsEx3);
		session.setAttribute("sumAnaerobicSetsEx1", sumAnaerobicSetsEx1);
		session.setAttribute("sumAnaerobicRepsEx1", sumAnaerobicRepsEx1);
		session.setAttribute("sumAnaerobicSetsEx2", sumAnaerobicSetsEx2);
		session.setAttribute("sumAnaerobicRepsEx2", sumAnaerobicRepsEx2);
		session.setAttribute("sumAnaerobicSetsEx3", sumAnaerobicSetsEx3);
		session.setAttribute("sumAnaerobicRepsEx3", sumAnaerobicRepsEx3);
		%>
	<div class="container">
		<canvas id="myChart"></canvas>
	</div>
<div id="ex1sets" data-prodnumber="${sessionScope.sumAnaerobicSetsEx1}" />
<div id="ex1reps" data-prodnumber="${sessionScope.sumAnaerobicRepsEx1}" />
<div id="ex2sets" data-prodnumber="${sessionScope.sumAnaerobicSetsEx2}" />
<div id="ex2reps" data-prodnumber="${sessionScope.sumAnaerobicRepsEx2}" />
<div id="ex3sets" data-prodnumber="${sessionScope.sumAnaerobicSetsEx3}" />
<div id="ex3reps" data-prodnumber="${sessionScope.sumAnaerobicRepsEx3}" />
	<script>
	var ex1sets = document.getElementById("ex1sets"), 
	sumAnerobicSetsEx1 = ex1sets.getAttribute("data-prodnumber"),
	ex1reps = document.getElementById("ex1reps"), 
	sumAnerobicRepsEx1  = ex1reps.getAttribute("data-prodnumber"),
	ex2sets = document.getElementById("ex2sets"), 
	sumAnerobicSetsEx2 = ex2sets.getAttribute("data-prodnumber"),
	ex2reps = document.getElementById("ex2reps"), 
	sumAnerobicRepsEx2  = ex2reps.getAttribute("data-prodnumber"),
	ex3sets = document.getElementById("ex3sets"), 
	sumAnerobicSetsEx3 = ex3sets.getAttribute("data-prodnumber"),
	ex3reps = document.getElementById("ex3reps"), 
	sumAnerobicRepsEx3  = ex3reps.getAttribute("data-prodnumber");
	
		let myChart = document.getElementById('myChart').getContext('2d');

		// Global Options
		Chart.defaults.global.defaultFontFamily = 'Lato';
		Chart.defaults.global.defaultFontSize = 18;
		Chart.defaults.global.defaultFontColor = '#777';

		let massPopChart = new Chart(myChart, {
			type : 'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
			data : {
				labels : [ 'Squat', 'ChestPress',
						'ShoulderPress' ],
				datasets : [ {
					label : 'Sets',
					data : [ sumAnerobicSetsEx1, sumAnerobicSetsEx2, sumAnerobicSetsEx3 ],
					//backgroundColor:'green',
					backgroundColor : [ 'rgba(255, 99, 132, 0.6)',
							'rgba(54, 162, 235, 0.6)',
							'rgba(255, 206, 86, 0.6)', ],
					borderWidth : 1,
					borderColor : '#777',
					hoverBorderWidth : 3,
					hoverBorderColor : '#000'
				} ,{
					label : 'Reps',
					data : [ sumAnerobicRepsEx1, sumAnerobicRepsEx2, sumAnerobicRepsEx3 ],
					//backgroundColor:'green',
					backgroundColor : [ 'rgba(255, 99, 132, 0.6)',
							'rgba(54, 162, 235, 0.6)',
							'rgba(255, 206, 86, 0.6)', ],
					borderWidth : 1,
					borderColor : '#777',
					hoverBorderWidth : 3,
					hoverBorderColor : '#000'
				}]
			},
			options : {
				title : {
					display : true,
					text : 'Summary of the aerobic activties',
					fontSize : 25
				},
				legend : {
					display : false,
					position : 'right',
					labels : {
						fontColor : '#000'
					}
				},
				layout : {
					padding : {
						left : 50,
						right : 0,
						bottom : 0,
						top : 0
					}
				},
				tooltips : {
					enabled : true
				}
			}
		});
	</script>
</body>
</html>

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
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>GraphAerobic</title>
</head>
<body>
<%
//check if user is connected
if (session.getAttribute("username") == null) {
String redirectURL = "/lazyGamersGYM/controller/toLogin";
response.sendRedirect(redirectURL);
}
%>
<div data-role="page" id="page1">
		<div data-role="header" data-theme="c"
			style="background-color: #00CED1;">
			<h4>LazyGamers GYM</h4>
		</div>
		<!-- /header -->

	<div class="container">
		<canvas id="myChart"></canvas>
	</div>
<div id="ex1" data-prodnumber="${sessionScope.sumAerobicEx1}" />
<div id="ex2" data-prodnumber="${sessionScope.sumAerobicEx2}" />
<div id="ex3" data-prodnumber="${sessionScope.sumAerobicEx3}" />
	<script>
	var ex1 = document.getElementById("ex1"), 
	sumAerobicEx1 = ex1.getAttribute("data-prodnumber"),
	ex2 = document.getElementById("ex2"), 
	sumAerobicEx2 = ex2.getAttribute("data-prodnumber"),
	ex3 = document.getElementById("ex3"), 
	sumAerobicEx3 = ex3.getAttribute("data-prodnumber");
	
	
		let myChart = document.getElementById('myChart').getContext('2d');

		// Global Options
		Chart.defaults.global.defaultFontFamily = 'Lato';
		Chart.defaults.global.defaultFontSize = 18;
		Chart.defaults.global.defaultFontColor = '#777';

		let massPopChart = new Chart(myChart, {
			type : 'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
			data : {
				labels : [ 'RunningOnTreadmill', 'StairStepper',
						'StationaryBike' ],
				datasets : [ {
					label : 'Activity',
					data : [ sumAerobicEx1, sumAerobicEx2, sumAerobicEx3 ],
					//backgroundColor:'green',
					backgroundColor : [ 'rgba(255, 99, 132, 0.6)',
							'rgba(54, 162, 235, 0.6)',
							'rgba(255, 206, 86, 0.6)', ],
					borderWidth : 1,
					borderColor : '#777',
					hoverBorderWidth : 3,
					hoverBorderColor : '#000'
				} ]
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
	<% System.out.println("finish");%>
	</div> 
</body>
</html>

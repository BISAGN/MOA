<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html>
<head>
<title>ADMIN Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"></script>
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<link rel="stylesheet" href="js/watermark/watermark.css">
<link rel="stylesheet" href="js/Dashboard/DashboardCSS.css">
<style>
 .info-box .content h5{
    font-size: 12px;
    font-weight: bold;
 }
 .info-box .content label{
     font-size: 12px;
     font-weight: bold;
     text-decoration: underline;
     cursor: pointer;
 }
 .info-box{
   text-align:center;
   color: #fff;
 }
 .info-box .content{
   padding: 7px;
 }
 #DASHBOARD_tabs .col-md-7{
   margin-top: 100px;
 }
 a{
  color: #fff;
 }
 .dashboard_boxes .col-md-2{
   padding-left: 10px;
   padding-right: 10px;
 }
 @media (min-width: 1400px){
    .dashboard_boxes .col-md-2{
	   flex: 0 0 11.11%;
	   max-width: 11.11%;
    } 
 }
 @media (max-width: 992px){
 .dashboard_boxes .col-md-2 {
    flex: 0 0 25%;
    max-width: 25%;
   }
 }


.amcharts-pie-slice {
  transform: scale(1);
  transform-origin: 40% 50%;
  transition-duration: 0.3s;
  transition: all .3s ease-out;
  -webkit-transition: all .3s ease-out;
  -moz-transition: all .3s ease-out;
  -o-transition: all .3s ease-out;
  cursor: pointer;
  box-shadow: 0 0 30px 0 #000;
}

.amcharts-pie-slice:hover {
  transform: scale(1.07);
  filter: url(#shadow);
}	

.amcharts-legend-div {
  overflow-y: auto!important;
  max-height: 400px;
  overflow-x: auto!important;
  max-width: 400px;
}	

.line {
    width: 100%;
    display: block;
    /* margin-top: 1rem; */
    margin-bottom: 1rem;
    border: 0;
    border-top-color: currentcolor;
    border-top-style: none;
    border-top-width: 0px;
    border-top: 2px solid #f0eaea; /* 1px solid #eee; */
}

#chartdiv {
  width: 100%;
  height: 500px;
}

</style>
</head>
<body>
	<div class="container">
				<div class="card">
					<div id="DASHBOARD_tabs">
	      				<h5 class="trans_heading">ADMIN DASHBOARD</h5>
	      				<img alt="Refresh" src="../login_file/referesh.ico" onclick="callAmPieChart();" style="display: block;margin: 15px auto;cursor: pointer;">
    					<div class="row">
								<div class="col-md-6">
									<h5 style="color: maroon; font-weight: bold; text-decoration: underline;">SUPPORT REQUEST</h5>
									<div id="piediv" style="width: 100%; height: 500px;"></div>							
								</div>
								<div class="col-md-6">
									<h5 style="color: maroon; font-weight: bold; text-decoration: underline;">VISITOR STATISTICS</h5>
										<div id="chartdiv" style="width: 100%; height: 500px;"></div>
									</div>									
						</div>
							<div class="row"><div class="col-md-12"><span class="line"></span></div></div>			
							<div class="row dashboard_boxes">
								 <div class="col-md-2">
									<div class="info-box bg-green">
										<div class="content">
											<h5>VISITOR COUNT</h5>
											<label id="totalactiveUnits" style="text-decoration: none;cursor: default;">${getUserLoginCount[0].total}</label>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="info-box bg-deep-purple">
										<div class="content">
											<h5>LOGGED IN USERS</h5>
											<a onclick="LoggedInDetails();">
												<label id="totalactiveusercount">${getActiveUserCount}</label>
											</a>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="info-box bg-darkyellow">
										<div class="content">
											<h5>NEW TICKETS</h5>
												<a onclick="TicketStatusNewDetails();">
													<label id="depotTotal">${getNewTicketCount[0].total}</label> 
												</a>
											</div>
										</div>
									</div>
								<div class="col-md-2">
									<div class="info-box bg-red">
										<div class="content">
											<h5>PENDING TICKETS</h5>
											<a onclick="TicketStatusInProgressDetails();">
												<label id="totalpendigstatus">${getPendigTicketCount[0].total}</label>
											</a>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="info-box bg-darkgreen">
										<div class="content">
											<h5>COMPLETED TICKETS</h5>
												<a onclick="TicketStatusCompletedDetails();">
													<label id="totalcompletedstatus">${getCompletedTicketCount[0].total}</label>
												</a>
										</div>
									</div>
									</div>
								<div class="col-md-2">
									<div class="info-box bg-darkblue">
										<div class="content">
											<h5>ROLES</h5>
												<a onclick="RoleDetails();">
													<label id="rolecount">${getRoleCount[0].total}</label>
												</a>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="info-box" style="background-color: #e67878;">
										<div class="content">
											<h5>USERS CREATED</h5>
											<a onclick="UserCreatedDetails();">
												<label id="usercount">${getUserCount[0].total}</label>
											</a>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="info-box" style="background-color: #cea328;">
										<div class="content">
											<h5>FEEDBACK RECEIVED</h5>
											<a onclick="HelpTopicFeedbackDetails();">
													<label id="feedbackcount">${getfeedbackrecCount[0].total}</label>
												</a>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="info-box" style="background-color: #e678c6;">
										<div class="content">
											<h5>FEATURE REQUESTS</h5>
											<a onclick="HelpTopicFeatureRequestDetails();">
												<label id="featurereq">${getFeatureReqCount[0].total}</label>
											</a>
										</div>
									</div>
								</div>
								</div>
	    			</div>
	    			
	    </div> <!-- end of card -->
	    
	</div> <!-- end of container -->
<script>
$(document).ready(function () {
    $.ajaxSetup({
	    async: false
	});
	callAmPieChart();	
	$.ajaxSetup({
	    async: false
	});
	callAmLineChart();
	$.ajaxSetup({
	    async: false
	});
});
</script>

<script>
//Pie Chart 3d show
function callAmPieChart() {
	/* am4core.useTheme(am4themes_spiritedaway); */
	am4core.useTheme(am4themes_animated);
	var chart = am4core.create("piediv", am4charts.PieChart);
	chart.hiddenState.properties.opacity = 0; // this creates initial fade-in
	chart.data =${getSupportRequest};
	var pieSeries = chart.series.push(new am4charts.PieSeries());
	pieSeries.dataFields.value = "total";
	pieSeries.dataFields.category = "ticket_status";
	pieSeries.ticks.template.disabled = true;
	pieSeries.labels.template.disabled = true;
	pieSeries.slices.template.stroke = am4core.color("#ccc");
	pieSeries.slices.template.strokeWidth = 0;
	pieSeries.slices.template.strokeOpacity = 1;
	pieSeries.dataFields.radiusValue = "total";	
	pieSeries.slices.template.cornerRadius = 6;
	pieSeries.hiddenState.properties.endAngle = -90;
	//pieSeries.colors.step = 3;
	 var colorSet = new am4core.ColorSet();
	colorSet.list = ["#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc" , "#33E6FF" ,"#B533FF"].map(function(color) {
	  return new am4core.color(color);
	});
	pieSeries.colors = colorSet; 
	chart.legend = new am4charts.Legend();
	chart.legend.useDefaultMarker = true;	
	var marker1 = chart.legend.markers.template.children.getIndex(0);
	marker1.cornerRadius(12, 12, 12, 12);
	marker1.strokeWidth = 2;
	marker1.strokeOpacity = 1;
	marker1.stroke = am4core.color("#ccc"); 
 	//////////////////////////////// end pie chart
}

function callAmLineChart() {
	// Themes begin
	am4core.useTheme(am4themes_animated);
	// Themes end
	// Create chart instance
	var chart = am4core.create("chartdiv", am4charts.XYChart);
	// Add data
	chart.data = ${getActiveUserList};
	// Set input format for the dates
	chart.dateFormatter.inputDateFormat = "yyyy-MM-dd";
	// Create axes
	var dateAxis = chart.xAxes.push(new am4charts.DateAxis());
	var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
	// Create series
	var series = chart.series.push(new am4charts.LineSeries());
	series.dataFields.valueY = "total";
	series.dataFields.dateX = "date";
	series.tooltipText = "{total}"
	series.strokeWidth = 2;
	series.minBulletDistance = 15;
	// Drop-shaped tooltips
	series.tooltip.background.cornerRadius = 20;
	series.tooltip.background.strokeOpacity = 0;
	series.tooltip.pointerOrientation = "vertical";
	series.tooltip.label.minWidth = 40;
	series.tooltip.label.minHeight = 40;
	series.tooltip.label.textAlign = "middle";
	series.tooltip.label.textValign = "middle";

	// Make bullets grow on hover
	var bullet = series.bullets.push(new am4charts.CircleBullet());
	bullet.circle.strokeWidth = 2;
	bullet.circle.radius = 4;
	bullet.circle.fill = am4core.color("#fff");

	var bullethover = bullet.states.create("hover");
	bullethover.properties.scale = 1.3;

	// Make a panning cursor
	chart.cursor = new am4charts.XYCursor();
	chart.cursor.behavior = "panXY";
	chart.cursor.xAxis = dateAxis;
	chart.cursor.snapToSeries = series;

	// Create vertical scrollbar and place it before the value axis
	chart.scrollbarY = new am4core.Scrollbar();
	chart.scrollbarY.parent = chart.leftAxesContainer;
	chart.scrollbarY.toBack();

	// Create a horizontal scrollbar with previe and place it underneath the date axis
	chart.scrollbarX = new am4charts.XYChartScrollbar();
	chart.scrollbarX.series.push(series);
	chart.scrollbarX.parent = chart.bottomAxesContainer;

	chart.events.on("ready", function () {
	  //dateAxis.zoom({start:0.79, end:1});
	});
	
}
</script>	

<script>	
var popupWindow = null
function TicketStatusDetails(url) {
	popupWindow = window.open("TicketStatus", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=1700,height=600,fullscreen=no");
}

var popupWindow = null
function UserCreatedDetails(url) {
	popupWindow = window.open("UserCreated", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=1700,height=600,fullscreen=no");
}

var popupWindow = null
function RoleDetails(url) {
	popupWindow = window.open("RoleUrl", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=1700,height=600,fullscreen=no");
}

var popupWindow = null
function LoggedInDetails(url) {
	popupWindow = window.open("LoggedInUsers", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=1700,height=600,fullscreen=no");
}

var popupWindow = null
function TicketStatusNewDetails(url) {
	popupWindow = window.open("TicketStatusNew", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=1700,height=600,fullscreen=no");
}

var popupWindow = null
function TicketStatusInProgressDetails(url) {
	popupWindow = window.open("TicketStatusInProgress", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=1700,height=600,fullscreen=no");
}

var popupWindow = null
function TicketStatusCompletedDetails(url) {
	popupWindow = window.open("TicketStatusCompleted", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=1700,height=600,fullscreen=no");
}

var popupWindow = null
function HelpTopicFeedbackDetails(url) {
	popupWindow = window.open("HelpTopicFeedback", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=1700,height=600,fullscreen=no");
}

var popupWindow = null
function HelpTopicFeatureRequestDetails(url) {
	popupWindow = window.open("HelpTopicFeatureRequest", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=1700,height=600,fullscreen=no");
}
</script>	
</body>
</html>
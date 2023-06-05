<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>	
<!-- <script type="text/javascript" src="js/InfiniteScroll/etc/buttonCOLVIS.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/etc/button.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/etc/buttonhtml.js"></script>
<link rel="stylesheet" href="js/InfiniteScroll/etc/butonCOL&PRINT.css">
<link rel="stylesheet" href="js/InfiniteScroll/etc/responsive@colvis.css"> -->
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/etc/buttonCOLVIS.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/etc/button.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/etc/buttonhtml.js"></script>
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/etc/butonCOL&PRINT.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/etc/responsive@colvis.css">


<form:form name="course_duration_enroll" id="course_duration_enroll"
	action="course_duration_enrollaction" method="post"
	class="form-horizontal" commandName="course_duration_enroll_CMD"
	enctype="multipart/form-data">

	<div class="cource-main">
		<div class="row">
			<div class="col-12 col-md-12 col-sm-12 text-align-center">
				<h2 class="section-title">COURSE DURATION ENROLLMENT</h2>
			</div>
			<div class="cource-box">
				<div class="row">
					<div class="card enrollcard">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">

							<div class="cource-heading">
								<a href="Search_Course_Duration_url" class="enroll-cource-link"><
									BACK TO COURSE </a>

								<div id="title_div"></div>

								<input type="hidden" id="course_hid" name="course_hid" value="0"
									class="form-control" /> <input type="hidden" id="module_hid"
									name="module_hid" value="0" class="form-control" /> <input
									type="hidden" id="set_hid" name="set_hid" value="0"
									class="form-control" /> <input type="hidden" id="system_hid"
									name="system_hid" value="0" class="form-control" />

							</div>
							<div class="course-details">
								<div id="count"></div>
							</div>
						</div>
						<div class="tbborder">
							<div class="row">

								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<h3 class="enroll-cource-subtitle ">ABOUT THE COURSE</h3>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-4 col-md-4 col-sm-12 col-12">
								<div class="enroll-course-image">

									<div id="div_video"></div>

								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-12 col-12">
								<div class="enroll-course-desc" id="description_div"></div>
							</div>
							<div class="tbborder">
								<div class="row">

									<div class="col-lg-8 col-md-12 col-sm-12 col-12">

										<h3 class="enroll-cource-subtitle">SELECT SET</h3>

										<ul class="set-course-list setbox" id="set_div">
										</ul>

										<div class="cource-heading">
											<h3 class="enroll-cource-subtitle">MODULE</h3>
											<ul class="set-course-list modulediv" id="module_div">
											</ul>
										</div>
									</div>

									<div class="col-lg-4 col-md-12 col-sm-12 col-12 borderleft">
										<div class="cource">
											<h3 class="enroll-cource-subtitle">SUMMARY</h3>

											<ul class="set-course-list summarydiv" id="summary">
												<li>COURSE CONTENT TYPE : <span class="summarydata"><label
														id="content_type"></label></span></li>
												<li>DURATION : <span class="summarydata"><label
														id="duration"></label></span></li>
												<li>START DATE : <span class="summarydata"><label
														id="strt_date"></label></span></li>
												<li>END DATE : <span class="summarydata"><label
														id="end_date"></label></span></li>
											</ul>

										</div>
<!-- 										<h3 class="enroll-cource-subtitle">CREDIT</h3> -->
<!-- 										<br> -->
<!-- 										<table style="width: 100%" id="credit_tb"> -->
<!-- 											<tr> -->
<!-- 												<th>DAYS OF COMPLETION</th> -->
<!-- 												<th>CREDIT</th> -->
<!-- 											</tr> -->
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<c:url value="ExitFromCourseAction" var="ExitFromCourseAction" />
<form:form action="${ExitFromCourseAction}" method="post"
	id="updateForm" name="updateForm" modelAttribute="course_id">
	<input type="hidden" name="course_id" id="course_id" value="0" />
</form:form>

<script>

$(document).ready(function() {
	
	$("#course_hid").val('${cid}');
		SetModule_Fetch1();
		Course_Set();
		videotopicCall1();
});

function Course_Set() {
	var course_id = $('#course_id').val();
	$.post("getCourse_Set1?"+key+"="+value,{course_id:'${cid}'},function(j) {
		for(var i=0;i<j.length;i++){
			
						$("ul#set_div").append('<li class="set-select" ><div class="form-check" >'  
								+'<input class="form-check-input" type="radio" name="set" id="set'+j[i][0]+'" onclick="Module_fetch1(this);" value="'+j[i][0]+'""> '
						        +'<label class="form-check-label setlable" for="flexRadioDefault1"  >  '+j[i][1]+' </label>  </div></li>');
		}
	});
	
	$.post("getSummary1?"+key+"="+value,{course_id:'${cid}'},function(j) {

		$("#content_type").text(j[0][1]);
		$("#duration").text(j[0][4]);
		$("#strt_date").text(j[0][2]);
		$("#end_date").text(j[0][3]);
	});
///Description
$.post("getCourse_Description1?"+key+"="+value,{course_id:'${cid}'},function(j) {
		for(var i=0;i<j.length;i++){
			
					if(i == 0){
						$("div#description_div").append('<br><p for="username" id="description" name="description"> '+j[i][1]+'</p>');	
					}else{
						$("div#description_div").append('<br><p for="username" id="description" name="description"> '+j[i][1]+'</p>');					
						}
		}
});
var course_id = $('#course_id').val();
$.post("getCourseDuration_Title1?"+key+"="+value,{course_id:'${cid}'},function(j) {
	for(var i=0;i<j.length;i++){
		
					$("div#title_div").append('<h3 class="enroll-cource-title"><a href="#">'+j[i][0]+'</a></h3>');
	}
	
	
});
	
$.post("getLearn_Count1?"+key+"="+value,{course_id:'${cid}'},function(j) {
	for(var i=0;i<j.length;i++){
		
					$("div#count").append('<span class="learncount" >Learners Enrolled:<label>'+j[i][0]+'</label></span>');	
					
	}
});

$.post("getCredit_Point1?"+key+"="+value,{course_id:'${cid}'},function(j) {
		for(var i=0;i<j.length;i++){
			$("table#credit_tb").append('<tr><td id="no_of_days">'+j[i][0]+'</td><td id="credit_point">'+j[i][1]+'</td></tr>');
		}
});

}


	function Module_fetch1(obj) {
		$("ul#module_div").empty();
		var eleID = obj.id;
		var set = $("#" + eleID).val();
		$('#set_hid').val(set);
		$.post("getModule_fetch1?" + key + "=" + value, {
			set : set,
			course_id : '${cid}'
		}, function(j) {
			var module = "";

			if (j.length == 0) {
				$("ul#module_div").append(
						'<li> <span>MODULE NOT AVAILABLE</span></li>');
			} else {
				for (var i = 0; i < j.length; i++) {
					module = j[i][0];
					$("ul#module_div").append(
							'<li> <span>(' + parseInt(i + 1) + ')  ' + j[i][1]
									+ '</span></li>');
				}
			}
			$('#module_hid').val(module);
		});
	}

	function videotopicCall1() {

		var id = '${cid}';

		$('div#div_video').empty();

		$("div#div_video")
				.append(
						'<video onended="video_ended()" id="ref_video" controls width="100%" height="250"><source id="sourceid" src="videocourseenroll1?kmlFileId65='
								+ id
								+ '&fildname=ref_video" type="video/mp4"></video>'
								+ '</tr>');
		var modal = document.getElementById("videoModal");
		var span = document.getElementsByClassName("Vclose")[0];

		document.getElementById('videoModal').style.display = 'block';

		span.onclick = function() {
			modal.style.display = "none";
		}
	}

	function SetModule_Fetch1() {

		$.post("getSetModule_Fetch1?" + key + "=" + value, {
			course_id : '${cid}'
		}, function(j) {
			if (j != "") {
				var c = j[0][0];
				document.getElementById('set' + c).click();
			}
		});
	}
</script>
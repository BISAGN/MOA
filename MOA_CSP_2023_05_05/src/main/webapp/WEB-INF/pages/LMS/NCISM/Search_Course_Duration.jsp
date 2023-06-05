<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>	
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/etc/buttonCOLVIS.js"></script> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/etc/button.min.js"></script> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/etc/buttonhtml.js"></script> -->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/etc/butonCOL&PRINT.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/etc/responsive@colvis.css">


<section class="dashboard-page">
      <div class="container-fluid">        
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-lg-6 col-md-6 col-sm-12 col-12">
              <div class="title mb-30">
                <h2>Course Duration Report</h2>
              </div>
            </div>
            <!-- end col -->
            <div class="col-lg-6 col-md-6 col-sm-12 col-12">
              <div class="breadcrumb-wrapper mb-30">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">					
					<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
					<li class="breadcrumb-item active" aria-current="page">Course Duration Report</li>
				  </ol>
                </nav>
              </div>
            </div>
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
        <!-- title-wrapper end -->

        <div class="row mt-3">
          <div class="col-lg-12">
            <div class="card-style mb-30">             
            	<form:form name="stu_elect_course" id="stu_elect_course" action="" method="post" class="form-horizontal" commandName="stu_elect_course_CMD" enctype="multipart/form-data">
            		<div class="custom-part-col">
            			<div class="row">
            				<div class="col-12 col-lg-3 col-md-12 col-sm-12">
            					<div class="aside">
            						<h3 class="filter-title">Filters</h3>
            						<ul class="col-list list-none">
            							<li class="col-list-inner">
											<div class="select-style-1">
												<label for="text-input">Course Duration<strong class="mandatory">*</strong></label>												
												<div class="select-position"> 
													<select class="singleselect form-control form-control-lg" name="course_duration" id="course_duration"
															class="form-control">
															<option value="0">All</option>
															<c:forEach var="item" items="${getCourse_Duration}"
																varStatus="num">
																<option value="${item[0]}" name="${item[0]}">${item[0]}</option>
															</c:forEach>
													</select>
												</div>
											</div>
            							</li>
            							<li class="col-list-inner">
											<div class="select-style-1">
												<label for="text-input">Course Start Date<strong class="mandatory">*</strong></label>												
												<div class="select-position">
													<select class="singleselect form-control form-control-lg" name="course_start_date" id="course_start_date"
														class="form-control">
														<option value="0">All</option>
														<c:forEach var="item" items="${getCourse_Start_Date}"
															varStatus="num">
															<option value="${item[0]}" name="${item[0]}">${item[0]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
            							</li>
            							
            							<li class="col-list-inner">
											<div class="select-style-1">
												<label for="text-input">System<strong class="mandatory">*</strong></label>												
												<div class="select-position">
													<select class="singleselect form-control form-control-lg" name="system_id" id="system_id"
														class="form-control" >
														<option value="0">All</option>
														<c:forEach var="item" items="${getSystemList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
            							</li>
            							<li class="col-list-inner">
											<div class="select-style-1">
												<label for="text-input">Degree<strong class="mandatory">*</strong></label>												
												<div class="select-position">
<!-- 												<select name="degree_id" id="degree_id"> -->
<!-- 										              <option value="0">ALL</option> -->
<!-- 								                </select> -->
													<select class="singleselect form-control form-control-lg" name="degree_id" id="degree_id"
														class="form-control" >
														<option value="0">All</option>
<%-- 														<c:forEach var="item" items="${getcoursenameList}" --%>
<%-- 														varStatus="num"> --%>
<%-- 														<option value="${item[0]}" name="${item[0]}">${item[1]}</option> --%>
<%-- 														</c:forEach> --%>
													</select>
												</div>
											</div>
            							</li>
            							<li class="col-list-inner">
											<div class="select-style-1">
												<label for="text-input">Term<strong class="mandatory">*</strong></label>												
												<div class="select-position">
													<select class="singleselect form-control form-control-lg" name="term_id" id="term_id"
														class="form-control" >
														<option value="0">All</option>
														<c:forEach var="item" items="${gettermList}"
														varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
														</c:forEach>
													</select>
												</div>
											</div>
            							</li>
<!--             							<li class="col-list-inner"> -->
<!-- 											<div class="select-style-1"> -->
<!-- 												<label for="text-input">Course Category<strong class="mandatory">*</strong></label>												 -->
<!-- 												<div class="select-position"> -->
<!-- 													<select name="course_category" id="course_category" -->
<!-- 														class="form-control" onchange="Course_wise_images_fetch();"> -->
<!-- 														<option value="0">All</option> -->
<%-- 														<c:forEach var="item" items="${getcoursenameList}" --%>
<%-- 														varStatus="num"> --%>
<%-- 														<option value="${item[0]}" name="${item[0]}">${item[1]}</option> --%>
<%-- 														</c:forEach> --%>
<!-- 													</select> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!--             							</li> -->
            						</ul>
            					</div>
            				</div>
            				<div class="col-12 col-lg-9 col-md-12 col-sm-12">
            					<div class="custom-tabs">
									<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
<!-- 										<li class="nav-item" role="presentation"> -->
<!-- 											<button class="nav-link active" id="pills-course-tab" -->
<!-- 												data-bs-toggle="pill" data-bs-target="#pills-course" -->
<!-- 												type="button" role="tab" aria-controls="pills-course" -->
<!-- 												aria-selected="true" onclick="openCity(event, 'Upcoming_Enroll_Open')">Courses</button> -->
<!-- 										</li> -->
										<li class="nav-item" role="presentation">
											<button class="nav-link active" id="pills-profile-tab"
												data-bs-toggle="pill" data-bs-target="#pills-mcourse"
												type="button" role="tab" aria-controls="pills-pmcourse"
												aria-selected="false">Courses</button>
										</li>									
									</ul>
									<div class="tab-content" id="pills-tabContent">
<!-- 										<div class="tab-pane fade show active" id="pills-course" role="tabpanel" aria-labelledby="pills-mcourse-tab"> -->
<!-- 											<div class="tab-content-inner" id="Upcoming_Enroll_Open"></div> -->
<!-- 										</div> -->
										<div class="tab-pane fade show active" id="pills-mcourse" role="tabpanel" aria-labelledby="pills-course-tab">
											<div class="tab-content-inner" id="Already_Applied"></div>
										</div>									
									</div>
								</div>								
							</div>
            			</div>
            		</div>
            	</form:form> 
         	</div>
          </div>
        </div>

        <!-- End Row -->
      </div>
      <!-- end container -->
</section>


<%-- <form:form name="stu_elect_course" id="stu_elect_course" action=""
	method="post" class="form-horizontal"
	commandName="stu_elect_course_CMD" enctype="multipart/form-data">
	
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="card-header">
					<h5>SELECT ELECTIVE COURSES</h5>
				</div>
				<div class="card-body card-block">
					<div class="row">
						<div class="col-lg-3 col-md-12 col-12">
							<div class="leftfilter">
								<div class="col-12 col-lg-12">
									<h3 class="leftfiltertitle">FILTERS</h3>
								</div>
								<div class="col-12 col-lg-12">
									<label for="text-input" class=" form-control-label">
										COURSE DURATION <strong style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-lg-12">
									<select name="course_duration" id="course_duration"
										class="form-control" onchange="Course_wise_images_fetch();">
										<option value="0">ALL</option>
										<c:forEach var="item" items="${getCourse_Duration}"
											varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[0]}</option>
										</c:forEach>
									</select>
								</div>
								<br> <br>
								<div class="col-12 col-lg-12">
									<label for="text-input" class=" form-control-label">
										COURSE START DATE <strong style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-lg-12">
									<select name="course_start_date" id="course_start_date"
										class="form-control" onchange="Course_wise_images_fetch();">
										<option value="0">ALL</option>
										<c:forEach var="item" items="${getCourse_Start_Date}"
											varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[0]}</option>
										</c:forEach>
									</select>
								</div>
								<br> <br>
								<div class="col-12 col-lg-12">
									<label for="text-input" class=" form-control-label ">
										COURSE CATEGORY <strong style="color: red;">*</strong>
									</label>
								</div>
								<div class="col-12 col-lg-12">
									<select name="course_category" id="course_category"
										class="form-control" onchange="Course_wise_images_fetch();">
										<option value="0">ALL</option>
										<c:forEach var="item" items="${getcoursenameList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
								<br> <br>

							</div>
						</div>

						<div class="col-lg-9 col-md-12 col-12  righttab">

							<div class="tab tabhead">
								<button type="button" class="tablinks active"
									onclick="openCity(event, 'Upcoming_Enroll_Open')">Courses</button>
<!-- 								<button type="button" class="tablinks" -->
<!-- 									onclick="openCity(event, 'OnGoing_Enroll_Closed')">OnGoing -->
<!-- 									(Enrollment CLosed)</button> -->
								<button type="button" class="tablinks"
									onclick="openCity(event, 'Already_Applied')">My
									Courses</button>
							</div>
							<div class="rightpanelcard">
								<div id="Upcoming_Enroll_Open" class="tabcontent"
									style="display: block;"></div>

<!-- 								<div id="OnGoing_Enroll_Closed" class="tabcontent"></div> -->

								<div id="Already_Applied" class="tabcontent"></div>

							</div>
							
<!-- 							<video controls autoplay id="ref_video"  width="100%" height="250"> -->
<!-- 							<source id="sourceid" src="https://youtu.be/jcN1mBpMbWA" type="video/mp4"> -->
<!-- 							</video> -->

<!-- <iframe width="420" height="315" -->
<!-- src="https://www.youtube.com/embed/tgbNymZ7vqY"> -->
<!-- </iframe> -->

						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</form:form> --%>


<c:url value="Course_Enroll_Url" var="Course_Enroll_Url" />
<form:form action="${Course_Enroll_Url}" method="get" id="updateForm"
	name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="ExitFromCourseAction" var="ExitFromCourseAction" />
<form:form action="${ExitFromCourseAction}" method="post" id="exitForm"
	name="exitForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	
	Onload_ImageDescription_fetch();
	
	
	setTimeout(setTimeLoadForTable, 1000);
	
});



function setTimeLoadForTable(){

	document.getElementById('course_duration').onclick = function() {
		Course_wise_images_fetch();
	};

	
	document.getElementById('course_start_date').onclick = function() {
		Course_wise_images_fetch();
	};
	
	document.getElementById('system_id').onclick = function() {
		Course_wise_images_fetch();
	};
	
	document.getElementById('system_id').onchange = function() {
		getdegreebysystem();
	};
	
	
	document.getElementById('degree_id').onclick = function() {
		Course_wise_images_fetch();
	};
	
	document.getElementById('term_id').onclick = function() {
		Course_wise_images_fetch();
	};
	
}



function getdegreebysystem(obj) {
	var system_id = $("#system_id").val();
	$.post('getDegreefromSystem?' + key + "=" + value, {system_id : system_id}).done(function(j) {
		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		for (var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		}
		$("select#degree_id").html(options);
	});
}


	function openCity(evt, cityName) {
		var i, tabcontent, tablinks;
		tabcontent = document.getElementsByClassName("tabcontent");
		for (i = 0; i < tabcontent.length; i++) {
			tabcontent[i].style.display = "none";
		}
		tablinks = document.getElementsByClassName("tablinks");
		for (i = 0; i < tablinks.length; i++) {
			tablinks[i].className = tablinks[i].className
					.replace(" active", "");
		}
		document.getElementById(cityName).style.display = "block";
		evt.currentTarget.className += " active";
	}

	function Course_wise_images_fetch() {
// 		debugger;
		$.ajaxSetup({
			async : false
		});
// 		$('div#Upcoming_Enroll_Open').empty();
// 		$('div#OnGoing_Enroll_Closed').empty();
		$('div#Already_Applied').empty();

		//-------------------Upcoming_Enroll_Open----------
		var d = $("select#course_duration").val();
		var sdt = $("select#course_start_date").val();
		//var c_id = $("select#course_category").val();
		var s_id = $("select#system_id").val();
		var d_id = $("select#degree_id").val();
		var t_id = $("select#term_id").val();

// 		 $
// 				.post(
// 						"getCourses_Description_fetch_new_Search?" + key + "=" + value,
// 						{
// 							c_id : c_id,
// 							course_duration : d,
// 							course_start_date : sdt,
// 							system_id : s_id,
// 							degree_id : d_id,
// 							term_id : t_id
							
// 						},
// 						function(j) {
// 							if (j != "") {
// 								for (var i = 0; i < j.length; i++) {
// 									if (i == 0) {
// 										$("div#Upcoming_Enroll_Open")	
// 													.append(
// 														'<div class="row" id="upcomingRow"><div class="col-xl-4 col-lg-6 col-md-12 col-12"><div class="card custom-card">'
// 																+ '<video controls autoplay id="ref_video"><source id="sourceid" src="videoChoose_Ele_Course_Stu_Search?i_id='
// 																+ j[i][0]
// 																+ '" type="video/mp4"></video>'
// 																+ '<div class="card-body"><h4 class="card-title"><a href="#">title here'
// 																+ '</a></h4>'
// 																+ '<div class="card-detais"><p>'
// 																+ j[i][1]
// 																+ '</p><p><a href="#" class="readmore">read more &gt;</a></p></div>'
// 																+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
// 																+ j[i][2]
// 																+ ')</p></div>'
// 																+ '<ul class="buttons-group card-btn-footer"><li><button type="button" class="main-btn active-btn-outline btn-hover" onclick="course_Enroll('
// 																+ j[i][0]
// 																+ ');">Enroll Now</button></li>'
// 																/* +'<button type="button" class="custom-btn btn-7 mt-3" onclick="course_Enroll('+j[i][0]+ ');"><span>Enroll Now</span></button>' */
// 																// 															+ '<a href="Course_Enroll_Url" class="custom-btn btn-7 mt-3"><span>Enroll Now</span></a>'
// 																+ '</div></div></div></div>');
// 										/* .append(
// 												'<div class="row" id="upcomingRow"><div class="col-lg-4 col-md-12 col-12"><div class="card carddiv">'
// 												+'<video controls autoplay id="ref_video"  width="100%" height="174"><source id="sourceid" src="videoChoose_Ele_Course_Stu?i_id='+j[i][0]+'" type="video/mp4"></video>'
// 												+ '<div class="card-body"><a href="#"><h5 class="card-title">'
// 												+ '</h5></a>'
// 												+ '<div class="cardcont"><p class="card-text">'
// 												+ j[i][1]
// 												+ '</p><p><a href="#" class="readmore">read more &gt;&gt;</a></p></div>'
// 												+ '<ul class="cardlist"><li class="list-group-item"><i class="fa fa-calendar"></i>(Starts: '
// 												+ j[i][2]
// 												+ ')</li>'
// 										// 														+'<a href="Course_Enroll_Url" class="custom-btn btn-7 mt-3"><span>Enroll Now</span></a>'
// 												+'<button type="button" class="custom-btn btn-7 mt-3" onclick="course_Enroll('+j[i][0]+ ');"><span>Enroll Now</span></button>'
// 												+ '</div></div></div></div>'); */
// 									} else {
// 										$("div#upcomingRow")
// 										.append(
// 														'<div class="col-xl-4 col-lg-6 col-md-12 col-12"><div class="card custom-card">'
// 																+ '<video controls autoplay id="ref_video"><source id="sourceid" src="videoChoose_Ele_Course_Stu_Search?i_id='
// 																+ j[i][0]
// 																+ '" type="video/mp4"></video>'
// 																+ '<div class="card-body"><h4 class="card-title"><a href="#">title here'
// 																+ '</a></h4>'
// 																+ '<div class="card-detais"><p>'
// 																+ j[i][1]
// 																+ '</p><p><a href="#" class="readmore">read more &gt;</a></p></div>'
// 																+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
// 																+ j[i][2]
// 																+ ')</p></div>'
// 																+ '<ul class="buttons-group card-btn-footer"><li><button type="button" class="main-btn active-btn-outline btn-hover" onclick="course_Enroll('
// 																+ j[i][0]
// 																+ ');">Enroll Now</button></li>'
// 																+ '</div></div></div>');
												
// 									}
// 								}
// 							} else {
// 								$("div#Upcoming_Enroll_Open")
// 										.append(
// 												'<label for="one" class="">Data Not Available</label>');
// 							}

// 						}); 

		//------------------------------My Courses-------------------------------------------//

		$
				.post(
						"getDescriptionfetchAlreadyAppliedMyCourses_Search?" + key
								+ "=" + value,
						{
							//c_id : c_id,
							
							
							course_duration : d,
							course_start_date : sdt,
							system_id : s_id,
							degree_id : d_id,
							term_id : t_id
						},
						function(j) {
							
							
							
							if (j != "") {
								for (var i = 0; i < j.length; i++) {

									
									
									if (i == 0) {
										$("div#Already_Applied")
										.append(
												'<div class="row" id="Already_AppRow"><div class="col-lg-4 col-md-12 col-12"><div class="card custom-card">'
														+ '<div class="cardimg"('
														+ j[i][1]
														+ ');"><img class="card-img-block" alt="No Image" id="myImg'
														+ j[i][1]
														+ '" src="Image_Fetch_Path_Already_Applied_My_Courses_Search?i_id='
														+ j[i][0]
														+ '"><div id="count" class="main-count"><span class="learncount" >Learners Enrolled:<span class="count-no">'+j[i][5]+'</span></span></div></div>'
														+ '<div class="card-body"><h4 class="card-subtitle">'
														+ j[i][1]
														+ '</h4><h4 class="card-title"><a href="#">'
														+ j[i][2]
														+ '</a></h4>'
														/* +'<div id="count" class="main-count"><span class="learncount" >Learners Enrolled:<span class="count-no">'+j[i][5]+'</span></span></div>' */
														+ '<div class="card-detais"><p>'
														+ j[i][3]
														+ '</p><p><a href="#" class="readmore">read more &gt;</a></p></div>'
														+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
														+ j[i][4]
														+ ')</p></div>'
// 														+'<ul class="buttons-group card-btn-footer"><li><button type="button" class="main-btn dark-btn-outline btn-hover" onclick="Exit_Enroll('+j[i][6]+ ');">Exit Course</button></li></ul>'
														+ '</div></div></div></div>');
							} else {
								$("div#Already_AppRow")
										.append(
												'<div class="col-lg-4 col-md-12 col-12"><div class="card custom-card">'
														+ '<div class="cardimg" ><img class="card-img-block" alt="No Image" id="myImg'
														+ j[i][1]
														+ '" src="Image_Fetch_Path_Already_Applied_My_Courses_Search?i_id='
														+ j[i][0]
															+ '"><div id="count" class="main-count"><span class="learncount" >Learners Enrolled:<span class="count-no">'+j[i][5]+'</span></span></div></div>'
														+ '<div class="card-body"><h4 class="card-subtitle">'
														+ j[i][1]
														+ '</h4><h4 class="card-title"><a href="#">'
														+ j[i][2]
														+ '</a></h4>'
														/* +'<div id="count" class="main-count"><span class="learncount" >Learners Enrolled:<span class="count-no">'+j[i][5]+'</span></span></div>' */
														+ '<div class="card-detais"><p>'
														+ j[i][3]
														+ '</p><p><a href="#" class="readmore">read more &gt;</a></p></div>'
														+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
														+ j[i][4]
														+ ')</p></div>'
// 														+'<ul class="buttons-group card-btn-footer"><li><button type="button" class="main-btn dark-btn-outline btn-hover" onclick="Exit_Enroll('+j[i][6]+ ');">Exit Course</button></li></ul>'
														+ '</div></div></div>');
									}
								}
							} else {
								$("div#Already_Applied")
										.append(
												'<label for="one" class="">Data Not Available</label>');
							}

						});

	}

	function Onload_ImageDescription_fetch() {
		$.ajaxSetup({
			async : false
		});

// 		$('div#Upcoming_Enroll_Open').empty();
		// 		$('div#OnGoing_Enroll_Closed').empty();
		$('div#Already_Applied').empty();

		var d = $("select#course_duration").val();
		var sdt = $("select#course_start_date").val();
		//var c_id = $("select#course_category").val();
		var s_id = $("select#system_id").val();
		var d_id = $("select#degree_id").val();
		var t_id = $("select#term_id").val();

		//-------------------Upcoming_Enroll_Open----------

// 		 $
// 				.post(
// 						"getCourses_Description_fetch_new_Search?" + key + "=" + value,
// 						{
// 							c_id : c_id,
// 							course_duration : d,
// 							course_start_date : sdt,
// 							system_id : s_id,
// 							degree_id : d_id,
// 							term_id : t_id
// 						},
// 						function(j) {
// 							// 							alert(j.length);
// 							for (var i = 0; i < j.length; i++) {
// 								if (i == 0) {
// 									$("div#Upcoming_Enroll_Open")
// 											.append(
// 													'<div class="row" id="upcomingRow"><div class="col-xl-4 col-lg-6 col-md-12 col-12"><div class="card custom-card">'
// 															+ '<video controls autoplay id="ref_video"><source id="sourceid" src="videoChoose_Ele_Course_Stu_Search?i_id='
// 															+ j[i][0]
// 															+ '" type="video/mp4"></video>'
// 															+ '<div class="card-body"><h4 class="card-title"><a href="#">title here'
// 															+ '</a></h4>'
// 															+ '<div class="card-detais"><p>'
// 															+ j[i][1]
// 															+ '</p><p><a href="#" class="readmore">read more &gt;</a></p></div>'
// 															+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
// 															+ j[i][2]
// 															+ ')</p></div>'
// 															+ '<ul class="buttons-group card-btn-footer"><li><button type="button" class="main-btn active-btn-outline btn-hover" onclick="course_Enroll('
// 															+ j[i][0]
// 															+ ');">Enroll Now</button></li>'
// 															/* +'<button type="button" class="custom-btn btn-7 mt-3" onclick="course_Enroll('+j[i][0]+ ');"><span>Enroll Now</span></button>' */
// 															// 															+ '<a href="Course_Enroll_Url" class="custom-btn btn-7 mt-3"><span>Enroll Now</span></a>'
// 															+ '</div></div></div></div>');
// 								} else {
// 									$("div#upcomingRow")
// 											.append(
// 													'<div class="col-xl-4 col-lg-6 col-md-12 col-12"><div class="card custom-card">'
// 															+ '<video controls autoplay id="ref_video"><source id="sourceid" src="videoChoose_Ele_Course_Stu_Search?i_id='
// 															+ j[i][0]
// 															+ '" type="video/mp4"></video>'
// 															+ '<div class="card-body"><h4 class="card-title"><a href="#">title here'
// 															+ '</a></h4>'
// 															+ '<div class="card-detais"><p>'
// 															+ j[i][1]
// 															+ '</p><p><a href="#" class="readmore">read more &gt;</a></p></div>'
// 															+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
// 															+ j[i][2]
// 															+ ')</p></div>'
// 															+ '<ul class="buttons-group card-btn-footer"><li><button type="button" class="main-btn active-btn-outline btn-hover" onclick="course_Enroll('
// 															+ j[i][0]
// 															+ ');">Enroll Now</button></li>'
// 															/* +'<button type="button" class="custom-btn btn-7 mt-3" onclick="course_Enroll('+j[i][0]+ ');"><span>Enroll Now</span></button>' */
// 															// 															+ '<a href="Course_Enroll_Url" class="custom-btn btn-7 mt-3"><span>Enroll Now</span></a>'
// 															+ '</div></div></div>');
// 									/* .append(
// 											'<div class="col-lg-4 col-md-12 col-12"><div class="card carddiv">'
// 													+'<video controls autoplay id="ref_video"  width="100%" height="174"><source id="sourceid" src="videoChoose_Ele_Course_Stu?i_id='+j[i][0]+'" type="video/mp4"></video>'
// 													+ '<div class="card-body"><a href="#"><h5 class="card-title">'
// 													+ '</h5></a>'
// 													+ '<div class="cardcont"><p class="card-text">'
// 													+ j[i][1]
// 													+ '</p><p><a href="#" class="readmore">read more &gt;&gt;</a></p></div>'
// 													+ '<ul class="cardlist"><li class="list-group-item"><i class="fa fa-calendar"></i>(Starts: '
// 													+ j[i][2]
// 													+ ')</li>'
// 													+'<button type="button" class="custom-btn btn-7 mt-3" onclick="course_Enroll('+j[i][0]+ ');"><span>Enroll Now</span></button>'
// 									// 															+ '<a href="Course_Enroll_Url" class="custom-btn btn-7 mt-3"><span>Enroll Now</span></a>'
// 													+ '</div></div></div>'); */
// 								}
// 							}
// 						}); 

		//------------------------------myCourses start here-------------------------------------------------///

		$
				.post(
						"getDescriptionfetchAlreadyAppliedMyCourses_Search?" + key
								+ "=" + value,
						{
							//c_id : c_id,
							course_duration : d,
							course_start_date : sdt,
							system_id : s_id,
							degree_id : d_id,
							term_id : t_id
						},
						function(j) {
// 							alert(j.length);
							if (j != "") {
							for (var i = 0; i < j.length; i++) {

								if (i == 0) {
									$("div#Already_Applied")
											.append(
													'<div class="row" id="Already_AppRow"><div class="col-lg-4 col-md-12 col-12"><div class="card custom-card">'
															+ '<div class="cardimg" ><img class="card-img-block" alt="No Image" id="myImg'
															+ j[i][1]
															+ '" src="Image_Fetch_Path_Already_Applied_My_Courses_Search?i_id='
															+ j[i][0]
															+ '"><div id="count" class="main-count"><span class="learncount" >Learners Enrolled:<span class="count-no">'+j[i][5]+'</span></span></div></div>'
															+ '<div class="card-body"><h4 class="card-subtitle">'
															+ j[i][1]
															+ '</h4><h4 class="card-title"><a href="#">'
															+ j[i][2]
															+ '</a></h4>'
															/* +'<div id="count" class="main-count"><span class="learncount" >Learners Enrolled:<span class="count-no">'+j[i][5]+'</span></span></div>' */
															+ '<div class="card-detais"><p>'
															+ j[i][3]
															+ '</p><p><a href="#" class="readmore">read more &gt;</a></p></div>'
															+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
															+ j[i][4]
															+ ')</p></div>'
// 															+'<ul class="buttons-group card-btn-footer"><li><button type="button" class="main-btn dark-btn-outline btn-hover" onclick="Exit_Enroll('+j[i][6]+ ');">Exit Course</button></li></ul>'
															+ '</div></div></div></div>');
								} else {
									$("div#Already_AppRow")
											.append(
													'<div class="col-lg-4 col-md-12 col-12"><div class="card custom-card">'
															+ '<div class="cardimg" ><img class="card-img-block" alt="No Image" id="myImg'
															+ j[i][1]
															+ '" src="Image_Fetch_Path_Already_Applied_My_Courses_Search?i_id='
															+ j[i][0]
																+ '"><div id="count" class="main-count"><span class="learncount" >Learners Enrolled:<span class="count-no">'+j[i][5]+'</span></span></div></div>'
															+ '<div class="card-body"><h4 class="card-subtitle">'
															+ j[i][1]
															+ '</h4><h4 class="card-title"><a href="#">'
															+ j[i][2]
															+ '</a></h4>'
															/* +'<div id="count" class="main-count"><span class="learncount" >Learners Enrolled:<span class="count-no">'+j[i][5]+'</span></span></div>' */
															+ '<div class="card-detais"><p>'
															+ j[i][3]
															+ '</p><p><a href="#" class="readmore">read more &gt;</a></p></div>'
															+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
															+ j[i][4]
															+ ')</p></div>'
// 															+'<ul class="buttons-group card-btn-footer"><li><button type="button" class="main-btn dark-btn-outline btn-hover" onclick="Exit_Enroll('+j[i][6]+ ');">Exit Course</button></li></ul>'
															+ '</div></div></div>');
								}

							}
						} else {
								$("div#Already_Applied")
										.append(
												'<label for="one" class="">Data Not Available</label>');
							}

						});
	}
	

// 	function Exit_Enroll(id) {
// 			$("#id2").val(id);
// 			document.getElementById('exitForm').submit();
// 	}



		
</script>
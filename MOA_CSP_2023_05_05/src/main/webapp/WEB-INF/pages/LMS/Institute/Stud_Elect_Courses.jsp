<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- <script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>	 -->
<link rel="stylesheet" href="assets/vendor/internal_css.css">
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/etc/buttonCOLVIS.js"></script> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/etc/button.min.js"></script> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/etc/buttonhtml.js"></script> -->
<!-- <link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/etc/butonCOL&PRINT.css"> -->
<!-- <link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/etc/responsive@colvis.css"> -->



<section class="dashboard-page">
      <div class="container-fluid">        
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-lg-6 col-md-6 col-sm-12 col-12">
              <div class="title mb-30">
                <h2>Enroll Elective Course</h2>
              </div>
            </div>
            <!-- end col -->
            <div class="col-lg-6 col-md-6 col-sm-12 col-12">
              <div class="breadcrumb-wrapper mb-30">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">					
					<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
					<li class="breadcrumb-item active" aria-current="page">Enroll Elective Course</li>
				  </ol>
                </nav>
              </div>
            </div>
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
        <!-- title-wrapper end -->

       <div class="form-elements-wrapper">
        <div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
            <div class="card-style mb-30">             
            	<form:form name="stu_elect_course" id="stu_elect_course" action="" method="post" commandName="stu_elect_course_CMD" enctype="multipart/form-data">
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
													<select class="singleselect form-control form-control-lg" name="course_duration" id="course_duration">
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
												<label for="text-input">Course Category<strong class="mandatory">*</strong></label>												
												<div class="select-position">
													<select class="singleselect form-control form-control-lg" name="course_category" id="course_category"
														class="form-control" >
														<option value="0">All</option>
														<c:forEach var="item" items="${getcoursenameList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
            							</li>
            						</ul>
            					</div>
            				</div>
            				<div class="col-12 col-lg-9 col-md-12 col-sm-12">
            					<div class="custom-tabs">
									<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
										<li class="nav-item" role="presentation">
											<button class="nav-link active" id="pills-course-tab"
												data-bs-toggle="pill" data-bs-target="#pills-course"
												type="button" role="tab" aria-controls="pills-course"
												aria-selected="true" >Courses</button>
										</li>
										<li class="nav-item" role="presentation">
											<button class="nav-link" id="pills-profile-tab"
												data-bs-toggle="pill" data-bs-target="#pills-mcourse"
												type="button" role="tab" aria-controls="pills-pmcourse"
												aria-selected="false">My Courses</button>
										</li>									
									</ul>
									<div class="tab-content" id="pills-tabContent">
										<div class="tab-pane fade show active" id="pills-course" role="tabpanel" aria-labelledby="pills-course-tab">
											<div class="tab-content-inner" id="Upcoming_Enroll_Open"></div>
										</div>
										<div class="tab-pane fade" id="pills-mcourse" role="tabpanel" aria-labelledby="pills-mcourse-tab">
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
      </div>
      <!-- end container -->
</section>




<c:url value="Course_Enroll_Url" var="Course_Enroll_Url" />
<form:form action="${Course_Enroll_Url}" method="get" id="updateForm"
	name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
	<input type="hidden" name="termhidden1" id="termhidden1" value="0" />
</form:form>
<c:url value="Course_Enroll_Url" var="Course_Enroll_Url" />
<form:form action="${Course_Enroll_Url}" method="get" id="if_exitForm"
	name="if_exitForm" modelAttribute="exit_id1">
	<input type="hidden" name="exit_id1" id="exit_id1" value="1" />
	<input type="hidden" name="exit_course_id" id="exit_course_id" value="" />
	<input type="hidden" name="exit_id_Payment1" id="exit_id_Payment1" value="0" />	
</form:form>

<c:url value="ExitFromCourseAction" var="ExitFromCourseAction" />
<form:form action="${ExitFromCourseAction}" method="post" id="exitForm"
	name="exitForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
	<input type="hidden" name="setid2" id="setid2" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	
	Onload_ImageDescription_fetch();
	
// 	if(window.location.href.includes("msg"))
// 		{
// 		 var url = window.location.href.split("?msg")[0];
// 		 window.location = url;
// 		}
	
	
});


function course_Enroll(id) {
	$("#id1").val(id);
	$("#termhidden1").val($("#termhidden"+id).val());
	document.getElementById('updateForm').submit();
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
		$.ajaxSetup({
			async : false
		});
		$('div#Upcoming_Enroll_Open').empty();
// 		$('div#OnGoing_Enroll_Closed').empty();
		$('div#Already_Applied').empty();

		//-------------------Upcoming_Enroll_Open----------
		var d = $("select#course_duration").val();
		var sdt = $("select#course_start_date").val();
		var c_id = $("select#course_category").val();

		$
				.post(
						"getCourses_Description_fetch_new?" + key + "=" + value,
						{
							c_id : c_id,
							course_duration : d,
							course_start_date : sdt
						},
						function(j) {
							if (j != "") {
								for (var i = 0; i < j.length; i++) {
									if (i == 0) {
										$("div#Upcoming_Enroll_Open")	
													.append(
														'<div class="row" id="upcomingRow"><div class="col-xl-4 col-lg-6 col-md-12 col-12"><div class="card custom-card">'
																+ '<video controls autoplay id="ref_video"><source id="sourceid" src="videoChoose_Ele_Course_Stu?i_id='
																+ j[i][0]
																+ '" type="video/mp4"></video>'
																+ '<div class="card-body"><h4 class="card-title"><a href="#">'
																+ j[i][2]
																+ '</a></h4>'
																+ '<div class="card-detais"><p>'
																+ j[i][1]
																+ '</p><p><a href="#" class="readmore">read more &gt;</a><input type="hidden" value="'+j[i][3]+'" name="termhidden'+j[i][0]+'" id="termhidden'+j[i][0]+'"></p></div>'
// 																+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
// 																+ j[i][2]
// 																+ ')</p></div>'
																+ '<ul class="buttons-group card-btn-footer"><li><button id="btncourseEnroll'+j[i][0]+'" type="button" class="main-btn active-btn-outline btn-hover">Enroll Now</button></li>'//onclick="course_Enroll('j[i][0]');"
																/* +'<button type="button" class="custom-btn btn-7 mt-3" onclick="course_Enroll('+j[i][0]+ ');"><span>Enroll Now</span></button>' */
																// 															+ '<a href="Course_Enroll_Url" class="custom-btn btn-7 mt-3"><span>Enroll Now</span></a>'
																+ '</div></div></div></div>');
										                        setEnrollevent(j[i][0]);
										/* .append(
												'<div class="row" id="upcomingRow"><div class="col-lg-4 col-md-12 col-12"><div class="card carddiv">'
												+'<video controls autoplay id="ref_video"  width="100%" height="174"><source id="sourceid" src="videoChoose_Ele_Course_Stu?i_id='+j[i][0]+'" type="video/mp4"></video>'
												+ '<div class="card-body"><a href="#"><h5 class="card-title">'
												+ '</h5></a>'
												+ '<div class="cardcont"><p class="card-text">'
												+ j[i][1]
												+ '</p><p><a href="#" class="readmore">read more &gt;&gt;</a></p></div>'
												+ '<ul class="cardlist"><li class="list-group-item"><i class="fa fa-calendar"></i>(Starts: '
												+ j[i][2]
												+ ')</li>'
										// 														+'<a href="Course_Enroll_Url" class="custom-btn btn-7 mt-3"><span>Enroll Now</span></a>'
												+'<button type="button" class="custom-btn btn-7 mt-3" onclick="course_Enroll('+j[i][0]+ ');"><span>Enroll Now</span></button>'
												+ '</div></div></div></div>'); */
									} else {
										
										$("div#upcomingRow")
										.append(
														'<div class="col-xl-4 col-lg-6 col-md-12 col-12"><div class="card custom-card">'
																+ '<video controls autoplay id="ref_video"><source id="sourceid" src="videoChoose_Ele_Course_Stu?i_id='
																+ j[i][0]
																+ '" type="video/mp4"></video>'
																+ '<div class="card-body"><h4 class="card-title"><a href="#">'
																+ j[i][2]
																+ '</a></h4>'
																+ '<div class="card-detais"><p>'
																+ j[i][1]
																+ '</p><p><a href="#" class="readmore">read more &gt;</a></p><input type="hidden" value="'+j[i][3]+'" name="termhidden'+j[i][0]+'" id="termhidden'+j[i][0]+'"></div>'
// 																+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
// 																+ j[i][2]
// 																+ ')</p></div>'
																+ '<ul class="buttons-group card-btn-footer"><li><button id="btncourseEnroll'+j[i][0]+'" type="button" class="main-btn active-btn-outline btn-hover" >Enroll Now</button></li>'//onclick="course_Enroll(' j[i][0]');"
																/* +'<button type="button" class="custom-btn btn-7 mt-3" onclick="course_Enroll('+j[i][0]+ ');"><span>Enroll Now</span></button>' */
																// 															+ '<a href="Course_Enroll_Url" class="custom-btn btn-7 mt-3"><span>Enroll Now</span></a>'
																+ '</div></div></div>');
																setEnrollevent(j[i][0]);
												/* .append(
														'<div class="col-lg-4 col-md-12 col-12"><div class="card carddiv">'
																+ '<video controls autoplay id="ref_video"  width="100%" height="174"><source id="sourceid" src="videoChoose_Ele_Course_Stu?i_id='
																+ j[i][0]
																+ '" type="video/mp4"></video>'
																+ '<div class="card-body"><a href="#"><h5 class="card-title">'
																+ '</h5></a>'
																+ '<div class="cardcont"><p class="card-text">'
																+ j[i][1]
																+ '</p><p><a href="#" class="readmore">read more &gt;&gt;</a></p></div>'
																+ '<ul class="cardlist"><li class="list-group-item"><i class="fa fa-calendar"></i>(Starts: '
																+ j[i][2]
																+ ')</li>'
																+ '<button type="button" class="custom-btn btn-7 mt-3" onclick="course_Enroll('
																+ j[i][0]
																+ ');"><span>Enroll Now</span></button>'
																// 														+'<a href="Course_Enroll_Url" class="custom-btn btn-7 mt-3"><span>Enroll Now</span></a>'
																+ '</div></div></div>'); */
									}
								}
							} else {
								$("div#Upcoming_Enroll_Open")
										.append(
												'<label for="one" class="">Data Not Available</label>');
							}
						});

		//------------------------------My Courses-------------------------------------------//

		$
				.post(
						"getDescriptionfetchAlreadyAppliedMyCourses?" + key
								+ "=" + value,
						{
							c_id2 : c_id,
							course_duration2 : d,
							course_start_date2 : sdt
						},
						function(j) {
							if (j != "") {
								for (var i = 0; i < j.length; i++) {

									if (i == 0) {
										$("div#Already_Applied")
										.append(
												'<div class="row" id="Already_AppRow"><div class="col-lg-4 col-md-12 col-12"><div class="card custom-card">'
														+ '<div class="cardimg" ><img class="card-img-block" alt="No Image" id="myImg'+j[i][1]+'" src="Image_Fetch_Path_Already_Applied_My_Courses?i_id='
														+ j[i][0]
														+ '"></div>'
														+ '<div class="card-body"><h4 class="card-subtitle">'
														+ j[i][1]
														+ '</h4><h4 class="card-title"><a href="#">'
														+ j[i][2]
														+ '</a></h4>'
														+ '<div class="card-detais"><p>'
														+ j[i][3]
														+ '</p><p><a href="#" class="readmore">read more &gt;</a></p></div>'
														+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
														+ j[i][4]
														+ ')</p></div>'
														+'<ul class="buttons-group card-btn-footer"><li><button id="btnclose'+j[i][6]+'" type="button" class="main-btn dark-btn-outline btn-hover" >Exit Course</button></li></ul>' //onclick="Exit_Enroll('+j[i][6]+ ');"
														+ '</div></div></div></div>');
														setExitevent(j[i][6]);
										
							} else {
								$("div#Already_AppRow")
										.append(
												'<div class="col-lg-4 col-md-12 col-12"><div class="card custom-card">'
														+ '<div class="cardimg" ><img class="card-img-block" alt="No Image" id="myImg'+ j[i][1]+ '" src="Image_Fetch_Path_Already_Applied_My_Courses?i_id='
														+ j[i][0]
														+ '"></div>'
														+ '<div class="card-body"><h4 class="card-subtitle">'
														+ j[i][1]
														+ '</h4><h4 class="card-title"><a href="#">'
														+ j[i][2]
														+ '</a></h4>'
														+ '<div class="card-detais"><p>'
														+ j[i][3]
														+ '</p><p><a href="#" class="readmore">read more &gt;</a></p></div>'
														+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
														+ j[i][4]
														+ ')</p></div>'
														+'<ul class="buttons-group card-btn-footer"><li><button id="btnclose'+j[i][6]+'"  type="button" class="main-btn dark-btn-outline btn-hover" >Exit Course</button></li></ul>' //onclick="Exit_Enroll('+j[i][6]+ ');"
														+ '</div></div></div>');
														setExitevent(j[i][6]);
									}
								}
							} else {
								$("div#Already_Applied")
										.append(
												'<label for="one" class="">Data Not Available</label>');
							}

						});

		//-------------------OnGoing_Enroll_Closed----------
		/* $
				.post(
						"getCourse_Description_fetch_OnGoing_Enroll_Closed?"
								+ key + "=" + value,
						{
							c_id1 : c_id,
							course_duration1 : d,
							course_start_date1 : sdt
						},
						function(j) {
							if (j != "") {
								for (var i = 0; i < j.length; i++) {
									if (i == 0) {

										$("div#OnGoing_Enroll_Closed")
												.append(
														'<div class="row" id="OngoingRow"><div class="col-lg-4 col-md-12 col-12"><div class="card carddiv">'
																+ '<div class="cardimg" onclick="course_Enroll('
																+ j[i][1]
																+ ');"><img class="d-block img5050 imageZomm card-img-top" alt="No Image" id="myImg'
																+ j[i][1]
																+ '" src="OnGoing_Enroll_Closed_Path?i_id='
																+ j[i][1]
																+ '"></div>'
																+ '<div class="card-body"><a href="#"><h5 class="card-title">'
																+ j[i][2]
																+ '</h5></a>'
																+ '<div class="cardcont"><p class="card-text">'
																+ j[i][0]
																+ '</p><p><a href="#" class="readmore">read more &gt;&gt;</a></p></div>'
																+ '<ul class="cardlist"><li class="list-group-item"><i class="fa fa-calendar"></i>(Starts: '
																+ j[i][3]
																+ ')</li>'
																+ '</div></div></div></div>');
									} else {
										$("div#OngoingRow")
												.append(
														'<div class="col-lg-4 col-md-12 col-12"><div class="card carddiv">'
																+ '<div class="cardimg" onclick="course_Enroll('
																+ j[i][1]
																+ ');"><img class="d-block img5050 imageZomm card-img-top" alt="No Image" id="myImg'
																+ j[i][1]
																+ '" src="OnGoing_Enroll_Closed_Path?i_id='
																+ j[i][1]
																+ '"></div>'
																+ '<div class="card-body"><a href="#"><h5 class="card-title">'
																+ j[i][2]
																+ '</h5></a>'
																+ '<div class="cardcont"><p class="card-text">'
																+ j[i][0]
																+ '</p><p><a href="#" class="readmore">read more &gt;&gt;</a></p></div>'
																+ '<ul class="cardlist"><li class="list-group-item"><i class="fa fa-calendar"></i>(Starts: '
																+ j[i][3]
																+ ')</li>'
																+ '</div></div></div></div>');
									}

								}
							} else {
								$("div#OnGoing_Enroll_Closed")
										.append(
												'<label for="one" class="">Data Not Available</label>');
							}

						}); */

		//-------------------Already Applied----------
		/* 		$
		 .post(
		 "getCourse_Description_fetch_Already_Applied?" + key
		 + "=" + value,
		 {
		 c_id2 : c_id,
		 course_duration2 : d,
		 course_start_date2 : sdt
		 },
		 function(j) {
		 if (j != "") {
		 for (var i = 0; i < j.length; i++) {

		 if (i == 0) {
		 $("div#Already_Applied")
		 .append(
		 '<div class="row" id="Already_AppRow"><div class="col-lg-4 col-md-12 col-12"><div class="card carddiv">'
		 + '<div class="cardimg" onclick="course_Enroll('
		 + j[i][1]
		 + ');"><img class="d-block img5050 imageZomm card-img-top" alt="No Image" id="myImg'
		 + j[i][1]
		 + '" src="Image_Fetch_Path_Already_Applied?i_id='
		 + j[i][1]
		 + '"></div>'
		 + '<div class="card-body"><a href="#"><h5 class="card-title">'
		 + j[i][2]
		 + '</h5></a>'
		 + '<div class="cardcont"><p class="card-text">'
		 + j[i][0]
		 + '</p><p><a href="#" class="readmore">read more &gt;&gt;</a></p></div>'
		 + '<ul class="cardlist"><li class="list-group-item"><i class="fa fa-calendar"></i>(Starts: '
		 + j[i][3]
		 + ')</li>'
		 + '</div></div></div></div>');
		 } else {
		 $("div#Already_AppRow")
		 .append(
		 '<div class="col-lg-4 col-md-12 col-12"><div class="card carddiv">'
		 + '<div class="cardimg" onclick="course_Enroll('
		 + j[i][1]
		 + ');"><img class="d-block img5050 imageZomm card-img-top" alt="No Image" id="myImg'
		 + j[i][1]
		 + '" src="Image_Fetch_Path_Already_Applied?i_id='
		 + j[i][1]
		 + '"></div>'
		 + '<div class="card-body"><a href="#"><h5 class="card-title">'
		 + j[i][2]
		 + '</h5></a>'
		 + '<div class="cardcont"><p class="card-text">'
		 + j[i][0]
		 + '</p><p><a href="#" class="readmore">read more &gt;&gt;</a></p></div>'
		 + '<ul class="cardlist"><li class="list-group-item"><i class="fa fa-calendar"></i>(Starts: '
		 + j[i][3]
		 + ')</li>'
		 + '</div></div></div></div>');
		 }
		 }
		 } else {
		 $("div#Already_Applied")
		 .append(
		 '<label for="one" class="">Data Not Available</label>');
		 }

		 }); */
	}

	function Onload_ImageDescription_fetch() {
		$.ajaxSetup({
			async : false
		});

		$('div#Upcoming_Enroll_Open').empty();
		// 		$('div#OnGoing_Enroll_Closed').empty();
		$('div#Already_Applied').empty();

		var c_id = $("select#course_category").val();
		var d = $("select#course_duration").val();
		var sdt = $("select#course_start_date").val();

		//-------------------Upcoming_Enroll_Open----------

		$
				.post(
						"getCourses_Description_fetch_new?" + key + "=" + value,
						{
							c_id : c_id,
							course_duration : d,
							course_start_date : sdt
						},
						function(j) {
							if (j != "") {
							for (var i = 0; i < j.length; i++) {
								if (i == 0) {
									$("div#Upcoming_Enroll_Open")
											.append(
													'<div class="row" id="upcomingRow"><div class="col-xl-4 col-lg-6 col-md-12 col-12"><div class="card custom-card">'
															+ '<video controls autoplay id="ref_video"><source id="sourceid" src="videoChoose_Ele_Course_Stu?i_id='
															+ j[i][0]
															+ '" type="video/mp4"></video>'
															+ '<div class="card-body"><h4 class="card-title"><a href="#">'
															+ j[i][2]
															+ '</a></h4>'
															+ '<div class="card-detais"><p>'
															+ j[i][1]
															+ '</p><p><a href="#" class="readmore">read more &gt;</a></p><input type="hidden" name="termhidden'+j[i][0]+'" value="'+j[i][3]+'" id="termhidden'+j[i][0]+'"></div>'
// 															+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
// 															+ j[i][2]
// 															+ ')</p></div>'
															+ '<ul class="buttons-group card-btn-footer"><li><button id="btncourseEnroll'+j[i][0]+'" type="button" class="main-btn active-btn-outline btn-hover" >Enroll Now</button></li>'//onclick="course_Enroll('j[i][0]');"
															/* +'<button type="button" class="custom-btn btn-7 mt-3" onclick="course_Enroll('+j[i][0]+ ');"><span>Enroll Now</span></button>' */
															// 															+ '<a href="Course_Enroll_Url" class="custom-btn btn-7 mt-3"><span>Enroll Now</span></a>'
															+ '</div></div></div></div>');
															setEnrollevent(j[i][0]);
								} else {
									$("div#upcomingRow")
											.append(
													'<div class="col-xl-4 col-lg-6 col-md-12 col-12"><div class="card custom-card">'
															+ '<video controls autoplay id="ref_video"><source id="sourceid" src="videoChoose_Ele_Course_Stu?i_id='
															+ j[i][0]
															+ '" type="video/mp4"></video>'
															+ '<div class="card-body"><h4 class="card-title"><a href="#">'
															+ j[i][2]
															+ '</a></h4>'
															+ '<div class="card-detais"><p>'
															+ j[i][1]
															+ '</p><p><a href="#" class="readmore">read more &gt;</a></p><input type="hidden" value="'+j[i][3]+'" name="termhidden'+j[i][0]+'" id="termhidden'+j[i][0]+'"></div>'
// 															+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
// 															+ j[i][2]
// 															+ ')</p></div>'
															+ '<ul class="buttons-group card-btn-footer"><li><button id="btncourseEnroll'+j[i][0]+'" type="button" class="main-btn active-btn-outline btn-hover" >Enroll Now</button></li>'//onclick="course_Enroll('j[i][0] ');"
															/* +'<button type="button" class="custom-btn btn-7 mt-3" onclick="course_Enroll('+j[i][0]+ ');"><span>Enroll Now</span></button>' */
															// 															+ '<a href="Course_Enroll_Url" class="custom-btn btn-7 mt-3"><span>Enroll Now</span></a>'
															+ '</div></div></div>');
															setEnrollevent(j[i][0]);
									/* .append(
											'<div class="col-lg-4 col-md-12 col-12"><div class="card carddiv">'
													+'<video controls autoplay id="ref_video"  width="100%" height="174"><source id="sourceid" src="videoChoose_Ele_Course_Stu?i_id='+j[i][0]+'" type="video/mp4"></video>'
													+ '<div class="card-body"><a href="#"><h5 class="card-title">'
													+ '</h5></a>'
													+ '<div class="cardcont"><p class="card-text">'
													+ j[i][1]
													+ '</p><p><a href="#" class="readmore">read more &gt;&gt;</a></p></div>'
													+ '<ul class="cardlist"><li class="list-group-item"><i class="fa fa-calendar"></i>(Starts: '
													+ j[i][2]
													+ ')</li>'
													+'<button type="button" class="custom-btn btn-7 mt-3" onclick="course_Enroll('+j[i][0]+ ');"><span>Enroll Now</span></button>'
									// 															+ '<a href="Course_Enroll_Url" class="custom-btn btn-7 mt-3"><span>Enroll Now</span></a>'
													+ '</div></div></div>'); */
								}
								
							}
							} else {
								$("div#Upcoming_Enroll_Open")
										.append(
												'<label for="one" class="">Data Not Available</label>');
							}
						});

		//------------------------------myCourses start here-------------------------------------------------///

		$
				.post(
						"getDescriptionfetchAlreadyAppliedMyCourses?" + key
								+ "=" + value,
						{
							c_id2 : c_id,
							course_duration2 : d,
							course_start_date2 : sdt
						},
						function(j) {
// 							debugger;
							if (j != "") {							
							for (var i = 0; i <= j.length-1; i++) {
// 								alert(j[0]);
								if (i == 0) {
									$("div#Already_Applied")
											.append(
													'<div class="row" id="Already_AppRow"><div class="col-lg-4 col-md-12 col-12"><div class="card custom-card">'
															+ '<div class="cardimg" ><img class="card-img-block" alt="No Image" id="myImg'
															+ j[i][1]
															+ '" src="Image_Fetch_Path_Already_Applied_My_Courses?i_id='
															+ j[i][0]
															+ '"></div>'
															+ '<div class="card-body"><h4 class="card-subtitle">'
															+ j[i][1]
															+ '</h4><h4 class="card-title"><a href="#">'
															+ j[i][2]
															+ '</a></h4>'
															+ '<div class="card-detais"><p>'
															+ j[i][3]
															+ '</p><p><a href="#" class="readmore">read more &gt;</a></p></div>'
															+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
															+ j[i][4]
															+ ')</p></div>'
															+'<ul class="buttons-group card-btn-footer"><li><button id="btnclose'+j[i][6]+'"  type="button" class="main-btn dark-btn-outline btn-hover" >Exit Course</button></li></ul>' //onclick="Exit_Enroll('+j[i][6]+ ');"
															+ '</div></div></div></div>');
															setExitevent(j[i][6]);
								} else {
									$("div#Already_AppRow")
											.append(
													'<div class="col-lg-4 col-md-12 col-12"><div class="card custom-card">'
															+ '<div class="cardimg" ><img class="card-img-block" alt="No Image" id="myImg'+ j[i][1]+'" src="Image_Fetch_Path_Already_Applied_My_Courses?i_id='+ j[i][0]+ '"></div>'
															+ '<div class="card-body"><h4 class="card-subtitle">'
															+ j[i][1]
															+ '</h4><h4 class="card-title"><a href="#">'
															+ j[i][2]
															+ '</a></h4>'
															+ '<div class="card-detais"><p>'
															+ j[i][3]
															+ '</p><p><a href="#" class="readmore">read more &gt;</a></p></div>'
															+ '<div class="custom-calendar"><p><i class="lni lni-calendar"></i>(Starts: '
															+ j[i][4]
															+ ')</p></div>'
															+'<ul class="buttons-group card-btn-footer"><li><button id="btnclose'+j[i][6]+'"  type="button" class="main-btn dark-btn-outline btn-hover" >Exit Course</button></li></ul>' //onclick="Exit_Enroll('+j[i][6]+ ');"
															+ '</div></div></div>');
															setExitevent(j[i][6]);
								}

							}
						} else {
								$("div#Already_Applied")
										.append(
												'<label for="one" class="">Data Not Available</label>');
							}

						});
	}
	

	function Exit_Enroll(id) {
    alert("Are You Sure Want to Exit This Course ?");
   $.post("getExitCourse?" + key + "=" + value, {
	  course_category : id
			}, function(data) {
			}).done(function(data) {
				var created_date = new Date(data[0][1]);
				var current_date = new Date();
				var course_switch_duration = (data[0][0]);
				var set_id = (data[0][3]);
				
				// To calculate the time difference of two dates
				var Difference_In_Time = current_date.getTime() - created_date.getTime();
				
				// To calculate the no. of days between two dates
				var Difference_In_Days = (Difference_In_Time / (1000 * 3600 * 24)) + 1;
				
			    if(Difference_In_Days <= course_switch_duration){
			    	 alert(" if you have under switch duration and rejoin another course do not have any extra fees.");
			    	 $("#exit_id_Payment1").val("1");
			    }else if(Difference_In_Days > course_switch_duration)
			    	{
			    	 alert("if you exit the course do not have return fees.");
			    	}
			    $("#setid2").val(data[0][3]);
			}).fail(function(xhr, textStatus, errorThrown) {
			});
   
   var set_id = $("#setid2").val();
//    alert("-------"+set_id)
      $.post("getExitCourse_count?" + key + "=" + value, {
    	  set_id : set_id
				}, function(data) {
				}).done(function(data) {
	 				var count_course_id = (data[0][0]);
	 				var set_name = (data[0][1]);
	 				var set_id =(data[0][2]);
	 				
	 			    if(count_course_id > 1){
	 			    	 $("#id2").val(id);
	 					 document.getElementById('exitForm').submit();
	 			    }else{
	 			    	alert("Please Choose Another Course From "+data[0][1]+" Then You Will Exit From This Course Other wise Your Credit Point will be 0 ");
// 	 			    	  window.location.href="Course_Enroll_Url";
				        $("#exit_id1").val();
				        $("#exit_course_id").val(id);
						document.getElementById('if_exitForm').submit();
	 			    }
				}).fail(function(xhr, textStatus, errorThrown) {
				});
// 	        $("#id2").val(id);
// 			document.getElementById('exitForm').submit();
	}

	function setExitevent(val){		
		document.getElementById('btnclose'+val).onclick = function () {
			Exit_Enroll(val);
		};
	}
	
	function setEnrollevent(val){
		document.getElementById('btncourseEnroll'+val).onclick = function () {
			course_Enroll(val);
		};	
	}
	
	
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('course_duration').onchange = function() {
			 Course_wise_images_fetch();
		};
		document.getElementById('course_start_date').onchange = function() {
			 Course_wise_images_fetch();
		};
		document.getElementById('course_category').onchange = function() {
			 Course_wise_images_fetch();
		};
		document.getElementById('pills-course-tab').onclick = function() {
			openCity(event, 'Upcoming_Enroll_Open');
		};
	});

	


		//-------------------OnGoing_Enroll_Closed----------
		/* $
				.post(
						"getCourse_Description_fetch_OnGoing_Enroll_Closed?"
								+ key + "=" + value,
						{
							c_id1 : c_id,
							course_duration1 : d,
							course_start_date1 : sdt
						},
						function(j) {
							for (var i = 0; i <= j.length; i++) {

								if (i == 0) {
									$("div#OnGoing_Enroll_Closed")
											.append(
													'<div class="row" id="OngoingRow"><div class="col-lg-4 col-md-12 col-12"><div class="card carddiv">'
															+ '<div class="cardimg" onclick="course_Enroll('
															+ j[i][1]
															+ ');"><img class="d-block img5050 imageZomm card-img-top" alt="No Image" id="myImg'
															+ j[i][1]
															+ '" src="OnGoing_Enroll_Closed_Path?i_id='
															+ j[i][1]
															+ '"></div>'
															+ '<div class="card-body"><a href="#"><h5 class="card-title">'
															+ j[i][2]
															+ '</h5></a>'
															+ '<div class="cardcont"><p class="card-text">'
															+ j[i][0]
															+ '</p><p><a href="#" class="readmore">read more &gt;&gt;</a></p></div>'
															+ '<ul class="cardlist"><li class="list-group-item"><i class="fa fa-calendar"></i>(Starts: '
															+ j[i][3]
															+ ')</li>'
															+ '</div></div></div></div>');
								} else {
									$("div#OngoingRow")
											.append(
													'<div class="col-lg-4 col-md-12 col-12"><div class="card carddiv">'
															+ '<div class="cardimg" onclick="course_Enroll('
															+ j[i][1]
															+ ');"><img class="d-block img5050 imageZomm card-img-top" alt="No Image" id="myImg'
															+ j[i][1]
															+ '" src="OnGoing_Enroll_Closed_Path?i_id='
															+ j[i][1]
															+ '"></div>'
															+ '<div class="card-body"><a href="#"><h5 class="card-title">'
															+ j[i][2]
															+ '</h5></a>'
															+ '<div class="cardcont"><p class="card-text">'
															+ j[i][0]
															+ '</p><p><a href="#" class="readmore">read more &gt;&gt;</a></p></div>'
															+ '<ul class="cardlist"><li class="list-group-item"><i class="fa fa-calendar"></i>(Starts: '
															+ j[i][3]
															+ ')</li>'
															+ '</div></div></div></div>');
								}

							}

						}); */

		//-------------------Already Applied----------
		// 		$
		// 				.post(
		// 						"getCourse_Description_fetch_Already_Applied?" + key
		// 								+ "=" + value,
		// 						{
		// 							c_id2 : c_id,
		// 							course_duration2 : d,
		// 							course_start_date2 : sdt
		// 						},
		// 						function(j) {
		// 							for (var i = 0; i <= j.length; i++) {
		// 								if (i == 0) {
		// 									$("div#Already_Applied")
		// 											.append(
		// 													'<div class="row" id="Already_AppRow"><div class="col-lg-4 col-md-12 col-12"><div class="card carddiv">'
		// 															+ '<div class="cardimg" onclick="course_Enroll('
		// 															+ j[i][1]
		// 															+ ');"><img class="d-block img5050 imageZomm card-img-top" alt="No Image" id="myImg'
		// 															+ j[i][1]
		// 															+ '" src="Image_Fetch_Path_Already_Applied?i_id='
		// 															+ j[i][1]
		// 															+ '"></div>'
		// 															+ '<div class="card-body"><a href="#"><h5 class="card-title">'
		// 															+ j[i][2]
		// 															+ '</h5></a>'
		// 															+ '<div class="cardcont"><p class="card-text">'
		// 															+ j[i][0]
		// 															+ '</p><p><a href="#" class="readmore">read more &gt;&gt;</a></p></div>'
		// 															+ '<ul class="cardlist"><li class="list-group-item"><i class="fa fa-calendar"></i>(Starts: '
		// 															+ j[i][3]
		// 															+ ')</li>'
		// 															+ '</div></div></div></div>');
		// 								} else {
		// 									$("div#Already_AppRow")
		// 											.append(
		// 													'<div class="col-lg-4 col-md-12 col-12"><div class="card carddiv">'
		// 															+ '<div class="cardimg" onclick="course_Enroll('
		// 															+ j[i][1]
		// 															+ ');"><img class="d-block img5050 imageZomm card-img-top" alt="No Image" id="myImg'
		// 															+ j[i][1]
		// 															+ '" src="Image_Fetch_Path_Already_Applied?i_id='
		// 															+ j[i][1]
		// 															+ '"></div>'
		// 															+ '<div class="card-body"><a href="#"><h5 class="card-title">'
		// 															+ j[i][2]
		// 															+ '</h5></a>'
		// 															+ '<div class="cardcont"><p class="card-text">'
		// 															+ j[i][0]
		// 															+ '</p><p><a href="#" class="readmore">read more &gt;&gt;</a></p></div>'
		// 															+ '<ul class="cardlist"><li class="list-group-item"><i class="fa fa-calendar"></i>(Starts: '
		// 															+ j[i][3]
		// 															+ ')</li>'
		// 															+ '</div></div></div></div>');
		// 								}
		// 							}
		// 						});

	

	// 	function videotopicCall() {

	// 		var id = ${cid};

	// 				$('div#Upcoming_Enroll_Open').empty();

	// 					$("div#Upcoming_Enroll_Open").append('<video onended="video_ended()" id="ref_video" controls width="100%" height="250"><source id="sourceid" src="videoChoose_Ele_Course_Stu?i_id='+id+'&fildname=ref_video" type="video/mp4"></video>'
	// 						    +'</tr>');
	// 				var modal = document.getElementById("videoModal");
	// 				var span = document.getElementsByClassName("Vclose")[0];

	// 		        document.getElementById('videoModal').style.display = 'block';

	// 		  span.onclick = function() {
	// 		  modal.style.display = "none";
	// 		}
	// 	}
</script>
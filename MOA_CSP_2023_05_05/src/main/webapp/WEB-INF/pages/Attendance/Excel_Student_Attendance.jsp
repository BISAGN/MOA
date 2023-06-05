<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- <script type="text/javascript" src="js/watermark/common.js"></script> -->

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script src="js/Calender/jquery-ui.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>


<section class="dashboard-page regulation_report">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Upload Student Attendance</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#0">Dashboard</a></li>
								<!--  <li class="breadcrumb-item"><a href="#0">Regulation Forms</a></li> -->
								<li class="breadcrumb-item active" aria-current="page">
									Upload Student Attendance</li>
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
					<form:form
						action="Student_Attendance_action?${_csrf.parameterName}=${_csrf.token}"
						method="POST" modelAttribute="Student_Attendance_cmd"
						enctype="multipart/form-data">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Upload Attendance Data</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Professional<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="professional_id" id="professional_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getprofessionalList}"
														varStatus="num">
														<option value="${item.id}" name="${item.professional}">${item.professional}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-2">
											<label>Date<span class="mandatory">*</span></label> <input
												type="text" name="attendance_date" id="attendance_date"
												maxlength="10"
												class="form-control-sm form-control custom_excl_stu_attend_date"
												aria-required="true" autocomplete="off" value="DD/MM/YYYY"
												placeholder="Select Attendance Date">
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Course<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="course_id"
													class="singleselect form-control form-control-lg"
													id="course_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
<!-- 									<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
<!-- 										<div class="select-style-1"> -->
<!-- 											<label for="text-input"></br></label> <i id="btnexcel" -->
<!-- 												class="fa fa-file-excel-o"></i><input type="button" -->
<!-- 												id="btnExport" class="main-btn success-btn  btn-hover" -->
<!-- 												value="Export Sample Template Format"> -->

<!-- 										</div> -->
<!-- 									</div> -->
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>Upload Student Attendance<strong
												class="mandatory">*</strong></label> <input type="file" id=u_file1
												name="u_file1" class="form-control" autocomplete="off"
												maxlength="25" placeholder="Enter Staff Name">
										</div>
									</div>
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											
											<li><input type="submit" id="btn-save"
												class="main-btn info-btn btn-hover btnsave" value="Save">
											</li>
											
											<li><a href="Excel_student_attend_url"
												class="main-btn dark-btn btn-hover btnreset" value="Reset">Reset</a>
											</li>
											
											<li id="btnExport"><a href="#0"
												class="main-btn success-btn btn-hover btn-iconic-icon btnexcel"
												value="Export Sample Template Format"><i
													class="bi bi-file-earmark-excel"></i>Export Sample Template
													Format</a></li>
											<!-- <li>
										<i id="btnexcel" class="fa fa-file-excel-o"></i><input
										type="button" id="btnExport"
										class="main-btn success-btn  btn-hover" value="EXPORT EXCEL">
									</li> -->
										</ul>
									</div>
								</div>
							</div>



						</div>
					</form:form>
					<!-- end card -->
				</div>
			</div>
			<!-- end row -->
		</div>
	</div>
</section>



<c:url value="Student_Atten_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="typeReportdate" id="typeReportdate"
		value="0" />
	<input type="hidden" name="typeReportcourse" id="typeReportcourse"
		value="0" />
	<input type="hidden" name="professional_hid" id="professional_hid"
		value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		$.ajaxSetup({
			async : false
		});
		datepicketDate('attendance_date');

	});

	function getStudent_Atten_Excel() {
		$("#typeReportdate").val($("#attendance_date").val());
		$("#typeReportcourse").val($("#course_id").val());
		$("#professional_hid").val($("#professional_id").val());
		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('search2').submit();
	}

	function getStudent_Atten_Excel1() {
		var attendance_date = $("#attendance_date").val();
		$("#typeReportdate").val(attendance_date);
		var a = $("#typeReportdate").val();
		document.getElementById('typeReportdate').value = a;

	}

	function getprofessional() {
		var professional_id = $("#professional_id").val();
		$("#professional_hid").val(professional_id);
		var pro = $("#professional_hid").val();
		document.getElementById('professional_hid').value = pro;

	}

	function getcourselistbydate() {
		var attendance_date = $("#attendance_date").val();
		$
				.post('getCourseListbyDate?' + key + "=" + value, {
					attendance_date : attendance_date
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_id").html(options);
						});
	}
	function getCourseName() {
		var course = $("#course_id").val();
		$("#typeReportcourse").val(course);
		var a = $("#typeReportcourse").val();
		document.getElementsByName('typeReportcourse').value = a;
	}
	function Validation() {
		if ($("#u_file1").val().trim() == "") {
			alert("Please Upload Attendance");
			$("input#u_file1").focus();
			return false;
		}
		return true;
	}
	function Validation1() {

		if ($("#professional_id").val() == "0") {
			alert("Please Select Professional");
			$("select#professional_id").focus();
			return false;
		}
		var attendance_date = $("input#attendance_date").val();
		if (attendance_date == "DD/MM/YYYY" || attendance_date == ""
				|| attendance_date == null) {
			alert("Please Select Date")
			$("input#attendance_date").focus();
			return false;
		}
		if ($("#course_id").val() == "0") {
			alert("Please Select Course");
			$("select#course_id").focus();
			return false;
		}
		return true;
	}
	////////CSP start================================================	
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btn-save').onclick = function() {
			return Validation();
			getStudent_Atten_Excel();
			getCourseName();
		};
		document.getElementById('btn-save').onchange = function() {
			getStudent_Atten_Excel1();
		};
		document.getElementById('attendance_date').onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('attendance_date').onfocus = function() {
			this.style.color = '#000000';
		};
		document.getElementById('attendance_date').onblur = function() {
			clickrecall(this, 'DD/MM/YYYY');
		};
		document.getElementById('attendance_date').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('attendance_date').onchange = function() {
			onchangeCount(this.value);
			clickrecall(this, 'DD/MM/YYYY');
		};
		document.getElementById('attendance_date').onchange = function() {
			getcourselistbydate();
			getStudent_Atten_Excel1();
		};
		document.getElementById('course_id').onchange = function() {
			getCourseName();
		};
		document.getElementById('btnExport').onclick = function() {
			if (Validation1()) {
				getStudent_Atten_Excel();
				getCourseName();
			}
		};
		document.getElementById('btnExport').onchange = function() {
			getStudent_Atten_Excel1();
		};
		document.getElementById('professional_id').onclick = function() {
			// 			getcourselistby_professional();
			getprofessional();
		};
	});

	// 	function getcourselistby_professional() {
	// 		var degree_id = $("#degree_id").val();
	// 		var professional_id = $("#professional_id").val();

	// 		$.post('getCourseList?' + key + "=" + value,{  
	// 			degree_id : degree_id,
	// 			professional_id : professional_id
	// 			}).done(function(j) {
	// 							var options = '<option value="' + "0" + '">'
	// 									+ "--Select--" + '</option>';
	// 							for (var i = 0; i < j.length; i++) {
	// 								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
	// 										+ j[i][1] + '</option>';
	// 							}
	// 							$("select#course_id").html(options);
	// 		});
	// 	}
</script>
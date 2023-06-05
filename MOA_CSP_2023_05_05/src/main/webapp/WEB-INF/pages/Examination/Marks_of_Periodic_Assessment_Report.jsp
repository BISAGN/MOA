<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>
<script src="js/common/multicheck.js" type="text/javascript"></script>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- <script type="text/javascript" src="js/watermark/common.js"></script> -->
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span> Marks Of Periodic Assessment Report
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Marks
									Of Periodic Assessment Report</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="add_marks_of_pa" id="Add_Marks_of_PAAction"
						action="Marks_of_Periodic_Assessment_Report_Action" method='POST'
						commandName="Marks_of_Periodic_Assessment_Report_CMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Add Marks of PA</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Degree</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="degree_id" id="degree_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Professional</label>
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
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Subject</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="course_id" id="course_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Term</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="term_id" id="term_id">
													<option value="0">--Select--</option>
<%-- 													<c:forEach var="item" items="${geti3_termList}" --%>
<%-- 														varStatus="num"> --%>
<%-- 														<option value="${item.id}" name="${item.term}">${item.term}</option> --%>
<%-- 													</c:forEach> --%>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Exam Serial</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="exam_serial" id="exam_serial">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getExam_SerialList}"
														varStatus="num">
														<option value="${item.id}" name="${item.exam_serial}">${item.exam_serial}</option>
													</c:forEach>

												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Exam Type</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="exam_type_id" id="exam_type_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getExam_Type}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4"
										id="inst_drp_div">
										<div class="select-style-1">
											<label for="text-input">Institute</label>
											<div class="select-position">
												<select name="institute_id" id="institute_id"
													class="singleselect form-control form-control-lg">
													<option value="0" name="select">--Select--</option>
													<c:forEach var="item"
														items="${getinstitute_listbyuniversity}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input"> Year</label>
											<div class="input-style-2">
												<input type="month" id="mon_year" name="mon_year"
													class="form-control-sm form-control effect-9 hasDatepicker">
											</div>
										</div>
									</div>
									<!-- 								<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
									<!-- 									<div class="input-with-selection"> -->
									<!-- 										<div class="input-style-2 mb-0"> -->
									<!-- 											<label id="topic_name"> Course<span class="mandatory">*</span></label>  -->
									<!-- 												<input type="text" -->
									<!-- 												id="search_data"  -->
									<!-- 												autocomplete="off" placeholder="Search Course"> -->
									<!-- 										</div> -->
									<!-- 										<div class="col-two" id="checkboxes" class="chklist"></div> -->
									<!-- 									</div> -->
									<!-- 									<input type="hidden" id="no_of_student" name="no_of_student" class="form-control autocomplete" value="0"> -->
									<!-- 									<input type="hidden" id="no_of_course" name="no_of_course" class="form-control autocomplete" value="0"> -->
									<!-- 									<input type="hidden" name="course_check_list"id="course_check_list" /> -->
									<!-- 									<input type="hidden" id="student_check_list" name="student_check_list" /> -->
									<!-- 								</div> -->
									<input type="hidden" name="old_course_topic"
										id="old_course_topic" /> <input type="hidden"
										name="new_course_topic" id="new_course_topic" /> <input
										type="hidden" name="add_course_topic" id="add_course_topic" />
									<input type="hidden" name="remove_course_topic"
										id="remove_course_topic" />
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a href="Marks_of_Periodic_Assessment_Report"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<!-- 								<li><input value="Save" id="btn-save" -->
											<!-- 									class="main-btn info-btn btn-hover" type="submit" /></li> -->
										</ul>
									</div>
								</div>
							</div>
						</div>

						<section class="single-detail-block">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="card-style mb-30">
										<div class="table-wrapper table-responsive custom-datatable-p">
											<table class="table" id="search_marks_Report">
												<thead>
													<tr>
														<th><h6>Sr No.</h6></th>
														<th><h6>Degree</h6></th>
														<th><h6>Term</h6></th>
														<th><h6>Exam serial</h6></th>
														<th><h6>Exam type</h6></th>
														<c:if
															test="${role == 'University_NCISM' || role == 'University_NCH' }">
															<th><h6>Institute</h6></th>
														</c:if>
														<th><h6>Year</h6></th>
														<th><h6>Subject</h6></th>
														<th><h6>Marks</h6></th>
														<th><h6>Student name</h6></th>
													</tr>
												</thead>
												<tbody class="custom-datatablepra">
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</section>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		var role = '${role}';
		if (role.toLowerCase().includes('institute')) {
			$("#inst_drp_div").hide();
			$("#inst").hide();
		}

		mockjax1('search_marks_Report');
		table = dataTable('search_marks_Report');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		if (window.location.href.includes("msg")) {
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}
		getDegreeFromInstitute();
	});

	function getDegreeFromInstitute() {
		$
				.post(
						"getDegreeFromInstituteExam?" + key + "=" + value,
						{},
						function(j) {

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_id").html(options);
						});
	}
	function getcourselistby_professional() {
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();

		$
				.post('getCourseList_for_Curri?' + key + "=" + value, {
					degree_id : degree_id,
					professional_id : professional_id
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
	function getTerm_listByProf() {
// 		var system = $("#system_id").val();
		var degree = $("#degree_id").val();
		var professional = $("#professional_id").val();

		$
				.post('getTerm_listByProf_for_Curri?' + key + "=" + value, {
// 					system : system,
					degree : degree,
					professional : professional
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#term_id").html(options);
						});
	}
	function data(search_marks_Report) {
		jsondata = [];
		var table = $('#' + search_marks_Report).DataTable();
		var info = table.page.info();
		//		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		//var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();
		var term_id = $("#term_id").val();
		var exam_serial = $("#exam_serial").val();
		var exam_type_id = $("#exam_type_id").val();
		var mon_year = $("#mon_year").val();
		var institute_id = $("#institute_id").val();
		var course_id = $("#course_id").val();

		var role = '${role}';

		$.post("getFilter_Marks_perodic_report_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			degree_id : degree_id,
			professional_id : professional_id,
			term_id : term_id,
			exam_serial : exam_serial,
			exam_type_id : exam_type_id,
			mon_year : mon_year,
			institute_id : institute_id,
			course_id : course_id

		}, function(j) {
			for (var i = 0; i < j.length; i++) {

				if (role.toLowerCase().includes('institute')) {
					jsondata.push([ j[i].ser, j[i].degree_name, j[i].term,
							j[i].exam_serial, j[i].exam_type, j[i].mon_year,
							j[i].course_name, j[i].marks, j[i].name ]);
				} else {
					jsondata.push([ j[i].ser, j[i].degree_name, j[i].term,
							j[i].exam_serial, j[i].exam_type,
							j[i].institute_name, j[i].mon_year,
							j[i].course_name, j[i].marks, j[i].name ]);
				}

			}
		});

		$.post("getTotalEdu_Marks_perodic_report_dataCount?" + key + "="
				+ value, {
			degree_id : degree_id,
			professional_id : professional_id,
			term_id : term_id,
			exam_serial : exam_serial,
			exam_type_id : exam_type_id,
			mon_year : mon_year,
			institute_id : institute_id,
			course_id : course_id
		}, function(j) {
			FilteredRecords = j;
		});
		// 	setTimeout(setTimeLoadForTable, 1000);
	}
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('professional_id').onchange = function() {
			getcourselistby_professional();
			getTerm_listByProf();
		};
	});

	function Validation() {

		if ($("#degree_id").val().trim() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		if ($("#professional_id").val().trim() == "0") {
			alert("Please Select Professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#term_id").val().trim() == "0") {
			alert("Please Select Term.");
			$("select#term_id").focus();
			return false;
		}
		if ($("#exam_serial").val().trim() == "0") {
			alert("Please Select Exam Serial.");
			$("select#exam_serial").focus();
			return false;
		}
		if ($("#exam_type_id").val().trim() == "0") {
			alert("Please Select Exam Type.");
			$("select#exam_type_id").focus();
			return false;
		}
		if ($("#mon_year").val().trim() == "") {
			alert("Please Enter Month Year.");
			$("input#mon_year").focus();
			return false;
		}
		if ($("#new_course_topic").val().trim() == "") {
			alert("Please Select Subject.");
			$("select#new_course_topic").focus();
			return false;
		}

		// 	if ($("#attempt_id").val().trim() == "ATTEMPT") {
		// 			if ($("#marks").val().trim() == "") {
		// 				alert("Please Enter Marks.");
		// 				$("select#marks").focus();
		// 				return false;
		// 			}	
		// 	  }	
		return true;
	}
</script>

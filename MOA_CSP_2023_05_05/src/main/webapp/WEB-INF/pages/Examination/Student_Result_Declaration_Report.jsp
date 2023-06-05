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
							<span id="lbladd1"></span>Student Result Declaration Report
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Student
									Result Declaration Report</li>
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
							<h6 class="mb-25">Student Result Declaration Report</h6>
							<div class="row">
								<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
								<!-- 								<div class="select-style-1"> -->
								<!-- 									<label for="text-input">System<span class="mandatory">*</span></label> -->
								<!-- 									<div class="select-position"> -->
								<!-- 									<select class="singleselect form-control form-control-lg" name="system_id" id="system_id"> -->
								<!-- 									  <option value="0">--Select--</option> -->
								<%-- 										<c:forEach var="item" items="${getSystemList}" varStatus="num"> --%>
								<%-- 													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option> --%>
								<%-- 										</c:forEach> --%>
								<!-- 								   </select> -->
								<!-- 							      </div> -->
								<!-- 								</div>					 -->
								<!-- 							</div> -->
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
												<c:forEach var="item" items="${getTerm}" varStatus="num">
													<option value="${item.term}" name="${item.term}">${item.term}</option>
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
											<select class="singleselect form-control form-control-lg"
												name="institute_id" id="institute_id"
												class="select2 narrow wrap form-control form-control-lg form-control-a disablecopypaste">
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
									<div class="input-style-2">
										<label>Date of Exam</label> <input type="text"
											name="date_of_exam" id="date_of_exam" maxlength="10"
											class="form-control-sm form-control effect-9 "
											aria-required="true" autocomplete="off" value="DD/MM/YYYY"
											placeholder="DD/MM/YYYY">
										<!-- 																onclick="clickclear(this, 'DD/MM/YYYY')" -->
										<!-- 																onfocus="this.style.color='#000000'" -->
										<!-- 																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" -->
										<!-- 																onkeyup="clickclear(this, 'DD/MM/YYYY')" -->
										<!-- 																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" -->
									</div>


									<!-- 									<div class="select-style-1"> -->
									<!-- 										<label for="text-input"> Year<span -->
									<!-- 											class="mandatory">*</span></label> -->
									<!-- 										<div class="input-style-2"> -->
									<!-- 											<input type="month" id="mon_year" -->
									<!-- 												name="mon_year" -->
									<!-- 												class="form-control-sm form-control effect-9 hasDatepicker" > -->
									<!-- 										</div> -->
									<!-- 									</div> -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">Result Status</label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="result_status" id="result_status">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getstudentresultstatuslist}"
													varStatus="num">
													<option value="${item.id}" name="${item.result_status}">${item.result_status}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<!-- 								<li><a class="main-btn info-btn btn-hover" id="btn-reload" -->
											<!-- 									value="Search"><i class="lni lni-search-alt"></i>Search</a></li> -->

											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>

											<li><a
												class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon btnpdf"
												id="pdfex"><i class="bi bi-file-pdf" id="printId"
													value="PDF" title="Export to PDF"></i> PDF </a></li>

											<li><a
												class="main-btn success-btn btn-hover btn-iconic-icon btnexcel"
												id="btnExport"><i class="bi bi-file-earmark-excel" value="PDF"
													title="Export to PDF"></i> Excel </a></li>
											<li><a href="Student_Result_Declaration_Report"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
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
													<th><h6>Professional</h6></th>
													<!-- 									<th id="th_inst_name" class="d-none"><h6>Institute</h6></th> -->
													<c:if
														test="${role == 'University_NCISM' || role == 'University_NCH' }">
														<th><h6>Institute</h6></th>
													</c:if>
													<th><h6>Ayush ID</h6></th>
													<th><h6>Student name</h6></th>
													<th><h6>Date of exam</h6></th>
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
<c:url value="getStudent_Result_Report_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="degree_id1" id="degree_id1" value="0" />
	<input type="hidden" name="professional_id1" id="professional_id1"
		value="0" />
	<input type="hidden" name="date_of_exam1" id="date_of_exam1" value="0" />
	<input type="hidden" name="institute_id1" id="institute_id1" value="0" />
	<input type="hidden" name="result_status1" id="result_status1"
		value="0" />
</form:form>
<c:url value="Student_Result_Report_PDF" var="searchUrl1" />
<form:form action="${searchUrl1}" method="post" id="search3"
	name="search3" modelAttribute="comd1">
	<input type="hidden" name="degree_id2" id="degree_id2" value="0" />
	<input type="hidden" name="professional_id2" id="professional_id2"
		value="0" />
	<input type="hidden" name="date_of_exam2" id="date_of_exam2" value="0" />
	<input type="hidden" name="institute_id2" id="institute_id2" value="0" />
	<input type="hidden" name="result_status2" id="result_status2"
		value="0" />
</form:form>

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
		datepicketDate('date_of_exam');

		getDegreeLFromInstituteExam();

	});

	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btnExport').onclick = function() {
			getStudent_Result_Report_Excel();
		};
		document.getElementById('pdfex').onclick = function() {
			Student_Result_Report_PDF();
		};
		// 		document.getElementById('system_id').onchange = function() {
		// 			getdegreelistbysystem();
		// 		};
	});
	function getDegreeLFromInstituteExam() {
		$
				.post(
						"getDegreeLFromInstituteExam?" + key + "=" + value,
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
	// function getdegreelistbysystem() {
	// 		var system_name = $("#system_id").val();
	// 		$
	// 				.post('getDegreeListbysystem1?' + key + "=" + value, {
	// 					system_name : system_name
	// 				})
	// 				.done(
	// 						function(j) {
	// 							var options = '<option value="' + "0" + '">'
	// 									+ "--Select--" + '</option>';
	// 							for (var i = 0; i < j.length; i++) {
	// 								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
	// 										+ j[i][1] + '</option>';
	// 							}
	// 							$("select#degree_id").html(options);
	// 						});
	// 	}
	function data(search_marks_Report) {
		jsondata = [];
		var table = $('#' + search_marks_Report).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();
		var date_of_exam = $("#date_of_exam").val();
		var institute_id = $("#institute_id").val();
		var result_status = $("#result_status").val();

		$.post("getFilter_Student_result_report?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			degree_id : degree_id,
			professional_id : professional_id,
			institute_id : institute_id,
			date_of_exam : date_of_exam,
			result_status : result_status

		}, function(j) {
			for (var i = 0; i < j.length; i++) {

				if (role.toLowerCase().includes('institute')) {
					jsondata.push([ j[i].ser, j[i].degree_name, j[i].term,
							j[i].ayush_id, j[i].name, j[i].date_of_exam ]);
				} else {
					$("#th_inst_name").show();
					jsondata.push([ j[i].ser, j[i].degree_name, j[i].term,
							j[i].institute_name, j[i].ayush_id, j[i].name,
							j[i].date_of_exam ]);
				}

			}
		});

		$.post("getTotal_Student_result_report_dataCount?" + key + "=" + value,
				{
					degree_id : degree_id,
					professional_id : professional_id,
					institute_id : institute_id,
					date_of_exam : date_of_exam,
					result_status : result_status

				}, function(j) {
					FilteredRecords = j;
				});

	}

	function getStudent_Result_Report_Excel() {
		// 		alert(1);
		$("#degree_id1").val($("#degree_id").val());
		$("#professional_id1").val($("#professional_id").val());
		$("#date_of_exam1").val($("#date_of_exam").val());
		$("#institute_id1").val($("#institute_id").val());
		$("#result_status1").val($("#result_status").val());

		document.getElementById('search2').submit();

	}
	function Student_Result_Report_PDF() {
		// 		alert("HIIIIIIIIII");
		$("#degree_id2").val($("#degree_id").val());
		$("#professional_id2").val($("#professional_id").val());
		$("#date_of_exam2").val($("#date_of_exam").val());
		$("#institute_id2").val($("#institute_id").val());
		$("#result_status2").val($("#result_status").val());

		document.getElementById('search3').submit();

	}
</script>

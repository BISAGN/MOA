<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>
<script src="js/common/multicheck.js" type="text/javascript"></script>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
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
							<span id="lbladd1"></span>Summarized Report of Examination
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Summarized Report of Examination</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="report" id="reportAction" action="report_Action"
						method='POST' commandName="report_CMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Summarized Report Of Examination</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4"
									id="state_drp_div">
									<div class="select-style-1">
										<label for="text-input">State</label>
										<div class="select-position">
											<select name="state_id" id="state_id">
												<option value="0">---Select---</option>
												<c:forEach var="item" items="${MedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4" id="uni_drp_div">
									<div class="select-style-1">
										<label for="text-input">University</label>
										<div class="select-position">
											<select name="university_id" id="university_id">
												<option value="0">---Select---</option>
												<%-- 										<c:forEach var="item" items="${getuniversity_listbyState}" varStatus="num"> --%>
												<%-- 											<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
												<%-- 										</c:forEach> --%>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">Institute</label>
										<div class="select-position">
											<select name="institute_id" id="institute_id"
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
									<div class="select-style-1">
										<label for="text-input">Degree</label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="degree_id" id="degree_id">
												<option value="0" name="select">--Select--</option>
												<%-- 												<c:forEach var="item" --%>
												<%-- 													items="${getDegreeList}" varStatus="num"> --%>
												<%-- 													<option value="${item.id}" name="${item.degree_name}">${item.degree_name}</option> --%>
												<%-- 												</c:forEach> --%>
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
							</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											
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
													title="Export to PDF"></i> EXCEL </a></li>
											<li><a href="Report_URL"
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
										<table class="table" id="search_Report">
											<thead>
												<tr>
													<th><h6>Sr No.</h6></th>
													<c:if test="${role == 'NCISM' || role == 'NCH' }">
														<th><h6>State</h6></th>
														<th><h6>University</h6></th>
													</c:if>
													<th><h6>Institute</h6></th>
													<th><h6>Degree</h6></th>
													<!-- 									<th><h6>Professional</h6></th> -->
													<th><h6>Promote</h6></th>
													<th><h6>Detain</h6></th>
													<th><h6>Supplementary</h6></th>
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

<c:url value="get_Student_Report_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="state_id1" id="state_id1" value="0" />
	<input type="hidden" name="university_id1" id="university_id1"
		value="0" />
	<input type="hidden" name="institute_id1" id="institute_id1" value="0" />
	<input type="hidden" name="degree_id1" id="degree_id1" value="0" />
	<input type="hidden" name="professional_id1" id="professional_id1"
		value="0" />
</form:form>

<c:url value="get_Student_Report_PDF" var="searchUrl1" />
<form:form action="${searchUrl1}" method="post" id="search3"
	name="search3" modelAttribute="comd1">
	<input type="hidden" name="state_id2" id="state_id2" value="0" />
	<input type="hidden" name="university_id2" id="university_id2"
		value="0" />
	<input type="hidden" name="institute_id2" id="institute_id2" value="0" />
	<input type="hidden" name="degree_id2" id="degree_id2" value="0" />
	<input type="hidden" name="professional_id2" id="professional_id2"
		value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		var role = '${role}';
		if (role.toLowerCase().includes('university')) {
			$("#state_drp_div").hide();
			$("#uni_drp_div").hide();

		}

		mockjax1('search_Report');
		table = dataTable('search_Report');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		if (window.location.href.includes("msg")) {
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}

		getDegreeLFromInstituteExam1();
	});

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('state_id').onchange = function() {
			getuniversity_list();
		};

		document.getElementById('university_id').onchange = function() {
			getinstitute_listbyuniversity_exam1();
		};

		document.getElementById('institute_id').onchange = function() {
			getDegreeListFromInstitute3();
		};
		document.getElementById('btnExport').onclick = function() {
			getStudent_Report_Excel();
		};
		document.getElementById('pdfex').onclick = function() {
			Student_Report_PDF();
		};
	});
	function getStudent_Report_Excel() {
		$("#state_id1").val($("#state_id").val());
		$("#university_id1").val($("#university_id").val());
		$("#institute_id1").val($("#institute_id").val());
		$("#degree_id1").val($("#degree_id").val());
		$("#professional_id1").val($("#professional_id").val());
		document.getElementById('search2').submit();
	}

	function Student_Report_PDF() {
		$("#state_id2").val($("#state_id").val());
		$("#university_id2").val($("#university_id").val());
		$("#institute_id2").val($("#institute_id").val());
		$("#degree_id2").val($("#degree_id").val());
		$("#professional_id2").val($("#professional_id").val());
		document.getElementById('search3').submit();

	}

	function data(search_Report) {
		jsondata = [];
		var table = $('#' + search_Report).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var state_id = $("#state_id").val();
		var university_id = $("#university_id").val();
		var institute_id = $("#institute_id").val();
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();

		$.post("getFilter_report?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			state_id : state_id,
			university_id : university_id,
			institute_id : institute_id,
			degree_id : degree_id,
			professional_id : professional_id

		}, function(j) {
			for (var i = 0; i < j.length; i++) {

				if (role.toLowerCase().includes('university')) {
					jsondata.push([ j[i].ser, j[i].institute_name,
							j[i].degree_name, j[i].count, j[i].count1,
							j[i].count2 ]);
				} else {
					jsondata.push([ j[i].ser, j[i].state_name,
							j[i].university_name, j[i].institute_name,
							j[i].degree_name, j[i].count, j[i].count1,
							j[i].count2 ]);
				}
			}
		});

		$.post("getTotal_report_dataCount?" + key + "=" + value, {
			Search : Search,
			state_id : state_id,
			university_id : university_id,
			institute_id : institute_id,
			degree_id : degree_id,
			professional_id : professional_id
		}, function(j) {
			FilteredRecords = j;
		});
	}

	function getuniversity_list() {
		var state_id = $("#state_id").val();
		$
				.post('getuniversity_list?' + key + "=" + value, {
					state_id : state_id
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#university_id").html(options);
						});
	}

	function getinstitute_listbyuniversity_exam1() {
		var university_id = $("#university_id").val();
		$
				.post(
						'getinstitute_listbyuniversity_exam1?' + key + "="
								+ value, {
							university_id : university_id
						})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#institute_id").html(options);
						});
	}

	function getDegreeListFromInstitute3() {
		var institute_id = $("#institute_id").val();
		$
				.post('getDegreeListFromInstitute2?' + key + "=" + value, {
					institute_id : institute_id
				})
				.done(
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
</script>

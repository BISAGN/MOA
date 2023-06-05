<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css"> -->
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
<!-- datatable style and js end-->
<!-- <link rel="stylesheet" href="assets/vendor/internal_css.css"> -->


<section class="dashboard-page regulation_report">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Upload Faculty Attendance</h2>
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
									Upload Faculty Attendance</li>
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
						action="Upload_Att_action?${_csrf.parameterName}=${_csrf.token}"
						method="POST" modelAttribute="Upload_Att_cmd"
						enctype="multipart/form-data">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Upload Attendance Data</h6>
								<div class="row">

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label>Upload Attendance <strong class="mandatory">*</strong></label>
											<input type="file" id=u_file1 name="u_file1"
												class="form-control" autocomplete="off" maxlength="25"
												placeholder="Enter Staff Name">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-2">
											<label>Date<strong class="mandatory">* </strong></label> <input
												type="text" name="attendance_date" id="attendance_date"
												maxlength="10"
												class="form-control-sm form-control custom_attend_exp_excl"
												aria-required="true" autocomplete="off" value="DD/MM/YYYY"
												placeholder="Select Attendance Date">
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
											
											
											
<!-- 											<li id="btnexcel"><i class="fa fa-file-excel-o"></i><input -->
<!-- 												type="button" id="btnExport" -->
<!-- 												class="main-btn success-btn btn-hover btn-iconic-icon btnexcel" -->
<!-- 												value="Export Sample Template Format"></li> -->
											<li><a href="exp_attendance_excel_url"
												class="main-btn dark-btn btn-hover btnreset">Reset</a></li>
												
												<li id="btnExport"><a href="#0"
												class="main-btn success-btn btn-hover btn-iconic-icon btnexcel"
												value="Export Sample Template Format"><i
													class="bi bi-file-earmark-excel"></i>Export Sample Template
													Format</a></li>
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

<c:url value="Att_Exp_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="typeReportdate" id="typeReportdate"
		value="0" />
</form:form>

<script type="text/javascript" nonce="${cspNonce}">
	$(document).ready(function() {
		$.ajaxSetup({
			async : false
		});
		datepicketDate('attendance_date');
	});
	function getAtt_Excel() {
		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('search2').submit();
	}
	function getAtt_Excel1() {
		var attendance_date = $("#attendance_date").val();
		$("#typeReportdate").val(attendance_date);
		var a = $("#typeReportdate").val();
		document.getElementById('typeReportdate').value = a;
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
		var attendance_date = $("input#attendance_date").val();
		if (attendance_date == "DD/MM/YYYY" || attendance_date == ""
				|| attendance_date == null) {
			alert("Please Select Date")
			$("input#attendance_date").focus();
			return false;
		} else {
			getAtt_Excel();
		}
		return true;
	}
	////////CSP start================================================	
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btn-save').onclick = function() {
			return Validation();
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
			getAtt_Excel1();
		};
		document.getElementById('btnExport').onchange = function() {
			getAtt_Excel1();
		};
		document.getElementById('btnExport').onclick = function() {
			return Validation1();
		};
	});
</script>
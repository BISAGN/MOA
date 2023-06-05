<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Upload Questions</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Upload
									Questions</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form action="Upload_Paper_action" method="POST"
						modelAttribute="Upload_Paper_cmd" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Upload Questions</h6>
								<div class="row">

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label>Course<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="course_id" id="course_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCourse_upload_Paper}"
														varStatus="num">
														<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label>Set<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="set_id" id="set_id">
													<option value="0">--Select--</option>
													<%-- 												<c:forEach var="item" items="${s_name}" varStatus="num"> --%>
													<%-- 													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option> --%>
													<%-- 												</c:forEach> --%>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label>Module<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="module_id" id="module_id">
													<option value="0">--Select--</option>
													<%-- 												<c:forEach var="item" items="${s_name}" varStatus="num"> --%>
													<%-- 													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option> --%>
													<%-- 												</c:forEach> --%>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>Upload Question<span class="mandatory">*</span></label>

											<input type="file" id="u_file1" accept=".pdf" name="u_file1"
												autocomplete="off" class="form-control" /></input>
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
												class="main-btn info-btn btn-hover btnsave" value="Save"></li>
											<li><a href="Upload_Paper_url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>
											<li id="btnExport"><input href="Upload_Paper_url" 
												class="main-btn success-btn btn-hover btn-iconic-icon btnexcel"
												value=" Export Sample Template Formate " type="button"></li>
											<li><a href="Question_ReportUrl"
												class="main-btn dark-btn btn-hover btn-iconic-icon btnview" type="button"
												value="Reset"><i class="lni lni-eye"></i>View Uploaded Questions</a></li>
												
<!-- 												<li id="btnExport"><a href="Upload_Paper_url"  -->
<!-- 												class="main-btn success-btn btn-hover btn-iconic-icon btnexcel" -->
<!-- 												value="Export Sample Template Format"><i -->
<!-- 													class="bi bi-file-earmark-excel"></i>Export Sample Template -->
<!-- 													Format</a></li> -->
										</ul>
									</div>
								</div>
							</div>
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>


<c:url value="UploadPaper_Excel" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		if (window.location.href.includes("msg")) {
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}
	});

	function getExcel() {

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('search2').submit();
	}

	function getcourselistbyset() {
		var course_id = $("#course_id").val();

		$
				.post('getcourselistby_set?' + key + "=" + value, {
					course_id : course_id
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#set_id").html(options);
						});
	}

	function getcourselistbymodule() {
		var course_id = $("#course_id").val();

		$
				.post('getcourselistby_module?' + key + "=" + value, {
					course_id : course_id
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#module_id").html(options);
						});
	}

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

		document.getElementById('course_id').onchange = function() {
			getcourselistbyset();
			getcourselistbymodule();
		};
		document.getElementById('btnExport').onclick = function() {
			return getExcel();
		};
	});

	function Validation() {

		if ($("#course_id").val() == "0") {
			alert("Please Select Course");
			$("select#course_id").focus();
			return false;
		}
		if ($("#set_id").val() == "0") {
			alert("Please Select Set");
			$("select#set_id").focus();
			return false;
		}
		if ($("#module_id").val() == "0") {
			alert("Please Select Module");
			$("select#module_id").focus();
			return false;
		}

		var u_file1 = $("#u_file1").val();
		var lastDot = u_file1.lastIndexOf('.');
		var fileName = u_file1.substring(0, lastDot);
		var ext = u_file1.substring(lastDot + 1);

		if (ext != "xls") {
			alert("Please Upload Excel File");
			$("input#u_file1").focus();
			return false;
		}
		if ($("#u_file1").val().trim() == "") {
			alert("Please Upload File.");
			$("input#u_file1").focus();
			return false;
		}
		return true;
	}
</script>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css"> -->
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

<section class="dashboard-page">
	<div class="container-fluid">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Download Certificate</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Download
									Certificate</li>
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
					<form:form action="Exam_result_action" method="POST"
						class="form-horizontal" modelAttribute="Exam_result_cmd">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Download Certificate</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Course<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="course_id" id="course_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${courselist}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>
									<!-- <div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>Set<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="set_id" id="set_id" onclick="getModuleNamebyset_id();">
											<option value="0">--Select--</option>
										</select>
									</div>
								</div>								
								end select
							</div> -->

									<!-- <div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>Module<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="module_id" id="module_id" onclick="getexam_namebyModuleName();">
									        <option value="0">---Select---</option>
										</select>
									</div>
								</div>								
								end select
							</div> -->
									<!-- <div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>Exam<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="exam_name" id="exam_name">
									       <option value="0">--Select--</option>
										</select>
									</div>
								</div>								
								end select
							</div> -->
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><input type="button"
												class="main-btn info-btn btn-hover" value="Get Result"
												id="result"> <input type="hidden" name="logo_path"
												id="logo_path" value="" /></li>
											<li>
												<button id="dl_btn"
													class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon custom-d-none"
													type="button">
													<i class="bi bi-file-pdf"></i>Download PDF
												</button>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>
			</div>
		</div>

		<c:url value="getExamBoardAdmitCardPDF" var="getExamBoardAdmitCardPDF" />
		<form:form action="${getExamBoardAdmitCardPDF}" method="post"
			id="Downlod_pdf" name="course_name" modelAttribute="id1">
			<input type="hidden" name="id1" id="id1" value="0" />
			<input type="hidden" name="id2" id="id2" value="0" />
			<input type="hidden" name="id3" id="id3" value="0" />
			<input type="hidden" name="exam_id2" id="exam_id2" value="0" />
			<input type="hidden" name="ayush_id1" id="ayush_id1" />
			<input type="hidden" name="course_id2" id="course_id2" value="0" />
			<input type="hidden" name="module_id2" id="module_id2" value="0" />
			<input type="hidden" name="typeReport" id="typeReport" value="0" />
			<input type="hidden" name="reportname1" id="reportname1" value="0" />
		</form:form>
	</div>
</section>
<%-- <form:form action="" method="POST" class="form-horizontal"
	modelAttribute="Exam_result_cmd">
	<div class="container" align="center">
		<div class="card" >
		<div class="card-header"><h5><span id="lbladd"></span>RESULT</h5></div>
		<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
							<div class="col-md-3">
									<label class=" form-control-label"><strong style="color: red;">* </strong> COURSE </label>
								</div>
									<div class="col-12 col-md-6">
										<select name="course_id" id="course_id" class="form-control" onclick="getset_idbycourse_id();" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${courselist}"
										varStatus="num"> 
 										<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
 									</c:forEach> 
										
									</select>
								</div>
							</div>
						</div>
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 							<div class="col-md-3"> -->
<!-- 									<label class=" form-control-label"><strong style="color: red;">* </strong> SET </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-12 col-md-6"> -->
<!-- 										<select name="set_id" id="set_id" class="form-control" onclick="getModuleNamebyset_id();"> -->
<!-- 										<option value="0">--Select--</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
<!-- 					<div class="col-md-12"> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 							<div class="col-md-3"> -->
<!-- 									<label class=" form-control-label"><strong style="color: red;">* </strong> MODULE </label> -->
<!-- 								</div> -->
<!-- 									<div class="col-12 col-md-6"> -->
<!-- 									<select name="module_id" id="module_id" class="form-control" onclick="getexam_namebyModuleName();"> -->
<!-- 										<option value="0">--Select--</option> -->
										
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 							<div class="col-md-3"> -->
<!-- 									<label class=" form-control-label"><strong style="color: red;">* </strong> EXAM </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-12 col-md-6"> -->
<!-- 									<select name="exam_name" id="exam_name" class="form-control"> -->
<!-- 										<option value="0">--Select--</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
							<div class="col-md-3">
									<label class=" form-control-label"><strong style="color: red;"> </strong> </label>
								</div>
									<div class="col-12 col-md-6">
									<button type="button" class="btn-save" onclick="getResult();">Get Result</button>
<!-- 									<input type="hidden" name="logo_path" id="logo_path" value="" /> -->
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
							<div class="col-md-3">
								</div>
								<div class="col-12 col-md-6" id="dl_btn" style="display: none">
									<button type="button" class="btn-print" onclick="dl_pdf();">Download PDF</button>
								</div>
							</div>
						</div>
					</div>
					<br><br>
		</div>
		</div>
	</div>
</form:form>

<c:url value="getExamBoardAdmitCardPDF" var="getExamBoardAdmitCardPDF" />
<form:form action="${getExamBoardAdmitCardPDF}" method="post" id="Downlod_pdf"
	name="course_name" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
	<input type="hidden" name="id2" id="id2" value="0" />
	<input type="hidden" name="id3" id="id3" value="0" />
	<input type="hidden" name="exam_id2" id="exam_id2" value="0" />
	<input type="hidden" name="ayush_id1" id="ayush_id1" />
	<input type="hidden" name="course_id2" id="course_id2" value="0" />
	<input type="hidden" name="module_id2" id="module_id2" value="0" />
	<input type="hidden" name="typeReport" id="typeReport" value="0" />
	<input type="hidden" name="reportname1" id="reportname1" value="0" />
</form:form> --%>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		getInstLogoPath();
	});
	function getResult() {
		//	getuserlist();
		var course_id = $("select#course_id").val();
		var t = 0;
		var username = '${username}';
		var userid = '${userId}';
		$.post('getModuleListByCourse_data?' + key + "=" + value, {
			course_id : course_id
		}).done(function(x) {
			t = x.length;
		});
		if (t == 0) {
			$.post('Exam_result_action?' + key + "=" + value, {
				course_id : course_id
			}).done(
					function(j) {
						alert("YOU HAVE GOT" + " " + j + " " + "MARKS");
					});
			document.getElementById('dl_btn').style.display = 'block';
		} else {
			alert("finish All module exam first");
			return false;
		}
	}

	$.post('getaayushidfromuserid?' + key + "=" + value, {}).done(function(j) {
		$("#ayush_id1").val(j);
	});

	function getInstLogoPath() {
		$.post('getInstLogoPath?' + key + "=" + value, {}).done(function(j) {
			$("#logo_path").val(j);
		});
	}

	function editData(id) {

		$("#id1").val(id);
		document.getElementById('course_name').submit();
	}

	function dl_pdf(id) {
		if ($("#course_id").val() == "0") {
			alert("Please Select Course  ");
			$("select#course_id").focus();
			return false;
		}

		// 		if ($("#set_id").val() == "0") {
		// 			alert("Please Select Set  ");

		// 			return false;
		// 		}

		// 		if ($("#module_id").val() == "0") {
		// 			alert("Please Select Module  ");

		// 			return false;
		// 		}
		// 		if ($("#exam_name").val() == "0") {
		// 			alert("Please Select Exam  ");

		// 			return false;
		// 		}

		$("#id1").val($("#course_id option:selected").text());
		$("#id2").val($("#set_id option:selected").text());
		$("#id3").val($("#logo_path").val());
		// 		$("#exam_id2").val($("#exam_name").val());
		$("#course_id2").val($("#course_id").val());
		// 		$("#module_id2").val($("#module_id").val());
		$("#reportname1").val("1");
		document.getElementById('typeReport').value = 'pdfL';
		document.getElementById('Downlod_pdf').submit();
	}
</script>

<script nonce="${cspNonce}" type="text/javascript">
	function Validate() {
		if ($("#course_id").val() == "0") {
			alert("Please Select Course ");
			$("select#course_id").focus();
			return false;
		}
		return true;
	}
	// 	function getset_idbycourse_id() {

	// 		//debugger;

	// 		var course_id = $("select#course_id").val();

	// 		$
	// 				.post(
	// 						"getsetDatabyCourse?" + key + "=" + value,
	// 						{
	// 							course_id : course_id
	// 						},
	// 						function(j) {

	// 							var options = '<option value="' + "0" + '">'
	// 									+ "--Select--" + '</option>';
	// 							for (var i = 0; i < j.length; i++) {

	// 								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
	// 										+ j[i][1] + '</option>';
	// 							}
	// 							$("select#set_id").html(options);
	// 						});
	// 	}

	// 	function getModuleNamebyset_id() {

	// 		var set_id = $("select#set_id").val();
	// 		var course_id = $("select#course_id").val();
	// 		$
	// 				.post(
	// 						"getModuleListBySet_data?" + key + "=" + value,
	// 						{
	// 							set_id : set_id,
	// 							course_id : course_id
	// 						},
	// 						function(j) {
	// 							var options = '<option value="' + "0" + '">'
	// 									+ "--Select--" + '</option>';
	// 							for (var i = 0; i < j.length; i++) {

	// 								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
	// 										+ j[i][1] + '</option>';
	// 							}
	// 							$("select#module_id").html(options);
	// 						});
	// 	}
	// 	function getexam_namebyModuleName() {
	// 		var course_id = $("select#course_id").val();
	// 		var set_id = $("select#set_id").val();
	// 		var module_id = $("select#module_id").val();
	// 		$
	// 				.post(
	// 						"getExamlistFromModule?" + key + "=" + value,
	// 						{
	// 							set_id : set_id,
	// 							course_id : course_id,
	// 							module_id : module_id
	// 						},
	// 						function(j) {

	// 							var options = '<option value="' + "0" + '">'
	// 									+ "--Select--" + '</option>';
	// 							for (var i = 0; i < j.length; i++) {
	// 								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
	// 										+ j[i][1] + '</option>';
	// 							}
	// 							$("select#exam_name").html(options);
	// 						});
	// 	}

	function getexamststuslist() {
		var course_id = $("select#course_id").val();
		//		 var exam_name = $("select#exam_name").val();

		var username = '${username}';
		var userid = '${userId}';
		$.post('getModuleListByCourse_data?' + key + "=" + value, {
			course_id : course_id,
		// 				userid : userid
		}).done(function(j) {
			// 				alert(j.length);

			var t = j.length;
			return t;
		});
	}

	function getuserlist() {
		//var module_id = $("select#module_id").val();
		// 		 var exam_name = $("select#exam_name").val();

		var username = '${username}';
		var userid = '${userId}';
	}
	//alert(userid);
	// 			$.post('getUserListstatus?' + key + "=" + value, {
	// 				module_id : module_id,
	// // 				exam_name : exam_name

	// 			}).done(function(j) {
	// // 						alert(j[0]);
	// // 						alert(j[1]);
	// // 						alert(j[2]);
	// 			});

	// 		function getDownloadPdfUploadFile(a){  

	// 			$("#upload_file2").val(a); 
	// 			document.getElementById("search1").submit();
	// 		} 

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('result').onclick = function() {

			if (Validate() == true) {
				return getResult();
			} else {
				return false;
			}

		};
		document.getElementById('dl_btn').onclick = function() {
			dl_pdf();
		};

	});

	//         document.addEventListener('DOMContentLoaded', function() {

	// 			document.getElementById('course_id').onclick = function() {
	// 				 getset_idbycourse_id();
	// 			};
	// 		});
</script>
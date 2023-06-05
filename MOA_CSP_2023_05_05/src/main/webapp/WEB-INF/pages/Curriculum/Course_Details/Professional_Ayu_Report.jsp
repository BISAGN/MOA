<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
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
						<h2>
							<span id="lbladd1"></span> Ncism Curriculum Pdf
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Curriculum
									Pdf</li>
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
					<form:form name="Professional_Ayu_Report"
						id="Professional_Ayu_Report"
						action="Professional_Ayu_ReportAction" method='POST'
						commandName="Professional_Ayu_ReportCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Curriculum Pdf</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">System<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="system_id" id="system_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSystemList}"
														varStatus="num">
														<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
													</c:forEach>
												</select>
												
											</div>
										</div>
										<!-- end select -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="degree_id" id="degree_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>
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
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Subject<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="course_id"
													class="singleselect form-control form-control-lg"
													id="course_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>
									<input type="hidden" id="count_hidden_att"
										name="count_hidden_att" class="form-control autocomplete"
										value="1"> <input type='hidden' id='id' name="id"
										value='0' />
								</div>
							</div>

							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a
												class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon btnpdf"
												id="pdfex"><i class="bi bi-file-pdf" id="printId"
													value="PDF" title="Export to PDF"></i> PDF </a></li>
											<li><a href="CC_Professional_Ayu_Report_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
												<li>
													<a href="https://ncismindia.org/AyUG-SN%20AI-1.pdf" id="sanskrit_pdf_btn"
												       class="main-btn dark-btn btn-hover btnreset d-none" type="button" target="_blank"></a>
												</li>
												<li>
													<a href="https://ncismindia.org/Transitional_Curriculum.pdf" id="Transitional_Curri_pdf_btn"
												       class="main-btn dark-btn btn-hover btnreset d-none" type="button" target="_blank"></a>
												</li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->

						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- start new pdf -->
<c:url value="Professional_Ayu_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search1" name="search1">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="course_id1" id="course_id1" value="0" />
</form:form>
<!-- end -->

<script nonce="${cspNonce}" type="text/javascript">
	$.ajaxSetup({
		async : false
	});
	$(document).ready(function() {
		$.ajaxSetup({
			async : false
		});
		var role = '${role}';

		if (role != "" && role.toLowerCase().includes("student")) {

			if ('${system}' != "") {

				var stu_system = '${system[0][0]}';
				var stu_degree = '${system[0][1]}';
				var stu_prof = '${system[0][2]}';

				if (stu_prof == "1") {
					stu_prof = "15";
				}
				if (stu_prof == "2") {
					stu_prof = "16";
				}
				if (stu_prof == "3") {
					stu_prof = "17";
				}

				$("#system_id").val(stu_system);
				$('#system_id').trigger('change');
				$("select#system_id").attr("disabled", true);
				getdegreelistbysystem();
				$("#degree_id").val(stu_degree);
				$('#degree_id').trigger('change');
				$("select#degree_id").attr("disabled", true);
				$("#professional_id").val(stu_prof);
				$('#professional_id').trigger('change');
				// 				$("select#professional_id").attr("disabled", true); 
				// 				getcourselistby_professional();
				$.ajaxSetup({
					async : false
				});
			}
		}
		 if (role != "" && role.toLowerCase().includes("institute")) {

			if ('${system_name}' != "") {

				var stu_system_inst = '${system_name[0][0]}';

				$("select#system_id").val(stu_system_inst);
				$('select#system_id').trigger('change');
				$("select#system_id").attr("disabled", true);
				$.ajaxSetup({
					async : false
				});
			}
		}
	});

	//start new pdf

	function getPDFExecl(pdf_excel) {
		var course_id = $("#course_id").val();
		if(course_id =='47'){
			 var href = $('#sanskrit_pdf_btn').attr('href');
			 window.open(href, '_blank');
		}
		
		else if(course_id =='117'){
			
			var href = $('#Transitional_Curri_pdf_btn').attr('href');
			 
			 window.open(href, '_blank');
			
		  }else{
		document.getElementById('course_id1').value = course_id;
		document.getElementById('typeReport').value = pdf_excel;
		document.getElementById('search1').submit();
		}
	}

	function getdegreelistbysystem() {
		var system_name = $("#system_id").val();
		$
				.post('getDegreeListbysystem1?' + key + "=" + value, {
					system_name : system_name
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

	function getCourseList() {
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_hid").val();
		var professional_id = $("#professional_id").val();
		$
				.post('getCourseList_for_Curri?' + key + "=" + value, {
					system_id : system_id,
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
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem();
		};
		document.getElementById('professional_id').onchange = function() {
			getCourseList();
		};
		document.getElementById('pdfex').onclick = function() {

			return Validation();
		};
	});

	function Validation() {
		if ($("#system_id").val().trim() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("#degree_id").val().trim() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		if ($("#professional_id").val().trim() == "0") {
			alert("Please Select professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#course_id").val().trim() == "0") {
			alert("Please Select Subject .");
			$("select#course_id").focus();
			return false;
		}
		getPDFExecl('pdfL');
		return true;
	}
</script>

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
							<span id="lbladd1"></span>Curriculum Pdf
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
												id="pdfex">
												<i class="bi bi-file-pdf" id="printId"
													value="PDF" title="Export to PDF"></i> PDF </a></li>
											<li><a href="NCH_Curriculum_Pdf_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
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

	<!-- 	<div id="pdf" class="custom-d-none"></div> -->
</section>

<!-- start new pdf -->
<c:url value="NCH_Curriculum_Report_Url_pdf" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search1" name="search1">
	<input type="hidden" name="typeReport" id="typeReport" value="" />
	<input type="hidden" name="course_id1" id="course_id1" value="0" />
</form:form>

<c:url value="AnatomyPdfDownload" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search2" name="search2">
	<input type="hidden" name="typeReport2" id="typeReport2" value="" />
	<input type="hidden" name="course_id2" id="course_id2" value="0" />
</form:form>

<c:url value="Organon_of_Medicine_Homoeopathicphilosophy_Fundamentals_of_PsychologyPdfDownload_url" var="mprUrl2" />
<form:form action="${mprUrl2}" method="post" id="search3" name="search3">
	<input type="hidden" name="typeReport3" id="typeReport3" value="" />
	<input type="hidden" name="course_id3" id="course_id3" value="0" />
</form:form>

<c:url value="NCHPsychologyPdfDownload" var="mprUrl4" />
<form:form action="${mprUrl4}" method="post" id="search4" name="search4">
	<input type="hidden" name="typeReport4" id="typeReport4" value="" />
	<input type="hidden" name="course_id4" id="course_id4" value="0" />
</form:form>

<!--  	STRT	Riddhi -->
<c:url value="NCHYogaHealthPromotionPdfDownload" var="mprUrl4" />
<form:form action="${mprUrl4}" method="post" id="search5" name="search5">
<input type="hidden" name="typeReport5" id="typeReport5" value="" />
<input type="hidden" name="course_id5" id="course_id5" value="0" />
</form:form>
<!--  	End	Riddhi -->
<!-- end -->

<!--  	STRT	Riddhi --Homoeopathic Materia Medica -->
<c:url value="NCHHomoeopathicMateriaMedicaPdfDownload" var="mprUrl6" />
<form:form action="${mprUrl6}" method="post" id="search6" name="search6">
<input type="hidden" name="typeReport6" id="typeReport6" value="" />
<input type="hidden" name="course_id6" id="course_id6" value="0" />
</form:form>
<!--  	End	Riddhi --Homoeopathic Materia Medica -->

<!--  	STRT	Riddhi --Homoeopathic Pharmacy -->
<c:url value="NCHHomoeopathicPharmacyPdfDownload" var="mprUrl8" />
<form:form action="${mprUrl8}" method="post" id="search8" name="search8">
<input type="hidden" name="typeReport8" id="typeReport8" value="" />
<input type="hidden" name="course_id8" id="course_id8" value="0" />
</form:form>
<!--  	End	Riddhi --Homoeopathic Pharmacy -->

<c:url value="NCHHumanPhysiologyBiochemistryPdfDownload" var="mprUrl7" />
<form:form action="${mprUrl7}" method="post" id="search7" name="search7">
<input type="hidden" name="typeReport7" id="typeReport7" value="" />
<input type="hidden" name="course_id7" id="course_id7" value="0" />
</form:form>
<!-- end -->

<c:url value="NCHHomoeopathicReportandCaseTakingPdfDownload" var="mprUrl9" />
<form:form action="${mprUrl9}" method="post" id="search9" name="search9">
<input type="hidden" name="typeReport9" id="typeReport9" value="" />
<input type="hidden" name="course_id9" id="course_id9" value="0" />
</form:form>


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
		if(course_id == "57"){
			document.getElementById('course_id2').value = course_id;
			document.getElementById('typeReport2').value = pdf_excel;
			document.getElementById('search2').submit();
		}else if(course_id == "59"){
			document.getElementById('course_id3').value = course_id;
			document.getElementById('typeReport3').value = pdf_excel;
			document.getElementById('search3').submit();
		}else if(course_id == "122"){
			document.getElementById('course_id4').value = course_id;
			document.getElementById('typeReport4').value = pdf_excel;
			document.getElementById('search4').submit();
		}// 	STRT	Riddhi
		else if(course_id == "160"){
			document.getElementById('course_id5').value = course_id;
			document.getElementById('typeReport5').value = pdf_excel;
			document.getElementById('search5').submit();
		}
//			 	End	Riddhi
		//STRT	Riddhi -- Homoeopathic Materia Medica
		else if(course_id == "58"){
			document.getElementById('course_id6').value = course_id;
			document.getElementById('typeReport6').value = pdf_excel;
			document.getElementById('search6').submit();
		}
	//End	Riddhi --Homoeopathic Materia Medica
//		 	START	Riddhi -- Homoeopathic Pharmacy
			else if(course_id == "60"){
				document.getElementById('course_id8').value = course_id;
				document.getElementById('typeReport8').value = pdf_excel;
				document.getElementById('search8').submit();
			}
//		End	Riddhi --Homoeopathic Pharmacy
			else if(course_id == "61"){
				document.getElementById('course_id7').value = course_id;
				document.getElementById('typeReport7').value = pdf_excel;
				document.getElementById('search7').submit();
			}else if(course_id == "116"){
				document.getElementById('course_id9').value = course_id;
				document.getElementById('typeReport9').value = pdf_excel;
				document.getElementById('search9').submit();
			}
		else{		
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

	// 	function getCourseList() {

	// 		var system_id = $("#system_id").val();
	// 		var degree_id = $("#degree_id").val();
	// 		var professional_id = $("#stu_prof").val();
	// 		$
	// 				.post('getCourseList_for_Curri?' + key + "=" + value, {
	// 					degree_id : degree_id,
	// 					system_id : system_id,
	// 					professional_id : professional_id
	// 				})
	// 				.done(
	// 						function(j) {

	// 							var options = '<option value="' + "0" + '">'
	// 									+ "--Select--" + '</option>';
	// 							for (var i = 0; i < j.length; i++) {
	// 								$.ajaxSetup({
	// 									async: false
	// 								});
	// 								$
	// 										.post(
	// 												'getCoursedata?' + key + "="
	// 														+ value, {
	// 													course_id : j[i][0]
	// 												})
	// 										.done(
	// 												function(k) {
	// 													if (k > 0) {

	// 														options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
	// 																+ j[i][1]
	// 																+ '</option>';
	// 													}

	// 													$("select#course_id").html(
	// 															options);
	// 												});
	// 							}
	// 						});
	// 	}

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
		// 		document.getElementById('degree_id').onchange = function() {
		// 			getCourseList();
		// 		};
		document.getElementById('pdfex').onclick = function() {

			return Validation();
		};
		// 		document.getElementById('pdfex').onclick = function() {
		// 			genpdf();
		// 		};
	});

	function data(search_Professional_Ayu_Report) {
		jsondata = [];
		var table = $('#' + search_Professional_Ayu_Report).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];

		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();
		var course_id = $("#course_id").val();
		setTimeout(setTimeLoadForTable, 500);
	}

	// 	function getcourselistby_professional() {
	// 		var degree_id = $("#degree_id").val();
	// 		var professional_id = $("#professional_id").val();

	// 		$
	// 				.post('getCoursedata?' + key + "=" + value, {
	// 					degree_id : degree_id,
	// 					professional_id : professional_id
	// 				})
	// 				.done(
	// 						function(j) {
	// 							var options = '<option value="' + "0" + '">'
	// 									+ "--Select--" + '</option>';
	// 							for (var i = 0; i < j.length; i++) {
	// 								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
	// 										+ j[i][1] + '</option>';
	// 							}
	// 							$("select#course_id").html(options);
	// 						});
	// 	}

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

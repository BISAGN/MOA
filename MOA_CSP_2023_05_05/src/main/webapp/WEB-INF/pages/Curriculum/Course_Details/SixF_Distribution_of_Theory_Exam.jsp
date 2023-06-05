<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<link rel="stylesheet" href="assets/vendor/internal_css.css">

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span>T6-F-Distribution Of Theory Exam
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">T6-F-Distribution
									Of Theory Exam</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<!-- Maximum Marks: 100 -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="course" id="SixF_Distribution_Theory_Action"
						action="SixF_Distribution_Theory_Action" method='POST'
						commandName="SixF_Distribution_Theory_CMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">T6-F-Distribution Of Theory Exam</h6>
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
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="degree_id" id="degree_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
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
											<input type="hidden" id="count_hidden_att"
												name="count_hidden_att" class="form-control autocomplete"
												value="1">
										</div>
									</div>
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">

										<ul class="buttons-group mainbtn">

											<li id="btn-save"><input value="Save"
												class="main-btn info-btn btn-hover btnsave" type="submit" />
											</li>
											<li><a href="SixF_Distribution_Theory_Exam_url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>
											<li id="btn-view"><a
												class="main-btn dark-btn btn-hover btn-iconic-icon btnview"
												type="button"><i class="lni lni-eye"></i>View</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
						<div id="pop">
							<div class="row">
								<div class="card-style mb-30">
									<div class="col-lg-12 col-md-12 col-sm-12">

										<div class="custom-multi-table">
											<h6>Distribution Of Theory Exam (6-F)</h6>

											<div
												class="table-wrapper table-responsive custom-table custom-table-v2">
												<!-- id="container-table" -->
												<table class="table table-striped" id="popT1">

													<thead>
														<tr>
															<th colspan="4"><h6>Paper - 1</h6></th>
															<th colspan="3"><h6>D -Type of Questions Yes
																	can be asked. No should not be asked.</h6></th>
														</tr>

														<tr>
															<th><h6>Sr No.</h6></th>
															<th><h6>List of Topics</h6></th>
															<th><h6>Term</h6></th>
															<th><h6>Marks</h6></th>
															<th><h6>MCQ</h6></th>
															<th><h6>SAQ</h6></th>
															<th><h6>LAQ</h6></th>
														</tr>

													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>

									</div>
									<!-- 										end table -->

									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="custom-multi-table">
											<h6></h6>

											<div
												class="table-wrapper table-responsive custom-table custom-table-v2">
												<!-- id="container-table" -->
												<table class="table table-striped" id="popT2">

													<thead>

														<tr>
															<th colspan="4"><h6>Paper - 2</h6></th>
															<th colspan="3"><h6>D - Type of Questions Yes
																	can be asked. No should not be asked.</h6></th>
														</tr>

														<tr>
															<th><h6>Sr No.</h6></th>
															<th><h6>List of Topics</h6></th>
															<th><h6>Term</h6></th>
															<th><h6>Marks</h6></th>
															<th><h6>MCQ</h6></th>
															<th><h6>SAQ</h6></th>
															<th><h6>LAQ</h6></th>
														</tr>

													</thead>
													<tbody>
													</tbody>
												</table>

												<!-- 						end card -->
											</div>
											<!-- 				end col -->
										</div>
									</div>

								</div>
							</div>

						</div>
					</form:form>
				</div>
			</div>
		</div>

	</div>
</section>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$.ajaxSetup({
			async : false
		});
		$("#pop").hide();
		$("#popT2").hide();
		$("#btn-save").hide();
	});

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

	function getcourselistbysystemdegreeprof() {
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();
		$
				.post('getCourseList_for_Curri?' + key + "=" + value, {
					degree_id : degree_id,
					system_id : system_id,
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
		document.getElementById('btn-view').onclick = function() {
			return View_Validation();
		};
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

		document.getElementById('degree_id').onchange = function() {
			getcourselistbysystemdegreeprof();
		};
		document.getElementById('professional_id').onchange = function() {
			getcourselistbysystemdegreeprof();
		};
		// 	document.getElementById('btn-view').onclick = function() {
		// 		getpop();
		// 	};

	});

	function getpop() {
		var course_id = $("#course_id").val();
		var ser = 1;
		var paper = "";
		var noofpaper = 1;
		var paperIIser = 1;
		$
				.post(
						"getSixFviewdata?" + key + "=" + value,
						{
							course_id : course_id
						},
						function(j) {
							if (j == 0) {
								$("#popT1")
										.append(
												'<tr><td colspan="7"><p>No Data Available</p></td></tr>');
							}
							if (j == 0) {
								$("#popT2")
										.append(
												'<tr><td colspan="7"><p>No Data Available</p></td></tr>');
							}
							$("#count_hidden_att").val(j.length);
							$("tr#popTR").empty();
							for (var i = 0; i < j.length; i++) {
								paper = j[i][4];
								if (paper == "PAPER I") {
									$("table#popT1")
											.append(
													'<tr id="popT1R">'
															+ '<td><p id="serno">'
															+ noofpaper
															+ '</p></td>'
															+ '<td><p id="topic'+ser+'">'
															+ j[i][1]
															+ '</p><input type="hidden" id="topicid'+ser+'" name="topicid'+ser+'" value="'+j[i][5]+'"></td>'
															+ '<td><p id="term'+ser+'">'
															+ j[i][2]
															+ '</p></td>'
															+ '<td><p id="marks'+ser+'">'
															+ j[i][3]
															+ '</p></td>'
															+ '<td><input class="form-check-input mr-5 mul_check chb" type="checkbox" name="mcq'+ser+'" id="mcq'+ser+'" value=""/><input type="hidden" id="mcqcbval'+ser+'" name="mcqcbval'+ser+'" value="0"></td>'
															+ '<td><input class="form-check-input mr-5 mul_check chb" type="checkbox" name="saq'+ser+'" id="saq'+ser+'" value=""/><input type="hidden" id="saqcbval'+ser+'" name="saqcbval'+ser+'" value="0"></td>'
															+ '<td><input class="form-check-input mr-5 mul_check chb" type="checkbox" name="laq'+ser+'" id="laq'+ser+'" value=""/><input type="hidden" id="laqcbval'+ser+'" name="laqcbval'+ser+'" value="0"></td>'
															+ '</tr>');
									noofpaper++;
								}
								if (paper == "PAPER II") {
									
									$("table#popT2")
											.append(
													'<tr id="popT2R">'
															+ '<td><p id="serno">'
															+ paperIIser
															+ '</p></td>'
															+ '<td><p id="topic'+ser+'">'
															+ j[i][1]
															+ '</p><input type="hidden" id="topicid'+ser+'" name="topicid'+ser+'" value="'+j[i][5]+'"></td>'
															+ '<td><p id="term'+ser+'">'
															+ j[i][2]
															+ '</p></td>'
															+ '<td><p id="marks'+ser+'">'
															+ j[i][3]
															+ '</p></td>'
															+ '<td><input class="form-check-input mr-5 mul_check chb" type="checkbox" name="mcq'+ser+'" id="mcq'+ser+'" value=""/><input type="hidden" id="mcqcbval'+ser+'" name="mcqcbval'+ser+'" value="0"></td>'
															+ '<td><input class="form-check-input mr-5 mul_check chb" type="checkbox" name="saq'+ser+'" id="saq'+ser+'" value=""/><input type="hidden" id="saqcbval'+ser+'" name="saqcbval'+ser+'" value="0"></td>'
															+ '<td><input class="form-check-input mr-5 mul_check chb" type="checkbox" name="laq'+ser+'" id="laq'+ser+'" value=""/><input type="hidden" id="laqcbval'+ser+'" name="laqcbval'+ser+'" value="0"></td>'
															+ '</tr>');
									paperIIser++;
								}
								setcbsonclick(ser);
								ser++;
							}
							$("#popT2").show();
							$("#btn-save").show();
							$("#noofpaper").val(noofpaper);
							
						});
		$("#pop").show();
		$("#btn-save").show();
		
	}

	function setcbsonclick(ser) {

		document.getElementById('mcq' + ser).onclick = function() {
			onclicksetval('mcq', ser);
		};
		document.getElementById('saq' + ser).onclick = function() {
			onclicksetval('saq', ser);
		};
		document.getElementById('laq' + ser).onclick = function() {
			onclicksetval('laq', ser);
		};

	}

	function onclicksetval(qt, ser) {

		if (qt == "mcq") {
			if ($("#mcq" + ser).is(":checked") == true) {
				$("#mcqcbval" + ser).val("1");
			}
			if ($("#mcq" + ser).is(":checked") == false) {
				$("#mcqcbval" + ser).val("0");
			}
		}
		if (qt == "saq") {
			if ($("#saq" + ser).is(":checked") == true) {
				$("#saqcbval" + ser).val("1");
			}
			if ($("#saq" + ser).is(":checked") == false) {
				$("#saqcbval" + ser).val("0");
			}
		}
		if (qt == "laq") {
			if ($("#laq" + ser).is(":checked") == true) {
				$("#laqcbval" + ser).val("1");
			}
			if ($("#laq" + ser).is(":checked") == false) {
				$("#laqcbval" + ser).val("0");
			}
		}

	}
	function View_Validation() {
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
			alert("Please Select Subject.");
			$("select#course_id").focus();
			return false;
		}
		getpop();
		return true;
	}
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
			alert("Please Select Subject.");
			$("select#course_id").focus();
			return false;
		}
		return true;
	}
</script>

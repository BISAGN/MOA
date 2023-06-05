<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
							<span id="lbladd1"></span>View Calculation Method for Internal
							Assessment Marks 
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">View
									Calculation Method for Internal Assessment Marks</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="course" id="search_nch_exam_Action"
						action="search_nch_exam_Action" method='POST'
						commandName="search_nch_exam_CMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">View Calculation Method for Internal
									Assessment Marks </h6>
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
												<select class="singleselect form-control form-control-lg"
													name="degree_id" id="degree_id">
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
												<select class="singleselect form-control form-control-lg"
													name="course_id" id="course_id">
													<option value="0">--Select--</option>
												</select> <input type="hidden" id="count_hidden_att"
													name="count_hidden_att" class="form-control autocomplete"
													value="1">

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
											<li><a href="NCH_View_intern_assem_marks_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>
											<li><a id="btn-view"
												class="main-btn dark-btn btn-hover btn-iconic-icon btnview"
												type="button"><i class="lni lni-eye"></i>View</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>
			</div>
			<div id="pop" style="">
				<div class="row">
					<div class="col-lg-12">
						<div class="card-style mb-30">
							<div class="custom-multi-table">
								<div
									class="table-wrapper table-responsive custom-table custom-table-v2">
									<!-- id="container-table" -->
									<table class="table table-striped">
										<thead>
											<tr>
												<th colspan="20">
													<h6>Calculation Method for Internal assessment Marks</h6>
												</th>


												<!-- 										<td colspan="9"><span><label class="bold"> -->
												<!-- 													<br> -->
												<!-- 												<h4>6 C - Calculation Method for Internal assessment -->
												<!-- 														Marks (30 Marks)</h4> -->
												<!-- 											</label></span></td> -->
											</tr>

											<tr>
										    	<th rowspan="2" ><h6>S.NO.</h6></th>
									    		<th rowspan="2"><h6>SUBJECT</h6></th>
												<th rowspan="2" colspan="6"><h6>PERIODIC EXAM ASSESSMENT*</h6></th>
												<th colspan="6"><h6>TERMINAL TEST[TT] AND PRELIMINARY EXAM[PE] </h6></th>
												<th colspan="2"><h6>TOTAL</h6></th>
												<th rowspan="2"><h6>CONVERT TO 10 THEORY MARKS</h6></th>
												<th rowspan="2" ><h6>CONVERT TO 20 PRACTICAL MARKS</h6></th>
												<th rowspan="2"><h6>FINAL INTERNAL ASSESSMENT MARKS FOR THEORY
												     (TOTAL A + B/2 = C) </h6></th>
											     <th rowspan="2" ><h6>FINAL INTERNAL ASSESSMENT MARKS FOR PRACTICAL
												     MARKS OBTAINED AT D</h6></th>
											</tr>

											<tr>
												<th colspan="2"><p>1 TT</p></th>
												<th colspan="2"><p>2 TT</p></th>
												<th colspan="2"><p>PE</p></th>
												<th colspan="2"><p>(1 TT + 2 TT + PE)</p></th>
											</tr>

											<tr>
											    <th colspan="1"><p></p></th>
												<th colspan="1"><p></p></th>
												<th><p>Theory Only</p></th>
												<th><p>1 PE</p></th>
												<th><p>2 PE </p></th>
												<th><p>3 PE</p></th>
												<th><p>Total Marks Obtained</p></th>
												<th><p>Convert to 10 (Total Marks Obtained/Sum Total Marks of 03 PE * 10)   A</p></th>
												<th><p>Theory</p></th>
												<th><p>Practical</p></th>
												<th><p>Theory</p></th>
												<th><p>Practical</p></th>
												<th><p>Theory</p></th>
												<th><p>Practical</p></th>
												<th><p>Theory</p></th>
												<th><p>Practical</p></th>
												<th><p>Total Marks Obtained in Theory TT 1 + TT 2 + PE / Sum Total Marks of Theory TT 1 + TT 2 +PE * 10    B</p></th>
												<th><p>Total Marks Obtained in Practical TT 1 + TT 2 + PE / Sum Total Marks of Practical TT 1 + TT 2 +PE * 10 /20   
												                (as applicable to subject )     </p></th>
								                <th colspan="1"><p></p></th>
								                <th colspan="1"><p></p></th>
							           </tr>
										</thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$("#pop").hide();
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
		document.getElementById('degree_id').onchange = function() {
			getcourselistbysystemdegreeprof();
		};
		document.getElementById('professional_id').onchange = function() {
			getcourselistbysystemdegreeprof();
		};
// 			document.getElementById('course_id').onclick = function() {
// 				$("#pop").show();
// 		// 	};
		document.getElementById('btn-view').onclick = function() {
			$("#pop").show();
		};

	});
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
			alert("Please Select Subject .");
			$("select#course_id").focus();
			return false;
		}
		getpop();
		return true;
	}

</script>

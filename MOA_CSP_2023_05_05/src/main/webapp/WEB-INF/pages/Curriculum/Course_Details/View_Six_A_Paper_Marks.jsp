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
							<span id="lbladd1"></span>T6 - a -Number of Papers and Marks
							Distribution
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">T6 -
									a -Number of Papers and Marks Distribution (6-A)</li>
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
					<form:form name="course" id="search_exam_Action"
						action="search_exam_Action" method='POST'
						commandName="search_exam_CMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">T6 - a -Number of Papers and Marks
									Distribution (6-A)</h6>
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
													name="degree_id" id="degree_id" class="form-control">
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
													name="course_id" class="form-control" id="course_id">
													<option value="0">--Select--</option>
												</select>
											</div>
											<!-- Hidden Start --> 
											<input type="hidden" id="count_hidden_att"
												name="count_hidden_att" class="form-control autocomplete"
												value="1">
												<!-- Hidden End -->
												
										</div>
									</div>
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a href="SixA_url"
												class="main-btn dark-btn n btn-hover" type="button">Reset</a>
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
			<section class="single-detail-block">
				<div class="tables-wrapper">
					<div id="pop">
						<div class="row">
							<div class="col-lg-12">
								<div class="card-style mb-30">
									<div class="custom-multi-table">
										<!-- <h3>Number of Papers and Marks Distribution</h3> id="container-table" -->
										<div class="table-wrapper table-responsive custom-table custom-table-v2">
										<h6 class="mb-10">Number of Papers and Marks Distribution</h6>
											<table class="table table-striped" id="pop">
												<thead>
													<tr>
														<th rowspan="2"><h6>Sr No.</h6></th>
														<th rowspan="2"><h6>Subject Code</h6></th>
														<th rowspan="2"><h6>Papers</h6></th>
														<th rowspan="2"><h6>Theory</h6></th>
														<th colspan="5"><h6>Practical/Clinical Assessment</h6></th>
														<th rowspan="2"><h6>Grand Total</h6></th>
													</tr>

													<tr>
														<th><h6>Practical</h6></th>
														<th><h6>Viva</h6></th>
														<th><h6>Elective</h6></th>
														<th><h6>IA</h6></th>
														<th><h6>Sub Total</h6></th>

													</tr>

													<tr>
														<td><p>1</p></td>
														<td><p id="course_code"></p></td>
														<td><p id="paper"></p></td>
														<td><p id="theory"></p></td>
														<td><p id="practical">--</p></td>
														<td><p id="viva">--</p></td>
														<td><p id="elective">--</p></td>
														<td><p id="ia">--</p></td>
														<td><p id="sub_total"></p></td>
														<td><p id="grand_total"></p></td>
													</tr>
												</thead>
												<tbody></tbody>
											</table>
											<!-- 										end table -->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</section>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$("#pop").hide();
	});

	function getpop() {
		var course_id = $("#course_id").val();
		$.post('get6Aviewdata?' + key + "=" + value, {
			course_id : course_id
		}).done(
				function(j) {
					$("#course_code").text(j[0][0]);
					$("#paper").text(j[0][1]);
					$("#theory").text(j[0][2]);
					if (j[0][3] != "0") {
						$("#practical").text(j[0][3]);
					}
					if (j[0][4] != "0") {
						$("#viva").text(j[0][4]);
					}
					if (j[0][5] != "0") {
						$("#elective").text(j[0][5]);
					}
					if (j[0][6] != "0") {
						$("#ia").text(j[0][6]);
					}
					var sub_total = parseInt(j[0][3]) + parseInt(j[0][4])
							+ parseInt(j[0][5]) + parseInt(j[0][6]);
					$("#sub_total").text(sub_total);
					var grand_total = parseInt(sub_total) + parseInt(j[0][2]);
					$("#grand_total").text(grand_total);
				});
		$("#pop").show();
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
		// 	document.getElementById('btn-view').onclick = function() {
		// 		getpop();
		// 	};

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
			alert("Please Select Subject.");
			$("select#course_id").focus();
			return false;
		}
		getpop();
		return true;
	}
</script>

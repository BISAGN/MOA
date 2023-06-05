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

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span>T6 B - View Scheme of Assessment
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">T6 B
									- View Scheme of Assessment</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="View_ Scheme of Assessment"
						id="View_ Scheme of Assessment_Action"
						action="View_ Scheme of Assessment_Action" method='POST'
						commandName="View_ Scheme of Assessment_CMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">T6 B - View Scheme of Assessment</h6>
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
										<label for="text-input">Subject<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="course_id" id="course_id">
												<option value="0">--Select--</option>
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
											<li><a href="View_Scheme_of_Assessment_url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
											<li><a id="btn-view"
												class="main-btn dark-btn btn-hover btn-iconic-icon btnview"
												type="button"><i class="lni lni-eye"></i>View</a></li>
											<!-- 								<li><input value="Save" id="btn-save" -->
											<!-- 									class="main-btn info-btn btn-hover" type="submit" /></li> -->
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
						<div id="pop">
							<div class="row">
								<div class="col-lg-12">
									<div class="card-style mb-30">
									<div class="custom-multi-table">
										<h6 class="mb-10">6 B - Scheme of Assessment (formative and Summative)</h6>
										<div class="table-wrapper table-responsive custom-table custom-table-v2">
											<!-- id="container-table" -->
											<table class="table table-striped" id="pop">
												<thead>
													<tr>
														<th rowspan="2"><h6>SR.NO.</h6></th>
														<th rowspan="2"><h6>PROFESSIONAL SUBJECT</h6></th>
														<th colspan="3"><h6 >DURATION OF PROFESSIONAL SUBJECT</h6></th>
													</tr>
													<tr>
														<th><h6>First	Term (1-6 Months)</h6></th>
														<th><h6>Second Term (7-12 Months)</h6></th>
														<th><h6>Third Term (13-18 Months)</h6></th>
													</tr>
													</thead>
													<tbody>
													<tr>
														<td><p id="ser">1</p></td>
														<td><p id="prof"></p></td>
														<td><p id="termI"></p></td>
														<td><p id="termII"></p></td>
														<td><p id="termIII"></p></td>
													</tr>
												</tbody>
											</table>
										</div>
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

	function getpop() {
		var course_id = $("#course_id").val();
		$("#prof").text($("#professional_id option:selected").text());
		var ser = 1;
		$.post("get6BSchemeviewdata?" + key + "=" + value, {
			course_id : course_id,
			term : "I"
		}, function(j) {
			$("#termI").text(j[0][1]);
		});
		$.post("get6BSchemeviewdata?" + key + "=" + value, {
			course_id : course_id,
			term : "II"
		}, function(j) {
			$("#termII").text(j[0][1]);
		});
		$.post("get6BSchemeviewdata?" + key + "=" + value, {
			course_id : course_id,
			term : "III"
		}, function(j) {
			$("#termIII").text(j[0][1]);
		});
		$("#pop").show();
	}

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem();
		};
		document.getElementById('degree_id').onchange = function() {
			getcourselistbysystemdegreeprof();
		};
		document.getElementById('professional_id').onchange = function() {
			getcourselistbysystemdegreeprof();
		};
		document.getElementById('btn-view').onclick = function() {
			return View_Validation();
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
			alert("Please Select Subject.");
			$("select#course_id").focus();
			return false;
		}
		getpop();
		return true;

	}
</script>
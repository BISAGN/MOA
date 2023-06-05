<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<script type="text/javascript" src="js/watermark/common.js"></script>
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

<link rel="stylesheet" href="assets/vendor/internal_css.css">

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>View Teaching Hours Summary</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Teaching
									Hours Summary</li>
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
					<form:form name="course" id="view_summary"
						action="view_summary_Action" method='POST'
						commandName="view_summary_CMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Teaching Hours Summary</h6>
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
												<select name="course_id"
													class="singleselect form-control form-control-lg"
													id="course_id">
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
											<li><a href="View_summary_Url"
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
			
			<div id="teaching_hours_summary_report">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="custom-multi-table">
							<div class="table-wrapper table-responsive custom-table custom-table-v2">
								<!-- id="container-table" -->
								<table class="table table-striped">
									<thead>
										<tr>
											<th colspan="4"><h6 
													id="system_name1"></h6></th>
										</tr>
										<tr>
											<th colspan="4"><h6>
														Total number of Teaching hours: <span id="num_teaching_hours" class ="text-bold"></span></h6>
													</th>
										</tr>
										</thead>
										<tbody>
										<tr>
											<td colspan="2"><p >Lecture
													hours (LH) - Theory</p></td>
											<td rowspan="3"><p
												 id="total_lh"></p></td>
											<td rowspan="3" ><p
												 id="hou_tot_lh"></p></td>
										</tr>

										<tr id="lh_theory_p1">
											<td><p>Paper I</p></td>
											<td ><p 
												id="no_of_hours1"></p></td>
										</tr>

										<tr id="lh_theory_p2">
											<td><p>Paper II</p></td>
											<td><p
												id="no_of_hours2"></p></td>
										</tr>

										<tr id="nlh_theory">
											<td colspan="2"><p>Non-Lecture
													hours (NLH) - Theory</p></td>
											<td rowspan="3"><p
												 id="total_nlh"></p></td>
											<td rowspan="4"><p
												 id="tot_hou_nlh"></p></td>
										</tr>

										<tr id="nlh_theory_p1">
											<td><p >Paper I</p></td>
											<td><p
												id="no_of_hours3"></p></td>
										</tr>

										<tr id="nlh_theory_p2">
											<td><p>Paper II</p></td>
											<td ><p
												id="no_of_hours4"></p></td>
										</tr>

										<tr id="nlh_prac">
											<td colspan="2"><p >Non-Lecture
													hours (NLH) - Practical</p></td>
											<td ><p
												id="no_of_hours5"></p></td>
										</tr>
									
									</tbody>
								</table>
							</div>
						</div>
		
						<div class="custom-multi-table">
							<div class="table-wrapper table-responsive custom-table custom-table-v2">
								<!-- id="container-table" -->
								<table class="table table-striped">
									<thead>
									<tr>
										<th colspan="6"><h6
												id="system_name2"></h6></th>
									</tr>
									<tr>
										<th colspan="6"><h6>Examination
													(Papers & Mark Distribution)</h6></th>
									</tr>
										</thead>
										<tbody>
									<tr>
										<td rowspan="2"><p
											>Item</p></td>
										<td rowspan="2"><p
											>Theory Component Marks</p></td>
										<td rowspan="1" colspan="4" ><p
											>Practical Component Marks</p></td>
									</tr>

									<tr>
										<td><p>Practical</p></td>
										<td><p>Viva</p></td>
										<td><p>Elective</p></td>
										<td><p>IA</p></td>
									</tr>

									<tr>
										<td><p>Paper I</p></td>
										<td><p 
											id="theory_comp_marks1"></p></td>
										<td rowspan="2"><p
											id="practical"></p></td>
										<td rowspan="2"><p
											id="viva"></p></td>
										<td rowspan="2"><p
											id="elective_mark"></p></td>
										<td rowspan="2"><p
											 id="ia_mark"></p></td>
									</tr>

									<tr>
										<td><p>Paper II</p></td>
										<td ><p
											id="theory_comp_marks2"></p></td>
									</tr>

									<tr>
										<td><p >Sub Total</p></td>
										<td><p
											id="sub_total"></p></td>
										<td colspan="4"><p
										id="pract_all_mark"></p></td>
									</tr>

									<tr>
										<td><p>Total Marks</p></td>
										<td colspan="5"><p
											id="total_marks"></p></td>
									</tr>

									</tbody>
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
		$("#teaching_hours_summary_report").hide();
		$("#Exam_teaching_hours_summary_report").hide();
	});

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
		// 	document.getElementById('btn-view').onclick = function() {

		// 	};

		// 	document.getElementById('btn-view').onclick = function() {
		// 		viewSummaryTH2();
		// 	};
	});

	function viewSummaryTH1() {
		var course_id = $("#course_id").val();
		$.post('getViewSummaryTH_data?' + key + "=" + value, {
			course_id : course_id
		}).done(
				function(j) {
					var total_lh = 0;
					var total_nlh = 0;
					var tot_lh_nlh = 0;
					var tot_hou_nlh = 0;
					var num_teaching_hours = 0;

					for (var i = 0; i < j.length; i++) {
						$("#system_name1").text(j[i].system_name);
						$("#system_name2").text(j[i].system_name);
						if (j[i].type_of_hours == "LH") {
							if (j[i].paper == "PAPER I") {
								$("#no_of_hours1").text(
										j[i].no_of_hours + " Hours");
								total_lh = total_lh + j[i].no_of_hours;
							}
							if (j[i].paper == "PAPER II") {
								$("#no_of_hours2").text(
										j[i].no_of_hours + " Hours");
								total_lh = total_lh + j[i].no_of_hours;
							}
						}
						if (j[i].type_of_hours == "NLH") {
							if (j[i].type_of_teaching == "THEORY") {
								if (j[i].paper == "PAPER I") {
									$("#no_of_hours3").text(
											j[i].no_of_hours + " Hours");
									total_nlh = total_nlh + j[i].no_of_hours;
								}
								if (j[i].paper == "PAPER II") {
									$("#no_of_hours4").text(
											j[i].no_of_hours + " Hours");
									total_nlh = total_nlh + j[i].no_of_hours;
								}
							}
							if (j[i].type_of_teaching == "PRACTICAL") {
								$("#no_of_hours5").text(
										j[i].no_of_hours + " Hours");
								tot_hou_nlh = tot_hou_nlh + j[i].no_of_hours;
							}
						}
					}

					tot_hou_nlh = parseInt(tot_hou_nlh) + parseInt(total_nlh);
					num_teaching_hours = parseInt(tot_hou_nlh)
							+ parseInt(total_lh);

					$("#total_lh").text(total_lh + " Hours");
					$("#total_nlh").text(total_nlh + " Hours");
					$("#hou_tot_lh").text(total_lh + " Hours(LH)");
					$("#tot_lh_nlh").text(tot_lh_nlh);
					$("#tot_hou_nlh").text(tot_hou_nlh + " Hours(LH)");
					$("#num_teaching_hours").text(num_teaching_hours);
				});

		$("#teaching_hours_summary_report").show();
	}

	function viewSummaryTH2() {
		var course_id = $("#course_id").val();
		$.post('getViewExami_SummaryTH_data?' + key + "=" + value, {
			course_id : course_id
		}).done(function(j) {
			var sub_total = 0;
			var total_marks = 0;
			var pract_all_mark = 0;

			for (var i = 0; i < j.length; i++) {

				if (j[i].paper == "PAPER I") {
					$("#theory_comp_marks1").text(j[i].theory_comp_marks);
					sub_total = sub_total + j[i].theory_comp_marks;
					total_marks = total_marks + j[i].theory_comp_marks;
				}
				if (j[i].paper == "PAPER II") {
					$("#theory_comp_marks2").text(j[i].theory_comp_marks);
					sub_total = sub_total + j[i].theory_comp_marks;
					total_marks = total_marks + j[i].theory_comp_marks;

				}
				if (j[i].paper == "PAPER I" || j[i].paper == "PAPER II") {
					$("#practical").text(j[i].practical_marks);
				}

				pract_all_mark = pract_all_mark + j[i].practical_marks;
				$("#viva").text(j[i].viva_marks);
				pract_all_mark = pract_all_mark + j[i].viva_marks;
				$("#elective_mark").text(j[i].elective_marks);
				pract_all_mark = pract_all_mark + j[i].elective_marks;
				$("#ia_mark").text(j[i].ia_marks);
				pract_all_mark = pract_all_mark + j[i].ia_marks;
			}

			total_marks = parseInt(total_marks) + parseInt(sub_total);
			// 		pract_all_mark = parseInt(practical1)+parseInt(viva1)+parseInt(elective_mark1)+parseInt(ia_mark1);
			$("#sub_total").text(sub_total);
			$("#total_marks").text(total_marks);
			$("#pract_all_mark").text(pract_all_mark);

		});

		$("#Exam_teaching_hours_summary_report").show();
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
		viewSummaryTH1();
		viewSummaryTH2();
		return true;
	}
</script>
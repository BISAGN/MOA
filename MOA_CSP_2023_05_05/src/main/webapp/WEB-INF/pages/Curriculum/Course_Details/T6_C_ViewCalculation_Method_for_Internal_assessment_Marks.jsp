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
							Assessment Marks (6-C)
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
									Calculation Method for Internal Assessment Marks (6-C)</li>
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
								<h6 class="mb-25">View Calculation Method for Internal
									Assessment Marks (6-C)</h6>
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
											<li><a href="View_t6c_intern_assem_marks_Url"
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
												<th colspan="9">
													<h6>6 C - Calculation Method for Internal assessment
														Marks (30 Marks)</h6>
												</th>


												<!-- 										<td colspan="9"><span><label class="bold"> -->
												<!-- 													<br> -->
												<!-- 												<h4>6 C - Calculation Method for Internal assessment -->
												<!-- 														Marks (30 Marks)</h4> -->
												<!-- 											</label></span></td> -->
											</tr>

											<tr>
												<th rowspan="3"><h6>TERM</h6></th>
												<th colspan="5"><h6>PERIODICAL ASSESSMENT*</h6></th>
												<th rowspan="1"><h6>TERM TEST**</h6></th>
												<th colspan="2"><h6>TERM ASSESSMENT</h6></th>
											</tr>

											<tr>
												<th><p>A</p></th>
												<th><p>B</p></th>
												<th><p>C</p></th>
												<th><p>D</p></th>
												<th><p>E</p></th>
												<th><p>F</p></th>
												<th><p>G</p></th>
												<th><p>H</p></th>
											</tr>

											<tr>
												<th><p>1(15 Marks)</p></th>
												<th><p>2(15 Marks)</p></th>
												<th><p>3(15 Marks)</p></th>
												<th><p>Average(A+B+C/3)</p></th>
												<th><p>Converted to 30 Marks (D/15*30)</p></th>
												<th><p>Term Test (Marks converted to 30)</p></th>
												<th><p>Sub Total_/60 Marks</p></th>
												<th><p>Term Assessment(.../30)</p></th>
											</tr>

											<tr>
												<th><p>FIRST</p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p>E+F</p></th>
												<th><p>(E+F)/2</p></th>
											</tr>

											<tr>
												<th><p>SECOND</p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p>E+F</p></th>
												<th><p>(E+F)/2</p></th>
											</tr>
											<tr>
												<th><p>THIRD</p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p></p></th>
												<th><p>NIL</p></th>
												<th><p></p></th>
												<th><p>E</p></th>
											</tr>

											<tr>
												<th colspan="1"><p>Final IA</p></th>
												<th colspan="8"><p>Average of Three Term Assessment
														Marks as Shown in ' H ' Column</p></th>
											</tr>
											<tr>
												<th colspan="1"><p></p></th>
												<th colspan="8"><p>Maximum Marks in Parentheses
														*Select an Evaluation Method which is appropriate for the
														objectives of Topics from the Table 6 D for Periodic
														assessment. Conduct 15 marks assessment and enter marks in
														A, B, and C. ** Conduct Theory (100 Marks) [MCQ (20*1
														Marks), SAQ (8*5), LAQ (4*10)] and Practical (100 Marks)
														Then convert to 30 marks</p></th>
											</tr>


										</thead>
										<tbody></tbody>
									</table>

									<div class="custom-multi-table">
										<div
											class="table-wrapper table-responsive custom-table custom-table-v2">
											<table class="table table-striped" id="pop2">
												<thead>
													<tr>
														<td colspan="8">
															<h6>6 C - Calculation Method for Internal assessment
																Marks (20 Marks)</h6>
														</td>
													</tr>
													<tr>
														<th rowspan="3"><h6>TERM</h6></th>
														<th colspan="4"><h6>PERIODICAL ASSESSMENT*</h6></th>
														<th rowspan="1"><h6>TERM TEST**</h6></th>
														<th colspan="2"><h6>TERM ASSESSMENT</h6></th>
													</tr>

													<tr>
														<th><p>A</p></th>
														<th><p>B</p></th>
														<th><p>C</p></th>
														<th><p>D</p></th>
														<th><p>E</p></th>
														<th><p>F</p></th>
														<th><p>G</p></th>
													</tr>

													<tr>
														<th><p>1(20)</p></th>
														<th><p>2(20)</p></th>
														<th><p>3(20)</p></th>
														<th><p>Average(A+B+C/20)</p></th>
														<th><p>Term Test (MCQ+SAQ+LAQMarks and
																Practical(Converted to 20))</p></th>
														<th><p>Sub Total</p></th>
														<th><p>Term Assessment</p></th>
													</tr>

													<tr>
														<th><p>FIRST</p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p>D+E</p></th>
														<th><p>D+E/2</p></th>
													</tr>

													<tr>
														<th><p>SECOND</p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p>D+E</p></th>
														<th><p>D+E/2</p></th>
													</tr>
													<tr>
														<th><p>THIRD</p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p>NIL</p></th>
														<th><p>D</p></th>
														<th><p>D</p></th>
													</tr>

													<tr>
														<th colspan="1"><p>Final IA</p></th>
														<th colspan="8"><p>Average of Three Term
																Assessment Marks as Shown in ' G ' Column</p></th>
													</tr>
												</thead>
												<tbody></tbody>
											</table>
										</div>
									</div>


									<div class="custom-multi-table">
										<div
											class="table-wrapper table-responsive custom-table custom-table-v2">
											<table class="table table-striped" id="pop3">
												<thead>
													<tr>
														<td colspan="9">
															<h6>6 C - Calculation Method for Internal assessment
																Marks (15 Marks)</h6>
														</td>
													</tr>
													<tr>
														<th rowspan="3"><h6>TERM</h6></th>
														<th colspan="5"><h6>PERIODICAL ASSESSMENT*</h6></th>
														<th rowspan="1"><h6>TERM TEST**</h6></th>
														<th colspan="2"><h6>TERM ASSESSMENT</h6></th>
													</tr>

													<tr>
														<th><p>A</p></th>
														<th><p>B</p></th>
														<th><p>C</p></th>
														<th><p>D</p></th>
														<th><p>E</p></th>
														<th><p>F</p></th>
														<th><p>G</p></th>
														<th><p>H</p></th>
													</tr>

													<tr>
														<th><p>1(15 Marks)</p></th>
														<th><p>2(15 Marks)</p></th>
														<th><p>3(15 Marks)</p></th>
														<th><p>Average(A+B+C/3)</p></th>
														<th><p>Converted to 30 Marks (D/15*15)</p></th>
														<th><p>Term Test (Marks converted to 15)(15Marks)</p></th>
														<th><p>Sub Total_/30 Marks</p></th>
														<th><p>Term Assessment(.../15)</p></th>
													</tr>

													<tr>
														<th><p>FIRST</p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p>E+F</p></th>
														<th><p>(E+F)/2</p></th>
													</tr>

													<tr>
														<th><p>SECOND</p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p>E+F</p></th>
														<th><p>(E+F)/2</p></th>
													</tr>
													<tr>
														<th><p>THIRD</p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p>NIL</p></th>
														<th><p></p></th>
														<th><p>E</p></th>
													</tr>

													<tr>
														<th colspan="1"><p>Final IA</p></th>
														<th colspan="8"><p>Average of Three Term
																Assessment Marks as Shown in ' H ' Column</p></th>
													</tr>
													<tr>
														<th colspan="1"><p></p></th>
														<th colspan="8"><p>Maximum Marks in Parentheses
																*Select an Evaluation Method which is appropriate for
																the objectives of Topics from the Table 6 D for Periodic
																assessment. Conduct 15 marks assessment and enter marks
																in A, B, and C. ** Conduct Theory (100 Marks) [MCQ (20*1
																Marks), SAQ (8*5), LAQ (4*10)] and Practical (100 Marks)
																Then convert to 15 marks</p></th>
													</tr>
												</thead>
											<tbody></tbody>
											</table>
										</div>
									</div>

									<div class="custom-multi-table">
										<div class="table-wrapper table-responsive custom-table custom-table-v2">
											<table class="table table-striped" id="pop4">
												<thead>
													<tr>
														<td colspan="9">
															<h6>6 C - Calculation Method for Internal assessment
																Marks (40 Marks)</h6>
														</td>
													</tr>
													<tr>
														<th rowspan="3"><h6>TERM</h6></th>
														<th colspan="5"><h6>PERIODICAL ASSESSMENT*</h6></th>
														<th rowspan="1"><h6>TERM TEST**</h6></th>
														<th colspan="2"><h6>TERM ASSESSMENT</h6></th>
													</tr>

													<tr>
														<th><p>A</p></th>
														<th><p>B</p></th>
														<th><p>C</p></th>
														<th><p>D</p></th>
														<th><p>E</p></th>
														<th><p>F</p></th>
														<th><p>G</p></th>
														<th><p>H</p></th>
													</tr>

													<tr>
														<th><p>1(15 Marks)</p></th>
														<th><p>2(15 Marks)</p></th>
														<th><p>3(15 Marks)</p></th>
														<th><p>Average(A+B+C/3)</p></th>
														<th><p>Converted to 40 Marks (D/20*40)</p></th>
														<th><p>Term Test (Marks converted to 40)</p></th>
														<th><p>Sub Total_/80 Marks</p></th>
														<th><p>Term Assessment(.../40)</p></th>
													</tr>

													<tr>
														<th><p>FIRST</p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p>E+F</p></th>
														<th><p>(E+F)/2</p></th>
													</tr>

													<tr>
														<th><p>SECOND</p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p>E+F</p></th>
														<th><p>(E+F)/2</p></th>
													</tr>
													<tr>
														<th><p>THIRD</p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p></p></th>
														<th><p>NIL</p></th>
														<th><p></p></th>
														<th><p>E</p></th>
													</tr>

													<tr>
														<th colspan="1"><p>Final IA</p></th>
														<th colspan="8"><p>Average of Three Term
																Assessment Marks as Shown in ' H ' Column</p></th>
													</tr>
													<tr>
														<th colspan="1"><p></p></th>
														<th colspan="8"><p>Maximum Marks in Parentheses
																*Select an Evaluation Method which is appropriate for
																the objectives of Topics from the Table 6 D for Periodic
																assessment. Conduct 15 marks assessment and enter marks
																in A, B, and C. ** Conduct Theory (100 Marks) [MCQ (20*1
																Marks), SAQ (8*5), LAQ (4*10)] and Practical (100 Marks)
																Then convert to 40 marks</p></th>
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
		</div>
	</div>
</section>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$("#pop1").hide();
		$("#pop2").hide();
		$("#pop3").hide();
		$("#pop4").hide();
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
			getMarksbyCourseId();
			return View_Validation();

		};
		document.getElementById('degree_id').onchange = function() {
			getcourselistbysystemdegreeprof();
		};
		document.getElementById('professional_id').onchange = function() {
			getcourselistbysystemdegreeprof();
		};
		// 	document.getElementById('course_id').onclick = function() {
		// 		getMarksbyCourseId();
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
			alert("Please Select Professional.");
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

	function getMarksbyCourseId() {
		var course_id = $("#course_id").val();
		$.post('getMarksbyCourse?' + key + "=" + value, {
			course_id : course_id
		}).done(function(j) {

			if (j == "30") {
				$("#pop1").show();
				$("#pop").show();
			}
			if (j == "20") {
				$("#pop2").show();
				$("#pop").show();
			}
			if (j == "15") {
				$("#pop3").show();
				$("#pop").show();
			}
			if (j == "40") {
				$("#pop4").show();
				$("#pop").show();
			}
// 			$("select#course_id").html(options);
		});
	}
</script>

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
<!-- INTERNAL REMOVE START (NEED TO ADD IN PARTICULAR JSP)-->
			<link rel="stylesheet" href="assets/vendor/internal_css.css">
<!-- INTERNAL REMOVE END-->
<!-- </style> -->
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span>View Assessment
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">View
									Assessment</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
					<form:form name="course" id="search_exam_Action"
						action="search_exam_Action" method='POST'
						commandName="search_exam_CMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<h6 class="mb-25">View Assessment</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">Degree<span class="mandatory">*</span></label>

										<input type="hidden" value="${degree[0][0]}" id="degree_hid"
											name="degree_hid"
											class="form-control form-control-lg form-control-a effect-9"
											value="0"> <input type="text" value="${degree[0][1]}"
											id="degree_id" name="degree_id"
											class="form-control form-control-lg form-control-a effect-9"
											readonly="readonly">
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">Professional<span
											class="mandatory">*</span></label>
										<!-- 											<input type="hidden" -->
										<%-- 											value="${degree[0][2]}" id="professional_hid" --%>
										<!-- 											name="professional_hid" -->
										<!-- 											class="form-control form-control-lg form-control-a effect-9" -->
										<%-- 											value="0"> <input type="text" value="${degree[0][2]}" --%>
										<!-- 											id="professional_id" name="professional_id" -->
										<!-- 											class="form-control form-control-lg form-control-a effect-9"> -->
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="professional_id" id="professional_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getprofessionalList}"
													varStatus="num">
													<option value="${item.id}" name="${item.professional}">${item.professional}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">Subject<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="course_id" class="singleselect form-control form-control-lg" id="course_id">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<ul class="buttons-group mainbtn">

								<li><a href="View_Internal_assessment_Marks_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
								<li><a id="btn-view"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-view-alt"></i>View</a></li>

							</ul>
						</div>
					</form:form>
				</div>
			</div>
		</div>

		<div class="row" id="pop">
			<div class="col-12">
				<div class="card-style mb-30">
						
						<div class="table-wrapper table-responsive custom-table custom-table-v2">
							<table class="table table-striped" id="pop3">
								<thead>
									<tr>
										<th colspan="9"><h6>6 C - Calculation Method for Internal assessment Marks (15 Marks)</h6></th>
									</tr>
									<tr>
										<th rowspan="3"><h6>TERM</h6></th>
										<th colspan="5"><h6>PERIODICAL ASSESSMENT<span class="mandatory">*</span></h6></th>
										<th colspan="1"><h6>TERM TEST<span class="mandatory">**</span></h6></th>
										<th colspan="2"><h6>TERM ASSESSMENT</h6></th>
									</tr>
									<tr>
										<th><h6>A</h6></th>
										<th><h6>B</h6></th>
										<th><h6>C</h6></th>
										<th><h6>D</h6></th>
										<th><h6>E</h6></th>
										<th><h6>F</h6></th>
										<th><h6>G</h6></th>
										<th><h6>H</h6></th>
									</tr>
									<tr>
										<th><h6>1(15 Marks)</h6></th>
										<th><h6>2(15 Marks)</h6></th>
										<th><h6>3(15 Marks)</h6></th>
										<th><h6>Average(A+B+C/3)</h6></th>
										<th><h6>Converted To 15 Marks (D/15*15)</h6></th>
										<th><h6>Term Test (Marks Converted To 15)(15Marks)</h6></th>
										<th><h6>Sub Total_/30 Marks</h6></th>
										<th><h6>Term Assessment(.../15)</h6></th>
									</tr>
									<!-- end table header row-->
								</thead>
								<tbody>
									<tr>
										<td><p>FIRST</p></td>
										<td><p id="marks_15_fa"></p></td>
										<td><p id="marks_15_fb"></p></td>
										<td><p id="marks_15_fc"></p></td>
										<td><p id="marks_15_fd"></p></td>
										<td><p id="marks_15_fe"></p></td>
										<td><p id="marks_15_ff"></p></td>
										<td><p id="marks_15_fg"></p></td>
										<td><p id="marks_15_fh"></p></td>
									</tr>
	
									<tr>
										<td><p>SECOND</p></td>
										<td><p id="marks_15_sa"></p></td>
										<td><p id="marks_15_sb"></p></td>
										<td><p id="marks_15_sc"></p></td>
										<td><p id="marks_15_sd"></p></td>
										<td><p id="marks_15_se"></p></td>
										<td><p id="marks_15_sf"></p></td>
										<td><p id="marks_15_sg"></p></td>
										<td><p id="marks_15_sh"></p></td>
									</tr>
									
									<tr>
										<td><p>THIRD</p></td>
										<td><p id="marks_15_ta"></p></td>
										<td><p id="marks_15_tb"></p></td>
										<td><p id="marks_15_tc"></p></td>
										<td><p id="marks_15_td"></p></td>
										<td><p id="marks_15_te"></p></td>
										<td><p id="marks_15_tf"></p></td>
										<td><p id="marks_15_tg"></p></td>
										<td><p id="marks_15_th"></p></td>
									</tr>

								<tr>
								
									<td><p>Final IA</p></td>
									<td colspan="8"><p>Average of Three Term Assessment Marks as Shown in ' H ' Column</p></td>
								</tr>
								<tr>
								
									<td><p></p></td>
									<td colspan="8"><p>Maximum Marks in Parentheses <br> <span class="mandatory">*</span>Select
											an Evaluation Method which is appropriate for the objectives
											of Topics from the Table 6 D for Periodic assessment. Conduct
											15 marks assessment and enter marks in A, B, and C. <br><span class="mandatory">**</span>
											Conduct Theory (100 Marks) [MCQ (20*1 Marks), SAQ (8*5), LAQ
											(4*10)] and Practical (100 Marks) Then convert to 15 marks</p></td>
								</tr>
								</tbody>
							</table>
							<!-- end table -->
						</div>
						
				</div>
				<!-- 						end card -->
			</div>
			<!-- 				end col -->
		</div>


		
		
		



	</div>
</section>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$.ajaxSetup({
			async : false
		});
		
		var stu_prof = '${degree[0][3]}';
		
		if(stu_prof == "1"){
			stu_prof = "15";
		}
		if(stu_prof == "2"){
			stu_prof = "16";
		}
		if(stu_prof == "3"){
			stu_prof = "17";
		}
		
		$("select#degree_id").attr("disabled", true); 
		$("#professional_id").val(stu_prof);
		getcourselistby_professional();

	});

	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('professional_id').onchange = function() {
			getcourselistby_professional();
		};
		
		document.getElementById('btn-view').onclick = function() {
			View_Validation();
			getMarksbyCourseId();
		};
	});
	
	function getcourselistby_professional() {
		var degree_id = $("#degree_hid").val();
		var professional_id = $("#professional_id").val();
		$
				.post('getCourseList_for_Curri?' + key + "=" + value, {
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
	
	function View_Validation() {
		if ($("#professional_id").val().trim() == "0") {
			alert("Please Select Professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#course_id").val().trim() == "0") {
			alert("Please Select Subject.");
			$("select#course_id").focus();
			return false;
		}
		viewInternal_ass_marks();
		return true;
		
	}
	
	
	function viewInternal_ass_marks() {

		var IASCHEME = "";
		var course_id = $("#course_id").val();
		var professional_id = $("#professional_id").val();
		
		if(professional_id == "1"){
			professional_id = "15";
		}
		if(professional_id == "2"){
			professional_id = "16";
		}
		if(professional_id == "3"){
			professional_id = "17";
		}

		$.post('getMarksbyCourse?' + key + "=" + value, {
			course_id : course_id
		}).done(function(j) {


		});

		
		


	}



</script>

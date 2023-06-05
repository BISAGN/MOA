<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/common/commonmethod.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd">Lesson Plan</span>
						</h2>
						
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Lesson
									Plan</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12 col-lg-12">
					<form:form name="lecturer" id="lecturer" action="Lecturer_Action"
						method="post" modelAttribute="LecturerCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Professional<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="professional" id="professional"
													class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getProfessionalList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.professional}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Name of Course<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="course_name" id="course_name"
													class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<%-- 											<c:forEach var="item" items="${getCourceList}" varStatus="num"> --%>
													<%-- 												<option value="${item.id}" name="${item.id}">${item.course_name}</option>  --%>
													<%-- 											</c:forEach> --%>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Topic<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="topic" id="topic"
													class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>

												</select>
											</div>
										</div>

										<!-- end select -->
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Learning Objectives<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="learning_objective" id="learning_objective"
													class="form-control form-control-lg">
													<option value="0">---Select---</option>
													<%-- 											<c:forEach var="item" items="${getLearningList}" varStatus="num"> --%>
													<%-- 												<option value="${item.id}" name="${item.id}">${item.b3_learning_obj}</option>  --%>
													<%-- 											</c:forEach> --%>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">

											<label>Lecture Hours Duration<span class="mandatory">*</span></label>
											<input type="text" id="lecture_hours" name="lecture_hours"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100"
												placeholder="Lecture Hours" readonly />

										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">

											<label>Non Lecture Hours Duration<span
												class="mandatory">*</span></label> <input type="text"
												id="non_lecture_hours" name="non_lecture_hours"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100"
												placeholder="Non Lecture Hours" readonly />

										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Instructional Method(Circle as
												appropriate)<span class="mandatory">*</span>
											</label>
											<div class="select-position">
												<select name="instructional_method"
													id="instructional_method"
													class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getInstructionalList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.instructional_method_name}</option>
													</c:forEach>
												</select>

											</div>
										</div>
										<div class="input-style-1">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Academic Year <span class="mandatory">*</span></label>
											<input type="year" id="academic_year" name="academic_year"
												name="academic_year" class="autocomplete xt-transupp"
												autocomplete="off" maxlength="4"
												placeholder="Academic Year " />

										</div>
										<!-- end input -->
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-2">
											<label for="username">Date<span class="mandatory">*</span></label>
											<input type="text" name="fdate" id="fdate" maxlength="10"
												class="form-control-sm form-control effect-9 "
												aria-required="true" autocomplete="off" value="DD/MM/YYYY">
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">

											<label for="time">Lecture Hours Taken<span
												class="mandatory">*</span></label> <input type="text" id="time"
												name="time" class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100"
												placeholder="Lecture Hours" readonly />

										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">

											<label for="time">Previous Lecture Hours<span
												class="mandatory">*</span></label> <input type="text" id="prev_time"
												name="prev_time"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100"
												placeholder="Previous Lecture Hours" readonly />

										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">

											<label for="time">Lecture Hours Remaining<span
												class="mandatory">*</span></label> <input type="text" id="time_rem"
												name="time_rem"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100"
												placeholder="Lecture Hours Remaining" readonly />

										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Activity Description<span class="mandatory">*</span></label>
											<input type="textarea" id="activity_description"
												name="activity_description"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="1000"
												placeholder="Activity Description" />

										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Resources/ A-V Aids<span class="mandatory">*</span></label>
											<input type="textarea" id="resources" name="resources"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="1000" placeholder="Resources" />

										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Assessment Method<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="assessment_method" id="assessment_method"
													class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getAssessmentList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.assessment_type}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li id="btn-save"><input 
												class="main-btn info-btn btn-hover btnsave" type="submit"
												 value="Save" /></li>
<!-- 												 onclick="return Validation();" -->
											<li><a href="LecturerUrl"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
											<li id="btn-view"><a href="Lecturer_ViewUrl" 
												class="main-btn dark-btn btn-hover btn-iconic-icon btnview"
												type="button"><i class="lni lni-eye"></i>View Plan</a></li>
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


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		$.ajaxSetup({
			async : false
		});

		mockjax1('search_plan');
		table = dataTable('search_plan');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		datepicketDate('fdate');
		// 	getInstitute();

	});

	// function setTimeLoadForTable(){

	//--------------------CSP-------------------------------//
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

		document.getElementById('professional').onchange = function() {
			getCourseList();
		};

		document.getElementById('course_name').onchange = function() {
			getTopic();
		};

		document.getElementById('topic').onchange = function() {
			getLearning();
		};

		document.getElementById('learning_objective').onchange = function() {
			getL_N_Hours();
		};

		document.getElementById('fdate').onchange = function() {
			getPrev_Lec_HR();
			clickrecall(this, 'DD/MM/YYYY');
		};

		//******Date CSP********//

		document.getElementById('fdate').onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};

		document.getElementById('fdate').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};

		document.getElementById('fdate').onblur = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate_BackDate(this.value, this);
		};

		document.getElementById('fdate').onfocus = function() {
			this.style.color = '#000000';
		};

	});
	// 	}

	function getL_N_Hours() {

		var learning_objective = $("#learning_objective").val();
		$.post("getL_N_Hours?" + key + "=" + value, {
			learning_objective : learning_objective
		}, function(j) {

			$("#lecture_hours").val(j[0][0]);
			$("#non_lecture_hours").val(j[0][1]);

		});
	}

	function getL_N_Hours() {

		var learning_objective = $("#learning_objective").val();

		$.post("getL_N_Hours?" + key + "=" + value, {

			learning_objective : learning_objective

		}, function(j) {
			if (j.length == 0) {
				$("#lecture_hours").val(0);
				$("#non_lecture_hours").val(0)
			} else {
				$("#lecture_hours").val(j[0][0]);
				$("#non_lecture_hours").val(j[0][1]);
			}
		});
	}

	function getPrev_Lec_HR() {

		var professional = $("#professional").val();
		var course_name = $("#course_name").val();
		var topic = $("#topic").val();
		var learning_objective = $("#learning_objective").val();

		$.post("getPrev_Lec_HR?" + key + "=" + value, {
			professional : professional,
			course_name : course_name,
			topic : topic,
			learning_objective : learning_objective

		}, function(j) {
// 				alert(j[0][0]);
			getDate(j[0][0]);

		});
	}

	function getDate(pre_hr) {;
		var fdate = $("#fdate").val();
		$.post("gettime_duration?" + key + "=" + value, {
			fdate : fdate
		}, function(j) {

			if (pre_hr == null || pre_hr == "") 
			{
				pre_hr = "0";
			}
			
			if (j[0][0] == null)
			{
				j[0][0]= "0";
			}
			
			$("#time").val(parseInt(j[0][0]));
			
			$("#prev_time").val(parseInt(pre_hr));
			
			var tot = 0;
			
			var lh = $("#lecture_hours").val();
			
			var Nlh = $("#non_lecture_hours").val();
			
			if (lh != null || lh != "" && Nlh != null || Nlh != "") {

				tot = parseInt(lh) + parseInt(Nlh);
			}
			var rem = tot - parseInt(j[0][0]) - parseInt(pre_hr);
			
			if (rem != null || rem != "") {

				rem = 0;
			}
			
			$("#time_rem").val(rem);

		});
	}
	
// 	function getDate(pre_hr) {
// 		var fdate = $("#fdate").val();
// 		$.post("gettime_duration?" + key + "=" + value, {
// 			fdate : fdate
// 		}, function(j) {
			
// 			console.log(j);
// 			debugger;
			
// 			var jj = j[0][0];
// 			var tot = 0;
			
// 			if (pre_hr != null && pre_hr != "" ) {
			
// 			$("#prev_time").val(parseInt(pre_hr));
// 			}else {
// 				$("#prev_time").val("0");
// 			}
// 			if (jj != null && jj != "" && jj != "") {
// 				$("#time").val(parseInt(jj));
				
// 			var rem = tot - parseInt(jj) - parseInt(pre_hr);
// 			$("#time_rem").val(rem);
// 			}else {
				
// 				$("#time").val("0");
// 				$("#time_rem").val("0");
// 			}
// 			var lh = $("#lecture_hours").val();
// 			var Nlh = $("#non_lecture_hours").val();
// 			if (lh != null && lh != "" && Nlh != null && Nlh != "") {
// 				tot = parseInt(lh) + parseInt(Nlh);
// 			}
// 		});
// 	}

	function getTopic() {
		$("#topic").empty();
		var course_id = $("#course_name").val();

		$
				.post(
						"getTopic_name?" + key + "=" + value,
						{

							course_id : course_id

						},
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#topic").html(options);

						});

		$("#learning_objective").val("0");

	}

	function getLearning() {
		$("#learning_objective").empty();

		var topic_id = $("#topic").val();

		$
				.post(
						"getLearning_Objective?" + key + "=" + value,
						{

							topic_id : topic_id

						},
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#learning_objective").html(options);
							$("#lecture_hours").val("");
							$("#non_lecture_hours").val("");

						});

	}
	function getCourseList() {
		$("select#course_name").empty();

		var professional_id = $("#professional").val();
		var system_id = '${system[0][1]}';

		$
				.post(
						"getCourseList_for_Curri?" + key + "=" + value,
						{

							professional_id : professional_id,
							system_id : system_id

						},
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_name").html(options);

						});

	}

	function getAprrovedDegree(id) {

		//		var selval = $("#system_hid").val();
		var selval = id;
		$
				.post(
						"getdegrebysysidlist?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {
							if (j.length > 0) {
								var options = '<option value="' + "0" + '">'
										+ "--Select--" + '</option>';
								for (var i = 0; i < j.length; i++) {
									options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
											+ j[i][1] + '</option>';
								}
								$("select#degree").html(options);
							}
						});
	}

	function Validation() {

		if ($("select#professional").val() == "0") {
			alert("Please Select Professional.");
			$("select#professional").focus();
			return false;
		}
		if ($("select#course_name").val() == "0") {
			alert("Please Select Course Name.");
			$("select#course_name").focus();
			return false;
		}
		if ($("select#topic").val() == "0") {
			alert("Please Select Topic.");
			$("select#topic").focus();
			return false;
		}
		if ($("select#learning_objective").val() == "0") {
			alert("Please Select Learning Objective.");
			$("select#learning_objective").focus();
			return false;
		}
		if ($("select#instructional_method").val() == "0") {
			alert("Please Select Instructional Method");
			$("select#instructional_method").focus();
			return false;
		}
		if ($("#academic_year").val() == "") {
			alert("Please Enter Academic Year.");
			$("input#academic_year").focus();
			return false;
		}

		if ($("#fdate").val() == "DD/MM/YYYY" || $("#fdate").val() == "") {
			alert("Please Enter Date.");
			$("#fdate").focus();
			return false;
		}

		if ($("#activity_description").val() == "") {
			alert("Please Enter Activity Description.");
			$("input#activity_description").focus();
			return false;
		}
		if ($("#resources").val() == "") {
			alert("Please Enter Resources.");
			$("input#resources").focus();
			return false;
		}
		if ($("select#assessment_method").val() == "0") {
			alert("Please Select Assessment Method.");
			$("select#assessment_method").focus();
			return false;
		}
		return true;
	}
</script>




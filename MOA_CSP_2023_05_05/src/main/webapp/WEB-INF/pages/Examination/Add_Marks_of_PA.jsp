<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- <script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>	 -->
<script src="js/common/multicheck.js" type="text/javascript"></script>
<!-- INTERNAL REMOVE START (NEED TO ADD IN PARTICULAR JSP)-->
<!-- 			<link rel="stylesheet" href="assets/vendor/internal_css.css"> -->
<!-- INTERNAL REMOVE END-->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span> Add Marks of Examination
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Add
									Marks of Examination</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="add_marks_of_pa" id="Add_Marks_of_PAAction"
						action="Add_Marks_of_PAAction" method='POST'
						commandName="add_marks_of_pa_CMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Add Marks Of Examination</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
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
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
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
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Term<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="term_id" id="term_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${geti3_termList}"
														varStatus="num">
														<option value="${item.id}" name="${item.term}">${item.term}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Exam Serial<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="exam_serial" id="exam_serial">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getExam_SerialList}"
														varStatus="num">
														<option value="${item.id}" name="${item.exam_serial}">${item.exam_serial}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Exam Type<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="exam_type_id" id="exam_type_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getExam_Type}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input"> Year<span class="mandatory">*</span></label>
											<div class="input-style-2">
												<input type="month" id="mon_year" name="mon_year"
													class="form-control-sm form-control effect-9 hasDatepicker">
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-with-selection">
											<div class="input-style-1 mb-0">
												<label id="topic_name"> Subject <span
													class="mandatory">*</span></label> <input type="text"
													id="search_data" autocomplete="off"
													placeholder="Search Subject">
											</div>
											<div class="col-two" id="checkboxes" class="chklist"></div>
										</div>
										<input type="hidden" id="no_of_student" name="no_of_student"
											class="form-control autocomplete" value="0"> <input
											type="hidden" id="no_of_course" name="no_of_course"
											class="form-control autocomplete" value="0"> <input
											type="hidden" name="course_check_list" id="course_check_list" />
										<input type="hidden" id="student_check_list"
											name="student_check_list" />
									</div>
									<input type="hidden" name="old_course_topic"
										id="old_course_topic" /> <input type="hidden"
										name="new_course_topic" id="new_course_topic" /> <input
										type="hidden" name="add_course_topic" id="add_course_topic" />
									<input type="hidden" name="remove_course_topic"
										id="remove_course_topic" />

								</div>

							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><input value="Save" id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="submit" /></li>
											<li><a href="Add_Marks_of_PA_url"
												class="main-btn dark-btn btn-hover btnreset" type="button"
												value="Reset">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>

						<div id="divofTable">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="card-style mb-30" id="over_sc">

										<div
											class="table-wrapper table-responsive custom-table custom-table-v2">
											<table class="table table-striped" id="popT1">

												<!-- 									<div class="table-wrapper table-responsive custom-table custom-table-v2" -->
												<!-- 										id="container-table"> -->
												<!-- 										<table class="table table-striped" id="popT1"> -->
												<thead>
													<tr class="learncount middle-center" id="tr_of_thead">
														<th><h6>Sr No.</h6></th>
														<th><h6>Student Name</h6></th>
													</tr>
												</thead>
											</table>
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
	$(document).ready(
			function() {

				initiateChkFn('new_course_topic', 'old_course_topic',
						'add_course_topic', 'remove_course_topic', 'Subject',
						'topic_name');

				$("#divofTable").hide();
				if (window.location.href.includes("msg")) {
					var url = window.location.href.split("?msg")[0];
					window.location = url;
				}
				getDegreeListFromInstitute();
			});

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('exam_type_id').onchange = function() {
			getcourselistby_professional();
		};
		document.getElementById('professional_id').onchange = function() {
			getTerm_listByProf();
		};

		document.getElementById('search_data').onkeyup = function() {
			fnFilterChk(this.value);
		};
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
	});

	var noOfCourse = 0;

	function getcourselistby_professional() {
		$("#checkboxes").empty();
		$("table#popT1").empty();
		$("table#popT1")
				.append(
						'<thead>'
								+ '<tr class="learncount middle-center" id="tr_of_thead">'
								+ '<th><h6>Sr No.</h6></th>'
								+ '<th><h6>Student name</h6></th>' + '</tr>'
								+ '</thead>');

		noOfCourse = 0;
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();
		var check_list = "";
		var exam_type_id = $("#exam_type_id").val();

		$
				.post('getia_marks?' + key + "=" + value, {
					degree_id : degree_id,
					professional_id : professional_id
				})
				.done(
						function(data) {

							var options = '';
							for (var i = 0; i < data.length; i++) {
								check_list = $("#course_check_list").val();
								options += '<label for="one"  class="chklist"> <input class="customeCheck" type="checkbox" name="multisub"  id="multisub'
									+ data[i][0]
									+ '" value='
									+ data[i][0]+' />'
										+ data[i][1] + '</label>';

								check_list = check_list + data[i][0] + ",";
								$("#course_check_list").val(check_list);
							}
							$("div#checkboxes").append(options);
							var options1 = '';
							for (var i = 0; i < data.length; i++) {
								if (exam_type_id == "8") {
									options1 += '<th id="'+data[i][0]+'"><h6>'
											+ data[i][1]
											+ '<br><br>(Out of '
											+ data[i][2]
											+ ' Marks)</h6>'
											+ '<input type="hidden" name="ia_marks_hid'+data[i][0]+'" id="ia_marks_hid'+data[i][0]+'" value="'+data[i][2]+'"/>'
											+ '</th>';
									noOfCourse++;
								} else if (exam_type_id == "12") {
									options1 += '<th id="'+data[i][0]+'"><h6>'
											+ data[i][1]
											+ '<br><br>(Out of 100 Marks)</h6></th>';
									noOfCourse++;
								} else {
									options1 += '<th id="'+data[i][0]+'"><h6>'
											+ data[i][1] + '</h6></th>';
									noOfCourse++;
								}

							}
							$("tr#tr_of_thead").append(options1);
							for (var j = 0; j < data.length; j++) {
								$("th#" + data[j][0]).hide();
							}
							for (var i = 0; i < data.length; i++) {
								chkboxonclick(data[i][0]);
							}
							getpop(data);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
		$("#divofTable").show();
	}

	function chkboxonclick(value) {
		document.getElementById('multisub' + value).onclick = function() {
			chkXClick(value);
		};
	}

	function chkXClick(obj) {
		$("#divofTable").show();
		if ($('#multisub' + obj).is(":checked")) {
			$("th#" + obj).show();
			$(".table_col" + obj).show();
		} else {
			$("th#" + obj).hide();
			$(".table_col" + obj).hide();
		}
		chkClick();
	}

	function getpop(data) {
		debugger;
		var stuIds = "";
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();

		$.post("getstu_name?" + key + "=" + value, {
			degree_id : degree_id,
			professional_id : professional_id
		}, function(j) {

			$("#no_of_student").val(j.length);
			$("#no_of_course").val(noOfCourse);
			for (var i = 0; i < j.length; i++) {
				$("table#popT1").append(
						'<tr id="popT1R">' + '<td><p id="ser">'
								+ parseInt(i + 1) + '</p></td>'
								+ '<td><p id="name">' + j[i][0] + '</p></td>'
								+ setRowsOfTable(i, j[i][1]) + '</tr>');
				stuIds = stuIds + j[i][1] + ",";
			}
			$("#student_check_list").val(stuIds);

			var course_list = $("#course_check_list").val();
			course_list = course_list.substring(0, course_list.length - 1);
			const myArray = course_list.split(",");

			for (var i = 0; i < myArray.length; i++) {
				$(".table_col" + myArray[i]).hide();
			}
			$("#divofTable").hide();
			hsatmptoset();
		});
	}

	function setRowsOfTable(obj, stu_id) {
		debugger;
		var stringofDI = "";
		var course_list = $("#course_check_list").val();
		course_list = course_list.substring(0, course_list.length - 1);
		const myArray = course_list.split(",");
		var d = $("#no_of_course").val();
		for (var e = 0; e < d; e++) {

			stringofDI += ' <td class="table_col'+myArray[e]+'"><div class="select-style-1"><div class="select-position"><select name="attempt_id'+myArray[e]+'_'+stu_id+'" id="attempt_id'+myArray[e]+'_'+stu_id+'">'
					+ '<c:forEach var="item" items="${getAttemptList}" varStatus="num"><option value="${item.attempt}" name="${item.attempt}">${item.attempt}</option>'
					+ '</c:forEach></select></div>'
					+ '<div class="input-style-1"><input type="number"  id="marks'+myArray[e]+'_'+stu_id+'" name="marks'+myArray[e]+'_'+stu_id+'"  class="custom-d-none"'
		+'class="form-control"  autocomplete="off"  placeholder="Enter Marks"></div></div></td>'
					+ '<input type="hidden" id="student_name_id'+myArray[e]+'_'+stu_id+'" name="student_name_id'+myArray[e]+'_'+stu_id+'" value="'+stu_id+'" autocomplete="off" />';
		}
		return stringofDI;
	}

	function hsatmptoset() {
		var course_list = $("#course_check_list").val();
		course_list = course_list.substring(0, course_list.length - 1);
		const myArray1 = course_list.split(",");

		var student_check_list = $("#student_check_list").val();
		student_check_list = student_check_list.substring(0,
				student_check_list.length - 1);
		const myArray2 = student_check_list.split(",");

		for (var i = 0; i < myArray2.length; i++) {
			var a2 = myArray2[i];
			for (var m = 0; m < myArray1.length; m++) {
				var a1 = myArray1[m];
				document.getElementById('attempt_id' + a1 + '_' + a2).onchange = function() {
					var selval = this.id;
					selval = selval.split("d")[1];
					selval = selval.split("_");
					if (this.value == "NOT ATTEMPT") {
						$("#marks" + selval[0] + "_" + selval[1]).hide();
					} else {
						$("#marks" + selval[0] + "_" + selval[1]).show();
					}
				};
			}
		}
	}

	function hsatmptdhs(ob1, ob2) {
		var selval = $("#attempt_id" + ob1 + "_" + ob2).val();
		if (selval == "NOT ATTEMPT") {
			$("#marks" + ob1 + "_" + ob2).hide();
		} else {
			$("#marks" + ob1 + "_" + ob2).show();
		}
	}

	function Validation() {

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
		if ($("#term_id").val().trim() == "0") {
			alert("Please Select Term.");
			$("select#term_id").focus();
			return false;
		}
		if ($("#exam_serial").val().trim() == "0") {
			alert("Please Select Exam Serial.");
			$("select#exam_serial").focus();
			return false;
		}
		if ($("#exam_type_id").val().trim() == "0") {
			alert("Please Select Exam Type.");
			$("select#exam_type_id").focus();
			return false;
		}
		if ($("#mon_year").val().trim() == "") {
			alert("Please Enter Year.");
			$("input#mon_year").focus();
			return false;
		}
		if ($("#new_course_topic").val().trim() == "") {
			alert("Please Select Subject.");
			$("select#new_course_topic").focus();
			return false;
		}
		var courses = $("#new_course_topic").val();
		var students = $("#student_check_list").val();
		var course = courses.split(",");
		var student = students.split(",");
		for (var k = 0; k < course.length; k++) {
			for (var r = 0; r < student.length; r++) {
				var an = $("#attempt_id" + course[k] + "_" + student[r]).val();
				var mark = $("#marks" + course[k] + "_" + student[r]).val();
				var iamarks = $("#ia_marks_hid" + course[k]).val();
				if (an == "ATTEMPT") {
					if (mark == "") {
						alert("Please Enter Marks.");
						return false;
					}
					if (parseInt(mark) > parseInt(iamarks)) {
						alert("Please Enter Valid Marks.");
						return false;
					}
				}
			}
		}
		return true;
	}
	function getDegreeListFromInstitute() {
		$
				.post(
						"getDegreeListFromInstituteExam?" + key + "=" + value,
						{},
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
	function getTerm_listByProf() {
		// 	var system = $("#system_id").val();
		var degree = $("#degree_id").val();
		var professional = $("#professional_id").val();

		$
				.post('getTerm_listByProf_for_Curri?' + key + "=" + value, {
					// 				system : system,
					degree : degree,
					professional : professional
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#term_id").html(options);
						});
	}
</script>

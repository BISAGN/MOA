<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

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

<!-- Datepicker start -->
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- Datepicker End -->

<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd">Student Lecture Plan</span>
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Student
									Lecture Plan</li>
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
			<form:form name="lecturer_plan" id="lecturer_plan" method="post"
				modelAttribute="Student_LecturerCMD">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">


						<%-- 							<form:form name="lecturer_plan" id="lecturer_plan" method="post" --%>
						<%-- 								modelAttribute="Student_LecturerCMD"> --%>
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<div class="row">
									<div class="col-lg-4 col-md-4 col-sm-6 col-12">
										<div class="icon-card primary-shade">
											<div class="icon">
												<i class="lni lni-blackboard"></i>
											</div>
											<div class="content">
												<h4 class="counter-title">Total Lectures</h4>
												<h3 class="counter-count">${TotalLecture}</h3>
											</div>
										</div>
										<!-- End Icon Cart -->
									</div>

									<div class="col-lg-4 col-md-4 col-sm-6 col-12">
										<div class="icon-card success-shade">
											<div class="icon">
												<i class="lni lni-blackboard"></i>
											</div>
											<div class="content">
												<h4 class="counter-title">Attended Lectures</h4>
												<h3 class="counter-count">${TotalAttendedLecture}</h3>
											</div>
										</div>
										<!-- End Icon Cart -->
									</div>

									<div class="col-lg-4 col-md-4 col-sm-6 col-12">
										<div class="icon-card orange-shade">
											<div class="icon">
												<i class="lni lni-blackboard"></i>
											</div>
											<div class="content">
												<h4 class="counter-title">Not Attended Lectures</h4>
												<h3 class="counter-count">${TotalNotAttendedLecture}</h3>
											</div>
										</div>
										<!-- End Icon Cart -->
									</div>
								</div>
								<div class="row">
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<!-- Hidden Start -->
										<input type="hidden" id="datacounthId" name="datacounthId"
											value="0" autocomplete="off" />
										<!-- Hidden End -->
										<div class="input-style-1">
											<label>Professional<span class="mandatory">*</span></label> <input
												type="text" value="${system[0][0]}" id="professionallab"
												name="professionallab" class="form-control form-control-lg"
												readonly="readonly">
										</div>
										<div class="select-style-1 custom-d-none">

											<div class="select-position">
												<select name="professional" id="professional"
													class="singleselect form-control form-control-lg">
													<c:forEach var="item" items="${getProfessionalList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.professional}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-2">
											<label>Date<span class="mandatory">*</span></label> <input
												type="text" name="sdate" id="sdate" maxlength="10"
												class="form-control-sm form-control effect-9 "
												aria-required="true" autocomplete="off" value="DD/MM/YYYY">
										</div>
										<div class="input-style-1 mt-3">
											<!-- Hidden Start -->
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
											<!-- Hidden End -->
										</div>
									</div>
								</div>
							</div>
						</div>


					</div>

				</div>

				<section class="single-detail-block">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div id="lectdiv" class="custom-d-none">
								<div class="card-style mb-30">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="family_table" class="custom-d-none">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Start Time</h6></th>
													<th><h6>End Time</h6></th>
													<th><h6>Subject</h6></th>
													<th><h6>Faculty</h6></th>
													<th><h6>
															Topic<span class="mandatory">*</span>
														</h6></th>
													<th><h6>
															Sub-Topic<span class="mandatory">*</span>
														</h6></th>
													<th><h6>
															Learning Objective<span class="mandatory">*</span>
														</h6></th>
													<th><h6>
															Instructional Method<span class="mandatory">*</span>
														</h6></th>
													<th class="hide-action"><h6>
															Attended<span class="mandatory">*</span>
														</h6></th>
												</tr>
											</thead>
											<tbody id="family_sibtbody">

											</tbody>
										</table>
									</div>
									<div class="btn-bottom">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<ul class="buttons-group mainbtn">

													<li><input id="btn-save"
														class="main-btn info-btn btn-hover btnsave" type="button"
														value="Save" /></li>
													<li><a href="Student_Lecturer_PlanUrl"
														class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
												</ul>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</section>


			</form:form>
		</div>

	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(
			function() {

				$.ajaxSetup({
					async : false
				});

				mockjax1('search_plan');
				table = dataTable('search_plan');
				$('#btn-reload').on('click', function() {
					table.ajax.reload();
				});

				//         	professionallab

				if ('${Current_Prof[0][2]}' == '1') {
					$("#professional").val('15');
					$("#professionallab").val(
							$("#professional option:selected").text());
				} else if ('${Current_Prof[0][2]}' == '2') {
					$("#professional").val('16');
					$("#professionallab").val(
							$("#professional option:selected").text());
				} else if ('${Current_Prof[0][2]}' == '3') {
					$("#professional").val('17');
					$("#professionallab").val(
							$("#professional option:selected").text());
				}
				$("#professionallab").val(
						$("#professional option:selected").text());
				getcoursedtl();
				//         	 getTopic()
				datepicketDate('sdate');
			});

	// 		$("#lectdiv").hide();
	//--------------------CSP-------------------------------//

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('sdate').onchange = function() {
			selectLectureDetails();
			clickrecall(this, 'DD/MM/YYYY');
			getcoursedtl();

		};

		document.getElementById('btn-save').onclick = function() {
			return Validation();

		};

		//******Date CSP********//

		document.getElementById('sdate').onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};

		document.getElementById('sdate').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};

		document.getElementById('sdate').onblur = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate_BackDate(this.value, this);
		};

		document.getElementById('sdate').onfocus = function() {
			this.style.color = '#000000';
		};

	});

	// function setTimeLoadForTable(){

	// 	document.getElementById('btn-save').onclick = function() {
	// 		return Validation();
	// 	};

	// 	document.getElementById('professional').onkeypress = function() {
	// 		return onlyAlphabetsStringSpace(this, event);
	// 		getcoursedtl();
	// 	};

	// document.getElementById('course_name').onkeypress = function() {
	// 		return onlyAlphabetsStringSpace(this, event);
	// 		getFacultyDtl();
	// 	};
	// 	}

	// 		document.getElementById('sdate').onchange = function() {
	// 			selectLectureDetails(); 
	// 			getcoursedtl(); 
	// 		};

	var datacount = 0;

	function student() {

		var start_time = $("#start_time1").val();
		var end_time = $("#end_time1").val();
		var professional = $("#professional").val();
		// 	var course_name = $("#course_name").val();
		// 	alert(course_name);
		// 	var faculty = $("#faculty").val();
		// 	alert(faculty);
		// 	var topic = $("#topic").val();
		// 	alert(topic);
		// 	var learning_objective = $("#learning_objective").val();
		// 	alert(learning_objective);
		// 	var instructional_method = $("#instructional_method").val();
		// 	alert(course_name);
		var sdate = $("#sdate").val();
		var form = $("#lecturer_plan").serialize();
		$.post("getStudent_Details?" + key + "=" + value, form, function(j) {
			
			
			
			 if(j.err != undefined){
				 console.log(j.err);
				
				 
// 	      		 $("#hid_quali1").val(data);
	      		 alert(j.err);
	      		$("#"+j.field).focus(); 
	      		 
// 	      		$("#Migrated_Students_add"+ps).show(); 
// 		    	$("#Migrated_Students_remove"+ps).show(); 
	        }
			
			 else if (j.msg == 'Data Already Exist') {
				alert("Data Already Exist");
			} else {
				(j.msg == 'Data Saved Successfully.')
				alert("Data Saved Successfully.");

				window.location.href = "Search_Student_Lecturer_PlanUrl";
			}

		});
	}

	function data(search_plan) {
		jsondata = [];
		var table = $('#' + search_plan).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var professional = $("#professional").val();
		var start_time = $("#start_time").val();
		var end_time = $("#end_time").val();
		var course_name = $("#course_name").val();
		var faculty = $("#faculty").val();
		var topic = $("#topic").val();
		var learning_objective = $("#learning_objective").val();
		var instructional_method = $("#instructional_method").val();
		var sdate = $("#sdate").val();

		$.post("getFilter_Student_Lecturer_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			professional : professional,
			start_time : start_time,
			end_time : end_time,
			course_name : course_name,
			faculty : faculty,
			topic : topic,
			learning_objective : learning_objective,
			instructional_method : instructional_method,
			sdate : sdate

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].sr_no, j[i].professional, j[i].start_time,
						j[i].end_time, j[i].course_name, j[i].faculty,
						j[i].topic, j[i].learning_objective,
						j[i].instructional_method, j[i].sdate ]);
			}
		});
		$.post("getTotal_Student_Lecturer_dataCount?" + key + "=" + value, {
			Search : Search,
			professional : professional,
			start_time : start_time,
			end_time : end_time,
			course_name : course_name,
			faculty : faculty,
			topic : topic,
			learning_objective : learning_objective,
			instructional_method : instructional_method,
			sdate : sdate

		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	// 	function editData(id) {

	// 		$("#id3").val(id);
	// 		document.getElementById('updateForm').submit();
	// 	}

	// 	function deleteData(id) {
	// 		$("#id2").val(id);
	// 		document.getElementById('deleteForm').submit();
	// 		}	

	function Validation() {

		var datacounthId = $("#datacounthId").val();

		for (i = 1; i <= parseInt(datacounthId); i++) {

			if ($("#yes" + i).is(":checked")) {

				// 		if ($("#sdate").val().trim() =="" || $("#sdate").trim().equals("DD/MM/YYYY"))  {
				// 			alert("Please Enter Date");
				// 			$("input#sdate").focus();
				// 			return false;
				// 		}

				// 			if($("select#course_name"+i).val() == "0"){
				// 				alert("Please Select Course");
				// 				$("select#course_name+"+i).focus();
				// 				return false;
				// 		  }
				// 			if($("select#faculty"+i).val() == "0"){
				// 				alert("Please Enter faculty");
				// 				$("select#faculty"+i).focus();
				// 				return false;
				// 		 	 }

				if ($("select#topic" + i).val() == "0") {
					alert("Please Select Topic of Row " + i);
					$("select#topic" + i).focus();
					return false;
				}

				if ($("select#sub_topic" + i).val() == "0") {
					alert("Please Select Sub-Topic of Row " + i);
					$("select#sub_topic" + i).focus();
					return false;
				}

				if ($("select#learning_objective" + i).val() == "0") {
					alert("Please Select Learning Objective of Row " + i);
					$("select#learning_objective" + i).focus();
					return false;
				}

				if ($("#instructional_method" + i).val() == "0") {
					alert("Please Select Instructional Method of Row " + i);
					$("select#instructional_method" + i).focus();
					return false;
				}
			}
		}

		student();
		return true;

	}

	function getTopic(ind) {
		$("#topic").empty();
		var course_id = $("#course_name" + ind).val();

		$
				.post(
						"getTopic_name1?" + key + "=" + value,
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
							$("select#topic" + ind).html(options);

						});

	}

	function getSubTopic(ind) {
		$("#sub_topic").empty();
		var topic_id = $("#topic" + ind).val();

		$
				.post(
						"getSubTopic_name1?" + key + "=" + value,
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
							$("select#sub_topic" + ind).html(options);

						});

	}

	function getLearning(ind) {
		$("#learning_objective").empty();
		var topic_id = $("#topic" + ind).val();

		$
				.post(
						"getLearning_Objective1?" + key + "=" + value,
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
							$("select#learning_objective" + ind).html(options);

						});

	}

	function getFacultyData(ind) {
		var course_id = $("#course_name" + ind).val();

		$
				.post(
						"getfacutlydetails1?" + key + "=" + value,
						{
							course_id : course_id

						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select Course--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#faculty" + ind).html(options);

						});
	}

	function getcoursedtl(ind) {

		// 		var degree_id = $("#degree").val();
		var professional_id = $("#professional" + ind).val();

		$
				.post(
						"getCourseList_for_Curri?" + key + "=" + value,
						{

							// 			degree_id : degree_id,
							professional_id : professional_id

						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "---Select Course---" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_name" + ind).html(options);

						});
	}

	function SelectCourseName(ind) {

		document.getElementById('course_name' + ind).onclick = function() {
			getFacultyData(ind);
			getTopic(ind)
		};

	}

	function SelectTopic(ind) {

		document.getElementById('topic' + ind).onclick = function() {
			getLearning(ind);
			getSubTopic(ind);
		};

	}

	function SelectRadioBtn(ind) {

		document.getElementById('yes' + ind).onclick = function() {
			getreadonlydataforyes(ind);
		};

		document.getElementById('no' + ind).onclick = function() {
			getreadonlydataforno(ind);
		};

	}

	function selectLectureDetails() {

		$("#family_table").show();
		$("#lectdiv").show();

		var ldate = $("#sdate").val();

		var course = $("#course_name").val();

		var faculty = $("#faculty").val();

		var topic = $("#topic").val();

		var sub_topic = $("#sub_topic").val();

		var professional = $("#professional").val();

		$
				.post(
						'getLecturedetailsbyDate?' + key + "=" + value,
						{
							ldate : ldate,
							course : course,
							faculty : faculty,
							professional : professional
						},
						function(k) {

							// 			console.log(k);
							var att = 0;
							datacount = k.length;
							$("#datacounthId").val(k.length);

							$('#family_sibtbody').empty();

							for (var i = 0; i < k.length; i++) {
								att = i + 1;
								$("input#new_count_hidden").val(att);//current serial No
								$("table#family_table")
										.append(
												'<tr id="tr_id_att'+att+'"><td><p>'
														+ att
														+ '</p></td>'

														+ '<td><div class="input-style-1"><input type="time" id="start_time'+att+'" name="start_time'+att+'" class="form-control"></div></td>'
														+ '<td><div class=""><div class="input-style-1"><input type="time" id="end_time'+att+'" name="end_time'+att+'" class="form-control"></div></td>'
														+ '<td><div class=""><div class="select-style-1"><select name="course_name'+att+'" id="course_name'+att+'" class="singleselect form-control form-control-lg" >'
														+ '<option value="0">---Select---</option><c:forEach var="item" items="${getCourseList}" varStatus="num"><option value="${item.id}" name="${item.id}">${item.course_name}</option></c:forEach>'
														+ '</select></div></div></td>'

														+ '<td><div class="select-style-1"><div class="select-position"><select name="faculty'+att+'" id="faculty'+att+'"  class="singleselect form-control form-control-lg" ><option value="0"---Select---></option><c:forEach var="item" items="${getFacultyList}" varStatus="num">'
														+ '<option value="${item[0]}" name="${item[0]}">${item[1]}</option></c:forEach></select></div></div></td>'

														+ '<td><div class="select-style-1"><div class="select-position"><select name="topic'+att+'" id="topic'+att+'" class="singleselect form-control form-control-lg" ><option value="0"---Select---></option></select></div></div></td>'
														+ '<td><div class="select-style-1"><div class="select-position"><select name="sub_topic'+att+'" id="sub_topic'+att+'" class="singleselect form-control form-control-lg" ><option value="0">---Select---</option></select></div></div></td>'

														// 				+'<td><div class="select-style-1 mb-30"><div id="subtopic_opt'+att+'" name="subtopic_opt'+att+'" class="select-position"><select class="" id="sub_topic'+att+'" name="sub_topic'+att+'"><option value="0" id="" class="">--Select-<option>'
														// 				+'</select></div><div id="subtopic_option'+att+'" class="multiselect'+att+'"><c:forEach var="item" items="${getSubTopicList}" varStatus="num"><div class="form-check radio-style checkbox-style "><input class="multi form-check-input mr-5" type="checkbox" id="subtopic_option_${num.index+1}" name="subtopic_option_${num.index+1}" value="${item.id}"> <label class="lbl" value="${item.sub_topic}" for="first">${item.sub_topic}</label>'
														// 				+'</div></c:forEach><input class="multi form-check-input mr-5" type="hidden" id="multiSelect_SubTopic'+att+'" name="multiSelect_SubTopic'+att+'" value=""></div></div></td>'
														+ '<td><div class="select-style-1"><div class="select-position"><select name="learning_objective'+att+'" id="learning_objective'+att+'" class="singleselect form-control form-control-lg" ><option value="0">---Select---</option></select></div></div></td>'

														+ '<td><div class="select-style-1"><div class="select-position"><select name="instructional_method'+att+'" id="instructional_method'+att+'" class="singleselect form-control form-control-lg" ><option value="0">---Select---</option><c:forEach var="item" items="${getInstructionalList}" varStatus="num">'
														+ '<option value="${item.id}" name="${item.id}">${item.instructional_method_name}</option> </c:forEach></select></div></div></td>'
														+ '<td><div class="input-style-form-check_block"><div class="form-check radio-style"><input type="radio"  id="yes'+att+'" name="btnradio'+att+'" class="form-check-input" value="1" checked>'
														+ '<label class="form-check-label" for="yes'+att+'">Yes</label> </div><div class="form-check radio-style"><input type="radio" id="no'+att+'" name="btnradio'+att+'" class="form-check-input" value="0" ><label class="form-check-label" for="no'+att+'">No</label></div></div></td>');

								// 			 $("#sub_topic"+att).click(function(){
								// 					$(".multiselect"+att).toggle();

								// 				});
								$("#start_time" + att).val(k[i][0]);
								$("#start_time" + att).attr('readonly', 'true');

								$("#end_time" + att).val(k[i][1]);
								$("#end_time" + att).attr('readonly', 'true');

								getcoursedtl(att);
								$("#course_name" + att).val(k[i][2]);
								$("#course_name" + att)
										.attr('readonly', 'true');

								getFacultyData(att);
								$("#faculty" + att).val(k[i][3]);
								$("#faculty" + att).attr('readonly', 'true');

								getTopic(att);

								getSubTopic(att);

								SelectRadioBtn(att);

								SelectCourseName(att);

								SelectTopic(att);

							}

							att++;
						});
	}

	function getreadonlydataforno(att) {

		$("#course_name" + att).attr('readonly', 'true');

		$("#faculty" + att).attr('readonly', 'true');

		$("#topic" + att).attr('readonly', 'true');
		$("#topic" + att).val("0");

		$("#sub_topic" + att).attr('readonly', 'true');
		$("#sub_topic" + att).val("0");

		$("#learning_objective" + att).attr('readonly', 'true');
		$("#learning_objective" + att).val("0");

		$("#instructional_method" + att).attr('readonly', 'true');
		$("#instructional_method" + att).val("0");

	}

	function getreadonlydataforyes(att) {

		$("#topic" + att).attr('readonly', false);

		$("#sub_topic" + att).attr('readonly', false);

		$("#learning_objective" + att).attr('readonly', false);

		$("#instructional_method" + att).attr('readonly', false);

	}
</script>
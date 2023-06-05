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
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd">Update Student Lecture Plan</span>
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update
									Student Lecture Plan</li>
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
					<!--  style start -->
					<form:form name="edit_student_lecture_planUrl"
						id="edit_student_lecture_planUrl" method="post"
						class="form-horizontal" modelAttribute="Student_LecturerCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<div class="row">
									<!-- Hidden Start-->
									<input type="hidden" id="datacounthId" name="datacounthId"
										value="0" autocomplete="off" />
									<!-- Hidden End -->
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1 ">
											<label>Professional<span class="mandatory">*</span></label>
											<div class="select-position">
												<input type="text" value="${system[0][0]}"
													id="professionallab" name="professionallab"
													class="form-control form-control-lg form-control-a effect-9"
													readonly="readonly"> <select name="professional"
													id="professional" class="custom-d-none">
													<!-- 														<option value="0">--Select Professional--</option> -->
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
										<div class="input-style-2">
											<label for="username">Date<span class="mandatory">*</span></label>
											<input type="text" name="sdate" id="sdate" maxlength="10"
												class="form-control-sm form-control effect-9 "
												aria-required="true" autocomplete="off" value="DD/MM/YYYY">
										</div>
										<div class="input-style-1">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>
									</div>
								</div>
							</div>
							<!-- 										<span class="mandatory" >(All fields are mandatory)</span> -->

							<section class="single-detail-block">
								<div class="row">
									<div class="col-12 col-sm-12 col-md-12 col-lg-12">
										<div class="table-wrapper table-responsive simple-table">
											<table class="table" id="family_table" class="custom-d-none">
												<thead>
													<tr>
														<th rowspan="2"><h6>Sr No</h6></th>
														<th rowspan="2"><h6>Start Time</h6></th>
														<th rowspan="2"><h6>End Time</h6></th>
														<th rowspan="2"><h6>Subject</h6></th>
														<th rowspan="2"><h6>Faculty</h6></th>
														<th rowspan="2"><h6>Topic</h6></th>
														<th rowspan="2"><h6>Sub-Topic</h6></th>
														<th rowspan="2"><h6>Learning Objective</h6></th>
														<th rowspan="2"><h6>Instructional Method</h6></th>
														<th rowspan="2" class="hide-action"><h6>Attended</h6></th>
													</tr>
												</thead>
												<tbody id="family_sibtbody">
													<!-- 													<tr id="tr_sibling1"> -->
													<!-- 														<td>1</td> -->

													<!-- 													<td> -->
													<!-- 														<div class=""> -->
													<!-- 															<div class="input-style-1"> -->
													<!-- 															<select name="course_name" id="course_name" onchange="getFacultyDtl(); getTopic()" class="form-control" > -->
													<!-- 															<option value="0">---Select Course---</option> -->
													<%-- 																<c:forEach var="item" items="${getCourseList}" varStatus="num"> --%>
													<%-- 																	<option value="${item.id}" name="${item.id}">${item.course_name}</option> --%>
													<%-- 																</c:forEach> --%>
													<!-- 														    </select> -->
													<!-- 															</div> -->
													<!-- 														</div>'+att+' -->
													<!-- 													</td> -->

													<!-- 													<td> -->
													<!-- 														<div class=""> -->
													<!-- 															<div class="input-style-1"> -->
													<!-- 															<select name="faculty" id="faculty"  class="form-control" > -->
													<!-- 															<option value="0">---Select Faculty---</option> -->
													<%-- 																<c:forEach var="item" items="${getFacultyList}" varStatus="num"> --%>
													<%-- 																	<option value="${item[0]}" name="${item[0]}">${item[1]}</option> --%>
													<%-- 																</c:forEach> --%>
													<!-- 														    </select> -->
													<!-- 															</div> -->
													<!-- 														</div> -->
													<!-- 													</td> -->

													<!-- 													<td> -->
													<!-- 														<div class=""> -->
													<!-- 															<div class="input-style-1"> -->
													<!-- 															<select name="topic" id="topic" onchange="getLearning()" class="form-control" > -->
													<!-- 															<option value="0">---Select Topic---</option> -->
													<!-- 														    </select> -->
													<!-- 															</div> -->
													<!-- 														</div> -->
													<!-- 													</td> -->
													<!-- 													<td> -->
													<!-- 														<div class=""> -->
													<!-- 															<div class="input-style-1"> -->
													<!-- 															<select name="learning_objective" id="learning_objective" class="form-control" > -->
													<!-- 															<option value="0">---Select Learning Objectives---</option> -->
													<!-- 														    </select> -->
													<!-- 															</div> -->
													<!-- 														</div> -->
													<!-- 													</td> -->
													<!-- 													<td> -->
													<!-- 														<div class=""> -->
													<!-- 															<div class="input-style-1"> -->
													<!-- 															<select name="instructional_method" id="instructional_method" class="form-control" > -->
													<!-- 															<option value="0">---Select Instructional Method---</option> -->
													<%-- 															<c:forEach var="item" items="${getInstructionalList}" varStatus="num"> --%>
													<%-- 																<option value="${item.id}" name="${item.id}">${item.instructional_method_name}</option>  --%>
													<%-- 															</c:forEach> --%>
													<!-- 														    </select> -->
													<!-- 															</div> -->
													<!-- 														</div> -->
													<!-- 													</td> -->
													<!-- 													<td> -->
													<!-- 													<div class=""> -->
													<!-- 														<div class="input-style-form-check"> -->
													<!-- 															<div class="input-style-form-check"> -->
													<!-- 																<label for="fname"></label> -->
													<!-- 																<div class="form-check radio-style"> -->
													<!-- 																	<input type="radio" id="yes" name="btnradio" class="form-check-input" value="0" checked> <label -->
													<!-- 																			class="form-check-label">Yes</label> -->
													<!-- 																</div> -->
													<!-- 																<div class="form-check radio-style"> -->
													<!-- 																	<input type="radio" id="no" name="btnradio" class="form-check-input" value="1" > <label -->
													<!-- 																		class="form-check-label">No</label> -->
													<!-- 																</div> -->
													<!-- 															</div> -->
													<!-- 														</div> -->
													<!-- 													</div> -->
													<!-- 												  </td> -->
													<!-- 												</tr> -->
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</section>



							<div id="lectdiv" class="custom-d-none">

								<!-- Bottom Button Start -->
								<div class="btn-bottom">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
												<li><a href="Search_Student_Lecturer_PlanUrl"
													class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
														<i class="lni lni-chevron-left"></i>Back
												</a></li>

												<li><input type="button" id="update" value="Update"
													class="main-btn deactive-btn btn-hover btnupda" /></li>
											</ul>
										</div>
									</div>
								</div>
							</div>

							<!-- Bottom Button End -->
							<!-- 							<ul class="buttons-group mainbtn"> -->

							<!-- 								<li><input id="Search_Student_Lecturer_PlanUrl" -->
							<!-- 									class="main-btn dark-btn-outline  btn-hover btn-iconic-icon" type="button" -->
							<!-- 									 value="Update" /></i>Back -->
							<!-- 									</a></li> -->
							<!-- 								</li> -->

							<!-- 									<li><input type="submit" id="update" value="Update" -->
							<!-- 										class="main-btn deactive-btn btn-hover" /></li> -->
							<!-- 							</ul> -->

						</div>
					</form:form>

				</div>
			</div>
		</div>
	</div>

</section>

<script type="text/javascript"
	src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>

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
				//         	alert(professionallab);
				getcoursedtl()
				//         	 getTopic()

				$("#sdate").val('${sdate}');
				datepicketDate('sdate');
				selectLectureDetailsforEdit();

			});

	//--------------------CSP-------------------------------//

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('update').onclick = function() {
			return Validation();
		};

		document.getElementById('sdate').onchange = function() {
			selectLectureDetailsforEdit();
			getcoursedtl();
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

	var datacount = 0;

	function studentforedit() {

		var start_time = $("#start_time1").val();
		var end_time = $("#end_time1").val();
		var professional = $("#professional").val();
		var course_name = $("#course_name").val();
		var faculty = $("#faculty").val();
		var topic = $("#topic").val();
		var sub_topic = $("#sub_topic").val();
		var learning_objective = $("#learning_objective").val();
		var instructional_method = $("#instructional_method").val();

		var sdate = $("#sdate").val();
		var form = $("#edit_student_lecture_planUrl").serialize();
		$
				.post(
						"getStudent_DetailsforEdit?" + key + "=" + value,
						form,
						function(j) {
							

							 if(j.err != undefined){
								 console.log(j.err);
								
								 
//				 	      		 $("#hid_quali1").val(data);
					      		 alert(j.err);
					      		$("#"+j.field).focus(); 
					      		 
//				 	      		$("#Migrated_Students_add"+ps).show(); 
//				 		    	$("#Migrated_Students_remove"+ps).show(); 
					        }
							
							 else if (j == 'Data Already Exist') {
								alert("Data Already Exist");
							} else {
								(j == 'Data Updated Successfully.')
								alert("Data Updated Successfully.");

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
		var course_name = $("#course_name").val();
		var faculty = $("#faculty").val();
		var topic = $("#topic").val();
		var sub_topic = $("#sub_topic").val();
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
			course_name : course_name,
			faculty : faculty,
			topic : topic,
			sub_topic : sub_topic,
			learning_objective : learning_objective,
			instructional_method : instructional_method,
			sdate : sdate

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].sr_no, j[i].professional,
						j[i].course_name, j[i].faculty, j[i].topic,
						j[i].learning_objective, j[i].instructional_method,
						j[i].sdate ]);
			}
		});
		$.post("getTotal_Student_Lecturer_dataCount?" + key + "=" + value, {
			Search : Search,
			professional : professional,
			course_name : course_name,
			faculty : faculty,
			topic : topic,
			sub_topic : sub_topic,
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

	// 	function Validation() {

	// // 		if ($("#sdate").val().trim() =="" || $("#sdate").trim().equals("DD/MM/YYYY"))  {
	// // 			alert("Please Enter Date");
	// // 			$("input#sdate").focus();
	// // 			return false;
	// // 		}

	// 		if ($("#course_name").val() =="0" ) {
	// 			alert("Please Enter Course Name");
	// 			$("select#course_name").focus();
	// 			return false;
	// 		}
	// 		if ($("#faculty").val() =="0" ) {
	// 			alert("Please Enter faculty");
	// 			$("select#faculty").focus();
	// 			return false;
	// 		}
	// 		if ($("#topic").val() =="0" ) {
	// 			alert("Please Enter topic.");
	// 			$("select#topic").focus();
	// 			return false;
	// 		}
	// 		if ($("#learning_objective").val() =="0" ) {
	// 			alert("Please Enter Learning_Objective.");
	// 			$("select#learning_objective").focus();
	// 			return false;
	// 		}
	// 		if ($("#instructional_method").val() =="0" ) {
	// 			alert("Please Enter instructional_method");
	// 			$("select#instructional_method").focus();
	// 			return false;
	// 		}
	// 		studentforedit();
	// 		return true;
	// 	} 

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
							var options = '<option   value="0">'
									+ "---Select---" + '</option>';
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
							var options = '<option   value="0">'
									+ "---Select---" + '</option>';
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
									+ "---Select---" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#faculty" + ind).html(options);

						});
	}

	function getcoursedtl(ind) {

		// 		var degree_id = $("#degree").val();
		var professional_id = $("#professional").val();

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

	function SelectCourseNameForUpdate(ind) {

		document.getElementById('course_name' + ind).onclick = function() {
			getFacultyData(ind);
			getTopic(ind);
		};

	}

	function SelectTopicForUpdate(ind) {

		document.getElementById('topic' + ind).onclick = function() {
			getLearning(ind);
			getSubTopic(ind);
		};

	}

	function SelectRadioBtnForUpdate(ind) {

		document.getElementById('yes' + ind).onclick = function() {
			getreadonlyupdatedataforyes(ind);
		};

		document.getElementById('no' + ind).onclick = function() {
			getreadonlyupdatedataforno(ind);
		};

	}

	function selectLectureDetailsforEdit() {

		$("#family_table").show();
		$("#lectdiv").show();

		var sdate = $("#sdate").val();
		$("#sdate").attr('readonly', 'true');
// 		$( "#sdate" ).datepicker( "option", "disabled", true );
		$(".ui-datepicker-trigger").attr('disabled', 'true');

		var professional = $("#professional").val();

		$
				.post(
						'getLecturedetailsbyDateforEdit?' + key + "=" + value,
						{
							sdate : sdate,
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
												'<tr align="center" id="tr_id_att'+att+'"><td>'
														+ att
														+ '</td>'

														+ '<td><div class=""><div class="input-style-1"><input type="time" id="start_time'+att+'" name="start_time'+att+'" class="form-control"></div></div></td>'
														+ '<td><div class=""><div class="input-style-1"><input type="time" id="end_time'+att+'" name="end_time'+att+'" class="form-control"></div></div></td>'
														+ '<td><div class=""><div class="select-style-1"><select name="course_name'+att+'" id="course_name'+att+'" class="form-control form-control-lg" >'
														+ '<option value="0">---Select---</option><c:forEach var="item" items="${getCourseList}" varStatus="num"><option value="${item.id}" name="${item.id}">${item.course_name}</option></c:forEach>'
														+ '</select></div></div><input type="hidden" id="EditHid'+att+'" name="EditHid'+att+'" value="0" autocomplete="off" /></td>'

														+ '<td><div class=""><div class="select-style-1"><select name="faculty'+att+'" id="faculty'+att+'" class="form-control form-control-lg" ><option value="0">---Select---</option><c:forEach var="item" items="${getFacultyList}" varStatus="num">'
														+ '<option value="${item[0]}" name="${item[0]}">${item[1]}</option></c:forEach></select></div></div></td>'

														+ '<td><div class=""><div class="select-style-1"><select name="topic'+att+'" id="topic'+att+'" class="form-control form-control-lg" ><option value="0">---Select---</option></select></div></div></td>'

														+ '<td><div class=""><div class="select-style-1"><select name="sub_topic'+att+'" id="sub_topic'+att+'" class="form-control form-control-lg" ><option value="0">---Select---</option></select></div></div></td>'

														+ '<td><div class=""><div class="select-style-1"><select name="learning_objective'+att+'" id="learning_objective'+att+'" class="form-control form-control-lg" ><option value="0">---Select---</option></select></div></div></td>'

														+ '<td><div class=""><div class="select-style-1"><select name="instructional_method'+att+'" id="instructional_method'+att+'" class="form-control form-control-lg" ><option value="0">---Select---</option><c:forEach var="item" items="${getInstructionalList}" varStatus="num">'
														+ '<option value="${item.id}" name="${item.id}">${item.instructional_method_name}</option> </c:forEach></select></div></div></td>'
														+ '<td><div class="input-style-form-check_block"><div class="form-check radio-style"><input type="radio" id="yes'+att+'" name="btnradio'+att+'" class="form-check-input" value="1">'
														+ '<label class="form-check-label">Yes</label> </div><div class="form-check radio-style"><input type="radio" id="no'+att+'" name="btnradio'+att+'" class="form-check-input" value="0" ><label class="form-check-label">No</label></div></div></div></td>');

								$("#start_time" + att).val(k[i][0]);
								$("#start_time" + att).attr('readonly', 'true');

								$("#end_time" + att).val(k[i][1]);
								$("#end_time" + att).attr('readonly', 'true');

								getcoursedtl(att);
								$("#course_name" + att).val(k[i][2]);
								$("#course_name" + att).attr('readonly', 'true');

								getFacultyData(att);

								getTopic(att);

								$("#faculty" + att).val(k[i][3]);
								$("#faculty" + att).attr('readonly', 'true');

								$("#topic" + att).val(k[i][4]);
								getSubTopic(att);
								$("#sub_topic" + att).val(k[i][5]);
								getLearning(att);

								$("#learning_objective" + att).val(k[i][6]);

								$("#instructional_method" + att).val(k[i][7]);

								$("#EditHid" + att).val(k[i][8]);

								SelectRadioBtnForUpdate(att);

								if (k[i][9] == "0") {
									$("#no" + att).click();
								}
								if (k[i][9] == "1") {
									$("#yes" + att).click();
								}
								SelectCourseNameForUpdate(att);
								SelectTopicForUpdate(att);

							}

							att++;

						});
	}

	function Validation() {

		var datacounthId = $("#datacounthId").val();

		for (i = 1; i <= parseInt(datacounthId); i++) {

			if ($("#yes" + i).is(":checked")) {

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

		studentforedit();
		return true;

	}

	function getreadonlyupdatedataforno(att) {

		$("#course_name" + att).attr('readonly', 'true');

		$("#faculty" + att).attr('readonly', 'true');

		$("#topic" + att).val("0");
		$("#topic" + att).attr('readonly', 'true');

		$("#sub_topic" + att).val("0");
		$("#sub_topic" + att).attr('readonly', 'true');

		$("#learning_objective" + att).val("0");
		$("#learning_objective" + att).attr('readonly', 'true');

		$("#instructional_method" + att).val("0");
		$("#instructional_method" + att).attr('readonly', 'true');

	}

	function getreadonlyupdatedataforyes(att) {

		$("#topic" + att).attr('readonly', false);

		$("#sub_topic" + att).attr('readonly', false);

		$("#learning_objective" + att).attr('readonly', false);

		$("#instructional_method" + att).attr('readonly', false);

	}
</script>
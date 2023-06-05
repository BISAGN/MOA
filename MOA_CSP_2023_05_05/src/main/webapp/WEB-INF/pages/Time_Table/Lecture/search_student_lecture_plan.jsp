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

<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/datePicketValidation.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd">Search Student Lesson Plan</span>
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Search
									Student Lesson Plan</li>
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
					<!-- input style start -->
					<!-- 					<div class="card-style mb-30"> -->

					<!-- 						<div id="Form_D" class="content tabcontent"> -->
					<form:form name="lecturer_plan" id="lecturer_plan" method="post"
						class="form-horizontal" modelAttribute="Student_LecturerCMD">
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
<!-- 								<div class="row"> -->
<!-- 									<div class="col-lg-4 col-md-4 col-sm-6 col-12"> -->
<!-- 										<div class="icon-card mb-15"> -->
<!-- 											<div class="content"> -->
<!-- 												<h6 class="mb-2">Total No. of Lectures</h6> -->
<%-- 												<h3 class="">${TotalLecture}</h3> --%>
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										End Icon Cart -->
<!-- 									</div> -->

<!-- 									<div class="col-lg-4 col-md-4 col-sm-6 col-12"> -->
<!-- 										<div class="icon-card mb-30"> -->
<!-- 											<div class="content"> -->
<!-- 												<h6 class="mb-2">Total No. of Attended Lectures</h6> -->
<%-- 												<h3 class="">${TotalAttendedLecture}</h3> --%>
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										End Icon Cart -->
<!-- 									</div> -->

<!-- 									<div class="col-lg-4 col-md-4 col-sm-6 col-12"> -->
<!-- 										<div class="icon-card mb-30"> -->
<!-- 											<div class="content"> -->
<!-- 												<h6 class="mb-2">Total No. of Not Attended Lectures</h6> -->
<%-- 												<h3 class="">${TotalNotAttendedLecture}</h3> --%>
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										End Icon Cart -->
<!-- 									</div> -->
<!-- 								</div> -->

								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
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
												<select name="" id="professional"
													class="singleselect form-control form-control-lg">
													<!-- 													<option value="0">--Select Professional--</option> -->
													<c:forEach var="item" items="${getProfessionalList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.professional}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>


									<div class="col-12 col-sm-12 col-md-3 col-lg-2">
										<div class="input-style-1">
											<label for="appt">Start time</label> <input type="time"
												id="start_time" name="start_time" class="form-control">
										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-sm-12 col-md-3 col-lg-2">
										<div class="input-style-1">
											<label for="appt">End time</label> <input type="time"
												id="end_time" name="end_time" class="form-control">
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-2">
											<label for="username">Date</label> <input type="text"
												name="sdate" id="sdate" maxlength="10"
												class="form-control-sm form-control effect-9 "
												aria-required="true" autocomplete="off" value="DD/MM/YYYY">
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Subject</label>
											<div class="select-position">
												<select name="course_name" id="course_name"
													class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getCourceList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.course_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Faculty</label>
											<div class="select-position">
												<select name="faculty" id="faculty"
													class="form-control form-control-lg form-control-a effect-9 select2 narrow wrap">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getFacultyList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="input-style-1">
											<!-- Hidden Start -->
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
											<!-- Hidden End -->
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Topic</label>
											<div class="select-position">
												<select name="topic" id="topic"
													class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getTopicList}"
														varStatus="num">
														<option value="${item.id}" name="${item.topic}">${item.topic}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>



									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Sub-Topic</label>
											<div class="select-position">
												<select name="sub_topic" id="sub_topic"
													class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getSubTopicList}"
														varStatus="num">
														<option value="${item.id}" name="${item.sub_topic}">${item.sub_topic}</option>
													</c:forEach>
												</select>
											</div>
										</div>

									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Learning Objective</label>
											<div class="select-position">
												<select name="learning_objective" id="learning_objective"
													class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getLearning}"
														varStatus="num">
														<option value="${item.id}"
															name="${item.learning_objective}">${item.learning_objective}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Instructional Method</label>
											<div class="select-position">
												<select name="instructional_method"
													id="instructional_method"
													class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getInstructionalList}"
														varStatus="num">'
												<option value="${item.id}" name="${item.id}">${item.instructional_method_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>
								</div>
							</div>
							<!-- Button Start-->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<ul class="buttons-group mainbtn">
											<li><a type="button" id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Search_Student_Lecturer_PlanUrl"
												class="main-btn dark-btn btn-hover btnreset">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Button End-->
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
			<!-- end col -->
			<!-- title-wrapper end -->
			<section class="single-detail-block">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<input type="hidden" id="professionallab" name="professionallab"
									class="custom-d-none"> <select name="professional"
									id="professional" class="custom-d-none">
									<c:forEach var="item" items="${getProfessionalList}"
										varStatus="num">
										<option value="${item.id}" name="${item.id}">${item.professional}</option>
									</c:forEach>
								</select>
								<table class="table" id="search_plan">
									<thead>
										<tr>
											<th id="1"><h6>Sr No</h6></th>
											<th id="2"><h6>Professional</h6></th>
											<th id="9"><h6>Start Time</h6></th>
											<th id="10"><h6>End Time</h6></th>
											<th id="4"><h6>Subject</h6></th>
											<th id="3"><h6>Faculty</h6></th>
											<th id="5"><h6>Topic</h6></th>
											<th id="5"><h6>Sub-Topic</h6></th>
											<th id="6"><h6>Learning Objectives</h6></th>
											<th id="7"><h6>Instructional Method</h6></th>
											<th id="8"><h6>Date</h6></th>
											<th><h6>Action</h6></th>

										</tr>
										<!-- 						end table row -->
									</thead>
									<tbody>
									</tbody>
								</table>
								<!-- 				end table -->
							</div>
						</div>
						<!-- 		end card -->
					</div>
					<!-- 	end col -->
				</div>
			</section>
		</div>
	</div>
</section>

<script type="text/javascript"
	src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>

<c:url value="edit_student_lecture_planUrl"
	var="edit_student_lecture_planUrl" />
<form:form action="${edit_student_lecture_planUrl}" method="post"
	id="updateForm" name="updateForm" modelAttribute="sdate1">
	<input type="hidden" name="sdate1" id="sdate1" value="0" />
</form:form>

<c:url value="delete_Student_LectureUrl" var="delete_Student_LectureUrl" />
<form:form action="${delete_Student_LectureUrl}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
        	mockjax1('search_plan');
        	table = dataTable('search_plan');
        	$('#btn-reload').on('click', function() {
        		table.ajax.reload();
        	});
        	
//         	professionallab
        	
        	if('${Current_Prof[0][2]}'=='1'){
        		$("#professional").val('15');
        		$("#professionallab").val($( "#professional option:selected" ).text());
        	}else if('${Current_Prof[0][2]}'=='2'){
        		$("#professional").val('16');
        		$("#professionallab").val($( "#professional option:selected" ).text());
        	}else if('${Current_Prof[0][2]}'=='3'){
        		$("#professional").val('17');
        		$("#professionallab").val($( "#professional option:selected" ).text());
        	}
        	$("#professionallab").val($( "#professional option:selected" ).text());
        	getcoursedtl()
//         	 getTopic()
                datepicketDate('sdate');
        });
        



				//--------------------CSP-------------------------------//
				
				
				document.addEventListener('DOMContentLoaded', function () {	

					
				document.getElementById('course_name').onchange = function() {
					getFacultyDtl(); 
					getTopic()
				};
				
				document.getElementById('topic').onchange = function() {
					getLearning();
					getSubTopic();
				};
				
				document.getElementById('sdate').onchange = function() {
					clickrecall(this,'DD/MM/YYYY');
				};
				
				//******Date CSP********//
				
				
				document.getElementById('sdate').onclick = function() {
					clickclear(this, 'DD/MM/YYYY');
				};
				
				document.getElementById('sdate').onkeyup = function() {
					clickclear(this, 'DD/MM/YYYY');
				};
				
				document.getElementById('sdate').onblur = function() {
					clickrecall(this,'DD/MM/YYYY');
					validateDate_BackDate(this.value,this);
				};
				
				document.getElementById('sdate').onfocus = function() {
					this.style.color='#000000';
				};
				
				
				});

				
				
function setTimeLoadForTable(){
	
	document.querySelectorAll('.ADDSystem').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('apIdAGE'+val).value;
				
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData(hid);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('deleteID'+val).value;
			
			if (confirm('Are You Sure You Want to Delete Detail ?')) {
				deleteData(hid);
			} else {
				return false;
			}
		})
	});
		
	}



var datacount = 0;


function student(){
	
	var professional = $("#professional").val();
	var start_time = $("#start_time").val();
	var end_time = $("#end_time").val();
	var course_name = $("#course_name").val();
	var faculty = $("#faculty").val();
	var topic = $("#topic").val();
	var sub_topic = $("#sub_topic").val();
	var learning_objective = $("#learning_objective").val();
	var instructional_method = $("#instructional_method").val();
	var sdate = $("#sdate").val();
	var form = $("#lecturer_plan").serialize();
	$.post("getStudent_Details?" + key + "=" + value, form, function(j) {
	if (j=='Data Already Exist') {
		alert("Data Already Exist");
	}
	else {
		(j=='Data Saved Successfully.') 
			alert("Data Saved Successfully.");
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
		
		var orderColunm = $(table.column(order[0][0]).header()).attr('id')
		.toLowerCase();
		var orderType = order[0][1];
		var professional = $("#professional").val();
		var start_time = $("#start_time").val();
		var end_time = $("#end_time").val();
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
			start_time : start_time,
			end_time : end_time,
			course_name : course_name,
			faculty : faculty,
			topic : topic,
			sub_topic : sub_topic,
			learning_objective : learning_objective,
			instructional_method : instructional_method,
			sdate : sdate

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].sr_no, j[i].professional,j[i].start_time,j[i].end_time,
					j[i].course_name, j[i].faculty, j[i].topic, j[i].sub_topic,j[i].learning_objective, 
					j[i].instructional_method, j[i].sdate ,j[i].action]);
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
			sub_topic : sub_topic,
			learning_objective : learning_objective,
			instructional_method : instructional_method,
			sdate : sdate
			
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	function editData(id) {

		$("#sdate1").val(id);
		document.getElementById('updateForm').submit();
		
	}

	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
		
		
		}	
		

function getTopic(){
	$("#topic").empty();
	var course_id= $("#course_name").val();
	
	$.post("getTopic_name1?" + key + "=" + value, {
		
		course_id : course_id
		
	}, function(j) {
		var options = '<option   value="0">' + "--Select--"
		+ '</option>';
			for (var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
					+ j[i][1] + '</option>';
       }
	$("select#topic").html(options);

	});
	
}


function getSubTopic(){
	$("#sub_topic").empty();
	var topic_id= $("#topic").val();
	
	$.post("getSubTopic_name1?" + key + "=" + value, {
		
		topic_id : topic_id
		
	}, function(j) {
		var options = '<option   value="0">' + "--Select--"
		+ '</option>';
			for (var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
					+ j[i][1] + '</option>';
       }
	$("select#sub_topic").html(options);
	});
	
}

function getLearning(){
	$("#learning_objective").empty();
	var topic_id= $("#topic").val();
	
	$.post("getLearning_Objective1?" + key + "=" + value, {
		
		topic_id : topic_id
		
	}, function(j) {
		var options = '<option   value="0">' + "--Select--"
		+ '</option>';
			for (var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
					+ j[i][1] + '</option>';
       }
	$("select#learning_objective").html(options);

	});
	
}

function getFacultyDtl(){
	var course_id = $("#course_name").val();

	$.post("getfacutlydetails1?" + key + "=" + value, {
		course_id : course_id
}, function(j) {
	var options = '<option value="' + "0" + '">'
			+ "--Select--" + '</option>';
	for (var i = 0; i < j.length; i++) {

		options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
				+ j[i][1] + '</option>';
	}
	$("select#faculty").html(options);

});
}

function getcoursedtl(){
	
//		var degree_id = $("#degree").val();
	var professional_id = $("#professional").val();
	var system_id = "${system_id}";
	console.log(system_id);
	$.post("getCourseList_for_Curri?" + key + "=" + value, {
		
//			degree_id : degree_id,
		professional_id : professional_id,
		system_id :system_id
		
}, function(j) {
	var options = '<option value="' + "0" + '">'
			+ "---Select Course---" + '</option>';
	for (var i = 0; i < j.length; i++) {

		options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
				+ j[i][1] + '</option>';
	}
	$("select#course_name").html(options);
	
});
}


	
</script>
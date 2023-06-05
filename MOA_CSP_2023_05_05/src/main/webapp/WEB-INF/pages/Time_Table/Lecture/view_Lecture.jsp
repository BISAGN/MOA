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
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd">View Lesson Plan</span>
						</h2>
						
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">View Lesson Plan</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper start -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="lecturer" id="lecturer" action="Lecturer_Action"
						method="post" modelAttribute="LecturerCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
							
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Professional</label>
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
											<label for="text-input">Name of Course</label>
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
											<label for="text-input">Topic</label>
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
											<label for="text-input">Learning Objectives</label>
											<div class="select-position">
												<select name="learning_objective" id="learning_objective"
													class="singleselect form-control form-control-lg">
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
										<div class="select-style-1">
											<label for="username">Instructional Method(Circle as
												appropriate)</label>
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
											<label>Academic Year</label> <input type="year"
												id="academic_year" name="academic_year" name="academic_year"
												class="autocomplete xt-transupp" autocomplete="off"
												maxlength="4" placeholder="Academic Year " />

										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Assessment Method</label>
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
											<li><a href="LecturerUrl"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
													<i class="lni lni-chevron-left"></i>Back
											</a></li>
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<!-- 								<li><input id="btn-save" -->
											<!-- 									class="main-btn info-btn btn-hover" type="submit" -->
											<!-- 									onclick="return Validation();" value="Update" /> -->
											<!-- 								</li> -->
											<li><a href="Lecturer_ViewUrl"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
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
			<section class="single-detail-block">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="search_plan">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<th><h6>Professional</h6></th>
											<th><h6>Course</h6></th>
											<th><h6>Topic</h6></th>
											<th><h6>Learning Objectives</h6></th>
											<th><h6>Lecture Hours Duration</h6></th>
											<th><h6>Non_lecture HoursDuration</h6></th>
											<th><h6>Instructional Method</h6></th>
											<th><h6>Academic Year</h6></th>
											<th><h6>Date</h6></th>
											<th><h6>Lecture Hours Taken</h6></th>
											<th><h6>Lecture Hours Remaining</h6></th>
											<th><h6>Activity Description</h6></th>
											<th><h6>Resources/ A-V Aids</h6></th>
											<th><h6>Assessment Method</h6></th>
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
<c:url value="Lecturer_EditUrl" var="Edit_Url" />
<form:form action="${Edit_Url}" method="get" id="updateForm"
	name="updateForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3">
</form:form>
<c:url value="delete_LecUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	
	mockjax1('search_plan');
	table = dataTable('search_plan');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});

});

document.addEventListener('DOMContentLoaded', function () {	
	
	
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
	var topic = $("#topic").val();
	var learning_objective = $("#learning_objective").val();
	var instructional_method = $("#instructional_method").val();
	var academic_year = $("#academic_year").val();
	var assessment_method = $("#assessment_method").val();
	

	$.post("getFilter_Lecturer_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		professional : professional,
		course_name : course_name,
		topic : topic,
		learning_objective : learning_objective,
		instructional_method : instructional_method,
		academic_year : academic_year,
		assessment_method : assessment_method
		

	}, function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].sr_no, j[i].professional, j[i].course_name, j[i].topic, j[i].learning_objective, 
				j[i].lecture_hours, j[i].non_lecture_hours,j[i].instructional_method, j[i].academic_year, j[i].fdate,
				j[i].time,j[i].time_rem, j[i].activity_description, j[i].resources, j[i].assessment_method, j[i].action ]);
		}
	});
	$.post("getTotal_Lecturer_dataCount?" + key + "=" + value, {
		Search : Search,
		course_name : course_name,
		topic : topic,
		professional : professional,
		learning_objective : learning_objective,
		instructional_method : instructional_method,
		academic_year : academic_year,
		assessment_method : assessment_method
		
	}, function(j) {

		FilteredRecords = j;

	});
	setTimeout(setTimeLoadForTable, 1000);
}

function Search() {
	$("select#professional").val($('select#professional').val());
	$("#course_name").val($('#course_name').val());
	$("#topic").val($('#topic').val());
	$("#learning_objective").val($('#learning_objective').val());
	$("#lecture_hours").val($('#lecture_hours').val());
	$("#non_lecture_hours").val($('#non_lecture_hours').val());
	$("#instructional_method").val($('#instructional_method').val());
	$("#academic_year").val($('#academic_year').val());
	$("#fdate").val($('#fdate').val());
	$("#time").val($('#time').val());
	$("#time_rem").val($('#time_rem').val());
	$("#activity_description").val($('#activity_description').val());
	$("#resources").val($('#resources').val());
	$("#assessment_method").val($('#assessment_method').val());
	

	document.getElementById('searchForm').submit();
}
function editData(id) {

	$("#id3").val(id);
	document.getElementById('updateForm').submit();
}

function deleteData(id) {
	$("#id2").val(id);
	document.getElementById('deleteForm').submit();
	}	
	
function Validation() {
	
	if ($("select#professional").val() =="0" ) {
		alert("Please select professional");
		$("select#professional").focus();
		return false;
	}
	if ($("select#course_name").val() =="0" ) {
		alert("Please select Course Name");
		$("select#course_name").focus();
		return false;
	}
	if ($("select#topic").val() =="0" ) {
		alert("Please select topic.");
		$("select#topic").focus();
		return false;
	}
	if ($("select#learning_objective").val() =="0" ) {
		alert("Please select Learning_Objective.");
		$("select#learning_objective").focus();
		return false;
	}
	if ($("#lecture_hours").val() =="" ) {
		alert("Please Enter lecture_hours");
		$("input#lecture_hours").focus();
		return false;
	}
	if ($("#non_lecture_hours").val() =="" ) {
		alert("Please Enter non_lecture_hours");
		$("input#non_lecture_hours").focus();
		return false;
	}
	if ($("select#instructional_method").val() =="0" ) {
		alert("Please select instructional_method");
		$("select#instructional_method").focus();
		return false;
	}
	if ($("#academic_year").val() =="" ) {
		alert("Please Enter academic_year");
		$("input#academic_year").focus();
		return false;
	}
	if ($("#fdate").val() =="" ) {
		alert("Please Enter Date");
		$("input#fdate").focus();
		return false;
	}
	if ($("#time").val() =="" ) {
		alert("Please Enter time ");
		$("input#time").focus();
		return false;
	}
	if ($("#time_rem").val() =="" ) {
		alert("Please Enter time_rem ");
		$("input#time_rem").focus();
		return false;
	}
	if ($("#activity_description").val() =="" ) {
		alert("Please Enter activity_description ");
		$("input#activity_description").focus();
		return false;
	}
	if ($("#resources").val() =="" ) {
		alert("Please Enter resources ");
		$("input#resources").focus();
		return false;
	}
	if ($("select#assessment_method").val() =="0" ) {
		alert("Please select assessment_method ");
		$("select#assessment_method").focus();
		return false;
	}
	return true;
} 

function getL_N_Hours(){
	var learning_objective = $("#learning_objective").val();
	$.post("getL_N_Hours?" + key + "=" + value, {
		learning_objective : learning_objective
}, function(j) {
	
	$("#lecture_hours").val(j[0][0]);
	$("#non_lecture_hours").val(j[0][1]);
	
	
	});
}

function getDate(){
	var fdate = $("#fdate").val();
	$.post("gettime_duration?" + key + "=" + value, {
		
		fdate : fdate
		
}, function(j) {
	
	$("#time").val(j[0][2]);
	
	var lh = $("#lecture_hours").val();
	if (lh != null || lh!= "") {
		 var rem = parseInt(lh) - parseInt(j[0][2]);
	 $("#time_rem").val(rem);
	}

	});
}

// function getInstitute(){
	
// 	var userid= $("#userid").val();
// 	$.post("getInstitute_name?" + key + "=" + value, {
		
// 		userid : userid
		
// 	}, function(j) {
// //  		alert(j);
// 		$("#college_name").val(j);
		
// 	});
	
// }

function getTopic(){
	$("#topic").empty();
	var course_id= $("#course_name").val();
	
	$.post("getTopic_name?" + key + "=" + value, {
		
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

function getLearning(){
	$("#learning_objective").empty();
	var topic_id= $("#topic").val();
	
	$.post("getLearning_Objective?" + key + "=" + value, {
		
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
function getCourseList(){
	$("select#course_name").empty();
	
	var professional_id= $("#professional").val();
	var system_id = '${system[0][1]}';
	
	$.post("getCourseList_for_Curri?" + key + "=" + value, {
		
		professional_id : professional_id,
		system_id : system_id
	
	}, function(j) {
		var options = '<option   value="0">' + "--Select--"+ '</option>';
			for (var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
					+ j[i][1] + '</option>';
       }
		$("select#course_name").html(options);

	});
	
}
function getAprrovedDegree(id) {
	
//	var selval = $("#system_hid").val();
	var selval = id;
	$.post("getdegrebysysidlist?" + key + "=" + value,
		{
			selval : selval
		},
	function(j) {
		if(j.length>0){
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

</script>



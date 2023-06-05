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
							<span id="lbladd"></span> Link Exam and Course
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Link
									Exam and Course</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="link_exam_and_course" id="link_exam_and_course"
						action="link_exam_and_courseAction" method='POST'
						commandName="link_exam_and_courseCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Link Exam and Course</h6>
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
												<select name="degree_id" id="degree_id"
													class="singleselect form-control form-control-lg">
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
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Term<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="term_id" id="term_id">
													<option value="0">--Select--</option>
<%-- 													<c:forEach var="item" items="${geti3_termList}" --%>
<%-- 														varStatus="num"> --%>
<%-- 														<option value="${item.id}" name="${item.term}">${item.term}</option> --%>
<%-- 													</c:forEach> --%>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
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
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>No of Exam<span class="mandatory">*</span></label> <input
												type="text" id="no_of_exam" name="no_of_exam"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="2" placeholder="No of Exam" />
										</div>

										<!-- end input -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="username">Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="status" id="status">
													<c:forEach var="item" items="${ActiveInActiveList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>
										<!-- end select -->
										<input type='hidden' id='id' name="id" value='0' />
									</div>
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">

											<li id="btn-reload1"><a href="Link_Exam_and_Course_Url"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i>Back</a></li>

											<li id="btn-update"><input
												class="main-btn deactive-btn btn-hover btnupda"
												type="button" value="Update" /></li>

											<li id="btn-reload"><a
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li id="btn-save"><input value="Save"
												class="main-btn info-btn btn-hover btnsave" type="button" /></li>

											<li id="btn-reload2" ><a href="Link_Exam_and_Course_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>


										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->

						</div>
					</form:form>
				</div>
			</div>
			<section class="single-detail-block">
			<div class="row" id="tbl">
				<div class="col-lg-12">
					<div class="card-style mb-30">
						<div class="table-wrapper table-responsive custom-datatable-p">
							<table class="table" id="search_Link_Exam_and_Course">
								<thead>
									<tr>
										<th id="1"><h6>Sr No</h6></th>
										<th id="11"><h6>System</h6></th>
										<th id="12"><h6>degree</h6></th>
										<th id="13"><h6>Professional</h6></th>
										<th id="14"><h6>Subject</h6></th>
										<th id="16"><h6>Term</h6></th>
										<th id="15"><h6>Exam Type</h6></th>
										<th id="9"><h6>No of Exam</h6></th>
										<th><h6>Action</h6></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			</section>
		</div>
	</div>
</section>

<c:url value="Link_Exam_and_Course_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	$("#pop").hide();
	$("#btn-update").hide();
	$("#btn-reload1").hide();


	mockjax1('search_Link_Exam_and_Course');
	table = dataTable('search_Link_Exam_and_Course');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
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
function getcourselistby_professional() {
	var degree_id = $("#degree_id").val();
	var professional_id = $("#professional_id").val();
	
	$.post('getCourseList_for_Curri?' + key + "=" + value,{  
		degree_id : degree_id,
		professional_id : professional_id
		}).done(function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#course_id").html(options);
	});
}
function getTerm_listByProf() {
	var system = $("#system_id").val();
	var degree = $("#degree_id").val();
	var professional = $("#professional_id").val();

	$
			.post('getTerm_listByProf_for_Curri?' + key + "=" + value, {
				system : system,
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
function editData(id,system_id,degree_id,professional_id,course_id,term_id,exam_type_id,no_of_exam,status) {
	
	$("#btn-update").show();
	$("#btn-save").hide();
	$("#tbl").hide();
	$("#btn-reload").hide();
	$("#btn-reload2").hide();
	$("#btn-reload1").show();
	
	document.getElementById('lbladd').innerHTML = "Update ";
	$("select#system_id").val(system_id);
	$('#system_id').trigger('change');
	getdegreelistbysystem();
	$("select#degree_id").val(degree_id);
	$("select#professional_id").val(professional_id);
	$('#professional_id').trigger('change');
	getcourselistby_professional();
	$("select#course_id").val(course_id);
	getTerm_listByProf();
	$("select#term_id").val(term_id);
	$('#term_id').trigger('change');
	$("select#exam_type_id").val(exam_type_id);
	$('#exam_type_id').trigger('change');
	$("input#no_of_exam").val(no_of_exam);
	$("select#status").val(status);
	document.getElementById('id').value=id;
}
function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}

function setTimeLoadForTable(){
	
	document.querySelectorAll('.ADDLinkExamCourse').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('apId'+val).value;
			var haps = document.getElementById('aps'+val).value;
			var hapd = document.getElementById('apd'+val).value;
			var happ = document.getElementById('app'+val).value;
			var hapc = document.getElementById('apc'+val).value;
			var hapt = document.getElementById('apt'+val).value;
			var hape = document.getElementById('ape'+val).value;
			var hapne = document.getElementById('apne'+val).value;
			var hstatus = document.getElementById('apstatus'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData(hid,haps,hapd,happ,hapc,hapt,hape,hapne,hstatus);
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
function data(search_Link_Exam_and_Course) {
	
	jsondata = [];
	var table = $('#' + search_Link_Exam_and_Course).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id')
			.toLowerCase();
	var orderType = order[0][1];
	var system_id = $("#system_id").val();
	var degree_id = $("#degree_id").val();
	var professional_id = $("#professional_id").val();
	var course_id = $("#course_id").val();
	var term_id = $("#term_id").val();
	var exam_type_id = $("#exam_type_id").val();
	var no_of_exam = $("#no_of_exam").val();
	var status = $("#status").val();

	$.post("getFilterLink_Exam_and_Course_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		system_id : system_id,
		degree_id : degree_id,
		professional_id : professional_id,
		course_id : course_id,
		term_id : term_id,
		exam_type_id : exam_type_id,
		no_of_exam : no_of_exam,
		status : status

	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser,j[i].system_name,j[i].degree_name,j[i].professional, j[i].course_name,j[i].term,j[i].exam_type, j[i].no_of_exam, j[i].action ]);
		}
	});
	$.post("getTotalLink_Exam_and_Course_dataCount?" + key + "=" + value, {
		Search : Search,
		system_id : system_id,
		degree_id : degree_id,
		professional_id : professional_id,
		course_id : course_id,
		term_id : term_id,
		exam_type_id : exam_type_id,
		no_of_exam : no_of_exam,
		status : status
	}, function(j) {
		FilteredRecords = j;
	});
	setTimeout(setTimeLoadForTable, 1000);
}

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('btn-save').onclick = function() {
		if(Validation()){
			 $("#link_exam_and_course").submit();
		}
	};
	document.getElementById('btn-update').onclick = function() {
		if(Validation()){
			 $("#link_exam_and_course").submit();
		}
	};
	document.getElementById('system_id').onchange = function() {
		getdegreelistbysystem();
	};
	document.getElementById('professional_id').onchange = function() {
		getcourselistby_professional();
		getTerm_listByProf();
	};
	document.getElementById('no_of_exam').onkeypress = function() {
		return isNumberKey0to9(event,this);
	};
});
function Validation() {
	
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
	if ($("#term_id").val().trim() == "0") {
		alert("Please Select Term .");
		$("select#term_id").focus();
		return false;
	}
	if ($("#exam_type_id").val().trim() == "0") {
		alert("Please Select Exam Type .");
		$("select#exam_type_id").focus();
		return false;
	}
	if ($("#no_of_exam").val().trim() == "") {
		alert("Please Enter No of Exam.");
		$("input#no_of_exam").focus();
		return false;
	}
	if ($("select#status").val() == "2") {
		alert("Only Select Active Status.");
		$("select#status").focus();
		return false;
	}
	return true;
}
</script>

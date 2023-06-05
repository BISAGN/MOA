<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
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

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span>T6 - h - Distribution of Practical Exam
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">T6 -
									h - Distribution of Practical Exam</li>
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
					<form:form name="Distribute_practical_exam"
						id="Distribute_practical_exam"
						action="Distribute_practical_examAction" method='POST'
						commandName="Distribute_practical_examCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">T6 - h - Distribution of Practical Exam</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
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
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
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

									<!-- 							<div class="row"> -->
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
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

									<h6 class="mb-25">Distribution Practical Exam</h6>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Heads<span class="mandatory">*</span></label> <input
												type="text" id="head" name="head"
												class="autocomplete  txt-transupp" autocomplete="off"
												maxlength="500" placeholder="Heads" /> <input type="hidden"
												id="id" name="id" value="0" autocomplete="off" />
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Marks<span class="mandatory">*</span></label> <input
												type="text" id="mark" name="mark"
												class="autocomplete  txt-transupp" autocomplete="off"
												maxlength="3" placeholder="Marks" /> <input type="hidden"
												id="id" name="id" value="0" autocomplete="off" />
										</div>
									</div>
									<!-- </div> -->
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
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
										<!-- Hidden Start --> 
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" /> <input type="hidden"
												id="count_hidden_att" name="count_hidden_att"
												class="form-control autocomplete" value="1"> <input
												type='hidden' id='id' name="id" value='0' />
												<!-- Hidden End --> 
										</div>
									</div>
								</div>
							</div>

							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
										
										<li id="btn-reload1"><a
												href="Distribution_practical_exam_Url"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
													<i class="lni lni-chevron-left"></i>Back
											</a></li>
											
											<li id="btn-update"><input
												class="main-btn deactive-btn btn-hover btnupda" type="button"
												value="Update" /></li>
												
											<li id="btn-reload"><a
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input value="Save" id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="button" /></li>
											
											<li id="btn-reload2"><a
												href="Distribution_practical_exam_Url"
											class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>
											<li id="btn-view"><a
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
				<!-- end card -->
			</div>
					<div class="row">
					<div id="pop">
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="card-style mb-30">
							<div class="custom-multi-table">
								<h6 class="mb-10">6 H - I - Distribution of Practical Exam</h6>
								<div class="table-wrapper table-responsive custom-table custom-table-v2">
								<!-- id="container-table" -->
									<table class="table table-striped" id="pop">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>
												<th><h6>Heads</h6></th>
												<th><h6>Marks</h6></th>

											</tr>
											<!-- 								<tr id="tr_p1_mcq"></tr> -->
											<!-- 								<tr id="tr_p1_saq"></tr> -->
											<!-- 								<tr id="tr_p1_laq"></tr> -->
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					</div>
				</div>
			
			<section class="single-detail-block">
				<div class="row">
				<div id="view_tbl">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
						 <div class="custom-multi-table">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="search_Distribution_practical_exam">
									<thead>
										<tr>
											<th id="1"><h6>Sr No</h6></th>
											<th id="3"><h6>System</h6></th>
											<th id="5"><h6>Degree</h6></th>
											<th id="7"><h6>Professional</h6></th>
											<th id="9"><h6>Subject</h6></th>
											<th id="11">Head</th>
											<th id="12"><h6>Mark</h6></th>
											<th><h6>Action</h6></th>
										</tr>
									</thead>
									<tbody class="custom-datatablepra">
									</tbody>
								</table>
							</div>
							</div>
						</div>
					</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</section>

<c:url value="getSearch_Distribution_practical_exam" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Distribution_practical_exam1">
	<input type="hidden" name="Distribution_practical_exam1"
		id="Distribution_practical_exam1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="deleteDistribution_practical_exam_Url"
	var="deleteDistribution_practical_exam_Url" />
<form:form action="${deleteDistribution_practical_exam_Url}"
	method="post" id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	$("#pop").hide();
	$("#btn-update").hide();
	$("#btn-reload1").hide();

	$("#pop").hide();
	mockjax1('search_Distribution_practical_exam');
	table = dataTable('search_Distribution_practical_exam');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
});

function getdegreelistbysystem() {
	debugger;
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

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('btn-save').onclick = function() {
		if(Validation()){
			 $("#Distribute_practical_exam").submit();
		}
	};
	document.getElementById('btn-update').onclick = function() {
		if(Validation()){
			 $("#Distribute_practical_exam").submit();
		}
	};
	document.getElementById('btn-view').onclick = function() {
		return View_Validation();
	};

	document.getElementById('system_id').onchange = function() {
		getdegreelistbysystem();
	};
	document.getElementById('professional_id').onchange = function() {
		getcourselistby_professional();
	};
	document.getElementById('mark').onkeypress = function() {
		return isNumberKey0to9(event);
	};
// 	document.getElementById('btn-view').onclick = function() {
// 		getpop();
// 	};
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
	if ($("#head").val().trim() == "0") {
		alert("Please Select Head .");
		$("select#head").focus();
		return false;
	}
	if ($("#mark").val().trim() == "0") {
		alert("Please Select Mark .");
		$("select#mark").focus();
		return false;
	}
	return true;	
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
	getpop();
	return true;	
}

function data(search_Distribution_practical_exam) {
	jsondata = [];
	var table = $('#' + search_Distribution_practical_exam).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
	var orderType = order[0][1];
	
	var system_id = $("#system_id").val();
	var degree_id = $("#degree_id").val();
	var professional_id = $("#professional_id").val();
	var course_id = $("#course_id").val();
	var head = $("#head").val();
	var mark = $("#mark").val();
	var status = $("#status").val();

	$.post("getFilterDistribution_practical_exam_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		system_id:system_id,
		degree_id:degree_id,
		professional_id:professional_id,
		course_id:course_id,
		head:head,
		mark:mark,
		status:status
		
	}, function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].ser,j[i].system_name,j[i].degree_name,j[i].professional, j[i].course_name ,j[i].head ,j[i].mark ,j[i].action]);
		}
	});
	$.post("getTotalDistribution_practical_exam_dataCount?" + key + "=" + value, {
		Search : Search,
		system_id:system_id,
		degree_id:degree_id,
		professional_id:professional_id,
		course_id:course_id,
		head:head,
		mark:mark,
		status:status

	}, function(j) {
		FilteredRecords = j;
		});
	setTimeout(setTimeLoadForTable, 500);
}

function removeOnclick(index){
	document.getElementById('att_id_remove'+index).onclick = function() {
		formopen_re_att(index);
	};
}

function EditData(id,system_id,degree_id,professional_id,course_id,head,mark,status) {
		
		$("#btn-update").show();
	    $("#btn-save").hide();
	    $("#btn-reload").hide();
		$("#btn-reload2").hide();
		$("#btn-reload1").show();
		$("#view_tbl").hide();
		$("#btn-view").hide();
	    document.getElementById('lbladd').innerHTML = "Update  ";
		$("select#system_id").val(system_id);
		$('#system_id').trigger('change');
		getdegreelistbysystem();
		$("select#degree_id").val(degree_id);
		$("select#professional_id").val(professional_id);
		$('#professional_id').trigger('change');
		getcourselistby_professional();
		$("select#course_id").val(course_id);
		$("input#head").val(head);
		$("input#mark").val(mark);
		$("input#status").val(status);
		document.getElementById('id').value=id;
	}
	
function deleteData(id) {
	$("#id2").val(id);
	document.getElementById('deleteForm').submit();
}

function setTimeLoadForTable(){
	
	document.querySelectorAll('.editOnclick').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var cid = document.getElementById('apIdAGE'+val).value;			
			var sid = document.getElementById('sysID'+val).value;
			var did = document.getElementById('degID'+val).value;
			var pid = document.getElementById('proID'+val).value;
			var coid = document.getElementById('couID'+val).value;
			var hid = document.getElementById('headID'+val).value;
			var mid = document.getElementById('markID'+val).value;
			var sts = document.getElementById('apstatusAGE'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				EditData(cid,sid,did,pid,coid,hid,mid,sts);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var dcid = document.getElementById('deleteID'+val).value;
			
			if (confirm('Are You Sure You Want to Delete Detail ?')) {
				deleteData(dcid);
			} else {
				return false;
			}
		})
	});
}
function getpop() {
	$("#view_tbl").hide();
	var course_id = $("#course_id").val();
	var temp_count = 0;
	 $("table#pop > tbody").empty();
	$.post("gettable6HDistributionofPracticalExam1viewdata?"+key+"="+value,{course_id:course_id},function(j) {
		$("table#pop").append('<tr>' 
				+'<td colspan="10"><span><p id="sn"><p>Practical '+j[0]["practical_marks"]+' Marks + (Viva '+j[0]["viva_marks"]+'+ IA '+j[0]["ia_marks"]+') Marks</p></p></span></td>'
				+'</tr>');
	  });
	
	$.post("gettable6HDistributionofPracticalExamviewdata?"+key+"="+value,{course_id:course_id},function(j) {
		for(var i=0;i<j.length;i++){
				$("table#pop").append('<tr>' 
							+'<td><p id="sn">'+j[i][0]+'</p></td>'
							+'<td><p id="heads">'+j[i][1]+'</p></td>'
							+'<td><p id="marks">'+j[i][2]+'</p></td>'
							+'</tr>');
				temp_count += parseInt(j[i][2]);
		}
		$("table#pop").append('<tr>' 
				+'<td><p id="sn"></p></td>'
				+'<td><p id="heads">Total Marks</p></td>'
				+'<td><p id="marks">'+temp_count+'</p></td>'
				+'</tr>');
  });
	$("#pop").show();
}
</script>
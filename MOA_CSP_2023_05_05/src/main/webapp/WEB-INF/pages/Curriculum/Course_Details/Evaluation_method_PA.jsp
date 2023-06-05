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
<script type="text/javascript" src="js\watermark\common.js"></script>
<!-- <link href="js/dropDown/select2.min.css" rel="Stylesheet"></link> -->
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span>T6-d-Evaluation Method Periodical
							Assessment
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">T6-d-Evaluation
									Method Periodical Assessment</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="evalu_method_pa" id="evalu_method_pa"
						action="evalu_method_paAction" method='POST'
						commandName="evalu_method_paCMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">T6-d-Evaluation Method Periodical
									Assessment</h6>
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
												<select class="singleselect form-control form-control-lg"
													name="degree_id" id="degree_id" class="form-control">
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
											<label for="text-input">Subject<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="course_id" class="form-control" id="course_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Evaluation Method PA<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="evaluation_method_pa_id"
													class="select2 narrow wrap form-control form-control-lg form-control-a disablecopypaste"
													id="evaluation_method_pa_id">
													<option value="0">--Select--</option>
<%-- 													<c:forEach var="item" items="${getEval_Method_PA}" --%>
<%-- 														varStatus="num"> --%>
<%-- 														<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 													</c:forEach> --%>
												</select>
											</div>
										</div>
									</div>
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
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" /> <input type='hidden' id='id'
												name="id" value='0' />
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
											<li id="btn-reload1"><a href="Evalu_Method_PA_Url"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
													<i class="lni lni-chevron-left"></i>Back
											</a></li>

											<li id="btn-update"><input
												class="main-btn deactive-btn btn-hover btnupda"
												type="button" value="Update" /></li>

											<li id="btn-reload"><a
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>

											<li id="btn-save"><input value="Save"
												class="main-btn info-btn btn-hover btnsave" type="button" /></li>

											<li id="btn-reload2"><a href="Evalu_Method_PA_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>

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
				<section class="single-detail-block">
					<div class="tables-wrapper">
						<div class="row" id="pop">
							<div class="col-lg-12">
								<div class="card-style mb-30">
									<h6 class="mb-10">Evaluation Method PA</h6>
									<!-- 								<h3>Evaluation Method PA</h3> -->
									<div class="table-wrapper table-responsive custom-table"
										id="container-table">
										<table class="table" id="popT1">
										
											<thead>

												<tr>
													<th class="middle-center"><label class="ml-5 bold">Sr
															No.</label></th>
													<th class="middle-center"><label class="ml-5 bold">Evaluation
															Methods for Periodical Assessment</label></th>
												</tr>

											</thead>
											<tbody></tbody>
										</table>
									</div>
								</div>
								<!-- 						end card -->
							</div>
							<!-- 				end col -->
						</div>
					</div>
				</section>
			</div>
			<div class="row" id="view_tbl">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="table-wrapper table-responsive custom-datatable-p">
							<table class="table" id="Search_Evalu_Method_PA">
								<thead>
									<tr>
										<th id="1"><h6>Sr No</h6></th>
										<th id="9"><h6>System</h6></th>
										<th id="10"><h6>degree</h6></th>
										<th id="11"><h6>Professional</h6></th>
										<th id="12"><h6>Subject</h6></th>
										<th id="13"><h6>Evaluation Method PA</h6></th>
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
		</div>
	</div>
</section>

<c:url value="Evalu_Method_PA_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	$("#pop").hide();
	$("#btn-update").hide();
	$("#btn-reload1").hide();
	
	mockjax1('Search_Evalu_Method_PA');
	table = dataTable('Search_Evalu_Method_PA');
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

function getEvaluationlistbysystem() {
	var system_id = $("#system_id").val();
	$
			.post('getEval_Method_PA?' + key + "=" + value, {
				system_id : system_id
			})
			.done(
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#evaluation_method_pa_id").html(options);
					});
}
function editData(id,system_id,degree_id,professional_id,course_id,evaluation_method_pa_id,status) {
	
	$("#btn-update").show();
	$("#btn-save").hide();
	$("#btn-reload").hide();
	$("#btn-reload2").hide();
	$("#btn-reload1").show();
	$("#view_tbl").hide();
	$("#btn-view").hide();
	
	document.getElementById('lbladd').innerHTML = "Update ";
	$("select#system_id").val(system_id);
	$('#system_id').trigger('change');
	getdegreelistbysystem();
	$("select#degree_id").val(degree_id);
	$("select#professional_id").val(professional_id);
	$('#professional_id').trigger('change');
	getcourselistby_professional();
	$("select#course_id").val(course_id);
	getEvaluationlistbysystem();
	$("select#evaluation_method_pa_id").val(evaluation_method_pa_id);
	$('#evaluation_method_pa_id').trigger('change');
	$("select#status").val(status);
	document.getElementById('id').value=id;
}
function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}

function setTimeLoadForTable(){
	
	document.querySelectorAll('.ADDEvalPa').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('apId'+val).value;
			var haps = document.getElementById('aps'+val).value;
			var hapd = document.getElementById('apd'+val).value;
			var happ = document.getElementById('app'+val).value;
			var hapc = document.getElementById('apc'+val).value;
			var happid = document.getElementById('appid'+val).value;
			var hstatus = document.getElementById('apstatus'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData(hid,haps,hapd,happ,hapc,happid,hstatus);
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
function data(Search_Evalu_Method_PA) {
	
	jsondata = [];
	var table = $('#' + Search_Evalu_Method_PA).DataTable();
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
	var evaluation_method_pa_id = $("#evaluation_method_pa_id").val();
	var status = $("#status").val();

	$.post("getFilterEvalu_Method_PA_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		system_id : system_id,
		degree_id : degree_id,
		professional_id : professional_id,
		course_id : course_id,
		evaluation_method_pa_id : evaluation_method_pa_id,
		status : status

	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].ser,j[i].system_name,j[i].degree_name,j[i].professional, j[i].course_name,j[i].evaluation_method_pa, j[i].action ]);
		}
	});
	$.post("getTotalEvalu_Method_PA_dataCount?" + key + "=" + value, {
		Search : Search,
		system_id : system_id,
		degree_id : degree_id,
		professional_id : professional_id,
		course_id : course_id,
		evaluation_method_pa_id : evaluation_method_pa_id,
		status : status
	}, function(j) {
		FilteredRecords = j;
	});
	setTimeout(setTimeLoadForTable, 1000);
}

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('btn-save').onclick = function() {
		if(Validation()){
			 $("#evalu_method_pa").submit();
		}
	};
	document.getElementById('btn-update').onclick = function() {
		return UpdateFn();
	};
	document.getElementById('btn-view').onclick = function() {
		return View_Validation();
	};
	document.getElementById('system_id').onchange = function() {
		getdegreelistbysystem();
		getEvaluationlistbysystem();
	};
	document.getElementById('professional_id').onchange = function() {
		getcourselistby_professional();
	};
// 	document.getElementById('system_id').onchange = function() {
// 		getEvaluationlistbysystem();
// 	};
	
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
	if ($("#evaluation_method_pa_id").val().trim() == "0") {
		alert("Please Select Evaluation Method PA.");
		$("select#evaluation_method_pa_id").focus();
		return false;
	}
	if ($("select#status").val() == "2") {
		alert("Only Select Active Status.");
		$("select#status").focus();
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

function getpop() {
	$("#view_tbl").hide();
	
	var course_id = $("#course_id").val();
	 $("table#popT1 > tbody").empty();
// 	 $("table#popT1").find('thead').empty();
	$.post("getView_Evaluation_PA_data?"+key+"="+value,{course_id:course_id},function(j) {
		for(var i=0;i<j.length;i++){
			paper = j[i][2];
				$("table#popT1").append('<tr id="popT1R">' 
							+'<td><label id="sr_no">'+j[i][0]+'</label></td>'
							+'<td><label id="evaluation_method_pa">'+j[i][1]+'</label></td>'
							+'</tr>');
		}
  });
	
	$("#pop").show();
}

</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<!-- <link href="js/dropDown/select2.min.css" rel="Stylesheet"></link> -->
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->


<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">

						<h2>
							<span id="lbladd1"></span> Reference and Resourses
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Reference
									and Resourses</li>
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
					<form:form name="list_topics" id="list_topics"
						action="Reference_Resourses_Action" method='POST'
						commandName="Reference_Resourses_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Reference and Resourses</h6>
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

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<!-- 								<div class="select-style-1"> -->
											<label for="text-input">Reference And Resourses<span
												class="mandatory">*</span></label> <input type="text" id="resource"
												name="resource"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="1000" placeholder="Resource" />
											<!-- 										<div class="select-position"> -->
											<!-- 										   <select name="refresource_id" id="refresource_id"> -->
											<!-- 									         <option value="0">--Select--</option> -->
											<%-- 												<c:forEach var="item" items="${getRefResourceList}" varStatus="num"> --%>
											<%-- 													<option value="${item.id}" name="${item.RefResource}">${item.RefResource}</option> --%>
											<%-- 												</c:forEach> --%>
											<!-- 								    		</select>										 -->
											<!-- 								      </div> -->
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
											<input type='hidden' id="eid" name="eid" value="0" /> <input
												type="hidden" id="count_hidden_att" name="count_hidden_att"
												class="form-control autocomplete" value="1">
										</div>
									</div>
									<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
									<!-- 								<div class="select-style-1"> -->
									<!-- 									<label for="text-input">Paper<span class="mandatory">*</span></label> -->
									<!-- 										<div class="select-position"> -->
									<!-- 										   <select name="paper_id" id="paper_id"> -->
									<!-- 									         <option value="0">--Select--</option> -->
									<%-- 												<c:forEach var="item" items="${getpaperList}" varStatus="num"> --%>
									<%-- 													<option value="${item.id}" name="${item.paper}">${item.paper}</option> --%>
									<%-- 												</c:forEach> --%>
									<!-- 								    		</select>										 -->
									<!-- 								      </div> -->
									<!-- 									</div>								 -->
									<!-- 							</div> -->
								</div>
							</div>


							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li id="btn-reload1"><a href="Reference_Resourses_Url"
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

											<li id="btn-reload2"><a href="Reference_Resourses_Url"
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
						<div id="pop">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="card-style mb-30">
									<div class="custom-multi-table">
									<h6 class="mb-10">Reference and Resourses</h6>
										<div class="table-wrapper table-responsive custom-table custom-table-v2">
											<!-- id="container-table" -->
											<table class="table table-striped" id="popT1">
												<thead>
													<tr>
														<th class="middle-center"><h6 class="ml-5 bold">Sr
																No.</h6></th>
														<th class="middle-center"><h6 class="ml-5 bold">Reference
																and Resourses</h6></th>
													</tr>

												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
									</div>
									<!-- 						end card -->
								</div>
								</div>
								<!-- 				end col -->
							</div>
							</div>
					</form:form>
				</div>
			</div>
			<section class="single-detail-block">
				<div class="row" id="view_tbl">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="search_resourses">
									<thead>
										<tr>
											<th id="1"><h6>Sr No</h6></th>
											<th id="7"><h6>System</h6></th>
											<th id="8"><h6>Degree</h6></th>
											<th id="9"><h6>Professional</h6></th>
											<th id="10"><h6>Subject</h6></th>
											<th id="11"><h6>Resourses</h6></th>
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
<c:url value="getSearch_Reference_Resourses" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="System_name1">
	<input type="hidden" name="System_name1" id="System_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="EditReference_Resourses_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>
<c:url value="delete_Re_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	$("#pop").hide();
	$("#btn-reload1").hide();
	$("#btn-update").hide();
	
    
	
	$("#pop").hide();
	mockjax1('search_resourses');
	table = dataTable('search_resourses');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
});

function setTimeLoadForTable(){

	document.querySelectorAll('.ADDSystem').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdAGE'+val).value;
			var hidsystem = document.getElementById('hidsystem'+val).value;
			var hidprofessional= document.getElementById('hidprofessional'+val).value;
			var hprodegree= document.getElementById('hprodegree'+val).value;
			var hcourse = document.getElementById('hcourse'+val).value;
			var hstatus = document.getElementById('hstatus'+val).value;
			var dpresource = document.getElementById('dpresource'+val).value;
			
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData(hid,hidsystem,hidprofessional,hprodegree,hcourse,hstatus,dpresource);
			} else {
				return false;
			}
		})
	});
	
	document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('deleteID'+val).value;
			
			if (confirm('Are You Sure You Want to Delete Data ?')) {
				deleteData(hid);
			} else {
				return false;
			}
		})
	});
	
	
}
function data(search_resourses) {
	debugger;
		
		jsondata = [];
		var table = $('#' + search_resourses).DataTable();
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
		var resource = $("#resource").val();
		var status = $("#status").val();

		$.post("getFilterr_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			system_id : system_id,
			degree_id : degree_id ,
			professional_id : professional_id,
			course_id : course_id,
			resource : resource,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].system_name, j[i].degree_name,j[i].professional,j[i].course_name,j[i].resource,j[i].action ]);
			}
		});
		$.post("getFilterr_dataCount?" + key + "=" + value, {
			Search : Search,
			system_id : system_id,
			professional_id : professional_id,
			degree_id : degree_id,
			course_id : course_id,
			resource : resource,
			status : status
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
 	}

function editData(id,system_id,degree_id,professional_id,course_id,status,resource) {
	$("#btn-update").show();
	$("#btn-reload1").show();
	$("#btn-save").hide();
	$("#btn-view").hide();
	$("#btn-reload").hide();
	$("#btn-reload2").hide();
	$("#view_tbl").hide();

	document.getElementById('lbladd1').innerHTML = "Update ";
	$("select#system_id").val(system_id);
	$('#system_id').trigger('change');
	getdegreelistbysystem111();
	$("select#degree_id").val(degree_id);
	$("select#professional_id").val(professional_id);
	$('#professional_id').trigger('change');
	getcourselistby_professional();
	$("select#course_id").val(course_id);
	$("input#resource").val(resource);
	$("select#status").val(status);
	
	document.getElementById('eid').value=id;
}
	
// 	function editData(id) {

// 		$("#id2").val(id);
// 		document.getElementById('updateForm').submit();
// 	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	
function getdegreelistbysystem111() {
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


document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('btn-save').onclick = function() {
		if(Validation()){
			 $("#list_topics").submit();
		}
	};
	document.getElementById('btn-update').onclick = function() {
		if(Validation()){
			 $("#list_topics").submit();
		}
	};
	document.getElementById('btn-view').onclick = function() {
		return View_Validation();
	};

	document.getElementById('system_id').onchange = function() {
		getdegreelistbysystem111();
	};
	document.getElementById('professional_id').onchange = function() {
		getcourselistby_professional();
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
	if ($("#resource").val().trim() == "") {
		alert("Please Enter Reference And Resourses.");
		$("#resource").focus();
		return false;
	}
	if ($("select#status").val() == "0") {
		alert("Please Select Status.");
		$("select#status").focus();
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
	$.post("getView_Resource_data?"+key+"="+value,{course_id:course_id},function(j) {
		for(var i=0;i<j.length;i++){
			paper = j[i][2];
				$("table#popT1").append('<tr id="popT1R">' 
							+'<td><p id="sr_no">'+j[i][1]+'</p></td>'
 							+'<td><p id="resource">'+j[i][0]+'</p></td>'
// 							+'<td><p id="po">'+j[i][2]+'</p></td>'
							+'</tr>');
		}
  });
	
	$("#pop").show();
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

</script>
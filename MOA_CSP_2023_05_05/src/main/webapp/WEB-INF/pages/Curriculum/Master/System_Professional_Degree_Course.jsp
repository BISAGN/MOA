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
	<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">

						<h2>
							<span id="lbladd"></span> Link System-Degree-Professional-Subject
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Link
									System-Degree-Professional-Subject</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<!-- title-wrapper end -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="list_topics" id="list_topics"
						action="System_Professional_Degree_Course_Action" method='POST'
						commandName="System_Professional_Degree_Course_CMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Link System-Degree-Professional-Subject</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">System<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="system_id" id="system_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getSystemList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
									<label for="username">Type Of Degree<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="type_of_degree" id="type_of_degree" class="singleselect form-control form-control-lg">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${gettype_of_degree}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
									</div>
								</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">Degree<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="degree_id" id="degree_id" class="form-control">
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
											<select class="singleselect form-control form-control-lg" name="professional_id" id="professional_id">
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
										<label for="text-input">Subject<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="course_id"class="select2 form-control form-control-lg" id="course_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCourseList}"
 													varStatus="num"> --%>
 													<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
 												</c:forEach> 
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="status" id="status">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
										<div>
										<input type="hidden" id="count_hidden_att"
								name="count_hidden_att" class="form-control autocomplete"
								value="1">
								<input type='hidden' id="eid" name="eid" value="0" />
								</div>
									</div>
								</div>
							</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">

							<ul class="buttons-group mainbtn">
							
									<li>
						           <a id="btn-reload1" href="System_Professional_Degree_Course_Url" class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"></i>Back</a>
						        </li>
						        
						        <li><input id="btn-update"
									class="main-btn deactive-btn btn-hover btnupda" type="button"
									value="Update" /></li>
									
								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input value="Save" id="btn-save"
									class="main-btn info-btn btn-hover btnsave" type="button" /></li>
								
								<li><a id="btn-reload2" href="System_Professional_Degree_Course_Url"
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
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_system">
							<thead>
								<tr>
									<th id="1"><h6>Ser No</h6></th>
									<th id="3"><h6>System</h6></th>
									<th id="4"><h6>Type of Degree</h6></th>
									<th id="4"><h6>Degree</h6></th>
									<th id="5"><h6>Professional</h6></th>
									<th id="6"><h6>Subject</h6></th>
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
<c:url value="getSearch_sdpcl_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="System_name1">
	<input type="hidden" name="System_name1" id="System_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="EditSysDegProfCourselink_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>
<c:url value="delete_sdpcl_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Systemreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	$("#btn-update").hide();
	$("#btn-reload1").hide();

	mockjax1('search_system');
	table = dataTable('search_system');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$('.UpperClassName').keyup(function() {
		this.value = this.value.toUpperCase();
	});
	if(window.location.href.includes("msg"))
	{
		 var url = window.location.href.split("?msg")[0];
		 window.location = url;
	}
});

function setTimeLoadForTable(){

	
	document.querySelectorAll('.ADDSystem').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var hid = document.getElementById('apIdAGE'+val).value;
			var hidsystem = document.getElementById('hidsystem'+val).value;
			var hidtype_of_degree1 = document.getElementById('hidtype_of_degree'+val).value;
			var hprodegree= document.getElementById('hprodegree'+val).value;
			var hidprofessional= document.getElementById('hidprofessional'+val).value;
		
			var hcourse = document.getElementById('hcourse'+val).value;
			var hstatus = document.getElementById('hstatus'+val).value;
			
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData(hid,hidsystem,hidtype_of_degree1,hprodegree,hidprofessional,hcourse,hstatus);
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
function data(search_system) {
		
		jsondata = [];
		var table = $('#' + search_system).DataTable();
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
		var type_of_degree = $("#type_of_degree").val();
		var professional_id = $("#professional_id").val();
		var course_id = $("#course_id").val();
		var status = $("#status").val();
		

		$.post("getFiltesdpcl_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			system_id : system_id,
			type_of_degree : type_of_degree,
			degree_id : degree_id ,
			professional_id : professional_id,
			course_id : course_id,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].system_name,j[i].type_of_degree, j[i].degree_name,j[i].professional,j[i].course_name,j[i].action ]);
			}
		});
		$.post("getFiltesdpcl_dataCount?" + key + "=" + value, {
			Search : Search,
			system_id : system_id,
			type_of_degree :type_of_degree ,
			degree_id : degree_id,
			professional_id : professional_id,
			course_id : course_id,
			status : status
		}, function(j) {

			FilteredRecords = j;

		});

		setTimeout(setTimeLoadForTable, 1000);
 	}

function editData(id,system_id,type_of_degree,degree_id,professional_id,course_id,status) {
	$("#btn-update").show();
	$("#btn-save").hide();
	$("#tbl").hide();
	$("#btn-reload").hide();
	$("#btn-reload2").hide();
	$("#btn-reload1").show();
	
	
	document.getElementById('lbladd').innerHTML = "Update ";
	$("select#system_id").val(system_id);
	$('#system_id').trigger('change');
	$("select#type_of_degree").val(type_of_degree);
	$('#type_of_degree').trigger('change');
	getdegreelistbysystem_type_degree();
	$("select#degree_id").val(degree_id);
	$('#degree_id').trigger('change');
	$("select#professional_id").val(professional_id);
	$('#professional_id').trigger('change');
	$("select#course_id").val(course_id);
	$('#course_id').trigger('change');
	$("select#status").val(status);
	document.getElementById('eid').value=id;
}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Search() {
		$("#System_name1").val($('#system_name').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
	}
	
// function getdegreelistbysystem111() {
// 	var system_name = $("#system_id").val();
// 	$
// 			.post('getDegreeListbysystem1?' + key + "=" + value, {
// 				system_name : system_name
// 			})
// 			.done(
// 					function(j) {
// 						var options = '<option value="' + "0" + '">'
// 								+ "--Select--" + '</option>';
// 						for (var i = 0; i < j.length; i++) {
// 							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
// 									+ j[i][1] + '</option>';
// 						}
// 						$("select#degree_id").html(options);
// 					});
// }

// function getdegreelistbysystem_type_degree() {
// 	var system_id = $("#system_id").val();
// 	var type_of_degree = $("#type_of_degree").val();
	
// 	$.post('getDegreelistbySystem_type_Degree_Curri?' + key + "=" + value,{  
// 		system_id : system_id,
// 		type_of_degree : type_of_degree
// 		}).done(function(j) {
// 						var options = '<option value="' + "0" + '">'
// 								+ "--Select--" + '</option>';
// 						for (var i = 0; i < j.length; i++) {
// 							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
// 									+ j[i][1] + '</option>';
// 						}
// 						$("select#degree_id").html(options);
// 	});
// }
function getdegreelistbysystem_type_degree() {
	var system_id = $("#system_id").val();
	var type_of_degree = $("#type_of_degree").val();
	$
			.post('getDegreelistbySystem_type_Degree_Curri?' + key + "=" + value, {
				system_id : system_id,
				type_of_degree : type_of_degree
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

	document.getElementById('type_of_degree').onchange = function() {
		getdegreelistbysystem_type_degree();
	};
// 	document.getElementById('professional_id').onchange = function() {
// 		getcourselistby_professional();
// 	};

});

function Validation() {
	if ($("#system_id").val().trim() == "0") {
		alert("Please Select System.");
		$("select#system_id").focus();
		return false;
	}
	if ($("#type_of_degree").val().trim() == "0") {
		alert("Please Select Type of Degree.");
		$("select#type_of_degree").focus();
		return false;
	}
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
	if ($("#course_id").val().trim() == "0") {
		alert("Please Select Subject .");
		$("select#course_id").focus();
		return false;
	}
	if ($("select#status").val() == "0") {
		alert("Please Select Status.");
		$("select#status").focus();
		return false;
	}
	if ($("select#status").val() == "2") {
		alert("Only Select Active Status");
		$("select#status").focus();
		return false;
	}
	return true;
 }

</script>
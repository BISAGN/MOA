<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
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
						<h2>Module Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Module
									Master</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form action="Module_action" method="POST"
						modelAttribute="Module_cmd">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Module Master</h6>
								<div class="row ">
									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="select-style-1">
											<label>System<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="system_id" id="system_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${s_name}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="select-style-1">
											<label>Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="degree_id" id="degree_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="select-style-1">
											<label>Course<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="course_id"
													class="singleselect form-control form-control-lg"
													id="course_id">
													<option value="0">--Select--</option>

												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="input-style-1">
											<label for="username">Module<span class="mandatory">*</span></label>
											<input id="module_name" name="module_name" autocomplete="off"
												maxlength="100" type="text" placeholder="Module" />
										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="select-style-1">
											<label for="username">Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="status" id="status"
													class="singleselect form-control form-control-lg">
													<c:forEach var="item" items="${ActiveInActiveList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group d-flex justify-content-center">
											<li><a type="button" id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><input type="submit" id="btn-save"
												class="main-btn info-btn btn-hover btnsave" value="Save" /></li>
											<li><a href="module_url"
												class="main-btn dark-btn n btn-hover btnreset">Reset</a></li>
										</ul>
									</div>
								</div>

							</div>
						</div>
						<section class="single-detail-block">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="card-style mb-30">
										<div class="table-wrapper table-responsive custom-datatable-p">
											<table class="table" id="search_Module">
												<thead>
													<tr>
														<th align="center"><h6>Sr No</h6></th>
														<th><h6>System</h6></th>
														<th><h6>Degree</h6></th>
														<th id="${item.id}"><h6>Course</h6></th>
														<th id="2"><h6>Module</h6></th>
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

					</form:form>
				</div>
			</div>
		</div>
	</div>

</section>


<c:url value="Edit_moduleUrl" var="Edit_moduleUrl" />
<form:form action="${Edit_moduleUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="deletemodule_Url" var="deletemodule_Url" />
<form:form action="${deletemodule_Url}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>



<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_Module');
		table = dataTable('search_Module');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
	});
	
	document.addEventListener('DOMContentLoaded', function () {	
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem();
		};
		
		document.getElementById('degree_id').onchange = function() {
			getcourselistbydegree();
		};
		
	
// 		document.getElementById('module_name').onkeypress = function() {
//     	return onlyAlphabetsStringSpace(event,this);		
// 		};
		
		document.getElementById('module_name').onkeypress = function() {
			return onlyAlphaNumeric(event,this);		
		};
	});
	
	function setTimeLoadForTable(){
		
		
		document.querySelectorAll('.ADDModule').forEach((items, index) => {
			
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var mid = document.getElementById('ModId'+val).value;
 				var mname = document.getElementById('ModName'+val).value;
				var mstatus = document.getElementById('ModStatus'+val).value;
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(mid,mname,mstatus);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.deleteModule').forEach((items, index) => {
			items.addEventListener('click', event => {
			
				var val=parseInt(index)+1;
				
				var dmodid = document.getElementById('DModId'+val).value;
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(dmodid);
				} else {
					return false;
				}
			})
		});
		
	}

	function data(search_Module) {
		jsondata = [];
		var table = $('#' + search_Module).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		var course_id = $("#course_id").val();
		var status = $("#status").val();
		var module_name = $("#module_name").val();

		$.post("getFiltermodule_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			course_id : course_id,
			status : status,
			module_name : module_name,
			degree_id : degree_id,
			system_id : system_id

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].system_name, j[i].degree_name,
						j[i].course_name, j[i].module_name, j[i].action ]);
			}
		});
		$.post("getTotalmodule_dataCount?" + key + "=" + value, {
			course_id : course_id,
			module_name : module_name,
			degree_id : degree_id,
			system_id : system_id

		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}
	function editData(id) {
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

	function getdegreelistbysystem() {
		var system_name = $("#system_id").val();
		$
				.post('getdegreelistby_system?' + key + "=" + value, {
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

	function getcourselistbydegree() {
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		$
				.post('getcourselistby_degree_andsystem?' + key + "=" + value,
						{
							degree_id : degree_id,
							system_id : system_id
						})
				.done(
						function(j) {
							
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								//alert(j[i][0])
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_id").html(options);
						});
	}

	function Validation() {

		if ($("#system_id").val() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("#degree_id").val() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		if ($("#course_id").val() == "0") {
			alert("Please Select Course.");
			$("select#course_id").focus();
			return false;
		}
		if ($("#module_name").val().trim() == "") {
			alert("Please Enter Module.");
			$("input#module_name").focus();
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
</script>
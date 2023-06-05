<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<%-- <script nonce="${cspNonce}" type="text/javascript"> --%>
<%-- // 	var username = "${username}"; --%>
<!-- </script> -->

<section class="dashboard-page">
<div class="container-fluid">
	<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2> Course Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Course Master</li>
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
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form action="Course_Master_action" method="POST" modelAttribute="Course_Master_cmd">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Course Master</h6>
							<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
										<label>Type of Lecture<strong class="mandatory">*</strong></label>
										<div class="select-position">
											<select name="type_of_content_id" id="type_of_content_id" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${TypeOfcontent}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-1">
										<label>Course Name<span class="mandatory">*</span></label> 
										<input id="course_name" name="course_name" class="form-control" autocomplete="off" maxlength="100" placeholder="Course Name" />
									<input type="hidden" id="id" name="id" value="0"
										class="form-control" autocomplete="off" />
									</div>
									<!-- end input -->
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-1">
										<label>Course Code<span class="mandatory">*</span></label> 
										<input id="course_code" name="course_code" class="form-control" autocomplete="off" minlength="03" maxlength="15" placeholder="Course Code" />
									<input type="hidden" id="id" name="id" value="0"
										class="form-control" autocomplete="off" />
									</div>
									<!-- end input -->
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="select-style-1">
										<label>Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">
												<c:forEach var="item" items="${ActiveInActiveList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
			
											</select>
										<input type="hidden" id="id" name="id" value="0"
										class="form-control" autocomplete="off" />
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
									<li><a type="button" id="btn-reload"
										class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"><i
											class="lni lni-search-alt"></i>Search</a></li>
									<li><input type="submit" id="btn-save" class="main-btn info-btn btn-hover btnsave" value="Save"></li>
									<li><a href="course_master_url" class="main-btn dark-btn btn-hover btnreset">Reset</a></li>
								</ul>
								</div>
								</div>
								</div>
								<!-- Bottom Button End -->
						</div>
						<!-- end card -->
					
		<section class="single-detail-block">
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="card-style mb-30">
				<div class="table-wrapper table-responsive custom-datatable-p">
					<table class="table" id="search_course_master">
						<thead>
							<tr>
								<th id="1"><h6>Sr NO</h6></th>
								<th id="3"><h6>Type of lecture</h6></th>
								<th id="4"><h6>Course name</h6></th>
								<th id="5"><h6>Course code</h6></th>
								<th><h6>Action</h6></th>
							</tr>
						</thead>
					</table>
					<!-- 				end table -->
				</div>
			</div>
			<!-- 		end card -->
		</div>
		<!-- 	end col -->
	</div>
	</section>
	</form:form>
				</div>
			</div>
	</div>
</div>
</section>

<c:url value="Edit_course_mstrUrl" var="Edit_course_mstrUrl" />
<form:form action="${Edit_course_mstrUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="delete_course_mstr_Url" var="delete_course_mstr_Url" />
<form:form action="${delete_course_mstr_Url}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	
	$(document).ready(function() {

		mockjax1('search_course_master');
		table = dataTable('search_course_master');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
		

	});

	function data(search_course_master) {
		
		jsondata = [];
		var table = $('#' + search_course_master).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id')
				.toLowerCase();
		var orderType = order[0][1];
		var type_of_content_id = $("#type_of_content_id").val();
		var course_name = $("#course_name").val();
		var course_code = $("#course_code").val();
		var status = $("#status").val();

		$.post("getFilter_Course_master_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			type_of_content_id : type_of_content_id,
			course_name : course_name,
			course_code : course_code,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,j[i].type_of_content, j[i].course_name, j[i].course_code, j[i].action ]);
			}
		});
		$.post("getTotal_Course_master_dataCount?" + key + "=" + value, {
			Search : Search,
			type_of_content_id : type_of_content_id,
			course_name : course_name,
			course_code : course_code
		}, function(j) {

			FilteredRecords = j;

		});
		
		setTimeout(setTimeLoadForTable, 1000);
	}
	
	function EditData(id) {
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}

	
	function Validation() {

		if ($("#type_of_content_id").val().trim() == "0") {
			alert("Please Select Type of Lecture.");
			$("select#type_of_content_id").focus();
			return false;
		}
		if ($("#course_name").val().trim() == "") {
			alert("Please Enter Course Name.");
			$("input#course_name").focus();
			return false;
		}
		
		if ($("#course_code").val().trim() == "") {
			alert("Please Enter Course Code.");
			$("input#course_code").focus();
			return false;
		}

		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}

		return true;

	}

document.addEventListener('DOMContentLoaded', function () {	
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	
//		document.getElementById('course_name').onkeypress = function() {
//			return onlyAlphabetsStringSpace(this,event);
//		};
		
		});
	
function setTimeLoadForTable(){
		
		
		document.querySelectorAll('.ADDcourse').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var cid = document.getElementById('CourseId'+val).value;
				var ctoc = document.getElementById('contentId'+val).value;
				var cname = document.getElementById('CourseName'+val).value;
				var ccode = document.getElementById('CourseCode'+val).value;				
				var cstatus = document.getElementById('CourseStatus'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					EditData(cid,ctoc,cname,ccode,cstatus);
				} else {
					return false;
				}
			})
		});
		document.querySelectorAll('.deleteCourse').forEach((items, index) => {
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
	
	
</script>





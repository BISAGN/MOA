<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
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

<section class="dashboard-page search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>College Staff List Report</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									College Staff List Report</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<div class="form-elements-wrapper search-regulation-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="Add_Staff_Report" id="Add_Staff_Report"
						action="Add_Staff_ReportAction?${_csrf.parameterName}=${_csrf.token}"
						method="post" modelAttribute="Add_Staff_Report_CMD">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">College Staff List Report</h6>
								<div class="row">
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="select-style-1">
											<label>College Staff List </label>
											<div class="select-position">
												<select name="college_staff_list" id="college_staff_list"
													class="form-control">
													<option value="0" selected="selected">-- Select--</option>
													<option value="1">Teaching Faculty</option>
													<option value="2">Guest Faculty</option>
													<option value="3">Non Teaching Staff</option>

												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Name</label> <input type="text"
												id="teach_faclt_name" name="teach_faclt_name"
												class="form-control" placeholder="Name">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-2">
											<label>Appointment date </label> <input
												type="text" name="teach_faclt_name_appoint_date"
												id="teach_faclt_name_appoint_date"
												class="form-control-sm form-control effect-9 "
												aria-required="true" autocomplete="off" value="DD/MM/YYYY">
										</div>
										<input type="hidden" id="yrr" name="yrr" value="">
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Nature of Appointment</label> <input
												type="text" id="teach_faclt_nat_of_appoin"
												name="teach_faclt_nat_of_appoin" class="form-control"
												placeholder="Nature of Appointment">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Employee ID</label> <input
												type="text" id="teach_faclt_emp_id"
												name="teach_faclt_emp_id" class="form-control"
												placeholder="Employee ID">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Department</label> <input type="text"
												id="teach_faclt_depart" name="teach_faclt_depart"
												class="form-control"
												placeholder="Department">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Qualification</label> <input
												type="text" id="teach_faclt_quali" name="teach_faclt_quali"
												class="form-control"
												placeholder="Qualification">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Aadhaar No.</label> <input
												type="text" id="teach_faclt_aadhar"
												name="teach_faclt_aadhar" class="form-control"
												placeholder="Aadhaar No.">
										</div>
									</div>

<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label>Guest Faculty Name</label> <input type="text" -->
<!-- 												id="guest_faclt_name" name="guest_faclt_name" -->
<!-- 												class="form-control" placeholder="Guest Faculty Name"> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-2"> -->
<!-- 											<label>Guest Faculty Appointment date </label> <input -->
<!-- 												type="text" name="guest_faclt_name_appoint_date" -->
<!-- 												id="guest_faclt_name_appoint_date" -->
<!-- 												class="form-control-sm form-control effect-9 " -->
<!-- 												aria-required="true" autocomplete="off" value="DD/MM/YYYY"> -->
<!-- 										</div> -->
<!-- 										<input type="hidden" id="yrr" name="yrr" value=""> -->
<!-- 									</div> -->
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label>Guest Faculty Employee ID</label> <input -->
<!-- 												type="text" id="guest_faclt_emp_id" -->
<!-- 												name="guest_faclt_emp_id" class="form-control" -->
<!-- 												placeholder="Guest Faculty Employee ID"> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label>Guest Faculty Department</label> <input type="text" -->
<!-- 												id="guest_faclt_depart" name="guest_faclt_depart" -->
<!-- 												class="form-control" -->
<!-- 												placeholder="Guest Faculty Department"> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label>Guest Faculty Qualification</label> <input -->
<!-- 												type="text" id="guest_faclt_quali" name="guest_faclt_quali" -->
<!-- 												class="form-control" -->
<!-- 												placeholder="Guest Faculty Qualification"> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label>Guest Faculty Aadhaar No.</label> <input -->
<!-- 												type="text" id="guest_faclt_aadhar" -->
<!-- 												name="guest_faclt_aadhar" class="form-control" -->
<!-- 												placeholder="Guest Faculty Aadhaar No."> -->
<!-- 										</div> -->
<!-- 									</div> -->
									
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label>Non Teaching Staff Name</label> <input type="text" -->
<!-- 												id="non_teach_staff_name" name="non_teach_staff_name" -->
<!-- 												class="form-control" placeholder="Non Teaching Staff Name"> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-2"> -->
<!-- 											<label>Non Teaching Staff Appointment date </label> <input -->
<!-- 												type="text" name="non_teach_staff_name_appoint_date" -->
<!-- 												id="non_teach_staff_name_appoint_date" -->
<!-- 												class="form-control-sm form-control effect-9 " -->
<!-- 												aria-required="true" autocomplete="off" value="DD/MM/YYYY"> -->
<!-- 										</div> -->
<!-- 										<input type="hidden" id="yrr" name="yrr" value=""> -->
<!-- 									</div> -->
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label>Non Teaching Staff Employee ID</label> <input -->
<!-- 												type="text" id="non_teach_staff_emp_id" -->
<!-- 												name="non_teach_staff_emp_id" class="form-control" -->
<!-- 												placeholder="Non Teaching Staff Employee ID"> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label>Non Teaching Staff Department</label> <input type="text" -->
<!-- 												id="non_teach_staff_depart" name="non_teach_staff_depart" -->
<!-- 												class="form-control" -->
<!-- 												placeholder="Non Teaching Staff Department"> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label>Non Teaching Staff Qualification</label> <input -->
<!-- 												type="text" id="non_teach_staff_quali" name="non_teach_staff_quali" -->
<!-- 												class="form-control" -->
<!-- 												placeholder="Non Teaching Staff Qualification"> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label>Non Teaching Staff Aadhaar No.</label> <input -->
<!-- 												type="text" id="non_teach_staff_aadhar" -->
<!-- 												name="non_teach_staff_aadhar" class="form-control" -->
<!-- 												placeholder="Non Teaching Staff Aadhaar No."> -->
<!-- 										</div> -->
<!-- 									</div> -->
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">

										<ul class="buttons-group mainbtn">
											<li><a href="add_staff"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><a type="button"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												id="btn-reload" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Add_Staff_Report_url"
												class="main-btn dark-btn btn-hover btnreset" value="Reset">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->

						</div>

						<section class="single-detail-block">
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="card-style mb-30">

											<div
												class="table-wrapper table-responsive custom-datatable-p">
												<table id="getSearch_College_Staff_List" class="table">
													<thead>
														<tr>

															<th><h6>Sr No</h6></th>
															<th><h6>Name</h6></th>
															<th><h6>Appointment date</h6></th>
															<th><h6>Nature of Appointment</h6></th>
															<th><h6>Employee ID</h6></th>
															<th><h6>Department</h6></th>
															<th><h6>Qualification</h6></th>
															<th><h6>Aadhaar No.</h6></th>
															<th><h6>Action</h6></th>
														</tr>
														<!-- end table row-->
													</thead>
													<tbody class="custom-datatablepra">

													</tbody>
												</table>
												<!-- end table -->
											</div>
										</div>
										<!-- end card -->
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


<%-- <c:url value="Edit_Teacher_dtlUrl" var="editUrl" /> --%>
<%-- <form:form action="${editUrl}" method="post" id="updateForm" --%>
<%-- 	name="updateForm" modelAttribute="id2"> --%>
<!-- 	<input type="hidden" name="id2" id="id2"> -->
<%-- </form:form> --%>

<%-- <c:url value="delete_Teacher_dtl_Url" var="deleteUrl" /> --%>
<%-- <form:form action="${deleteUrl}" method="post" id="deleteForm" --%>
<%-- 	name="deleteForm" modelAttribute="id1"> --%>
<!-- 	<input type="hidden" name="id1" id="id1" value="0" /> -->
<%-- </form:form> --%>

<c:url value="View_Search_College_Staff_ListUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>

<%-- <c:url value="App_Teacher_dtlUrl" var="App_MedUrl" /> --%>
<%-- <form:form action="${App_MedUrl}" method="post" id="AppForm" --%>
<%-- 	name="AppForm" modelAttribute="id7"> --%>
<!-- 	<input type="hidden" name="id7" id="id7" value="0" /> -->
<!-- 	<input type="hidden" name="status7" id="status7" value="0" /> -->
<%-- </form:form> --%>


<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {

	datepicketDate('teach_faclt_name_appoint_date');
// 	datepicketDate('date_of_birth');
	
	mockjax1('getSearch_College_Staff_List');
	table = dataTable('getSearch_College_Staff_List');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
});

function data(getSearch_College_Staff_List) {
	
	jsondata = [];
var table = $('#' + getSearch_College_Staff_List).DataTable();
	var info = table.page.info();
//		var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];

	var college_staff_list = $("#college_staff_list").val();
	var teach_faclt_name = $("#teach_faclt_name").val();
	var teach_faclt_name_appoint_date = $("#teach_faclt_name_appoint_date").val();
	var teach_faclt_nat_of_appoin = $("#teach_faclt_nat_of_appoin").val();
	var teach_faclt_emp_id = $("#teach_faclt_emp_id").val();
	var teach_faclt_depart = $("#teach_faclt_depart").val();
	var teach_faclt_quali = $("#teach_faclt_quali").val();
	var teach_faclt_aadhar = $("#teach_faclt_aadhar").val();
	
	$.post("getFilterSearch_College_Staff_List_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		college_staff_list : college_staff_list,
		first_name : teach_faclt_name,
		date_of_appoinment : teach_faclt_name_appoint_date,
		nature_of_appoinment : teach_faclt_nat_of_appoin,
		emp_id : teach_faclt_emp_id,
		emp_department : teach_faclt_depart,
		emp_qualification : teach_faclt_quali,
		aadhar_no : teach_faclt_aadhar,
		guest_first_name : teach_faclt_name,
		guest_date_of_appoinment : teach_faclt_name_appoint_date,
		guest_nature_of_appoinment : teach_faclt_nat_of_appoin,
		guest_emp_id : teach_faclt_emp_id,
		guest_emp_department : teach_faclt_depart,
		guest_emp_qualification : teach_faclt_quali,
		guest_aadhar_no : teach_faclt_aadhar,
		non_first_name : teach_faclt_name,
		non_date_of_appoinment : teach_faclt_name_appoint_date,
		non_nature_of_appoinment : teach_faclt_nat_of_appoin,
		non_emp_id : teach_faclt_emp_id,
		non_emp_department : teach_faclt_depart,
		non_emp_qualification : teach_faclt_quali,
		non_aadhar_no : teach_faclt_aadhar

		
	}, function(j) {
var ser=0;
		for (var i = 0; i < j.length; i++) {
			ser = i+1;
			var date_of_appoinment=""
				if (j[i][2] !="" && j[i][2] !=null && j[i][2] !="null") {
					date_of_appoinment= j[i][2];
				}else {
					date_of_appoinment="-";
				}
			
			jsondata.push([ ser,j[i][1],date_of_appoinment, j[i][3],j[i][4],j[i][5],
				j[i][6],j[i][7],j[i][8]]);
			
		}
	});
	$.post("getTotalSearch_College_Staff_List_dataCount?" + key + "=" + value, {
		Search : Search,
		college_staff_list : college_staff_list,
		first_name : teach_faclt_name,
		date_of_appoinment : teach_faclt_name_appoint_date,
		nature_of_appoinment : teach_faclt_nat_of_appoin,
		emp_id : teach_faclt_emp_id,
		emp_department : teach_faclt_depart,
		emp_qualification : teach_faclt_quali,
		aadhar_no : teach_faclt_aadhar,
		guest_first_name : teach_faclt_name,
		guest_date_of_appoinment : teach_faclt_name_appoint_date,
		guest_nature_of_appoinment : teach_faclt_nat_of_appoin,
		guest_emp_id : teach_faclt_emp_id,
		guest_emp_department : teach_faclt_depart,
		guest_emp_qualification : teach_faclt_quali,
		guest_aadhar_no : teach_faclt_aadhar,
		non_first_name : teach_faclt_name,
		non_date_of_appoinment : teach_faclt_name_appoint_date,
		non_nature_of_appoinment : teach_faclt_nat_of_appoin,
		non_emp_id : teach_faclt_emp_id,
		non_emp_department : teach_faclt_depart,
		non_emp_qualification : teach_faclt_quali,
		non_aadhar_no : teach_faclt_aadhar
		
	}, function(j) {
		
		FilteredRecords = j;
		});
	setTimeout(setTimeLoadForTable, 1000);
}

function setTimeLoadForTable() {
	
	document.querySelectorAll('.viewData').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('viewId'+val).value;
			
			if (confirm('Are You Sure You Want to View Detail ?')) {
				ViewData(hid);
			} else {
				return false;
			}
		})
	});
	
}



function ViewData(id){
	$("#id6").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
}

</script>

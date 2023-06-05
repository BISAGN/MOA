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
						<h2>Hospital Staff Details</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View Hospital Staff Details</li>
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
					<form:form name="" id=""
						action="Search_College_DepartmentAction?${_csrf.parameterName}=${_csrf.token}"
						method="post" modelAttribute="Search_College_Department_CMD">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">View College Department</h6>
								<div class="row">
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label for="fname">Post</label> 
											<select name="post" id="post"
													class="form-control">
													<option value="0" selected="selected">-- Select--</option>
													<option value="1">Medical Superintendent</option>
													<option value="2">Senior Medical officer</option>
													<option value="3">Medical Officer</option>
													<option value="4">Resident Medical Officer</option>
													<option value="5">Surgeon (General Surgery)</option>
													<option value="6">Anesthetist</option>
													<option value="7">Obstetrician / Gynaecologist</option>
													<option value="8">Radiologist</option>
													<option value="9">Pathologist/ Biochemist</option>
													<option value="10">House Physician (Resident)</option>
													<option value="11">Dispenser</option>
													<option value="12">Laboratory Technician</option>
													<option value="13">X-ray Technician/ Radiographer</option>
													<option value="14">Dresser</option>
													<option value="15">X-ray Attendant</option>
													<option value="16">Nursing Staff Incharge</option>
													<option value="17">Nursing Staff</option>
													<option value="18">Ward Boys/Ayas</option>
													<option value="19">Store Keeper</option>
													<option value="20">Registration Clerk/ Telephone Operator</option>
													<option value="21">Yoga Expert</option>
													<option value="22">Physiotherapist</option>
													<option value="23">Dietician (Part Time)</option>
													<option value="24">Secretarial Staff</option>
													<option value="25">Accounts Staff</option>
													<option value="26">Auxiliary staff -Lab Attendant</option>
													<option value="27">Auxiliary staff - Dark Room Attendant</option>
													<option value="28">Auxiliary staff- Other if any</option>
													<option value="29">Hospital reception Staff</option>
													<option value="30">Cleanliness Staff</option>
													<option value="31">Laundry</option>
													<option value="32">Gardening Staff</option>
													<option value="33">Watch and Ward duties Staff</option>

												</select>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="select-style-1">
											<label>Type </label>
											<div class="select-position">
												<select name="type" id="type"
													class="form-control">
													<option value="0" selected="selected">-- Select--</option>
													<option value="1">Staff Type 1</option>
													<option value="2">Staff Type 2</option>

												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
											<label for="fname">Available Staff</label> <input type="text"
												name="no_of_av_staff" id="no_of_av_staff" class="form-control"
												placeholder="Available Staff">
										</div>
									</div>
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">

										<ul class="buttons-group mainbtn">
											<li><a href="hospital_staffdetails"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><a type="button"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												id="btn-reload" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Search_Hospital_Staff_url"
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
												<table id="getSearch_Hospital_Staff_Dtl" class="table">
													<thead>
														<tr>
															<th><h6>Sr No</h6></th>
															<th><h6>Post</h6></th>
															<th><h6>Type </h6></th>
															<th><h6>Available Staff</h6></th>
													       <th><h6>Action</h6></th>
														</tr>
														
														<!-- end table row-->
													</thead>
													<tbody class="custom-datatablepra">
													<tr>
														  <td>1</td>
															<td>Medical Superintendent</td>
															<td>Staff Type 2</td>
															<td>34234</td>
															<td><a class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD' 
															 title='View Data' href="hospital_staffdetails_view" >
						                                     <i id="eye" class='lni lni-eye'></i></a></td>
												   </tr>
												   <tr>
														  <td>2</td>
															<td>Dresser</td>
															<td>Staff Type 1</td>
															<td>3432</td>
															<td><a class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD' 
															 title='View Data' href="hospital_staffdetails_view" >
						                                     <i id="eye" class='lni lni-eye'></i></a></td>
												   </tr>	

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

<c:url value="View_Search_College_DepartmentUrl" var="viewUrl" />
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

// 	datepicketDate('date_of_birth');
	
	mockjax1('getSearch_Hospital_Staff_Dtl');
	table = dataTable('getSearch_Hospital_Staff_Dtl');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
});

// function data(getSearch_Hospital_Staff_Dtl) {
	
// 	jsondata = [];
// var table = $('#' + getSearch_Hospital_Staff_Dtl).DataTable();
// 	var info = table.page.info();
// //		var currentPage = info.page;
// 	var pageLength = info.length;
// 	var startPage = info.start;
// 	var endPage = info.end;
// 	var Search = table.search();
// 	var order = table.order();
// 	var orderColunm = $(table.column(order[0][0]).header()).html()
// 			.toLowerCase();
// 	var orderType = order[0][1];

// // if(document.querySelector('input[name="council_check"]:checked')==true){
// 	var department= $('#department').val();
// 	var computer = $("#computer").val();
// 	var printer = $("#printer").val();
	
// 	$.post(" " + key + "=" + value, {
// 		startPage : startPage,
// 		pageLength : pageLength,
// 		Search : Search,
// 		orderColunm : orderColunm,
// 		orderType : orderType,
// 		department : department,
// 		computer : computer,
// 		printer : printer
// 	}, function(j) {

// 		for (var i = 0; i < j.length; i++) {
			
// 			var computer ="";
// 			computer = j[i].computer;
// 			if(computer == "1"){
// 				computer="Available"
// 			}
// 			if(computer == "2"){
// 				computer="Not Available"
// 			}
			
// 			var printer ="";
// 			printer = j[i].printer;
// 			if(printer == "1"){
// 				printer="Available"
// 			}
// 			if(printer == "2"){
// 				printer="Not Available"
// 			}
			
// 			jsondata.push([ j[i].ser,j[i].department,computer, printer]);
			
// 		}
// 	});
// 	$.post(" " + key + "=" + value, {
// 		Search : Search,
// 		department : department,
// 		computer : computer,
// 		printer : printer
		
// 	}, function(j) {
		
// 		FilteredRecords = j;
// 		});
// 	setTimeout(setTimeLoadForTable, 1000);
// }

// function setTimeLoadForTable() {
	
// 	document.querySelectorAll('.viewData').forEach((items, index) => {
// 		items.addEventListener('click', event => {
			
// 			var val=parseInt(index)+1;
			
// 			var hid = document.getElementById('viewId'+val).value;
			
// 			if (confirm('Are You Sure You Want to View Detail ?')) {
// 				ViewData(hid);
// 			} else {
// 				return false;
// 			}
// 		})
// 	});
	
// }

// function ViewData(id){
// 	$("#id6").val(id);
// 	$("#statusview").val($("#statusA").val());
// 	document.getElementById('viewForm').submit();
// }

</script>

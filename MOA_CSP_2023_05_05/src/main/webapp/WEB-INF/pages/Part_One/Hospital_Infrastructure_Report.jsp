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
						<h2>View Hospital Infrastructure</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View Hospital Infrastructure</li>
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
						action="Search_Hospital_InfrastractureAction?${_csrf.parameterName}=${_csrf.token}"
						method="post" modelAttribute="Search_Hospital_Infrastracture_CMD">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">View Hospital Infrastructure</h6>
								<div class="row">
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="select-style-1">
											<label>Administration Departments </label>
											<div class="select-position">
												<select name="institution_type" id="institution_type"
													class="form-control">
													<option value="0" selected="selected">-- Select--</option>
													<option value="1">Superintendent Room / Deputy Superintendent Room</option>
													<option value="2">Senior Medical Officers Room</option>
													<option value="3">Staff Nurse Room</option>
													<option value="4">Reception & Registration</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="select-style-1">
											<label>OPD Departments </label>
											<div class="select-position">
												<select name="institution_type" id="institution_type"
													class="form-control">
													<option value="0" selected="selected">-- Select--</option>
													<option value="1">General Medicine OPD</option>
													<option value="2">Obstetrics & Gynaecology OPD</option>
													<option value="3">Paediatrics and Reproductive & Child health OPD</option>
													<option value="4">Surgery OPD</option>
													<option value="5">Dressing Room</option>
													<option value="6">Dispensary</option>
													<option value="7">Waiting Space for Patients</option>
													<option value="8">Store</option>
													<option value="9">Male & Female Toilet for Patients</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="select-style-1">
											<label>IPD Departments </label>
											<div class="select-position">
												<select name="institution_type" id="institution_type"
													class="form-control">
													<option value="0" selected="selected">-- Select--</option>
													<option value="1">General Medicine Male Ward</option>
													<option value="2">General Medicine Female Ward</option>
													<option value="3">Surgery Male Ward</option>
													<option value="4">Surgery Female Ward</option>
													<option value="5">Obstetrics & Gynaecology Ward</option>
													<option value="6">Paediatrics Ward</option>
													<option value="7">Doctors Duty Room</option>
													<option value="8">Nursing Staff Duty Rooms</option>
													<option value="9">Store Room For Linen, Etc.</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="select-style-1">
											<label>Theatre Departments </label>
											<div class="select-position">
												<select name="institution_type" id="institution_type"
													class="form-control">
													<option value="0" selected="selected">-- Select--</option>
													<option value="1">Operation Theatre</option>
													<option value="2">Post Operative Recovery Room</option>
													<option value="3">Labour Room with Attached Toilet & Bathroom</option>
													<option value="4">Central Sterlisation / Autoclave Unit</option>
													<option value="5">Room for Sterilized Linen / Equipments</option>
													<option value="6">Doctors Duty Room with Attached Toilet & Bathroom</option>
													<option value="7">Interns / House Officer / Resident Doctors Room with Attached Toilet & Bathroom</option>
													<option value="8">Nursing Staff Room with Attached Toilet & Bathroom</option>
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

										<ul class="buttons-group mainbtn">
											<li><a href="hospital_infrastructure"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><a type="button"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												id="btn-reload" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Search_Hospital_Infrastracture_url"
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
												<table id="getSearch_Hospital_Infrastructure" class="table">
													<thead>
														<tr>

															<th><h6>Sr No</h6></th>
															<th><h6>Administration Departments</h6></th>
															<th><h6>OPD Departments</h6></th>
															<th><h6>IPD Departments</h6></th>
															<th><h6>Operation Theatre</h6></th>
															<th><h6>Action</h6></th>
														</tr>
														<!-- end table row-->
													</thead>
													<tbody class="custom-datatablepra">
														<tr>
															<td>1</td>
															<td>Superintendent Room / Deputy Superintendent Room</td>
															<td>General Medicine OPD</td>
															<td>General Medicine Male Ward</td>
															<td>Post Operative Recovery Room</td>
															<td><a href="hospital_infrastructure_view" class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD'  title='View Data' >
															<i class='lni lni-eye'></i></a></td>
														</tr>
														<tr>
															<td>2</td>
															<td>Senior Medical Officers Room</td>
															<td>Obstetrics & Gynaecology OPD</td>
															<td>General Medicine Female Ward</td>
															<td>Labour Room with Attached Toilet & Bathroom</td>
															<td><a href="hospital_infrastructure_view" class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD'  title='View Data' >
															<i class='lni lni-eye'></i></a></td>
														</tr>
														<tr>
															<td>3</td>
															<td>Staff Nurse Room</td>
															<td>Paediatrics and Reproductive & Child health OPD</td>
															<td>Surgery Male Ward</td>
															<td>Theatre Departments</td>
															<td><a href="hospital_infrastructure_view" class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD'  title='View Data' >
															<i class='lni lni-eye'></i></a></td>
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

<c:url value="View_Search_Hospital_InfrastructureUrl" var="viewUrl" />
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

	mockjax1('getSearch_Hospital_Infrastructure');
	table = dataTable('getSearch_Hospital_Infrastructure');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
});

// function setTimeLoadForTable() {
	
// 	document.getElementById('website_update_date').onblur = function() {
// 		clickrecall(this,'DD/MM/YYYY');
// 	};
// 	document.getElementById('website_update_date').onkeyup = function() {
// 		clickclear(this, 'DD/MM/YYYY');
// 	};
// 	document.getElementById('website_update_date').onchange = function() {
// 		clickrecall(this,'DD/MM/YYYY');
// 	};
	
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

function ViewData(id){
	$("#id6").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
}


</script>

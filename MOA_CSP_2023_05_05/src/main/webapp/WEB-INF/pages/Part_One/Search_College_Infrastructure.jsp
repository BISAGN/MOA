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
						<h2>View College Infrastructure</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View College Infrastructure</li>
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
						action="Search_College_InfrastractureAction?${_csrf.parameterName}=${_csrf.token}"
						method="post" modelAttribute="Search_College_Infrastracture_CMD">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">View College Infrastructure</h6>
								<div class="row">

									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<label>Is College Council available?
										</label>
										<div class="input-style-form-check">

											<div class="form-check radio-style">
												<input type="radio" id="council_yes" name="council_check"
													class="form-check-input" value="1"> <label
													for="council_Yes" class="form-check-label">Yes</label>
											</div>
											<div class="form-check radio-style">
												<input type="radio" id="council_no" name="council_check"
													class="form-check-input" value="2"> <label
													for="council_No" class="form-check-label">No</label>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-2">
											<label>Last website update date
											</label> <input type="text" name="website_update_date"
												id="website_update_date"
												class="form-control-sm form-control effect-9 "
												aria-required="true" autocomplete="off" value="DD/MM/YYYY">
										</div>
										<input type="hidden" id="yrr" name="yrr" value="">
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Username</label> <input
												type="text" id="username" name="username"
												class="form-control" placeholder="Username">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<label>Is Biometric available?</label>
										<div class="input-style-form-check">

											<div class="form-check radio-style">
												<input type="radio" id="biometric_status_yes"
													name="biometric_status" class="form-check-input" value="1">
												<label for="BiometricYes" class="form-check-label">Yes</label>
											</div>
											<div class="form-check radio-style">
												<input type="radio" id="biometric_status_no"
													name="biometric_status" class="form-check-input" value="2">
												<label for="BiometricNo" class="form-check-label">No</label>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<label>Is CCTV installed as per CCH instruction ?
										</label>
										<div class="input-style-form-check">

											<div class="form-check radio-style">
												<input type="radio" id="cctv_status_yes" name="cctv_status"
													class="form-check-input" value="1"> <label
													for="CCHYes" class="form-check-label">Yes</label>
											</div>
											<div class="form-check radio-style">
												<input type="radio" id="cctv_status_no" name="cctv_status"
													class="form-check-input" value="2"> <label
													for="CCHNo" class="form-check-label">No</label>
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
											<li><a href="college_infrastructure"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><a type="button"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												id="btn-reload" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Search_College_Infrastracture_url"
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
												<table id="getSearch_College_Infrastructure" class="table">
													<thead>
														<tr>

															<th><h6>Sr No</h6></th>
															<th><h6>College Council availability</h6></th>
															<th><h6>website update date</h6></th>
															<th><h6>Username</h6></th>
															<th><h6>Biometric availability</h6></th>
															<th><h6>CCTV Status</h6></th>
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

<c:url value="View_Search_College_InfrastructureUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6" value="0" />
	<input type="hidden" name="id7" id="id7" value="0" />
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

	datepicketDate('website_update_date');
// 	datepicketDate('date_of_birth');
	
	mockjax1('getSearch_College_Infrastructure');
	table = dataTable('getSearch_College_Infrastructure');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
});

function data(getSearch_College_Infrastructure) {
	
	jsondata = [];
var table = $('#' + getSearch_College_Infrastructure).DataTable();
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

// if(document.querySelector('input[name="council_check"]:checked')==true){
	var council_check= $('input[name="council_check"]:checked').val();
		
	console.log(council_check);
	

	var website_update_date = $("#website_update_date").val();
	var username = $("#username").val();
	var biometric_status = $('input[name="biometric_status"]:checked').val();
	var cctv_status = $('input[name="cctv_status"]:checked').val();
	
	$.post("getFilterSearch_College_Infrastructure_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		council_check : council_check,
		website_update_date : website_update_date,
		username : username,
		biometric_status : biometric_status,
		cctv_status : cctv_status

		
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			
			var website_update_date=""
				if (j[i].website_update_date !="" && j[i].website_update_date !=null && j[i].website_update_date !="null") {
					website_update_date= j[i].website_update_date;
				}else {
					website_update_date="-";
				}

			var council_check ="";
			council_check = j[i].council_check;
			if(council_check == "2"){
				council_check="No"
			}
			if(council_check == "1"){
				council_check="Yes"
			}
			
			var biometric_status ="";
			biometric_status = j[i].biometric_status;
			if(biometric_status == "2"){
				biometric_status="No"
			}
			if(biometric_status == "1"){
				biometric_status="Yes"
			}
			
			var cctv_status ="";
			cctv_status = j[i].cctv_status;
			if(cctv_status == "2"){
				cctv_status="No"
			}
			if(cctv_status == "1"){
				cctv_status="Yes"
			}
			
			jsondata.push([ j[i].ser,council_check,website_update_date, j[i].username,biometric_status,cctv_status,j[i].action]);
			
		}
	});
	$.post("getTotalSearch_College_Infrastructure_dataCount?" + key + "=" + value, {
		Search : Search,
		council_check : council_check,
		website_update_date : website_update_date,
		username : username,
		biometric_status : biometric_status,
		cctv_status : cctv_status
		
	}, function(j) {
		
		FilteredRecords = j;
		});
	setTimeout(setTimeLoadForTable, 1000);
}

function setTimeLoadForTable() {
	
	document.getElementById('website_update_date').onblur = function() {
		clickrecall(this,'DD/MM/YYYY');
	};
	document.getElementById('website_update_date').onkeyup = function() {
		clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('website_update_date').onchange = function() {
		clickrecall(this,'DD/MM/YYYY');
	};
	
	document.querySelectorAll('.viewData').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			
			var hid = document.getElementById('viewId'+val).value;
			var institute_hid = document.getElementById('instituteId'+val).value;
			
			if (confirm('Are You Sure You Want to View Detail ?')) {
				ViewData(hid,institute_hid);
			} else {
				return false;
			}
		})
	});
	
}



// function editData(id) {
	
// 	$("#id2").val(id);
// 	document.getElementById('updateForm').submit();
// }

// function deleteData(id) {
// 	$("#id1").val(id);
// 	document.getElementById('deleteForm').submit();
// }

function ViewData(id,institute_hid){
	$("#id6").val(id);
	$("#id7").val(institute_hid);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
}

// function Approved(id){
// 	$("#id7").val(id);
// 	$("input#status7").val('${status1}');
// 	document.getElementById('AppForm').submit();
// }

</script>

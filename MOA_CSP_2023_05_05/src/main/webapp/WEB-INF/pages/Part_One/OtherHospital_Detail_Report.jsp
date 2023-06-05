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
						<h2>Other Hospital Details Report</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Other Hospital Details Report</li>
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
						action="Search_Basic_InfoAction?${_csrf.parameterName}=${_csrf.token}"
						method="post" modelAttribute="Search_Basic_Info_CMD">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Other Hospital Details Report</h6>
								<div class="row">

									 	
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="select-style-1">
												<label>Central Registration<strong class="mandatory">*
												</strong></label>
												<div class="select-position">
													<select name="central_reg" id="central_reg"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">State</option>
														<option value="2">Central Registration</option>
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="select-style-1">
												<label>OPD Records<strong class="mandatory">*
												</strong></label>
												<div class="select-position">
													<select name="opd_records" id="opd_records"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="select-style-1">
												<label>IPD Records<strong class="mandatory">*
												</strong></label>
												<div class="select-position">
													<select name="ipd_records" id="ipd_records"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="select-style-1">
												<label>Medical Record Room<strong class="mandatory">*
												</strong></label>
												<div class="select-position">
													<select name="med_rec_room" id="med_rec_room"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="select-style-1">
												<label>Account Section<strong class="mandatory">*
												</strong></label>
												<div class="select-position">
													<select name="acc_section" id="acc_section"
														class="form-control">
														<option value="0" selected="selected">-- Select --</option>
														<option value="1">Select 1</option>
														<option value="2">Select 2</option>
													</select>
												</div>
											</div>
										</div>
									
<!-- 								 <div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label>Total No of Beds</label> -->
<!-- 											<input type="text" name="tn" id="tn" -->
<!-- 												class="form-control" -->
<!-- 												placeholder="Enter no of Beds" maxlength="100"> -->
<!-- 								</div> -->
<!-- 								</div> -->
<!-- 								 <div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label>Total Wards</label> -->
<!-- 											<input type="text" name="tw" id="tw" -->
<!-- 												class="form-control" -->
<!-- 												placeholder="Enter no of Wards" maxlength="100"> -->
<!-- 								</div> -->
<!-- 								</div> -->
									
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">

										<ul class="buttons-group mainbtn">
											<li><a href="otherhospital_detail_report_url"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><a type="button"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												id="btn-reload" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="otherhospital_detail_report_url"
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
												<table id="getSearch_college_Staff_Info_Report" class="table">
													<thead>
														<tr>

															<th><h6>Sr No</h6></th>
															<th><h6>Central Registration</h6></th>
															<th><h6>OPD Records</h6></th>
															<th><h6>IPD Records</h6></th>
															<th><h6>Medical Record Room</h6></th>
															<th><h6>Account Section</h6></th>
<!-- 															<th><h6>Name of the Managing Body</h6></th> -->
<!-- 															<th><h6>Name of the Management Contact</h6></th> -->
<!-- 															<th><h6>Name of Society/Trust/Government</h6></th> -->
<!-- 															<th><h6>Management State</h6></th> -->
<!-- 															<th><h6>Management City</h6></th> -->
<!-- 															<th><h6>Management Mo. no.</h6></th> -->
<!-- 															<th><h6>Management Email</h6></th> -->
<!-- 															<th><h6>Society/Trust Registration no.</h6></th> -->
<!-- 															<th><h6>Name of the University affiliated</h6></th> -->
															<th><h6>Action</h6></th>
														</tr>
														
														<!-- end table row-->
													</thead>
													<tbody class="custom-datatablepra">
                                                        <tr>
														  <td>1</td>
															<td>Staff</td>
															<td>444</td>
															<td>678</td>
															<td>1000</td>
															<td>45</td>
															<td><a class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD' 
															 title='View Data' href="otherhospital_detail_view" >
						                                     <i id="eye" class='lni lni-eye'></i></a></td>
														</tr>	
														 <tr>
														  <td>2</td>
															<td>Staff</td>
															<td>555</td>
															<td>43534</td>
															<td>4445</td>
															<td>4445</td>
															<td><a class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD' 
															 title='View Data' href="otherhospital_detail_view" >
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

<c:url value="View_Search_Basic_InfoUrl" var="viewUrl" />
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

	datepicketDate('dob');
// 	datepicketDate('date_of_birth');
	
	mockjax1('getSearch_college_Staff_Info_Report');
	table = dataTable('getSearch_college_Staff_Info_Report');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
});

// function data(getSearch_college_Staff_Info_Report) {
	
// 	jsondata = [];
// var table = $('#' + getSearch_college_Staff_Info_Report).DataTable();
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

// 	var inst_state = $("#inst_state").val();
// 	var inst_city = $("#inst_city").val();
// 	var inst_pincode = $("#inst_pincode").val();
// 	var inst_mo_no = $("#inst_mo_no").val();
// 	var inst_email = $("#inst_email").val();
// 	var institution_type = $("#institution_type").val();
// 	var managing_body = $("#managing_body").val();
// 	var management_contact = $("#management_contact").val();
// 	var name_of_society = $("#name_of_society").val();
// 	var mng_state = $("#mng_state").val();
// 	var mng_city = $("#mng_city").val();
// 	var mng_mo_no = $("#mng_mo_no").val();
// 	var mng_email = $("#mng_email").val();
// 	var s_registration_no = $("#s_registration_no").val();
// 	var university_affiliated = $("#university_affiliated").val();
	
// 	$.post("getFilterSearch_Basic_Info_data?" + key + "=" + value, {
// 		startPage : startPage,
// 		pageLength : pageLength,
// 		Search : Search,
// 		orderColunm : orderColunm,
// 		orderType : orderType,
// 		inst_state : inst_state,
// 		inst_city : inst_city,
// 		inst_pincode : inst_pincode,
// 		inst_mo_no : inst_mo_no,
// 		inst_email : inst_email,
// 		institution_type : institution_type,managing_body : managing_body,management_contact : management_contact,
// 		name_of_society : name_of_society,mng_state : mng_state,mng_city:mng_city,mng_mo_no:mng_mo_no,mng_email : mng_email,s_registration_no : s_registration_no,
// 		university_affiliated : university_affiliated
		
// 	}, function(j) {

// 		for (var i = 0; i < j.length; i++) {
			
// // 			var date_of_reg=""
// // 				if (j[i].date_of_reg !="" && j[i].date_of_reg !=null && j[i].date_of_reg !="null") {
// // 					date_of_reg= j[i].date_of_reg;
// // 				}else {
// // 					date_of_reg="-";
// // 				}
			
// 			jsondata.push([ j[i].ser,j[i].state_name,j[i].city, j[i].pincode,j[i].mobile_no,j[i].email_id,j[i].institute_type,j[i].name_of_managing_body,
// 				j[i].name_of_management_contact,j[i].name_of_society,j[i].state_name,j[i].city,j[i].mngt_mobile_no,j[i].mngt_email_id,j[i].society_reg_no,j[i].name_of_uni_affilate,j[i].action]);
			
// 		}
// 	});
// 	$.post("getTotalSearch_Basic_Info_dataCount?" + key + "=" + value, {
// 		Search : Search,
// 		inst_state : inst_state,
// 		inst_city : inst_city,
// 		inst_pincode : inst_pincode,
// 		inst_mo_no : inst_mo_no,
// 		inst_email : inst_email,
// 		institution_type : institution_type,managing_body : managing_body,management_contact : management_contact,
// 		name_of_society : name_of_society,mng_state : mng_state,mng_city:mng_city,mng_mo_no:mng_mo_no,mng_email : mng_email,s_registration_no : s_registration_no,
// 		university_affiliated : university_affiliated
		
// 	}, function(j) {
		
// 		FilteredRecords = j;
// 		});
// 	setTimeout(setTimeLoadForTable, 1000);
// }

// function setTimeLoadForTable() {
// 	//onkeypress="return onlyAlphaNumeric(event, this)"
// 	//onkeypress="return onlyAlphabetsStringSpace(event,this)"
	
// 	document.getElementById('inst_city').onkeypress = function() {
// 		return onlyAlphabetsStringSpace(event,this);
// 	};
// 	document.getElementById('inst_pincode').onkeypress = function() {
// 		return AllowOnlyDigit(event, this);
// 	};
	
// // 	document.getElementById('date_of_birth').onblur = function() {
// // 		clickrecall(this,'DD/MM/YYYY');
// // 	};
// // 	document.getElementById('date_of_birth').onkeyup = function() {
// // 		clickclear(this, 'DD/MM/YYYY');
// // 	};
// // 	document.getElementById('date_of_birth').onchange = function() {
// // 		clickrecall(this,'DD/MM/YYYY');
// // 	};
// // 	document.getElementById('state_reg_no').onkeypress = function() {
// // 		return onlyAlphaNumeric(event, this);
// // 	};
// // 	document.getElementById('state_board_name').onkeypress = function() {
// // 		return onlyAlphabetsStringSpace(event,this);
// // 	};
// // 	document.getElementById('central_reg_no').onkeypress = function() {
// // 		return onlyAlphaNumeric(event, this);
// // 	};
	
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



// function editData(id) {
	
// 	$("#id2").val(id);
// 	document.getElementById('updateForm').submit();
// }

// function deleteData(id) {
// 	$("#id1").val(id);
// 	document.getElementById('deleteForm').submit();
// }

function ViewData(id){
	$("#id6").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
}

// function Approved(id){
// 	$("#id7").val(id);
// 	$("input#status7").val('${status1}');
// 	document.getElementById('AppForm').submit();
// }

</script>

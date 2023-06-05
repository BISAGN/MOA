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
						<h2>View Basic Information</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View Basic Information</li>
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
								<h6 class="mb-25">View Basic Information</h6>
								<div class="row">

									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Institute Code</label>
											<input type="text" name="inst_code" id="inst_code"
												class="form-control"
												placeholder="Enter Institute Code" maxlength="100">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="select-style-1">
											<label>Institute State</label>
											<div class="select-position">
												<select class="form-control" name="inst_state"
													id="inst_state">
													<!-- style="text-transform: uppercase" -->
													<option value="0" selected="selected">--Select
														State --</option>
													<c:forEach var="item" items="${getMedStateName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Institute City</label>
											<input type="text" name="inst_city" id="inst_city"
												class="form-control"
												placeholder="Enter Institute City/Village" maxlength="100">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Institute Pincode</label>
											<input type="text" name="inst_pincode" id="inst_pincode"
												class="form-control" placeholder="Institute Pincode"
												maxlength="6">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Institute Mobile Number</label> <input type="text"
												id="inst_mo_no" name="inst_mo_no"
												class="form-control" autocomplete="off"
												placeholder="Institute Mobile Number">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Institute Email ID </label>
											<input type="text" name="inst_email" id="inst_email"
												class="form-control" placeholder="abc@example.com"
												pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="select-style-1">
											<label>Institution Type </label>
											<div class="select-position">
												<select name="institution_type" id="institution_type"
													class="form-control">
													<option value="0" selected="selected">-- Select--</option>
													<option value="1">Institution Type 1</option>
													<option value="2">Institution Type 2</option>

												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Name of the Managing Body</label> <input type="text"
												name="managing_body" id="managing_body" class="form-control"
												placeholder="Name of the Managing Body">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Name of the Management Contact
											</label> <input type="text" name="management_contact"
												id="management_contact" class="form-control"
												placeholder="Name of the Management Contact">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Name of Society/Trust/Government
											</label> <input type="text" name="name_of_society"
												id="name_of_society" class="form-control"
												placeholder="Name of Society/Trust/Government">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="select-style-1">
											<label>Management State </label>
											<div class="select-position">
												<select class="form-control" name="mng_state"
													id="mng_state">
													<!-- style="text-transform: uppercase" -->
													<option value="0" selected="selected">--Select
														State --</option>
													<c:forEach var="item" items="${getMedStateName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Management City</label>
											<input type="text" name="mng_city" id="mng_city"
												class="form-control"
												placeholder="Enter Management City/Village" maxlength="100">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Management Mobile Number</label> <input type="text"
												id="mng_mo_no" name="mng_mo_no"
												class="form-control" autocomplete="off"
												placeholder="Management Mobile Number">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Management Email ID </label>
											<input type="text" name="mng_email" id="mng_email"
												class="form-control" placeholder="abc@example.com"
												pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$">
										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Society/Trust Registration Number
											</label> <input type="text" name="s_registration_no"
												id="s_registration_no" class="form-control"
												placeholder="Society/Trust Registration Number">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="input-style-1">
											<label>Name of the University affiliated
											</label> <input type="text" name="university_affiliated"
												id="university_affiliated" class="form-control"
												placeholder="Name of the University affiliated">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
										<div class="select-style-1">
											<label>Status</label>
											<div class="select-position">
												<select class="form-control" name="status"
													id="status">
													<!-- style="text-transform: uppercase" -->
<!-- 													<option value="0" selected="selected">--Select -->
<!-- 														State --</option> -->
														<option value="1" >Pending</option>
														<option value="3" >Approved</option>
														<option value="2" >Reject</option>
												
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
											<li><a href="basics_information"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><a type="button"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												id="btn-reload" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="Search_Basic_Info_url"
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
												<table id="getSearch_Basic_Info" class="table">
													<thead>
														<tr>

															<th><h6>Sr No</h6></th>
															<th><h6>Institute Code</h6></th>
															<th><h6>Institute State</h6></th>
															<th><h6>Institute City</h6></th>
															<th><h6>Institute Pincode</h6></th>
															<th><h6>Institute Mo.no.</h6></th>
															<th><h6>Institute Email</h6></th>
															<th><h6>Institution Type</h6></th>
															<th><h6>Name of the Managing Body</h6></th>
															<th><h6>Name of the Management Contact</h6></th>
															<th><h6>Name of Society/Trust/Government</h6></th>
															<th><h6>Management State</h6></th>
															<th><h6>Management City</h6></th>
															<th><h6>Management Mo. no.</h6></th>
															<th><h6>Management Email</h6></th>
															<th><h6>Society/Trust Registration no.</h6></th>
															<th><h6>Name of the University affiliated</h6></th>
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

<c:url value="View_Search_Basic_InfoUrl" var="viewUrl" />
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

// 	datepicketDate('date_of_reg');
// 	datepicketDate('date_of_birth');
	
	mockjax1('getSearch_Basic_Info');
	table = dataTable('getSearch_Basic_Info');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
});

function data(getSearch_Basic_Info) {
	
	jsondata = [];
var table = $('#' + getSearch_Basic_Info).DataTable();
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

	var inst_code = $("#inst_code").val();
	var inst_state = $("#inst_state").val();
	var inst_city = $("#inst_city").val();
	var inst_pincode = $("#inst_pincode").val();
	var inst_mo_no = $("#inst_mo_no").val();
	var inst_email = $("#inst_email").val();
	var institution_type = $("#institution_type").val();
	var managing_body = $("#managing_body").val();
	var management_contact = $("#management_contact").val();
	var name_of_society = $("#name_of_society").val();
	var mng_state = $("#mng_state").val();
	var mng_city = $("#mng_city").val();
	var mng_mo_no = $("#mng_mo_no").val();
	var mng_email = $("#mng_email").val();
	var status = $("#status").val();
	var s_registration_no = $("#s_registration_no").val();
	var university_affiliated = $("#university_affiliated").val();
	
	$.post("getFilterSearch_Basic_Info_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		inst_code : inst_code,
		inst_state : inst_state,
		inst_city : inst_city,
		inst_pincode : inst_pincode,
		inst_mo_no : inst_mo_no,
		inst_email : inst_email,
		institution_type : institution_type,managing_body : managing_body,management_contact : management_contact,
		name_of_society : name_of_society,mng_state : mng_state,mng_city:mng_city,mng_mo_no:mng_mo_no,mng_email : mng_email,s_registration_no : s_registration_no,
		university_affiliated : university_affiliated,status:status
		
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			
// 			var date_of_reg=""
// 				if (j[i].date_of_reg !="" && j[i].date_of_reg !=null && j[i].date_of_reg !="null") {
// 					date_of_reg= j[i].date_of_reg;
// 				}else {
// 					date_of_reg="-";
// 				}
			
			jsondata.push([ j[i].ser,j[i].code,j[i].state_name,j[i].city, j[i].pincode,j[i].mobile_no,j[i].email_id,j[i].institute_type,j[i].name_of_managing_body,
				j[i].name_of_management_contact,j[i].name_of_society,j[i].state_name,j[i].city,j[i].mngt_mobile_no,j[i].mngt_email_id,j[i].society_reg_no,j[i].name_of_uni_affilate,j[i].action]);
			
		}
	});
	$.post("getTotalSearch_Basic_Info_dataCount?" + key + "=" + value, {
		Search : Search,
		inst_code : inst_code,
		inst_state : inst_state,
		inst_city : inst_city,
		inst_pincode : inst_pincode,
		inst_mo_no : inst_mo_no,
		inst_email : inst_email,
		institution_type : institution_type,managing_body : managing_body,management_contact : management_contact,
		name_of_society : name_of_society,mng_state : mng_state,mng_city:mng_city,mng_mo_no:mng_mo_no,mng_email : mng_email,s_registration_no : s_registration_no,
		university_affiliated : university_affiliated,status:status
		
	}, function(j) {
		
		FilteredRecords = j;
		});
	setTimeout(setTimeLoadForTable, 1000);
}

function setTimeLoadForTable() {
	//onkeypress="return onlyAlphaNumeric(event, this)"
	//onkeypress="return onlyAlphabetsStringSpace(event,this)"
	
	document.getElementById('inst_city').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);
	};
	document.getElementById('inst_pincode').onkeypress = function() {
		return AllowOnlyDigit(event, this);
	};
	
// 	document.getElementById('date_of_birth').onblur = function() {
// 		clickrecall(this,'DD/MM/YYYY');
// 	};
// 	document.getElementById('date_of_birth').onkeyup = function() {
// 		clickclear(this, 'DD/MM/YYYY');
// 	};
// 	document.getElementById('date_of_birth').onchange = function() {
// 		clickrecall(this,'DD/MM/YYYY');
// 	};
// 	document.getElementById('state_reg_no').onkeypress = function() {
// 		return onlyAlphaNumeric(event, this);
// 	};
// 	document.getElementById('state_board_name').onkeypress = function() {
// 		return onlyAlphabetsStringSpace(event,this);
// 	};
// 	document.getElementById('central_reg_no').onkeypress = function() {
// 		return onlyAlphaNumeric(event, this);
// 	};
	
	
	
	
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
	
	
	document.querySelectorAll('.ApproveData').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('ApproveId'+val).value;
			var institute_hid = document.getElementById('instituteId'+val).value;
			if (confirm('Are You Sure You Want to Approve Detail ?')) {
				ApproveData(hid,institute_hid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.EnableData').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('EnableId'+val).value;
			var institute_hid = document.getElementById('instituteId'+val).value;
			if (confirm('Are You Sure You Want to Enable Detail ?')) {
				EnableData(hid,institute_hid);
			} else {
				return false;
			}
		})
	});

document.querySelectorAll('.RejectData').forEach((items, index) => {
	items.addEventListener('click', event => {
		var val=parseInt(index)+1;
		var hid = document.getElementById('RejectId'+val).value;
		var institute_hid = document.getElementById('instituteId'+val).value;
		if (confirm('Are You Sure You Want to Reject Detail ?')) {
			RejectData(hid,institute_hid);
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




function ApproveData(id,institute_hid){
	
	$.post("approve_Teaching_Faculty_Details?" + key + "=" + value, {
		institute_hid : institute_hid
	}, function(j) {
		alert(j);
		location.reload();
	});
}
function EnableData(id,institute_hid){
	
	$.post("Enable_Teaching_Faculty_Details?" + key + "=" + value, {
		institute_hid : institute_hid
	}, function(j) {
		alert(j);
		location.reload();
	});
}

function RejectData(id,institute_hid){
	
	$.post("Reject_Teaching_Faculty_Details?" + key + "=" + value, {
		institute_hid : institute_hid
	}, function(j) {
		alert(j);
		location.reload();
	});
}




// function Approved(id){
// 	$("#id7").val(id);
// 	$("input#status7").val('${status1}');
// 	document.getElementById('AppForm').submit();
// }

</script>

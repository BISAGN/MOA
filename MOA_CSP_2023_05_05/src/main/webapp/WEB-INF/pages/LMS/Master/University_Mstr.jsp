<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
<script type="text/javascript" src="js/watermark/common.js"></script>
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
						<h2>University Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">University
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
					<!-- input style start -->
					<form:form
						action="Univers_action?${_csrf.parameterName}=${_csrf.token}"
						method="POST" modelAttribute="Univers_cmd">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">University Master</h6>
								<div class="row">
									<div class="col-12 col-lg-3 col-md-3 col-sm-12">
										<div class="input-style-1">
											<label>University<span class="mandatory">*</span></label> <input
												type="text" name="university_name" id="university_name"
												placeholder="University" maxlength="100">
										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-lg-3 col-md-3 col-sm-12">
										<div class="input-style-1">
											<label>University Code<span class="mandatory">*</span></label>
											<input type="text" name="university_code"
												id="university_code" placeholder="University code"
												maxlength="50"> <input type="hidden" id="id"
												name="id" value="0" class="form-control" autocomplete="off" />
										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-lg-3 col-md-3 col-sm-12">
										<div class="select-style-1">
											<label>University type<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="university_type"
													class="singleselect form-control form-control-lg"
													id="university_type">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getUniversityTypelList}"
														varStatus="num">
														<option value="${item.id}" name="${item.university_type}">${item.university_type}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-lg-3 col-md-3 col-sm-12">
										<div class="select-style-1">
											<label>Organization<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="organization_id"
													class="singleselect form-control form-control-lg"
													id="organization_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getOrganizationList}"
														varStatus="num">
														<option value="${item.id}" name="${item.organization}">${item.organization}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-lg-3 col-md-3 col-sm-12">
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
									<div class="col-12 col-lg-3 col-md-3 col-sm-12">
										<div class="select-style-1">
											<label>Country<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="country_id" id="country_id"
													class="singleselect form-control form-control-lg">
													<option value="0" selected="selected">-- Select
														Country --</option>
													<c:forEach var="item" items="${getMedCountryName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-lg-3 col-md-3 col-sm-12">
										<div class="select-style-1">
											<label>State<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="state_id" id="state_id"
													class="singleselect form-control form-control-lg">
													<option value="0">---Select State---</option>
													<%-- 											<c:forEach var="item" items="${MedStateName}" varStatus="num"> --%>
													<%-- 										<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
													<%-- 									</c:forEach> --%>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-lg-3 col-md-3 col-sm-12">
										<div class="select-style-1">
											<label>District<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="district_id" id="district_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select District--</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-lg-3 col-md-3 col-sm-12">
										<div class="input-style-1">
											<label>City<span class="mandatory">*</span></label> <input
												type="text" name="city_name" id="city_name"
												class="form-control" placeholder="City" maxlength="50">
										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-lg-3 col-md-3 col-sm-12">
										<div class="input-style-1">
											<label>Address<span class="mandatory">*</span></label> <input
												id="address" name="address" autocomplete="off"
												maxlength="100" placeholder="Address" />
										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-lg-3 col-md-3 col-sm-12">
										<div class="select-style-1">
											<label>Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="status"
													class="singleselect form-control form-control-lg"
													id="status">
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
										<ul class="buttons-group mainbtn">
											<li><a type="button" id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><input type="submit" id="btn-save"
												class="main-btn info-btn btn-hover btnsave" value="Save"></li>
											<li><a href="University_Url"
												class="main-btn dark-btn n btn-hover btnreset">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>

						</div>
						<!-- end card -->

						<section class="single-detail-block">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="card-style mb-30">
									<div class="table-wrapper table-responsive custom-datatable-p">
										<table class="table" id="getSearch_University">
											<thead>
												<tr>
													<th id="1"><h6>Sr No</h6></th>
													<th id="2"><h6>University</h6></th>
													<th id="3"><h6>University code</h6></th>
													<th id="10"><h6>University type</h6></th>
													<th id="11"><h6>Organization</h6></th>
													<th id="12"><h6>System</h6></th>
													<th id="4"><h6>Country</h6></th>
													<th id="6"><h6>State</h6></th>
													<th id="7"><h6>District</h6></th>
													<th id="8"><h6>City</h6></th>
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


<c:url value="Edit_UniverUrl" var="Edit_UniverUrl" />
<form:form action="${Edit_UniverUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="deleteUni_Url" var="deleteUni_Url" />
<form:form action="${deleteUni_Url}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('getSearch_University');
		table = dataTable('getSearch_University');
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
	
	document.getElementById('university_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	
	document.getElementById('university_code').onkeypress = function () {
		return isAlphaNumeric_SpecialsWithSpace(event);
	};
	
	document.getElementById('country_id').onchange = function () {
		getState();
	};
	
	document.getElementById('state_id').onchange = function () {
		getDistrict();
	};
	
	document.getElementById('city_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
		
		});
	

	function setTimeLoadForTable(){
		
		
		document.querySelectorAll('.ADDUni').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var uid = document.getElementById('UniId'+val).value;
 				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(uid);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.deleteUni').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var dtdid = document.getElementById('DEUniId'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(dtdid);
				} else {
					return false;
				}
			})
		});
				
		
	}
	
	function data(getSearch_University) {
		//debugger;
		jsondata = [];
		var table = $('#' + getSearch_University).DataTable();
		var info = table.page.info();
		//		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id');
		var orderType = order[0][1];
// 		var order = table.order();
// 		var orderColunm = $(table.column(order[0][0]).header()).html()
// 				.toLowerCase();
// 		var orderType = order[0][1];

		var university_name = $("#university_name").val();
		var university_code = $("#university_code").val();
		var country_id = $("#country_id").val();
		var state_id = $("#state_id").val();
		var district_id = $("#district_id").val();
		var city_name = $("#city_name").val();
		var status = $("#status").val();
		var university_type = $("#university_type").val();
		var organization_id = $("#organization_id").val();
		var system_id = $("#system_id").val();

		$.post("getFilterUniversitydata?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			university_name : university_name,
			university_code : university_code,
			country_id : country_id,
			state_id : state_id,
			district_id : district_id,
			city_name : city_name,
			status : status,
			university_type : university_type,
			organization_id : organization_id,
			system_id : system_id
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].university_name,
						j[i].university_code,j[i].university_type,  j[i].organization,  j[i].system_name, j[i].name, j[i].state_name,
						j[i].district_name, j[i].city_name, j[i].action ]);

			}
		});
		$.post("getTotalUniversity_dataCount?" + key + "=" + value, {
			university_name : university_name,
			university_code : university_code,
			country_id : country_id,
			state_id : state_id,
			district_id : district_id,
			city_name : city_name,
			status : status,
			university_type : university_type,
			organization_id : organization_id,
			system_id : system_id
			
			
		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	function getState() {
		var selval = $("#country_id").val();
		$
				.post(
						"getStateUrl?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i].state_id + '" name="'+j[i].state_id+'" >'
										+ j[i].state_name + '</option>';
							}
							$("select#state_id").html(options);
						});
	}

	function getDistrict() {
		var selval = $("#state_id").val();
		$
				.post(
						"getDistrictUrl?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
										+ j[i].district_name + '</option>';
							}
							$("select#district_id").html(options);
						});
	}

	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}

	function editData(id) {
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

	function Validation() {

		if ($("#university_name").val().trim() == "") {
			alert("Please Enter University.");
			$("#university_name").focus();
			return false;
		}
		if ($("#university_code").val().trim() == "") {
			alert("Please Enter University Code.");
			$("#university_code").focus();
			return false;
		}
		if ($("#university_type").val() == "0") {
			alert("Please Select University Type.");
			$("select#university_type").focus();
			return false;
		}
		if ($("#organization_id").val() == "0") {
			alert("Please Select Organization.");
			$("select#organization_id").focus();
			return false;
		}
		if ($("#system_id").val() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("#country_id").val() == "0") {
			alert("Please Select Country.");
			$("select#country_id").focus();
			return false;
		}
		if ($("#state_id").val() == "0") {
			alert("Please Select State.");
			$("select#state_id").focus();
			return false;
		}
		if ($("#district_id").val() == "0") {
			alert("Please Select District.");
			$("select#district_id").focus();
			return false;
		}

		if ($("#city_name").val().trim() == "") {
			alert("Please Enter City.");
			$("input#city_name").focus();
			return false;
		}
		if ($("#address").val().trim() == "") {
			alert("Please Enter Address.");
			$("input#address").focus();
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

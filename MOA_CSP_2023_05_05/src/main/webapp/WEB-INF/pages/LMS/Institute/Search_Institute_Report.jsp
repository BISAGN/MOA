<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<h2>College Registration Report</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">College
									Registration Report</li>
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
					<form:form name="" id="" action="Search_College_RegAction"
						method="post" commandName="Search_Institute_Reg_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">College Registration Report</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">College</label> <input type="text"
												id="institute_name" name="College_name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100" placeholder="College" />
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Country</label>
											<div class="select-position">
												<select name="country_id" id="country_id">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${MedCountryName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">State</label>
											<div class="select-position">
												<select name="state_id" id="state_id">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${MedStateName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">District</label>
											<div class="select-position">
												<select name="district_id" id="district_id">
													<option value="0">---Select---</option>
													<%-- 									<c:forEach var="item" items="${getMedDistrictName}" varStatus="num"> --%>
													<%-- 										<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
													<%-- 									</c:forEach> --%>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Approval Status</label>
											<div class="select-position">
												<select name="app_status" id="app_status">
													<option value="0">Pending</option>
													<option value="1">Approve</option>
													<option value="2">Reject</option>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>

								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">


										<ul class="buttons-group mainbtn">

											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<!-- <li>
								<a id="btn-save" class="main-btn info-btn btn-hover" type="submit">Save</a>
							</li> -->
											<li><a href="Search_institute_report_url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>
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
											<table class="table" id="getSearch_Inst_Reg">
												<thead>
													<tr>
														<th id="1"><h6>Sr No</h6></th>
														<th id="2"><h6>College</h6></th>
														<th id="3"><h6>Country</h6></th>
														<th id="4"><h6>State</h6></th>
														<th id="5"><h6>District</h6></th>
														<th id="6"><h6>Code</h6></th>
														<th id="7"><h6>Address</h6></th>
														<th id="8"><h6>Action</h6></th>
														<%-- 				            <c:if test="${app_status=='1' && app_status=='2'}"> --%>
														<!-- 				            	<th id="9"><h6>Action</h6></th> -->
														<%-- 				            </c:if> --%>
													</tr>
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


					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<c:url value="Search_institute_Approve_url"
	var="Search_institute_Approve_url" />
<form:form action="${Search_institute_Approve_url}" method="post"
	id="AcceptRegistration" name="AcceptRegistration"
	modelAttribute="Acceptid">
	<input type="hidden" name="Acceptid" id="Acceptid" value="0" />
	<input type="hidden" name="inst_email" id="inst_email" value="0" />
	<input type="hidden" name="code" id="code" value="0" />
</form:form>

<c:url value="Search_institute_Reject_url"
	var="Search_institute_Reject_url" />
<form:form action="${Search_institute_Reject_url}" method="post"
	id="RejectRegistration" name="RejectRegistration"
	modelAttribute="Rejecttid">
	<input type="hidden" name="Rejectid" id="Rejectid" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">

	$(document).ready(function() {

		mockjax1('getSearch_Inst_Reg');
		table = dataTable('getSearch_Inst_Reg');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
			
// 			setTimeout(setTimeLoadForTable, 1000);
		});
		
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
	});
	
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('country_id').onchange = function() {
			 getState();
		};
		document.getElementById('state_id').onchange = function() {
			 getDistrict();
		};
		document.getElementById('app_status').onchange = function() {
			getDistrict();
		};
	
	});

function setTimeLoadForTable(){
	document.querySelectorAll('.approve_inst').forEach((items, index) => {
		
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var eid = document.getElementById('appID'+val).value;
				var email = document.getElementById('apIdAGE'+val).value;
				var code = document.getElementById('apIdCODE'+val).value;
				alert(code)
			
			if (confirm('Are You Sure You Want to Approve Detail ?')) {
				Accepturl(eid,email,code);
			} else {
				return false;
			}
		})
	});
		
		document.querySelectorAll('.reject_inst').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var rid = document.getElementById('rejID'+val).value;
				if (confirm('Are You Sure You Want to Reject Detail ?')) {
					Rejecturl(rid);
				} else {
					return false;
				}
			})
		});
		
	}
	

	function data(getSearch_Inst_Reg) {
		jsondata = [];
		var table = $('#' + getSearch_Inst_Reg).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];

		var institute_name = $("#institute_name").val();
		var country_id = $("#country_id").val();
		var state_id = $("#state_id").val();
		var district_id = $("#district_id").val();
		var app_status = $("#app_status").val();

		$.post("getFilterinstitute_reg_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			institute_name : institute_name,
			country_id : country_id,
			state_id : state_id,
			district_id : district_id,
			app_status : app_status
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].institute_name, j[i].name,
						j[i].state_name, j[i].district_name, j[i].code,
						j[i].address, j[i].action ]);

			}
		});
		$.post("getTotalinstitute_reg_dataCount?" + key + "=" + value, {
			institute_name : institute_name,
			country_id : country_id,
			state_id : state_id,
			district_id : district_id,
			app_status : app_status
		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	function Accepturl(id, mail,code) {
		$("#Acceptid").val(id);
		$("#inst_email").val(mail);
		$("#code").val(code);

		document.getElementById('AcceptRegistration').submit();
	}

	function Rejecturl(id) {

		$("#Rejectid").val(id);
		document.getElementById('RejectRegistration').submit();
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
		$.post(
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
</script>

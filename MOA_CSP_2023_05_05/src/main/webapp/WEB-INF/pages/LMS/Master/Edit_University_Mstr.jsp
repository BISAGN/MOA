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
						<h2>Update University Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">
									Update University Master</li>
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
						action="Edit_UniversAction?${_csrf.parameterName}=${_csrf.token}"
						method="POST" modelAttribute="EditUnivers_cmd">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Update University Master</h6>
								<div class="row">


									<div class="col-12 col-lg-3 col-md-3 col-sm-12">
										<div class="input-style-1">
											<label>University<span class="mandatory">*</span></label> <input
												type="text" name="university_name" id="university_name"
												class="form-control" placeholder="University"
												maxlength="100">
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>University Code <span class="mandatory">*</span></label>
											<input type="text" name="university_code"
												id="university_code" class="form-control"
												placeholder="University Code" maxlength="50">
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
											<div class="input-style-1">
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
									</div>
									<div class="col-12 col-lg-3 col-md-3 col-sm-12">
										<div class="select-style-1">
											<label>System<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="system_id" id="system_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${s_name}" varStatus="num">
														<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
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
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>Address<span class="mandatory">*</span></label> <input
												id="address" name="address" class="form-control"
												autocomplete="off" maxlength="100" placeholder="Address" />
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
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
												</select> <input type="hidden" id="id" name="id" value="0"
													class="form-control" autocomplete="off" />
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
											<li><a href="University_Url" id="University_Url"
												class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><input type="submit"
												class="main-btn deactive-btn btn-hover btnupda" value="Update"
												id="update"></li>
										</ul>
									</div>
								</div>

							</div>

						</div>
						<!-- end card -->

					</form:form>

				</div>
			</div>
		</div>
	</div>
</section>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		$('#id').val('${Univers_dtl.id}');
		$('#university_name').val('${Univers_dtl.university_name}');
		$('#university_code').val('${Univers_dtl.university_code}');
		$('select#university_type').val('${Univers_dtl.university_type}');
		$('#university_type').trigger('change');
		$('select#organization_id').val('${Univers_dtl.organization_id}');
		$('#organization_id').trigger('change');
		$('select#system_id').val('${Univers_dtl.system_id}');
		$('select#country_id').val('${Univers_dtl.country_id}');
		$('#country_id').trigger('change');
		$("#country_id").change();
		$('select#state_id').val('${Univers_dtl.state_id}');
		$('#state_id').trigger('change');
		$("#state_id").change();
		$('select#district_id').val('${Univers_dtl.district_id}');
		$('#district_id').trigger('change');
		$("#district_id").change();
		$('#city_name').val('${Univers_dtl.city_name}');
		$('#address').val('${Univers_dtl.address}');
		$('select#status').val('${Univers_dtl.status}');
		$('#status').trigger('change');

	});

	function getState() {

		$("select#state_id").val('0');
		$("#state_id").change();

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

	function Validation() {

		if ($("#university_name").val().trim() == "") {
			alert("Please Enter University.");
			$("input#university_name").focus();
			return false;
		}
		if ($("#university_code").val().trim() == "") {
			alert("Please Enter University Code.");
			$("input#university_code").focus();
			return false;
		}
		if ($("#university_type").val() == "0") {
			alert("Please Select University Type.");
			$("select#university_type").focus();
			return false;
		}
		if ($("#organization_id").val() == "0") {
			alert("Please Enter Organization.");
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

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('update').onclick = function() {
			return Validation();
		};

		document.getElementById('university_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};

		document.getElementById('university_code').onkeypress = function() {
			return onlyAlphaNumeric(event, this);
		};

		document.getElementById('country_id').onchange = function() {
			getState();
		};

		document.getElementById('state_id').onchange = function() {
			getDistrict();
		};

		document.getElementById('city_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};

	});
</script>

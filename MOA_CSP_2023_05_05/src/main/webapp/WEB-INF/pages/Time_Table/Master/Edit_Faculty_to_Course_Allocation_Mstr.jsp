<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />

<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css"> -->
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
						<h2>
							<span id="lbladd"></span>Update Faculty to Subject Master
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Update
									Faculty to Subject Master</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form action="Edit_Faculty_to_Course_Master_action"
						method="POST"
						modelAttribute="Edit_Faculty_to_Course_Master_cmd">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<!-- <h6 class="mb-25">Update Faculty to Course Master</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="username">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-control form-control-lg"
													name="degree" id="degree">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getDegreeList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.degree_name}</option>
													</c:forEach>

												</select>
											</div>
										</div>
								<div class="input-style-1">
									<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
                                </div>	
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Professional<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-control form-control-lg"
													name="professional" id="professional">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getProfessionalList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.professional}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="username">Subject<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-control form-control-lg"
													name="course" id="course">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCourseList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.course_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
								<div class="input-style-1">
									<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
                                </div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="username">Faculty<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-control form-control-lg"
													name="faculty" id="faculty">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getFacultyList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
							<div class="input-style-1">
								<input type="hidden" id="id" name="id" value="0" autocomplete="off" />                                </div>
							</div>
								</div>
							</div>
							
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a href="Faculty_to_CourseUrl"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
													<i class="lni lni-chevron-left"></i>Back
											</a></li>
											<li><input type="submit" id="update" value="Update"
												class="main-btn deactive-btn btn-hover btnupda" /></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>
			</div>
		</div>

	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document)
			.ready(
					function() {
						// debugger;
						$.ajaxSetup({
							async : false
						});
						getAprrovedDegree('${system[0][1]}');

						$('#id').val(
								'${Edit_faculty_to_course_master_details.id}');
						$('select#degree')
								.val(
										'${Edit_faculty_to_course_master_details.degree}');
						$('#degree').trigger('change');
						$('select#professional')
								.val(
										'${Edit_faculty_to_course_master_details.professional}');
						$('#professional').trigger('change');
						getcoursedtl();
						$('select#course')
								.val(
										'${Edit_faculty_to_course_master_details.course}');
						$('#course').trigger('change');
						$('select#faculty')
								.val(
										'${Edit_faculty_to_course_master_details.faculty}');
						$('#faculty').trigger('change');
					});

	function Validation() {

		if ($("select#degree").val().trim() == "0") {
			alert("Please Select Degree.");
			$("Select#degree").focus();
			return false;
		}

		if ($("select#professional").val().trim() == "0") {
			alert("Please Select Professional.");
			$("Select#professional").focus();
			return false;
		}
		if ($("select#course").val().trim() == "0") {
			alert("Please Select Course.");
			$("Select#course").focus();
			return false;
		}
		if ($("select#faculty").val().trim() == "0") {
			alert("Please Select Faculty.");
			$("Select#faculty").focus();
			return false;
		}

		if ($("#course").val().trim() == "") {
			alert("Please Enter Course");
			$("input#course").focus();
			return false;
		}
		if ($("#faculty").val().trim() == "") {
			alert("Please Enter Faculty");
			$("input#faculty").focus();
			return false;
		}

		return true;
	}

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('professional').onchange = function() {
			getcoursedtl();
		};
		document.getElementById('update').onclick = function() {
			return Validation();
		};
		document.getElementById('degree').onkeypress = function() {
			return isNumberKey0to9(event);
		};
		document.getElementById('course').onkeypress = function() {
			return isNumberKey0to9(event);
		};
		document.getElementById('faculty').onkeypress = function() {
			return isNumberKey0to9(event);
		};

	});

	function getcoursedtl() {

		var degree_id = $("#degree").val();
		var professional_id = $("#professional").val();

		if ($("#degree").val().trim() == 0) {
			alert("please select degree");
			$("select#degree").focus();
			$("#professional").val("0");
		}

		$
				.post(
						"getCourseList_for_Curri?" + key + "=" + value,
						{

							degree_id : degree_id,
							professional_id : professional_id

						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course").html(options);
						});
	}

	function getFacultyDtl() {
		var course_id = $("#course").val();

		$
				.post(
						"getFacutlyDetails?" + key + "=" + value,
						{
							course_id : course_id
						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#faculty").html(options);
						});
	}

	function getAprrovedDegree(id) {

		// 		var selval = $("#system_hid").val();
		var selval = id;
		$
				.post(
						"getdegrebysysidlist?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {
							if (j.length > 0) {
								var options = '<option value="' + "0" + '">'
										+ "--Select--" + '</option>';
								for (var i = 0; i < j.length; i++) {
									options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
											+ j[i][1] + '</option>';
								}
								$("select#degree").html(options);
							}
						});
	}
</script>


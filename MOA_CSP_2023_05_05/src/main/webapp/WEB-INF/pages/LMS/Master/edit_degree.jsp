<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
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



<script nonce="${cspNonce}" type="text/javascript">
	var username = "${username}";
</script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span>Update Degree Master
						</h2>
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
									Update Degree Master</li>
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
					<form:form action="Edit_Degree_Master_action" method="POST"
						modelAttribute="Edit_Degree_Master_cmd">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Update Degree Master</h6>
								<div class="row">

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">

										<div class="select-style-1">
											<label for="username">Type Of Degree<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="type_of_degree" id="type_of_degree"
													class="singleselect form-control form-control-lg">

													<option value="0">--Select--</option>
													<c:forEach var="item" items="${gettype_of_degree}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>

												</select> <input type="hidden" id="id" name="id" value="0"
													autocomplete="off" />
											</div>
										</div>
										<div class="input-style-1">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>

										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="username">Degree<span class="mandatory">*</span></label>
											<input id="degree_name" name="degree_name" autocomplete="off"
												maxlength="50" placeholder="Degree" /> <input type="hidden"
												id="id" name="id" value="0" autocomplete="off" />
										</div>

										<!-- end select -->
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="username">Semester<span class="mandatory">*</span></label>
											<input id="semester" name="semester" autocomplete="off"
												maxlength="2" placeholder="Semester" /> <input
												type="hidden" id="id" name="id" value="0" autocomplete="off" />
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="username">Degree Code<span
												class="mandatory">*</span></label> <input id="degree_code"
												name="degree_code" autocomplete="off" maxlength="10"
												placeholder="Degree Code" /> <input type="hidden" id="id"
												name="id" value="0" autocomplete="off" />
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="status" id="status"
													class="singleselect form-control form-control-lg">
													<c:forEach var="item" items="${ActiveInActiveList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>

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
											<li><a href="degree_master_url"
												class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-cancel"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><input id="update"
												class="main-btn deactive-btn btn-hover btn-update"
												value="Update" type="submit"></li>
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
	$(document).ready(
			function() {

				$('#id').val('${Edit_degree_master_details.id}');
				$('select#type_of_degree').val(
						'${Edit_degree_master_details.type_of_degree}');
				$('#type_of_degree').trigger('change');
				$('input#degree_name').val(
						'${Edit_degree_master_details.degree_name}');
				$('input#semester').val(
						'${Edit_degree_master_details.semester}');
				$('input#degree_code').val(
						'${Edit_degree_master_details.degree_code}');
				$('select#status').val('${Edit_degree_master_details.status}');
				$('#status').trigger('change');

			});

	function Validation() {

		if ($("select#type_of_degree").val() == 0) {
			alert("Please Select Type of Degree");
			$("select#type_of_degree").focus();
			return false;
		}

		if ($("#degree_name").val().trim() == "") {
			alert("Please Enter degree");
			$("input#degree_name").focus();
			return false;
		}

		if ($("#semester").val().trim() == "") {
			alert("Please Enter Semester");
			$("input#semester").focus();
			return false;
		}
		if ($("#degree_code").val().trim() == "") {
			alert("Please Enter Degree Code");
			$("input#degree_code").focus();
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}

		return true;
	}

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('update').onclick = function() {
			return Validation();
		};

		document.getElementById('semester').onkeypress = function() {
			return isNumberKey0to9(event);
		};

		document.getElementById('degree_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};

	});
</script>


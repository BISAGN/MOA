<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->


<%-- <script nonce="${cspNonce}" type="text/javascript"> --%>
<%-- 	var username = "${username}"; --%>
<!-- </script> -->

<section class="dashboard-page">
<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Update Course Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Update Course Master</li>
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
					<form:form action="Edit_Course_Master_action" method="POST" modelAttribute="Edit_Course_Master_cmd">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Update Course Master</h6>
							<div class="row">
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
										<label>Type of Lecture<strong class="mandatory">*</strong></label>
										<div class="select-position">
											<select name="type_of_content_id" id="type_of_content_id" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${TypeOfcontent}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- end input -->
								</div>
							
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-1">
										<label for="username">Course Name<span class="mandatory">*</span></label> 
										<input id="course_name" name="course_name" class="form-control" autocomplete="off" maxlength="50" placeholder="Course Name" />
									<input type="hidden" id="id" name="id" value="0" class="form-control" autocomplete="off" />
									</div>
									<!-- end input -->
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-1">
										<label>Course Code<span class="mandatory">*</span></label> 
										<input id="course_code" name="course_code" class="form-control" autocomplete="off" maxlength="50" placeholder="Course Code" />
									<input type="hidden" id="id" name="id" value="0"
										class="form-control" autocomplete="off" />
									</div>
									<!-- end input -->
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="select-style-1">
										<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">
												<c:forEach var="item" items="${ActiveInActiveList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
			
											</select>
										<input type="hidden" id="id" name="id" value="0"
										class="form-control" autocomplete="off" />
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
									<!-- <li><a type="button" id="btn-reload"
										class="main-btn secondary-btn btn-hover btn-iconic-icon"><i
											class="lni lni-search-alt"></i>Search</a></li>
									<li><input type="submit" id="btn-save"
										onclick="return Validation();"
										class="main-btn info-btn btn-hover" value="Save"></li>
									<li><a href="course_master_url"
										class="main-btn dark-btn n btn-hover">Reset</a></li> -->
										<li>
						                    <a href="course_master_url" class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"></i>Back</a>
						                </li>
						                <li>
						                    <input type="submit" class="main-btn deactive-btn btn-hover btnupda" value="Update" id="update">
						                </li>
								</ul>
								</div>
								</div>
								</div>
								<!-- Bottom Button Start -->
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
	
	$('#id').val('${Edit_course_mstr_Details.id}');
	$('select#type_of_content_id').val('${Edit_course_mstr_Details.type_of_content_id}');
	$('#type_of_content_id').trigger('change');
	$('input#course_name').val('${Edit_course_mstr_Details.course_name}');
	$('select#status').val('${Edit_course_mstr_Details.status}');
	$('#status').trigger('change');
	$('input#course_code').val('${Edit_course_mstr_Details.course_code}');
});

	function Validation() {

		if ($("#type_of_content_id").val().trim() == "0") {
			alert("Please Select Type of Lecture.");
			$("select#type_of_content_id").focus();
			return false;
		}
		if ($("#course_name").val().trim() == "") {
			alert("Please Enter Course Name.");
			$("input#course_name").focus();
			return false;
		}
		
		if ($("#course_code").val().trim() == "") {
			alert("Please Enter Course Code.");
			$("input#course_name").focus();
			return false;
		}

		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}

		return true;

	}
	
document.addEventListener('DOMContentLoaded', function () {			
		
		document.getElementById('update').onclick = function () {
			return Validation();
		};
		
		document.getElementById('course_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
				
	});
</script>


<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>


<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Update Module Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update
									Module Master</li>
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
					<form:form action="edit_module_Action" method="POST"
						 modelAttribute="edit_moduleCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Update Module Master</h6>
							<div class="row ">
								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="select-style-1">
										<label>System<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="system_id" id="system_id" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${s_name}" varStatus="num">
													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="select-style-1">
										<label>Degree<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="degree_id" id="degree_id" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="select-style-1">
										<label>Course<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="course_id" class="singleselect form-control form-control-lg" id="course_id">
												<option value="0">--Select--</option>

											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="input-style-1">
										<label for="username">Module<span class="mandatory">*</span></label>
										<input id="module_name" name="module_name" autocomplete="off"
											maxlength="100" type="text" placeholder="Module" />
									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="select-style-1">
										<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<input type="hidden" id="id" name="id" value="0" />
							</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
								<ul class="buttons-group d-flex justify-content-center">
									<li><a href="module_url"
										class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
											<i class="lni lni-chevron-left"></i>Back
									</a></li>
									<li><input type="submit" id="update" value="Update"
										class="main-btn deactive-btn btn-hover btnupda" /></li>
								</ul>
								</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>

	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		
		$('#id').val('${Module_Details.id}');
		$('select#system_id').val('${Module_Details.system_id}');
		getdegreelistbysystem();
		$('select#degree_id').val('${Module_Details.degree_id}');
		getcourselistbydegree();
		$('select#course_id').val('${Module_Details.course_id}');
		$('input#module_name').val('${Module_Details.module_name}');
		$('select#status').val('${Module_Details.status}');
	});
	

	function getdegreelistbysystem() {
		var system_name = $("#system_id").val();
		$
				.post('getdegreelistby_system?' + key + "=" + value, {
					system_name : system_name
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_id").html(options);
						});
	}

	function getcourselistbydegree() {
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		$
				.post('getcourselistby_degree_andsystem?' + key + "=" + value,
						{
							degree_id : degree_id,
							system_id : system_id
						})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								//alert(j[i][0])
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_id").html(options);
						});
	}
	
	function Validation() {

		if ($("#system_id").val() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("#degree_id").val() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		if ($("#course_id").val() == "0") {
			alert("Please Select Course.");
			$("select#course_id").focus();
			return false;
		}
		if ($("#module_name").val().trim() == "") {
			alert("Please Enter Module.");
			$("input#module_name").focus();
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

		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem();
		};
		
		document.getElementById('degree_id').onchange = function() {
			getcourselistbydegree();
		};
		
		document.getElementById('module_name').onkeypress = function() {
			return onlyAlphaNumeric(event, this);
		};

	});
</script>
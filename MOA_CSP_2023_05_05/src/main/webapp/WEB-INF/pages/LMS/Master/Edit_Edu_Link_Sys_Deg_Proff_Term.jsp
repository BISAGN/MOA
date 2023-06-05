<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<script type="text/javascript" src="js/watermark/common.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Update Link System Degree Profession Term Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update
									Link System Degree Profession Term Master</li>
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
				<div class="col-12">
					<!-- input style start -->
					<form:form name="Editlink_sys_deg_prof_term_Url" id="Editlink_sys_deg_prof_term_Url" action="Edit_link_sys_deg_prof_term_Action"
						method="POST" class="form-horizontal"
						modelAttribute="Edit_link_sys_deg_prof_term_UrlCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Update Link System Degree Profession Term Master</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>System<span class="mandatory">*</span></label> 
										<div class="select-position">
											<select name="system_id" id="system_id" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getSystemList}"
													varStatus="num">
													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
												</c:forEach>
											</select>
										</div>
										
									</div>
									<!-- end input -->
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Degree<span class="mandatory">*</span></label> 	
											<div class="select-position">
											<select name="degree_id" id="degree_id" class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
											</select>
										</div>
									</div>
									<!-- end input -->
								</div>
                               <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Profession<span class="mandatory">*</span></label> 
											<div class="select-position">
											<select name="prof" id="prof" class="singleselect form-control form-control-lg">
											<option value="0">--Select--</option>
												<c:forEach var="item" items="${getprofessionalList}"
													varStatus="num">
													<option value="${item.id}" name="${item.professional}">${item.professional}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Term<span class="mandatory">*</span></label> 
											<div class="select-position">
											<select name="term" id="term" class="singleselect form-control form-control-lg">
											<option value="0">--Select--</option>
												<c:forEach var="item" items="${geti3_termList}"
													varStatus="num">
													<option value="${item.id}" name="${item.term}">${item.term}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
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
									<div class="input-style-2 mt-3">
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>

									<!-- end select -->
								</div>

							</div>
							<div class="row">
								<ul class="buttons-group d-flex justify-content-center">
									<li><a href="link_sys_deg_prof_term_Url"
										class="main-btn dark-btn-outline  btn-hover btn-iconic-icon">
											<i class="lni lni-chevron-left"></i>Back
									</a></li>
									<li><input type="submit" id="update" value="Update"
										class="main-btn deactive-btn btn-hover" /></li>
								</ul>
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
		$('#id').val('${sys_deg_prof_term_Details.id}');
		$('select#system_id').val('${sys_deg_prof_term_Details.system}');
		$('select#degree_id').val('${sys_deg_prof_term_Details.degree}');
		$('select#prof').val('${sys_deg_prof_term_Details.prof}');
		$('select#term').val('${sys_deg_prof_term_Details.term}');
		$('select#status').val('${sys_deg_prof_term_Details.status}');
		$('#status').trigger('change');
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});

	});

	function GetSystemFromDegree() {
		var system_id = $("select#system_id").val();

		$.post("getSystemlistFromDegree?" + key + "=" + value,
						{
							system_id : system_id
						},
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
	function Validation() {

		if ($("select#system_id").val() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("select#degree_id").val() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		if ($("select#prof").val() == "0") {
			alert("Please Select Profession.");
			$("select#prof").focus();
			return false;
		}
		if ($("select#term").val() == "0") {
			alert("Please Select Term.");
			$("select#term").focus();
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
		document.getElementById('system_id').onchange = function() {
			return GetSystemFromDegree();
		};
// 		document.getElementById('system_name').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(this, event);
// 		};
		

// 		document.getElementById('system_abbr').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(this, event);
// 		};

		document.getElementById('update').onclick = function() {
			return Validation();
		};

	});
</script>

	




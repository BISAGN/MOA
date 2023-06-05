<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<!-- <script type="text/javascript" src="js/watermark/common.js"></script> -->

<!-- <section class="dashboard-page"> -->
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Edit Student/Intern Rules Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update Student/Intern Rules Master</li>
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
					<form:form name="Edit_student_intern_ruleUrl" id="Edit_student_intern_ruleUrl" action="edit_student_intern_rule_action"
						method="POST" class="form-horizontal"
						modelAttribute="edit_student_intern_ruleCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Update Student/Intern Rules Master</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Role Name<span class="mandatory">*</span></label> <input
											type="text" id="role_name" name="role_name"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Please Enter Role Name" />
										
									</div>
									<!-- end input -->
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Total Years<span class="mandatory">*</span></label> <input
											type="number" id="year" name="year" 
											class=" form-control UpperClassName txt-transupp" autocomplete="off"  placeholder="Pleas Enter Total Years" />
									</div>
									<!-- end input -->
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Total Months<span class="mandatory">*</span></label> <input
											type="number" id="month" name="month" 
											class=" form-control UpperClassName txt-transupp" autocomplete="off"  placeholder="Please Enter Total Months" />
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
									<li><a href=student_intern_Rule_URL
										class="main-btn dark-btn-outline  btn-hover btn-iconic-icon">
											<i class="lni lni-chevron-left"></i>Back
									</a></li>
									<li><input type="submit" id="update" value="Update"
										class="main-btn deactive-btn btn-hover" />
										<input type="hidden" name="updateid" id="updateid" value="${updateid}"
										/></li> 
								</ul>   
							</div>

						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>
	</div>
<!-- </section> -->

<script nonce="${cspNonce}" type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('update').onclick = function() {
		return Validation();
	};
	document.getElementById('role_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};

// 	document.getElementById('Attachment').onkeypress = function() {
// 		return onlyAlphabetsStringSpace(this, event);
// 	}; 
});
	$(document).ready(function() {
		
		$('#id').val('${student_intern_Rule_details.id}'); 
		$('#role_name').val('${student_intern_Rule_details.role_name}');
		$('#year').val('${student_intern_Rule_details.year}'); 
		$('#month').val('${student_intern_Rule_details.month}'); 
		$('#status').val('${student_intern_Rule_details.status}'); 
		$('#status').trigger('change');
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
	});


	function Validation() {

		if ($("#role_name").val().trim() == "") {
			alert("Please Enter Role Name.");
			$("input#role_name").focus();
			return false;
		}
		
		if ($("input#year").val().trim() == "") {
			alert("Please Enter Total Years.");
			$("input#year").focus();
			return false;
		}
		
		if ($("input#month").val().trim() == "") {
			alert("Please Enter Total Months.");
			$("input#month").focus();
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}
	
		return true;

		

	}

</script>

	

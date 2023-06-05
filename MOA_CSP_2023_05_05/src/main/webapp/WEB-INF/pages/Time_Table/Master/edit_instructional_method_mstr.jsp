<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Update Instructional Methods Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update
									Instructional Methods Master</li>
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
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<!-- input style start -->
					<form:form name="Edit_Instructional_Master_Master" id="Edit_Instructional_Master_Master" action="edit_Instructional_Master_Action"
						method="POST"
						modelAttribute="edit_Instructional_MasterCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Update Instructional Methods Master</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Instructional Methods Master<span class="mandatory">*</span></label> <input
											type="text" id="instructional_method_name" name="instructional_method_name"
											placeholder="Instructional Methods Master" />
									</div>
									<!-- end input -->
								</div>
							
							</div>
							<div class="row">
								<ul class="buttons-group d-flex justify-content-center">
									<li><a href="Instructional_MasterUrl"
										class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btnback">
											<i class="lni lni-chevron-left"></i>Back
									</a></li>
									<li><input type="submit" id="update" value="Update"
										class="main-btn deactive-btn btn-hover" />
										
										<input type="hidden" name="updateid" id="updateid" value="${updateid}"
										class="main-btn deactive-btn btn-hover btnupda" /></li>
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
		$('#id').val('${instructional_Method.id}');
		$('#instructional_method_name').val('${instructional_Method.instructional_method_name}');
		
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});

	});


	function Validation() {

		if ($("#instructional_method_name").val() == "") {
			alert("Please Select Instructional Methods");
			$("#instructional_method_name").focus();
			return false;
		}
// 		if ($("input#refer_code").val().trim() == "") {
// 			alert("Please Enter Refer Code.");
// 			$("input#refer_code").focus();
// 			return false;
// 		}
		
		return true;

	}

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('instructional_method_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event);
		};
		
// 		document.getElementById('refer_code').onkeyup = function() {
// 			return isNumberKey0to9(event, this);
// 		};
		

		document.getElementById('update').onclick = function() {
			return Validation();
		};

	});
</script>

	

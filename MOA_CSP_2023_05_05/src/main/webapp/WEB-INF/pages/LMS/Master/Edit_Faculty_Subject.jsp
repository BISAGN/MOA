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
						<h2>Update Faculty Subject</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update Faculty Subject</li>
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
					<form:form name="Edit_Faculty_Subject_MasterUrl" id="Edit_Faculty_Subject_MasterUrl" action="edit_Faculty_Subject_Master_action"
						method="POST" class="form-horizontal"
						modelAttribute="edit_Faculty_Subject_MasterCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Update Faculty Subject</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Faculty Subject<span class="mandatory">*</span></label> <input
											type="text" id="Faculty_Subject" name="Faculty_Subject"  maxlength="50"  placeholder="Enter Faculty Subject"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Enter Faculty_Subject" />
									</div>
									<!-- end input -->
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Faculty Course<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="fac_course_id" id="fac_course_id" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${course_name}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<!-- end select -->
								</div> 
							</div>
							<div class="row">
								<ul class="buttons-group d-flex justify-content-center">
									<li><a href=Faculty_Subject_MasterUrl
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
	document.getElementById('Faculty_Subject').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	}; 
});
	$(document).ready(function() {
		
		$('#id').val('${Faculty_Subject_Master_details.id}'); 
		$('#Faculty_Subject').val('${Faculty_Subject_Master_details.subject_name}'); 
		
		$('#fac_course_id').val('${Faculty_Subject_Master_details.fac_course_id}');
		$('#fac_course_id').trigger('change');
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
	});


	function Validation() {

		if ($("#Faculty_Subject").val() == "") {
			alert("Please Enter Faculty Subject");
			$("#designation").focus();
			return false;
		}
		
		if ($("#fac_course_id").val() == "0") {
			alert("Please Select Faculty Course");
			$("#fac_course_id").focus();
			return false;
		}
		return true;

	}

</script>

	

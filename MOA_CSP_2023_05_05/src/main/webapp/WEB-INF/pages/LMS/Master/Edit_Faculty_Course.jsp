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
						<h2>Update Faculty Course</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update Faculty Course</li>
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
					<form:form name="Edit_Faculty_Course_MasterUrl" id="Edit_Faculty_Course_MasterUrl" action="edit_Faculty_Course_Master_action"
						method="POST" class="form-horizontal"
						modelAttribute="edit_Faculty_Course_MasterCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Update Faculty Course</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Faculty Course<span class="mandatory">*</span></label> <input
											type="text" id="Faculty_Course" name="Faculty_Course"  maxlength="50"  placeholder="Enter Faculty Course"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Enter Faculty_Course" />
									</div>
									<!-- end input -->
								</div>
								
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Type Of Degree<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="type_of_degree" id="type_of_degree" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${type_degree_id}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<!-- end select -->
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
								

									<!-- end select -->
								</div>
							</div>
							<div class="row">
								<ul class="buttons-group d-flex justify-content-center">
									<li><a href=Faculty_Course_MasterUrl
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
	document.getElementById('Faculty_Course').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	}; 
});
	$(document).ready(function() {
		
		$('#id').val('${Faculty_Course_Master_details.id}'); 
		$('#Faculty_Course').val('${Faculty_Course_Master_details.course_name}'); 
		
		$('#type_of_degree').val('${Faculty_Course_Master_details.type_degree_id}'); 
		$('#type_of_degree').trigger('change');
		$('#status').val('${Faculty_Course_Master_details.status}'); 
		$('#status').trigger('change');
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
	});


	function Validation() {

		if ($("#Faculty_Course").val() == "") {
			alert("Please Enter Faculty Course");
			$("#Faculty_Course").focus();
			return false;
		}
		if ($("#type_of_degree").val() == "0") {
			alert("Please Select Type Of Degree");
			$("#type_of_degree").focus();
			return false;
		}

		return true;

	}

</script>

	

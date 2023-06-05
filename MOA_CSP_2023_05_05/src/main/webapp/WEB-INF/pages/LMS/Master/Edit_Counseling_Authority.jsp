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
						<h2>Update Counseling Authority</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update Counseling Authority</li>
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
					<form:form name="Edit_Counseling_Authority_MasterUrl" id="Edit_Counseling_Authority_MasterUrl" action="edit_counseling_authority_Master_action"
						method="POST" class="form-horizontal"
						modelAttribute="edit_counseling_authority_MasterCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Update Counseling Authority</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Counseling Authority<span class="mandatory">*</span></label> <input
											type="text" id="counseling_authority" name="counseling_authority"  maxlength="50" placeholder="Enter Counseling Authority"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Enter counseling_authority" />
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
								

									<!-- end select -->
								</div>

							</div>
							<div class="row">
								<ul class="buttons-group d-flex justify-content-center">
									<li><a href=Counseling_Authority_MasterUrl
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
	document.getElementById('counseling_authority').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	}; 
});
	$(document).ready(function() {
		
		$('#id').val('${counseling_authority_Master_details.id}'); 
		$('#counseling_authority').val('${counseling_authority_Master_details.counseling_authority}'); 
		$('#status').val('${counseling_authority_Master_details.status}'); 
		$('#status').trigger('change');
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
	});


	function Validation() {

		if ($("#counseling_authority").val() == "") {
			alert("Please Enter counseling authority");
			$("#counseling_authority").focus();
			return false;
		}
		

		return true;

	}

</script>

	

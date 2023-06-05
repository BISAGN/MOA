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
						<h2>Update No. Of Attempt</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update No. Of Attempt</li>
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
					<form:form name="Edit_No_Of_Attempt_MasterUrl" id="Edit_No_Of_Attempt_MasterUrl" action="edit_No_Of_Attempt_Master_action"
						method="POST" class="form-horizontal"
						modelAttribute="edit_Attachment_MasterCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Update No. Of Attempt</h6>
							<div class="row">
							 <div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">System<span class="mandatory">*</span></label>
									<div class="select-position">
									<select name="system_id" id="system_id">
									  <option value="0">--Select--</option>
										<c:forEach var="item" items="${getSystemList}" varStatus="num">
													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
										</c:forEach>
								   </select>
							     </div>
								</div>					
							  </div>
							  <div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Degree<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="degree_id" id="degree_id" class="form-control">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>								
							  </div>
							  <div class="col-12 col-sm-12 col-md-6 col-lg-3">
								 <div class="select-style-1">
									<label for="text-input">Professional<span class="mandatory">*</span></label>
									  <div class="select-position">
											<select name="professional_id" id="professional_id">
									      <option value="0">--Select--</option>
										<c:forEach var="item" items="${getprofessionalList}" varStatus="num">
											<option value="${item.id}" name="${item.professional}">${item.professional}</option>
										</c:forEach>
								     </select>										
								 </div>
								 </div>								
							   </div>
					
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-2">
										<label>No. Of Attempt<span class="mandatory">*</span></label> <input
											type="number" id="no_of_attempt" name="no_of_attempt"
											class="autocomplete txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Enter No.Of Attempt" />
										
									</div>
									<!-- end input -->
								</div>
								

							</div>
							<div class="row">
								<ul class="buttons-group d-flex justify-content-center">
									<li><a href=No_Of_Attempt_Url
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

<script nonce='' type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('update').onclick = function() {
		return Validation();
	};
	document.getElementById('system_id').onclick = function() {
		getdegreelistbysystem111();
	};
});
	
	$(document).ready(function() {
		
		$('#id').val('${nod.id}'); 
		$('#system_id').val('${nod.system_id}'); 
		getdegreelistbysystem111();
		$('#degree_id').val('${nod.degree_id}'); 
		$('#professional_id').val('${nod.professional_id}'); 
		$('#no_of_attempt').val('${nod.no_of_attempt}');

	});
	
	function getdegreelistbysystem111() {
		var system_name = $("#system_id").val();
		$
				.post('getDegreeListbysystem2?' + key + "=" + value, {
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
	

	function Validation() {

		if ($("#system_id").val().trim() == "0") {
			alert("Please Select System.");
			$("input#system_id").focus();
			return false;
		}
		if ($("#degree_id").val().trim() == "0") {
			alert("Please Select Degree.");
			$("input#degree_id").focus();
			return false;
		}
		if ($("#professional_id").val().trim() == "0") {
			alert("Please Select Professional.");
			$("input#professional_id").focus();
			return false;
		}
		if ($("#no_of_attempt").val().trim() == "") {
			alert("Please Enter No Of Attempt.");
			$("input#no_of_attempt").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}
		return true;
	}

</script>

	

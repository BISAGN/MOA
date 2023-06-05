<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js/watermark/common.js"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>


<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Update Specialization Master </h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update
									Specialization Master</li>
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
					<form:form name="Specialization" id="Specialization" action="edit_Specialization_Action"
						method="POST" class="form-horizontal"
						modelAttribute="edit_Specialization_CMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Update Specialization Master</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="text-input">Degree:</label>
									<div class="select-position">
										<select name="degree" id="degree" >
								        	<option value="0">---Select---</option>
								        	<c:forEach var="item" items="${getdegreeList}" varStatus="num">
												<option value="${item.id}" name="${item.id}">${item.degree_name}</option> 
											</c:forEach>
										</select>
									</div>
								</div>								
								
								<!-- end select -->
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Specialization Name<span class="mandatory">*</span></label> <input
											type="text" id="specialization_name" name="specialization_name"
											 placeholder="Specialization Name" />
										
									</div>
									<!-- end input -->
								</div>
							
								<div class="input-style-2 mt-3">
										<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
								</div>	
							
							</div>
							<div class="row">
								<ul class="buttons-group d-flex justify-content-center">
									<li><a href="specialization_detailsUrl"
										class="main-btn dark-btn-outline  btn-hover btn-iconic-icon">
											<i class="lni lni-chevron-left"></i>Back
									</a></li>
									<li><input type="submit" id="update" value="Update"
										class="main-btn deactive-btn btn-hover" />
										<input type="hidden" name="updateid" id="updateid" value="${updateid}"
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

<script nonce='r02122i021210p215a12455l12411' type="text/javascript">

	$(document).ready(function() {
		
	
		$('#id').val('${specialization_Name.id}');
		$('#specialization_name').val('${specialization_Name.specialization_name}');
		$('select#degree').val('${specialization_Name.degree}');
		
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		
	});


	function Validation() {

		if ($("#specialization_name").val() == "") {
			alert("Please Select Specialization Name");
			$("#specialization_name").focus();
			return false;
		}
		if ($("select#degree").val() == 0) {
			alert("Please Select Type of Degree");
			$("select#degree").focus();
			return false;
		}		return true;

	}

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('specialization_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		

		document.getElementById('degree').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
		document.getElementById('update').onclick = function() {
			return Validation();
		};

	});
	

</script>

	

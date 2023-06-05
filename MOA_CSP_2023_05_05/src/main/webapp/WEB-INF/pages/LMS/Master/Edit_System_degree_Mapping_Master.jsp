<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
<section class="dashboard-page">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="title mb-30">
					<h2><span id="lbladd"></span>Update Link System & Degree Master</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="commonDashboard">Dashboard</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">Update Link System & Degree Master</li>
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
                <form:form action="Edit_System_Degree_Mapping_Master_action" method="POST"  modelAttribute="Edit_System_Degree_Mapping_Master_cmd">
				<div class="card-style mb-30">
				<div class="custom-field-block">
					<h6 class="mb-25">Update Link System & Degree Master</h6>
						<div class="row">

							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
							
								<div class="select-style-1">
									<label for="username">System<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="system_name" id="system_name" class="singleselect form-control form-control-lg">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${s_name}" varStatus="num">
												<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
											</c:forEach>
										</select>
								 	<input type="hidden" id="id" name="id" value="0"
										class="form-control" autocomplete="off" />
									</div>
								</div>	
													
								
								<!-- end select -->
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								
							
							<div class="select-style-1">
									<label for="username">Degree<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="degree_name" id="degree_name" class="singleselect form-control form-control-lg">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${d_name}" varStatus="num">
										<option value="${item.id}" name="${item.degree_name}">${item.degree_name}</option>
									</c:forEach>
								</select>
								 	<input type="hidden" id="id" name="id" value="0"
										class="form-control" autocomplete="off" />
									</div>
								</div>	
							</div>
							
							
						
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="form-control">
												<c:forEach var="item" items="${ActiveInActiveList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>								
								
								<!-- end select -->
							</div>
						</div>		
						</div>
						<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">			
						<ul class="buttons-group mainbtn">
							<li>
							  <a href="System_Degree_Mapping_Master_Url" class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-cancel"><i class="lni lni-chevron-left"></i>Back</a>
							</li>
							<li>
								<input type="submit"  class="main-btn deactive-btn btn-hover btn-update" value="Update" id="btn-update">
							</li>					
						</ul>
						</div>
						</div>
						</div>
				</div>
				
				<!-- end card -->
            </form:form>
			</div>
		</div>
	</div>
</section>
</div>

<script nonce="${cspNonce}" type="text/javascript">

	$(document).ready(function() {
		
				$('#id').val('${Edit_system_degree_mapping_details.id}');
				$('select#system_name').val('${Edit_system_degree_mapping_details.system_name}');
				
				$('select#degree_name').val('${Edit_system_degree_mapping_details.degree_name}');
				$('#degree_name').trigger('change');
				$('select#status').val('${Edit_system_degree_mapping_details.status}');
				$('#status').trigger('change');
			});

	function Validation() {

		if ($("select#system_name").val().trim() == "0") {
			alert("Please Select System");
			$("select#system_name").focus();
			return false;
		}
		if ($("select#degree_name").val().trim() == "0") {
			alert("Please Select Degree");
			$("select#degree_name").focus();
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}

		return true;
	}

// csp----------------------------
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('btn-update').onclick = function() {
			return Validation();
		};
		  
	});
</script>


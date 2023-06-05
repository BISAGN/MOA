<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />

<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css">

<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<script>
	var username = "${username}";
</script>

<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>Update System Degree Mapping</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="commonDashboard">Dashboard</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">Update System Degree Mapping </li>
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
            	<form:form action="Edit_System_Degree_Mapping_Inst_action" method="POST" class="form-horizontal" modelAttribute="Edit_System_Degree_Mapping_Inst_cmd">
				<div class="card-style mb-30">
					<h6 class="mb-25">Update System Degree Mapping</h6>
						<div class="row">
							

							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									 <label class=" form-control-label" for="username">System <span class="mandatory">*</span></label>
									 
									<input type="hidden" value="${system[0][1]}" id="system_hid" name="system_hid" 
											class="form-control form-control-lg form-control-a effect-9" value="0" >	
											
											<input type="text" value="${system[0][0]}" id="system_id2" name="system_id2" 
											class="form-control form-control-lg form-control-a effect-9" readonly="readonly">
<!-- 									<div class="select-position"> -->
<!-- 											<select name="system_id" id="system_id" class="form-control" > -->
<!-- <!-- 											onchange="getAprrovedDegree();" --> 
<!-- 										<option value="0">--Select--</option> -->
<%-- 													<c:forEach var="item" items="${s_name}" varStatus="num"> --%>
<%-- 												<option value="${item.id}" name="${item.system_name}">${item.system_name}</option> --%>
<%-- 											</c:forEach> --%>
<!-- 										</select> -->
										
<!-- 											<input type="hidden" id="id" name="id" value="0" class="form-control" -->
<!-- 												autocomplete="off" /> -->
												
<!-- 												<input type="hidden" id="institute_id" name="institute_id" value="0" class="form-control" autocomplete="off" /> -->
									
<!-- 									</div> -->
								</div>								
	
								<!-- end select -->
							</div>
							
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									 <label class=" form-control-label">Degree<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="degree_id" id="degree_id" class="form-control">
											<option value="0">--Select--</option>
													<c:forEach var="item" items="${d_name}" varStatus="num">
														<option value="${item.id}" name="${item.degree_name}">${item.degree_name}</option>
													</c:forEach>
										</select>
									</div>
								</div>	
							</div>
							
							
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									 <label class=" form-control-label">Status<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="status" id="status" class="form-control">
											<c:forEach var="item" items="${ActiveInActiveList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
							
									</div>
								</div>								
								<div class="input-style-2 mt-3">
								<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
								
								<!-- end select -->
							</div>
							
			

						</div>						
	              	
						<ul class="buttons-group mainbtn">

							<li>
			                    <a href="System_Degree_Mapping_InstUrl" id="" class="main-btn dark-btn-outline  btn-hover btn-iconic-icon"><i class="lni lni-chevron-left"></i>Back</a>
			                </li>
							<li>
			                    <input type="submit" class="main-btn deactive-btn btn-hover" id ="btn-update" value="Update" >
<!-- 			                    onclick="return Validation();" -->
			                 </li>
<!-- 							<li> -->
<!-- 								<input type="reset" class="main-btn dark-btn n btn-hover btn-clear" type="button" value="Reset"  onclick="clearall();"> -->
<!-- 							</li> -->
						</ul>
								
				</div>
				<!-- end card -->
            </form:form>
			</div>
		</div>
	</div>

</div>
</section>	

<%-- <form:form action="Edit_System_Degree_Mapping_Inst_action" method="POST" class="form-horizontal"
	modelAttribute="Edit_System_Degree_Mapping_Inst_cmd">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header title">
				<h5>
					<span id="lbladd"></span> <b> UPDATE SYSTEM DEGREE MAPPING</b>
				</h5>
			</div>
						<div class="card-body card-block">
				<div class="row">			
					<div class="col-md-4 left_content">
						<div class="row form-group">
							<div class="col-md-6">
								<label for="username">SYSTEM <span class="star_design"></span> <span style="color:#ff0000">*</span></label>
							</div>
							<div class="col-md-6">
								<select name="system_id" id="system_id" class="form-control"  onchange="getAprrovedDegree();">
								<option value="0">--Select--</option>
											<c:forEach var="item" items="${s_name}" varStatus="num">
										<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
									</c:forEach>
								</select>
								<div class="col-md-6">
									<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
										<input type="hidden" id="institute_id" name="institute_id" value="${userId}" class="form-control"
										autocomplete="off" />
								</div>
							</div>
	
						</div>
					</div>		
				
					<div class="col-md-4 left_content">
						<div class="row form-group">
							<div class="col-md-6">
								<label for="username">DEGREE <span class="star_design"></span> <span style="color:#ff0000">*</span></label>
							</div>
							<div class="col-md-6">
									<select name="degree_id" id="degree_id" class="form-control">
							<option value="0">--Select--</option>
											<c:forEach var="item" items="${getDegreeList}" varStatus="num">
										<option value="${item.id}" name="${item.degree_name}">${item.degree_name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					
					<div class="col-md-4 left_content">
						<div class="row form-group">
							<div class="col-md-6">
								<label for="username">STATUS<span class="star_design"></span> <span style="color:#ff0000">*</span></label>
							</div>
							<div class="col-md-6">
								<select name="status" id="status" class="form-control">
											<c:forEach var="item" items="${ActiveInActiveList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>

									<c:forEach var="item" items="${getEducationStatusList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
								<div class="col-md-6">
									<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
							</div>
						</div>
					</div>					
				</div>
			</div>

			<div class="card-footer" align="center">
	        		     <a href="System_Degree_Mapping_InstUrl" id=""	class="btn-cancel">BACK</a>
	        		     <button type="reset" class="btn-clear" value="Reset" onclick="clearall();"> RESET</button>
	              		 <input type="submit" class="btn-update" value="UPDATE" onclick="return Validation();"> 
	              	</div>
		</div>
	</div>
</form:form> --%>


<script nonce="${cspNonce}" type="text/javascript">
var deg_id = '${Edit_system_degree_mapping_inst_details.degree_id}';

$(document).ready(function() {
		
		$.ajaxSetup({
			async : false
		});
	$("#institute_id").val('${institute_id}');
	$('#id').val('${Edit_system_degree_mapping_inst_details.id}');

// 	$('#system_id').val('${Edit_system_degree_mapping_inst_details.system_id}');
	$('#system_id2').change();
	$('select#status').val('${Edit_system_degree_mapping_inst_details.status}');
	$('select#degree_id').val('${Edit_system_degree_mapping_inst_details.degree_id}');
	$('select#degree_id').change();
	getAprrovedDegree('${system[0][1]}');
});
	

// 	function getAprrovedDegree(id) {
// // 		var selval = $("#system_id").val();
// 		var selval = id;
// 		$
// 				.post(
// 						"getdegrebysysidlist?" + key + "=" + value,
// 						{
// 							selval : selval
// 						},
// 						function(j) {
// 							var options = '<option value="' + "0" + '">'
// 									+ "--Select--" + '</option>';
// 							for (var i = 0; i < j.length; i++) {
// 								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
// 										+ j[i][1] + '</option>';
// 							}
// 							$("select#degree_id").html(options);
// 							if (deg_id != "") {
// 								$("select#degree_id").val(deg_id);
// 								$('select#degree_id').change();
// 							}

// 						});
// 	}
// 	function getAprrovedDegree(id) {
		
// // 		var selval = $("#system_hid").val();
// 		var selval = id;
// 		$
// 				.post(
// 						"getdegrebysysidlist?" + key + "=" + value,
// 						{
// 							selval : selval
// 						},
// 						function(j) {
// 							if(j.length>0){
// 							var options = '<option value="' + "0" + '">'
// 									+ "--Select--" + '</option>';
// 							for (var i = 0; i < j.length; i++) {
// 								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
// 										+ j[i][1] + '</option>';
// 							}
// 							$("select#degree_id").html(options);
// 							}
// 						});
// 	}


function getAprrovedDegree(id) {
// 		var selval = $("#system_id").val();
var selval = id;
		$
				.post(
						"getdegrebysysidlist?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_id").html(options);
							if (deg_id != "") {
								$("select#degree_id").val(deg_id);
								$('select#degree_id').change();
							}

						});
	}



	function Validation() {

// 		if ($("#system_id").val() == 0) {
// 			alert("Please Enter System");
// 			$("input#system_id").focus();
// 			return false;
// 		}

		if ($("#degree_id").val() == 0) {
			alert("Please Enter degree");
			$("input#degree_id").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	// Start csp
	document.addEventListener('DOMContentLoaded', function() {
		
// 	document.getElementById('system_id').onchange = function() {
// 		getAprrovedDegree();
// 	};
	document.getElementById('btn-update').onclick = function() {
		return Validation();
	};
});
	
	// end csp
</script>


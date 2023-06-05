<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>


<!-- <link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link> -->
<!-- datatable style and js start -->
<!-- <link rel="stylesheet" -->
<!-- 	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css"> -->
<!-- <script type="text/javascript" -->
<!-- 	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script> -->
<!-- <script type="text/javascript" -->
<!-- 	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script> -->
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->
<!-- datatable style and js end -->


<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						
						<h2> <span id="lbladd"></span>Update Inquiry Link Role Master</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update Inquiry Link Role Master</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="Inq_Link_Role_form" id="Inq_Link_Role_form"
						action="Edit_Inq_Link_Role_Action" method="post" 
						modelAttribute="Edit_Inq_Link_Role_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<!-- <h6 class="mb-25">Classroom Master</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										
											
											
											<div class="input-style-1">
											<label for="username">Inquiry Number<span class="mandatory">*</span></label>
											<input type="text" id="inq_no" name="inq_no"
												placeholder="Inquiry Number"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="50" />
<!-- 												<select class="form-control form-control-lg" -->
<!-- 													name="Inq_Cat" id="Inq_cat"> -->
<!-- 													<option value="0">-- Select --</option> -->
<%-- 													<c:forEach var="item" items="${getInq_CatList}" varStatus="num"> --%>
<%-- 														<option value="${item.id}" name="${item.id}">${item.inq_cat}</option> --%>
<%-- 													</c:forEach> --%>
<!-- 												</select> -->
											</div>
										
										
<!-- 									<div class="input-style-1"> -->
<!-- 										<input type="hidden" id="id" name="id" value="0" autocomplete="off" /> -->
<!--                                	 	</div> -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Role<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="role" id="role"  class="singleselect form-control form-control-lg">
													<option value="0">-- Select --</option>
			             							<c:forEach var="item" items="${getRoleNameList}" varStatus="num" >
			             								<option value="${item.roleId}">${item.role}</option>
			             							</c:forEach>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">User<span class="mandatory">*</span></label>
											<div class="select-position">
											
											<select name="user" id="user"  class="singleselect form-control form-control-lg">
													<option value="0">-- Select --</option>
												</select>
<!-- 												<select name="user" id="user" -->
<!-- 													class="form-control form-control-lg"> -->
<!-- 													<option value="0">-- Select --</option> -->
<%-- 													<c:forEach var="item" items="${getUserList}" --%>
<%-- 														varStatus="num"> --%>
<%-- 														<option value="${item.id}" name="${item.id}">${item.User}</option> --%>
<%-- 													</c:forEach> --%>
<!-- 												</select> -->
											</div>
										</div>
<!-- 									<div class="input-style-1"> -->
								         <input type="hidden" id="id" name="id" value="0" autocomplete="off" />
<!--                                     </div> -->
                                    
									</div>
<!-- 									<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
<!-- 										<div class="select-style-1"> -->
<!-- 											<label for="text-input">Status<span -->
<!-- 												class="mandatory">*</span></label> -->
<!-- 											<div class="select-position"> -->
<!-- 												<select name="status" id="status"  class=" form-control form-control-lg"> -->
<!-- 													<option value="0">-- Select --</option> -->
<%-- 			             							<c:forEach var="item" items="${ActiveInActiveList}" varStatus="num" > --%>
<%-- 			             								<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 			             							</c:forEach> --%>
<!-- 												</select> -->
<!-- 											</div> -->
<!-- 										</div> -->
										<!-- end select -->
<!-- 									</div> -->
								</div>
							</div>
							
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											
											<li><input id="btn-update"
												class="main-btn deactive-btn btn-hover btnupda" type="submit"
												value="Update" /></li>
											<li><a href="Inquiry_Link_Role_Mster_Url"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback" type="button"><i class="lni lni-chevron-left"></i>Back</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>	
			</div>
		</div>
	</div>
</section>


<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {
	debugger;
	$('#id').val('${Inquiry_Mstr.id}');
	$('#inq_no').val('${Inquiry_Mstr.inq_no}');
	$('#role').val('${Inquiry_Mstr.role}');
	getuser(); 
	$('#user').val('${Inquiry_Mstr.user_id1}');

	$('#status').val('${Inquiry_Mstr.status}');
	
	
	
	$('#user').trigger('change');
			if (window.location.href.includes("msg")) {
				var url = window.location.href.split("?msg")[0];
				window.location = url;
			}
			
		});
	
	
	
	document.addEventListener('DOMContentLoaded', function () {	
	document.getElementById('btn-update').onclick = function() {
		return Validation();
	};
	document.getElementById('role').onchange = function() {
		return getuser();
	};

	});	
	
	function getuser() {
		$.ajaxSetup({
			async : false
		});	
		  var role_id = $("select#role").val();

	      $.post("getuserform?" + key + "=" + value,{role_id : role_id},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "-- Select --" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1]+ '</option>';
							}
							$("select#user").html(options);
						});
	}


	function Validation() {

		if ($("#inq_no").val() == "") {
			alert("Please Enter Inquiry Number.");
			$("#inq_no").focus();
			return false;
		}
		if ($("#role").val() == "0") {
			alert("Please Select Role.");
			$("select#role").focus();
			return false;
		}
		if ($("#user").val() == "0") {
			alert("Please Select User.");
			$("select#user").focus();
			return false;
		}
// 		if ($("select#status").val() == "0") {
// 			alert("Please Select Status.");
// 			$("select#status").focus();
// 			return false;
// 		}
// 		if ($("select#status").val() == "2") {
// 			alert("Only Select Active Status.");
// 			$("select#status").focus();
// 			return false;
// 		}
		return true;
	}	

</Script>

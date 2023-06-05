<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link> -->
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->
<!-- datatable style and js end-->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						
						<h2> <span id="lbladd"></span>Inquiry Link Role Master</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Inquiry Link Role Master</li>
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
						action="Inq_Link_Role_Action" method="post" 
						modelAttribute="Inq_Link_Role_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<!-- <h6 class="mb-25">Classroom Master</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										
											<div class="input-style-1">
											<label for="username">Inquiry Number<span class="mandatory">*</span></label>
											<input type="text" id="inq_no" name="inq_no"
												placeholder="Inquiry Number"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="50"/>
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
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Role<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="role" id="role"  class="singleselect form-control form-control-lg">
													<option value="0">-- Select --</option>
			             							<c:forEach var="item" items="${getRoleFromStaff_lvl}" varStatus="num" >
			             								<option value="${item.roleId}">${item.role}</option>
			             							</c:forEach>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
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
<!-- 								         <input type="hidden" id="id" name="id" value="0" autocomplete="off" /> -->
<!--                                     </div> -->
                                    
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Status<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="status" id="status" class="singleselect form-control form-control-lg">
<!-- 													<option value="0">-- Select --</option> -->
			             							<c:forEach var="item" items="${ActiveInActiveList}" varStatus="num" >
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
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btnassign" type="submit"
												value="Assign" /></li>
											<li><a href="Inquiry_Link_Role_Mster_Url"
												class="main-btn dark-btn btn-hover btnreset " type="button">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
							
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>	

		<section class="single-detail-block">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="table-wrapper table-responsive custom-datatable-p">
							<table class="table" id="Inquiry_Link_Role_Master">
								<thead>
									<tr>
										<th><h6>Sr No</h6></th>
										<th id="${item.id}"><h6>Inquiry Number</h6></th>
										<th id="${item.id}"><h6>Role</h6></th>
										<th id="${item.id}"><h6>Login Name</h6></th>
										<th id="${item.id}"><h6>Username</h6></th>
										<th class="action"><h6>Action</h6></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>	
				</div>		
			</div>
		</section>
	</div>
</div>
</div>
</section>

<%--  <c:url value="getSearch_Inquiry_Link_Role_Master_Url" var="searchUrl" />  --%>
<%-- <form:form action="${searchUrl}" method="post" id="searchForm"  --%>
<%-- 	name="searchForm" modelAttribute="Inquiry_Link_role1">  --%> 
<!-- 	<input type="hidden" name="Inquiry_Link_Role1" id="Inquiry_Link_Role1" />  -->
<!-- 	<input type="hidden" name="status1" id="status1" value="0" />  -->
<%-- < </form:form>  --%>

 <c:url value="Edit_Inquiry_Link_Role_Master_Url1" var="Edit_Inquiry_Link_Role_Master_Url1" /> 
 <form:form action="${Edit_Inquiry_Link_Role_Master_Url1}" method="post" id="updateForm" name="updateForm" modelAttribute="id2"> 
 	<input type="hidden" name="id2" id="id2"> 
 </form:form> 

 <c:url value="Inquiry_Link_Role_Master_Delete_Url" var="Inquiry_Link_Role_Master_Delete_Url" /> 
 <form:form action="${Inquiry_Link_Role_Master_Delete_Url}" method="post" id="deleteForm" 
 	name="deleteForm" modelAttribute="id1">   
 	<input type="hidden" name="id1" id="id1" value="0" /> 
 </form:form>   

<%--  <c:url value="Inquiry_Link_Role_Master2" var="searchUrl" />  --%>
<%--  <form:form action="${searchUrl}" method="post" id="search2"  --%>
<%--   	name="search2" modelAttribute="comd1">   --%>
<!--  	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />  -->
<%--  </form:form>   --%>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		
		var hidcase = '${hidcase}';		
		if(hidcase != ''){
			$("#inq_no").val(hidcase);
			$("#inq_no").prop("readonly", true);
		}
		
		mockjax1('Inquiry_Link_Role_Master');
		table = dataTable('Inquiry_Link_Role_Master');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		
		
		
	});
	
	
	
	document.addEventListener('DOMContentLoaded', function () {	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};

// 	document.getElementById('Inq_Cat').onkeypress = function() {
// 		return onlyAlphaNumeric(this, event);
// 	};
	
// 	document.getElementById('role').onkeypress = function() {
// 		return onlyAlphaNumeric(this, event);
// 	};
	
// 	document.getElementById('user').onkeypress = function() {
// 		return onlyAlphaNumeric(this, event);
// 	};
	document.getElementById('role').onchange = function() {
		return getuser();
	};
	document.getElementById('inq_no').onkeypress = function () {
		return isNumberKey0to9(event, this);
	 };
	});	
	
	
	function setTimeLoadForTable(){
		
		
		
		document.querySelectorAll('.DeleteInquirylink').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var gdid = document.getElementById('DELETE_INQUIRYLINK'+val).value;
				if (confirm('Are You Sure You Want to Remove Detail ?')) {
					deleteData(gdid);
				} else {
					return false;
				}
			})
		});
		document.querySelectorAll('.Edit_Inquirylink').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var gid = document.getElementById('EDIT_INQUIRYLINK'+val).value;
 				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(gid);
				} else {
					return false;
				}
			})
		});
		
	}

	
	
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

  	
  	
	function data(Inquiry_Link_Role_Master) {
		
		jsondata = [];
		var table = $('#' + Inquiry_Link_Role_Master).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var inq_no = $("#inq_no").val();
		var role = $("#role").val();
		var user = $("#user").val();
		var status = $("#status").val();

		$.post("getFilterInq_Cat_datalist?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			inq_no : inq_no,
			role : role,
			user : user,
			status :status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].inq_no, j[i].role, j[i].login_name, j[i].username, j[i].action ]);
			}
		});
		$.post("getTotalInq_Cat_dataCount1?" + key + "=" + value, {
			Search : Search,
			inq_no : inq_no,
			role : role,
			user : user,
			status :status
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id) {
		$("#id2").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	

// 	function Search() {
// 		$("select#Inquiry_Number").val(Inq_Cat);
// 		$("select#role").val(role);
// 		$("select#user").val(user);
// 		document.getElementById('searchForm').submit();
// 	}

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
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}
		return true;
	}	

</Script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<link rel="stylesheet" href="js/watermark/watermark.css">
<script src="js/watermark/watermark_onclick.js" type="text/javascript"></script>
<!-- <link href="js/dropDown/select2.min.css" rel="Stylesheet"></link> -->
<!-- <script src="js/dropDown/select2.min.js" type="text/javascript"></script> -->
<!-- INTERNAL REMOVE START (NEED TO ADD IN PARTICULAR JSP)-->
			<link rel="stylesheet" href="assets/vendor/internal_css.css">
<!-- INTERNAL REMOVE END--> 

<script nonce="${cspNonce}" type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
	$(document).ready(function () {
		watermarkreport();
	});
</script>

<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>Role Master</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="#">Dashboard</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">Role Master</li>
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
                <form:form name="role_mst" id="role_mst" action="role_mstAction" method='POST' modelAttribute="role_mstCMD"> 
					<div class="card-style mb-30">
					<h6 class="mb-25">Role Master</h6>
						<div class="row">

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-2">
									<label for="text-input">Role Name<span class="mandatory">*</span></label>
									<input id="role" name="role" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off" maxlength="45" placeholder="Enter Role Name" />
								</div>
								<!-- end input -->
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Role Type <span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="role_type" id="role_type" class="singleselect form-control form-control-lg">
								        	<option value="0">--Select--</option>
											<c:forEach var="item" items="${getRoleType}" varStatus="num">
												<option value="${item.role_type}">${item.role_type}</option>
											</c:forEach>
										</select>
									</div>
								</div>								
								
								<!-- end select -->
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Access Level<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="access_lvl" id="access_lvl" class="singleselect form-control form-control-lg">
								        	<option value="">--Select--</option>
											<option value="State">State</option>
											<option value="National">National</option>
											<option value="Staff">Staff</option>
											<option value="Admin">Admin</option>
										</select>
											
									</div>
								</div>								
								
								<!-- end select -->
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Sub Access Level<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="formation_se" id="formation_se" class="singleselect form-control form-control-lg" >
									        <option value="">--Select--</option>
											<option value="ALL">ALL</option>
											<option value="NCISM">NCISM</option>
											<option value="NCH">NCH</option>
											<option value="TPO">TPO</option>
											<option value="BOARD">BOARD</option>
											 <!-- 04_02_2023 -->
				<!-- 							<option value="Brigade">Brigade</option> -->
										</select>
										
<!-- 										<select name="line_dte_se" id="line_dte_se" class="singleselect form-control form-control-lg" > -->
<!-- 									        <option value="">--Select--</option> -->
<!-- 											<option value="Arm">NCISM</option> -->
<!-- 											<option value="Staff">NCH</option> -->
<!-- 										</select> -->
									</div>
								</div>								
								
								<!-- end select -->
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1" id="staff_lvl_div" >
									<label for="text-input">Staff Level</label>
									<div class="select-position">
										<select name="staff_lvl" id="staff_lvl" class="singleselect form-control form-control-lg" >
								        	<option value="">--Select--</option>
											<option value="ALL">ALL</option>
											<option value="NCISM">NCISM</option>
											<option value="NCH">NCH</option>
										</select>
									</div>
								</div>								
								
								<!-- end select -->
							</div>

<!-- 							<div id="sys_div"> -->
								<div class="col-12 col-sm-12 col-md-6 col-lg-3" id="sys_div">
									<div class="select-style-1 ">
										<label for="text-input">System<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="system_name" id="system_name">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getsysList}"
 													varStatus="num"> 
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
												</c:forEach>
											</select>
										</div>
									</div>

									<!-- end select -->
								</div>
<!-- 							</div> -->


								<!-- <div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1" id="sub_lev1" >
									<label for="text-input">Staff Level</label>
									<div class="select-position">
										<select name="staff_se" id="staff_se" class="singleselect form-control form-control-lg">
								        	<option value="">--Select--</option>
											<option value="MGO">MGO</option>
											<option value="SD">SD</option>
											<option value="WE">WE</option>
										</select>
									</div>
								</div>								
								
								end select
							</div> -->
							<div>
								<input id="sub_lvl_text" name="sub_lvl_text" type="hidden">
								<input id="staff_text" name="staff_text" type="hidden">
							</div>
						</div>		

						<ul class="buttons-group mainbtn">

						<!-- 	<li>
							   <a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button" value="Search"><i class="lni lni-search-alt"></i>search</a>
							</li> -->
							<li>
								<input id="btn-save" class="main-btn info-btn btn-hover" type="submit" value="Save">
							</li> 
							<li  >
								<input id="update_btn"  type="submit" class="main-btn deactive-btn btn-hover" value="Update" >
							</li>
							
							<li><a  href="role_mstUrl"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
							
						</ul>
				</div>

				</form:form>
			</div>
		</div>
	</div>

<div class="row">
	<div class="col-12">
		<div class="card-style mb-30">
			<div id="divSerachInput">
				<input id="searchInput" type="text"
						placeholder="&#xF002; Search Word" size="35" class="form-control custom_mst_role_searchip">
			</div>
			<div class="table-wrapper table-responsive custom-datatable-p simple-table" id="att_Tb">
				<table class="table" id="RoleReport">
					<thead>
							<tr>
				       		    <th><h6>Ser No</h6></th>
								<th><h6>Role Name</h6></th>
								<th><h6>Role Type</h6></th>
								<th><h6>Access Level</h6></th>
								<th><h6>Sub Access Level</h6></th>
								<th><h6>Staff Level</h6></th>
								<th class='lastCol'><h6>Action</h6></th>
				            </tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td>${num.index+1}</td>
							<td>${item.role}</td>
							<td>${item.role_type}</td>
							<td>${item.access_lvl}</td>
							<td>${item.sub_access_lvl}</td>
							<td>${item.staff_lvl}</td>
							<td>${item.action}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 				end table -->
			</div>
		</div>
		<!-- 		end card -->
	</div>
	<!-- 	end col -->
</div>

</div>
</section>

<!-- 		old 	 	-->

<%-- <form:form name="role_mst" id="role_mst" action="role_mstAction"
	method='POST' modelAttribute="role_mstCMD">
	<div class="container">
		<div class="card">
			<div class="card-header">
				<h5>Role Master</h5>
			</div>
			<!-- end of card-header -->
			<div class="card-body card-block">
				<div class="row mb-3">
					<div class="col-md-2">
						<label for="text-input">Role Name <strong
							style="color: red;">*</strong></label>
					</div>
					<div class="col-md-3">
						<input id="role" name="role"
							style="font-family: 'FontAwesome', Arial;" maxlength="45"
							class="form-control" autocomplete="off">
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-md-2">
						<label for="text-input">Role Type <strong
							style="color: red;">*</strong></label>
					</div>
					<div class="col-md-4">
						<select name="role_type" id="role_type" style="width: 100%;"
							class="select2 narrow wrap">
							<option value="0">--Select--</option>
							<c:forEach var="item" items="${getRoleType}" varStatus="num">
								<option value="${item.role_type}">${item.role_type}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="row mb-3">
					<div class="col-md-2">
						<label for="text-input">Access Level <strong
							style="color: red;">*</strong></label>
					</div>
<!-- 					onchange="access_lev(this.value)" -->
					<div class="col-md-4">
						<select name="access_lvl" id="access_lvl" style="width: 100%;"
							 class="select2 narrow wrap">
							<option value="">--Select--</option>
							<option value="State">State</option>
							<option value="National">National</option>
							<option value="">Staff</option>
							<option value="Admin">Admin</option>
						</select>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-md-2" id="sub_lev">
						<label for="text-input">Sub Access Level <strong style="color: red;">*</strong></label>
					</div>
					<div class="col-md-4">
						<select name="formation_se" class="form-control" id="formation_se"  onchange="value_pass(this.value)">
							<option value="">--Select--</option>
							<option value="University">University</option>
							<option value="NCISM">NCISM</option>
							<option value="NCH">NCH</option>
<!-- 							<option value="Brigade">Brigade</option> -->
						</select>
						
						<select name="line_dte_se" class="form-control" id="line_dte_se" style="display: none" onchange="value_pass(this.value)">
							<option value="">--Select--</option>
							<option value="Arm">NCISM</option>
							<option value="Staff">NCH</option>
						</select>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-md-2" id="sub_lev1" style="display: none">
						<label for="text-input">Staff Level <strong style="color: red;">*</strong></label>
					</div>
					<div class="col-md-4">
						<select name="staff_se" class="form-control" id="staff_se" style="display: none" onchange="value_pass1(this.value)">
							<option value="">--Select--</option>
							<option value="MGO">MGO</option>
							<option value="SD">SD</option>
							<option value="WE">WE</option>
						</select>
					</div>
				</div>
				<input id="sub_lvl_text" name="sub_access_lvl" type="hidden">
				<input id="staff_text" name="staff_lvl" type="hidden">
			</div>
			<!-- end of card-body -->

			<div class="card-footer">
				<input type="reset" class="btn btn-success btn-sm" value="Clear">
				<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isValid();">
			</div>
			<!-- end of card-footer -->

		</div>
		<!-- end of card -->
	</div>
	<!-- end of container -->
</form:form>

<div class="container">
	<div id="divSerachInput">
		<input id="searchInput" type="text"
			style="font-family: 'FontAwesome', Arial; margin-bottom: 5px; width: 50%;"
			placeholder="&#xF002; Search Word" size="35" class="form-control">
	</div>
	<div id="divPrint">
		<div id="divShow"></div>
		<div class="watermarked" data-watermark="" id="divwatermark"
			style="display: block;">
			<span id="ip"></span>
			<table id="RoleReport"
				class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead>
					<tr style="font-size: 15px;">
						<th width="10" style="text-align: center;">Ser No</th>
						<th width="20">Role Name</th>
						<th width="20">Role Type</th>
						<th width="20">Access Level</th>
						<th width="20">Sub Access Level</th>
						<th width="20">Staff Level</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr style="font-size: 15px;">
							<td width="10" align="center">${num.index+1}</td>
							<td width="20">${item.role}</td>
							<td width="20">${item.role_type}</td>
							<td width="20">${item.access_lvl}</td>
							<td width="20">${item.sub_access_lvl}</td>
							<td width="20">${item.staff_lvl}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div> --%>
<c:url value="role_report" var="role_report" />
	<form:form action="${role_report}" method="post" id="searchForm"
		name="searchForm">
		<input type="hidden" name="role1" id="role1" value="0" />
		<input type="hidden" name="role_type1" id="role_type1" value="0" />
		<input type="hidden" name="access_lvl1" id="access_lvl1" value="0" />
		<input type="hidden" name="formation_se1" id="formation_se1" value="0" />
		<input type="hidden" name="staff_lvl1" id="staff_lvl1" value="0" />
		<input type="hidden" name="staff_se1" id="staff_se1" value="0" />
	</form:form>

	<c:url value="role_mstAction" var="role_mstAction" />
<form:form action="${role_mstAction}" method="post" id="updateForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
$("#update_btn").hide();

   $(document).ready(function () {
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#RoleReport tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
	});
// 		setTimeLoadForTable();
	 	 $("#sys_div").hide();
	 	 
	 	
			document.querySelectorAll('.updateOnclick').forEach((items, index) => {
				items.addEventListener('click', event => {
					
					var val=parseInt(index)+1;
					
					var hid = document.getElementById('updateID'+val).value;
					var hrole = document.getElementById('aprole'+val).value;
					var hrole_type = document.getElementById('aproletype'+val).value;
					var haccess_lvl = document.getElementById('apaccesslvl'+val).value;
					var hsubaccesslvl= document.getElementById('apsubaclvl'+val).value;
					var hstaff_lvl= document.getElementById('apstflvl'+val).value;
					var systemid= document.getElementById('systemid'+val).value;
					
					if (confirm('Are You Sure You Want to Edit Detail ?')) {
						Update(hid,hrole,hrole_type,haccess_lvl,hsubaccesslvl,hstaff_lvl,systemid);
					} else {
						return false;
					}
				})
			});
   });
	
   document.addEventListener('DOMContentLoaded', function() {

		
		document.getElementById('btn-save').onclick = function() {
			return isValid();
		}; 
// 		document.getElementById('line_dte_se').onchange = function() {
// 			return value_pass(this.value)
// 		}; 
// 		document.getElementById('staff_se').onchange = function() {
// 			return value_pass1(this.value);
// 		}; 

		document.getElementById('staff_lvl').onchange = function() {
			getSystemlistbyStaff_lvl();
		}; 
		document.getElementById('formation_se').onchange = function() {
			hide_showSystem();
		}; 
		
	});

	
		
// 		setTimeout(setTimeLoadForTable, 1000);

</script>

<script nonce="${cspNonce}" type="text/javascript">


    function access_lev(v)
	{
    	document.getElementById('sub_lvl_text').value="";
		if(v == "Formation"){
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('formation_se').style.display='block';
			document.getElementById('line_dte_se').style.display='none'; 
			document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none';
    		document.getElementById('staff_text').value="";
			
		}
		else if(v == "Line_dte" ){
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('line_dte_se').style.display='block';
			document.getElementById('formation_se').style.display='none';
		}
		else if(v == "Depot" ){
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';
			document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none';
    		document.getElementById('staff_text').value="";
		}
		else if(v == "Unit" ){
			document.getElementById('sub_lev').style.display='none';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';			
			document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none';
    		document.getElementById('staff_text').value="";
		}
	} 
    
    function value_pass(v){
    	document.getElementById('sub_lvl_text').value =v;
    	if(v == "Staff"){
    		document.getElementById('sub_lev1').style.display='block';
    		document.getElementById('staff_se').style.display='block';
    	}
    	else{
    		document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none';
    		document.getElementById('staff_text').value="";
    	}
    }
    function reportrole(){
    	
    	$("#role1").val($("select#role").val());
    	$("#role_type1").val($("select#role_type").val());
    	$("#access_lvl1").val($("select#access_lvl").val());
    	$("#formation_se1").val($("select#formation_se").val());
    	$("#staff_lvl1").val($("select#staff_lvl").val());
    	$("#staff_se1").val($("select#staff_se").val());
    	
    	document.getElementById('searchForm').submit();
    }
    function value_pass1(v){
    	document.getElementById('staff_text').value =v;
    }
	function updateData(id) {
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}
	function Update(hid,hrole,hrole_type,haccess_lvl,hsubaccesslvl,hstaff_lvl,systemid){	
		
		$("#btn-save").hide();
		
		alert(systemid)
		debugger;
		document.getElementById('role').value=hrole;
		$("#role").val(hrole);	
		$('#role').trigger('change');
		$("#role_type").val(hrole_type);	
		$('#role_type').trigger('change');
		$("#access_lvl").val(haccess_lvl);	
		$('#access_lvl').trigger('change');
		$("#formation_se").val(hsubaccesslvl);	
		$('#formation_se').trigger('change');
		$("#staff_lvl").val(hstaff_lvl);	
		$('#staff_lvl').trigger('change');
		$("#system_name").val(systemid);	
		$('#system_name').trigger('change');
		debugger
		document.getElementById('sub_lvl_text').value=hid;
		document.getElementById('update_btn').style.display='inline-block'; 
// 		document.getElementById('save_btn').style.display='none'; 
	}
   function isValid()
   {	return true;
	   if($("input#role").val()==""){
  			alert("Please Enter Role");
  			$("input#role").focus();
  			return false;
  		}
	   
    	if($("select#role_type").val()==" "){
   			alert("Please Select Role Type");
   			$("select#role_type").focus();
   			return false;
   		}  
    	
    	if($("select#role_type").val()=="Other" && $("#role_text").val() == ""){
    		alert("please Enter Role type");
    		return false;
    	}
    	
    	if($("select#access_lvl").val()==""){
   			alert("Please Select Access Level");
   			$("select#role_type").focus();
   			return false;
   		}  
   
    	 if($("select#access_lvl").val() != "Unit" && $("select#access_lvl").val() != "Depot"){
	    	 if($("input#sub_lvl_text").val() == ""){
	    		 alert("Please Select Sub Access Level");   			
	   			return false;
	    	 }
    	}
    	return true;
   	}
   
   
   function getSystemlistbyStaff_lvl() {
		debugger
		var staff_lvl = $("#staff_lvl").val();
		
		$.post(
						"getSystembyStafflvl?" + key + "=" + value,
						{
							staff_lvl : staff_lvl
						},
						function(j) {

							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i].id + '" name="'+j[i].id+'" >'
										+ j[i].system_name + '</option>';
							}
							$("select#system_name").html(options);
						});
	}
   
   function hide_showSystem(){
		var formation_se = $("#formation_se").val();
		if(formation_se == "BOARD"){
			$("#sys_div").show();
		}else{
		
			 $("#sys_div").hide();
		}
	   
   }
   
</script>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/commonJS/addmorefunctionality.js"></script>
<script src="js/common/multicheck.js"></script>
<link rel="stylesheet" href="js/common/multicheck.css">
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<!-- Datepicker start -->
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- Datepicker End -->

<script nonce="${cspNonce}" type="text/javascript">
	var username = "${username}";
</script>

<section class="dashboard-page search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Practitioner Sign Up Details Report</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Practitioner
									Sign Up Details Report</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>

		</div>
		<!-- title-wrapper end -->

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12 col-lg-12">
					<div class="card-style mb-30">
						<div class="custom-field-block">
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Name<strong class="mandatory"></strong></label> <input
											id="name" name="name" class="form-control" autocomplete="off"
											maxlength="25" placeholder="Name"> <input
											type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Date of Birth </label> <input type="text" name="dob"
											id="dob" maxlength="10"
											class="form-control-sm form-control effect-9 "
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Aadhaar Card No.</label> <input id="aadhar_card"
											name="aadhar_card" class="form-control" autocomplete="off"
											maxlength="14" minlength="12"
											placeholder="Enter Aadhar Card No"> <input
											type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12 col-3">
									<div class="input-style-1">
										<label class=" form-control-label">Email</label> <input
											id="email" name="email" class="form-control"
											autocomplete="off" maxlength="100" placeholder="Enter Email">
									</div>
								</div>

								<div class="col-lg-4 col-md-6 col-sm-12 col-3">
									<div class="input-style-1">
										<label class=" form-control-label">Mobile Number</label> <input
											id="mobile_no" name="mobile_no" class="form-control"
											autocomplete="off" maxlength="10" minlength="0"
											placeholder="Enter Mobile Number">
									</div>
								</div>


								<div class="col-lg-4 col-md-6 col-sm-12 col-3">
									<div class="input-style-1">
										<label>Upload Date</label> <input type="text"
											name="upload_date" id="upload_date" maxlength="10"
											class="form-control-sm form-control effect-9 "
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">
									</div>
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">Gender </label>
										<div class="select-position">
											<select name="gender" id="gender"
												class=" form-control form-control-lg">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getgenderList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12 col-3">
									<div class="input-style-2">
										<label>Internship Completion Date</label> <input type="text"
											name="internship_completion_date"
											id="internship_completion_date" maxlength="10"
											class="form-control-sm form-control effect-9 "
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Registration State</label>
										<div class="select-position">
											<select class="form-control form-control-lg"
												name="reg_state" id="reg_state"
												class="form-control customselect">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${MedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12 col-3">
									<div class="input-style-1">
										<label class=" form-control-label">State Registered
											Number</label> <input id="state_reg_num" name="state_reg_num"
											class="form-control" autocomplete="off" maxlength="10"
											minlength="0" placeholder="Enter State Registered Number">
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label> Status <strong class="mandatory"> </strong>
										</label> <input type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="institute_status" id="institute_status"
												class="form-control customselect" onchange="statusChange()">
												<option value="0">Pending</option>
												<option value="1">Approved</option>
												<option value="2">Reverted</option>
											</select>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="btn-bottom">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<ul class="buttons-group mainbtn">
										<li><a
											class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch btn-search"
											id="btn-reload" value="Search"><i
												class="lni lni-search-alt"></i>Search</a></li>
										<li><a href="Pract_Signup_Details_ReportUrl"
											class="main-btn dark-btn btn-hover btnreset btn-clear"
											value="Reset">Reset</a></li>
										<li id="apprvbt"><input type="button"
											class="main-btn success-btn btn-hover btnappr"
											value="Approve"></li>
										<li id="rejbt"><input type="button"
											class="main-btn danger-btn btn-hover btnreject"
											value="Reject" name="rejbt"></li>
											<li><a class="main-btn success-btn btn-hover btn-iconic-icon btnexcel" id="btnExport">
											<i class="bi bi-file-earmark-excel" value="PDF" title="Export to PDF"></i> Excel </a></li>
									</ul>
								</div>
							</div>
						</div>


					</div>

				</div>
			</div>
			
<!-- 			--18-04-2023 -->
			 <div class="modal fade custom-modal bd-example-modal-lg" tabindex="-1"
			role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true"
			id="modelWindow">
			<div class="modal-dialog modal-xl modal-dialog-scrollable">
				<div class="modal-content">
				
				
					<div class="modal-header">
						<h3 class="modal-title" id="reject_remarks">Enter Remarks</h3>

										<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>


					<div class="modal-body custom-modal-table">
						<div class="custom-modal-inner">
							<div class="table-wrapper table-responsive custom-table">
<!-- 									<table class="table "> -->
<!-- 										<thead> -->
<!-- 											<tr> -->
										
<!-- 											<th>Sr No.</th> -->
<!-- 											<th>Name</th> -->
<!-- 											<th>Remarks</th> -->
<!-- 										</tr> -->
<!-- 									</thead> -->
<!-- 								</table> -->
								<div id="dynamicDataTable"></div>
							</div>
						</div>

					</div>
					
					
					<div class="modal-footer">
						<ul class="buttons-group">
						<li><a type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal">Close</a></li>
							<li><button type="button"
								class="main-btn success-btn  btn-hover"  data-bs-dismiss="modal"
									data-dismiss="modal" aria-label="Close" id="ok">Submit</button></li>
						</ul>
					</div>

				</div>
			</div>
		</div>
		
			<!-- end card -->


			<!-- Select all start -->
			<div class="custom-select-all">
				<div class="row">
					<div class="col-12 col-sm-12 col-md-12 col-lg-12">
						<div class="custom-card-sm card-style mb-30 selectsection"
							id="checkheaddiv">
							<input type="hidden" id="CheckVal" name="CheckVal"> <input
								class="form-check-input" type=checkbox id='nSelAll' name='tregn'>Select
							all [<span id="tregn">0</span><span id='nTotRow1'>/</span><span
								id="tregnn"> </span>]
						</div>
					</div>
				</div>
			</div>
			<!-- Select all end -->

			<section class="single-detail-block">
				<div class="row">
					<div class="col-12 col-sm-12 col-md-12 col-lg-12">
						<div class="card-style mb-30">

							<div class="table-wrapper table-responsive custom-datatable-p">
								<table id="Search_State_Prac" class="table">
									<thead>
										<tr>

											<th ><h6>Sr No.</h6></th>
											<th id="apprvchk"><h6>Select</h6></th>
											<th><h6>Name</h6></th>
											<th><h6>Date of birth</h6></th>
											<th><h6>Aadhaar card no.</h6></th>
											<th><h6>Email</h6></th>
											<th><h6>Mobile number</h6></th>
											<th><h6>Upload date</h6></th>
											<th><h6>Gender</h6></th>
											<th><h6>Internship completion date</h6></th>
											<th><h6>Registered state</h6></th>
											<th><h6>State registered number</h6></th>
											<th id="remarks_hide"><h6>Revert Remarks</h6></th>

										</tr>
<%-- 										<c:forEach var="item" items="${list}" varStatus="num"> --%>
<!-- 											<tr> -->
<%-- 												<td>${num.index+1}</td> --%>
<%-- 												<td>${item.chekboxaction}</td> --%>
<%-- 												<td>${item.name}</td> --%>
<%-- 												<td>${item.dob}</td> --%>
<%-- 												<td>${item.aadhar_card}</td> --%>
<%-- 												<td>${item.email}</td> --%>
<%-- 												<td>${item.mobile_no}</td> --%>
<%-- 												<td>${item.upload_date}</td> --%>
<%-- 												<td>${item.gender}</td> --%>
<%-- 												<td>${item.internship_completion_date}</td> --%>
<%-- 												<td>${item.reg_state}</td> --%>
<%-- 												<td>${item.state_reg_num}</td> --%>
<%-- 												<td>${item.reject_remarks}</td> --%>
<!-- 											</tr> -->
<%-- 										</c:forEach> --%>
										<!-- end table row-->
									</thead>
									<tbody>

									</tbody>
								</table>
								<!-- end table -->
							</div>
						</div>
						<!-- end card -->

					</div>
					<!-- end row -->
				</div>
			</section>
		</div>
		</div>
</section>
<c:url value="Pract_Signup_Details_Report_Excel" var="searchUrl1" />
	<form:form action="${searchUrl1}" method="post" id="search3"
		name="search3" modelAttribute="comd1">
		
		<input type="hidden" name="Search2" id="Search2" value="0" />
		<input type="hidden" name="name2" id="name2" value="0" />
		<input type="hidden" name="dob2" id="dob2" value="0" />
		<input type="hidden" name="aadhar_card2" id="aadhar_card2" value="0" />
		<input type="hidden" name="email2" id="email2" value="0" />
		<input type="hidden" name="mobile_no2" id="mobile_no2" value="0" />
		<input type="hidden" name="upload_date2" id="upload_date2" value="0" />
		<input type="hidden" name="gender2" id="gender2" value="0" />
		<input type="hidden" name="internship_completion_date2" id="internship_completion_date2" value="0" />
		<input type="hidden" name="reg_state2" id="reg_state2" value="0" />
		<input type="hidden" name="state_reg_num2" id="state_reg_num2" value="0" />
		<input type="hidden" name="institute_status2" id="institute_status2" value="0" />
		
	</form:form>
<script nonce="${cspNonce}" type="text/javascript">

function setTimeLoadForTable(){
//	debugger;
	document.getElementById('name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this,event);
	};

$('.nrCheckBox').on('change', function() {
	checkCKBT();
});
// $('.nrCheckBox').on('change', function() {
// 	$(".nrCheckBox").each(function(i) {
// 		   if (this.checked) {
// 		       alert("Checkbox at index " + i + " is checked.");
// 		   }
// 		});
// });


	document.getElementById('dob').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('dob').onfocus = function() {
		return this.style.color='#000000';
	};
	document.getElementById('dob').onblur = function() {
		return clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('dob').onkeyup = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('dob').onchange = function() {
		return clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);calculate_age('dob');
	};
	

	document.getElementById('apprvbt').onclick = function() {
		return setApproveStatus();
	};
	document.getElementById('ok').onclick = function() {
		return setRejectStatus();
	};
	document.getElementById('rejbt').onclick = function() {debugger;
		
		return setRejectStatus();
	};
	document.getElementById('institute_status').onclick = function() {
		return statusChange();
	};
document.getElementById('rejbt').onclick = function() {
		return setRejectStatus();
	};
	document.getElementById('btnExport').onclick = function() {
		Pract_Signup_Details_Report_Excel();
	};
/* 	document.getElementById('rejbt').onclick = function() { */
/* 		//alert("1234"); */
/* 		   $('#modelWindow').modal('show'); */
		   
/* 		  findselected(); */
/* 			var a = $("#CheckVal").val(); */
		 
/* 			if(a == ""){ */
/* 				alert("Please Select the Data for Revert");  */
/* 			}else{ */
   
/* 				 dynamicTable(a); */

/* 			} */
		   
/* // 		 return setRejectStatus(); */
/* 	}; */
 
 
	document.getElementById('rejbt').onclick = function() {
		//alert("1234");
		   $('#modelWindow').modal('show');
		   
		  findselected();
			var a = $("#CheckVal").val();
		 
			if(a == ""){
				alert("Please Select the Data for Revert Back"); 
			}else{

				 dynamicTable(a);

			}
		   
//		 return setRejectStatus();
	};
	document.getElementById('nSelAll').onclick = function() {
		setselectall();
	};
	
	document.querySelectorAll('.editstate').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('CounId'+val).value;
			var gname = document.getElementById('Counnrh_en_no'+val).value;
			var gstatus = document.getElementById('Counstatus'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				EditData(gdid,gname,gstatus);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.statedlt').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('DCounId'+val).value;
			if (confirm('Are You Sure You Want to Delete Detail ?')) {
				deleteData(gdid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.dgpop').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('degreeId'+val).value;
			if (confirm('Are You Sure You Want to Show Detail ?')) {
				Pop_Up_Degree(gdid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.popview').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('pop_ioch'+val).value;
			if (confirm('Are You Sure You Want to Show Detail ?')) {
				Pop_Up_IOCH(gdid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.popview').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('pop_ioch'+val).value;
			if (confirm('Are You Sure You Want to Show Detail ?')) {
				Pop_Up_IOCH(gdid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.popdegreep').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('popdegreepId'+val).value;
			if (confirm('Are You Sure You Want to View Detail?')) {
				Pop_Up_DegreeP(gdid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.degreepdf').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('degreepdfId'+val).value;
			if (confirm('Are You Sure You Want to download File ?')) {
				getPDF(gdid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.imgcsp').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('imghid'+val).value;
//				if (confirm('Are You Sure You Want to View Detail?')) {
				imageView(gdid);
//				} else {
//					return false;
//				}
		})
	});
	var institute_status = $("#institute_status").val();
	if(institute_status == "1" || institute_status == "2"){
		 removecol();	
	}
}

function getPDF(id)
{  
	$("#id3").val(id); 
	document.getElementById('typeReport2').value='pdfL';
	document.getElementById('search1').submit();
}

function Pop_Up_DegreeP(id)
{  
	$("#id7").val(id); 
	document.getElementById('typeReport7').value='pdfL';
	document.getElementById('search2').submit();
}

function setselectall(){
 
	if ($("#nSelAll").prop("checked")) {
		$(".nrCheckBox").prop('checked', true);
	} else {
		$(".nrCheckBox").prop('checked', false);
	}
	
	var l = $('[name="cbox"]:checked').length;
	 $("#tregn").val(l);
	document.getElementById('tregn').innerHTML = l;
	
	checkCKBT();
}
	
	$(document).ready(function() {
		datepicketDate('dob');
		datepicketDate('upload_date');
		datepicketDate('internship_completion_date');
		
		//Checkboxhideshow
		$("#apprvbt").hide();
		$("#rejbt").hide();
		  
		var st_id = ${state_id};
		 
		 if( st_id != null && st_id != "0" && st_id != ""){
			 $("#reg_state").val(st_id); 
			  $("#reg_state").attr("disabled","disabled");
		 }
		
		mockjax1('Search_State_Prac');
		table = dataTable('Search_State_Prac');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
			

		});
		$.ajaxSetup({
			async : false
		});
	});
 
	function statusChange(){
		 var institute_status = $("#institute_status").val();
		 if(institute_status=='0'){
			 $("#rejbt").show();
			 $("#apprvbt").show();
			// $("#checkheaddiv").show();
			 checkCKBT();
		 }
		 else if(institute_status=='1' || institute_status=='2'){
			 $("#rejbt").hide();
			 $("#apprvbt").hide();
			$("#checkheaddiv").hide();
			// checkCKBT();
		 }
	}
function removecol() {	

   var tble = document.getElementById('Search_State_Prac'); 
   var row = tble.rows;  
   var i = 1; 
   for (var x = 0; x < row.length; x++) {
       row[x].deleteCell(i);
   }
}
	
function addcol() {

   var tble = document.getElementById('Search_State_Prac');
	
   var row = tble.rows;  
   var i = 1; 
   for (var x = 0; x < row.length; x++) {
       row[x].insertCell(i);
   }
}

function data(Search_State_Prac) {

	jsondata = [];
	var table = $('#' + Search_State_Prac).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	var name = $("#name").val();
	var dob = $("#dob").val();
	var aadhar_card = $("#aadhar_card").val();
	var email = $("#email").val();
	var mobile_no = $("#mobile_no").val();
	var upload_date = $("#upload_date").val();
	var gender = $("#gender").val();
	var internship_completion_date = $("#internship_completion_date").val();
	var reg_state = $("#reg_state").val();
	var state_reg_num = $("#state_reg_num").val();
	var institute_status = $("#institute_status").val();

	$.post("getFilter_Pract_Signup_Details_Report_data?" + key + "=" + value, {
		
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		name : name,
		dob : dob,
		aadhar_card : aadhar_card,
		email : email,
		mobile_no : mobile_no,
		upload_date : upload_date,
		gender : gender,
		internship_completion_date : internship_completion_date,
		reg_state : reg_state,
		state_reg_num : state_reg_num,
		institute_status : institute_status

	}, function(j) {
		
		$("#tregnn").text(" "+j.length);	
		$("#tregnn").text(" "+j.length);	
		
		for (var i = 0; i < j.length; i++) {
			 var institute_status = $("#institute_status").val();
			 if(institute_status=='0'){
				 $("#apprvchk").show();
				 $("#checkheaddiv").show();
				 $("#remarks_hide").hide();
				 
				 
			jsondata.push([ j[i].sr_no,j[i].chekboxaction, j[i].name, j[i].dob,
				j[i].aadhar_card, j[i].email, j[i].mobile_no,j[i].upload_date,
				j[i].gender_name, j[i].internship_completion_date,
				j[i].state_name, j[i].state_reg_num,j[i].reject_remarks]);
		}
			 else if(institute_status=='1'){
				 $("#apprvchk").hide();
				 $("#checkheaddiv").hide();
				 $("#remarks_hide").hide();
				 jsondata.push([ j[i].sr_no,j[i].chekboxaction, j[i].name, j[i].dob,
						j[i].aadhar_card, j[i].email, j[i].mobile_no,j[i].upload_date,
						j[i].gender_name, j[i].internship_completion_date,
						j[i].state_name, j[i].state_reg_num,j[i].reject_remarks]); 
				
					 }
			 else if(institute_status=='2'){
				 $("#apprvchk").hide();
				 $("#checkheaddiv").hide();
				 $("#remarks_hide").show();
				 jsondata.push([ j[i].sr_no,j[i].chekboxaction, j[i].name, j[i].dob,
						j[i].aadhar_card, j[i].email, j[i].mobile_no,j[i].upload_date,
						j[i].gender_name, j[i].internship_completion_date,
						j[i].state_name, j[i].state_reg_num,j[i].reject_remarks]); 
				
					 }
			 
				 }
	});
	$.post("getPract_Signup_Details_ReportCount?" + key + "=" + value, {
		Search : Search,
		name : name,
		dob : dob,
		aadhar_card : aadhar_card,
		email : email,
		mobile_no : mobile_no,
		upload_date : upload_date,
		gender : gender,
		internship_completion_date : internship_completion_date,
		reg_state : reg_state,
		state_reg_num : state_reg_num,
		institute_status : institute_status

	}, function(j) {

		FilteredRecords = j;

	});
	setTimeout(setTimeLoadForTable, 1000);
}
	

function EditData(id) {
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

function deleteData(id) {
	$("#id2").val(id);
	document.getElementById('deleteForm').submit();
}
	
	function setApproveStatus(){debugger;
		findselected();
		 var a = $("#CheckVal").val();
		 var id = $("#CheckVal2").val();
// 		 $("#id").val(hid_id);
		if(a == ""){
			alert("Please Select the Data for Approval"); 
		}
// 		if(upto==""){ 
// 			alert("Please Select the Valid Upto Date for Approval"); 
// 		}
		else{
				$.post("Approve_Pract_SignUp_Data?"+key+"="+value, {a:a,id:id}).done(function(j) {debugger;
			
					alert("Data approved successfully.");

				location.reload();
			}); 
		}
	}


/* function setRejectStatus(){ */
/* 		findselected(); */
		
/* 		var a = $("#CheckVal").val(); */
/* 		var f = a.split(":"); */
/* 		for (var i = 0; i < f.length; i++) { */
			
/* 				$.post("Reject_Pract_SignUp_Data?"+key+"="+value, {a:a,status:"2"}).done(function(j) { */
/* 					alert("Revert Back Successfully"); */
/*  				location.reload(); */
/* 			});  */
/* 		} */
/* 	} */

function findselected(){debugger;

 		var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
 		return $(this).attr('id');
  		}).get();
		
		var b=nrSel.join(':');
		$("#CheckVal").val(b);
		$('#tregn').text(nrSel.length);
		
		var nrSel2=$('.nrCheckBox:checkbox:checked').map(function() {
		//var upto=$("#valid_upto"+$(this).attr('id')).val();
			 
// 			 var y = upto.substring(0,4);
// 			 var m = upto.substring(5,7);
// 			 var d = upto.substring(8,10);
// 			 var upto2 = d+"/"+m+"/"+y;
		//	return upto2;
		}).get();
		
		var c=nrSel2.join(':');
		$("#CheckVal2").val(c);
		
		var nrSel3=$('.nrCheckBox:checkbox:checked').map(function() {
			var state = $("#nrCHid"+ $(this).attr('id')).val();
			return state;
		}).get();
			
		var z=nrSel3.join(':');
		$("#CheckVal3").val(z);
		
		var b=nrSel.join(':');
		
		$("#CheckVal").val(b);
		$("#CheckVal2").val(id);
		
		$('#tregn').text(nrSel.length);
	}
	
// 	function setRejectStatus(){
		 
// 		findselected();
		
// 		var a = $("#CheckVal").val();

// 		if(a == ""){
// 			alert("Please Select the Data for Reject"); 
// 		}else{

// 				$.post("Approve_FromState_Data?"+key+"="+value, {a:a,status:"2"}).done(function(j) {
// 					alert("Rejected Successfully");
//  				location.reload();
// 			}); 
// 		}	
// 	}
		
		// Get the modal
		function imageView(obj){

		var modal = document.getElementById("myModal");
		var span = document.getElementsByClassName("close")[0];

		// Get the image and insert it inside the modal - use its "alt" text as a caption
		var img = document.getElementById("myImg"+obj);

		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");

		img.onclick = function(){
		  modal.style.display = "block";
		  modalImg.src = this.src;
		  //captionText.innerHTML = this.alt;
		}
		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() { 
		  modal.style.display = "none";
		}
	}
	</script>

<script nonce="${cspNonce}" type="text/javascript">

function getExcel() {	

	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
} 

function getDistrictper() {

	var selval = $("#per_state").val();
	$("select#per_district").empty();

	$
			.post(
					"getDistrictOnstate?" + key + "=" + value,
					{
						selval : selval
					},
					function(j) {
					
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
									+ j[i].district_name + '</option>';
						}
						$("select#per_district").html(options);
					});
}

function getDegreeName(obj,R){
	var DegreeName = $("#"+obj.id).val();
	$.post('getDegreeName?' + key + "=" + value,{DegreeName:DegreeName},function(k) {
		var options = '';
		for (var i = 0; i < k.length; i++) {
			options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'+ k[i].degree_name + '</option>';
		}
		$("#DegreeName"+R).html(options);
//			for (var i = 0; i < data.length - 1; i++) {
//				datap = data[i].split(":");
//				options += '<option value="'+datap[i].id+'" name="' + datap[]+ '" >'+ datap[0] + '</option>';
//			}
//			$("#rank").html(options);
//			var q = '${list.rank}';
//			if(q != ""){
//				$("#rank").val(q);
//			}
	});
}

function checkCKBT(){
	 
	// 	--urmik 10-03-2023 for single count
	var lchk = $('input[name="cbox"]:checked').length;
	 $("#tregn").val(lchk);
	document.getElementById('tregn').innerHTML = lchk;
// 	--urmik 10-03-2023 for single count End
	if(lchk>0 ){
		$("#rejbt").show();
		 $("#apprvbt").show();
	}
	else{
		$("#rejbt").hide();
		 $("#apprvbt").hide();
	}
}

function Pop_Up_Degree(a) {
	var institute_name_hid = $("select#institute_name").val(); 
 
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	$("input#popid").val(a); 
	$("input#institute_name_hid").val(institute_name_hid); 
	
	
	document.getElementById('degreepopup_Form').submit();
}

function Pop_Up_IOCH(a) {

	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	$("input#popidIOCH").val(a);
	document.getElementById('IOCHpopup_Form').submit();
}

function valid_upto(){ 
	$("#id2").val(id);
	document.getElementById('validupto_Form').submit();
	
}
 
function dynamicTable(a){
	
	
	// debugger;
    
// 	var R = a.split(":");
// 	//var dd = R.split(",");
// 	//alert("a1==="+a1)
	
// // var d = 	$("#valid_upto127").val();
// // 	alert(d)
// 	$("div#dynamicDataTable").html("");
	
// 	for(var i = 1 ; i <= R.length ;i ++ ){
		
// 		//$("#dynamicDataTable").append("<div id='dynamicDataTable"+i+"'></div>");
	
// 		var  seq =   i;
	
//  	//alert("ijjj=="+a);
	
		
// 		$("#dynamicDataTable").append("<div id='dynamicDataTable"+seq+"'></div>");
// // 		var r1 = "'"+R+"_"+"'";
// 		var R = a.split(":");
// 		//alert("i-----"+seq)
// 		var idx = R[i-1]
// 		var nam = "";

// --18-04-2023
var R = a.split(":");
	//var dd = R.split(",");
	//alert("a1==="+a1)
	
// var d = 	$("#valid_upto127").val();
// 	alert(d)
	$("div#dynamicDataTable").html("");
	
	for(var i = 1 ; i <= R.length ;i ++ ){
		
		//$("#dynamicDataTable").append("<div id='dynamicDataTable"+i+"'></div>");
	
		var  seq =   i;
	
 	//alert("ijjj=="+a);
	
		
		$("#dynamicDataTable").append("<div id='dynamicDataTable"+seq+"'></div>");
// 		var r1 = "'"+R+"_"+"'";
		var R = a.split(":");
		//alert("i-----"+seq)
		var idx = R[i-1]
		var nam = "";
			
	$.post("get_Parctname_signup_by_Reject_id?" + key + "=" + value, {
		id:idx
		}, function(p) {
				nam = p;
		});
			
	 
// // 		--18-04-2023
// 	$("div#dynamicDataTable"+seq).append(
// 			'<table class="table model-table" id="addAttDoc'+seq+'">'
// //				+'<thead><tr><th>Ser No</th>'
// //				+'<th>Name</th>'
// //				+'<th>Remarks</th>'
// //				+'</tr>'
// //				+'</thead>'
// 			+'<tbody id="att_TbbodyattDoc'+seq+'">'
// 			+'	<tr id="tr_id_attDoc'+seq+'">'
// 			+'		<td class="min-width">'
// 			+'			<div class="lead">'
// 				+'				<div class="lead-text">'
// 			+'					<p>'+i+'</p>'
// 			+'				</div>'
// 			+'			</div>'
// 				+'		</td>'
// 						+'	<td class="min-width">'
// 							+'	<div class="input-style-2">'
// 							+'<label id="name'+R[i-1]+'" name="name'+R[i-1]+'"  value="'+nam+'">'+nam+'</label>'
// 					//	+'	<input type="text" id="name'+R[i-1]+'" name="name'+R[i-1]+'"  value="'+nam+'"  class="form-control"> '
// 						+'	<input type="text" hidden="hidden" id="client'+seq+'" name="client'+seq+'"  value="'+R[(i - 1)]+'"> '
// 							+'</div> '
// 						+'</td>'
// 						+'	<td class="min-width">'
// 							+'	<div class="input-style-2">'
// 						+'	<input type="text" id="remarks'+R[i-1]+'" name="remarks'+R[i-1]+'" placeholder= "Enter Your Remarks"  class="form-control"> '
// 							+'</div> '
// 						+'</td>'
// 						 +'</tr> </tbody> </table>' 
// 		);
		
		
// 		--18-04-2023
	$("div#dynamicDataTable"+seq).append(
			'<table class="table model-table" id="addAttDoc'+seq+'">'
			+'<thead><tr><th>Ser No</th>'
			+'<th>Name</th>'
			+'<th>Remarks</th>'
			+'</tr>'
			+'</thead>'
			+'<tbody id="att_TbbodyattDoc'+seq+'">'
			+'	<tr id="tr_id_attDoc'+seq+'">'
			+'		<td class="min-width">'
			+'			<div class="lead">'
				+'				<div class="lead-text">'
			+'					<p>'+i+'</p>'
			+'				</div>'
			+'			</div>'
				+'		</td>'
						+'	<td class="min-width">'
							+'	<div class="input-style-1">'
							+'<p id="name'+R[i-1]+'" name="name'+R[i-1]+'"  value="'+nam+'">'+nam+'</p>'
					//	+'	<input type="text" id="name'+R[i-1]+'" name="name'+R[i-1]+'"  value="'+nam+'"  class="form-control"> '
						+'	<input type="text" hidden="hidden" id="client'+seq+'" name="client'+seq+'"  value="'+R[(i - 1)]+'"> '
							+'</div> '
						+'</td>'
						+'	<td class="min-width">'
							+'	<div class="input-style-1">'
						+'	<input type="text" id="remarks'+R[i-1]+'" name="remarks'+R[i-1]+'" placeholder= "Enter Your Remarks"  class="form-control"> '
							+'</div> '
						+'</td>'
						 +'</tr> </tbody> </table>' 
		);
		
// 		$("div#dynamicDataTable"+seq).append(
// 				'<table class="table model-table" id="addAttDoc'+seq+'">'
// // 				+'<thead><tr><th>Ser No</th>'
// // 				+'<th>Name</th>'
// // 				+'<th>Remarks</th>'
// // 				+'</tr>'
// // 				+'</thead>'
// 				+'<tbody id="att_TbbodyattDoc'+seq+'">'
// 				+'	<tr id="tr_id_attDoc'+seq+'">'
// 				+'		<td class="min-width">'
// 				+'			<div class="lead">'
//  				+'				<div class="lead-text">'
// 				+'					<p>'+i+'</p>'
// 				+'				</div>'
// 				+'			</div>'
// 					+'		</td>'
// 							+'	<td class="min-width">'
//  							+'	<div class="input-style-2">'
//  							+'<label id="name'+R[i-1]+'" name="name'+R[i-1]+'"  value="'+nam+'">'+nam+'</label>'
// 						//	+'	<input type="text" id="name'+R[i-1]+'" name="name'+R[i-1]+'"  value="'+nam+'"  class="form-control"> '
// 							+'	<input type="text" hidden="hidden" id="client'+seq+'" name="client'+seq+'"  value="'+R[(i - 1)]+'"> '
//  							+'</div> '
// 							+'</td>'
// 							+'	<td class="min-width">'
//  							+'	<div class="input-style-2">'
// 							+'	<input type="text" id="remarks'+R[i-1]+'" name="remarks'+R[i-1]+'" placeholder= "Enter Your Remarks"  class="form-control"> '
//  							+'</div> '
// 							+'</td>'
// 							 +'</tr> </tbody> </table>' 
// 			);
		
		
//   attachmentt(R,index);
	

 	 
		
	}
	 
	
//alert(R+"--"+index);
// 	var listItem = document.createElement("a");
// 	 var listItemCheckbox = document.createElement("a");
	  
// 	listElement.appendChild(listItem);
// 	 listItem.appendChild(listItemCheckbox);
	
	}
// function setRejectStatus(){
// 	findselected();
// 	 var a = $("#CheckVal").val();
// /* 	 var email_id = $("#CheckVal5").val(); */
// /* 	 var notified = $("#CheckVal6").val(); */
// // 	 var upto = $("#CheckVal2").val();
// // 	 var state = $("#CheckVal3").val();

// 	 console.log(a);
	
// 	if(a == ""){
// 		alert("Please Select Data For Revert"); 
// 	}
 
// 	else{
// 			$.post("Reject_Pract_SignUp_Data?"+key+"="+value, {a:a}).done(function(j) {
// 				alert("Data Reverted Successfully.");
// 			location.reload();
// 		}); 
// 	}
// }


// --18-04-2023
function setRejectStatus(){
	//	debugger;
		findselected();
		var a = $("#CheckVal").val();
		var tempSt='';
// 		alert(tempSt);
		var f = a.split(":");
// 		 var upto2 = $("#CheckVal2").val();
		for (var i = 0; i < f.length; i++) {
			
		//	alert($("#remarks"+f[i]).val());
// 		--15-03-2023
			if($("#remarks"+f[i]).val().trim() == ""){
				alert("Please Enter the Remarks for Revert Back"); 
				return false;
			}
			 
			if(i==0){
				tempSt+=$("#remarks"+f[i]).val();
			}else{
				tempSt+=","+$("#remarks"+f[i]).val();
			}
// 			if($("#remarks"+f[i] == ""){
// 				alert("Please Enter the Remarks for Revert Back"); 
// 			}
		}
		if(tempSt == ""){
		//	alert("Please Enter the Remarks for Revert Back"); 
		}else{
			//alert("Rejected Successfully");
				$.post("Reject_Fromsignupdetails_Data?"+key+"="+value, {a:a,status:"2",tempSt:tempSt}).done(function(j) {
					alert("Revert Back Successfully");
 				location.reload();
			}); 
		}
	}



function Pract_Signup_Details_Report_Excel() {	


	$("#name2").val($("#name").val());
	$("#dob2").val($("#dob").val());
	$("#aadhar_card2").val($("#aadhar_card").val());
	$("#email2").val($("#email").val());
	$("#mobile_no2").val($("#mobile_no").val());
	$("#upload_date2").val($("#upload_date").val());
	$("#gender2").val($("#gender").val());
	$("#internship_completion_date2").val($("#internship_completion_date").val());
	$("#reg_state2").val($("#reg_state").val());
	$("#state_reg_num2").val($("#state_reg_num").val());	
	$("#institute_status2").val($("#institute_status").val());
	$("#Search2").val($("#Search").val());

document.getElementById('search3').submit();

}

</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- datatable style and js end-->

<section class="dashboard-page">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Late Admission Confirmation</h2>
					</div>
					<!-- end col -->
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Late
									Admission Confirmation</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<!-- title-wrapper end -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12 col-lg-12">
					<form:form name="lateadmission" id="lateadmission"
						action="LateAdmissionAction" method='POST'
						commandName="lateadmissionCMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Student Name</label> <input
												type="text" id="name" name="name"
												placeholder="Enter Student Name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="20" />
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Date of Birth</label> <input
												type="text" name="dob" id="dob" maxlength="10"
												aria-required="true" autocomplete="off" value="DD/MM/YYYY"
												class="form-control-sm form-control effect-9 ">
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>Aadhaar Number</label> <input type="text"
												id="aadhaar_no" name="aadhaar_no" maxlength="12"
												class="form-control" autocomplete="off"
												placeholder="Aadhaar Number">
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3" hidden="hidden">
										<div class="select-style-1">
											<label for="text-input">Status <span
												class="mandatory">*</span></label>
											<div class="select-position" id="div_role">
												<select name="verified_status" id="verified_status">
													<option value=" ">--Select--</option>
													<option value="0">Verified</option>
													<option value="2">Not-Present</option>
													<option selected="selected" value="-1">Not-Verified</option>
												</select>
											</div>
										</div>
										<label id="role_type_lbl"></label>
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Admission Status <span
												class="mandatory">*</span></label>
											<div class="select-position" id="div_role">
												<select name="late_admission_status"
													id="late_admission_status">
													<c:if test="${role!='NCH'}">
														<option value="1">Late Admission</option>
														<option value="6">Forwarded to Commission</option>
														<option value="7">Approved by Commission</option>
														<option value="2">Rejected by Commission</option>
													</c:if>
													<c:if test="${role=='NCH'}">
														<option value="6">Late Admission</option>
													</c:if>
												</select>
											</div>
											<div class="input-style-1 m-0">
												<input type="hidden" id="institude_id" name="institude_id">
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li id="search-reg"><a
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												value="Search"><i class="lni lni-search-alt"></i>Search</a></li>

											<li><a href="Late_Admission_Confirmation_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>

											<li id="apprvbt"><input type="button"
												class="main-btn success-btn btn-hover btnverify" value="Verify"></li>

											<li id="rejbt"><input type="button"
												class="main-btn danger-btn btn-hover" value="Not-Present"
												name="rejbt"></li>

											<li id="editbt"><input type="button"
												class="btn btn-primary btn-sm" value="Enable To Edit"
												name="editbt"></li>

											<li id="forwardtocomm"><input type="button"
												class="main-btn deactive-btn btn-hover"
												value="Forward to Commission" name="forwardtocomm"></li>

											<li id="btn-comm-approve"><input type="button"
												class="main-btn success-btn  btn-hover btnappr" value="Approve"
												name="btn-comm-approve"></li>

											<li id="btn-comm-reject"><input type="button"
												class="main-btn danger-btn btn-hover btnreject" value="Reject"
												name="btn-comm-reject"></li>


										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>

					</form:form>
				</div>
			</div>

			<div class="card-style mb-30 selectsection d-none" id="checkheaddiv">
				<input type="hidden" id="CheckVal" name="CheckVal"> <input
					type="hidden" id="CheckVal2" name="CheckVal2"> <input
					type="hidden" id="CheckVal3" name="CheckVal3"> <input
					type="hidden" id="CheckVal5" name="CheckVal5"> <input
					type="hidden" id="CheckVal6" name="CheckVal6"> <input
					class="form-check-input" type=checkbox id='nSelAll' name='tregn'>Select
				all [<span id="tregn">0</span><span id='nTotRow1'>/</span><span
					id="tregnn">${list.size()}</span>]
			</div>
			
			<section class="single-detail-block">
				<div class="row">
					<div class="col-12 col-sm-12 col-md-12 col-lg-12">
						<div class="card-style mb-30">

							<div class="custom-table-filter">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="custom-table-search" id="divSerachInput">
											<label>Search: <input id="searchInput" type="search"
												class="" placeholder="" aria-controls="search_system"></label>
										</div>
									</div>
								</div>
							</div>

							<div class="table-wrapper table-responsive custom-table"
								id="att_Tb">
								<table class="table" id="ScreenReport">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<c:if
												test="${late_admission_status  != '6'   && role != 'NCH'}">
												<th class="width-sm"><h6>Select for</h6></th>
											</c:if>
											<c:if
												test="${late_admission_status  == '6'   && role == 'NCH'}">
												<th><h6>Select for</h6></th>
											</c:if>

											<th><h6>Student Name</h6></th>
											<th><h6>Date Of Birth</h6></th>
											<th><h6>Email Id</h6></th>
											<th><h6>Aadhaar Number</h6></th>
											<th class='lastCol'><h6>Action</h6></th>
										</tr>



									</thead>
									<tbody>
										<c:if test="${list.size()==0}">
											<tr>
												<td colspan="6">
													<p class="no-data">No Data Available</p>
												</td>
											</tr>
										</c:if>
										<c:if test="${list.size()>0}">
											<c:forEach var="item" items="${list}" varStatus="num">
												<tr>
													<td><p>${num.index+1}</p></td>
													<c:if
														test="${late_admission_status  != '6' && role != 'NCH' }">
														<td class="width-sm">${item.chekboxaction}</td>
													</c:if>
													<c:if
														test="${late_admission_status  == '6' && role == 'NCH' }">
														<td class="width-sm">${item.chekboxaction}</td>
													</c:if>


													<td><p>${item.name}</p></td>
													<td><p>${item.dob}</p></td>
													<td><p>${item.email}</p></td>
													<td><p>${item.aadhar_card}</p></td>
													<td><p>${item.action}</p></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
							</div>

						</div>

					</div>

				</div>
			</section>

		</div>
	</div>
</section>


<div class="modal fade custom-modal bd-example-modal-lg" tabindex="-1"
	role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="modelWindow">
	<div class="modal-dialog modal-xl modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="reject_remarks">Enter Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>

			<div class="modal-body custom-modal-table">
				<div class="custom-modal-inner">
					<div class="table-wrapper table-responsive custom-table">
						<table class="table model-table" id="addAttDoc">
							<thead>
								<tr>
									<th>Sr No.</th>
									<th>Name</th>
									<th>Remarks</th>
								</tr>
							</thead>
						</table>
						<div id="dynamicDataTable"></div>
					</div>
				</div>

			</div>
			
			<div class="modal-footer">
				<ul class="buttons-group">
<!-- 				<li><a type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal">Close</a></li> -->
					
					<li>
						<button type="button"  class="main-btn success-btn  btn-hover"
							data-bs-dismiss="modal" data-dismiss="modal" aria-label="Close"
							id="ok">Submit</button>
					</li>
				</ul>
			</div>

		</div>
	</div>
</div>


<c:url value="Late_Admission_Confirmation_Search"
	var="Late_Admission_Confirmation_Search" />
<form:form action="${Late_Admission_Confirmation_Search}" method="post"
	id="searchForm" name="searchForm" modelAttribute="">
	<input type="hidden" name="name1" id="name1" value="0" />
	<input type="hidden" name="dob1" id="dob1" value="0" />
	<input type="hidden" name="email1" id="email1" value="0" />
	<input type="hidden" name="aadhar_card1" id="aadhar_card1" value="0" />
	<input type="hidden" name="verified_status1" id="verified_status1"
		value="" />
	<input type="hidden" name="late_admission_status1"
		id="late_admission_status1" value="" />
</form:form>

<c:url value="NCH_Std_details_view_Url" var="appUrl" />
<form:form action="${appUrl}" method="GET" id="applicationUrlForm5"
	name="applicationUrlForm5" modelAttribute="ch_eid">
	<input type="hidden" name="ch_eid" id="ch_eid" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
// alert('${role}');

	
	datepicketDate('dob');
	
	
	var role = '${role}';
	
	 var name = '${name}';
	 if ( name != "" && name != null && name != "null") {
		 $("#name").val(name);
		}else {
			 $("#name").val('');
		}
	var dob = '${dob}';
	 if ( dob != "" &&  dob != "DD/MM/YYYY") {
		 $("#dob").val(dob);
		}else {
			 $("#dob").val('DD/MM/YYYY');
		}
	
	 var ac = '${aadhar_card}';
	 if ( ac != "" && ac != null && ac != "null") {
		 $("#aadhaar_no").val(ac);
		}else {
			 $("#aadhaar_no").val('');
		}
	 
	
 var n = '${verified_status}';
 if ( n != "" && n != null && n != "null") {
	 $("#verified_status").val(n);
// 			 if (n!=0) {
// 				 $("#checkheaddiv").show();
// 			}
	}else {
		 $("#verified_status").val('-1');
	}
 
 
//  alert(role);
 
 var las = '${late_admission_status}';
 if ( las != "" && las != null && las != "null") {
	 $("#late_admission_status").val(las);
	 
	 if (parseInt(las) != 6  && role != 'NCH' ) {
// 			$("#checkheaddiv").show();
			
			 if ($("#checkheaddiv").hasClass("d-none")) {
				 $( "#checkheaddiv").removeClass("d-none")
			}
	}
	 
	 if (parseInt(las) == 6  && role == 'NCH' ) {
// 		 $("#checkheaddiv").show();
		 
		 if ($("#checkheaddiv").hasClass("d-none")) {
			 $( "#checkheaddiv").removeClass("d-none")
		}
	}
	 
	 
	}else {
		 $("#late_admission_status").val('');
	}
 
 
 checkCKBT();
 
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#ScreenReport tbody tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
  	});
	
	if ('${list.size()}'== 0) {
// 		 $("#checkheaddiv").hide();

 if (!$( "#checkheaddiv" ).hasClass('d-none')) {
				$( "#checkheaddiv" ).addClass("d-none")
			}
		 
	}
	
});

function serachScreen(){
	$("input#name1").val($("#name").val());	
	$("#dob1").val($("#dob").val());
// 	$("#email1").val($("#email").val());	
	$("#aadhar_card1").val($("#aadhaar_no").val());
	$("#verified_status1").val($("#verified_status").val().trim());
	$("#late_admission_status1").val($("#late_admission_status").val().trim());
	
	document.getElementById('searchForm').submit();
}

 //START CSP
document.addEventListener('DOMContentLoaded', function() {

	//		document.getElementById('save_reg').onclick = function() {
	//			return Validate();
	//		};	

	document.getElementById('dob').onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('dob').onfocus = function() {
			this.style.color = '#000000';
		};
		document.getElementById('dob').onblur = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate_BackDate(this.value, this);
		};
		document.getElementById('dob').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('dob').onchange = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate_FutureDate(this.value, this);
		};
		
// 		document.getElementById('name').onchange = function() {
// 			checkLength();
// 		};
	document.getElementById('aadhaar_no').onkeypress = function() {
		return isNumberKey0to9(event);
	};


		document.getElementById('name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
	
	document.getElementById('apprvbt').onclick = function() {
		return setApproveStatus();
		 
	};
	document.getElementById('editbt').onclick = function() {
		return setEditStatus();
		 
	};
	document.getElementById('rejbt').onclick = function() {
		return setRejectStatus();
	};
	
	document.getElementById('forwardtocomm').onclick = function() {
		return setforwardtocommission();
	};
	
	document.getElementById('btn-comm-approve').onclick = function() {
		return setApproveByCommisionStatus();
	};
	
	document.getElementById('btn-comm-reject').onclick = function() {
		return setRejectByCommisionStatus();
	};
	
	
	document.getElementById('ok').onclick = function() {
		return setRejectbycomiwithremarksStatus();
	};
	
	
	document.getElementById('search-reg').onclick = function() {
			return serachScreen();
	};

	document.getElementById('nSelAll').onclick = function() {
		setselectall();
	};
	
	$('.nrCheckBox').on('change', function() {
		checkCKBT();
		});
	
	document.querySelectorAll('.veiwOnclick').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var pid = document.getElementById('viewID'+val).value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				ViewData(pid);
			} else {
				return false;
			}
		})
	});
});

 function ViewData(pid) {
// 	 alert("p_id------>    "+pid);
			$("#ch_eid").val(pid);
			document.getElementById("applicationUrlForm5").submit();
}
 
function setEditStatus(){
	findselected();
	 var a = $("#CheckVal2").val();
	 var email_id = $("#CheckVal5").val();
	 var notified = $("#CheckVal6").val();
// 	 alert(notified);
// 	 var upto = $("#CheckVal2").val();
// 	 var state = $("#CheckVal3").val();
	
	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}
	else{
			$.post("Enable_to_Edit_Admission_Student_ctrl?"+key+"="+value, {a:a ,email_id :email_id,notified:notified }).done(function(j) {
					alert(j);
			location.reload();
		}); 
	}
}

function setApproveStatus(){
	 findselected();
	 var a = $("#CheckVal").val();
	 if(a == ""){
		alert("Please Select Student's for Approval");
	}else{
			$.post("Approve_From_Admission_Confirmation_Student_Data?"+key+"="+value, 
			{ a:a }).done(function(j) {
				alert(j);
				location.reload();
			}); 
	}
}

function setApproveByCommisionStatus(){
	 findselected();
	 var a = $("#CheckVal").val();
	 if(a == ""){
		alert("Please Select Student's for Approval");
	}else{
			$.post("Approve_Student_Admission_By_Commission_NCH_Ctrl?"+key+"="+value, 
			{ a:a }).done(function(j) {
				alert(j);
				location.reload();
			}); 
	}
}


function setRejectByCommisionStatus(){
	
	
	 $('#modelWindow').modal('show');
	   
	  findselected();
		var a = $("#CheckVal").val();
	 
		if(a == ""){
			alert("Please Select the Data for Revert Back"); 
		}else{

			 dynamicTable(a);

		}
	
	
// 	 findselected();
// 	 var a = $("#CheckVal").val();
// 	 if(a == ""){
// 		alert("Please Select Student's for Reject");
// 	}else{
// 			$.post("Reject_Approve_Student_Admission_By_Commission_NCH_Ctrl?"+key+"="+value, 
// 			{ a:a }).done(function(j) {
// 				alert(j);
// 				location.reload();
// 			}); 
// 	}
	 
	 
}



function setRejectbycomiwithremarksStatus(){
	 
	findselected();
	
	var a = $("#CheckVal").val();
	
	var tempSt='';
	var f = a.split(":");

	for (var i = 0; i < f.length; i++) {
		
		//	alert($("#remarks"+f[i]).val());
			
			if($("#remarks"+f[i]).val() == ""){
				alert("Please Enter the Remarks for Reject"); 
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
		alert("Please Select Student's for Reject"); 
	}else{
		 
// 			$.post("Reject_regulation_Data?"+key+"="+value, {a:a,status:"2",tempSt:tempSt}).done(function(j) {
// 			//	alert(j);
// 			alert("Revert Back Successfully");
// 				location.reload();
// 			Search();
// 		}); 
			
			$.post("Reject_Approve_Student_Admission_By_Commission_NCH_Ctrl?"+key+"="+value, 
					{ a:a,tempSt:tempSt }).done(function(j) {
						alert(j);
						location.reload();
					}); 
			
			
	}	
}


function dynamicTable(a){
	 
	var R = a.split(":");
	 
	$("div#dynamicDataTable").html("");
	
	for(var i = 1 ; i <= R.length ;i ++ ){
		 
		var  seq =   i;
	 
		$("#dynamicDataTable").append("<div id='dynamicDataTable"+seq+"'></div>");
 		var R = a.split(":");
 		var idx = R[i-1]
		var nam = "";
 		
 		
 		
 		$.ajaxSetup({
 			async : false
 		});
	$.post("get_StudentName_by_commReject_id?" + key + "=" + value, {
		id:idx
		}, function(p) {
			
// 			console.log(p[0][0])
			
				nam = p[0][0];
				console.log(nam);
				
		});
		
	
	console.log(nam);
		
		
		$("div#dynamicDataTable"+seq).append(
							'<table class="table model-table" id="addAttDoc'+seq+'">'
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
 							+'<label id="name'+R[i-1]+'" name="name'+R[i-1]+'"  value="'+nam+'">'+nam+'</label>'
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
		
	}
	 
 
	}
	

function setforwardtocommission(){
	 findselected();
	 var a = $("#CheckVal").val();
	 if(a == ""){
		alert("Please Select Student's for Forward to Commission");
	}else{
			$.post("Forward_Student_To_Commission_NCH_ctrl?"+key+"="+value, 
			{ a:a }).done(function(j) {
				alert(j);
				location.reload();
			}); 
	}
}


function setRejectStatus(){
	findselected();
	 var a = $("#CheckVal").val();
	 var email_id = $("#CheckVal5").val();
	 var notified = $("#CheckVal6").val();
// 	 var upto = $("#CheckVal2").val();
// 	 var state = $("#CheckVal3").val();

	 console.log(a);
	
	if(a == ""){
		alert("Please Select Student's for Pending"); 
	}
 
	else{
			$.post("Reject_From_Admission_Confirmation_Student_Data?"+key+"="+value, {a:a}).done(function(j) {
				alert("Student's Admission is set Pending Successfully.");
			location.reload();
		}); 
	}
}
 
	
function findselected(){

		var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		return $(this).attr('id');
		}).get();
	
	var b=nrSel.join(':');
	$("#CheckVal").val(b);
	$('#tregn').text(nrSel.length);
	
	var c ="";
	for (var x = 0; x < nrSel.length; x++) {
		console.log(nrSel[x]);
		if (x=="0") {
			c += $("#p_id"+nrSel[x]).val();
		}else {
			c +=":"+$("#p_id"+nrSel[x]).val();
		}
	}
	$("#CheckVal2").val(c);
	
	
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


function checkCKBT(){
	
	
	var n = '${late_admission_status}';
	var lchk = $('input[name="cbox"]:checked').length;
	
	$('#tregn').text(lchk);
	
	
	if(lchk>0 ){
		if ('${role}'=='NCH') {
// 			$("#btn-comm-approve").show();
			
			 if ($("#btn-comm-approve").hasClass("d-none")) {
				 $( "#btn-comm-approve").removeClass("d-none")
			}
			 
// 			 $("#btn-comm-reject").show();
			 
			 if ($("#btn-comm-reject").hasClass("d-none")) {
				 $( "#btn-comm-reject").removeClass("d-none")
			}
			 
		
		}	
		
		else {
			
			 if (n == "6") {
// 				 $("#editbt").hide();
				 if (!$( "#editbt" ).hasClass('d-none')) {
						$( "#editbt" ).addClass("d-none")
					}
				 
// 				 $("#rejbt").show();
				  if ($("#rejbt" ).hasClass('d-none')) {
						$( "#rejbt" ).removeClass("d-none")
					}
				 
			}
			
			 
			 if (n == "7") {
// 				 $("#editbt").hide();
				 if (!$( "#editbt" ).hasClass('d-none')) {
						$( "#editbt" ).addClass("d-none")
					}
				 
// 				 $("#forwardtocomm").hide();
				 if (!$( "#forwardtocomm" ).hasClass('d-none')) {
						$( "#forwardtocomm" ).addClass("d-none")
					}
				 
// 				 $("#apprvbt").show();
				 if ($("#apprvbt").hasClass("d-none")) {
					 $( "#apprvbt").removeClass("d-none")
				}
				 
			}
			 else if (n == "2") {
// 				 $("#editbt").show();
				  if ($("#editbt" ).hasClass('d-none')) {
						$( "#editbt" ).removeClass("d-none")
					}
//	 				 $("#rejbt").show();
				  if ($("#rejbt" ).hasClass('d-none')) {
						$( "#rejbt" ).removeClass("d-none")
					}
// 				 $("#forwardtocomm").hide();
				 if (!$( "#forwardtocomm" ).hasClass('d-none')) {
						$( "#forwardtocomm" ).addClass("d-none")
					}
// 				 $("#apprvbt").hide();
				 if (!$( "#apprvbt" ).hasClass('d-none')) {
						$( "#apprvbt" ).addClass("d-none")
					}
			}
			 else {
				 
// 				 $("#editbt").show();
				 if ($("#editbt").hasClass("d-none")) {
					 $( "#editbt").removeClass("d-none")
				}
// 				 $("#forwardtocomm").show();
				 if ($("#forwardtocomm").hasClass("d-none")) {
					 $( "#forwardtocomm").removeClass("d-none")
				}
					//8feb23	
// 				 $("#rejbt").show();
				  if ($("#rejbt" ).hasClass('d-none')) {
						$( "#rejbt" ).removeClass("d-none")
					}
 // 				 $("#rejbt").hide();
// 				 if (!$( "#rejbt" ).hasClass('d-none')) {
// 						$( "#rejbt" ).addClass("d-none")
// 					}
// 				 $("#apprvbt").hide();
				 
				 if (!$( "#apprvbt" ).hasClass('d-none')) {
						$( "#apprvbt" ).addClass("d-none")
					}
			}
	
		}
	}
	else{
// 		$("#rejbt").hide();

if (!$( "#rejbt" ).hasClass('d-none')) {
			$( "#rejbt" ).addClass("d-none")
		}
		
// 		 $("#apprvbt").hide();
		 if (!$( "#apprvbt" ).hasClass('d-none')) {
				$( "#apprvbt" ).addClass("d-none")
			}
		 
// 		 $("#editbt").hide();
		 if (!$( "#editbt" ).hasClass('d-none')) {
				$( "#editbt" ).addClass("d-none")
			}
// 		 $("#forwardtocomm").hide();
		 if (!$( "#forwardtocomm" ).hasClass('d-none')) {
				$( "#forwardtocomm" ).addClass("d-none")
					}
// 		$("#btn-comm-approve").hide();
 if (!$( "#btn-comm-approve" ).hasClass('d-none')) {
				$( "#btn-comm-approve" ).addClass("d-none")
			}
// 		$("#btn-comm-reject").hide();

 if (!$( "#btn-comm-reject" ).hasClass('d-none')) {
				$( "#btn-comm-reject" ).addClass("d-none")
			}
		 
	}
}

		function checkgmail(email1) {
			 document.getElementById("email").innerHTML="";
			if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
				
			}else{
				alert("Please Enter Valid Email Address");
				$("input#email").focus();
				$("input#email").val('');
				return false ;
			}
		}
		

		function isAadhar(e,evt) {
			if(e.value==0 || e.value=="null" || e.value == null){
				e.value="";
				}

			var bool=isNumber(evt);	
			if(bool){
			  var value = e.value;
			  value = value.replace(/\D/g, "").split(/(?:([\d]{4}))/g).filter(s => s.length >0 ).join("-");
			  e.value=value;
			  return true;
			  }
			  else{
			  return false;}
			}
	// end csp
</script>
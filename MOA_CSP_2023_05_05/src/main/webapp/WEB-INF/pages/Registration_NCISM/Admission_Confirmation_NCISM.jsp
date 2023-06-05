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
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Admission Confirmation</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Admission
									Confirmation</li>
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
					<form:form name="course" id="course" action="CourseAction"
						method='POST' commandName="courseCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<!-- <h6 class="mb-25">Admission Confirmation</h6> -->
							<div class="row">

								<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
								<!-- 								<div class="input-style-2"> -->
								<!-- 									<label for="text-input">Ayush Id</label> -->
								<!-- 									<input type="text" id="ayush_id" name="ayush_id" placeholder="Enter Ayush Id" class="autocomplete UpperClassName txt-transupp" -->
								<!-- 										autocomplete="off" maxlength="20" /> -->
								<!-- 								</div> -->
								<!-- 							</div> -->

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
									<div class="input-style-2">
										<label for="text-input">Date of Birth</label> <input
											type="text" name="dob" id="dob" maxlength="10"
											aria-required="true" autocomplete="off" value="DD/MM/YYYY"
											class="form-control-sm form-control effect-9 ">
										<!-- 																onclick="clickclear(this, 'DD/MM/YYYY')" -->
										<!-- 																onfocus="this.style.color='#000000'" -->
										<!-- 																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" -->
										<!-- 																onkeyup="clickclear(this, 'DD/MM/YYYY')" -->
										<!-- 																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" -->
									</div>
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-1">
										<label>Aadhaar Number</label><input type="text"
											id="aadhaar_no" name="aadhaar_no" maxlength="12"
											class="form-control" autocomplete="off"
											placeholder="Aadhaar Number">
									</div>
								</div>


								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="select-style-1">
										<label for="text-input">Status <span class="mandatory">*</span></label>
										<div class="select-position" id="div_role">
											<select name="verified_status" id="verified_status">
												<option value=" ">--Select--</option>
												<option value="0">Verified</option>
												<option value="2">Not-Present</option>
												<option value="-1">Not-Verified</option>
											</select>
										</div>
									</div>
									<label id="role_type_lbl" class="d-none"></label>
									<!-- Hidden  Start -->
								<input type="hidden" id="institude_id" name="institude_id">
								<!-- Hidden  End -->
								</div>
							</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
																			<li><input class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch" type="button" value="Search" id="search-reg" name="search-reg"  /></li>
<!-- 											<li><a class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch" value="Search"><i -->
<!-- 													class="lni lni-search-alt"></i>Search</a></li> -->
											<li><a href="Admission_Confirmation_NCISM_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
											<li><input type="button"
												class="main-btn success-btn  btn-hover d-none"
												value="Verify" id="apprvbt"></li>
											<li><input type="button"
												class="main-btn danger-btn btn-hover d-none" value="Not-Present"
												id="rejbt" name="rejbt"></li>

											<li><input type="button"
												class="btn btn-primary btn-sm d-none" value="Enable To Edit"
												id="editbt" name="editbt"></li>


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
				<!-- Hidden  Start -->
				<input type="hidden" id="CheckVal" name="CheckVal"> <input
					type="hidden" id="CheckVal2" name="CheckVal2"> <input
					type="hidden" id="CheckVal3" name="CheckVal3"> <input
					type="hidden" id="CheckVal5" name="CheckVal5"> <input
					type="hidden" id="CheckVal6" name="CheckVal6">
				<!-- Hidden  End -->
				<input class="form-check-input" type=checkbox id='nSelAll'
					name='tregn'>Select all [<span id="tregn">0</span><span
					id='nTotRow1'>/</span><span id="tregnn">${list.size()} </span>]
			</div>
			<section class="single-detail-block">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-style mb-30">

							<div class="custom-table-filter">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="custom-table-search">
											<label>Search: <input type="search" class=""
												id="searchInput" placeholder=""
												aria-controls="search_system"></label>
										</div>
									</div>
								</div>
							</div>


							<div class="table-wrapper table-responsive  simple-table"
								id="att_Tb">
								<table class="table" id="ScreenReport">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<c:if test="${verified_status  != '0'}">
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
												<td colspan="6">No Data Available</td>
											</tr>
										</c:if>
										<c:if test="${list.size()>0}">
											<c:forEach var="item" items="${list}" varStatus="num">
												<tr>
													<td>${num.index+1}</td>
													<c:if test="${verified_status  != '0'}">
														<td>${item.chekboxaction}</td>
													</c:if>
													<td>${item.name}</td>
													<td>${item.dob}</td>
													<td>${item.email}</td>
													<td>${item.aadhar_card}</td>
													<td>${item.action}</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<!-- 				end table -->
							</div>
						</div>
						<!-- 		end card -->
					</div>
					</div>
					</section>
				</div>
			</div>
			</section>
			<!-- 	end col -->


			<c:url value="getSearch_StatusVise1" var="getSearch_StatusVise1" />
			<form:form action="${getSearch_StatusVise1}" method="post"
				id="searchForm" name="searchForm" modelAttribute="">
				<input type="hidden" name="name1" id="name1" value="0" />
				<input type="hidden" name="dob1" id="dob1" value="0" />
				<input type="hidden" name="email1" id="email1" value="0" />
				<input type="hidden" name="aadhar_card1" id="aadhar_card1" value="0" />
				<input type="hidden" name="verified_status1" id="verified_status1"
					value=" " />
				<!-- <input type="hidden" name="unit_name1" id="unit_name1"  /> -->
			</form:form>

			<c:url value="NCISM_Std_details_view_Url" var="appUrl" />
			<form:form action="${appUrl}" method="GET" id="applicationUrlForm5"
				name="applicationUrlForm5" modelAttribute="ch_eid">
				<input type="hidden" name="ch_eid" id="ch_eid" value="0" />
			</form:form>

			<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
// 	mockjax1('search_Admission_Confirmation');
// 	table = dataTable('search_Admission_Confirmation');
// 	$('#btn-reload').on('click', function() {
// 		table.ajax.reload();
// 		alert(search_Admission_Confirmation);
// 	});

	datepicketDate('dob');
	
	
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
		 if (n!=0) {
// 			 $("#checkheaddiv").show();
			 
			 if ($("#checkheaddiv").hasClass("d-none")) {
				 $( "#checkheaddiv").removeClass("d-none")
			}
		}
		 
		}else {
			 $("#verified_status").val('-1');
		}
	 
	 checkCKBT();
	 
		$("#searchInput").on("keyup", function() {
				var value = $(this).val().toLowerCase();
				$("#ScreenReport tbody tr").filter(function() {
				$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
				});
	  	});

});


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
		
		document.getElementById('aadhaar_no').onkeypress = function() {
			return isNumberKey0to9(event);
		};
		
		document.getElementById('name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
	
	document.getElementById('apprvbt').onclick = function() {
		return setApproveStatus();
		 
	};
	document.getElementById('rejbt').onclick = function() {
		
		return setRejectStatus();
	
	};
	
	document.getElementById('editbt').onclick = function() {
		return setEditStatus();
		 
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
//	 alert("p_id------>    "+pid);
			$("#ch_eid").val(pid);
			document.getElementById("applicationUrlForm5").submit();
			
			
}


function serachScreen(){
	$("#name1").val($("#name").val());	
	$("#dob1").val($("#dob").val());
// 	$("#email1").val($("#email").val());	
	$("#aadhar_card1").val($("#aadhaar_no").val());
	$("#verified_status1").val($("#verified_status").val().trim());	
	document.getElementById('searchForm').submit();
	
}

function setTimeLoadForTable(){

	
}

function setApproveStatus(){
	findselected();
	 var a = $("#CheckVal").val();
	 var email_id = $("#CheckVal5").val();
	 var notified = $("#CheckVal6").val();
// 	 alert(notified);
// 	 var upto = $("#CheckVal2").val();
// 	 var state = $("#CheckVal3").val();
 
	 console.log(a);
	
	if(a == ""){
		alert("Please Select Student's for Approval"); 
	}
 
	else{
			$.post("Approve_From_Admission_Confirmation_Student_Data1?"+key+"="+value, {a:a ,email_id :email_id,notified:notified }).done(function(j) {
			
				alert("Student's Admission is Approved Successfully.");

			location.reload();
		}); 
	}
}

function setRejectStatus(){
	findselected();
	 var a = $("#CheckVal").val();
	 
// 	 var email_id = $("#CheckVal5").val();
// 	 var notified = $("#CheckVal6").val();
// 	 var upto = $("#CheckVal2").val();
// 	 var state = $("#CheckVal3").val();

	 console.log(a);
	
	if(a == ""){
		alert("Please Select Student's for Pending"); 
	}
 
	else{
			$.post("Reject_From_Admission_Confirmation_Student_Data1?"+key+"="+value, {a:a}).done(function(j) {
			
				alert("Student's Admission Data Saved Successfully.");

			location.reload();
		}); 
	}
}
 
 
 
function setEditStatus(){
	findselected();
	 var a = $("#CheckVal2").val();
	 var email_id = $("#CheckVal5").val();
	 var notified = $("#CheckVal6").val();
// 	 alert(notified);
// 	 var upto = $("#CheckVal2").val();
// 	 var state = $("#CheckVal3").val();
 
	 console.log(a);
	
	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}
 
	else{
			$.post("Enable_to_Edit_Admission_Student_NCISM_ctrl?"+key+"="+value, {a:a ,email_id :email_id,notified:notified }).done(function(j) {
			
// 				alert("Student's Admission is Approved for Edit.");
					alert(j);

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
	
	var n = $('#verified_status').val();
	
	var lchk = $('input[name="cbox"]:checked').length;
	
	
	if(lchk>0 ){
// 		$("#rejbt").show();
		
		if ($("#rejbt").hasClass("d-none")) {
			 $( "#rejbt").removeClass("d-none")
		}
		
		
// 		 $("#apprvbt").show();
		 
		 if ($("#apprvbt").hasClass("d-none")) {
			 $( "#apprvbt").removeClass("d-none")
		}
		 
		 
		 if (n == "1") {
// 			 $("#editbt").hide();
			 
			 if (!$( "#editbt" ).hasClass('d-none')) {
					$( "#editbt" ).addClass("d-none")
				}
			 
			 
		}else {
			 $("#editbt").show();
			 
			 if ($("#editbt").hasClass("d-none")) {
				 $( "#editbt").removeClass("d-none")
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
		 
	}
}


function data(search_Admission_Confirmation) {
	jsondata = [];
	var table = $('#' + search_Admission_Confirmation).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	var	name = $("#name").val();
	var dob = $("#dob").val();
	var email = $("#email").val();
	var aadhar_card = $("#aadhaar_no").val();
	
	$.post("getFilterAdmission_Confirmation_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		
		name:name,
		dob:dob,
		email:email,
		aadhar_card : aadhar_card

		
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			
			jsondata.push([ j[i].ser,j[i].chekboxaction, j[i].name,j[i].dob,j[i].email,j[i].aadhar_card]);
			
		}
	});
	$.post("getTotalAdmission_ConfirmationCount?" + key + "=" + value, {
	
		name:name,
		dob:dob,
		email:email,
		aadhar_card : aadhar_card

		
	}, function(j) {
		
		FilteredRecords = j;
		});
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
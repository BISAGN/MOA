<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<!-- <script type="text/javascript" src="js/watermark/common.js"></script> -->

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-ui.js"></script> -->
<script src="js/Calender/datePicketValidation.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<!-- 				CHANGE SPAN LOCATION   -->

						<h2>
							<span id="lbladd"></span> Admission Schedule Date
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Admission
									Schedule Date</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form action="AcademicScheduleAction" method="POST" modelAttribute="AcademicScheduleCMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<!-- <h6 class="mb-25">Admission Schedule Date</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Academic Year<span
												class="mandatory"> *</span></label>
											<div class="input-style-1">
												<input type="month" id="academic_year" name="academic_year"
													class="form-control-sm form-control effect-9 hasDatepicker" />
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">System<span class="mandatory">
													*</span></label>
											<div class="select-position">
												<select name="system_id" id="system_id">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${data}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="username">Start Date<span
												class="mandatory">*</span></label> <input type="text"
												name="start_date" id="start_date" maxlength="10"
												aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">
										</div>
										<div class="input-style-1 mt-3">
											<!-- Hidden Start -->
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
											<!-- Hidden End -->
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="end_date">End Date<span class="mandatory">*</span></label>
											<input type="text" name="end_date" id="end_date"
												maxlength="10" aria-required="true" autocomplete="off"
												placeholder="DD/MM/YYYY">
										</div>
										<div class="input-style-1 mt-3">
											<!-- Hidden Start -->
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
											<!-- Hidden End -->
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="status" id="status" class="form-control">
													<c:forEach var="item" items="${ActiveInActiveList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
												<div class="col-lg-12 col-md-12 col-sm-12">
												<!-- Hidden Start --> 
													<input type="hidden" id="id" name="id" value="0"
														class="form-control" autocomplete="off" />
														<!-- Hidden End --> 
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="input-style-1 mt-3">
									<!-- Hidden Start -->
									<input type="hidden" id="id" name="id" value="0"
										autocomplete="off" />
									<!-- Hidden End -->
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
												class="main-btn info-btn btn-hover btnsave" type="submit"
												value="Save" /></li>
											<li><a href="Admission_Academic_schedule_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>
			</div>
		<section class="single-detail-block">
			<div class="row">
				<div class="col-12">
					<div class="card-style mb-30">
						<div class="table-wrapper table-responsive custom-datatable-p">
							<table class="table" id="search_academic_schedule_details">
								<thead>
									<tr>
										<th><h6>Sr No</h6></th>
										<th><h6>Academic Year</h6></th>
										<th><h6>System</h6></th>
										<th><h6>Start Date</h6></th>
										<th><h6>End Date</h6></th>
										<th><h6>Action</h6></th>
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
</section>


<c:url value="getSearch_Academicschedule" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="academicscheduleForm"
	name="searchForm" modelAttribute="Academic_schedule1">
	<!-- Hidden Start --> 
	<input type="hidden" name="Academic_year1" id="Academic_year1"
		value="0" />
	<input type="hidden" name="Start_date1" id="Start_date1" />
	<input type="hidden" name="End_date1" id="End_date1" value="0" />
	<input type="hidden" name="System_name1" id="System_name1" value="0" />
</form:form>


<c:url value="Edit_Academic_schedule_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6">
</form:form>

<c:url value="delete_admission_date_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
	<!-- Hidden End --> 
</form:form>


<c:url value="Systemreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		
		datepicketDate('start_date');
		$( "#start_date").datepicker( "option", "maxDate", null);
		datepicketDate('end_date');
		$( "#end_date").datepicker( "option", "maxDate", null);
		
		
		
		mockjax1('search_academic_schedule_details');
		table = dataTable('search_academic_schedule_details');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		
		
// 	HideExamType_Serial();
	
	});
	
	function setTimeLoadForTable(){
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		
// 		document.getElementById('academic_details').onkeypress = function() {
//  			 onlyAlphabetsStringSpace(this, event);
//  		};
 		
//  		document.getElementById('academic_details').onclick = function() {
//  			HideExamType_Serial();
// 		};
 		
 		
 		
// 		document.getElementById('start_date').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(this, event);
// 		};
		
// 		document.getElementById('end_date').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(this, event);
// 		};
		
		document.getElementById('start_date').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		
		document.getElementById('start_date').onblur = function() {
				clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('start_date').onkeyup = function() {
			return	clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('start_date').onchange = function() {
			onchangeCount(this.value);
			  clickrecall(this,'DD/MM/YYYY'); 
		};
		
		document.getElementById('end_date').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('end_date').onblur = function() {
				clickrecall(this,'DD/MM/YYYY');
				
		};
		document.getElementById('end_date').onkeyup = function() {
			return	clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('end_date').onchange = function() {
			onchangeCount(this.value);
			  clickrecall(this,'DD/MM/YYYY');
		};
		
		document.querySelectorAll('.ADDAcademicdate1').forEach((items, index) => {
			items.addEventListener('click', event => {
				debugger;
				var val=parseInt(index)+1;
				var hid = document.getElementById('apIdAGE'+val).value;
				var haca = document.getElementById('apacaAGE'+val).value;
				var hsn = document.getElementById('apsnAGE'+val).value;
				var hsd = document.getElementById('apsdAGE'+val).value;
				var hed = document.getElementById('apedAGE'+val).value;
				var hstatus = document.getElementById('apstatusAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,haca,hsn,hsd,hed,hstatus);
				} else {
					return false;
				}
			})
		});
		
	 document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('deleteID'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(hid);
				} else { 	
					return false;
				}
			})
		});  
		
		
	}	

	function data(search_academic_schedule_details) {
		
		jsondata = [];
		var table = $('#' + search_academic_schedule_details).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var academic_year = $("#academic_year").val();
		var start_date = $("#start_date").val();
		var end_date = $("#end_date").val();
		var system_name = $("#system_id").val();
		var status = $("#status").val();
		
		////////////   userid add rajdip 26/12/2022
		
		var userid = '${userId}';
		
		console.log(userid);
		
 		$.post("getFilteracademic_schedule_data?" + key + "=" + value, {
 			startPage : startPage,
 			pageLength : pageLength,
 			Search : Search,
 			orderColunm : orderColunm,
 			orderType : orderType,
 			academic_year : academic_year,
 			system_name : system_name,
 			start_date : start_date,
 			end_date : end_date,
 			status : status,
 			userid:userid
 			
 		},
 		function(j) {
 			for (var i = 0; i < j.length; i++) {
 				jsondata.push([ i + 1, j[i].academic_year, j[i].system_name, j[i].start_date, j[i].end_date,j[i].action ]);
 			}
 		});
 		
		$.post("getTotalacademicschedule_dataCount?" + key + "=" + value, {
			
 			Search : Search,
 			academic_year : academic_year,
 			system_name : system_name,
 			start_date : start_date,
 			end_date : end_date,
 			status : status,
 			userid:userid
 			
 		}, function(j) {
 			FilteredRecords = j;
 		});
 		setTimeout(setTimeLoadForTable, 1000);
 	}
	
	function editData(id,academic_year,system_name,start_date,end_date,status) {
		document.getElementById('lbladd').innerHTML = "Update ";
		
		////////chnge rajdip 3 jan
		$("#btn-save").val('Update');
		//////////
		$("input#academic_year").val(academic_year);
		$("select#system_id").val(system_name);
		$("input#start_date").val(start_date);
		$("input#end_date").val(end_date);
		$("select#status").val(status);
		$("input#id").val(id);
		
	}
	
	function deleteData(id){
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Search() {
		$("#Academic_year1").val($('#academic_year').val());
		$("#Start_date1").val($('#start_date').val());
		$("#End_date1").val($('#end_date').val());
		$("#System_name1").val($('#system_name').val());
		document.getElementById('academicscheduleForm').submit();
	}


	function Validation() {
		
		
		if ($("#academic_year").val().trim() == "0") {
			alert("Please Select Academic Year.");
			$("#academic_year").focus();
			return false;
		}
		
		if ($("input#start_date").val().trim() == "") {
			alert("Please Enter Starting Date.");
			$("input#start_date").focus();
			return false;
		}
		
		if ($("input#end_date").val().trim() == "") {
			alert("Please Enter Ending Date.");
			$("input#end_date").focus();
			return false;
		}
		
		
		if ($("input#system_name").val().trim() == "") {
			alert("Please Enter System name.");
			$("input#system_name").focus();
			return false;
		}
		
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		
		return true;
	}
	
	function onchangeCount(val) {
		if (document.getElementById("start_date").value != "DD/MM/YYYY"
				&& (document.getElementById("end_date").value != "DD/MM/YYYY")
				&& (document.getElementById("end_date").value != "")) {

			var start_date = document.getElementById("start_date").value.split(
					'/').reverse().join('-');
			var end_date = document.getElementById("end_date").value.split('/')
					.reverse().join('-');
			var start_dt = new Date(start_date);
			var end_dt = new Date(end_date);
			// To calculate the time difference of two dates
			var Difference_In_Time = end_dt.getTime() - start_dt.getTime();
			// To calculate the no. of days between two dates
			var Difference_In_Days = (Difference_In_Time / (1000 * 3600 * 24)) ;

			var Difference_In_Weeks = Math.floor(Difference_In_Days / 7);
			var remainday = (Difference_In_Days) - (Difference_In_Weeks * 7);
			if (Difference_In_Days < 1) {
				alert("End Date Should Not be Before Start Date");
// 				$("input#start_date").val("DD/MM/YYYY");
				$("input#end_date").val("DD/MM/YYYY");
				return false;
			}
		}
	}
	
	
</Script>



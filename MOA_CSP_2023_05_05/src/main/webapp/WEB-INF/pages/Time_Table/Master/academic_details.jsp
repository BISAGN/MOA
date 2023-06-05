<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
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
<script type="text/javascript" src="js/watermark/common.js"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Academic Details</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Academic
									Details</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form action="AcademicAction" method="POST"
						class="form-horizontal" modelAttribute="AcademicCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<!-- <h6 class="mb-25">Academic Details</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Professional<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="professional" id="professional" class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getProfessionalList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.professional}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Academic Details<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="academic_details" id="academic_details" class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getAcmDetList}"
														varStatus="num">
														<option value="${item.refer_code}"
															name="${item.refer_code}">${item.academic_details_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>						
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4" id="att_TbdivNameMed">
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4" id="term_div">
										<div class="select-style-1">
											<label for="text-input">Term<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="term" id="term" class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<%-- <c:forEach var="item" items="${geti3_termList}"
														varStatus="num">
														<option value="${item.id}" name="${item.term}">${item.term}</option>
													</c:forEach> --%>
												</select>
											</div>
										</div>	
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4"
										id="exam_type_hid">
										<div class="select-style-1">
											<label for="text-input">Exam Type<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="exam_type" id="exam_type" class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getExam_Type}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4"
										id="exam_serial_hid">
										<div class="select-style-1">
											<label for="text-input">Exam Serial<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select name="exam_serial" id=exam_serial class="singleselect form-control form-control-lg">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getExam_SerialList}"
														varStatus="num">
														<option value="${item.id}" name="${item.exam_serial}">${item.exam_serial}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label for="username">Start Date<span
												class="mandatory">*</span></label> <input type="text"
												name="starting_date" id="starting_date" maxlength="10"
												class="form-control-sm form-control effect-9"
												aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label for="username">End Date<span class="mandatory">*</span></label>
											<input type="text" name="ending_date" id="ending_date"
												maxlength="10" class="form-control-sm form-control effect-9"
												aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">
										</div>
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
												class="main-btn info-btn btn-hover btnsave" type="submit"
												value="Save" /></li>
											<li><a href="academic_detailsUrl"
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
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="table-wrapper table-responsive custom-datatable-p">
							<table class="table" id="search_academic_details">
								<thead>
									<tr>
										<th id="s.id"><h6>Sr No</h6></th>
										<th id="professional"><h6>Professional</h6></th>
										<th id="academic_details_name"><h6>Academic Details</h6></th>
										<th id="term"><h6>Term</h6></th>
										<th id="exam_type"><h6>Exam Type</h6></th>
										<th id="exam_serial"><h6>Exam Serial</h6></th>
										<th id="starting_date"><h6>Starting Date</h6></th>
										<th id="ending_date"><h6>Ending Date</h6></th>
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

<c:url value="getSearch_Academic" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="academicForm"
	name="searchForm" modelAttribute="Academic_details1">
	<input type="hidden" name="Professional1" id="Professional1" value="0" />
	<input type="hidden" name="Academic_details1" id="Academic_details1"
		value="0" />
	<input type="hidden" name="Starting_date1" id="Starting_date1" />
	<input type="hidden" name="Ending_date1" id="Ending_date1" value="0" />
</form:form>

<c:url value="Edit_Academic_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6">
</form:form>

<c:url value="delete_Url22" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id22">
	<input type="hidden" name="id22" id="id22" value="0" />
</form:form>

<c:url value="Systemreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		
		datepicketDate('starting_date');
		$( "#starting_date").datepicker( "option", "maxDate", null);
		
		datepicketDate('ending_date');
		$( "#ending_date").datepicker( "option", "maxDate", null);
		
		
		mockjax1('search_academic_details');
		table = dataTable('search_academic_details');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		
		HideExamType_Serial();
		HideExamSerial();
		$("#exam_serial").val("0");
		$('div#att_TbdivNameMed').hide();
	});
	
	
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('professional').onchange = function() {
			getTerm();
		};
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		document.getElementById('professional').onkeypress = function() {
 			return onlyAlphabetsStringSpace(this, event);
 		};
 		document.getElementById('academic_details').onchange = function() {
 			HideExamType_Serial();
		};
		document.getElementById('exam_type').onchange = function() {
 			HideExamSerial();
 		};
 		document.getElementById('starting_date').onchange = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
		document.getElementById('ending_date').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		document.getElementById('starting_date').onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		
		document.getElementById('starting_date').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		
		document.getElementById('starting_date').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');
			validateDate_BackDate(this.value,this);
		};
		
		document.getElementById('starting_date').onfocus = function() {
			this.style.color='#000000';
		};
		
		document.getElementById('starting_date').onchange = function() {
			
		var start_date = $("#starting_date").val();
		var end_date = $("#ending_date").val();
			
		var s2 = start_date.split('/');
		start_date = s2[2]+"-"+s2[1]+"-"+s2[0];
			
		var e2 = end_date.split('/');
		end_date = e2[2]+"-"+e2[1]+"-"+e2[0]
			
		start_date = new Date(start_date);
		end_date = new Date(end_date);
			
			 if(start_date != "DD/MM/YYYY" && end_date !="DD/MM/YYYY"){
					
				if(end_date < start_date ){
					alert("Starting Date Should be less than Ending Date.")
					$("#ending_date").val("DD/MM/YYYY");
					return false ;
				}
			 }
		};		
		
		document.getElementById('ending_date').onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		
		document.getElementById('ending_date').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		
		document.getElementById('ending_date').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');
			validateDate_BackDate(this.value,this);
		};
		
		document.getElementById('ending_date').onfocus = function() {
			this.style.color='#000000';
		};
		
		document.getElementById('ending_date').onchange = function() {
		
		var start_date = $("#starting_date").val();
		var end_date = $("#ending_date").val();
		
		var s2 = start_date.split('/');
		start_date = s2[2]+"-"+s2[1]+"-"+s2[0];
		
		var e2 = end_date.split('/');
		end_date = e2[2]+"-"+e2[1]+"-"+e2[0]
		
		start_date = new Date(start_date);
		end_date = new Date(end_date);
		
		 	if(start_date != "DD/MM/YYYY" && end_date !="DD/MM/YYYY"){
				
				if(start_date > end_date ){
					alert("Ending Date can not be less than Starting Date.")
					$("#ending_date").val("DD/MM/YYYY");
					return false ;
				}
		 	}
		};
	});
	
	function getTerm(){
		var prof = $("#professional").val();
		$.post("getTermBySysProf?" + key + "=" + value, {
			prof : prof
		}, function(j) {
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			for (var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="'+j[i][0]+'" >'
						+ j[i][1] + '</option>';
			}
			$("select#term").html(options);
		});
	}
	
	function setTimeLoadForTable(){
 		
// 		document.getElementById('academic_details').onkeypress = function() {
//  			 onlyAlphabetsStringSpace(this, event);
//  		};
//  		document.getElementById('term').onkeypress = function() {
//  			return onlyAlphabetsStringSpace(this, event);
//  		};
//  		document.getElementById('exam_type').onkeypress = function() {
//  			return onlyAlphabetsStringSpace(this, event);
//  		};
//  		document.getElementById('exam_serial').onkeypress = function() {
//  			return onlyAlphabetsStringSpace(this, event);
//  		};
		
		document.querySelectorAll('.ADDSystem').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid);
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
	
	function data(search_academic_details) {
		
		jsondata = [];
		var table = $('#' + search_academic_details).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr("id")
				.toLowerCase();
		var orderType = order[0][1];
		var professional = $("#professional").val();
		var academic_details = $("#academic_details").val();
		var term = $("#term").val();
		var exam_type = $("#exam_type").val();
		var exam_serial = $("#exam_serial").val();
		var starting_date = $("#starting_date").val();
		var ending_date = $("#ending_date").val();
		
 		$.post("getFilteracademic_data?" + key + "=" + value, {
 			startPage : startPage,
 			pageLength : pageLength,
 			Search : Search,
 			orderColunm : orderColunm,
 			orderType : orderType,
 			professional : professional,
 			academic_details : academic_details,
 			term : term,
 			exam_type : exam_type,
 			exam_serial : exam_serial,
 			starting_date : starting_date,
 			ending_date : ending_date
 			
 		},
 		function(j) {

 			for (var i = 0; i < j.length; i++) {
 				jsondata.push([ j[i].ser, j[i].professional, j[i].academic_details_name, j[i].term, j[i].exam_type, j[i].exam_serial, j[i].starting_date, j[i].ending_date, j[i].action ]);
 			}
 		});
 		
		$.post("getTotalacademic_dataCount?" + key + "=" + value, {
 			Search : Search,
 			professional : professional,
 			academic_details : academic_details,
 			term : term,
 			exam_type : exam_type,
 			exam_serial : exam_serial,
 			starting_date : starting_date,
 			ending_date : ending_date,
 			
 		}, function(j) {

 			FilteredRecords = j;

 		});
 		setTimeout(setTimeLoadForTable, 1000);
 	}
	
	function editData(id) {

	$("#id6").val(id);
	document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
	$("#id22").val(id);
	document.getElementById('deleteForm').submit();
	}	

	function Search() {
		$("#Professional1").val($('#professional').val());
		$("#Academic_details1").val($('#academic_details').val());
		$("#Starting_date1").val($('#starting_date').val());
		$("#Ending_date1").val($('#ending_date').val());
		document.getElementById('academicForm').submit();
	}
	
	function HideExamType_Serial(){
		var academic_details =	$("#academic_details").val();
		if(academic_details == "002"){
			$("#exam_type_hid").show();
			$("#term_div").show();
			$('div#att_TbdivNameMed').hide();
		}else if(academic_details == "003"){
			$("#term_div").hide();
			$("#exam_type_hid").hide();
			$("#exam_serial_hid").hide();
			$('div#att_TbdivNameMed').show();
			GetPartFromFeeTimeRange();			
		}else if(academic_details == "001"){
			$("#term_div").show();
			$("#exam_type_hid").hide();
			$("#exam_serial_hid").hide();
			$('div#att_TbdivNameMed').hide();
		}else if(academic_details == "004"){
			$("#term_div").hide();
			$("#exam_type_hid").hide();
			$("#exam_serial_hid").hide();
			$('div#att_TbdivNameMed').hide();
		}else{
			$("#exam_type_hid").hide();
			$("#exam_type").val("0");
			$("#term_div").hide();
			$('div#att_TbdivNameMed').hide();
		}	
	}
	
	function HideExamSerial(){
		var exam_type =	$("#exam_type").val();
		if(exam_type == "8"){
			
			$("#exam_serial_hid").show();	
			$("#exam_serial").val("0");
			
		}else{
			$("#exam_serial_hid").hide();
			$("#exam_serial").val("1");
		}		
	}
	
	function Validation() {
		
		if ($("select#professional").val().trim() == "0") {
			alert("Please Select Professional.");
			$("select#professional").focus();
			return false;
		}
		
		if ($("select#academic_details").val().trim() == "0") {
			alert("Please Select Academic Details.");
			$("select#academic_details").focus();
			return false;
		}
		
		if($("select#academic_details").val() == "001"){
			if ($("select#term").val().trim() == "0") {
				alert("Please Select Term.");
				$("select#term").focus();
				return false;
			}
		}
		
		
		if($("select#academic_details").val() == "002"){
			if ($("select#exam_type").val().trim() == "0") {
				alert("Please Select Exam Type.");
				$("select#exam_type").focus();
				return false;
			}
			
			if ($("select#exam_type").val().trim() == "8"){
				if ($("select#exam_serial").val().trim() == "0") {
					alert("Please Select Exam Serial.");
					$("select#exam_serial").focus();
					return false;
				}
			}
			if ($("select#term").val().trim() == "0") {
				alert("Please Select Term.");
				$("select#term").focus();
				return false;
			}
		}
		
		if($("#starting_date").val() == "DD/MM/YYYY" || $("#starting_date").val() == "") {
			alert("Please Enter Starting Date.");
 			$("#starting_date").focus();
			return false;
	 	}
		
		
		if($("#ending_date").val() == "DD/MM/YYYY" || $("#ending_date").val() == "") {
			alert("Please Enter Ending Date.");
 			$("#ending_date").focus();
			return false;
	 	}
	
		return true;
	}
	
// 	function GetTermFromNoofpart(role,inst_id){
		
// // 		var stuid = $("#viewhidId"+ser).val();
// 		var stuid = $("#stu_hid").val();
// 		var part = $("#no_of_part_hid").val();
		
// 		$.post('updatefeesdata?' + key + "=" + value, {
// 			part : part
// 		}).done(function(j) {
// 			alert(j);
// 			window.location = $("#url1").val();
// 		});
// 	}
	
	function GetPartFromFeeTimeRange() {
		
// 		var part = $("#no_of_part_hid").val();
		
		$.ajaxSetup({
			async: false
		});
		$.post("GetTermFromNoofpartUrl?" + key + "=" + value,
			{ },function(j) {
				if(j[0].no_of_part=="1") {
					$("div#att_TbdivNameMed").empty();
// 					$("div#att_TbdivNameMed").append('<label>Full Payment</label>');
					$("div#att_TbdivNameMed").append('<div class="input-style-1 mb-0">'
					+'	<label>Payment</label>'
					+'</div>'
					+'<div class="custom-choose-one">'
					+'<div class="input-style-form-check">'
					+'<div class="form-check radio-style">'
					+'	<input type="radio" class="form-check-input" id="full_rb" name="Choise" value="Full" checked>'
					+'<label for="Upload" class="form-check-label">Full Payment</label>'
					+'</div>'
					+'<div class="form-check radio-style">'
					+'		<input type="radio" class="form-check-input" id="part_rb" name="Choise" value="Part" disabled>'
					+'<label class="form-check-label" for="Fillform">Part'
					+'		Payment</label>'
					+'</div>'
					+'</div>'
					+'</div>');
				}
				if(j[0].no_of_part>"1") {
					$("div#att_TbdivNameMed").empty();
					$("div#att_TbdivNameMed").append('<div class="input-style-1 mb-0">'
							+'	<label>Payment</label>'
							+'</div>'
							+'<div class="custom-choose-one">'
							+'<div class="input-style-form-check">'
							+'<div class="form-check radio-style">'
							+'	<input type="radio" class="form-check-input" id="full_rb" name="Choise" value="Full" disabled>'
							+'<label for="Upload" class="form-check-label">Full Payment</label>'
							+'</div>'
							+'<div class="form-check radio-style">'
							+'		<input type="radio" class="form-check-input" id="part_rb" name="Choise" value="Part" checked>'
							+'<label class="form-check-label" for="Fillform">Part'
							+'		Payment</label>'
							+'</div>'
							+'</div>'
							+'</div>');
					$("#term_div").show();
				}
				
			});
	}
	
</Script>

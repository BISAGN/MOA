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
						<h2>Update Academic Details</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update
									Academic Details</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="Academic" id="Academic"
						action="edit_Academic_Action" method="POST"
						modelAttribute="edit_Academic_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<!-- <h6 class="mb-25">Update Academic Details</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Professional:<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="professional" id="professional">
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
												<select class="singleselect form-control form-control-lg"
													name="refer_code" id="refer_code">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${getAcademicList}"
														varStatus="num">
														<option value="${item.refer_code}"
															name="${item.refer_code}">${item.academic_details_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Term<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-control form-control-lg"
													name="term" id="term">
													<option value="0">---Select---</option>
													<c:forEach var="item" items="${geti3_termList}"
														varStatus="num">
														<option value="${item.id}" name="${item.term}">${item.term}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4"
										id="exam_type_hid" name="exam_type_hid">
										<div class="select-style-1">
											<label for="text-input">Exam Type<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="exam_type" id="exam_type">
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
										id="exam_serial_hid" name="exam_serial_hid">
										<div class="select-style-1">
											<label for="text-input">Exam Serial<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="exam_serial" id=exam_serial>
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
										<div class="input-style-2">
											<label for="username">Start Date<span
												class="mandatory">*</span></label> <input type="text"
												name="starting_date" id="starting_date" maxlength="10"
												class="form-control-sm form-control effect-9 "
												onfocus="this.style.color='#000000'" aria-required="true"
												autocomplete="off" placeholder="DD/MM/YYYY">
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-2">
											<label for="username">End Date<span
												class="mandatory">*</span></label> <input type="text"
												name="ending_date" id="ending_date" maxlength="10"
												class="form-control-sm form-control effect-9 "
												onfocus="this.style.color='#000000'" aria-required="true"
												autocomplete="off" placeholder="DD/MM/YYYY">
										</div>
									</div>
								</div>
							</div>
							
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group d-flex justify-content-center">
											<li><a href="academic_detailsUrl"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
													<i class="lni lni-chevron-left"></i>Back
											</a></li>
											<li><input id="update"
												class="main-btn deactive-btn btn-hover btn-update"
												value="Update" type="submit"> <input type="hidden"
												name="updateid" id="updateid" value="${updateid}"
												class="main-btn deactive-btn btn-hover btnupda" /></li>
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
	$(document).ready(function() {debugger;
	$.ajaxSetup({
		async : false
	});
		datepicketDate('starting_date');
		$( "#starting_date").datepicker( "option", "maxDate", null);		
		datepicketDate('ending_date');
		$( "#ending_date").datepicker( "option", "maxDate", null);
		$('#id').val('${academic_Details.id}');
		$('select#professional').val('${academic_Details.professional}');
		$('#professional').trigger('change');
		$('#refer_code').val('${academic_Details.academic_details}');		
		$('#refer_code').trigger('change');
		HideExamType_Serial();
		$('#term').val('${academic_Details.term}');
		$('select#exam_type').val('${academic_Details.exam_type}');
		$('#exam_type').trigger('change');
		HideExamSerial();
		$('select#exam_serial').val('${academic_Details.exam_serial}');
		$('#exam_serial').trigger('change');
		$('#starting_date').val('${academic_Details.starting_date}');
		$('#ending_date').val('${academic_Details.ending_date}');
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});

		var end_date = '${academic_Details.ending_date}'
		end_date = end_date.substring(0, 10)
		var end_year = end_date.substring(0, 4)
		var end_month = end_date.substring(5, 7)
		var end_date = end_date.substring(8, 10)

		end_date = end_date + "/" + end_month + "/" + end_year
		$('#ending_date').val(end_date);
		var start_date = '${academic_Details.starting_date}'
		start_date = start_date.substring(0, 10)
		var start_year = start_date.substring(0, 4)
		var start_month = start_date.substring(5, 7)
		var start_date = start_date.substring(8, 10)

		start_date = start_date + "/" + start_month + "/" + start_year
		$('#starting_date').val(start_date);

		HideExamType_Serial();
		HideExamSerial();
	});

	//------------------------csp start------------------------------------//
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('professional').onchange = function() {
			getTerm();
		};
// 		document.getElementById('btn-save').onclick = function() {
// 			return Validation();
// 		};
		document.getElementById('professional').onkeypress = function() {
 			return onlyAlphabetsStringSpace(this, event);
 		};
 		document.getElementById('refer_code').onchange = function() {
 			HideExamType_Serial();
		};
		document.getElementById('exam_type').onchange = function() {
 			HideExamSerial();
 		};
 		document.getElementById('starting_date').onchange = function() {
			return onlyAlphabetsStringSpace(this, event);HideExamSerial()
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

	function HideExamType_Serial(){
		debugger;
		var academic_details =	$("#refer_code").val();
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
		
		if ($("select#refer_code").val().trim() == "0") {
			alert("Please Select Academic Details.");
			$("select#refer_code").focus();
			return false;
		}
		
		if($("select#refer_code").val() == "001"){
			if ($("select#term").val().trim() == "0") {
				alert("Please Select Term.");
				$("select#term").focus();
				return false;
			}
		}		
		
		if($("select#refer_code").val() == "002"){
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
</script>



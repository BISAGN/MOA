<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">

	<section class="dashboard-page">
		<div class="container-fluid">
			<!-- title-wrapper start -->
			<div class="title-wrapper pt-30">
				<div class="row align-items-center">
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="title mb-30">
							<h2>Declaration</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
									<li class="breadcrumb-item active" aria-current="page">
										Declaration</li>
								</ol>
							</nav>
						</div>
					</div>
					<!-- end col -->
				</div>
				<!-- end row -->
			</div>
			<!-- title-wrapper end -->
			<div class="form-elements-wrapper custom_v_tab">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-style mb-30">
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>Institution Name :</label> <span class="value-bind">${roleloginName }</span>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>College Code :</label> <span class="value-bind">${username}</span>
									</div>

								</div>
							</div>
						</div>
					</div>
					<!--    ===========================
	Declaration Start
===========================     -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-style mb-30">
						<!-- Affidavit & Indemnity Bond Start-->
							<div class="tabs content_h600">
								<div class="tab">
									<button class="tab-toggle" id="defaultOpen">Affidavit & Indemnity Bond</button>
								</div>
								<div class="content ">
									<h4 class="heading">Affidavit & Indemnity Bond</h4>
									<form:form name="upload_doc" id="upload_doc" action="" method="post" class=""
										commandName="">
										<div class="row">
										
										<input type="hidden" id="hid_upload_doc" name="hid_upload_doc" value="0">
										<input type="hidden" id="basic_info_id" name="basic_info_id" value="0">
										
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="inst_block simple-instruction">
												<strong>Instruction :</strong>You are required to submit an
												Affidavit/ Indemnity bond before final submission. Please
												click on 'Download templates' to download the templates of
												Affidavit/ Indemnity bond form given below.
											</div>
										</div>
										<hr>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Please upload scan copy of duly notarized undertaking/indemnity Bond<strong
														class="mandatory">*</strong></label>
														<div class="input-group custom-input-group"> 
														
															<input type="file" id="notarizedundertaking" name="notarizedundertaking" class="form-control" accept=".pdf">
															<input type="hidden" id="hid_notarizedundertaking" name="hid_notarizedundertaking" value="0">
														
													<div class="note-text">
														<span class="errorClass" id="notarizedundertaking_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="notarizedundertaking_lbltik"></span>
													</div>
														
										<ul class="buttons-group">
											<li><a id="notarizedundertaking_download" href="AttachmentFilePath_declaration?i_id=${GetDocument_Details[0][0]}&doc_id=1"download 
													class="main-btn info-btn btn-hover btn-sm btndownload" title="Download Template">
											<i class="lni lni-download"></i></a></li>
										</ul></div>
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label>Please upload scan copy of duly notarized undertaking/affidavit<strong
														class="mandatory">*</strong></label>
														<div class="input-group custom-input-group"> 
														
															<input type="file" id="notarizedaffidavit" name="notarizedaffidavit" class="form-control" accept=".pdf">
															<input type="hidden" id="hid_notarizedaffidavit" name="hid_notarizedaffidavit" value="0">
															
													<div class="note-text">
														<span class="errorClass" id="notarizedaffidavit_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="notarizedaffidavit_lbltik"></span>
													</div>
															
										<ul class="buttons-group">
											<li><a id="notarizedaffidavit_download" href="AttachmentFilePath_declaration?i_id=${GetDocument_Details[0][0]}&doc_id=2"download
												class="main-btn info-btn btn-hover btn-sm btndownload" title="Download Template">
											<i class="lni lni-download"></i></a></li>
										</ul></div>
												</div>
											</div>

										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave" id="upload_doc_save"
														type="button" value="Save"></li>
														<li><a class="main-btn dark-btn-outline btn-hover btn-iconic-icon viewData d-none"><i
															class="lni lni-eye"></i>View</a>
															<input type='hidden' id="viewId${parent_id}" value="${institude}"></li>
												</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Affidavit & Indemnity Bond End-->
								<!-- Declaration by Principal Start-->

								<div class="tab">
									<button class="tab-toggle">Declaration by Principal</button>
								</div>
								<div class="content ">
									<h4 class="heading">Declaration by Principal</h4>
									<form:form name="principal_declaration" id="principal_declaration" action="" method="post" class=""
										commandName="">
										<div class="top_content">
											<div class="row">
											
											<input type="hidden" id="hid_principal_declaration" name="hid_principal_declaration" value="0">
											
											    <div class="col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="inst_block simple-instruction">
												<strong>Instruction :</strong>This is an electronic form and requires no signature. Clicking on 'I Agree' by the Institution signifies that the Principal has agreed to
the information submitted for this visitation.
											</div>
										</div>
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<div class="input-style-1">
														<p>I, Dr.<input type="text" name="prin_declaration"
															id="prin_declaration" class="form-control declaration_input"
															placeholder="Principal Name" maxlength="100" readonly="readonly"> Principal, <b>Test Homoeopathic Medical College and Hospital</b> solemnly writing that if any information
provided by me in proforma and Annexures found false, I shall be held responsible in the matter. I shall have no objection if any action is
taken by the Competent authority against me
														</p>
													</div>
													<div class="custom-choose-one">
														<div class="input-style-form-check_block check-multi-list">
															<div class="form-check checkbox-style">
																<input type="checkbox" id="prin_Declaration"
																	name="prin_Declaration" autocomplete="off" maxlength="25"
																	class="form-check-input"  value="1"> <label
																	class="check-list" for="prin_Declaration">I Agree</label>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
													<li><input class="main-btn info-btn btn-hover btnsave" id="principal_check_save"
														type="button" value="Save"></li>
												</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Declaration by Principal End-->
								<!-- Declaration by Management Start-->
									<div class="tab">
									<button class="tab-toggle">Declaration by Management</button>
								</div>
								<div class="content ">
									<h4 class="heading">Declaration by Management</h4>
									<form:form name="management_declaration" id="management_declaration" action="" method="post" class=""
										commandName="">
										<div class="top_content">
											<div class="row">
											
											<input type="hidden" id="hid_management_declaration" name="hid_management_declaration" value="0">
											
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<div class="input-style-1">
														<p>I, Dr.<input type="text" name="mangement_declaration"
															id="mangement_declaration" class="form-control declaration_input"
															placeholder="Enter Name" maxlength="100"> solemnly state that, I am looking after the management of the college & hospital. The information
provided by the Principal in the proforma and Annexures are true. If any information provided by the Principal found false the undersigned has
no objection for any action initiated by the Competent authority against the Principal and me.
														</p>
													</div>
													<div class="custom-choose-one">
														<div class="input-style-form-check_block check-multi-list">
															<div class="form-check checkbox-style">
																<input type="checkbox" id="mange_Declaration"
																	name="mange_Declaration" autocomplete="off" maxlength="25"
																	class="form-check-input"  value="1"> <label
																	class="check-list" for="mange_Declaration">I Agree</label>
															</div>
														</div>
													</div>

												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input
															class="main-btn success-btn btn-hover btnsubmit" id="mang_check_save"
															type="button" value="Submit"></li>
													</ul>
												</div>
											</div>

										</div>
									</form:form>
								</div>
								<!-- Declaration by Management End-->
							</div>

						</div>
					</div>
					<!--    ===========================
		Declaration End
===========================     -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</section>
	<!-- components end -->
	
			<c:url value="Declaration_ViewUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="declaration_view_id">
	<input type="hidden" name="declaration_id" id="declaration_view_id" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>

	<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>

<script type="text/javascript" nonce="${cspNonce}">
$(document).ready(function() {
	if ('${role}'=='NCH') {
		$(".viewData").addClass("d-none")
	}
	if ('${role}'=='Institute_NCH') {
		$(".viewData").removeClass("d-none")
	} 
	var valid_dt = '${valid_dt}';
	var y = valid_dt.substring(0, 4);
	var m = valid_dt.substring(5, 7);
	var d = valid_dt.substring(8, 10);
	var valid_dt1 = d + "/" + m + "/" + y;
	var today = new Date();
	var yyyy = today.getFullYear();
	var mm = today.getMonth() + 1; // Months start at 0!
	var dd = today.getDate();
	if (dd < 10)
		dd = '0' + dd;
	if (mm < 10)
		mm = '0' + mm;
	today = dd + '/' + mm + '/' + yyyy;
	var valid_dt2 = valid_dt.split('/').reverse().join('-');
	var today2 = today.split('/').reverse().join('-');
	if ("${hid}" != "3" && valid_dt2 <= today2) {
//			datepicketDate('lsad1');
	}
	$("#basic_info_id").val('${basic_info_id}');
	$("#prin_declaration").val('${GetPrinacipal_Name[0][0]}');
	
	var dataparent ='${GetDocument_Details}';
	
	if(dataparent != "[]"){
	$("#hid_upload_doc").val('${GetDocument_Details[0][0]}');
	$("#hid_principal_declaration").val('${GetDocument_Details[0][0]}');
	$("#hid_management_declaration").val('${GetDocument_Details[0][0]}');
	$("#hid_notarizedundertaking").val('${GetDocument_Details[0][3]}');
	$("#hid_notarizedaffidavit").val('${GetDocument_Details[0][4]}');
	
	var princ_declaration_check = '${GetDocument_Details[0][5]}'
		$("input[name='prin_Declaration'][value='" + princ_declaration_check + "']").prop('checked', true);
	var mang_declaration_check = '${GetDocument_Details[0][6]}'
		$("input[name='mange_Declaration'][value='" + mang_declaration_check + "']").prop('checked', true);
	
	}
	
});

//	--------------------------------------------START-->UPLOAD DOCUMENT DETAILS---------------------------------------------

//SAVE TEACHERS DETAILS
function Save_As_Draft_Document_Details(sd) {
	
	$.ajaxSetup({
	    async: false
	});	
	
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		if(sd != -1){
		alert("Please Save Basic details First");
		}
		return false;
	}
	var hid_notarizedundertaking = CheckNullorBlank('hid_notarizedundertaking');
	if(hid_notarizedundertaking !== "true"){
		var res = CheckNullorBlank('notarizedundertaking');
		if (res !== "true") {
			alert(res +"Document of Duly Notarized Undertaking/Indemnity Bond");
				$('#notarizedundertaking').focus();
			return false;
		}
	}

	var hid_notarizedaffidavit = CheckNullorBlank('hid_notarizedaffidavit');
	if(hid_notarizedaffidavit !== "true"){
		var res = CheckNullorBlank('notarizedaffidavit');
		if (res !== "true") {
			alert(res +"Document of Duly Notarized Undertaking/Affidavit");
				$('#notarizedaffidavit').focus();
			return false;
		}
	}
	
	var form_data = new FormData(document.getElementById("upload_doc"));
	$.ajax({
		url : 'Declaration_Document_Details_Save_Draft_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j){
      if(j>0){
    	  
			if(sd != -1){
    	  alert("Data Save Sucessfully");
			}
    	  location.reload();
      }
      else{
    	  if(sd != -1){
    	  alert(j);
    	  }
    	  location.reload();
      }
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
		
 }



//SAVE PRICIPAL DECLARATION CHECK
function Save_As_Draft_Pricipal_Declaration_check(sd) {
	
	$.ajaxSetup({
	    async: false
	});	

	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		if(sd != -1){
		alert("Please Save Basic details First");
		}
		return false;
	}
	
	var prin_Declaration = $("input[name='prin_Declaration']:checked").val();
	if (prin_Declaration == null) {
		alert("Please Accept Terms And Condition");
		return false;
	}
	
	var form_data = new FormData(document.getElementById("principal_declaration"));
	$.ajax({
		url : 'Pricipal_Declaration_check_Save_Draft_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j){
      if(j>0){
    	  if(sd != -1){
    	  alert("Data Save Sucessfully");
    	  }
    	  location.reload();
      }
      else{
    	  if(sd != -1){
    	  alert(j);
    	  }
    	  location.reload();
      }
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
		
 }


//SAVE MANAGMENT DECLARATION CHECK
function Save_As_Draft_Managment_Declaration_check(sd) {
	
	$.ajaxSetup({
	    async: false
	});	

	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	var hid_notarizedundertaking = CheckNullorBlank('hid_notarizedundertaking');
	if(hid_notarizedundertaking !== "true"){
		var res = CheckNullorBlank('notarizedundertaking');
		if (res !== "true") {
			alert(res +"Document of Duly Notarized Undertaking/Indemnity Bond");
				$('#notarizedundertaking').focus();
			return false;
		}
	}

	var hid_notarizedaffidavit = CheckNullorBlank('hid_notarizedaffidavit');
	if(hid_notarizedaffidavit !== "true"){
		var res = CheckNullorBlank('notarizedaffidavit');
		if (res !== "true") {
			alert(res +"Document of Duly Notarized Undertaking/Affidavit");
				$('#notarizedaffidavit').focus();
			return false;
		}
	}
	var prin_Declaration = $("input[name='prin_Declaration']:checked").val();
	if (prin_Declaration == null) {
		alert("Please Accept Terms And Condition");
		return false;
	}
	var mange_Declaration = $("input[name='mange_Declaration']:checked").val();
	if (mange_Declaration == null) {
		alert("Please Accept Terms And Condition");
		return false;
	}
	
	Save_As_Draft_Document_Details(-1);
	Save_As_Draft_Pricipal_Declaration_check(-1);
	
	
	var form_data = new FormData(document.getElementById("management_declaration"));
	$.ajax({
		url : 'Manegment_Declaration_check_Save_Draft_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j){
      if(j>0){
    	  alert("Final Submit Sucessfully");
    	  location.reload();
      }
      else{
    	  if(sd != -1){
    	  alert(j);
    	  }
    	  location.reload();
      }
	}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	});
		
 }
//	--------------------------------------------END-->UPLOAD DOCUMENT DETAILS---------------------------------------------

// ================================CSP START=================================
	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('upload_doc_save').onclick = function() {
			return Save_As_Draft_Document_Details();	
		};
		document.getElementById('principal_check_save').onclick = function() {
			return Save_As_Draft_Pricipal_Declaration_check();	
		};
		document.getElementById('mang_check_save').onclick = function() {
			return Save_As_Draft_Managment_Declaration_check();	
		};
		
		document.getElementById('notarizedundertaking').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"notarizedundertaking","200kb","notarizedundertaking_lbltik","notarizedundertaking_lbl",this.accept)
		};
		document.getElementById('notarizedaffidavit').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"notarizedaffidavit","200kb","notarizedaffidavit_lbltik","notarizedaffidavit_lbl",this.accept)
		};
		
	});
	
// ================================CSP END=================================

		document.querySelectorAll('.viewData').forEach((items, index) => {
	items.addEventListener('click', event => {
		var val=parseInt(index)+1;
		var hid = document.getElementById('viewId${parent_id}').value;
		if (confirm('Are You Sure You Want to View Detail ?')) {
			ViewData(hid);
		} else {
			return false;
		}
	})
});

function ViewData(id){

	$("#declaration_view_id").val(id);
$("#statusview").val($("#statusA").val());
document.getElementById('viewForm').submit();
}

</script>
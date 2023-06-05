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
							<h2>Hospital Staff Details</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
									<li class="breadcrumb-item active" aria-current="page">
										Hospital Staff Details</li>
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
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="inst_block simple-instruction">
										<strong>Instruction :</strong> If any of the value is not
										available or not applicable then please put it as 0
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>Institution Name :</label> <span class="value-bind">${roleloginName}</span>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>Institution State :</label> <span class="value-bind">${username}</span>
									</div>

								</div>

							</div>
						</div>
					</div>
					<!-- ===========================
							Hospital Staff Start
						=========================== -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-style mb-30">
							<div class="tabs content_h750">
								<!-- Hospital Staff Start -->
								<div class="tab">
									<button class="tab-toggle" id="defaultOpen">Hospital Staff</button>
								</div>
								<div class="content">
									<h4 class="heading">Hospital Staff</h4>
									<form:form name="hospital_staff" id="hospital_staff" action="Hospital_Details_Action" method="post" class=""
										commandName="hospital_staff_CMD">
										<div class="row">
										
										<input type="hidden" id="basic_info_id" name="basic_info_id" value="0">
										
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="hospitalstaff_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Post</h6></th>
																<th><h6>Type<strong class="mandatory">*</strong></h6></th>
																<th><h6>Available Staff<strong class="mandatory">*</strong>
																	</h6></th>
															</tr>
														
														</thead>
														<tbody id=" ">
														<c:forEach var="j" items="${getHospitalpostlist}"
															varStatus="num">
															
																<tr>
																<td>
																	<p>${num.index+1}</p>
																</td>
																<td>
																	<p>${j[1]}</p>
																	<input type="hidden" id="hs_post_hid${num.index+1}"
																		name="hs_post_hid${num.index+1}" value="${j[0]}"
																		class="form-control">
																	
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="type_${j[0]}" id="type_${j[0]}"
																				class="form-control">
																				<option value="0" selected="selected">--
																					Select--</option>
																				<c:forEach var="item" items="${getTypelist}"
											                                        	varStatus="num">
											                             	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										                               	</c:forEach>
	
																			</select>
																		</div>
																	</div>
																	
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" maxlength="9" name="no_of_av_staff_${j[0]}"
																			id="no_of_av_staff_${j[0]}" value = "0" class="form-control"
																			placeholder="Total Available Staff">
																	</div>
																	
																	<input type="hidden" id="hosp_det_hid${j[0]}"
																		name="hosp_det_hid${j[0]}" value="0"
																		class="form-control ">
																</td>
															
																</tr>
																
																</c:forEach>
																
																
																
														</tbody>
													</table>
												</div>
												<!-- end row -->
											</div>
										</div>
										
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input
															class="main-btn info-btn btn-hover btnssave" type="button" id="hosp_save"
															value="Save"></li>
															<li><a 
																class="main-btn dark-btn-outline btn-hover btn-iconic-icon viewData d-none"><i
																	class="lni lni-eye"></i>View</a>
																	<input type='hidden' id="viewId${parent_id}" value="${institude}"></li>
													</ul>
												</div>
											</div>
										</div>
										
										<%-- <input type="hidden" id="totalcount"
												name="totalcount" value="${getHospitalpostlist.size()}"
												class="form-control"> --%>
										
									</form:form>
								</div>
								<!-- Hospital Staff End -->
								<!-- Upload Document Start-->
								<div class="tab">
									<button class="tab-toggle">Upload Document</button>
								</div>
								<div class="content">
									<h4 class="heading">Upload Document</h4>
									<form:form name="upload_doc" id="upload_doc" action="Hosp_Detl_Upload_Documnet_Action" method="post" class=""
										commandName="UploadCMD">
										<div class="row">
										<input type="hidden" id="hid_upload_document" name="hid_upload_document" value="0">
										<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="inst_block simple-instruction">
									<strong>Instruction :</strong> Kindly upload last 1 financial year document.
								</div>
							</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label for="fname">Attendance Register and Biometric attendance of Hospital Staff<strong
														class="mandatory">*</strong></label> 
													<input type="file" id="attend_hospitalstaff"
														name="attend_hospitalstaff" class="form-control" accept="pdf">
														<input type="hidden" id="hid_attend_hospitalstaff" name="hid_attend_hospitalstaff" class="form-control" value="">
												<div class="note-text">
													<span class="errorClass" id="attend_hospitalstaff_lbl">${doc_path_doc1_msg}</span>
													<span class='tikClass' id="attend_hospitalstaff_lbltik"></span>
												</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label for="fname">Acquittance Role of Hospital Staff<strong
														class="mandatory">*</strong></label> <input type="file"
														id="acquittancestaff" name="acquittancestaff" class="form-control" accept="pdf">
														<input type="hidden" id="hid_acquittancestaff" name="hid_acquittancestaff"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="acquittancestaff_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="acquittancestaff_lbltik"></span>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label for="fname">Doctors duty rosters <strong
														class="mandatory">*</strong></label> <input type="file"
														id="doctor_rosters" name="doctor_rosters"
														class="form-control" accept="pdf">
														<input type="hidden" id="hid_doctor_rosters" name="hid_doctor_rosters"
															class="form-control" value="">
												<div class="note-text">
													<span class="errorClass" id="doctor_rosters_lbl">${doc_path_doc1_msg}</span>
													<span class='tikClass' id="doctor_rosters_lbltik"></span>
												</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label for="fname">Nurse duty rosters<strong class="mandatory">*</strong>
													</label> <input type="file" id="nurse_rosters" name="nurse_rosters"
														class="form-control" accept="pdf">
														<input type="hidden" id="hid_nurse_rosters" name="hid_nurse_rosters"
															class="form-control" value="">
													<div class="note-text">
														<span class="errorClass" id="nurse_rosters_lbl">${doc_path_doc1_msg}</span>
														<span class='tikClass' id="nurse_rosters_lbltik"></span>
													</div>
												</div>
											</div>
											
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn success-btn btn-hover btnsubmit" id="Doc_save"
															type="button" value="Submit"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Upload Document End-->
								
							</div>
						</div>
					</div>
					<!-- ===========================
							Hospital Staff End
						=========================== -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</section>
	<!-- regulation components end -->
	
<!-- ==================================================================== -->
	<c:url value="hospital_staffdetails_view" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewForm"
		name="viewForm" modelAttribute="hos_staff_dtl_view_id">
		<input type="hidden" name="hos_staff_dtl_id" id="hos_staff_dtl_view_id" value="0" />
		<input type="hidden" name="statusview" id="statusview" value="0" />
	</form:form>
<!-- ==================================================================== -->	
	
	<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>
		
<script type="text/javascript" nonce="${cspNonce}">

$(document).ready(function() {
	
	
	$.ajaxSetup({
		async : false
	});
	if ('${role}'=='NCH') {
		$(".viewData").addClass("d-none")
	}
	if ('${role}'=='Institute_NCH') {
		$(".viewData").removeClass("d-none")
	} 
	
	$("#basic_info_id").val('${basic_info_id}');
	
	
//-------------Start----------------Data Fetch For----------------
	var dataparent ='${dataparent}';
	
	if(dataparent != "[]"){
		
	<c:forEach var="j" items="${dataparent}"
			varStatus="num">
		var index = '${num.index+1}';
		$("#hosp_det_hid"+index).val('${j.id}');
		$("#type_"+index).val('${j.type}');
		$("#no_of_av_staff_"+index).val('${j.no_of_av_staff}');
	</c:forEach>
		
// //--------------EnD------------------Data Fetch For ----------------		

		}		
	getUpload_Document_Details();
});


document.addEventListener('DOMContentLoaded', function() {
	
	
	document.getElementById('hosp_save').onclick = function() {
		saveDraft_Hospital_Staff_data();
	};
	
	<c:forEach var="j" items="${getHospitalpostlist}" varStatus="num">
	
	document.getElementById('no_of_av_staff_'+${j[0]}).onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	
	</c:forEach>
	document.getElementById('attend_hospitalstaff').onchange = function() {
		return pdfFileSizeValidation(this,this.value,"attend_hospitalstaff","200kb","attend_hospitalstaff_lbltik","attend_hospitalstaff_lbl",this.accept)
	};
	document.getElementById('acquittancestaff').onchange = function() {
		return pdfFileSizeValidation(this,this.value,"acquittancestaff","200kb","acquittancestaff_lbltik","acquittancestaff_lbl",this.accept)
	};
	document.getElementById('doctor_rosters').onchange = function() {
		return pdfFileSizeValidation(this,this.value,"doctor_rosters","200kb","doctor_rosters_lbltik","doctor_rosters_lbl",this.accept)
	};
	document.getElementById('nurse_rosters').onchange = function() {
		return pdfFileSizeValidation(this,this.value,"nurse_rosters","200kb","nurse_rosters_lbltik","nurse_rosters_lbl",this.accept)
	};
	
	document.getElementById('Doc_save').onclick = function() {
	      Submit_Hospital_Staff_doc();
	};
	
});


function saveDraft_Hospital_Staff_data(sd) {

$.ajaxSetup({
    async: false
});	


var basic_info_id = $("#basic_info_id").val();
if(basic_info_id == "0"){
	alert("Please Save Basic details First");
	return false;
}

<c:forEach var="j" items="${getHospitalpostlist}" varStatus="num">

var res = CheckNullorBlank('type_'+${j[0]});
if (res !== "true") {
	alert(res +"Type of ${j[1]}");
	$('#type_'+${j[0]}).focus();
	return false;
}

var res = CheckNullorBlankZero('no_of_av_staff_'+${j[0]});
if (res !== "true") {
	alert(res +"Number of ${j[1]}");
	$('#no_of_av_staff_'+${j[0]}).focus();
	return false;
}

</c:forEach>


var form_data = new FormData(document.getElementById("hospital_staff"));

		
	$.ajax({
        url: 'Hospital_Details_Action?' + key + "=" + value,
        type: "POST",
        data: form_data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false
      }).done(function(data) {
    	 if(sd != -1){ 
    	 alert(data.msg);
    	 }
	   	location.reload();
   	
		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
         
      }).fail(function(jqXHR, textStatus) {

      });
	 
}


function Submit_Hospital_Staff_doc(sd) {

	$.ajaxSetup({
	    async: false
	});	

	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	
	var hid_attend_hospitalstaff = CheckNullorBlank('hid_attend_hospitalstaff');
	if(hid_attend_hospitalstaff !== "true"){
		var res = CheckNullorBlank('attend_hospitalstaff');
		if (res !== "true") {
			alert(res +"Document of Attendance Register and Biometric attendance of Hospital Staff");
			return false;
		}
	}
	
	var hid_acquittancestaff = CheckNullorBlank('hid_acquittancestaff');
	if(hid_acquittancestaff !== "true"){
		var res = CheckNullorBlank('acquittancestaff');
		if (res !== "true") {
			alert(res +"Document of Acquittance Role of Hospital Staff");
			return false;
		}
	}
	
	var hid_doctor_rosters = CheckNullorBlank('hid_doctor_rosters');
	if(hid_doctor_rosters !== "true"){
		var res = CheckNullorBlank('doctor_rosters');
		if (res !== "true") {
			alert(res +"Document of Doctors duty rosters ");
			return false;
		}
	}
	
	var hid_nurse_rosters = CheckNullorBlank('hid_nurse_rosters');
	if(hid_nurse_rosters !== "true"){
		var res = CheckNullorBlank('nurse_rosters');
		if (res !== "true") {
			alert(res +"Document of Nurse duty rosters");
			return false;
		}
	}
	
	saveDraft_Hospital_Staff_data(-1);
	
		var form_data = new FormData(document.getElementById("upload_doc"));
		$.ajax({
			url : 'Hosp_Detl_Upload_Documnet_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j) {
					if(j>0){
						$("#hid_upload_document").val(j);
						if(sd != -1){
						alert("Data Saved Successfully");
						}
		          }
		          else{
		        	  if(sd != -1){
		        	  alert(j);
		        	  }
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
		
	
	}
	
	
//FETCH UPLOAD DOCUMNET DETAILS
function getUpload_Document_Details() {
	$.ajaxSetup({
		async : false
	});
	
	$.post("getHosp_Document_Details?" + key + "=" + value, {
		
	}, function(j) {
		
		
		$("#hid_upload_document").val(j[0].id);
		$("#hid_attend_hospitalstaff").val(j[0].attend_hospitalstaff);
		$("#hid_acquittancestaff").val(j[0].acquittancestaff);
		$("#hid_doctor_rosters").val(j[0].doctor_rosters);
		$("#hid_nurse_rosters").val(j[0].nurse_rosters);
		
	});
}


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

	$("#hos_staff_dtl_view_id").val(id);
$("#statusview").val($("#statusA").val());
document.getElementById('viewForm').submit();
}




		
</script>




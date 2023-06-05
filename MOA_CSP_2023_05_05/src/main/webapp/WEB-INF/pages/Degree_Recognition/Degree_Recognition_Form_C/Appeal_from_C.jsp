<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<link rel="stylesheet"href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<link rel="stylesheet" href="assets/vendor/internal_css.css">

<section class="search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Appeal Form C</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Appeal Form C</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<div class="custom_v_tab">
			<div class="row">
				<div class="col-lg-12">
					<div class="card-style mb-30">
						<div class="tabs content_h500">
							<div class="tab tablinks" id="tab_id1">
								<button class="tab-toggle" id="defaultOpen">
								Application For Appeal</button>
							</div>
							<div class="content tabcontent">
								<h4 class="heading">Application for appeal to grant of recognition to a medical qualification in Homoeopathy under section 35 of NCH Act, 2020 <br/>(for degree awarding universities within India)</h4>
								<div id="1" class="form-horizontal">
									<div class="inst_block mb-10">
										<h6 class="mb-2">Instruction</h6>
										<ul class="inst_list">
										<p class="inst_text">1. Application is to be submitted by degree awarding institute/University.
														 <br/> 2. In case of multiple institutes are affiliated to a single university, separate application for each institution is to be submitted.
 														 <br/>3. Application may be submitted either after issue of provisional certificate or award of degree or during internship of the first batch.</p>
										</ul>	
                            	   </div>
											
			<form:form name="Form_A_details" id="Form_A_details" action="form_c_appeal_action" method="post"
						       modelAttribute="APPEAL_Form_C_CMD" enctype="multipart/form-data">
						<div class="row">
							<div class="col-lg-4 col-md-6 col-sm-12">
								<div class="input-style-1">
									<label>Name Of The Aggrieved University<strong
											class="mandatory">*</strong></label>
											<div class="select-position">
												<select name="aggrieved_university_name" id="aggrieved_university_name" class="singleselect form-control form-control-lg" >
													<option value="0">---Select University---</option>
												<c:forEach var="item" items="${getMedUniversitynchName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
												</select>
											</div>
								</div>
							</div>
									
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>Address of the Aggrieved University
													<strong class="mandatory">*</strong></label> 
													<textarea name="aggrieved_university_address" id="aggrieved_university_address"
														class="form-control" placeholder="Enter University Address" 
														autocomplete="off"></textarea>
												</div>
											</div>
										
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>E-Mail ID of concerned  University Registrar<strong
															class="mandatory">*</strong></label>
														 	<input type="text" name="university_registrar_email_id" id="university_registrar_email_id"  class="form-control" 
															 placeholder="E-Mail ID of concerned  University Registrar" maxlength="30" 
															pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
															value="${email_txt}" autocomplete="off">
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12"> 
												<div class="input-style-1"> 
														<label>Contact Person Name<strong
														class="mandatory">*</strong></label>  
 														<input type="text" name="contact_person_name" 
														id="contact_person_name" class="form-control" 
 														placeholder="Enter Contact Person Name" 
 														autocomplete="off"> 
 												</div>
 											</div> 
 											
										<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
														<label>Contact Person Designation<strong
														class="mandatory">*</strong></label>
														<input type="text" name="contact_person_designation" 
														id="contact_person_designation" class="form-control" 
 														placeholder="Enter Contact Person Designation" 
 														autocomplete="off"> 
												</div>
										</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label>Contact Person Mobile No.
													<span class="mandatory">*</span></label> <input type="text" name="contact_person_mobile_no" maxlength="10" id="contact_person_mobile_no" autocomplete="off" placeholder="Enter Contact Person Mobile No.">
												</div>
										</div>
											
										<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>Contact Person E-Mail ID<strong
														class="mandatory">*</strong></label>
													 	<input type="text" name="contact_person_email_id" id="contact_person_email_id"  class="form-control" 
														 placeholder="Enter Contact PersonE-Mail ID" maxlength="30" 
														 pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
														 value="${email_txt}" autocomplete="off">
												</div>
										</div>
											
										<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label>Name Of The Institute<strong
													class="mandatory">*</strong></label>
														<div class="select-position">
															<select name="institute_name" id="institute_name" class="singleselect form-control form-control-lg" >
															<option value="0">---Select Institute---</option>
																<c:forEach var="item" items="${getMedInstitutenchName}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																</c:forEach>
															</select>
														</div>
												</div>
										</div>
											
										<div class="col-lg-4 col-md-6 col-sm-12"> 
												<div class="input-style-1"> 
 													<label>Nomenclature Of Degree<strong
														class="mandatory">*</strong></label> <input type="text" 
														name="nomenclature_of_degree"
 														id="nomenclature_of_degree" class="form-control" placeholder="Enter nomenclature_of_degree" > 
 												</div> 
 										</div> 
											
										<div class="col-lg-4 col-md-6 col-sm-12"> 
												<div class="input-style-1"> 
 													<label>Abbreviation of Degree(if any)<strong
														class="mandatory">*</strong></label> <input type="text" 
														name="abbreviation_of_degree"
 														id="abbreviation_of_degree" class="form-control" placeholder="Enter Abbreviation of Degree " > 
 												</div> 
 										</div> 
 											
 											
 										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Date of First Application to the Board<strong
													class="mandatory">*</strong></label> <input type="text"
													name="first_application_date"
													id="first_application_date" maxlength="10"
													class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>
										
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Date of denial of the Application<strong
													class="mandatory">*</strong></label> <input type="text"
													name="denial_application_date"
													id="denial_application_date" maxlength="10"
													class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>
 										<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>The reason of the denial of application
													<strong class="mandatory">*</strong></label> 
													<textarea name="reason" id="reason"
														class="form-control" 
														placeholder="Enter The reason of the denial of application"></textarea>
												</div>
										</div>
											
										<div class="col-lg-4 col-md-6 col-sm-12"> 
												<div class="input-style-1"> 
 													<label>Appeal / Prayer of the University  <strong
														class="mandatory">*</strong></label> <input type="text" 
														name="prayer_of_the_university"
 														id="prayer_of_the_university" class="form-control"   placeholder="Enter Prayer of the University" > 
 												</div> 
 										</div> 
											
										<div class="col-lg-4 col-md-6 col-sm-12"> 
												<div class="input-style-1"> 
 													<label>The justification of Appeal along with documents  <strong
														class="mandatory">*</strong></label> <input type="text" 
														name="document"
 														id="document" class="form-control"  placeholder="Enter The justification of documents"> 
 												</div> 
 										</div> 
			</div><!-- End row -->
			
											<ul class="buttons-group mainbtn">
												<li><a href="Degree_rec_Url"
													class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
												<li><input type="button" id="btn-save-appeal"
													class="main-btn info-btn btn-hover" value="Save As Draft">

												<li>
											</ul>
				</form:form>
		</div>
</div> <!--  End Form A -->

							<div class="tab tablinks dg-rec-block" id="tab_id5">
								<button class="tab-toggle">Declaration</button>
							</div>
						
							<div id="Form_Declaration" class="content tabcontent">
<%-- 								<form id="form_F_details"> --%>
									<h4 class="heading">Declaration/Enclosure</h4>
						<form:form name="Form_dec_details" id="Form_dec_details" action="appeal_declaration_action" method="post"
						       modelAttribute="Appeal_declarationCMD" enctype="multipart/form-data">			
									<div class="row">
										<input type="hidden" id="form_dec_id" name="form_dec_id" value="0"
											class="form-control autocomplete" autocomplete="off">
									
									<div class="col-12 col-lg-12 col-md-12 col-sm-12">
							<div class="card-style mb-30">
								<div class="row">
					<div class="col-lg-12">
							<h5 class="text-medium text-medium mb-10 mt-20">Declaration</h5>
							<div class="custom-choose-one">
									<div>
										<label class=""> It is certified that the details above furnished/enclosed are true to the best of my knowledge. I ensure that this university/awarding body is following, in Toto, the rules and the regulations specified by Central Government /National Commission for Homoeopathy/erstwhile Central Council for Homoeopathy specified for time to time and implementing the same in the Homoeopathy medical colleges/ Medical institution affiliated to this University/awarding body.</label>
									</div>
							</div>
							<h5 class="text-medium text-medium mb-10 mt-20">Enclosure</h5>
							<div class="custom-choose-one">
									<div>
										<label class="">1. Cancelled copy/specimen copy of degree in original awarded/to be awarded to the successful candidates.
   										 <br/>2. Copy of curriculum and syllabus followed by university for conducting course.</label>
									</div>
								
							</div>
					</div>
				</div>
							</div>
							<!-- end card -->
						</div>
 							
						<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label for="username">Upload Signature<span class="mandatory">*</span></label> 
												<div class="with_icon">
												<input type="file" accept="image/*"
												id="upload_signature" name="upload_signature" class="form-control"
												autocomplete="off" />
												</div>
										</div>
									</div>
						
						<div class="col-lg-6 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Date<strong
													class="mandatory">*</strong></label> <input type="text"
													name="declaration_date"
													id="declaration_date" maxlength="10"
													class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>

															
										<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
        										<li><a type="button" id="btn-save-declaration" class="main-btn info-btn btn-hover btnsave" >Final Submit</a>
											</ul>
										</div>
									
									</div></form:form>
								
							</div>
							
						
							
							</div> <!--  End Form C -->
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--   </body> -->
</section>

<script type="text/javascript"
	src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>
	<script nonce="${cspNonce}" type="text/javascript">
		$(document).ready(function() {
		$("#fillform").show();
    	$("#UploadExcel").hide();
		if (window.location.href.includes("msg")) 
		{
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}
	
		datepicketDate('first_application_date');
		datepicketDate('denial_application_date');
		  datepicketDate('declaration_date');
	});

	document.addEventListener('DOMContentLoaded', function () {	
		debugger;
		
	
		document.getElementById('university_registrar_email_id').onchange = function(){
  		   return checkemail(this.value);
       };
    
       document.getElementById('contact_person_email_id').onchange = function(){
  		   return conemailid(this.value);
       };
       document.getElementById('contact_person_mobile_no').onkeypress = function(){
       	return isNumberKey0to9(event, this);
       };
   
   	document.getElementById('btn-save-declaration').onclick = function(){
   		alert("---in btn")
		if(declaration_appeal_form()){
		$("#Form_dec_details").submit();
			}
	};	
	
		document.getElementById('btn-save-appeal').onclick = function(){
			if(Validation_form()){
				$("#Form_A_details").submit();
 			}
		};
   	
});

function checkemail(email) {

	document.getElementById("university_registrar_email_id").innerHTML = "";
	if (/@gmail.com\s*$/.test(email) || /@yahoo.com\s*$/.test(email)) {

	} else {
		alert("Please Enter Valid Email Address");
		$("input#university_registrar_email_id").focus();
		return false;
	}
}

function conemailid(email) {

	document.getElementById("contact_person_email_id").innerHTML = "";
	if (/@gmail.com\s*$/.test(email) || /@yahoo.com\s*$/.test(email)) {

	} else {
		alert("Please Enter Valid Email Address");
		$("input#contact_person_email_id").val('');
		return false;
	}
}
// 	document.getElementById('upload_sign').onchange = function() { 
// 	imgFileSizeValidation(this,this.value,"upload_sign","50kb","upload_file_sign");
// 	document.getElementById('upload_signature_doc_preview').src = window.URL.createObjectURL(this.files[0])
// };

function Validation_form() {

		if($("#aggrieved_university_name").val() == "0" || $("#aggrieved_university_name").val() == ""){
			alert("Please Select University Name.");
			$("#aggrieved_university_name").focus();
			return false;
		}
		if ($("textarea#aggrieved_university_address").val().trim() == "") {
			alert("Please Enter University Address.");
			$("textarea#aggrieved_university_address").focus();
			return false;
		}
		if ($("input#university_registrar_email_id").val().trim() == "") {
			alert("Please Enter Registrar Email.");
			$("input#university_registrar_email_id").focus();
			return false;
		}
		if ($("input#contact_person_name").val().trim() == "") {
			alert("Please Enter Contact Person Name.");
			$("input#contact_person_name").focus();
			return false;
		}
		if ($("#contact_person_designation").val().trim() == "") {
			alert("Please Enter Contact Person Designation.");
			$("input#contact_person_designation").focus();
			return false;
		}
		if ($("input#contact_person_mobile_no").val().trim() == "") {
			alert("Please Enter Contact Person Mobile No.");
			$("input#contact_person_mobile_no").focus();
			return false;
		}	
		if ($("input#contact_person_email_id").val().trim() == "") {
			alert("Please Enter Contact Person Email Id");
			$("input#contact_person_email_id").focus();
			return false;
		}	
		if($("#institute_name").val() == "0" || $("#institute_name").val() == ""){
			alert("Please Select Institute Name.");
			$("#institute_name").focus();
			return false;
		}
	
		if ($("input#nomenclature_of_degree").val().trim() == "") {
			alert("Please Enter Nomenclature of Degree.");
			$("input#nomenclature_of_degree").focus();
			return false;
		}
		if ($("input#abbreviation_of_degree").val().trim() == "") {
			alert("Please Enter Abbreviation of Degree.");
			$("input#abbreviation_of_degree").focus();
			return false;
		}
		if ($("input#first_application_date").val().trim() == "") {
			alert("Please Select First Application Date.");
			$("input#first_application_date").focus();
			return false;
		}
		if ($("input#denial_application_date").val().trim() == "") {
			alert("Please Select Denial Application Date.");
			$("input#denial_application_date").focus();
			return false;
		}
	
		if ($("textarea#reason").val().trim() == "") {
			alert("Please Enter  Reason of the Application.");
			$("textarea#reason").focus();
			return false;
		}
		if ($("input#prayer_of_the_university").val().trim() == "") {
			alert("Please Enter Prayer of the Application.");
			$("input#prayer_of_the_university").focus();
			return false;
		}
		if ($("input#document").val().trim() == "") {
				alert("Please Enter Document.");
				$("input#document").focus();
				return false;
			}
		return true;
	 }

function declaration_appeal_form() {
	 if ($("#upload_signature").val().trim() == "") {
			alert("Please Upload Signature");
			$("input#upload_signature").focus();
			return false;
		}
		else  if($("input#declaration_date").val().trim()=="DD/MM/YYYY"){
		alert("Please Enter Date Enclosure");
		$("input#declaration_date").focus();
		return false;
		}
return true;
}


	
</script>
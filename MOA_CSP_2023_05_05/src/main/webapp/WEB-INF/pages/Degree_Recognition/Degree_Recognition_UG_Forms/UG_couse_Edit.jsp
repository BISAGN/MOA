<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- <link rel="stylesheet" href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css"> -->
<!-- <script type="text/javascript" src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>	 -->
<link rel="stylesheet" href="assets/vendor/internal_css.css">
<section class="search_regulation Degree_Recognition">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2><span id="lbladd"></span>Degree Recognition Student</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Degree Recognition</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->
	<form:form name="edit_ug_id" id="edit_ug_id" action="edit_ug_Action" method="post" class="form-horizontal">
		<div class="custom_v_tab">
			<div class="row">
				<div class="col-lg-12">
					 
					<!-- input style start -->
					<div class="card-style mb-30">
					<span id="lbladd"></span>
						 <div class="tabs content_h500"> 
						 
							 	
							 <div class="content tabcontent" id="Form_B">
									<h4 class="heading">Undergraduate Courses in Homoeopathy In India</h4>
									<span class="mandatory" >(All fields are mandatory)</span>
									
									
									<div id="fillform" class="">
										<div class="row">
										
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Name Of Applicant University Granting
														Qualification<strong class="mandatory">*</strong>
													</label> <input type="text" name="name_of_applicant_university"
														id="name_of_applicant_university" class="form-control"
														placeholder="Enter Applicant University granting qualification"
														autocomplete="off" >
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Full Name Of undergraduate Course<strong
														class="mandatory">*</strong></label> <input type="text"
														name="name_ug_course" id="name_ug_course"
														class="form-control"  value="BHMS">
												</div>
											</div>
												<input type="hidden" id="eaid" name="eaid" value="0">
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Abbreviation of Undergraduate Course<strong
														class="mandatory">*</strong></label> <input type="text"
														name="abbre_undergraduate_course"
														id="abbre_undergraduate_course" class="form-control"
														 value="BHMS">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
																<div class="select-position">
																<label>Institute Name:<strong
														class="mandatory">*</strong></label>
																	<select name="institute_name" id="institute_name" class="form-control">
																		<option value="0">-Select-</option>
																		<c:forEach var="item" items="${getInstituteListbyUserID}"
					 														varStatus="num"> 
					 														<option value="${item.institute_name}"
					 															name="${item.institute_name.trim()}">${item.institute_name.trim()}</option> 
																		</c:forEach> 
																	</select>
																</div>
															</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
												<label>Academic Year Applied For</label>
													<input type="month" name="academic_year_applied_for"
														   id="academic_year_applied_for" maxlength="10" class="form-control"
															aria-required="true" autocomplete="off" value="YYYY"
															placeholder="Enter Year Of Admission">
												</div>
											</div>
<!-- 											<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label for="username">Academic File Upload<span class="mandatory">*</span></label>  -->
<!-- 												<div class="with_icon"> -->
<!-- 												<input type="file" accept=".pdf" -->
<!-- 												id="academic_file_upload" name="academic_file_upload" class="form-control" -->
<!-- 												autocomplete="off" /> -->
<!-- 												</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label for="username">Permission From Central Government<span class="mandatory">*</span></label>  -->
<!-- 												<div class="with_icon"> -->
<!-- 												<input type="file" accept=".pdf" -->
<!-- 												id="permission_from_central_gov" name="permission_from_central_gov" class="form-control" -->
<!-- 												autocomplete="off" /> -->
<!-- 												</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Admission Intake<strong class="mandatory">*</strong></label> 
														<div class="input-style-2">
															<input type="text"	name="admission_intake" id="admission_intake"
																	class="form-control" placeholder="Enter Admission Intake Capacity permitted"
																	autocomplete="off">
												</div>
											</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Number Of Student Admitted<strong class="mandatory">*</strong>
													</label> <input type="text" name="num_of_student_admitted" id="num_of_student_admitted" 
																 class="form-control" placeholder="Enter Number of Student Admitted"
																autocomplete="off" >
												</div>
											</div>
<!-- 											<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 												<div class="select-style-1"> -->
<!-- 													<label>Country<strong class="mandatory">*</strong></label> -->
<!-- 														<div class="input-style-2"> -->
<!-- 															<input type="text" name="country" -->
<!-- 																id="country" class="form-control" readonly="readonly" value="India"> -->
<!-- 														</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
													
<!-- 											<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 												<div class="input-style-2"> -->
<!-- 													<label>State<strong class="mandatory">*</strong></label>  -->
<!-- 															<select name="state_id" id="state_id" class="form-control"> -->
<!-- 																	<option value="0">---Select State---</option> -->
<%-- 																			<c:forEach var="item" items="${getMedStateName}" varStatus="num"> --%>
<%-- 																			<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 																			</c:forEach> --%>
<!-- 																			</select> -->
<!-- 												</div> -->
<!-- 											</div> -->
											
<!-- 											<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 												<div class="select-style-1"> -->
<!-- 													<label>District<strong class="mandatory">*</strong></label> -->
<!-- 													<div class="select-position"> -->
<!-- 														<select name="district_id" id="district_id" class="form-control"> -->
<!-- 														<option value="0">---Select District---</option> -->
<%-- 															<c:forEach var="item" items="${getMedDistrictName}" varStatus="num"> --%>
<%-- 																<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 															</c:forEach> --%>
<!-- 														</select> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 												<div class="input-style-2"> -->
<!-- 													<label>City<strong class="mandatory">*</strong></label> <input -->
<!-- 														type="text" name="city" id="city" class="form-control" -->
<!-- 														placeholder="Enter City" autocomplete="off"> -->
<!-- 												</div> -->
<!-- 											</div> -->

											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label>Postal Address<strong class="mandatory">*</strong></label>
													<textarea name="postal_address" id="postal_address"
														class="form-control" placeholder="Enter Postal Address" autocomplete="off"></textarea>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Email<strong class="mandatory">*</strong></label> <input
														name="email" id="email" class="email"
														placeholder="Enter email(abc@example.com)" maxlength="30"
														onchange="checkgmail(this.value)"
														pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
														value="${email_txt}" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Website<strong class="mandatory">*</strong></label>
													<input type="text" name="website" id="website"
														class="form-control" placeholder="Enter name Website(Url)" autocomplete="off">
												</div>
											</div>
<!-- 											<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 												<div class="input-style-2"> -->
<!-- 													<label>Permission From Central Government<strong class="mandatory">*</strong></label> -->
<!-- 													<input type="file" name="permission_from_central_gov" id="permission_from_central_gov"  -->
<!-- 															class="form-control" accept=".pdf" placeholder="Enter Permission from central government"> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 												<div class="input-style-2"> -->
<!-- 													<label>Application For Admitted Academic Session Document<strong class="mandatory">*</strong></label> -->
<!-- 													<input type="file" name="academic_file_upload" id="academic_file_upload"  -->
<!-- 															class="form-control" accept=".pdf" placeholder="Enter Permission from central government"> -->
<!-- 												</div> -->
<!-- 											</div> -->
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Remarks, if any<strong class="mandatory">*</strong></label>
													<input type="text" name="remarks" id="remarks"
														class="form-control" placeholder="Enter Remarks, if any" autocomplete="off">
												</div>
											</div>
											
											<input type="hidden" id="userId" name="userId" value="${userId}"> 
											<input type="hidden" id="eid" name="eid" value="0">
										</div>
									</div>
									
									<ul class="buttons-group mainbtn">
										<li><input type="submit" id="btn_update" class="main-btn info-btn btn-hover" value="Update" >
									</ul>
				
							</div>
							</div>
						</div>
					
				</div>
			</div>
		</div>
		</form:form>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">

 $(document).ready(function() {
	 
         if(window.location.href.includes("msg"))
         {
                  var url = window.location.href.split("?msg")[0];
                  window.location = url;
         }
         
         $("#eid").val('${ug_detail.id}');
//          alert(${ug_detail.id})
         $("#name_of_applicant_university").val('${getuni_dtl[0][0]}');
         $("#institute_name").val('${ug_detail.institute_name}');
         $("#academic_year_applied_for").val('${ug_detail.academic_year_applied_for}');
         $("#admission_intake").val('${ug_detail.admission_intake}');
         $("#num_of_student_admitted").val('${ug_detail.num_of_student_admitted}');
//          $("#country").val('${ug_detail.country}');
//          $("#state_id").val('${ug_detail.state_id}');
//          $("#district_id").val('${ug_detail.district_id}');
//          $("#city").val('${ug_detail.city}');
         $("#postal_address").val('${ug_detail.postal_address}');
         $("#email").val('${ug_detail.email}');
         $("#website").val('${ug_detail.website}');
         $("#remarks").val('${ug_detail.remarks}');
         $("#permission_from_central_gov").val('${ug_detail.permission_from_central_gov}');
         $("#academic_file_upload").val('${ug_detail.academic_file_upload}');
      
 });
        
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn_update').onclick = function(){
		alert("-------update---")
			return UpdateFn();
	};
	
});
function UpdateFn(){

	if($("input#name_of_applicant_university").val().trim() == "") {
	    alert("Please Enter Name Of Applicant University AND Allowed onlyAlphabets");
	    $("input#name_of_applicant_university").focus();
		return false;
	}
	if($("input#undergraduate_course").val().trim() == "") {
	    alert("Please Enter Undergraduate Course");
	    $("input#undergraduate_course").focus();
		return false;
	}
	if($("input#abbre_undergraduate_course").val().trim() == "") {
	    alert("Please Enter Abbreviation Undergraduate Course");
	    $("input#abbre_undergraduate_course").focus();
		return false;
	}
	if($("input#academic_session").val().trim() == "") {
	    alert("Please Enter Academic Session");
	    $("input#academic_session").focus();
		return false;
	}
	else if ($("#name_of_college").val() == "0") {
		alert("Select Name Of College");
		$("#name_of_college").focus();
		return false;
	}
	if ($("#country").val() == "0") {
		alert("Select Country Name ");
		$("#country").focus();
		return false;
	}
	if ($("#state").val() == "0") {
		alert("Select Sate Name");
		$("#state").focus();
		return false;
	}
	if ($("#district").val() == "0") {
		alert("Select District Name");
		$("#district").focus();
		return false;
	}
	if ($("input#city").val().trim() == "") {
		alert("Select City Name");
		$("input#city").focus();
		return false;
	}
    if($("textarea#postal_address").val().trim() == "") {
            alert("Please Enter Postal Address");
            $("textarea#postal_address").focus();
            return false;
    }
    if($("input#email").val().trim() == "") {
            alert("Please Enter Email");
            $("input#email").focus();
            return false;
    }
	if($("input#website").val().trim() == "") {
            alert("Please Enter Website");
            $("input#website").focus();
            return false;
    }
    if($("input#academic_year_applied_for").val().trim() == "") {
            alert("Please Enter Academic Year Applied For");
            $("input#academic_year_applied_for").focus();
            return false;
    }
//     if($("input#permission_from_central_gov").val().trim() == "") {
//             alert("Please Enter Permission from central government/court-order for specific batch");
//             $("input#permission_from_central_gov").focus();
//             return false;
//     }
    if($("input#admission_intake").val().trim() == "") {
            alert("Please Enter Admission Intake Capacity permitted");
            $("input#admission_intake").focus();
            return false;
    }
    if($("input#num_of_student_admitted").val().trim() == "") {
        alert("Please Enter number Of Student Admitted");
        $("input#num_of_student_admitted").focus();
        return false;
	   }                
    if($("input#remarks").val().trim() == "") {
            alert("Please Enter Remarks, if any");
            $("input#remarks").focus();
            return false;
    }
	return true;
}



</script>
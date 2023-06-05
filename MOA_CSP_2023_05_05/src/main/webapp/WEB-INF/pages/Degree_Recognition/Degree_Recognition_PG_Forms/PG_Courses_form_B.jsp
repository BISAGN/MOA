<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- <script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
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
<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/watermark/common.js"></script>

<section class="search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Degree Recognition</h2>
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
</div>
<div class="custom_v_tab">
				<div class="row">
					<div class="col-lg-12">
					
						<!-- input style start form A -->
						<div class="card-style mb-30">
							<div class="tabs content_h600">
								<div class="tab tablinks" id="tab_id1">
									<button class="tab-toggle" id="defaultOpen">Form B(For Postgraduate Course In Homoeopathy In India)</button>
								</div>
								<div class="content tabcontent">
								  	<h3 class="heading">For Postgraduate Course In Homoeopathy In India</h3> 
										<div id="1" class="form-horizontal">
											<form id="Form_B_detail">						
												<h6 class="mb-25">Degree Recognition</h6>
												<div class="row">
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Name Of the Applicant University granting qualification<strong class="mandatory">*</strong></label> 
															<input type="text" name="name_of_applicant_university" id="name_of_applicant_university" class="form-control"
																placeholder="Enter Applicant University granting qualification" autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Full name Of the postgraduate course<strong class="mandatory">*</strong></label> 
															<input type="text" name="postgraduate_course" id="postgraduate_course" class="form-control"
																placeholder="Enter Full name Of the postgraduate course"  autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Abbreviation of postgradute course<strong class="mandatory">*</strong></label> 
															<input type="text" name="abbre_postgraduate_course" id="abbre_postgraduate_course" class="form-control"
																placeholder="Enter Abbreviation of postgradute course"  autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Application for admitted academic session/batch<strong class="mandatory">*</strong></label> 
															<input type="text" name="academic_session" id="academic_session" class="form-control"
																placeholder="Enter Application for admitted academic session"  autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="select-style-1">
															<label>Name Of College/Center/Medical institute<strong class="mandatory">*</strong></label> 
														<div class="select-position">
																<select name="name_of_college" id="name_of_college" class="form-control">
													   					<option value="0">---SELECT---</option>
																				<c:forEach var="item" items="${getInstituteListbyUserID}" varStatus="num">
																					<option value="${item.institute_name}" name="${item.institute_name}">${item.institute_name}</option>
																					</c:forEach>
																		</select>
																		</div>
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>Country<strong class="mandatory">*</strong></label>
													<div class="select-position">
														<select name="country" id="country" class="form-control">
														<option value="0">---Select Country---</option>
															<c:forEach var="item" items="${getMedCountryName}" varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>State<strong class="mandatory">*</strong></label>
													<div class="select-position">
														<select name="state" id="state" class="form-control">
														<option value="0">---Select State---</option>
															<c:forEach var="item" items="${getMedStateName}" varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>District<strong class="mandatory">*</strong></label>
													<div class="select-position">
														<select name="district" id="district" class="form-control">
														<option value="0">---Select District---</option>
															<c:forEach var="item" items="${getMedDistrictName}" varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>City<strong class="mandatory">*</strong></label> <input
														type="text" name="city" id="city" class="form-control"
														placeholder="Enter City" autocomplete="off">
												</div>
											</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-1">
															<label>Postal Address<strong class="mandatory">*</strong></label>
															<textarea name="postal_address" id="postal_address"
																class="form-control" placeholder="Enter Postal Address"></textarea>
														</div>
													</div>
													
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
																<label>Email<strong class="mandatory">*</strong></label> <input
																	name="email_id" id="email_id" class="email"
																	placeholder="Enter Email(abc@example.com)" maxlength="30" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
																	value="${email_txt}" autocomplete="off">
																</div>
														</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Website<strong class="mandatory">*</strong></label> 
															<input type="text" name="website" id="website" class="form-control"
																placeholder="Enter name Website(Url)" autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Academic Year Applied for<strong
																class="mandatory">*</strong></label> 
																<input type="month" name="academic_year_applied_for"
													id="academic_year_applied_for" maxlength="10" class="form-control"
													aria-required="true" autocomplete="off" value="YYYY"
													placeholder="Enter Year Of Admission">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Permission from central government/court-order
																for specific batch(enclose the copy of same)<strong
																class="mandatory">*</strong>
															</label> <input type="file" name="permission_from_central_gov"
																id="permission_from_central_gov" class="form-control"
																placeholder="Enter Permission from central government" autocomplete="off">
														</div>
													</div>
<input type="hidden" id="form_a_id" name="form_a_id" value="0" class="form-control autocomplete" autocomplete="off">
													
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Admission intake Capacity permitted<strong
																class="mandatory">*</strong></label> <input type="text"
																name="admission_intake" id="admission_intake"
																class="form-control"
																placeholder="Enter Admission Intake Capacity permitted"  autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Number of Student Admitted for Said Academic year<strong
																class="mandatory">*</strong></label> <input type="text"
																name="num_of_student_admitted" id="num_of_student_admitted"
																class="form-control"
																placeholder="Enter Number of Student Admitted"  autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Remarks<strong class="mandatory">*</strong></label>
															<input type="text" name="remarks" id="remarks"
																class="form-control" placeholder="Enter Remarks, if any" autocomplete="off">
														</div>
													</div>
												<ul class="buttons-group mainbtn">
													<li><input type="button" id="btn-save-a" class="main-btn info-btn btn-hover" 
																		value="Save as Draft">
														<input type="hidden" id="userId" name="userId" value="${userId}"> 
														<input type="hidden" id="status" name="status" value="0">
													</li>
												 </ul>
											</div>						
						<!-- end card -->
											</form>
										</div>
								</div>
								
								<div class="tab tablinks dg-rec-block" id="tab_id5">
								<button class="tab-toggle">Enclosure</button>
							</div>
							<div id="Form_E" class="content tabcontent">
								<form:form name="form_E_details" id="form_E_details" action="enclosure_u_PG_Action" method="post"
						       modelAttribute="enclosure_u_CMD" enctype="multipart/form-data">
									<h4 class="heading">List of Enclosure</h4>
<!-- 									<div> -->
<!-- 										<span class="mandatory">(All fields are mandatory* )</span> -->
<!-- 									</div> -->
	<div class="row">
							<div class="custom-choose-one">
								<div class="input-style-form-check_block check-multi-list">
									<div class="form-check checkbox-style">
<!-- 			<input class="form-check-input" type="checkbox" id="checkbox1" name="checkbox1">  -->
									<label class="">i).Upload/ provide details of name of students of post graduate course specialty subject-wise, batch wise with PG entrance examination (whichever applicable)
											 score-if applicable, enrolled by the colleges concerned who have been examined by the University (till date). </label>
									<label class="">
											 ii).Provide details of number of beds earmarked for P.G. students in IPD in Homoeopathic  Hospitals of the College concerned be sent 
											 with month & year wise bed occupation by patients.</label> 
									<label class="">iii).Upload / provide a report on work done by PG students during house job/training in the department.</label>
									<label class="">iv).Copy of University Ordinance/Regulations (duly attested by Registrar of University) be sent for PG Courses.</label>
									<label class="">v).Copy of results declared of PG course in various specialty subjects (college wise, eligible batch wise).</label>
									<label class="">vi).Any other relevant information as & when required. </label>
									</div>
								</div>
							</div>

						
						<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label for="username">Upload Signature<span class="mandatory">*</span></label> 
												<div class="with_icon">
												<input type="file" accept="image/*"
												id="upload_file" name="upload_file" class="form-control"
												autocomplete="off" />
												</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Date<strong
													class="mandatory">*</strong></label> <input type="text"
													name="en_date"
													id="en_date" maxlength="10"
													class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>
									</div>
									</form:form>
										<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
												
	<li><a type="button" id="en-btn" class="main-btn info-btn btn-hover btnsave" >Final Submit</a>
													
											</ul>
										</div>
									</div>
							</div>						
			</div>

		</div>
</div>
</div>
</section>
<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>

<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() {

	if(window.location.href.includes("msg"))
	{
		 var url = window.location.href.split("?msg")[0];
		 window.location = url;
	}
 	datepicketDate('date_of_reg');
 	datepicketDate('date_of_birth');
 	datepicketDate('date_of_appointment');
 	datepicketDate('exp_from');
 	datepicketDate('exp_to');
	datepicketDate('date_of_examination');
	datepicketDate('en_date');

});
        
document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('en-btn').onclick = function(){
// 		alert("----1111")
		if(enclosure_validation()){
			alert("----save")
			$("#form_E_details").submit();
			}
	};
	
	document.getElementById('btn-save-a').onclick = function(){
		return Validation_A();
	};
// 	document.getElementById('btn-save-c').onclick = function(){
// 			return Validation_Form_C();
// 	};
// 	document.getElementById('btn-save-f').onclick = function(){
// 				return Validation_Form_F();	
// 	};
// 	document.getElementById('name_of_applicant_university').onkeypress = function(){
//    	 return onlyAlphabetsStringSpace(this,event);
//     };
//     document.getElementById('postgraduate_course').onkeypress = function(){
// 	   	 return onlyAlphabetsStringSpace(this,event);
// 	};
// 	document.getElementById('abbre_postgraduate_course').onkeypress = function(){
// 		   	 return onlyAlphabetsStringSpace(this,event);
// 	};
// 	document.getElementById('academic_session').onkeypress = function(){
// 			   	 return onlyAlphabetsStringSpace(this,event);
// 	};
	document.getElementById('email_id').onchange = function(){
		   return checkgmail(this.value);
    };
//     document.getElementById('email').onkeypress = function(){
//       	return noSpace(event, this);
//     };
//     document.getElementById('num_of_student_admitted').onkeypress = function(){
//       	return isNumberKey0to9(event, this);
//     };
//     document.getElementById('admission_intake').onkeypress = function(){
//         	return isNumberKey0to9(event, this);
//     };
//     document.getElementById('name_of_teaching_staff').onkeypress = function(){
//     	return onlyAlphabetsStringSpace(event, this);
// 	};
// 	document.getElementById('phone').onkeypress = function(){
//     	return isNumberKey0to9(event, this);
// 	};
// 	document.getElementById('email_id').onchange = function(){
// 		   return checkgmail(this.value);
//  	};
//  	document.getElementById('email_id').onkeypress = function(){
//    			return noSpace(event, this);
//  	};
//  	document.getElementById('designation').onkeypress = function(){
//     	return onlyAlphabetsStringSpace(event, this);
// 	};
// 	document.getElementById('department').onkeypress = function(){
//     	return onlyAlphabetsStringSpace(event, this);
// 	};
// 	document.getElementById('registration_no').onkeypress = function(){
//     	return isNumberKey0to9(event, this);
// 	};
// 	document.getElementById('qualification_awarding_authority').onkeypress = function(){
//     	return onlyAlphabetsStringSpace(event, this);
// 	};
// 	document.getElementById('post_teaching').onkeypress = function(){
//     	return onlyAlphabetsStringSpace(event, this);
// 	};
// 	document.getElementById('total_teaching_exp_in_year').onkeypress = function(){
//     	return isNumberKey0to9(event, this);
// 	};
// 	document.getElementById('subject').onkeypress = function(){
//     	return onlyAlphabetsStringSpace(event, this);
// 	};
// 	document.getElementById('name_of_examiners').onkeypress = function(){
//     	return onlyAlphabetsStringSpace(event, this);
// 	};
	
	
});
        //////////////////////// Form A Validation //////////////////////
        
        function Validation_A(){
                if($("input#name_of_applicant_university").val().trim() == "") {
                        alert("Please Enter Name Of Applicant University");
                        $("input#name_of_applicant_university").focus();
                        return false;
                }
                else if($("input#postgraduate_course").val().trim() == "") {
                        alert("Please Enter Postgraduate Course");
                        $("input#postgraduate_course").focus();
                        return false;
                }
                else if($("input#abbre_postgraduate_course").val().trim() == "") {
                        alert("Please Enter Abbreviation Of Postgraduate Course");
                        $("input#abbre_postgraduate_course").focus();
                        return false;
                }else if($("input#academic_session").val().trim() == "") {
                        alert("Please Enter Application Admitted Session");
                        $("input#academic_session").focus();
                        return false;
                }
                else if($("#name_of_college").val() == "0") {
                        alert("Please Select Name Of College");
                        $("#name_of_college").focus();
                        return false;
                }
                else if ($("#country").val() == "0") {
            		alert("Select Country Name ");
            		$("#country").focus();
            		return false;
            	}
            	else if ($("#state").val() == "0") {
            		alert("Select Sate Name");
            		$("#state").focus();
            		return false;
            	}
            	else if ($("#district").val() == "0") {
            		alert("Select District Name");
            		$("#district").focus();
            		return false;
            	}
            	else if ($("input#city").val().trim() == "") {
            		alert("Select City Name");
            		$("input#city").focus();
            		return false;
            	}
                else if($("textarea#postal_address").val().trim() == "") {
                        alert("Please Enter Postal Address");
                        $("textarea#postal_address").focus();
                        return false;
                }
                else if($("input#email").val().trim() == "") {
                    alert("Please Enter email");
                    $("input#email").focus();
                    return false;
                }
                else if($("input#website").val().trim() == "") {
                    alert("Please Enter Website");
                    $("input#website").focus();
                    return false;
           		 }
                else if($("input#academic_year_applied_for").val().trim() == "") {
                    alert("Please Enter Academic Year Applied For");
                    $("input#academic_year_applied_for").focus();
                    return false;
           		 }
           		 else if($("input#permission_from_central_gov").val().trim() == "") {
                     alert("Please Enter Permission From Central Government");
                     $("input#permission_from_central_gov").focus();
                     return false;
            	 }
            	 else if($("input#admission_intake").val().trim() == "") {
                     alert("Please Enter Admission Intake Capacity");
                     $("input#admission_intake").focus();
                     return false;
            	 }
            	 else if($("input#num_of_student_admitted").val().trim() == "") {
                     alert("Please Enter Number Of Student Admitted");
                     $("input#num_of_student_admitted").focus();
                     return false;
            	 }
                else if($("input#remarks").val().trim() == "") {
                        alert("Please Enter Remarks, if any");
                        $("input#remarks").focus();
                        return false;
                } 
                
                var formvalues = $("#Form_B_detail").serialize();
                var form_a_id = $('#form_a_id').val();
                
                 $.post('Degree_rec_action_B?' + key + "=" + value, formvalues, function(data) {
                       if(data.error == null) {
                              if(data.form_a_id != null) { 
                               $('#form_a_id').val(data.form_a_id);
                           alert(data.saved);
                           location.reload();
                              } else {
                              alert(data.updated);
                              }
                      }  
                      else {
                              alert(data.error)
                              
                      }
              }).fail(function(xhr, textStatus, errorThrown) {
                      alert("fail to fetch")
              });
       }
        

//  	//////////////////// Save Form C/////////////////
	 	
	        function Validation_Form_C(){
	        	
	        	if ($("#name_of_college_pg").val() == "0") {
	        		alert("Select Name Of College");
	        		$("#name_of_college_pg").focus();
	        		return false;
	        	}
		         else if($("input#name_of_teaching_staff").val().trim()=="")
		         {
		                alert("Please Enter Name Of Teaching Staff");
		                $("input#name_of_teaching_staff").focus();
		                return false;
		   	     }  
		   		 else if($("input#phone").val().trim() == "") 
		   		 {
		                alert("Please Enter Phone");
		                $("input#phone").focus();
		                return false;
	            }  
	            else if($("input#email_id").val().trim() == "") 
	            {
	                alert("Please Enter Email_id");
	                $("input#email_id").focus();
	                return false;
	            } 
	            else if($("input#designation").val().trim() == "") 
	            {
	                alert("Please Enter Designation");
	                $("input#designation").focus();
	                return false;
	            } 
	   		 
	    		else if($("input#department").val().trim() == "") 
	    		{
		             alert("Please Enter Department");
		             $("input#department").focus();
		             return false;
			 	}
		 
	    		else if($("input#registration_no").val().trim() == "") 
	    		{
		              alert("Please Enter Registration Number");
		              $("input#registration_no").focus();
		              return false;
		        }
		  
	    		else if($("input#date_of_reg").val().trim() == "DD/MM/YYYY") 
	    		{
	            	 alert("Please Select Date Of Registration");
	            	 $("input#date_of_reg").focus();
	            	 return false;
		       }
		 
	    		else if($("input#date_of_birth").val().trim() == "DD/MM/YYYY") 
	    		{
		              alert("Please Select Date Of Birth");
		              $("input#date_of_birth").focus();
		              return false;
		 		}
	    
	    		else if($("input#qualification_awarding_authority").val().trim() == "") 
	    		{
	                alert("Please Enter Qualification Awarding Authority");
	                $("input#qualification_awarding_authority").focus();
	                return false;
	    		} 
	    
	    		else if($("input#year_of_award").val().trim() == "") 
	    		{
		             alert("Please Enter year Of award");
		             $("input#year_of_award").focus();
		             return false;
				} 
	    		else if($("input#date_of_appointment").val().trim() == "DD/MM/YYYY") 
	    		{
		              alert("Please Select Date Of Appointment");
		              $("input#date_of_appointment").focus();
		              return false;
		 		}
	    		else if($('input[name=fulltime_parttime]:checked').length == 0)
	    		{
     				 alert("Please Select Full Time Or Part Time");
     				 $("input#fulltime_parttime").focus();
     				 return false;
	    		 }
	    		else if($("input#post_teaching").val().trim() == "") 
	    		{
	                alert("Please Enter Post");
	                $("input#post_teaching").focus();
	                return false;
	    		}
	    		else if($("input#exp_from").val().trim() == "DD/MM/YYYY") 
	    		{
	                alert("Please Enter Experience From");
	                $("input#exp_from").focus();
	                return false;
	    		}
	    		else if($("input#exp_to").val().trim() == "DD/MM/YYYY") 
	    		{
	                alert("Please Enter Experience To");
	                $("input#exp_to").focus();
	                return false;
	    		}
	    		else if($("input#total_teaching_exp_in_year").val().trim() == "") 
	    		{
	                alert("Please Enter Total Teaching Exp In Year");
	                $("input#total_teaching_exp_in_year").focus();
	                return false;
	    		}
	        	
		   		
		   	var form_c_id = $('#form_c_id').val();
		  	var formvalues = $("#form_C_details").serialize();
		   		
		  	$.post('form_c_pg?' + key + "=" + value, formvalues, function(data) {
				 if(data.error == null) {
					if(data.form_c_id != null) { 
		     		    $('#form_c_id').val(data.form_c_id);
		                     alert(data.saved);
		                     location.reload();
					} else {
					alert(data.updated);
					}
				}  
				else {
					alert(data.error)
				}
			}).fail(function(xhr, textStatus, errorThrown) {
				alert("fail to fetch")
			});
	  }


//	  	//////////////////// Save Form F/////////////////
		 	
	        function Validation_Form_F(){
	
// 	        	 if($("input#subject").val().trim()=="")
// 		         {
// 		                alert("Please Enter Subject");
// 		                $("input#subject").focus();
// 		                return false;
// 		   	     }  
// 	        	 else if ($("#name_of_examiners").val() == "0") {
// 	        		alert("Please Enter Name Of Examiners");
// 	        		$("#name_of_examiners").focus();
// 	        		return false;
// 	        	 }
// 	    		 else if($("input#date_of_examination").val().trim() == "DD/MM/YYYY") 
// 	    		 {
// 	            	 alert("Please Select Date Of Examination");
// 	            	 $("input#date_of_examination").focus();
// 	            	 return false;
// 		         }
		 
		   	var form_f_id = $('#form_f_id').val();
		  	var formvalues = $("#form_F_details").serialize();
		   		
		  	$.post('form_f_pg?' + key + "=" + value, formvalues, function(data) {
				 if(data.error == null) {
					if(data.form_f_id != null) { 
		     		    $('#form_f_id').val(data.form_f_id);
		                     alert(data.saved);
		                     location.reload();
					} else {
					alert(data.updated);
					}
				}  
				else {
					alert(data.error)
				}
			}).fail(function(xhr, textStatus, errorThrown) {
				alert("fail to fetch")
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
	          
 function enclosure_validation(){
	 	if($("input#upload_file").val().trim() == "") {
         alert("Please Upload File");
         $("input#upload_file").focus();
         return false;
		 }
	 	
	 	 else if($("input#en_date").val().trim()=="DD/MM/YYYY"){
             alert("Please Enter Date");
             $("input#en_date").focus();
             return false;
 	 }
	 	return true;
 }
 
</script>
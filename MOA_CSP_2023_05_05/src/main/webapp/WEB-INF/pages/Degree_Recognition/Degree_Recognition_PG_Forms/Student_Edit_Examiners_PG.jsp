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

<style>
.Degree_Recognition .hide-action .multi-btn-group {
   justify-content: center;
}
.Degree_Recognition .hide-action ul.buttons-group li{
    padding: 0 3px;
}
</style>
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
	<form:form name="edit_admitted_id" id="edit_admitted_id" action="edit_examinerstudent_pg_Action" method="post" class="form-horizontal">
		<div class="custom_v_tab">
			<div class="row">
				<div class="col-lg-12">
					 
					<!-- input style start -->
					<div class="card-style mb-30">
					<span id="lbladd"></span>
						 <div class="tabs content_h500"> 
							 <div class="content tabcontent" id="Form_B">
									<h4 class="heading">Details Of Guides And Examiners For
										M.D.(HOM) Course/Any Other Post Graduate Course</h4>
									<span class="mandatory">(All fields are mandatory)</span>
									<div id="fillform" class="">
										<div class="row">
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>Name Of Homoeopathic Medical College<strong
														class="mandatory">*</strong></label>
													<div class="select-position">
														<select name="name_of_homoeopathic_medical_college"
															id="name_of_homoeopathic_medical_college"
															class="form-control">
															<option value="0">---Select---</option>
															<c:forEach var="item" items="${getInstituteListbyUserID}"
																varStatus="num">
																<option value="${item.institute_name}"
																	name="${item.institute_name}">${item.institute_name}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Name Of Guide<strong
														class="mandatory">*</strong></label> <input type="text"
														name="name_of_guide" id="name_of_guide"
														class="form-control" placeholder="Name Of Guide/Co-Guide" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Name Of Students<strong
														class="mandatory">*</strong>
													</label> <input type="text" name="name_of_student_for_guide"
														id="name_of_student_for_guide" maxlength="10"
														class="form-control-sm form-control effect-9"
														placeholder="Enter Name Of Student For Guide" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Topic Of Dissertation<strong
														class="mandatory">*</strong>
													</label> <input type="text" name="topic_of_dissertation"
														id="topic_of_dissertation" class="form-control"
														placeholder="Enter Topic Of Dissertation" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Whether Guide Participated In Examination<strong class="mandatory">*</strong></label>
														<div class="form-check radio-style">
															<label class="form-check-label" for="radio-1">
															<input type="radio" id="whether_guide_participated_in_examination1" name="whether_guide_participated_in_examination" class="form-check-input" value="Yes">
																 Yes</label>
														</div>
														<div class="form-check radio-style">
															 <label class="form-check-label" for="radio-1">
															<input type="radio" name="whether_guide_participated_in_examination" id="whether_guide_participated_in_examination2" class="form-check-input" value="No">
																 No</label>
														</div>
													</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date Of Submission<strong class="mandatory">*</strong>
													</label> <input type="text" name="date_of_submission"
														id="date_of_submission" class="form-control"
														placeholder="Enter Date Of Submission"
														aria-required="true" autocomplete="off" value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date Of Acceptance<strong
														class="mandatory">*</strong>
													</label> <input type="text" name="date_of_acceptance"
														id="date_of_acceptance" class="form-control"
														placeholder="Enter Date Of Submission"
														aria-required="true" autocomplete="off" value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Whether Student published Article From Dissertation<strong class="mandatory">*</strong></label>
														<div class="form-check radio-style">
															<label class="form-check-label" for="radio-1">
															<input type="radio" id="whether_student_published_article1" name="whether_student_published_article" class="form-check-input" value="Yes">
																 Yes</label>
														</div>
														<div class="form-check radio-style">
															 <label class="form-check-label" for="radio-1">
															<input type="radio" name="whether_student_published_article" id="whether_student_published_article2" class="form-check-input" value="No">
																 No</label>
														</div>
													</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>If Yes,Mention The Details<strong class="mandatory">*</strong></label>
													<div class="form-check radio-style">
														<label class="form-check-label" for="radio-1">
															<input type="radio" id="mention_details1" name="mention_details" class="form-check-input" value="Yes" onclick="Supplementry();"> 
															Yes</label></div>
															<div class="form-check radio-style">
														<label class="form-check-label" for="radio-1">
															<input type="radio" id="mention_details2" name="mention_details" value="No" class="form-check-input" onclick="Supplementry();">
															No</label></div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Article Title<strong
														class="mandatory">*</strong></label> <input type="text"
														name="article_title" id="article_title" maxlength="10"
														class="form-control-sm form-control effect-9"
														aria-required="true" autocomplete="off">
												</div>
												</div>
												<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Month And Year<strong
														class="mandatory">*</strong></label> 
														<input type="month" name="month_year_exam" id="month_year_exam" autocomplete="off" class="form-control"
														placeholder="Enter Result" autocomplete="off">
												</div>
												</div>
											
											</div>
											
											<input type="hidden" id="userId" name="userId" value="${userId}"> 
											<input type="hidden" id="expg_id" name="expg_id" value="0">
										</div>
									</div>
									
									<ul class="buttons-group mainbtn">
										<li><input type="submit" id="btn_update" class="main-btn info-btn btn-hover" value="Update"  onclick="UpdateExaminersPg();">
									</ul>
				
							</div>
							</div>
						</div>
					
				</div>
			</div>
		</form:form>
	</div>
</section>

<script>

 $(document).ready(function() {
	 
         if(window.location.href.includes("msg"))
         {
                  var url = window.location.href.split("?msg")[0];
                  window.location = url;
         }
         datepicketDate('date_of_submission');
         datepicketDate('date_of_acceptance');
         datepicketDate('whether_examiner_approved_university');
         
         $("#name_of_homoeopathic_medical_college").val('${admitted_detail.name_of_homoeopathic_medical_college}');
         $("#expg_id").val('${admitted_detail.id}');
		 $("#name_of_guide").val('${admitted_detail.name_of_guide}');
		 $("#name_of_student_for_guide").val('${admitted_detail.name_of_student_for_guide}');
		 $("#topic_of_dissertation").val('${admitted_detail.topic_of_dissertation}'); 
		
		 if('${admitted_detail.whether_guide_participated_in_examination}' == "Yes"){
				$("#whether_guide_participated_in_examination1").prop("checked", true);
			    $("#whether_guide_participated_in_examination1").val('${admitted_detail.whether_guide_participated_in_examination}');
			}
			else if('${admitted_detail.whether_guide_participated_in_examination}' == "No"){
				$("#whether_guide_participated_in_examination2").prop("checked", true);
			    $("#whether_guide_participated_in_examination2").val('${admitted_detail.whether_guide_participated_in_examination}');
			}
		 var ds = DateFormateSet('${admitted_detail.date_of_submission}');
		 $("#date_of_submission").val(ds);
		 var da = DateFormateSet('${admitted_detail.date_of_acceptance}');
		 $("#date_of_acceptance").val(da);
		 
		 if('${admitted_detail.whether_student_published_article}' == "Yes"){
				$("#whether_student_published_article1").prop("checked", true);
			    $("#whether_student_published_article1").val('${admitted_detail.whether_student_published_article}');
			}
			else if('${admitted_detail.whether_student_published_article}' == "No"){
				$("#whether_student_published_article2").prop("checked", true);
			    $("#whether_student_published_article2").val('${admitted_detail.whether_student_published_article}');
			}
		 if('${admitted_detail.mention_details}' == "Yes"){
				$("#mention_details1").prop("checked", true);
			    $("#mention_details1").val('${admitted_detail.mention_details}');
			}
			else if('${admitted_detail.mention_details}' == "No"){
				$("#mention_details2").prop("checked", true);
			    $("#mention_details2").val('${admitted_detail.mention_details}');
			}
		 $("#article_title").val('${admitted_detail.article_title}'); 
		 $("#month_year_exam").val('${admitted_detail.month_year_exam}'); 
		
 });
        
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn_update').onclick = function(){
			return UpdateMigratedPg();
	};
	
});
function UpdateExaminersPg(){

// 	if($("input#student_name").val().trim() == "") {
//         alert("Please Enter Student Name");
//         $("input#student_name").focus();
//         return false;
// 	}
//  	if($("input#date_of_admission").val().trim()=="DD/MM/YYYY"){
//              alert("Please Enter Date Of Admission");
//              $("input#date_of_admission").focus();
//              return false;
//  	 }
//      if($("input#rank").val().trim() == "") {
//              alert("Please Enter Rank");
//              $("input#rank").focus();
//              return false;
//      }
//      if($("input#marks").val().trim() == "") {
//              alert("Please Enter Marks");
//              $("input#marks").focus();
//              return false;
// 	 }
//      if($('input[name=all_india]:checked').length == 0){
// 		 alert("Please Select All india (Yes or No)");
// 		 $("input#all_india").focus();
// 		 return false;
// 	 }
//      if($('input[name=state]:checked').length == 0){
//        	alert("Please Select State (Yes or No)");
//        	$("input#state").focus();
//        	return false;
//      }
//      if($('input[name=admission_authority]:checked').length == 0){
//        	alert("Please Select Admission Authority (Yes or No)");
//        	$("input#admission_authority").focus();
//        	return false;
//      }
//      if($("input#court_order").val().trim() == "") {
//         alert("Please Enter Court Order & Others");
//         $("input#court_order").focus();
//         return false;
//      }
//      if($("input#date_of_enroll_university").val().trim()=="DD/MM/YYYY"){
//       	 alert("Please Enter Date Of Enrollment In University");
//       	 $("input#date_of_enroll_university").focus();
//        	return false;
//      }
//      if($("input#uni_enroll_number").val().trim()==""){
//     		alert("Please Enter University Enrollment Number");
//    		 $("input#uni_enroll_number").focus();
//    		 return false;
//      }
//      if($("input#date_of_intern_compl").val().trim()=="DD/MM/YYYY"){
//    		alert("Please Enter Date Of Internship Completion");
//     		$("input#date_of_intern_compl").focus();
//     		return false;
//      }
//      if($("input#remarks_form_b").val().trim() == "") {
//               alert("Please Enter Remarks Form");
//          	 $("input#remarks_form_b").focus();
//          	 return false; 
// 	 } 
	return true;
}

function DateFormateSet(obj){
	
 var doa = obj.substring(0,10);
 var doaD = doa.substring(8,10);
 var doaM = doa.substring(5,7);
 var doaY = doa.substring(0,4);
 doa = doaD+"/"+doaM+"/"+doaY;
 return doa;
	
}


</script>
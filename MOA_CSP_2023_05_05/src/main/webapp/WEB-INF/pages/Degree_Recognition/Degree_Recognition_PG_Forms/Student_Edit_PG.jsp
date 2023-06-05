<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>



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
	<form:form name="edit_admitted_id" id="edit_admitted_id" action="edit_admittedstudent_pg_Action" method="post" class="form-horizontal">
		<div class="custom_v_tab">
			<div class="row">
				<div class="col-lg-12">
					 
					<!-- input style start -->
					<div class="card-style mb-30">
					<span id="lbladd"></span>
						 <div class="tabs content_h500"> 
						 
							 	
							 <div class="content tabcontent" id="Form_B">
									<h4 class="heading">Number Of Students Admitted As regular Candidate(Year And Subject Wise)</h4>
									<span class="mandatory" >(All fields are mandatory)</span>
									
									
									<div id="fillform" class="">
										<div class="row">
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Name Of the Student <strong
														class="mandatory">*</strong></label> <input type="text"
														name="student_name" id="student_name" class="form-control"
														placeholder="Enter name of Students">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date Of Admission <strong class="mandatory">*</strong>
													</label> <input type="text" name="date_of_admission"
														id="date_of_admission" class="form-control"
														aria-required="true" autocomplete="off" value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date of Registration<strong
														class="mandatory">*</strong>
													</label> <input type="text" name="date_of_registration"
														id="date_of_registration" class="form-control"
														aria-required="true" autocomplete="off" maxlength="10"
														value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label for="username">University For The Course<span
														class="mandatory">*</span></label>
													<div class="select-position">
														<select name="course_name" id="course_name">
															<option value="">Course Name</option>
															<c:forEach var="item" items="${course_name}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Rank<strong class="mandatory">*</strong></label> <input
														name="rank" id="rank" class="rank"
														placeholder="Enter Rank">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Marks<strong class="mandatory">*</strong></label> <input
														type="text" name="marks" id="marks" class="form-control"
														placeholder="Enter Marks">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>All India<strong class="mandatory">*</strong></label>
													<div class="form-check radio-style">
														<label class="form-check-label" for="radio-1"> <input
															type="radio"  name="all_india" id="all_india1"
															class="form-check-input" value="Yes"> Yes
														</label>
													</div>
													<div class="form-check radio-style">
														<label class="form-check-label" for="radio-1"> <input
															type="radio" name="all_india" id="all_india2"
															class="form-check-input" value="No"> No
														</label>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>State<strong class="mandatory">*</strong></label>
													<div class="form-check radio-style">
														<label class="form-check-label" for="radio-1"> <input
															type="radio" name="state" id="state1"
															class="form-check-input" value="Yes"> Yes
														</label>
													</div>
													<div class="form-check radio-style">
														<label class="form-check-label" for="radio-1"> <input
															type="radio" name="state" id="state2"
															class="form-check-input" value="No"> No
														</label>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Admission Authority<strong class="mandatory">*</strong></label>
													<div class="form-check radio-style">
														<label class="form-check-label" for="radio-1"> <input
															type="radio" name="admission_authority"
															id="admission_authority1" class="form-check-input"
															value="Yes"> Yes
														</label>
													</div>
													<div class="form-check radio-style">
														<label class="form-check-label" for="radio-1"> <input
															type="radio" name="admission_authority"
															id="admission_authority2" class="form-check-input"
															value="No"> No
														</label>
													</div>
												</div>
											</div>

											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Court Order & Others<strong
														class="mandatory">*</strong></label> <input type="text"
														name="court_order" id="court_order" class="form-control"
														placeholder="Enter Number of Court Order">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Qualification Name <strong class="mandatory">*</strong>
													</label> <input type="text" name="qualification_name"
														id="qualification_name" maxlength="10"
														class="form-control"
														placeholder="Enter Name Of Awarding Authority">

												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Year Of Award For Admission To M.D.(Hom.)<strong
														class="mandatory">*</strong></label> <input type="month"
														name="year_of_award_admission"
														id="year_of_award_admission" class="form-control"
														placeholder="Enter Year Of Award">

												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date of Completion Of Internship <strong
														class="mandatory">*</strong>
													</label> <input type="text" name="date_of_completion_internship"
														id="date_of_completion_internship" maxlength="10"
														class="form-control-sm form-control effect-9 "
														aria-required="true" autocomplete="off" value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date of Registration With State <strong
														class="mandatory">*</strong>
													</label> <input type="text" name="date_of_registration_state"
														id="date_of_registration_state" maxlength="10"
														class="form-control-sm form-control effect-9 "
														aria-required="true" autocomplete="off" value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date of Completion Of MD Part-1 <strong
														class="mandatory">*</strong>
													</label> <input type="text" name="date_of_completion_md_part1"
														id="date_of_completion_md_part1" maxlength="10"
														class="form-control-sm form-control effect-9 "
														aria-required="true" autocomplete="off" value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date of Completion Of MD Part-2 <strong
														class="mandatory">*</strong>
													</label> <input type="text" name="date_of_completion_md_part2"
														id="date_of_completion_md_part2" maxlength="10"
														class="form-control-sm form-control effect-9 "
														aria-required="true" autocomplete="off" value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date of Declaration Of MD(Hom.)Part-2 <strong
														class="mandatory">*</strong>
													</label> <input type="text" name="date_of_declaration_of_md"
														id="date_of_declaration_of_md" maxlength="10"
														class="form-control-sm form-control effect-9 "
														aria-required="true" autocomplete="off" value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Remarks, if any<strong></strong></label>
													<input type="text" name="remarks" id="remarks"
														class="form-control" placeholder="Enter Remarks, if any">
												</div>
											</div>
											<input type="hidden" id="userId" name="userId" value="${userId}"> 
											<input type="hidden" id="eid" name="eid" value="0">
										</div>
									</div>
									
									<ul class="buttons-group mainbtn">
										<li><input type="submit" id="btn_update" class="main-btn info-btn btn-hover" value="Update">
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
         datepicketDate('date_of_admission');
         datepicketDate('date_of_registration');
         datepicketDate('date_of_registration_state');
         datepicketDate('date_of_completion_md_part1');
         datepicketDate('date_of_completion_md_part2');
         datepicketDate('date_of_declaration_of_md');
         datepicketDate('date_of_enroll_university');
         datepicketDate('date_of_intern_compl');
         datepicketDate('date_of_result_final_year');
         datepicketDate('date_of_starting_internship');
         datepicketDate('date_of_completion_internship');
         datepicketDate('to_date1');
         datepicketDate('dt_of_migration1');
         
         $("#student_name").val('${admitted_detail.student_name}');
         
         $("#eid").val('${admitted_detail.id}');
         
         var fd = DateFormateSet('${admitted_detail.date_of_admission}');
		 $("#date_of_admission").val(fd);
		 var rd = DateFormateSet('${admitted_detail.date_of_registration}');
		 $("#date_of_registration").val(rd);
		 $("#course_name").val('${admitted_detail.course_name}');
		 $("#rank").val('${admitted_detail.rank}');
		 $("#marks").val('${admitted_detail.marks}');
		 
		 if('${admitted_detail.all_india}' == "Yes"){
				$("#all_india1").prop("checked", true);
			    $("#all_india1").val('${admitted_detail.all_india}');
			}
			else if('${admitted_detail.all_india}' == "No"){
				$("#all_india2").prop("checked", true);
			    $("#all_india2").val('${admitted_detail.all_india}');
			}
		 
		 if('${admitted_detail.state}' == "Yes"){
				$("#state1").prop("checked", true);
			    $("#state1").val('${admitted_detail.state}');
			}
			else if('${admitted_detail.state}' == "No"){
				$("#state2").prop("checked", true);
			    $("#state2").val('${admitted_detail.state}');
			}
		 
		 if('${admitted_detail.admission_authority}' == "Yes"){
				$("#admission_authority1").prop("checked", true);
			    $("#admission_authority1").val('${admitted_detail.admission_authority}');
			}
			else if('${admitted_detail.admission_authority}' == "No"){
				$("#admission_authority2").prop("checked", true);
			    $("#admission_authority2").val('${admitted_detail.admission_authority}');
			}
		 
		 $("#court_order").val('${admitted_detail.court_order}'); 
		 $("#qualification_name").val('${admitted_detail.qualification_name}'); 
		 $("#year_of_award_admission").val('${admitted_detail.year_of_award_admission}'); 
		 
		 var dci = DateFormateSet('${admitted_detail.date_of_completion_internship}');
		 $("#date_of_completion_internship").val(dci);
		 
		 var drs = DateFormateSet('${admitted_detail.date_of_registration_state}');
		 $("#date_of_registration_state").val(drs);
		 
		 var dcm = DateFormateSet('${admitted_detail.date_of_completion_md_part1}');
		 $("#date_of_completion_md_part1").val(dcm);
		 
		 var dmc = DateFormateSet('${admitted_detail.date_of_completion_md_part2}');
		 $("#date_of_completion_md_part2").val(dmc);
		 
		 var ddm = DateFormateSet('${admitted_detail.date_of_declaration_of_md}');
		 $("#date_of_declaration_of_md").val(ddm);
		 
		 $("#remarks").val('${admitted_detail.remarks}');
		
 });
        
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn_update').onclick = function(){
			return UpdateFnPg();
	};
	
});
function UpdateFnPg(){

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
// 	return true;
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
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
						<h2><span id="lbladd"></span>Admitted Student Edit Data</h2>
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
	<form:form name="edit_admitted_id" id="edit_admitted_id" action="edit_admittedstudent_Action" method="post" class="form-horizontal">
		<div class="custom_v_tab">
			<div class="row">
				<div class="col-lg-12">
					 
					<!-- input style start -->
					<div class="card-style mb-30">
					<span id="lbladd"></span>
						 <div class="tabs content_h500"> 
						 
							 	
							 <div class="content tabcontent" id="Form_B">
									<h4 class="heading">Admitted Students For Each
										College/Institute As Per Proforma Provide</h4>
									<span class="mandatory" >(All fields are mandatory)</span>
									
									
									<div id="fillform" class="">
										<div class="row">
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Name Of Student<strong class="mandatory">*</strong></label>
														  <input type="text" name="student_name" id="student_name" 
                									 		class="form-control" placeholder="Enter Student Name"  maxlength="25" autocomplete="off">
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date of Admission<strong class="mandatory">*</strong></label>
													<input type="text" name="date_of_admission" id="date_of_admission" maxlength="10"
														   class="form-control-sm form-control"
														     aria-required="true" autocomplete="off"
										 				   value="DD/MM/YYYY">
												</div>
											</div>
											
										<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Neet Rank<strong class="mandatory">*</strong></label>
													<div class="form-check radio-style">
														 <input type="radio" id="neet_rank1" name="neet_rank"
															class="form-check-input" value="Yes"> 
															<label class="form-check-label" for="radio-1">Yes</label>
													</div>
													<div class="form-check radio-style">
														 <input type="radio" name="neet_rank" id="neet_rank2"
															class="form-check-input" value="No"> <label class="form-check-label" for="radio-1">No</label>
													</div>
												</div>
											</div>
											
											<div class="custom-d-none" id="mention_div">
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Rank<strong class="mandatory">*</strong></label> 
													<input type="text" name="rank" id="rank" class="form-control" placeholder="Enter Rank" 
														           autocomplete="off" maxlength="5">
												</div>
											</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Marks<strong class="mandatory">*</strong></label> 
													<input type="text" name="marks" id="marks" class="form-control"
														placeholder="Enter Marks"
														           autocomplete="off" maxlength="5">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>All India Quota<strong class="mandatory">*</strong></label>
														<div class="form-check radio-style">
															<label class="form-check-label" for="radio-1">
															<input type="radio" id="all_india1" name="all_india" class="form-check-input" value="Yes">
																 Yes</label>
														</div>
														<div class="form-check radio-style">
															 <label class="form-check-label" for="radio-1">
															<input type="radio" name="all_india" id="all_india2" class="form-check-input" value="No">
																 No</label>
														</div>
													</div>
												 </div>
												
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>State Quota<strong class="mandatory">*</strong></label>
														<div class="form-check radio-style">
															<label class="form-check-label" for="radio-1">
															<input type="radio" name="state" id="state1" class="form-check-input" value="Yes">
																Yes</label>
														</div>
														<div class="form-check radio-style">
															<label class="form-check-label" for="radio-1">
															<input type="radio" name="state" id="state2" class="form-check-input" value="No">
																No</label>
														</div>
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Management Quota<strong class="mandatory">*</strong></label>
														<div class="form-check radio-style">
															<label class="form-check-label" for="radio-1">
															<input type="radio" name="management_quota" id="management_quota1" class="form-check-input" value="Yes">
																Yes</label>
														</div>
														<div class="form-check radio-style">
															<label class="form-check-label" for="radio-1">
															<input type="radio" name="management_quota" id="management_quota2" class="form-check-input" value="No">
																No</label>
														</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Admission Authority<strong class="mandatory">*</strong></label>
														<div class="form-check radio-style">
															<label class="form-check-label" for="radio-1">
													 		<input type="radio" name="admission_authority" id="admission_authority1" class="form-check-input"  value="Yes">
															Yes</label>
														</div>
														<div class="form-check radio-style">
															<label class="form-check-label" for="radio-1">
													 		<input type="radio" name="admission_authority" id="admission_authority2" class="form-check-input"  value="No">
															No</label>
														</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Court Order and Others<strong class="mandatory">*</strong></label>
														<div class="form-check radio-style">
															<label class="form-check-label" for="radio-1">
													 		<input type="radio" name="court_order" id="court_order1" class="form-check-input"  value="Yes"  onclick="Court();">
															Yes</label>
														</div>
														<div class="form-check radio-style">
															<label class="form-check-label" for="radio-1">
													 		<input type="radio" name="court_order" id="court_order2" class="form-check-input"  value="No"  onclick="Court();">
															No</label>
														</div>
												</div>
											</div>
											<div class="custom-d-none"  id="mention_div" >
												<div class="row">
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Upload File Court Order<strong class="mandatory">*</strong></label> 
															<input type="file" name="court_order_file" id="court_order_file" class="form-control"
																	placeholder="Enter Number of Court Order" accept=".pdf" autocomplete="off">
														</div>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date of Enrollment In University<strong
														class="mandatory">*</strong></label> 
														<input type="text" name="date_of_enroll_university" id="date_of_enroll_university" maxlength="10"
														   class="form-control-sm form-control effect-9" 
														   aria-required="true" autocomplete="off"
										 				   value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>University Enrollment Number<strong
														class="mandatory">*</strong></label> 
														<input type="text"
														name="uni_enroll_number" id="uni_enroll_number" 
														class="form-control-sm form-control effect-9"
														aria-required="true"  
																maxlength="14" autocomplete="off" placeholder="Enter University Number">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date Of Internship Completion<strong
														class="mandatory">*</strong></label> 
														<input type="text" name="date_of_intern_compl" id="date_of_intern_compl" maxlength="10"
														   class="form-control-sm form-control effect-9" 
														   aria-required="true" autocomplete="off"
										 				   value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Remarks,If Any</label>
													<textarea name="remarks_form_b"
														id="remarks_form_b" class="form-control"
														placeholder="Enter Remarks" 
														autocomplete="off"  maxlength="500"></textarea>
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
         datepicketDate('date_of_enroll_university');
         datepicketDate('date_of_intern_compl');
         
         $("#student_name").val('${admitted_detail.student_name}');
         
         $("#eid").val('${admitted_detail.id}');
         
         var fd = DateFormateSet('${admitted_detail.date_of_admission}');
		 $("#date_of_admission").val(fd);
		 
		 if('${admitted_detail.neet_rank}' == "Yes"){
				$("#neet_rank1").prop("checked", true);
			    $("#neet_rank1").val('${admitted_detail.neet_rank}');
			}
			else if('${admitted_detail.neet_rank}' == "No"){
				$("#neet_rank2").prop("checked", true);
			    $("#neet_rank2").val('${admitted_detail.neet_rank}');
			}
		 
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
		 if('${admitted_detail.management_quota}' == "Yes"){
				$("#management_quota1").prop("checked", true);
			    $("#management_quota1").val('${admitted_detail.management_quota}');
			}
			else if('${admitted_detail.management_quota}' == "No"){
				$("#management_quota2").prop("checked", true);
			    $("#management_quota2").val('${admitted_detail.management_quota}');
			}
		 if('${admitted_detail.admission_authority}' == "Yes"){
				$("#admission_authority1").prop("checked", true);
			    $("#admission_authority1").val('${admitted_detail.admission_authority}');
			}
			else if('${admitted_detail.admission_authority}' == "No"){
				$("#admission_authority2").prop("checked", true);
			    $("#admission_authority2").val('${admitted_detail.admission_authority}');
			}
		 
		 if('${admitted_detail.court_order}' == "Yes"){
				$("#court_order1").prop("checked", true);
			    $("#court_order1").val('${admitted_detail.court_order}');
			}
			else if('${admitted_detail.court_order}' == "No"){
				$("#court_order2").prop("checked", true);
			    $("#court_order2").val('${admitted_detail.court_order}');
			}
		 
		 //$("#date_of_enroll_university").val('${admitted_detail.date_of_enroll_university}');
		 
		 var deu = DateFormateSet('${admitted_detail.date_of_enroll_university}');
		 $("#date_of_enroll_university").val(deu);
		 
		 $("#uni_enroll_number").val('${admitted_detail.uni_enroll_number}');
		// $("#date_of_intern_compl").val('${admitted_detail.date_of_intern_compl}');
		 
		 var dic = DateFormateSet('${admitted_detail.date_of_intern_compl}');
		 $("#date_of_intern_compl").val(dic);
		 $("#remarks_form_b").val('${admitted_detail.remarks_form_b}');
		
 });
        
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn_update').onclick = function(){
			return UpdateFn();
	};
	document.getElementById('student_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('rank').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('marks').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('uni_enroll_number').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('remarks_form_b').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	document.getElementById('court_order1').onclick = function() {
		Court();
	};
	document.getElementById('court_order2').onclick = function() {
		Court();
	};
	document.getElementById('neet_rank1').onclick = function() {
		Neet_Rank();
	};
	document.getElementById('neet_rank2').onclick = function() {
		Neet_Rank();
	};
	document.getElementById('date_of_admission').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_admission').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('date_of_admission').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_admission').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_admission').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	document.getElementById('date_of_enroll_university').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_enroll_university').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('date_of_enroll_university').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_enroll_university').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_enroll_university').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	document.getElementById('date_of_intern_compl').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_intern_compl').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('date_of_intern_compl').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_intern_compl').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_intern_compl').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	
});
function Neet_Rank(){
	var neet_rank = $('input:radio[name=neet_rank]:checked').val();
	if(neet_rank == "Yes"){
        $("div#mention_div").show();
	}
	else if(neet_rank == "No"){
        $("div#mention_div").hide();
	}
}

function UpdateFn(){

	if($("input#student_name").val().trim() == "") {
        alert("Please Enter Student Name");
        $("input#student_name").focus();
        return false;
	}
 	if($("input#date_of_admission").val().trim()=="DD/MM/YYYY"){
             alert("Please Enter Date Of Admission");
             $("input#date_of_admission").focus();
             return false;
 	 }
     if($("input#rank").val().trim() == "") {
             alert("Please Enter Rank");
             $("input#rank").focus();
             return false;
     }
     if($("input#marks").val().trim() == "") {
             alert("Please Enter Marks");
             $("input#marks").focus();
             return false;
	 }
     if($('input[name=all_india]:checked').length == 0){
		 alert("Please Select All india (Yes or No)");
		 $("input#all_india").focus();
		 return false;
	 }
     if($('input[name=state]:checked').length == 0){
       	alert("Please Select State (Yes or No)");
       	$("input#state").focus();
       	return false;
     }
     if($('input[name=admission_authority]:checked').length == 0){
       	alert("Please Select Admission Authority (Yes or No)");
       	$("input#admission_authority").focus();
       	return false;
     }
     if($('input[name=court_order]:checked').length == 0){
    	  alert("Please Enter Court Order & Others (Yes Or No)");
        	$("input#court_order").focus();
        	return false;
      }
     if($("input#date_of_enroll_university").val().trim()=="DD/MM/YYYY"){
      	 alert("Please Enter Date Of Enrollment In University");
      	 $("input#date_of_enroll_university").focus();
       	return false;
     }
     if($("input#uni_enroll_number").val().trim()==""){
    		alert("Please Enter University Enrollment Number");
   		 $("input#uni_enroll_number").focus();
   		 return false;
     }
     if($("input#date_of_intern_compl").val().trim()=="DD/MM/YYYY"){
   		alert("Please Enter Date Of Internship Completion");
    		$("input#date_of_intern_compl").focus();
    		return false;
     }
     if($("input#remarks_form_b").val().trim() == "") {
              alert("Please Enter Remarks Form");
         	 $("input#remarks_form_b").focus();
         	 return false; 
	 } 
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
function Neet_Rank(){
	var neet_rank = $('input:radio[name=neet_rank]:checked').val();
	if(neet_rank == "Yes"){
        $("div#mention_div").show();
	}
	else if(neet_rank == "No"){
        $("div#mention_div").hide();
	}
}	


</script>
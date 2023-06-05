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
	<form:form name="edit_ug_exami_id" id="edit_ug_exami_id" action="edit_ug_examiners_Action" method="post" class="form-horizontal">
		<div class="custom_v_tab">
			<div class="row">
				<div class="col-lg-12">
					 
					<!-- input style start -->
					<div class="card-style mb-30">
					<span id="lbladd"></span>
						 <div class="tabs content_h500"> 
							 <div class="content tabcontent" id="Form_C">
									<h4 class="heading">Details Of Examiners Appointed In For Examination(Year Wise)</h4>
									<span class="mandatory" >(All fields are mandatory)</span>
									<div id="fillform" class="">
										<div class="row">
										
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
												<label>Name Of Examiners External/Internal<strong class="mandatory">*</strong></label> 
													<input type="text" name="name_of_examiners" id="name_of_examiners" class="form-control"
													placeholder="Enter name of Examiners External/Internal">
												</div>
											</div>
										    <div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Subject<strong class="mandatory">*</strong></label> <input
													type="text" name="subject_examiners" id="subject_examiners"
													class="form-control" placeholder="Enter Subject">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Year<strong class="mandatory">*</strong></label>
												 <input type="month" name="year_examiners" id="year_examiners" maxlength="10"
													class="form-control-sm form-control effect-9" 
													aria-required="true" autocomplete="off" value="YYYY">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Qualification<strong class="mandatory">*</strong></label>
												<input type="text" name="qualification_examiners" id="qualification_examiners"
													class="form-control" placeholder="Enter Qualification">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Teaching Experience<strong class="mandatory">*</strong></label>
												<input type="text" name="teaching_experience"
													id="teaching_experience" class="form-control"
													placeholder="Enter Teaching Experience">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Teachers Code(Mention The Number)<strong class="mandatory">*</strong></label>
												<input type="text" name="teacher_code" id="teacher_code" class="form-control"
													placeholder="Enter Teachers Code">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Registration Number<strong class="mandatory">*</strong></label>
												<input type="text" name="reg_number" id="reg_number" class="form-control"
													placeholder="Enter Registration Number">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Date Of University Appointment Letter<strong class="mandatory">*</strong></label>
												<input type="text" name="d_university_appointment" id="d_university_appointment"
													maxlength="10" class="form-control-sm form-control effect-9" 
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>
										
											<input type="hidden" id="userId" name="userId" value="${userId}"> 
											<input type="hidden" id="exid" name="exid" value="0">
										</div>
									</div>
									
									<ul class="buttons-group mainbtn">
										<li><input type="submit" id="btn_examiners_update" class="main-btn info-btn btn-hover" value="Update">
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
         datepicketDate('d_university_appointment');
         
         $("#exid").val('${ug_examiners_detail.id}');
         $("#name_of_examiners").val('${ug_examiners_detail.name_of_examiners}');
         $("#subject_examiners").val('${ug_examiners_detail.subject_examiners}');
         $("#year_examiners").val('${ug_examiners_detail.year_examiners}');
         $("#qualification_examiners").val('${ug_examiners_detail.qualification_examiners}');
         $("#teaching_experience").val('${ug_examiners_detail.teaching_experience}');
         $("#teacher_code").val('${ug_examiners_detail.teacher_code}');
         $("#reg_number").val('${ug_examiners_detail.reg_number}');
         var hdt = DateFormateSet('${ug_examiners_detail.d_university_appointment}');
		 $("#d_university_appointment").val(hdt);
		
 });
        
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn_examiners_update').onclick = function(){
			return UpdateExaminersFn();
	};
	document.getElementById('name_of_examiners').onkeypress = function(){
   	 return onlyAlphabetsStringSpace(this,event);
    };
    document.getElementById('subject_examiners').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('qualification_examiners').onkeypress = function(){
	   	 return onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('teaching_experience').onkeypress = function(){
    	return isNumberKey0to9(event, this);
    };
    document.getElementById('teacher_code').onkeypress = function(){
    	return isNumberKey0to9(event, this);
    };
    document.getElementById('reg_number').onkeypress = function(){
    	return isNumberKey0to9(event, this);
    };
    
});
function UpdateExaminersFn(){

	if($("input#name_of_examiners").val().trim() == "") {
	   	 alert("Please Enter Name Of Examiners");
	   	 $("input#name_of_examiners").focus();
	  	  return false;
		 	 }
	     if($("input#subject_examiners").val().trim() == "") {
	            alert("Please Enter Subject");
	            $("input#subject_examiners").focus();
	            return false;
	    }
	   if($("input#year_examiners").val().trim() == "") {
	            alert("Please Enter Year");
	            $("input#year_examiners").focus();
	            return false;
	    }
	     if($("input#qualification_examiners").val().trim() == "") {
	            alert("Please Enter Qualification");
	            $("input#qualification_examiners").focus();
	            return false;
	    }
	     if($("input#teaching_experience").val().trim() == "") {
	            alert("Please Enter Teaching Experience");
	            $("input#teaching_experience").focus();
	            return false;
	    }
	     if($("input#teacher_code").val().trim() == "") {
	        alert("Please Enter Teacher Code");
	        $("input#teacher_code").focus();
	        return false;
		 }
	     if($("input#reg_number").val().trim() == "") {
	        alert("Enter Registration Number");
	        $("input#reg_number").focus();
	        return false;
		 }
	    if($("input#d_university_appointment").val().trim() == "DD/MM/YYYY") {
	        alert("Enter Date Of University Appointment Letter");
	        $("input#d_university_appointment").focus();
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
</script>
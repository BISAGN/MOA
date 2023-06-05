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
	<form:form name="edit_intern_id" id="edit_intern_id" action="edit_internstudent_Action" method="post" class="form-horizontal">
		<div class="custom_v_tab">
			<div class="row">
				<div class="col-lg-12">
					 
					<div class="card-style mb-30">
					<span id="lbladd"></span>
						 <div class="tabs content_h500"> 
						 
							<div id="Form_F" class="content tabcontent">
							<h4 class="heading">Details Of Intern Students For Course</h4>
									<span class="mandatory">(All fields are mandatory)</span>
									
									<div class="row">
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Name Of Students<strong class="mandatory">*</strong></label>
												<input type="text" name="name_of_students"
													id="name_of_students" class="form-control"
													placeholder="Enter name of Students" autocomplete="off">
											</div>
										</div>
								
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Year Of Admission<strong class="mandatory">*</strong></label>
												<input type="month" name="year_of_admission"
													id="year_of_admission" maxlength="10" class="form-control"
													aria-required="true" autocomplete="off" value="YYYY"
													placeholder="Enter Year Of Admission">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Date Of Result Of Final Year BHMS/Course
													Completed</label> 
														<input type="text" name="date_of_result_final_year" id="date_of_result_final_year"
													maxlength="10" class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Provisional Registration No.<strong
													class="mandatory">*</strong></label>
												<input type="text"
													name="provisional_reg_no" id="provisional_reg_no"
													class="form-control"
													placeholder="Enter Provisional Registration No" autocomplete="off" maxlength="15">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Year Of Provisional Registration(From State Board)<strong
													class="mandatory">*</strong></label> 
													<input type="month" name="year_of_provisional_reg" id="year_of_provisional_reg" maxlength="10"
													class="form-control" aria-required="true"
													autocomplete="off" placeholder="Enter Year Of Provisional Registration" value="YYYY">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Date Of Starting Of Internship<strong
													class="mandatory">*</strong></label>
												<input type="text" name="date_of_starting_internship" id="date_of_starting_internship"
													maxlength="10" class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Date Of Completion Of Internship<strong
													class="mandatory">*</strong></label> 
												<input type="text" name="date_of_completion_internship" id="date_of_completion_internship"
													maxlength="10" class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Remarks If Any</label>
												<textarea type="text" name="remark_form_f" id="remark_form_f"
													class="form-control" autocomplete="off" placeholder="Enter Reamark" maxlength="500"></textarea>
											</div>
										</div>
										<input type="hidden" id="userId" name="userId" value="${userId}"> 
										<input type="hidden" id="insid" name="insid" value="0">
											
											<ul class="buttons-group mainbtn">
												<li><input type="submit" id="btn-update" class="main-btn info-btn btn-hover" value="Update" onclick="update_f();">
											</ul>
									</div><!-- end row -->
								</div><!-- end form f -->
							</div>
						</div><!-- end card style -->
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
         datepicketDate('date_of_result_final_year');
         datepicketDate('date_of_starting_internship');
         datepicketDate('date_of_completion_internship');
         
         $("#name_of_students").val('${intern_detail.name_of_students}');
         $("#insid").val('${intern_detail.id}');
         $("#year_of_admission").val('${intern_detail.year_of_admission}');
         
         var fd = DateFormateSet('${intern_detail.date_of_result_final_year}');
		 $("#date_of_result_final_year").val(fd);
		 
         $("#provisional_reg_no").val('${intern_detail.provisional_reg_no}');
         $("#year_of_provisional_reg").val('${intern_detail.year_of_provisional_reg}');
         
         var ds = DateFormateSet('${intern_detail.date_of_starting_internship}');
		 $("#date_of_starting_internship").val(ds);
		 
		 var idc = DateFormateSet('${intern_detail.date_of_completion_internship}');
		 $("#date_of_completion_internship").val(idc);
         
         $("#remark_form_f").val('${intern_detail.remark_form_f}');
		
 });
        
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn-update').onclick = function(){
			return update_f();
	};
	
	document.getElementById('name_of_students').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	
	document.getElementById('remark_form_f').onkeypress = function() {
		return onlyAlphabetsStringSpace(event, this);
	};
	
	document.getElementById('provisional_reg_no').onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('date_of_result_final_year').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_result_final_year').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('date_of_result_final_year').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_result_final_year').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_result_final_year').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	
	document.getElementById('date_of_starting_internship').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_starting_internship').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('date_of_starting_internship').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_starting_internship').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_starting_internship').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	document.getElementById('date_of_completion_internship').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_completion_internship').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('date_of_completion_internship').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('date_of_completion_internship').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('date_of_completion_internship').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
});
function update_f(){
	
	if($("input#name_of_students").val().trim() == "") {
	   	 alert("Please Enter Name Of Students");
	   	 $("input#name_of_students").focus();
	  	     return false;
		    }
	   if($("input#year_of_admission").val().trim() == "") {
	          alert("Please Enter Year Of Admission");
	          $("input#year_of_admission").focus();
	          return false;
	   }
	 if($("input#date_of_result_final_year").val() == "DD/MM/YYYY") {
	       alert("Please Enter Date Of Result Final Year");
	       $("input#date_of_result_final_year").focus();
	       return false;
	  }
	 if($("input#provisional_reg_no").val().trim() == "") {
	          alert("Please Enter Provisional Registration Number");
	          $("input#provisional_reg_no").focus();
	          return false;
	   }
	   if($("input#year_of_provisional_reg").val().trim() == "") {
	      alert("Please Enter Year Of Provisional Registration");
	      $("input#year_of_provisional_reg").focus();
	      return false;
	   }
	    if($("input#date_of_starting_internship").val().trim() == "DD/MM/YYYY") {
	        alert("Please Enter Date Of Starting Internship");
	        $("input#date_of_starting_internship").focus();
	        return false;
	   }	
	 if($("input#date_of_completion_internship").val().trim() == "DD/MM/YYYY") {
	        alert("Please Enter Date Of Completion Internship");
	        $("input#date_of_completion_internship").focus();
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
 
// DateFormateSet('${admitted_detail.date_of_admission}');
 
 return doa;
	
}


</script>
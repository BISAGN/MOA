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
	<form:form name="edit_pg_teaching_id" id="edit_pg_teaching_id" action="edit_pg_teaching_Action" method="post" class="form-horizontal">
		<div class="custom_v_tab">
			<div class="row">
				<div class="col-lg-12">
					 
					<!-- input style start -->
					<div class="card-style mb-30">
					<span id="lbladd"></span>
						 <div class="tabs content_h500"> 
						 
							 	
							 <div class="content tabcontent" id="Form_C">
									<h4 class="heading">College Wise/Institute Wise Regarding The Hospital Attached</h4>
									<span class="mandatory" >(All fields are mandatory)</span>
									
									
									<div id="fillform" class="">
										<div class="row">
											
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="select-style-1">
													<label>College/Center/Medical institute<strong class="mandatory">*</strong></label>
													<div class="select-position">
														<select name="name_of_college_pg" id="name_of_college_pg" class="form-control" >
															<option value="0">---Select College---</option>
															<c:forEach var="item" items="${getInstituteListbyUserID}"
																varStatus="num">
																<option value="${item.institute_name}" name="${item.institute_name}">${item.institute_name}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
													
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Name Of Teaching Staff<strong class="mandatory">*</strong>
													</label> <input type="text" name="name_of_teaching_staff"
														id="name_of_teaching_staff" class="form-control"
														placeholder="Enter name of Teaching Staff" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2"> 
													<label>Phone<strong class="mandatory">*</strong> 
													</label>  
													<input type="text" name="phone"	id="phone" class="form-control" 
 														placeholder="Enter Phone" autocomplete="off"> 
												</div> 
											</div> 
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Email ID<strong class="mandatory">*</strong></label>
													<input type="text" name="email_id" id="email_id" class="form-control"
														placeholder="Enter Email" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Designation<strong class="mandatory">*</strong></label>
													<input name="designation"
														id="designation" class="form-control"
														placeholder="Enter Designation" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12"> 
												<div class="input-style-2">
													<label>Department<strong class="mandatory">*</strong></label>
													<input name="department"
														id="department" class="form-control"
														placeholder="Enter Department" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Registration No.<strong class="mandatory">*</strong></label>
													<input name="registration_no" id="registration_no"
														class="form-control" placeholder="Enter Registration No." autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date Of Registration<strong class="mandatory">*</strong></label>
													<input name="date_of_reg" id="date_of_reg"
														class="form-control" placeholder="Enter Date Of Registration" aria-required="true" autocomplete="off"
																value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date Of Birthday<strong class="mandatory">*</strong></label>
													<input name="date_of_birth" id="date_of_birth"
														class="form-control" placeholder="Enter Date Of Birthday" aria-required="true" autocomplete="off"
																value="DD/MM/YYYY">
												</div> 
											</div> 
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Qualification Awarding Authority <strong class="mandatory">*</strong></label> 
													<input type="text" name="qualification_awarding_authority" id="qualification_awarding_authority" maxlength="10"
															class="form-control" placeholder="Enter Qualification Awarding Authority" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Year Of Award<strong class="mandatory">*</strong></label> 
													<input type="month" name="year_of_award" id="year_of_award" class="form-control"
															placeholder="Enter Year Of Award" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Date Of Appointment<strong class="mandatory">*</strong></label> 
													<input type="text" name="date_of_appointment" id="date_of_appointment" class="form-control"
														   placeholder="Enter Date Of Appointment" aria-required="true" autocomplete="off"
															value="DD/MM/YYYY">
												</div>
											</div>
											
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-form-check">
													<label>Full Time/Part Time<strong class="mandatory">*</strong></label>
														<div class="form-check radio-style">
															<input type="radio" id="fulltime_parttime1" name="fulltime_parttime" class="form-check-input" value="Yes">
															<label class="form-check-label" for="radio-1">Full Time</label>
														</div>
														<div class="form-check radio-style">
															 <input type="radio" id="fulltime_parttime2" name="fulltime_parttime" class="form-check-input" value="No">
															<label class="form-check-label" for="radio-1">Part Time</label>
														</div>
													</div>
												 </div>
												 
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>post<strong class="mandatory">*</strong></label>
													<input name="post_teaching" id="post_teaching" class="form-control"
														placeholder="Enter Post" autocomplete="off">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Experience From<strong class="mandatory">*</strong></label>
													 <input type="text" name="exp_from" id="exp_from" class="form-control"
															placeholder="Enter Date Of Appointment" aria-required="true" autocomplete="off"
															value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Experience To<strong class="mandatory">*</strong></label>
													 <input type="text" name="exp_to" id="exp_to" class="form-control"
															placeholder="Enter Date Of Appointment" aria-required="true" autocomplete="off"
															value="DD/MM/YYYY">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12">
												<div class="input-style-2">
													<label>Total Teaching Experience In Years<strong class="mandatory">*</strong></label> 
													<input type="text" name="total_teaching_exp_in_year" id="total_teaching_exp_in_year" class="form-control"
															placeholder="Enter Total Teaching Experience In Years" autocomplete="off">
												</div>
											</div>
										</div>	
											
											<input type="hidden" id="userId" name="userId" value="${userId}"> 
											<input type="hidden" id="hid" name="hid" value="0">
										</div>
									</div>
									
									<ul class="buttons-group mainbtn">
										<li><input type="submit" id="btn_teaching_update" class="main-btn info-btn btn-hover" value="Update" >
									</ul>
				
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
         datepicketDate('date_of_reg');
         datepicketDate('date_of_birth');
         datepicketDate('date_of_appointment');
         datepicketDate('exp_from');
         datepicketDate('exp_to');

         $("#hid").val('${pg_teaching_detail.id}');
         $("#name_of_college_pg").val('${pg_teaching_detail.name_of_college_pg}');
         $("#name_of_teaching_staff").val('${pg_teaching_detail.name_of_teaching_staff}');
         $("#phone").val('${pg_teaching_detail.phone}');
         $("#email_id").val('${pg_teaching_detail.email_id}');
         $("#designation").val('${pg_teaching_detail.designation}');
         $("#department").val('${pg_teaching_detail.department}');
         $("#registration_no").val('${pg_teaching_detail.registration_no}');
         var hdt = DateFormateSet('${pg_teaching_detail.date_of_reg}');
		 $("#date_of_reg").val(hdt);
		 var bdt = DateFormateSet('${pg_teaching_detail.date_of_birth}');
		 $("#date_of_birth").val(bdt);
         $("#qualification_awarding_authority").val('${pg_teaching_detail.qualification_awarding_authority}');
         $("#year_of_award").val('${pg_teaching_detail.year_of_award}');
         var adt = DateFormateSet('${pg_teaching_detail.date_of_appointment}');
		 $("#date_of_appointment").val(adt);

           if('${pg_teaching_detail.fulltime_parttime}' == "Yes"){
				$("#fulltime_parttime1").prop("checked", true);
			    $("#fulltime_parttime1").val('${pg_teaching_detail.fulltime_parttime}');
			}
			else if('${pg_teaching_detail.fulltime_parttime}' == "No"){
				$("#fulltime_parttime2").prop("checked", true);
			    $("#fulltime_parttime2").val('${pg_teaching_detail.fulltime_parttime}');
			}
           
         $("#post_teaching").val('${pg_teaching_detail.post_teaching}');
         var fdt = DateFormateSet('${pg_teaching_detail.exp_from}');
		 $("#exp_from").val(fdt);
		 var tdt = DateFormateSet('${pg_teaching_detail.exp_to}');
		 $("#exp_to").val(tdt);
         $("#total_teaching_exp_in_year").val('${pg_teaching_detail.total_teaching_exp_in_year}');
      
		
 });
        
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn_teaching_update').onclick = function(){
			return UpdateTeachingFn();
	};
	
});
function UpdateTeachingFn(){

	if ($("#name_of_college_pg").val() == "0") {
		alert("Select Name Of College");
		$("#name_of_college_pg").focus();
		return false;
	}
    if($("input#name_of_teaching_staff").val().trim()=="")
    {
            alert("Please Enter Name Of Teaching Staff");
            $("input#name_of_teaching_staff").focus();
            return false;
	}  
	if($("input#phone").val().trim() == "") 
	{
            alert("Please Enter Phone");
            $("input#phone").focus();
            return false;
    }  
    if($("input#email_id").val().trim() == "") 
    {
        alert("Please Enter Email_id");
        $("input#email_id").focus();
        return false;
    } 
    if($("input#designation").val().trim() == "") 
    {
        alert("Please Enter Designation");
        $("input#designation").focus();
        return false;
    } 
	if($("input#department").val().trim() == "") 
	{
         alert("Please Enter Department");
         $("input#department").focus();
         return false;
 	}
	if($("input#registration_no").val().trim() == "") 
	{
          alert("Please Enter Registration Number");
          $("input#registration_no").focus();
          return false;
    }
	if($("input#date_of_reg").val().trim() == "DD/MM/YYYY") 
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
	if($("input#qualification_awarding_authority").val().trim() == "") 
	{
        alert("Please Enter Qualification Awarding Authority");
        $("input#qualification_awarding_authority").focus();
        return false;
	} 
	if($("input#year_of_award").val().trim() == "") 
	{
         alert("Please Enter year Of award");
         $("input#year_of_award").focus();
         return false;
	} 
	if($("input#date_of_appointment").val().trim() == "DD/MM/YYYY") 
	{
          alert("Please Select Date Of Appointment");
          $("input#date_of_appointment").focus();
          return false;
		}
	if($('input[name=fulltime_parttime]:checked').length == 0)
	{
			 alert("Please Select Full Time Or Part Time");
			 $("input#fulltime_parttime").focus();
			 return false;
	 }
	if($("input#post_teaching").val().trim() == "") 
	{
        alert("Please Enter Post");
        $("input#post_teaching").focus();
        return false;
	}
	if($("input#exp_from").val().trim() == "DD/MM/YYYY") 
	{
        alert("Please Enter Experience From");
        $("input#exp_from").focus();
        return false;
	}
	if($("input#exp_to").val().trim() == "DD/MM/YYYY") 
	{
        alert("Please Enter Experience To");
        $("input#exp_to").focus();
        return false;
	}
	if($("input#total_teaching_exp_in_year").val().trim() == "") 
	{
        alert("Please Enter Total Teaching Exp In Year");
        $("input#total_teaching_exp_in_year").focus();
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
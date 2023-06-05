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
						<h2><span id="lbladd"></span>Hospital Attached Edit Data</h2>
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
	<form:form name="edit_ug_hos_id" id="edit_ug_hos_id" action="edit_ug_hospital_Action" method="post" class="form-horizontal">
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
												 <input type="hidden" id="id_c" name="id_c" > 
												<label>Name Of Homoeopathic Medical
													College/Institute<strong class="mandatory">*</strong>
												</label> 
												<div class="select-position">
											<select name="name_homoeopathic_medical_clg" id="name_homoeopathic_medical_clg"
														class="form-control">
														<option value="0">---SELECT Name Of Homoeopathic Medical College---</option>
														<c:forEach var="item" items="${getInstituteListbyUserID}"
															varStatus="num">
															<option value="${item.institute_name}" name="${item.institute_name}">${item.institute_name}</option>
														</c:forEach>
													</select>
											</div>
											</div>
										</div>
										
<!-- 										<input type="" id="id_c" name="id_c" value=""> -->
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Name Of Attached Homoeopathic Hospital/Institute<strong class="mandatory">*</strong>
												</label> 
												<input type="text" name="attached_homoeopath_hospital"
													id="attached_homoeopath_hospital" class="form-control"
													placeholder="Enter name of Attached Homoeopathic Hospital" autocomplete="off" maxlength="100">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Name Of Attached Super Speciality Hospital<strong
													class="mandatory">*</strong>
												</label> 
												<input name="super_speciality_hospital"
													id="super_speciality_hospital" class="form-control"
													placeholder="Enter Name of Attached Super Speciality Hospital"  autocomplete="off" maxlength="100">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>MOU Date<strong class="mandatory">*</strong></label>
												<input type="text" name="mou_date" id="mou_date"
													maxlength="10"
													class="form-control-sm form-control effect-9 "
													aria-required="true" autocomplete="off" value="DD/MM/YYYY">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Copy Of MOU Date<strong class="mandatory">*</strong></label>
												<input type="file" name="copy_of_mou" id="copy_of_mou"
													class="form-control" accept=".pdf">
											</div>
										</div>
										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Name Of Hospital Staff<strong class="mandatory">*</strong></label>
												<input name="name_of_hospital_staff"
													id="name_of_hospital_staff" class="form-control"
													placeholder="Enter Name of Hospital Staff"  autocomplete="off" maxlength="50">
											</div>
										</div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="select-style-1">
												<label>Designation<strong class="mandatory">*</strong>
												</label>
												<div class="select-position">
												<select name="designation" id="designation" class="form-control valid"
													aria-invalid="false">
													<option value="0" selected="selected">-- Select
														Designation --</option>
													<c:forEach var="item" items="${getDesignationList}"
														varStatus="num">
														<option value="${item.designation}" name="${item.designation}">${item.designation}</option>
													</c:forEach>
												</select>
											</div>
										</div></div>

										<div class="col-lg-4 col-md-6 col-sm-12">
											<div class="input-style-2">
												<label>Qualification<strong class="mandatory">*</strong></label>
												<input name="qualification" id="qualification"
													class="form-control" placeholder="Enter Qualification" autocomplete="off" maxlength="50">
											</div>
										</div>
							<input type="hidden" id="form_c_id" name="form_c_id" value="0">

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
												<label>Remarks<strong class="mandatory">*</strong></label> 
												<textarea type="text" name="remarks_form_c" id="remarks_form_c"
													class="form-control" placeholder="Enter Remarks" autocomplete="off" maxlength="500"></textarea>
											</div>
										</div>
											
											<input type="hidden" id="userId" name="userId" value="${userId}"> 
											<input type="hidden" id="hid" name="hid" value="0">
										</div>
									</div>
									
									<ul class="buttons-group mainbtn">
										<li><input type="submit" id="btn_hospital_update" class="main-btn info-btn btn-hover" value="Update">
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
         datepicketDate('mou_date');
         
         $("#hid").val('${ug_hospital_detail.id}');
         $("#name_homoeopathic_medical_clg").val('${ug_hospital_detail.name_homoeopathic_medical_clg}');
         $("#attached_homoeopath_hospital").val('${ug_hospital_detail.attached_homoeopath_hospital}');
         $("#super_speciality_hospital").val('${ug_hospital_detail.super_speciality_hospital}');
         var hdt = DateFormateSet('${ug_hospital_detail.mou_date}');
		 $("#mou_date").val(hdt);
         $("#copy_of_mou").val('${ug_hospital_detail.copy_of_mou}');
         $("#name_of_hospital_staff").val('${ug_hospital_detail.name_of_hospital_staff}');
         $("#designation").val('${ug_hospital_detail.designation}');
         $("#qualification").val('${ug_hospital_detail.qualification}');
         if('${ug_hospital_detail.fulltime_parttime}' == "Full Time"){
				$("#fulltime_parttime1").prop("checked", true);
			    $("#fulltime_parttime1").val('${ug_hospital_detail.fulltime_parttime}');
			}
			else if('${ug_hospital_detail.fulltime_parttime}' == "Part Time"){
				$("#fulltime_parttime2").prop("checked", true);
			    $("#fulltime_parttime2").val('${ug_hospital_detail.fulltime_parttime}');
			}
         $("#remarks_form_c").val('${ug_hospital_detail.remarks_form_c}');
      
		
 });
        
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn_hospital_update').onclick = function(){
			return UpdateHospitalFn();
	};
	document.getElementById('mou_date').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('mou_date').onfocus = function() {
		this.style.color='#000000';
	};
	document.getElementById('mou_date').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('mou_date').onkeyup = function() {
		return	clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('mou_date').onchange = function() {
		  clickrecall(this,'DD/MM/YYYY');
		  return validateDate_FutureDate(this.value,this); 
	};
	
});
function UpdateHospitalFn(){

	if($("select#name_homoeopathic_medical_clg").val() == "0") {
        alert("Please Enter Name Homoeopathic Medical College");
        $("select#name_homoeopathic_medical_clg").focus();
        return false;
	}
	if($("input#attached_homoeopath_hospital").val().trim() == "") {
	        alert("Please Enter Attached Homoeopath Hospital");
	        $("input#attached_homoeopath_hospital").focus();
	        return false;
	}
	if($("input#super_speciality_hospital").val().trim() == "") {
	        alert("Please Enter Super Speciality Hospital");
	        $("input#super_speciality_hospital").focus();
	        return false;
	}
	if($("input#mou_date").val().trim()=="DD/MM/YYYY") {
	        alert("Please Enter MOU Date");
	        $("input#mou_date").focus();
	        return false;
	}
// 	if($("input#copy_of_mou").val().trim() == "") {
// 	   		alert("Please Enter Copy Of MOU Date");
// 		 		$("input#copy_of_mou").focus();
// 				 return false; 
// 	} 
	if($("input#name_of_hospital_staff").val().trim() == "") {
	        alert("Please Enter Name Of Hospital Staff");
	        $("input#name_of_hospital_staff").focus();
	        return false;
	}
	if($("select#designation").val() == "0") {
	        alert("Please Enter Designation");
	        $("select#designation").focus();
	        return false;
	}
	if($("input#qualification").val().trim() == "") {
	    alert("Please Enter Qualification");
	    $("input#qualification").focus();
	    return false;
	}
	if($('input[name=fulltime_parttime]:checked').length == 0)
	{
			 alert("Please Select Full Time Or Part Time");
			 $("input#fulltime_parttime").focus();
			 return false;
    }
	if($("input#parttime").val().trim() == "") {
	        alert("Please Enter Part Time");
	        $("input#parttime").focus();
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
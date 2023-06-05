<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 
<!-- <script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script> -->
<script src="js/common/multicheck.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>

<section class="dashboard-page">
<div class="container-fluid">

<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
					<span id="lbladd"></span>
						<h2>Intern Registration Form</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Placement Management</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
					<form:form name="linkmaster" id="linkmaster" action="placement_reg_Action"
						method="post" class="form-horizontal" modelAttribute="placement_reg_CMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<h6 class="mb-25">Link Graduate Attribute And Program Outcome</h6>
							<div class="row">
					
								  <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Intern Full Name <strong class="mandatory">* </strong></label> 
										<input type="text" id="first_name" name="first_name"
										placeholder="Intern Full Name"
										maxlength="50" class="form-control" autocomplete="off">
									</div>
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Father Full Name <strong class="mandatory">* </strong></label> 
										<input type="text" id="father_name" name="father_name"
										placeholder="Father Full Name"
										maxlength="50" class="form-control" autocomplete="off">
									</div>
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Upload Photograph<strong class="mandatory">* </strong></label> 
										<input type="file" accept="image/*" id="photo_path" name="photo_path" class="form-control">
										<input type="hidden" id="upload_img_hid" name="upload_img_hid" class="form-control">
										<input type="hidden" id="upload_img_forV"
										 name="upload_img_forV" class="form-control" value="0">
										 <span class="errorClass" id="upload_photo_doc_lbl"></span> 
									</div>
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Upload CV<strong class="mandatory">* </strong></label> 
										<input type="file" accept="file/*" id="upload_cv" name="upload_cv" class="form-control">
										<input type="hidden" id="upload_cv_hid" name="upload_cv_hid" class="form-control">
										<input type="hidden" id="upload_cv_forV"
										 name="upload_cv_forV" class="form-control" value="0">
										 <span class="errorClass" id="upload_cv_doc_lbl"></span> 
									</div>
								</div>
								
								<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Date Of Birth<strong class="mandatory">*
															</strong></label> <input type="text" name="dob" id="dob" maxlength="10"
																 
																class="form-control-sm form-control effect-9 "
																aria-required="true" autocomplete="off"
																value="DD/MM/YYYY">
																<div class="info-value">
																<b>Age :</b><span class="text-heighlight get-value"
																	id="age" name="age"></span>
															</div>
														</div>
										<input type="hidden" id = "yrr" name ="yrr" value="">
													</div>
							
														<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-form-check">
															<label>Gender<strong class="mandatory"
																aria-required="true" autocomplete="off">*
															</strong>
															</label>
															<div class="form-check radio-style">
																<input type="radio" id="male" name="gender"
																	class="form-check-input" value="1" required /> <label
																	for="male" class="form-check-label">Male</label>
															</div>
															<div class="form-check radio-style">
																<input type="radio" id="female" name="gender"
																	class="form-check-input" value="2" required /> <label
																	for="female" class="form-check-label">Female</label>
															</div>
															<div class="form-check radio-style">
																<input type="radio" id="other" name="gender"
																	class="form-check-input" value="3" required /> <label
																	for="other" class="form-check-label">Other</label>
															</div>
														</div>
													</div>
																			
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
									<label>Mobile Number<strong class="mandatory">*</strong></label>
									<input type="text" id="mo_no" name="mo_no"
									  maxlength="10" minlength="10" placeholder="Mobile Number">
									</div>
								</div>
									
									
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
									<label>Alternative Mobile Number</label>
									<input type="text" id="alt_no" name="alt_no"
									  maxlength="10" minlength="10" placeholder="Alternative Mobile Number">
									</div>
								</div>
								
									 <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
									<label>Email Id <strong class="mandatory">*</strong></label>
									<input type="email" id="email_id" name="email_id" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
									 class="form-control" autocomplete="off"  placeholder="Email Id">
									</div>
									</div>
									 
<!-- 									 <div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
<!-- 									<div class="input-style-1"> -->
<!-- 										<label>Current Adress<strong class="mandatory">* </strong></label>  -->
<!-- 										<input type="text" id="curr_add" name="curr_add" -->
<!-- 										placeholder="Current Address" -->
<!-- 										maxlength="50" class="form-control" autocomplete="off"> -->
<!-- 									</div> -->
<!-- 							     	</div> -->
													
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Adress<strong class="mandatory">* </strong></label> 
										<input type="text" id="add_line1" name="add_line1"
										placeholder="Full Address"
										maxlength="50" class="form-control" autocomplete="off">
									</div>
								    </div>
								
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">State<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="state" id="state"
											 class="singleselect form-control form-control-lg" >
											 <option value="0">--Select--</option>
											 <c:forEach var="item" items="${MedStateName}" varStatus="num">
											 <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											 </c:forEach>
											 </select>
										</div>
									</div>
								</div>
								
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">District<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="per_district" id="per_district"
											 class="singleselect form-control form-control-lg autocomplete" >
											 <option value="0">--Select--</option>
											 </select>
										</div>
									</div>
								</div>			
													
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="username">Pin Code<span class="mandatory">*</span></label>
										<input type="text" id="pincode" name="pincode"
										 maxlength="6" minlength="6" class="form-control" autocomplete="off" placeholder="Pin Code">

									</div>
								    </div>	
								    
<!-- 								<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
<!-- 									<div class="select-style-1"> -->
<!-- 										<label for="username">Degree<span class="mandatory">*</span></label> -->
<!-- 										<div class="select-position"> -->
<!-- 											 <select name="typeOfDegree" id="typeOfDegree" class="form-control autocomplete" > -->
<!-- 											 <option value="0">--Select--</option> -->
<%--  											 <c:forEach var="item" items="${TypeOfDegree}" varStatus="num"> --%>
<%-- 											 <option value="${item.id}" name="${item.type_of_degree}">${item.type_of_degree}</option> --%>
<%-- 											 </c:forEach> --%>
<!-- 											 </select> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="username">Internship Project Title<span class="mandatory">*</span></label>
										<input type="text" id="gp_title" name="gp_title"
										 maxlength="70" minlength="5" class="form-control" autocomplete="off" placeholder="Graduation Project Title">

									</div>
								</div>	
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="username">Name Of Internship Faculty Mentor<span class="mandatory">*</span></label>
										<input type="text" id="fm_name" name="fm_name"
										 maxlength="50" class="form-control" autocomplete="off" placeholder="Name Of Faculty Mentor">

									</div>
								</div>		
								
								 <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
									<label>Email Of Internship Faculty Mentor<strong class="mandatory">*</strong></label>
									<input type="email" id="fm_email" name="fm_email" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
									 class="form-control" autocomplete="off"  placeholder="Email Of Faculty Mentor">
									</div>
									</div>
								
								 <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="username">Name Of Internship Industry Mentor<span class="mandatory">*</span></label>
										<input type="text" id="im_name" name="im_name"
										 maxlength="50" class="form-control" autocomplete="off" placeholder="Name Of Faculty Mentor">

									</div>
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
									<label>Email Of Internship Industry Mentor<strong class="mandatory">*</strong></label>
									<input type="email" id="fi_email" name="fi_email" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
									 class="form-control" autocomplete="off"  placeholder="Email Of Industry Mentor">
									</div>
								</div>	
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="username">Designation Of Internship Industry Mentor</label>
										<input type="text" id="im_designation" name="im_designation"
										 maxlength="50" class="form-control" autocomplete="off" placeholder="Designation Of Industry Mento">

									</div>
								</div>	
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
									<label for="username">Internship Duration<span class="mandatory">*</span></label>
										<div class="select-position">
									<select id="intership_dur" name="intership_dur">
									  <option value="0">--Select--</option>
									  <option value="1">1 To 2 Years</option>
									  <option value="2">2 To 3 Years</option>
									  <option value="3">4 To 6 Years</option>
									</select>
									</div>
									</div>
									</div>
									
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="username">Internship Stipend<span class="mandatory">*</span></label>
										<input type="text" id="stipend" name="stipend"
										 maxlength="50" class="form-control" autocomplete="off" placeholder="Internship Stipend">

									</div>
								</div>	
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="username">Internship Hours Of Operation From<span class="mandatory">*</span></label>
										<input type="time" id="intern_hours_from" name="intern_hours_from"
										 maxlength="50" class="form-control" autocomplete="off" placeholder="Internship Hours Of Operation From">

									</div>
								</div>	
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="username">Internship Hours Of Operation To<span class="mandatory">*</span></label>
										<input type="time" id="intern_hours_to" name="intern_hours_to"
										 maxlength="50" class="form-control" autocomplete="off" placeholder="Internship Hours Of Operation To">

									</div>
								</div>
							 
							 
							 <div class="col-12 col-sm-12 col-md-12 col-lg-12" id="checkheaddiv">
								<div class="input-style-form-check_block">
						<!-- <label for="fname"></label> -->
						<div class="form-check checkbox-style mb-20">
						<input type="hidden" id="job_seekers_hid" name="job_seekers_hid" value="0"> 
							<input class="form-check-input" type="checkbox" value=""
								id="job_seekers" name="job_seekers" checked = "checked" /> <label class="form-check-label"
								for="job_seekers"> Student Can Apply For Job Seekers</label>
						</div>
									
								</div>	
								
								<!-- <div class="col-12 col-sm-12 col-md-6 col-lg-4" id="checkheaddiv">
									<div class="input-style-1">
										<label for="username">Student Can Apply For Job Seekers</label>
										<input type="hidden" id="job_seekers_hid" name="job_seekers_hid" value="0"> 
										<input type="checkbox" id="job_seekers" name="job_seekers" checked = "checked">
									</div>
								</div>	 -->
						 
								
<!-- 						<div class="col-12 col-sm-12 col-md-6 col-lg-4" id="checkheaddiv"> -->
<!-- 						<div class="input-style-form-check"> -->
<!-- <!-- 						<label for="username">Job Seekers</label> -->  
<!-- 							<div class="form-check checkbox-style"> -->
<!-- 							<input type="hidden" id="job_seekers_hid" name="job_seekers_hid" value="0">  -->
<!-- 							<input type="checkbox" id="job_seekers" name="job_seekers" class="form-check-input" value="0" required=""> <label for="male1" class="form-check-label">Student Can Apply For Job Seekers -->
<!-- 						</label> -->
<!-- 						</div> -->
<!-- 						</div> -->
<!-- 						</div> -->

								
<!-- 						  <input type = "checkbox" name = "maths" checked = "checked" /> Maths -->
							
							</div>
							<ul class="buttons-group mainbtn">
								<li><input class="main-btn info-btn btn-hover" type="submit" value="Save" id="save" name="save"  /></li>
								<li><a href="Placement_Reg_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
							</ul>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		</div>
</section>

<c:url value="Ga_Po_Delete_Url" var="deleteUrl" /> 
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
 	<input type="hidden" name="id1" id="id1" value="0" /> 
</form:form> 

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		datepicketDate('dob');
	});
	
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('first_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this)
		};
		document.getElementById('first_name').oninput = function () {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('father_name').oninput = function () {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('photo_path').onchange = function() {
 			photoValidate();
 			return companyimgFileSizeValidationplacement(this,this.value,'photo_path','50kb','upload_photo_doc_lbl');
 		};
 		document.getElementById('upload_cv').onchange = function() {
 			CVValidate();
 			return imgFileSizeCVValidation(this,this.value,'upload_cv','50kb','upload_cv_doc_lbl');
 		};
		document.getElementById('father_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this)
		};
		document.getElementById('dob').onclick = function() {
 			return clickclear(this, 'DD/MM/YYYY');
 		};
 		document.getElementById('dob').onfocus = function() {
 			return this.style.color='#000000';
 		};
 		document.getElementById('dob').onchange = function() {
 			return calculate_age('dob');
 		};
 		document.getElementById('dob').onblur = function() {
 			return clickrecall(this,'DD/MM/YYYY');
 		};
 		document.getElementById('dob').onkeyup = function() {
 			return clickclear(this, 'DD/MM/YYYY');
 		};
 		document.getElementById('dob').onchange = function() {
 			return clickrecall(this,'DD/MM/YYYY');
 		};
 		document.getElementById('dob').onchange = function() {
 			validateDate_FutureDate(this.value,this);
 		};
 		document.getElementById('dob').onchange = function() {
 			return calculate_age('dob');
 		};
 		document.getElementById('mo_no').oninput = function () {
 			this.value = this.value.toUpperCase()
		};
		document.getElementById('mo_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('mo_no').onchange = function() {
			 return mobileNumber(this);
 		};
 		document.getElementById('alt_no').oninput = function () {
 			this.value = this.value.toUpperCase()
		};
		document.getElementById('alt_no').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('alt_no').onchange = function() {
			 return mobileNumber(this);
 		};
 		document.getElementById('email_id').onchange = function() {
 			return  checkgmail(this.value);
 		};
 		document.getElementById('add_line1').oninput = function () {
			this.value = this.value.toUpperCase()
		};
// 		document.getElementById('curr_add').oninput = function () {
// 			this.value = this.value.toLowerCase()
// 		};
		document.getElementById('first_name').oninput = function () {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('pincode').onchange = function() {
			  checkMinLength(this.id,6);
			  checkMaxLength(this.id,6);
		};
		document.getElementById('pincode').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('gp_title').oninput = function () {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('fm_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this)
		};
		document.getElementById('fm_name').oninput = function () {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('fm_email').onchange = function() {
 			return  checkgmail2(this.value);
 		};
 		document.getElementById('im_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this)
		};

		document.getElementById('im_name').oninput = function () {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('fi_email').onchange = function() {
 			return  checkgmail4(this.value);
 		};
 		document.getElementById('im_designation').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this)
		};

		document.getElementById('im_designation').oninput = function () {
			this.value = this.value.toUpperCase()
		};
 		document.getElementById('state').onchange = function() {
 			getDistrictper();
		};
		document.getElementById('stipend').onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('intern_hours_from').oninput = function () {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('intern_hours_to').oninput = function () {
			this.value = this.value.toUpperCase()
		};
		document.getElementById('save').onclick = function() {
			return Validate();
		};
	});

	
	function calculate_age(obj){ 
	    var from_d=$("#"+obj).val();
	    var from_d1=from_d.substring(6,10);
	    var from_d2=from_d.substring(3,5);
	    var from_d3=from_d.substring(0,2);
	    var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
	    var today=new Date();
	    var to_d3 = today.getDate();
	    var to_d2 = today.getMonth() + 1;
	    var to_d1 = today.getFullYear();        
	    var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
	    if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
	    var year = to_d1 - from_d1     
	     
	    var month = to_d2 - from_d2
	    }
	    if(to_d2 > from_d2 && to_d3 < from_d3){
	            var year = to_d1 - from_d1        
	            var month = to_d2 - from_d2 - 1
	            }
	    if(from_d2 > to_d2){
	            var year = to_d1 - from_d1 - 1
	            var month1 = from_d2 - to_d2
	            var month = 12 - month1
	    }
	    if(from_d2 == to_d2 && from_d3 > to_d3){
	            var year = to_d1 - from_d1 - 1
	            var days = from_d3 - to_d3
	    }
	    if(from_d2 == to_d2 && to_d3 > from_d3){
	            var year = to_d1 - from_d1 
	            var days =  to_d3 - from_d3 
	    }
	    if(from_d2 == to_d2 && to_d3 == from_d3){
	            var year = to_d1 - from_d1 
	            var days = 0
	    }
	    //days
	    if(from_d2 > to_d2 && from_d3 > to_d3){
	            var m = from_d2 - to_d2 
	            var n = m * 30
	            var m1 = from_d3 - to_d3 
	            var m2 = n+m1
	            var days =  m2
	    }
	    if(from_d2 > to_d2 && to_d3 > from_d3){
	            var m = from_d2 - to_d2 
	            var n = m * 30
	            var m1 =  to_d3 - from_d3 
	            var m2 = n+m1
	            var days =  m2
	    }
	    if(to_d2 > from_d2   && to_d3 > from_d3){
	            var m =  to_d2 - from_d2 
	            var n = m * 30
	            var m1 =  to_d3 - from_d3 
	            var m2 = n+m1        
	            var days =  m2 
	    }
	    if(to_d2 >  from_d2 && from_d3 > to_d3){
	            var m = to_d2 - from_d2   
	            var n = m * 30
	            var m1 = from_d3 - to_d3 
	            var m2 = n+m1
	            var days =  m2
	    }
	    $(".get-value").text(""+year+" Years");
	    $("#yrr").val(year);
	 
	     
	}
	
	function getDistrictper() {
		var selval = $("#state").val();
		$("select#per_district").empty();
		$ .post( "getDistrictOnstate_placement?" + key + "=" + value, { selval : selval },
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
										+ j[i].district_name + '</option>';
							}
							$("select#per_district").html(options);
						});
	}
	
	function mobileNumber(obj){
	_mobile = obj.value;
	var regExp =/^[6789]\d{9}$/;
    if(_mobile == '' || !regExp.test(_mobile)){
        alert('Please Enter Valid Number');
        $('#'+obj.id).focus();
        $('#'+obj.id).val("");
        return false;
    }
}
	
	function checkgmail(email1) {
		 document.getElementById("email_id").innerHTML="";
		if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
		}else{
			alert("Please Enter Valid Email Id");
			$("input#email_id").focus();
			$("input#email_id").val('');
			return false ;
		}
	}
	
	function checkgmail2(email1) {
		 document.getElementById("email_id").innerHTML="";
		if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
		}else{
			alert("Please Enter Valid Email Id");
			$("input#fm_email").focus();
			$("input#fm_email").val('');
			return false ;
		}
	}
	
	function checkgmail4(email1) {
		 document.getElementById("email_id").innerHTML="";
		if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
		}else{
			alert("Please Enter Valid Email Id");
			$("input#fi_email").focus();
			$("input#fi_email").val('');
			return false ;
		}
	}
	
	function checkMinLength(id,minleg){
		var charLength = $("#"+id).val().length;
		   if(charLength < minleg){
		   		alert('Pincode Should Contain Minimum '+minleg+' Digit'); 
		   		$("#"+id).val("");
		   		  $("#"+id).focus();
		   }  
	}

	function checkMaxLength(id,maxleg){
		var charLength = $("#"+id).val().length;
		   if(charLength > maxleg){
		   		alert('Pincode Should Contain Maximum '+maxleg+' Digit');
		   		$("#"+id).val("");
		   		  $("#"+id).focus();
		   }  
	}
  
	function photoValidate(){
		var path = $("#photo_path").val();
		$("#upload_img_hid").val(path); 
	}
	
	function CVValidate(){
		var path = $("#upload_cv").val();
		$("#upload_cv_hid").val(path); 
	}
	
// function setTimeLoadForTable(){
// 	document.getElementById('btn-save').onclick = function() {
// 		return Validation();
// 	};

function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}
 
		
function Validate() {
	
	
	
	 if( $("#job_seekers").is(":checked") == true ){
 		$("#job_seekers_hid").val(1);
  	 }
	 else{
 		 $("#job_seekers_hid").val(0);
 		 }
	  
	
 
	if ($("#first_name").val().trim() == "") {
		alert("Please Enter Intern Full Name");
		$("input#first_name").focus();
		return false;
	}
	if ($("#father_name").val().trim() == "") {
		alert("Please Enter Father Full Name");
		$("input#father_name").focus();
		return false;
	}
	if ($("#upload_img_forV").val().trim() == "1") {
		alert("Please Select Photo");
		$("input#upload_img_forV").focus();
		return false;
	}
	if ($("#upload_img_hid").val().trim() == "") {
		alert("Please Select Photo");
		$("input#photo_path").focus();
		return false;
	}
	if( $("#dob").val() == "" ||  $("#dob").val() == "DD/MM/YYYY"){
		alert("Please Enter Valid Date of Birth");
		$("#dob").focus();
		return false;
   	}
	var a = $('input:radio[name=gender]:checked').val();
	if(a == undefined) {
		alert("Please Select Gender");
		return false;
	}
	var yrr=$("#yrr").val()
	 if(yrr < 18 || yrr == "" || yrr == "0"){
			 alert("Age Should Be Greater Than 17 Years");
	    	$("#dob").focus();
	    	return false;
	 }
	if ($("#mo_no").val().trim() == "") {
		alert("Please Enter Mobile Number");
		$("input#mo_no").focus();
		return false;
	}
	if ($("#email_id").val().trim() == "") {
		alert("Please Enter Email Id");
		$("input#email_id").focus();
		return false;
	}
	if ($("#add_line1").val().trim() == "") {
		alert("Please Enter Permanet Address");
		$("input#add_line1").focus();
		return false;
	}
// 	if ($("#curr_add").val().trim() == "") {
// 		alert("Please Enter Current Address");
// 		$("input#curr_add").focus();
// 		return false;
// 	}
	if ($("#state").val().trim() == "0") {
		alert("Please Select State");
		$("select#state").focus();
		return false;
	}
	if ($("#per_district").val().trim() == "0") {
		alert("Please Select District");
		$("select#per_district").focus();
		return false;
	}
	if ($("#pincode").val().trim() == "") {
		alert("Please Enter Pincode");
		$("input#pincode").focus();
		return false;
	}
	if($("#pincode").val() =="000000"){
		alert("Please Enter the Valid Pin Code");
		$("#pincode").focus();
		return false;
	}
	if($("#pincode").val() =="0"){
		alert("Please Enter the Valid Pin Code");
		$("#pincode").focus();
		return false;
	}
	if ($("#gp_title").val().trim() == "") {
		alert("Please Enter Graduation Project Title");
		$("input#gp_title").focus();
		return false;
	}
	
	if ($("#fm_name").val().trim() == "") {
		alert("Please Enter Name Of Faculty Mentor");
		$("input#fm_name").focus();
		return false;
		}
	
	if ($("#fm_email").val().trim() == "") {
		alert("Please Enter Email Of Faculty Mentor");
		$("input#fm_email").focus();
		return false;
	}
	if ($("#im_name").val().trim() == "") {
		alert("Please Enter Name Of Industry Mentor");
		$("input#im_name").focus();
		return false;
	}
	if ($("#im_name").val().trim() == "") {
		alert("Please Enter Name Of Industry Mentor");
		$("input#im_name").focus();
		return false;
	}
	if ($("#fi_email").val().trim() == "") {
		alert("Please Enter Email Of Industry Mentor");
		$("input#fi_email").focus();
		return false;
	}
	if ($("#im_name").val().trim() == "0") {
		alert("Please Select Duration");
		$("select#im_name").focus();
		return false;
	}
	if ($("#stipend").val().trim() == "") {
		alert("Please Enter Internship Stipend");
		$("input#stipend").focus();
		return false;
	}
	if ($("#intern_hours_from").val().trim() == "") {
		alert("Please Enter Internship Hours Of Operation From");
		$("input#intern_hours_from").focus();
		return false;
	}
	if ($("#intern_hours_to").val().trim() == "") {
		alert("Please Enter Internship Hours Of Operation To");
		$("input#intern_hours_to").focus();
		return false;
	}
	 return true;
}
		
	</script>
	
	



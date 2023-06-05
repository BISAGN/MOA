<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/commonJS/addmorefunctionality.js"></script>
<script src="js/common/multicheck.js"></script>
<link rel="stylesheet" href="js/common/multicheck.css">
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
<!-- <link rel="stylesheet" href="assets/db_css/db_customF_style.css"> -->

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>




<script nonce="${cspNonce}" type="text/javascript">
	var username = "${username}";
</script>

<section class="dashboard-page search_regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>State Search for Student</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									State Search</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<div class=" form-elements-wrapper search-regulation-wrapper">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12 col-lg-12">
					<!-- input style start -->
					<div class="card-style mb-30">
						<div class="custom-field-block">
							<div class="row">
								<!--                  <div class="col-lg-4 col-md-6 col-sm-12"> -->
								<!--                 <div class="input-style-1"> -->
								<!--                   <label> NRH No <strong class="mandatory">  </strong> </label> -->
								<!--                   <input id="nrh_en_no" name="nrh_en_no" class="form-control" autocomplete="off" maxlength="25" placeholder="Maximum 25 Character"> -->
								<!-- 				 <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">  -->

								<!--                 </div> -->
								<!--                 </div> -->
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="input-style-1">
										<label>Full Name <strong class="mandatory"></strong></label> <input
											id="first_name" name="first_name" class="form-control"
											autocomplete="off" maxlength="25" placeholder="Full Name">
										<input type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
									</div>
								</div>
								<!--  <div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
								<!--  													<div class="input-style-1"> -->
								<!-- 															<label>Date Of Birth </label> -->
								<!-- 															 <input type="text" name="dob" id="dob" maxlength="10" -->
								<!-- 																onclick="clickclear(this, 'DD/MM/YYYY')" -->
								<!-- 																class="form-control-sm form-control effect-9 " -->
								<!-- 																onfocus="this.style.color='#000000'" -->
								<!-- 																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" -->
								<!-- 																onkeyup="clickclear(this, 'DD/MM/YYYY')" -->
								<!-- 																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" -->
								<!-- 																aria-required="true" autocomplete="off" -->
								<!-- 																value="DD/MM/YYYY"> -->
								<!-- 														</div> -->
								<!-- 	</div>  -->
								<div class="col-lg-4 col-md-6 col-sm-12 col-12">
									<div class="input-style-2">
										<label>Date of Birth </label> <input type="text" name="dob"
											id="dob" maxlength="10"
											class="form-control-sm form-control effect-9 "
											aria-required="true" autocomplete="off" value="DD/MM/YYYY">
									</div>
								</div>

								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label>Gender <strong class="mandatory"> </strong></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="gender" id="gender" class="form-control">
												<option value=" ">--Select--</option>
												<option value="1">Male</option>
												<option value="2">Female</option>
												<option value="3">Other</option>
											</select>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<!-- 							<div class="col-lg-4 col-md-6 col-sm-12 d-none"> -->
								<!-- 								<div class="input-style-2"> -->
								<!-- 									<label>Registration Number<strong class="mandatory"> -->
								<!-- 									</strong></label>  -->
								<!-- <!-- 									<input type="text" id="reg_no" name="reg_no" onkeypress="return isNumberOnly(event)" -->
								<!-- <!-- 										oninput="this.value = this.value.toUpperCase()" maxlength="10" class="form-control autocomplete" autocomplete="off" placeholder="Registration Number"> -->
								<!-- 									 <input type="text"  id="reg_no" name="reg_no"  maxlength="10" class="form-control autocomplete"	autocomplete="off" placeholder="Registration Number">  	 -->
								<!-- 								</div> -->
								<!-- 							</div> -->
								<div class="col-lg-4 col-md-6 col-sm-1 d-none">
									<div class="input-style-2">
										<label>Date Of First Registration<strong
											class="mandatory"></strong></label> <input type="date"
											id="date_of_reg" name="date_of_reg" class="form-control"
											autocomplete="off" maxlength="25"
											placeholder="Maximum 25 Character"> <span
											class="icon"><i class="bi bi-calendar"></i></span>
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12 d-none">
									<div class="select-style-1">
										<label>Registration State<strong class="mandatory">
										</strong></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="registration_state" id="registration_state"
												class="form-control customselect">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${MedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

							</div>
							<input type="hidden" id="st_id" name="st_id">
							<div class="row d-none">

								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label>State<strong class="mandatory"> </strong></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="per_state" id="per_state"
												class="form-control customselect">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${MedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label> District<strong class="mandatory"> </strong>
										</label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="per_district" id="per_district"
												class="form-control customselect">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label>Place Of Working<strong class="mandatory">
										</strong></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="place_of_working1" id="place_of_working1"
												class="form-control customselect">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${PlaceOfWorking}"
													varStatus="num">
													<option value="${item.id}"
														name="${item.place_of_working_practitioner}">${item.place_of_working_practitioner}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row d-none">
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label>Type of Degree<strong class="mandatory">
										</strong></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="type_of_degree" id="type_of_degree"
												class="form-control customselect">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${TypeOfDegree}"
													varStatus="num">
													<option value="${item.id}" name="${item.type_of_degree}">${item.type_of_degree}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label>Degree<strong class="mandatory"> </strong></label>
										<div class="select-position">
											<select name="DegreeName1" id="DegreeName1"
												class="form-control customselect">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label>Registration Is Renewable Or Permanent<strong
											class="mandatory"> </strong>
										</label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="reg_renew_permanent" id="reg_renew_permanent"
												class="form-control customselect">
												<option value="0">--Select--</option>
												<option value="0">Renewable</option>
												<option value="1">Permanent</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row">



								<!--                  <div class="col-lg-4 col-md-6 col-sm-12"> -->
								<!--                 <div class="input-style-1"> -->
								<!--                   <label> Registration District<strong class="mandatory">  </strong> </label> -->
								<!--                   <select class="singleselect form-control form-control-lg" name="registration_district" id="registration_district" class="form-control autocomplete"> -->
								<!-- 			  </select>    -->
								<!--                 </div> -->
								<!--                 </div> -->
								<!-- 							<div class="col-lg-4 col-md-6 col-sm-12"  > -->
								<!-- 										<div class="col-lg-6 col-md-6 col-sm-12 mb-3" id="hideshow_instituteid">  -->
								<!-- 											<div class="form-group"> -->
								<!-- 												<label class=" form-control-label">University Name<span class="mandatory"> </span></label>  -->
								<!-- 													<select  class="singleselect form-control form-control-lg" name="institute_name" 	class="form-control form-control-lg form-control-a disablecopypaste" -->
								<!-- 													id="institute_name" placeholder="Enter Your University Name"   onchange="InstituteChangeFn(this.value);" > -->
								<!-- 													<option value="0">--Select--</option> -->
								<%-- 													<c:forEach var="item" items="${getInstituteList}" --%>
								<%-- 														varStatus="num"> --%>
								<%-- 														<option value="${item.id}" name="${item.id}">${item.university_name}</option> --%>
								<%-- 													</c:forEach> --%>
								<!-- 												</select> -->
								<!-- 											</div> -->
								<!-- 								 </div> -->


								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label> University Name <strong class="mandatory">
										</strong>
										</label> <input type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="institute_name" id="institute_name"
												class="form-control customselect">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getInstituteList}"
													varStatus="num">
													<option value="${item.id}" name="${item.id}">${item.university_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12 d-none">
									<div class="select-style-1">
										<label> Request For <strong class="mandatory">
										</strong>
										</label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="type_status" id="type_status"
												class="form-control customselect">
												<option value="NEW">NEW</option>
												<option value="RENEW">RENEW</option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label> Status <strong class="mandatory"> </strong>
										</label> <input type="hidden" id="id" name="id" class="form-control"
											value="0" autocomplete="off">
										<div class="select-position">
											<select class="singleselect form-control form-control-lg"
												name="institute_status" id="institute_status"
												class="form-control customselect">
												<option value="0">Pending</option>
												<option value="1">Approved</option>
												<option value="2">Reverted</option>
											</select>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- Bottom Button Start -->
						<div class="btn-bottom">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<ul class="buttons-group mainbtn">
										<li><a
											class="main-btn secondary-btn btn-hover btn-iconic-icon btn-search"
											id="btn-reload" value="Search"><i
												class="lni lni-search-alt"></i>Search</a></li>
										<li><a href="intern_Search_State_pracUrl"
											class="main-btn dark-btn btn-hover btnreset" value="Reset">Reset</a>
										</li>
										<!--                     <li> -->
										<!--                     <input type="submit" class="main-btn secondary-btn btn-hover btn-save" value="Save" onclick="return Validate();"> -->
										<!--                   </li> -->

										<li id="apprvbt"><input type="button"
											class="main-btn success-btn  btn-hover btnappr" value="Approve"
											></li>
										<li id="rejbt"><input type="button"
											class="main-btn danger-btn btn-hover btnreject" value="Reject"
											 name="rejbt"></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- end card -->
				</div>
			</div>
			<!-- end row -->


			<div class="modal fade custom-modal bd-example-modal-lg"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
				aria-hidden="true" id="modelWindow">
				<div class="modal-dialog modal-xl modal-dialog-scrollable">
					<div class="modal-content">
					
						<div class="modal-header">
							<h3 class="modal-title" id="reject_remarks">Enter Remarks</h3>

							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>

						<div class="modal-body custom-modal-table">
							<div class="custom-modal-inner">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table model-table" id="addAttDoc">
										<thead>
											<tr>
												<th>Sr No.</th>
												<th>Name</th>
												<th>Remarks</th>
											</tr>
										</thead>
									</table>
									<div id="dynamicDataTable"></div>
								</div>
							</div>

						</div>
						
						
						<div class="modal-footer">
							<ul class="buttons-group">
								<li><a type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal">Close</a></li>
								<li><button type="button"
										 class="main-btn success-btn  btn-hover" data-bs-dismiss="modal"
										data-dismiss="modal" aria-label="Close" id="ok">Submit</button></li>
							</ul>
						</div>

					</div>
				</div>
			</div>

			<div class="custom-card-sm card-style mb-30 selectsection"
				id="checkheaddiv">
				<input type="hidden" id="CheckVal" name="CheckVal"><input
					type="hidden" id="CheckVal2" name="CheckVal2"> <input
					type="hidden" id="CheckVal3" name="CheckVal3"><input
					class="form-check-input" type=checkbox id='nSelAll' name='tregn'>Select
				all [<span id="tregn">0</span><span id='nTotRow1'>/</span><span
					id="tregnn"> </span>]
			</div>
  <section class="single-detail-block">
			<div class="tables-wrapper">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table id="Search_State_Prac" class="table">
									<thead>
										<tr>
											<th align="center"><h6>Sr No.</h6></th>
											<th id="apprvchk"><h6>Select</h6></th>
											<th><h6>Ayush ID/ABHA No.</h6></th>
											<!-- 										<th id="state_reg_no"><h6>State Register No.</h6></th> -->
											<!-- 										<th><h6>NRH No.</h6></th> -->
											<!-- 										 <th><h6>Valid Upto</h6></th> -->
											<th><h6>Full name</h6></th>
											<th><h6>Gender</h6></th>
											<th><h6>Photo</h6></th>
											<th><h6>Father name</h6></th>
											<th><h6>Present district</h6></th>
											<th><h6>Present state</h6></th>
											<th><h6>Present pincode</h6></th>
											<th><h6>Permanent district</h6></th>
											<th><h6>Permanent state</h6></th>
											<th><h6>Permanent pincode</h6></th>
											<th><h6>Email ID</h6></th>
											<th><h6>Date of birth</h6></th>
											<th><h6>Nationality</h6></th>
											<th><h6>Revert remarks</h6></th>
											<th><h6>Degree details</h6></th>
											<!-- 										<th><h6>Practice Details</h6></th> -->
											<!-- 										<th><h6>Action</h6></th> -->
											<!--   <th>Registration No.</th> -->
											<!--  <th>Date Of Registration</th> -->
											<!--   <th>Name Of The Register</th> -->
										</tr>
										<!-- end table row-->
									</thead>
									<tbody class="custom-datatablepra">
										<!-- 	  <th> ACTION </th> -->
									</tbody>
								</table>
								<!-- end table -->
							</div>
						</div>
						<!-- end card -->
					</div>
					<!-- end col -->
				</div>
				<!-- end row -->
			</div>
			</section>
		</div>
	</div>
</section>

<c:url value="DegreePopupUrl" var="DegreePopupUrl" />
<form:form action="${DegreePopupUrl}" method="post"
	id="degreepopup_Form" name="degreepopup_Form" modelAttribute="id"
	target="result">
	<input type="hidden" name="popid" id="popid" value="0" />
	<input type="hidden" name="institute_name_hid" id="institute_name_hid"
		value="0" />


</form:form>

<c:url value="IOCHPopupUrl" var="IOCHPopupUrl" />
<form:form action="${IOCHPopupUrl}" method="post" id="IOCHpopup_Form"
	name="IOCHpopup_Form" modelAttribute="popidIOCH" target="result">
	<input type="hidden" name="popidIOCH" id="popidIOCH" value="0" />
</form:form>

<c:url value="Edit_edu_reg_mstrUrl" var="Edit_edu_reg_mstrUrl" />
<form:form action="${Edit_edu_reg_mstrUrl}" method="post"
	id="updateForm" name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="delete_edu_reg_mstr_Url" var="delete_edu_reg_mstr_Url" />
<form:form action="${delete_edu_reg_mstr_Url}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<c:url value="valid_upto_Url" var="valid_upto_Url" />
<form:form action="${valid_upto_Url}" method="post" id="validupto_Form"
	name="validupto_Form" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<c:url value="Excel_Auth_Posted_query" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="cont_comd_ex">
	<!-- 	 <input type="hidden" name="cont_comd_ex" id="cont_comd_ex"  value="0"> -->
	<!-- 	   <input type="hidden" name="cont_corps_ex" id="cont_corps_ex" value="0"> -->
	<!-- 	   <input type="hidden" name="cont_div_ex" id="cont_div_ex" value="0"> -->
	<!-- 	   <input type="hidden" name="cont_bde_ex" id="cont_bde_ex" value="0"> -->
	<!-- 	   <input type="hidden" name="cont_comd_txt" id="cont_comd_txt" > -->
	<!-- 	   <input type="hidden" name="cont_corps_txt" id="cont_corps_txt"> -->
	<!-- 	   <input type="hidden" name="cont_div_txt" id="cont_div_txt"> -->
	<!-- 	   <input type="hidden" name="cont_bde_txt" id="cont_bde_txt"> -->
	<!-- 	   <input type="hidden" name="unit_name_ex" id="unit_name_ex"> -->
	<!-- 	   <input type="hidden" name="sus_no_ex" id="sus_no_ex"> -->
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<!-- The Modal -->
<div id="myModal" class="modal">
	<span class="close">&times;</span> <img class="modal-content"
		id="img01">
	<div id="caption"></div>
</div>

<c:url value="getCertificatePDF" var="getCertificatePDF" />
<form:form action="${getCertificatePDF}" method="post" id="search1"
	name="search1" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" value="0" />
	<input type="hidden" name="typeReport2" id="typeReport2" value="0" />
	<input type="hidden" name="reportname1" id="reportname1" value="0" />
</form:form>
<c:url value="getView" var="getView" />
<form:form action="${getView}" method="post" id="search2" name="search2"
	modelAttribute="id7">
	<input type="hidden" name="id7" id="id7" value="0" />
	<input type="hidden" name="typeReport7" id="typeReport7" value="0" />
	<input type="hidden" name="reportname8" id="reportname8" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

function setTimeLoadForTable(){
//	debugger;
	document.getElementById('first_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this,event);
	};

$('.nrCheckBox').on('change', function() {
	checkCKBT();
});
// $('.nrCheckBox').on('change', function() {
// 	$(".nrCheckBox").each(function(i) {
// 		   if (this.checked) {
// 		       alert("Checkbox at index " + i + " is checked.");
// 		   }
// 		});
// });


	document.getElementById('dob').onclick = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('dob').onfocus = function() {
		return this.style.color='#000000';
	};
	document.getElementById('dob').onblur = function() {
		return clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
	};
	document.getElementById('dob').onkeyup = function() {
		return clickclear(this, 'DD/MM/YYYY');
	};
	document.getElementById('dob').onchange = function() {
		return clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);calculate_age('dob');
	};
	
// 	document.getElementById('reg_no').onkeypress = function() {
// 		return isNumberOnly(event);
// 	};
	
// 	document.getElementById('reg_no').oninput = function () {
// 		 this.value=this.value.toUpperCase()
// 	};

	document.getElementById('date_of_reg').onkeyup = function() {
		return  onlyAlphabetsStringSpace(this,event);
	};
	document.getElementById('registration_state').onchange = function() {
		  getDistrictper();
	};
	document.getElementById('per_state').onchange = function() {
		  getDistrictper();
	};
	document.getElementById('type_of_degree').onchange = function() {
		getDegreeName(this,1);
	};
	document.getElementById('institute_name').onchange = function() {
		statusChange();
	};
	document.getElementById('institute_status').onchange = function() {
		   statusChange();
	};
	document.getElementById('apprvbt').onclick = function() {
		return setApproveStatus();
	};
	document.getElementById('ok').onclick = function() {
		
		return setRejectStatus();
	};
	document.getElementById('rejbt').onclick = function() {
		//alert("1234");
		   $('#modelWindow').modal('show');
		   
		  findselected();
			var a = $("#CheckVal").val();
		 
			if(a == ""){
				alert("Please Select the Data for Revert"); 
			}else{
   
				 dynamicTable(a);

			}
		   
// 		 return setRejectStatus();
	};
	document.getElementById('nSelAll').onclick = function() {
		setselectall();
	};
	
	document.querySelectorAll('.editstate').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('CounId'+val).value;
			var gname = document.getElementById('Counnrh_en_no'+val).value;
			var gstatus = document.getElementById('Counstatus'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				EditData(gdid,gname,gstatus);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.statedlt').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('DCounId'+val).value;
			if (confirm('Are You Sure You Want to Delete Detail ?')) {
				deleteData(gdid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.dgpop').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('degreeId'+val).value;
			if (confirm('Are You Sure You Want to Show Detail ?')) {
				Pop_Up_Degree(gdid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.popview').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('pop_ioch'+val).value;
			if (confirm('Are You Sure You Want to Show Detail ?')) {
				Pop_Up_IOCH(gdid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.popview').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('pop_ioch'+val).value;
			if (confirm('Are You Sure You Want to Show Detail ?')) {
				Pop_Up_IOCH(gdid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.popdegreep').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('popdegreepId'+val).value;
			if (confirm('Are You Sure You Want to View Detail?')) {
				Pop_Up_DegreeP(gdid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.degreepdf').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('degreepdfId'+val).value;
			if (confirm('Are You Sure You Want to download File ?')) {
				getPDF(gdid);
			} else {
				return false;
			}
		})
	});
	document.querySelectorAll('.imgcsp').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			
			var gdid = document.getElementById('imghid'+val).value;
//				if (confirm('Are You Sure You Want to View Detail?')) {
				imageView(gdid);
//				} else {
//					return false;
//				}
		})
	});
}

function getPDF(id)
{  
	$("#id3").val(id); 
	document.getElementById('typeReport2').value='pdfL';
	document.getElementById('search1').submit();
}

function Pop_Up_DegreeP(id)
{  
	$("#id7").val(id); 
	document.getElementById('typeReport7').value='pdfL';
	document.getElementById('search2').submit();
}

function setselectall(){
 
	if ($("#nSelAll").prop("checked")) {
		$(".nrCheckBox").prop('checked', true);
	} else {
		$(".nrCheckBox").prop('checked', false);
	}
	
	var l = $('[name="cbox"]:checked').length;
	 $("#tregn").val(l);
	document.getElementById('tregn').innerHTML = l;
	
	checkCKBT();
}
	
	$(document).ready(function() {
		datepicketDate('dob');
		datepicketDate('date_of_reg');
		
		//Checkboxhideshow
		$("#apprvbt").hide();
		$("#rejbt").hide();
		  
		 $("#st_id").val('${state_id}');
		mockjax1('Search_State_Prac');
		table = dataTable('Search_State_Prac');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
			
			setTimeout(findselected, 1000);
			var institute_status = $("#institute_status").val();
			if(institute_status == "1" || institute_status == "2"){
				 removecol();	
			}
			if(institute_status == "0"){
 				addcol();
			}
		});
		$.ajaxSetup({
			async : false
		});
	});
 
	function statusChange(){
		 var institute_status = $("#institute_status").val();
		 if(institute_status=='0'){
			 $("#rejbt").show();
			 $("#apprvbt").show();
			// $("#checkheaddiv").show();
			 checkCKBT();
		 }
		 else if(institute_status=='1' || institute_status=='2'){
			 $("#rejbt").hide();
			 $("#apprvbt").hide();
			// $("#checkheaddiv").hide();
			// checkCKBT();
		 }
	}
function removecol() {	

   var tble = document.getElementById('Search_State_Prac'); 
   var row = tble.rows;  
   var i = 1; 
   for (var x = 0; x < row.length; x++) {
       row[x].deleteCell(i);
   }
}
	
function addcol() {

   var tble = document.getElementById('Search_State_Prac');
	
   var row = tble.rows;  
   var i = 1; 
   for (var x = 0; x < row.length; x++) {
       row[x].insertCell(i);
   }
}
	
	function data(Search_State_Prac) {
		jsondata = [];
		var table = $('#' + Search_State_Prac).DataTable();
		var info = table.page.info();
// 		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		//var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];

		//var nrh_en_no = $("#nrh_en_no").val();
		var first_name = $("#first_name").val();
		var gender = $("#gender").val();
		var photo_path = $("#photo_path").val();
		var father_name = $("#father_name").val();
		var pre_address = $("#pre_address").val();
		var pre_district = $("#pre_district").val();
		var pre_state = $("#pre_state").val();
		var pre_pincode = $("#pre_pincode").val();
		var per_address = $("#per_address").val();
		var per_district = $("#per_district").val();
		var per_state = $("#per_state").val();
		 
		var per_pincode = $("#per_pincode").val();
		var aadhaar_no = $("#aadhaar_no").val();
		var fax_no = $("#fax_no").val();
		var mo_no = $("#mo_no").val();
		var alt_mo_no = $("#alt_mo_no").val();
		var email_id = $("#email_id").val();
		var dob = $("#dob").val();
		var nationality = $("#nationality").val();
		//var reg_no = $("#reg_no").val();
		var date_of_reg = $("#date_of_reg").val();
		var name_reg = $("#name_reg").val();
		var reg_renew_permanent = $("#reg_renew_permanent").val();
		var name_of_patient = $("#name_of_patient").val();
		var institute_status = $("#institute_status").val();
		 
		var type_of_degree = $("#type_of_degree").val();
	//	var reg_no = $("#reg_no").val();
		
		var degree_name = $("#DegreeName1").val();
		var place_of_working = $("#place_of_working1").val();
	 	var registration_for_type = $("select#reg_renew_permanent").val();
	 	 
	 	var registration_state = $("#st_id").val(); 
	 	var institute_name = $("#institute_name").val(); 
	 	var type_status = $("#type_status").val(); 
	 	 
		$.post("getFilter_State_intern_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			//nrh_en_no:nrh_en_no,
			first_name:first_name,
			institute_status:institute_status,
			 gender:gender,
			 
			 registration_state:registration_state,
			 per_state:per_state,
			 per_district:per_district,
			 type_of_degree:type_of_degree,
			 degree_name:degree_name,
			 place_of_working:place_of_working,
			 registration_for_type:registration_for_type,
			 dob:dob,
			 date_of_reg:date_of_reg,
			 institute_name:institute_name,
			 type_status:type_status
			 
		}, function(j) {
	//		debugger;
			for (var i = 0; i < j.length; i++) {
			
			$("#tregnn").text(" "+j.length);	
			$("#tregnn").text(" "+j.length);	
		 	
			
			
			var gender ="";
			  gender = j[i].gender;
			  
			  //console.log(j[i].gender);
			  
			if(gender == "1"){
				gender="Male"
			}
			if(gender == "2"){
				gender="Female"
			}
			if(gender == "3"){
				gender="Other"
			}
			var registration_for_type ="";
			registration_for_type = j[i].registration_for_type;
			if(registration_for_type == "0"){
				registration_for_type="Renewable"
			}
			if(registration_for_type == "1"){
				registration_for_type="Permanent"
			}
			
			var ayus = j[i].ayush_id ;
			var  ayu_abha;
			var abha  ;
			if( abha == "null" || abha == null || abha == " " || abha == "0"){
				abha ="";
				    ayu_abha =  ('Ayush Id: '+ayus+ '</br>' +'  '+abha+'</br>' );
			}
			else{
				  abha = j[i].abha_no ;
				     ayu_abha =  ('Ayush Id: '+ayus+ '</br>' +'ABHA No. : '+abha+'</br>' );
			}
		 
			 var institute_status = $("#institute_status").val();
// 			 var institute_status = '1';
// 	 var institute_status = $("#institute_status").val();
// 	var type_status = $("#type_status").val(); 
// 	if(j[i].nrh_en_no == null) {


				 if(institute_status=='0'){
  					
					 if( j[i].nrh_en_no == null){
 						 $("#apprvchk").show();
	 					 $("#checkheaddiv").show();
	 					 jsondata.push([ j[i].ser ,j[i].chekboxaction,(ayu_abha ) , j[i].first_name, gender, j[i].img, j[i].father_name,
						 j[i].pre_district, j[i].pre_state, j[i].pre_pincode,  
						 j[i].per_district, j[i].per_state, j[i].per_pincode,
						 j[i].email_id , j[i].dob, j[i].nationality,j[i].reject_remarks,j[i].vd  
						 
							  ]);
 					 }
					 else{
 						 $("#apprvchk").show();
	 					 $("#checkheaddiv").show();
	 					 jsondata.push([ j[i].ser ,j[i].chekboxaction,(ayu_abha ),  j[i].first_name, gender, j[i].img, j[i].father_name,
						 j[i].pre_district, j[i].pre_state, j[i].pre_pincode,  
						 j[i].per_district, j[i].per_state, j[i].per_pincode,
						 j[i].email_id , j[i].dob, j[i].nationality,j[i].reject_remarks,j[i].vd  
						 
							  ]);
 					 }
					 
	 }
					
		 		 
				 else if(institute_status=='1' || institute_status=='2'){
					 
					 
					 if( j[i].nrh_en_no == null){
						 $("#apprvchk").hide();
	 					 $("#checkheaddiv").hide();
						 jsondata.push([ j[i].ser ,j[i].chekboxaction,(ayu_abha ),  j[i].first_name, gender, j[i].img, j[i].father_name
								 ,j[i].pre_district, j[i].pre_state, j[i].pre_pincode,
								j[i].per_district, j[i].per_state, j[i].per_pincode ,
								j[i].email_id , j[i].dob, j[i].nationality,j[i].reject_remarks,j[i].vd 
						 
									  ]);
					 }
					 else{
 						 $("#apprvchk").hide();
	 					 $("#checkheaddiv").hide();
	 					 jsondata.push([ j[i].ser ,j[i].chekboxaction,(ayu_abha )  , j[i].first_name, gender, j[i].img, j[i].father_name,
						 j[i].pre_district, j[i].pre_state, j[i].pre_pincode,  
						 j[i].per_district, j[i].per_state, j[i].per_pincode,
						 j[i].email_id , j[i].dob, j[i].nationality,j[i].reject_remarks,j[i].vd 
						 
							  ]);
 					 }
				  
 					 
					 
					 
				 }
			}
		});
		
		$.post("getTotalState_intern_dataCount?" + key + "=" + value, {
			//nrh_en_no:nrh_en_no,
			first_name:first_name,
			institute_status:institute_status,
			 gender:gender,
			 
			 registration_state:registration_state,
			 per_state:per_state,
			 per_district:per_district,
			 type_of_degree:type_of_degree,
			 degree_name:degree_name,
			 place_of_working:place_of_working,
			 registration_for_type:registration_for_type,
			 dob:dob,
			 date_of_reg:date_of_reg,
			 institute_name:institute_name
			 ,type_status:type_status
		}, function(j) {
			
			FilteredRecords = j;
			});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
function EditData(id) {
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

function deleteData(id) {
	$("#id2").val(id);
	document.getElementById('deleteForm').submit();
}
	
	function setApproveStatus(){
		findselected();
		 var a = $("#CheckVal").val();
		 var upto = $("#CheckVal2").val();
		 var state = $("#CheckVal3").val();

		 console.log(a);
		
		if(a == ""){
			alert("Please Select the Data for Approval"); 
		}
// 		if(upto==""){ 
// 			alert("Please Select the Valid Upto Date for Approval"); 
// 		}
		else{
				$.post("internApprove_FromState_Data?"+key+"="+value, {a:a,status:"1",per_state:state}).done(function(j) {
				
					alert("Data approved successfully.");

				location.reload();
			}); 
		}
	}


function setRejectStatus(){
	//	debugger;
		findselected();
		
		var a = $("#CheckVal").val();
		
		var tempSt='';
		var f = a.split(":");
		
		 var upto2 = $("#CheckVal2").val();
		for (var i = 0; i < f.length; i++) {
			
		//	alert($("#remarks"+f[i]).val());
			
			if($("#remarks"+f[i]).val() == ""){
				alert("Please Enter the Remarks for Revert"); 
				return false;
			}
			
			
			
			if(i==0){
				tempSt+=$("#remarks"+f[i]).val();
			}else{
				tempSt+=","+$("#remarks"+f[i]).val();
			}
			
// 			if($("#remarks"+f[i] == ""){
// 				alert("Please Enter the Remarks for Reject"); 
// 			}
			
			
		}
		
		if(tempSt == ""){
		//	alert("Please Enter the Remarks for Reject"); 
		}else{
			//alert("Rejected Successfully");
				$.post("Reject_FromState_Data?"+key+"="+value, {a:a,upto2:upto2,status:"2",tempSt:tempSt}).done(function(j) {
					alert("Revert Back Successfully");
 				location.reload();
			}); 
		}
	}

function findselected(){

 		var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
 		return $(this).attr('id');
  		}).get();
		
		var b=nrSel.join(':');
		$("#CheckVal").val(b);
		$('#tregn').text(nrSel.length);
		
		var nrSel2=$('.nrCheckBox:checkbox:checked').map(function() {
		//var upto=$("#valid_upto"+$(this).attr('id')).val();
			 
// 			 var y = upto.substring(0,4);
// 			 var m = upto.substring(5,7);
// 			 var d = upto.substring(8,10);
// 			 var upto2 = d+"/"+m+"/"+y;
		//	return upto2;
		}).get();
		
		var c=nrSel2.join(':');
		$("#CheckVal2").val(c);
		
		var nrSel3=$('.nrCheckBox:checkbox:checked').map(function() {
			var state = $("#nrCHid"+ $(this).attr('id')).val();
			return state;
		}).get();
			
		var z=nrSel3.join(':');
		$("#CheckVal3").val(z);
	}
	
// 	function setRejectStatus(){
		 
// 		findselected();
		
// 		var a = $("#CheckVal").val();

// 		if(a == ""){
// 			alert("Please Select the Data for Reject"); 
// 		}else{

// 				$.post("Approve_FromState_Data?"+key+"="+value, {a:a,status:"2"}).done(function(j) {
// 					alert("Rejected Successfully");
//  				location.reload();
// 			}); 
// 		}	
// 	}
		
		// Get the modal
		function imageView(obj){

		var modal = document.getElementById("myModal");
		var span = document.getElementsByClassName("close")[0];

		// Get the image and insert it inside the modal - use its "alt" text as a caption
		var img = document.getElementById("myImg"+obj);

		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");

		img.onclick = function(){
		  modal.style.display = "block";
		  modalImg.src = this.src;
		  //captionText.innerHTML = this.alt;
		}
		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() { 
		  modal.style.display = "none";
		}
	}
	</script>

<script nonce="${cspNonce}" type="text/javascript">

function getExcel() {	

	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
} 

function getDistrictper() {

	var selval = $("#per_state").val();
	$("select#per_district").empty();

	$
			.post(
					"getDistrictOnstate?" + key + "=" + value,
					{
						selval : selval
					},
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

function getDegreeName(obj,R){
	var DegreeName = $("#"+obj.id).val();
	$.post('getDegreeName?' + key + "=" + value,{DegreeName:DegreeName},function(k) {
		var options = '';
		for (var i = 0; i < k.length; i++) {
			options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'+ k[i].degree_name + '</option>';
		}
		$("#DegreeName"+R).html(options);
//			for (var i = 0; i < data.length - 1; i++) {
//				datap = data[i].split(":");
//				options += '<option value="'+datap[i].id+'" name="' + datap[]+ '" >'+ datap[0] + '</option>';
//			}
//			$("#rank").html(options);
//			var q = '${list.rank}';
//			if(q != ""){
//				$("#rank").val(q);
//			}
	});
}

function checkCKBT(){
	 
	var lchk = $('input[name="cbox"]:checked').length;
	
	if(lchk>0 ){
		$("#rejbt").show();
		 $("#apprvbt").show();
	}
	else{
		$("#rejbt").hide();
		 $("#apprvbt").hide();
	}
}

function Pop_Up_Degree(a) {
	var institute_name_hid = $("select#institute_name").val(); 
 
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	$("input#popid").val(a); 
	$("input#institute_name_hid").val(institute_name_hid); 
	
	
	document.getElementById('degreepopup_Form').submit();
}

function Pop_Up_IOCH(a) {

	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	$("input#popidIOCH").val(a);
	document.getElementById('IOCHpopup_Form').submit();
}

function valid_upto(){ 
	$("#id2").val(id);
	document.getElementById('validupto_Form').submit();
	
}
 
function dynamicTable(a){
	
	
	// debugger;
    
	var R = a.split(":");
	//var dd = R.split(",");
	//alert("a1==="+a1)
	
// var d = 	$("#valid_upto127").val();
// 	alert(d)
	$("div#dynamicDataTable").html("");
	
	for(var i = 1 ; i <= R.length ;i ++ ){
		
		//$("#dynamicDataTable").append("<div id='dynamicDataTable"+i+"'></div>");
	
		var  seq =   i;
	
 	//alert("ijjj=="+a);
	
		
		$("#dynamicDataTable").append("<div id='dynamicDataTable"+seq+"'></div>");
// 		var r1 = "'"+R+"_"+"'";
		var R = a.split(":");
		//alert("i-----"+seq)
		var idx = R[i-1]
		var nam = "";
			
	$.post("get_Parctname_by_Reject_id?" + key + "=" + value, {
		id:idx
		}, function(p) {
				nam = p;
		});
		
		
		
		$("div#dynamicDataTable"+seq).append(
				'<table class="table model-table" id="addAttDoc'+seq+'">'
// 				+'<thead><tr><th>Sr No</th>'
// 				+'<th>Name</th>'
// 				+'<th>Remarks</th>'
// 				+'</tr>'
// 				+'</thead>'
				+'<tbody id="att_TbbodyattDoc'+seq+'">'
				+'	<tr id="tr_id_attDoc'+seq+'">'
				+'		<td class="min-width">'
				+'			<div class="lead">'
 				+'				<div class="lead-text">'
				+'					<p>'+i+'</p>'
				+'				</div>'
				+'			</div>'
					+'		</td>'
							+'	<td class="min-width">'
 							+'	<div class="input-style-1">'
 							+'<label id="name'+R[i-1]+'" name="name'+R[i-1]+'"  value="'+nam+'">'+nam+'</label>'
						//	+'	<input type="text" id="name'+R[i-1]+'" name="name'+R[i-1]+'"  value="'+nam+'"  class="form-control"> '
							+'	<input type="text" hidden="hidden" id="client'+seq+'" name="client'+seq+'"  value="'+R[(i - 1)]+'"> '
 							+'</div> '
							+'</td>'
							+'	<td class="min-width">'
 							+'	<div class="input-style-1">'
							+'	<input type="text" id="remarks'+R[i-1]+'" name="remarks'+R[i-1]+'" placeholder= "Enter Your Remarks"  class="form-control"> '
 							+'</div> '
							+'</td>'
							 +'</tr> </tbody> </table>' 
			);
		
		
//   attachmentt(R,index);
	

 	 
		
	}
	 
	
//alert(R+"--"+index);
// 	var listItem = document.createElement("a");
// 	 var listItemCheckbox = document.createElement("a");
	  
// 	listElement.appendChild(listItem);
// 	 listItem.appendChild(listItemCheckbox);
	
	}
</script>
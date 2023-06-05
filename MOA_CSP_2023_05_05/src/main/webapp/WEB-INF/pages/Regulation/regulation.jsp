<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <link href="https://fonts.googleapis.com/css?family=Poppins:400,600,700" rel="stylesheet" /> -->
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.js"></script> 
<script src="js/miso/commonJS/addmorefunctionality.js" type="text/javascript"></script>
	
<script src="Pages_JS/Policy_Creation.js" type="text/javascript"></script>
<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.js" type="text/javascript"></script>
<script src="js/Pdf_View/pdf.worker.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="js/Pdf_View/pdfview.css">
	

<!-- INTERNAL REMOVE START-->
<link rel="stylesheet" href="assets/vendor/internal_css.css">
<!-- INTERNAL REMOVE END-->	

<!-- INTERNAL REMOVE START (NEED TO ADD IN PARTICULAR JSP)-->
			<link rel="stylesheet" href="assets/vendor/internal_css.css">
<!-- INTERNAL REMOVE END--> 

<section class="dashboard-page regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
					<c:if test = "${ login_role  == 'Student' }">
						<h2>Provisional Certificate </h2>
						 </c:if>
						 <c:if test = "${ login_role  == '27' }">
						<h2>Regulation</h2>
						 </c:if>
						 <c:if test = "${ login_role  == 'Intern' }">
						<h2>Regulation</h2>
						 </c:if>
						 
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#0">Start View</a></li>
								<li class="breadcrumb-item"><a href="#0">Regulation
										Forms</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Form A</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->

		<div class="form-elements-wrapper">
			<div class="row">

				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form action="Regulation_Action" id="uploadForm" method="POST"
						class="form-horizontal" modelAttribute="RegulationCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">

							<div class="accordion" id="accordionPanelsStayOpenExample">
								<div class="accordion-item accordion-itemstyle">
									<h2 class="accordion-header" id="panelsStayOpen-headingOne">
										<button
											class="accordion-button accordion-itemstylena accordion-primary-button "
											type="button" data-bs-toggle="collapse"
											data-bs-target="#panelsStayOpen-collapseOne"
											aria-expanded="true"
											aria-controls="panelsStayOpen-collapseOne">PERSONAL
											DETAILS</button>
									</h2>
									<div id="panelsStayOpen-collapseOne"
										class="accordion-collapse collapse show"
										aria-labelledby="panelsStayOpen-headingOne">
										<div class="accordion-body">
											<div class="card-style mb-30">

											 <div class="row" id = "nr_ay"  >
											 	<c:if test = "${ role  != 'Student_NCH' }">
													<div class="col-lg-6 col-md-12 col-sm-12 mb-4" id="ny">
													<label>NRH No : <strong class="mandatory"> <span> <label type="text" id="nrh" name="nrh"  	> </label>  </strong>
													</span></label>
  											 		</div>
 											  </c:if>
 											 <div class="col-lg-6 col-md-12 col-sm-12 mb-4" id="ay">
 											 <label>Ayush Id: <strong class="mandatory"> <span>
													<label type="text" id="ayu" name="ayu"  	> </label>  </strong>
													</span></label>
<!-- 												 <h6>Ayush  Id: <strong> <label type="text" id="ayu" name="ayu"  	> </label></strong> </h6> -->
 											 </div>
 										 </div>
										<div class="row">

											<input type="hidden" id="userId" name="userId" value="0">
											<input type="hidden" id="aayushid" name="aayushid" value="">
											<input type="hidden" id="abha_no" name="abha_no" value="">
													
													<!-- 	19-07-22 testing changes -->
													<div class="col-lg-4 col-sm-12">
														<div class="input-style-2">
															<label>Full Name <strong class="mandatory">* </strong></label> 
															<input type="text" id="first_name" name="first_name"
																placeholder="Full Name"
																maxlength="50" class="form-control" autocomplete="off">

														</div>
													</div>

													<div class="col-lg-4 col-sm-12">
														<div class="input-style-2">
															<label>Father's Full Name <strong class="mandatory">*
															</strong>
															</label> <input type="text" id="father_name" name="father_name"
																placeholder="Father's Full Name"
																maxlength="50" class="form-control" autocomplete="off">

														</div>
													</div>

													<div class="col-lg-4 col-sm-12">
														<div class="input-style-2">
															<label>Date Of Birth <strong class="mandatory">*
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
<!-- 													<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 														<div class="input-style-form-check"> -->
<!-- 															<label>Gender<strong class="mandatory" class="form-control-sm form-control effect-9 " -->
<!-- 																aria-required="true" autocomplete="off">* -->
<!-- 															</strong> -->
<!-- 															</label> -->
<!-- 															<div class="form-check radio-style"> -->
<!-- 																<input type="radio" id="male" name="gender" -->
<!-- 																	class="form-check-input" value="1" required /> <label -->
<!-- 																	for="male" class="form-check-label">Male</label> -->
<!-- 															</div> -->
<!-- 															<div class="form-check radio-style"> -->
<!-- 																<input type="radio" id="female" name="gender" -->
<!-- 																	class="form-check-input" value="2" required /> <label -->
<!-- 																	for="female" class="form-check-label">Female</label> -->
<!-- 															</div> -->
<!-- 															<div class="form-check radio-style"> -->
<!-- 																<input type="radio" id="other" name="gender" -->
<!-- 																	class="form-check-input" value="3" required /> <label -->
<!-- 																	for="other" class="form-check-label">Other</label> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</div> -->

													<div class="col-lg-4 col-md-6 col-sm-12">
													<div class="input-style-1">
													<label>Gender<strong class="mandatory" class="form-control-sm form-control effect-9 " aria-required="true" autocomplete="off">*
															</strong>
															</label>
													</div>
													<div class="input-style-form-check">
													<!-- <label for="fname"></label> -->
																<div class="form-check radio-style">
																<input type="radio" id="male" name="gender" class="form-check-input" value="1" required />
																 <label for="male" class="form-check-label">Male</label>
															</div>
															<div class="form-check radio-style">
																<input type="radio" id="female" name="gender" 	class="form-check-input" value="2" required /> 
																 <label for="female" class="form-check-label">Female</label>
															</div>
															<div class="form-check radio-style">
																<input type="radio" id="other" name="gender" class="form-check-input" value="3" required />
																 <label for="other" class="form-check-label">Other</label>
															</div>
																</div>
						
														<!-- <div class="input-style-form-check">
															<label>Gender<strong class="mandatory" class="form-control-sm form-control effect-9 " aria-required="true" autocomplete="off">*
															</strong>
															</label>
															<div class="form-check radio-style">
																<input type="radio" id="male" name="gender" class="form-check-input" value="1" required />
																 <label for="male" class="form-check-label">Male</label>
															</div>
															<div class="form-check radio-style">
																<input type="radio" id="female" name="gender" 	class="form-check-input" value="2" required /> 
																 <label for="female" class="form-check-label">Female</label>
															</div>
															<div class="form-check radio-style">
																<input type="radio" id="other" name="gender" class="form-check-input" value="3" required />
																 <label for="other" class="form-check-label">Other</label>
															</div>
														</div> -->
													</div>
													<!-- end input -->
															<div class="col-lg-4 col-sm-12">
														<div class="input-style-2">
															<label>Aadhaar Number <strong class="mandatory">*
															</strong></label> <input type="text" id="aadhaar_no" name="aadhaar_no"
																maxlength="14" minlength="12" class="form-control" autocomplete="off"
																placeholder="Aadhaar Number">
														</div>
													</div>

													<div class="col-lg-4 col-sm-12">
														<div class="input-style-2">
															<label>Mobile Number<strong class="mandatory">*
															</strong>
															</label> <input type="text" id="mo_no" name="mo_no" readonly="readonly"
																  maxlength="10" minlength="10"  
																placeholder="Mobile Number">

														</div>
													</div>

													<div class="col-lg-4 col-sm-12">
														<div class="input-style-2">
															<label>Email Id <strong class="mandatory">*
															</strong></label> <input type="email" id="email_id" name="email_id"  readonly="readonly"
																pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
																class="form-control" autocomplete="off">
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Upload Photograph <strong class="mandatory">*   </strong>    </label>
														
 															<input type="file" accept="image/*" id="photo_path" name="photo_path"  class="form-control">
															<label  id = "img_up">  <strong   class="mandatory"> Image Not Uploaded </strong> </label> 
															
															<input type="hidden" id="upload_img_hid" name="upload_img_hid" class="form-control">
															 <input type="hidden" id="upload_img_forV" name="upload_img_forV" class="form-control" value="0">
															<span class="errorClass" id="upload_photo_doc_lbl"></span> 

														</div>
													</div>

													<div class="col-lg-4 col-sm-12">
														<div class="select-style-1">
															<label>Nationality <strong class="mandatory">*
															</strong></label>
															
															<div class="input-style-2">
															 <input type="text" id="nat" name="nat" maxlength="10" class="form-control" autocomplete="off"
																 value="INDIAN" readonly="readonly">
														</div>
															<div class="select-position custom-d-none">
																<select name="nationality" id="nationality"  
																	 class="singleselect form-control form-control-lg regupract">
																	<option value="0" selected="selected">--
																		Select Country --</option>
																	<c:forEach var="item" items="${getMedNationality}"
																		varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>

															</div>
														</div>
													</div>

													<div class="col-lg-12 col-md-12 col-sm-12">
														<h6 class="mb-25">Alternat Fields</h6>
													</div>
<!-- 													<div class="col-lg-3 col-md-6 col-sm-12"> -->
<!-- 														<div class="input-style-2"> -->
<!-- 															<label>Alternative Mobile Number 1</label> <input -->
<!-- 																type="text" id="alt_mo_no1" name="alt_mo_no1" -->
<!-- 																  maxlength="10" minlength="10"  class="form-control mb2" autocomplete="off" -->
<!-- 																placeholder="Alternate Mobile Number 1"> -->
<!-- 														</div> -->
<!-- 													</div> -->

<!-- 													<div class="col-lg-3 col-md-6 col-sm-12"> -->
<!-- 														<div class="input-style-2"> -->
<!-- 															<label>Alternative Mobile Number 2 </label>  -->
<!-- 															<input type="text" id="alt_mo_no2" name="alt_mo_no2" -->
<!-- 															 maxlength="10" minlength="10"  class="form-control mb2" autocomplete="off" placeholder="Alternate Mobile Number 2"> -->
<!-- 														</div> -->
<!-- 													</div> -->
 
												<div class="col-lg-4 col-sm-12">
														<div class="input-style-2">
															<label>Alternative Mobile Number 1</label> 
															<input type="text" id="alt_mo_no1" name="alt_mo_no1"   maxlength="10" minlength="10"  class="form-control mb2" autocomplete="off"
																placeholder="Alternate Mobile Number 1">
														</div>
													</div>

													<div class="col-lg-4 col-sm-12">
														<div class="input-style-2">
															<label>Alternative Mobile Number 2 </label> 
															<input type="text" id="alt_mo_no2" name="alt_mo_no2" maxlength="10" minlength="10"  class="form-control mb2" autocomplete="off" placeholder="Alternate Mobile Number 2">
														</div>
													</div>
<!-- 													<div class="col-lg-3 col-md-6 col-sm-12"> -->
<!-- 														<div class="input-style-2"> -->
<!-- 															<label>Alternative Email Id 1 </label> <input type="email" -->
<!-- 																id="alt_email_id1" name="alt_email_id1" -->
<!-- 																class="form-control mb2" autocomplete="off"  -->
<!-- 																pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" -->
<!-- 																placeholder="Alternate Email Id 1"> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 													<div class="col-lg-3 col-md-6 col-sm-12"> -->
<!-- 														<div class="input-style-2"> -->
<!-- 															<label>Alternative Email Id 2 </label> <input type="email" -->
<!-- 																id="alt_email_id2" name="alt_email_id2" -->
<!-- 																pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" -->
<!-- 																class="form-control mb2" autocomplete="off" -->
<!-- 																placeholder="Alternate Email Id 2"> -->
<!-- 														</div> -->
<!-- 													</div> -->

														<div class="col-lg-4 col-sm-12">
														<div class="input-style-2">
															<label>Alternative Email Id 1 </label>
															 <input type="email" id="alt_email_id1" name="alt_email_id1" class="form-control mb2" autocomplete="off" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
																placeholder="Alternate Email Id 1">
														</div>
													</div>
													<div class="col-lg-4 col-sm-12">
														<div class="input-style-2">
															<label>Alternative Email Id 2 </label> <input type="email"
																id="alt_email_id2" name="alt_email_id2"
																pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
																class="form-control mb2" autocomplete="off"
																placeholder="Alternate Email Id 2">
														</div>
													</div>
													<div class="col-lg-4 col-sm-12" id="reg_div">
														<div class="select-style-1">
															<label>Registration State <strong class="mandatory">*
															</strong></label>
															<div class="select-position">
																<select name="reg_state" id="reg_state"
																	class="singleselect form-control form-control-lg" >
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${MedStateName}" varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-lg-4 col-md-6 col-sm-12"></div>
													<div class="col-lg-4 col-md-6 col-sm-12"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="accordion-item accordion-itemstyle">
									<h2 class="accordion-header" id="panelsStayOpen-headingTwo">
										<button
											class="accordion-button accordion-itemstylena accordion-primary-button collapsed"
											type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo"
											aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">ADDRESS DETAILS</button>
									</h2>
									<div id="panelsStayOpen-collapseTwo"
										class="accordion-collapse collapse"
										aria-labelledby="panelsStayOpen-headingTwo">
										<div class="accordion-body">
											<div class="card-style mb-30">
												<div class="row">
													<div class="col-lg-12 col-sm-12">
														<h6 class="mb-25">Permanent Address</h6>
													</div>
													<div class="col-lg-12 col-sm-12">
														<div class="select-style-1">
															<label> Permanent Address <strong class="mandatory">*
															</strong></label>
														</div>
													</div>
													<div class="col-lg-4  col-sm-12">
														<div class="input-style-2">
															<input type="text" id="per_address" name="per_address"
																maxlength="100" class="form-control autocomplete"
																 autocomplete="off" placeholder="Address Line 1">
														</div>
													</div>
													<div class="col-lg-4  col-sm-12">
														<div class="input-style-2">
															<input type="text" id="per_address2" name="per_address2"
																maxlength="100" class="form-control autocomplete"  autocomplete="off" placeholder="Address Line 2">
														</div>
													</div>
													<div class="col-lg-4  col-sm-12">
														<div class="input-style-2">
															<input type="text" id="per_address3" name="per_address3"
																maxlength="100" class="form-control autocomplete"  autocomplete="off" placeholder="Address Line 3">
														</div>
													</div>
													<div class="col-lg-4 col-sm-12">
														<div class="select-style-1">
															<label> State <strong class="mandatory">*
															</strong></label>
															<div class="select-position">
																<select name="per_state" id="per_state"
																	class="singleselect form-control form-control-lg">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${MedStateName}" varStatus="num">
																		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-lg-4 col-sm-12">
														<div class="select-style-1">
															<label>District<strong class="mandatory">*
															</strong></label>
															<div class="select-position">
																<select name="per_district" id="per_district"
																	class="singleselect form-control form-control-lg" >
																	<option value="0">--Select--</option>
																</select>
															</div>
														</div>
													</div>
													<div class="col-lg-4 col-sm-12">
														<div class="input-style-2">
															<label> Pin Code<strong class="mandatory">*
															</strong></label> <input type="text" id="per_pincode" name="per_pincode"
																 maxlength="6" minlength="6" class="form-control" autocomplete="off" placeholder="Pin Code">
														</div>
													</div>
												</div>
												<div class="col-lg-12 col-sm-12">
													<div class="row">
														<div class="col-lg-12 col-md-12 col-sm-12">
												<div class="custom-choose-one">
													<div class="input-style-form-check">
														<div class="form-check checkbox-style">
															<input type="checkbox" class="form-check-input" id="check_address" name="check_address"> <label class="form-check-label" for="check_address">Same
																as Permanent Address</label>
														</div>
													</div>
												</div>
											</div>
														<div class="col-lg-12 col-md-12 col-sm-12">
															<h6 class="mb-25">Present Address</h6>
														</div>
													</div>
													<div class="row">
														<div class="col-lg-12 col-sm-12">
															<div class="select-style-1">
																<label> Present Address <strong class="mandatory">*
																</strong></label>
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
															<div class="input-style-2">
																<input type="text" id="pre_address" name="pre_address"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 1">
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
															<div class="input-style-2">
																<input type="text" id="pre_address2" name="pre_address2"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 2">
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
															<div class="input-style-2">
																<input type="text" id="pre_address3" name="pre_address3"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 3">
															</div>
														</div>
													 
														<div class="col-lg-4 col-sm-12">
															<div class="select-style-1">
																<label> State <strong class="mandatory">*
																</strong></label>
																<div class="select-position">
																	<select name="pre_state" id="pre_state" 
																		class="form-control" >
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${MedStateName}" varStatus="num">
																			<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</div>
														 
														<div class="col-lg-4 col-sm-12">
															<div class="select-style-1">
																<label>District<strong class="mandatory">*
																</strong></label>
																<div class="select-position">
																	<select  name="pre_district" id="pre_district"
																	class="form-control" >
																		<option value="0">--Select--</option>
																	</select>
																</div>
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
															<div class="input-style-2">
																<label> Pin Code<strong class="mandatory">*
																</strong></label> 
																<input type="text" id="pre_pincode" name="pre_pincode"
																	maxlength="6" minlength="6" class="form-control" autocomplete="off" 	placeholder="Pin Code">
															</div>
														</div>
													</div>
												</div>
										 
											<div class="col-lg-12 col-md-12 col-sm-12">
											 <h6 class="mb-25">Corresponding Address</h6>
											 </div>	
								 <div class="col-lg-12 col-sm-12">
									 <div class="row">
								 <div class="col-lg-12 col-md-12 col-sm-12">
<!-- 								 <div class="form-check checkbox-style mb-20"> -->
<!-- 							  <input class="form-check-radio" type="radio" id="check_address1"  name="check_address" >  -->
<!-- 							 <label class="form-check-label"> <span class="text-heighlight"> Same as Permanent Address</span> </label></br> -->
<!-- 								<input class="form-check-radio" type="radio" id="check_address2" name="check_address" > -->
<!-- 							 <label class="form-check-label"> <span class="text-heighlight"> Same as Present Address</span> </label></br> -->
<!--  								<input class="form-check-radio" type="radio" id="check_address3" name="check_address"  >  -->
<!-- 							 <label class="form-check-label"> <span class="text-heighlight">Other</span> </label> -->
<!-- 								</div> -->

											<div class="custom-choose-one">
											<div class="row">
												<div class="col-lg-4 col-md-4 col-sm-12">
													<div class="input-style-form-check">
														<div class="form-check radio-style">
															<input type="radio" class="form-check-input" id="check_address1" name="check_address"> <label class="form-check-label" for="check_address1">Same
																as Permanent Address</label>
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-12">
													<div class="input-style-form-check">
														<div class="form-check radio-style">
															<input type="radio" class="form-check-input" id="check_address2" name="check_address"> <label class="form-check-label" for="check_address2">Same
																as Present Address</label>
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-12">
													<div class="input-style-form-check">
														<div class="form-check radio-style">
															<input type="radio" class="form-check-input" id="check_address3" name="check_address" checked="checked">
															<label class="form-check-label" for="check_address3">Other</label>
														</div>
													</div>
												</div>
												<div class="col-lg-12 col-md-12 col-sm-12">
													<input class="form-check-radio" type="hidden" id="chk_add_val" name="chk_add_val" value="">
												</div>
											</div>
										</div>
								<input class="form-check-radio" type="hidden" id="chk_add_val" name="chk_add_val" value="" >
								 </div>
													</div>
												<div class="row">
														<div class="col-lg-12 col-sm-12">
															<div class="select-style-1">
																<label> Corresponding Address <strong class="mandatory">*
																</strong></label>
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
															<div class="input-style-2">
																<input type="text" id="curr_address" name="curr_address"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 1">
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
															<div class="input-style-2">
																<input type="text" id="curr_address2" name="curr_address2"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 2">
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
															<div class="input-style-2">
																<input type="text" id="curr_address3" name="curr_address3"
																	maxlength="100" class="form-control autocomplete"
																	autocomplete="off" placeholder="Address Line 3">
															</div>
														</div>
													 
														<div class="col-lg-4 col-sm-12">
															<div class="select-style-1">
																<label> State <strong class="mandatory">*
																</strong></label>
																<div class="select-position">
																	<select name="curr_state" id="curr_state" 
																		class="form-control">
																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${MedStateName}"
																			varStatus="num">
																			<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
															<div class="select-style-1">
																<label>District<strong class="mandatory">*
																</strong></label>
																<div class="select-position">
																	<select name="curr_district" id="curr_district" 
																		class="form-control" >
																		<option value="0">--Select--</option>
																	</select>
																</div>
															</div>
														</div>
														<div class="col-lg-4 col-sm-12">
															<div class="input-style-2">
																<label> Pin Code<strong class="mandatory">*
																</strong></label> <input type="text" id="curr_pincode" name="curr_pincode"
																	maxlength="6" minlength="6" class="form-control" autocomplete="off" placeholder="Pin Code">
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
						 
								<div class="accordion-item accordion-itemstyle">
									<h2 class="accordion-header" id="panelsStayOpen-headingThree">
										<button class="accordion-button accordion-itemstylena accordion-primary-button collapsed"
											type="button" data-bs-toggle="collapse"
											data-bs-target="#panelsStayOpen-collapseThree"
											aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">NAME OF MEDICAL DEGREE GRADUATE/POSTGRADUATE/DIPLOMA OBTAINED</button>
									</h2>
									<div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse" 	aria-labelledby="panelsStayOpen-headingThree">
										<div class="accordion-body">
											<div class="card-style mb-30">
												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">
															<div class="table-wrapper table-responsive custom-table">
																<table class="table" id="addNameOfMed">
																	<thead>
																		<tr>
														 	<th>Ser No</th>
															<th>Type Of Degree<strong class="mandatory">* </strong></th>
															 <th>Degree Name<strong class="mandatory">* </strong></th>
															 <th>Month and Year<strong class="mandatory">* </strong></th>
															 <th>Name of Institute/Board<strong class="mandatory">* </strong></th>
 															 <th>Attachment<strong class="mandatory">* </strong></th>
															 <th>Action</th>
														 </tr>
																		<!-- end table row-->
																	</thead>
																	<tbody id="att_TbbodyNameMed">
																		<tr id="tr_id_attNameMed">
																			<td class="min-width">
																				<div class="lead">
																					<div class="lead-text">
																						<p>1</p>
																					</div>
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="select-style-1">
																			<!-- <input type="hidden" value="0" id="btopen_close1"> -->
 																					<div class="select-position">
																						<select name="typeOfDegree1" id="typeOfDegree1" class="form-control autocomplete" >
																							<option value="0">--Select--</option>
 																							<c:forEach var="item" items="${TypeOfDegree}" varStatus="num">
																								<option value="${item.id}" name="${item.type_of_degree}">${item.type_of_degree}</option>
																							</c:forEach>
																						</select>
																						<input type="hidden" id="degree_hid1" name="degree_hid1" value="0" class="form-control" autocomplete="off" />
																					</div>
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="select-style-1">
																					<div class="select-position">
																						<select name="DegreeName1" id="DegreeName1" class="form-control autocomplete">
																							<option value="0">--Select--</option>
																						</select>
																							<input type="hidden" id="DegreeName1_hid" name="DegreeName1_hid" value="0"
																							class="form-control" autocomplete="off" />
																					</div>
																				</div>
																			</td>
																			
																			<td class="min-width">
																				<div class="input-style-2">
																					<input type="month" id="monthYearOfDegree1" name="monthYearOfDegree1"
																						class="form-control-sm form-control effect-9 hasDatepicker" />
																							<input type="hidden" id="monthYearOfDegree1_hid" name="monthYearOfDegree1_hid" value=""
																							class="form-control" autocomplete="off" />
																				</div>
																			</td>
<!-- 																			<td class="min-width"> -->
<!-- 																				<div class="input-style-2"> -->
<!-- 																					<input type="text" id="NameOfUniversity1" -->
<!-- 																						name="NameOfUniversity1" value="" class="form-control autocomplete"  -->
<!-- 																						autocomplete="off"  onkeypress="AutoCompleteNameOfUniversity(this);" -->
<!-- 																							maxlength="100"  placeholder="Name of Institute/Board"> -->
<!-- 																						<input type="hidden" id="NameOfUniversity1_hid" name="NameOfUniversity1_hid" value="" class="form-control" autocomplete="off" /> -->
<!-- 																				</div> -->
<!-- 																			</td> -->
																          <td class="min-width">
																				<div class="select-style-1">
 																					<div class="select-position">
																						<select name="NameOfUniversity1" id="NameOfUniversity1" class="form-control autocomplete" >
																							<option value="0">--Select--</option>
 																							<c:forEach var="item" items="${getInstituteList}" varStatus="num">
																								<option value="${item.id}" name="${item.university_name}">${item.university_name}</option>
																							</c:forEach>
																						</select>
																						<input type="hidden" id="NameOfUniversity1_hid" name="NameOfUniversity1_hid" value="0" class="form-control" autocomplete="off" />
																					</div>
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="input-style-2">
											 <input type="button" id="btnmodel1" class="main-btn secondary-btn btn-hover btn-save" value="ATTACHMENT">
												<input type="hidden" id="med_degree_att_old1" name="med_degree_att_old1" class="form-control autocomplete">
												<input type="hidden" id="med_degree_att_new1" name="med_degree_att_new1" class="form-control autocomplete">
										 </div>
									 </td>
						 	<td class="min-width addminusbut">
										 <div class="action">
							 	<ul class="buttons-group mainbtn daobtn">
							 <li><a class="main-btn success-btn btn-hover btn-sm addminusbut" value="ADD" title="ADD" id="id_add_attNameMed1">
							 <i class="lni lni-plus"></i></a></li>
							 </ul>
 								</div>
							 </td>
						 </tr>
					 	<!-- end table row -->
					 </tbody>
				 </table>
 					<input type="hidden" id="count_hidden_att_name_med" name="count_hidden_att_name_med" class="form-control autocomplete" value="1">
					<input type="hidden" id="count_hidden_att_name_med1" name="count_hidden_att_name_med1" class="form-control autocomplete" value="1">
					 <!-- end table -->
			 </div>
 			<!-- end card -->
		 </div>
		 <!-- end col -->
			 </div>
		 <!-- end row -->
						</div>
					</div>
				</div>
			</div>
		</div>
  
								<div class="accordion-item accordion-itemstyle">
									<h2 class="accordion-header" id="panelsStayOpen-headingFive">
										<button class="accordion-button accordion-itemstylena accordion-primary-button collapsed"
											type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseFive"
											aria-expanded="false" aria-controls="panelsStayOpen-collapseFive"> INSTITUTE/OFFICE/CLINIC/HOSPITAL</button>
									</h2>
									<div id="panelsStayOpen-collapseFive" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingFive">
										<div class="accordion-body">
											<div class="card-style mb-30">
												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">
															<div class="table-wrapper table-responsive custom-table">
																<table class="table" id="addHospital">
																	<thead>
																		<tr>
																			<th>Ser No</th>
																			<th>Place of Working<strong class="mandatory"></strong></th>
																			<th>Name of Place<strong class="mandatory"></strong></th>
																			<th>Adjunct Place<strong class="mandatory"></strong></th>
																			<th>Landline No<strong class="mandatory"></strong></th>
																			<th>Mobile No<strong class="mandatory"></strong></th>
																			<th>Email Id<strong class="mandatory"></strong></th>
																			<th>Address<strong class="mandatory"></strong></th>
																			<th>State<strong class="mandatory"></strong></th>
																			<th>District<strong class="mandatory"></strong></th>
																			<th>Authority Type<strong class="mandatory"></strong></th>
																			<th>Name of Responsible Owner<strong class="mandatory"></strong></th>
																			<!-- <th>Date of Practice From</th>
																			<th>Date of Practice To</th> -->
																			<th>Action</th>
																		</tr>
																		<!-- end table row-->
																	</thead>
																	<tbody id="att_TbbodyNameMed">
																		<tr id="tr_id_attNameMed">
																			<td class="min-width">
																				<div class="lead">
																					<div class="lead-text">
																						<p>1</p>
																					</div>
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="select-style-1">
																					<div class="select-position">
																						<select name="place_of_working1" id="place_of_working1"
																							class="form-control autocomplete">
																							<option value="0">--Select--</option>
																							<c:forEach var="item" items="${PlaceOfWorking}" varStatus="num">
																								<option value="${item.id}" name="${item.place_of_working_practitioner}">${item.place_of_working_practitioner}</option>
																							</c:forEach>
																						</select>
																						<input type="hidden" id="working_hid1" name="working_hid1" value="0"
																						class="form-control" autocomplete="off" />
																					</div>
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="input-style-2">
																					<input type="text" class="form-control autocomplete"
																						id="place_of_working_name1" name="place_of_working_name1" maxlength="50" 
																						class="form-control autocomplete" placeholder="Name Of Place" />
																				</div>
																				</div>
																			</td>
													 
																			<td class="min-width">
																				<div class="input-style-2">
																					<input type="text" class="form-control autocomplete"
																						id="adjunct_place1" name="adjunct_place1" maxlength="50"  class="form-control autocomplete" placeholder="Adjunct Place" />
																				</div>
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="input-style-2">
																					<input type="text" class="form-control autocomplete" id="landline1"
																					  maxlength="10" minlength="10" name="landline1" placeholder="Landline No" />
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="input-style-2">
																					<input type="text" class="form-control autocomplete" id="mobileHosp1" name="mobileHosp1"
																					  maxlength="10" minlength="10" placeholder="Mobile No" />
																				</div>
																			</td>
																			 
																			<td class="min-width">
																				<div class="input-style-2">
																					<input type="email" class="form-control autocomplete" id="email1" 
																						pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
																						name="email1" maxlength="50" placeholder="Enter Email" />
																				</div>
																			</td>															
																			<td class="min-width">
																				<div class="input-style-2">
																					<input type="text" id="hos_address1_1" name="hos_address1_1" maxlength="100"
																						class="form-control autocomplete mb2" autocomplete="off" placeholder="Address Line 1">
																					<input type="text" id="hos_address2_1" name="hos_address2_1" maxlength="100" class="form-control autocomplete mb2"
																						autocomplete="off" placeholder="Address Line 2">
																					<input type="text" id="hos_address3_1" name="hos_address3_1" maxlength="100" class="form-control autocomplete mb2s"
																						autocomplete="off" placeholder="Address Line 3">
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="select-style-1">
																					<div class="select-position">
																						<select name="hos_state1" id="hos_state1"
																							class="form-control autocomplete" >
																							<option value="0">--Select--</option>
																							<c:forEach var="item" items="${MedStateName}" varStatus="num">
																								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																							</c:forEach>
																						</select>
																					</div>
																				</div>
																			</td>
																			<td class="min-width">
																				<div class="select-style-1">
																					<div class="select-position">
																						<select name="hos_district1" id="hos_district1"
																							class="form-control autocomplete"><option
																								value="0">--Select--</option>
																						</select>
																			</td>
																			<td class="min-width">
																				<div class="select-style-1">
																					<div class="select-position">
																						<select name="authority_type1" 	id="authority_type1" class="form-control autocomplete">
																							<option value="0">--Select--</option>
																							<c:forEach var="item" items="${NameOfResOwner}" varStatus="num">
																								<option value="${item.id}" name="${item.name_of_res_owner}">${item.name_of_res_owner}</option>
																							</c:forEach>
																						</select>
																						<input type="hidden" id="authority_type1_hid" name="authority_type1_hid" value="0"
																						class="form-control" autocomplete="off" />
																					</div>
																				</div> 
																			</td>
 
																			<td class="min-width">
																				<div class="input-style-2">
																					<input type="text" id="name_of_res_p1" maxlength="50" class="form-control autocomplete" name="name_of_res_p1"  placeholder="Responsible Owner" />
																				</div>
																			</td>
																			<td class="min-width addminusbut">
																				<ul class="buttons-group mainbtn daobtn action">
																					<li>
																					<a class="main-btn success-btn btn-hover btn-sm addminusbut"
																						value="ADD" title="ADD" id="id_add_attHospital1" >
																						<i class="lni lni-plus"></i></a></li>
																				</ul>
																			</td>
																		</tr>
																		<!-- end table row -->
																	</tbody>
																</table>
																<input type="hidden" id="count_hidden_att_name_med" name="count_hidden_att_name_med" class="form-control autocomplete" value="1">
															    <input type="hidden" id="count_hidden_for_new" name="count_hidden_for_new" class="form-control autocomplete" value="">
																<input type="hidden" id="count_hidden_for_old" name="count_hidden_for_old" class="form-control autocomplete" value="">
																<input type="hidden" id="admmisson_dt_hidden_whole" name="admmisson_dt_hidden_whole" class="form-control autocomplete" value="">
																<!-- end table -->
															</div>
															<!-- end card -->
														</div>
														<!-- end col -->
													</div>
													<!-- end row -->
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
 <div id = "chek_confirm" class="custom-d-none">
 <input class="form-input" type="checkbox" id="terms" name="terms"  > 
 <span class="text-heighlight"> I attest that all of the details, including any attachments, are true and accurate.</span> 
</div>
							<ul class="buttons-group mainbtn">
								<li><input type="button"
									class="main-btn success-btn  btn-hover btn-save" id="draft" 
									value="Save as Draft"></li>
								<li><input type="button"
									class="main-btn deactive-btn btn-hover custom-d-none" id="update"
									value="Update" ></li>
								<li>
								<li><input type="submit" class="main-btn secondary-btn btn-hover btnsave custom-d-none" value="Final Submit" id="final_submit" name="final_submit" ></li>
								<li><input type="button" id="renew" class="custom-d-none main-btn secondary-btn btn-hover btnsave " value="Revision" ></li>
								 <li><a href="pract_previewUrl"><input type="button" id="preview"  class="main-btn dark-btn n btn-hover" value="Preview"></a></li>
								<li></li>
							</ul>
 
						</div>
<!-- 						<input type="hidden" id="NRHstatus" name="NRHstatus"> -->
						<input type="hidden" id="Regulationstatus" name="Regulationstatus">
						<input type="hidden" id="p_id" name="p_id" value="${p_id}">
						<input type="hidden" id="parentid" name="parentid" value="${parentid}">
						<input type="hidden" id="count_hidden_att_Hospital" name="count_hidden_att_Hospital" class="form-control autocomplete" value="1">
						 <input type="hidden" id="count_hidden_att_His" name="count_hidden_att_His" class="form-control autocomplete" value="1">
						<input type="hidden" id="SaveDraft" name="SaveDraft" class="form-control autocomplete" value="0">
						<!-- end card -->
<div class="modal fade custom-modal bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="modelWindow">
	<div class="modal-dialog modal-xl modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="myLargeModalLabel">Upload Attachments</h3>
<!-- 				<label class="errorClass">[Maximum file size upto 200 kb and Allowed only *.pdf File]</label> -->
			
						<i data-dismiss="modal" id="closeModel" aria-label="Close" class="bi bi-x-lg"></i>
			</div>
			<div class="modal-header">
				
				<label class="errorClass">  Note : <br> 1)Documents need to Upload : Final Year Marksheet , Provisional Registration Certificate,Internship Completion Certificate ,Provisional Degree Certificate .<br>
				2)Maximum file size upto 200 kb and Allowed only *.pdf File
				</label> 
 				</div>
			 
			<div class="modal-body custom-modal-table">
				<div class="custom-modal-inner">
					<div class="table-wrapper table-responsive custom-table">
						<div id="dynamicDataTable">
						
							<!--                                		//JANKI PDF VIEW -->
<!--                      <div class="row"> -->
<!-- 						<div class="col-md-12 regupractcss"> -->

<!-- 							<div id="my_pdf_viewer" class="custom-d-none"> -->
<!-- 								<div id="canvas_container"> -->
<!-- 									<div id="zoom_controls" align="right" class="regupractzoomcon"> -->

<!-- 										<div class="regupractzoomdiv"> -->
<!-- 											<label class="btn btn-success btn-sm pdfbtnzoom" value=""></label><i -->
<!-- 												id="zoom_in" -->
<!-- 												class="fa fa-search-plus regupractzoomin" title='Zoom in'></i> -->
<!-- 										</div> -->
<!-- 										<br /> -->
<!-- 										<div class="regupractzoomdiv"> -->
<!-- 											<label class="btn btn-success btn-sm pdfbtnzoom" value=""></label><i -->
<!-- 												id="zoom_out" -->
<!-- 												class="fa fa-search-minus regupractzoomin" title='Zoom out'></i> -->
<!-- 										</div> -->
<!-- 									</div> -->
<%-- 									<canvas id="pdf_renderer"></canvas> --%>
<!-- 									<input type="hidden" value="0" id="PicturePDFId" /> <input -->
<!-- 										type="hidden" value="0" id="val1" /> <input type="hidden" -->
<!-- 										value="0" id="fildname1" /> -->
<!-- 								</div> -->

<!-- 								<div class="regupractzoomprevidiv"> -->
<!-- 									<i id="go_previous" -->
<!-- 										class="fa fa-angle-double-left pdfbtnzoom regupractzoomprevi" title='Previous'></i> -->
<!-- 								</div> -->
<!-- 								<input id="current_page" value="1" type="number" -->
<!-- 									 class="regupractcurrent" /> -->
<!-- 								<div class="regupractzoomprevidiv"> -->
<!-- 									<i id="go_next" -->
<!-- 										class="fa fa-angle-double-right pdfbtnzoom regupractgo" title='Next'></i> -->
<!-- 								</div> -->
<!-- 								<div  id="downloadbtn"> -->
<!-- 									<i id="downloadbtn" title="Download" class='fa fa-download regupractdown'></i> -->
<!-- 								</div> -->
								
<!-- 								<div id="downloadbtnview"> -->
<!-- 									<i id="downloadbtnview" title="Download" class='fa fa-download regupractdown'></i> -->
<!-- 								</div> -->
<!-- 								<ul class="buttons-group mainbtn"  id="dlall_pdf"> </ul> -->
 							 							 
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					 </div> -->


             	<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div id="my_pdf_viewer" class="custom-pdf-viewer d-none">										
										<div id="canvas_container">
											<div id="zoom_controls" class="pdf_zoom_control">
											    <div class="zoom_btn">
													<button type="button" class="btn pdfbtnzoom btn-close danger-btn"
														value="" id="btn-close-pdf">
														<i class="lni lni-close" title='Close'></i>
													</button>
												</div>
												<br>
												<div class="zoom_btn">
													<button type="button" class="btn btn-sm pdfbtnzoom"
														value="">
														<i id="zoom_in" class="lni lni-zoom-in" title='Zoom in'></i>
													</button>
												</div>
												<br />
												<div class="zoom_btn">
													<button type="button" class="btn btn-sm pdfbtnzoom"
														value="">
														<i id="zoom_out" class="lni lni-zoom-out" title='Zoom out'></i>
													</button>
												</div>
											</div>
											<canvas id="pdf_renderer"></canvas>
											<input type="hidden" value="0" id="PicturePDFId" /> <input
												type="hidden" value="0" id="val1" /> <input type="hidden"
												value="0" id="fildname1" />
										</div>
										
										<div class="footer_btn">
											<div class="footer_btn_control">
												<i id="go_previous"
													class="lni lni lni-chevron-left pdfbtnzoom pdfbtnzoom-pre"
													title='Previous'></i> <input id="current_page"
													class="pdfbtnzoom-current-page" value="1" type="number" readonly="readonly"  />
											</div>

											<div class="footer_btn_control">
												<i id="go_next"
													class="lni lni lni-chevron-right pdfbtnzoom pdfbtnzoom-next"
													title='Next'> </i>
											</div>
<!-- 											<div class="footer_btn_control footer-down-btn" -->
<!-- 												id="downloadbtn"> -->
<!-- 												<i id="downloadbtn" title="Download" -->
<!-- 													class='lni lni-download pdfbtnzoom pdfbtnzoom-down-btn'></i> -->
<!-- 											</div> -->
										</div>
									</div>
								</div>
						
				 </div>
					</div>
				</div>
			</div>
			<div class="modal fade custom-modal custom-modal-pdf" tabindex="-1"
	aria-labelledby="myLargeModalLabel" id="exampleModal"
	aria-hidden="true">
	<div
		class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Document Preview</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="alert alert-success alert-dismissible">
								<button type="button" class="btn-close" data-bs-dismiss="alert"></button>

							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-12 text-center">

							<div id="pdfmodelcanvas"></div>

						</div>
					</div>
				</div>


			</div>
			<div class="modal-footer" id="modal-footer"></div>


		</div>
	</div>
</div>
			<input type="hidden" id="attachmenttsss_hid1" name="attachmenttsss_hid1" value="0" class="form-control" autocomplete="off" />
		</div>
	</div>
</div>
</form:form>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
	</div>
	<!-- end container -->
</section>
<!-- regulation components end -->

<script nonce="${cspNonce}" type="text/javascript">
function isAadhar(e,evt) {
	if(e.value==0 || e.value=="null" || e.value == null){
		e.value="";
		}

	var bool=isNumber(evt);	
	if(bool){
	  var value = e.value;
	  value = value.replace(/\D/g, "").split(/(?:([\d]{4}))/g).filter(s => s.length >0 ).join("-");
	  e.value=value;
	  return true;
	  }
	  else{
	  return false;}
	}
function isNumber(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	return false;
	}
	return true;
	}
function validationAdd(obj){
 
	if(obj == 0){
		$("#uploadForm").submit();
	}
	else if(obj == 1){
		$("#uploadForm").submit();
	}else {
		return false;
	}
}
	
$(document).ready(function() {
	//alert('${hid}' +'  '+'${login_role}');
	if('${login_role}'=="Student"){
		$("#reg_div").hide();
	}
	 if($("#terms").is(":checked") == true) { 
			$("#final_submit").show();
		}
	
// 	if('${role}'=="Student_NCH"){
// 		$("#ny").hide();
// 	}
	$("#userId").val(${userId}); 
	
    var userId =	${username};
 	$.post('getayusAbhaDatalist?' + key + "=" + value,{userId:userId},function(k) {
 		var aayushid =	k[0]["ayush_id"];
 		var abha_no =	k[0]["abha_no"];
 		var first_name = k[0]["name"];
 		var dob = k[0]["dob"];
 		var aadhar_card = k[0]["aadhar_card"];
 		var email = k[0]["email"];
 		var mo_no = k[0]["mobile_no"];
 		var gender = k[0]["gender"];
 		var admission_date2 = k[0]["fire2"];
 		$("#admmisson_dt_hidden_whole").val(admission_date2);
 		
  		if(aayushid!= null && aayushid.trim()!=""){ 
 			$("#aayushid").val(aayushid);
 		}
 		if(abha_no!= null && abha_no.trim()!=""){ 
 			$("#abha_no").val(abha_no);
 		}
 		if(aadhar_card!= null && aadhar_card.trim()!=""){ 
 			$("#aadhaar_no").val(aadhar_card);
 			$("#aadhaar_no").attr("readonly","readonly");
 		}
 		if(${p_id == 0}){
 			  
 		 if(first_name!= null && first_name.trim()!=" "){ 
 			$("input#first_name").val(first_name);
 		 }
 		 
 		if(dob.length != 10){
			 var  myArray = dob.split("/");
			 var day  ;
			 var month  ;
			 var year  ;
			for(var i  = 0; i < myArray.length; i++){
 				   day = myArray[0];
				   month = myArray[1];
				   year = myArray[2];
  				  if(day.length != 2){
					  day = "0"+day
 				  }
 				  if(month.length != 2){
 					month = "0"+month
				  }
 			}
 			dob =  day+"/"+month+"/"+year
		}
 		else{
 			if(dob!= null && dob.trim()!=""){ 
 	 			$("#dob").val(dob);
 		 		 calculate_age('dob');
 	 		}
 		}
 		
//  		if(admission_date2.length != 10){
// 			 var  myArray = dob.split("/");
// 			 var day  ;
// 			 var month  ;
// 			 var year  ;
// 			for(var i  = 0; i < myArray.length; i++){
// 				debugger;
//  				   day = myArray[0];
// 				   month = myArray[1];
// 				   year = myArray[2];
//   				  if(day.length != 2){
// 					  day = "0"+day
//  				  }
//  				  if(month.length != 2){
//  					month = "0"+month
// 				  }
//  			}
//  			dob =  day+"/"+month+"/"+year
// 		}
// 		else{
// 			if(admission_date2!= null && dob.trim()!=""){ 
// 	 			$("#admission_date").val(admmisson_dt_hidden_whole);
// 	 		}
// 		}
 	  
 		 if(email!= null && email.trim()!=" "){ 
 			$("input#email_id").val(email);
 			
 		 }
 		if(mo_no!= null && mo_no.trim()!=" "){ 
 			$("input#mo_no").val(mo_no);
 		 }
 		if(gender!= null && gender.trim()!=" " && gender == "Female"){ 
 			female = document.getElementById("female");
 			female.checked = true;
 		 }
 		else if(gender!= null && gender.trim()!=" " && gender == "Male"){ 
  		Male = document.getElementById("male");
  		Male.checked = true;
		 }
 		else{
 			other = document.getElementById("other");
 			other.checked = true;
 		}
 	  }
	});
// 	if("${setRegAuth[0].email_id}" != ""){
// 	$("#email_id").val("${setRegAuth[0].email_id}");
// 	$("#email_id").attr("readonly","readonly");
// 	}
// 	if("${setRegAuth[0].aadhaar_no}" != ""){
// 		$("#aadhaar_no").val("${setRegAuth[0].aadhaar_no}");
// 		$("#aadhaar_no").email_idattr("readonly","readonly");
// 		}
// 	if("${setRegAuth[0].name}" != ""){
// 		$("#name").val("${setRegAuth[0].name}");
// 		$("#name").attr("readonly","readonly");
// 		}
	$("#nationality").val(6);
		$.ajaxSetup({
			async : false
		});
		$.ajaxSetup({
			async : false
		});
		datepicketDate('date_of_reg');
		datepicketDate('valid_up_to');
		datepicketDate('date_pract_to1');
		datepicketDate('date_pract_from1');
		datepicketDate('dob');
		if("${setDataCMD.size()}" != 0){
		setAll();	
		$("#final_submit").hide();
		$("#chek_confirm").show();
		
		$("#draft").hide();
		$("#update").show();
		}
		if("${hid}" == "1"){
			$("#draft").hide();
			$("#final_submit").hide();
			$("#chek_confirm").hide();
			$("#update").hide();
		}
		function Pop_Up_Multi_Upload(a) {
			var x = screen.width/2 - 1100/2;
		    var y = screen.height/2 - 900/2;
		    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
			window.onfocus = function () { 
			}
			$("input#popid").val(a);
			document.getElementById('degreepopup_Form').submit();
		}
var valid_dt = '${valid_dt}' ;
var y = valid_dt.substring(0,4);
var m = valid_dt.substring(5,7);
var d = valid_dt.substring(8,10);
var valid_dt1 = d+"/"+m+"/"+y;
var today = new Date();
var yyyy = today.getFullYear();
var mm = today.getMonth() + 1; // Months start at 0!
var dd = today.getDate();
if (dd < 10) dd = '0' + dd;
if (mm < 10) mm = '0' + mm;
today = dd + '/' + mm + '/' + yyyy;
var valid_dt2=valid_dt.split('/').reverse().join('-');  
var today2=today.split('/').reverse().join('-'); 

//PRACTITIONER
   if (valid_dt2  <= today2  
		  && '${hid}' == "7" && ('${role}'=="Practitioner_NCH")
		   ){
	   $("#renew").show();
	   $("#draft").hide();
	   $("#final_submit").hide();
		$("#chek_confirm").hide();
	   $("#update").hide();
		 document.getElementById("first_name").readOnly = true;
		 document.getElementById("nationality").readOnly = true;
		 document.getElementById("father_name").readOnly = true;
		 document.getElementById("dob").readOnly = true;
   }
   else	if("${hid}" == "3" && valid_dt2  >= today2 && valid_dt2!="" && ('${login_role}'=="27")){
			$("#draft").hide();
			$("#final_submit").show();
			$("#chek_confirm").show();
			$("#update").show();
			$("#renew").hide();
		 document.getElementById("first_name").readOnly = true;
		 document.getElementById("father_name").readOnly = true;
		 $("#nationality").attr("readonly","readonly");
		 document.getElementById("dob").readOnly = true;
	}
   if("${hid}" != "3" && valid_dt2  <= today2 && valid_dt2!="" && ('${login_role}'=="27")){
	   datepicketDate('dob');
	}
   if("${hid}" == "4" && ('${login_role}'=="25" || '${login_role}'=="35")){
	   $("#draft").hide();
		$("#final_submit").show();
		$("#chek_confirm").show();
	   $("#update").show();
		$("#renew").hide();
	}
  // alert("hid===="+'${role}')
//     alert("login_role===="+'${login_role}')
   if("${hid}" == "5" && ('${login_role}'=="25" || '${login_role}'=="35")){
	   $("#draft").hide();
		$("#final_submit").hide();
		$("#chek_confirm").hide();
	   $("#update").hide();
		$("#renew").hide();
	}
   if("${hid}" == "6" && ('${login_role}'=="25" || '${login_role}'=="35" || '${login_role}'=="27")){
	   $("#draft").hide();
		$("#final_submit").hide();
		$("#chek_confirm").hide();
	   $("#update").hide();
		$("#renew").hide();
	}
   if("${hid}" == "8" && ('${role}'=="Intern_NCH"  )){
	   $("#draft").hide();
		$("#final_submit").show();
		$("#chek_confirm").show();
	   $("#update").show();
		$("#renew").hide();
   }
		
// 		if("${CheckNRH.size()}" != 0){
// 			$("#NRHstatus").val("${CheckNRH[0]['registration']}");
// 		}else{
// 				$("#NRHstatus").val("0");
// 		}
	});
 
$('#draft').click(function(){
	$("#SaveDraft").val();
});
$('#update').click(function(){
	$("#Regulationstatus").val(0);
});
$('#final_submit').click(function(){
	$("#Regulationstatus").val(1);
});

function validateUptoR(){
	var date_reg = $("#date_of_reg").val();
	if(date_reg == "" || date_reg == "DD/MM/YYYY"){
		alert("Please Select Date Of First Registration First");
	}else{
		$("#valid_up_to").datepicker("option", "minDate", date_reg);
		$("#valid_up_to").val(date_reg);
		$('#valid_up_to').datepicker('option', 'maxDate', null);
	}
}
</script>
<script  nonce="${cspNonce}" type="text/javascript">
	function divN(obj){
		var id = obj.id;
		var sib_id = $("#"+id).parent().parent().next().attr('id');
		var hasC=$("#"+sib_id).hasClass("show");
			$(".panel-collapse").removeClass("show");
			 $('.droparrow').each(function(i, obj) {
				 $("#"+obj.id).attr("class", "droparrow collapsed");
				 });
			if (hasC) {	
			$("#"+id).addClass( " collapsed");		 
			}  else {				
				$("#"+sib_id).addClass("show");	
				$("#"+id).removeClass("collapsed");
		    }
 	}
	function isNumberOnly(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	}
	function mobileNumber1(obj){
		_mobile = obj.value;
		var regExp =/^[6789]\d{9}$/;
	    if(_mobile == '' || !regExp.test(_mobile)){
	        alert('Please Enter Valid Number');
	        $('#'+obj.id).focus();
	        $('#'+obj.id).val('');
	        return false;
	    }
	}
	function mobileNumber2(obj){
		_mobile = obj.value;
		var regExp =/^[6789]\d{9}$/;
	    if(_mobile == '' || !regExp.test(_mobile)){
	        alert('Please Enter Valid Number');
	        $('#'+obj.id).focus();
	        $('#'+obj.id).val("");
	        return false;
	    }
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
	function landlinenumber(obj){
		_mobile = obj.value;
		var regExp =/^[6789]\d{9}$/;
	    if(_mobile == '' || !regExp.test(_mobile)){
	        alert('Please Enter Valid Number');
	        $('#'+obj.id).focus();
	        $('#'+obj.id).val("");
	        return false;
	    }
	}
	function formOpenNameMed(R){
		if(validateAddMoreMedical(R)){
		 $("#addNameOfMed").show();
 			 $("#id_add_attNameMed"+R).hide();
			 $("#att_id_removeNameMed"+R).hide();
			 att=0;
			 att= parseInt(R)+1;
			 if(att < 51){
				 $("input#count_hidden_att_name_med").val(att);//current serial No
				 $("table#addNameOfMed").append('<tr align="center" id="tr_id_attNameMed'+att+'"><td>'+att+'</td>'
				 	+'<td class="min-width"><div class="select-style-1"><div class="select-position">  '
// 				 +'	<input type="hidden" value="0" id="btopen_close'+att+'"> '
				 +'	<select name="typeOfDegree'+att+'" id="typeOfDegree'+att+'" class="form-control autocomplete" >  <option value="0">--Select--</option> <c:forEach var="item" items="${TypeOfDegree}" varStatus="num">	<option value="${item.id}" name="${item.type_of_degree}">${item.type_of_degree}</option> </c:forEach></select></select></div></div></td>'
 				 	+'<td class="min-width"><div class="select-style-1"><div class="select-position"><select name="DegreeName'+att+'" id="DegreeName'+att+'" class="form-control autocomplete">  <option value="0">--Select--</option> </select></div></div></td>' 
 				 	+'<td class="min-width"><div class="input-style-2"><input type="month" id="monthYearOfDegree'+att+'" name="monthYearOfDegree'+att+'" class="form-control-sm form-control effect-9 hasDatepicker">  </div</td>'
 				 	+'<td class="min-width">'
 				 	+'<div class="select-style-1">'
 				 	+'<div class="select-position">'
  					+'<select id="NameOfUniversity'+att+'" i name="NameOfUniversity'+att+'" value="" class="form-control autocomplete" >'
 				 	+'	<option value="0">--Select--</option>'
 				 	+'	<c:forEach var="item" items="${getInstituteList}" varStatus="num">'
 				 	+'	<option value="${item.id}" name="${item.university_name}">${item.university_name}</option>'
 				 	+'</c:forEach>'
					+'	</select>'
					+'</div>'
					+'</div>'
					+'</td>'
 				 	+'<td class="min-width"><div class="input-style-2"><input type="button" id="btnmodel'+att+'" name="btnmodel'+att+'" class="main-btn secondary-btn btn-hover btn-save" value="ATTACHMENT"></div>'
				 	+'<input type="hidden" id="med_degree_att_old'+att+'" name="med_degree_att_old'+att+'" class="form-control autocomplete">'
					+'<input type="hidden" id="med_degree_att_new'+att+'" name="med_degree_att_new'+att+'" class="form-control autocomplete"></td>'
 				 	+'<td class="min-width"><div class="action"><ul class="buttons-group mainbtn daobtn"><li><a class="main-btn success-btn btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_attNameMed'+att+'" ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeNameMed'+att+'" "><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
 				 	+'<input type="hidden" id="degree_hid'+att+'" name="degree_hid'+att+'" value="0" class="form-control" autocomplete="off" /></tr>');
				 medicaldegree(att);
				 check_exs(att);
				 Getchekmonthyear(att);
				}else{
						alert("Please Enter max 50 Quantity");
						 if ( att == 51){
							 att = att-1; 
							 $("#rp_id_remove"+att).show();
						 }	   
				}
		}
	}	
	function check_exs(ser) {
		var Benefit_current= $('#typeOfDegree'+ser).val();
	 for(var i=1;i<ser;i++ ) {
		if($('#typeOfDegree'+i).val() == Benefit_current ) {
	 		alert("Please change Type Of Degree . You have previously used these .");
	 		$('#typeOfDegree'+ser).val("0");
	 		$('#DegreeName'+ser).val("0");
	 		return false;
			}
	}
	return true; 
	}
	
	//no same attachment validation 
	function check_exs_att(R,att) {
		
		var att_current= $('#NameOfAttachment'+R+"_"+att).val();
		 
	 	for(var i=1;i<att;i++ ) {
		if($('#NameOfAttachment'+R+"_"+i).val() == att_current ) {
	 		alert("Please change your Attachment . You have previously uploaded this .");
	 		 $('#NameOfAttachment'+R+"_"+att).val("0");
	 		 
	 		return false;
			}
	}
	return true; 
	}
	
	function formopen_re_NameMed(R){
		var f_att=	$("#degree_hid"+R).val();
 		var result = confirm("Are you sure you want to delete?");
		if (result) {
 		$.post("DeleteMedicalAddmore?" + key + "=" + value, {f_att:f_att}, function(j) {
		 alert("Data Deleted Successfully.")
	 	}); 
		 $("tr#tr_id_attNameMed"+R).remove();
		 att = att-1;
		 $("input#count_hidden_att_name_med").val(att);
		 $("#id_add_attNameMed"+att).show();
		 $("#att_id_removeNameMed"+att).show();	
		 if(att != null && att != "" && att == "${setAddMoreMedicalCMD.size()}")
			 $("#att_id_removeNameMed"+att).hide();	
		}
	}
	 
	$('#btnmodel1').click(function() {
		 //$('#btopen_close1').val(1);
		   $('#modelWindow').modal('show');
		   dynamicTable(1,1);
		});
	$('#closeModel').click(function() {
		   $('#modelWindow').modal('hide');
		});
	$('#closeModel2').click(function() {
		   $('#modelWindow').modal('hide');
		});
	 
	function formOpenHospital(R){
		if(validateAddMoreHospital(R)){
		 $("#addHospital").show();
			 $("#id_add_attHospital"+R).hide();
			 $("#att_id_removeHospital"+R).hide();
			 att=0;
			 att= parseInt(R)+1;
			 if(att < 51){
				 $("input#count_hidden_att_Hospital").val(att);//current serial No
				 $("table#addHospital").append('<tr align="center" id="tr_id_attHospital'+att+'"><td>'+att+'</td>'
				 	+'<td  class="min-width"><div class="select-style-1"><div class="select-position"><select name="place_of_working'+att+'" id="place_of_working'+att+'" class="form-control autocomplete"><option value="0">--Select--</option>  <c:forEach var="item" items="${PlaceOfWorking}" varStatus="num"> <option value="${item.id}"  name="${item.place_of_working_practitioner}">${item.place_of_working_practitioner}</option> </c:forEach> </select>'
					+'<input type="hidden" id="working_hid'+att+'" name="working_hid'+att+'" value="0" class="form-control" autocomplete="off" />'
					+'</div></div></td>'
				 	+'<td class="min-width"><div class="input-style-2"><input type="text" class="form-control autocomplete" placeholder="Name Of Place" maxlength="50"  class="" id="place_of_working_name'+att+'"	name="place_of_working_name'+att+'" /></div></td>'
				 	+'<td class="min-width"><div class="input-style-2"><input type="text" class="form-control autocomplete" placeholder="Adjunct Place" maxlength="50"  class="" id="adjunct_place'+att+'"	name="adjunct_place'+att+'" /></div></td>'
				 	+'<td class="min-width"><div class="input-style-2"><input type="text" class="form-control autocomplete"   placeholder="Landline No" maxlength="10" minlength="10" id="landline'+att+'" name="landline'+att+'" /></div></td>' /* <input type="text" class="form-control autocomplete"  placeholder="Landline No" style="width: 90%" id="landline'+att+'" name="landline'+att+'" /> </td>' */
				 	+'<td class="min-width"><div class="input-style-2"><input type="text" class="form-control autocomplete" id="mobileHosp'+att+'" name="mobileHosp'+att+'" placeholder="Mobile No" maxlength="10" minlength="10"/></div></td>'
				 	+'<td class="min-width"><div class="input-style-2"><input type="text" class="form-control autocomplete" placeholder="Email Id" maxlength="50" id="email'+att+'" name="email'+att+'"></div></td>'
				 	+'<td class="min-width"><div class="input-style-2"><input type="text" id="hos_address1_'+att+'" name="hos_address1_'+att+'" maxlength="100" class="form-control autocomplete mb2" autocomplete="off" placeholder="Address Line 1"><input type="text" id="hos_address2_'+att+'"	name="hos_address2_'+att+'" maxlength="100" class="form-control autocomplete mb2" autocomplete="off"	placeholder="Address Line 2"><input type="text" id="hos_address3_'+att+'"	 name="hos_address3_'+att+'" maxlength="100" class="form-control autocomplete mb2" autocomplete="off"	placeholder="Address Line 3" ></div></td>'
				 	+'<td class="min-width"><div class="select-style-1"><div class="select-position"><select name="hos_state'+att+'" id="hos_state'+att+'" class="form-control autocomplete"><option value="0">--Select--</option>	<c:forEach var="item" items="${MedStateName}" varStatus="num"> <option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach> </select></div></div></td>'
				 	+'<td class="min-width"><div class="select-style-1"><div class="select-position"><select name="hos_district'+att+'"  id="hos_district'+att+'"	class="form-control autocomplete"><option value="0">--Select--</option></select></div></div></td>'
				 	+'<td class="min-width"><div class="select-style-1"><div class="select-position"><select name="authority_type'+att+'" id="authority_type'+att+'" class="form-control autocomplete"><option value="0">--Select--</option> <c:forEach var="item" items="${NameOfResOwner}" varStatus="num"> <option value="${item.id}" 	name="${item.name_of_res_owner}">${item.name_of_res_owner}</option>	</c:forEach></select></div></div> </td>'
				 	+'<td class="min-width"><div class="input-style-2"><input  class="form-control autocomplete"  placeholder="Responsible Owner" maxlength="50" type="text" id="name_of_res_p'+att+'"  name="name_of_res_p'+att+'" /></div></td>' 
				 	+'<td class="min-width"><div class="action"><ul class="buttons-group mainbtn daobtn"><li><a class="main-btn success-btn btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_attHospital'+att+'"  ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeHospital'+att+'"><i class="lni lni-trash-can"></i></a></li></ul></div></td>' /* <a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_attHospital'+att+'" onclick="formOpenHospital('+att+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 0px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeHospital'+att+'" onclick="formopen_re_Hospital('+att+');"><i class="fa fa-trash"></a></td>'  */
				    +'</tr>');
				 hospital(att);
			 }else{
						alert("Please Enter max 50 Quantity");
						 if ( att == 51){
							 att = att-1; 
							 $("#rp_id_remove"+att).show();
						 }	   
				}
		}
	}
	function formopen_re_Hospital(R){
		
		var f_att=	$("#working_hid"+R).val();
 	 	var result = confirm("Are you sure you want to delete?");
 	    if(result){
	     	$.post("DeleteInstituteAddmore?" + key + "=" + value, {f_att:f_att}, function(j) {
	    		 alert("Data Deleted Successfully.")
	    	 	});
	     	
	     	 $("tr#tr_id_attHospital"+R).remove();
			 att = att-1;
			 $("input#count_hidden_att_Hospital").val(att);
			 $("#id_add_attHospital"+att).show();
			 $("#att_id_removeHospital"+att).show();	
			 if(att != null && att != "" && att == "${setAddMoreHospCMD.size()}")
			 $("#att_id_removeHospital"+"${setAddMoreHospCMD.size()}").hide();	
  	    }
		 
	}
	function getDegreeName(obj,R){
		var typeofdegree = $("#typeOfDegree"+R).val();
 		$.post('getDegreedetails?' + key + "=" + value,{typeofdegree:typeofdegree},function(k) {
			var options = '';
			for (var i = 0; i < k.length; i++) {
				options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'+ k[i].degree_name + '</option>';
			}
			$("#DegreeName"+R).html(options);
		});
	}
	
	function getDegreeNameAddMore(obj,R){
		var typeofdegree = $("#typeOfDegree"+R).val();
 		$.post('getDegreedetails?' + key + "=" + value,{typeofdegree:typeofdegree},function(k) {
			var options = '';
			for (var i = 0; i < k.length; i++) {
				options += '<option value="'+k[i].id+'" name="' + k[i].degree_name+ '" >'+ k[i].degree_name + '</option>';
			}
			$("#DegreeName"+R).html(options); 
		});
	}
	function save_validate() { 
		if($("#mo_no").val() !="0" ){
			alert("Please Enter the Valid Mobile No");
			$("#mo_no").focus();
			return false;
		}
	}
	
	function Validate() {
		/* personal details */
		if ($("#first_name").val().trim() == "") {
			alert("Please Enter Full Name");
			$("input#first_name").focus();
			return false;
		}
	    var today = new Date();
	    var ad = $("#admmisson_dt_hidden_whole").val();
	    var birthDate = new Date(ad);
	    var age = today.getFullYear() - birthDate.getFullYear();
	    var m = today.getMonth() - birthDate.getMonth();
	    if(age==4){ 
	     if(m<=6){ 
	     alert("Student Will be Eligible After 4.5 Years To Register Iteself");
	     return false;
	     }
	    }else if(age<4){ 
	    alert("Student Will be Eligible After 4.5 Years To Register Iteself");
	    return false;
	    }
		
		if ($("#father_name").val().trim() == "") {
			alert("Please Enter Father's Full Name");
			$("input#father_name").focus();
			return false;
		}
		if( $("#dob").val() == "" ||  $("#dob").val() == "DD/MM/YYYY"){
			alert("Please Enter Valid Date of Birth");
			$("#dob").focus();
			return false;
	   	}
		var yrr=$("#yrr").val()
		 if(yrr < 18 || yrr == "" || yrr == "0"){
				 alert("Age Should Be Greater Than 17 Years");
		    	$("#dob").focus();
		    	return false;
		    }
		var a = $('input:radio[name=gender]:checked').val();
		if(a == undefined) {
			alert("Please Select Gender");
			return false;
		}
		if ($("#aadhaar_no").val().trim() == "") {
			alert("Please Enter Aadhaar Number");
			$("input#aadhaar_no").focus();
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
		if ($("#upload_img_hid").val().trim() == "") {
			alert("Please Select Photo");
			$("input#photo_path").focus();
			return false;
		}
		if ($("#upload_img_forV").val().trim() == "1") {
			alert("Please Select Photo");
			$("input#upload_img_forV").focus();
			return false;
		}
		if ($("#nationality").val().trim() == "0") {
			alert("Please Select Nationality");
			$("input#nationality").focus();
			return false;
		}
		if ($("#per_address").val().trim() == "") {
			alert("Please Enter Permanent Address Line 1");
			$("#per_address").focus();
			return false;
		}
		if ($("#per_address2").val().trim() == "") {
			alert("Please Enter Permanent Address Line 2");
			$("#per_address2").focus();
			return false;
		}
		if ($("#per_address3").val().trim() == "") {
			alert("Please Enter Permanent Address Line 3");
			$("#per_address3").focus();
			return false;
		}
		if ($("#per_state").val().trim() == "0") {
			alert("Please Select Permanent State");
			$("select#per_state").focus();
			return false;
		}
		if('${role}'!="Student_NCH"){
		if ($("#reg_state").val().trim() == "0") {
			alert("Please Select Registration State");
			$("select#reg_state").focus();
			return false;
		}
		}
		if ($("#per_district").val().trim() == "0") {
			alert("Please Select Permanent District");
			$("select#per_district").focus();
			return false;
		}
		if ($("#per_pincode").val().trim() == "") {
			alert("Please Enter Permanent Pincode");
			$("input#per_pincode").focus();
			return false;
		}
		if($("#per_pincode").val() =="000000"){
			alert("Please Enter the Valid Permanent Pin Code");
			$("#per_pincode").focus();
			return false;
		}
		if($("#per_pincode").val() =="0"){
			alert("Please Enter the Valid Permanent Pin Code");
			$("#per_pincode").focus();
			return false;
		}
		if ($("#pre_address").val().trim() == "") {
			alert("Please Enter Present Address Line 1");
			$("input#pre_address").focus();
			return false;
		}
		if ($("#pre_address2").val().trim() == "") {
			alert("Please Enter Present Address Line 2");
			$("input#pre_address2").focus();
			return false;
		}
		if ($("#pre_address3").val().trim() == "") {
			alert("Please Enter Present Address Line 3");
			$("input#pre_address3").focus();
			return false;
		}
		if ($("#pre_state").val().trim() == "0") {
			alert("Please Select Present State");
			$("select#pre_state").focus();
			return false;
		}
		if ($("#pre_district").val().trim() == "0") {
			alert("Please Select Present District");
			$("select#pre_district").focus();
			return false;
		}
		if ($("#pre_pincode").val().trim() == "") {
			alert("Please Enter Present Pincode");
			$("input#pre_pincode").focus();
			return false;
		}
		if($("#pre_pincode").val() =="000000"){
			alert("Please Enter the Valid Permanent Pin Code");
			$("#pre_pincode").focus();
			return false;
		}
		if($("#pre_pincode").val() =="0"){
			alert("Please Enter the Valid Permanent Pin Code");
			$("#pre_pincode").focus();
			return false;
		}
		if ($("#curr_address").val().trim() == "") {
		alert("Please Enter Corresponding Address line1");
		$("input#curr_address").focus();
		return false;
		}
		if ($("#curr_address2").val().trim() == "") {
		alert("Please Enter Corresponding Address line2");
		$("input#curr_address2").focus();
		return false;
		}
		if ($("#curr_address3").val().trim() == "") {
		alert("Please Enter Corresponding Address line3");
		$("input#curr_address3").focus();
		return false;
		}
		if ($("select#curr_state").val() == "0") {
			alert("Please Select Your Corresponding State");
			$("select#curr_state").focus();
			return false;
			}
		if ($("select#curr_district").val() == "0") {
		alert("Please Select Your Corresponding District");
		$("select#curr_district").focus();
		return false;
		}
		if ($("#curr_pincode").val().trim() == "") {
		alert("Please Enter Present Corresponding Pincode");
		$("input#curr_pincode").focus();
		return false;
		}
		if($("#curr_pincode").val() =="000000"){
			alert("Please Enter the Valid Permanent Pin Code");
			$("#curr_pincode").focus();
			return false;
		}
		if($("#curr_pincode").val() =="0"){
			alert("Please Enter the Valid Permanent Pin Code");
			$("#curr_pincode").focus();
			return false;
		}
		 var ccc=    $('#count_hidden_att_doc1').val() ;
	     var b = $("#attachmentDocument1_3").val();
   		 var bb = $("#attachmentDoc_hid1_3").val(); 
   		 
	   		 if(ccc == 2){
	   			alert("Please Select Attachment File5675765.")
	   			return false;
	   		 }
	   		 else if(ccc == 3){
 	   			 if((b == null || b == undefined || b == "" && (bb == undefined || bb == null || bb == "" ) ) ){
	   				alert("Please Select Attachment File.")
					return false;
	   			 }
	   		 }
		//confirm
		//  Please check this box if you agree to the terms and conditions.
		// Please indicate that you accept the Terms and Conditions
		    if($("#terms").is(":checked") != true){
		  alert("Please check this box if you agree to the terms and conditions.");
 		  return false;
	   }
//			------------------------name of medical degree
		for(exp = 1; exp <= $('#count_hidden_att_name_med').val(); exp++){
			if($("#typeOfDegree"+exp).val()=='0'){
				alert("Please Select Type Of Degree"+exp+" Row");
				$("#typeOfDegree"+exp).focus();
				return false;
			}
			if($("#DegreeName"+exp).val()=="0"){
				alert("Please Select Degree Name"+exp+" Row");
				$("#DegreeName"+exp).focus();
				return false;
			}
			if($("#monthYearOfDegree"+exp).val()==""){
				alert("Please Enter Month And Year"+exp+" Row");
				$("#monthYearOfDegree"+exp).focus();
				return false;
			}
			if($("#NameOfUniversity"+exp).val()=="0"){
				alert("Please Enter Name Of Institute/Board"+exp+" Row");
				$("#NameOfUniversity1"+exp).focus();
				return false;
			}
// 			var btopen_close = $("#btopen_close"+exp).val();
// 		 	if(btopen_close == "0" || btopen_close == 0){
// 						alert("Please Select Name of Attachment In "+exp+" Row");
// 						return false;
// 				}
	 
// 		-----subchilde
   	for(a1 = 1; a1 <= $('#count_hidden_att_doc'+exp).val(); a1++){
	 	if($("#NameOfAttachment"+a1+"_"+1).val() == ""   ){
					alert("Please Select Name of Attachment In "+a1+" Row");
					$("#name_of_attachment"+exp+"_"+a1).focus();
					return false;
			}
			if($("#attachmentDocument"+a1+"_"+1).val().trim()=='' && $("#attachmentDoc_hid"+a1+"_"+1).val().trim()==''){
						alert("Please Select Attachment File In "+a1+" Row");
						$("#name_of_degree"+exp+"_"+a1).focus();
						return false;
			}
		 }
	}
//institute/office
 
for(exp = 1; exp <= $('#count_hidden_att_Hospital').val(); exp++){

if($("#place_of_working"+exp).val()=='0' && $("#place_of_working_name"+exp).val()=="" && $("#adjunct_place"+exp).val().trim() == "" && $("#landline"+exp).val().trim() == "")
{
}
else
{
	
if($("#place_of_working"+exp).val()=='0'){
	alert("Please Select Place Of Working In Row "+exp+"");
	$("#place_of_working"+exp).focus();
	return false;
}
if($("#place_of_working_name"+exp).val()==""){
	alert("Please Select Name Of Place In Row "+exp+"");
	$("#place_of_working_name"+exp).focus();
	return false;
}

if ($("#adjunct_place"+exp).val().trim() == "") {
	alert("Please Enter Name Of Adjunct Place In Row "+exp+"");
	$("input#adjunct_place"+exp).focus();
	return false;
}
if ($("#landline"+exp).val().trim() == "") {
	alert("Please Enter Landline No In Row "+exp+"");
	$("input#landline"+exp).focus();
	return false;
}
if ($("#mobileHosp"+exp).val().trim() == "") {
	alert("Please Enter Mobile No In Row "+exp+"");
	$("input#mobileHosp"+exp).focus();
	return false;
}
if ($("#email"+exp).val().trim() == "") {
	alert("Please Enter Email Id In Row "+exp+"");
	$("input#email"+exp).focus();
	return false;
}
if ($("#hos_address1_"+exp).val().trim() == "") {
	alert("Please Enter AddressLine In Row 1_"+exp+"");
	$("input#hos_address1_"+exp).focus();
	return false;
}
if ($("#hos_address2_"+exp).val().trim() == "") {
	alert("Please Enter AddressLine In Row 2_"+exp+"");
	$("input#hos_address2_"+exp).focus();
	return false;
}
if ($("#hos_address3_"+exp).val().trim() == "") {
	alert("Please Enter AddressLine In Row 3_"+exp+"");
	$("input#hos_address3_"+exp).focus();
	return false;
}
if ($("select#hos_state"+exp).val() == "0") {
	alert("Please Select Your State In Row "+exp+"");
	$("select#hos_state"+exp).focus();
	return false;
}
if ($("select#hos_district"+exp).val() == "0") {
	alert("Please Select Your District In Row "+exp+"");
	$("select#hos_district"+exp).focus();
	return false;
}
if ($("select#authority_type"+exp).val() == "0") {
	alert("Please Select Name Of Authority Type In Row "+exp+"");
	$("select#authority_type"+exp).focus();
	return false;
}
if ($("#name_of_res_p"+exp).val().trim() == "") {
	alert("Please Enter Name Of Responsible Owner In Row "+exp+"");
	$("input#name_of_res_p"+exp).focus();
	return false;
   }
 }
}
return true;
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
	
// 	30-08-2022
	function check_email_addmore(email) {
		 var email1 = $("#"+email.id).val();
		if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
		}else{
			alert("Please Enter Valid Email Id");
			$("input#"+email.id).focus();
			$("input#"+email.id).val('');
			return false ;
		}
	}
	
	function checkgmail1(email1) {
		 document.getElementById("email1").innerHTML="";
		if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
		}else{
			alert("Please Enter Valid Email Id");
			$("input#email1").focus();
			$("input#email1").val('');
			return false ;
		}
	}
	function checkalterenet(email1) {
		 document.getElementById("alt_email_id1").innerHTML="";
		var pattern=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(email1.match(pattern)) {
		}else{
			alert("Please Enter Valid Alternative Email Address 1");
			$("input#alt_email_id1").focus();
			$("input#alt_email_id1").val('');
			return false ;
		}
	}
	function checkalterenet2(email1) {
		 document.getElementById("alt_email_id2").innerHTML="";
		var pattern=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(email1.match(pattern)) {
		}else{
			alert("Please Enter Valid Alternative Email Address 2");
			$("input#alt_email_id2").focus();
			$("input#alt_email_id2").val('');
			return false ;
		}
	}
	 
	//pending
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
	
	function FunctionState(val){
		var roleid = $("#"+val.id).val();
		$.post('getDistrictOnstate?' + key + "=" + value,{roleid:roleid},function(k) {
			var options = '';
			for (var i = 0; i < k.length; i++) {
				options += '<option value="'+k[i].district_id+'" name="' + k[i].district_name+ '" >'+ k[i].district_name + '</option>';
			}
			$("#per_district").html(options);
			$("#pre_district").html(options);
		});		
}
	function getDistrictper() {
		var selval = $("#per_state").val();
		$("select#per_district").empty();
		$ .post( "getDistrictOnstate?" + key + "=" + value, { selval : selval },
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
	function getDistrictpre() {
		var selval = $("#pre_state").val();
		$("select#pre_district").empty();
		$ .post( "getDistrictOnstate?" + key + "=" + value,
						{ selval : selval },
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
										+ j[i].district_name + '</option>';
							}
							$("select#pre_district").html(options);
							
						});
	}
	function getDistrictcurr() {
		var selval = $("#curr_state").val();
		$("select#curr_district").empty();
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
							$("select#curr_district").html(options);
							
						});
	}
	
	function getDistricthos(R) {
		var selval = $("#hos_state"+R).val();
		$ .post( "getDistrictOnstate?" + key + "=" + value,
						{ selval : selval },
						function(j) {
							var options = '<option value="' + "0" + '">'
							+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
										+ j[i].district_name + '</option>';
							}
							$("select#hos_district"+R).html(options);
							
						});
	}
	var valid_dt = '${valid_dt}' ;
	var today = new Date();
	
	function setAll(){
		var valid_dt2 = valid_dt.split('/').reverse().join('-');
		var today2 = today.toISOString().substring(0,10); 

		var nrh = '${setDataCMD[0]['nrh_en_no']}' ;
		var ayu = '${setDataCMD[0]['ayush_id']}' ;
  
		if(nrh != ""){
			$("#nr").show();
			$("#nrh").text(nrh);
		}
		if(ayu != ""){
			$("#ay").show();
			$("#ayu").text(ayu);
		}
		
		if("${setDataCMD[0]['first_name']}" != null && "${setDataCMD[0]['first_name']}" != ""){
			$("#first_name").val("${setDataCMD[0]['first_name']}");
			if (valid_dt2  <= today2  && '${hid}' == "3"){
				document.getElementById("first_name").readOnly = true;
				   }else{
						if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
							document.getElementById("first_name").readOnly = true;
				   }
		}
		if("${setDataCMD[0]['father_name']}" != null && "${setDataCMD[0]['father_name']}" != ""){
			$("#father_name").val("${setDataCMD[0]['father_name']}");
			if (valid_dt2  <= today2  && '${hid}' == "3"){
				document.getElementById("father_name").readOnly = true;
				   }else{
						if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
							document.getElementById("father_name").readOnly = true;
				   }
		}
		if("${setDataCMD[0]['dob']}" != null && "${setDataCMD[0]['dob']}" != ""){
			$("#dob").val("${setDataCMD[0]['dob']}");
			if (valid_dt2  <= today2  && '${hid}' == "3"){
				document.getElementById("dob").readOnly = true;
				   }else{
						if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
							document.getElementById("dob").readOnly = true;
				   }
		}
		calculate_age("dob");
		if("${setDataCMD[0]['gender']}" != null && "${setDataCMD[0]['gender']}" != ""){
			$("input[name = 'gender'][value='${setDataCMD[0]['gender']}']").click();
		}
		if("${setDataCMD[0]['mo_no']}" != null && "${setDataCMD[0]['mo_no']}" != "0"){
			$("#mo_no").val("${setDataCMD[0]['mo_no']}");
		}
		if("${setDataCMD[0]['email_id']}" != null && "${setDataCMD[0]['email_id']}" != ""){
			$("#email_id").val("${setDataCMD[0]['email_id']}");
		}
		if("${setDataCMD[0]['aadhaar_no']}" != null && "${setDataCMD[0]['aadhaar_no']}" != ""){
			$("#aadhaar_no").val("${setDataCMD[0]['aadhaar_no']}");
			if (valid_dt2  <= today2  && '${hid}' == "3"){
				   }else{
						if($("#draft").is(":hidden") || $("#update").is(":hidden") || $("#final_submit").is(":hidden")){
						}
				   }
		}
		
		if("${setDataCMD[0]['per_state']}" != null && "${setDataCMD[0]['per_state']}" != ""){
			$("#per_state").val("${setDataCMD[0]['per_state']}");
		}
		if("${setDataCMD[0]['reg_state']}" != null && "${setDataCMD[0]['reg_state']}" != ""){
			$("#reg_state").val("${setDataCMD[0]['reg_state']}");
		}
		getDistrictper();
		if("${setDataCMD[0]['per_district']}" != null && "${setDataCMD[0]['per_district']}" != ""){
			$("#per_district").val("${setDataCMD[0]['per_district']}");
		}
		if("${setDataCMD[0]['per_address_details1']}" != null && "${setDataCMD[0]['per_address_details1']}" != ""){
			$("#per_address").val("${setDataCMD[0]['per_address_details1']}");
		}
		if("${setDataCMD[0]['per_address_details2']}" != null && "${setDataCMD[0]['per_address_details2']}" != ""){
			$("#per_address2").val("${setDataCMD[0]['per_address_details2']}");
		}
		if("${setDataCMD[0]['per_address_details3']}" != null && "${setDataCMD[0]['per_address_details3']}" != ""){
			$("#per_address3").val("${setDataCMD[0]['per_address_details3']}");
		}
		if("${setDataCMD[0]['per_pincode']}" != null && "${setDataCMD[0]['per_pincode']}" != ""){
			$("#per_pincode").val("${setDataCMD[0]['per_pincode']}");
		}
		if("${setDataCMD[0]['pre_address_details1']}" == "${setDataCMD[0]['per_address_details1']}" && "${setDataCMD[0]['pre_address_details2']}" == "${setDataCMD[0]['per_address_details2']}"  && "${setDataCMD[0]['pre_address_details3']}" == "${setDataCMD[0]['per_address_details3']}" && "${setDataCMD[0]['pre_state']}" == "${setDataCMD[0]['per_state']}" && "${setDataCMD[0]['per_district']}" == "${setDataCMD[0]['pre_district']}"   && "${setDataCMD[0]['per_pincode']}" == "${setDataCMD[0]['pre_pincode']}")
		{
			$("#check_address").click();
		}
		$("#chk_add_val").val(${setDataCMD[0]['check_address']});
		
		if("${setDataCMD[0]['pre_state']}" != null && "${setDataCMD[0]['pre_state']}" != ""){
			$("#pre_state").val("${setDataCMD[0]['pre_state']}");
		}
		getDistrictpre();
		if("${setDataCMD[0]['pre_district']}" != null && "${setDataCMD[0]['pre_district']}" != ""){
			$("#pre_district").val("${setDataCMD[0]['pre_district']}");
		}
		if("${setDataCMD[0]['pre_address_details1']}" == "${setDataCMD[0]['per_address_details1']}" && "${setDataCMD[0]['pre_address_details2']}" == "${setDataCMD[0]['per_address_details2']}"  && "${setDataCMD[0]['pre_address_details3']}" == "${setDataCMD[0]['per_address_details3']}" && "${setDataCMD[0]['pre_state']}" == "${setDataCMD[0]['per_state']}" && "${setDataCMD[0]['per_district']}" == "${setDataCMD[0]['pre_district']}"   && "${setDataCMD[0]['per_pincode']}" == "${setDataCMD[0]['pre_pincode']}")
		{
			$("#check_address").click();
		}
		if("${setDataCMD[0]['pre_address_details1']}" == "${setDataCMD[0]['per_address_details1']}" && "${setDataCMD[0]['pre_address_details2']}" == "${setDataCMD[0]['per_address_details2']}"  && "${setDataCMD[0]['pre_address_details3']}" == "${setDataCMD[0]['per_address_details3']}" && "${setDataCMD[0]['pre_state']}" == "${setDataCMD[0]['per_state']}" && "${setDataCMD[0]['per_district']}" == "${setDataCMD[0]['pre_district']}"   && "${setDataCMD[0]['per_pincode']}" == "${setDataCMD[0]['pre_pincode']}")
		{
			$("#check_address").prop('checked',false);
		}
		if("${setDataCMD[0]['per_address_details1']}" != null && "${setDataCMD[0]['per_address_details1']}" != ""){
			$("#curr_address").val("${setDataCMD[0]['per_address_details1']}");
		}
		if("${setDataCMD[0]['per_address_details2']}" != null && "${setDataCMD[0]['per_address_details2']}" != ""){
			$("#curr_address2").val("${setDataCMD[0]['per_address_details2']}");
		}
		if("${setDataCMD[0]['per_address_details3']}" != null && "${setDataCMD[0]['per_address_details3']}" != ""){
			$("#curr_address3").val("${setDataCMD[0]['per_address_details3']}");
		}
		if("${setDataCMD[0]['pre_address_details1']}" == "${setDataCMD[0]['per_address_details1']}" && "${setDataCMD[0]['pre_address_details2']}" == "${setDataCMD[0]['per_address_details2']}"  && "${setDataCMD[0]['pre_address_details3']}" == "${setDataCMD[0]['per_address_details3']}" && "${setDataCMD[0]['pre_state']}" == "${setDataCMD[0]['per_state']}" && "${setDataCMD[0]['per_district']}" == "${setDataCMD[0]['pre_district']}"   && "${setDataCMD[0]['per_pincode']}" == "${setDataCMD[0]['pre_pincode']}")
		{
			$("#check_address").click();
		}
		if("${setDataCMD[0]['curr_state']}" != null && "${setDataCMD[0]['curr_state']}" != ""){
			$("#curr_state").val("${setDataCMD[0]['curr_state']}");
		}
		getDistrictcurr();
		if("${setDataCMD[0]['curr_district']}" != null && "${setDataCMD[0]['curr_district']}" != ""){
			$("#curr_district").val("${setDataCMD[0]['curr_district']}");
		}
		if("${setDataCMD[0]['curr_pincode']}" != null && "${setDataCMD[0]['curr_pincode']}" != ""){
			$("#curr_pincode").val("${setDataCMD[0]['curr_pincode']}");
		}
// 		------------------for corrospondind select
		if("${setDataCMD[0]['check_address']}" == "0"){
			$("#check_address1").click();
		}
		if("${setDataCMD[0]['check_address']}" == "1"){
			$("#check_address2").click();
		}
		if("${setDataCMD[0]['check_address']}" == "2"){
			$("#check_address3").click();
		}
		if("${setDataCMD[0]['curr_address']}" != null && "${setDataCMD[0]['curr_address']}" != ""){
			$("#curr_address").val("${setDataCMD[0]['curr_address']}");
		}
		if("${setDataCMD[0]['curr_address2']}" != null && "${setDataCMD[0]['curr_address2']}" != ""){
			$("#curr_address2").val("${setDataCMD[0]['curr_address2']}");
		}
		if("${setDataCMD[0]['curr_address3']}" != null && "${setDataCMD[0]['curr_address3']}" != ""){
			$("#curr_address3").val("${setDataCMD[0]['curr_address3']}");
		}
		if("${setDataCMD[0]['curr_state']}" != null && "${setDataCMD[0]['curr_state']}" != ""){
			$("#curr_state").val("${setDataCMD[0]['curr_state']}");
		}
		if("${setDataCMD[0]['curr_district']}" != null && "${setDataCMD[0]['curr_district']}" != ""){
			$("#curr_district").val("${setDataCMD[0]['curr_district']}");
		}
		if("${setDataCMD[0]['curr_pincode']}" != null && "${setDataCMD[0]['curr_pincode']}" != ""){
			$("#curr_pincode").val("${setDataCMD[0]['curr_pincode']}");
		}
		if("${setDataCMD[0]['pre_address_details1']}" != null && "${setDataCMD[0]['pre_address_details1']}" != ""){
			$("#pre_address").val("${setDataCMD[0]['pre_address_details1']}");
		}
		if("${setDataCMD[0]['pre_address_details2']}" != null && "${setDataCMD[0]['pre_address_details2']}" != ""){
			$("#pre_address2").val("${setDataCMD[0]['pre_address_details2']}");
		}
		if("${setDataCMD[0]['pre_address_details3']}" != null && "${setDataCMD[0]['pre_address_details3']}" != ""){
			$("#pre_address3").val("${setDataCMD[0]['pre_address_details3']}");
		}
		if("${setDataCMD[0]['pre_pincode']}" != null && "${setDataCMD[0]['pre_pincode']}" != ""){
			$("#pre_pincode").val("${setDataCMD[0]['pre_pincode']}");
		}
		if("${setDataCMD[0]['alt_mo_no1']}" != null && "${setDataCMD[0]['alt_mo_no1']}" != "0"){
			$("#alt_mo_no1").val("${setDataCMD[0]['alt_mo_no1']}");
		}
		if("${setDataCMD[0]['alt_mo_no2']}" != null && "${setDataCMD[0]['alt_mo_no2']}" != "0"){
			$("#alt_mo_no2").val("${setDataCMD[0]['alt_mo_no2']}");
		}
		if("${setDataCMD[0]['alt_email_id1']}" != null && "${setDataCMD[0]['alt_email_id1']}" != ""){
			$("#alt_email_id1").val("${setDataCMD[0]['alt_email_id1']}");
		}
		if("${setDataCMD[0]['alt_email_id2']}" != null && "${setDataCMD[0]['alt_email_id2']}" != ""){
			$("#alt_email_id2").val("${setDataCMD[0]['alt_email_id2']}");
		}
		if("${setDataCMD[0]['photo_path']}" != null && "${setDataCMD[0]['photo_path']}" != ""){
			$("#upload_img_hid").val("${setDataCMD[0]['photo_path']}");
			$("#img_up").text("Image Uploaded");
			
		}
		if("${setDataCMD[0]['date_of_reg']}" != null && "${setDataCMD[0]['date_of_reg']}" != ""){
			$("#date_of_reg").val("${setDataCMD[0]['date_of_reg']}");
			validateUptoR();
		}
		if("${setDataCMD[0]['registration_for_type']}" != null && "${setDataCMD[0]['registration_for_type']}" != ""){
			$("input[name = 'registration_for'][value='${setDataCMD[0]['registration_for_type']}']").click();
			if("${setDataCMD[0]['registration_for_type']}" == "1"){
				$("#valid").show();
				if("${setDataCMD[0]['valid_up_to']}" != null && "${setDataCMD[0]['valid_up_to']}" != ""){
					$("#valid_up_to").val("${setDataCMD[0]['valid_up_to']}");
				}	
			}
		}
		//ADDMORE MEDICAL
		if("${setAddMoreMedicalCMD.size()}" > 0){
			
			$("#count_hidden_att_name_med").val("${setAddMoreMedicalCMD.size()}");
			$("#count_hidden_att_name_med1").val("${setAddMoreMedicalCMD.size()}");
			var i = 1;
			<c:forEach var="j" items="${setAddMoreMedicalCMD}" varStatus="num">
			var id = "${j[0]}";
			var name_of_institute = "${j[1]}";
			var month_and_year_of_degree = "${j[2]}";
			var type_of_degree = "${j[3]}";
			var degree_name = "${j[4]}";
			var c_status = "${j[5]}";
			var degree_hid = id;
// 			if(c_status == "1"){
// 				$("#DegreeName"+i).attr("readonly","readonly");
// 			 	$("#typeOfDegree"+i).attr("readonly","readonly");
// 			 	$("#monthYearOfDegree"+i).attr("readonly","readonly");
// 			 	$("#NameOfUniversity"+i).attr("readonly","readonly");
// 			 	$("#btnmodel"+i).attr("readonly","readonly");
// 			}
 
	 		if(type_of_degree != null && type_of_degree.trim() != ""){
	 			$("#typeOfDegree"+i).val(type_of_degree);
			   if (valid_dt2  <= today2  && '${hid}' == "3"){
			$("#typeOfDegree"+i).attr("readonly","readonly");
			   }else{
					if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden")){
						$("#typeOfDegree"+i).attr("readonly","readonly");
					}
			   }
			   
			getDegreeNameAddMore("typeOfDegree"+i,i);
	}else{
		$("#typeOfDegree"+i).val(0);
	}
	 		if(degree_hid != null && degree_hid != "0"){
				$("#degree_hid"+i).val(degree_hid);
	 		}
	 		if(degree_name != null && degree_name.trim() != ""){
			$("#DegreeName"+i).val(degree_name);
			   if (valid_dt2  <= today2  && '${hid}' == "3"){
			$("#DegreeName"+i).attr("readonly","readonly");
			   }else{
					if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
						$("#DegreeName"+i).attr("readonly","readonly");
			   }
		}else{
			$("#DegreeName"+i).val(0);
		}
		if(name_of_institute != null && name_of_institute.trim() != ""){
			$("#NameOfUniversity"+i).val(name_of_institute);
			   if (valid_dt2  <= today2  && '${hid}' == "3"){
			$("#NameOfUniversity"+i).attr("readonly","readonly");
			   }else{
					if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
				   $("#NameOfUniversity"+i).attr("readonly","readonly");
			   }
		}else{
			$("#NameOfUniversity"+i).val("");
		} 
		 
		if(month_and_year_of_degree != null && month_and_year_of_degree.trim() != ""){
			$("#monthYearOfDegree"+i).val(month_and_year_of_degree);
			   if (valid_dt2  <= today2  && '${hid}' == "3"){
			$("#monthYearOfDegree"+i).attr("readonly","readonly");
			}else{
				if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
					$("#monthYearOfDegree"+i).attr("readonly","readonly");
		   }
		}
		 
		if("${setAddMoreMedicalCMD.size()}" > i)
			$("#id_add_attNameMed"+i).click();	
				if("${setAddMoreMedicalAttachmentChildCMD.size() > 0}"){
				var oldcount = "${setAddMoreMedicalAttachmentChildCMD.size()}";
				var k = 1;
				<c:forEach var="k" items="${setAddMoreMedicalAttachmentChildCMD}" varStatus="num">
				var at_id = "${k['id']}";
				var parent_id = "${k['parent_id']}";
				var path_of_att = "${k['attachment']}";
				var name_of_att = "${k['name_of_attachment']}";
				var attachmenttsss_hid = at_id;
				if(parent_id == id){
					dynamicTable(i,k);
					if(k > 1){
						$("#id_add_attDoc"+i+"_"+(k-1)).click();
					}
					$("#f_att"+i+"_"+k).val(at_id);
					 
					if(path_of_att == ""){
						 $("#file_upld"+i+"_"+k).text("No File Uploded");
					}
					else{
						$("#dbtn"+i+"_"+k).show();
						$("#file_upld"+i+"_"+k).text("File Uploded");
					}
					
					if(name_of_att != null && name_of_att.trim() != ""){
						$("#NameOfAttachment"+i+"_"+k).val(name_of_att);
						   if (valid_dt2  <= today2  && '${hid}' == "3"){
								$("#NameOfAttachment"+i+"_"+k).attr("readonly","readonly");
						   }else{
								if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
									$("#NameOfAttachment"+i+"_"+k).attr("readonly","readonly");
						   }
					}
					if(path_of_att != null && path_of_att.trim() != ""){
						$("#attachmentDoc_hid"+i+"_"+k).val(path_of_att);
						   if (valid_dt2  <= today2  && '${hid}' == "3"){
						$("#attachmentDoc_hid"+i+"_"+k).attr("readonly","readonly");
						   }else{
								if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
									$("#attachmentDoc_hid"+i+"_"+k).attr("readonly","readonly");

						   }
					}
					if (valid_dt2  <= today2  && '${hid}' == "3"){
						$("#attachmentDocument"+i+"_"+k).attr("readonly","readonly");
					}else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
							 $("#attachmentDocument"+i+"_"+k).attr("readonly","readonly");
					   }
					   if(attachmenttsss_hid != null && attachmenttsss_hid != "0"){
							$("#attachmenttsss_hid"+i).val(attachmenttsss_hid);

				 		}
					$("#count_hidden_att_doc"+i).val(k);
					$("#oldattachmenttsss_hid"+i).val(k); 
					k++;
				}

				</c:forEach>
				}
				   if (valid_dt  <= today  && '${hid}' == "3"){
						var temp = k-1;
						$("#att_id_removeattDoc"+i+"_"+temp).hide();	
				   }
				i++;
		 		</c:forEach>
		 	   if (valid_dt2  <= today2  && '${hid}' == "3"){

				 $("#att_id_removeNameMed"+"${setAddMoreMedicalCMD.size()}").hide();	
		 	   }
			}
		//ADDMORE HOSPITAL
		if("${setAddMoreHospCMD.size()}" > 0){
			$("#count_hidden_att_Hospital").val("${setAddMoreHospCMD.size()}");
			$("#count_hidden_att_His").val("${setAddMoreHospCMD.size()}");
			var i = 1;
			<c:forEach var="j" items="${setAddMoreHospCMD}" varStatus="num">
			var id = "${j[0]}";
			var place_of_working = "${j[1]}";
			var landline = "${j[2]}";
			var email = "${j[3]}";
			var authority_type = "${j[4]}";
			var name_of_res_p = "${j[5]}";
			var hos_address1 = "${j[6]}";
			var hos_address2 = "${j[7]}";
			var hos_address3 = "${j[8]}";
			var hos_state = "${j[9]}";
			var hos_district = "${j[10]}";
			var place_of_working_name = "${j[13]}";
			var adjunct_place = "${j[15]}";
			var mobile_no = "${j[14]}";
			var working_hid = id;
			if(place_of_working != null || place_of_working != ""){
				$("#place_of_working"+i).val(place_of_working);
				 if (valid_dt2  <= today2  && '${hid}' == "3"){
						$("#place_of_working"+i).attr("readonly","readonly");
						   }else{
								if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
									$("#place_of_working"+i).attr("readonly","readonly");
						   }
			}
			if(place_of_working_name != null || place_of_working_name != ""){
				$("#place_of_working_name"+i).val(place_of_working_name);
				if (valid_dt2  <= today2  && '${hid}' == "3"){
					$("#place_of_working_name"+i).attr("readonly","readonly");
					   }else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
								$("#place_of_working_name"+i).attr("readonly","readonly");
					   }
			}
			if(working_hid != null && working_hid != "0"){
				$("#working_hid"+i).val(working_hid);

	 		}
			if(adjunct_place != null || adjunct_place != ""){
				$("#adjunct_place"+i).val(adjunct_place);
				if (valid_dt2  <= today2  && '${hid}' == "3"){
					$("#adjunct_place"+i).attr("readonly","readonly");
					   }else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
								$("#adjunct_place"+i).attr("readonly","readonly");
					   }
			}
			if(landline != null || landline != ""){
				$("#landline"+i).val(landline);
				if (valid_dt2  <= today2  && '${hid}' == "3"){
					$("#landline"+i).attr("readonly","readonly");
					   }else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
								$("#landline"+i).attr("readonly","readonly");
					   }
			}
			if(mobile_no != null || mobile_no != ""){
				$("#mobileHosp"+i).val(mobile_no);
				if (valid_dt2  <= today2  && '${hid}' == "3"){
					$("#mobileHosp"+i).attr("readonly","readonly");
					   }else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
								$("#mobileHosp"+i).attr("readonly","readonly");
					   }
			}
			if(email != null || email != ""){
				$("#email"+i).val(email);
				if (valid_dt2  <= today2  && '${hid}' == "3"){
					$("#email"+i).attr("readonly","readonly");
					   }else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
								$("#email"+i).attr("readonly","readonly");
					   }
			}
			
			if(authority_type != null || authority_type != ""){
				$("#authority_type"+i).val(authority_type);
				if (valid_dt2  <= today2  && '${hid}' == "3"){
					$("#authority_type"+i).attr("readonly","readonly");
					   }else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
								$("#authority_type"+i).attr("readonly","readonly");
					   }
			}
			
			if(hos_address1 != null || hos_address1 != ""){
				$("#hos_address1_"+i).val(hos_address1);
				if (valid_dt2  <= today2  && '${hid}' == "3"){
					$("#hos_address1_"+i).attr("readonly","readonly");
					   }else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
								$("#hos_address1_"+i).attr("readonly","readonly");

					   }
			}
			if(hos_address2 != null || hos_address2 != ""){
				$("#hos_address2_"+i).val(hos_address2);
				if (valid_dt2  <= today2  && '${hid}' == "3"){
					$("#hos_address2_"+i).attr("readonly","readonly");
					   }else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
								$("#hos_address2_"+i).attr("readonly","readonly");

					   }
			}
			
			if(hos_address3 != null || hos_address3 != ""){
				$("#hos_address3_"+i).val(hos_address3);
				if (valid_dt2  <= today2  && '${hid}' == "3"){
					$("#hos_address3_"+i).attr("readonly","readonly");
				   }else{
						if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
							$("#hos_address3_"+i).attr("readonly","readonly");

				   }
			}
			if(hos_state != null || hos_state != ""){
				$("#hos_state"+i).val(hos_state);
				if (valid_dt2  <= today2  && '${hid}' == "3"){
					$("#hos_state"+i).attr("readonly","readonly");
					   }else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
								$("#hos_state"+i).attr("readonly","readonly");

					   }
			}
			getDistricthos(i);
			if(hos_district != null || hos_district != ""){
				$("#hos_district"+i).val(hos_district);
				if (valid_dt2  <= today2  && '${hid}' == "3"){
					$("#hos_district"+i).attr("readonly","readonly");
					   }else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
								$("#hos_district"+i).attr("readonly","readonly");
					   }
			}
			if(name_of_res_p != null || name_of_res_p != ""){
				$("#name_of_res_p"+i).val(name_of_res_p);
				if (valid_dt2  <= today2  && '${hid}' == "3"){
					$("#name_of_res_p"+i).attr("readonly","readonly");
					   }else{
							if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
								$("#name_of_res_p"+i).attr("readonly","readonly");
					   }
			}
 
		if("${setAddMoreHospCMD.size()}" > i)
		$("#id_add_attHospital"+i).click();	
			i++;
	 		</c:forEach>
	 		if (valid_dt2  <= today2  && '${hid}' == "3"){
				 $("#att_id_removeHospital"+"${setAddMoreHospCMD.size()}").hide();	
		 	   }else{
					if($("#draft").is(":hidden") && $("#update").is(":hidden") && $("#final_submit").is(":hidden"))
						 $("#att_id_removeHospital"+"${setAddMoreHospCMD.size()}").hide();	
			   }
		}
		 
		if($("#per_address").val() == "" ||  $("#per_address2").val() == "" ||  $("#per_address3").val() == "" ||  $("#per_state").val() == "0" 
			||  $("#per_district").val() == "0" ||  $("#per_pincode").val() == ""	){ 
			$("#check_address").prop('checked',false);
			$("#pre_state").attr('readonly',false);
			$("#pre_state").val("0");
			$("#pre_state").attr('readonly',false);
			$("#pre_district").attr('readonly',false);
			$("#pre_district").val("0");
			$("#pre_district").attr('readonly',false);
			$("#pre_address").attr('readonly',false);
			$("#pre_address2").attr('readonly',false);
			$("#pre_address3").attr('readonly',false);
			$("#pre_pincode").attr('readonly',false);
		}
		if($("#curr_address").val() == "" ||  $("#curr_address2").val() == "" ||  $("#curr_address3").val() == "" ||  $("#curr_state").val() == "0" 
			||  $("#curr_district").val() == "0" ||  $("#curr_pincode").val() == ""	){ 
			$("#check_address3").prop('checked',true);
			other_address();
		}
	}
	function photoValidate(){
		var path = $("#photo_path").val();
		$("#upload_img_hid").val(path); 
	}
	function copy_address() {
		if($("#check_address").is(":checked") == true){
			$("#pre_address").val($("#per_address").val());
			$("#pre_address").attr('readonly',true);
			
			$("#pre_address2").val($("#per_address2").val());
			$("#pre_address2").attr('readonly',true);
			
			$("#pre_address3").val($("#per_address3").val());
			$("#pre_address3").attr('readonly',true);
			
			$("#pre_state").val($("#per_state").val());
			$('#pre_state').trigger('change');
			
			$("#pre_district").val($("#per_district").val());
			$("#pre_district").change();
			$("#pre_district").attr('readonly',true);
			
			$("#pre_pincode").val($("#per_pincode").val());
			$("#pre_pincode").attr('readonly',true);
			
			$("#pre_state").val($("#per_state").val());
			$('#pre_state').change();
			$("#pre_state").attr('readonly',true);
			getDistrictpre();
			
// 			$("#pre_state").attr('style','pointer-events: none; touch-action: none; background: rgb(233, 236, 239);');
			$("#pre_state").attr('tabindex','-1');
			
			$("#pre_district").val($("#per_district").val());
			$("#pre_district").change();
			$("#pre_district").attr('readonly',true);
			
// 			$("#pre_district").attr('style','pointer-events: none; touch-action: none; background: rgb(233, 236, 239);');
			$("#pre_district").attr('tabindex','-1');
			
		}
		
		if($("#check_address").is(":checked") == false){
			$("#pre_address").val("");
			$("#pre_address").attr('readonly',false);
			
			$("#pre_address2").val("");
			$("#pre_address2").attr('readonly',false);
			
			$("#pre_address3").val("");
			$("#pre_address3").attr('readonly',false);
			
			$("#pre_state").val("0");
			$("#pre_state").attr('readonly',false);
// 			$("#pre_state").attr('style','');
			
			
			$("#pre_district").val("0");
			$("#pre_district").attr('readonly',false);
// 			$("#pre_district").attr('style','');
			
			$("#pre_pincode").val("");
			$("#pre_pincode").attr('readonly',false);
		}
	}

function copy_permanent_address() {
		 
	if($("#check_address1").is(":checked") == true){
		$("#curr_address").val($("#per_address").val());
		$("#curr_address").attr('readonly',true);
		
		$("#curr_address2").val($("#per_address2").val());
		$("#curr_address2").attr('readonly',true);
		
		$("#curr_address3").val($("#per_address3").val());
		$("#curr_address3").attr('readonly',true);
		
		$("#curr_state").val($("#per_state").val());
		$("#curr_state").change();
		$("#curr_state").attr('readonly',true);
		
		$("#curr_state").attr('tabindex','-1');

		$("#curr_district").val($("#per_district").val());
		$("#curr_district").change();
		$("#curr_district").attr('readonly',true);
		
		$("#curr_district").attr('tabindex','-1');

		$("#curr_pincode").val($("#per_pincode").val());
		$("#curr_pincode").attr('readonly',true);
		
		$("#chk_add_val").val("0");
	}
	
	if($("#check_address1").is(":checked") == false){
		$("#curr_address").val("");
		$("#curr_address").attr('readonly',false);
		
		$("#curr_address2").val("");
		$("#curr_address2").attr('readonly',false);
		
		$("#curr_address3").val("");
		$("#curr_address3").attr('readonly',false);
		
		$("#curr_state").val("0");
		$("#curr_state").attr('readonly',false);
		
		$("#curr_district").val("0");
		$("#curr_district").attr('readonly',false);
		
		$("#curr_pincode").val("");
		$("#curr_pincode").attr('readonly',false);
	 }
	}

function copy_present_address() {
	 
	if($("#check_address2").is(":checked") == true){
		  
		$("#curr_address").val($("#pre_address").val());
		$("#curr_address").attr('readonly',true);
		
		$("#curr_address2").val($("#pre_address2").val());
		$("#curr_address2").attr('readonly',true);
		
		$("#curr_address3").val($("#pre_address3").val());
		$("#curr_address3").attr('readonly',true);
		
		$("#curr_state").val($("#pre_state").val());
		$("#curr_state").change();
		$("#curr_state").attr('readonly',true);
		$("#curr_state").attr('tabindex','-1');

		$("#curr_district").val($("#pre_district").val());
		$("#curr_district").change();
		$("#curr_district").attr('readonly',true);
		$("#curr_district").attr('tabindex','-1');

		$("#curr_pincode").val($("#pre_pincode").val());
		$("#curr_pincode").attr('readonly',true);
		$("#chk_add_val").val("1");
		
	}
	
	if($("#check_address2").is(":checked") == false){
		$("#curr_address").val("");
		$("#curr_address").attr('readonly',false);
		
		$("#curr_address2").val("");
		$("#curr_address2").attr('readonly',false);
		
		$("#curr_address3").val("");
		$("#curr_address3").attr('readonly',false);
		
		$("#curr_state").val("0");
		$("#curr_state").attr('readonly',false);
		
		$("#curr_district").val("0");
		$("#curr_district").attr('readonly',false);
		
		$("#curr_pincode").val("");
		$("#curr_pincode").attr('readonly',false);
	}
}

function other_address() {
		$("#curr_address").val("");
 		$("#curr_address").attr('readonly',false);
		
		$("#curr_address2").val("");
 		$("#curr_address2").attr('readonly',false);
		
		$("#curr_address3").val("");
  		$("#curr_address3").attr('readonly',false);
		
		$("#curr_state").val("0");
		$("#curr_state").attr('readonly',false);
		
		$("#curr_district").val("0");
  		$("#curr_district").attr('readonly',false);

		$("#curr_pincode").val("");
  		$("#curr_pincode").attr('readonly',false);
  		
  		$("#chk_add_val").val("2");
}

function changeCurrespongAddress() {
	if(document.getElementById('check_address2').checked == true) {   
		$("#check_address2").prop("checked", false);
		 
			$("#curr_address").val("");
			$('#curr_address').attr('readonly', false);
			
			$("#curr_address2").val("");
			$('#curr_address2').attr('readonly', false);
			 
			$("#curr_address3").val("");
			$('#curr_address3').attr('readonly', false);
			
			$("#curr_state").val("0");
			$("#curr_state").attr('readonly',false);
			
			$("#curr_district").val("0");
	  		$("#curr_district").attr('readonly',false);
			$("#curr_pincode").val("");
			$('#curr_pincode').attr('readonly', false);
		}
}

	function changeAddress() {
		
		if($("#check_address").is(":checked") == true){
			$("#check_address").prop("checked", false);
			
			$("#pre_address").val("");
			$('#pre_address').attr('readonly', false);
			
			$("#pre_address2").val("");
			$('#pre_address2').attr('readonly', false);
			 
			$("#pre_address3").val("");
			$('#pre_address3').attr('readonly', false);
			
			$("#pre_state").val("0");
			$("#pre_state").attr('readonly',false);
			
			$("#pre_district").val("0");
	  		$("#pre_district").attr('readonly',false);
	  		
			$("#pre_pincode").val("");
			$('#pre_pincode').attr('readonly', false);
		}
		if(document.getElementById('check_address1').checked == true) {   
			
		$("#check_address1").prop("checked", false);
		 
			$("#curr_address").val("");
			$('#curr_address').attr('readonly', false);
			
			$("#curr_address2").val("");
			$('#curr_address2').attr('readonly', false);
			 
			$("#curr_address3").val("");
			$('#curr_address3').attr('readonly', false);
			
			$("#curr_state").val("0");
			$("#curr_state").attr('readonly',false);
			
			$("#curr_district").val("0");
	  		$("#curr_district").attr('readonly',false);
	  		
			$("#curr_pincode").val("");
			$('#curr_pincode').attr('readonly', false);
		}
	}
	 
	function renewFunction(){ 
		if(validateAddMoreMedical(1)){
		$("#Regulationstatus").val(1);
	 var form = new FormData($("#uploadForm")[0]);
		 $.ajax({
		        type: "POST",
		        enctype: "multipart/form-data",
		        url: 'Renew_Data_Action?' + key + "=" + value,
		        data: form,
		        processData: false,
		        contentType: false,
		        cache: false,
		        timeout: 600000,
		        success: function (data) {
		        	 if(data=="0"){
				      	  alert("Your Record for Revision has been Forwarded Successfully. ");
					        	    location.reload();
					          }
		        },
		        error: function (e) {
		          console.log("ERROR : ", e);
		        },
		    });
		 }
	}
 function fileValidation() {
            var fileInput = document.getElementById('file');
             var filePath = fileInput.value;
             // Allowing file type
            var allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;
            if (!allowedExtensions.exec(filePath)) {
                alert('Invalid file type');
                fileInput.value = '';
                return false;
            }
            else
            {
                // Image preview
                if (fileInput.files && fileInput.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function(e) {
                        document.getElementById(
                            'imagePreview').innerHTML =
                            '<img src="' + e.target.result
                            + '"/>';
                    };
                     
                    reader.readAsDataURL(fileInput.files[0]);
                }
            }
        }       
	 
	function formOpenattDoc(R,index){
	 
		 $("#addAttDoc"+R).show();

			 $("#id_add_attDoc"+R+"_"+index).hide();
			 $("#att_id_removeattDoc"+R+"_"+index).hide();
			 
			 att=0;
			 att= parseInt(index)+1;
			
			 var seq = ""+R+"_"+att+"";

			 if(att < 51){
// 				 $("input#count_hidden_att_doc"+R).val(att);//current serial No
// 				 $("table#addAttDoc"+R).append('<tr align="center" id="tr_id_attDoc'+seq+'"><td>'+att+'</td>'
// 				 	+'<td class="min-width">'
// 					+' <div class="input-style-2">'
// 					+'<input type="text" mexlength="30" id="NameOfAttachment'+seq+'" name="NameOfAttachment'+seq+'" value="" class="form-control autocomplete" '
// 					+'autocomplete="off"  placeholder="Name of Attachment"></div>'
// 					+'<input type="hidden" mexlength="30" id="f_att'+seq+'" name="f_att'+seq+'" '
// 					+'value="" class="form-control autocomplete" autocomplete="off"   ></div>'
// 					+'	</td>'
// 				 	+'<td class="min-width"><div class="input-style-2"><input type="file" id="attachmentDocument'+seq+'" name="attachmentDocument'+seq+'" accept=".pdf"  class="form-control"><input type="hidden" id="attachmentDoc_hid'+seq+'" name="attachmentDoc_hid'+seq+'" class="form-control"><span class="errorClass" id="attachmentDoc_file_lbl'+seq+'"></span> <span class="tikClass" id="attachmentDoc_file_lbltik'+seq+'"></div></td>' 
// 				 	+'<td class="min-width"><div class="action"><ul class="buttons-group mainbtn daobtn"><li><a class="main-btn success-btn btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_attDoc'+seq+'" name = "id_add_attDoc'+seq+'" ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeattDoc'+seq+'"><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
// 			   	 +'<input type="hidden" id="attachmenttsss_hid'+att+'" name="attachmenttsss_hid'+att+'"  class="form-control" autocomplete="off" /></tr>');
				 
				 $("input#count_hidden_att_doc"+R).val(att);//current serial No
				 $("table#addAttDoc"+R).append('<tr align="center" id="tr_id_attDoc'+seq+'"><td>'+att+'</td>'
						 
					+'<td class="min-width">'
					 +'	 <div class="select-style-1 regupractaddmore">'
					 +'	 <div class="select-position">'
// 					+'<input type="text" mexlength="30" id="NameOfAttachment'+seq+'" name="NameOfAttachment'+seq+'" value="" class="form-control autocomplete"  autocomplete="off"  placeholder="Name of Attachment">'
                    //janki yash
                     +'<input type="hidden" mexlength="30" id="f_att'+seq+'" name="f_att'+seq+'"  value="" class="form-control autocomplete" autocomplete="off"   >'
					 +'	<select name="NameOfAttachment'+seq+'" id="NameOfAttachment'+seq+'" class="form-control autocomplete" >  '
					 +' <option value="0">--Select--</option> 	'
					 +' <option value="Leaving Certificate/Date Of Birth Certificate" name="Final Year Marksheet"> Leaving Certificate/Date Of Birth Certificate</option>  '
//  					 +' <option value="Date Of Birth Certificate" name="Provisional Registration Certificate">Date Of Birth Certificate</option>  '
 					 +' <option value="Neet Marksheet" name="Internship Completion Certificate">Neet Marksheet</option>  '
 					 +' <option value="Profession Marksheets" name="Provisional Degree Certificate">Profession Marksheets</option>  '
 					 +' <option value="Final Year Marksheet" name="Final Year Marksheet"> Final Year Marksheet</option>  '
					 +' <option value="Provisional Registration Certificate" name="Provisional Registration Certificate">Provisional Registration Certificate</option>  '
					 +' <option value="Internship Completion Certificate" name="Internship Completion Certificate">Internship Completion Certificate</option>  '
					 +' <option value="Provisional Degree Certificate" name="Provisional Degree Certificate">Provisional Degree Certificate</option>  '
					 +'</select>'
					 +'</div>'
					 +' </div>'
					 +' </td>' 
					 
					+'<td class="min-width"><div class="input-style-2 regupractaddmoretd">'
				 	+'<input type="file" id="attachmentDocument'+seq+'" name="attachmentDocument'+seq+'" accept=".pdf"  class="form-control">'
				 	+'<input type="hidden" id="attachmentDoc_hid'+seq+'" name="attachmentDoc_hid'+seq+'" class="form-control">'
					+' <label id="file_upld'+seq+'" name="file_upld'+seq+'" value= " ">No File Uploded</label>' 
				 	+'<span class="errorClass" id="attachmentDoc_file_lbl'+seq+'"></span> '
				 	+'<span class="tikClass" id="attachmentDoc_file_lbltik'+seq+'"></div>'
				 	+'</td>' 
				 	
					//file_upld1_1
// 				 	+'<td class="min-width addminusbut">'
// 	  				+' <label id="file_upld'+seq+'" name="file_upld'+seq+'" value= " ">No File Uploded</label>' 
// 	  				+'</td> '
// 	  				+'<input type="text" id="file_id1" name="file_id1"></li></ul>'
				 	
	  				+'<td class="min-width addminusbut"> '
	  				+'<ul class="buttons-group mainbtn action daobtn"> '
	  				+'<li><a class="main-btn success-btn btn-hover btn-sm addminusbut"  '
	  				+'id="downloadbtn'+att+'" title="Download"> '
	  				+'<i class="bi bi-file-pdf"></i></a> '
	  				+'</td> '
	  				+'<td class="min-width addminusbut">'
				 	+'<ul class="buttons-group mainbtn action">'
				 	+'<li><a class="main-btn success-btn-outline btn-hover btn-sm addminusbut" '
				 	+' id="downloadbtn'+seq+'" title="Download">'
//					 	+'onclick="download_file_view('+att+');" id="downloadbtnview' +att+ '" title="Downlaod">'
				 	+'<i class="bi bi-file-pdf"></i></a>'
				 	+'<input type="hidden" id="file_id'+att+'" name="file_id'+att+'"></li></ul>'
				 	+' </td>'
				 	+'<td class="min-width">'
				 	+'<div class="action">'
				 	+'<ul class="buttons-group mainbtn daobtn">'
				 	+'<li>'
				 	+'<a class="main-btn success-btn btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_attDoc'+seq+'" name = "id_add_attDoc'+seq+'" >'
				 	+'<i class="lni lni-plus"></i> </a>'
				 	+'</li>'
 				 	+'<li>'
				 	+'<a class="main-btn danger-btn btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_removeattDoc'+seq+'"><i class="lni lni-trash-can"></i>'
				 	+'</a>'
				 	+'</li>'
				 	+'</ul>'
				 	+'</div>'
				 	+'</td>'
			   	    +'<input type="hidden" id="attachmenttsss_hid'+att+'" name="attachmenttsss_hid'+att+'"  class="form-control" autocomplete="off" />'
			   	    +'</tr>');
				 
					 attachmentt(R,att);
					 attachmenttforparentthisseq(seq);
					 download(att);
					 attachmentforindex(R,att);
				 
					$("#med_degree_att_new"+R).val(att);
					$("#Newattachmenttsss_hid"+R).val(att);
				}else{
						alert("Please Enter max 50 Quantity");
						 if ( att == 51){
							 att = att-1; 
							 $("#att_id_removeattDoc"+att).show();
						 }	   
				}
		}
		
	function formopen_re_attDoc(R,index){
		var f_att=	$("#f_att"+R+"_"+att).val();
		var result = confirm("Want to delete?");
		if (result) {
		 
		$.post("DeleteMedAttachment?" + key + "=" + value, {f_att:f_att}, function(j) {
			 alert("Data Deleted Successfully.")
			location.reload();
			});
		 
		 $("tr#tr_id_attDoc"+R+"_"+index).remove();
		 att = index-1;
		 $("input#count_hidden_att_doc"+R).val(att);
		 $("#id_add_attDoc"+R+"_"+att).show();
		 $("#att_id_removeattDoc"+R+"_"+att).show();	
			$("#Newattachmenttsss_hid"+R).val(att);
	  }
	}
	
	function validateAddMoreMedical(R){
		  
		var count = $("#count_hidden_att_name_med").val();
		 
			var typedegree = $("#typeOfDegree"+R).val();
 			var degreename = $("#DegreeName"+R).val();
			var monthYear = $("#monthYearOfDegree"+R).val();
			var nameUni = $("#NameOfUniversity"+R).val();
			 
			if (typedegree == null || typedegree == "" || typedegree.trim() == "" || typedegree.trim() == "0") {
				alert("Please Select Type of Degree");
				$("#typeOfDegree"+R).focus();
				return false;
			}
			if (degreename == null || degreename == "" || degreename.trim() == "" || degreename.trim() == "0") {
				alert("Please Select Degree Name");
				$("#DegreeName"+R).focus();
				return false;
			}
			
			if (monthYear == null || monthYear == "" || monthYear.trim() == "") {
				alert("Please Enter Month And Year");
				$("#monthYearOfDegree"+R).focus();
				return false;
			}
			
			if (nameUni == null || nameUni == "" || nameUni.trim() == "") {
				alert("Please Enter Name of Institute/Board");
				$("#NameOfUniversity"+R).focus();
				return false;
			}
// 			if (btopen_close == null || btopen_close == "" || btopen_close == "0") {
// 				alert("Please Select Attachment");
// 				return false;
// 			}
		 	
// 		}
	return true;
	}
	
	function validateAddMoreHospital(R){
			var count = $("#count_hidden_att_name_med").val();
			var place_of_working = $("#place_of_working"+R).val();
			var place_of_working_name = $("#place_of_working_name"+R).val();
			var adjunct_place = $("#adjunct_place"+R).val();
			var hos_address1 = $("#hos_address1_"+R).val();
			var landline = $("#landline"+R).val();
			var mobileHosp = $("#mobileHosp"+R).val();
			var email = $("#email"+R).val();
			var hos_state = $("#hos_state"+R).val();
			var hos_district = $("#hos_district"+R).val();
			var authority_type = $("#authority_type"+R).val();
			var name_of_res_p = $("#name_of_res_p"+R).val();
			
			if (place_of_working == null || place_of_working == "" || place_of_working.trim() == "" || place_of_working.trim() == "0") {
				alert("Please Select Place Of Working");
				$("#place_of_working"+R).focus();
				return false;
			}
 
			if (place_of_working_name == null || place_of_working_name == "" || place_of_working_name.trim() == "" ) {
				alert("Please Enter Name of Place");
				$("#place_of_working_name"+R).focus();
				return false;
			}
			 
		if (adjunct_place == null || adjunct_place == "" || adjunct_place.trim() == "" ) {
				alert("Please Enter Adjunct Place");
				$("#adjunct_place"+R).focus();
				return false;
			}
 
			if (landline == null || landline == "" || landline.trim() == "" ) {
				alert("Please Enter Landline Number");
				$("#landline"+R).focus();
				return false;
			}
			
			if (mobileHosp == null || mobileHosp == "" || mobileHosp.trim() == "") {
				alert("Please Enter Mobile Number");
				$("#mobileHosp"+R).focus();
				return false;
			}
			if (email == null || email == "" || email.trim() == "") {
				alert("Please Enter Email");
				$("#email"+R).focus();
				return false;
			}
			
			if (hos_address1 == null || hos_address1 == "" || hos_address1.trim() == "") {
				alert("Please Enter  Address");
				$("#hos_address1_"+R).focus();
				return false;
			}
			
			if (hos_state == null || hos_state == "" || hos_state.trim() == "" || hos_state.trim() == "0") {
				alert("Please Select State");
				$("#hos_state"+R).focus();
				return false;
			}
			
			if (hos_district == null || hos_district == "" || hos_district.trim() == "" || hos_district.trim() == "0") {
				alert("Please Select District");
				$("#hos_district"+R).focus();
				return false;
			}
			
			if (authority_type == null || authority_type == "" || authority_type.trim() == "" || authority_type.trim() == "0") {
				alert("Please Select Authority Type");
				$("#authority_type"+R).focus();
				return false;
			}
			
			if (name_of_res_p == null || name_of_res_p == "" || name_of_res_p.trim() == "") {
				alert("Please Enter Name Of Responsible Owner");
				$("#name_of_res_p"+R).focus();
				return false;
			}
           return true;
	    }
	 
	function dynamicTable(R,index){
		
		var length = $("#count_hidden_att_name_med").val();
		
		for(var i = 1 ;i<=length;i++){
		$("#dynamicDataTable"+i).hide();
		}
		
		if($("#dynamicDataTable"+R).length){
			$("#dynamicDataTable"+R).show();

		}else{
		
		$("#dynamicDataTable").append("<div id='dynamicDataTable"+R+"'></div>");

		var seq = ""+R+"_"+index+"";
  
  		$("div#dynamicDataTable"+R).append('<table class="table model-table" id="addAttDoc'+R+'">'
				+'<thead> '
				+'<tr>'
 				+'<th>Ser No </th>'
				+'<th>Name of Attachment</th>'
				+'<th>Attachment</th>'
// 				+'<th>Previously Uploaded</th>'
				+'<th>Download</th>'
				+'<th>View Attachment</th>'
				
				+'<th>Action</th>'
				+'</tr>'
				+'</thead>'
				+'<tbody id="att_TbbodyattDoc'+seq+'">'
				+'<tr id="tr_id_attDoc'+seq+'">'
				
				+'	 <td class="min-width">'
				+'	 <div class="lead">'
 				+'	 <div class="lead-text">'
				+'	 <p>1</p>'
				+'	 </div>'
				+'	 </div>'
				+'   </td>'
				//janki
				 +'	<td class="min-width">'
				 +'	 <div class="select-style-1">'
				 +'	 <div class="select-position">'
			     +'	 <input type="hidden" id="f_att'+seq+'"  name="f_att'+seq+'" value="" class="form-control autocomplete" autocomplete="off">'
 			     // +'	 <input type="text" id="NameOfAttachment'+seq+'" name="NameOfAttachment'+seq+'" value="" class="form-control autocomplete" autocomplete="off" placeholder="Name of Attachments">'
 				+'	<select name="NameOfAttachment'+seq+'" id="NameOfAttachment'+seq+'" class="form-control autocomplete" >  '
				 +' <option value="0">--Select--</option> 	'
				 +' <option value="Leaving Certificate/Date Of Birth Certificate" name="Final Year Marksheet"> Leaving Certificate/Date Of Birth Certificate</option>  '
// 				 +' <option value="Date Of Birth Certificate" name="Provisional Registration Certificate">Date Of Birth Certificate</option>  '
				 +' <option value="Neet Marksheet" name="Internship Completion Certificate">Neet Marksheet</option>  '
				 +' <option value="Profession Marksheets" name="Provisional Degree Certificate">Profession Marksheets</option>  '
				 +' <option value="Final Year Marksheet" name="Final Year Marksheet"> Final Year Marksheet</option>  '
				 +' <option value="Provisional Registration Certificate" name="Provisional Registration Certificate">Provisional Registration Certificate</option>  '
				 +' <option value="Internship Completion Certificate" name="Internship Completion Certificate">Internship Completion Certificate</option>  '
				 +' <option value="Provisional Degree Certificate" name="Provisional Degree Certificate">Provisional Degree Certificate</option>  '
				 
				 +'</select>'
				 +'</div>'
				 +' </div>'
				 +' </td>' 
// 				+'	<td class="min-width addminusbut">'
// 				+'	<div class="input-style-2">'
// 				+'	<input type="file" id="attachmentDocument'+seq+'" name="attachmentDocument'+seq+'" accept=".pdf" class="form-control">'
// 				+' <input type="hidden" id="attachmentDoc_hid'+seq+'" name="attachmentDoc_hid'+seq+'" class="form-control">'
// 				+' <span class="errorClass" id="attachmentDoc_file_lbl'+seq+'"></span>'
// 				+' <span class="tikClass" id="attachmentDoc_file_lbltik'+seq+'">'
// 	            +'</div> '
// 				+'</td>'
				+'<td class="min-width"><div class="input-style-2 regupractaddmoretd">'
			 	+'<input type="file" id="attachmentDocument'+seq+'" name="attachmentDocument'+seq+'" accept=".pdf"  class="form-control">'
			 	+'<input type="hidden" id="attachmentDoc_hid'+seq+'" name="attachmentDoc_hid'+seq+'" class="form-control">'
				+' <label id="file_upld'+seq+'" name="file_upld'+seq+'" value= " ">No File Uploded</label>' 
			 	+'<span class="errorClass" id="attachmentDoc_file_lbl'+seq+'"></span> '
			 	+'<span class="tikClass" id="attachmentDoc_file_lbltik'+seq+'"></div>'
			 	+'</td>' 
// 				+'<td class="min-width addminusbut">'
//   				+' <label id="file_upld'+seq+'" name="file_upld'+seq+'" value= " ">No File Uploded</label>' 
//   				+'</td> '
//   				+'<input type="text" id="file_id1" name="file_id1"></li></ul>'
   				+'<td class="min-width addminusbut"> '
  				+'<ul class="buttons-group mainbtn action daobtn"> '
  				+'<li><a class="main-btn success-btn btn-hover btn-sm addminusbut"  '
  				+'id="downloadbtn1" title="Download"> '
  				+'<i class="bi bi-file-pdf"></i></a> '
  				+'</td> '
  				+'<td class="min-width addminusbut">'
			 	+'<ul class="buttons-group mainbtn action">'
			 	+'<li><a class="main-btn success-btn-outline btn-hover btn-sm addminusbut" '
			 	+'id="downloadbtn' +seq+'" title="Download">'
//				 	+'onclick="download_file_view('+att+');" id="downloadbtnview' +att+ '" title="Downlaod">'
			 	+'<i class="bi bi-file-pdf"></i></a>'
			 	+'<input type="hidden" id="file_id'+seq+'" name="file_id'+seq+'"></li></ul>'
			 	+' </td>'
				+'<td class="min-width addminusbut">'
				+' <div class="action">'
				+' <ul class="buttons-group mainbtn daobtn">'
				+' <li><a class="main-btn success-btn btn-hover btn-sm addminusbut" value="ADD" title="ADD" id="id_add_attDoc'+seq+'" name="id_add_attDoc'+seq+'">'
				+' <i class="lni lni-plus"></i></a></li>' 
				+' </ul>'
 				+' </div>'
  				+'</td> '
 				+' </tr> '
 				+' </tbody>'
 				+'<input type="hidden" id="oldattachmenttsss_hid'+R+'" name="oldattachmenttsss_hid'+R+'" value=1 class="form-control" autocomplete="off" />'
 				+'<input type="hidden" id="Newattachmenttsss_hid'+R+'" name="Newattachmenttsss_hid'+R+'" value="0"  class="form-control" autocomplete="off" />'
 				+'</table>'
				+'<input type="hidden" id="count_hidden_att_doc'+R+'" name="count_hidden_att_doc'+R+'" value=1 class="form-control autocomplete">');

 		attachmenttforparent(R,index);
		}
	}
	
	var filelength = 0;
	var xml_file_name = '';
	function onfileselected(event){
		filelength = event.target.files.length;
		var arpanlist = '';
		var arr = [];
		
		var arry = new Array();
		
		for(var i = 0;i< filelength;i++){
			var selectedfile = event.target.files[i];
			xml_file_name = selectedfile.name;

			arry.push(xml_file_name);
		
		}
	   $("#my_attachment_hid").val(arry);
	}
	
	function Getchekmonthyear(att) {
		
		if (att>1) {
			var att_cur = att;
			var att_prev = parseInt(att) - 1;
			
		var tod_cur =  $("#typeOfDegree"+att_cur).val();
		var tod_prev = $("#typeOfDegree"+att_prev).val();
		var monyr_cur = $("#monthYearOfDegree"+att_cur).val();
		var monyr_prev = $("#monthYearOfDegree"+att_prev).val();
			
			if (parseInt(tod_cur) > parseInt(tod_prev)  &&  (monyr_cur) < (monyr_prev)) {
				alert("Please Select Month and Year Grater Then "+$("#typeOfDegree"+att_prev+" option:selected").text()+"'s Month and Year.");
				$("#monthYearOfDegree"+att_cur).val('');
				$("#monthYearOfDegree"+att_cur).focus();
				return false;
			}
			
			if (parseInt(tod_cur) < parseInt(tod_prev)  &&  (monyr_cur) > (monyr_prev)) {
					alert("Please Select Month and Year Smaller Than "+$("#typeOfDegree"+att_prev+" option:selected").text()+"'s Month and Year.");
					$("#monthYearOfDegree"+att_cur).val('');
					$("#monthYearOfDegree"+att_cur).focus();
					return false;
			}
		}
		return true;
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

// 	==================CSP START============== ======================================== ============================CSP======================================
		
			document.addEventListener('DOMContentLoaded', function() {

				document.getElementById('first_name').onkeypress = function() {
					return onlyAlphabetsStringSpace(event,this)
				};

				document.getElementById('first_name').oninput = function () {
					this.value = this.value.toUpperCase()
				};
				
				document.getElementById('terms').onchange = function() {
					
					 if($("#terms").is(":checked") == true) { 
							$("#final_submit").show();
						}else{
							$("#final_submit").hide();
						}
				};
				
// 				document.getElementById('father_name').onkeypress = function() {
// 					return onlyAlphabetsStringSpace(event,this)
// 				};
				
				document.getElementById('father_name').oninput = function () {
					this.value = this.value.toUpperCase()
				};
				document.getElementById('dob').onclick = function() {
		 			return clickclear(this, 'DD/MM/YYYY');
		 		};
		 		document.getElementById('dob').onfocus = function() {
		 			return this.style.color='#000000';
		 		};
		 		document.getElementById('dob').onblur = function() {
		 			return clickrecall(this,'DD/MM/YYYY');
		 		};
		 		
// 		 		document.getElementById('dob').onblur = function() {
// 		 		validateDate_BackDate(this.value,this);
// 		 		};
		 		
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
		 		
		 		document.getElementById('aadhaar_no').oninput = function () {
		 			this.value = this.value.toUpperCase()
				};
				
				document.getElementById('aadhaar_no').onkeypress = function() {
					return isNumberKey0to9(event, this);
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
		 		
// 		 		document.getElementById('email_id').onchange = function() {
// 		 			checkemail(this.value)
// 		 		};
		 		
		 		document.getElementById('email_id').onchange = function() {
		 			return  checkgmail(this.value);
		 		};

		 		document.getElementById('email_id').oninput = function () {
					this.value = this.value.toLowerCase()
				};
				
//			 	document.getElementById('photo_path').onchange = function() {
//		 		pdfFileSizeValidation(this,this.value,'demo_video','100mb','doc_upload_lbltik','doc_upload_lbl',this.accept);
//		 	};
		 		
		 		document.getElementById('photo_path').onchange = function() {
		 			photoValidate();
		 			return imgFileSizeValidation(this,this.value,'photo_path','50kb','upload_photo_doc_lbl');
		 		};
		 		
// 		 		document.getElementById('photo_path').onchange = function() {
// 		 			imgFileSizeValidation(this,this.value,'photo_path','50kb','upload_photo_doc_lbl');
// 		 		};
		 		
// 		 		document.getElementById('photo_path').onchange = function() {
		 		
// 		 		};
		 		
		 		document.getElementById('alt_mo_no1').onkeypress = function() {
		 			return isNumberOnly(event)
				};
				
				document.getElementById('alt_mo_no1').oninput = function () {
					this.value = this.value.toUpperCase()
				};
				
				document.getElementById('alt_mo_no1').onchange = function() {
					return mobileNumber1(this);
				};
				
				document.getElementById('alt_mo_no2').onkeypress = function() {
		 			return isNumberOnly(event)
				};
				
				document.getElementById('alt_mo_no2').oninput = function () {
					this.value = this.value.toUpperCase()
				};
				
				document.getElementById('alt_mo_no2').onchange = function() {
					return mobileNumber2(this);
				};

				document.getElementById('alt_email_id1').onchange = function() {
					checkalterenet(this.value);
				};
				
				document.getElementById('alt_email_id1').oninput = function () {
					this.value = this.value.toLowerCase()
				};
				
				document.getElementById('alt_email_id2').onchange = function() {
					checkalterenet2(this.value);
				};
				
				document.getElementById('alt_email_id2').oninput = function () {
					this.value = this.value.toLowerCase()
				};
				
				document.getElementById('per_address').onchange = function() {
					changeAddress();
				};
		 		
				document.getElementById('per_address2').onchange = function() {
					changeAddress();
				};
		 		
				document.getElementById('per_address3').onchange = function() {
					changeAddress();
				};
				
				document.getElementById('per_state').onchange = function() {
					getDistrictper();
				};
				
				document.getElementById('per_district').onchange = function() {
				 changeAddress();
				};
				
				document.getElementById('per_pincode').onkeypress = function() {
					return isNumberOnly(event)
				};
				
				document.getElementById('per_pincode').oninput = function () {
					this.value = this.value.toUpperCase()
				};
		 		
				document.getElementById('per_pincode').onchange = function() {
					 changeAddress();
					 checkMinLength(this.id,6);
					 checkMaxLength(this.id,6);
				};
					
				document.getElementById('pre_state').onchange = function() {
					getDistrictpre();
				};
				
				document.getElementById('pre_pincode').onkeypress = function() {
					return isNumberOnly(event)
				};
				
				document.getElementById('pre_pincode').oninput = function () {
					this.value = this.value.toUpperCase()
				};
				
				document.getElementById('pre_pincode').onchange = function() {
					  checkMinLength(this.id,6);
					  checkMaxLength(this.id,6);
				};
				
				document.getElementById('curr_state').onchange = function() {
					getDistrictcurr();
				};
			
				document.getElementById('curr_pincode').onkeypress = function() {
					return isNumberOnly(event)
				};
				
				document.getElementById('curr_pincode').oninput = function () {
					this.value = this.value.toUpperCase()
				};
				
				document.getElementById('curr_pincode').onchange = function() {
					  checkMinLength(this.id,6);
					  checkMaxLength(this.id,6);
				};
				
				document.getElementById('typeOfDegree1').onchange = function() {
					getDegreeName(this,1);
				};
				
				document.getElementById('NameOfUniversity1').onkeypress = function() {
					return onlyAlphabetsStringSpace(event,this)
				};
				
				document.getElementById('place_of_working_name1').onkeypress = function() {
					return onlyAlphabetsStringSpace(event,this)
				};
				
				document.getElementById('adjunct_place1').onkeypress = function() {
					return onlyAlphabetsStringSpace(event,this)
				};
				

				document.getElementById('landline1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				
				document.getElementById('landline1').onchange = function() {
					return mobileNumber1(this);
				};
				
				document.getElementById('mobileHosp1').onkeypress = function() {
					return isNumberKey0to9(event, this);
				};
				
				document.getElementById('mobileHosp1').onchange = function() {
					return mobileNumber1(this);
				};
				
				document.getElementById('email1').onchange = function() {
					checkgmail1(this.value)
				};
				
				document.getElementById('email1').oninput = function () {
					this.value = this.value.toLowerCase()
				};
				
				document.getElementById('hos_state1').onchange = function() {
					getDistricthos(1);
				};
				
				document.getElementById('name_of_res_p1').onkeypress = function() {
					return onlyAlphabetsStringSpace(event,this)
				};
				
				document.getElementById('id_add_attHospital1').onclick = function() {
					formOpenHospital(1);
				};
				
// 				 onclick="return validationAdd(0);" for SAVE
				document.getElementById('draft').onclick = function() {
					return validationAdd(0);
				};
				
// 				document.getElementById('draft').onclick = function() {
// 					return save_validate();
// 				};
				

				document.getElementById('update').onclick = function() {
					return validationAdd(1);
				};
				
				document.getElementById('final_submit').onclick = function() {
					return Validate();
				};
				
				document.getElementById('renew').onclick = function() {
					renewFunction();
				};
				
				document.getElementById('check_address').onclick = function() {
					copy_address();
				};
				
				document.getElementById('check_address1').onclick = function() {
					copy_permanent_address();
				};
				
				document.getElementById('check_address2').onclick = function() {
					copy_present_address();
				};
				
				document.getElementById('check_address3').onclick = function() {
					other_address();
				};
				
				document.getElementById('pre_address').onchange = function() {
					changeCurrespongAddress();
				};
		 		
				document.getElementById('pre_address2').onchange = function() {
					changeCurrespongAddress();
				};
		 		
				document.getElementById('pre_address3').onchange = function() {
					changeCurrespongAddress();
				};
				
				document.getElementById('pre_district').onchange = function() {
					changeCurrespongAddress();
				 };
				 document.getElementById('pre_pincode').onchange = function() {
					 changeCurrespongAddress();
				};
				document.getElementById('id_add_attNameMed1').onclick = function() {
					formOpenNameMed(1)
				};
// 				document.getElementById('downloadbtn').onclick = function() {
// 					download_file();
// 				};
// 				document.getElementById('downloadbtnview').onclick = function() {
// 					download_file_view();
// 				};
			});
			
			
			
// 			==========medical admore
			function medicaldegree(index){
				document.getElementById('id_add_attNameMed'+index).onclick = function () {
					formOpenNameMed(index);
				};
				document.getElementById('att_id_removeNameMed'+index).onclick = function () {
					//DeleteMedicalFn(index);
					formopen_re_NameMed(index);
				};
				document.getElementById('typeOfDegree'+index).onchange = function () {
					getDegreeName(this,index);
					check_exs(index);
				};
// 				document.getElementById('NameOfUniversity'+index).onkeypress = function () {
// 					AutoCompleteNameOfUniversity(this,index);
// 				};
				document.getElementById('monthYearOfDegree'+att).onchange = function() {
					return Getchekmonthyear(att);	
				};
				 $('#btnmodel'+index).click(function() {
					   $('#modelWindow').modal('show');
					   dynamicTable(index,1);
					});
				$('#closeModel').click(function() {
					   $('#modelWindow').modal('hide');
					});
				$('#closeModel2').click(function() {
					   $('#modelWindow').modal('hide');
					});
			}

// hospital addmore
function hospital(index){
				document.getElementById('id_add_attHospital'+index).onclick = function () {
				formOpenHospital(index);
				};
				document.getElementById('att_id_removeHospital'+index).onclick = function () {
					formopen_re_Hospital(index);
				};
				document.getElementById('place_of_working_name'+index).onkeypress = function () {
					return onlyAlphabetsStringSpace(this,index);
				};
					document.getElementById('adjunct_place'+index).onkeypress = function () {
					return onlyAlphabetsStringSpace(this,index);
				};
					document.getElementById('landline'+index).onkeypress = function () {
					return isNumberKey0to9(event,this,index);
				};
				
				document.getElementById('landline'+index).onkeypress = function () {
					return isNumberOnly(event,this,index);
				};
				
				document.getElementById('landline'+index).onchange = function () {
					return landlinenumber(this,index);
				};
				
// 				30-08-2022
				document.getElementById('email'+index).onchange = function () {
					return check_email_addmore(this);
				};
				
				document.getElementById('mobileHosp'+index).onkeypress = function () {
				return isNumberKey0to9(event,this,index);
					
				};
				
				document.getElementById('mobileHosp'+index).onkeypress = function () {
					return isNumberOnly(event,this,index);
				};
				
				document.getElementById('mobileHosp'+index).onchange = function () {
					return mobileNumber(this,index);
				};
				
				document.getElementById('hos_state'+index).onchange = function () {
					 getDistricthos(index);
				};
				
				document.getElementById('name_of_res_p'+index).onkeypress = function () {
				return onlyAlphabetsStringSpace(event,this,index);
				};
			}
			
function attachmentt(R,att){
	document.getElementById('id_add_attDoc'+R+'_'+att).onclick = function () {
		formOpenattDoc(R,att);
    };
 
	document.getElementById('att_id_removeattDoc'+R+'_'+att).onclick = function () {
		formopen_re_attDoc(R,att);
	};
	document.getElementById('NameOfAttachment'+R+'_'+att).onclick = function () {
		AutoCompleteNameOfUniversity(this,R,att);
	};

	document.getElementById('NameOfAttachment'+R+'_'+att).onclick = function () {
		check_exs_att(R,att);
	};
	
	document.getElementById('attachmentDocument'+R+'_'+att).onchange = function () {
		//attachmentDetails(this,1);
		return   pdfFileSizeValidation(this,this.value,"attachmentDocument"+R+'_'+att,"200kb","attachmentDoc_file_lbltik"+R+'_'+att,"attachmentDoc_file_lbltik"+R+'_'+att,this.accept);
	};
	
	document.getElementById('downloadbtn1').onclick = function () {
		download_file(R,att);
    };
    document.getElementById('downloadbtn'+R+'_'+att).onclick = function () {
    	download_file_view2(R,att);
    };
//     +'onclick="download_file( '+R+' , '+att+' );" id="downloadbtn1" title="Downlaod"> 'seq
    
// // 	onchange="attachmentDetails(this,'+seq+');"
// 	document.getElementById('attachmentDocument'+R+'_'+att).onclick = function () {
// 		attachmentDetails(this,R,att);
// 	};


// document.getElementById('att_id_removeattDoc'+R+'_'+att).onclick = function () {
// 	DeleteMedAttachFn(this,R,att);
// 	};
}

function download(att){
	document.getElementById('downloadbtn'+att).onclick = function () {
		download_file(att);
    };
}

function attachmenttforparent(R,index){
	document.getElementById('attachmentDocument'+R+'_'+index).onchange = function () {
		//attachmentDetails(this,1);
		return   pdfFileSizeValidation(this,this.value,"attachmentDocument"+R+'_'+index,"200kb","attachmentDoc_file_lbltik"+R+'_'+index,"attachmentDoc_file_lbltik"+R+'_'+index,this.accept);
	};
  
	document.getElementById('id_add_attDoc'+R+'_'+index).onclick = function () {
		formOpenattDoc(R,1);
	};
	document.getElementById('downloadbtn1').onclick = function () {
		download_file(R,1);
	};
	document.getElementById('downloadbtn'+R+'_'+index).onclick = function () {
		download_file_view2(R,1);
	};
	
}

function attachmenttforparentthisseq(seq){
	 
	document.getElementById('downloadbtn'+seq).onclick = function () {
// 		download_file(att);
// 		alert("ddd")
		download_file_view2(seq);
	};
	
	
}

function attachmentforindex(R,a){
	
	document.getElementById('downloadbtn'+R+'_'+a).onclick = function () {
	
		download_file_view2(R,a);
	};
}

// function attachment(R,att){
	 
// 	document.getElementById('downloadbtn'+R+a).onclick = function () {
// // 		download_file(att);
// 		alert("ddd")
// 		download_file_view2(R,a);
// 	};
	
	
// }

function download_file(R,att) {
	
	
	var val= $("#f_att"+R+"_"+att).val();
 
$.post('getIndivisualAttachmentFilePath?' + key + "=" + value,{id:val},function(k) {
	 
		var file = k.split(",");
		for(var i=0;i<file.length;i++){

		var pdfView="kmlFileDownloadDemo2?path="+file[i];
		
		alert(PDFView);
		
	    fileName="TEST_DOC";
	    fileURL=pdfView;
	    if (!window.ActiveXObject) {
	        var save = document.createElement('a');
	        save.href = fileURL;
	        save.target = '_blank';
	        var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
	        save.download = fileName || filename;
		       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
					document.location = save.href; 
				}else{
			        var evt = new MouseEvent('click', {
			            'view': window,
			            'bubbles': true,
			            'cancelable': false
			        });
			        save.dispatchEvent(evt);
			        (window.URL || window.webkitURL).revokeObjectURL(save.href);
				}	
	    }
	    else if ( !! window.ActiveXObject && document.execCommand)     {
	        var _window = window.open(fileURL, '_blank');
	        _window.document.close();
	        _window.document.execCommand('SaveAs', true, fileName || fileURL)
	        _window.close();
	     }
	 }

  });
	//location.reload();
}	

function download_file(obj) {
 	var id = $("#f_att1_"+obj).val();  
	var val= $("#val1").val();
// 	var fildname= $("#fildname1").val();
	$.post('getFilePathAttachment?' + key + "=" + value,{id:id},function(k) {
	 	
		var file = k.split(",");
		for(var i=0;i<file.length;i++){
		 	 
		var pdfView="attachmentfiledownloadIndividual?path="+file[i];
		
	    fileName="TEST_DOC";
	    fileURL=pdfView;
	    if (!window.ActiveXObject) {
	        var save = document.createElement('a');
	        save.href = fileURL;
	        save.target = '_blank';
	        var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
	        save.download = fileName || filename;
		       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
					document.location = save.href; 
				}else{
			        var evt = new MouseEvent('click', {
			            'view': window,
			            'bubbles': true,
			            'cancelable': false
			        });
			        save.dispatchEvent(evt);
			        (window.URL || window.webkitURL).revokeObjectURL(save.href);
				}	
	    }
	    else if ( !! window.ActiveXObject && document.execCommand)     {
	        var _window = window.open(fileURL, '_blank');
	        _window.document.close();
	        _window.document.execCommand('SaveAs', true, fileName || fileURL)
	        _window.close();
	    }
	}
});
// 	location.reload();

}

function download_file_view2(R,a) {
// 	f_att1_1
	var val= $("#f_att"+R+"_"+a).val();
	var pdf1="attachmentfiledownload?kmlId2="+val;
	PDFView(pdf1,val,0,0);
 	
}

function hide_PDF() {
//	   $("#my_pdf_viewer").hide();
	if (!$( "#my_pdf_viewer" ).hasClass('d-none')) {
		$( "#my_pdf_viewer" ).addClass("d-none")
	}
}
// 	===================for view

function PDFView(path1,idx,val,field){
	
// 	$("div#my_pdf_viewer").show();

// 	var pdfView1=path1;
	
//   	var myState = {
//             pdf: null,
//             currentPage: 1,
//             pdfScale: 1,
//             zoom: 1.5
//         }
      
//         pdfjsLib.getDocument(pdfView1).then((pdf) =>
//         {
//             myState.pdf = pdf;
//             render();
//         });
 
//         function render() {
//             myState.pdf.getPage(myState.currentPage).then((page) => 
//             {
//                 var canvas = document.getElementById("pdf_renderer");
//                 var ctx = canvas.getContext('2d');
      
//                 var viewport = page.getViewport(myState.zoom);
 
//                 canvas.width = viewport.width;
//                 canvas.height = viewport.height;
          
//                 page.render({
//                     canvasContext: ctx,
//                     viewport: viewport
//                 });
//             });
//         }
 
//         document.getElementById('go_previous').addEventListener('click', (e) => {
//             if(myState.pdf == null || myState.currentPage == 1) 
//               return;
//             myState.currentPage -= 1;
//             document.getElementById("current_page").value = myState.currentPage;
//             render();
//         });
 
//         document.getElementById('go_next').addEventListener('click', (e) => 
//         {
        	
//             if(myState.pdf == null || myState.currentPage >= myState.pdf._pdfInfo.numPages) 
//                return;
//             myState.currentPage += 1;
//             document.getElementById("current_page").value = myState.currentPage;
//             render();
//         });
 
//         document.getElementById('current_page').addEventListener('keypress', (e) => {
//             if(myState.pdf == null) return;
          
//             var code = (e.keyCode ? e.keyCode : e.which);
          
//             if(code == 13) {
//                 var desiredPage = 
//                 document.getElementById('current_page').valueAsNumber;
                                  
//                 if(desiredPage >= 1 && desiredPage <= myState.pdf._pdfInfo.numPages) {
//                     myState.currentPage = desiredPage;
//                     document.getElementById("current_page").value = desiredPage;
//                     render();
//                 }
//             }
//         });        
 
//         document.getElementById('zoom_in').addEventListener('click', (e) => {
//             if(myState.pdf == null) return;
//             myState.zoom += 0.5;

//             render();
//         });
 
//         document.getElementById('zoom_out').addEventListener('click', (e) => {
//             if(myState.pdf == null) return;
//             myState.zoom -= 0.5;
//             render();
//         });

	$("#pdfmodelcanvas").empty();
	$("#modal-footer").empty();
	$("#exampleModal").modal('show');
	
		var canvas_data = "";
		
		canvas_data += '<div id="my_pdf_viewer'+idx+'" class="custom-pdf-viewer">'										
								+'<div id="canvas_container">'
								+'<div>'
								+'<canvas id="pdf_renderer'+idx+'"  width="600px" height="500px" ></canvas>'
								+'<input type="hidden" value="'+idx+'" id="PicturePDFId" /> <input'
								+'	type="hidden" value="'+val+'" id="val1" /> <input type="hidden"'
								+'	value="'+field+'" id="fildname1" />'
								+'</div>'
								+'</div>'
								+'</div>';

	$("#pdfmodelcanvas").append(canvas_data);
						
		var btn="";
			btn +=   '<div class="modal-footer-left">'
								+'<ul class="buttons-group">'
								+'<li><a  id="zoom_in'+idx+'"	class="main-btn active-btn btn-hover btn-sm btnzoomin"'
								+'title="Zoom In"><i class="lni lni-zoom-in"></i></a></li>'
								+'<li><a  id="zoom_out'+idx+'"	class="main-btn active-btn btn-hover btn-sm btnzoomout"'
								+'title="Zoom Out"><i class="lni lni-zoom-out"></i></a></li>'
								+'</ul>'
								+'</div>'
								+'<div class="modal-footer-center">'
								+'<ul class="buttons-group" >'
								+'<li class="footer_btn_control"><a id="go_previous'+idx+'" type="button"'
								+'class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"'
								+'></i>Previous</a></li>'
								+'<li><a type="button" class="main-btn dark-btn n "><span '
								+'class="pdf-page">Page: <span id="page_num'+idx+'">1</span>/ <span id="page_count'+idx+'">'
								+'</span></span></a></li>'
								+'<li><a id="go_next'+idx+'" type="button" '
								+'class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left btnnext">Next'
								+'<i class="lni lni-chevron-right"></i>'
								+'</a></li>'
								+'</ul>'
								+'</div>'
								+'<div class="modal-footer-right">'
								+'<ul class="buttons-group">'
								+'<li><a type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal">Close</a></li>'
								+'</ul>'
								+'</div>';


			$("#modal-footer").append(btn);

	var pdfDoc = null,
    pageNum = 1,
    pageRendering = false,
    pageNumPending = null,
    scale = 1,
    canvas = document.getElementById('pdf_renderer'+idx),
    ctx = canvas.getContext('2d');

/**
 * Get page info from document, resize canvas accordingly, and render page.
 * @param num Page number.
 */
function renderPage(num) {
  pageRendering = true;
  // Using promise to fetch the page
  pdfDoc.getPage(num).then(function(page) {
    var viewport = page.getViewport({scale: scale});
    canvas.height = viewport.height;
    canvas.width = viewport.width;

    // Render PDF page into canvas context
    var renderContext = {
      canvasContext: ctx,
      viewport: viewport
    };
    var renderTask = page.render(renderContext);

    // Wait for rendering to finish
    renderTask.promise.then(function() {
      pageRendering = false;
      if (pageNumPending !== null) {
        // New page rendering is pending
        renderPage(pageNumPending);
        pageNumPending = null;
      }
    });
  });

  // Update page counters
  document.getElementById('page_num'+idx).textContent = num;
}

/**
 * If another page rendering in progress, waits until the rendering is
 * finised. Otherwise, executes rendering immediately.
 */
function queueRenderPage(num) {
  if (pageRendering) {
    pageNumPending = num;
  } else {
    renderPage(num);
  }
}

/**
 * Displays previous page.
 */
function onPrevPage() {
  if (pageNum <= 1) {
    return;
  }
  pageNum--;
  queueRenderPage(pageNum);
}

document.getElementById('go_previous'+idx).addEventListener('click', onPrevPage);

/**
 * Displays next page.
 */
 
function onNextPage() {
  if (pageNum >= pdfDoc.numPages) {
    return;
  }
  pageNum++;
  queueRenderPage(pageNum);
}
document.getElementById('go_next'+idx).addEventListener('click', onNextPage);

document.getElementById('zoom_in'+idx).addEventListener('click', (e) => {
    if(pdfDoc == null) return;
    if (scale!= 4) {
    scale += 0.5;
   }
    queueRenderPage(pageNum);
});

document.getElementById('zoom_out'+idx).addEventListener('click', (e) => {
	 if(pdfDoc == null) return;
	 
	 if (scale != 0.5) {
		 scale -= 0.5;
	}
	    queueRenderPage(pageNum);
});

// document.getElementById('downloadbtn'+idx).addEventListener('click', (e) => {
// 	download_file();
// });

// document.getElementById('btn-close-pdf').addEventListener('click', (e) => {
// 	hide_PDF();
// });
/**
 * Asynchronously downloads PDF.
 */
 
pdfjsLib.getDocument(path1).promise.then(function(pdfDoc_) {

	 if (pdfDoc) {
         pdfDoc.destroy();
     }
     pdfDoc = pdfDoc_;
//      this.total_pages = this.pdfDoc.numPages;
	
//   pdfDoc = pdfDoc;
  document.getElementById('page_count'+idx).textContent = pdfDoc.numPages;
  // Initial/first page rendering
  renderPage(pageNum);
});
	
}
	
function View_hide() {
	   $("#my_pdf_viewer").show();
}
	</script>

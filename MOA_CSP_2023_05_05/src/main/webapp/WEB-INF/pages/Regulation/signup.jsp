
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>

<title>Ministry of Ayush</title>

<!-- Favicons -->
<link href="admin/assets/img/favicon.ico" rel="icon">

<!-- Font CSS Files -->
<link href="admin/assets/font/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="admin/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
<link href="admin/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="admin/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="admin/assets/css/style.css" rel="stylesheet">
<link href="admin/assets/css/custom_pages_style.css" rel="stylesheet">
<link href="admin/assets/css/responsive.css" rel="stylesheet">


<!-- //// -->


<link rel="shortcut icon" href="admin/layout_file/images/favicon.png">

<script src="admin/layout_file/bootstrap_5/bootstrap.bundle.min.js"></script>


<script type="text/javascript" src="admin/js/common/commonmethod.js"></script>
<script type="text/javascript" src="admin/js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="admin/js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="admin/js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="admin/js/autoComplate/autoComplate.css">

<script type="text/javascript"
	src="admin/js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <link rel="stylesheet" href="admin/js/InfiniteScroll_old/css/site.css"> -->
<link rel="stylesheet" href="admin/js/autoComplate/autoComplate.css">

<link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script>



<link href="admin/js/jquery/jquery-ui.css" rel="Stylesheet"></link>
<script src="admin/js/jquery/jquery-ui.js" type="text/javascript"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<!-- <link href="admin/js/admin/assets/scss/custom-style.css" rel="Stylesheet"></link> -->
</head>


<body>

	<!--  Property Search Section  -->
	<div class="click-closed"></div>

	<!--/ Form Search Star /-->
	<div class="box-collapse">
		<div class="title-box-d">
			<h3 class="title-d">Search Property</h3>
		</div>
		<span class="close-box-collapse right-boxed bi bi-x"></span>
		<div class="box-collapse-wrap form">
			<form class="form-a">
				<div class="row">
					<div class="col-md-12 m-2">
						<div class="form-group">
							<label class="pb-2" for="Type">Keyword</label> <input type="text"
								class="form-control form-control-lg form-control-a"
								placeholder="Keyword">
						</div>
					</div>
					<div class="col-md-12">
						<button type="submit" class="btn btn-b mt-3">Search
							Property</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- End Property Search Section -->

<!--  Header/Navbar start-->
	<!--  Header/Navbar start-->
	<header>
		<nav
			class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
			<div class="container">
				<div class="logo-main">
					<a href="landingpage">
					<img src="admin/assets/img/ayush-grid.png" class="img-fluid">
					<span class="logo-subtext"><img alt="moa-logo" src="admin/assets/img/moa-icon.jpg" class="moa-logo">Ministry of AYUSH</span>
					</a>
				</div>
				<button class="navbar-toggler collapsed" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarDefault"
					aria-controls="navbarDefault" aria-expanded="false"
					aria-label="Toggle navigation">
					<span></span> <span></span> <span></span>
				</button>
				<div class="navbar-collapse collapse justify-content-center"
					id="navbarDefault">
					<ul class="navbar-nav">

						<li class="nav-item"><a class="nav-link active"
							href="landingpage">Home</a></li>																

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Indian Medicine</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="ayurveda">Ayurveda</a> <a
									class="dropdown-item" href="#">Yoga</a> <a
									class="dropdown-item" href="#">Naturopathy</a> <a
									class="dropdown-item" href="#">Unani</a> <a
									class="dropdown-item" href="#">Siddha</a> <a
									class="dropdown-item" href="#">Sowa-Rigpa</a> <a
									class="dropdown-item" href="#">Homeopathy</a>
							</div>
						</li>
						
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">About</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="aboutncism">NCISM</a>
								<a class="dropdown-item" href="aboutnch">NCH</a>
								<a class="dropdown-item" href="aboutmoa">Ministry of Ayush(MOA)</a>
							</div>
						</li>
						
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Information</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="admin">Admin</a>
								<a class="dropdown-item" href="staff">Staff</a>
								<a class="dropdown-item" href="student">Student</a>
								<a class="dropdown-item" href="practitioner">Practitioner</a>								
								<a class="dropdown-item" href="placement">Placement</a>
								<a class="dropdown-item" href="openapiplatform">Open API Platform</a>
								<a class="dropdown-item" href="coreregistration">Core Registries / Directories</a>
								<a class="dropdown-item" href="education">Education</a>
							</div>
						</li>
						
						<li class="nav-item"><a class="nav-link" href="alumni">Alumni</a></li>
						
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">E-learning</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="coursemanagement">Course management</a>
								<a class="dropdown-item" href="learningplatforms">Learning Platform</a>
								<a class="dropdown-item" href="eclassroom">E-classroom Module</a>
								<a class="dropdown-item" href="trainingandwebcast">Training & Webcast</a>
								<a class="dropdown-item" href="#">Collaboration utilities</a>
								<a class="dropdown-item" href="#">Research</a>						
							</div>
						</li>											
						
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Others</a>
							<div class="dropdown-menu">							
								<a class="dropdown-item" href="#">Regulation</a>
								<a class="dropdown-item" href="contactus">Contact US</a>
								<a class="dropdown-item" href="feedback">Feedback</a>													
							</div>
						</li>																				

					</ul>
				</div>
				<div class="custom-btn">
					<div class="authentication-detail">

						<a href="login" class="btn btn-bh">SignIn</a> <a href="signup"
							class="btn btn-bh">SignUp</a>

						<!-- <div class="navbar-toggle-box navbar-toggle-box-collapse">
							<button type="button" class="btn btn-b-n"
								data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01">
								<i class="bi bi-search"></i>
							</button>
						</div> -->

					</div>
				</div>
				<div class="site-logo">
					<ul class="site-logo-inner d-flex">
						<li class="logo-s1"><a href="#"><img src="admin/assets/img/ncism_logo.png"></a></li>
						<li class="logo-s1"><a href="#"><img src="admin/assets/img/nchlogo.png"></a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<!-- Header/Navbar End-->

	<!--  Intro Single  -->
	<section class="intro-single">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-8">
					<div class="title-single-box">
						<h1 class="title-single">SignUp</h1>
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Signup</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single-->

	<!--  Register Section  -->
	<section class="section-register">
		<div class="container">
			<div class="row justify-content-center">

				<div class="col-lg-8 col-md-8 col-sm-12">
					<div class="signup-form">

						<form:form action="Practitioner_registration_action" method='POST'
							modelAttribute="Practitioner_registration_cmd"
							enctype="multipart/form-data">

							<div class="row">
								<div class="box-border-p">
									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label class=" form-control-label"> Are You New Practitioner
												?<span class="mandatory">*</span>
											</label>
										</div>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label for="new" class="form-check-label"> <input
												type="radio" id="new" name="registration"
												class="form-check-input radiostyle" value="0" required
												onclick="fn_new();" />Yes
											</label> <label for="old" class="form-check-label"> <input
												type="radio" id="old" name="registration"
												class="form-check-input radiostyle" value="1" required
												onclick="fn_old();" />No
											</label>
										</div>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
									<div class="form-group">
										<input type="hidden" id="id" name="id" value="0"
											class="form-control" autocomplete="off" />
									</div>
								</div>

								<div id="div_ayush" style="display: none;" class="box-border-p">
									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label class=" form-control-label">Do You Have Ayush
												Id ?<span class="mandatory">*</span>
											</label>
										</div>
									</div>

									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label for="YES" class="form-check-label"> <input
												type="radio" id="yes" name="aayushid"
												class="form-check-input radiostyle" value="0" required
												onclick="fn_ayus_yes();" />Yes
											</label> <label for="NO" class="form-check-label"> <input
												type="radio" id="no" name="aayushid"
												class="form-check-input radiostyle" value="1" required
												onclick="fn_ayus_no()" />No
											</label>
											<div class="col-md-12">
												<input type="hidden" id="id" name="id" value="0"
													class="form-control" autocomplete="off" />
											</div>
										</div>
									</div>
								</div>

								<div id="div_ayush_nrh" style="display: none;"
									class="box-border-p">
									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label class=" form-control-label">Do You Have Ayush
												Id Or NRH No ?<span class="mandatory">*</span>
											</label>
										</div>
									</div>

									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label for="YES" class="form-check-label"> <input
												type="radio" id="yes" name="aayushid"
												class="form-check-input radiostyle" value="0" required
												onclick="fn_ayus_nrh_yes();" />Ayush Id
											</label> <label for="NO" class="form-check-label"> <input
												type="radio" id="no" name="aayushid"
												class="form-check-input radiostyle" value="1" required
												onclick="fn_ayus_nrh_no();" />NRH No
											</label>
											<div class="col-md-12">
												<input type="hidden" id="id" name="id" value="0"
													class="form-control" autocomplete="off" />
											</div>
										</div>
									</div>
								</div>


								<div id="div_ayush_y" style="display: none;">

									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group">
											<label class=" form-control-label">Ayush Id<span
												class="mandatory">*</span></label> <input id="ayush_id"
												name="ayush_id"
												class="form-control-sm form-control effect-9 form-control form-control-lg form-control-a disablecopypaste"
												autocomplete="off" value="" maxlength="50"
												placeholder="Enter Your Ayush Id" /> <span
												class="focus-border"><i></i></span>
										</div>
									</div>


								</div>

								<div id="div_nrh" style="display: none;">
									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group">
											<label class=" form-control-label">NRH No<span
												class="mandatory">*</span></label> <input id="nrh_no" name="nrh_no"
												placeholder="Enter Your NRH No"
												class="form-control-sm form-control effect-9 form-control form-control-lg form-control-a disablecopypaste"
												autocomplete="off" value="" maxlength="50"
												placeholder="Enter NRH No" /> <span class="focus-border"><i></i></span>
										</div>
									</div>


								</div>

								<div id="hideshownew1" style="display: none;">
									<div class="row">
										<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
											<div class="form-group">
												<label class=" form-control-label">Name<span
													class="mandatory">*</span></label> <input type="text" id="name"
													name="name" oninput="this.value = this.value.toUpperCase()"
													maxlength="50"
													class="form-control form-control-lg form-control-a disablecopypaste"
													placeholder="Enter Your Name" autocomplete="off">
											</div>
										</div>

										<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
											<div class="form-group">
												<label class=" form-control-label">Email Id<span
													class="mandatory">*</span></label> <input type="email"
													id="email_id" name="email_id" maxlength="50"
													class="form-control form-control-lg form-control-a disablecopypaste"
													placeholder="Enter Your Email Id" autocomplete="off">
												<div class="col-lg-12">
													<input type="hidden" id="id" name="id" value="0"
														class="form-control form-control-lg form-control-a disablecopypaste"
														autocomplete="off" />
												</div>
											</div>


										</div>

										<div class="col-lg-6 col-md-6 col-sm-12 mb-3" id="hideshow_instituteid" style="display: none;"> 
											<div class="form-group">
												<label class=" form-control-label">Institute Name<span
													class="mandatory">*</span></label> <select name="institute_name"
													class="form-control form-control-lg form-control-a disablecopypaste"
													id="institute_name" placeholder="Enter Your Institute Name"
													onchange="InstituteChangeFn(this.value)">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getInstituteList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.institute_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<div class="col-lg-6 col-md-6 col-sm-12 mb-3" id="hideshow_stateid" style="display: none;">
											<div class="form-group">
												<label class=" form-control-label">State<span
													class="mandatory">*</span></label> <select name="institute_state"
													id="institute_state"
													class="form-control form-control-lg form-control-a disablecopypaste autocomplete"
													>
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${MedStateName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										
										
<!-- 										<div class="col-lg-6 col-md-6 col-sm-12 mb-3" id="registrationstate_stateid" style="display: none;"> -->
<!-- 										<div class="form-group"> -->
<!-- 												<label class=" form-control-label">Registration District -->
<!-- 												</label> -->
											
											
<!-- 												 <select name="regisration_district" id="regisration_district" -->
<!-- 										class="form-control form-control-lg form-control-a disablecopypaste autocomplete" onchange="getRState();"> -->
<!-- 										<option value="0">--Select--</option> -->
<%-- 										<c:forEach var="item" items="${DistrictName}" varStatus="num"> --%>
<%-- 											<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 										</c:forEach> --%>
<!-- 									</select> -->
	              			
<!-- 										</div> -->
<!-- 									</div>	 -->
									
									<div class="col-lg-6 col-md-6 col-sm-12 mb-3" id="registrationstate_stateid" style="display: none;">
										<div class="form-group">
												<label class=" form-control-label">Registration State
												</label>
<!-- 											<label id ="regisration_state" class=" form-control-label">Registration State -->
<!-- 												</label> -->
											
												 <select name="regisration_state" id="regisration_state"
										class="form-control form-control-lg form-control-a disablecopypaste autocomplete">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${MedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
	              			
										</div>
									</div>
									
									<div class="col-lg-6 col-md-6 col-sm-12 mb-3" id="registrationstate_stateid" style="display: none;">
										<div class="form-group">
												<label class=" form-control-label">Registration State<strong style="color: red;">* </strong>
												</label>
											
											
												 <select name="registration_district" id="registration_district"
										class="form-control form-control-lg form-control-a disablecopypaste autocomplete" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${MedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
	              			
										</div>
									</div>	
									
									<div class="col-lg-6 col-md-6 col-sm-12 mb-3" id="aadhardiv" style="display: none;">
										<div class="form-group">
												<label class=" form-control-label">Aadhar Number<strong style="color: red;">* </strong>
												</label>
											<input type="text" id="aadhaar_no" name="aadhaar_no"
																onkeypress="return isAadhar(this,event);"
																oninput="this.value = this.value.toUpperCase()"
																maxlength="14" class="form-control" autocomplete="off"
																placeholder="Aadhaar Number">
	              			
										</div>
									</div>		
									
										
									</div>

									<div
										class="col-lg-12 col-md-12 col-sm-12 mb-3 justify-content-center d-flex">
										<div class="custom-btn footer-btn">


											<a href="login" class="btn btn-dark">Back</a> <a
												href="signup" class="btn-clear btn btn-danger">Reset</a> <input
												type="submit" class="btn-save btn btn-primary" id="save_btn"
												value="Register" onclick="return Validation();">

										</div>
									</div>


								</div>

							</div>




							<input type="hidden" id="hidden_id" name="id"
								class="form-control">

						</form:form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--  End Register Section  -->




<!--  Footer  -->
	<footer class="section-footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="widget-a">
						<div class="w-header-a">
							<h3 class="w-title-a text-brand">Ministry of Ayush</h3>
						</div>
						<div class="w-body-a">
							<p class="w-text-a color-text-a">
							Jawahar Lal Nehru Bhartiya Chikitsa Avam Homoeopathy Anusandhan Bhawan, 61-65, Institutional Area, Janakpuri "D" Block, New Delhi-110058
							</p>
						</div>
						<div class="w-footer-a">
							<ul class="list-unstyled">
								<li class="color-a"><span class="color-text-a">Phone
										:</span> <a href="tel:1800-11-0180,1964">1800-11-0180,1964</a></li>
								<li class="color-a"><span class="color-text-a">Email
										:</span> <a href="mailto:webmanager-ayush@gov.in">webmanager-ayush@gov.in</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-2 col-sm-12 section-md-t3">
					<div class="widget-a">
						<div class="w-header-a">
							<h3 class="w-title-a text-brand">Company</h3>
						</div>
						<div class="w-body-a">
							<ul class="list-unstyled">
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#">Website Policy</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#">Disclaimer</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#">Help</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#">Privacy Policy</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-2 col-sm-12 section-md-t3">
					<div class="w-header-a">
						<h3 class="w-title-a text-brand">Contact with us</h3>
					</div>
					<div class="socials-a">
						<ul class="list-inline">
							<li class="list-inline-item"><a href="#"> <i
									class="bi bi-facebook" aria-hidden="true"></i>
							</a></li>
							<li class="list-inline-item"><a href="#"> <i
									class="bi bi-twitter" aria-hidden="true"></i>
							</a></li>
							<li class="list-inline-item"><a href="#"> <i
									class="bi bi-instagram" aria-hidden="true"></i>
							</a></li>
							<li class="list-inline-item"><a href="#"> <i
									class="bi bi-linkedin" aria-hidden="true"></i>
							</a></li>
						</ul>
					</div>

				</div>
				<div class="col-lg-2 col-md-2 col-sm-12">
					<div class="logo-right">
						<a href="#"><img src="admin/assets/img/ayush-grid.png" class="img-fluid"></a>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="copyright-footer text-center">
						<p class="copyright">
							Website designed, developed, Content managed & hosted by <a
								href="https://bisag-n.gov.in/">Bisag-N</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- End  Footer -->



	<!-- signup default script for developer start -->
	<script>
		$(document)
				.ready(
						function() {
							var key = "${_csrf.parameterName}";
							var value = "${_csrf.token}";

							//		alert(ser);
							jQuery("#ayush_id")
									.keypress(
											function(evt) {
												debugger;
												if (evt.key.length == 1) {
													var job_no = evt.key;
												} else {
													var job_no = $("#ayush_id")
															.val()
															+ evt.key;
												}

												// 		alert(job_no);
												var jobNoAuto = jQuery("#ayush_id");
												jobNoAuto
														.autocomplete({
															source : function(
																	request,
																	response) {
																jQuery
																		.ajax({
																			type : 'POST',
																			url : "getaayushid_Autocomplete?"
																					+ key
																					+ "="
																					+ value,
																			data : {
																				getcolumnname : job_no
																			},
																			success : function(
																					data) {
																				// 			        	  alert(data);
																				// 			        	  debugger;
																				var jobval = [];
																				var length = data.length - 1;
																				if (data.length - 1 != 0) {
																					var enc = data[length]
																							.substring(
																									0,
																									16);
																				}
																				for (var i = 0; i < data.length; i++) {
																					jobval
																							.push(dec(
																									enc,
																									data[i]));
																				}
																				var dataCountry1 = jobval
																						.join("|");
																				var myResponse = [];
																				var autoTextVal = jobNoAuto
																						.val();
																				jQuery
																						.each(
																								dataCountry1
																										.toString()
																										.split(
																												"|"),
																								function(
																										i,
																										e) {
																									var newE = e
																											.substring(
																													0,
																													autoTextVal.length);
																									if (e
																											.toLowerCase()
																											.includes(
																													autoTextVal
																															.toLowerCase())) {
																										myResponse
																												.push(e);
																									}
																								});
																				response(myResponse);
																			}
																		});
															},
															minLength : 1,
															autoFill : true,
															change : function(
																	event, ui) {
																if (ui.item) {
																	autocomplete_data_fetch();
																	return true;

																} else {
																	// 			        	  alert("Please Enteraayush id");
																	document
																			.getElementById("ayush_id").value = "";
																	jobNoAuto
																			.val("");
																	jobNoAuto
																			.focus();
																	return false;
																}
															},
															select : function(
																	event, ui) {
																var course = ui.item.value;

																// 			    	  $.post("getTotalDuration?" + key + "=" + value, {course:course}, function(j) {
																// 							if (j.length != 0) {
																// 								$("#max_duration"+ser).val(j[0][0]);
																// 							}
																// 						});
															}
														});
											});

							//	------------------------nrh_no autocomplete
							jQuery("#nrh_no")
									.keypress(
											function(evt) {
												debugger;
												if (evt.key.length == 1) {
													var job_no = evt.key;
												} else {
													var job_no = $("#nrh_no")
															.val()
															+ evt.key;
												}

												// 		alert(job_no);
												var jobNoAuto = jQuery("#nrh_no");
												jobNoAuto
														.autocomplete({
															source : function(
																	request,
																	response) {
																jQuery
																		.ajax({
																			type : 'POST',
																			url : "getnrhid_Autocomplete?"
																					+ key
																					+ "="
																					+ value,
																			data : {
																				getcolumnname : job_no
																			},
																			success : function(
																					data) {
																				debugger;
																				// 			        	  alert(data);
																				var jobval = [];
																				var length = data.length - 1;
																				if (data.length - 1 != 0) {
																					var enc = data[length]
																							.substring(
																									0,
																									16);
																				}
																				for (var i = 0; i < data.length; i++) {
																					jobval
																							.push(dec(
																									enc,
																									data[i]));
																				}
																				var dataCountry1 = jobval
																						.join("|");
																				var myResponse = [];
																				var autoTextVal = jobNoAuto
																						.val();
																				jQuery
																						.each(
																								dataCountry1
																										.toString()
																										.split(
																												"|"),
																								function(
																										i,
																										e) {
																									var newE = e
																											.substring(
																													0,
																													autoTextVal.length);
																									if (e
																											.toLowerCase()
																											.includes(
																													autoTextVal
																															.toLowerCase())) {
																										myResponse
																												.push(e);
																									}
																								});
																				response(myResponse);
																			}
																		});
															},
															minLength : 1,
															autoFill : true,
															change : function(
																	event, ui) {
																if (ui.item) {
																	nrh_no_autocomplete_data_fetch();
																	return true;

																} else {
																	// 			        	  alert("Please Enteraayush id");
																	document
																			.getElementById("nrh_no").value = "";
																	jobNoAuto
																			.val("");
																	jobNoAuto
																			.focus();
																	return false;
																}
															},
															select : function(
																	event, ui) {
																var course = ui.item.value;

																// 			    	  $.post("getTotalDuration?" + key + "=" + value, {course:course}, function(j) {
																// 							if (j.length != 0) {
																// 								$("#max_duration"+ser).val(j[0][0]);
																// 							}
																// 						});
															}
														});
											});
							
							if('${msg}' != ""){
								alert('${msg}');
							}

						});

		function fn_new() {
			$("#div_ayush").show();
			$("#div_ayush_nrh").hide();
			 $("#hideshow_instituteid").show(); 
			 $("#hideshow_stateid").show(); 
			 $("#registrationstate_stateid").show(); 
			 $("#aadhardiv").show();
		}
		function fn_old() {
			$("#div_ayush").hide();
			$("#div_ayush_nrh").show();
			 $("#hideshownew1").hide();
			  $("#hideshow_instituteid").hide(); 
			  $("#hideshow_stateid").hide(); 
			  $("#registrationstate_stateid").show();
			  $("#hideshow_instituteid").show(); 
				 $("#hideshow_stateid").show(); 
				 $("#aadhardiv").hide();
				 
			  
			 
		}
		
		function fn_ayus_yes() {
			//$("#div_ayush").hide();
			 $("#div_ayush_y").show();
			 $("#hideshownew1").show();
			 
			 
		}
		
		function fn_ayus_no() {
			//$("#div_ayush").hide();
			 $("#div_ayush_y").hide();
			 $("#hideshownew1").show();
			 
			
		}
		function fn_ayus_nrh_yes() {
			//$("#div_ayush").hide();
			 $("#div_ayush_y").show();
			  $("#div_nrh").hide();
			  $("#hideshownew1").show();
//	 		  $("#hideshow_instituteid").show(); 
		}
		function fn_ayus_nrh_no() {
			//$("#div_ayush").hide();
		  $("#div_ayush_y").hide();
	 		 $("#div_nrh").show();
	 		 $("#hideshownew1").show();
	 		 
			
		}
		function autocomplete_data_fetch() {
			var key = "${_csrf.parameterName}";
			var value = "${_csrf.token}";
			// 		alert($("#ayush_id").val());
			var ayush_id = $("#ayush_id").val();
			$.post("getaayushdataautocomplete?" + key + "=" + value, {
				ayush_id : ayush_id
			}, function(j) {
// 							 alert(j[0][2]);
				if (j.length != 0) {
					$("#name").val(j[0][0]);
					$("#email_id").val(j[0][1]);
					$("#institute_name").val(j[0][2]);
					$("#institute_state").val(j[0][3]);
					$("#regisration_state").val(j[0][4]);
				}
			});

		}

		// 	----------------------for nrh_no data fetch

		function nrh_no_autocomplete_data_fetch() {

			var key = "${_csrf.parameterName}";
			var value = "${_csrf.token}";
debugger;
			var nrh_no = $("#nrh_no").val();
			// 		alert(nrh_no+"==dd");
			$.post("nrh_no_autocomplete_data_fetchaaa?" + key + "=" + value, {
				nrh_no : nrh_no
			}, function(j) {
				//  	 alert(j+"--j");
				if (j.length != 0) {
// 										alert("innnnn=xxxx="+j[0][2])
					$("#name").val(j[0][0]);
					$("#email_id").val(j[0][1]);
					$("#institute_name").val(j[0][2]);
					$("#institute_state").val(j[0][3]);
					$("#regisration_state").val(j[0][4]);
				}
			});

		}

		// 	--------------------end

		function Validation() {
			
			var a = $("#ayush_id").val();
			// 		 alert(a+"--a")

			if ($("#name").val().trim() == "") {
				alert("Please Enter Your Name ");
				$("input#name").focus();
				return false;
			}

			if ($("#email_id").val() == "") {
				alert("Only Select Your Email Id");
				$("input#email_id").focus();
				return false;
			}

							if ($("select#institute_name").val() == "0") {
								alert("Only Select Institute Name");
								$("select#institute_name").focus();
								return false;
							}

			if ($("select#institute_state").val() == "0") {
				alert("Only Select Institute State");
				$("select#institute_state").focus();
				return false;
				
			}
			
			if($("#new").prop("checked")){
				if ($("#aadhaar_no").val() == "") {
					alert("Please Enter Your Aadhar Number");
					$("#aadhaar_no").focus();
					return false;
				}
			} 
				
				
// 				if ($("select#regisration_state").val() == "0") {
// 					alert("Only Select Regisration State");
// 					$("select#regisration_state").focus();
// 					return false;
				
				
// 			}
			
			

			return true;

		}

		function getState() {
			var selval = $("#country_id").val();

			$
					.post(
							"getStateUrl?" + key + "=" + value,
							{
								selval : selval
							},
							function(j) {

								var options = '<option value="' + "0" + '">'
										+ "--Select--" + '</option>';
								for (var i = 0; i < j.length; i++) {

									options += '<option   value="' + j[i].state_id + '" name="'+j[i].state_id+'" >'
											+ j[i].state_name + '</option>';
								}
								$("select#state_id").html(options);
							});
		}

		function getRState() {
			var selval = $("#regisration_district").val();

			$
					.post(
							"getRStateMapUrl" ,
							{
								selval : selval
							},
							function(j) {
								var regisration_district = j;
								
								$("#regisration_state").html(options);
							});
		}
		
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
	</script>

	<!-- signup default script for developer end-->







</body>

</html>
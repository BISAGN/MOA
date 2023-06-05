<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  
  <title>Ayush Grid</title>

  <!-- Favicons -->
 <link href="admin/assets/img/favicon.ico" rel="icon">

  <!-- Font CSS Files -->
  <link href="admin/assets/font/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="admin/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
  <link href="admin/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="admin/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
   <link href="admin/assets/vendor/hover.css" rel="stylesheet" media="all">
   <link href="admin/assets/vendor/svg-animation.css" rel="stylesheet" media="all">

  <!-- Template Main CSS File -->
  <link href="admin/assets/css/style.css" rel="stylesheet">
  <link href="admin/assets/css/responsive.css" rel="stylesheet">
  <script type="text/javascript" src="admin/assets/js/jquery-3.6.0.min.js"></script>
  
<!-- theme switch mode -->
<link href="admin/assets/vendor/themeSwitchMode/switchmodestyle.css" rel="stylesheet">
<link href="admin/assets/css/themeswitch-mode.css" rel="stylesheet">
<script type="text/javascript" src="admin/assets/vendor/themeSwitchMode/themeswitchermode.js"></script>
<link href="admin/assets/vendor/page-fontsize/page_fontsize.css" rel="stylesheet">

</head>


<body class="light-mode" id="body">
	
	<!-- theme switch mode start -->
		<div class="switch-mode">
			<ul class="switch-btn-list">
				<li class="switch-btn"><button type="button" id="light" class="sbtn light-btn" onclick="toggleLight(event);"><i class="bi bi-brightness-high-fill"></i></button></li>
				<li class="switch-btn"><button type="button" id="dark" class="sbtn dark-btn" onclick="toggleDark(event);"><i class="bi bi-moon-fill"></i></button></li>
			</ul>			
		</div>
	<!-- theme switch mode end -->

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
	<header>
	<!-- Language start-->
	
	<div class="language-block text-right">
			<div class="container">
			<div class="head-top d-flex">
			
	  <div id="chcp_font_size" class="input-group page-fontsize">
        <div class="input-group-btn">
          <button id="btn-decrease" class="btn btn-default" type="button">A-</button>
          <button id="btn-orig" class="btn btn-default" type="button">A</button>
          <button id="btn-increase" class="btn btn-default" type="button">A+</button>
        </div>
      </div>
				<div class="dropdown">
					<button class="btn btn-secondary dropdown-toggle" type="button"
						id="dropdownMenuButton1" data-bs-toggle="dropdown"
						aria-expanded="false">Language</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
						<li><a class="dropdown-item" href="ncismsignuphindi">हिन्दी</a></li>
					<li><a class="dropdown-item" href="ncismsignup">English</a></li>
					</ul>
				</div>
			</div>
			</div>
		</div>
		
	
	<!-- Language end-->
		<nav
			class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
			<div class="container">
			<div class="header-inner">
				<div class="logo-main">
					<a href="landingpage"> <img
						src="admin/assets/img/grid-logo.png" class="img-fluid">
					</a> <span class="logo-subtext"><img alt="moa-logo"
						src="admin/assets/img/moa-icon.jpg" class="moa-logo">Ministry
						of <span class="org-text">Ayush</span></span>
				</div>
				<button class="navbar-toggler collapsed" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarDefault"
					aria-controls="navbarDefault" aria-expanded="false"
					aria-label="Toggle navigation">
					<span></span> <span></span> <span></span>
				</button>
				<!-- navigation start here -->
				<div class="navbar-collapse collapse justify-content-center"
					id="navbarDefault">
					<div class="container">

						<ul class="navbar-nav justify-content-center">

							<li class="nav-item"><a class="nav-link active"
								href="ncismlanding"><i class="bi bi-house-door"></i></a></li>

							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">Ayush System</a>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="ncismayurveda">Ayurveda</a> <a
										class="dropdown-item" href="ncismunani">Unani</a> <a
										class="dropdown-item" href="ncismsiddha">Siddha</a> <a
										class="dropdown-item" href="ncismsowa">Sowa-Rigpa</a> <a
										class="dropdown-item" href="ncismhomoeopathy">Homoeopathy</a>
								</div></li>

							<li class="nav-item"><a class="nav-link" href="aboutncism">About NCISM</a></li>
							
							<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">Curriculum</a>
								<div class="dropdown-menu">
									<c:set var="sys" value="" />
									<c:forEach var="item" items="${degreelist}" varStatus="num">
											<c:if test="${item[0] != sys}">
												<c:set var="sys" value="${item[0]}" />
												<a class="dropdown-item menu-title" href="#">${item[0]}</a>
											</c:if>
											<c:if test="${item[0] == sys}">
												<a class="dropdown-item" href="portalsignin">${item[2]}</a>
											</c:if>
									</c:forEach>
								</div>
							</li>
							
							<li class="nav-item"><a class="nav-link" href="https://apps.bisag.co.in/BERISM/landingpage">BERISM</a></li>
							
							<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">Admission</a>
								<div class="dropdown-menu">
									<c:set var="sys" value=""/>
									<c:forEach var="item" items="${degreelist}" varStatus="num">
											<c:if test="${item[0] != sys}">
												<c:set var="sys" value="${item[0]}" />
												<a class="dropdown-item menu-title" href="#">${item[0]}</a>
											</c:if>
											<c:if test="${item[0] == sys}">
												<a class="dropdown-item" href="portalsignin">${item[2]}</a>
											</c:if>
									</c:forEach>							
								</div>
							</li>
							
							<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">Educational Info</a>
								<div class="dropdown-menu max-dropdown">	
								
								<div class="multi-col-menu col-m2">
									<a class="dropdown-item" href="https://apps.bisag.co.in/AyushCounselling/StudentSignUp">Application Management</a>																							
									<a class="dropdown-item" href="https://apps.bisag.co.in/AyushCounselling/landingpage">Counselling</a>
									<a class="dropdown-item" href="portalsignin">Time-table management</a>	
									<a class="dropdown-item" href="InstituteInformation">Institute Fee Structure</a>								
									<a class="dropdown-item" href="portalsignin">Lecture management</a>
									<a class="dropdown-item" href="portalsignin">Student management</a>
									<a class="dropdown-item" href="portalsignin">Examination system</a>									
									<a class="dropdown-item" href="portalsignin">Student Attendance</a>
									<a class="dropdown-item" href="portalsignin">Staff Attendance</a>
									<a class="dropdown-item" href="https://apps.bisag.co.in/HMS/hmssignin">Hostel management</a>
									<a class="dropdown-item" href="portalsignin">Library management</a>
									<a class="dropdown-item" href="portalsignin">Feedback management</a>
									<a class="dropdown-item" href="portalsignin">Alumni</a>
									<a class="dropdown-item" href="portalsignin">Reports management</a>																	
									<div class="dropdown-item sub-dropdown-menu">	
										<a class="dropdown-item" href="https://apps.bisag.co.in/API">Open API</a>								
										<a class="dropdown-item" href="https://apps.bisag.co.in/API">Core Registries</a>
										<a class="dropdown-item" href="https://apps.bisag.co.in/API">Core Directories</a>
										<a class="dropdown-item" href="institute_registration_url">Fees</a>
                                    </div>
                                    <div class="dropdown-item sub-dropdown-menu">
										<a class="dropdown-item" href="portalsignin">Placement</a>
										<a class="dropdown-item" href="search_placement_Reg_Url">- Jobseeker</a>
										<a class="dropdown-item" href="company_signup_Url">- Enterprise/Hospital/Other Reg. Form</a>																
                                    </div>
								</div>
									
								</div>
							</li>
							<li class="nav-item"><a class="nav-link" href="portalsignin">E-Learning</a></li>							
						   <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">More</a>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="ViewCollabration">Collaboration with organization</a>									
								</div>
						   </li>
						   	<li class="nav-item"><a class="nav-link" href="ncismcontact">Contact</a></li>

							<!-- 	<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">About</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="aboutncism">NCISM</a> <a
									class="dropdown-item" href="aboutnch">NCH</a> <a
									class="dropdown-item" href="aboutmoa">Ministry of
									Ayush(MOA)</a>
							</div></li>
 -->
							<!-- <li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Information</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="admin">Admin</a> <a
									class="dropdown-item" href="staff">Staff</a> <a
									class="dropdown-item" href="student">Student</a> <a
									class="dropdown-item" href="practitioner">Practitioner</a> <a
									class="dropdown-item" href="placement">Placement</a> <a
									class="dropdown-item" href="openapiplatform">Open API
									Platform</a> <a class="dropdown-item" href="coreregistration">Core
									Registries / Directories</a> <a class="dropdown-item"
									href="education">Education</a>
							</div></li> -->

							<!-- <li class="nav-item"><a class="nav-link" href="alumni">Alumni</a></li>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">E-learning</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="coursemanagement">Course
									management</a> <a class="dropdown-item" href="learningplatforms">Learning
									Platform</a> <a class="dropdown-item" href="eclassroom">E-classroom
									Module</a> <a class="dropdown-item" href="trainingandwebcast">Training
									& Webcast</a> <a class="dropdown-item" href="#">Collaboration
									utilities</a> <a class="dropdown-item" href="#">Research</a>
							</div></li>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Others</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">Regulation</a> <a
									class="dropdown-item" href="ncismcontact">Contact US</a> <a
									class="dropdown-item" href="feedback">Feedback</a>
							</div></li> -->							

						</ul>
					</div>
				</div>
				<!-- navigation end here -->
				<div class="custom-btn">
					<div class="authentication-detail">
						<a href="portalsignin" class="btn btn-bh"><span
							class="login-text">Sign In</span><span class="login-icon d-none"><i
								class="bi bi-person-lines-fill"></i></span></a> 
						<a href="institute_registration_url" class="btn btn-bh" data-bs-toggle="modal" data-bs-target="#exampleModal"><span
							class="login-text">Sign Up</span><span class="login-icon d-none"><i
								class="bi bi-person-plus-fill"></i></span></a>

						<!-- <a href="ncismsignin" class="btn btn-bh"><span
							class="login-text">Sign In</span><span class="login-icon d-none"><i
								class="bi bi-person-lines-fill"></i></span></a> <a
							href="institute_registration_url" class="btn btn-bh"><span
							class="login-text">Sign Up</span><span class="login-icon d-none"><i
								class="bi bi-person-plus-fill"></i></span></a> -->

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
						<li class="logo-s1"><a href="#"><img
								src="admin/assets/img/ncism_logo.png"></a></li>
						<!-- <li class="logo-s1"><a href="#"><img
								src="admin/assets/img/nchlogo.png"></a></li> -->
					</ul>
				</div>
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
						<h1 class="title-single">Institute Sign Up</h1>
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Institute Sign Up</li>
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
			<div class="form-main-block">
				<div class="row justify-content-center">
					<div class="col-lg-5 col-md-12 col-sm-12">
						<div class="area form-bg">
							<div class="form-intro white-text">
								<div class="text-welcome">
									<span class="img-intro"><img
										src="admin/assets/img/ncism_logo.png"></span>
									<h4 class="intro-title">Welcome to Institute Sign Up</h4>
								</div>								
							</div>
							<ul class="circles">
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</div>						
					</div>
					<div class="col-lg-5 col-md-12 col-sm-12">
						<div class="login-form-main form-min-height">
							<form:form action="Practitioner_registration_action"
								method='POST' modelAttribute="Practitioner_registration_cmd"
								enctype="multipart/form-data">

								<div class="row">
									<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">Institute Name<span class="mandatory">*</span></label>
														<input id="institute_name" name="institute_name" class="form-control form-control-lg form-control-a effect-9" autocomplete="off" maxlength="50" placeholder="Enter Institute Name">
														
												</div>
											</div>	
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">Email Id<span class="mandatory">*</span></label>
														<input id="institute_email" name="institute_email" class="form-control form-control-lg form-control-a effect-9" autocomplete="off" maxlength="50" placeholder="Enter Email Id">

												</div>
											</div>	
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">Mobile No.<span class="mandatory">*</span></label>
														<input id="institute_mob_no" name="institute_mob_no" class="form-control form-control-lg form-control-a effect-9" autocomplete="off" maxlength="50" placeholder="Enter Mobile No">
														

												</div>
											</div>	
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">University<span class="mandatory">*</span></label>
														
														<select name="university_name"
														class="form-control form-control-lg form-control-a disablecopypaste"
														id="university_name">
														<option value="0">---Select---</option>
													</select>	
														

												</div>
											</div>
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">Country<span class="mandatory">*</span></label>
													
													<select name="country_id" id="country_id"
														onchange="getState();"
														class="form-control form-control-lg form-control-a effect-9 select2 narrow wrap">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${MedCountryName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>	
														

												</div>
											</div>
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">State<span class="mandatory">*</span></label>
													
													<select name="state_id" id="state_id"
														class="form-control form-control-lg form-control-a effect-9 select2 narrow wrap"
														onchange="getDistrict();">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${MedStateName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>	
														

												</div>
											</div>
											
												<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">District<span class="mandatory">*</span></label>
												
													<select name="district_id" id="district_id"
														class="form-control form-control-lg form-control-a effect-9 select2 narrow wrap">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${MedDistrictName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>	
														

												</div>
											</div>
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">Code<span class="mandatory">*</span></label>
														<input id="code" name="code" class="form-control form-control-lg form-control-a effect-9" autocomplete="off" value="" maxlength="50" placeholder="Enter Code">
														
														

												</div>
											</div>
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">Address<span class="mandatory">*</span></label>
														<textarea id="address" name="address" rows="3" cols="50"  class="form-control form-control-lg form-control-a effect-9"></textarea>

												</div>
											</div>
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">Upload Image<span class="mandatory">*</span></label>
                                                        <input type="file" accept=".jpeg,.png" id="upload_image" name="upload_image" class="form-control form-control-lg form-control-a effect-9">
												</div>
											</div>	
											
											<div class="col-lg-12 col-md-12 col-sm-12 mb-3 justify-content-center">
											<div class="footer-btn">
												<ul class="footer-btn-list">
													<li class="f-btn"><a href="ncismsignin" class="link-color">Sign in instead</a></li>
													<li class="f-btn"><input type="submit" class="btn btn-bh" id="save_btn" value="SignUp" onclick="return Validation();"></li>													
												</ul>												 										
											</div>
										</div>	
											
																	
										<!-- <div class="box-border-p">
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
									</div> -->
										<!-- <div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group">
											<input id="id" name="id" value="0"
												class="form-control" autocomplete="off" />
										</div>
									</div> -->
	                                   <%-- <div class="col-lg-12 col-md-12 col-sm-12">	
										<div id="div_ayush" style="" class="box-border-p row">
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
	
										<div id="div_ayush_nrh" style="display: none;" class="box-border-p row">
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
	
										<div id="div_ayush_y" style="display: none;" class="row">
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
	
										<div id="div_nrh" style="display: none;" class="row">
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
														<label class=" form-control-label">First Name<span class="mandatory">*</span></label>
														<input type="text" id="name"
														name="name"
														oninput="this.value = this.value.toUpperCase()"
														maxlength="50"
														class="form-control form-control-lg form-control-a disablecopypaste"
														placeholder="Enter Your Name" autocomplete="off">
												</div>
											</div>
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">Last Name<span class="mandatory">*</span></label>
														<input type="text" id="name"
														name="name"
														oninput="this.value = this.value.toUpperCase()"
														maxlength="50"
														class="form-control form-control-lg form-control-a disablecopypaste"
														placeholder="Enter Your Name" autocomplete="off">
												</div>
											</div>

											<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
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

											<div class="col-lg-6 col-md-6 col-sm-12 mb-3"
												id="hideshow_instituteid" style="display: none;">
												<div class="form-group">
													<label class=" form-control-label">Institute Name<span
														class="mandatory">*</span></label> <select name="institute_name"
														class="form-control form-control-lg form-control-a disablecopypaste"
														id="institute_name"
														placeholder="Enter Your Institute Name"
														onchange="InstituteChangeFn(this.value)">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getInstituteList}"
															varStatus="num">
															<option value="${item.id}" name="${item.id}">${item.institute_name}</option>
														</c:forEach>
													</select>
												</div>
											</div>

											<div class="col-lg-6 col-md-6 col-sm-12 mb-3"
												id="hideshow_stateid" style="display: none;">
												<div class="form-group">
													<label class=" form-control-label">State<span
														class="mandatory">*</span></label> <select name="institute_state"
														id="institute_state"
														class="form-control form-control-lg form-control-a disablecopypaste autocomplete">
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
																					<c:forEach var="item" items="${DistrictName}" varStatus="num">
																						<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																					</c:forEach>
											<!-- 									</select> -->

											<!-- 										</div> -->
											<!-- 									</div>	 -->

											<div class="col-lg-6 col-md-6 col-sm-12 mb-3"
												id="registrationstate_stateid" style="display: none;">
												<div class="form-group">
													<label class=" form-control-label">Registration
														State </label>
													<!-- 											<label id ="regisration_state" class=" form-control-label">Registration State -->
													<!-- 												</label> -->

													<select name="regisration_state" id="regisration_state"
														class="form-control form-control-lg form-control-a disablecopypaste autocomplete">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${MedStateName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>

												</div>
											</div>

											<div class="col-lg-6 col-md-6 col-sm-12 mb-3"
												id="registrationstate_stateid" style="display: none;">
												<div class="form-group">
													<label class=" form-control-label">Registration
														State<strong style="color: red;">* </strong>
													</label> <select name="registration_district"
														id="registration_district"
														class="form-control form-control-lg form-control-a disablecopypaste autocomplete">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${MedStateName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>

												</div>
											</div>

										</div>

										<div
											class="col-lg-12 col-md-12 col-sm-12 mb-3 justify-content-center">
											<div class="footer-btn">
												<ul class="footer-btn-list">
													<li class="f-btn"><a href="ncismsignin" class="link-color">Sign in instead</a></li>
													<li class="f-btn"><input type="submit" class="btn-save btn btn-primary" id="save_btn" value="SignUp" onclick="return Validation();"></li>													
												</ul>												 										
											</div>
										</div>


									</div>
									</div> --%>
								</div>
								
								<input type="hidden" id="hidden_id" name="id"
									class="form-control">

							</form:form>
						</div>
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
				<div class="col-lg-5 col-md-6 col-sm-12">
					<div class="widget-a">
						<div class="w-header-a mb-2">
							<a href="#"><img alt="moa-logo"
								src="admin/assets/img/ayushlogo-w.png"></a>
						</div>
						<div class="w-body-a">
							<p class="w-text-a">Jawahar Lal Nehru Bhartiya
								Chikitsa Avam Homoeopathy Anusandhan Bhawan, 61-65,
								Institutional Area, Janakpuri "D" Block, New Delhi-110058</p>
						</div>
						<div class="w-footer-a">
							<ul class="list-unstyled">
								<li class="color-a"><span class="color-text-a">Phone :</span><a href="tel:1800-11-0180,1964">1800-11-0180,1964</a></li>
								<li class="color-a"><span class="color-text-a">Email :</span><a href="mailto:webmanager-ayush@gov.in">webmanager-ayush@gov.in</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-6 col-sm-12 section-md-t3">
					<div class="widget-a">
						<div class="w-header-a">
							<h5 class="w-title-a">Ayush Grid</h5>
						</div>
						<div class="w-body-a">
							<ul class="list-unstyled">
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#">Website Policy</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#">Disclaimer</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#">Privacy Policy</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="ncismcontact">Contact Us</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-12 section-md-t3">
					<div class="widget-a">
						<div class="w-header-a">
							<h5 class="w-title-a">Connect With Us</h5>
						</div>
						<div class="socials-a">
							<ul class="list-inline">
								<li class="list-inline-item"><a href="#" class="fb-icon hvr-bounce-in"><i class="bi bi-facebook" aria-hidden="true"></i></a></li>
								<li class="list-inline-item"><a href="#" class="tw-icon hvr-bounce-in"><i class="bi bi-twitter" aria-hidden="true"></i></a></li>
								<li class="list-inline-item"><a href="#" class="insta-icon hvr-bounce-in"><i class="bi bi-instagram" aria-hidden="true"></i></a></li>
								<li class="list-inline-item"><a href="#" class="ld-icon hvr-bounce-in"><i class="bi bi-linkedin" aria-hidden="true"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-6 col-sm-12">				
					<div class="logo-footer-right widget-a">
						<a href="#"><img src="admin/assets/img/ayush-grid.png"
							class="img-fluid"></a>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="copyright-footer text-center">
						<p class="copyright">
							Website designed, developed, Content managed & hosted by <a href="https://bisag-n.gov.in/" class="f-link">BISAG-N</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- End  Footer -->

	<!-- <div id="preloader"></div> -->	
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center hvr-icon-spin"><i class="bi bi-arrow-up-short hvr-icon"></i></a>
	
	<div class="modal fade custom-modal custom-model-icon" tabindex="-1" role="dialog"
			aria-labelledby="myLargeModalLabel" aria-hidden="true"
			id="exampleModal">
			<div class="modal-dialog modal-md modal-dialog-centered">
				<div class="modal-content text-center">
					 <div class="modal-header pb-2">
						
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
				<div class="modal-body">
                  <div class="custom-model-img">
               <!--  <img src="admin/assets/svg/signup-img.svg" alt=""> -->
               <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:svgjs="http://svgjs.com/svgjs" class="animated" id="freepik_stories-gdpr" viewBox="0 0 750 500" version="1.1"><g id="freepik--background-complete--inject-2" class="animable" style="transform-origin: 429.563px 250.795px;"><rect x="165.22" y="50.28" width="60.41" height="82.67" style="fill: rgb(245, 245, 245); transform-origin: 195.425px 91.615px;" id="el56nwcbbhc6" class="animable"/><rect x="149.91" y="60.69" width="60.41" height="82.67" style="fill: rgb(224, 224, 224); transform-origin: 180.115px 102.025px;" id="eln86ok0l7tj" class="animable"/><path d="M495.07,173.74l-.39,0h-1.17l-4.55,0-17.07.06L413,174h-.16l0-.16a14.64,14.64,0,0,1,14.44-18.74l-.21.29a12.27,12.27,0,0,1-.46-4,13,13,0,0,1,6.74-11,13.36,13.36,0,0,1,12.49.07l-.31.11a19.46,19.46,0,0,1,11.43-10.65,18.28,18.28,0,0,1,14.5.68,19.78,19.78,0,0,1,9,9.81,18.73,18.73,0,0,1,1.1,11.75l-.16-.19a14,14,0,0,1,6.82,1,14.3,14.3,0,0,1,5.06,3.46,9.16,9.16,0,0,1,1.59,2.29,12.73,12.73,0,0,1,.85,2.42,22,22,0,0,1,.58,4.44,25.58,25.58,0,0,1-.53,6.12,13.59,13.59,0,0,1-.43,1.55l-.18.5" style="fill: rgb(235, 235, 235); transform-origin: 454.284px 151.369px;" id="elghuxg9rhccg" class="animable"/><path d="M652.14,134.45a15.05,15.05,0,1,0,17.49,15.9h0l4.13-.69L676,145l3.22,3.74,5.17-.86,2.21-4.64,3.22,3.74,4.44-.74,1.35-2.83,2,2.28,2.59-.43,2.84-3.76-4.87-5.36-30.75,5.11A15.05,15.05,0,0,0,652.14,134.45Zm-8.38,16.66a4.32,4.32,0,1,1,5,3.55A4.32,4.32,0,0,1,643.76,151.11Z" style="fill: rgb(235, 235, 235); transform-origin: 671.303px 149.288px;" id="el31ge5cp5dsr" class="animable"/><path d="M636.87,249.49l1.86,34.1a2.3,2.3,0,0,0,2.28,2.18l61.35.44a2.3,2.3,0,0,0,2.32-2.28l.19-26.54a2.3,2.3,0,0,0-2.3-2.32l-37.73,0a2.25,2.25,0,0,1-1.62-.68l-6.51-6.5a2.33,2.33,0,0,0-1.61-.68l-15.91-.11A2.3,2.3,0,0,0,636.87,249.49Z" style="fill: rgb(235, 235, 235); transform-origin: 670.869px 266.655px;" id="elirffh0k0e3l" class="animable"/><g id="el9al81kqvqfj"><rect x="637.74" y="250.82" width="67.04" height="40.06" style="fill: rgb(245, 245, 245); transform-origin: 671.26px 270.85px; transform: rotate(0.42deg);" class="animable" id="el7iipofcgjpk"/></g><path d="M632.53,254.13l4.81,41.8a1.48,1.48,0,0,0,1.46,1.32l65.33.47a1.47,1.47,0,0,0,1.48-1.35l3.6-41.75a1.47,1.47,0,0,0-1.47-1.6L634,252.48A1.47,1.47,0,0,0,632.53,254.13Z" style="fill: rgb(235, 235, 235); transform-origin: 670.867px 275.1px;" id="els06kw10tx4g" class="animable"/><path d="M629.13,437.75c-15.55-10.86-19.08-31-18.46-49.16.1-3-.23-6.22,1.21-8.87s4.79-4.47,7.5-3.2c2.25,1.06,3.23,3.68,4.49,5.84a17.65,17.65,0,0,0,7.09,6.78c1.93,1,4.32,1.61,6.22.56,2.61-1.44,2.92-5.05,2.9-8.06q-.06-8.42-.11-16.82a27.89,27.89,0,0,1,.91-8.94c.94-2.86,3-5.51,5.87-6.32s6.4.85,7,3.8a27.52,27.52,0,0,1,.08,3.75c.09,1.25.67,2.63,1.85,3s2.41-.5,3.31-1.39c3.11-3,5.21-6.95,7.18-10.84s3.89-7.88,6.71-11.2,6.71-5.94,11-6.18,8.91,2.42,10,6.65-1.39,8.55-4.08,12a67.23,67.23,0,0,1-14,13.33A5.52,5.52,0,0,0,673.5,365c-.65,2,1.25,4,3.23,4.66,2.24.75,4.68.51,7,.79s4.89,1.31,5.78,3.53c1.23,3.07-1.34,6.3-3.87,8.41a57.55,57.55,0,0,1-17,9.83c-2.22.81-4.55,1.52-6.33,3.08s-2.87,4.26-1.79,6.38,3.85,2.8,6.17,2.37,4.39-1.69,6.6-2.52c4.14-1.56,9.48-1.28,12.13,2.3a9.49,9.49,0,0,1,1,8.42,22,22,0,0,1-4.56,7.44A57.84,57.84,0,0,1,657.15,437c-9.59,3.33-18.48,4.23-28,.74" style="fill: rgb(235, 235, 235); transform-origin: 652.373px 385.225px;" id="elijmypkxriv" class="animable"/><path d="M628.89,451.19a132.85,132.85,0,0,1,4.92-24.8,270.1,270.1,0,0,1,10.71-29l3.19-7.46c1.06-2.45,2.1-4.88,3.27-7.19a133.38,133.38,0,0,1,7.49-12.9c2.58-4,5.17-7.68,7.62-11.11s4.81-6.58,7.07-9.36A106.8,106.8,0,0,1,684.82,337c1.48-1.34,2.67-2.34,3.48-3l.93-.76a1.51,1.51,0,0,1,.33-.24l-.29.29c-.23.19-.52.46-.9.79-.79.7-2,1.73-3.41,3.09a114.14,114.14,0,0,0-11.5,12.41c-2.24,2.79-4.56,5.95-7,9.38s-5,7.14-7.57,11.12A136.29,136.29,0,0,0,651.47,383c-1.16,2.3-2.2,4.7-3.25,7.16l-3.2,7.46a278.44,278.44,0,0,0-10.72,29,135.16,135.16,0,0,0-5,24.69" style="fill: rgb(224, 224, 224); transform-origin: 659.225px 392.155px;" id="el7uaebi0ktue" class="animable"/><path d="M651.14,382.68a6.7,6.7,0,0,1-.29-1.29c-.16-.83-.35-2-.56-3.54-.43-3-.87-7.15-1.35-11.74s-1-8.73-1.45-11.71c-.23-1.49-.43-2.7-.57-3.53a5.45,5.45,0,0,1-.17-1.31,6,6,0,0,1,.38,1.27c.21.81.47,2,.75,3.5.57,3,1.14,7.13,1.61,11.72s.87,8.62,1.19,11.75c.15,1.41.28,2.61.38,3.56A5.7,5.7,0,0,1,651.14,382.68Z" style="fill: rgb(224, 224, 224); transform-origin: 648.949px 366.12px;" id="elwjwva7ngbj" class="animable"/><path d="M689.84,375.44a7.67,7.67,0,0,1-1.55.19c-1,.09-2.43.21-4.2.41-3.54.39-8.42,1.09-13.76,2.09s-10.15,2.15-13.6,3.06c-1.73.45-3.12.85-4.08,1.12a9.24,9.24,0,0,1-1.51.37,8.48,8.48,0,0,1,1.45-.58c.94-.34,2.32-.79,4-1.29,3.43-1,8.23-2.21,13.59-3.23a135.89,135.89,0,0,1,13.84-1.93c1.78-.16,3.22-.22,4.22-.24A10.56,10.56,0,0,1,689.84,375.44Z" style="fill: rgb(224, 224, 224); transform-origin: 670.49px 379.036px;" id="eli28nqkozzr" class="animable"/><path d="M634.29,425.68a2.26,2.26,0,0,1-.27-.48c-.18-.37-.4-.84-.66-1.4-.57-1.21-1.35-3-2.28-5.22-1.87-4.43-4.25-10.63-6.79-17.51s-4.87-13.11-6.64-17.57c-.87-2.17-1.58-4-2.12-5.29l-.56-1.44a2.3,2.3,0,0,1-.17-.52,2.26,2.26,0,0,1,.27.48l.66,1.4c.57,1.21,1.35,3,2.28,5.22,1.87,4.43,4.25,10.63,6.79,17.52s4.87,13.1,6.64,17.56l2.12,5.29.56,1.44A2.3,2.3,0,0,1,634.29,425.68Z" style="fill: rgb(224, 224, 224); transform-origin: 624.545px 400.965px;" id="el7bu9xfoqsb" class="animable"/><path d="M685.71,409.1a2.12,2.12,0,0,1-.51.21l-1.5.5-5.5,1.79c-4.65,1.51-11.05,3.66-18.11,6s-13.5,4.5-18.18,5.91c-2.34.71-4.24,1.26-5.56,1.61l-1.52.4a2.73,2.73,0,0,1-.54.11,2.08,2.08,0,0,1,.51-.22l1.49-.49,5.51-1.79c4.65-1.51,11-3.66,18.11-6.06s13.49-4.49,18.17-5.9c2.34-.71,4.24-1.26,5.56-1.61l1.53-.4A2.59,2.59,0,0,1,685.71,409.1Z" style="fill: rgb(224, 224, 224); transform-origin: 660px 417.365px;" id="elue3j5q2qff" class="animable"/></g><g id="freepik--Floor--inject-2" class="animable" style="transform-origin: 371.3px 451.245px;"><path d="M692.4,451.23c0,.15-143.77.26-321.08.26s-321.12-.11-321.12-.26S193.94,451,371.32,451,692.4,451.09,692.4,451.23Z" style="fill: rgb(38, 50, 56); transform-origin: 371.3px 451.245px;" id="elylgjjkvo5os" class="animable"/></g><g id="freepik--Plant--inject-2" class="animable" style="transform-origin: 616.76px 421.656px;"><path d="M618.33,416.32c1.74,2.25,4.87,1.32,6.27-.18s1.91-3.58,2.38-5.58c1.06-4.55,2.13-9.32.94-13.84a7.76,7.76,0,0,0-1.95-3.66,4,4,0,0,0-3.85-1.06c-1.58.5-2.49,2.11-3.13,3.64a32.78,32.78,0,0,0-2.53,14.24,11.3,11.3,0,0,0,1.87,6.44" style="fill: #EFB504; transform-origin: 622.434px 404.769px;" id="ele4plut8cbxf" class="animable"/><g id="el4d36qbrvc7y" class=""><g style="opacity: 0.2; transform-origin: 622.434px 404.769px;" class="animable" id="eljncyq8ro6yo"><path d="M618.33,416.32c1.74,2.25,4.87,1.32,6.27-.18s1.91-3.58,2.38-5.58c1.06-4.55,2.13-9.32.94-13.84a7.76,7.76,0,0,0-1.95-3.66,4,4,0,0,0-3.85-1.06c-1.58.5-2.49,2.11-3.13,3.64a32.78,32.78,0,0,0-2.53,14.24,11.3,11.3,0,0,0,1.87,6.44" id="el8f2ugwu7xiw" class="animable" style="transform-origin: 622.434px 404.769px;"/></g></g><path d="M625,427.8a14.13,14.13,0,0,1,8.71-1.51,7.32,7.32,0,0,1,4.42,1.72c1.15,1.14,1.46,3.23.27,4.32a4.23,4.23,0,0,1-2.7.83c-2.32.09-4.91-.19-6.69,1.3-1,.84-1.65,2.2-2.89,2.66a3,3,0,0,1-3.36-1.3,5.11,5.11,0,0,1-.56-3.74A5.75,5.75,0,0,1,625,427.8Z" style="fill: #EFB504; transform-origin: 630.61px 431.686px;" id="eluaje27xa2gb" class="animable"/><path d="M614,419.89a8.49,8.49,0,0,0,.92-7.86,15.05,15.05,0,0,0-5-6.47,24.93,24.93,0,0,0-8.52-4.46,8.84,8.84,0,0,0-3.74-.44,3.9,3.9,0,0,0-3,2c-.79,1.71.15,3.68,1.14,5.27a62.74,62.74,0,0,0,5.84,8c1.74,2,3.71,4,6.23,4.81s5.1.58,6.33-1.2" style="fill: #EFB504; transform-origin: 604.927px 410.902px;" id="elyy9ag3rr3f" class="animable"/><path d="M620,451.44a5.08,5.08,0,0,0,.09-1.06c0-.78.09-1.75.14-2.91s.18-2.67.43-4.26a33,33,0,0,1,1.29-5.08,18.54,18.54,0,0,1,2.32-4.65,9.48,9.48,0,0,1,3.1-2.84,8.93,8.93,0,0,1,2.67-1,4.83,4.83,0,0,0,1-.2,4.57,4.57,0,0,0-1.07,0,8.11,8.11,0,0,0-2.79.88,9.4,9.4,0,0,0-3.27,2.87,18.1,18.1,0,0,0-2.41,4.75,30.49,30.49,0,0,0-1.27,5.16,34.78,34.78,0,0,0-.35,4.31c0,1.23,0,2.23,0,2.91A4.82,4.82,0,0,0,620,451.44Z" style="fill: rgb(38, 50, 56); transform-origin: 625.46px 440.424px;" id="el029vd0eouw4t" class="animable"/><path d="M620.08,449.21a3,3,0,0,0,0-.49c0-.37,0-.84-.07-1.41-.07-1.21-.16-3-.2-5.16-.11-4.36,0-10.38.35-17s1.06-12.64,1.72-16.95c.16-1.08.32-2.05.47-2.9s.27-1.59.4-2.19.19-1,.26-1.38a3,3,0,0,0,.06-.49,1.7,1.7,0,0,0-.15.47l-.34,1.37c-.15.59-.3,1.32-.47,2.17s-.36,1.82-.53,2.9a159.33,159.33,0,0,0-1.86,17c-.39,6.65-.41,12.7-.22,17.06.08,2.19.22,3.95.34,5.17l.15,1.4A1.66,1.66,0,0,0,620.08,449.21Z" style="fill: rgb(38, 50, 56); transform-origin: 621.226px 425.225px;" id="el6p7gcib2rcd" class="animable"/><path d="M619.52,437.58a8.83,8.83,0,0,0-.09-1.46,39.89,39.89,0,0,0-.65-3.95,49.94,49.94,0,0,0-1.58-5.72,51.35,51.35,0,0,0-2.77-6.73A36.59,36.59,0,0,0,606.77,409a18.72,18.72,0,0,0-1.75-1.47c-.27-.2-.51-.4-.75-.56l-.67-.42a12.59,12.59,0,0,0-1.27-.73,46.38,46.38,0,0,1,4.23,3.41A38.36,38.36,0,0,1,614,419.92a53,53,0,0,1,2.78,6.66,58.69,58.69,0,0,1,1.66,5.66c.39,1.63.63,3,.79,3.9A8.78,8.78,0,0,0,619.52,437.58Z" style="fill: rgb(38, 50, 56); transform-origin: 610.926px 421.7px;" id="elo1kfbtp9a" class="animable"/></g><g id="freepik--User--inject-2" class="animable" style="transform-origin: 116.75px 291.665px;"><rect x="34.82" y="226.75" width="113.06" height="75.72" style="fill: rgb(250, 250, 250); transform-origin: 91.35px 264.61px;" id="el497igy0lyno" class="animable"/><path d="M117.16,272.68h-3V254.55a7.9,7.9,0,0,0-15.79,0v18.13h-3V254.55a10.91,10.91,0,0,1,21.81,0Z" style="fill: rgb(38, 50, 56); transform-origin: 106.275px 258.325px;" id="el9254itmcroh" class="animable"/><rect x="91.34" y="264.61" width="29.83" height="19.14" rx="3.39" style="fill: rgb(38, 50, 56); transform-origin: 106.255px 274.18px;" id="elmqq35hytyr" class="animable"/><path d="M106.26,274.18a2.49,2.49,0,1,1,2.49-2.49,2.49,2.49,0,0,1-2.49,2.49" style="fill: rgb(69, 90, 100); transform-origin: 106.26px 271.69px;" id="el6dh6e5dszqe" class="animable"/><rect x="105.58" y="272.68" width="1.36" height="5.13" style="fill: rgb(69, 90, 100); transform-origin: 106.26px 275.245px;" id="ell6bxxxntvnb" class="animable"/><path d="M90.6,272.46a12.05,12.05,0,0,0,7.19-11.16c0-6.67-5-12.1-11.27-12.13s-11.34,5.35-11.37,12a12,12,0,0,0,7.22,11.27C65,275.09,65.74,294,65.74,294l41.19.19S107.87,275.07,90.6,272.46Z" style="fill: #EFB504; transform-origin: 86.3372px 271.68px;" id="eleomovnkeh6f" class="animable"/><path d="M147.87,302.48s0-.48,0-1.39,0-2.25,0-4c0-3.51,0-8.65-.06-15.23,0-13.16-.06-32.06-.1-55.11l.2.21-113,0h0l.26-.26c0,27,0,52.63,0,75.73l-.23-.24,81.43.11,23.28.07,6.19,0h1.6l.55,0h-2.09l-6.15,0-23.22.07-81.59.11h-.24v-.23c0-23.1,0-48.75,0-75.73v-.26h.27l113.05.06h.21v.2c0,23.1-.08,42.05-.11,55.24,0,6.56,0,11.69-.05,15.19,0,1.73,0,3.05,0,4S147.87,302.48,147.87,302.48Z" style="fill: rgb(38, 50, 56); transform-origin: 91.465px 264.44px;" id="el8i8jdy9fykh" class="animable"/><path d="M147.85,235.85c0,.14-25.3.26-56.49.26s-56.49-.12-56.49-.26,25.29-.26,56.49-.26S147.85,235.71,147.85,235.85Z" style="fill: rgb(38, 50, 56); transform-origin: 91.36px 235.85px;" id="eljxj1ehgzd5r" class="animable"/><path d="M42.26,231.31a1.17,1.17,0,1,1-1.17-1.17A1.17,1.17,0,0,1,42.26,231.31Z" style="fill: rgb(38, 50, 56); transform-origin: 41.09px 231.31px;" id="eln25h2k4rued" class="animable"/><path d="M46.09,231.31a1.17,1.17,0,0,1-2.34,0,1.17,1.17,0,0,1,2.34,0Z" style="fill: rgb(38, 50, 56); transform-origin: 44.92px 231.31px;" id="elyg2bt3xupe" class="animable"/><path d="M50.2,231.31A1.17,1.17,0,1,1,49,230.14,1.17,1.17,0,0,1,50.2,231.31Z" style="fill: rgb(38, 50, 56); transform-origin: 49.03px 231.31px;" id="elrq1slb7w87" class="animable"/><path d="M147.87,302.48s0-.48,0-1.39,0-2.25,0-4c0-3.51,0-8.65-.06-15.23,0-13.16-.06-32.06-.1-55.11l.2.21-113,0h0l.26-.26c0,27,0,52.63,0,75.73l-.23-.24,81.43.11,23.28.07,6.19,0h1.6l.55,0h-2.09l-6.15,0-23.22.07-81.59.11h-.24v-.23c0-23.1,0-48.75,0-75.73v-.26h.27l113.05.06h.21v.2c0,23.1-.08,42.05-.11,55.24,0,6.56,0,11.69-.05,15.19,0,1.73,0,3.05,0,4S147.87,302.48,147.87,302.48Z" style="fill: rgb(38, 50, 56); transform-origin: 91.465px 264.44px;" id="elz33xa1crzcd" class="animable"/><path d="M90.61,305c-.14,0-.26-.56-.26-1.25s.12-1.25.26-1.25.27.56.27,1.25S90.76,305,90.61,305Z" style="fill: rgb(38, 50, 56); transform-origin: 90.615px 303.75px;" id="eleulq40qhsvn" class="animable"/><path d="M90.61,315.92a7.55,7.55,0,0,1-.26-2.74,7.47,7.47,0,0,1,.26-2.73,7.11,7.11,0,0,1,.27,2.73A7.18,7.18,0,0,1,90.61,315.92Z" style="fill: rgb(38, 50, 56); transform-origin: 90.6162px 313.185px;" id="el88j0krqq4rh" class="animable"/><path d="M90.61,326.86a7.55,7.55,0,0,1-.26-2.74,7.47,7.47,0,0,1,.26-2.73,7.11,7.11,0,0,1,.27,2.73A7.18,7.18,0,0,1,90.61,326.86Z" style="fill: rgb(38, 50, 56); transform-origin: 90.6162px 324.125px;" id="el2lohdwb036" class="animable"/><path d="M90.61,337.8a7.55,7.55,0,0,1-.26-2.74,7.47,7.47,0,0,1,.26-2.73,7.11,7.11,0,0,1,.27,2.73A7.18,7.18,0,0,1,90.61,337.8Z" style="fill: rgb(38, 50, 56); transform-origin: 90.6162px 335.065px;" id="elmj9c5atwz3" class="animable"/><path d="M90.61,348.74a7.55,7.55,0,0,1-.26-2.74,7.47,7.47,0,0,1,.26-2.73,7.11,7.11,0,0,1,.27,2.73A7.18,7.18,0,0,1,90.61,348.74Z" style="fill: rgb(38, 50, 56); transform-origin: 90.6162px 346.005px;" id="elo5rarqb70r8" class="animable"/><path d="M93.11,356.71a6.27,6.27,0,0,1-2.5.26l-.26-.26h0v-.94c0-.3,0-.57.06-.8,0-.45.11-.73.18-.73s.14.28.19.73c0,.23,0,.5,0,.8l0,.47v.47h0c-.12-.12.25.26-.27-.26A6.27,6.27,0,0,1,93.11,356.71Z" style="fill: rgb(38, 50, 56); transform-origin: 91.73px 355.62px;" id="elxs1v4tuvv6" class="animable"/><path d="M102.94,356.71a6.11,6.11,0,0,1-2.46.26,6.08,6.08,0,0,1-2.45-.26,6.08,6.08,0,0,1,2.45-.26A6.11,6.11,0,0,1,102.94,356.71Z" style="fill: rgb(38, 50, 56); transform-origin: 100.485px 356.71px;" id="elxmn6rvbwqf" class="animable"/><path d="M112.77,356.71a6.11,6.11,0,0,1-2.46.26,6.11,6.11,0,0,1-2.46-.26,6.11,6.11,0,0,1,2.46-.26A6.11,6.11,0,0,1,112.77,356.71Z" style="fill: rgb(38, 50, 56); transform-origin: 110.31px 356.71px;" id="eluzcx3r6dzb" class="animable"/><path d="M122.6,356.71a6.11,6.11,0,0,1-2.46.26,6.11,6.11,0,0,1-2.46-.26,6.11,6.11,0,0,1,2.46-.26A6.11,6.11,0,0,1,122.6,356.71Z" style="fill: rgb(38, 50, 56); transform-origin: 120.14px 356.71px;" id="ely9zjwl6in6" class="animable"/><path d="M132.42,356.71a11.72,11.72,0,0,1-4.91,0,11.72,11.72,0,0,1,4.91,0Z" style="fill: rgb(38, 50, 56); transform-origin: 129.965px 356.71px;" id="el4een6ovanwc" class="animable"/><path d="M142.25,356.71a11.72,11.72,0,0,1-4.91,0,11.72,11.72,0,0,1,4.91,0Z" style="fill: rgb(38, 50, 56); transform-origin: 139.795px 356.71px;" id="el0i5qxiz65yu8" class="animable"/><path d="M152.08,356.71a11.77,11.77,0,0,1-4.92,0,11.77,11.77,0,0,1,4.92,0Z" style="fill: rgb(38, 50, 56); transform-origin: 149.62px 356.71px;" id="elkqbnfndswnb" class="animable"/><path d="M161.9,356.71a11.72,11.72,0,0,1-4.91,0,11.72,11.72,0,0,1,4.91,0Z" style="fill: rgb(38, 50, 56); transform-origin: 159.445px 356.71px;" id="elrltdyelza7m" class="animable"/><path d="M171.73,356.71a11.72,11.72,0,0,1-4.91,0,11.72,11.72,0,0,1,4.91,0Z" style="fill: rgb(38, 50, 56); transform-origin: 169.275px 356.71px;" id="elqdqemy20c2" class="animable"/><path d="M181.56,356.71a11.77,11.77,0,0,1-4.92,0,11.77,11.77,0,0,1,4.92,0Z" style="fill: rgb(38, 50, 56); transform-origin: 179.1px 356.71px;" id="el5okm3j6md8" class="animable"/><path d="M191.38,356.71a11.72,11.72,0,0,1-4.91,0,11.72,11.72,0,0,1,4.91,0Z" style="fill: rgb(38, 50, 56); transform-origin: 188.925px 356.71px;" id="elhe0a8gljst6" class="animable"/><path d="M198.8,356.71c0,.14-.56.26-1.25.26s-1.25-.12-1.25-.26.56-.26,1.25-.26S198.8,356.57,198.8,356.71Z" style="fill: rgb(38, 50, 56); transform-origin: 197.55px 356.71px;" id="el47rx45zt32m" class="animable"/></g><g id="freepik--Document--inject-2" class="animable" style="transform-origin: 174.665px 154.485px;"><rect x="139.56" y="73.39" width="60.41" height="82.67" style="fill: rgb(255, 255, 255); transform-origin: 169.765px 114.725px;" id="elvgcciu1ditd" class="animable"/><path d="M200,156s0-.14,0-.41,0-.67,0-1.17c0-1,0-2.56,0-4.52,0-3.95,0-9.71-.08-17,0-14.55-.11-35.18-.18-59.59l.27.27-60.4.07h0l.3-.3v82.67l-.24-.24,43.87.09,12.21.06,3.21,0,1.12,0s-.35,0-1.06,0l-3.17,0-12.16.07-44,.16h-.25v-.25c0-23.2-.07-51.79-.11-82.67v-.3h.31l60.4,0h.28v.28c0,24.48-.07,45.18-.1,59.77,0,7.25,0,13-.06,16.92,0,1.94,0,3.43,0,4.46,0,.49,0,.87,0,1.14A2.61,2.61,0,0,1,200,156Z" style="fill: rgb(38, 50, 56); transform-origin: 169.825px 114.55px;" id="el1qtddpmkxux" class="animable"/><path d="M176.49,90c0,.17-6.57.3-14.68.31s-14.68-.12-14.68-.29,6.57-.31,14.68-.31S176.49,89.83,176.49,90Z" style="fill: rgb(38, 50, 56); transform-origin: 161.81px 90.0103px;" id="elrhwk6mpy0j" class="animable"/><path d="M192.37,97.17c0,.17-10.13.31-22.62.32s-22.62-.12-22.62-.29,10.13-.31,22.62-.31S192.37,97,192.37,97.17Z" style="fill: rgb(38, 50, 56); transform-origin: 169.75px 97.1903px;" id="el0parqm0xy2qf" class="animable"/><path d="M192.38,104.67c0,.16-10.13.3-22.62.31s-22.62-.12-22.62-.28,10.13-.31,22.62-.32S192.38,104.5,192.38,104.67Z" style="fill: rgb(38, 50, 56); transform-origin: 169.76px 104.68px;" id="el1qtgdquks9g" class="animable"/><path d="M192.38,112.16c0,.17-10.12.31-22.61.32s-22.62-.12-22.62-.29,10.12-.31,22.62-.32S192.38,112,192.38,112.16Z" style="fill: rgb(38, 50, 56); transform-origin: 169.765px 112.175px;" id="elded4g42pv" class="animable"/><path d="M192.39,119.65c0,.17-10.13.31-22.62.32s-22.62-.12-22.62-.28,10.13-.31,22.62-.32S192.39,119.49,192.39,119.65Z" style="fill: rgb(38, 50, 56); transform-origin: 169.77px 119.67px;" id="elnfcip1iinf" class="animable"/><path d="M192.39,127.15c0,.17-10.12.31-22.61.32s-22.62-.12-22.62-.29,10.12-.31,22.62-.32S192.39,127,192.39,127.15Z" style="fill: rgb(38, 50, 56); transform-origin: 169.775px 127.165px;" id="elzop0of3n4vk" class="animable"/><path d="M192.4,134.64c0,.17-10.13.31-22.62.32s-22.62-.12-22.62-.28,10.13-.31,22.62-.32S192.4,134.48,192.4,134.64Z" style="fill: rgb(38, 50, 56); transform-origin: 169.78px 134.66px;" id="elz5j1zjf4w" class="animable"/><path d="M171.48,142.15c0,.17-5.44.31-12.15.31s-12.16-.12-12.16-.29,5.44-.31,12.16-.31S171.48,142,171.48,142.15Z" style="fill: rgb(38, 50, 56); transform-origin: 159.325px 142.16px;" id="elgq1k4330rpi" class="animable"/><circle cx="194.72" cy="149.78" r="12.27" style="fill: #EFB504; transform-origin: 194.72px 149.78px;" id="el6e9yq8tdodf" class="animable"/><path d="M201.76,145.05c.52.49-1.89,3.92-5.38,7.67-.57.6-1.12,1.17-1.65,1.7l-.85.84-.85-.71c-3.05-2.56-5.05-4.76-4.68-5.24s3,.93,6.23,3.26l-1.69.13c.49-.56,1-1.16,1.58-1.76C198,147.19,201.23,144.56,201.76,145.05Z" style="fill: rgb(224, 224, 224); transform-origin: 195.068px 150.126px;" id="eljs8enrf851" class="animable"/><path d="M165.19,160.57a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 165.19px 158.07px;" id="elexrm88ll8n5" class="animable"/><path d="M165.19,170.57a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 165.19px 168.07px;" id="eljoq6ga1kczb" class="animable"/><path d="M165.19,180.57a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 165.19px 178.07px;" id="el4nxdex2tlc7" class="animable"/><path d="M165.19,190.57a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 165.19px 188.07px;" id="el5ypnjvj0zem" class="animable"/><path d="M165.19,200.57a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 165.19px 198.07px;" id="elfwsd5m6llum" class="animable"/><path d="M165.19,210.57a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 165.19px 208.07px;" id="el21nb4azoot6" class="animable"/><path d="M165.19,220.57a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 165.19px 218.07px;" id="el3uzg8iu0izq" class="animable"/><path d="M165.19,230.57a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 165.19px 228.07px;" id="el85xs3frofzg" class="animable"/><path d="M170,235.74a6.31,6.31,0,0,1-2.5.26,12.58,12.58,0,0,1-2.33-.16l-.11,0,0-.07c0-.11.06-.17.09-.17s.07.06.1.17l-.1-.09a11.51,11.51,0,0,1,2.33-.17A6.31,6.31,0,0,1,170,235.74Z" style="fill: rgb(38, 50, 56); transform-origin: 167.53px 235.75px;" id="elth3embxmpw" class="animable"/><path d="M180,235.74a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 177.5px 235.74px;" id="elbm1v0taua6s" class="animable"/><path d="M190,235.74a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 187.5px 235.74px;" id="elsvxp5ya1oz" class="animable"/><path d="M200,235.74a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 197.5px 235.74px;" id="elt5us9ksacpb" class="animable"/><path d="M210,235.74a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 207.5px 235.74px;" id="elzr4t1bhlwai" class="animable"/></g><g id="freepik--Cloud--inject-2" class="animable" style="transform-origin: 382.652px 147.815px;"><path d="M376.57,145.28a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 376.57px 142.78px;" id="elzkxeq8657lb" class="animable"/><path d="M376.57,155.28a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 376.57px 152.78px;" id="elm8g75vjlsd8" class="animable"/><path d="M376.57,165.28a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 376.57px 162.78px;" id="elmk6f4xd7bi" class="animable"/><path d="M376.57,175.27a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 376.57px 172.77px;" id="el22a9f7vnc59" class="animable"/><path d="M376.57,185.28a12.2,12.2,0,0,1,0-5,12.2,12.2,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 376.57px 182.78px;" id="elye3sueoi33h" class="animable"/><path d="M376.57,195.28a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 376.57px 192.78px;" id="elc2vzmjth9aj" class="animable"/><path d="M376.57,205.28a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 376.57px 202.78px;" id="elic6wuk429qa" class="animable"/><path d="M376.57,215.28a12.15,12.15,0,0,1,0-5,12.15,12.15,0,0,1,0,5Z" style="fill: rgb(38, 50, 56); transform-origin: 376.57px 212.78px;" id="elrhmb66ftp9" class="animable"/><path d="M444.48,151.7l-.59,0-1.77,0-6.89,0-25.84.09L320.2,152H320l-.07-.23a22.16,22.16,0,0,1,21.87-28.37l-.33.43a19,19,0,0,1-.69-6c.27-6.9,4.19-13.56,10.21-16.64a20.19,20.19,0,0,1,18.9.1l-.46.17A29.35,29.35,0,0,1,386.7,85.37c7.36-2.68,15.52-2.33,21.94,1a29.88,29.88,0,0,1,13.58,14.84A28.42,28.42,0,0,1,423.9,119l-.25-.28A21.1,21.1,0,0,1,434,120.23a21.65,21.65,0,0,1,7.66,5.25,13.49,13.49,0,0,1,2.41,3.46,19.63,19.63,0,0,1,1.29,3.67,33.56,33.56,0,0,1,.88,6.71,38.24,38.24,0,0,1-.81,9.28,21.87,21.87,0,0,1-.64,2.33c-.18.52-.28.77-.28.77" style="fill: #EFB504; transform-origin: 382.652px 117.794px;" id="elwcmzbcx5f7c" class="animable"/><path d="M433.17,117.45h-3.85V94.29a10.09,10.09,0,1,0-20.18,0v23.16h-3.85V94.29a13.94,13.94,0,1,1,27.88,0Z" style="fill: rgb(224, 224, 224); transform-origin: 419.23px 98.9px;" id="elu2xjc8ffdq9" class="animable"/><rect x="400.17" y="107.14" width="38.12" height="24.46" rx="3.39" style="fill: rgb(224, 224, 224); transform-origin: 419.23px 119.37px;" id="elggskk45b31j" class="animable"/><path d="M419.23,119.37a3.19,3.19,0,1,1,3.18-3.18,3.18,3.18,0,0,1-3.18,3.18" style="fill: rgb(69, 90, 100); transform-origin: 419.22px 116.18px;" id="elphio6hf3vwi" class="animable"/><rect x="418.36" y="117.45" width="1.74" height="6.55" style="fill: rgb(69, 90, 100); transform-origin: 419.23px 120.725px;" id="el554adetisf6" class="animable"/></g><g id="freepik--Key--inject-2" class="animable" style="transform-origin: 610.348px 150.562px;"><path d="M653.61,109.76a20.85,20.85,0,1,0-32.35-5l-4.25,4,1,7L611.44,114l-5.31,5,1,7-6.59-1.85L596,128.38l.62,4.29-4-1.12L590,134l-.4,6.53,9.73,2.45,31.57-29.46A20.85,20.85,0,0,0,653.61,109.76Zm-3.08-25.64a6,6,0,1,1-8.46-.29A6,6,0,0,1,650.53,84.12Z" style="fill: rgb(38, 50, 56); transform-origin: 624.929px 108.312px;" id="elsgcy7gzu70k" class="animable"/><path d="M653.61,109.76a20.85,20.85,0,1,0-32.35-5l-4.25,4,1,7L611.44,114l-5.31,5,1,7-6.59-1.85L596,128.38l.62,4.29-4-1.12L590,134l-.4,6.53,9.73,2.45,31.57-29.46A20.85,20.85,0,0,0,653.61,109.76Zm-3.08-25.64a6,6,0,1,1-8.46-.29A6,6,0,0,1,650.53,84.12Z" style="fill: none; transform-origin: 624.929px 108.312px;" id="ely8zeicnd7i" class="animable"/><path d="M650.53,84.12s-.39-.37-1.19-.86a5.93,5.93,0,0,0-3.76-.82,5.76,5.76,0,1,0,6.28,6.74,6,6,0,0,0-.56-3.82c-.43-.83-.81-1.21-.77-1.24s.11.07.29.26a4.62,4.62,0,0,1,.66.89,6,6,0,0,1,.75,4A6.17,6.17,0,0,1,645,94.32a6.16,6.16,0,0,1-5-5.39,6.16,6.16,0,0,1,5.57-6.86,5.93,5.93,0,0,1,3.91,1,4.54,4.54,0,0,1,.84.72A1.23,1.23,0,0,1,650.53,84.12Z" style="fill: rgb(38, 50, 56); transform-origin: 646.132px 88.2346px;" id="elelybvhxp5z" class="animable"/><path d="M653.61,109.76a2.65,2.65,0,0,1-.3.29l-.92.82a21.9,21.9,0,0,1-4,2.57,21,21,0,0,1-17.58.25l.17,0-31.51,29.51-.1.08-.12,0-9.73-2.44-.2-.05,0-.2c.12-2,.25-4.26.39-6.52v-.1l.08-.07,2.65-2.48.1-.09.14,0,4,1.13-.32.28c-.19-1.34-.4-2.81-.62-4.29v-.14l.09-.09,4.57-4.26.1-.1.14,0,6.59,1.85-.33.28c-.32-2.23-.67-4.63-1-7v-.13l.09-.1,1.41-1.31,3.9-3.64.11-.1.14,0,6.59,1.85-.33.28q-.51-3.55-1-7l0-.13.1-.09,4.24-4-.05.31a21.41,21.41,0,0,1-2.71-9.49,20.46,20.46,0,0,1,.35-4.83,20.23,20.23,0,0,1,1.39-4.54,21,21,0,0,1,38.17-.87,20.91,20.91,0,0,1,2,11,20.63,20.63,0,0,1-2.34,8l-.39.74-.42.66c-.29.42-.52.84-.8,1.18-.56.68-1,1.29-1.42,1.72l-1,1-.34.31.31-.35.93-1c.41-.43.83-1,1.37-1.72.27-.35.5-.77.77-1.19l.42-.66.37-.74a20.92,20.92,0,0,0,.16-18.75,20.67,20.67,0,0,0-24.79-10.49A20.65,20.65,0,0,0,620.5,86.28a19.76,19.76,0,0,0-1.35,4.43,19.46,19.46,0,0,0-.33,4.71,20.71,20.71,0,0,0,2.66,9.27l.09.17-.14.14-4.24,4,.08-.22q.5,3.48,1,7l.06.39-.38-.1-6.59-1.85.25-.06-3.9,3.64-1.41,1.31.08-.22c.35,2.41.69,4.81,1,7l.06.39-.38-.1-6.59-1.85.25-.06-4.57,4.26.08-.23c.21,1.48.42,2.95.62,4.29l0,.39-.37-.1-4-1.13.24-.06-2.65,2.48.08-.17c-.14,2.26-.28,4.49-.41,6.52l-.18-.25,9.73,2.46-.22,0,31.62-29.39.07-.07.1,0a20.92,20.92,0,0,0,17.38-.12,22.42,22.42,0,0,0,4.05-2.48c.41-.33.72-.59.94-.78Z" style="fill: rgb(38, 50, 56); transform-origin: 624.853px 108.332px;" id="el5bdvoxh7an" class="animable"/><path d="M652.91,107.4a20.85,20.85,0,1,0-32.35-5h0l-4.24,4,1,7-6.59-1.85-5.31,5,1,7-6.59-1.85L595.3,126l.62,4.29-4-1.12-2.65,2.48-.4,6.52,9.73,2.45,31.57-29.45A20.85,20.85,0,0,0,652.91,107.4Zm-3.08-25.65a6,6,0,1,1-8.45-.29A6,6,0,0,1,649.83,81.75Z" style="fill: rgb(224, 224, 224); transform-origin: 624.214px 105.952px;" id="elke755w4m4v" class="animable"/><path d="M652.91,107.4a20.85,20.85,0,1,0-32.35-5h0l-4.24,4,1,7-6.59-1.85-5.31,5,1,7-6.59-1.85L595.3,126l.62,4.29-4-1.12-2.65,2.48-.4,6.52,9.73,2.45,31.57-29.45A20.85,20.85,0,0,0,652.91,107.4Zm-3.08-25.65a6,6,0,1,1-8.45-.29A6,6,0,0,1,649.83,81.75Z" style="fill: none; transform-origin: 624.214px 105.952px;" id="els0bc68e1jhk" class="animable"/><path d="M649.83,81.75s-.39-.36-1.19-.85a5.92,5.92,0,0,0-3.76-.83,5.76,5.76,0,1,0,6.29,6.74A5.91,5.91,0,0,0,650.6,83c-.43-.83-.81-1.21-.77-1.25s.11.08.29.26a4.62,4.62,0,0,1,.66.89,6,6,0,0,1,.75,4A6.19,6.19,0,0,1,644.3,92a6.19,6.19,0,0,1-5-5.4,6.08,6.08,0,0,1,.65-3.55,6.15,6.15,0,0,1,4.92-3.31,6,6,0,0,1,3.91,1,4.19,4.19,0,0,1,.84.72Q649.85,81.74,649.83,81.75Z" style="fill: rgb(38, 50, 56); transform-origin: 645.432px 85.9082px;" id="elnrcfhc5me3r" class="animable"/><path d="M652.91,107.4a3.58,3.58,0,0,1-.3.28l-.91.82a21.87,21.87,0,0,1-4,2.57,21,21,0,0,1-17.58.25l.17,0L598.74,140.8l-.09.09-.13,0-9.73-2.44-.2,0,0-.2c.12-2,.25-4.27.39-6.53v-.1l.07-.06c.82-.77,1.73-1.63,2.65-2.48l.11-.1.13,0,4,1.12-.32.28c-.2-1.34-.41-2.81-.62-4.29l0-.13.09-.1,4.57-4.25.1-.1.14,0,6.59,1.84-.33.29c-.32-2.24-.67-4.63-1-7l0-.13.1-.09,1.4-1.32,3.9-3.64.11-.1.14,0,6.59,1.85-.33.29c-.34-2.38-.68-4.72-1-7l0-.13.1-.09,4.24-4,0,.31a21.29,21.29,0,0,1-2.72-9.49,21.3,21.3,0,0,1,1.74-9.36,21,21,0,0,1,38.17-.87,20.86,20.86,0,0,1,2.05,10.94,20.58,20.58,0,0,1-2.34,8l-.38.74-.43.66c-.29.43-.52.84-.79,1.19-.57.68-1,1.28-1.42,1.71l-1,1c-.22.22-.34.32-.34.32s.1-.13.31-.35l.93-1c.41-.43.83-1,1.38-1.73.26-.35.49-.77.76-1.19l.42-.66.38-.74A20.68,20.68,0,1,0,619.8,83.91a20.76,20.76,0,0,0-1.68,9.15,20.67,20.67,0,0,0,2.66,9.26l.1.18-.15.13-4.24,4,.08-.22q.49,3.48,1,7l.06.4-.38-.11-6.59-1.85.25-.06-3.9,3.64-1.41,1.32.08-.23c.35,2.41.69,4.81,1,7l.06.4-.38-.11L599.79,122l.25-.06-4.57,4.26.08-.22c.21,1.48.43,3,.62,4.29l.05.38-.37-.1-4-1.13.24-.06-2.66,2.48.08-.17c-.14,2.26-.28,4.49-.41,6.52l-.18-.25,9.73,2.46-.22.06L630,111.06l.08-.07.09,0a21.07,21.07,0,0,0,10,1.79,21.4,21.4,0,0,0,7.36-1.9,23.63,23.63,0,0,0,4-2.48l.94-.79Z" style="fill: rgb(38, 50, 56); transform-origin: 624.158px 105.972px;" id="eld0nmos7mjje" class="animable"/><path d="M651,105.77a28,28,0,0,0,2.8-3.53,17.39,17.39,0,0,0,2.14-4.8,18.17,18.17,0,0,0,.6-6.91,18,18,0,0,0-2.75-7.89,18.26,18.26,0,0,0-7.08-6.42,17.07,17.07,0,0,0-4.93-1.66,21.54,21.54,0,0,0-2.68-.31,21.2,21.2,0,0,0-2.75.14A18,18,0,0,0,620.71,89a21.39,21.39,0,0,0-.33,2.73,22,22,0,0,0,.12,2.7,17.51,17.51,0,0,0,1.32,5,18.2,18.2,0,0,0,5.91,7.51,18.27,18.27,0,0,0,14.61,3.17,17.38,17.38,0,0,0,4.94-1.8,29.17,29.17,0,0,0,3.72-2.55s-.07.08-.21.21l-.65.58a8.41,8.41,0,0,1-1.12.89,14.72,14.72,0,0,1-1.64,1,17.55,17.55,0,0,1-5,1.91,18.57,18.57,0,0,1-14.94-3.11,18.74,18.74,0,0,1-6.09-7.67A17.46,17.46,0,0,1,620,94.46a19.41,19.41,0,0,1-.13-2.76,19.06,19.06,0,0,1,.33-2.81,18.49,18.49,0,0,1,16.09-15,20.48,20.48,0,0,1,2.82-.13,20.85,20.85,0,0,1,2.75.32,17.61,17.61,0,0,1,5.06,1.73,18.61,18.61,0,0,1,7.22,6.61,18.18,18.18,0,0,1,2.76,8.08,18.44,18.44,0,0,1-.69,7,17.43,17.43,0,0,1-2.25,4.83,12.91,12.91,0,0,1-1.16,1.56,8,8,0,0,1-1,1l-.63.61C651.08,105.71,651,105.78,651,105.77Z" style="fill: rgb(38, 50, 56); transform-origin: 638.423px 92.2724px;" id="elrpfdem4p3s" class="animable"/><path d="M650.15,91s.44-.45,1-1.38a6.93,6.93,0,0,0-.06-7.38,6.75,6.75,0,0,0-2.67-2.39,6.61,6.61,0,0,0-3.86-.68,6.73,6.73,0,0,0-5.85,5.45,6.71,6.71,0,0,0,.41,3.91,6.9,6.9,0,0,0,9.56,3.39c1-.52,1.41-1,1.45-.92s-.09.13-.31.33a5.34,5.34,0,0,1-1,.77,7.09,7.09,0,0,1-7.71-.41,7.22,7.22,0,0,1-2.86-7.17,7.22,7.22,0,0,1,6.29-5.86,7.17,7.17,0,0,1,8,6.49,7,7,0,0,1-1.18,4.58,6,6,0,0,1-.84,1A1.55,1.55,0,0,1,650.15,91Z" style="fill: rgb(38, 50, 56); transform-origin: 645.349px 85.8275px;" id="el5gznanbds76" class="animable"/><path d="M596,139.47c-.1-.1,7-6.89,15.87-15.16s16.14-14.9,16.24-14.79-7,6.89-15.88,15.17S596.13,139.58,596,139.47Z" style="fill: rgb(38, 50, 56); transform-origin: 612.055px 124.495px;" id="elu6pcphnxi4" class="animable"/><path d="M565.31,229.81a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 562.81px 229.81px;" id="elhlaeen733ho" class="animable"/><path d="M575.31,229.81a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 572.81px 229.81px;" id="el5gc5pb1i9k" class="animable"/><path d="M585.31,229.81a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 582.81px 229.81px;" id="elh6a6dyq4ij9" class="animable"/><path d="M595.31,229.81a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 592.81px 229.81px;" id="eliq383tfz6ro" class="animable"/><path d="M605.31,229.81a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 602.81px 229.81px;" id="elqpj3jr5ln" class="animable"/><path d="M615.31,229.81a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 612.81px 229.81px;" id="elde4807fqv79" class="animable"/><path d="M623.8,228.3c.1,0,.2.62.24,1.51V230h-.25l-1,0a6.31,6.31,0,0,1-2.5-.26,6.31,6.31,0,0,1,2.5-.26l1,0-.24.24C623.6,228.92,623.69,228.3,623.8,228.3Z" style="fill: rgb(38, 50, 56); transform-origin: 622.165px 229.165px;" id="elob5qwn295vc" class="animable"/><path d="M623.8,218.3a12.15,12.15,0,0,1,0,5,12.15,12.15,0,0,1,0-5Z" style="fill: rgb(38, 50, 56); transform-origin: 623.8px 220.8px;" id="elq55fyr0wh6" class="animable"/><path d="M623.8,208.3a12.15,12.15,0,0,1,0,5,12.15,12.15,0,0,1,0-5Z" style="fill: rgb(38, 50, 56); transform-origin: 623.8px 210.8px;" id="el3ke430uo25p" class="animable"/><path d="M623.8,198.3a12.15,12.15,0,0,1,0,5,12.15,12.15,0,0,1,0-5Z" style="fill: rgb(38, 50, 56); transform-origin: 623.8px 200.8px;" id="elagc6orv3co" class="animable"/><path d="M623.8,188.3a12.15,12.15,0,0,1,0,5,12.15,12.15,0,0,1,0-5Z" style="fill: rgb(38, 50, 56); transform-origin: 623.8px 190.8px;" id="elmysfxho1n1" class="animable"/><path d="M623.8,178.3a12.15,12.15,0,0,1,0,5,12.15,12.15,0,0,1,0-5Z" style="fill: rgb(38, 50, 56); transform-origin: 623.8px 180.8px;" id="elolpb431wk9q" class="animable"/><path d="M623.8,168.3a12.15,12.15,0,0,1,0,5,12.15,12.15,0,0,1,0-5Z" style="fill: rgb(38, 50, 56); transform-origin: 623.8px 170.8px;" id="elkw1w0kw6wq" class="animable"/><path d="M623.8,158.3a12.15,12.15,0,0,1,0,5,12.15,12.15,0,0,1,0-5Z" style="fill: rgb(38, 50, 56); transform-origin: 623.8px 160.8px;" id="elsoaepqpvbd" class="animable"/><path d="M623.8,148.3a12.15,12.15,0,0,1,0,5,12.15,12.15,0,0,1,0-5Z" style="fill: rgb(38, 50, 56); transform-origin: 623.8px 150.8px;" id="el8er90z42ko" class="animable"/><path d="M623.8,138.3a12.15,12.15,0,0,1,0,5,12.15,12.15,0,0,1,0-5Z" style="fill: rgb(38, 50, 56); transform-origin: 623.8px 140.8px;" id="ela58a7leso2n" class="animable"/><path d="M623.8,128.3a12.15,12.15,0,0,1,0,5,12.15,12.15,0,0,1,0-5Z" style="fill: rgb(38, 50, 56); transform-origin: 623.8px 130.8px;" id="elzueyjp7zdxd" class="animable"/></g><g id="freepik--Folder--inject-2" class="animable" style="transform-origin: 615.312px 297.291px;"><path d="M548.82,329.48a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 546.32px 329.48px;" id="elq9wsvhkhp9" class="animable"/><path d="M558.82,329.48a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 556.32px 329.48px;" id="elakgjc8przb7" class="animable"/><path d="M568.82,329.48a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 566.32px 329.48px;" id="eldixohbyem4r" class="animable"/><path d="M578.82,329.48a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 576.32px 329.48px;" id="eltm4mmh3y0hh" class="animable"/><path d="M588.82,329.48a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 586.32px 329.48px;" id="el5fwu3fxzr02" class="animable"/><path d="M598.82,329.48a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 596.32px 329.48px;" id="elitg5xoesfcm" class="animable"/><path d="M608.82,329.48a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 606.32px 329.48px;" id="elqjgnuo9i84e" class="animable"/><path d="M618.82,329.48a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 616.32px 329.48px;" id="el5sf9gzmxyb7" class="animable"/><path d="M628.82,329.48a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 626.32px 329.48px;" id="eldw04gncc58w" class="animable"/><path d="M638.82,329.48a12.15,12.15,0,0,1-5,0,12.15,12.15,0,0,1,5,0Z" style="fill: rgb(38, 50, 56); transform-origin: 636.32px 329.48px;" id="el5giaxds6zd4" class="animable"/><path d="M647.89,328.55c.09,0,.15.36.2.94v.18l-.22,0c-.42,0-1,.05-1.56.05a6.11,6.11,0,0,1-2.5-.26,6.31,6.31,0,0,1,2.5-.26c.6,0,1.14,0,1.56.06l-.2.2C647.73,328.91,647.81,328.55,647.89,328.55Z" style="fill: rgb(38, 50, 56); transform-origin: 645.95px 329.151px;" id="eljcb0yyuvlq" class="animable"/><path d="M648,318.55a6.11,6.11,0,0,1,.24,2.5,6.21,6.21,0,0,1-.29,2.5,6.21,6.21,0,0,1-.23-2.5A6.31,6.31,0,0,1,648,318.55Z" style="fill: rgb(38, 50, 56); transform-origin: 647.98px 321.05px;" id="elr3c8635mr4g" class="animable"/><path d="M648.09,308.55a6.32,6.32,0,0,1,.24,2.5,6.21,6.21,0,0,1-.29,2.5,6.21,6.21,0,0,1-.23-2.5A6.31,6.31,0,0,1,648.09,308.55Z" style="fill: rgb(38, 50, 56); transform-origin: 648.068px 311.05px;" id="elbekgk3l2ia8" class="animable"/><path d="M648.19,298.55a6.32,6.32,0,0,1,.24,2.5,6.42,6.42,0,0,1-.29,2.5,6.32,6.32,0,0,1-.24-2.5A6.42,6.42,0,0,1,648.19,298.55Z" style="fill: rgb(38, 50, 56); transform-origin: 648.165px 301.05px;" id="ela6eg5dnn2ne" class="animable"/><path d="M615.1,267.21,616.94,301a2.27,2.27,0,0,0,2.26,2.16l60.81.45a2.29,2.29,0,0,0,2.3-2.27l.19-26.3a2.28,2.28,0,0,0-2.28-2.3l-37.4,0a2.28,2.28,0,0,1-1.61-.67l-6.45-6.45a2.26,2.26,0,0,0-1.6-.67l-15.77-.12A2.29,2.29,0,0,0,615.1,267.21Z" style="fill: #EFB504; transform-origin: 648.799px 284.22px;" id="elbti9mxb6hl" class="animable"/><g id="ellhp1u4mej6k"><rect x="615.95" y="268.52" width="66.45" height="39.7" style="fill: rgb(245, 245, 245); transform-origin: 649.175px 288.37px; transform: rotate(0.42deg);" class="animable" id="elrfrsjwazyfh"/></g><path d="M610.79,271.8l4.77,41.44a1.45,1.45,0,0,0,1.45,1.3l64.75.48a1.46,1.46,0,0,0,1.47-1.35l3.57-41.38a1.47,1.47,0,0,0-1.46-1.59l-73.08-.53A1.47,1.47,0,0,0,610.79,271.8Z" style="fill: #EFB504; transform-origin: 648.793px 292.595px;" id="el1nv5v26owp6" class="animable"/></g><g id="freepik--Device--inject-2" class="animable animator-active" style="transform-origin: 389.115px 329.3px;"><rect x="207.26" y="207.37" width="363.83" height="240.6" rx="14.57" style="fill: rgb(38, 50, 56); transform-origin: 389.175px 327.67px;" id="eliedk88hrnq" class="animable"/><path d="M192,430.18H586.23a0,0,0,0,1,0,0v3.25a17.8,17.8,0,0,1-17.8,17.8H209.85a17.8,17.8,0,0,1-17.8-17.8v-3.25A0,0,0,0,1,192,430.18Z" style="fill: rgb(38, 50, 56); transform-origin: 389.115px 440.705px;" id="elc2t2ccc0ltn" class="animable"/><rect x="217.03" y="216.93" width="343.28" height="203.18" style="fill: rgb(69, 90, 100); transform-origin: 388.67px 318.52px;" id="el25rw1yrpbo1" class="animable"/><polygon points="353.31 430.18 358.2 437.96 427.03 437.96 431.58 430.18 353.31 430.18" style="fill: rgb(69, 90, 100); transform-origin: 392.445px 434.07px;" id="eln0l1k5xu21e" class="animable"/><rect x="224.1" y="223.81" width="329.85" height="195.65" rx="3.27" style="fill: rgb(235, 235, 235); transform-origin: 389.025px 321.635px;" id="elfnxoo90l6vg" class="animable"/><rect x="224.1" y="238.15" width="329.85" height="181.32" style="fill: rgb(250, 250, 250); transform-origin: 389.025px 328.81px;" id="elugx4ey0xqlq" class="animable"/><path d="M251.15,231.37a2.64,2.64,0,1,1-2.63-2.64A2.63,2.63,0,0,1,251.15,231.37Z" style="fill: rgb(38, 50, 56); transform-origin: 248.51px 231.37px;" id="eltq710hd8k0j" class="animable"/><path d="M235.19,231.37a2.64,2.64,0,1,1-2.64-2.64A2.64,2.64,0,0,1,235.19,231.37Z" style="fill: rgb(38, 50, 56); transform-origin: 232.55px 231.37px;" id="eljh6h19xkvs" class="animable"/><path d="M242.85,231.37a2.64,2.64,0,1,1-2.63-2.64A2.63,2.63,0,0,1,242.85,231.37Z" style="fill: rgb(38, 50, 56); transform-origin: 240.21px 231.37px;" id="elvza4emch2w" class="animable"/><path d="M568,430.18c0,.13-80.75.24-180.34.24s-180.37-.11-180.37-.24,80.74-.24,180.37-.24S568,430.05,568,430.18Z" style="fill: rgb(69, 90, 100); transform-origin: 387.645px 430.18px;" id="eljfgseohhxp" class="animable"/><rect x="246.96" y="329.49" width="75.27" height="5.04" style="fill: rgb(224, 224, 224); transform-origin: 284.595px 332.01px;" id="elfs45ney3shn" class="animable"/><rect x="344.18" y="259.58" width="75.27" height="5.04" style="fill: rgb(224, 224, 224); transform-origin: 381.815px 262.1px;" id="eloudisj7mql" class="animable"/><path d="M531.4,278.33c0,.13-41.91.24-93.61.24s-93.61-.11-93.61-.24,41.91-.23,93.61-.23S531.4,278.2,531.4,278.33Z" style="fill: rgb(224, 224, 224); transform-origin: 437.79px 278.335px;" id="elxeg9oykx2jd" class="animable"/><path d="M531.4,289.33c0,.13-41.91.23-93.61.23s-93.61-.1-93.61-.23,41.91-.24,93.61-.24S531.4,289.2,531.4,289.33Z" style="fill: rgb(224, 224, 224); transform-origin: 437.79px 289.325px;" id="elwdeig5xdn5e" class="animable"/><path d="M531.4,300.32c0,.13-41.91.24-93.61.24s-93.61-.11-93.61-.24,41.91-.23,93.61-.23S531.4,300.19,531.4,300.32Z" style="fill: rgb(224, 224, 224); transform-origin: 437.79px 300.325px;" id="elndstmbacyz" class="animable"/><path d="M531.4,311.32c0,.13-41.91.23-93.61.23s-93.61-.1-93.61-.23,41.91-.24,93.61-.24S531.4,311.19,531.4,311.32Z" style="fill: rgb(224, 224, 224); transform-origin: 437.79px 311.315px;" id="elddzbr094chu" class="animable"/><path d="M531.4,322.31c0,.13-41.91.24-93.61.24s-93.61-.11-93.61-.24,41.91-.23,93.61-.23S531.4,322.18,531.4,322.31Z" style="fill: rgb(224, 224, 224); transform-origin: 437.79px 322.315px;" id="elsey5b9sggig" class="animable"/><path d="M417.26,333.31c0,.13-16.36.23-36.54.23s-36.54-.1-36.54-.23,16.36-.24,36.54-.24S417.26,333.18,417.26,333.31Z" style="fill: rgb(224, 224, 224); transform-origin: 380.72px 333.305px;" id="elx6wrbtqwv7c" class="animable"/><path d="M530,354.64c0,.13-27.55.24-61.52.24s-61.53-.11-61.53-.24,27.54-.23,61.53-.23S530,354.51,530,354.64Z" style="fill: rgb(224, 224, 224); transform-origin: 468.475px 354.645px;" id="el8mh8nno59gb" class="animable"/><path d="M530,366.32c0,.13-27.55.23-61.52.23s-61.53-.1-61.53-.23,27.54-.24,61.53-.24S530,366.19,530,366.32Z" style="fill: rgb(224, 224, 224); transform-origin: 468.475px 366.315px;" id="elv8jisckgkh" class="animable"/><path d="M530,378c0,.13-27.55.23-61.52.23S407,378.12,407,378s27.54-.24,61.53-.24S530,377.86,530,378Z" style="fill: rgb(224, 224, 224); transform-origin: 468.5px 377.995px;" id="elif5pu18i3s" class="animable"/><path d="M530,389.66c0,.13-27.55.23-61.52.23s-61.53-.1-61.53-.23,27.54-.24,61.53-.24S530,389.53,530,389.66Z" style="fill: rgb(224, 224, 224); transform-origin: 468.475px 389.655px;" id="el1segstvnwg5" class="animable"/><path d="M322.42,353.53c0,.13-16.42.23-36.68.23s-36.68-.1-36.68-.23,16.42-.24,36.68-.24S322.42,353.4,322.42,353.53Z" style="fill: rgb(224, 224, 224); transform-origin: 285.74px 353.525px;" id="elc9qm9t9zar" class="animable"/><path d="M322.42,362.69c0,.13-16.42.24-36.68.24s-36.68-.11-36.68-.24,16.42-.23,36.68-.23S322.42,362.56,322.42,362.69Z" style="fill: rgb(224, 224, 224); transform-origin: 285.74px 362.695px;" id="elf5nwg7c2j1b" class="animable"/><path d="M322.42,371.86c0,.13-16.42.24-36.68.24s-36.68-.11-36.68-.24,16.42-.24,36.68-.24S322.42,371.73,322.42,371.86Z" style="fill: rgb(224, 224, 224); transform-origin: 285.74px 371.86px;" id="el3bzntsdo2go" class="animable"/><path d="M322.42,381c0,.13-16.42.23-36.68.23s-36.68-.1-36.68-.23,16.42-.24,36.68-.24S322.42,380.9,322.42,381Z" style="fill: rgb(224, 224, 224); transform-origin: 285.74px 380.995px;" id="elsdz771tb9u8" class="animable"/><path d="M322.42,390.19c0,.13-16.42.24-36.68.24s-36.68-.11-36.68-.24,16.42-.23,36.68-.23S322.42,390.06,322.42,390.19Z" style="fill: rgb(224, 224, 224); transform-origin: 285.74px 390.195px;" id="elx0s1poj3iqh" class="animable"/><rect x="344.13" y="349.08" width="53.4" height="51.67" style="fill: rgb(245, 245, 245); transform-origin: 370.83px 374.915px;" id="el53o8vwwm6zs" class="animable"/><polygon points="348.27 396.32 363.05 369.73 375.13 386.73 382.48 377.7 393.4 396.32 348.27 396.32" style="fill: rgb(235, 235, 235); transform-origin: 370.835px 383.025px;" id="elf4ary306c5m" class="animable"/><path d="M380.28,364.56a3.85,3.85,0,1,1-3.84-3.84A3.84,3.84,0,0,1,380.28,364.56Z" style="fill: rgb(235, 235, 235); transform-origin: 376.43px 364.57px;" id="elqnmcw7gx86b" class="animable"/><path d="M393.4,396.47a.82.82,0,0,1,0-.22c0-.15,0-.35,0-.6,0-.55,0-1.32,0-2.32,0-2.05,0-5-.06-8.78,0-7.55-.06-18.29-.11-31.2l.22.22-45.12,0,.25-.25h0V365c0,3.8,0,7.52,0,11.13,0,7.24,0,14.08,0,20.38l-.22-.23,32.62.12,9.2.05,2.44,0h.64l.22,0h-.81l-2.4,0-9.15.05-32.76.12h-.23v-.22c0-6.3,0-13.14,0-20.38,0-3.61,0-7.33,0-11.13V353.37h0l.26-.26,45.12,0h.22v.21c-.05,13-.09,23.76-.11,31.34,0,3.74-.05,6.69-.06,8.73q0,1.47,0,2.28c0,.24,0,.43,0,.58A1.31,1.31,0,0,1,393.4,396.47Z" style="fill: rgb(235, 235, 235); transform-origin: 370.93px 374.865px;" id="elap7xlvx972" class="animable"/><rect x="248.38" y="250.98" width="72.43" height="70.08" style="fill: rgb(245, 245, 245); transform-origin: 284.595px 286.02px;" id="elhxfs60sq26k" class="animable"/><polygon points="253.99 315.06 274.03 279 290.42 302.05 300.38 289.81 315.19 315.06 253.99 315.06" style="fill: rgb(224, 224, 224); transform-origin: 284.59px 297.03px;" id="el1yerkhwclia" class="animable"/><path d="M297.4,272a5.21,5.21,0,1,1-5.21-5.21A5.21,5.21,0,0,1,297.4,272Z" style="fill: rgb(224, 224, 224); transform-origin: 292.19px 272px;" id="elvuei1je2laq" class="animable"/><path d="M315.19,315.26s0-.1,0-.29,0-.48,0-.82q0-1.11,0-3.15c0-2.78-.05-6.8-.08-11.9,0-10.24-.09-24.81-.15-42.31l.29.29-61.19.05c-.08.07.65-.65.34-.33h0v15.72q0,7.73,0,15.1c0,9.81,0,19.09,0,27.63l-.3-.3,44.25.15,12.48.08,3.32,0,.85,0,.3,0h-.26l-.83,0-3.25,0-12.41.08-44.45.15h-.3v-.3c0-8.54,0-17.82,0-27.63q0-7.36,0-15.1V256.81h0c-.31.3.42-.44.35-.36l61.19.05h.3v.3c-.06,17.58-.11,32.22-.15,42.5,0,5.07-.06,9.07-.08,11.83,0,1.33,0,2.37,0,3.1,0,.33,0,.58,0,.78A.71.71,0,0,1,315.19,315.26Z" style="fill: rgb(224, 224, 224); transform-origin: 284.682px 285.927px;" id="el1j8oq68ii2o" class="animable"/><polygon points="330.52 277.61 334.1 284.86 342.1 286.02 336.31 291.67 337.67 299.64 330.52 295.88 323.36 299.64 324.73 291.67 318.94 286.02 326.94 284.86 330.52 277.61" style="fill: #EFB504; transform-origin: 330.52px 288.625px;" id="elvur41s5zdvs" class="animable"/><polygon points="355.72 252.41 359.3 259.66 367.3 260.82 361.51 266.47 362.88 274.44 355.72 270.67 348.56 274.44 349.93 266.47 344.14 260.82 352.14 259.66 355.72 252.41" style="fill: #EFB504; transform-origin: 355.72px 263.425px;" id="ellfwczsdxdia" class="animable"/><polygon points="389.4 242.52 392.98 249.77 400.98 250.93 395.19 256.57 396.56 264.54 389.4 260.78 382.24 264.54 383.61 256.57 377.82 250.93 385.82 249.77 389.4 242.52" style="fill: #EFB504; transform-origin: 389.4px 253.53px;" id="el3qnxio3oinc" class="animable"/><polygon points="424.26 252.65 427.84 259.9 435.84 261.06 430.05 266.7 431.42 274.67 424.26 270.91 417.1 274.67 418.47 266.7 412.68 261.06 420.68 259.9 424.26 252.65" style="fill: #EFB504; transform-origin: 424.26px 263.66px;" id="elnql7t6e49yi" class="animable"/><polygon points="448.28 276.67 451.86 283.92 459.86 285.08 454.07 290.73 455.44 298.69 448.28 294.93 441.13 298.69 442.49 290.73 436.7 285.08 444.7 283.92 448.28 276.67" style="fill: #EFB504; transform-origin: 448.28px 287.68px;" id="elap5me4hgy3" class="animable"/><polygon points="457 311.53 460.58 318.78 468.58 319.94 462.79 325.58 464.15 333.55 457 329.79 449.84 333.55 451.21 325.58 445.42 319.94 453.42 318.78 457 311.53" style="fill: #EFB504; transform-origin: 457px 322.54px;" id="elzm6ktmii8f" class="animable"/><polygon points="448.52 346.15 452.1 353.4 460.1 354.56 454.31 360.21 455.68 368.18 448.52 364.41 441.36 368.18 442.73 360.21 436.94 354.56 444.94 353.4 448.52 346.15" style="fill: #EFB504; transform-origin: 448.52px 357.165px;" id="elhkt7rc1e6i4" class="animable"/><polygon points="423 370.18 426.58 377.43 434.58 378.59 428.79 384.23 430.16 392.2 423 388.44 415.85 392.2 417.21 384.23 411.43 378.59 419.43 377.43 423 370.18" style="fill: #EFB504; transform-origin: 423.005px 381.19px;" id="elupw8e1wkrq" class="animable"/><polygon points="388.72 380.08 392.3 387.33 400.3 388.49 394.51 394.14 395.88 402.1 388.72 398.34 381.57 402.1 382.93 394.14 377.14 388.49 385.14 387.33 388.72 380.08" style="fill: #EFB504; transform-origin: 388.72px 391.09px;" id="elfr5ozyh4g9e" class="animable"/><polygon points="355.98 370.19 359.56 377.44 367.56 378.6 361.77 384.24 363.14 392.21 355.98 388.45 348.83 392.21 350.19 384.24 344.4 378.6 352.41 377.44 355.98 370.19" style="fill: #EFB504; transform-origin: 355.98px 381.2px;" id="elv9d7o6ljmt" class="animable"/><polygon points="330.08 345.52 333.65 352.77 341.65 353.94 335.87 359.58 337.23 367.55 330.08 363.79 322.92 367.55 324.29 359.58 318.5 353.94 326.5 352.77 330.08 345.52" style="fill: #EFB504; transform-origin: 330.075px 356.535px;" id="elnz7v70fco2" class="animable"/><polygon points="321.36 311.57 324.94 318.82 332.94 319.98 327.15 325.63 328.52 333.59 321.36 329.83 314.2 333.59 315.57 325.63 309.78 319.98 317.78 318.82 321.36 311.57" style="fill: #EFB504; transform-origin: 321.36px 322.58px;" id="elwvf3rdu61lj" class="animable"/><path d="M414.09,336.44h-7v-38.1a16.26,16.26,0,1,0-32.52,0v38.1h-7v-38.1a23.26,23.26,0,1,1,46.52,0Z" style="fill: rgb(38, 50, 56); transform-origin: 390.83px 305.76px;" id="elhzd4io2qeup" class="animable"/><rect x="359.48" y="319.49" width="62.7" height="40.23" rx="5.81" style="fill: #EFB504; transform-origin: 390.83px 339.605px;" id="elnn5bzuuy75" class="animable"/><path d="M396.07,334.36a5.24,5.24,0,1,1-5.24-5.24A5.24,5.24,0,0,1,396.07,334.36Z" style="fill: rgb(38, 50, 56); transform-origin: 390.83px 334.36px;" id="el83drb314al6" class="animable"/><rect x="389.4" y="336.44" width="2.86" height="10.78" style="fill: rgb(38, 50, 56); transform-origin: 390.83px 341.83px;" id="ellsx6zdsghvj" class="animable"/><path d="M362.82,355.9c-.14,0-.26-6.71-.26-15s.12-15,.26-15,.26,6.71.26,15S363,355.9,362.82,355.9Z" style="fill: rgb(250, 250, 250); transform-origin: 362.82px 340.9px;" id="elxve8hufpu2" class="animable"/><path d="M386.73,341.08s-.25-.08-.62-.35a6.31,6.31,0,0,1-1.35-1.38,7.35,7.35,0,0,1,2.61-11,6.32,6.32,0,0,1,1.83-.62,1.72,1.72,0,0,1,.7,0,17.24,17.24,0,0,0-2.35,1,7.27,7.27,0,0,0-2.49,10.48A15.6,15.6,0,0,0,386.73,341.08Z" style="fill: rgb(250, 250, 250); transform-origin: 386.593px 334.387px;" id="elqwkccf9z2qq" class="animable"/></g><g id="freepik--Character--inject-2" class="animable" style="transform-origin: 144.46px 371.77px;"><path d="M170.27,301.63a11.07,11.07,0,0,0,.16,7,33,33,0,0,0,3.16,6.38,112.81,112.81,0,0,0,4.25-12.24,2.88,2.88,0,0,0-.23-2.72,2.52,2.52,0,0,0-2-.55,9.26,9.26,0,0,0-5.72,2.44" style="fill: rgb(38, 50, 56); transform-origin: 173.929px 307.24px;" id="elxn08os8ivmj" class="animable"/><polygon points="82.09 413.8 65.73 451.19 88.25 451.19 104.29 413.5 82.09 413.8" style="fill: rgb(38, 50, 56); transform-origin: 85.01px 432.345px;" id="elja91djlq9b" class="animable"/><path d="M179.37,450.75c22.07-7.31,6.94-36.5,6.94-36.5l-96.47,9.07a14,14,0,0,0-14,13.95h0a14,14,0,0,0,14,14l89.62-.48" style="fill: rgb(69, 90, 100); transform-origin: 133.605px 432.76px;" id="ello50y63vklr" class="animable"/><path d="M201.31,451.23c21.51-7.26,7.33-37,7.33-37l-94,8.86c-9.18.12-13.67,4.55-13.67,13.93h0c0,7.7,2.86,14.2,13.64,14l86.74.22" style="fill: rgb(69, 90, 100); transform-origin: 157.098px 432.735px;" id="el0k14oyw25f3r" class="animable"/><path d="M180.12,419.17h-.26l-.79,0-3,.12-11.06.55-36.49,2c-1.79.09-3.55.24-5.28.27s-3.46.1-5.14.2-3.33.23-5,.4a17.62,17.62,0,0,0-4.7,1,11.11,11.11,0,0,0-3.91,2.5,10.54,10.54,0,0,0-2.38,3.81,19.53,19.53,0,0,0-1,4.11,27.72,27.72,0,0,0-.26,3.89,16.49,16.49,0,0,0,1.24,6.55,10.7,10.7,0,0,0,2.93,4,7.94,7.94,0,0,0,2.66,1.51c.66.21.68.14,0-.1a8.56,8.56,0,0,1-2.58-1.56,10.65,10.65,0,0,1-2.79-3.95,16.31,16.31,0,0,1-1.15-6.43,27.05,27.05,0,0,1,.29-3.83,18.52,18.52,0,0,1,1-4,9.81,9.81,0,0,1,2.29-3.63,10.64,10.64,0,0,1,3.77-2.39,17.56,17.56,0,0,1,4.6-.93c1.6-.16,3.25-.29,4.93-.38s3.38-.17,5.12-.2,3.5-.17,5.28-.26L165,420.21l11.06-.71,3-.22.78-.07A.7.7,0,0,0,180.12,419.17Z" style="fill: rgb(38, 50, 56); transform-origin: 140.484px 434.687px;" id="el2fvwdem4nc3" class="animable"/><path d="M201.36,428.38a2.33,2.33,0,0,1-.76.38,8.1,8.1,0,0,1-2.29.47,8.93,8.93,0,0,1-9.56-9.81,7.66,7.66,0,0,1,.53-2.27,2.16,2.16,0,0,1,.39-.75,17.08,17.08,0,0,0-.55,3.05,8.84,8.84,0,0,0,9.18,9.41A15.62,15.62,0,0,0,201.36,428.38Z" style="fill: rgb(38, 50, 56); transform-origin: 195.032px 422.828px;" id="elqkyq5qviij" class="animable"/><polygon points="153.52 369.25 144.59 399.79 111.56 403.33 111.56 409.19 160.1 409.86 166.7 370.1 153.52 369.25" style="fill: rgb(255, 190, 157); transform-origin: 139.13px 389.555px;" id="eljxtyi2glo9g" class="animable"/><path d="M196.59,337.35c5.86,0,21.73,3,23.12,13.7,1.26,9.66,3.14,20.62,3.14,20.62l-20.88.56,7.11,1.89,3,45.09-54.06-1.88,2.87-23.18L163.29,371h0l-11.81-1s4-20.17,11.09-26.4a28.7,28.7,0,0,1,15.56-6.27,163.53,163.53,0,0,1,18.46,0" style="fill: #EFB504; transform-origin: 187.165px 378.14px;" id="elfmoys2vzll9" class="animable"/><path d="M165.23,356c.13,0-.7,8.16-1.84,18.2s-2.17,18.17-2.3,18.15.7-8.16,1.85-18.2S165.11,356,165.23,356Z" style="fill: rgb(38, 50, 56); transform-origin: 163.16px 374.175px;" id="el9ao7ixpju47" class="animable"/><path d="M198.2,337.42s0,.13.06.38.05.62.06,1.09a12.13,12.13,0,0,1-.59,4,11.67,11.67,0,0,1-3.06,5.2,9,9,0,0,1-3.13,1.94,12.44,12.44,0,0,1-3.93.62,21.6,21.6,0,0,1-4-.27,8.16,8.16,0,0,1-3.43-1.39,9,9,0,0,1-3.25-5.07,13.12,13.12,0,0,1-.45-4c0-.47.05-.84.08-1.09a1.49,1.49,0,0,1,.07-.38,13,13,0,0,1,0,1.47,14.49,14.49,0,0,0,.57,3.94,8.85,8.85,0,0,0,3.2,4.83,7.84,7.84,0,0,0,3.27,1.3,21.38,21.38,0,0,0,3.88.26,12.28,12.28,0,0,0,3.79-.6,8.35,8.35,0,0,0,3-1.83,11.64,11.64,0,0,0,3.05-5,13.52,13.52,0,0,0,.71-3.91C198.18,338,198.17,337.42,198.2,337.42Z" style="fill: rgb(38, 50, 56); transform-origin: 187.396px 344.039px;" id="elyjbd5q94d7" class="animable"/><g id="ellhnziz04aq"><g style="opacity: 0.2; transform-origin: 189.118px 378.425px;" class="animable" id="elt7p6sz3cvce"><path d="M201.1,356c-2,9.57-4.61,19.4-6.61,29a14.54,14.54,0,0,1-2.12,5.8,13.77,13.77,0,0,1-2.86,2.63,50.25,50.25,0,0,1-12.74,7.42,115.74,115.74,0,0,0,19.83-3.45,4.11,4.11,0,0,0,3.87-4.38c1.44-12.27,1-24.64.63-37" id="ellhixh5k728o" class="animable" style="transform-origin: 189.118px 378.425px;"/></g></g><path d="M223.19,371.83a8.29,8.29,0,0,1-1.57.12l-4.29.16c-3.61.12-8.61.24-14.14.35l-1.77,0h-.22v-.22c-.2-5.13-.11-9.69,0-13,0-1.63.11-2.95.17-3.86a8.09,8.09,0,0,1,.13-1.41,8.58,8.58,0,0,1,0,1.41c0,1,0,2.29,0,3.87,0,3.28-.06,7.84.15,12.94l-.23-.22,1.78,0c5.52-.11,10.52-.18,14.14-.21h4.29A10.19,10.19,0,0,1,223.19,371.83Z" style="fill: rgb(38, 50, 56); transform-origin: 212.136px 363.215px;" id="elz6nuwuef6ff" class="animable"/><path d="M70.19,374,88.64,410.3h58.18l-2.36-4.73-16.85-.51-13.74-30.58a5.28,5.28,0,0,0-4.83-3.12H71.83A1.84,1.84,0,0,0,70.19,374Z" style="fill: rgb(224, 224, 224); transform-origin: 108.413px 390.83px;" id="elw1072y0g7lo" class="animable"/><circle cx="97.1" cy="390.27" r="2.23" style="fill: rgb(38, 50, 56); transform-origin: 97.1px 390.27px;" id="eliobuhh02pnk" class="animable"/><path d="M198.49,310l-2.08,29.52a9.21,9.21,0,0,1-9.18,8.55h0a9.21,9.21,0,0,1-9.19-9.8c.19-2.81.35-5.12.35-5.12s-7.13-1.11-7.23-8.25c0-3.37.4-10,.9-16.29a14.08,14.08,0,0,1,14-12.95h0A13.23,13.23,0,0,1,198.49,310Z" style="fill: rgb(255, 190, 157); transform-origin: 184.849px 321.865px;" id="eliiu0pih5gtl" class="animable"/><path d="M178.35,333.52a16.59,16.59,0,0,0,9.32-2.65s-2.21,5.14-9.34,4.5Z" style="fill: rgb(235, 153, 110); transform-origin: 183px 333.148px;" id="el8zs8bu0k0p" class="animable"/><path d="M174.3,313.27a1,1,0,0,0,1,1,1,1,0,0,0,1.05-.92,1,1,0,0,0-1-1A1,1,0,0,0,174.3,313.27Z" style="fill: rgb(38, 50, 56); transform-origin: 175.325px 313.31px;" id="elkx3pv3xxa1" class="animable"/><path d="M173.66,311.87c.13.13.9-.44,2-.44s1.9.55,2,.42-.07-.3-.42-.56a2.86,2.86,0,0,0-1.62-.5,2.72,2.72,0,0,0-1.59.52C173.71,311.57,173.6,311.81,173.66,311.87Z" style="fill: rgb(38, 50, 56); transform-origin: 175.666px 311.34px;" id="elfwhuej2x06a" class="animable"/><path d="M184.64,313.57a1,1,0,0,0,1,1,1,1,0,0,0,1-.92,1,1,0,0,0-1-1A1,1,0,0,0,184.64,313.57Z" style="fill: rgb(38, 50, 56); transform-origin: 185.64px 313.61px;" id="elpjqkgx6m7bb" class="animable"/><path d="M184.45,312.07c.13.13.91-.44,2-.43s1.9.55,2,.41-.07-.3-.42-.55a2.76,2.76,0,0,0-1.62-.5,2.68,2.68,0,0,0-1.59.51C184.51,311.77,184.39,312,184.45,312.07Z" style="fill: rgb(38, 50, 56); transform-origin: 186.457px 311.545px;" id="eli3rtrnl6u3" class="animable"/><path d="M180.47,320.21a7.49,7.49,0,0,0-1.78-.32c-.28,0-.55-.08-.6-.27a1.47,1.47,0,0,1,.19-.84l.83-2.13a37.37,37.37,0,0,0,1.86-5.58,37.71,37.71,0,0,0-2.31,5.42c-.27.75-.54,1.46-.79,2.14a1.58,1.58,0,0,0-.14,1.11.72.72,0,0,0,.46.41,2.16,2.16,0,0,0,.47.06A6.63,6.63,0,0,0,180.47,320.21Z" style="fill: rgb(38, 50, 56); transform-origin: 179.328px 315.671px;" id="elu6idym06a7i" class="animable"/><path d="M183.7,321c-.18,0-.18,1.18-1.2,2s-2.29.72-2.3.88.29.23.82.25a2.94,2.94,0,0,0,1.92-.67,2.59,2.59,0,0,0,.93-1.7C183.92,321.26,183.78,321,183.7,321Z" style="fill: rgb(38, 50, 56); transform-origin: 182.04px 322.565px;" id="elhfonqo2ksy7" class="animable"/><path d="M184.17,307.77c.11.3,1.21.16,2.49.31s2.33.51,2.5.24-.1-.41-.52-.7a4.35,4.35,0,0,0-3.8-.42C184.36,307.39,184.12,307.63,184.17,307.77Z" style="fill: rgb(38, 50, 56); transform-origin: 186.688px 307.681px;" id="eliwpqnjqf0qn" class="animable"/><path d="M174.07,308.69c.19.25,1,0,1.86,0s1.67.17,1.85-.1,0-.37-.38-.61a2.68,2.68,0,0,0-3,.09C174.09,308.31,174,308.57,174.07,308.69Z" style="fill: rgb(38, 50, 56); transform-origin: 175.949px 308.183px;" id="elazw7ficbt" class="animable"/><path d="M196.08,315.83c.12-.06,4.91-1.45,4.75,3.43s-5,3.72-5,3.58S196.08,315.83,196.08,315.83Z" style="fill: rgb(255, 190, 157); transform-origin: 198.332px 319.331px;" id="elshzbphum9tf" class="animable"/><path d="M197.35,321s.09.06.23.13a.82.82,0,0,0,.63,0,2.09,2.09,0,0,0,1-1.85,3,3,0,0,0-.2-1.23,1,1,0,0,0-.62-.67.43.43,0,0,0-.51.21c-.07.14,0,.24-.07.24s-.1-.08,0-.28a.5.5,0,0,1,.2-.3.62.62,0,0,1,.47-.11,1.19,1.19,0,0,1,.87.78,3,3,0,0,1,.25,1.38,2.24,2.24,0,0,1-1.31,2.07,1,1,0,0,1-.78-.16C197.36,321.16,197.34,321.05,197.35,321Z" style="fill: rgb(235, 153, 110); transform-origin: 198.476px 319.261px;" id="elerb1lzrkrrh" class="animable"/><rect x="81.9" y="410.36" width="71.11" height="3.47" style="fill: rgb(38, 50, 56); transform-origin: 117.455px 412.095px;" id="ele0d0shncxpd" class="animable"/><polygon points="131.11 412.1 152.74 451.23 172.11 451.23 153.01 413.83 131.11 412.1" style="fill: rgb(38, 50, 56); transform-origin: 151.61px 431.665px;" id="elbdwnfzsosqn" class="animable"/><path d="M153,413.83c0,.14-15.94.34-35.6.44s-35.6.06-35.6-.08,15.94-.34,35.6-.44S153,413.69,153,413.83Z" style="fill: rgb(69, 90, 100); transform-origin: 117.4px 414.01px;" id="eluxrxtpdwm9" class="animable"/><path d="M170.09,305.43a4.87,4.87,0,0,0,4-.29,1.15,1.15,0,0,0,.2,1.71,2.9,2.9,0,0,0,1.88.41,14.47,14.47,0,0,0,9.49-4.05,3.16,3.16,0,0,0,2.18,2.72,6.39,6.39,0,0,0,3.65,0,13.91,13.91,0,0,0-.22,5.52,4.75,4.75,0,0,0,3.58,3.8,9.25,9.25,0,0,1,.23,2.89c.07,1,.63,2.08,1.61,2.11s1.49-.86,1.61-1.71-.39-2.58.22-3.95.4-1.22,1.05-2.71,1-3.55,1.54-5.1a7.29,7.29,0,0,0,.13-4.81,4,4,0,0,0-3.73-2.65,3.93,3.93,0,0,0-5.78-4.73s-5.56-3.78-11.71-1.69-7.07,7.67-8.41,7.84-5.4-2-4.7-.64,1.86,2.73,2.44,2.79-4.65-2.15-2.85-.17A11.23,11.23,0,0,0,170.09,305.43Z" style="fill: rgb(38, 50, 56); transform-origin: 183.854px 306.26px;" id="ela6rq2l43aew" class="animable"/><path d="M219.11,353a11.9,11.9,0,0,0-1.93-1.85,11.39,11.39,0,0,0-6.86-2c-3.09,0-6.63,1.33-10.11,3.46s-7.05,5-11.47,6.93a17.52,17.52,0,0,1-13.23.49,18.45,18.45,0,0,1-5.12-2.92c-1.45-1.12-2.73-2.25-4-3.16a12.25,12.25,0,0,0-6.64-2.71,8.67,8.67,0,0,0-2.65.35s0,0,.16-.08a4.26,4.26,0,0,1,.49-.19,6.69,6.69,0,0,1,2-.28,12.15,12.15,0,0,1,6.86,2.61c2.56,1.77,5.17,4.58,9,5.93a17.37,17.37,0,0,0,12.87-.51c4.32-1.88,7.86-4.79,11.43-6.87s7.17-3.48,10.35-3.42a11.32,11.32,0,0,1,7,2.23,7.57,7.57,0,0,1,1.43,1.42A2.85,2.85,0,0,1,219.11,353Z" style="fill: rgb(250, 250, 250); transform-origin: 188.105px 354.936px;" id="ele3w9slctz9u" class="animable"/><path d="M201.32,379.32s-.49-.32-1.38-.83a14.11,14.11,0,0,0-4.05-1.56,13.61,13.61,0,0,0-6.36.11c-2.36.59-4.77,1.83-7.45,2.82a18.73,18.73,0,0,1-4.06,1,18.28,18.28,0,0,1-3.9.07,18.52,18.52,0,0,1-6.33-1.73,15.72,15.72,0,0,1-3.71-2.41,8.15,8.15,0,0,1-.87-.83c-.19-.19-.29-.3-.27-.32s.45.36,1.27,1a18,18,0,0,0,3.73,2.24,19.23,19.23,0,0,0,6.22,1.59,17.92,17.92,0,0,0,7.74-1.11,73.14,73.14,0,0,1,7.51-2.79,13.66,13.66,0,0,1,10.64,1.73,11.12,11.12,0,0,1,1,.72C201.23,379.21,201.33,379.31,201.32,379.32Z" style="fill: rgb(250, 250, 250); transform-origin: 182.129px 378.321px;" id="elj68u2vh7y0k" class="animable"/><path d="M209.19,396.13a15.54,15.54,0,0,0-1.93-.62,11,11,0,0,0-5.43.49,21.66,21.66,0,0,0-3.5,1.5c-1.22.64-2.49,1.4-3.86,2.14a22.66,22.66,0,0,1-4.53,1.95,11,11,0,0,1-5.31.22c-3.63-.79-6.5-2.89-9.14-4.46a17.85,17.85,0,0,0-7.54-3,8.61,8.61,0,0,0-5.27,1.14,8.83,8.83,0,0,0-1.56,1.29,2.08,2.08,0,0,1,.3-.44,5.36,5.36,0,0,1,1.15-1A8.54,8.54,0,0,1,168,394c2.42.12,5.09,1.31,7.76,2.92s5.52,3.61,9,4.36a10.57,10.57,0,0,0,5.07-.2,23,23,0,0,0,4.44-1.88c1.37-.72,2.65-1.47,3.89-2.11a21.77,21.77,0,0,1,3.6-1.46,10.47,10.47,0,0,1,5.57-.33,6.54,6.54,0,0,1,1.44.53C209.05,396,209.2,396.11,209.19,396.13Z" style="fill: rgb(250, 250, 250); transform-origin: 185.155px 398px;" id="el5gwr6qpv2ew" class="animable"/><path d="M211.74,415.24a1.71,1.71,0,0,1-.5.21,7.1,7.1,0,0,1-1.55.26,14,14,0,0,1-5.63-1.18,32.91,32.91,0,0,0-3.74-1.21,15.07,15.07,0,0,0-4.45-.26c-3.16.27-6.49,1.47-10,2.49a31.23,31.23,0,0,1-5.32,1.09,23.72,23.72,0,0,1-5.07-.07,34.71,34.71,0,0,1-8.25-2.21A56.26,56.26,0,0,1,162,412c-.61-.3-1.07-.56-1.38-.73a2.28,2.28,0,0,1-.47-.29,1.72,1.72,0,0,1,.51.2l1.42.64c1.24.56,3,1.37,5.28,2.21a35.6,35.6,0,0,0,8.17,2.07,22.77,22.77,0,0,0,5,0,30.42,30.42,0,0,0,5.23-1.08c3.53-1,6.9-2.21,10.15-2.46a15,15,0,0,1,4.59.31,30.08,30.08,0,0,1,3.77,1.29,14.84,14.84,0,0,0,5.49,1.32A11.87,11.87,0,0,0,211.74,415.24Z" style="fill: rgb(250, 250, 250); transform-origin: 185.945px 413.862px;" id="el6cm8h9ppz3w" class="animable"/><path d="M222,366.78a2.89,2.89,0,0,1-.61.64,8,8,0,0,1-2.06,1.26,9,9,0,0,1-3.53.69,10,10,0,0,1-4.26-1.13c-1.4-.67-2.66-1.4-3.89-1.84a7.17,7.17,0,0,0-3.37-.37,4.35,4.35,0,0,0-2.1.87c-.42.36-.6.62-.64.59a1.82,1.82,0,0,1,.5-.74,4.15,4.15,0,0,1,2.17-1.08,7.25,7.25,0,0,1,3.6.28,36.06,36.06,0,0,1,4,1.82,9.85,9.85,0,0,0,4,1.12,9,9,0,0,0,3.38-.55A16.15,16.15,0,0,0,222,366.78Z" style="fill: rgb(250, 250, 250); transform-origin: 211.77px 367.465px;" id="elwell1cj334" class="animable"/><path d="M163.92,366.28s-.06.17-.32.35a2.24,2.24,0,0,1-1.26.33,4,4,0,0,1-1.85-.53c-.63-.35-1.23-.84-1.88-1.26a5.09,5.09,0,0,0-3.74-.75c-1,.18-1.55.55-1.6.47a2.81,2.81,0,0,1,1.52-.83,5,5,0,0,1,4.1.68c.69.44,1.27.93,1.84,1.27a3.93,3.93,0,0,0,1.63.58A3,3,0,0,0,163.92,366.28Z" style="fill: rgb(250, 250, 250); transform-origin: 158.595px 365.424px;" id="ell2mvzrceas" class="animable"/><path d="M136.76,398.57l14.55-1.75,10.25,6.72,39.2-9.39,1.15-21.88,17.6-.44-.41,25.68a14.9,14.9,0,0,1-15.31,14.58l-57.42-1.59-2.44-4.89-12.32-.36Z" style="fill: rgb(255, 190, 157); transform-origin: 175.56px 391.963px;" id="elpt6cygg8m0k" class="animable"/><path d="M200.27,394.15a6.15,6.15,0,0,1-.74.22l-2.13.56-7.87,2-26,6.3-2.41.57-.1,0-.08-.06L150.69,397l.14,0-14.55,1.68.09,0-3.84,4.88c-.44.53-.78,1-1,1.28a3.17,3.17,0,0,1-.38.43,3.38,3.38,0,0,1,.32-.48l1-1.33,3.74-5,0,0h.06l14.54-1.82h.08l.07,0,10.26,6.7-.18,0,2.42-.58,26-6.17,7.91-1.82,2.15-.47A4.09,4.09,0,0,1,200.27,394.15Z" style="fill: rgb(235, 153, 110); transform-origin: 165.71px 399.71px;" id="ele1e6sszl0l9" class="animable"/><path d="M144.71,402.74a36.08,36.08,0,0,1-5.66.23h-1l.15,0a27.21,27.21,0,0,1-3.67,2.82,26.55,26.55,0,0,1,3.37-3.16l.07-.06h1.11A34.68,34.68,0,0,1,144.71,402.74Z" style="fill: rgb(235, 153, 110); transform-origin: 139.62px 404.157px;" id="eliwkmh64bub" class="animable"/><path d="M207.49,398.53a8.54,8.54,0,0,0-6.78-4.27,2.45,2.45,0,0,1,1.26-.1,6.77,6.77,0,0,1,2.91,1,6.6,6.6,0,0,1,2.15,2.19A2.4,2.4,0,0,1,207.49,398.53Z" style="fill: rgb(235, 153, 110); transform-origin: 204.1px 396.325px;" id="el7rhtwsad9vb" class="animable"/></g><defs>     <filter id="active" height="200%">         <feMorphology in="SourceAlpha" result="DILATED" operator="dilate" radius="2"/>                <feFlood flood-color="#32DFEC" flood-opacity="1" result="PINK"/>        <feComposite in="PINK" in2="DILATED" operator="in" result="OUTLINE"/>        <feMerge>            <feMergeNode in="OUTLINE"/>            <feMergeNode in="SourceGraphic"/>        </feMerge>    </filter>    <filter id="hover" height="200%">        <feMorphology in="SourceAlpha" result="DILATED" operator="dilate" radius="2"/>                <feFlood flood-color="#ff0000" flood-opacity="0.5" result="PINK"/>        <feComposite in="PINK" in2="DILATED" operator="in" result="OUTLINE"/>        <feMerge>            <feMergeNode in="OUTLINE"/>            <feMergeNode in="SourceGraphic"/>        </feMerge>            <feColorMatrix type="matrix" values="0   0   0   0   0                0   1   0   0   0                0   0   0   0   0                0   0   0   1   0 "/>    </filter></defs></svg>
              </div>
					<div class="custom-model-content">					
						<h2 class="mb-15">Sign Up</h2>
						<p class="text-sm">You can Sign Up from below categories</p>
					</div>
					
					<ul class="wrapper1">	
						<li class="wrap-multi-btn">				
						<a href="https://apps.bisag.co.in/AyushCounselling/StudentSignUp" class="animation-btn btn--bolt">
							<span class="btn-icon"><i
								  class="bi bi-mortarboard-fill fs-2"></i></span><span
								  class="btn-topFakeBorders"></span>
							<span class="btn-bottomAnim btn-anim"></span>
							<span class="btn-sideAnim btn-anim"></span>
							<span class="btn-topAnim btn-anim"></span>
							<span class="btn-content">Student</span>
						</a>
						</li>
						<li class="wrap-multi-btn">
						<a href="institute_registration_url" class="animation-btn btn--bolt">
							<span class="btn-icon"><i class="bi bi-building fs-2"></i></span>
							<span class="btn-topFakeBorders"></span>
							<span class="btn-bottomAnim btn-anim"></span>
							<span class="btn-sideAnim btn-anim"></span>
							<span class="btn-topAnim btn-anim"></span>
							<span class="btn-content">College</span>
						</a>
						</li>
					</ul>

				</div>
				<!-- 					<div class="modal-footer">
						<ul class="buttons-group">
							<li><a type="button" class="main-btn dark-btn n btn-hover"
								data-bs-dismiss="modal">Close</a></li>
							<li><a onclick="notification();"
								class="main-btn success-btn  btn-hover" type="button">Submit</a>
							</li>
						</ul>
					</div> -->
				</div>
			</div>
		</div>

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

							if ('${msg}' != "") {
								alert('${msg}');
							}

						});

		function fn_new() {
			$("#div_ayush").show();
			$("#div_ayush_nrh").hide();
			$("#hideshow_instituteid").show();
			$("#hideshow_stateid").show();
			$("#registrationstate_stateid").show();

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

			$.post("getRStateMapUrl", {
				selval : selval
			}, function(j) {
				alert(j)
				var regisration_district = j;

				$("#regisration_state").html(options);
			});
		}
	</script>

	<!-- signup default script for developer end-->




<script type="text/javascript" src="admin/assets/vendor/page-fontsize/page_fontsize.js"></script>


</body>

</html>
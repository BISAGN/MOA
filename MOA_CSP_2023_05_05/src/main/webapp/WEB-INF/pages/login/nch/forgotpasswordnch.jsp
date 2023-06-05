<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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

<body class="theme-color-v2 light-mode" id="body">
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
	              <label class="pb-2" for="Type">Keyword</label>
	              <input type="text" class="form-control form-control-lg form-control-a" placeholder="Keyword">
	            </div>
	          </div>
	          <div class="col-md-12">
	            <button type="submit" class="btn btn-b mt-3">Search Property</button>
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
						<li><a class="dropdown-item" href="forgotpasswordnchhindi">हिन्दी</a></li>
    <li><a class="dropdown-item" href="forgotpasswordnch">English</a></li>
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
					<a href="landingpage"> <img src="admin/assets/img/grid-logo.png"
						class="img-fluid"> 
					</a>
					<span class="logo-subtext"><img alt="moa-logo" src="admin/assets/img/moa-icon.jpg" class="moa-logo">Ministry of <span class="org-text">Ayush</span></span> 
				</div>
				<button class="navbar-toggler collapsed" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarDefault"
					aria-controls="navbarDefault" aria-expanded="false"
					aria-label="Toggle navigation">
					<span></span> <span></span> <span></span>
				</button>
				<div class="navbar-collapse collapse justify-content-center"
					id="navbarDefault">
					<div class="container">	
					<ul class="navbar-nav justify-content-center">

						<li class="nav-item"><a class="nav-link active"
							href="nchlanding"><i class="bi bi-house-door"></i></a></li>

						<!-- <li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Ayush System</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="ayurveda">Ayurveda</a> <a
									class="dropdown-item" href="#">Yoga</a> <a
									class="dropdown-item" href="#">Naturopathy</a> <a
									class="dropdown-item" href="#">Unani</a> <a
									class="dropdown-item" href="#">Siddha</a> <a
									class="dropdown-item" href="#">Sowa-Rigpa</a> <a
									class="dropdown-item" href="#">Homoeopathy</a>
							</div></li> -->

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Ayush System</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="nchayurveda">Ayurveda</a> <a
									class="dropdown-item" href="nchunani">Unani</a> <a
									class="dropdown-item" href="nchsiddha">Siddha</a> <a
									class="dropdown-item" href="nchsowa">Sowa-Rigpa</a> <a
									class="dropdown-item" href="nchhomoeopathy">Homoeopathy</a>
							</div></li>

						<li class="nav-item"><a class="nav-link" href="aboutnch">About NCH</a></li>													
								
						<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">Curriculum</a>
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
						
						<li class="nav-item"><a class="nav-link" href="portalsignin">Regulation</a></li>
						
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
						
						<li class="nav-item"><a class="nav-link" href="nchcontact">Contact</a></li>

						<!-- 	<li class="nav-item dropdown"><a
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
							</div></li>

						<li class="nav-item"><a class="nav-link" href="alumni">Alumni</a></li>

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
									class="dropdown-item" href="nchcontact">Contact US</a> <a
									class="dropdown-item" href="feedback">Feedback</a>
							</div></li> -->

					</ul>
					</div>
				</div>
				<div class="custom-btn">
					<div class="authentication-detail">

<!-- <a href="nchsignin" class="btn btn-bh"><span class="login-text">Sign In</span><span class="login-icon d-none"><i class="bi bi-person-lines-fill"></i></span></a> --> 
<a href="portalsignin" class="btn btn-bh"><span class="login-text">Sign In</span><span class="login-icon d-none"><i class="bi bi-person-lines-fill"></i></span></a>
<!-- <a href="nchsignup" class="btn btn-bh"><span class="login-text">Sign Up</span><span class="login-icon d-none"><i class="bi bi-person-plus-fill"></i></span></a> -->
						
						<!-- <a href="nchsignin" class="btn btn-bh">Sign In</a> 
						<a href="nchsignup" class="btn btn-bh" style="display: none;" >Sign Up</a> -->

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
						<!-- 	<li class="logo-s1"><a href="#"><img
								src="admin/assets/img/ncism_logo.png"></a></li> -->
						<li class="logo-s1"><a href="#"><img
								src="admin/assets/img/nchlogo.png"></a></li>
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
              <h1 class="title-single">Forgot Password</h1>
             <!--  <p>Enter your email address and we will send you a link to reset your password.</p> -->
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="landingpage">Home</a>
                </li>
                <li class="breadcrumb-item active" aria-current="page">
                  Forgot Password
                </li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
    </section>
    <!-- End Intro Single--> 

  <!--  Forgot Section  -->    
    <section class="section-fogot">
      <div class="container">
        <div class="row">
          
          <div class="col-sm-12">
            <div class="row justify-content-center">
              <div class="col-lg-6 col-md-6 col-sm-12">
                    
                    <div class="login-form">
	                    <form action="#">
	                    	<div class="row">
	                    		<div class="col-md-12 mb-3">
									<div class="form-group">
									  <label for="email-1">Enter Email</label>
									  <input type="text" name="f-email" class="form-control form-control-lg form-control-a" placeholder="your-email@gmail.com" id="email-1">
									</div>
	                    		</div>
								<div class="col-md-12 text-center">							
								  <button type="submit" class="btn btn-a" value="Reset Password" >Submit</button>
								</div>
	                    	</div>
	                    </form> 
						<div class="row">                  
							<div class="col-md-12 col-sm-12">
								<div class="login-part">
								   <p>Already have an account? <a href="nchsignin">Sign In</a></p>
	                      		   <p>New here? <a href="#">Sign Up</a></p>
								 </div>
							</div>
						</div>                     
                    </div>                
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--  End Forgot Section  -->


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
									<a href="nchcontact">Contact Us</a></li>
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

  <!-- Vendor JS Files -->
  <script src="admin/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="admin/assets/vendor/swiper/swiper-bundle.min.js"></script>

  <!-- Template Main JS File -->
  <script src="admin/assets/js/main.js"></script>
  <script type="text/javascript" src="admin/assets/vendor/page-fontsize/page_fontsize.js"></script>

</body>

</html>
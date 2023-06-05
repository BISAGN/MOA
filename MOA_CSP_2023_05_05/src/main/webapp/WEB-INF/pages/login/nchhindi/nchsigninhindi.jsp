<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Ayush Grid</title>

<!-- Favicons -->
<link href="admin/assets/img/favicon.ico" rel="icon">

<!-- Font CSS Files -->
<link href="admin/assets/font/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="admin/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
<link href="admin/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="admin/assets/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">
	 <link href="admin/assets/vendor/hover.css" rel="stylesheet" media="all">
<link href="admin/assets/vendor/svg-animation.css" rel="stylesheet" media="all">

<!-- Template Main CSS File -->
<link href="admin/assets/css/style.css" rel="stylesheet">
<link href="admin/assets/css/responsive.css" rel="stylesheet">
<link href="admin/assets/css/custom_pages_style.css" rel="stylesheet">
<script type="text/javascript" src="admin/assets/js/jquery-3.6.0.min.js"></script>

<!-- theme switch mode -->
<link href="admin/assets/vendor/themeSwitchMode/switchmodestyle.css" rel="stylesheet">
<link href="admin/assets/css/themeswitch-mode.css" rel="stylesheet">
<script type="text/javascript" src="admin/assets/vendor/themeSwitchMode/themeswitchermode.js"></script>
<link href="admin/assets/vendor/page-fontsize/page_fontsize.css" rel="stylesheet">
<!-- <script type="text/javascript">
	jQuery(document).ready(function() {
		var msg = document.getElementById("msg").value;
		if (msg != null) {
			alert(msg);
		}
	});
</script> -->
</head>

<body class="theme-color-v2 landing-main light-mode" id="body">

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

	
		<header>
		<div class="language-block text-right">
		<!-- <div class="container-fluid">
			
				<ul class="list-unstyled list-inline custom-m-line lang-custom-m-line">
					
					<li class="list-inline-item"><a href="nchlandinghindi">हिन्दी</a></li>
					<li class="list-inline-item"><a href="nchlanding">English</a></li>
				</ul>
			
		</div> -->
		 <div class="container">
		 <div class="head-top d-flex">
			
	  <div id="chcp_font_size" class="input-group page-fontsize">
        <div class="input-group-btn">
          <button id="btn-decrease" class="btn btn-default" type="button">ए-</button>
          <button id="btn-orig" class="btn btn-default" type="button">ए</button>
          <button id="btn-increase" class="btn btn-default" type="button">ए+</button>
        </div>
      </div>
		<div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
    Language
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
    <li><a class="dropdown-item" href="nchsigninhindi">हिन्दी</a></li>
    <li><a class="dropdown-item" href="nchsignin">English</a></li>
   
  </ul>
  </div>
</div>
</div>
	</div>
	<nav class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
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
								href="nchlandinghindi"><i class="bi bi-house-door"></i></a></li>

							<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">आयुष पद्धतियाँ</a>
							<div class="dropdown-menu">
							<a class="dropdown-item" href="nchayurvedahindi">आयुर्वेद</a><!--  <a
									class="dropdown-item" href="#">Yoga</a> <a
									class="dropdown-item" href="#">Naturopathy</a>  --><a
									class="dropdown-item" href="nchunanihindi">यूनानी</a> <a
									class="dropdown-item" href="nchsiddhahindi">सिद्ध</a> <a
									class="dropdown-item" href="nchsowahindi">सोवा-रिग्पा</a>
									 <a
									class="dropdown-item" href="nchhomoeopathyhindi">होम्योपैथी</a>
								
							</div></li>

<li class="nav-item"><a class="nav-link" href="aboutnchhindi">
एनसीएच के बारे में</a></li>
							
							<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">पाठ्यक्रम</a>
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
							
							<li class="nav-item"><a class="nav-link" href="portalsignin">विनियमन</a></li>
							
							<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">प्रवेश</a>
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
								aria-expanded="false">शैक्षिक जानकारी</a>
								<div class="dropdown-menu max-dropdown">	
								
								<div class="multi-col-menu col-m2">
									<a class="dropdown-item" href="https://apps.bisag.co.in/AyushCounselling/StudentSignUp"">आवेदन प्रबंधन</a>							
									<a class="dropdown-item" href="https://apps.bisag.co.in/AyushCounselling/landingpage">काउंसिलिंग</a>
									<a class="dropdown-item" href="portalsignin">समय-सारणी प्रबंधन</a>
									<a class="dropdown-item" href="InstituteInformation">संस्थान शुल्क संरचना</a>								
									<a class="dropdown-item" href="portalsignin">व्याख्यान प्रबंधन</a>
									<a class="dropdown-item" href="portalsignin">छात्र प्रबंधन</a>
									<a class="dropdown-item" href="portalsignin">परीक्षा प्रणाली</a>
									<a class="dropdown-item" href="portalsignin">छात्र उपस्थिति</a>
									<a class="dropdown-item" href="portalsignin">स्टाफ उपस्थिति</a>
									<a class="dropdown-item" href="https://apps.bisag.co.in/HMS/hmssignin">छात्रावास प्रबंधन</a>
									<a class="dropdown-item" href="portalsignin">पुस्तकालय प्रबंधन</a>
									<a class="dropdown-item" href="portalsignin">प्रतिक्रिया प्रबंधन</a>
									<a class="dropdown-item" href="portalsignin">पूर्व छात्रों</a>
									<a class="dropdown-item" href="portalsignin">रिपोर्ट प्रबंधन</a>																
									<div class="dropdown-item sub-dropdown-menu">	
										<a class="dropdown-item" href="https://apps.bisag.co.in/API">ओपन एपीआई</a>
									<a class="dropdown-item" href="https://apps.bisag.co.in/API">कोर रजिस्ट्रियां</a>
									<a class="dropdown-item" href="https://apps.bisag.co.in/API">मूल निर्देशिकाएं</a>
									<a class="dropdown-item" href="institute_registration_url">फीस</a>
                                    </div>
                                    <div class="dropdown-item sub-dropdown-menu">
									<a class="dropdown-item" href="portalsignin">प्लेसमेंट</a>
									<a class="dropdown-item" href="search_placement_Reg_Url">- नौकरी खोजने वाला</a>
									<a class="dropdown-item" href="company_signup_Url">- उद्यम / अस्पताल /अन्य पंजीकरण फॉर्म</a>																
                                    </div>
								</div>
									
								</div>
							</li>
							
							<li class="nav-item"><a class="nav-link" href="portalsignin">ई-लर्निंग</a></li>							
						   <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">अन्य</a>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="ViewCollabration">संगठन के साथ सहयोग</a>									
								</div>
						   </li>
						   	<li class="nav-item"><a class="nav-link" href="nchcontacthindi">संपर्क करें</a></li>

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

						<!-- <a href="login" class="btn btn-bh">साइन इन </a> <a href="signup"
							class="btn btn-bh">साइन अप</a> -->
<a href="portalsignin" class="btn btn-bh"><span class="login-text">साइन इन</span><span class="login-icon d-none"><i class="bi bi-person-lines-fill"></i></span></a> 
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
						<h1 class="title-single">साइन इन</h1>
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="nchlandinghindi">मुख पृष्ठ</a></li>
							<li class="breadcrumb-item active" aria-current="page">साइन इन</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single-->

		<!--  Login Section  -->
	<section class="section-login">	
		<div class="container">
			<div class="form-main-block">			
				<div class="row justify-content-center">
					<div class="col-lg-5 col-md-12 col-sm-12">
						<div class="area form-bg">
							<div class="form-intro white-text">
								<div class="text-welcome">
									<span class="img-intro"><img
										src="admin/assets/img/nchlogo.png"></span>
									<h4 class="intro-title">एनसीएच साइन इन में आपका स्वागत है</h4>
								</div>
								<!-- <div class="login-part">
									<p>
										नया उपयोगकर्ता? <a href="#">साइन अप</a>
									</p>
									<p>
										पासवर्ड भूल गए हैं? <a href="forgotpasswordnchhindi">यहां क्लिक करें</a>
									</p>
								</div> -->
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

<form role="form" name='loginForm' action="#" method='POST' id="myFormId" class="login-form inputHeight">
								
									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group">
											<label class=" form-control-label">अपनी श्रेणी का चयन करें ?<span class="mandatory">*</span>
											</label> 
											<select id="practfactnch" name="practfactnch" onchange="roleHide(this.value);" 	class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
												<option value=" " id=" " name=" " >--चयन करे--</option>
												 <option value="Student" id="Student" name="Student" onclick="nch_pract_fact();">विद्यार्थी</option>
												 <option value="2" id="nchfact" name="practfactregistration" onclick="nch_pract_fact();">संकाय</option>
												 <option value="Principal" id="Principal" name="Principal" onclick="nch_pract_fact();">प्रधानाचार्य</option>
<!-- 											 	<option value="Institute" id="Institute" name="Institute" onclick="nch_pract_fact();">Institute</option> -->
												<option value="University" id="University" name="University" onclick="nch_pract_fact();">विश्वविद्यालय</option>
												<option value="council" id="council" name="council" onclick="nch_pract_fact();">परिषद</option>
												 <option value="Practitioner" id="nchpract" name="practfactregistration" onclick="nch_pract_fact();">व्यवसायी</option>


											</select>
										</div>
									</div>
							
									<div id="new_pract" style="display: none">					
								<div class="col-md-12 mb-3">
									<div class="form-group">
										<label for="username">आधार नंबर</label> 
										<input type="text" id="newusername" name='newusername'   
										onchange="newdatavalid()"	maxlength="14" autocomplete="off" class="form-control form-control-lg form-control-a disablecopypaste" placeholder="आपका आधार नंबर">
									</div>
								</div>

								<div class="col-md-12 mb-3">
									<div class="form-group">
										<label for="mobile">मोबाइल नंबर</label>
										 <input type="text" id="newmobile_no" name='newmobile_no' class="form-control form-control-lg form-control-a disablecopypaste"
											placeholder="आपका मोबाइल नंबर" maxlength="10" autocomplete="off" readonly='true' value="98XXXXX58">
									</div>
								</div>
 
								<div class="col-md-12 mb-3  text-center">
									<button type="button" id="gtOTP" name="gtOTP" class="btn btn-a ghost" onclick="genrateOTP();">ओटीपी प्राप्त करे</button>
 								</div>

								<div class="col-md-12 mb-3 " id="otpdiv" style="display: none">
									<div class="form-group">
 										<label>ओटीपी दर्ज करें</label>
									</div>
 									<input  id="newpassword" name="newpassword" class="otp form-control form-control-lg form-control-a disablecopypaste"   maxlength=6>

								</div>


								<div class="col-md-12 mb-3  text-center">
									<button style="display: none" type="submit" id="stOTP" name="stOTP" class="btn btn-a ghost" onclick="OTPVerify();">साइन इन करें</button>
 								</div>
 								
 	</div>
								
								
<!-- 								/////////////////////// -->
								<div id="council_login" style="display: none">
								<div class="row">
									<div class="col-md-12 mb-3">
										<div class="form-group">
											<label for="username">उपयोगकर्ता नाम</label> <input type="text"
												id="username" name='username' maxlength="30" size="35"
												autocomplete="off"
												class="form-control form-control-lg form-control-a disablecopypaste"
												placeholder="उपयोगकर्ता नाम">
										</div>
									</div>
	
									<div class="col-md-12 mb-3">
										<div class="form-group">
											<label for="password">पासवर्ड</label> <input type="password"
												id="password" name='password'
												class="form-control form-control-lg form-control-a disablecopypaste"
												placeholder="आपका  पासवर्ड" maxlength="28" size="35"  autocomplete="off">
										</div>
									</div>
									
									<div class="col-md-12 mb-3">
										<div class="row align-items-end">
											<div class="col-lg-6 col-md-6 col-sm-12">
												<div class="form-group captchadiv enter_captcha">
													<label for="password">कॅप्चा</label> <input type='text'
														class="form-control form-control-lg form-control-a disablecopypaste" size="35"
														id="txtInput" name="txtInput" placeholder="केप्चा भरे"
														maxlength="5" autocomplete="off" />
												</div>
											</div>
											<div class="col-lg-6 col-md-6 col-sm-12">
												<div class="form-group captcha img_captcha">
													<div class="input-group input-group  align-items-center">
														<img id="capcha" src="genCapchaCode"
															class="form-control disablecopypaste p-0" /> <span
															class="input-group-btn">
														<button class="btn btn-b-n" id="btnrefresh" tabindex="-1"
															type="button">
															<i class="bi bi-arrow-clockwise"></i>
														</button>
														</span>
													</div>
												</div>
											</div>
										</div>
									</div>
									 
									<div class="col-md-12 my-3">
										<div class="mb-3">
											<label> <input type="checkbox" checked="checked"
												name="remember"> याद रखे
											</label>
										</div>
									</div>
									<div class="col-md-12 text-center">									
										<button class="btn btn-a ghost" name="submit" type="submit" value="Login" onclick="return validation();">साइन इन</button>									
									</div>
									<input type="hidden" id="csrfIdSet" name="" value="" />
								</div>
								</div>
								
								
							</form>	



						
					</div>
				</div>
					<!-- <div class="col-lg-5 col-md-12 col-sm-12">
						<div class="login-form-main" style="width: 100%; height: 100%;">
							<form name='temp' action="j_spring_security_check" method='POST'>
							<form role="form" name='loginForm' action="#" method='POST'
							id="myFormId" class="login-form inputHeight">
							<div class="row">
							
								<div class="box-border-p row">
									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label class=" form-control-label"> Are You  Practitioner  ?
												 <span class="mandatory">*</span>
											</label>
										</div>
									</div>
									
									
									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label for="new" class="form-check-label">
											 <input type="radio" id="ys" name="prct" class="form-check-input radiostyle" value="0" required onclick="fn_prct();" />Yes
											</label>
											
											 <label for="old" class="form-check-label"> 
											 <input type="radio" id="no" name="prct" class="form-check-input radiostyle" value="1" required onclick="fn_prct();" /> No	
											</label>
										</div>
									</div>

									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group">
											<label class=" form-control-label">अपनी श्रेणी का चयन करें ?<span class="mandatory">*</span>
											</label> <select id="practfactnch" name="practfactnch"
												onchange="roleHide(this.value);"
												class="form-select form-select-lg mb-3"
												aria-label=".form-select-lg example">
												<option value="2" id="nchfact" name="practfactregistration"
													onclick="nch_pract_fact();">संकाय</option>
												<option value="University" id="University"
													name="University" onclick="nch_pract_fact();">विश्वविद्यालय</option>
														<option value="Institute" id="Institute"
													name="Institute" onclick="nch_pract_fact();">संस्थान</option>
														<option value="Student" id="Student"
													name="Student" onclick="nch_pract_fact();">विद्यार्थी</option>
													<option value="Principal" id="Principal"
													name="Principal" onclick="nch_pract_fact();">प्रधानाचार्य</option>
													<option value="Practitioner" id="nchpract"
													name="practfactregistration" onclick="nch_pract_fact();">व्यवसायी</option>


											</select>
										</div>
									</div>


										Faculty start
											<div id="faculty" style="display:none">					
								<div class="col-md-12 mb-3">
									<div class="form-group">
										<label for="username">Aadhaar no</label> 
										<input type="text" id="newusername" name='newusername' onkeypress="return isAadhar(this,event);" oninput="this.value = this.value.toUpperCase()"
											maxlength="14" autocomplete="off" class="form-control form-control-lg form-control-a disablecopypaste" placeholder="Your Aadhaar No">
									</div>
								</div>

								<div class="col-md-12 mb-3">
									<div class="form-group">
										<label for="mobile">Mobile No</label>
										 <input type="text" id="newmobile_no" name='newmobile_no' class="form-control form-control-lg form-control-a disablecopypaste"
											placeholder="Your Mobile No" maxlength="10" autocomplete="off" readonly='true' value="98XXXXX58">
									</div>
								</div>
 
								<div class="col-md-12 mb-3  text-center">
									<button type="button" id="gtOTP" name="gtOTP" class="btn btn-a ghost" onclick="genrateOTP();">Get-OTP</button>
 								</div>

								<div class="col-md-12 mb-3 " id="otpdiv" style="display: none">
									<div class="form-group">
 										<label>Enter OTP</label>
									</div>
 									<input  id="newpassword" name="newpassword" class="otp form-control form-control-lg form-control-a disablecopypaste"   maxlength=6>

								</div>


								<div class="col-md-12 mb-3  text-center">
									<button style="display: none" type="submit" id="stOTP" name="stOTP" class="btn btn-a ghost" onclick="OTPVerify();">Sign In</button>
 								</div>
 								
 	</div>
 	
 		Faculty end

									
		  <input type="hidden" id="username" name='username' onkeypress="return isAadhar(this,event);" oninput="this.value = this.value.toUpperCase()"
		 maxlength="14" autocomplete="off" class="form-control form-control-lg form-control-a disablecopypaste" placeholder="Your Aadhaar No">
		 <input type="hidden"  id="password" name="password" class="otp form-control form-control-lg form-control-a disablecopypaste"  type="password" maxlength=6>
		  <input type="hidden" id="mobile_no" name='mobile_no' class="form-control form-control-lg form-control-a disablecopypaste"
											placeholder="Your Mobile No" maxlength="10" autocomplete="off" readonly='true' value="98XXXXX58">
					 
								<div  id="yes_practDiv" class="col-lg-12 col-md-12 col-sm-12 mb-3"  style="display: ">
										<div class="form-group signup-form-group">
											<label for="new" class="form-check-label">
							 <input type="radio" id="new" name="registration" class="form-check-input radiostyle" value="0"   onclick="fn_new_old_pract();" />नया व्यवसायी
											</label>
										  <label for="old" class="form-check-label"> 
							 <input type="radio" id="old" name="registration" class="form-check-input radiostyle" value="1"   onclick="fn_new_old_pract();" /> पुराने  व्यवसायी
											</label>
										</div>
									</div>
							
							
							</div>
							
		 //new pract			
							
		<div id="new_pract" style="display: none">					
								<div class="col-md-12 mb-3">
									<div class="form-group">
										<label for="username">आधार नंबर</label> 
										<input type="text" id="newusername" name='newusername' onkeypress="return isAadhar(this,event);" oninput="this.value = this.value.toUpperCase()"
											maxlength="14" autocomplete="off" class="form-control form-control-lg form-control-a disablecopypaste" placeholder="आपका आधार नंबर">
									</div>
									onchange="newdatavalid()"
								</div>

								<div class="col-md-12 mb-3">
									<div class="form-group">
										<label for="mobile">मोबाइल नंबर</label>
										 <input type="text" id="newmobile_no" name='newmobile_no' class="form-control form-control-lg form-control-a disablecopypaste"
											placeholder="Your Mobile No" maxlength="10" autocomplete="off" readonly='true' value="98XXXXX58">
									</div>
								</div>
 
								<div class="col-md-12 mb-3  text-center">
									<button type="button" id="gtOTP" name="gtOTP" class="btn btn-a ghost" onclick="genrateOTP();">ओटीपी प्राप्त करे</button>
 								</div>

								<div class="col-md-12 mb-3 " id="otpdiv" style="display: none">
									<div class="form-group">
 										<label>ओटीपी दर्ज करें</label>
									</div>
 									<input  id="newpassword" name="newpassword" class="otp form-control form-control-lg form-control-a disablecopypaste"   maxlength=6>

								</div>


								<div class="col-md-12 mb-3  text-center">
									<button style="display: none" type="submit" id="stOTP" name="stOTP" class="btn btn-a ghost" onclick="OTPVerify();">साइन इन करें</button>
 								</div>
 								
 	</div>
 	///old prac					
 								
 				////////janki				
 				<div id="old_pract" style="display: none">					
								<div class="col-md-12 mb-3">
									<div class="form-group">
										<label for="username">आयुष आईडी / आधार नंबर / एनआरएच नंबर</label> 
										<input type="text" id="ayus" name='ayus' oninput="this.value = this.value.toUpperCase()"
						onchange="fnayusNrhAbhaDataFetch()"	 	maxlength="14" autocomplete="off" class="form-control form-control-lg form-control-a disablecopypaste" placeholder="कृपया कोई भी दर्ज करें">
									</div>
								</div>

								<div class="col-md-12 mb-3">
									<div class="form-group">
										<label for="mobile">मोबाइल नंबर</label>
										 <input type="text" id="oldmobile_no" name='oldmobile_no' class="form-control form-control-lg form-control-a disablecopypaste"
											placeholder="आपका मोबाइल नंबर" maxlength="10" autocomplete="off" readonly='true' value="98XXXXX58">
									</div>
								</div>
 
								<div class="col-md-12 mb-3  text-center">
									<button type="button" id="oldgtOTP" name="oldgtOTP" class="btn btn-a ghost" onclick="oldgenrateOTP();">ओटीपी प्राप्त करे</button>
 								</div>

								<div class="col-md-12 mb-3 " id="oldotpdiv" style="display: none">
									<div class="form-group">
 										<label>ओटीपी दर्ज करें</label>
									</div>
 									<input  id="oldpassword" name="oldpassword" class="otp form-control form-control-lg form-control-a disablecopypaste"  maxlength=6>

								</div>


								<div class="col-md-12 mb-3  text-center">
									<button style="display: none" type="submit" id="oldstOTP" name="oldstOTP" class="btn btn-a ghost" onclick="oldOTPVerify();">साइन इन करें</button>
 								</div>
 								
 	</div>				
 	
 	//////////janki end							
 				 	
 						
 						//end no prac		
								<input type="hidden" id="csrfIdSet" name="" value="" />
							</div>
						</form>					

						</div>
					</div> -->
				</div>
			</div>
		</div>
	</section>
	<!--  End Login Section  -->

		<!--  Footer  -->
	<footer class="section-footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-5 col-md-6 col-sm-12">
					<div class="widget-a">
						<div class="w-header-a mb-2">							
							<a href="#"><img alt="moa-logo" src="admin/assets/img/ayushlogo-w.png"></a>
						</div>
						<div class="w-body-a">
							<p class="w-text-a">जवाहर लाल नेहरू भारतीय चिकित्सा एवं होम्योपैथी अनुसंधान भवन, 61-65, संस्थागत क्षेत्र, जनकपुरी "डी" ब्लॉक, नई दिल्ली-110058</p>
						</div>
						<div class="w-footer-a">
							<ul class="list-unstyled">
								<li class="color-a"><span class="color-text-a">
फ़ोन
										:</span> <a href="tel:1800-11-0180,1964">1800-11-0180,1964</a></li>
								<li class="color-a"><span class="color-text-a">
ईमेल
										:</span> <a href="mailto:webmanager-ayush@gov.in">webmanager-ayush@gov.in</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-6 col-sm-12 section-md-t3">
					<div class="widget-a">
						<div class="w-header-a">
							<h3 class="w-title-a text-brand">
आयुष ग्रिड</h3>
						</div>
						<div class="w-body-a">
							<ul class="list-unstyled">
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#">
वेबसाइट नीतियाँ</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#">
अस्वीकरण</a></li>
								
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#">गोपनीयता नीति</a></li>
									
									<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="nchcontacthindi">
संपर्क करें</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-12 section-md-t3">
					<div class="w-header-a">
						<h3 class="w-title-a text-brand">
हमारे साथ जुड़ें</h3>
					</div>
					<div class="socials-a">
						<ul class="list-inline">
							<li class="list-inline-item"><a href="#" class="fb-icon hvr-bounce-in"> <i
									class="bi bi-facebook" aria-hidden="true"></i>
							</a></li>
							<li class="list-inline-item"><a href="#" class="tw-icon hvr-bounce-in"> <i
									class="bi bi-twitter" aria-hidden="true"></i>
							</a></li>
							<li class="list-inline-item"><a href="#" class="insta-icon hvr-bounce-in"> <i
									class="bi bi-instagram" aria-hidden="true"></i>
							</a></li>
							<li class="list-inline-item"><a href="#" class="ld-icon hvr-bounce-in"> <i
									class="bi bi-linkedin" aria-hidden="true"></i>
							</a></li>
						</ul>
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
							वेबसाइट डिजाइन, विकसित, सामग्री प्रबंधित और होस्ट किया गया <a
								href="https://bisag-n.gov.in/" class="f-link">
बाइसेग-एन</a>
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
	<script src="admin/assets/js/jquery-3.6.0.min.js"></script>
	
	
	<!-- Signin jsp script start-->
	<!-- Signin jsp script start-->

	<!-- Signin jsp script start-->
	<script nonce="${cspNonce}" type="text/javascript">
	  	var   csrfparname ="${_csrf.parameterName}";
	  	var csrfvalue="${_csrf.token}";
		var yuji = "<c:url value='/auth/login_check?targetUrl=${targetUrl}' />";
		
		jQuery(document).ready(function(){
						
			
			var msg = "";
	   		msg = jQuery('label#msg').text();
	   		if('${error}' != ""){
				jQuery("div#errorDiv").show();
			}
			if('${msg}' != ""){
				window.alert = function(al, $){
				    return function(msg) {
				        al.call(window,msg);
				        $(window).trigger("okbuttonclicked");
				    };
				}(window.alert, window.jQuery);

				$(window).on("okbuttonclicked", function() {
				    console.log("you clicked ok");
				    window.location = window.location.href.split("?")[0];
				});
				alert('${msg}');
				jQuery("div#errorDiv").show();
			}	
			
			// Start Canvas Capcha
			function captcha() {
				$("#capcha").attr("src", "genCapchaCode");
			};
			function clear() {
				$("#txtInput").val("");
			};
			$("#btnrefresh").click(function() {
			    clear();
			    captcha();
			})
	   		// End Canvas Capcha
	   		
	   		/* jQuery(document).on('keypress', function(event) {
	     		var regex = new RegExp("^[a-zA-Z0-9\\[\\] \\+ \\* \\-.,/ ~!@#$^&%_]+$");
	     	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	     	    if (!regex.test(key)) {
	     	       event.preventDefault();
	     	       return false;
	     	    } 
	     	}); */
		});	
	    
	    function validation() {
			var ck_username = /^[A-Za-z0-9_]{1,20}$/;
			var ck_password =  /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/;
			var a = document.getElementById("username");
			if (a.value == "" ||a.value == "'" || a.value == null || a.value.toString().trim() == "" ||a.value == "'''" ) {
				alert("Enter username");
				a.focus();
				return false;
			}
			var b = document.getElementById("password");
			if (b.value == "" || b.value == "'"|| b.value == null || b.value.toString().trim() == "" ) {
				alert("Enter password");
				b.focus();
				return false;
			}	
			var iCapcha = removeSpaces(jQuery('#txtInput').val());
			if(iCapcha == "" || iCapcha.length != 5){
				alert("Enter valid Captcha!");
				jQuery('#txtInput').focus();
		    	return false;
		    }
			if(iCapcha != ""){
				var test = ValidCaptcha(iCapcha);
				if(test != "0"){
					jQuery('#csrfIdSet').attr('name',csrfparname);
			    	jQuery('#csrfIdSet').attr('value',csrfvalue);
			    	jQuery('#myFormId').attr('action', yuji);
			    	return true;
				}else{
					alert("Captcha Validation failed!");
					jQuery('#txtInput').focus();
					return false;
				}
			}
			return false;
		}
		// Validate the Entered input aganist the generated security code function   
		function ValidCaptcha(iCapcha){
			var test = "0";
	    	try{
				$.ajax({
					url : "checkCapchaCode?"+csrfparname+"="+csrfvalue,
					type : 'POST',
					data : {iCapcha:iCapcha},
					success : function(data) {
						if(data){
							test = data;
			     		}
					},
					async : false,
				});
	    	}catch(err){
	    		console.log(err.message);
	    	}
			return test;
	    }
		// Remove the spaces from the entered and generated code
		function removeSpaces(string)
		{
		    return string.split(' ').join('');
		}
	</script>
	<!-- Signin jsp script end-->
	<script type="text/javascript" src="admin/assets/js/jquery-3.6.0.min.js"></script>
<script nonce="${cspNonce}" type="text/javascript">








jQuery(document).ready(function(){
	
	
	 
	
		var msg = "";
		msg = jQuery('label#msg').text();
		if('${error}' != ""){
		jQuery("div#errorDiv").show();
	}
	if('${msg}' != ""){
		window.alert = function(al, $){
		    return function(msg) {
		        al.call(window,msg);
		        $(window).trigger("okbuttonclicked");
		    };
		}(window.alert, window.jQuery);

		$(window).on("okbuttonclicked", function() {
		    console.log("you clicked ok");
		    window.location = window.location.href.split("?")[0];
		});
		alert('${msg}');
		jQuery("div#errorDiv").show();
	}	
	
	// Start Canvas Capcha
	function captcha() {
		$("#capcha").attr("src", "genCapchaCode");
	};
	function clear() {
		$("#txtInput").val("");
	};
	$("#btnrefresh").click(function() {
	    clear();
	    captcha();
	})
		// End Canvas Capcha
		
		/* jQuery(document).on('keypress', function(event) {
 		var regex = new RegExp("^[a-zA-Z0-9\\[\\] \\+ \\* \\-.,/ ~!@#$^&%_]+$");
 	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
 	    if (!regex.test(key)) {
 	       event.preventDefault();
 	       return false;
 	    } 
 	}); */
});	












function OTPVerify(){
	 
	var u = $("#newusername").val();
	$("#username").val(u);
	var m = $("#newmobile_no").val();
	$("#mobile_no").val(m);
	var p = $("#newpassword").val();
	$("#password").val(p);
	try{
		var aadhar_no = $("#username").val();
		var otpField = $("#password").val();
		var mobile_no = $("#mobile_no").val();
		var status=0;
		$.ajax({
			url : "VerifyOTP?"+csrfparname+"="+csrfvalue,
			type : 'POST',
			data : {aadhar_no:aadhar_no,otpField:otpField,mobile_no:mobile_no},
			success : function(data) {
				if(data == true){
					status = 1;
					jQuery('#csrfIdSet').attr('name',csrfparname);
			    	jQuery('#csrfIdSet').attr('value',csrfvalue);
			    	jQuery('#myFormId').attr('action', yuji);
			    	return true;
				}else{
					status = 0;
					return false;

				}

			},
			async : false,
		});
	}catch(err){
		console.log(err.message);
	}
	
}
function newdatavalid(obj) {
	 
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
 	var newusername = $("#newusername").val();
	  
	$.post("newdatavalidfetch?" + key + "=" + value, { newusername : newusername }, function(j) {
		 
		if (j.length == 0) {
			
			alert("Please enter Valid Adhar Card number.");
			$("#newusername").val("");
 		//$("#username").val(j[0][4]);
// 			$("#email_id").val(j[0][1]);
// 			$("#institute_name").val(j[0][2]);
// 			$("#institute_state").val(j[0][3]);
// 			$("#regisration_state").val(j[0][4]);
		}
		else{
			 
			genrateOTP();
		}
	});
}
var   csrfparname ="${_csrf.parameterName}";
var csrfvalue="${_csrf.token}";
var yuji = "<c:url value='/auth/login_check?targetUrl=${targetUrl}' />";
function roleHide(val){
	 
	$("#username").val("");
	$("#password").val("");
	$("#newusername").val("");
	//$("#newmobile_no").val("");
	
	if(val=="Practitioner")
	{
		$("div#council_login").hide();
		$("div#new_pract").show();
		
	}else{
 		$("div#new_pract").hide();
		$("div#council_login").show();
		
	}
	
}


function fnayusNrhAbhaDataFetch() {
	 
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
 	var ayus = $("#ayus").val();
	// 		alert(nrh_no+"==dd");
	$.post("ayus_abha_nrh_data_fetch?" + key + "=" + value, {
		ayus : ayus
	}, function(j) {
		 
		if (j.length != 0) {
 		$("#username").val(j[0][4]);
// 			$("#email_id").val(j[0][1]);
// 			$("#institute_name").val(j[0][2]);
// 			$("#institute_state").val(j[0][3]);
// 			$("#regisration_state").val(j[0][4]);
		}
		else{
			alert("Please enter valid number ");
			$("#ayus").val("");
		}
	});
 }
 
function genrateOTP(){
	 
	
	var pract_type = $("#new").val();
	 
	var u = $("#newusername").val();
	$("#username").val(u);
	var m = $("#newmobile_no").val();
	$("#mobile_no").val(m);
	try{
		var aadhar_no = $("#username").val();
		var mobile_no = $("#newmobile_no").val();
		 
		var  csrfparname ="${_csrf.parameterName}";
	  	var csrfvalue="${_csrf.token}";
		var yuji = "<c:url value='/auth/login_check?targetUrl=${targetUrl}' />";
		 
		
		
		if(aadhar_no != null && aadhar_no != ""){
		$.ajax({
			url : "genrateOTP?"+csrfparname+"="+csrfvalue,
			type : 'POST',
			data : {aadhar_no:aadhar_no,mobile_no:mobile_no},
			success : function() {
		 
				$("#gtOTP").hide();
				$("#otpdiv").show();
				$("#stOTP").show();

				$("#oldgtOTP").hide();
				$("#oldotpdiv").hide();
				$("#oldstOTP").hide();
				
			},
			async : false,
		});
		}else{
			alert("Please Enter Aadhar No");
		}
	}catch(err){
		console.log(err.message);
	}
	}



function OTPVerify(){
	
	var u = $("#newusername").val();
	$("#username").val(u);
	var m = $("#newmobile_no").val();
	$("#mobile_no").val(m);
	var p = $("#newpassword").val();
	$("#password").val(p);
	try{
		var aadhar_no = $("#username").val();
		var otpField = $("#password").val();
		var mobile_no = $("#mobile_no").val();
		var status=0;
		$.ajax({
			url : "VerifyOTP?"+csrfparname+"="+csrfvalue,
			type : 'POST',
			data : {aadhar_no:aadhar_no,otpField:otpField,mobile_no:mobile_no},
			success : function(data) {
				if(data == true){
					status = 1;
					jQuery('#csrfIdSet').attr('name',csrfparname);
			    	jQuery('#csrfIdSet').attr('value',csrfvalue);
			    	jQuery('#myFormId').attr('action', yuji);
			    	return true;
				}else{
					status = 0;
					return false;

				}

			},
			async : false,
		});
	}catch(err){
		console.log(err.message);
	}
	
}

</script>

</body>

</html>
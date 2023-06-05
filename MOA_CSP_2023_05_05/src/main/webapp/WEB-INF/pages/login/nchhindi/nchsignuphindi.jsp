
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Ministry of Ayush</title>

<!-- Favicons -->
<link href="admin/assets/img/favicon.ico" rel="icon">

<!-- Font CSS Files -->
<link href="admin/assets/font/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="admin/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
<link href="admin/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="admin/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
 <link href="admin/assets/vendor/hover.css" rel="stylesheet" media="all">

<!-- Template Main CSS File -->
<link href="admin/assets/css/style.css" rel="stylesheet">
<link href="admin/assets/css/custom_pages_style.css" rel="stylesheet">
<link href="admin/assets/css/responsive.css" rel="stylesheet">
<link href="admin/assets/css/custom_pages_style.css" rel="stylesheet">


<!-- //// -->


<link rel="shortcut icon" href="admin/layout_file/images/favicon.png">

<script src="admin/layout_file/bootstrap_5/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="admin/js/jquery/jquery-3.6.0.min.js"></script>


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
<!-- <link rel="stylesheet"
	href="admin/js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="admin/js/InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="admin/js/InfiniteScroll/js/jquery.mockjax.js"></script> -->

<!-- <link href="admin/js/admin/assets/scss/custom-style.css" rel="Stylesheet"></link> -->
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
<!-- Language start-->
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
    भाषा
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
    <li><a class="dropdown-item" href="nchsignuphindi">हिन्दी</a></li>
    <li><a class="dropdown-item" href="nchsignup">English</a></li>
   
  </ul>
  </div>
</div>
</div>
	</div>
	<!-- Language end-->
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
						<h1 class="title-single">
साइन अप</h1>
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="nchlandinghindi">मुख पृष्ठ</a></li>
							<li class="breadcrumb-item active" aria-current="page">
साइन अप</li>
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
										src="admin/assets/img/nchlogo.png"></span>
									<h4 class="intro-title">एनसीएच साइनअप में आपका स्वागत है</h4>
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

						<form:form action="Practitioner_registration_action" method='POST'
							modelAttribute="Practitioner_registration_cmd"
							enctype="multipart/form-data">

							<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="box-border-p row">
									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label class=" form-control-label"> 
क्या आप नए प्रैक्टिशनर हैं
												?<span class="mandatory">*</span>
											</label>
										</div>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label for="new" class="form-check-label"> <input
												type="radio" id="new" name="registration"
												class="form-check-input radiostyle" value="0" required
												onclick="fn_new();" />
हाँ
											</label> <label for="old" class="form-check-label"> <input
												type="radio" id="old" name="registration"
												class="form-check-input radiostyle" value="1" required
												onclick="fn_old();" />
नहीं
											</label>
										</div>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
									<div class="form-group">
										<input type="hidden" id="id" name="id" value="0"
											class="form-control" autocomplete="off" />
									</div>
								

								<div id="div_ayush" style="display: none;" class="box-border-p row">
									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label class=" form-control-label">
क्या आपके पास आयुष आईडी है?<span class="mandatory">*</span>
											</label>
										</div>
									</div>

									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label for="YES" class="form-check-label"> <input
												type="radio" id="yes" name="aayushid"
												class="form-check-input radiostyle" value="0" required
												onclick="fn_ayus_yes();" />हाँ
											</label> <label for="NO" class="form-check-label"> <input
												type="radio" id="no" name="aayushid"
												class="form-check-input radiostyle" value="1" required
												onclick="fn_ayus_no()" />नहीं
											</label>
											<div class="col-md-12">
												<input type="hidden" id="id" name="id" value="0"
													class="form-control" autocomplete="off" />
											</div>
										</div>
									</div>
								</div>

								<div id="div_ayush_nrh" style="display: none;"
									class="box-border-p row">
									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label class=" form-control-label">
क्या आपके पास आयुष आईडी या एनआरएच नंबर है?<span class="mandatory">*</span>
											</label>
										</div>
									</div>

									<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
										<div class="form-group signup-form-group">
											<label for="YES" class="form-check-label"> <input
												type="radio" id="yes" name="aayushid"
												class="form-check-input radiostyle" value="0" required
												onclick="fn_ayus_nrh_yes();" />आयुष आईडी
											</label> <label for="NO" class="form-check-label"> <input
												type="radio" id="no" name="aayushid"
												class="form-check-input radiostyle" value="1" required
												onclick="fn_ayus_nrh_no();" />एनआरएच नंबर
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
											<label class=" form-control-label">आयुष आईडी<span
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
											<label class=" form-control-label">एनआरएच नंबर<span
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
														<label class=" form-control-label">
पहला नाम<span class="mandatory">*</span></label>
														<input type="text" id="name"
														name="name"
														oninput="this.value = this.value.toUpperCase()"
														maxlength="50"
														class="form-control form-control-lg form-control-a disablecopypaste"
														placeholder="
पहला नाम" autocomplete="off">
												</div>
											</div>
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">उपनाम<span class="mandatory">*</span></label>
														<input type="text" id="name"
														name="name"
														oninput="this.value = this.value.toUpperCase()"
														maxlength="50"
														class="form-control form-control-lg form-control-a disablecopypaste"
														placeholder="उपनाम" autocomplete="off">
												</div>
											</div>

										<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
											<div class="form-group">
												<label class=" form-control-label">
ईमेल आईडी<span
													class="mandatory">*</span></label> <input type="email"
													id="email_id" name="email_id" maxlength="50"
													class="form-control form-control-lg form-control-a disablecopypaste"
													placeholder="अपना ईमेल आईडी दर्ज करें" autocomplete="off">
												<div class="col-lg-12">
													<input type="hidden" id="id" name="id" value="0"
														class="form-control form-control-lg form-control-a disablecopypaste"
														autocomplete="off" />
												</div>
											</div>


										</div>

										<div class="col-lg-6 col-md-6 col-sm-12 mb-3" id="hideshow_instituteid" style="display: none;"> 
											<div class="form-group">
												<label class=" form-control-label">संस्थान का नाम<span
													class="mandatory">*</span></label> <select name="institute_name"
													class="form-control form-control-lg form-control-a disablecopypaste"
													id="institute_name" placeholder="Enter Your Institute Name"
													onchange="InstituteChangeFn(this.value)">
													<option value="0">--
चुने--</option>
													<c:forEach var="item" items="${getInstituteList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.institute_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<div class="col-lg-6 col-md-6 col-sm-12 mb-3" id="hideshow_stateid" style="display: none;">
											<div class="form-group">
												<label class=" form-control-label">राज्य<span
													class="mandatory">*</span></label> <select name="institute_state"
													id="institute_state"
													class="form-control form-control-lg form-control-a disablecopypaste autocomplete"
													>
													<option value="0">--
चुने--</option>
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
												<label class=" form-control-label">
पंजीकरण राज्य
												</label>
<!-- 											<label id ="regisration_state" class=" form-control-label">Registration State -->
<!-- 												</label> -->
											
												 <select name="regisration_state" id="regisration_state"
										class="form-control form-control-lg form-control-a disablecopypaste autocomplete">
										<option value="0">--चुने--</option>
										<c:forEach var="item" items="${MedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
	              			
										</div>
									</div>
									
									<div class="col-lg-6 col-md-6 col-sm-12 mb-3" id="registrationstate_stateid" style="display: none;">
										<div class="form-group">
												<label class=" form-control-label">
पंजीकरण राज्य<strong class="mandatory">* </strong>
												</label>
											
											
												 <select name="registration_district" id="registration_district"
										class="form-control form-control-lg form-control-a disablecopypaste autocomplete" >
										<option value="0">--चुने--</option>
										<c:forEach var="item" items="${MedStateName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
	              			
										</div>
									</div>	
									
									<div class="col-lg-6 col-md-6 col-sm-12 mb-3" id="aadhardiv" style="display: none;">
										<div class="form-group">
												<label class="form-control-label">
आधार नंबर<strong class="mandatory">* </strong>
												</label>
											<input type="text" id="aadhaar_no" name="aadhaar_no"
																onkeypress="return isAadhar(this,event);"
																oninput="this.value = this.value.toUpperCase()"
																maxlength="14" class="form-control form-control-lg" autocomplete="off"
																placeholder="Aadhaar Number">
	              			
										</div>
									</div>		
									
										
									</div>

									<div
										class="col-lg-12 col-md-12 col-sm-12 mb-3 justify-content-center d-flex">
										<div class="custom-btn footer-btn">


											
                                               <ul class="footer-btn-list">
													<li class="f-btn"><a href="nchsignin" class="link-color">
साइन इन करें</a></li>
													<li class="f-btn"><input type="submit" class="btn-save btn btn-primary" id="save_btn"
												value="पंजीकरण करें" onclick="return Validation();"></li>													
												</ul>	
										</div>
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

<script type="text/javascript" src="admin/assets/vendor/page-fontsize/page_fontsize.js"></script>


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
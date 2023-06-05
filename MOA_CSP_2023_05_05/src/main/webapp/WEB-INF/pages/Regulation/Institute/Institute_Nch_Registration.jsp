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
<script type="text/javascript" src="admin/js/watermark/common.js"></script>
<!-- <link rel="stylesheet" href="admin/js/InfiniteScroll_old/css/site.css"> -->
<link rel="stylesheet" href="admin/js/autoComplate/autoComplate.css">

<link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script>



<link href="admin/js/jquery/jquery-ui.css" rel="Stylesheet"></link>
<script src="admin/js/jquery/jquery-ui.js" type="text/javascript"></script>
<!-- datatable style and js start-->
<!-- <link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css"> -->
<!-- <link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css"> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script> -->
<!-- datatable style and js end-->

<script type="text/javascript" src="admin/assets/js/jquery-3.6.0.min.js"></script>
</head>


<body class="theme-color-v2">

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
<section class="language-block text-right">
		<!-- <div class="container-fluid">
			
				<ul class="list-unstyled list-inline custom-m-line lang-custom-m-line">
					
					<li class="list-inline-item"><a href="nchlandinghindi">हिन्दी</a></li>
					<li class="list-inline-item"><a href="nchlanding">English</a></li>
				</ul>
			
		</div> -->
		 <div class="container">
		<div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
    Language
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
    <li><a class="dropdown-item" href="nchsignuphindi">हिन्दी</a></li>
    <li><a class="dropdown-item" href="nchsignup">English</a></li>
   
  </ul>
</div>
</div>
	</section>

		<header>
		<nav
			class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
			<div class="container">
				<div class="logo-main">
					<a href="landingpage"> <img src="admin/assets/img/ayush-grid.png"
						class="img-fluid"> <span class="logo-subtext"><img
							alt="moa-logo" src="admin/assets/img/moa-icon.jpg" class="moa-logo">Ministry
							of AYUSH</span>
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
							href="nchlanding">Home</a></li>

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
							
								  <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Ayush System</a>
                        <div class="dropdown-menu">
                           <a class="dropdown-item" href="nchayurveda">Ayurveda</a> 
                           <a class="dropdown-item" href="nchunani">Unani</a>
                           <a class="dropdown-item" href="nchsiddha">Siddha</a> 
                           <a class="dropdown-item" href="nchsowa">Sowa-Rigpa</a> 
                           <a class="dropdown-item" href="nchhomoeopathy">Homoeopathy</a>
                        </div>
                     </li>
							
                            <li class="nav-item"><a class="nav-link" href="aboutnch">About NCH</a></li>
					<!-- 	<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">About NCH</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="aboutncism">NCISM</a> <a
									class="dropdown-item" href="aboutnch">NCH</a> <a
									class="dropdown-item" href="aboutmoa">Ministry of
									Ayush(MOA)</a>
							</div></li>  -->
							
							  <li class="nav-item"><a class="nav-link" href="nchcontact">Contact Us</a></li>




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
									class="dropdown-item" href="contactus">Contact US</a> <a
									class="dropdown-item" href="feedback">Feedback</a>
							</div></li> -->

					</ul>
				</div>
				<div class="custom-btn">
					<div class="authentication-detail">

					<a href="nchsignin" class="btn btn-bh">Practitioner SignIn</a> 
						<a href="nchsignup"   class="btn btn-bh">SignIn</a>

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
		<div class="form-main-block">
			<div class="row justify-content-center">
			
			<div class="col-lg-5 col-md-12 col-sm-12">
						<div class="area form-bg">
							<div class="form-intro white-text">
								<div class="text-welcome">
									<span class="img-intro"><img
										src="admin/assets/img/nchlogo.png"></span>
									<h4 class="intro-title">Welcome to NCH Institute SignUp</h4>
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

<form:form action="institute_registration_nch_action"
							method='POST' modelAttribute="inst_reg_nch_CMD"
							enctype="multipart/form-data">

								<div class="row">
									<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">Institute Name<span class="mandatory">*</span></label>
														<input id="institute_name" name="institute_name" class="form-control form-control-lg form-control-a effect-9" autocomplete="off" maxlength="50" 
															onkeypress="return onlyAlphabetsStringSpace(event,this)" placeholder="Enter Institute Name">
														
												</div>
											</div>	
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">Email Id<span class="mandatory">*</span></label>
														<input id="institute_email" name="institute_email" class="form-control form-control-lg form-control-a effect-9" autocomplete="off" placeholder="abc@example.com" 
								                               maxlength="50" onchange="checkgmail(this.value)" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" value="${email_txt}">

												</div>
											</div>	
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">Mobile No.<span class="mandatory">*</span></label>
														<input id="institute_mob_no" name="institute_mob_no" class="form-control form-control-lg form-control-a effect-9" autocomplete="off" maxlength="10" 
															minlength="10" onkeypress="return isNumberKey0to9(event, this);" onchange="return mobileNumber(this)" placeholder="Enter Mobile No">
														

												</div>
											</div>	
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">University<span class="mandatory">*</span></label>
														
														<select name="university_id" id="university_id"
														class="form-control form-control-lg form-control-a disablecopypaste">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getUniversity_nchList}" varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>	
														

												</div>
											</div>
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">Country<span class="mandatory">*</span></label>
<!-- 													onchange="getState();" -->
													<select name="country_id" id="country_id"
														
														class=" form-control form-control-lg form-control-a effect-9 select2 narrow wrap">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${MedCountryName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>	
														<input type="hidden" id="id" name="id" value="0"
															class="form-control form-control-lg form-control-a effect-9" autocomplete="off" />

												</div>
											</div>
											onchange="getDistrict();
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
												<div class="form-group">
														<label class=" form-control-label">State<span class="mandatory">*</span></label>
													
													<select name="state_id" id="state_id"
														class="form-control form-control-lg form-control-a effect-9 select2 narrow wrap"
														">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${MedStateName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>	
														

												</div>
											</div>
											
												<div class="col-lg-6 col-md-6 col-sm-12 mb-3 mt-4">
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
														<input type="hidden" id="id" name="id" value="0"
															class="form-control form-control-lg form-control-a effect-9" autocomplete="off" />

												</div>
											</div>
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3 mt-4">
												<div class="form-group">
														<label class=" form-control-label">Code<span class="mandatory">*</span></label>
														<input id="code" name="code" class="form-control form-control-lg form-control-a effect-9" autocomplete="off" value="" maxlength="50" placeholder="Enter Code">
														
														

												</div>
											</div>
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3 mt-1">
												<div class="form-group">
														<label class=" form-control-label">Address<span class="mandatory">*</span></label>
														<textarea id="address" name="address" rows="3" cols="50"  class="form-control form-control-lg form-control-a effect-9" autocomplete="off"></textarea>
                                                       <input type="hidden" id="id" name="id" value="0"
															class="form-control form-control-lg form-control-a effect-9" autocomplete="off" />
												</div>
											</div>
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3 mt-1">
												<div class="form-group">
														<label class=" form-control-label">Upload Image<span class="mandatory">*</span></label>
                                                        <input type="file" accept=".jpeg,.png" id="upload_image" name="upload_image" class="form-control form-control-lg form-control-a effect-9">
												</div>
											</div>	
											
											<div class="col-lg-6 col-md-6 col-sm-12 mb-3" style="display: none;">
												<div class="form-group">
													
														<label class=" form-control-label" for="username">STATUS<span class="mandatory">*</span></label>
													<select name="status" id="status" class="form-control form-control-lg form-control-a effect-9 select2 narrow wrap">
															<c:forEach var="item" items="${ActiveInActiveList}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
														<input type="hidden" id="id" name="id" value="0"
																class="form-control form-control-lg form-control-a effect-9" autocomplete="off" />
													
												</div>
											</div>
											
											<div class="col-lg-12 col-md-12 col-sm-12 mb-3 justify-content-center d-flex">
										<div class="custom-btn footer-btn">
											
                                               <ul class="footer-btn-list">
													<li class="f-btn"><a href="nchsignin" class="link-color">Sign in instead</a></li>
													<li class="f-btn"><input type="submit" class="btn btn-bh" id="save_btn" value="Sign Up" onclick="return Validation();"></li>													
												</ul>	
										</div>
									</div>
											
								</div>
								
								<input type="hidden" id="hidden_id" name="id"
									class="form-control form-control-lg form-control-a effect-9">

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
               <div class="col-lg-6 col-md-6 col-sm-12">
                  <div class="widget-a">
                     <div class="w-header-a mb-2">
                        <a href="#"><img alt="moa-logo"
                           src="admin/assets/img/ayushlogo.png"></a>
                     </div>
                     <div class="w-body-a">
                        <p class="w-text-a color-text-a">Jawahar Lal Nehru Bhartiya
                           Chikitsa Avam Homoeopathy Anusandhan Bhawan, 61-65,
                           Institutional Area, Janakpuri "D" Block, New Delhi-110058
                        </p>
                     </div>
                     <div class="w-footer-a">
                        <ul class="list-unstyled">
                           <li class="color-a"><span class="color-text-a">Phone
                              :</span> <a href="tel:1800-11-0180,1964">1800-11-0180,1964</a>
                           </li>
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
                        <h3 class="w-title-a text-brand">Ayush Grid</h3>
                     </div>
                     <div class="w-body-a">
                        <ul class="list-unstyled">
                           <li class="item-list-a"><i class="bi bi-chevron-right"></i>
                              <a href="#">Website Policy</a>
                           </li>
                           <li class="item-list-a"><i class="bi bi-chevron-right"></i>
                              <a href="#">Disclaimer</a>
                           </li>                           
                           <li class="item-list-a"><i class="bi bi-chevron-right"></i>
                              <a href="#">Privacy Policy</a>
                           </li>
                           <li class="item-list-a"><i class="bi bi-chevron-right"></i>
                              <a href="contactus">Contact Us</a>
                           </li>
                        </ul>
                     </div>
                  </div>
               </div>
               <div class="col-lg-2 col-md-2 col-sm-12 section-md-t3">
                  <div class="w-header-a">
                     <h3 class="w-title-a text-brand">Connect With Us</h3>
                  </div>
                  <div class="socials-a">
                     <ul class="list-inline">
                        <li class="list-inline-item">
                        	<a href="#" class="fb-icon hvr-bounce-in"><i class="bi bi-facebook" aria-hidden="true"></i></a>
                        </li>
                        <li class="list-inline-item">
                        	<a href="#" class="tw-icon hvr-bounce-in"><i class="bi bi-twitter" aria-hidden="true"></i></a>                        	
                        </li>
                        <li class="list-inline-item">
                        	<a href="#" class="insta-icon hvr-bounce-in"><i class="bi bi-instagram" aria-hidden="true"></i></a>                        
                        </li>
                        <li class="list-inline-item">
                        	<a href="#" class="ld-icon hvr-bounce-in"><i class="bi bi-linkedin" aria-hidden="true"></i></a>                        
                        </li>
                     </ul>
                  </div>
               </div>
               <div class="col-lg-2 col-md-2 col-sm-12">
                  <div class="logo-right">
                     <a href="#"><img src="admin/assets/img/ayush-grid.png"
                        class="img-fluid"></a>
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

</body>

</html>
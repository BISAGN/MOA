<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
  <link href="admin/assets/vendor/page-fontsize/page_fontsize.css" rel="stylesheet">
  <style>

 </style>
</head>

<body class="theme-color-v3">
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
      
      <div class="language-block text-right">
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
    <li><a class="dropdown-item" href="collaborate">हिन्दी</a></li>
    <li><a class="dropdown-item" href="collaborate">English</a></li>
   
  </ul>
</div>
</div>
	</div>
         <nav
            class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
            <div class="container">
               <div class="logo-main">
                  <a href="landingpage">
                     <img src="admin/assets/img/grid-logo.png"
                        class="img-fluid">
                  </a>
                  <span class="logo-subtext d-none"><img alt="moa-logo" src="admin/assets/img/moa-icon.jpg" class="moa-logo">Ministry of <span class="org-text">Ayush</span></span>
               </div>
               <button class="navbar-toggler collapsed" type="button"
                  data-bs-toggle="collapse" data-bs-target="#navbarDefault"
                  aria-controls="navbarDefault" aria-expanded="false"
                  aria-label="Toggle navigation">
               <span></span> <span></span> <span></span>
               </button>
               <!-- navigation start here -->				
                <div class="navbar-collapse collapse justify-content-center" id="navbarDefault">
                	<div class="container">				
						<ul class="navbar-nav justify-content-center">
							<li class="nav-item"><a class="nav-link active"
								href="landingpage">Home</a></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">Ayush System</a>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="ayurveda">Ayurveda</a> <a
										class="dropdown-item" href="unani">Unani</a> <a
										class="dropdown-item" href="siddha">Siddha</a> <a
										class="dropdown-item" href="sowa">Sowa-Rigpa</a> <a
										class="dropdown-item" href="homoeopathy">Homoeopathy</a>
								</div></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">About</a>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="aboutmoa">Ministry of
										Ayush(MOA)</a> <a class="dropdown-item" href="ncismabout">NCISM</a>
									<a class="dropdown-item" href="nchabout">NCH</a>
								</div></li>
							<li class="nav-item"><a class="nav-link" href="contactus">Contact US</a></li>
						</ul>
					</div>				
				</div>
               <div class="logo-main1">
                  <a href="landingpage"> <img src="admin/assets/img/ayushlogo.png"
                     class="img-fluid">
                  </a>
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
              <h1 class="title-single">Collaborate</h1>
           <!--    <span class="color-text-a">Your message has been sent. Thank you! </span> -->
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="landingpage">Home</a>
                </li>
                <li class="breadcrumb-item active" aria-current="page">
                  Collaborate
                </li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
    </section>
    <!-- End Intro Single-->

    <!--  Contact Single -->
	<section class="collaborate">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<form action="" method="post" role="form" class="php-email-form">
						<div class="card-style mb-30" id="card_view">
							<div class="row">
							
							<div class="col-lg-5 col-md-12 col-sm-12 text-center animate__animated animate__fadeInLeft">
					<div class="about-image-area">
						<div class="about-image js-tilt">
							<img src="admin/assets/img/collaboration.jpg" alt="about-image" class="img-fluid">
						</div>								
						<!-- Animation Shape Start -->
						<div class="shape shape-1 scene">
						    <span data-depth="1"><img src="admin/assets/img/about-shape-1.png"  alt=""></span>
						</div>
						<!-- Animation Shape End -->
					</div>							
				</div>
				
				<div class="col-lg-7 col-md-12 col-sm-12 animate__animated animate__fadeInRight">
                                <div class="row auto-fill-form">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="collabratehead">
                                <h2>International <span class="collatext">(Collaboration Type)</span></h2>
                                </div>
                                 </div>
                                  <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                                  <div class="collabratetitle">
                                 <h4>Title <span class="collatext">(Title Name)</span></h4>
                                 </div>
                                 <span class="line"></span>
                                 </div>
                              <!--    
                                 <div class="line_design">
				<h4 class="line_text">Student Details </h4>		
				
			 </div> -->
										<!-- <div class="col-lg-4 col-md-6 col-sm-6 col-12">
										<div class="select-style-2">
										<label for="username">collaborate<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="" id="">
											       <option value="0">Select</option>
													<option value="1" name="International">International</option>
													<option value="2" name="National">National</option>
											</select>
										</div>
									</div>
										</div> -->
										<!-- 
                                         <div class="col-lg-4 col-md-6 col-sm-6 col-12">
										<div class="select-style-2">
										<label for="username">Sector<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="" id="">
											       <option value="0">Select</option>
													<option value="1" name="">Public</option>
													<option value="2" name="">Private</option>
													<option value="3" name="">Government</option>
													<option value="4" name="">Semi Government</option>
													<option value="5" name="">State Government</option>
													<option value="6" name="">Central Government</option>
											</select>
										</div>
									</div>
										</div> -->
										
										<!-- <div class="col-lg-4 col-md-6 col-sm-6 col-12">
										<div class="select-style-2">
										<label for="username">Categories<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="" id="">
											       <option value="0">Select</option>
													<option value="1" name="">Educational</option>
													<option value="2" name="">Social</option>
													<option value="3" name="">Private</option>
											</select>
										</div>
									</div>
										</div> -->
										
<!-- 									</div> -->
<!-- 								<div class="row auto-fill-form"> -->

								<div class="col-lg-3 col-md-6 col-sm-6 col-12">
									<div class="input-with-value">
										<label>Sector</label> <span class="auto-fill-value"
											id="">Government</span>
									</div>
								</div>
								<div class="col-lg-3 col-md-6 col-sm-6 col-12">
									<div class="input-with-value">
										<label>Categories</label> <span class="auto-fill-value"
											id="">Educational</span>
									</div>
								</div>
								
								<!-- <div class="col-lg-4 col-md-6 col-sm-6 col-12">
									<div class="input-with-value">
										<label>Title</label> <span class="auto-fill-value"
											id="">Title Name</span>
									</div>
								</div> -->

								<div class="col-lg-3 col-md-6 col-sm-6 col-12">
									<div class="input-with-value">
										<label>From Date</label> <span class="auto-fill-value"
											id="">10-08-2022</span> 
									</div>
								</div>
								<div class="col-lg-3 col-md-6 col-sm-6 col-12">
									<div class="input-with-value">
										<label>End Date</label> <span class="auto-fill-value"
											id="">20-08-2022</span> 									
								   </div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="input-with-value">
										<label>Description</label> 
<!-- 										<textarea class="auto-fill-value" rows="3" id="comment" name="text"></textarea> -->
										<span class="auto-fill-value address" id="">collaboration in the workplace is working together with one or more people to complete a project or task or develop ideas or processes.</span> 
									</div>
								</div>
								
								</div>
								
								</div>
							
								
								
     
							</div>
						</div>
						<!-- end card -->
					</form>
				</div>
				
			</div>
		</div>
	</section>

	<!-- End Contact Single-->

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
									<a href="contactus">Contact Us</a></li>
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
  
</body>

</html>
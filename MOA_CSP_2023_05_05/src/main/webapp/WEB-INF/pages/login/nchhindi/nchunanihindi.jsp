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
  <link href="admin/assets/css/custom_pages_style.css" rel="stylesheet">
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
  

		<!-- Language end-->
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
    <li><a class="dropdown-item" href="nchunanihindi">हिन्दी</a></li>
    <li><a class="dropdown-item" href="nchunani">English</a></li>
   
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
यूनानी</h1>
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="nchlandinghindi">मुख पृष्ठ</a>
                </li>
                <li class="breadcrumb-item active" aria-current="page">
                  
यूनानी
                </li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
    </section>
    <!-- End Intro Single-->
    
     <!-- welcome section start -->
      <section class="section-about about-img-box-sm">
         <div class="row m-0">
            <div class="col-lg-12 col-md-12 col-sm-12 p-0">
               <div class="about-img-box"
                  style="background: url('admin/assets/img/unani.jpg') no-repeat;">
                  <!-- <img src="admin/assets/img/aurveda.jpg" alt="" class="img-fluid"> -->
                  <div class="innerpage-imgbox">
	                  <div class="container">                  
		                  <div class="sinse-box">
		                     <h5 class="sinse-title">यूनानी चिकित्सा पद्धति एक महान उपचार कला के साथ-साथ विज्ञान भी है।</h5>		                   
		                  </div>
	                  </div>
                  </div>                  
               </div>
            </div>
         </div>
      </section>
      <!-- welcome section end -->

    <!--  About Section start-->
    <section class="section-about">
      <div class="container">
        <div class="row">
          <!-- <div class="col-sm-12 position-relative">
            <div class="about-img-box">
              <img src="admin/assets/img/unani.jpg" alt="" class="img-fluid">
            </div>
            <div class="sinse-box">
              <h3 class="sinse-title">आयुष ग्रिड</h3>
            </div>
          </div> -->
          <div class="col-md-12 pt-5">
            <div class="row">
              <!-- <div class="col-md-6 col-lg-5">
                <img src="admin/assets/img/about1.jpg" alt="" class="img-fluid">
              </div> -->
             <div class="col-md-12 col-lg-12 section-md-t3">
                <div class="title-box-d">
                  <h3 class="title-d">
यूनानी का परिचय</h3>
                </div>
                <p class="color-text-a">
         नानी या स्वास्थ्य और उपचार का विज्ञान चिकित्सा की एक प्रणाली है जिसने भारत की परंपरा के साथ मिश्रित किया है और इसे इस प्रकार की दवा का अभ्यास करने वाले अग्रणी देशों में से एक बना दिया है। यूनानी चिकित्सा पद्धति की उत्पत्ति लगभग 2500 साल पहले ग्रीस में हुई थी और इसकी नींव हिप्पोक्रेट्स ने रखी थी। ग्रीको-अरबी चिकित्सा की एक परंपरा, यूनानी चिकित्सा में एक जड़ी-बूटी-एनिमो-खनिज आधार है। चिकित्सा का एक मूल विज्ञान होने के अलावा, यूनानी दर्शन और चिकित्सा के सिद्धांतों का एक समृद्ध भंडार भी है जो सामान्य रूप से चिकित्सा और विज्ञान के क्षेत्र में मूल्य रखता है।</p>
			  <p class="color-text-a">अरब वे हैं जिन्होंने अपने योगदान के माध्यम से दवा के यूनानी साहित्य को अपने स्वयं की प्रणाली में बढ़ावा प्रदान किया। उसी के लिए, उन्होंने पैथोलॉजी, थेरेप्यूटिक्स, भौतिकी, रसायन विज्ञान, एनाटॉमी, फिजियोलॉजी, वनस्पति विज्ञान और सर्जरी की अवधारणाओं को शामिल किया। चिकित्सा की यूनानी प्रणाली को मध्य पूर्व के देशों और एशियाई देशों द्वारा पारंपरिक प्रणाली के समकालीन रूप के कारण समृद्ध किया गया था। अरबों ने भारत के लिए यूनानी प्रणाली की शुरुआत की जिसने कुछ ही समय में मजबूत जड़ें हासिल कर लीं। दिल्ली के सुल्तानों ने संरक्षण दिया यूनानी विद्वानों ने उनमें से कुछ को अदालत के चिकित्सकों और राज्य कर्मचारियों के रूप में काम पर रखा था।</p>
			  <p class="color-text-a">हालांकि, एलोपैथिक प्रणाली की शुरुआत के साथ ब्रिटिश शासन के दौरान प्रणाली को एक बड़ा झटका लगा। इसने अपनी शिक्षा, अभ्यास और अनुसंधान के संबंध में, यूनानी चिकित्सा पद्धति के पतन का परिणाम दिया। लगभग दो शताब्दियों के लिए, इस प्रणाली की प्रभावशीलता को चिकित्सा के अन्य पारंपरिक रूपों की तरह, उपेक्षा का सामना करना पड़ा। Iयह हैदराबाद के निजाम का प्रयास था, लखनऊ में अजीज़ी परिवार और दिल्ली में शरीफ़ परिवार; ब्रिटिश काल में यूनानी चिकित्सा बच गई। इस प्रणाली ने हकीम अजमल खान के साथ स्वतंत्रता संग्राम के दौरान अपने पुनरुद्धार को देखा, जिन्होंने वर्ष 1916 में एक आयुर्वेदिक और यूनानी तिब्बिया कॉलेज और हिंदुस्तानी दावखाना की स्थापना की। इसका उद्घाटन महात्मा गांधी द्वारा 13 फरवरी, 1921 को किया गया था और यूनानी प्रणाली को विभिन्न रियासतों द्वारा संरक्षण दिया गया था। स्वतंत्रता के बाद, यूनानी प्रणाली ने पारंपरिक चिकित्सा के अन्य रूपों के साथ, एक बढ़ावा देखा। भारत सरकार ने इस प्रणाली के सर्वांगीण विकास के लिए कई उपाय किए और विभिन्न शोध संस्थानों, शैक्षणिक संस्थानों, परीक्षण प्रयोगशालाओं और अस्पतालों की स्थापना की और मान्यता प्राप्त चिकित्सकों को नियुक्त किया। वर्तमान में, भारत उन अग्रणी देशों में से एक है जो यूनानी चिकित्सा पद्धति का अभ्यास करते हैं और उन संस्थानों की सबसे बड़ी संख्या है जो यूनानी शिक्षा, अनुसंधान और स्वास्थ्य सेवा पर ध्यान केंद्रित करते हैं। निरंतर उपेक्षा का सामना करने के बावजूद, चिकित्सा की यह कला न केवल बच गई है, बल्कि यह चिकित्सा की अन्य प्रणालियों को भी पूरी तरह से पूरक करती है। यूनानी चिकित्सा पद्धति संपूर्ण मानव शरीर के रोगों का उपचार करती है। यकृत, त्वचा, प्रजनन प्रणाली, मस्कुलोस्केलेटल और प्रतिरक्षा संबंधी विकारों के पुराने रोगों और बीमारियों के इलाज के लिए यूनानी अत्यधिक प्रभावी साबित हुआ है।</p>

              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
     <!--  About Section end-->

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
  
</body>

</html>
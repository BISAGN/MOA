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
<link href="admin/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
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
    <li><a class="dropdown-item" href="ayushknowledgenchhindi">हिन्दी</a></li>
    <li><a class="dropdown-item" href="ayushknowledgench">English</a></li>
   
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
आयुष ज्ञान</h1>
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="nchlandinghindi">मुख पृष्ठ</a></li>
							<li class="breadcrumb-item active" aria-current="page">
								
आयुष ज्ञान</li>
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
                  style="background: url('admin/assets/img/ayushknowledge.jpg') no-repeat;">
                  <!-- <img src="admin/assets/img/aurveda.jpg" alt="" class="img-fluid"> -->
                  <div class="innerpage-imgbox">
	                  <div class="container">                  
		                  <div class="sinse-box">
		                     <h5 class="sinse-title">आयुष ज्ञान</h5>		                   
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
						<img src="admin/assets/img/banner-3.png" alt="" class="img-fluid">
					</div>
					<div class="sinse-box">
						<h3 class="sinse-title">
आयुष ग्रिड</h3>
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
आयुष ज्ञान के बारे में</h3>
							</div>
							<p class="color-text-a">आयुष आयुर्वेद, योग और प्राकृतिक चिकित्सा, यूनानी, सिद्ध और होम्योपैथी जैसे भारत में प्रचलित चिकित्सा प्रणालियों का संक्षिप्त रूप है। ये प्रणालियाँ निश्चित चिकित्सा दर्शन पर आधारित हैं और बीमारियों की रोकथाम और स्वास्थ्य को बढ़ावा देने पर स्थापित अवधारणाओं के साथ स्वस्थ जीवन जीने के तरीके का प्रतिनिधित्व करती हैं। स्वास्थ्य, रोग और उपचार पर इन सभी प्रणालियों का मूल दृष्टिकोण समग्र है। इस वजह से, आयुष प्रणालियों पर रुचि का पुनरुत्थान हो रहा है। योग अब वैश्विक स्वास्थ्य का प्रतीक बन गया है और कई देशों ने इसे अपने स्वास्थ्य देखभाल वितरण प्रणाली में एकीकृत करना शुरू कर दिया है। इसी तरह आयुर्वेद, होम्योपैथी, सिद्ध और यूनानी के सिद्धांतों और अभ्यास को समझने की बहुत उत्सुकता है, विशेष रूप से गैर संचारी रोगों (एनसीडी) में चिकित्सा में बढ़ती चुनौतियों, जीवन शैली विकारों, दीर्घकालिक रोगों, बहु दवा प्रतिरोधी रोगों, नए के उद्भव के कारण। रोग आदि। 1995 में, इन प्रणालियों के इष्टतम और केंद्रित विकास के उद्देश्य से, केंद्रीय स्वास्थ्य और परिवार कल्याण मंत्रालय में भारतीय चिकित्सा और होम्योपैथी विभाग (ISM & H) बनाया गया था। 2003 में इस विभाग का नाम बदलकर आयुष विभाग कर दिया गया।</p>
							<p class="color-text-a">भारत में आयुर्वेद के रूप में प्रचलित वेदों से प्राप्त चिकित्सा ज्ञान की एक समृद्ध विरासत है। इसे प्राचीन संतों (ऋषि) द्वारा सिद्ध प्रणाली और योग प्रथाओं के साथ और समृद्ध किया गया था। ये सदियों से देश की प्रमुख चिकित्सा पद्धतियां थीं, जो भारतीय लोकाचार और संस्कृति का हिस्सा थीं। यूनानी तिब्ब जिसे हिप्पोक्रेट्स की अवधि से जाना जाता था, 8 वीं शताब्दी ईस्वी के दौरान भारत आया था। बाद में, पश्चिमी चिकित्सा जो जैव चिकित्सा अवधारणाओं पर आधारित है, जिसे आमतौर पर एलोपैथी के रूप में जाना जाता है, भारत आया और भारतीय चिकित्सा पद्धति में भी शामिल हो गया। 18 वीं शताब्दी में जर्मनी में विकसित एक प्रणाली होम्योपैथी ने तेजी से लोकप्रियता हासिल की और अपने समग्र चिकित्सा दर्शन और सिद्धांतों में समानता के कारण भारतीय पारंपरिक चिकित्सा पद्धतियों के साथ मिश्रित हो गई। प्राकृतिक चिकित्सा, सभी सभ्यताओं की स्वस्थ जीवन पद्धतियों की एक दवा रहित प्रणाली संगठित हो गई और देश के चिकित्सा बहुलवाद का भी हिस्सा बन गई। इस प्रकार, जैव चिकित्सा के साथ-साथ पारंपरिक प्रथाओं के साथ एक अद्वितीय चिकित्सा प्रतिमान विकसित हुआ। स्वतंत्रता के बाद, सरकार ने उनके विकास के लिए सभी चिकित्सा प्रणालियों का समर्थन करना शुरू कर दिया, जिससे जनता को उनकी नियमित स्वास्थ्य देखभाल की आवश्यकता के लिए एक विकल्प प्रदान किया गया। इस वजह से अब स्वास्थ्य देखभाल के उपचारात्मक, निवारक, प्रोत्साहक पहलुओं में इन प्रणालियों की ताकत को बढ़ाने के लिए सार्वजनिक संरक्षण और संस्थागत समर्थन मिल रहा है।</p>
							<p class="color-text-a">प्रयोगात्मक अवधारणाओं पर आधारित जैव चिकित्सा ने निरंतर अनुसंधान और अद्यतन ज्ञान के साथ नवाचार लाया है। इसके कारण रोगों के कारण, उनके पाठ्यक्रम, निदान, निदान, रोगों के प्रबंधन आदि के बारे में उल्लेखनीय जानकारी है। अधिकांश संक्रामक रोग जो उच्च मृत्यु दर का प्रमुख कारण थे, अब उन पर विजय प्राप्त कर ली गई है। उच्च जोखिम वाले मामलों के प्रबंधन, सर्जिकल हस्तक्षेप आदि ने स्वास्थ्य क्षेत्र में चमत्कार किया है। हालांकि, गैर संचारी रोगों की घटनाओं में वृद्धि हुई है। आयुष दवाएं जो अनुभवी दवाओं की श्रेणी में आती हैं, लागत प्रभावी हैं, सुरक्षा मुद्दों और समय परीक्षण के लिए जानी जाती हैं। आयुष दवाओं का व्यापक रूप से एक स्टैंडअलोन विकल्प के रूप में या दीर्घकालिक रोगों में जैव चिकित्सा के साथ सहायक के रूप में उपयोग किया जाता है। इसलिए स्वास्थ्य प्रतिमान में इस बदलाव के कारण आयुष की प्रासंगिकता अब और अधिक हो गई है। इस तथ्य को ध्यान में रखते हुए सरकार स्वास्थ्य देखभाल में एक बहुलवादी दृष्टिकोण को प्रोत्साहित कर रही है जहां हर चिकित्सा प्रणाली को अपनी स्पष्ट ताकत के आधार पर बढ़ने की अनुमति है।</p>
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
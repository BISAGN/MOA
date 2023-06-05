<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <c:if test="${langugae == 'english'}">      
	<header>
	<!--  Top search section start -->
	<div class="click-closed"></div>
	<!-- Form Search Star /-->
	<div class="box-collapse">
		<div class="title-box-d">
			<h3 class="title-d">Search Now</h3>
		</div>
		<span class="close-box-collapse right-boxed bi bi-x"></span>
		<div class="box-collapse-wrap form">
			<form class="form-a">
				<div class="row">
					<div class="col-md-12">
					<div class="input-style-1">
							<label>Enter Keyword</label><input type="text" placeholder="Enter Keyword here">
						</div>
					</div>
					<div class="col-md-12">				
						<button type="submit" class="detail-button mt-2"><i class="bi bi-search"></i> Search</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--  Top search section end -->
		<!-- topbar start-->
		<div class="top-block">
			<div class="container">
				<div class="head-top d-flex">
					<div class="head-top-left">
						<div class="head-top-logo">
							<a href="https://india.gov.in" title="Government of India" target="blank" class="logo-subtext">
								<img alt="Government of India" data-src="admin/assets/img/government_of_india.png" class="lazy">
								<span class="logo-sidetext">भारत सरकार | Government of India</span>
							</a>
						</div>
						<div id="chcp_font_size" class="page-fontsize">
							<button id="btn-decrease" class="btn btn-default" type="button" title="Decrease font size">A-</button>
							<button id="btn-orig" class="btn btn-default" type="button" title="Reset font size">A</button>
							<button id="btn-increase" class="btn btn-default" type="button" title="Increase font size">A+</button>
						</div>						
						<div class="custom-search">
							<div class="search-box">
								<button type="button" class="detail-button detail-button-iconic sm-btn hvr-icon-pulse-shrink searchicon"
									data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" title="Click for search">
									<i class="bi bi-search hvr-icon"></i>
								</button>
								<div class="serach-textbox">
									<input type="search" id="search_box" name="search" placeholder="Search here..." class="form-control form-control-sm">
									<div class="searchclear" id="clear_button" title="Clear search">
										<i class="bi bi-x-lg clear-btn"></i>
									</div>
																		
								</div>														
							</div>							
						</div>
					</div>
					<div class="head-top-right">
					    <div class="head-top-link">
					    <ul class="d-flex link-list">
					    <li><a href="screenreaderaccess">								
								Screen Reader Access
							</a></li>
					     <li><a href="#page-content">								
								Skip to main content
							</a></li>
					    </ul>							
						</div>
												 
						<!-- theme switch mode start -->
						<div class="switch-mode">
							<ul class="switch-btn-list">
								<li class="switch-btn"><button type="button" id="light"
										class="sbtn light-btn"  title="Light Mode">
								<i class="bi bi-brightness-high-fill"></i>
							</button></li>
						<li class="switch-btn"><button type="button" id="dark"
								class="sbtn dark-btn" title="Dark Mode">
							<i class="bi bi-moon-stars-fill"></i>
						</button></li>
						<!-- event -->
							</ul>
						</div>
						<!-- theme switch mode end -->
						<div id="google_translate_element" class="custom-lang" title="Change Language"></div>						
						<div class="dropdown custom-lang2">
							<button class="btn btn-secondary dropdown-toggle" type="button"
								id="dropdownMenuButton1" data-bs-toggle="dropdown"
								aria-expanded="false">Language</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
								<li><a class="dropdown-item" href="landingpagehindi">हिन्दी</a></li>
								<li><a class="dropdown-item" href="landingpage">English</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- topbar end-->
		<nav
			class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
			<div class="container">
				<div class="header-inner">
					<div class="logo-main">
						<a href="https://main.ayush.gov.in/" target="_blank" title="Ministry of Ayush">
							<img data-src="admin/assets/img/ayushlogo.png" class="img-fluid lazy" alt="Ministry of Ayush">
						</a>
						<a href="landingpage" title="Go to Home" class="logo-subtext d-none">
							<img alt="Ayush Grid" data-src="admin/assets/img/ayushgridleaf.png" class="moa-logo lazy">Ayush Grid
						</a>						
						<!-- <a href="https://main.ayush.gov.in/" target="_blank" title="Ministry of Ayush" class="logo-subtext d-none">
							<img alt="Ministry of Ayush" src="admin/assets/img/emblem.png" class="moa-logo">Ministry of <span class="org-text">Ayush</span>
						</a> -->
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
							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link active"
									href="landingpage"><i class="bi bi-house-door"></i></a></li>
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
<!-- 										<a class="dropdown-item" href="usermanual">User Manual</a> -->
									</div></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-bs-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">Courses</a>
<!-- 									<div class="dropdown-menu"> -->
<%-- 										<c:set var="sys" value="" /> --%>
<%-- 										<c:forEach var="item" items="${degreelist}" varStatus="num"> --%>
<%-- 											<c:if test="${item[0] != sys}"> --%>
<%-- 												<c:set var="sys" value="${item[0]}" /> --%>
<%-- 												<a class="dropdown-item menu-title" href="#">${item[0]}</a> --%>
<%-- 											</c:if> --%>
<%-- 											<c:if test="${item[0] == sys}"> --%>
<%-- 												<a class="dropdown-item" href="portalsignin">${item[2]}</a> --%>
<%-- 											</c:if> --%>
<%-- 										</c:forEach> --%>
<!-- 									</div> -->

                                        <div class="dropdown-menu">
										<a class="dropdown-item" href="ayurvedacourse">Ayurveda</a> <a
											class="dropdown-item" href="unanicourse">Unani</a> <a
											class="dropdown-item" href="siddhacourse">Siddha</a> <a
											class="dropdown-item" href="sowacourse">Sowa-Rigpa</a> <a
											class="dropdown-item" href="homoeopathycourse">Homoeopathy</a>
									</div>
									</li>
								<!-- <li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-bs-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">Regulation</a>
									<div class="dropdown-menu">
										<a class="dropdown-item"
											href="https://apps.bisag.co.in/BERISM/landingpage">NCISM</a>
										<a class="dropdown-item" href="portalsignin">NCH</a>
									</div></li> -->
									<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-bs-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">Registration</a>
									<div class="dropdown-menu">
										<a class="dropdown-item"
											href="https://apps.bisag.co.in/BERISM/landingpage">BERISM, NCISM</a>
											<a class="dropdown-item" href="signup_practitionner_Url">NCH Regulation</a>
<!-- 										<a class="dropdown-item" href="Search_NCH_forUser_Url">Rules & Regulations, NCH</a> -->
									</div></li>
<!-- 								<li class="nav-item dropdown"><a -->
<!-- 									class="nav-link dropdown-toggle" href="#" id="navbarDropdown" -->
<!-- 									role="button" data-bs-toggle="dropdown" aria-haspopup="true" -->
<!-- 									aria-expanded="false">Admission</a> -->
<!-- 									<div class="dropdown-menu"> -->
<%-- 										<c:set var="sys" value="" /> --%>
<%-- 										<c:forEach var="item" items="${degreelist}" varStatus="num"> --%>
<%-- 											<c:if test="${item[0] != sys}"> --%>
<%-- 												<c:set var="sys" value="${item[0]}" /> --%>
<%-- 												<a class="dropdown-item menu-title" href="#">${item[0]}</a> --%>
<%-- 											</c:if> --%>
<%-- 											<c:if test="${item[0] == sys}"> --%>
<%-- 												<a class="dropdown-item" href="portalsignin">${item[2]}</a> --%>
<%-- 											</c:if> --%>
<%-- 										</c:forEach> --%>
<!-- 									</div></li> -->

<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-bs-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">Modules</a>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="https://apps.bisag.co.in/API">Open API</a> 
										<a class="dropdown-item" href="https://apps.bisag.co.in/API">Core Registries/Directories</a> 
											<a class="dropdown-item" href="search_placement_Reg_Url">Jobseeker</a>
											<a class="dropdown-item" href="company_signup_Url">Enterprise/Hospital/Other
													Reg. Form</a>
									</div></li>
									
<!-- 								<li class="nav-item dropdown"><a -->
<!-- 									class="nav-link dropdown-toggle" href="#" id="navbarDropdown" -->
<!-- 									role="button" data-bs-toggle="dropdown" aria-haspopup="true" -->
<!-- 									aria-expanded="false">Modules</a> -->
<!-- 									<div class="dropdown-menu max-dropdown"> -->

<!-- 										<div class="multi-col-menu col-m2"> -->
<!-- <!-- 											<a class="dropdown-item" --> 
<!-- <!-- 												href="https://apps.bisag.co.in/AyushCounselling/StudentSignUp">Application --> 
<!-- <!-- 												Management</a> --> 
<!-- 												<a class="dropdown-item" href="portalsignin">Time-table -->
<!-- 												management</a> -->
<!-- <!-- 												 <a class="dropdown-item" --> 
<!-- <!-- 												href="https://apps.bisag.co.in/AyushCounselling/landingpage">Counselling</a> --> 
<!-- 											 <a class="dropdown-item" -->
<!-- 												href="InstituteInformation">Institute Fee Structure</a> <a -->
<!-- 												class="dropdown-item" href="portalsignin">Lecture -->
<!-- 												management</a> <a class="dropdown-item" href="portalsignin">Student -->
<!-- 												management</a> <a class="dropdown-item" href="portalsignin">Examination -->
<!-- 												system</a> <a class="dropdown-item" href="portalsignin">Student -->
<!-- 												Attendance</a> <a class="dropdown-item" href="portalsignin">Staff -->
<!-- 												Attendance</a> <a class="dropdown-item" -->
<!-- 												href="https://apps.bisag.co.in/HMS/hmssignin">Hostel -->
<!-- 												management</a> <a class="dropdown-item" href="portalsignin">Library -->
<!-- 												management</a> <a class="dropdown-item" href="portalsignin">Feedback -->
<!-- 												management</a> <a class="dropdown-item" href="portalsignin">Alumni</a> -->
<!-- 											<a class="dropdown-item" href="portalsignin">Reports -->
<!-- 												management</a> -->
<!-- 											<div class="dropdown-item sub-dropdown-menu"> -->
<!-- 												<a class="dropdown-item" href="https://apps.bisag.co.in/API">Open -->
<!-- 													API</a> <a class="dropdown-item" -->
<!-- 													href="https://apps.bisag.co.in/API">Core Registries</a> <a -->
<!-- 													class="dropdown-item" href="https://apps.bisag.co.in/API">Core -->
<!-- 													Directories</a> <a class="dropdown-item" -->
<!-- 													href="institute_registration_url">Fees</a> -->
<!-- 											</div> -->
<!-- 											<div class="dropdown-item sub-dropdown-menu"> -->
<!-- 												<a class="dropdown-item" href="portalsignin">Placement</a> <a -->
<!-- 													class="dropdown-item" href="search_placement_Reg_Url">- -->
<!-- 													Jobseeker</a> <a class="dropdown-item" -->
<!-- 													href="company_signup_Url">- Enterprise/Hospital/Other -->
<!-- 													Reg. Form</a> -->
<!-- 											</div> -->
<!-- 										</div> -->

<!-- 									</div></li> -->
								<!-- <li class="nav-item"><a class="nav-link"
									href="portalsignin">E-Learning</a></li> -->
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-bs-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">Collaboration System</a>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="ViewCollabration">Collaboration
											with organization</a>
									</div></li>
									
								<li class="nav-item"><a class="nav-link" href="contactus">Contact</a></li>
								<li class="nav-item"><a class="nav-link" href="usermanual_main">User Guide</a></li>
							</ul>
						</div>
					</div>
					<!-- navigation end here -->
					<div class="custom-btn">
						<div class="authentication-detail">
							<a href="portalsignin" class="btn btn-bh"><span
								class="login-text">Sign In</span><span class="login-icon d-none"><i
									class="bi bi-person-lines-fill"></i></span></a> <a
								href="institute_registration_url" class="btn btn-bh"
								data-bs-toggle="modal" data-bs-target="#exampleModal"><span
								class="login-text">Sign Up</span><span class="login-icon d-none"><i
									class="bi bi-person-plus-fill"></i></span></a>									
						</div>
					</div>
					<!-- <div class="custom-btn">
					<div class="authentication-detail">
						<a href="#ayush_system" class="btn btn-bh hvr-bubble-bottom" data-toggle="tooltip" data-placement="bottom" title="SignIn with Ayush System"><span class="login-text">Ayush System</span><span class="login-icon d-none"><i class="bi bi-person-lines-fill"></i></span></a>											
					</div>
				</div> -->
					<div class="logo-main1">
						<a href="landingpage" title="Go to Home"><img alt="Ayush Grid" data-src="admin/assets/img/grid-logo.png" class="img-fluid lazy"></a>
					</div>
				</div>
			</div>
		</nav>
	</header>

</c:if>

<c:if test="${langugae == 'hindi'}">	
	<header>
	<!--  Top search section start -->
	<div class="click-closed"></div>
	<!-- Form Search Star /-->
	<div class="box-collapse">
		<div class="title-box-d">
			<h3 class="title-d">Search Now</h3>
		</div>
		<span class="close-box-collapse right-boxed bi bi-x"></span>
		<div class="box-collapse-wrap form">
			<form class="form-a">
				<div class="row">
					<div class="col-md-12">
					<div class="input-style-1">
							<label>Enter Keyword</label> <input type="text" placeholder="Enter Keyword here">
						</div>
					</div>
					<div class="col-md-12">				
						<button type="submit" class="detail-button mt-2"><i class="bi bi-search"></i> Search</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--  Top search section end -->
		<!-- topbar start-->
		<div class="top-block">
			<div class="container">
				<div class="head-top d-flex">
					<div class="head-top-left">
						<div id="chcp_font_size" class="page-fontsize">
							<button id="btn-decrease" class="btn btn-default" type="button">ए-</button>
							<button id="btn-orig" class="btn btn-default" type="button">ए</button>
							<button id="btn-increase" class="btn btn-default" type="button">ए+</button>
						</div>
						<div class="navbar-toggle-box navbar-toggle-box-collapse custom-search">
							<button type="button" class="detail-button detail-button-iconic sm-btn hvr-icon-pulse-shrink"
								data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01">
								<i class="bi bi-search hvr-icon"></i>
							</button>
						</div>
					</div>
					<div class="head-top-right">
						<!-- theme switch mode start -->
						<div class="switch-mode">
							<ul class="switch-btn-list">
								<li class="switch-btn"><button type="button" id="light"
										class="sbtn light-btn" onclick="toggleLight();">
								<i class="bi bi-brightness-high-fill"></i>
							</button></li>
						<li class="switch-btn"><button type="button" id="dark"
								class="sbtn dark-btn">
							<i class="bi bi-moon-stars-fill"></i>
						</button></li>
						<!-- event -->
							</ul>
						</div>
						<!-- theme switch mode end -->
                        <div id="google_translate_element" class="custom-lang"></div>						
						<div class="dropdown custom-lang2">
							<button class="btn btn-secondary dropdown-toggle" type="button"
								id="dropdownMenuButton1" data-bs-toggle="dropdown"
								aria-expanded="false">भाषा</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
								<li><a class="dropdown-item" href="landingpagehindi">हिन्दी</a></li>
								<li><a class="dropdown-item" href="landingpage">English</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- topbar end-->
		<nav
			class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
			<div class="container">
				<div class="header-inner">
					<div class="logo-main">
						<a href="landingpagehindi"><img data-src="admin/assets/img/grid-logo.png" class="img-fluid lazy"></a> 
						<a href="https://main.ayush.gov.in/" target="_blank" title="Ministry of Ayush" class="logo-subtext d-none">
							<img alt="Ministry of Ayush" data-src="admin/assets/img/emblem.png" class="moa-logo lazy">Ministry of <span class="org-text">Ayush</span>
						</a>
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
							<ul class="navbar-nav">
								<li class="nav-item"><a class="nav-link active"
									href="landingpage"><i class="bi bi-house-door"></i></a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-bs-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">आयुष पद्धति</a>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="ayurvedahindi">आयुर्वेद</a> <a
											class="dropdown-item" href="unanihindi">यूनानी</a> <a
											class="dropdown-item" href="siddhahindi">सिद्ध</a> <a
											class="dropdown-item" href="sowahindi">सोवा-रिग्पा</a> <a
											class="dropdown-item" href="homoeopathyhindi">होम्योपैथी</a>
									</div></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-bs-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">मंत्रालय के बारे में</a>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="MoaPagehindi">आयुष मंत्रालय (एमओए)</a>
								   <a class="dropdown-item" href="abouthindincism">एनसीआईएसएम </a><a
									class="dropdown-item" href="abouthindinch">एनसीएच</a> 
									</div></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
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
									</div></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-bs-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">विनियमन</a>
									<div class="dropdown-menu">
										<a class="dropdown-item"
											href="https://apps.bisag.co.in/BERISM/landingpage">बीईआरआईएसएम,
											एनसीआईएसएम</a> <a class="dropdown-item" href="Search_NCH_forUser_Url">नियम और विनियम, एनसीएच</a>
									</div></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-bs-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">प्रवेश</a>
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
									</div></li>
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
								<li class="nav-item"><a class="nav-link"
									href="portalsignin">ई-लर्निंग</a></li>
								 <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">अन्य</a>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="ViewCollabration">संगठन के साथ सहयोग</a>									
								</div>
						   </li>
						   <li class="nav-item"><a class="nav-link" href="contactpagehindi">संपर्क करें</a></li>
							</ul>
						</div>
					</div>
					<!-- navigation end here -->
					<div class="custom-btn">
						<div class="authentication-detail">
						<a href="portalsignin" class="btn btn-bh"><span
							class="login-text">साइन इन</span><span class="login-icon d-none"><i
								class="bi bi-person-lines-fill"></i></span></a> 
						<a href="institute_registration_url" class="btn btn-bh" data-bs-toggle="modal" data-bs-target="#exampleModal"><span
							class="login-text">साइन अप</span><span class="login-icon d-none"><i
								class="bi bi-person-plus-fill"></i></span></a>
					</div>
					</div>
					
					<div class="logo-main1">
					<a href="landingpage"> 
					<img data-src="admin/assets/img/ayushlogo.png" class="img-fluid lazy">
					</a>
				</div>
				</div>
			</div>
		</nav>
	</header>
</c:if>
<c:url value="searchpageresult" var="searchpageresult" />
<form:form action="${searchpageresult}" method="get"
	id="advance_searchForm" name="advance_searchForm"
	modelAttribute="search_box2">
	<input type="hidden" name="search_box2" id="search_box2" value="0" />
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
$("#search_box").keyup(function(event) {
    if (event.keyCode === 13) {
        $("#search_box2").val($("#search_box").val());
        document.getElementById("advance_searchForm").submit();
    }
});
</script>
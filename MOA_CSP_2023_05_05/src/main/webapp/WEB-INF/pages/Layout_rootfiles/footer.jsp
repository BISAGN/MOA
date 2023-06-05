<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <c:if test="${langugae == 'english'}">
	<footer class="section-footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-5 col-md-6 col-sm-12">
					<div class="widget-a">
						<div class="w-header-a mb-2">
							<a href="https://main.ayush.gov.in/" target="_blank" class="logo-footer-left"><img alt="moa-logo"
								data-src="admin/assets/img/ayushlogo.png" title="Ministry Of Ayush" class="lazy"></a>
						</div>
						<div class="w-body-a">
							<p class="w-text-a">Ayush Bhawan, B Block, GPO Complex, INA, New Delhi – 110023 </p>
						</div>
						<div class="w-footer-a">
							<ul class="list-unstyled">
								<li class="color-a"><span class="color-text-a">Phone
										:</span><a href="tel:1800-11-0180,1964">1800-11-0180,1964</a></li>
								<li class="color-a"><span class="color-text-a">Email
										:</span><a href="mailto:webmanager-ayush@gov.in">webmanager-ayush@gov.in</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-6 col-sm-12 section-md-t3">
					<div class="widget-a">
						<div class="w-header-a">
							<h5 class="w-title-a">Quick Links</h5>
						</div>
						<div class="w-body-a">
						<ul class="list-unstyled">
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="websitepolicies">Website Policies</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="help">Help</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="contactus">Contact Us</a></li>
								<!-- <li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#">FAQ</a></li> -->
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="termsandcondition">Terms and Conditions</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="disclaimer">Disclaimer</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="feedback">Feedback</a></li>	
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="sitemap">Sitemap</a></li> 
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
								<li class="list-inline-item"><a href="https://www.facebook.com/moayush/"
									class="fb-icon hvr-bounce-in" title="Facebook" target="_blank"><i class="bi bi-facebook"
										aria-hidden="true"></i></a></li>
								<li class="list-inline-item"><a href="https://twitter.com/moayush"
									class="tw-icon hvr-bounce-in" title="Twitter" target="_blank"><i class="bi bi-twitter"
										aria-hidden="true"></i></a></li>
								<li class="list-inline-item"><a href="https://www.instagram.com/ministryofayush/"
									class="insta-icon hvr-bounce-in" title="Instagram" target="_blank"><i class="bi bi-instagram"
										aria-hidden="true"></i></a></li>
								<li class="list-inline-item"><a href="https://www.youtube.com/channel/UCqRR2gs-I3zrNcE4so4TpgQ"
									class="yt-icon hvr-bounce-in" title="Youtube" target="_blank"><i class="bi bi-youtube"
										aria-hidden="true"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-6 col-sm-12">
				<div class="footer-logo d-flex">
					<div class="logo-footer-right">
						<a href="landingpage"><img data-src="admin/assets/img/ayush-grid.png"
							class="img-fluid lazy" title="Ayush Grid" alt="Ayush Grid"></a>
					</div>
					<div class="logo-footer-right">
						<a href="https://www.g20.org/en/" target="_blank"><img data-src="admin/assets/img/G20_India_Logo.png"
							class="img-fluid lazy" title="G20 India" alt="G20 India"></a>
					</div>
					</div>
					<div class="footer-logo d-flex">
					<div class="logo-footer-right">
						<a href="https://swachhbharatmission.gov.in/sbmcms/index.htm" target="_blank"><img data-src="admin/assets/img/swach-bharat.png"
							class="img-fluid lazy" title="Swachh Bharat" alt="Swachh Bharat"></a>
					</div>
					<div class="logo-footer-right">
						<a href="https://amritmahotsav.nic.in/" target="_blank"><img data-src="admin/assets/img/amrit-mahotsav.png"
							class="img-fluid lazy" title="Azadi Ka Amrit Mahotsav" alt="Azadi Ka Amrit Mahotsav"></a>
					</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="copyright-footer text-center">					
						<p class="copyright">
							Website content owned by <a
								href="https://main.ayush.gov.in/" class="f-link" target="_blank">Ministry of Ayush, Government of India</a>.
								 Designed, developed and maintained by <a
								href="https://bisag-n.gov.in/" class="f-link" target="_blank">BISAG-N</a>
						<p class="sm-text">Last Update Version : <span class="text-color">[18-Mar-2023-11:00 AM-V1]</span>, VISITORS COUNT: <span class="text-color"><%= session.getAttribute("visiter_count")%></span></p>
					</div>
				</div>
			</div>
		</div>
	</footer>
	
	<!-- Signup (Student & institute) start -->
<div class="modal fade custom-modal custom-model-icon" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true"
		id="exampleModal">
		<div class="modal-dialog modal-md modal-dialog-centered">
			<div class="modal-content text-center">
				<div class="modal-header pb-2">

					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="custom-model-img">
						<img data-src="admin/assets/img/svg/signup-img.gif" alt="signup-img" class="img-fluid lazy">						
					</div>
					<div class="custom-model-content">
						<h2 class="mb-15">Sign Up</h2>
						<p class="text-sm">You can Sign Up from below categories</p>
					</div>

					<ul class="wrapper1">
<!-- 						<li class="wrap-multi-btn"><a -->
<!-- 							href="https://apps.bisag.co.in/AyushCounselling/StudentSignUp" -->
<!-- 							class="animation-btn btn--bolt"> <span class="btn-icon"> -->
<!-- 							<i class="bi bi-person-bounding-box fs-3"></i></span><span -->
<!-- 								class="btn-topFakeBorders"></span> <span -->
<!-- 								class="btn-bottomAnim btn-anim"></span> <span -->
<!-- 								class="btn-sideAnim btn-anim"></span> <span -->
<!-- 								class="btn-topAnim btn-anim"></span> <span class="btn-content">Student</span> -->
<!-- 						</a></li> -->
						<li class="wrap-multi-btn"><a
							href="institute_registration_url" class="animation-btn btn--bolt">
								<span class="btn-icon"><i class="bi bi-buildings-fill fs-3"></i></span>
								<span class="btn-topFakeBorders"></span> <span
								class="btn-bottomAnim btn-anim"></span> <span
								class="btn-sideAnim btn-anim"></span> <span
								class="btn-topAnim btn-anim"></span> <span class="btn-content">College</span>
						</a></li>
						<li class="wrap-multi-btn"><a
							href="AlumniSignup_Url" class="animation-btn btn--bolt">
								<span class="btn-icon"><i class="bi bi-mortarboard-fill fs-3"></i></span>
								<span class="btn-topFakeBorders"></span> <span
								class="btn-bottomAnim btn-anim"></span> <span
								class="btn-sideAnim btn-anim"></span> <span
								class="btn-topAnim btn-anim"></span> <span class="btn-content">Alumni</span>
						</a></li>
						<li class="wrap-multi-btn"><a
							href="signup_practitionner_Url" class="animation-btn btn--bolt">
								<span class="btn-icon"><i class="bi bi-mortarboard-fill fs-3"></i></span>
								<span class="btn-topFakeBorders"></span> <span
								class="btn-bottomAnim btn-anim"></span> <span
								class="btn-sideAnim btn-anim"></span> <span
								class="btn-topAnim btn-anim"></span> <span class="btn-content">Practitioner</span>
						</a></li>
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
<!-- Signup (Student & institute) end -->
</c:if>

 <c:if test="${langugae == 'hindi'}">
	<footer class="section-footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-5 col-md-6 col-sm-12">
					<div class="widget-a">
						<div class="w-header-a mb-2">
							<a href="https://main.ayush.gov.in/" target="_blank"><img alt="moa-logo"
								data-src="admin/assets/img/ayushlogo-w.png" class="lazy"></a>
						</div>
						<div class="w-body-a">
							<p class="w-text-a">जवाहर लाल नेहरू भारतीय चिकित्सा एवं होम्योपैथी अनुसंधान भवन, 61-65, संस्थागत क्षेत्र, जनकपुरी "डी" ब्लॉक, नई दिल्ली-110058</p>
						</div>
						<div class="w-footer-a">
							<ul class="list-unstyled">
								<li class="color-a"><span class="color-text-a">फ़ोन
										:</span><a href="tel:1800-11-0180,1964">1800-11-0180,1964</a></li>
								<li class="color-a"><span class="color-text-a">ईमेल
										:</span><a href="mailto:webmanager-ayush@gov.in">webmanager-ayush@gov.in</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-6 col-sm-12 section-md-t3">
					<div class="widget-a">
						<div class="w-header-a">
							<h5 class="w-title-a">आयुष ग्रिड</h5>
						</div>
						<div class="w-body-a">
							<ul class="list-unstyled">
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#"> वेबसाइट नीतियाँ</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#"> अस्वीकरण</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="#">गोपनीयता नीति</a></li>
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="contactpagehindi"> संपर्क करें</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-12 section-md-t3">
					<div class="widget-a">
						<div class="w-header-a">
							<h5 class="w-title-a">हमारे साथ जुड़ें</h5>
						</div>
						<div class="socials-a">
							<ul class="list-inline">
								<li class="list-inline-item"><a href="https://www.facebook.com/moayush/"
									class="fb-icon hvr-bounce-in"><i class="bi bi-facebook"
										aria-hidden="true"></i></a></li>
								<li class="list-inline-item"><a href="https://twitter.com/moayush"
									class="tw-icon hvr-bounce-in"><i class="bi bi-twitter"
										aria-hidden="true"></i></a></li>
								<li class="list-inline-item"><a href="https://www.instagram.com/ministryofayush/"
									class="insta-icon hvr-bounce-in"><i class="bi bi-instagram"
										aria-hidden="true"></i></a></li>
								<li class="list-inline-item"><a href="https://www.youtube.com/channel/UCqRR2gs-I3zrNcE4so4TpgQ"
									class="yt-icon hvr-bounce-in"><i class="bi bi-youtube
										aria-hidden="true"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-6 col-sm-12">
					<div class="logo-footer-right">
						<a href="#"><img data-src="admin/assets/img/ayush-grid.png"
							class="img-fluid lazy"></a>
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
						<p class="sm-text">अंतिम अद्यतन संस्करण <span class="text-color">[01-Dec-2022]</span></p>
					</div>
				</div>
			</div>
		</div>
	</footer>
	
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
               <img data-src="admin/assets/img/svg/signup-img.gif" alt="" class="img-fluid lazy">
              </div>
					<div class="custom-model-content">					
						<h2 class="mb-15">साइन अप करें</h2>
						<p class="text-sm">आप नीचे दी गई श्रेणियों से साइन अप कर सकते हैं</p>
					</div>
					
					<ul class="wrapper1">	
						<li class="wrap-multi-btn">				
						<a href="https://apps.bisag.co.in/AyushCounselling/StudentSignUp" class="animation-btn btn--bolt">
							<span class="btn-icon"><i class="bi bi-person-bounding-box fs-3"></i></span><span
								  class="btn-topFakeBorders"></span>
							<span class="btn-bottomAnim btn-anim"></span>
							<span class="btn-sideAnim btn-anim"></span>
							<span class="btn-topAnim btn-anim"></span>
							<span class="btn-content">विद्यार्थी</span>
						</a>
						</li>
						<li class="wrap-multi-btn">
						<a href="institute_registration_url" class="animation-btn btn--bolt">
							<span class="btn-icon"><i class="bi bi-building fs-3"></i></span>
							<span class="btn-topFakeBorders"></span>
							<span class="btn-bottomAnim btn-anim"></span>
							<span class="btn-sideAnim btn-anim"></span>
							<span class="btn-topAnim btn-anim"></span>
							<span class="btn-content">कॉलेज</span>
						</a>
						</li>
						<li class="wrap-multi-btn"><a
							href="portalsignin" class="animation-btn btn--bolt">
								<span class="btn-icon"><i class="bi bi-mortarboard-fill fs-3"></i></span>
								<span class="btn-topFakeBorders"></span> <span
								class="btn-bottomAnim btn-anim"></span> <span
								class="btn-sideAnim btn-anim"></span> <span
								class="btn-topAnim btn-anim"></span> <span class="btn-content">पूर्व छात्रों</span>
						</a></li>
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
</c:if>

<!-- back to top section start-->
 <a href="#" class="back-to-top d-flex align-items-center justify-content-center hvr-icon-spin" title="Back to Top"><i class="bi bi-arrow-up-short hvr-icon"></i></a>
 <!-- back to top section end-->
 
 

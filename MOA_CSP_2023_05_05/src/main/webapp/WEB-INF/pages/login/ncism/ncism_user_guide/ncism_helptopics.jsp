<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<section class="page-content">
	<!--  Intro Single  -->
	<section class="intro-single">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-8">
					<div class="title-single-box">
						<h1 class="title-single">Help Topics</h1>
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="ncism_usermanual_main">User Guide</a></li>
							<li class="breadcrumb-item active" aria-current="page">Help
								Topics</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single-->

	<!--  Help Topics Start -->
	<section class="helptopics">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="navi-tabs custom-accord-menu">
						<div class="row">
							<div class="col-lg-2 col-md-2 col-sm-12 col-12">
								<nav class="navi-tabs__nav">
									<ul class="verticalnav">
										<li class="dropdown open"><a class="verticalnav-title">Portal Topics</a>
											<ul>
												<li><a href="#intro1" class="active">Portal Guide</a></li>
											</ul></li>
										

									</ul>
								</nav>
							</div>
							<div class="col-lg-10 col-md-10 col-sm-12 col-12">
								<div class="navi-tabs__container">
									<div class="navi-tabs__content counter-section" id="intro1">

										<h4 class="section-main-title">Portal Guide</h4>
										<hr>

												<div class="accordion" id="accordionPanelsStayOpenExample">
										
											<div class="accordion-item accordion-itemstyle">
												<h2 class="accordion-header"
													id="panelsStayOpen-headingthree">
													<button
														class="accordion-button accordion-itemstylena accordion-primary-button "
														type="button" data-bs-toggle="collapse"
														data-bs-target="#panelsStayOpen-collapsethree"
														aria-expanded="true"
														aria-controls="panelsStayOpen-collapsethree">
														Portal Introduction</button>
												</h2>
												<div id="panelsStayOpen-collapsethree"
													class="accordion-collapse collapse show"
													aria-labelledby="panelsStayOpen-headingthree">
													<div class="accordion-body">
													
														<ul>
															<!-- <li><a href="#" class="custom-link">User Manual</a></li> -->
															<li><a class="custom-link no-hover">Videos</a>
																<div class="uvp-list">
																 <ul class="video-list">
																	<li><a
																href="ayusheducationusermanual?filename=/ayush_edu/ayush_intro.mp4"
																class="custom-link" target="_blank">Hindi </a></li>
																	<li><a
																href="ayusheducationusermanual?filename=/ayush_edu/ayush_intro_eng.mp4"
																class="custom-link" target="_blank">English</a></li>
																</ul></div></li>
														</ul>
													</div>
												</div>
											</div>
											
											
											<div class="accordion-item accordion-itemstyle">
												<h2 class="accordion-header"
													id="panelsStayOpen-headingtwo">
													<button
														class="accordion-button accordion-itemstylena accordion-primary-button collapsed"
														type="button" data-bs-toggle="collapse"
														data-bs-target="#panelsStayOpen-collapsetwo"
														aria-expanded="false"
														aria-controls="panelsStayOpen-collapsetwo">
														Sign In Process</button>
												
												
												</h2>
												<div id="panelsStayOpen-collapsetwo"
													class="accordion-collapse collapse"
													aria-labelledby="panelsStayOpen-headingtwo">
													<div class="accordion-body">
													
														<ul>
															<li><a href="signin_common_usermanual" class="custom-link">Sign In User Manual (General)</a></li>
															<li><a class="custom-link no-hover">Videos</a>
																<div class="uvp-list">
																 <ul class="video-list">
																	<li><a
																href="ayusheducationusermanual?filename=/ayush_edu/ayush_signin.mp4"
																class="custom-link" target="_blank">Hindi </a></li>
																	<li><a
																href="ayusheducationusermanual?filename=/ayush_edu/ayush_signin_eng.mp4"
																class="custom-link" target="_blank">English</a></li>
																</ul></div></li>
														</ul>
													</div>
												</div>
											</div>
											
											<div class="accordion-item accordion-itemstyle">
												<h2 class="accordion-header"
													id="panelsStayOpen-headingfour">
													<button
														class="accordion-button accordion-itemstylena accordion-primary-button collapsed"
														type="button" data-bs-toggle="collapse"
														data-bs-target="#panelsStayOpen-collapsefour"
														aria-expanded="flase"
														aria-controls="panelsStayOpen-collapsefour">
														Change Password</button>
												</h2>
												<div id="panelsStayOpen-collapsefour"
													class="accordion-collapse collapse"
													aria-labelledby="panelsStayOpen-headingfour">
													<div class="accordion-body">
													
														<ul>
															<!-- <li><a href="#" class="custom-link">User Manual</a></li> -->
															<li><a class="custom-link no-hover">Videos</a>
																<div class="uvp-list">
																 <ul class="video-list">
																	<li><a
																href="ayusheducationusermanual?filename=/ayush_edu/ayush_cpassword.mp4"
																class="custom-link" target="_blank">Hindi </a></li>
																	<li><a
																href="ayusheducationusermanual?filename=/ayush_edu/ayush_cpassword_eng.mp4"
																class="custom-link" target="_blank">English</a></li>
																</ul></div></li>
														</ul>
													</div>
												</div>
											</div>
											
																				
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
	<!-- End Help Topics-->
</section>


<script type="text/javascript" nonce="${cspNonce}">
	$(document).ready(function() {

		$('.verticalnav > li > a').click(function() {
			$(this).parent().toggleClass('open')
			$(this).siblings().toggle();
		});

		$('.verticalnav > li:first-child  ul').show();
		$(".navi-tabs__content").hide();
		$(".navi-tabs__content:first").show();

		$('.verticalnav .dropdown li a').click(function() {
			$(".navi-tabs__content").hide();
			var activeTab = $(this).attr("href");
			$(activeTab).fadeIn();
			$('.verticalnav .dropdown a').removeClass('active');
			$(this).addClass('active');
		});
	});
</script>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Help Topics</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="nch_pract_usermanual_main">User Guide</a></li>
								<li class="breadcrumb-item active" aria-current="page">Help
									Topics</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<div class="form-elements-wrapper">
			<section class="helptopics">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="card-style mb-30">
						<div class="navi-tabs custom-accord-menu">
						<div class="row">
							<div class="col-lg-2 col-md-2 col-sm-12 col-12">
								<nav class="navi-tabs__nav">
									<ul class="verticalnav">
										
									   <li class="dropdown open"><a
											class="verticalnav-title">NCH Commission</a>
											<ul>
												<li><a href="#nch1" class="active">Practitioner Guide</a></li>

											</ul></li>
									</ul>
								</nav>
							</div>
							<div class="col-lg-10 col-md-10 col-sm-12 col-12">
								<div class="navi-tabs__container">
																										
										<div class="navi-tabs__content" id="nch1">
										<h4>Practitioner Guide</h4>
										<hr>
										<div class="accordion" id="accordionPanelsStayOpenExample">
											<div class="accordion-item accordion-itemstyle">
												<h2 class="accordion-header" id="panelsStayOpen-headingsix">
													<button
														class="accordion-button accordion-itemstylena accordion-primary-button "
														type="button" data-bs-toggle="collapse"
														data-bs-target="#panelsStayOpen-collapsesix"
														aria-expanded="true"
														aria-controls="panelsStayOpen-collapsesix">
														Practitioner Guide</button>
												</h2>
												<div id="panelsStayOpen-collapsesix"
													class="accordion-collapse collapse show"
													aria-labelledby="panelsStayOpen-headingsix">
													<div class="accordion-body">
														<ul class="custom-dbsimple-list">
															<!-- <li><a href="#" class="custom-link">User Manual</a></li> -->
															
																<li><a class="custom-link no-hover">Videos</a>
																	<div class="uvp-list">
																		<ul class="video-list">
																			<li><a
																				href="db_ayusheducationusermanual?filename=/ayush_edu/nch/nch_practitioner.mp4"
																				class="custom-link" target="_blank">Hindi </a></li>
																			<li><a
																				href="db_ayusheducationusermanual?filename=/ayush_edu/nch/nch_practitioner_eng.mp4"
																				class="custom-link" target="_blank">English</a></li>
																		</ul>
																	</div></li>

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

		</div>
	</div>
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

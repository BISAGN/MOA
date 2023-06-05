<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="assets/vendor/audio-lang/audio-lang-change.js"></script>

	<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>User Guide</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">User Guide</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12 section-md-t3">
								<h5>Looking for something else?</h5>
								<a href="ncism_inst_helptopics" class="custom-link">View All Topics<i
									class="bi bi-chevron-right"></i>
								</a>
								<hr>
							</div>
							<div class="col-lg-10 col-md-10 col-sm-12 col-12 section-md-t3">
								<div class="title-box-d">
									<h5 class="title-d">Video Guide</h5>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-12 col-12">
								<div class="video-language">
									<select class="singleselect form-control form-control-lg"
										id="myselection">
										<option value="hindi">Choose Audio Language</option>
										<option value="hindi">Hindi</option>
										<option value="english">English</option>
									</select>									
								</div>
							</div>
						</div>
						<div class="row">
							
							<div class="col-lg-3 col-md-4 col-sm-6 col-12">
								<div class="card-style-2">
									<div class="card-image-outer">
										<a href="db_ayusheducationusermanual?filename=/ayush_edu/ncism/ncism_admission.mp4"
											title="Play Video" target="_blank" class="card-image hindivideo"> <span
											class="card-image1">
											 <img src="assets/img/portalss.png" alt="portalss"
												title="Play Video">
										</span> <span class="comission_bedge">NCISM</span>
										<span class="language_bedge">Language preference Hindi</span>
										</a>
										<a href="db_ayusheducationusermanual?filename=/ayush_edu/ncism/ncism_admission_eng.mp4" title="Play Video" target="_blank" class="card-image englishvideo">
											<span class="card-image1"> 
											     <img src="assets/img/portalss.png" alt="portalss" title="Play Video">
										    </span><span class="comission_bedge">NCISM</span>
										    <span class="language_bedge">Language preference English</span>
										</a>
										
									</div>
									<div class="card-content">
										<h5 class="custom-card-title">
											<a>Institute Admission Guide</a>
										</h5>
										<div class="custom-card-link">
											<a href="#" class="read-more" data-bs-toggle="modal"
												data-bs-target="#admissionguide">Admission Flow Chart <i
												class="bi bi-chevron-right"></i>

											</a>
										</div>
										
									</div>
									<div class="custom-card-footer">
										<span class="card-footer-c"><span class="post-time">last
												update : <span>10-03-2023</span>
										</span></span>
									</div>
								</div>
							</div>

							<div class="col-lg-3 col-md-4 col-sm-6 col-12 d-none">
								<div class="card-style-2">
									<div class="card-image-outer">

										<a
											href="db_ayusheducationusermanual?filename=/ayush_edu/nch/nch_practitioner.mp4"
											target="_blank" title="Play Video" class="card-image"> <span
											class="card-image1"> <img
												src="assets/img/portalss.png" alt="portalss"
												title="Play Video">
										</span> <span class="comission_bedge_nch">NCH</span>
										 <span class="language_bedge">Language preference Hindi</span>
										</a>
									</div>
									<div class="card-content">
										<h5 class="custom-card-title">
											<a>Practitioner Guide</a>
										</h5>
									</div>
									<div class="custom-card-footer">
										<span class="card-footer-c"><span class="post-time">last
												update : <span>03-03-2023</span>
										</span></span>
									</div>
								</div>
							</div>

							<div class="col-lg-3 col-md-4 col-sm-6 col-12 d-none">
								<div class="card-style-2">
									<div class="card-image-outer">

										<a
											href="db_ayusheducationusermanual?filename=/ayush_edu/ncism/ncism_institute_curri.mp4"
											title="Play Video" target="_blank" class="card-image"> <span
											class="card-image1"> <img
												src="assets/img/portalss.png" alt="portalss"
												title="Play Video">
										</span> <span class="comission_bedge">NCISM</span>
										</a>
									</div>
									<div class="card-content">
										<h5 class="custom-card-title">
											<a
												href="db_ayusheducationusermanual?filename=/ayush_edu/ncism/ncism_institute_curri.mp4"
												target="_blank">Institute Curriculum Guide</a>
										</h5>
										<div class="custom-card-link">
											<a href="#0" class="read-more" data-bs-toggle="modal"
												data-bs-target="#instituteguide">Institute Flow Chart <i
												class="bi bi-chevron-right"></i>
											</a>
										</div>
									</div>
									<div class="custom-card-footer">
										<span class="card-footer-c"><span class="post-time">last
												update : <span>03-03-2023</span>
										</span></span>
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

<div class="modal fade custom-modal" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true"
		id="instituteguide">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content text-center">
				<div class="modal-header">
					<h3 class="modal-title">Institute Flow Chart</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="custom-model-img">

						<iframe src="assets/pdf/ncism/ncism-institute.pdf"
							class="custom-pdf-view"> </iframe>
					</div>
				</div>
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><a type="button" class="main-btn dark-btn btn-hover"
							data-bs-dismiss="modal">Close</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade custom-modal" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true"
		id="admissionguide">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content text-center">
				<div class="modal-header">
					<h3 class="modal-title">Admission Flow Chart</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="custom-model-img">

						<iframe src="assets/pdf/ncism/ncism-admission.pdf"
							class="custom-pdf-view"> </iframe>
					</div>
				</div>
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><a type="button" class="main-btn dark-btn btn-hover"
							data-bs-dismiss="modal">Close</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

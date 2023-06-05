<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- Favicons -->
<link href="assets/img/favicon.ico" rel="icon">

<!-- Initial CSS Files -->
<link href="assets/font/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Main CSS Files -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/responsive.css" rel="stylesheet">

<link href="assets/vendor/svg-animation.css" rel="stylesheet" media="all">

<!-- Initial JS Files -->
<script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script>

<!-- Other JS Files -->
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


<!-- Join VC Start -->
<section class="page-content custom-join-meeting">
	<div class="container">
		<div class="row g-0 auth-row">
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="auth-cover-wrapper">
					<div class="auth-cover">
						<div class="title text-center">
							<h1 class="text-primary mb-10">NEW MEETING!</h1>
							<p class="text-medium">Connect From Anywhere</p>
						</div>
						<div class="cover-image">
							<img src="assets/image/signin-image.svg" alt="Join Meeting Image" title="Join Meeting" />
						</div>
						<div class="shape-image">
							<img src="assets/image/shape.svg" alt="Join Meeting Background Image" />
						</div>
					</div>
				</div>
			</div>
			<!-- end col -->
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="credential-wrapper">
					<div class="form-wrapper">
						<h4 class="text-primary mb-5 text-center">Connect Like Never Before</h4>				
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="input-style-1">
										<label>Join As</label> <input type="text" id="join_name"
											name="join_name"
											class="form-control form-control-lg form-control-a autocomplete UpperClassName txt-transupp"
											autocomplete="off"> <input type="hidden" id="meet_id"
											name="meet_id" value="${meetID}" />
									</div>
								</div>
								<!-- end col -->
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="input-style-1">
										<label>Password</label> <input type="text" id="join_pass"
											name="join_pass"
											class="form-control form-control-lg form-control-a autocomplete txt-transupp"
											autocomplete="off">
									</div>
								</div>
								<!-- end col -->
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="button-group">
										<button
											class="main-btn primary-btn btn-hover w-100 btn-iconic-icon btnmeet"
											id="btn-join" type="button" value="Join">
											<i class="bi bi-camera-video"></i> Join Meeting
										</button>
									</div>
								</div>
							</div>
            				<!-- end row -->						
					</div>
				</div>
			</div>
			<!-- end col -->
		</div>
		<!-- end row -->
	</div>
</section>
<!-- Join VC End -->

<!-- <section class="page-content join-meeting">
<section class="join-inner"> -->
<!--  Intro Single  -->
<!-- <section class="intro-single">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-lg-12 col-12">
				<div class="title-single-box text-center mb-5">
					<h1 class="title-single">Join Meeting</h1>
				</div>
			</div>			
		</div>
	</div>
</section> 
<!-- End Intro Single-->

<!--  About Section start-->
<%-- <section class="section-about">
	<div class="container">
		<div class="custom-img-with-content">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="custom-img">
                        <img src="assets/img/svg/Experts.gif" alt="" class="img-fluid">							
					</div>
				</div>

				<div class="col-lg-4 col-md-4 col-sm-12 col-12">
					<div class="row">
							<div class="col-lg-12 col-md-12 col-12 mb-3">
								<div class="form-group">
									<label for="username">Join As</label> <input type="text" id="join_name" name="join_name" 
										class="form-control form-control-lg form-control-a autocomplete UpperClassName txt-transupp"
										 autocomplete="off">
										<input type="hidden" id="meet_id" name="meet_id" value="${meetID}" />
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-12 mb-3">
								<div class="form-group">
									<label for="email">Password</label> <input type="text" id="join_pass" name="join_pass" 
										class="form-control form-control-lg form-control-a autocomplete txt-transupp"
										autocomplete="off">
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-12 text-center mt-5">
								<button class="btn btn-a" id="btn-join" type="button" value="Join">Join</button>
							</div>
							</div>
				</div>
			</div>
		</div>
	</div>
</section>  --%>
<!--  About Section end-->
<!-- </section> 
</section> -->



<%-- <div class="container-fluid">
<section class="dashboard-page">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>Join Meeting</h2>
				</div>
			</div>
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
<!-- 							<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li> -->
							<li class="breadcrumb-item active" aria-current="page">Join Meeting</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>

	<div class="form-elements-wrapper">
		<div class="row">
			<div class="col-12">
					<div class="card-style mb-30">
					<h6 class="mb-25">Join Meeting</h6>
						<div class="row">
							<div class="col-6 col-sm-6 col-md-3 col-lg-1">
								<div class="input-style-1">
									<label for="text-input"> Join As <span class="mandatory"></span></label>
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
									<input type="text" id="join_name" name="join_name" class="autocomplete UpperClassName txt-transupp"
										autocomplete="off"   />
										<input type="hidden" id="meet_id" name="meet_id" value="${meetID}" />
								</div>
							</div>
							
							<div class="col-6 col-sm-6 col-md-3 col-lg-1">
								<div class="input-style-1">
									<label for="text-input"> Password <span class="mandatory"></span></label>
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
									<input type="text" id="join_pass" name="join_pass" class="autocomplete  txt-transupp"
										autocomplete="off"   />
								</div>
							</div>
						</div>
						<ul class="buttons-group mainbtn">
								<li>
									<input id="btn-join" class="main-btn info-btn btn-hover " type="button" value="Join">
								</li>
						</ul>
					</div>
			</div>
		</div>
 	</div>
</section>
</div> --%>

<script type="text/javascript">
	$(document).ready(function() {
		var ec = ENC("meetingid", "test123");
// 		console.log(ec);
		var dc = dec("meetingid", ec);
// 		console.log(dc);
// 		alert(btoa("category=textile&user=user1"));
// 		alert(atob("Y2F0ZWdvcnk9dGV4dGlsZSZ1c2VyPXVzZXIx"));
		$("#meet_id").val("${meetID}");
		
		
	});
	document.addEventListener('DOMContentLoaded', function () {		
		
		document.getElementById('btn-join').onclick = function() {
			return joinMeeting();
		};
		
	});
	
		function joinMeeting() {
			var meet_id = atob($("#meet_id").val());
			var join_name = $("#join_name").val();
			var join_pass = $("#join_pass").val();
			$.post("/getMeeting_Link?" , {
				meet_id : meet_id,
				join_name : join_name,
				join_pass : join_pass
				
				},function(j) {
					console.log(j);
					window.location.href = j;
	
	
			});
		}

</script>



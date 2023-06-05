<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

	<!--  Intro Single  -->
	<section class="intro-single">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-8">
					<div class="title-single-box">
						<h1 class="title-single">संपर्क करें</h1>
						<!--  <span class="color-text-a">Your message has been sent. Thank you! </span> -->
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="landingpagehindi">मुख
									पृष्ठ</a></li>
							<li class="breadcrumb-item active" aria-current="page">
								संपर्क करें</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single-->

	<!--  Contact Single -->
	<section class="contact">
		<div class="container">
			<div class="row">
				<div class="col-md-7">
					<form action="forms/contact.php" method="post" role="form"
						class="php-email-form">
						<div class="row">
							<div class="col-md-6 mb-3">
								<div class="form-group">
									<label for="username">नाम</label> <input type="text"
										name="name"
										class="form-control form-control-lg form-control-a"
										placeholder="आपका नाम" required>
								</div>
							</div>
							<div class="col-md-6 mb-3">
								<div class="form-group">
									<label for="email">ईमेल आईडी</label> <input name="email"
										type="email"
										class="form-control form-control-lg form-control-a"
										placeholder="आपका ईमेल" required>
								</div>
							</div>
							<div class="col-md-12 mb-3">
								<div class="form-group">
									<label for="subject">विषय</label> <input type="text"
										name="subject"
										class="form-control form-control-lg form-control-a"
										placeholder="विषय" required>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="message">संदेश</label>
									<textarea name="message" class="form-control" name="message"
										cols="45" rows="8" placeholder="आपका संदेश" required></textarea>
								</div>
							</div>

							<div class="col-md-12 text-center mt-5">
								<button type="submit" class="btn btn-a">मेसेज भेजें</button>
							</div>
						</div>
					</form>
				</div>
				<div class="col-md-5 section-md-t3">
					<div class="icon-box section-b2">
						<div class="icon-box-icon">
							<span class="bi bi-envelope"></span>
						</div>
						<div class="icon-box-content table-cell">
							<div class="icon-box-title">
								<h4 class="icon-title">नमस्ते बोलो</h4>
							</div>
							<div class="icon-box-content">
								<p class="mb-1">
									ईमेल: <span class="color-a">webmanager-ayush@gov.in</span>
								</p>
								<p class="mb-1">
									फ़ोन: <span class="color-a">1800-11-0180,1964</span>
								</p>
							</div>
						</div>
					</div>
					<div class="icon-box section-b2">
						<div class="icon-box-icon">
							<span class="bi bi-geo-alt"></span>
						</div>
						<div class="icon-box-content table-cell">
							<div class="icon-box-title">
								<h4 class="icon-title">हमें इसमें खोजें</h4>
							</div>
							<div class="icon-box-content">
								<p class="mb-1">जवाहर लाल नेहरू भारतीय चिकित्सा एवं
									होम्योपैथी अनुसंधान भवन, 61-65, संस्थागत क्षेत्र, जनकपुरी "डी"
									ब्लॉक, नई दिल्ली-110058</p>
							</div>
						</div>
					</div>
					<div class="icon-box">
						<div class="icon-box-icon">
							<span class="bi bi-share"></span>
						</div>
						<div class="icon-box-content table-cell">
							<div class="icon-box-title">
								<h4 class="icon-title">हमारे साथ जुड़ें</h4>
							</div>
							<div class="socials-a">
								<ul class="list-inline">
									<li class="list-inline-item"><a href="#"
										class="fb-icon hvr-bounce-in"> <i class="bi bi-facebook"
											aria-hidden="true"></i>
									</a></li>
									<li class="list-inline-item"><a href="#"
										class="tw-icon hvr-bounce-in"> <i class="bi bi-twitter"
											aria-hidden="true"></i>
									</a></li>
									<li class="list-inline-item"><a href="#"
										class="insta-icon hvr-bounce-in"> <i
											class="bi bi-instagram" aria-hidden="true"></i>
									</a></li>
									<li class="list-inline-item"><a href="#"
										class="ld-icon hvr-bounce-in"> <i class="bi bi-linkedin"
											aria-hidden="true"></i>
									</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Contact Single-->

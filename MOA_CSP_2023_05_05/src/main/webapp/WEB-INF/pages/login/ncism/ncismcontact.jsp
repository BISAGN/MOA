<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<section class="page-content">
<!--  Intro Single  -->
<section class="intro-single">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-lg-8">
				<div class="title-single-box">
					<h1 class="title-single">Contact US</h1>
				</div>
			</div>
			<div class="col-md-12 col-lg-4">
				<nav aria-label="breadcrumb"
					class="breadcrumb-box d-flex justify-content-lg-end">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
						<li class="breadcrumb-item active" aria-current="page">
							Contact Us</li>
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
						<div class="col-md-6 mb-1">
							<div class="form-group">
								<label for="username">Name</label> <input type="text"
									name="name" class="form-control form-control-lg form-control-a"
									placeholder="Your Name" required>
							</div>
						</div>
						<div class="col-md-6 mb-1">
							<div class="form-group">
								<label for="email">Email ID</label> <input name="email"
									type="email"
									class="form-control form-control-lg form-control-a"
									placeholder="Your Email ID" required>
							</div>
						</div>
						<div class="col-md-12 mb-1">
							<div class="form-group">
								<label for="subject">Subject</label> <input type="text"
									name="subject"
									class="form-control form-control-lg form-control-a"
									placeholder="Subject" required>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label for="message">Message</label>
								<textarea name="message" class="form-control" name="message"
									cols="45" rows="8" placeholder="Your Message" required></textarea>
							</div>
						</div>

						<div class="col-md-12 text-center">
							<button type="submit" class="btn btn-a">Send Message</button>
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
							<h4 class="icon-title">Say Hello</h4>
						</div>
						<div class="icon-box-content">
							<p class="mb-1">
								Email: <a href="mailto:secretary@ncismindia.org" class="color-a">secretary@ncismindia.org</a>
							</p>
							<p class="mb-1">
								Phone: <a href="tel:+ 91-11-2852 5464,2519" class="color-a">+ 91-11-2852 5464,2519</a>
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
							<h4 class="icon-title">Find us in</h4>
						</div>
						<div class="icon-box-content">
							<p class="mb-1">Jawahar Lal Nehru Bhartiya Chikitsa Avam
								Homoeopathy Anusandhan Bhawan, 61-65, Institutional Area,
								Janakpuri "D" Block, New Delhi-110058</p>
						</div>
					</div>
				</div>
				<div class="icon-box">
					<div class="icon-box-icon">
						<span class="bi bi-share"></span>
					</div>
					<div class="icon-box-content table-cell">
						<div class="icon-box-title">
							<h4 class="icon-title">Connect With Us</h4>
						</div>
						<div class="icon-box-content socials-a">
							<ul class="list-inline">
								<li class="list-inline-item"><a href="https://www.facebook.com/moayush/"
										class="fb-icon hvr-bounce-in" target="blank"> <i class="bi bi-facebook"
											aria-hidden="true"></i>
									</a></li>
									<li class="list-inline-item"><a href="https://twitter.com/moayush"
										class="tw-icon hvr-bounce-in" target="blank"> <i class="bi bi-twitter"
											aria-hidden="true"></i>
									</a></li>
									<li class="list-inline-item"><a href="https://www.instagram.com/ministryofayush/"
										class="insta-icon hvr-bounce-in" target="blank"> <i
											class="bi bi-instagram" aria-hidden="true"></i>
									</a></li>
									<li class="list-inline-item"><a href="https://www.youtube.com/channel/UCqRR2gs-I3zrNcE4so4TpgQ"
										class="yt-icon hvr-bounce-in" target="blank"> <i class="bi bi-youtube"
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
</section>
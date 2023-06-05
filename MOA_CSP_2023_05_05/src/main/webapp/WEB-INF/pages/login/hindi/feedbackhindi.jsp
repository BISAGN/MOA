<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

	<!--  Intro Single  -->
	<section class="intro-single">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-8">
					<div class="title-single-box">
						<h1 class="title-single">Feedback page</h1>
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="landingpagehindi">Home</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">feedback</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single-->

	<!--  Register Section  -->
	<section class="section-register">
		<div class="container">
			<div class="row justify-content-center">

				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="login-form">
						<form name="user_mst" id="user_mst" action="Registration_Action"
							method='POST'>
							<div class="row">
								<div class=" col-lg-6 col-md-6 col-sm-12 mb-3">
									<div class="form-group">
										<label for="name1">First Name</label> <input type="text"
											name="First Name"
											class="form-control form-control-lg form-control-a"
											placeholder="Your First Name" id="name1">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 mb-3">
									<div class="form-group">
										<label for="name1">Last Name</label> <input type="text"
											name="Last Name"
											class="form-control form-control-lg form-control-a"
											placeholder="Your Last Name" id="name1">
									</div>
								</div>

								<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
									<div class="form-group">
										<label for="Phone">Phone</label> <input type="tel"
											name="Phone"
											class="form-control form-control-lg form-control-a"
											placeholder="Your Phone No" id="name1">
									</div>
								</div>

								<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
									<div class="form-group">
										<label for="email1">Email</label> <input type="text"
											name="email"
											class="form-control form-control-lg form-control-a"
											placeholder="your-email@gmail.com" id="email1">
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 mb-3">
									<div class="form-group">
										<label for="password">your feedback</label>
										<textarea type="text" name="pass"
											class="form-control form-control-lg form-control-a" rows="5"></textarea>
									</div>
								</div>

								<div class="col-lg-12 col-md-12 col-sm-12 text-center">
									<button type="submit" value="submit" class="btn btn-a"
										onclick="return isValid();">Submit</button>
								</div>

							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--  End Register Section  -->


	<!-- signup default script for developer start -->
	<script>
	function isValid()
	{	
		
		if($("#login_name").val()==""){
			alert("Please Enter User Name");
			$("#login_name").focus();
			return false;
		} 
		if($("#user_name").val()==""){
			alert("Please Enter User ID");
			$("#user_name").focus();
			return false;
		} 
		/* if($("#army_no").val()==""){
			alert("Please Enter Army No");
			$("#army_no").focus();
			return false;
		} */
		if($("#user_password").val()==""){
			alert("Please Enter User Password");
			$("#user_password").focus();
			return false;
		} 
		if($("#user_password").val().length < 8 | $("#user_password").val().length > 28){
			alert("Please Enter Password at least 8 to 28 digit");
			$("#user_password").focus();
			return false;
		} 
		if($("#user_re_password").val()==""){
			alert("Please Enter User Re-Password");
			$("#user_re_password").focus();
			return false;
		} 
		if($("#user_re_password").val().length < 8 | $("#user_re_password").val().length > 28){
			alert("Please Enter Re-Password at least 8 to 28 digit");
			$("#user_re_password").focus();
			return false;
		}
		if($("select#user_role_id").val()=="0"){
			alert("Please Select Role Id");
			$("select#user_role_id").focus();
			return false;
		} 
		return true;
	} 

	$(document).ready(function () {
		$("input#login_name").val("");
		$("#user_name").val("");
		/* $("#army_no").val(""); */
		$("#user_password").val("");
		$("#user_re_password").val("");
	});
	</script>
	<!-- signup default script for developer end-->

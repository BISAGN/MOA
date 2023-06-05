<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<section class="page-content">
<!--  Intro Single  -->
	<section class="intro-single">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-8">
					<div class="title-single-box">
						<h1 class="title-single">Contact Us</h1>
						<!--    <span class="color-text-a">Your message has been sent. Thank you! </span> -->
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
					  <form:form name="Contact_US" id="Contact_US" action="" method="post" class="form-horizontal" commandName="" >
						<div class="row">
							<div class="col-md-6 mb-1">
								<div class="form-group">
									<label for="username">Name</label> <input type="text"
										name="name" id="name" maxlength="30"
										class="form-control form-control-lg form-control-a"
										placeholder="Your Name" required>
								</div>
							</div>
							<div class="col-md-6 mb-1">
								<div class="form-group">
									<label for="email">Email ID</label> <input name="email"
										type="email" id="email" maxlength="50"
										class="form-control form-control-lg form-control-a"
										placeholder="Your Email ID" required>
								</div>
							</div>
							<div class="col-md-12 mb-1">
								<div class="form-group">
									<label for="subject">Subject</label> <input type="text"
										name="subject" id="subject" maxlength="100"
										class="form-control form-control-lg form-control-a"
										placeholder="Subject" required>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="message">Message</label>
									<textarea name="message" class="form-control" name="message" id="message" maxlength="250"
										cols="45" rows="8" placeholder="Your Message" required></textarea>
								</div>
							</div>

							<div class="col-md-12 text-center">
								<button type="submit" class="btn btn-a" id="btn-save-cont">Send Message</button>
							</div>
                     <input type="hidden" name="con_hid" id="con_hid" >
<!--                    		 <div class="col-md-12 text-center mt-5"> -->
<!--                       <button type="button"  class="main-btn success-btn  btn-hover" id="btn-save-cont">Send Message</button> -->
<!-- 						</div> -->
						</div>
					</form:form>
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
									Email: <a href="mailto:webmanager-ayush@gov.in" class="color-a">webmanager-ayush@gov.in</a>
								</p>
								<p class="mb-1">
									Phone: <a href="tel:1800-11-0180,1964" class="color-a">1800-11-0180,1964</a>
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
								<p class="mb-1">Ayush Bhawan, B Block, GPO Complex, INA, New Delhi – 110023</p>
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
	
	
	
	<script nonce="${cspNonce}" type="text/javascript">
function sentdata() {
	$.ajaxSetup({
	    async: false
	});
	if ($("input#name").val().trim() == "") {
		alert("Please Enter Name");
		$("input#name").focus();
		return false;
	}
	if ($("input#email").val().trim() == "") {
		alert("Please Enter Email");
		$("input#email").focus();
		return false;
	}
	if ($("input#subject").val().trim() == "") {
		alert("Please Enter Subject");
		$("input#subject").focus();
		return false;
	}
	if ($("textarea#message").val().trim() == "") {
		alert("Please Enter Message");
		$("textarea#message").focus();
		return false;
	}
	//var form = $("#Contact_US").serialize();
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	var name =$("#name").val();
	var email =$("#email").val();
	var subject =$("#subject").val();
	var message =$("#message").val();
 	$.post("Contact_USAction?"+key+"="+value,{name:name,email:email,subject:subject,message:message},function(j) {
		if(j == "Data Saved Successfully"){
        alert("Your Contact Has Been Sucessfully Submitted");
        location.reload();
		}
	});

}

document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn-save-cont').onclick = function () {
		return sentdata();
		 	
	}	
	
// 	document.getElementById('name').onkeypress = function() {
//		return onlyAlphabetsStringSpace(event, this);
//	};
//	document.getElementById('name').onchange = function() {
//		 return checkLength(this.id,30);
//	};

//	document.getElementById('email').onchange = function() {
//		 checkgmail(this.value);
//		return checkLength(this.id,30);
//	};
//	document.getElementById('mob_no').onchange = function() {
//		return mobileNumber(this);
//checkLength(this.id,10);
//	};
//	document.getElementById('mob_no').onkeypress = function() {
//		return isNumberKey0to9(event);

//	};
//	document.getElementById('subject').onkeypress = function() {
//		return onlyAlphabetsStringSpace(event, this);
//	};
//	document.getElementById('subject').onchange = function() {
//		 return checkLength(this.id,100);
//	};
//	document.getElementById('message').onkeypress = function() {
//		return  onlyAlphabetsStringSpace(event, this);
//	};
//	document.getElementById('message').onchange = function() {
//		 return checkLength(this.id,250);
//	};
});


// function checkgmail(email1) {
	
// 	 document.getElementById("recr_email").innerHTML="";
// 	if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
		
// 	}else{
		
// 		alert("Please Enter Valid Email Address");
// 		$("input#email").focus();
		
// 		$("input#email").val('');
	        
// 		return false ;
// 	}
// }



// function mobileNumber(obj) {
// 	if (obj.value.length < 10) {
// 		alert('Please Enter valid Number');
// 		$('#' + obj.id).focus();
// 		$('#' + obj.id).val("");
// 		return false;
// 	}
// 	_mobile = obj.value;
// 	var regExp = /^[6789]\d{9}$/;
// 	if (_mobile == '' || !regExp.test(_mobile)) {
// 		alert('Please Enter Number Start with 6,7,8,9 Digit');
// 		$('#' + obj.id).focus();
// 		$('#' + obj.id).val("");
// 		return false;
// 	}
// }
</script>
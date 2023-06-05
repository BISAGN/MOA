<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<link rel="stylesheet" href="admin/js/autoComplate/autoComplate.css">
<link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script>
<link rel="stylesheet"
	href="admin/assets/vendor/common_custom_style.css">
	
	<script type="text/javascript" src="admin/assets/js/jquery-3.6.0.min.js"></script>
	<script src="admin/assets/db_js/CommonValidation.js"></script>
	<script type="text/javascript" src="admin/js/watermark/common.js"></script>
	<script src="admin/js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="admin/js/Calender/datePicketValidation.js"></script>

<style nonce="${cspNonce}">
 @keyframes rotate {
    from {
        transform: rotate(0deg);
    }
    to { 
        transform: rotate(360deg);
    }
}
 

 @-webkit-keyframes rotate {
    from {
        -webkit-transform: rotate(0deg);
    }
    to { 
        -webkit-transform: rotate(360deg);
    }
}

.load {
	width: 100px;
	height: 100px;
	margin: 110px auto 0;
	border:solid 10px #8822aa;
	border-radius: 50%;
	border-right-color: transparent;
	border-bottom-color: transparent;
	 -webkit-transition: all 0.5s ease-in;
    -webkit-animation-name:             rotate; 
    -webkit-animation-duration:         1.0s; 
    -webkit-animation-iteration-count:  infinite;
    -webkit-animation-timing-function: linear;
    	
    	 transition: all 0.5s ease-in;
    animation-name:             rotate; 
    animation-duration:         1.0s; 
    animation-iteration-count:  infinite;
    animation-timing-function: linear; 
}

</style>

<section class="page-content">
<!--  Intro Single  -->
<section class="intro-single">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-lg-8">
				<div class="title-single-box">
					<h1 class="title-single">Send Mail To All Institute</h1>
					
				</div>
			</div>
			<div class="col-md-12 col-lg-4">
				<nav aria-label="breadcrumb"
					class="breadcrumb-box d-flex justify-content-lg-end">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
						<li class="breadcrumb-item active" aria-current="page">Send Mail To All Institute</li>
					</ol>
				</nav>
					
			</div>
		</div>
	</div>
</section>
<!-- End Intro Single-->

<!--  Register Section  -->
<section class="section-register">
	<div class="custom-btn footer-btn">
		<ul class="footer-btn-list">
				<li class="f-btn"><input type="button"  class="btn btn-a ghost" id="send_btn" value="Send mail"></li>
		</ul>
	</div>							
	<div id="process" name="process" >
		<div class="load">
		</div>
	</div>
														
</section>
<!--  End Register Section  -->
</section>
<script type="text/javascript" nonce="${cspNonce}">
$(document).ready(function() {
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	$("#process").hide();

	$.ajaxSetup({
		async : false
	});
 	
});
	
	
	document.addEventListener('DOMContentLoaded', function() {
		 
	 	
	 	
		document.getElementById('send_btn').onclick = function() {
			process();
		};
	 	
	
	});

	
	async function process(){
		$("#process").show();
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		
		var response = await $.ajax({

	      url: 'checkEmail?' + key + "=" + value,
	      type: 'GET',
	  		contentType: false,
	 		 processData: false,
	      success: function (data) {
	    	  if(data == "okay"){
	    	  $("#process").empty();
	  		$("#process").html('<h3>Sent Email Successfully To all Institutes</h3>');
	    	  }
	      }
		
	    });
	    
		
	  }
	  

</script>



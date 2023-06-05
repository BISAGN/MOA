<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
 
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("username") == null) {
		sess.invalidate();
		response.sendRedirect("/login"); return; 
	} 
	
	String user_agentWithIp = String.valueOf(sess.getAttribute("user_agentWithIp"));
	String userAgent = request.getHeader("User-Agent");
    String ip = "";

	if (request != null) {
        ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null || "".equals(ip)){
            ip = request.getRemoteAddr();
        }
    }
	String currentuser_agentWithIp = userAgent+"_"+ip;
	currentuser_agentWithIp = currentuser_agentWithIp.replace("&#40;","(");
	currentuser_agentWithIp = currentuser_agentWithIp.replace("&#41;",")");
	
	//out.print(currentuser_agentWithIp+"<=c = s=>"+user_agentWithIp);
	if(!user_agentWithIp.equals(currentuser_agentWithIp)){
		sess.invalidate();
		response.sendRedirect("/login"); return; 
	}
%>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <!-- Favicons -->
 <!--  <link href="assets/img/favicon.ico" rel="icon"> -->
 <!-- Favicons -->	
	<link rel="apple-touch-icon" sizes="180x180" href="assets/img/favicon_io/apple-touch-icon.png">
	<link rel="icon" type="image/png" sizes="32x32" href="assets/img/favicon_io/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="assets/img/favicon_io/favicon-16x16.png">
	<!-- <link rel="manifest" href="assets/img/favicon_io/site.webmanifest"> -->
  
  <title><spring:message code="myapp.title"/></title>

  <!-- style files here start-->
  <link rel="stylesheet" href="assets/font/bootstrap-icons/bootstrap-icons.css">
  <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css" />
  <link rel="stylesheet" href="assets/font/Lineicons/lineicons.css" />
  <link rel="stylesheet" href="assets/db_css/db_style.css">
  <link rel="stylesheet" href="assets/db_css/db_responsive.css">
  <link rel="stylesheet" href="assets/db_css/feedback.css">
  <link rel="stylesheet" href="assets/db_css/jquery-ui.css">
  <script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script>
  
	<script src="layout_file/js/plugins.js"></script> 
	<script src="layout_file/js/main.js"></script> 
	
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
	
<link href="js/jquery/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/jquery/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>

<!-- theme switch mode start -->
<link rel="stylesheet" href="assets/vendor/dashboard-setting/db_settingsmodestyle.css">
<link rel="stylesheet" href="assets/db_css/db-setting-mode.css">
<script type="text/javascript" src="assets/vendor/dashboard-setting/settings.js"></script>
<!-- theme switch mode end -->

<!-- single select, search with select start -->
<link href="js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="js/dropDown/select2.min.js" type="text/javascript"></script>
<!-- single select, search with select end -->

<!-- common style start -->
<link rel="stylesheet" href="assets/vendor/common_custom_style.css">
<!-- common style end -->
   
	<script type="text/javascript" nonce="${cspNonce}">
	
	var roleAccess = '${roleAccess}';
	var role = '${role}';
	var user_agent = '${user_agent}';
	var army_no = '${army_no}';
	var otpKey = '${otpKey}';
	var $=jQuery;
	var tbl, div;
 	function resetTimer() {
    	if (jQuery('#div_timeout').length) {  jQuery('#div_timeout').html(timeout());  }
 	}
 	function timeout() { return '1800'; }
 	function getsubmodule(id){localStorage.setItem("subModule", id); }
 	function getmodule(id){localStorage.setItem("Module", id); }
 	function getpagelink(id){localStorage.setItem("pagelink", id); }
 	
 	var key = "${_csrf.parameterName}";
 	var value = "${_csrf.token}";
 	
 	jQuery(document).on('keypress', function(event) {
 		localStorage.setItem("army_no", army_no);
 		
 		var regex = new RegExp("${regScript}");
 	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
//  	    if (!regex.test(key)) {
//  	       event.preventDefault();
//  	       return false;
//  	    } 
 	});
	
	   
 	jQuery(document).ready(function() {
 		
 		
 		//Search_noti();
 		
 		
// 			jQuery('body').bind('cut copy paste', function (e) {
//    	        e.preventDefault();
//    	    });


             // set current sub module
   			jQuery('a#Dropdown_'+localStorage.getItem("Module")).attr("aria-expanded","true");
   			jQuery('a#Dropdown_'+localStorage.getItem("Module")).attr("class","");
   			jQuery('ul#Dp_'+localStorage.getItem("Module")).attr("class","dropdown-nav collapse show");
   			jQuery('a#Dropdown_'+localStorage.getItem("subModule")).attr("aria-expanded","true");
   			jQuery('a#Dropdown_'+localStorage.getItem("subModule")).attr("class","");
   			jQuery('ul#Dp_'+localStorage.getItem("subModule")).attr("class","dropdown-nav collapse show");
   			jQuery('a#Dp_scr'+localStorage.getItem("pagelink")).attr("class","active");
						
			/* // set current sub module
   			jQuery('ul#Dropdown_'+localStorage.getItem("Module")).parent().attr("class","nav-item dropdown dropdown-item show");
   			jQuery('ul#Dropdown_'+localStorage.getItem("subModule")).parent().attr("class","dropdown-item dropdown create_search  show");
   			jQuery('ul#Dropdown_'+localStorage.getItem("subModule")).attr("class","dropdown-menu scrollbar show");
   			jQuery('ul#Dropdown_'+localStorage.getItem("Module")).attr("class","dropdown-menu show");
   			jQuery('li#Dropdown_scr'+localStorage.getItem("pagelink")).attr("class","dropdown-item active"); */
					
			setInterval(function() {
			var today = new Date();
			var date =("0" + today.getDate()).slice(-2)+'-'+ ("0" + (today.getMonth()+1)).slice(-2)+'-'+today.getFullYear();
			var time = ("0" + today.getHours()).slice(-2) + ":" + ("0" + today.getMinutes()).slice(-2);// + ":" + ("0" + today.getSeconds()).slice(-2);
			var dateTime = date+' '+time;
			jQuery("#datetime").text(dateTime);
			
			if (jQuery('#div_timeout').length) {
            	 var tt = jQuery('#div_timeout').html();
                 if (tt === undefined) {
                     tt = timeout();
                 }
                 var ct = parseInt(tt, 10) - 1;
                 jQuery('#div_timeout').html(ct.toString().padStart(3, '0'));
                 if (ct === 0) {
                	formSubmit();
                 }
             } else {
            	formSubmit();
             }
		}, 1000);
		try
		{
			var msg = document.getElementById("msg").value;
			if(msg != null )
			{
				alert(msg);
			}
		}
		catch (e) {
		}
		
		
		
	});
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('layoutBody').onclick = function() {
			parent_disable();resetTimer();
		};
		document.getElementById('layoutBody').onFocus = function() {
			parent_disable();
		};
		document.getElementById('layoutBody').oncontextmenu = function() {
			return false;
		};
		document.getElementById('feedback-save1').onclick = function() {
			Feedbacksave(1);
		};
// 		document.getElementById('feedback-save2').onclick = function() {
// 			Feedbacksave(2);
// 		};
// 		document.getElementById('feedback-save3').onclick = function() {
// 			Feedbacksave(3);
// 		};
// 		document.getElementById('feedback-save4').onclick = function() {
// 			Feedbacksave(4);
// 		};
// 		document.getElementById('feedback-save5').onclick = function() {
// 			Feedbacksave(5);
// 		};
		document.getElementById('feedback_for_sub').onclick = function() {
			jay(this.value);
		};
		document.getElementById('feedback_for').onclick = function() {
			getSubCat();
		};
		
	});
 	
 	
 	function formSubmit() {
		localStorage.clear();
		document.getElementById("logoutForm").submit();
	}
// 	function formSubmit() {
// 		document.getElementById("logoutForm").submit();
// 	}
	popupWindow = null;
	function parent_disable() {
		if(popupWindow && !popupWindow.closed)
			popupWindow.focus();
	}
// 	window.location();
	</script>
 
</head>
<!--  onclick="parent_disable();resetTimer();" -->
<!-- onFocus="parent_disable();" --> 
<!-- oncontextmenu="return false" -->
<body class="${roleStaff_lvl}-theme db_theme_default layoutBody" id="layoutBody"   >
		<c:if test="${not empty msg}">
			<input type="hidden" name="msg" id="msg" value="${msg}"  disabled="disabled"/>
		</c:if>
				
	<tiles:insertAttribute name="menu"/>	
	
	<main class="main-wrapper">
	
		<tiles:insertAttribute name="header"/>		 	
		<tiles:insertAttribute name="body"/>
		<tiles:insertAttribute name="footer"/>
		
		<a href="javascript:;" class="button" id="add_new"><i class="lni lni-pencil-alt"></i></a>
		<a class="button remove close_board">X</a> 
		<div id="board">
		</div>
		
		<div id="WaitLoader" align="center">
			<div class="WaitLoader_content" id="">
				<section>
					<span class="loader-118 awesome"><span class="loader-115"></span></span>
				</section>
				<div class="background">
					<span><img src="assets/img/ayushgridleaf.png"></span> <span><img
						src="assets/img/ayushgridleaf.png"></span> <span><img
						src="assets/img/ayushgridleaf.png"></span> <span><img
						src="assets/img/ayushgridleaf.png"></span> <span><img
						src="assets/img/ayushgridleaf.png"></span> <span><img
						src="assets/img/ayushgridleaf.png"></span>
				</div>
			</div>
		</div>
				     
	</main> 
<div class="feeeback_container">

	<button class="feedback__label">
		<div class="feedback__text">Feedback</div>
	</button>

	<div class="feeeback_content">
		<button type="button" aria-label="Close" class="closeButton">
			<i class="bi bi-x"></i>
		</button>
		<p class="feeeback__Title">Give me a Feedback.</p>
<!-- 		<div class="row m-0"> -->
<!-- 			<div class="col-12"> -->
<!-- 				<div class="select-style-2"> -->
<!-- 					<label>Feedback For</label> -->
<!-- 					<div class="select-position"> -->
<!-- 						<select onchange="jay(this.value);"> -->
<!-- 							<option value="0">Select option</option> -->
<!-- 							<option value="1">Admission</option> -->
<!-- 							<option value="2">Infrastructure of Institute</option> -->
<!-- 							<option value="3">Curriculum</option> -->
<!-- 							<option value="4">Faculty</option> -->
<!-- 							<option value="5">Over All Post Graduation</option> -->
<!-- 						</select> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<div class="row m-0">
			<div class="col-12">
				<div class="select-style-2">
					<label>Feedback For</label>
					<div class="select-position"  id="feedback_for">
						<select id="select_cat" name="select_cat" >
							<option value="0" selected="selected">--Select--</option>
							
						</select>
					</div>
				</div>
			</div>
		</div>
	
	<div class="row m-0">
			<div class="col-12">
				<div class="select-style-2">
					<label>Feedback Subcategory</label>
					<div class="select-position"  id="feedback_for_sub">
						<select id="select_subcat" name="select_subcat">
							<option value="0" selected="selected">--Select--</option>
							
						</select>
					</div>
				</div>
			</div>
		</div>
	
			<div class="row m-0">
			<div class="col-12" id="feedback_div" name="">

				<div class="" id="feedback_1">
					<div class="feedback-que">
						<h5 id="feed_back_url" name="feed_back_url">Q. How was the admission process?</h5>
					</div>
					<div class="feedback-faces">
						<div class="row">
							<div class="face-wrapper">
								<label class="face grayscale face-wtf" for="rate-0"
									data-hint="Oh God! Why?!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-wtf_text">Oh God! Why?!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-disappointed" for="rate-1"
									data-hint="Not Bad...">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-disappointed_text">Not Bad...</div>

							<div class="face-wrapper">
								<label class="face grayscale face-sad" for="rate-2"
									data-hint="It's ok. I guess.">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-sad_text">It's ok. I guess.</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-happy" for="rate-3"
									data-hint="This is great!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-happy_text">This is great!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-love" for="rate-4"
									data-hint="OMG! I love it!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-love_text">OMG! I love it!</div>
						</div>
					</div>
					
					<div class="region_input">
					<div class="input-style-1">
							<label>Please Enter Remarks.</label>
							<textarea placeholder="Please enter specific region" id="feedback_details1" rows="3"></textarea>
					</div>
					</div>

					<ul class="buttons-group m-0 d-flex justify-content-center">
						<li><a href="#" id="feedback-save1"
							class="main-btn send-btn square-btn btn-hover">Send Message <i
								class="bi-cursor-fill"></i>
						</a></li>

					</ul>

				</div>
				
				<div class="" id="feedback_2">
					<div class="feedback-que">
						<h5 id="feed_back_url" name="feed_back_url">Q. How is the infrastructure of institute?</h5>
					</div>
					<div class="feedback-faces">
						<div class="row">
							<div class="face-wrapper ">
								<label class="face grayscale face-wtf" for="rate-0"
									data-hint="Oh God! Why?!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-wtf_text">Oh God! Why?!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-disappointed" for="rate-1"
									data-hint="Not Bad...">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-disappointed_text">Not Bad...</div>

							<div class="face-wrapper">
								<label class="face grayscale face-sad" for="rate-2"
									data-hint="It's ok. I guess.">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-sad_text">It's ok. I guess.</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-happy" for="rate-3"
									data-hint="This is great!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-happy_text">This is great!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-love" for="rate-4"
									data-hint="OMG! I love it!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-love_text">OMG! I love it!</div>
						</div>
					</div>
					
                       
                       <div class="region_input">
					<div class="input-style-1">
							<label>Please Enter Remarks.</label>
							<textarea placeholder="Please enter specific region" id="feedback_details2" rows="3"></textarea>
					</div>
					</div>
					
					<ul class="buttons-group m-0 d-flex justify-content-center">
						<li><a href="#" id="feedback-save" 
							class="main-btn send-btn square-btn btn-hover">Send Message <i
								class="bi-cursor-fill"></i>
						</a></li>

					</ul>

				</div>
				
				<div class="" id="feedback_3">
					<div class="feedback-que">
						<h5 id="feed_back_url" name="feed_back_url">Q. How was the last 6 months curriculum ?</h5>
					</div>
					<div class="feedback-faces">
						<div class="row">
							<div class="face-wrapper ">
								<label class="face grayscale face-wtf" for="rate-0"
									data-hint="Oh God! Why?!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-wtf_text">Oh God! Why?!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-disappointed" for="rate-1"
									data-hint="Not Bad...">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-disappointed_text">Not Bad...</div>

							<div class="face-wrapper">
								<label class="face grayscale face-sad" for="rate-2"
									data-hint="It's ok. I guess.">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-sad_text">It's ok. I guess.</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-happy" for="rate-3"
									data-hint="This is great!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-happy_text">This is great!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-love" for="rate-4"
									data-hint="OMG! I love it!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-love_text">OMG! I love it!</div>
						</div>
					</div>
					
					<div class="region_input">
					<div class="input-style-1">
							<label>Please Enter Remarks.</label>
							<textarea placeholder="Please enter specific region" id="feedback_details3" rows="3"></textarea>
					</div>
					</div>
   
					<ul class="buttons-group m-0 d-flex justify-content-center">
						<li><a href="#" id="feedback-save" 
							class="main-btn send-btn square-btn btn-hover">Send Message <i
								class="bi-cursor-fill"></i>
						</a></li>

					</ul>

				</div>
				
				<div class="" id="feedback_4">
					<div class="feedback-que">
						<h5 id="feed_back_url" name="feed_back_url">Q. How was the faculty teaching?</h5>
					</div>
					<div class="feedback-faces">
						<div class="row">
							<div class="face-wrapper ">
								<label class="face grayscale face-wtf" for="rate-0"
									data-hint="Oh God! Why?!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-wtf_text">Oh God! Why?!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-disappointed" for="rate-1"
									data-hint="Not Bad...">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-disappointed_text">Not Bad...</div>

							<div class="face-wrapper">
								<label class="face grayscale face-sad" for="rate-2"
									data-hint="It's ok. I guess.">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-sad_text">It's ok. I guess.</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-happy" for="rate-3"
									data-hint="This is great!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-happy_text">This is great!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-love" for="rate-4"
									data-hint="OMG! I love it!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-love_text">OMG! I love it!</div>
						</div>
					</div>
					
					<div class="region_input">
					<div class="input-style-1">
							<label>Please Enter Remarks.</label>
							<textarea placeholder="Please enter specific region" id="feedback_details4" rows="3"></textarea>
					</div>
					</div>

					<ul class="buttons-group m-0 d-flex justify-content-center">
						<li><a href="#" id="feedback-save" 
							class="main-btn send-btn square-btn btn-hover">Send Message <i
								class="bi-cursor-fill"></i>
						</a></li>

					</ul>

				</div>
				
				<div class="" id="feedback_5">
					<div class="feedback-que">
						<h5 id="feed_back_url" name="feed_back_url">Q. How was the Over all post graduation experience?</h5>
					</div>
					<div class="feedback-faces">
						<div class="row">
							<div class="face-wrapper ">
								<label class="face grayscale face-wtf" for="rate-0"
									data-hint="Oh God! Why?!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-wtf_text">Oh God! Why?!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-disappointed" for="rate-1"
									data-hint="Not Bad...">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-disappointed_text">Not Bad...</div>

							<div class="face-wrapper">
								<label class="face grayscale face-sad" for="rate-2"
									data-hint="It's ok. I guess.">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-sad_text">It's ok. I guess.</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-happy" for="rate-3"
									data-hint="This is great!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-happy_text">This is great!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-love" for="rate-4"
									data-hint="OMG! I love it!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-love_text">OMG! I love it!</div>
						</div>
					</div>
					
					<div class="region_input">
					<div class="input-style-1">
							<label>Please Enter Remarks.</label>
							<textarea placeholder="Please enter specific region" id="feedback_details5" rows="3"></textarea>
					</div>
					</div>

					<ul class="buttons-group m-0 d-flex justify-content-center">
						<li><a href="#" id="feedback-save" 
							class="main-btn send-btn square-btn btn-hover">Send Message <i
								class="bi-cursor-fill"></i>
						</a></li>

					</ul>

				</div>
						<input type="hidden" name="feedback_cat" id="feedback_cat" value="0"/>
						<input type="hidden" name="feedback_rating" id="feedback_rating" value="0"/>
				
				<div id="feed_back_hid" name="feed_back_hid">
				</div>
			</div>
		</div>
	</div>
</div>
	<!-- theme switch mode start -->
	<div class="theme-setting-wrapper">
		<div id="settings-trigger">
			<i class="lni lni-cog"></i>
		</div>
		<div id="theme-settings" class="settings-panel">
			<a class="settings-close"><i class="lni lni-close"></i></a>			
			<p class="settings-heading mb-0">THEME CUSTOMIZER<span>Customize and Preview in Real time</span></p>
			<div class="theme-customizer-inner">
				<p class="settings-heading">THEME MODE</p>
				<div class="sidebar-bg-options selected" id="sidebar-light-theme">
					<div class="img-ss rounded-circle bg-light me-3"></div>
					Light
				</div>
				<div class="sidebar-bg-options" id="sidebar-dark-theme">
					<div class="img-ss rounded-circle bg-dark me-3"></div>
					Dark
				</div>
				<p class="settings-heading">HEADER SKINS</p>
				<div class="color-tiles">
					<div class="tiles default" id="h-tile-def"><span class="default-tag">Default</span></div>
					<div class="tiles success" id="h-tile-suc"></div>
					<div class="tiles warning" id="h-tile-war"></div>
					<div class="tiles danger" id="h-tile-dan"></div>
					<div class="tiles info" id="h-tile-inf"></div>
					<div class="tiles dark" id="h-tile-dark"></div>				
				</div>				
				<p class="settings-heading">SIDEBAR SKINS</p>
				<div class="color-tiles sidebar-tiles">
					<div class="tiles1 default" id="s-tile-def"><span class="default-tag">Default</span></div>
					<div class="tiles1 success" id="s-tile-suc"></div>
					<div class="tiles1 warning" id="s-tile-war"></div>
					<div class="tiles1 danger" id="s-tile-dan"></div>
					<div class="tiles1 info" id="s-tile-inf"></div>
					<div class="tiles1 dark" id="s-tile-dark"></div>				
				</div>
				<p class="settings-heading">HEADER + SIDEBAR SKIN MIXER</p>
				<div class="color-tiles nav-sidebar-mixer">
					<div class="tiles2 default" id="mix-tile-def"><span class="default-tag">Default</span></div>
					<div class="tiles2 success" id="mix-tile-suc"></div>
					<div class="tiles2 warning" id="mix-tile-war"></div>
					<div class="tiles2 danger" id="mix-tile-dan"></div>
					<div class="tiles2 info" id="mix-tile-inf"></div>
					<div class="tiles2 dark" id="mix-tile-dark"></div>				
				</div>
			</div>
		</div>
	</div>
	<!-- theme switch mode end -->


	<!-- javascript file here -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/db_js/main.js"></script>
</body>


<%-- <body  onFocus="parent_disable();" onclick="parent_disable();resetTimer();" oncontextmenu="return false" >
		<c:if test="${not empty msg}">
			<input type="hidden" name="msg" id="msg" value="${msg}"  disabled="disabled"/>
		</c:if>
		
		<div class="float-sm">
  <div class="fl-fl float-fb">
    <i class="fa fa-facebook i"></i>
    <a> Like us!</a>
  </div>
  <div class="fl-fl float-tw">
    <i class="fa fa-twitter i"></i>
    <a >Follow us!</a>
  </div>
  <div class="fl-fl float-gp">
    <i class="fa fa-google-plus i"></i>
    <a >Recommend us!</a>
  </div>
  <div class="fl-fl float-rs">
    <i class="fa fa-rss i"></i>
    <a >Follow via RSS</a>
  </div>
  <div class="fl-fl float-ig">
    <i class="fa fa-instagram i"></i>
    <a >Follow us!</a>
  </div>
  <div class="fl-fl float-pn">
    <i class="fa fa-pinterest i"></i>
    <a >Follow us!</a>
  </div>
</div>
		
		
	<div class="wrapper">
		 <header id="header" class="header py-1">
			<div class="row mx-0 align-items-center">
			<div class="col-md-6">
				<div class="d-flex align-items-center">
					<a href="#">
						<img src="layout_file/images/logo3.png" class="img-fluid">
						<span class="minist"><span class="hinditext">आयुष मंत्रालय</span><br>Ministry of<br> Ayush</span>
					</a>			   
				   <h2 class="heading my-0 ">Learning Management System</h2>
				</div>				
			</div>
			<div class="col-md-5">
				<div class="d-flex justify-content-end">
				<ul class="head-nav">
				<li class="custom-nav-list"><a class="imghover custom-admin d-flex" href="#">						
						<span class="text-style">
					      Welcome to ${roleloginName}<!-- class="divshow" -->
					    </span>					    						
					    <i class="menu-icon fa fa-user"></i>					    									  
					 </a>
				</li>
				<li class="custom-nav-list">
					<a class="imghover" href="#"><i class="fa fa-clock-o"></i>
					 <span class="divshow">
					     <span
						class="sessiontimeout px-2 py-1"> Session timeout in &nbsp;
						<i class="fa fa-hourglass fa-spin"></i> : <b
						style="color: orangered; min-width: 20px" id="div_timeout">600</b>
					</span>
					</span>
					 </a>
				</li>
				<li class="custom-nav-list">
					 <a class="imghover" href="#"><i class="fa fa-calendar"></i>
			  <span class="divshow">
			    <label class="datetime" id="datetime"></label>
			</span>
			  </a>
				</li>
				<li class="custom-nav-list">
				<span id="notiBellSpanId" class="imghover" style="position: relative; margin-right: -5px;"
				onclick="dropnoti();"> <!--  				<audio id="audio" src="js/audio/audio_file.mp3">   -->
				<!--   				</audio>  -->
				 <i class="fa fa-bell"></i>
				  <span class="dot blink imghover" id="notiSpanId"> </span>
			</span>
			
			<div id="notifications">
			  <div style="display: inline-flex;background: #124749;width: 100%;" >
				<h4 style="color: white;">Notifications</h4>
				<div style=" color: white;right: 0px;position: absolute;" >
				<a onclick="closefunction();" style="color: #ffffff;font-size: 25px;font-weight: 500;top: -4px;">X</a></div>
				</div>
				<div id="appnoti"
					style="overflow: auto; height: 50%; max-height: 200px; "></div>
				<div class="seeAll" style="background: #124749;color: white;">
					<a href="#" onclick="SeeAll_noti();" style="color: #ffffff;">See All</a>
				</div>
			</div>
				</li>				
				<li class="custom-nav-list">
				 <a class="imghover" href="javascript:formSubmit();" type="submit"
				   onclick="localStorage.clear();"><i class="fa fa-sign-out"></i>
			    <span class="divshow">
			     logout 
			    </span>
			  </a>
<!-- 			  <span class="dot blink " id="notiSpanId"> </span>  -->
				</li>
				</ul>			
			 
				</div>			  			 
			</div>
			
			<div class="col-md-1">
			<div class="right-logo">
				<img src="layout_file/images/ayush-grid.png" class="img-fluid">
			</div>				
			</div>
							 
			</div>
			</div>
		</header>
		<div class="middle_content">
			<div class="row mx-0">
				<div class="col-12 col-md-3 col-lg-2 outer_col p-0"
					style="padding-left: 0; position: relative;">
					<a id="menuToggle" class="menutoggle pull-left"><i
						class="fa fa fa-tasks"></i></a>
					<tiles:insertAttribute name="menu" />
				</div>
				<div class="col-12 col-md-9 col-lg-10 outer_col body-content">
					<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>
		<div id="WaitLoader" style="display: none;" align="center">
			<span id="">Processing Data.Please Wait ...<i
				style="font-size: 18px;" class="fa fa-hourglass fa-spin"></i></span>
		</div>
	</div>
</body> --%>

<!-- /////////////////////////notification_16_3///snehal_meera -->
 <c:url value="Policy_ViewUrl" var="Policy_ViewUrl" />
	 <form:form action="${Policy_ViewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="viewid">
		<input type="hidden" name="viewid" id="viewid" value="0"/>
	 </form:form> 
	<script type="text/javascript" nonce="${cspNonce}">

// function dropnoti() {	 
	  
//    // TOGGLE (SHOW OR HIDE) NOTIFICATION WINDOW.
//     $('#notifications').fadeToggle('fast', 'linear', function () {
//        if ($('#notifications').is(':hidden')) {
//            $('#notiBellSpanId').css('background-color', '');
//        }
//        // CHANGE BACKGROUND COLOR OF THE BUTTON.
//        else $('#notiBellSpanId').css('background-color', '');
//    }); 
//    $('#notiSpanId').fadeOut('slow');     // HIDE THE COUNTER.
//  //  Search_noti();
//    return false;
// }
/// Admin side notification count///
// function Search_noti(){
// 		 $.ajaxSetup({
// 				    async: false
// 				});
//   	$.post("getnotifilist?"+key+"="+value,function(j) {	
//   		$("#notiCount").text(j.length-1);
//    	   $("#notiSpanId").text(j.length-1);  	  
//   	      var c = j.length-1;
  	     
//       			  if((j.length-1) == 0){
//       				 $('#notiSpanId').fadeOut('slow');  
//       				$("#appnoti").append ("<label >No Notification  </label> ");
//       			  }
//       			else{
//         			 var notiMsg="";      			   
//         				notiMsg="You Have Unopend Notification";  
// /* /////////////////////////notification_21_3///snehal_meera */			
// 						for(var k=0;k<j.length;k++){
// 							let age = j[k].age.substring(0,8);
// // 							age = age.substring(0,7);
// // 							alert(age);
//         					$("#appnoti").css('background-color', '#c9d0dc');244
//         					/* /////////////////////////notification_16_3///snehal_meera */
// //         					$("#appnoti").append ("<label onclick=statusUpdate(&apos;"+j[k].url_id+"&apos;,&apos;"+j[k].url_value+"&apos;,&apos;"+j[k].id+"&apos;);><hr>"+j[k].message+",&apos;"+age+"&apos;</hr>  </label> ");
        					
// //         					$("#appnoti").append ("<label onclick='ReadData("+j[k].id+");' >"+j[k].message+",&apos;<span class='flex-end'>"+age+"</span>&apos;</label>");
        					
// 							$("#appnoti").append ("<label onclick='ReadData("+j[k].id+");' ><li> <a href='#0'><div class='image'><img src='assets/db_img/lead-1.png' alt='' /></div>"+
// 							"<div class='content'><h6> ${roleloginName}<br><button class='btn info'>Read</button></h6>"+
// 							"<span class='text-regular'>"+j[k].message+"</span><br>"+
// 							"<span>"+age+"</span></div></a></li></label>");
// 						}
//         			var oldcnt = document.getElementById("oldNotiCount").value;      			 
//     				var lastname = sessionStorage.getItem("key");
//     				$("#oldNotiCount").val(c);
//         			if(lastname == null) lastname=0;
//         			 if(c != 0 && c != lastname && notiMsg !=""){
//         				sessionStorage.setItem("key", c);      				 
//        	  		 }
//   			}  
//       	}).fail(function(xhr, textStatus, errorThrown, exception) {
// 		  	 alert(errorThrownMsg(xhr,exception));
// 		 });	
// 	}
	
// function SeeAll_noti(){
	  
// 		 $.ajaxSetup({
// 				    async: false
// 				});
// 	$.post("getseeallnotilist?"+key+"="+value,function(j) {	
// 		$("#notiCount").text(j.length-1);
// 	   $("#notiSpanId").text(j.length-1);  	  
// 	      var c = j.length-1;
//    			  if((j.length-1) == 0){
//    				 $('#notiSpanId').fadeOut('slow');    
//    			  }
//    			else{
//      			 var notiMsg="";      			   
//      				notiMsg="You Have Unopend Notification";  
//      					$("#appnoti").empty();
// 						for(var k=0;k<j.length-1;k++){
// 							let age = j[k].age.substring(0,8);
//      					$("#appnoti").css('background-color', '#c9d0dc');
//     					$("#appnoti").append ("<label onclick=statusUpdate(&apos;"+j[k].url_id+"&apos;,&apos;"+j[k].url_value+"&apos;,&apos;"+j[k].id+"&apos;);><hr>"+j[k].message+",&apos;"+age+"&apos;</hr>  </label> ");
// 		$("#appnoti").append ("<label onclick=ReadData("+j[k].id+"); >"+j[k].message+",&apos;<span class='flex-end'>"+age+"</span>&apos;</label>");

// 						}
//      			var oldcnt = document.getElementById("oldNotiCount").value;      			 
//  				var lastname = sessionStorage.getItem("key");
//  				$("#oldNotiCount").val(c);
//      			if(lastname == null) lastname=0;
//      			 if(c != 0 && c != lastname && notiMsg !=""){
//      				sessionStorage.setItem("key", c);      				 
//     	  		 }
// 			}  
//    	}).fail(function(xhr, textStatus, errorThrown, exception) {
// 		  	 alert(errorThrownMsg(xhr,exception));
// 		 });	
// 	}
/*/////////////////////////notification_16_3///snehal_meera  */	
// function statusUpdate(obj,url_value,id){
// // 	if(url_value!=null){
// // 		var splitemp1 = url_value.split(",");
// // 		for(var i = 0; i < splitemp1.length; i++) {
// // 			var splitemp2 = splitemp1[i].split(":");
// // 			$("input#"+splitemp2[0]).val(splitemp2[1]);
// // 		}
// // 			}
// 	ReadData(id);
// // 	document.getElementById(obj).submit(); 
// }
/*/////////////////////////notification_17_3///snehal_meera  */	
// function ReadData(id){
// 	$.post("UpdateNotification?"+key+"="+value, {id:id}).done(function(j) {
// 		if(j == "0"){
// 		location.reload();	
// 		}
// 	}); 
// 	closefunction();
// 	Search_noti();
// }
// function closeBell(){
// 	var modal = document.getElementById('bell_report');
// 	$("#notiCount").text('0');
// 	modal.style.display = "none";
// }
// function closefunction(){
	
// 	 $('#notifications').hide();
// }
</script>


	<script type="text/javascript" nonce="${cspNonce}">
	$(document).ready(function() {
		

		$(".feedback__label").click(function() {
			$(".feeeback_content").show();
			$(".feedback__label").hide();
		});

		$(".closeButton").click(function() {
			$(".feeeback_content").hide();
			$(".feedback__label").show();
				
		});
		
		$(".face-wtf").click(function() {
			$("#feedback_rating").val(1);
			$(".region_input").show();
		});
		
		$(".face-disappointed").click(function() {
			$("#feedback_rating").val(2);
			$(".region_input").show();
		});
		
		$(".face-sad").click(function() {
			$("#feedback_rating").val(3);
			$(".region_input").show();
		});
		
		$(".face-happy").click(function() {
			$("#feedback_rating").val(4);
			$(".region_input").show();
		});
		
		$(".face-love").click(function() {
			$("#feedback_rating").val(5);
			$(".region_input").show();
		});
		
		
		
		
		
		
		
		
	});
	
	
	
	
	
	
	
	function Feedbacksave(val){
		
		var user_id =  '${userId}'
// 		alert(user_id);
		
		var feedback_for = $("#select_cat").val();
		var feedback_for_sub = $("#select_subcat").val();

		var feedback_rating = $("#feedback_rating").val();
		var feedback_details = $("#feedback_details"+val).val();
		
		
		
	$.post("Feedback_details_save_ctrl?"+key+"="+value, {user_id:user_id,feedback_for:feedback_for,feedback_for_sub:feedback_for_sub,feedback_rating:feedback_rating,feedback_details:feedback_details}).done(function(j) {
// 		if(j == "0"){
// 		location.reload();	
// 		}

alert(j);

	}); 
}
	
	
</script>

	<script type="text/javascript" nonce="${cspNonce}">
	$(document).ready(function() {

		$("#feedback_1").hide();
		$("#feedback_2").hide();
		$("#feedback_3").hide();
		$("#feedback_4").hide();
		$("#feedback_5").hide();
// 		$(".feeeback_container").hide();
		getFeedBackCat();

		checkFeedBackAvail();
	});

	function Admission() {

		$("#feedback_1").show();
		$("#feedback_2").show();
		$("#feedback_3").show();
		$("#feedback_4").show();
		$("#feedback_5").show();

	}

	function Institute() {

		$("#feedback_1").hide();
		$("#feedback_2").show();
		$("#feedback_3").hide();
		$("#feedback_4").hide();
		$("#feedback_5").hide();
	}

	function Curriculum() {

		$("#feedback_1").hide();
		$("#feedback_2").hide();
		$("#feedback_3").show();
		$("#feedback_4").hide();
		$("#feedback_5").hide();
	}

	function Faculty() {

		$("#feedback_1").hide();
		$("#feedback_2").hide();
		$("#feedback_3").hide();
		$("#feedback_4").show();
		$("#feedback_5").hide();
	}

	function PostGraduation() {

		$("#feedback_1").hide();
		$("#feedback_2").hide();
		$("#feedback_3").hide();
		$("#feedback_4").hide();
		$("#feedback_5").show();
	}

	function jay(val) {
		
		
		$("#feedback_1").show();
		$("#feedback_2").hide();
		$("#feedback_3").hide();
		$("#feedback_4").hide();
		$("#feedback_5").hide();

		
		$("#feedback_cat").val(val);
			$(".region_input").hide();
			$("#feed_back_url").text($("#feedback_cat_"+val).val());			
// 		if (val == "1") {
// 			Admission();
// 			$("#feedback_cat").val(val);
// 			$(".region_input").hide();
// 		}
// 		if (val == "2") {
// 			Institute();
// 			$("#feedback_cat").val(val);
// 			$(".region_input").hide();
// 		}
// 		if (val == "3") {
// 			Curriculum();
// 			$("#feedback_cat").val(val);
// 			$(".region_input").hide();
// 		}
// 		if (val == "4") {
// 			Faculty();
// 			$("#feedback_cat").val(val);
// 			$(".region_input").hide();
// 		}
// 		if (val == "5") {
// 			PostGraduation();
// 			$("#feedback_cat").val(val);
// 			$(".region_input").hide();
// 		}

	}
	
	
	function getFeedBackCat(){
		$.post("getFeedBackCat?"+key+"="+value, {}).done(function(j) {
			$("select#select_cat").empty();

			var options = '<option  selected value="' + "0" + '">'
			+ "--Select--" + '</option>';
	for (var i = 0; i < j.length; i++) {
		options += '<option value="' + j[i].id + '" name="'+j[i].id+'" >'
				+ j[i].category + '</option>';
	
	}
	$("select#select_cat").html(options);

		}); 
	}
	
	function getFeedBackCat(){
		$.post("getFeedBackCat?"+key+"="+value, {}).done(function(j) {
			$("select#select_cat").empty();

			var options = '<option  selected value="' + "0" + '">'
			+ "--Select--" + '</option>';
	for (var i = 0; i < j.length; i++) {
		options += '<option value="' + j[i].id + '" name="'+j[i].id+'" >'
				+ j[i].category + '</option>';
	
// 		$("div#feed_back_hid").append('<input type="hidden" name="feedback_cat_'+j[i].id+'" id="feedback_cat_'+j[i].id+'" value="'+j[i].feedback_detail+'"/>');
			
	}
	$("select#select_cat").html(options);

		}); 
	}
	
	function checkFeedBackAvail(){
		$.post("checkFeedBackAvail?"+key+"="+value, {}).done(function(j) {
			if(j == "true"){
				$(".feeeback_container").show();
			}
			
			
		}); 
	}
	
	function getSubCat(){
		var cat = $("#select_cat").val();
		$.post("getFeedBackSubCat?"+key+"="+value, {cat:cat}).done(function(j) {
			$("select#select_subcat").empty();

			var options = '<option  selected value="' + "0" + '">'
			+ "--Select--" + '</option>';
	for (var i = 0; i < j.length; i++) {
		options += '<option value="' + j[i].id + '" name="'+j[i].id+'" >'
				+ j[i].subcategory + '</option>';
	
		$("div#feed_back_hid").append('<input type="hidden" name="feedback_cat_'+j[i].id+'" id="feedback_cat_'+j[i].id+'" value="'+j[i].feedback_detail+'"/>');
			
	}
	$("select#select_subcat").html(options);

		}); 
		
	}
	
</script>


<%-- 	<script type="text/javascript" nonce="${cspNonce}">
(function($)
		{
		    /**
		     * Auto-growing textareas; technique ripped from Facebook
		     *
		     * https://github.com/jaz303/jquery-grab-bag/tree/master/javascripts/jquery.autogrow-textarea.js
		     */
		    $.fn.autogrow = function(options)
		    {
		        return this.filter('textarea').each(function()
		        {
		            var self         = this;
		            var $self        = $(self);
		            var minHeight    = $self.height();
		            var noFlickerPad = $self.hasClass('autogrow-short') ? 0 : parseInt($self.css('lineHeight')) || 0;

		            var shadow = $('<div></div>').css({
		                position:    'absolute',
		                top:         -10000,
		                left:        -10000,
		                width:       $self.width(),
		                fontSize:    $self.css('fontSize'),
		                fontFamily:  $self.css('fontFamily'),
		                fontWeight:  $self.css('fontWeight'),
		                lineHeight:  $self.css('lineHeight'),
		                resize:      'none',
		                'word-wrap': 'break-word'
		            }).appendTo(document.body);

		            var update = function(event)
		            {
		                var times = function(string, number)
		                {
		                    for (var i=0, r=''; i<number; i++) r += string;
		                    return r;
		                };

		                var val = self.value.replace(/</g, '&lt;')
		                                    .replace(/>/g, '&gt;')
		                                    .replace(/&/g, '&amp;')
		                                    .replace(/\n$/, '<br/>&nbsp;')
		                                    .replace(/\n/g, '<br/>')
		                                    .replace(/ {2,}/g, function(space){ return times('&nbsp;', space.length - 1) + ' ' });

		                // Did enter get pressed?  Resize in this keydown event so that the flicker doesn't occur.
		                if (event && event.data && event.data.event === 'keydown' && event.keyCode === 13) {
		                    val += '<br />';
		                }

		                shadow.css('width', $self.width());
		                shadow.html(val + (noFlickerPad === 0 ? '...' : '')); // Append '...' to resize pre-emptively.
		                $self.height(Math.max(shadow.height() + noFlickerPad, minHeight));
		            }

		            $self.change(update).keyup(update).keydown({event:'keydown'},update);
		            $(window).resize(update);

		            update();
		        });
		    };
		})(jQuery);


		var noteTemp =  '<div class="note rounded">'
						+	'<a href="javascript:;" class="button remove">X</a>'
						+ 	'<div class="note_cnt">'
						+		'<textarea class="title" placeholder="Enter note title"></textarea>'
						+ 		'<textarea class="cnt" placeholder="Enter note description here"></textarea>'
						+	'</div> '
						+'</div>';

		var noteZindex = 1;
		function deleteNote(){
		    $(this).parent('.note').hide("puff",{ percent: 133}, 250);
		};

		function newNote() {
		  $(noteTemp).hide().appendTo("#board").show("fade", 300).draggable().on('dragstart',
		    function(){
		       $(this).zIndex(++noteZindex);
		    });
		 
			$('.remove').click(deleteNote);
			$('textarea').autogrow();
			
		  $('.note')
			return false; 
		};

       

		$(document).ready(function() {
			
					
			$("#add_new").click(function(){
		    	$('#board').show();
		    	$('.close_board').show();
		    });
		
		    
// 		    $("#board").height($(document).height());
		    
		    $("#add_new").click(newNote);
		    
		    $('.remove').click(deleteNote);
		    
		    $('.note').hide();
		    newNote();
			  
		    return false;
		    

		});
		
		 
		$(".close_board").click(function(){
	    	$('#board').hide();
	    	$('.close_board').hide();
	    });
</script> --%>
</html>
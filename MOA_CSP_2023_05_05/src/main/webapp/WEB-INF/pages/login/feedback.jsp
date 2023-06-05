<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<!-- single select, search with select start -->
<link href="admin/js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="admin/js/dropDown/select2.min.js" type="text/javascript"></script>
<!-- single select, search with select end -->

<!-- common style start -->
<link rel="stylesheet" href="admin/assets/vendor/common_custom_style.css">
<link rel="stylesheet" href="admin/assets/vendor/common_custom_responsive.css">
<!-- common style end -->
	

<section class="page-content">
<!--  Intro Single  -->
  <section class="intro-single">
    <div class="container">
      <div class="row">
        <div class="col-md-12 col-lg-8">
          <div class="title-single-box">
            <h1 class="title-single">Feedback</h1>
          </div>
        </div>
        <div class="col-md-12 col-lg-4">
          <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
            <ol class="breadcrumb">
              <li class="breadcrumb-item">
                <a href="landingpage">Home</a>
              </li>
              <li class="breadcrumb-item active" aria-current="page">Feedback</li>
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
            <form:form name="feedback" id="feedback" action="" method='POST'>            
                <div class="row"> 
                <div class=" col-lg-12 col-md-12 col-sm-12 mb-3">
                <div class="form-group">
											<label class=" form-control-label">Type of Issue <span class="mandatory">*</span>
											</label> <select class="singleselect form-control form-control-lg" name="type_of_issue" id="type_of_issue" >
												<option value="15" id="server" name=" ">Server Issue</option>
												<option value="25" id="content" name="Student">Content Issue</option>
												<option value="35" id="design" name="Intern">Design Issue</option>												
											</select>
										</div> 
										</div>                
                  <div class=" col-lg-6 col-md-6 col-sm-12 mb-3">
                    <div class="form-group">                    
                      <label for="name1">First Name <span class="mandatory">*</span></label>
                      <input type="text" name="first_name" class="form-control form-control-lg form-control-a" placeholder="Your First Name" id="first_name" maxlength="50">
                    </div>
                  </div> 
                  <div class="col-lg-6 col-md-6 col-sm-12 mb-3">
                    <div class="form-group">                    
                      <label for="name1">Last Name <span class="mandatory">*</span></label>
                      <input type="text" name="last_name" class="form-control form-control-lg form-control-a" placeholder="Your Last Name" id="last_name" maxlength="50">
                    </div>
                  </div> 
                  
                   <div class="col-lg-12 col-md-12 col-sm-12 mb-3">
                    <div class="form-group">                    
                      <label for="Phone">Phone <span class="mandatory">*</span></label>
                      <input type="tel" name="ph_no" class="form-control form-control-lg form-control-a" placeholder="Your Phone No" id="ph_no" maxlength="10">
                    </div>
                  </div> 
                                   
                  <div class="col-lg-12 col-md-12 col-sm-12 mb-3">
                    <div class="form-group">                   
                      <label for="email1">Email <span class="mandatory">*</span></label>
                      <input type="text" name="email" class="form-control form-control-lg form-control-a" placeholder="your-email@gmail.com" id="email" maxlength="50">
                    </div>
                  </div>
                  <div class="col-lg-12 col-md-12 col-sm-12 mb-3">
                    <div class="form-group">                                           
                      <label for="password">your feedback <span class="mandatory">*</span></label>
                      <textarea type="text" name="your_feedback" id="your_feedback" class="form-control form-control-lg form-control-a" rows="5" maxlength="200"></textarea>
                    </div>
                  </div>
                   <input type="hidden" name="feed_hid" id="feed_hid" >
                  <div class="col-lg-12 col-md-12 col-sm-12 text-center">                    
                    <button type="submit" value="submit" class="btn btn-a" id="submit_feedback">Submit</button>                  
                  </div>                                

<!--                    		 <div class="col-md-12 text-center mt-5"> -->
<!--                        <button type="button"  class="main-btn success-btn  btn-hover" id="submit_feedback">Submit</button> -->
<!-- 						</div> -->
                 
                </div>              
            </form:form>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!--  End Register Section  -->
</section>

	
	<script nonce="${cspNonce}" type="text/javascript">
	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('submit_feedback').onclick = function () {
			return feedback();
		}	
	});
	
function feedback() {
	$.ajaxSetup({
	    async: false
	});
	if ($("select#type_of_issue").val() == 0) {
		alert("Please Select Type of Issue");
		$("select#type_of_issue").focus();
		return false;
    }
	if ($("input#first_name").val().trim() == "") {
		alert("Please Enter First Name");
		$("input#first_name").focus();
		return false;
	}
	if ($("input#last_name").val().trim() == "") {
		alert("Please Enter Last Name");
		$("input#last_name").focus();
		return false;
	}
	if ($("input#ph_no").val().trim() == "") {
		alert("Please Enter Phone Number");
		$("input#ph_no").focus();
		return false;
	}
	if ($("input#email").val().trim() == "") {
		alert("Please Enter Email");
		$("input#email").focus();
		return false;
	}
	//var form = $("#Contact_US").serialize();
	
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	
	var type_of_issue=$("#type_of_issue").val();
	var first_name =$("#first_name").val();
	var last_name =$("#last_name").val();
	var ph_no=$("#ph_no").val();
	var email =$("#email").val();
	var your_feedback =$("#your_feedback").val();
 	$.post("feedbackAction?"+key+"="+value,{type_of_issue:type_of_issue,first_name:first_name,last_name:last_name,ph_no:ph_no,email:email,your_feedback:your_feedback},function(j) {
		if(j == "Data Saved Successfully"){
        alert("Your Feedback Has Been Sucessfully Submitted");
        location.reload();
		}
	});

}




</script>

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
            <form name="user_mst" id="user_mst" action="Registration_Action" method='POST'>            
                <div class="row"> 
                <div class=" col-lg-12 col-md-12 col-sm-12 mb-3">
                <div class="form-group">
											<label class=" form-control-label">Type of Issue <span class="mandatory">*</span>
											</label> <select class="singleselect form-control form-control-lg">
												<option value="15" id="server" name=" ">Server Issue</option>
												<option value="25" id="content" name="Student">Content Issue</option>
												<option value="35" id="design" name="Intern">Design Issue</option>												
											</select>
										</div> 
										</div>                
                  <div class=" col-lg-6 col-md-6 col-sm-12 mb-3">
                    <div class="form-group">                    
                      <label for="name1">First Name <span class="mandatory">*</span></label>
                      <input type="text" name="First Name" class="form-control form-control-lg form-control-a" placeholder="Your First Name" id="name1">
                    </div>
                  </div> 
                  <div class="col-lg-6 col-md-6 col-sm-12 mb-3">
                    <div class="form-group">                    
                      <label for="name1">Last Name <span class="mandatory">*</span></label>
                      <input type="text" name="Last Name" class="form-control form-control-lg form-control-a" placeholder="Your Last Name" id="name1">
                    </div>
                  </div> 
                  
                   <div class="col-lg-12 col-md-12 col-sm-12 mb-3">
                    <div class="form-group">                    
                      <label for="Phone">Phone <span class="mandatory">*</span></label>
                      <input type="tel" name="Phone" class="form-control form-control-lg form-control-a" placeholder="Your Phone No" id="name1">
                    </div>
                  </div> 
                                   
                  <div class="col-lg-12 col-md-12 col-sm-12 mb-3">
                    <div class="form-group">                   
                      <label for="email1">Email <span class="mandatory">*</span></label>
                      <input type="text" name="email" class="form-control form-control-lg form-control-a" placeholder="your-email@gmail.com" id="email1">
                    </div>
                  </div>
                  <div class="col-lg-12 col-md-12 col-sm-12 mb-3">
                    <div class="form-group">                                           
                      <label for="password">your feedback <span class="mandatory">*</span></label>
                      <textarea type="text" name="pass" class="form-control form-control-lg form-control-a" rows="5"></textarea>
                    </div>
                  </div>
                  
                  <div class="col-lg-12 col-md-12 col-sm-12 text-center">                    
                    <button type="submit" value="submit" class="btn btn-a">Submit</button>                  
                  </div>                                
                 
                </div>              
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!--  End Register Section  -->
</section>

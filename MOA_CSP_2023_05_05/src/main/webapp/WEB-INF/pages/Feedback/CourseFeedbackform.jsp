<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.my-form .input-select-1 label ,.my-form .select-style-2 label, 
.my-form .input-style-form-check label ,.my-form .simple-table label{
    font-size: 14px;
    font-weight: 500;
    color: #262d3f;
    display: block;
    margin-bottom: 10px;
}
.feedback-table .table thead th:first-child, .feedback-table .table tbody td:first-child {
    width: auto !important;
    min-width: 150px !important;
    max-width: auto !important;
    text-align: center !important;
}
.feedback-table thead th {
    border-top:1px solid #111;
    border-bottom: 1px solid #111 !important;
}
.feedback-table tbody th {
    border-right:1px solid #111;
    border-left: 1px solid #111;
}
.feedback-table th.b-top {
    border-top: 1px solid #111;
}
.feedback-table thead th.b-none{
    border: 0 !important;
    border-bottom: 0 !important;
}
.feedback-table .table thead th:nth-child(2){
    border-left: 1px solid #111;
}
.feedback-table .table thead th:last-child{
    border-right:1px solid #111;
}
.feedback-table .table tbody tr td:last-child{
    border-right:1px solid #111;
}
.my-form .card-style.mb-30 {
    box-shadow: -5px 0 0 0 rgb(96 146 92);
}

.my-form select:focus, .my-form input:focus, .my-form textarea:focus {
    transform: scale(1.02);
    
}
.my-form select, .my-form  input, .my-form textarea , .my-form .select-style-2 .select-position select{
    border-radius: 0px;
}
.drop {
    background: rgba(255, 255, 255, 0.3);
    -webkit-backdrop-filter: blur(10px);
    backdrop-filter: blur(10px);
    border-radius: 10px;
    border-left: 1px solid rgba(255, 255, 255, 0.3);
    border-top: 1px solid rgba(255, 255, 255, 0.3);
    box-shadow: 10px 10px 60px -8px rgb(0 0 0 / 20%);
    position: absolute;
    transition: all 0.2s ease;
}

.drop-1 {
    height: 75px;
    width: 75px;
    top: -10px;
    left: -30px;
    z-index: -1;
}
.drop-2 {
    height: 75px;
    width: 75px;
    bottom: 20px;
    left: -30px;
     z-index: -1;
}
.drop-3 {
    height: 100px;
    width: 100px;
    bottom: 120px;
    right: -50px;
    z-index: -1;
}
.drop-4 {
    height: 120px;
    width: 120px;
    top: -60px;
    right: -60px;
        z-index: -1;
}
.drop-5 {
    height: 60px;
    width: 60px;
    bottom: 170px;
    left: 90px;
    z-index: -1;
}

.form-elements-wrapper{
    position: relative;
}
.rating {
    display: inline-block;
}
.rate {
    float: left;
    height: 46px;
/*     padding: 0 10px; */
    margin-top: -15px;
}

.rate label {
     margin-bottom: 0px !important;
}
.rate:not(:checked) > input {
    position:absolute;
    top:-9999px;
}
.rate:not(:checked) > label {
    float:right;
    width:1em;
    overflow:hidden;
    white-space:nowrap;
    cursor:pointer;
    font-size:40px;
    color:#ccc;
}
.rate:not(:checked) > label:before {
    content: '★ ';
}
.rate > input:checked ~ label {
    color: #ffc700;    
}
.rate:not(:checked) > label:hover,
.rate:not(:checked) > label:hover ~ label {
    color: #deb217; 
    transform: scale(1.2); 
}
.rate > input:checked + label:hover,
.rate > input:checked + label:hover ~ label,
.rate > input:checked ~ label:hover,
.rate > input:checked ~ label:hover ~ label,
.rate > label:hover ~ input:checked ~ label {
    color: #c59b08;
}

.my-form .input-style-2 input:focus, .my-form .input-style-2 textarea:focus , .my-form .select-style-2 .select-position select:focus , .my-form textarea:focus {
    border-color: #60925c;
    background: #fff;
}
span.verypoor {
    color: red;
}
span.poor {
    color: orange;
}
span.average {
    color: #6c757d !important;
}
span.good {
    color: #97ca32;
}
span.excellent {
color: green;
}

.my-form .buttons-group .main-btn{
    border-radius: 4px !important;
}

@media (max-width: 767px){
.drop-1 {
    height: 50px;
    width: 50px;
    top: -10px;
    left: -15px;
    z-index: -1;
}

.drop-2{
    height: 50px;
    width: 50px;
    bottom: 20px;
    left: -15px;
    z-index: -1;
}

}

</style>

<section class="dashboard-page my-form">
	<div class="container-fluid">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							Course Feedback Form
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Course Feedback Form</li>
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
				<div class="col-12">
					<!-- input style start -->
						<div class="card-style mb-30">
							<h6 class="mb-25">Course Feedback Form</h6>
							<div class="row">

							<div class="col-12">
								<div class="alert alert-success">
									<strong>Success!</strong> Your Feedback Form has been submitted.
								</div>
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="input-style-2">
										<label>Course ID<span class="mandatory">*</span></label> 
										<input type="text" id="" name="name" class="autocomplete"
											autocomplete="off" placeholder="Enter Your Faculty ID">
									</div>
									<!-- end input -->
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="select-style-2">
										<label>Course<span class="mandatory">*</span></label>
									<div class="select-position">
										<select>
											<option value="">Select Course</option>
											<option value="">Course one</option>
											<option value="">Course two</option>
										</select>
									</div>
								</div>
									<!-- end select -->
								</div>
								
								<!-- <div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="input-style-2">
										<label>Email or Phone No.<span class="mandatory">*</span></label> 
										<input type="text" id="" name="name" class="autocomplete"
											autocomplete="off" placeholder="Enter Your Email or Phone No.">
									</div>
									end input
								</div> -->
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								<div class="input-style-2">
					<label>To Date<span class="mandatory">*</span></label>
					<input type="text" name="date_of_reg" id="date_of_reg" maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control-sm form-control effect-9 hasDatepicker" onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); validateUptoR();" aria-required="true" autocomplete="off" value="DD/MM/YYYY">
					<button type="button" class="ui-datepicker-trigger">
					<span class="icon"><i class="lni lni-calendar"></i></span></button>
				</div>
				</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="select-style-2">
										<label>Semester<span class="mandatory">*</span></label>
									<div class="select-position">
										<select>
											<option value="">Select Semester</option>
											<option value="">Semester One</option>
											<option value="">Semester Two</option>
										</select>
									</div>
								</div>
									<!-- end select -->
								</div>
								

							
			<div class="col-12">
						<div class="table-wrapper table-responsive feedback-table custom-datatable-p simple-table">
						<h4 class="text-center mb-1"><u>Fill Feedback Table</u></h4> 
		   <table class="table">
            <thead>
            <tr>
              <th  class="bg-none b-none">
<!--                Instructor Feedback -->
              </th>
              <th>
                Excellent 
              </th>
              <th>
              Very Good 
              </th>
              <th>
                Good 
              </th>
              <th>
                 Fair 
              </th>
              <th>
                Poor 
              </th>
              <th>
              Very Poor 
              </th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <th class="b-top">
                The course as a whole was
              </th>
              <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo1" name="gender" class="form-check-input" value="0" required="">
					<label for="demo1" class="form-check-label"></label>																													
				  </div>
               </td>
                <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo2" name="gender" class="form-check-input" value="0" required="">
					<label for="demo2" class="form-check-label"></label>																													
				  </div>
               </td>
               <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo3" name="gender" class="form-check-input" value="0" required="">
					<label for="demo3" class="form-check-label"></label>																													
				  </div>
               </td>
               <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo4" name="gender" class="form-check-input" value="0" required="">
					<label for="demo4" class="form-check-label"></label>																													
				  </div>
               </td>
                <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo5" name="gender" class="form-check-input" value="0" required="">
					<label for="demo5" class="form-check-label"></label>																													
				  </div>
               </td>
                <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo11" name="gender" class="form-check-input" value="0" required="">
					<label for="demo11" class="form-check-label"></label>																													
				  </div>
               </td>
            </tr>
            <tr>
              <th>
                The course content was
              </th>
              <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo6" name="gender" class="form-check-input" value="0" required="">
					<label for="demo6" class="form-check-label"></label>																													
				  </div>
               </td>
                <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo27 name="gender" class="form-check-input" value="0" required="">
					<label for="demo7" class="form-check-label"></label>																													
				  </div>
               </td>
               <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo8" name="gender" class="form-check-input" value="0" required="">
					<label for="demo8" class="form-check-label"></label>																													
				  </div>
               </td>
               <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo9" name="gender" class="form-check-input" value="0" required="">
					<label for="demo9" class="form-check-label"></label>																													
				  </div>
               </td>
                <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo10" name="gender" class="form-check-input" value="0" required="">
					<label for="demo10" class="form-check-label"></label>																													
				  </div>
               </td>
                <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo12" name="gender" class="form-check-input" value="0" required="">
					<label for="demo12" class="form-check-label"></label>																													
				  </div>
               </td>
            </tr>
            
          </tbody></table>
             </div>   
			</div>
			<!-- 	end col -->
			
			
			
			<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="input-style-1 rating mb-2">
									<label>Overall Rating<span class="mandatory">*</span></label> 
									<div class="rate">
    <input type="radio" id="star5" name="rate" value="5" />
    <label for="star5" title="text" class="star5">5 stars</label>
    <input type="radio" id="star4" name="rate" value="4" />
    <label for="star4" title="text" class="star4">4 stars</label>
    <input type="radio" id="star3" name="rate" value="3" />
    <label for="star3" title="text"class="star3">3 stars</label>
    <input type="radio" id="star2" name="rate" value="2" />
    <label for="star2" title="text" class="star2">2 stars</label>
    <input type="radio" id="star1" name="rate" value="1" />
    <label for="star1" title="text" class="star1">1 star</label>
  </div>
									
									
									</div>
									
									
									<div class="row">
									<div class="col-12 col-sm-12 col-md-4 col-lg-4">
									
									<div class="ratingvalue d-block">
									<span class="verypoor">Very Poor</span>
									<span class="poor">Poor</span>
									<span class="average">Average</span>
									<span class="good">Good</span>
									<span class="excellent">Excellent</span>
									</div>
									</div>
									
<div class="col-12 col-sm-12 col-md-8 col-lg-8">
								<div id="verypoor">
									<div class="input-style-1">
										<label>Why it is region ?</label>
										<textarea placeholder="please enter region" rows="2"></textarea>
									</div>
								</div>
								</div>
								</div>

								<!-- end input -->
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
								<div class="input-style-1">
                  <label>Message or Comment</label>
                  <textarea placeholder="Message" rows="5"></textarea>
                </div>
                </div>
                
                </div>

							<ul class="buttons-group mainbtn">
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Submit" onclick="success();"/>
								</li>
								<li><a href="Country" class="main-btn dark-btn n btn-hover"
									type="button">Reset</a></li>
							</ul>
						</div>

						<!-- end card -->
				</div>
				
				<div class="drops">
    <div class="drop drop-1"></div>
    <div class="drop drop-2"></div>
  </div>
			</div>
		</div>
	</div>
</section>



<script type="text/javascript">




function success() {
	
	$(".alert-success").show();
	
}

$(document).ready(function(){
	
    $(".verypoor").hide();
	$(".poor").hide();
	$(".average").hide();
	$(".good").hide();
	$(".excellent").hide();
	
	$("#verypoor").hide();

$(".star1").click(function(){
	$(".verypoor").show();
	$(".poor").hide();
	$(".average").hide();
	$(".good").hide();
	$(".excellent").hide();
	
	$("#verypoor").show();
});
	
$(".star2").click(function(){
	$(".verypoor").hide();
	$(".poor").show();
	$(".average").hide();
	$(".good").hide();
	$(".excellent").hide();
	
	$("#verypoor").hide();
	
	});
	
$(".star3").click(function(){
	$(".verypoor").hide();
	$(".poor").hide();
	$(".average").show();
	$(".good").hide();
	$(".excellent").hide();
	
	$("#verypoor").hide();
	});
	
$(".star4").click(function(){
	$(".verypoor").hide();
	$(".poor").hide();
	$(".average").hide();
	$(".good").show();
	$(".excellent").hide();
	
	$("#verypoor").hide();
	});
	
$(".star5").click(function(){
	$(".verypoor").hide();
	$(".poor").hide();
	$(".average").hide();
	$(".good").hide();
	$(".excellent").show();
	
	$("#verypoor").hide();
	});
	
	
});
	
</script>







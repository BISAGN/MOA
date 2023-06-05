<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>



<link rel="stylesheet" href="admin/assets/db_css/db_style.css">
<link href="admin/js/jquery/jquery-ui.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="admin/js/Calender/Calender_jquery-ui.css">
<link href="admin/js/Calender/jquery-ui.css" rel="Stylesheet"></link>

<link rel="stylesheet"href="admin/assets/councelling/css/jquery.multiselect.css">
<script src="admin/js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="admin/js/Calender/datePicketValidation.js"></script>

<script src="admin/assets/db_js/CommonValidation.js"></script>

<!-- <script src="admin/assets/db_js/jquery.blockUI.js"></script> -->

<script src="admin/assets/db_js/instituteinformation.js"></script>
<script src="admin/assets/councelling/js/jquery.multiselect.js"></script>


<link rel="stylesheet"
	href="admin/assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="admin/assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">

<script type="text/javascript"
	src="admin/assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="admin/assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

	

 
 <style nonce="${cspNonce}">
.custom_head h1, .InstituteInformation .h1{
     font-weight: 500;
    font-size: calc(1.375rem + 1.5vw) !important;
}

@media (min-width: 1200px){
.custom_head h1, .InstituteInformation .h1{
    font-size: 2.5rem !important;
}
}
 table.dataTable thead th, table.dataTable thead td {
    border-bottom: 1px solid #465b76;
    background-color: #465b76;
    vertical-align: middle;
}

</style> 

<section class="page-content">
	<!--  Intro Single  -->
	<section class="intro-single custom_head">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-8">
					<div class="title-single-box">
						<h1 class="title-single">Institute Information</h1>
						<!--    <span class="color-text-a">Your message has been sent. Thank you! </span> -->
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="landingpage">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Institute Information</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single-->

	<section class="dashboard-page studentenrollmen" id="instituteinformationdiv">
		<div class="container" id="studentenrollmentdiv">
			<div class="form-elements-wrapper">
				<div class="row">
					<div class="col-12">
						<!-- input style start -->
						<div class="card-style mb-30">
							<h6 class="mb-25">Institute Information</h6>

							<div class="row">
								<div class="col-12">
									<div class="col-12 col-sm-12 col-md-12 col-lg-12">
										<div class="input-style-2 mb-0">
											<lable>Choose System</lable>
										</div>
										<div class="input-style-form-check" id="sysytemdiv"
											name="sysytemdiv"></div>
										<!-- end input -->
									</div>
									<div class="col-12 col-sm-12 col-md-12 col-lg-12">
										<div class="input-style-2 mb-0">
											<lable>Select Institute</lable>
										</div>
										
										
										<div class="input-style-form-check">
											<select class="form-select" id="insdiv" name="insdiv"
												multiple="multiple">

											</select>
										</div>
										<ul class="buttons-group mainbtn">
											<li><input id="insinfbtn" name="insinfbtn"
												class="main-btn info-btn btn-hover" type="button"
												value="Search" tabindex="13" /></li>
											<li><a id="reset" class="main-btn dark-btn n btn-hover"
												type="reset" value="Clear" tabindex="14">Reset</a></li>
										</ul>
										<!-- end input -->
									</div>
								</div>
							</div>
                           
                          
						<div class="custom-d-none" id="tablediv">
							<div class="row"  >
								<div class="col-12">
									<div class="card-style mb-30">
										<div class="table-wrapper table-responsive custom-datatable-p">
											<table class="table" id="institutemasterotherdatatable">
												<thead>
													<tr>
														<th ><h6>No</h6></th>
														<th><h6>System Type</h6></th>
														<th><h6>University</h6></th>
														<th><h6>Institute Code</h6></th>
														<th><h6>Institute Name</h6></th>
														<th><h6>Institute Image</h6></th>
														<th><h6>Institute Abbreviation</h6></th>
														<th><h6>Institute Unique Code</h6></th>
														<th><h6>Total Seats</h6></th>
														<th><h6>Phone Number</h6></th>
														<th><h6>Email ID</h6></th>
														<th><h6>Country</h6></th>
														<th><h6>State</h6></th>
														<th><h6>District</h6></th>
														<th><h6>Address</h6></th>
														<th><h6>View Other Details</h6></th>
													</tr>
													<!-- 						end table row -->
												</thead>
												<tbody>
												</tbody>
											</table>
											<!-- 				end table -->
										</div>
									</div>
									<!-- 		end card -->
								</div>
								<!-- 	end col -->
							
						</div>
						</div>
						</div>
					</div>

					<!-- end card -->
				</div>
			</div>
		</div>





<!-- 		<div class="modal fade" id="exampleModalToggle" aria-hidden="true" -->
<!-- 			aria-labelledby="exampleModalToggleLabel" tabindex="-1"> -->
<!-- 			<div class="modal-dialog modal-dialog-centered"> -->
<!-- 				<div class="modal-content"> -->
<!-- 					<div class="modal-body alert-success"> -->
<!-- 						<div class="check-background"> -->
<!-- 							<svg viewBox="0 0 65 51" fill="none" -->
<!-- 								xmlns="http://www.w3.org/2000/svg"> -->
<!-- 				<path d="M7 25L27.3077 44L58.5 7" stroke="white" stroke-width="13" -->
<!-- 									stroke-linecap="round" stroke-linejoin="round"></path> -->
<!-- 			</svg> -->
<!-- 						</div> -->
<!-- 						<div class="d-flex justify-content-center Merit-text" -->
<!-- 							id="successmsg" name="successmsg"></div> -->
<!-- 						<div class="d-flex justify-content-center Merit-text"> -->
<!-- 							<a href="#" onclick="HideSuccessmodal()" -->
<!-- 								class="main-btn success-btn-outline square-btn btn-hover" -->
<!-- 								id="reset" name="reset">Ok</a> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->


		<div class="modal fade error" id="errorModalToggle" aria-hidden="true"
			aria-labelledby="errorModalToggleLabel" tabindex="-1">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-body alert-success">
						<div class="check-background">
							<img src="https://100dayscss.com/codepen/alert.png" width="50"
								height="70">
						</div>
						<div class="d-flex justify-content-center Merit-text"
							id="errormsg" name="errormsg"></div>
						<div class="d-flex justify-content-center Merit-text">
							<a href="#" class="main-btn danger-btn-outline square-btn btn-hover"
								id="reset1" name="reset1">Ok</a>
						</div>


					</div>
				</div>
			</div>
		</div>
<!-- 		<div class="modal fade" id="okcancleModalToggle" aria-hidden="true" -->
<!-- 			aria-labelledby="okcancleModalToggle" tabindex="-1"> -->
<!-- 			<div class="modal-dialog modal-dialog-centered"> -->
<!-- 				<div class="modal-content"> -->
<!-- 					<div class="modal-body" id="confirmid" name="confirmid"></div> -->
<!-- 					<div class="modal-footer"> -->
<!-- 						                  <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button> -->
<!-- 						<ul class="buttons-group"> -->
<!-- 							<li><a href="#0" -->
<!-- 								class="main-btn success-btn-outline square-btn btn-hover" -->
<!-- 								id="okmsgid">Pay NOW</a></li> -->
<!-- 							<li><a href="#0" -->
<!-- 								class="main-btn danger-btn-outline square-btn btn-hover" -->
<!-- 								id="cancelmsgid">Cancel</a></li> -->
<!-- 						</ul> -->

<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
	</section>
	</section>
	<div class="modal fade" id="okModalToggle" aria-hidden="true"
		aria-labelledby="okModalToggle" tabindex="-1">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">

					<div class="row">
						<!-- 					<div class="col-12"> -->

						<table class="table" id="viewotherdetailtable">
							<thead>
								<tr>

									<th><h6>Category</h6></th>
									<th><h6>Fees Type</h6></th>
									<th><h6>Fees</h6></th>
									<th><h6>Hostel Facility Available</h6></th>
									<th><h6>Library Facility Available</h6></th>

								</tr>
								<!-- 														end table row -->
							</thead>
							<tbody id="viewotherdetailtablebody">
							</tbody>
						</table>
						<!-- 										end table -->

						<!-- 						end card -->
						<!-- 					</div> -->
						<!-- 	end col -->
					</div>
				</div>
				<div class="modal-footer">
					<!--                   <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button> -->
					<ul class="buttons-group">
						<li><a href="#0" class="btn btn-danger" id="cls_modelid">Close</a></li>
					</ul>

				</div>
			</div>
		</div>
	</div>

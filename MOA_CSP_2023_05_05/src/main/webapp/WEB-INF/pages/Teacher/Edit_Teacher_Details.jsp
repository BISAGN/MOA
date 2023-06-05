<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<!-- <script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- datatable style and js start-->
<!-- <link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css"> -->
<!-- <link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css"> -->
<!-- <script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script> -->
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script src="js/miso/commonJS/addmorefunctionality.js" type="text/javascript"></script> 
<link rel="stylesheet" href="admin/assets/db_css/db_custom_style.css">
<link rel="stylesheet" href="admin/assets/db_css/db_style.css">

<!-- <style>
div#ui-datepicker-div {
	width: min-content !important;
}
div#ui-datepicker-div table thead {
	width: 100% !important;
	background-color: #257758;
}
div#ui-datepicker-div table tbody {
	height: auto !important;
}
.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all
	{
	background: #198754 !important;
}

img.ui-datepicker-trigger {
    max-height: 24px !important;
}
a {
    color: #fff;
    text-decoration: none !important
}
</style> -->
 <body>
 
 
 <!--**********************************
        Main wrapper start
    ***********************************-->
 
  <!--   <div id="main-wrapper">
<div class="card-header"> <h5>Edit Faculty Details</h5></div>
        <div class="content-body"> -->
	 <section class="search_regulation">
      <div class="container-fluid">
        <!-- title-wrapper start -->
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-md-6">
              <div class="title mb-30">
                <h2>Edit Faculty Details</h2>
              </div>
            </div>
            <!-- end col -->
            <div class="col-md-6">
              <div class="breadcrumb-wrapper mb-30">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">
                   <li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                     Edit Faculty Details
                    </li>
                  </ol>
                </nav>
              </div>
            </div>
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
            <!-- row -->
 <div class="teacher-wrapper tunnel-form">
          <div class="row">
          
          <div class="col-lg-12">
              <!-- input style start -->
              <div class="card-style mb-30">
                     <div class="content-body">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <form action="edit_tea_dtlAction?${_csrf.parameterName}=${_csrf.token}" name="edit_teacher_detail" id="step-form-horizontal" class="step-form-horizontal" 
                        	method='POST' modelAttribute="edit_tea_dtl_CMD" enctype="multipart/form-data">
                            <div>
                                <h4>Personal Details</h4>
                                <section>
                                    <div class="row">
                                    
                                    <div class="col-lg-4 col-md-6 col-sm-12">

																<label class="buttonmerge">First Name<strong class="mandatory">
																</strong></label>
																<div class="form-group stepform-groupadd">

																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="cand_prefix" id="cand_prefix" class="form-control qualification">
																				<option value="1">Mr</option>
																				<option value="2">Ms</option>
																				<option value="3">Mrs</option>
																			</select>

																		</div>
																	</div>
																	<div class="input-style-2 form-group buttonmergeinput">

																		<input type="text" name="first_name" id="first_name" class="form-control"
                                                    placeholder="Enter First Name" maxlength="50">
																	<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">	
																	</div>
																	
																</div>
															</div>
                                        
                                              <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label>Middle Name <strong class="mandatory">  </strong></label>
                 <input type="text" name="middle_name" id="middle_name" class="form-control"
                                                    placeholder="Enter Middle Name"  maxlength="50">
                </div>
                </div>
                
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label>Last Name <strong class="mandatory">  </strong></label>
                 <input type="text" name="last_name" id="last_name" class="form-control"
                                                    placeholder="Enter Last Name"  maxlength="50" >
                </div>
                </div>
                
                                        <div class="col-lg-4 col-md-6 col-sm-12">
														<div class="select-style-1">
															<label for="fname">Gender <strong class="mandatory"> 
															</strong></label>
															<div class="select-position">
																 <select name="gender" id="gender" class="form-control">
												<option value="0">--Select--</option>
													<c:forEach var="item" items="${getgenderList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>

															</div>
														</div>
													</div>
                                        
                                           <div class="col-lg-4 col-md-6 col-sm-12">
														<div class="input-style-2">
																	<label>Date of Birth </label> 																	
																	<input type="text" name="date_of_birth" id="date_of_birth" maxlength="10"
																		
																		class="form-control-sm form-control effect-9 "
																		
																		aria-required="true" autocomplete="off"
																		value="DD/MM/YYYY">
																

																</div>
													</div>
                                        
                                         <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">Father's Name <strong class="mandatory">  </strong></label>
               <input type="text" name="father_name" id="father_name" class="form-control" placeholder="Enter Father's Name"
                                                 maxlength="50">
                </div>
                </div>
                
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">Mother's Name <strong class="mandatory">  </strong></label>
               <input type="text" name="mother_name" id="mother_name" class="form-control" placeholder="Enter Mother's Name" 
                                                     maxlength="50">
                </div>
                </div>
                                        
                                        <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">Spouse's Name <strong class="mandatory">  </strong></label>
                <input type="text" name="spouse_name" id="spouse_name" class="form-control" placeholder="Enter Spouse's Name" 
                                                 maxlength="50">
                </div>
                </div>
                                        
                                          <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">Mobile No <strong class="mandatory">  </strong></label>
               <input type="text" name="mobile_no" id="mobile_no" class="form-control" placeholder="Enter Mobile No." 
                                                	 maxlength="10" minlength="10"  >
                </div>
                </div>
                
                                        
                                          <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">Email Id <strong class="mandatory">  </strong></label>
             <input type="text" name="email" id="email" class="form-control" placeholder="abc@example.com" 
                                                	maxlength="70"  pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" value="${email_txt}">
                                                	 
                </div>
                </div>
                                        
               <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">Aadhaar Card <strong class="mandatory">  </strong></label>
                  <div class="form-group stepform-groupadd">
              <input type="text" name="aadhar_no1" id="aadhar_no1" class="form-control" maxlength="4"
                                                		>
                                                		 <input type="text" name="aadhar_no2" id="aadhar_no2" class="form-control" maxlength="4"
                                                     	>
                                                		 <input type="text" name="aadhar_no3" id="aadhar_no3" class="form-control" maxlength="4"
                                                     	>
                                                     	<input type="hidden" id="pers_aadhar_no_hd" name="pers_aadhar_no_hd" class="form-control" value="${pers_aadhar_no77}"></input> 
                                        
                             </div>                   	 
                </div>
                </div>
                          
                                        <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">PAN No <strong class="mandatory">  </strong></label>
             <input type="text" name="pan_no" id="pan_no" class="form-control"
                                                    placeholder="Enter PAN No." maxlength="10" minlength="10">            	 
                </div>
                </div>
<!--                 								unused content -->
                
<!--                                          <div class="col-lg-4 col-md-6 col-sm-12" style="display:none"> -->
<!-- 														<div class="select-style-1"> -->
<!-- 															<label for="fname">Academic Qualification <strong class="mandatory"></strong></label> -->
<!-- 															<div class="select-position"> -->
<!-- 																<select name="academic_quali" id="academic_quali" class="form-control" onchange="FnMd_dtl(); hideshw_Degree();" onclick=""> -->
<!-- 																	<option value="0">--Select Academic Qualification--</option> -->
<!-- 																	<option value="1">Degree</option> -->
<!-- 																    <option value="2">MD</option> -->
<!-- 																    <option value="3">PhD</option> -->
<!-- 																</select> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 													<div class="col-lg-4 col-md-6 col-sm-12" id="Type_of_degree" style="display: none;"> -->
<!-- 														<div class="select-style-1"> -->
<!-- 															<label for="fname">Type of Degree <strong class="mandatory"></strong></label> -->
<!-- 															<div class="select-position"> -->
<!-- 																 <select name="sub_quali_degree" id="sub_quali_degree" class="form-control"> -->
<!-- 																	<option value="0">--Select Degree--</option> -->
<!-- 																	<option value="1">Allopatic</option> -->
<!-- 																    <option value="2">Homopatic</option> -->
<!-- <!-- 																    <option value="3">PhD</option> --> -->
<!-- 																</select> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														</div> -->
<!-- 													<div class="col-lg-4 col-md-6 col-sm-12" id="sub_degree" style="display: none;"> -->
<!-- 														<div class="select-style-1"> -->
<!-- 															<label for="fname">Course<strong class="mandatory"></strong></label> -->
<!-- 															<div class="select-position"> -->
<!-- 																 <select name="sub_deg_course" id="sub_deg_course" class="form-control" > -->
<!-- 																	<option value="0">--Select--</option> -->
<!-- 																	<option value="1">BHMS</option> -->
<!-- 																    <option value="2">MBBS</option> -->
<!-- 																    <option value="3">Graded</option> -->
<!-- 																</select> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														</div> -->
<!-- 													<div class="col-lg-4 col-md-6 col-sm-12"> -->
<!-- 														<div id="md_detl" style="display: none;"> -->
<!-- 														<div class="input-style-2"> -->
<!-- 															<label for="fname">Subject <strong class="mandatory">  </strong></label> -->
<!-- 							               					 	<input type="text" name="subject" id="subject" class="form-control" -->
<!-- 							                        				placeholder="Enter Subject" onkeypress="return onlyAlphaNumeric(event, this)" maxlength="50"> -->
<!-- 														</div> -->
<!-- 														</div> -->
<!-- 													</div> -->
													
													
                							</div>
               							 </section>
                                      <section>
                                    <div class="row">
             					 <div class="col-md-12">
								  <div class="line_design"> 
									<h6 class="line_text mb-25">Permanent Address<strong class="mandatory">* </strong></h6>		
									<span class="line"></span>
								  </div>
							    </div>
							    
							     <div class="col-lg-6 col-md-6 col-sm-12">
					                <div class="input-style-2">
					                  <label for="fname">Address Line 1 <strong class="mandatory">  </strong></label>
					             		<input type="text" name="per_add_line1" id="per_add_line1" class="form-control" placeholder="Address Line 1" 
					                             maxlength="100" >           	 
					                </div>
					             </div>
                                 <div class="col-lg-6 col-md-6 col-sm-12">
						            <div class="input-style-2">
						            	<label for="fname">Address Line 2 <strong class="mandatory">  </strong></label>
						              		<input type="text" name="per_add_line2" id="per_add_line2" class="form-control" placeholder="Address Line 2" 
						                              maxlength="100" >          	 
						            </div>
						         </div>
                                 <div class="col-lg-6 col-md-6 col-sm-12">
									<div class="select-style-1">
										<label for="fname">State <strong class="mandatory"></strong></label>
											<div class="select-position">
												<select name="per_state" id="per_state" class="form-control"><!-- style="text-transform: uppercase" -->
													<option value="0" selected="selected"> -- Select State -- </option>
													<c:forEach var="item" items="${getMedStateName}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
										<div class="select-style-1">
											<label for="fname">District <strong class="mandatory"></strong></label>
												<div class="select-position">
													<select name="per_district" id="per_district" class="form-control" >
														<option value="0">--Select District--</option>
													</select>
												</div>
										</div>
									</div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
						                <div class="input-style-2">
						                  	<label for="fname">City/Village <strong class="mandatory">  </strong></label>
						            			<input type="text" name="per_village" id="per_village" class="form-control" placeholder="Enter City/Village" 
						                                maxlength="100" >         	 
						                </div>
						            </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
						                <div class="input-style-2">
						                	<label for="fname">Pin Code <strong class="mandatory">  </strong></label>
						           				<input type="text" name="per_pincode" id="per_pincode" class="form-control" placeholder="Enter Pin Code" 
						                               maxlength="6"  >        	 
						                </div>
						            </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
						                <div class="input-style-2">
						                	<label for="fname">Phone No <strong class="mandatory">  </strong></label>
						           				<input type="text" name="per_phn_no" id="per_phn_no" class="form-control" placeholder="Enter Phone No." maxlength="10" 
						                                minlength="10"   >       	 
						                </div>
						            </div>
                                
                                        <div class="col-lg-12 col-md-12 col-sm-12">
															<div class="form-check checkbox-style mb-20">
																<input class="form-check-input" type="checkbox" id="check_address" name="check_address" > <label class="form-check-label"> <span class="text-heighlight"> Same as Permanent Address</span> 
																</label>
															</div>
														</div>   
                            
					             <div class="col-md-12">
									  <div class="line_design"> 
										<h6 class="line_text mb-25">Present Address<strong class="mandatory">* </strong></h6>		
										<span class="line"></span>
									  </div>
								    </div>
								    
								    <div class="col-lg-6 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">Address Line 1 <strong class="mandatory">  </strong></label>
              <input type="text" name="present_add_line1" id="present_add_line1" class="form-control" placeholder="Enter Address-Line-1" 
                                                	maxlength="100" ></div>
                </div>
                                        
                                        <div class="col-lg-6 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">Address Line 2 <strong class="mandatory">  </strong></label>
              <input type="text" name="present_add_line2" id="present_add_line2" class="form-control" placeholder="Enter Address-Line-2"
                                                	 maxlength="100" > </div>
                </div>
                              
                                             <div class="col-lg-6 col-md-6 col-sm-12">
														<div class="select-style-1">
															<label for="fname">State <strong class="mandatory"> 
															</strong></label>
															<div class="select-position">
																 <select name="present_state" id="present_state" class="form-control" ><!-- style="text-transform: uppercase" -->
												<option value="0" selected="selected"> -- Select State -- </option>
												<c:forEach var="item" items="${getMedStateName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
													
												</select>
															</div>
														</div>
													</div>
                                        
                                          <div class="col-lg-6 col-md-6 col-sm-12">
														<div class="select-style-1">
															<label for="fname">District <strong class="mandatory"> 
															</strong></label>
															<div class="select-position">
																 <select name="present_district" id="present_district" class="form-control">
												<option value="0">--Select District--</option>
													
												</select>
															</div>
														</div>
													</div>
                                        
                                        <div class="col-lg-6 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">City/Village <strong class="mandatory">  </strong></label>
           <input type="text" name="present_village" id="present_village" class="form-control" placeholder="Enter City/Village" 
                                                	 maxlength="100" > </div>
                </div>
                                        
                                        <div class="col-lg-6 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">Pin Code <strong class="mandatory">  </strong></label>
           <input type="text" name="present_pincode" id="present_pincode" class="form-control" placeholder="Enter Pin Code" 
                                                maxlength="6"  ></div>
                </div>
                                
                                        <div class="col-lg-6 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">Phone No <strong class="mandatory">  </strong></label>
             <input type="text" name="present_phn_no" id="present_phn_no" class="form-control"placeholder="Enter Phone No." 
                                                	 maxlength="10" minlength="10"></div>
                </div>
                                    </div>
                                    
                                </section>
                                <h4>Registration Details</h4>
                                <section>
                                    <div class="row">
                                     <div class="col-lg-6 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">State Registration No <strong class="mandatory">  </strong></label>
            <input type="text" name="state_reg_no" id="state_reg_no" class="form-control"
                                                    placeholder="Enter State Registration No."  maxlength="50"></div>
                </div>
                                           <div class="col-lg-6 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">Name of the State Board <strong class="mandatory">  </strong></label>
            <input type="text" name="state_board_name" id="state_board_name" class="form-control" placeholder="Enter Name of the State Board" 
                                                	 maxlength="100">
                </div>
                </div>
                                        <div class="col-lg-6 col-md-6 col-sm-12">
														<div class="input-style-2">
															<label>Date of Registration </label> <input type="text" name="date_of_reg" id="date_of_reg"
												maxlength="10" 
												class="form-control"
												
												aria-required="true" autocomplete="off" value="DD/MM/YYYY"
												placeholder="Select Date of Registration">
														</div>
													</div>
                                      
                                        
                                          <div class="col-lg-6 col-md-6 col-sm-12">
                <div class="input-style-2">
                  <label for="fname">Central Registration No <strong class="mandatory">  </strong></label>
            <input type="text" name="central_reg_no" id="central_reg_no" class="form-control" placeholder="Enter Central Registration No."
                                                	 maxlength="50"></div>
                </div>
                                    </div>
                                </section>
                                 <h4>Previous Experience</h4>
                                <section>
                                	<div class="card-style mb-30">

												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">


															<div class="table-wrapper table-responsive custom-table">
																<table class="table" id="att_Tb">
																	<thead>
																		<tr>
																			<th>Ser No</th>
																			<th>Organization Name</th>
																			<th>Department Name</th>
																			<th>Designation</th>
																			<th>From Date</th>
																			<th>To Date</th>
																			<th>Upload File</th>
																			<th>Action</th>
																		</tr>
																		<!-- end table row-->
																	</thead>
																	<tbody id="att_Tbbody">
																		<tr id="tr_id_att">
																	
																		</tr>
																		<!-- end table row -->

																	</tbody>
																</table>
																<!-- end table -->
															</div>

															<!-- end card -->
														</div>
														<!-- end col -->
													</div>
													<!-- end row -->
												</div>
											</div>
                                </section>
                                       <h4>Degree and Supporting Document</h4>
                                <section>
                                
                                  <div class="card-style mb-30">

												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">


															<div class="table-wrapper table-responsive custom-table">
																<table class="table" id="doc_Tb">
																	<thead>
																		<tr>
																			<th>Ser No</th>
																			<th>Document Name</th>
																			<th>Document Type</th>																		
																			<th>Upload</th>																		
																			<th>Action</th>
																		</tr>
																		<!-- end table row-->
																	</thead>
																	<tbody id="doc_Tbbody">
																		<tr id="tr_id_doc">
																		</tr>
																		<!-- end table row -->

																	</tbody>
																</table>
																<!-- end table -->
															</div>

															<!-- end card -->
														</div>
														<!-- end col -->
													</div>
													<!-- end row -->
												</div>
											</div>
                                </section>
                                <h4>Confirmation</h4>
                                <section>
                                    <div class="row h-100">
                                        <div
                                            class="col-12 h-100 d-flex flex-column justify-content-center align-items-center">
                                             <p>Ram - Principle of;DEMO Homoeopathic Medical College and Hospital,do hereby solemnly alfirm:-
                                            </p>
                                            <ol>
											  <li>That the particulars of given below are true to the best of my knowledge and belief and are based on 
											  	  the records available in the college / Institute Office.</li>
											  <li>That, I have personality verified all the above information and documents from the originals.</li>
											<!--   <li>Milk</li> -->
											</ol> 
                                           <!--  <h2>You have submitted form successfully!</h2>
                                            <p>Thank you very much for you information. we will procceed accordingly.
                                            </p> -->
                                        </div>
                                    </div>
                                    <input type="hidden" id="count_hidden_att" name="count_hidden_att" class="form-control autocomplete" value="1">
                                    <input type="hidden" id="count_hidden_doc" name="count_hidden_doc" class="form-control autocomplete" value="1">
                                </section>
                            </div>
						<ul class="buttons-group mainbtn" role="menu" aria-label="Pagination">
							<li>
								<a href="Teacher_dtl_Url" class="main-btn dark-btn n btn-hover" type="button">Reset</a>
							</li>
							<li>
								<input class="main-btn deactive-btn btn-hover btn-update" id="update" type="submit" value="Update" >
							</li>
						</ul>
                        </form>
                    </div>
                </div>
            </div>
            <!-- #/ container -->
            </div></div></div></div></div></div></section>
     
    
    <!--**********************************
        Main wrapper end
    ***********************************-->

<script type="text/javascript" src="js/stepform/common.min.js" ></script>
 <script type="text/javascript" src="js/stepform/custom.min.js"></script>
 <script type="text/javascript" src="js/stepform/jquery.validate.min.js"></script>
  <script type="text/javascript" src="js/stepform/jquery.steps.min.js"></script>
 <script type="text/javascript" src="js/stepform/jquery-steps-init.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

</body>
<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		
				$("#cand_prefix").val('${edit_tea_dtl_CMD.cand_prefix}');
				$("#first_name").val('${edit_tea_dtl_CMD.first_name}');
				$("#middle_name").val('${edit_tea_dtl_CMD.middle_name}');
				$("#last_name").val('${edit_tea_dtl_CMD.last_name}');
				$("#gender").val('${edit_tea_dtl_CMD.gender}');
				$("#id").val('${edit_tea_dtl_CMD.id}');
				
				var date_of_birth = '${edit_tea_dtl_CMD.date_of_birth}';
		        var dob= date_of_birth.substring(0,10);
		        var dob_y = dob.substring(0,4);
		        var dob_m = dob.substring(5,7);
		        var dob_d = dob.substring(8,10);
		         var dob_t = dob_d+"/"+dob_m+"/"+dob_y;
		        $("#date_of_birth").val(dob_t);

				$("#father_name").val('${edit_tea_dtl_CMD.father_name}');
				$("#mother_name").val('${edit_tea_dtl_CMD.mother_name}');
				$("#spouse_name").val('${edit_tea_dtl_CMD.spouse_name}');
				$("#mobile_no").val('${edit_tea_dtl_CMD.mobile_no}');
				$("#email").val('${edit_tea_dtl_CMD.email}');
				
				var adhar = '${edit_tea_dtl_CMD.aadhar_no}';
				var a1 = adhar.substring(0,4);
	 			var a2 = adhar.substring(4,8);
	 			var a3 = adhar.substring(8,12);
	 			$("#aadhar_no1").val(a1);
	 			$("#aadhar_no2").val(a2);
	 			$("#aadhar_no3").val(a3);
				
				$("#pan_no").val('${edit_tea_dtl_CMD.pan_no}');
				$("#per_add_line1").val('${edit_tea_dtl_CMD.per_add_line1}');
				$("#per_add_line2").val('${edit_tea_dtl_CMD.per_add_line2}');
				$("#per_village").val('${edit_tea_dtl_CMD.per_village}');
				
				$('#per_state').val('${edit_tea_dtl_CMD.per_state}');
				$("#per_state").change();
				$('#per_district').val('${edit_tea_dtl_CMD.per_district}');
				$("#per_district").change();
				
				$("#per_pincode").val('${edit_tea_dtl_CMD.per_pincode}');
				$("#per_phn_no").val('${edit_tea_dtl_CMD.per_phn_no}');
				
				 if("${edit_tea_dtl_CMD.present_add_line1}" == "${edit_tea_dtl_CMD.per_add_line1}" && "${edit_tea_dtl_CMD.present_add_line2}" == "${edit_tea_dtl_CMD.per_add_line2}" && "${edit_tea_dtl_CMD.present_village}" == "${edit_tea_dtl_CMD.per_village}"
						&& "${edit_tea_dtl_CMD.per_state}" == "${edit_tea_dtl_CMD.present_state}" && "${edit_tea_dtl_CMD.per_district}" == "${edit_tea_dtl_CMD.present_district}" 
						&& "${edit_tea_dtl_CMD.per_pincode}" == "${edit_tea_dtl_CMD.present_pincode}" && "${edit_tea_dtl_CMD.per_phn_no}" == "${edit_tea_dtl_CMD.present_phn_no}"){
				$("#check_address").prop("checked", true);
				 }
				 
				$("#present_add_line1").val('${edit_tea_dtl_CMD.present_add_line1}');
				$("#present_add_line1").attr('readonly',true);
				$("#present_add_line2").val('${edit_tea_dtl_CMD.present_add_line2}');
				$("#present_add_line2").attr('readonly',true);
				$("#present_village").val('${edit_tea_dtl_CMD.present_village}');
				$("#present_village").attr('readonly',true);
				
				$("#present_state").val('${edit_tea_dtl_CMD.present_state}');
				$("#present_state").attr('readonly',true);
				$("#present_state").change();
				$("#present_district").val('${edit_tea_dtl_CMD.present_district}');
				$("#present_district").attr('readonly',true);
				$("#present_district").change();
				
				$("#present_pincode").val('${edit_tea_dtl_CMD.present_pincode}');
				$("#present_pincode").attr('readonly',true);
				$("#present_phn_no").val('${edit_tea_dtl_CMD.present_phn_no}');
				$("#present_phn_no").attr('readonly',true);
				
				$("#state_reg_no").val('${edit_tea_dtl_CMD.state_reg_no}');
				$("#state_board_name").val('${edit_tea_dtl_CMD.state_board_name}');
				
				var date_of_reg = '${edit_tea_dtl_CMD.date_of_reg}';
		        var dob= date_of_reg.substring(0,10);
		        var dob_y = dob.substring(0,4);
		        var dob_m = dob.substring(5,7);
		        var dob_d = dob.substring(8,10);
		         var dob_t = dob_d+"/"+dob_m+"/"+dob_y;
		        $("#date_of_reg").val(dob_t);
		        
				$("#central_reg_no").val('${edit_tea_dtl_CMD.central_reg_no}');
				$("#cand_prefix").val('${edit_tea_dtl_CMD.cand_prefix}');
				
				$("#academic_quali").val('${edit_tea_dtl_CMD.academic_quali}');
				 if('${edit_tea_dtl_CMD.academic_quali}'== 2 ){
						$("#subject").val('${edit_tea_dtl_CMD.subject}');
							 $("#md_detl").show();
						 }
						 else{
							 $("#md_detl").hide();
						 }
				 
				 $("#academic_quali").val('${edit_tea_dtl_CMD.academic_quali}');
					if('${edit_tea_dtl_CMD.academic_quali}'== 1 ){
						$("#sub_quali_degree").val('${edit_tea_dtl_CMD.sub_quali_degree}');
						$("#sub_deg_course").val('${edit_tea_dtl_CMD.sub_deg_course}');
							 $("#hideshw_Degree").show();
						 }
						 else{
							 $("#hideshw_Degree").hide();
						 }
				
				addmoredatafetch1();
				addmoredatafetch2();
				
				datepicketDate('date_of_birth');
				datepicketDate('date_of_reg');
				
				datepicketDate('from_date1');
				datepicketDate('to_date1');
				
// 				$( "#date_of_birth").datepicker( "option", "maxDate", null);
// 				$( "#date_of_reg").datepicker( "option", "maxDate", null);

				setTimeout(setTimeLoadForTable, 1000);
				
			});
	
	function setTimeLoadForTable() {
		//first_name
		//onkeypress="return onlyAlphabetsStringSpace(event,this)" 
		document.getElementById('first_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);
		};
		//middle_name
		//onkeypress="return onlyAlphabetsStringSpace(event,this)"
		document.getElementById('middle_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);
		};
		//last_name
		//onkeypress="return onlyAlphabetsStringSpace(event,this)"
		document.getElementById('last_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event,this);
		};
		//date_of_birth
		//onclick="clickclear(this, 'DD/MM/YYYY')"
		

		document.getElementById('date_of_birth').onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_of_birth').onfocus = function() {
			this.style.color='#000000';
		};
		document.getElementById('date_of_birth').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);
		};
		document.getElementById('date_of_birth').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_of_birth').onchange = function() {
			clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); calculate_age('dob');
		};
		//onkeypress="return onlyAlphabetsStringSpace(event,this)"
		document.getElementById('father_name').onkeypress = function() {
			return  onlyAlphabetsStringSpace(event,this);
		};
		document.getElementById('mother_name').onkeypress = function() {
			return  onlyAlphabetsStringSpace(event,this);
		};
		document.getElementById('spouse_name').onkeypress = function() {
			return  onlyAlphabetsStringSpace(event,this);
		};
		document.getElementById('mobile_no').onkeypress = function() {
			return  isNumberKey0to9(event, this);
		};
		document.getElementById('mobile_no').onchange = function() {
			return   mobileNumber(this);
		};
		document.getElementById('email').onchange = function() {
			return  checkgmail(this.value);
		};
		document.getElementById('aadhar_no1').onkeyup = function() {
			return   movetoNext(this, 'aadhar_no2'),lengthadhar();
		};
		document.getElementById('aadhar_no1').onkeypress = function() {
			return   isNumberKey0to9(event, this);
		};
		document.getElementById('aadhar_no2').onkeyup = function() {
			return   movetoNext(this, 'aadhar_no3'),lengthadhar();
		};
		document.getElementById('aadhar_no2').onkeypress = function() {
			return   isNumberKey0to9(event, this);
		};
		document.getElementById('aadhar_no3').onkeyup = function() {
			return  lengthadhar();
		};
		document.getElementById('aadhar_no3').onkeypress = function() {
			return   isNumberKey0to9(event, this);
		};
		document.getElementById('per_add_line1').onkeypress = function() {
			return   onlyAlphaNumeric(event, this);
		};
		document.getElementById('per_add_line1').onchange = function() {
			return   changeAddress();
		};
		document.getElementById('per_add_line2').onkeypress = function() {
			return   onlyAlphaNumeric(event, this);
		};
		document.getElementById('per_add_line2').onchange = function() {
			return   changeAddress();
		};
		document.getElementById('per_state').onchange = function() {
			return   changeAddress(); getDistrict();
		};
		document.getElementById('per_district').onchange = function() {
			return   changeAddress(); 
		};
		document.getElementById('per_village').onkeypress = function() {
			return   onlyAlphabetsStringSpace(event,this);	
		};
		document.getElementById('per_village').onchange = function() {
			return   changeAddress();	
		};
		document.getElementById('per_pincode').onchange = function() {
			return   changeAddress();
			
		};
		document.getElementById('per_pincode').onkeypress = function() {
			return   isNumberKey0to9(event, this);	
		};
		document.getElementById('per_phn_no').onkeypress = function() {
			return   isNumberKey0to9(event, this);
			
		};
		document.getElementById('per_phn_no').onchange = function() {
			return    mobileNumber(this); changeAddress();
			
		};
		document.getElementById('check_address').onchange = function() {
			return    copy_address();	
		};
		document.getElementById('present_add_line1').onkeypress = function() {
			return    onlyAlphabetsStringSpace(event,this);	
		};
		document.getElementById('present_add_line2').onkeypress = function() {
			return    onlyAlphabetsStringSpace(event,this);
			
		};
		document.getElementById('present_state').onchange = function() {
			return   getDistrict_present();	
		};
		document.getElementById('present_village').onkeypress = function() {
			return    onlyAlphabetsStringSpace(event,this);	
		};
		document.getElementById('present_pincode').onkeypress = function() {
			return   isNumberKey0to9(event, this);	
		};
		document.getElementById('present_phn_no').onchange = function() {
			return   mobileNumber(this);	
		};
		document.getElementById('state_reg_no').onkeypress = function() {
			return   onlyAlphaNumeric(event, this);
		};
		document.getElementById('state_board_name').onkeypress = function() {
			return   onlyAlphaNumeric(event, this);
		};
		document.getElementById('date_of_reg').onclick = function() {
			return  clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_of_reg').onfocus = function() {
			return  this.style.color='#000000';
		};
		document.getElementById('date_of_reg').onblur = function() {
			return  clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('date_of_reg').onkeyup = function() {
			return  clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('date_of_reg').onchange = function() {
			return  clickrecall(this,'DD/MM/YYYY');
		};
		document.getElementById('central_reg_no').onkeypress = function() {
			return  onlyAlphaNumeric(event, this);
		};
		//onclick="return isValid();"
		document.getElementById('update').onclick = function() {
			return isValid();
		};
		
		function previousaddmore(att){
			
			
			document.getElementById('institute_name'+att).onkeypress = function() {
				return onlyAlphabetsStringSpace(att);
			};
			//from_date
		//	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');"
			document.getElementById('from_date'+att).onclick = function() {
				clickclear(this, 'DD/MM/YYYY');
			};
			document.getElementById('from_date'+att).onfocus = function() {
				this.style.color='#000000';
			};
			//onkeyup="clickclear(this, \'DD/MM/YYYY\')"
				document.getElementById('from_date'+att).onkeyup = function() {
					clickclear(this, 'DD/MM/YYYY');
			};
			//onchange="clickrecall(this,\'DD/MM/YYYY\'); onchangeCount('+att+');"
			document.getElementById('from_date'+att).onchange = function() {
				clickrecall(this,'DD/MM/YYYY');onchangeCount(att);
			};
			//to_date
			document.getElementById('to_date'+att).onclick = function() {
				clickclear(this, 'DD/MM/YYYY');
			};
			document.getElementById('to_date'+att).onfocus = function() {
				this.style.color='#000000';
			};
			//onkeyup="clickclear(this, \'DD/MM/YYYY\')"
				document.getElementById('to_date'+att).onkeyup = function() {
					clickclear(this, 'DD/MM/YYYY');
			};
			//onchange="clickrecall(this,\'DD/MM/YYYY\'); onchangeCount('+att+');"
			document.getElementById('to_date'+att).onchange = function() {
				clickrecall(this,'DD/MM/YYYY');onchangeCount(att);
			};
			//onchange="pdfFileSizeValidation(this,this.value,&apos;upload_file'+att+'&apos;,&apos;200kb&apos;,&apos;upload_file_lbltik'+att+'&apos;,&apos;upload_file_lbl'+att+'&apos;);"
			document.getElementById('upload_file'+att).onchange = function() {
				pdfFileSizeValidation(this,this.value,'upload_file'+att+'','200kb','upload_file_lbltik'+att+'','upload_file_lbl'+att+'',this.accept);
			};
			//id_add_att
			//onclick="formopen_att('+att+');"
			document.getElementById('id_add_att'+att).onclick = function() {
				formopen_att(att);
			};
			//att_id_remove
			//onclick="formopen_re_att('+att+');"
			document.getElementById('att_id_remove'+att).onclick = function() {
				formopen_re_att(att);
			};
		}
	function documentaddmore(doc){
		//onchange="GetTypeFromDoc('+doc+');"
		document.getElementById('doc_name'+att).onchange = function() {
			GetTypeFromDoc(doc);
		};
		//id_add_doc
		//onclick="formopen_doc('+doc+');"
		document.getElementById('id_add_doc'+att).onclick = function() {
			formopen_doc(doc);
		};
		//doc_id_remove
		//onclick="formopen_re_doc('+doc+');"
		document.getElementById('doc_id_remove'+att).onclick = function() {
			formopen_re_doc(doc);
		};
		
	}
	
	}

</script>
<script>

//Add-More-Add //////for previous Experience
var att=1;
function formopen_att(R){

	$("#att_Tb").show();
	
		 $("#id_add_att"+R).hide();
		 $("#att_id_remove"+R).hide();
		 
		 att=0;
		 att= parseInt(R)+1;
		 
		 if(att < 51){
				 
				 $("input#count_hidden_att").val(att);//current serial No
				 $("table#att_Tb").append('<tr align="center" id="tr_id_att'+att+'"><td>'+att+'</td>'
						 
						 +'<td class="min-width"><div class="input-style-2"><input type="text" id="institute_name'+att+'" name="institute_name'+att+'"  maxlength="100" class="form-control" autocomplete="off" placeholder="Enter Organization Name" ></div></td>' /* onkeypress="autox('+att+');" */
						 +'<td class="min-width"><div class="select-style-1"><div class="select-position"><select name="depart_name'+att+'" class="form-control" id ="depart_name'+att+'" ><option value="0">--Select Department--</option><option value="1">AYURVEDA</option><option value="2">UNANI</option>'
						 +'<option value="3">SIDDHA</option><option value="4">SOWA-RIGPA</option></select></div></div></td>'
						 /*  +'<td align="center"><select id="depart_name'+att+'" name="depart_name'+att+'" class="form-control" onchange="GetSystemFromCourse('+att+');"><option value="0">--Select--</option>'
							+'<c:forEach var="item" items="${getSystemList}" varStatus="num"><option value="${item.id}" name="${item.system_name}">${item.system_name}</option></c:forEach>' 
							+'</select> </td>' */
							+'<td class="min-width"><div class="select-style-1"><div class="select-position"><select name="desig'+att+'" class="form-control" id ="desig'+att+'" ><option value="0">--Select Designation--</option><option value="1">PRT</option><option value="2">TGT</option>'
							 +'<option value="3">PGT</option></select></div></div></td>'
						 
							+'<td class="min-width">' 
						    +' <div class="input-style-2"><input type="text" name="from_date'+att+'" id="from_date'+att+'" maxlength="10" value="DD/MM/YYYY"    class="form-control" '
						    +'	  '
					     	+'	 aria-required="true" autocomplete="off" ></div>'
						    + '</td>'
						    
						    +'<td class="min-width">' 
						    +' <div class="input-style-2"><input type="text" name="to_date'+att+'" id="to_date'+att+'" maxlength="10" value="DD/MM/YYYY"    class="form-control" '
						    +'	  
					     	+'	 aria-required="true" autocomplete="off" ></div>'
						    + '</td>'
						    +'<td class="min-width"><div class="input-style-2"><input type="text" id="yr_of_exp'+att+'" name="yr_of_exp'+att+'"  maxlength="100" class="form-control" autocomplete="off" placeholder="Year of Experience" ></div></td>'
						    +'<td class="min-width"><div class="input-style-2"><input type="file" accept=".pdf" id="upload_file'+att+'" name="upload_file'+att+'"class="form-control" ><span class="errorClass" id="upload_file_lbl'+att+'">${exp_path_msg}</span> <span class="tikClass" id="upload_file_lbltik'+att+'"></span>'
						    +'<input type="hidden" id="upload_file_hid'+att+'" name="upload_file_hid'+att+'" class="form-control" ></div></td>'
						// +'<td class="min-width"><div class="input-style-2"><input type="file" accept=".pdf" id="upload_file'+att+'" name="upload_file'+att+'"class="form-control"><input type="hidden" id="upload_file_hid'+att+'" name="upload_file_hid'+att+'" class="form-control" ></div></td>'
                          
						 +'<td class="min-width"><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_att'+att+'" ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn-outline btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'" ><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
			   		     +'</tr>');
						 /*  +'<td class="min-width"><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_att'+att+'" onclick="formopen_att('+att+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 10px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'" onclick="formopen_re_att('+att+');"><i class="fa fa-trash"></a></td>'
			   		     +'</tr>');  */
			   		    previousaddmore(att);
				 datepicketDate("from_date"+att);
				 datepicketDate("to_date"+att);
		 
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
		 var curcnt = $("#count_hidden_att").val();
		 $("#new_count_hidden").val(curcnt);
	
}
//Add-More-Remove////for previous Experience
function formopen_re_att(R){
	var del_index = $("#idofprocedure"+R).val();
	if(String(del_index) == "undefined"){
		del_index="0";
	}else{
		del_index = del_index;
	}
	 var del_field_val = $("#del_id_hidden").val();
	 
	 if(del_field_val == "0,undefined"){
		 $("#del_id_hidden").val(del_index);
	 }else{
		 $("#del_id_hidden").val(del_field_val+","+del_index);
	 }
	
	 $("tr#tr_id_att"+R).remove();
	 att = att-1;
	 $("input#count_hidden_att").val(att);
	 if(R > 2){
		 $("#id_add_att"+att).show();
		 $("#att_id_remove"+att).show();
	 }
	 if(R == 2){
		 $("#id_add_att"+att).show();
	 }
	 var ncc = $("#new_count_hidden").val();
	 ncc = ncc-1;
	 $("#new_count_hidden").val(ncc);
}
</script>
<script>

//Add-More-Add/////for document
var doc=1;
function formopen_doc(R){
	$("#doc_Tb").show();
	
		 $("#id_add_doc"+R).hide();
		 $("#doc_id_remove"+R).hide();
		 
		 doc=0;
		 doc= parseInt(R)+1;
		 
		 if(doc < 51){
				 
				 $("input#count_hidden_doc").val(doc);//current serial No
				 $("table#doc_Tb").append('<tr align="center" id="tr_id_doc'+doc+'"><td>'+doc+'</td>'
						 +'<td class="min-width"><div class="select-style-1"><div class="select-position"><select id="doc_name'+doc+'" name="doc_name'+doc+'" class="form-control" ><option value="0">--Select--</option>'
							+'<c:forEach var="item" items="${getDocList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach>' 
							+'</select></div></div> </td>'
					    
						 +'<td class="min-width"><div class="select-style-1"><div class="select-position"><select id="doc_id'+doc+'" name="doc_id'+doc+'" class="form-control"><option value="0">--Select--</option>'
							+'</select></div></div> </td>'	
						 +'<td class="min-width"><div class="input-style-2"><input type="file" accept=".pdf" id="upload_doc'+doc+'" name="upload_doc'+doc+'"class="form-control"><input type="hidden" id="doc_upload_hid'+doc+'" name="doc_upload_hid'+doc+'" class="form-control" ></div></td>'
						 +'<td class="min-width"><div class="action"><ul class="buttons-group mainbtn"><li><a class="main-btn info-btn-outline btn-hover btn-sm addminusbut" value = "ADD" title = "ADD" id = "id_add_doc'+doc+'" ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn-outline btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "doc_id_remove'+doc+'" ><i class="lni lni-trash-can"></i></a></li></ul></div></td>'
			   		     +'</tr>');
						/*  +'<td class="min-width"><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_doc'+doc+'" onclick="formopen_doc('+doc+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 10px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "doc_id_remove'+doc+'" onclick="formopen_re_doc('+doc+');"><i class="fa fa-trash"></a></td>'
			   		     +'</tr>'); */
			   		     documentaddmore(doc);
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( doc == 51){
						 doc = doc-1; 
						 $("#rp_id_remove"+doc).show();
					 }	   
			}
		 var curcnt = $("#count_hidden_doc").val();
		 $("#new_count_hidden").val(curcnt);
	
}
//Add-More-Remove
function formopen_re_doc(R){
	var del_index = $("#idofprocedure"+R).val();
	if(String(del_index) == "undefined"){
		del_index="0";
	}else{
		del_index = del_index;
	}
	 var del_field_val = $("#del_id_hidden").val();
	 
	 if(del_field_val == "0,undefined"){
		 $("#del_id_hidden").val(del_index);
	 }else{
		 $("#del_id_hidden").val(del_field_val+","+del_index);
	 }
	
	 $("tr#tr_id_doc"+R).remove();
	 doc = doc-1;
	 $("input#count_hidden_doc").val(doc);
	 if(R > 2){
		 $("#id_add_doc"+doc).show();
		 $("#doc_id_remove"+doc).show();
	 }
	 if(R == 2){
		 $("#id_add_doc"+doc).show();
	 }
	 var ncc = $("#new_count_hidden").val();
	 ncc = ncc-1;
	 $("#new_count_hidden").val(ncc);
}

function GetTypeFromDoc(obj) {
	
	var doc_name = $("select#doc_name"+obj).val();
  $.post("getTypelistFromDoc?" + key + "=" + value,{doc_name : doc_name},
				function(j) {
	  			
					var options = '<option value="' + "0" + '">'
							+ "--Select--" + '</option>';
					for (var i = 0; i < j.length; i++) {

						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
								+ j[i][1]+ '</option>';
					}
					$("select#doc_id"+obj).html(options);
				});
}

function getDistrict() {
	var selval = $("#per_state").val();
	$
			.post(
					"getDistrictUrl?" + key + "=" + value,
					{
						selval : selval
					},
					function(j) {
					
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
									+ j[i].district_name + '</option>';
						}
						$("select#per_district").html(options);
					});
}

 function getDistrict1() {
	var selval = $("#present_state").val();
	$
			.post(
					"getDistrictUrl?" + key + "=" + value,
					{
						selval : selval
					},
					function(j) {
					
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i].district_id + '" name="'+j[i].district_id+'" >'
									+ j[i].district_name + '</option>';
						}
						$("select#present_district").html(options);
					});
} 

function mobileNumber(obj){
	
	_mobile = obj.value;
	
	var regExp =/^[6789]\d{9}$/;
    if(_mobile == '' || !regExp.test(_mobile)){
        alert('Please Enter Number Start with 6789 Digit');
        $('#'+obj.id).focus();
        $('#'+obj.id).val('');
        return false;
    }
}

function checkgmail(email1) {
	
	 document.getElementById("email").innerHTML="";
	if(/@gmail.com\s*$/.test(email1) || /@yahoo.com\s*$/.test(email1)) {
		
	}else{
		
		alert("Please Enter Valid Email Address");
		$("input#email").focus();
		$("input#email").val('');
		return false ;
	}
}

function lengthadhar() {
	document.getElementById("aadhar_no1").maxLength = "4";
	document.getElementById("aadhar_no1").minLength = "4";
	document.getElementById("aadhar_no2").maxLength = "4";
	document.getElementById("aadhar_no2").minLength = "4";
	document.getElementById("aadhar_no3").maxLength = "4";
	document.getElementById("aadhar_no3").minLength = "4";
} 

function movetoNext(current, nextFieldID) {  
	if (current.value.length >= current.maxLength) {  
		document.getElementById(nextFieldID).focus();  
	}  
}

//////////////////////for same as address
function copy_address() {
	
	if($("#check_address").is(":checked") == true){
		$("#present_add_line1").val($("#per_add_line1").val());
		$("#present_add_line1").attr('readonly',true);
		
		$("#present_add_line2").val($("#per_add_line2").val());
		$("#present_add_line2").attr('readonly',true);
		
		$("#present_village").val($("#per_village").val());
		$("#present_village").attr('readonly',true);
		
		$("#present_state").val($("#per_state").val());
		$("#present_state").attr('readonly',true);
		
		$("#present_district").val($("#per_district").val());
		$("#present_district").attr('readonly',true);
		
		$("#present_pincode").val($("#per_pincode").val());
		$("#present_pincode").attr('readonly',true);
		
		$("#present_phn_no").val($("#per_phn_no").val());
		$("#present_phn_no").attr('readonly',true);
		
	}
	
	if($("#check_address").is(":checked") == false){
		$("#present_add_line1").val("");
		$("#present_add_line1").attr('readonly',false);
		
		$("#present_add_line2").val("");
		$("#present_add_line2").attr('readonly',false);
		
		$("#present_village").val("");
		$("#present_village").attr('readonly',false);
		
		$("#present_state").val("0");
		$("#present_state").attr('readonly',false);
		
		$("#present_district").val("0");
		$("#present_district").attr('readonly',false);
		
		$("#present_pincode").val("");
		$("#present_pincode").attr('readonly',false);
		
		$("#present_phn_no").val("");
		$("#present_phn_no").attr('readonly',false);
		
	}
}

function changeAddress() {
	
	if($("#check_address").is(":checked") == true){
		$("#check_address").prop("checked", false);
		
		$("#present_add_line1").val("");
		$('#present_add_line1').attr('readonly', false);
		
		$("#present_add_line2").val("");
		$('#present_add_line2').attr('readonly', false);
		 
		$("#present_village").val("");
		$('#present_village').attr('readonly', false);
		
		$("#present_state").val("0");
		$('#present_state').attr('readonly', false);
		
		$("#present_district").val("0");
		$('#present_district').attr('readonly', false);
		
		$("#present_pincode").val("");
		$('#present_pincode').attr('readonly', false);
		
 		$("#present_phn_no").val("");
		$('#present_phn_no').attr('readonly', false);
		 
	}
}
//////////////////////////edit_fetch method for child table

function addmoredatafetch1(){
	
	var ser = 0;
	var ind =1;
	<c:forEach var="j" items="${edit_expchild}" varStatus="num"> 
		
		formopen_att(ser);
		var inst_name = "${j[0]}";
		var depart_name = "${j[1]}";
		var desig = "${j[2]}";
		var fd = "${j[3]}"
		var td = "${j[4]}";
		var upload_file = "${j[5]}";
	
		fd = fd.substring(0,10);
		td = td.substring(0,10);
		
		var from_date = fd;
        var dob= from_date.substring(0,10);
        var dob_y = dob.substring(0,4);
        var dob_m = dob.substring(5,7);
        var dob_d = dob.substring(8,10);
         var dob_f = dob_d+"/"+dob_m+"/"+dob_y;
         
         var to_date = td;
         var dob= to_date.substring(0,10);
         var dob_y = dob.substring(0,4);
         var dob_m = dob.substring(5,7);
         var dob_d = dob.substring(8,10);
          var dob_t = dob_d+"/"+dob_m+"/"+dob_y;
		
		$("#institute_name"+ind).val(inst_name);
		$("#desig"+ind).val(desig);
		$("#depart_name"+ind).val(depart_name);
		$("#from_date"+ind).val(dob_f);
		$("#to_date"+ind).val(dob_t);
		
		$("#upload_file_hid"+ind).val(upload_file);
		
		ser=parseInt(ser)+1;
		ind=parseInt(ind)+1;
		
	</c:forEach>

}

function addmoredatafetch2(){

	var ser = 0;
	var ind =1;
	<c:forEach var="j" items="${edit_expchild_2}" varStatus="num"> 
		
	formopen_doc(ser);
		var doc_name = "${j[0]}";
		var doc_id = "${j[1]}";
		var upload_doc = "${j[2]}";
		
		$("#doc_name"+ind).val(doc_name);
		$("#doc_name"+ind).change();
		
		$("#doc_id"+ind).val(doc_id);
		$("#doc_id"+ind).change();
		$("#doc_upload_hid"+ind).val(upload_doc);
		
		ser=parseInt(ser)+1;
		ind=parseInt(ind)+1;
		
	</c:forEach>

}

function FnMd_dtl(){
	if($("#academic_quali").val() == "2"  ){
		$("div#md_detl").show();
	}
	if($("#academic_quali").val() == "1"  ){
		$("div#md_detl").hide();
	}
	if($("#academic_quali").val() == "3"  ){
		$("div#md_detl").hide();
	}
}

function hideshw_Degree() {
	if($("#academic_quali").val() == "1"  ){
		$("div#Type_of_degree").show();
		$("div#sub_degree").show();
	}
	if($("#academic_quali").val() == "2"  ){
		$("div#Type_of_degree").hide();
		$("div#sub_degree").hide();
	}
	if($("#academic_quali").val() == "3"  ){
		$("div#Type_of_degree").hide();
		$("div#sub_degree").hide();
	}
}

function isValid() {
	
	if ($("#cand_prefix").val() == "0") {
		alert("Please Select the Candidate Prifix");
		$("#cand_prefix").focus();
		return false;
	    }
	    
	if ($("#first_name").val().trim() == "") {
		alert("Please Enter the First Name");
		$("#first_name").focus();
		return false;
	}
	if ($("#middle_name").val().trim() == "") {
		alert("Please Enter the Middle Name");
		$("#middle_name").focus();
		return false;
	}
	if ($("#last_name").val().trim() == "") {
		alert("Please Enter the Last Name");
		$("#last_name").focus();
		return false;
	}
	if( $("#gender").val() == "0" ){
		alert("Please Select Gender");
		$("#gender").focus();
		return false;
   	}
	if ($("#date_of_birth").val() == "" || $("#date_of_birth").val() == "DD/MM/YYYY") {
		alert("Please Select the Date Of Birth");
		$("#date_of_birth").focus();
		return false;
	}
	if ($("#father_name").val().trim() == "") {
		alert("Please Enter the Father's Name");
		$("#father_name").focus();
		return false;
	}
	if ($("#mother_name").val().trim() == "") {
		alert("Please Enter the Mother's Name");
		$("#mother_name").focus();
		return false;
	}
	if ($("#spouse_name").val().trim() == "") {
		alert("Please Enter the Spouse's Name");
		$("#spouse_name").focus();
		return false;
	}
	if($("#mobile_no").val() == ""){
		alert("Please Enter Mobile Number");
		$("#mobile_no").focus();
		return false;
	}
	if($("#email").val() == ""){
		alert("Please Enter Email Address");
		$("#email").focus();
		return false;
	}
	if($("#aadhar_no1").val() == ""){
		alert("Please Enter Aadhaar Number should be same as mentioned in Aadhaar Card");
		$("#aadhar_no1").focus();
		return false;
	} 
	if($("#aadhar_no2").val() == ""){
		alert("Please Enter Aadhaar Number should be same as mentioned in Aadhaar Card");
		$("#aadhar_no2").focus();
		return false;
	} 
	if($("#aadhar_no3").val() == ""){
		alert("Please Enter Aadhaar Number should be same as mentioned in Aadhaar Card");
		$("#aadhar_no3").focus();
		return false;
	}
	if($("#pan_no").val() == ""){
		alert("Please Enter PAN No.");
		$("#pan_no").focus();
		return false;
	}
	if( $("#academic_quali").val() == "0" ){
		alert("Please Select Academic Qualification");
		$("#academic_quali").focus();
		return false;
   	}
	
	if ($("#academic_quali").val() == "2") {
		
		if ($("#subject").val() == "") {
			alert("Please Enter the Subject");
			$("#subject").focus();
			return false;
		}
	}

	///////Permanent Address validation
	if ($("#per_add_line1").val().trim() == "") {
		alert("Please Enter the Permanent Address-Line-1");
		$("#per_add_line1").focus();
		return false;
	} 
	if ($("#per_add_line2").val().trim() == "") {
		alert("Please Enter the Permanent Address-Line-2");
		$("#per_add_line2").focus();
		return false;
	} 
	if( $("#per_state").val() == "0" ){
		alert("Please Select State");
		$("#per_state").focus();
		return false;
   	}
	if( $("#per_district").val() == "0" ){
		alert("Please Select District");
		$("#per_district").focus();
		return false;
   	}
	if ($("#per_village").val().trim() == "") {
		alert("Please Enter the Permanent City/Village");
		$("#per_village").focus();
		return false;
   	}
	if ($("#per_pincode").val().trim() == "") {
		alert("Please Enter the Permanent Pin Code");
		$("#per_pincode").focus();
		return false;
   	}
	if ($("#per_phn_no").val().trim() == "") {
		alert("Please Enter the Permanent Phone No.");
		$("#per_phn_no").focus();
		return false;
   	}
	if(document.getElementById('check_address').checked == false) { 
		if ($("#present_add_line1").val().trim() == "") {
			alert("Please Enter the Present Address-Line-1");
			$("#present_add_line1").focus();
			return false;
		}
		if ($("#present_add_line2").val().trim() == "") {
			alert("Please Enter the Present Address-Line-2");
			$("#present_add_line2").focus();
			return false;
		}
		if( $("#present_state").val() == "0" ){
			alert("Please Select State");
			$("#present_state").focus();
			return false;
	   	}
		if( $("#present_district").val() == "0" ){
			alert("Please Select District");
			$("#present_district").focus();
			return false;
	   	}
		if ($("#present_village").val().trim() == "") {
			alert("Please Enter the Present City/Village");
			$("#present_village").focus();
			return false;
	   	}
		if ($("#present_pincode").val().trim() == "") {
			alert("Please Enter the Present Pin Code");
			$("#present_pincode").focus();
			return false;
	   	}
		if ($("#present_phn_no").val().trim() == "") {
			alert("Please Enter the Present Phone No.");
			$("#present_phn_no").focus();
			return false;
	   	}
	}
	
////////Registration Details	
		if ($("#state_reg_no").val().trim() == "") {
			alert("Please Enter the State Registration No.");
			$("#state_reg_no").focus();
			return false;
	   	}
		if ($("#state_board_name").val().trim() == "") {
			alert("Please Enter the Name of the State Board");
			$("#state_board_name").focus();
			return false;
	   	}
		if ($("#date_of_reg").val() == "" || $("#date_of_reg").val() == "DD/MM/YYYY") {
			alert("Please Select the Date Of Registration");
			$("#date_of_reg").focus();
			return false;
		}
		if ($("#central_reg_no").val().trim() == "") {
			alert("Please Enter the Central Registration No.");
			$("#central_reg_no").focus();
			return false;
	   	}
		
////////Previous Experience		

		for(exp = 1; exp <= $('#count_hidden_att').val(); exp++){
			if($("#institute_name"+exp).val().trim()==''){
				alert("Please Enter Organization Name In "+exp+" Row");
				$("#institute_name"+exp).focus();
				return false;
			}
			if($("#depart_name"+exp).val()=='0'){
				alert("Please Select Department Name In "+exp+" Row");
				$("#depart_name"+exp).focus();
				return false;
			}
			if($("#desig"+exp).val()=='0'){
				alert("Please Select Designation In "+exp+" Row");
				$("#desig"+exp).focus();
				return false;
			}
			if($("input#from_date"+exp).val().trim()=="" || $("input#from_date"+exp).val().trim()=="DD/MM/YYYY"){
				alert("Please Enter From Date"+exp+" Row");
				$("#from_date"+exp).focus();
				return false;
			}
			if($("input#to_date"+exp).val().trim()=="" || $("input#to_date"+exp).val().trim()=="DD/MM/YYYY"){
				alert("Please Enter To Date "+exp+" Row");
				$("#to_date"+exp).focus();
				return false;
			}
			if(getformatedDate($("input#from_date"+exp).val()) > getformatedDate($("#to_date"+exp).val())) {
				alert("To Date should be Greater than Or Equal to From Date  "+exp+" Row");
				$("#to_date"+exp).focus();
				return false;
		    }
			 if($("#upload_file_hid"+exp).val() == ""){	
			 	if($("#upload_file"+exp).val() == ""){
			 		alert("Please Select File "+exp+" Row");
			 		 $("input#upload_file"+exp).focus();
					 return false;
			 	}
			 }
		
		if($("#institute_name"+exp).val() != "0" && $("#institute_name"+exp).val() != "1")
		//	count_classi++;
		if($("#institute_name"+exp).val() != "1"){
			if($("#institute_name"+exp).val().trim()==''){
				alert("Please Enter Organization Name In "+exp+" Row");
				$("#institute_name"+exp).focus();
				return false;
			}
			if($("#depart_name"+exp).val()=='0'){
				alert("Please Select Department Name In "+exp+" Row");
				$("#depart_name"+exp).focus();
				return false;
			}
			if($("#desig"+exp).val()=='0'){
				alert("Please Select Designation In "+exp+" Row");
				$("#desig"+exp).focus();
				return false;
			}
			if($("input#from_date"+exp).val().trim()=="" || $("input#from_date"+exp).val().trim()=="DD/MM/YYYY"){
				alert("Please Enter From Date"+exp+" Row");
				$("#from_date"+exp).focus();
				return false;
			}
			if($("input#to_date"+exp).val().trim()=="" || $("input#to_date"+exp).val().trim()=="DD/MM/YYYY"){
				alert("Please Enter To Date "+exp+" Row");
				$("#to_date"+exp).focus();
				return false;
			}
			if(getformatedDate($("input#from_date"+exp).val()) > getformatedDate($("#to_date"+exp).val())) {
				alert("To Date should be Greater than Or Equal to From Date  "+exp+" Row");
				$("#to_date"+exp).focus();
				return false;
		    }
			 if($("#upload_file_hid"+exp).val() == ""){	
				 	if($("#upload_file"+exp).val() == ""){
				 		alert("Please Select File "+exp+" Row");
				 		 $("input#upload_file"+exp).focus();
						 return false;
				 	}
				 }
		}	
	}
	
////////Degree and Supporting Document	

		for(deg = 1; deg <= $('#count_hidden_doc').val(); deg++){
			
			if($("#doc_name"+deg).val()=='0'){
				alert("Please Select Document Name In "+deg+" Row");
				$("#doc_name"+deg).focus();
				return false;
			}
			if($("#doc_id"+deg).val()=='0'){
				alert("Please Select Document Type In "+deg+" Row");
				$("#doc_id"+deg).focus();
				return false;
			}
			 if($("#doc_upload_hid"+deg).val() == ""){	
			 	if($("#upload_doc"+deg).val() == ""){
			 		alert("Please Select Document "+deg+" Row");
			 		 $("input#upload_doc"+deg).focus();
					 return false;
			 	}
			 }
		
		if($("#doc_name"+deg).val() != "0" && $("#doc_name"+deg).val() != "1")
		//	count_classi++;
		if($("#doc_name"+deg).val() != "1"){
			if($("#doc_name"+deg).val()=='0'){
				alert("Please Select Document Name In "+deg+" Row");
				$("#doc_name"+deg).focus();
				return false;
			}
			if($("#doc_id"+deg).val()=='0'){
				alert("Please Select Document Type In "+deg+" Row");
				$("#doc_id"+deg).focus();
				return false;
			}
			 if($("#doc_upload_hid"+deg).val() == ""){	
				 	if($("#upload_doc"+deg).val() == ""){
				 		alert("Please Select Document "+deg+" Row");
				 		 $("input#upload_doc"+deg).focus();
						 return false;
				 	}
			 }
		}	
	}	
}

function onchangeCount(val){
	
	//debugger;
	
	if(document.getElementById("from_date"+val).value!="" && document.getElementById("to_date"+val).value!=""){
	var joining=document.getElementById("from_date"+val).value.split('/').reverse().join('-');  
	var dish=document.getElementById("to_date"+val).value.split('/').reverse().join('-'); 
	var joining2=new Date(joining);
	var dishcharge=new Date(dish);
	
 var a = dateDiffInDays_Months_Years(joining,dish,val)
	}
}

function dateDiffInDays_Months_Years(start, end,val) {
	
    var m1 = new Date(start);
    
    var m2 = new Date(end);
    
    var yDiff = m2.getFullYear() - m1.getFullYear();
    
//    var mdiff = m2.getMonth() - m1.getMonth();

    $("#yr_of_exp"+val).val(yDiff);
 //   $("#yr_of_exp1").val(""+yDiff+"."+mdiff);
    var mDiff = m2.getMonth() - m1.getMonth();
   var dDiff = m2.getDate() - m1.getDate();

    if (dDiff < 0) {
        var daysInLastFullMonth = getDaysInLastFullMonth(start);
        if (daysInLastFullMonth < m1.getDate()) {
            dDiff = daysInLastFullMonth + dDiff + (m1.getDate() - daysInLastFullMonth);
        } else {
            dDiff = daysInLastFullMonth + dDiff;
        }
        mDiff--;
    }
    if (mDiff < 0) {
        mDiff = 12 + mDiff;
        yDiff--;
    }
     console.log('Y:', yDiff, ', M:', mDiff, ', D:', dDiff);
    var message = dDiff+"/"+mDiff+"/"+yDiff
    return message
}

</script>

 
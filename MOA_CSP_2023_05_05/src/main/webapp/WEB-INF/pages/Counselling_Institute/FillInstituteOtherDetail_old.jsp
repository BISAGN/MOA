<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="assets/db_js/institutemasterotherdata.js"></script>
<!-- <script src="assets/db_js/SendListToInst.js"></script> -->
<script src="assets/db_js/CommonValidation.js"></script>
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<!-- <link rel="stylesheet" href="assets/db_css/jquery-confirm.min.css"> -->

<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- <link rel="stylesheet"
	href="assets/vendor/multiselect/jquery.multiselect.css">
<script type="text/javascript"
	src="assets/vendor/multiselect/jquery.multiselect.js"></script> -->

<!-- <style>
.ms-options-wrap{
    position: absolute !important;
}

.table .ms-options-wrap button{
   margin-top:-20px;
}
</style> -->
<section class="dashboard-page" id="insdiv">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<!-- title-wrapper start -->
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Add Institute Details</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Add Institute
									Details</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper start -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<!-- input style start -->
					<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Institute Details</h6>

							<div class="row">
								<!-- Hidden Start -->
								<input type="hidden" id="actiontype" name="actiontype"
									value="add"> <input type="hidden" id="insid"
									name="insid" value="0">
								<!-- Hidden End -->
							</div>

							<div id="fillform" class="hide">
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4"
										id="systemtypediv">
										<div class="select-style-1">
											<label>System Type<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-select" id="systemtype"
													name="systemtype" tabindex="1">
													<option value="-1">Please Select System Type</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4"
										id="universitytypediv">

										<div class="select-style-1">
											<label>University<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-select" id="university"
													name="university" tabindex="2">
													<option value="-1">Please Select University</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Institute Code<span class="mandatory">*</span></label>
											<input type="text" class="form-control" id="inscode"
												name="inscode" placeholder="Enter Institute Code"
												maxlength="16" autocomplete="off" tabindex="3">
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Institute Name<span class="mandatory">*</span></label>
											<input type="text" id="insname"
												placeholder="Enter Institute Name" name="insname"
												autocomplete="off" maxlength="256" tabindex="4">
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Institute Logo<span class="mandatory">*</span></label>

											<div class="input-group custom-input-group">
												<input type="file" class="form-control form-control-lg"
													placeholder="upload your document" name="instimage"
													id="instimage" tabindex="5">
												<ul class="buttons-group">
													<li><a href="#" id="viewimage" name="viewimage"
														class="main-btn dark-btn btn-hover btn-sm btnview"> <i
															class="lni lni-eye"></i></a></li>
												</ul>
												<!-- <a href='#' id="viewimage" name="viewimage"
													class="main-btn dark-btn btn-hover btn-sm"> <i
													class="lni lni-eye"></i></a> -->
											</div>


											<!-- 											<ul class="buttons-group"> -->
											<!-- 										<li> -->
											<!-- 										</li></ul> -->

											<!-- 											<a href='#' id="viewimage" name="viewimage">View Image</a> -->
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Institute Abbreviation<span class="mandatory">*</span></label>
											<input type="text" id="insabb"
												placeholder="Enter Institute Abbreviation" name="insabb"
												autocomplete="off" maxlength="10" tabindex="10">
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Phone Number<span class="mandatory">*</span></label> <input
												type="text" id="mobilenumber"
												placeholder="Enter Enter Number" name="mobilenumber"
												autocomplete="off" maxlength="10" tabindex="7">
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Email ID<span class="mandatory">*</span></label> <input
												type="text" class="form-control" id="email"
												placeholder="Enter Email ID" name="email" autocomplete="off"
												maxlength="256" tabindex="8">
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-6 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Country<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-select" id="country" name="country"
													tabindex="9">
													<option value="-1">Please Select Country</option>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-6 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>State<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-select" id="state" name="state"
													tabindex="10">
													<option value="-1">Please Select State</option>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-6 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>District<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-select" id="district" name="district"
													tabindex="11">
													<option value="-1">Please Select District</option>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Address<span class="mandatory">*</span></label>
											<textarea type="text" id="address"
												placeholder="Enter Address" name="address"
												autocomplete="off" tabindex="12" rows="4" maxlength="256"></textarea>
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Total Seats<span class="mandatory">*</span></label> <input
												type="text" id="totalseat" placeholder="Enter Total Seats"
												name="totalseat" autocomplete="off" tabindex="13"
												maxlength="5" />
										</div>
									</div>
									<!-- 								<div class="col-12 col-sm-6 col-md-6 col-lg-4"> -->
									<!-- 									<div class="select-style-1"> -->
									<!-- 										<label>Fees Category Type<span class="mandatory">*</span></label> -->
									<!-- 										<div class="select-position"> -->
									<!-- 											<select class="form-select" id="fees" name="fees" -->
									<!-- 												tabindex="9"> -->
									<!-- 												<option value="-1">Please Select Fees Category Type</option> -->
									<!-- 											</select> -->
									<!-- 										</div> -->
									<!-- 									</div> -->
									<!-- 									end select -->
									<!-- 								</div> -->

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1 mb-0">
											<label>Hostel Facility</label>
										</div>
										<div class="custom-choose-one">
											<div class="input-style-form-check">
												<div class="form-check checkbox-style">
													<input class="form-check-input" type="checkbox"
														id="hostelfacility"> <label
														class="form-check-label " for="hostelfacility">Hostel
														Facility Available</label>
												</div>
											</div>
										</div>
									</div>

									<!-- 								<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
									<!-- 									<div class="form-check form-check-inline mt-26"> -->
									<!-- 										<input class="form-check-input" type="checkbox" -->
									<!-- 											id="libraryfacility"> <label class="form-check-label" -->
									<!-- 											for="libraryfacility">Library Facility Available</label> -->
									<!-- 									</div> -->
									<!-- 								</div> -->

									<!-- 								<div class="col-12 col-sm-12 col-md-6 col-lg-4"> -->
									<!-- 									<div class="form-check form-check-inline mt-26" id="hide1"> -->
									<!-- 										<div class="select-style-1"> -->
									<!-- 											 <label for="fee_type_lbl" id="fee_type_lbl" name="fee_type_lbl" -->
									<!-- 												class="form-check-label"> Fees Payment Method Type : Full </label> -->
									<!-- 												<input type="checkbox" id="number_of_amountu" class="form-check-input" -->
									<!-- 												name="number_of_amountu"> -->
									<!-- 										</div> -->
									<!-- 										<div class="input-style-1" id="show1"> -->
									<!-- 											<label>Enter No. of Parts<span class="mandatory"></span></label>  -->
									<!-- 											<input type="number" id="number_of_amount1" placeholder="Enter No. of Part"  -->
									<!-- 												name="number_of_amount1" autocomplete="off"  maxlength="5"> -->
									<!-- 										</div> -->
									<!-- 									</div> -->
									<!-- 								</div> -->

									<div class="col-12 col-sm-12 col-md-6 col-lg-4"
										id="payment_type_div">
										<div class="input-style-1 mb-0">
											<label>Payment (Only for fee schedule)<span class="mandatory">*</span></label>
										</div>
										<div class="custom-choose-one">
											<div class="input-style-form-check">
												<div class="form-check radio-style">
													<input type="radio" class="form-check-input" id="full_rb"
														name="Choise" value="Full"> <label for="Upload"
														class="form-check-label">Full Payment</label>
												</div>
												<div class="form-check radio-style">
													<input type="radio" class="form-check-input" id="part_rb"
														name="Choise" value="Part"> <label
														class="form-check-label" for="Fillform">Part
														Payment</label>

												</div>
											</div>
										</div>
										<div class="input-style-1">
											<input type="text" placeholder="Enter No. Of Part" id="number_of_amount1"
												name="number_of_amount1" autocomplete="off" maxlength="5">
										</div>
										<!-- Hidden Start -->
										<input type="hidden" id="isfullpartHid" name="isfullpartHid"
											value="0" />
										<!-- Hidden End -->
									</div>

								</div>

							</div>
						</div>
						<section class="single-detail-block">
							<div class="tables-wrapper">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<h6 class="mb-10">Fees Structure</h6>
										<div class="table-wrapper table-responsive custom-table">
											<table class="table" id="co_feescategorytype">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th><h6>Category<span class="mandatory">*</span></h6><span class="mandatory table-note" id="">You can select multiple options</span></th>
														<th><h6>Fees Category Type<span class="mandatory">*</span></h6></th>
														<th><h6>Fees Sub-Category<span class="mandatory">*</span></h6></th>
														<th><h6>Fees Value <span class="mandatory">*</span></h6></th>
														<th><h6>Action</h6></th>

													</tr>

													<!-- 						end table row -->
												</thead>
												<tbody>

													<tr>
														<td><p>1</p></td>
														<td>

															<div class="select-style-1 custom-select2-multi">
																<div class="select-position">
																	<select class="multiselect2 form-control form-control-lg category" id="category_1"
																		name="category_1" tabindex="14" multiple="multiple">
																		<option value="0">--Please Select Category--</option>
																		</select>
																</div>
																
															</div>


															<!-- end select --> <!-- <select class="form-select category" id="category_1"
											name="category_1[]" tabindex="14" multiple="multiple"></select> -->
														</td>

														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select class="singleselect form-control form-control-lg form-select" id="fees_1" name="fees_1"
																		tabindex="9">
																		<option value="-1">Please Select Fees
																			Category Type</option>
																	</select>
																</div>
															</div>
														</td>
														
														<td>
															<div class="input-style-1">
																<input type="text" id="fees_sub_cat_1"
																	placeholder="Enter Fees Sub Category" name="fees_sub_cat_1"
																	autocomplete="off">
															</div>
														</td>

														<td>
															<div class="input-style-1">
																<input type="text" id="feesvalue_1"
																	placeholder="Enter Fees Value" name="feesvalue_1"
																	autocomplete="off" maxlength="8" tabindex="8">
															</div>
														</td>

														<td>

															<ul class="buttons-group">
																<li><a id="sub_add1"
																	class="main-btn success-btn btn-hover btn-sm"> <i
																		class="lni lni-plus" title="Add"></i></a></li>
															</ul> <!-- 										<a class="btn btn-success btn-sm" --> <!-- 											onclick="formopen();" id="sub_add1" title='ADD'><i -->
															<!-- 												class="fa fa-plus-circle"></i></a> -->

														</td>
											</table>
											<!-- end input -->

											<!-- 				end table -->
										</div>

									</div>
									<!-- 	end col -->
								</div>
							</div>
						</section>
						<!-- 	Button Start -->
						<div class="btn-bottom">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<ul class="buttons-group mainbtn">
										<li><input id="insotherdetailbtn"
											name="insotherdetailbtn"
											class="main-btn info-btn btn-hover btnsave" type="button"
											value="Save" tabindex="13" /></li>

										<li><a id="reset"
											class="main-btn dark-btn btn-hover btnreset" type="reset"
											value="Clear" tabindex="14">Reset</a></li>
									</ul>
								</div>
							</div>
						</div>
						<!-- 	Button End -->

						<!-- end card -->
					</div>
				</div>
			</div>


			<section class="single-detail-block">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					
						<div class="card-style mb-30">
							<div class="table-wrapper custom-datatable-p">
							<h6 class="mb-10">Institute Detail View</h6>
								<table class="table" id="institutemasterotherdatatable">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<th><h6>System Type</h6></th>
											<th><h6>University</h6></th>
											<th><h6>Institute Code</h6></th>
											<th><h6>Institute Name</h6></th>
											<th><h6>Institute Logo</h6></th>
											<th><h6>Institute Abbreviation</h6></th>
											<th><h6>Institute Unique Code</h6></th>
											<th><h6>Phone Number</h6></th>
											<th><h6>Email ID</h6></th>
											<th><h6>Country</h6></th>
											<th><h6>State</h6></th>
											<th><h6>District</h6></th>
											<th><h6>Address</h6></th>
											<th><h6>Total Sanction Seat</h6></th>
											<th><h6>Fees Structure</h6></th>
											<th><h6>Edit</h6></th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>Fees Sub-Category

					</div>

				</div>
			</section>
		</div>
	</div>
	<div class="modal fade custom-modal" id="okModalToggle"
		aria-hidden="true" aria-labelledby="okModalToggle" tabindex="-1">
		<div class="modal-dialog modal-xl modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">Fees Structure Details</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="table-wrapper table-responsive simple-table">
								<table class="table" id="viewotherdetailtable">
									<thead>
										<tr>
											<th><h6>Category</h6></th>
											<th><h6>Fees Category Type</h6></th>
											<th><h6>Fees Sub-Category</h6></th>
											<th><h6>Fees</h6></th>
											<th><h6>Hostel Facility Available</h6></th>
										</tr>
									</thead>
									<tbody id="viewotherdetailtablebody">
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<ul class="buttons-group">
						<li><a id="modal-close-btn"
							class="main-btn dark-btn n btn-hover">Close</a></li>
					</ul>

				</div>
			</div>
		</div>
	</div>
</section>



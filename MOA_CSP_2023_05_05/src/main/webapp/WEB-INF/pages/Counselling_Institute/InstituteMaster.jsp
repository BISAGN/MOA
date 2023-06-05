<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="assets/db_js/institutemaster.js"></script>
<script src="assets/councelling_js/validation.js"></script>
<script src="assets/db_js/CommonValidation.js"></script>

<script src="assets/db_js/jquery.blockUI.js"></script>
<script src="assets/db_js/jquery-confirm.min.js"></script>
<link rel="stylesheet" href="assets/db_css/jquery-confirm.min.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<link rel="stylesheet" href="assets/db_css/jquery-confirm.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>


<style>
.input-style-2 textarea {
	height: auto;
}
</style>

<section class="dashboard-page" id="insdiv">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Institute Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Institute
									Master</li>
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
						<h6 class="mb-25">Institute Master</h6>


						<div class="row">

							<input type="hidden" id="actiontype" name="actiontype"
								value="add"> <input type="hidden" id="insid"
								name="insid" value="0">

							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<!-- 									<div class="input-style-2 mb-0"> -->
								<!-- 										<h3 class="mb-2">Choose Any One</h3> -->
								<!-- 										</div> -->
								<div class="input-style-form-check">
									<div class="form-check radio-style" onclick="UploadExcel();">
										<input type="radio" class="form-check-input" id="Upload"
											name="Choise" value="Upload"> <label for="Upload"
											class="form-check-label">Upload Through Excel</label>
									</div>
									<div class="form-check radio-style" onclick="fillform();">
										<input type="radio" class="form-check-input" id="Fillform"
											name="Choise" value="Fillform" checked="checked"> <label
											class="form-check-label" for="ST">Fill Up Form</label>
									</div>
								</div>
								<!-- end input -->
							</div>
						</div>


						<div id="fillform" class="hide">
							<div class="row">
								<font color="red">* Indicates Mandatory Field</font> <br /> <br />

								<div class="col-12 col-sm-12 col-md-6 col-lg-4"
									id="systemtypediv">
									<div class="select-style-1">
										<label>System Type<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="form-select" id="systemtype" name="systemtype"
												tabindex="1">
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
											<select class="form-select" id="university" name="university"
												tabindex="2">
												<option value="-1">Please Select University</option>
											</select>
										</div>
									</div>
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Institute Code<span class="mandatory">*</span></label>
										<input type="text" class="form-control" id="inscode"
											name="inscode" placeholder="Enter Institute Code"
											maxlength="16" autocomplete="off" tabindex="3">
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Institute Name<span class="mandatory">*</span></label>
										<input type="text" id="insname"
											placeholder="Enter Institute Name" name="insname"
											autocomplete="off" maxlength="256" tabindex="4">
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Institute Image<span class="mandatory">*</span></label>
										<input type="file" class="form-control"
											placeholder="upload your document" name="instimage"
											id="instimage" tabindex="5">
											<a href='#' id="viewimage" name="viewimage" >View Image</a>
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Institute Abbreviation<span class="mandatory">*</span></label>
										<input type="text" id="insabb"
											placeholder="Enter Institute Abbreviation" name="insabb"
											autocomplete="off" maxlength="8" tabindex="8">
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Phone Number<span class="mandatory">*</span></label> <input
											type="text" id="mobilenumber"
											placeholder="Enter Enter Number" name="mobilenumber"
											autocomplete="off" maxlength="10" tabindex="7">
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
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
									<div class="input-style-2">
										<label>Address<span class="mandatory">*</span></label>
										<textarea type="text" id="address" placeholder="Enter Address"
											name="address" autocomplete="off" tabindex="12" rows="4"
											maxlength="256"></textarea>
									</div>
									<!-- end input -->
								</div>

							</div>
						</div>

						<div id="UploadExcel" class="hide">
							<div class="row">

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Downlaod Format</label>
										<ul class="buttons-group">
											<li class="m-0"><a href="../admin/institutemaster.xls"
												id="downlaod" name="downlaod" tabindex="11"
												class="main-btn info-btn square-btn btn-hover"> <i
													class="lni lni-download mr-5"></i>Download Excel
											</a></li>
										</ul>
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Upload Excel<span class="mandatory">*</span></label> <input
											type="file" class="form-control"
											placeholder="upload your document" name="institutedoc"
											id="institutedoc" tabindex="12">
									</div>
									<!-- end input -->
								</div>
								
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Upload ZIP File of Institute Image <span class="mandatory">*</span></label> <input
											type="file" class="form-control"
											placeholder="upload your document" name="imagezip"
											id="imagezip" tabindex="12">
									</div>
									<!-- end input -->
								</div>
							</div>
						</div>

						<ul class="buttons-group mainbtn">
							<li><input id="insbtn" name="insbtn"
								class="main-btn info-btn btn-hover" type="button" value="Save"
								tabindex="13" /></li>
							<li><a id="reset" class="main-btn dark-btn n btn-hover"
								type="reset" value="Clear" tabindex="14">Reset</a></li>
						</ul>

					</div>

					<!-- end card -->
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="institutemastertbl">
							<thead>
								<tr>
									<th><h6>No</h6></th>
									<th><h6>System Type</h6></th>
									<th><h6>University</h6></th>
									<th><h6>Institute Code</h6></th>
									<th><h6>Institute Name</h6></th>
									<th><h6>Institute Image</h6></th>
									<th><h6>Institute Abbreviation</h6></th>
									<th><h6>Institute Unique Code</h6></th>
									<th><h6>Phone Number</h6></th>
									<th><h6>Email ID</h6></th>
									<th><h6>Country</h6></th>
									<th><h6>State</h6></th>
									<th><h6>District</h6></th>
									<th><h6>Address</h6></th>
									<th><h6>Edit|Delete</h6></th>
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
</section>





<!-- 
<div id="insdiv">
	<div class="container mt-4">
		<div class="col-12">
			<div
				class="col-6 col-lg-4 mt-3 mb-3 w3-border w3-padding ws-lightgreen form-heading">
				<h5 class="m-0">Institute Master</h5>
			</div>
		</div>
		<div
			class="container-fluid w3-border w3-round w3-padding  ws-grey mb-3 main-form">
			<div class="row">
				<div class="col-12 col-md-6">
					<div class="mb-3">
						<label for="">Year <strong class="strong">*</strong></label>
						<input type="text" class="form-control" id="year"
							placeholder="Please Enter Year" name="year" autocomplete="off"
							maxlength="4" tabindex="1" readonly="readonly">
					</div>
				</div>

				<div class="col-12 col-md-6">
					<div class="mb-3">
						<input type="hidden" id="actiontype" name="actiontype" value="add">
						<input type="hidden" id="insid" name="insid" value="0"> <label
							for="">Institute Code <strong class="strong">*</strong></label>
						<input type="text" class="form-control" id="inscode"
							name="inscode" placeholder="Please Enter Institute Code"
							maxlength="32" autocomplete="off" tabindex="2">
					</div>
				</div>

			</div>
			<div class="row">

				<div class="col-12 col-md-6">
					<div class="mb-3">
						<label for="">Institute Name <strong class="strong">*</strong></label>
						<input type="text" class="form-control" id="insname"
							placeholder="Please Enter Institute Name" name="insname"
							autocomplete="off" maxlength="128" tabindex="3">
					</div>
				</div>
				<div class="col-12 col-md-6">
					<div class="mb-3">
						<label for="">Phone Number <strong class="strong">*</strong></label><input
							type="text" class="form-control" id="phno"
							placeholder="Please Enter Phone Number" name="phno"
							autocomplete="off" maxlength="12" tabindex="4">
					</div>
				</div>

			</div>
			<div class="row">
				<div class="col-12 col-md-6">
					<div class="mb-3">
						<label for="">EmailID <strong class="strong">*</strong></label>
						<input type="text" class="form-control" id="emailadd"
							placeholder="Please Enter Email ID" name="emailadd"
							autocomplete="off" maxlength="256" tabindex="5">
					</div>
				</div>
				<div class="col-12 col-md-6">
					<div class="mb-3">
						<label for="">Address<strong class="strong">*</strong></label>
						<textarea type="text" class="form-control" id="address"
							placeholder="Please Enter Address" name="address"
							autocomplete="off" tabindex="6"></textarea>
					</div>
				</div>

			</div>
			<div class="row">
				<div class="col-12 col-md-6">
					<div class="mb-3">
						<label for="">Country<strong class="strong">*</strong></label>
						<select class="form-select" id="country" name="country"
							tabindex="7" >
							<option value="-1">Please Select Country</option>
						</select>
					</div>
				</div>
				<div class="col-12 col-md-6">
					<div class="mb-3">
						<label for="">State<strong class="strong">*</strong></label>
						<select class="form-select" id="state" name="state" tabindex="8"
							>
							<option value="-1">Please Select State</option>
						</select>

					</div>
				</div>

			</div>

			<div class="row">
				<div class="col-12 col-md-6">
					<div class="mb-3">
						<label for="">District<strong class="strong">*</strong></label>
						<select class="form-select" id="district" name="district"
							tabindex="9">
							<option value="-1">Please Select District</option>
						</select>
					</div>
				</div>
			</div>

			<div class="row">
				<h5 class="center">
					<strong class="strong">OR</strong>
				</h5>
			</div>
			<div class="row">

				<div class="col-12 col-md-6">
					<div class="mb-3">
						<input type="checkbox" id="uplaodexcelcheck"
							name="uplaodexcelcheck" tabindex="10">Upload Excel
					</div>
					<div class="mb-3">
						<label for="">Downlaod Format</label> <a
							href='http://localhost:8012/AFMS/admin/institutemaster.xls'
							id='downlaod' name='downlaod' tabindex="11">Download Excel</a>
						<label for="">Downlaod Format</label> <a
							href='https://apps.bisag.co.in/AFMS/admin/institutemaster.xls'
							id='downlaod' name='downlaod' tabindex="11">Download Excel</a>
					</div>
				</div>
				<div class="col-12 col-md-6">
					<div class="mb-3">
						<label for="">Upload Excel <strong class="strong">*</strong></label>
						<input type="file" class="form-control"
							placeholder="upload your document" name="institutedoc"
							id="institutedoc" tabindex="12">
					</div>
				</div>


			</div>

			<div class="row m-0">
				<div
					class="col-12 mt-3 mb-3 w3-border w3-round w3-footer-padding ws-lightgreen text-center">
					<button type="button" class="btn btn-primary"
						id="insbtn" name="insbtn">Submit</button>
					<input type="reset" class="btn btn-success btn-sm" value="Clear"
						id="reset">

				</div>
			</div>

		</div>
	</div>


	<div class="container mt-3">
		<div class="table-wrapper">
			<table id="institutemastertbl"
				class="table no-margin table-striped table-hover table-bordered ">
				<thead>
					<tr>
						<th>No</th>
						<th>Institute Code</th>
						<th>Institute Name</th>
						<th>Phone Number</th>
						<th>Email ID</th>
						<th>Address</th>
						<th>Country</th>
						<th>State</th>
						<th>District</th>
						<th>Year</th>
						<th>Action</th>

					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div> -->
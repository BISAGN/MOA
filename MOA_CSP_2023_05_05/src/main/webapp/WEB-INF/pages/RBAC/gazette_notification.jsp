<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/common/multicheck.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script nonce="${cspNonce}" type="text/javascript">
	var username = "${username}";
</script>
 <!-- INTERNAL REMOVE START (NEED TO ADD IN PARTICULAR JSP)-->
			<link rel="stylesheet" href="assets/vendor/internal_css.css">
<!-- INTERNAL REMOVE END--> 

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span>Gazette Notification
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Gazette
									Notification</li>
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
					<form:form name="Gazette_Notification" id="Gazette_Notification"
						action="Gazette_Notification_Action" method='POST'
						modelAttribute="Gazette_Notification_cmd"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<h6 class="mb-25">Gazette Notification</h6>
							<div class="row">

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label for="text-input">Upload File<strong
											class="mandatory">*</strong></label> <input type="file" accept=".pdf"
											id="file_upload" class="form-control" name="file_upload"
											class="form-control"> <input type="hidden"
											id="file_upload_hid" name="file_upload_hid"
											class="form-control">

									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label for="username">Hyperlink<span class="mandatory">*</span></label>
										<input id="hyperlink" name="hyperlink" autocomplete="off"
											maxlength="100" type="text" placeholder="hyperlink" />
									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<h5 class="text-medium mb-10">Portal</h5>
									<div class="input-style-form-check">
										<!-- <label for="fname"></label> -->
										<div class="form-check checkbox-style">
											<input type="checkbox" id="common_cb" name="common_cb"
												class="form-check-input" value="0"> <label
												for="common" class="form-check-label">Common</label>
										</div>
										<div class="form-check checkbox-style">
											<input type="checkbox" id="nch_cb" name="nch_cb"
												class="form-check-input" value="1"> <label
												for="nch" class="form-check-label">NCH</label>
										</div>
										<div class="form-check checkbox-style">
											<input type="checkbox" id="ncism_cb" name="ncism_cb"
												class="form-check-input" value="2"> <label
												for="ncism" class="form-check-label">NCISM</label>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label for="text-input">From Date<span
											class="mandatory">*</span></label> <input type="text"
											name="from_date" id="from_date" maxlength="10"
											class="form-control-sm form-control" aria-required="true"
											autocomplete="off" class="custom_gazette_notificatio_to_date"
											value="DD/MM/YYYY" placeholder="Select From Date">
									</div>

								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label for="username">To Date<span class="mandatory">*</span></label>
										<input type="text" name="to_date" id="to_date" maxlength="10"
											class="form-control-sm form-control" aria-required="true"
											autocomplete="off" class="custom_gazette_notificatio_to_date"
											value="DD/MM/YYYY" placeholder="Select To Date">
									</div>

								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="text-input">Message<strong
											class="mandatory">* </strong></label>
										<textarea id="message" name="message" rows="5" cols="50"
											autocomplete="off" maxlength="250" placeholder="Message"></textarea>

										<input type="hidden" id="id" name="id" class="mt-3" value="0"
											autocomplete="off">
									</div>
								</div>

							</div>

							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input type="submit" id="btn-save"
									class="main-btn info-btn btn-hover" value="Save" /></li>
								<li><a href="gazette_notification_url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
							</ul>
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_elective_course_master">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="2"><h6>Degree</h6></th>
									<th id="3"><h6>Course</h6></th>
									<th id="4"><h6>Profession</h6></th>
									<th><h6>Upload Image</h6></th>
									<th><h6>Demo Video</h6></th>
									<th><h6>Action</h6></th>
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

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		datepicketDate('from_date');
		datepicketDate('to_date');

		// 			$( "#from_date" ).datepicker("option", "minDate", 0);
		// 		 	$( "#to_date").datepicker("option", "minDate", 0);

		// 			$( "#from_date").datepicker( "option", "maxDate", null);
		// 			$( "#to_date").datepicker( "option", "maxDate", null);

		if (window.location.href.includes("msg")) {
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}
	});
	
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btn-save').onclick = function() {
			return Validate();
		};
	});
	
	function Validate() {
		
		if ($("#file_upload").val().trim() == "") {
			alert("Please Select Upload File");
			$("input#file_upload").focus();
			return false;
		}
		if ($("#hyperlink").val().trim() == "") {
			alert("Please Enter Hyperlink");
			$("input#hyperlink").focus();
			return false;
		}
		
		if( $("#from_date").val() == "" ||  $("#from_date").val() == "DD/MM/YYYY"){
			alert("Please Enter From Date");
			$("#from_date").focus();
			return false;
		}
		if( $("#to_date").val() == "" ||  $("#to_date").val() == "DD/MM/YYYY"){
			alert("Please Enter To Date");
			$("#to_date").focus();
			return false;
		}
		if ($("#message").val().trim() == "") {
			alert("Please Enter Message");
			$("input#message").focus();
			return false;
		}
		
		 return true;
	}
</script>
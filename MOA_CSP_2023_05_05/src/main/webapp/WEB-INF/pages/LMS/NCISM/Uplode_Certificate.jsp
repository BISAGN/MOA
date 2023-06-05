<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="assets/font/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/font/Lineicons/lineicons.css" />
<link rel="stylesheet" href="assets/db_css/db_style.css">
<link rel="stylesheet" href="assets/db_css/db_responsive.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<link rel="stylesheet" href="js/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/collapse/collapse.css"> -->
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- resizable_col -->
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span>Upload certificate
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Upload
									certificates</li>
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
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form action="uplode_certificateUrl" method="POST"
						modelAttribute="UplodeCertificate_CMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Upload certificate</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="username">Upload Certificate<span
												class="mandatory">*</span></label> <input type="file" accept=".pdf"
												id="uplode_certificate" name="uplode_certificate"
												class="form-control"> <input type="hidden" id="id"
												name="id" value="0" class="mt-3 form-control"
												autocomplete="off" />
										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="username">Course Name<span
												class="mandatory"></span></label> <input id="coursename"
												name="coursename" readonly="readonly" autocomplete="off" />
											<input type="hidden" id="id" name="id" value="0" class="mt-3"
												autocomplete="off" />
										</div>
										<!-- end input -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-2">
											<label for="username">Start Date<span
												class="mandatory">*</span></label> <input type="text"
												name="start_date" id="start_date" maxlength="10"
												class="form-control-sm form-control " aria-required="true"
												autocomplete="off" style="color: rgb(0, 0, 0);"
												value="DD/MM/YYYY" placeholder="Select Start Date" /> <input
												type="hidden" id="id" name="id" value="0" class="mt-3"
												autocomplete="off" />
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-2">
											<label for="username">End Date<span class="mandatory">*</span></label>
											<input type="text" name="end_date" id="end_date"
												maxlength="10" class="form-control-sm form-control "
												aria-required="true" autocomplete="off"
												style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"
												placeholder="Select End Date" /> <input type="hidden"
												id="id" name="id" value="0" class="mt-3" autocomplete="off" />
										</div>

									</div>

								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">

											<li><input class="main-btn info-btn btn-hover"
												id="submit" type="submit" /></li>
											<li><a href="Free_Course_ViewUrl"
												class="main-btn dark-btn n btn-hover" type="button">Reset</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>


<script>
	$(document).ready(function() {

		datepicketDate('start_date');
		datepicketDate('end_date');

		$("#start_date").datepicker("option", "minDate", 0);
		$("#end_date").datepicker("option", "minDate", 0);

		$("#start_date").datepicker("option", "maxDate", null);
		$("#end_date").datepicker("option", "maxDate", null);

		$('input#coursename').val('${Onlinecourese.coursename}');

		if (window.location.href.includes("msg")) {
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}
	});

	function Validate() {

		if ($("#uplode_certificate").val().trim() == "") {
			alert("Please Uplode Certificate");
			$("#uplode_certificate").focus();
			return false;
		}
		var uplode_certificate = $("#uplode_certificate").val();
		var lastDot = uplode_certificate.lastIndexOf('.');
		var fileName = uplode_certificate.substring(0, lastDot);
		var ext = uplode_certificate.substring(lastDot + 1);

		if (ext != "pdf") {
			alert("Please Upload PDF File");
			$("input#uplode_certificate").focus();
			return false;
		}

		if ($("#start_date").val() == ""
				|| $("#start_date").val() == "DD/MM/YYYY") {
			alert("Please Select Start Date");
			$("#start_date").focus();
			return false;
		}
		if ($("#end_date").val() == "" || $("#end_date").val() == "DD/MM/YYYY") {
			alert("Please Select End Date");
			$("#end_date").focus();
			return false;
		}
		return true;
	}

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('submit').onclick = function() {
			return Validate();
		};
		document.getElementById('start_date').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('start_date').onfocus = function() {
			return this.style.color = '#000000';
		};
		document.getElementById('start_date').onblur = function() {
			return clickrecall(this, 'DD/MM/YYYY');
			//validateDate_BackDate(this.value,this);
		};
		document.getElementById('start_date').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('start_date').onchange = function() {
			return onchangeCount(this.value);
			clickrecall(this, 'DD/MM/YYYY');
			//validateDate_FutureDate(this.value,this);
		};
		document.getElementById('end_date').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('end_date').onfocus = function() {
			this.style.color = '#000000';
		};
		document.getElementById('end_date').onblur = function() {
			return clickrecall(this, 'DD/MM/YYYY');
		};
		document.getElementById('end_date').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('end_date').onchange = function() {
			onchangeCount(this.value);
			clickrecall(this, 'DD/MM/YYYY');
			checkTodate(this, start_date);

		};

	});

	// function checkTodate(obj,start_date) {
	// 		alert("------")

	// 		if(start_date.value =="DD/MM/YYYY"){

	// 			var Date1= $("#start_date").val();  

	// 				if(Date1 !=""){
	// 					var id = obj.id;
	// 					var myDate = document.getElementById(id).value;
	// 					if ((Date.parse(myDate) < Date.parse(Date1)))
	// 							{
	// 						alert('To Date should not be before Start Date');	
	// 						obj.value = "";
	// 					}
	// 				}				
	// 		}
	// 		else
	// 		{
	// 			alert("Please Select Start Date first.");
	// 			$("#start_date").focus();
	// 			obj.value = "";
	// 		}	
	// 	}

	function onchangeCount(val) {
		if (document.getElementById("start_date").value != "DD/MM/YYYY"
				&& (document.getElementById("end_date").value != "DD/MM/YYYY")
				&& (document.getElementById("end_date").value != "")) {
			//	 		$("#course_fee").val("");
			//			$("#demo_video").val("");
			//	 		$("#old_elective_name").val("");
			//	 		$("#course_switch_duration").val("");
			//	 		$("select#system_id").val("0");
			//	 		$("select#system_id").change();

			var start_date = document.getElementById("start_date").value.split(
					'/').reverse().join('-');
			var end_date = document.getElementById("end_date").value.split('/')
					.reverse().join('-');
			var start_dt = new Date(start_date);
			var end_dt = new Date(end_date);
			// To calculate the time difference of two dates
			var Difference_In_Time = end_dt.getTime() - start_dt.getTime();
			// To calculate the no. of days between two dates
			var Difference_In_Days = (Difference_In_Time / (1000 * 3600 * 24));

			var Difference_In_Weeks = Math.floor(Difference_In_Days / 7);
			var remainday = (Difference_In_Days) - (Difference_In_Weeks * 7);
			if (Difference_In_Days < 0) {
				alert("To Date should not be before Start Date!");
				$("input#start_date").val("DD/MM/YYYY");
				$("input#end_date").val("DD/MM/YYYY");
				// 				$("#duration").html("");
				return false;
			}
			if (Difference_In_Days > 0) {
				if (Difference_In_Weeks == 0) {
					$("#duration").html(Difference_In_Days + " Days");
				} else if (remainday == 0) {
					$("#duration").html(Difference_In_Weeks + " Weeks ");
				} else {
					$("#duration").html(
							Difference_In_Weeks + " Weeks " + remainday
									+ " Days");
				}
			}
		}
	}
</script>

</html>

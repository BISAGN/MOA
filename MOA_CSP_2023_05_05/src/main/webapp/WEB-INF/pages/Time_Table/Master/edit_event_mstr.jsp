<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Update Event Master</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update
									Event/Holiday Master</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="Edit_Event_Master" id="Edit_Event_Master"
						action="edit_Event_Action" method="POST" 
						modelAttribute="edit_EventCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<!-- <h6 class="mb-25">Update Event Master</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Event/Holiday Master<span class="mandatory">*</span></label> <input
												type="text" id="event_name" name="event_name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100" placeholder="Event Name" />
										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-2">
											<label for="username">Event Date<span
												class="mandatory">*</span></label> <input type="text"
												name="event_date" id="event_date" maxlength="10"
												class="form-control-sm form-control effect-9 "
												aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Holiday<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="holiday" id="holiday">
													<!-- <option value="0">---Select---</option>-->
													<option value="1">Yes</option>
													<option value="2">No</option> 
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group d-flex justify-content-center">
											<li><a href="event_mstrUrl"
											class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
													<i class="lni lni-chevron-left"></i>Back
											</a></li>
											<li><input type="submit" id="update" value="Update"
												class="main-btn deactive-btn btn-hover" /> <input
												type="hidden" name="updateid" id="updateid"
												value="${updateid}" class="main-btn deactive-btn btn-hover btnupda"/></li>
										</ul>

									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		datepicketDate('event_date');
		$( "#event_date").datepicker( "option", "maxDate", null);

		var ed = '${Event_Name.event_date}'.substring(0, 10);
		var edt = ed.split('-').reverse().join('/')

		$('#id').val('${Event_Name.id}');
		$('#event_name').val('${Event_Name.event_name}');
		$('#event_date').val(edt);
		$('#holiday').val('${Event_Name.holiday}');
		$('#holiday').trigger('change');
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});

		// 		.value.split('/').reverse().join('-'); 

	});

	function Validation() {

		if ($("#event_name").val().trim() == "") {
			alert("Please Enter Event Name.");
			$("input#event_name").focus();
			return false;
		}

		if ($("#event_date").val() == "DD/MM/YYYY"
				|| $("#event_date").val() == "") {
			alert("Please Enter Event Date.");
			$("#event_date").focus();
			return false;
		}

		if ($("select#holiday").val().trim() == "0") {
			alert("Please Select Holiday.");
			$("Select#holiday").focus();
			return false;
		}
		return true;

	}

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('event_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(event);
		};

		// 		document.getElementById('event_date').onkeypress = function() {
		// 			return onlyAlphabetsStringSpace(event);
		// 		};
		document.getElementById('event_date').onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('event_date').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};

		document.getElementById('event_date').onblur = function() {
			clickrecall(this, 'DD/MM/YYYY');
			validateDate_BackDate(this.value, this);
		};

		document.getElementById('event_date').onfocus = function() {
			this.style.color = '#000000';
		};

		document.getElementById('event_date').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		document.getElementById('holiday').onkeypress = function() {
			return onlyAlphabetsStringSpace(event);
		};

		document.getElementById('update').onclick = function() {
			return Validation();
		};

	});
</script>


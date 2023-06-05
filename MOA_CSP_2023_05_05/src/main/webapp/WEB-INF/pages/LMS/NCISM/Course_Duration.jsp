<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/common/multicheck.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span>Course Duration
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Course
									Duration</li>
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
					<form:form name="courseduration" id="courseduration"
						action="CourseDurationAction" method='POST'
						modelAttribute="CourseDurationCMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Course Duration</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-2">
											<label for="text-input">From Date<span
												class="mandatory">*</span></label> <input type="text"
												name="start_date" id="start_date" maxlength="10"
												class="form-control-sm form-control" aria-required="true"
												autocomplete="off" value="DD/MM/YYYY"
												placeholder="Select From Date">
										</div>

									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-2">
											<label for="username">To Date<span class="mandatory">*</span></label>
											<input type="text" name="end_date" id="end_date"
												maxlength="10" class="form-control-sm form-control"
												aria-required="true" autocomplete="off" value="DD/MM/YYYY"
												placeholder="Select To Date">
										</div>

									</div>



									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Duration<span
												class="mandatory">*</span></label> <span class="auto-input"
												for="text-input" id="duration"> </span> <input type="hidden"
												id="id" name="id" class="mt-3" value="0" autocomplete="off">
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">System<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="system_id" id="system_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSystemList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
											<div class="input-style-1">
												<input type="hidden" id="id" name="id" class="mt-3"
													value="0" autocomplete="off">
											</div>
										</div>
										<!-- end select -->
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="degree_id" id="degree_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="username">Profession<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="term_id" id="term_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${gettermList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
											<div class="input-style-1">
												<input type="hidden" id="id" name="id" value="0"
													class="mt-3" autocomplete="off" />
											</div>
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Course Switch Duration (In
												Days)<span class="mandatory">*</span>
											</label> <input id="course_switch_duration"
												name="course_switch_duration" maxlength="2"
												autocomplete="off"
												placeholder="Please Enter Course Switch Duration in Days">
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Course Fee<span
												class="mandatory">*</span></label> <input id="course_fee"
												name="course_fee" autocomplete="off"
												placeholder="Please Enter Course Fee" maxlength="10">
										</div>
										<!-- end select -->
									</div>


									<!-- 							<div class="row"> -->
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<div class="input-style-1 mb-0">
											<label id="elective_name">Course (0)<span
												class="mandatory">*</span></label> <input type="text"
												id="search_data" onkeyup="fnFilterChk(this.value);"
												autocomplete="off" placeholder="Search course ">
										</div>
										<div class="col-two" id="checkboxes"></div>

										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<div class="input-style-1 mb-0">
											<label>Selected Course<span class="mandatory"></span></label>
											<input type="text" id="value" name="value" maxlength="70"
												placeholder="Search course "
												onkeyup="fnFilterChk2(this.value);" />
										</div>
										<div class="badges-groups">
											<ul id="show_list" class="buttons-group">

											</ul>
											<div class="form-check checkbox-style"
												id="submodulecheckboxes"></div>
										</div>
										<!-- end input -->
									</div>

									<input type="hidden" name="old_elective_name"
										id="old_elective_name" /> <input type="hidden"
										name="new_elective_name" id="new_elective_name" /> <input
										type="hidden" name="add_elective_name" id="add_elective_name" />
									<input type="hidden" name="remove_elective_name"
										id="remove_elective_name" />
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="submit"
												value="Save" /></li>
											<li><a href="CourseDurationUrl"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
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


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(
			function() {
				datepicketDate('start_date');
				datepicketDate('end_date');

				$("#start_date").datepicker("option", "minDate", 0);
				$("#end_date").datepicker("option", "minDate", 0);

				$("#start_date").datepicker("option", "maxDate", null);
				$("#end_date").datepicker("option", "maxDate", null);

				initiateChkFn('new_elective_name', 'old_elective_name',
						'add_elective_name', 'remove_elective_name', 'Course',
						'elective_name');

				if (window.location.href.includes("msg")) {
					var url = window.location.href.split("?msg")[0];
					window.location = url;
				}
			});

	function GetCoursebysystem() {

		$.ajaxSetup({
			async : false
		});
		var start_date = $("input#start_date").val();
		var end_date = $("input#end_date").val();

		if (start_date == "DD/MM/YYYY" || start_date == ""
				|| start_date == null) {
			alert("please select From Date")
			$("input#start_date").focus();
			$("select#system_id").val("0");
			return false;
		}

		if (end_date == "DD/MM/YYYY" || end_date == "" || end_date == null) {
			alert("please select To Date")
			$("input#end_date").focus();
			$("select#system_id").val("0");
			return false;
		}

		var system_id = $("select#system_id").val();
		var degree_id = $("select#degree_id").val();
		var term_id = $("select#term_id").val();

		$
				.post(
						"getcoursewithcoursecontentbysystem?" + key + "="
								+ value,
						{
							system_id : system_id,
							degree_id : degree_id,
							term_id : term_id
						},
						function(j) {

							if (j == "") {
								$("#course_fee").val("");
								$("#demo_video").val("");
								$("#course_switch_duration").val("");
							}

							var options = '';
							for (var i = 0; i < j.length; i++) {
								options += '<div class="form-check radio-style checkbox-style d-flex align-items-center">'
										+ ' <label for="one" class="chklist"> <input class="form-check-input mr-5" type="checkbox" name="multisub" value="'+ j[i][0]+ '" '
									+ ' id="multisub'+ j[i][0] + '" /> '
										+ j[i][1] + '</label> </div>';
								// 	chkClick();
							}
							$("#checkboxes").html(options);
							for (var i = 0; i < j.length; i++) {
								chxboxOnclick(j[i][0]);
							}
						});

		System_elec_courseChangeFn($("select#system_id").val(), start_date,
				end_date);
	}

	function chxboxOnclick(ser) {
		document.getElementById('multisub' + ser).onclick = function() {
			chkClick();
		};
	}

	function System_elec_courseChangeFn(system_id, start_date, end_date) {
		$.ajaxSetup({
			async : false
		});
		$("input[type='checkbox'][name='multisub']").attr('checked', false);
		if (system_id > 0) {
			$.post("getSystem_CourseDuration?" + key + "=" + value, {
				system_id : system_id,
				start_date : start_date,
				end_date : end_date
			}, function(data) {
			}).done(function(data) {
				$("#old_elective_name").val("");
				var v = data.length;
				if (v != 0) {
					//define arry
					var d = [];
					for (var i = 0; i < v; i++) {
						d.push(data[i][0]);
					}
					$("#course_fee").val(data[0][1]);
					$("#course_switch_duration").val(data[0][3]);
					setChk(d);
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		}
		chkClick();
	}

	document
			.addEventListener(
					'DOMContentLoaded',
					function() {

						document.getElementById('btn-save').onclick = function() {
							return Validation();
						};

						document.getElementById('course_switch_duration').onkeypress = function() {
							return isNumberKey0to9(event);
						};

						document.getElementById('course_fee').onkeypress = function() {
							return isNumberKey0to9(event);
						};

						// 		 document.getElementById('system_name').oninput = function () {
						// 			return validateNumber(event);
						// 		onkeypress="return onlyAlphabets(event);"
						// 		};  

						document.getElementById('system_id').onchange = function() {
							return getdegreebysystem(this.value);
						};

						document.getElementById('term_id').onchange = function() {
							return GetCoursebysystem();
						};
						document.getElementById('start_date').onclick = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('start_date').onfocus = function() {
							this.style.color = '#000000';
						};
						document.getElementById('start_date').onblur = function() {
							clickrecall(this, 'DD/MM/YYYY');
						};
						document.getElementById('start_date').onkeyup = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('start_date').onchange = function() {
							onchangeCount(this.value);
							clickrecall(this, 'DD/MM/YYYY');
						};
						document.getElementById('end_date').onclick = function() {
							clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('end_date').onfocus = function() {
							this.style.color = '#000000';
						};
						document.getElementById('end_date').onblur = function() {
							clickrecall(this, 'DD/MM/YYYY');
						};
						document.getElementById('end_date').onkeyup = function() {
							clickclear(this, 'DD/MM/YYYY');
						};

						document.getElementById('end_date').onchange = function() {
							onchangeCount(this.value);
							clickrecall(this, 'DD/MM/YYYY');
						};
						// 	document.getElementById('multisub').onclick = function() {
						// 		return chkClick();
						// 	};

					});

	function getdegreebysystem(obj) {
		var system_id = obj;
		$
				.post('getDegreefromSystem?' + key + "=" + value, {
					system_id : system_id
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_id").html(options);
						});
	}

	function Validation() {
		var start_date = $("input#start_date").val();
		var end_date = $("input#end_date").val();
		if (start_date == "DD/MM/YYYY" || start_date == ""
				|| start_date == null) {
			alert("Please Select From Date")
			$("input#start_date").focus();
			return false;
		}
		if (end_date == "DD/MM/YYYY" || end_date == "" || end_date == null) {
			alert("Please Select To Date")
			$("input#end_date").focus();
			return false;
		}
		if ($("#system_id").val() == "0") {
			alert("Please Select System Name.");
			$("select#system_id").focus();
			return false;
		}
		if ($("#degree_id").val() == "0") {
			alert("Please Select Degree Name.");
			$("select#degree_id").focus();
			return false;
		}
		if ($("#term_id").val() == "0") {
			alert("Please Select Term Name.");
			$("select#term_id").focus();
			return false;
		}
		if ($("#course_switch_duration").val().trim() == "") {
			alert("Please Enter Course Switch Duration.");
			$("input#course_switch_duration").focus();
			return false;
		}
		if ($("#course_fee").val().trim() == "") {
			alert("Please Enter Course Fee.");
			$("input#course_fee").focus();
			return false;
		}
		// 		if ($("#course_switch_duration").val().trim() == "") {
		// 			alert("Please Enter Course Switch Duration.");
		// 			$("input#course_switch_duration").focus();
		// 			return false;
		// 		}
		if ($("#new_elective_name").val().trim() == "") {
			alert("Please Select atleast One Course.");
			$("select#new_elective_name").focus();
			return false;
		}
		return true;
	}

	function onchangeCount(val) {
		if (document.getElementById("start_date").value != "DD/MM/YYYY"
				&& (document.getElementById("end_date").value != "DD/MM/YYYY")
				&& (document.getElementById("end_date").value != "")) {
			$("#course_fee").val("");
			$("#demo_video").val("");
			$("#old_elective_name").val("");
			$("#course_switch_duration").val("");
			$("select#system_id").val("0");
			$("select#system_id").change();

			var start_date = document.getElementById("start_date").value.split(
					'/').reverse().join('-');
			var end_date = document.getElementById("end_date").value.split('/')
					.reverse().join('-');
			var start_dt = new Date(start_date);
			var end_dt = new Date(end_date);
			// To calculate the time difference of two dates
			var Difference_In_Time = end_dt.getTime() - start_dt.getTime();
			// To calculate the no. of days between two dates
			var Difference_In_Days = (Difference_In_Time / (1000 * 3600 * 24)) + 1;

			var Difference_In_Weeks = Math.floor(Difference_In_Days / 7);
			var remainday = (Difference_In_Days) - (Difference_In_Weeks * 7);
			if (Difference_In_Days < 1) {
				alert("To Date should not be before From Date");
				$("input#start_date").val("DD/MM/YYYY");
				$("input#end_date").val("DD/MM/YYYY");
				$("#duration").html("");
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

	function fnFilterChk2(val) {
		var re = new RegExp(val, "i")
		$('.subspan').each(function() {
			var text = $(this).text(), matches = !!text.match(re);
			$(this).toggle(matches)
		});
	}
</script>

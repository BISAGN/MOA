<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- <script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>	 -->
<script src="js/common/multicheck.js" type="text/javascript"></script>
<!-- <link rel="stylesheet" href="js/common/multicheck.css"> -->
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

<script nonce="${cspNonce}" type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
	$(document).ready(function() {
		$('#role').keyup(function() {
			this.value = this.value.toUpperCase();
		});

		// 		getsyslist();

	});
</script>

<section class="dashboard-page">
	<div class="container-fluid">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Link System And Elective Courses</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Link
									System And Elective Courses</li>
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
					<form:form name="roleMst" id="roleMst"
						action="link_system_Elective_Course_Action" method='POST'
						modelAttribute="link_system_Elective_Course_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Link System And Elective Courses</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<div class="select-style-1">
											<label for="text-input">System<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="system_id" id="system_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSystemList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<div class="select-style-1">
											<label for="text-input">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="degree_id" id="degree_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>
								</div>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<div class="input-with-selection">
											<div class="input-style-1 mb-0">
												<label id="elective_name"> Elective Course (0)<span
													class="mandatory">*</span></label>
												<!-- 											<label><span id="elective_name">Elective Course (0)</span><span class="mandatory">*</span></label> -->
												<input type="text" id="search_data" autocomplete="off"
													placeholder="Search Elective Course Name">

											</div>
											<div class="col-two" id="checkboxesECN"></div>
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<div class="input-with-selection">
											<div class="input-style-1 mb-0">
												<label>Selected Elective Course<span
													class="mandatory">*</span></label> <input type="text" id="value"
													name="value" maxlength="70"
													placeholder="Selected Elective Cours" />
											</div>
											<div class="badges-groups">
												<ul id="show_list" class="buttons-group">
												</ul>
											</div>
										</div>
										<!-- end input -->
									</div>
								</div>

								<input type="hidden" name="old_elective_name"
									id="old_elective_name" /> <input type="hidden"
									name="new_elective_name" id="new_elective_name" /> <input
									type="hidden" name="add_elective_name" id="add_elective_name" />
								<input type="hidden" name="remove_elective_name"
									id="remove_elective_name" />
								<!-- <div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="form-check checkbox-style" id="checkboxes"></div>
									end input
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="form-check checkbox-style" id="submodulecheckboxes"></div>
									end input
								</div>
								<div class="row">
									<div class="col-12">
										<div id="screencheckboxes"></div>
										end input
									</div>
								</div>
							</div> -->

							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">

											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"> <i class="lni lni-search-alt"></i>Search
											</a></li>
											<li><input class="main-btn info-btn btn-hover btnsave"
												type="submit" value="Save" id="btn-save"></li>
											<li><a href="link_system_Elective_Course_url"
												class="main-btn dark-btn n btn-hover btnreset" type="button">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>

						</div>
						<!-- end card -->

						<section class="single-detail-block">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="card-style mb-30">
										<div class="table-wrapper table-responsive custom-datatable-p">
											<table class="table" id="Link_System_Elective_Course">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th><h6>System</h6></th>
														<th><h6>Degree</h6></th>
														<th><h6>Elective course</h6></th>
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
						</section>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(
			function() {

				mockjax1('Link_System_Elective_Course');
				table = dataTable('Link_System_Elective_Course');
				$('#btn-reload').on('click', function() {
					table.ajax.reload();
				});

				if ('${list.size()}' == "") {
					$("div#Link_System_Elective_Course").hide();
				}

				initiateChkFn('new_elective_name', 'old_elective_name',
						'add_elective_name', 'remove_elective_name',
						'Elective Course', 'elective_name');

				if (window.location.href.includes("msg")) {
					var url = window.location.href.split("?msg")[0];
					window.location = url;
				}

			});

	function setTimeLoadForTable() {

		document.getElementById('system_id').onchange = function() {
			return GetSystemFromDegree();
		};

		document.getElementById('degree_id').onchange = function() {
			return System_elec_courseChangeFn();
		};

		document.getElementById('search_data').onkeyup = function() {
			return fnFilterChk(this.value);
		};
		// 		document.getElementById('search_data').onclick = function() {
		// 			return  checkValidation(ser);
		// 		};

		document.getElementById('btn-reload').onclick = function() {
			return isValid();
		};

		document.getElementById('btn-save').onclick = function() {
			return isValid();
		};

		// 		document.getElementById('multisub').onclick = function() {
		//			chkClick();
		//		};

	}

	function data(Link_System_Elective_Course) {
		jsondata = [];
		var table = $('#' + Link_System_Elective_Course).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();

		$.post(
				"getFilterLink_System_Elective_Course_data?" + key + "="
						+ value, {
					startPage : startPage,
					pageLength : pageLength,
					Search : Search,
					orderColunm : orderColunm,
					orderType : orderType,
					system_id : system_id,
					degree_id : degree_id

				}, function(j) {
					for (var i = 0; i < j.length; i++) {
						jsondata.push([ j[i][3], j[i][0], j[i][1], j[i][2] ]);
					}
				});
		$.post("getTotalLink_System_Elective_Course_dataCount?" + key + "="
				+ value, {
			system_id : system_id,
			degree_id : degree_id

		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	function addcbdata(system_id, degree_id) {
		$('div#checkboxesECN').empty();
		$
				.post("getSystemFromElec_Course?" + key + "=" + value, {
					system_id : system_id,
					degree_id : degree_id
				}, function(data) {
				})
				.done(
						function(data) {
							for (var i = 0; i < data.length; i++) {
								var ser = parseInt(i) + 1;
								$("div#checkboxesECN")

										.append(
												'<div class="form-check radio-style checkbox-style">'
														+ ' <input class="form-check-input mr-5" type="checkbox" name="multisub" id="multisub'+ser+'" value="'
														+ data[i][0]
														+ '" />'
														+ '<label for="one" class="">'
														+ data[i][1]
														+ '</label>' + '</div>');

								chxboxOnclick(ser);
							}
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	function System_elec_courseChangeFn() {
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		addcbdata(system_id, degree_id);

		$.ajaxSetup({
			async : false
		});
		$("input[type='checkbox'][name='multisub']").attr('checked', false);
		if (degree_id > 0) {
			$.post("getDegreeFromCourse_Checked?" + key + "=" + value, {
				degree_id : degree_id,
				system_id : system_id
			}, function(data) {
			}).done(function(data) {

				var v = data.length;

				if (v != 0) {
					var d = [];
					for (var i = 0; i < v; i++) {
						d.push(data[i][0]);
					}
					console.log(d);
					setChk(d);
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		}

		chkClick();

	}

	// 	function setChk1(data) {

	// 		var str = "";
	// 		for (i = 0; i < data.length; i++) {
	// 			$(
	// 					"input[type=checkbox][name='multisub'][value='"
	// 							+ data[i][0] + "']").prop("checked", true);
	// 			if (i == 0) {
	// 				str = data[i];
	// 			} else {
	// 				str = str + "," + data[i];
	// 			}
	// 			$("#" + old_store_id).val(str);

	// 		}
	// 	}

	function GetSystemFromDegree() {

		var system_id = $("select#system_id").val();

		$
				.post(
						"getSystemlistFromDegree?" + key + "=" + value,
						{
							system_id : system_id
						},
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

	function isValid() {

		if ($("select#system_id").val() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("select#degree_id").val() == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}
		if ($("#new_elective_name").val() == "") {
			alert("Please Select atleast One Elective Course.");
			$("#new_elective_name").focus();
			return false;
		}

		return true;
	}

	// 	function checkValidation(ser){
	// 		debugger;
	// 	var checkBox = document.getElementById('multisub'+ser);
	// 	if (checkBox.checked == false){	 
	// 		//alert("Please select Check box");	  

	// 			  alert("Please Select Elective Course ");
	// 			  return false;
	// 		}

	// 	}

	function chxboxOnclick(ser) {
		document.getElementById('multisub' + ser).onclick = function() {
			chkClick();
		};
	}
</script>
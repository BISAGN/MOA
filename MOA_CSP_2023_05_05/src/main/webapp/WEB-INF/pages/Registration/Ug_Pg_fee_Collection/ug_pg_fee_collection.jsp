<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/common/commonmethod.js"></script>

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

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span>Fee Collection
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Fee
									Collection</li>
							</ol>
						</nav>
					</div>
				</div>

			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<form:form name="ug_pg_Feecollect_form" id="ug_pg_Feecollect_form"
						action="ug_pg_Feecollect_Action" method="post"
						modelAttribute="ug_pg_Feecollect_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<div class="row">
									<div class="col-12 col-sm-12 col-md-12 col-lg-12"
										id="fees_nd_msg">
										<div class="alert alert-danger alert-dismissible">
											<button type="button" class="btn-close"
												data-bs-dismiss="alert"></button>
											Institute has not updated the student wise fees details yet.
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-12 col-lg-12"
										id="not_varified">										
										<div class="alert alert-warning alert-dismissible">
											<button type="button" class="btn-close"
												data-bs-dismiss="alert"></button>
											Document verification at institute level is pending.
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-12 col-lg-12">
										<div class="alert alert-success alert-dismissible" id="fees_paid_msg">
											<button type="button" class="btn-close"
												data-bs-dismiss="alert"></button>
											Fees Already Paid.
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Type Of Degree<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-control form-control-lg"
													name="type_of_degree" id="type_of_degree">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${gettype_of_degree}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>

												</select> <input type="hidden" id="id" name="id" value="0"
													autocomplete="off" />
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-control form-control-lg"
													name="degree_name" id="degree_name">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">profession<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="form-control form-control-lg" name="term_id"
													id="term_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${gettermList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select> <input type="hidden" id="id" name="id" value="0"
													autocomplete="off" />
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Student Name <span class="mandatory">*</span></label>
											<input type="text" id="student_name" name="student_name"
												class="autocomplete xt-transupp" autocomplete="off"
												maxlength="100" placeholder="Student Name " /> <input
												type="hidden" id="studentId_hid" name="studentId_hid"
												value="0" autocomplete="off" />

										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-8"
										id="payment_type_div">
										<div class="input-style-1 mb-0">
											<label>Choose Payment</label>
										</div>
										<div class="custom-choose-one">
											<div class="input-style-form-check">
												<div class="row">
													<div class="col-12 col-sm-12 col-md-6 col-lg-6">
														<div class="form-check radio-style">
															<input type="radio" class="form-check-input" id="full_rb"
																name="Choise" value="Full"> <label for="full_rb"
																class="form-check-label">Full Payment</label>
														</div>
													</div>
													<div class="col-12 col-sm-12 col-md-6 col-lg-6">
														<div class="form-check radio-style">
															<input type="radio" class="form-check-input" id="part_rb"
																name="Choise" value="Part"> <label
																class="form-check-label" for="part_rb">Part
																Payment</label>
														</div>
													</div>
												</div>
											</div>
											<input type="hidden" id="isfullpartHid" name="isfullpartHid"
												value="0" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-12 col-lg-12"
										id="fees_tb_div">
										<section class="single-detail-block">
											<div class="row">
												<div class="col-12 col-sm-12 col-md-12 col-lg-12">
													<div class="table-wrapper table-responsive custom-table">
														<table class="table" id="#">
															<thead>
																<tr>
																	<th><h6 id="$">Part</h6></th>
																	<th><h6 id="$">Amount</h6></th>
																</tr>
															</thead>
															<tbody id="fees_tb_tbody">
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</section>
										<input type="hidden" id="ch_tb_ids" name="ch_tb_ids" value="">
									</div>
									<div class="col-12 col-sm-12 col-md-12 col-lg-12"
										id="fees_nd_btn">
										<ul class="buttons-group mainbtn">
											<li><a href="set_student_fees_amount_Url"
												class="main-btn deactive-btn btn-hover btnupda">Update Student Wise Fees Data</a></li>
										</ul>
										<input type="hidden" name="no_of_part_hid" id="no_of_part_hid"
											value="${sturoledata[0].no_of_part}">
									</div>
                                     
								</div>
								
								<div class="row">
								<div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-5 mx-auto">
									<div class="custom-payment-block custom-choose-one">
											
											<div class="input-style-1">
											<label class="pay-title"> Payment </label>
										</div>
												<ul class="custom-payment-data">
													<li><a href="#"><img
															src="assets/db_img/mastercard.png" class="img-fluid">
													</a></li>
													<li><a href="#"><img
															src="assets/db_img/visacard.png" class="img-fluid"></a></li>
													<li><a href="#"><img src="assets/db_img/upi.png"
															class="img-fluid"></a></li>
													<li><a href="#"><img src="assets/db_img/rupay.png"
															class="img-fluid"></a></li>
													<li><a href="#"><img
															src="assets/db_img/netbank.png" class="img-fluid"></a></li>
												</ul>
											
									</div>
                                     </div>
								</div>
							</div>
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-12">
										<ul class="buttons-group mainbtn">
											<li><a id="btn-save"
												class="main-btn success-btn btn-hover btn-iconic-icon btnpay">
													<i class="lni lni-wallet"></i>Payment
											</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$("#fees_tb_div").hide();
		$("#fees_paid_msg").hide();
		$("#not_varified").hide();
		$("#fees_nd_msg").hide();
		$("#fees_nd_btn").hide();
		$("#btn-save").hide();
		$("#payment_type_div").hide();
		var role = '${role}';
		if (role.toLowerCase().includes("student")) {
			ifstudent();
		}
	});

	function ifstudent() {

		var stu_role = "";
		var stu_degree = "";
		var stu_prof = "";
		var stu_name = "";
		var stu_id = "";
		var no_of_part = "";

		if ('${role}' != "" && '${sturoledata}' != "") {

			stu_role = '${role}';

			if (stu_role.includes("PG")) {
				stu_role = "16";
			} else {
				stu_role = "15";
			}

			stu_degree = '${sturoledata[0].degree}';
			stu_prof = '${sturoledata[0].semester}';
			stu_name = '${sturoledata[0].name}';
			stu_id = '${sturoledata[0].id}';

			// 				if(stu_prof=="1"){
			// 					stu_prof="4";
			// 				}
			// 				if(stu_prof=="2"){
			// 					stu_prof="5";			
			// 				}
			// 				if(stu_prof=="3"){
			// 					stu_prof="6";
			// 				}

			$("#type_of_degree").val(stu_role);
			// 			$("#type_of_degree").trigger('change');

			$("select#type_of_degree").attr("disabled", true);
			$.ajaxSetup({
				async : false
			});
			getdegreefrom_type_of_degree();
			$("#degree_name").val(stu_degree);

			// 			$("#degree_name").trigger('change');

			$("select#degree_name").attr("disabled", true);

			$("#term_id").val(stu_prof);
			// 			$("#term_id").trigger('change');

			if (issupplymentry(stu_id) == 0) {
				$("select#term_id").attr("disabled", true);
			}
			$("#student_name").val(stu_name);
			// 			$("#student_name").trigger('change');
			$("input#student_name").attr("disabled", true);
			$("#studentId_hid").val(stu_id);

			// 			getfessdetails(stu_id);

			var fs = isfeesPaid($("#studentId_hid").val());
// 			alert(fs)
// 			alert("fee"+${sturoledata[0].fee_paid_status})

			if ('${sturoledata[0].verified_status}' == "-1") {
				
				$("#fees_tb_div").hide();
				$("#btn-save").hide();
				$("#payment_type_div").hide();
				$("#not_varified").show();
				
			}else if(isfeesDataAvailable($("#studentId_hid").val()) == "0"){
				
				$("#fees_nd_msg").show();
				$("#payment_type_div").hide();
				$("#btn-save").hide();
				$("#fees_nd_btn").hide();
				
			} else if (fs == '${sturoledata[0].no_of_part}' || ('${sturoledata[0].verified_status}' == "1" && '${sturoledata[0].fee_paid_status}' == "1")) {
				$("#fees_tb_div").hide();
				$("#btn-save").hide();
				$("#payment_type_div").hide();
				$("#fees_paid_msg").show();
			}
			else if(fs != '${sturoledata[0].no_of_part}'
					&& '${sturoledata[0].verified_status}' != "-1"
					&& '${sturoledata[0].no_of_part}' > 1) {
				$("#payment_type_div").show();
				$("#full_rb").click();
				$("#btn-save").show();
			} 
			else {
// 				$("#payment_type_div").hide();
				$("#payment_type_div").hide();
				$("#full_rb").click();
				getfessdetails($("#studentId_hid").val(), 'Full');
				
			}
		}

	}

	function issupplymentry(stu_id) {

		var count = 0;

		$.post('getSupplydata?' + key + "=" + value, {
			stu_id : stu_id
		}).done(function(j) {
			count = j;
		});

		return count;
	}

	function isfeesPaid(stu_id) {

		var count = 0;

		$.post('isfeespaid?' + key + "=" + value, {
			stu_id : stu_id,
			prof : $("#term_id").val()
		}).done(function(j) {
			count = j;
		});

		return count;
	}

	function isfeesDataAvailable(stu_id) {

		var count = 0;
		$.ajaxSetup({
			async : false
		});
		$.post('isfeesDataAvailable?' + key + "=" + value, {
			stu_id : stu_id,
			prof : $("#term_id").val(),
			degree : $("#degree_name").val()
		}).done(

		function(j) {

			count = j;
		});
		return count;
	}

	function AutoCompStudentName() {

		var susval = [];
		var myResponse1 = [];

		var susNoAuto = $("#student_name");
		
		var stu_id=($("#studentId_hid").val());

		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getStudentNameAuto?" + key + "=" + value,
					data : {
						autoString : $("#student_name").val(),
						degree_cat : $("#type_of_degree option:selected")
								.text()
					},
					success : function(data) {
						for (var i = 0; i < data.length; i++) {
							susval.push(data[i].name);
							myResponse1.push(data[i].id);
						}

						response(susval);
					}
				});
			},
			minLength : 1,
			autoFill : true,
			change : function(event, ui) {
				if (ui.item) {
					return true;
				} else {
					alert("STUDENT DETAILS NOT AVAILABLE");
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
					
				}
			},
			select : function(event, ui) {
// 				debugger;
				$(this).val(ui.item.value);
				document.getElementById("studentId_hid").value = myResponse1[$
						.inArray(ui.item.value, susval)];

				var fs = isfeesPaid($("#studentId_hid").val());

				/* ====================== */

				// 				alert($("#studentId_hid").val())
				// 				if(fs=="-1"){
				// 					$("#not_varified").show();
				// 				} else if(fs=="0"){
				// 					$("#fees_nd_msg").show();
				// 				} else if(fs=="1") {
				// 					$("#fees_paid_msg").show();
				// 				}
				/* 				=============================== */

// 				if (fs != '${Instno_of_part}') {
// 					// 					getfessdetails($("#studentId_hid").val(),'Full');
// 					$("#payment_type_div").show();
// 					$("#full_rb").click();
// 				}
// 				if (fs == '${Instno_of_part}') {
// 					$("#fees_paid_msg").show();
// 					$("#btn-save").hide();
// 				}
// alert("isfeesPaid"+isfeesPaid($("#studentId_hid").val()))
// alert("isfeesDataAvailable"+isfeesDataAvailable($("#studentId_hid").val()))
// alert("Instno_of_part"+isfeesPaid($("#studentId_hid").val()))
				if(isgetverifiedstatus($("#studentId_hid").val()) == "-1"){
					//document varification pending msg
					$("#not_varified").show();
					$("#fees_nd_msg").hide();
					$("#fees_paid_msg").hide();
					$("#btn-save").hide();
					$("#payment_type_div").hide();
					$("#fees_tb_div").hide();
					$("#fees_nd_btn").hide();
				}else if(isfeesDataAvailable($("#studentId_hid").val()) == "0"){
					$("#fees_nd_msg").show();
					$("#fees_paid_msg").hide();
					$("#not_varified").hide();
					$("#payment_type_div").hide();
					$("#btn-save").hide();
					$("#fees_tb_div").hide();
					$("#fees_nd_btn").show();
					//inst not defined msg
				}else if(isfeesPaid($("#studentId_hid").val()) == '${Instno_of_part}' || isgetverifiedstatus($("#studentId_hid").val()) == "1"){
					//fees already paid
					$("#not_varified").hide();
					$("#fees_nd_msg").hide();
					$("#fees_paid_msg").show();
					$("#fees_nd_btn").hide();
					$("#payment_type_div").hide();
					$("#fees_tb_div").hide();
					$("#btn-save").hide();
				} else {
					$("select#type_of_degree").attr("disabled", true);
					$("select#degree_name").attr("disabled", true);
					$("select#term_id").attr("disabled", true);
// 					$("input#student_name").attr("readonly", true);
					$("#fees_nd_msg").hide();
					$("#not_varified").hide();
					$("#fees_paid_msg").hide();
					$("#fees_nd_btn").hide();
					$("#full_rb").click();
					$("#btn-save").show();
					$("#payment_type_div").show();
					$("#fees_tb_div").show();
					getfessdetails($("#studentId_hid").val(), 'Full');
				}

			}
		});

	}
	
	function isgetverifiedstatus(stu_id) {
		var verified_status = "";
		$.ajaxSetup({
			async : false
		});
		$.post('getVerifiedStatus?' + key + "=" + value, {
			stu_id : stu_id
		}).done(

		function(j) {
			verified_status = j[0][1];
		});
		return verified_status;
	}

	function getfessdetails(studentid, ts) {
		var role = '${role}';
		if (ts == 'Full') {

			/* $.post('getFeesDetails?' + key + "=" + value, {
				studentid : studentid
			}).done(
					function(j) {
						
						var total=0;
						for(var i=0;i<j.length;i++){
							total = total+parseInt(j[i].feesvalue);
							$("#fees_tb_tbody").append('<tr>'
							+'<td><label id="fees_type"'+parseInt(i+1)+'>'+j[i].feestype+'</label></td>'
							+'<td><label id="fees_value"'+parseInt(i+1)+'>'+j[i].feesvalue+'</label></td>'
							+'<td><label id="no_of_part_value"'+parseInt(i+1)+'>'+Math.round(j[i].feesvalue/j[i].no_of_part)+' </label></td>'
							+'<td><label id="no_of_part_no"'+parseInt(i+1)+'>'+Math.floor(j[i].no_of_part)+' </label></td>'
							+'</tr>');
							if(i == (j.length-1)){
								$("#fees_tb_tbody").append('<tr>'
										+'<td><label id="fees_type"'+parseInt(i+1)+'>Total</label></td>'
										+'<td><label id="fees_value"'+parseInt(i+1)+'>'+total+'</label></td>'
										+'</tr>');
							}
						}
						$("#fees_tb_div").show();
						
					}); */
			$("#fees_tb_tbody").empty();
			$.post('getFeesDetails?' + key + "=" + value, {
				studentid : studentid
			}).done(
					function(j) {
// 						debugger;
						// 						alert(isfeesDataAvailable(studentid) )
// 						if (j.length == 0) {
// 							if (isfeesDataAvailable(studentid) == "0") {
// 								$("#fees_nd_msg").show();
// 								if (role.toLowerCase().includes("institute")) {
// 									$("#fees_nd_btn").show();
// 								}
// 								$("#payment_type_div").hide();
// 								$("#btn-save").hide();
// 							} else {
// 								$("#fees_paid_msg").show();
// 								$("#payment_type_div").hide();
// 								$("#btn-save").hide();
// 							}

// 						}
// 						else {
	if (j.length != 0) {
							var total = 0;
							var ch_tbIds = "";
							for (var i = 0; i < j.length; i++) {
								total = total + parseInt(j[i].amount);
								$("#fees_tb_tbody").append(
										'<tr>' + '<td><p id="part"'
												+ parseInt(i + 1) + '>'
												+ j[i].part_ser + '</p></td>'
												+ '<td><p id="amount"'
												+ parseInt(i + 1) + '>'
												+ j[i].amount + '</p></td>'
												+ '</tr>');
								if (i == (j.length - 1)) {
									$("#fees_tb_tbody").append(
											'<tr>' + '<td><p id="part"'
													+ parseInt(i + 1)
													+ '>Total</p></td>'
													+ '<td><p id="amount"'
													+ parseInt(i + 1) + '>'
													+ total + '</p></td>'
													+ '</tr>');
									ch_tbIds = ch_tbIds + j[i].id;
								} else {
									ch_tbIds = ch_tbIds + j[i].id + ",";
								}

							}
							$("#fees_tb_div").show();
							$("#ch_tb_ids").val(ch_tbIds);
// 						}
}
					});

		}
		if (ts == 'Part') {
			$("#fees_tb_tbody").empty();
			$
					.post('getFeesDetails?' + key + "=" + value, {
						studentid : studentid
					})
					.done(
							function(j) {
								var total = j[0].amount;
								$("#fees_tb_tbody")
										.append(
												'<tr>'
														+ '<td><p id="part"'+1+'>'
														+ j[0].part_ser
														+ '</p></td>'
														+ '<td><p id="amount"'+1+'>'
														+ j[0].amount
														+ '</p></td>'
														+ '</tr>'
														+ '<td><p id="part"'+1+'>Total</p></td>'
														+ '<td><p id="amount"'+1+'>'
														+ total + '</p></td>'
														+ '<tr>' + '</tr>');
								$("#ch_tb_ids").val(j[0].id);
								$("#fees_tb_div").show();

							});

		}

	}
	//csp

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btn-save').onclick = function() {
			ug_pg_coll_fees();
		};

		document.getElementById('student_name').onkeypress = function() {
			AutoCompStudentName();
		};

		document.getElementById('type_of_degree').onchange = function() {
			getdegreefrom_type_of_degree();
		};

		document.getElementById('full_rb').onchange = function() {
			checkTypePayment(this);
		};
		document.getElementById('part_rb').onchange = function() {
			checkTypePayment(this);
		};

		document.getElementById('term_id').onchange = function() {

			var role = '${role}';

			if (role.toLowerCase().includes("student")) {
				if (isfeesPaid($("#studentId_hid").val()) == 0) {
					ifstudent();
				}
			}

		};

	});

	function checkTypePayment(obj) {

		var selval = $("#" + obj.id).val();
		if (selval == "Full") {
			getfessdetails($("#studentId_hid").val(), selval);
			$("#isfullpartHid").val(1);
		}
		if (selval == "Part") {
			getfessdetails($("#studentId_hid").val(), selval);
			$("#isfullpartHid").val(2);
		}
	}

	//type of degree 

	function getdegreefrom_type_of_degree() {

		var type_of_degree = $("select#type_of_degree").val();

		$
				.post(
						"degreefrom_fromybyinstlist_ctrl?" + key + "=" + value,
						{
							type_of_degree : type_of_degree
						},
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_name").html(options);
						});
	}

	//ug_pg_coll_fees for update 

	function ug_pg_coll_fees() {

		var studentId_hid = $("#studentId_hid").val();
		var type_of_degree = $("#type_of_degree").val();
		var degree_name = $("#degree_name").val();
		var term_id = $("#term_id").val();
		var fullorpart = $("#isfullpartHid").val();
		var no_of_part = $("#no_of_part_hid").val();
		var ch_tb_ids = $("#ch_tb_ids").val();

		$.post('ug_pg_coll_fees?' + key + "=" + value, {

			studentId_hid : studentId_hid,
			type_of_degree : type_of_degree,
			degree_name : degree_name,
			term_id : term_id,
			fullorpart : fullorpart,
			no_of_part : no_of_part,
			ch_tb_ids : ch_tb_ids

		}).done(function(j) {
			alert(j);
			location.reload();
		});
	}
</Script>


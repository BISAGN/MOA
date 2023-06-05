<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<link rel="stylesheet" href="assets/vendor/internal_css.css">

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span>Academic Schedule
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Academic
									Schedule</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="course" id="search_exam_Action"
						action="search_exam_Action" method='POST'
						commandName="search_exam_CMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
							
					<div class="inst_block">
					<ul>
					<li><p class="inst_text">Please fill up Academic Details first from
									<b class="concat-string">MASTERS &gt; 
									<a class="text-heighlight" href="academic_detailsUrl">Academic</a></b></p></li>
							</ul>
					</div>
								<!-- <h6 class="mb-25">Academic Schedule</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="text-input">System<span class="mandatory">*</span></label>
											<input type="text" value="${system[0][0]}" id="system_idlab"
												name="system_idlab"
												class="form-control form-control-lg form-control-a effect-9"
												readonly="readonly">
										</div>
										<div class="select-style-1 custom-d-none">
											<div class="select-position">
												<select name="system_id" id="system_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSystemForAll}"
														varStatus="num">
														<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="degree" id="degree">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getDegreeList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.degree_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Professional<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="professional" id="professional">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getprofessionalList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.professional}</option>
													</c:forEach>
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
										<ul class="buttons-group mainbtn">
											<li><a href="Academic_Schedule_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
											<li><a id="btn-view"
												class="main-btn dark-btn btn-hover btn-iconic-icon btnview"
												type="button"><i class="lni lni-eye"></i>View</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>
		</div>
		<div id="pop">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="custom-multi-table">
							<div class="table-wrapper table-responsive custom-table custom-table-v2 custom-auto-width">
								<table class="table table-striped">
									<thead>
										<tr>
											<!-- class="learncount middle-center" -->
											<th colspan="3">
												<!-- <span><p class="bold"><br> </p></span> -->
												<h6>TRANSITIONAL CURRICULUM</h6>
											</th>
										</tr>

										<tr>
 											<th rowspan="1"><h6>TERM</h6></th>
											<th><h6>START DATE</h6></th>
											<th><h6>END DATE</h6></th>
										</tr>

									</thead>

									<tbody id="pop4">
										 <tr>
											<td> <p>FIRST</p></td>
											<td><p id="date_va" class="data_label"></p></td>
											<td><p id="date_vb" class="data_label"></p></td>
										</tr> 
<!-- 										<tr> -->
<!-- 											<td><p id="date_fsa" class="data_label"></p></td> -->
<!-- 											<td><p id="date_fsb" class="data_label"></p></td> -->
<!-- 										</tr> -->
										<!-- <tr id="fee_third">
											<td><p>THIRD</p></td>
											<td><p id="date_eta" class="data_label"></p></td>
											<td><p id="date_etb" class="data_label"></p></td>
										</tr> -->
									</tbody>

									<tbody id="pop4_not">
										<tr>
											<td colspan="3">
												<h6>DATA NOT AVAILABLE</h6>
											</td>
										</tr>
									</tbody>

								</table>
							</div>
						</div>
						<div class="custom-multi-table">
							<div class="table-wrapper table-responsive custom-table custom-table-v2">
								<!-- id="container-table" -->
								<table class="table table-striped">
									<thead>
										<tr>
											<!-- class="learncount middle-center" -->
											<th colspan="3">
												<h6>ACADEMIC TERM SCHEDULE</h6>
											</th>
											<!-- <span><label class="bold"></label></span>-->
										</tr>

										<tr>
											<th >
												<h6>Term</h6>
											</th>
											<th >
												<h6>Start Date</h6>
											</th>
											<th >
												<h6>End Date</h6>
											</th>
										</tr>
									</thead>
									<tbody id="pop1">
										<tr>
											<td>
												<p>FIRST</p>
											</td>
											<td>
												<p id="date_fa" class="data_label"></p>
											</td>
											<td>
												<p id="date_fb" class="data_label"></p>
											</td>
										</tr>
										<tr>
											<td>
												<p>SECOND</p>
											</td>
											<td>
												<p id="date_sa" class="data_label"></p>
											</td>
											<td>
												<p id="date_sb" class="data_label"></p>
											</td>
										<tr id="aca_third">
											<td>
												<p>THIRD</p>
											</td>
											<td>
												<p id="date_ta" class="data_label"></p>
											</td>
											<td>
												<p id="date_tb" class="data_label"></p>
											</td>
										</tr>
									</tbody>
									<tbody id="pop1_not">
										<tr>
											<td colspan="3">
												<h6>Data Not Available</h6> 
											</td>
										</tr>
									</tbody>

								</table>
							</div>
						</div>

						<div class="custom-multi-table">
							<div class="table-wrapper table-responsive custom-table custom-table-v2">
								<table class="table table-striped">
									<thead>
										<tr>
											<th colspan="5">
												<h6>EXAMINATION TERM SCHEDULE</h6>
											</th>
										</tr>
										<tr>
											<th><h6>TERM</h6></th>
											<th><h6>Exam Type</h6></th>
											<th><h6>Exam Serial</h6></th>
											<th><h6>Start Date</h6></th>
											<th><h6>End Date</h6></th>
										</tr>

									</thead>
									<tbody id="pop2">
										<tr>
											<!-- ml-5 bold data_label -->
											<td rowspan="4"><p>FIRST</p></td>
											<td rowspan="3"><p>PERIODIC ASSESSMENT PA</p></td>
											<td> <p>1</p></td>
											<td> <p id="date_fpa" class="data_label"> </p></td>
											<td> <p id="date_fpb" class="data_label"> </p></td>
										</tr>

										<tr>
											<td> <p>2</p></td>
											<td> <p id="date_fpc" class="data_label"> </p> </td>
											<td> <p id="date_fpd" class="data_label"> </p> </td>
										</tr>

										<tr>
											<td> <p>3</p></td>
											<td> <p id="date_fpe" class="data_label"> </p> </td>
											<td> <p id="date_fpf" class="data_label"> </p> </td>
										</tr>

										<tr>
											<td rowspan="1"><p >TERM TEST TT</p></td>
											<td > <p class="data_label"></p></td>
											<td > <p id="date_fta" class="data_label"></p></td>
											<td> <p id="date_ftb" class="data_label"></p></td>
										</tr>

										<tr>
											<td rowspan="4"><p>SECOND</p></td>
											<td rowspan="3"> <p>PERIODIC ASSESSMENT PA</p> </td>
											<td> <p>1</p></td>
											<td> <p id="date_spa" class="data_label"></p></td>
											<td> <p id="date_spb" class="data_label"></p></td>
										</tr>

										<tr>
											<td> <p>2</p></td>
											<td> <p id="date_spc" class="data_label"></p></td>
											<td> <p id="date_spd" class="data_label"></p></td>
										</tr>

										<tr>
											<td> <p>3</p></td>
											<td> <p id="date_spe" class="data_label"></p></td>
											<td> <p id="date_spf" class="data_label"></p></td>
										</tr>

										<tr>
											<td rowspan="1"> <p>TERM TEST TT</p> </td>
											<td> <p class="data_label"></p> </td>
											<td> <p id="date_sta" class="data_label"></p></td>
											<td> <p id="date_stb" class="data_label"></p></td>
										</tr>

										<tr id="exam_third">
											<td rowspan="4"> <p>THIRD</p> </td>
											<td rowspan="3"> <p>PERIODIC ASSESSMENT PA</p> </td>
											<td> <p>1</p> </td>
											<td> <p id="date_tpa" class="data_label"></p></td>
											<td> <p id="date_tpb" class="data_label"></p></td>
										</tr>

										<tr id="exam1_third">
											<td><p>2</p></td>
											<td><p id="date_tpc" class="data_label"></p></td>
											<td><p id="date_tpd" class="data_label"></p></td>
										</tr>

										<tr id="exam2_third">
											<td><p>3</p></td>
											<td><p id="date_tpe" class="data_label"></p></td>
											<td><p id="date_tpf" class="data_label"></p></td>
										</tr>

										<tr id="exam3_third">
											<td rowspan="1"><p> UNIVERSITY EXAM UE </p></td>
											<td><p class="data_label"></p></td>
											<td><p id="date_tua" class="data_label"></p></td>
											<td><p id="date_tub" class="data_label"></p></td>
										</tr>
									</tbody>

									<tbody id="pop2_not">
										<tr>
											<td colspan="5">
												<h6>Data Not Available</h6>
											</td>
										</tr>
									</tbody>

								</table>
							</div>
						</div>

						<div class="custom-multi-table">
							<div class="table-wrapper table-responsive custom-table custom-table-v2 custom-auto-width">
								<table class="table table-striped">
									<thead>
<!-- 									===========by Parth Rathod====================== -->
									<c:if test="${nonlecact1[0].no_of_part == 1}">
										<tr>
											<!-- class="learncount middle-center" -->
											<th colspan="2">
												<!-- <span><p class="bold"><br> </p></span> -->
												<h6>FEE SCHEDULE</h6>
											</th>
										</tr>
										<tr>
											<th><h6>START DATE</h6></th>
											<th><h6>END DATE</h6></th>
										</tr>
									</c:if>
									<c:if test="${nonlecact1[0].no_of_part > 1}">
										<tr>
											<!-- class="learncount middle-center" -->
											<th colspan="3">
												<!-- <span><p class="bold"><br> </p></span> -->
												<h6>FEE SCHEDULE</h6>
											</th>
										</tr>
										<tr>
											<th rowspan="1"><h6>TERM</h6></th>
											<th><h6>START DATE</h6></th>
											<th><h6>END DATE</h6></th>
										</tr>
										<tr>
									</c:if>
									</thead>
									<c:if test="${nonlecact1[0].no_of_part == 1}">
									<tbody id="pop3">
										<tr>
											<td><p id="date_fsa" class="data_label"></p></td>
											<td><p id="date_fsb" class="data_label"></p></td>
										</tr>
									</tbody>
									</c:if>
									<c:if test="${nonlecact1[0].no_of_part > 1}">
									<tbody id="pop3">
										 <tr>
											<td> <p>FIRST</p></td>
											<td><p id="date_ffa" class="data_label"></p></td>
											<td><p id="date_ffb" class="data_label"></p></td>
										</tr> 
										<tr>
											<td> <p>SECOND</p></td>
											<td><p id="date_fsa" class="data_label"></p></td>
											<td><p id="date_fsb" class="data_label"></p></td>
										</tr>
										<tr id="fee_third">
											<td><p>THIRD</p></td>
											<td><p id="date_eta" class="data_label"></p></td>
											<td><p id="date_etb" class="data_label"></p></td>
										</tr> 
									</tbody>
									</c:if>
<!-- 									================================= -->
									<tbody id="pop3_not">
										<tr>
											<td colspan="3">
												<h6>DATA NOT AVAILABLE</h6>
											</td>
										</tr>
									</tbody>

								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 						end card -->
		</div>
		<!-- 				end col -->


	<!-- 		<div class="row" id="pop_not"> -->
	<!-- 			<div class="col-12"> -->
	<!-- 				<div class="card-style mb-30"> -->
	<!-- 					<div class="table-wrapper table-responsive custom-table" -->
	<!-- 						id="container-table"> -->
	<!-- 						<table class="table" id="pop1_not"> -->
	<!-- 							<thead> -->
	<!-- 								<tr class="learncount middle-center"> -->
	<!-- 									<td colspan="9"><span><p class="bold"> -->
	<!-- 												<br> -->
	<!-- 												<h4>Data Not Available</h4> -->
	<!-- 										</p></span></td> -->
	<!-- 								</tr> -->
	<!-- 							</thead> -->
	<!-- 						</table> -->
	<!-- 					</div> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$("#pop").hide();
		$("#pop1").show();
		$("#pop2").show();
		$("#pop3").show();
		$("#pop4").show();
		$("#pop1_not").hide();
		$("#pop2_not").hide();
		$("#pop3_not").hide();
		$("#pop4_not").hide();
		$("#system_id").val('${system[0][1]}');
		$("#system_id").attr('readonly', 'true');
		getAprrovedDegree('${system[0][1]}');

		// 		$("#aca_third").show();
		// 		$("#fee_third").show();

	});

	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btn-view').onclick = function() {
			View_Validation();
			getpop();
		};
	});
	
	function View_Validation() {
		if ($("#system_id").val().trim() == "0") {
			alert("Please Select System.");
			$("select#system_id").focus();
			return false;
		}
		if ($("#degree").val().trim() == "0") {
			alert("Please Select Degree.");
			$("select#degree").focus();
			return false;
		}
		if ($("#professional").val().trim() == "0") {
			alert("Please Select Professional.");
			$("select#professional").focus();
			return false;
		}
		return true;
	}

	function getpop() {
// 		debugger;
		$(".data_label").each(function() {
			$(this).html("--");

		});

		var professional_id = $("#professional").val();
		if ($("#degree").val().trim() == "0") {
			$("select#degree").focus();
			return false;
		}
		
		if ($("#professional").val().trim() == "0") {
			$("select#professional").focus();
			return false;
		}
		
		$.post("GetviewDate_Academic_Prof?" + key + "=" + value, {
			professional_id : professional_id
		}, function(j) {
			if (j.length == 0) {
				$("#pop1").hide();
				$("#pop1_not").show();
			} else {
				$("#pop1").show();
				$("#pop1_not").hide();
			}

			for (var i = 0; i < j.length; i++) {
				if (j[i][2] == "I") {
					$("#date_fa").text(j[i][0]);
					$("#date_fb").text(j[i][1]);
				}
				if (j[i][2] == "II") {
					$("#date_sa").text(j[i][0]);
					$("#date_sb").text(j[i][1]);
				}
				if (j[i][2] == "III") {
					$("#date_ta").text(j[i][0]);
					$("#date_tb").text(j[i][1]);
				}
			}
		});

		$.post("GetviewDate_Examination_Prof?" + key + "=" + value, {
			professional_id : professional_id
		}, function(j) {
			if (j.length == 0) {
				$("#pop2").hide();
				$("#pop2_not").show();
			} else {
				$("#pop2").show();
				$("#pop2_not").hide();
			}

			for (var i = 0; i < j.length; i++) {
// 				debugger;
				if (j[i][2] == "I") {
					if (j[i][3] == "PERIODIC ASSESSMENT PA") {
						if (j[i][4] == "1") {
							$("#date_fpa").text(j[i][0]);
							$("#date_fpb").text(j[i][1]);
						}
						if (j[i][4] == "2") {
							$("#date_fpc").text(j[i][0]);
							$("#date_fpd").text(j[i][1]);
						}
						if (j[i][4] == "3") {
							$("#date_fpe").text(j[i][0]);
							$("#date_fpf").text(j[i][1]);
						}
					}
					if (j[i][3].trim() == "TERM TEST TT") {
						$("#date_fta").text(j[i][0]);
						$("#date_ftb").text(j[i][1]);
					}
				}

				if (j[i][2] == "II") {
					if (j[i][3] == "PERIODIC ASSESSMENT PA") {
						if (j[i][4] == "1") {
							$("#date_spa").text(j[i][0]);
							$("#date_spb").text(j[i][1]);
						}
						if (j[i][4] == "2") {
							$("#date_spc").text(j[i][0]);
							$("#date_spd").text(j[i][1]);
						}
						if (j[i][4] == "3") {
							$("#date_spe").text(j[i][0]);
							$("#date_spf").text(j[i][1]);
						}
					}
					if (j[i][3].trim() == "TERM TEST TT") {
						$("#date_sta").text(j[i][0]);
						$("#date_stb").text(j[i][1]);
					}
				}

				if (j[i][2] == "III") {
					if (j[i][3] == "PERIODIC ASSESSMENT PA") {
						if (j[i][4] == "1") {
							$("#date_tpa").text(j[i][0]);
							$("#date_tpb").text(j[i][1]);
						}
						if (j[i][4] == "2") {
							$("#date_tpc").text(j[i][0]);
							$("#date_tpd").text(j[i][1]);
						}
						if (j[i][4] == "3") {
							$("#date_tpe").text(j[i][0]);
							$("#date_tpf").text(j[i][1]);
						}
					}
					if (j[i][3] == "UNIVERSITY EXAM UE") {
						$("#date_tua").text(j[i][0]);
						$("#date_tub").text(j[i][1]);
					}
				}
			}
		});

		$.post("GetviewDate_Tran_Curr_Prof?" + key + "=" + value, {
			professional_id : professional_id
		}, function(j) {
			$("#date_va").text(j[0][0]);
			$("#date_vb").text(j[0][1]);
		});
		$.post("GetviewDate_Fee_Prof?" + key + "=" + value, {
			professional_id : professional_id
		}, function(j) {
			if (j.length == 0) {
				$("#pop3").hide();
				$("#pop3_not").show();
			} else {
				$("#pop3").show();
				$("#pop3_not").hide();
			}
//  									===========by Parth Rathod====================== 
			var nop ="${nonlecact1[0].no_of_part}";
			
			if(nop==1){
// 			for (var i = 0; i < j.length; i++) {
// 				if (j[i][2] == "I") {
// 					$("#date_ffa").text(j[i][0]);
// 					$("#date_ffb").text(j[i][1]);
// 				}
// 				if (j[i][2] == "II") {
					$("#date_fsa").text(j[0][0]);
					$("#date_fsb").text(j[0][1]);
// 				}
// 				if (j[i][2] == "III") {
// 					$("#date_eta").text(j[i][0]);
// 					$("#date_etb").text(j[i][1]);
// 				}
// 			}
			}
			if(nop>1){
// 				for (var i = 0; i < j.length; i++) {
				$("#date_ffa").text(j[0][0]);
				$("#date_ffb").text(j[0][1]);
				$("#date_fsa").text(j[1][0]);
				$("#date_fsb").text(j[1][1]);
				$("#date_eta").text(j[2][0]);
				$("#date_etb").text(j[2][1]);
// 				}
			}
//			================================= 

		});
		var system_id = $("#system_id").val();
		if (system_id == "45" && (professional_id == "16" || professional_id == "17")) {
			
			$("#aca_third").hide();
			$("#fee_third").hide();
			$("#exam_third").hide();
			$("#exam1_third").hide();
			$("#exam2_third").hide();
			$("#exam3_third").hide();
		} else {
			$("#aca_third").show();
			$("#fee_third").show();
			$("#exam_third").show();
			$("#exam1_third").show();
			$("#exam2_third").show();
			$("#exam3_third").show();
		}
		$("#pop").show();
	}

	function getAprrovedDegree(id) {

		// 		var selval = $("#system_hid").val();
		var selval = id;
		$
				.post(
						"getdegrebysysidlist?" + key + "=" + value,
						{
							selval : selval
						},
						function(j) {
							if (j.length > 0) {
								var options = '<option value="' + "0" + '">'
										+ "--Select--" + '</option>';
								for (var i = 0; i < j.length; i++) {
									options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
											+ j[i][1] + '</option>';
								}
								$("select#degree").html(options);
							}
						});
	}
</script>




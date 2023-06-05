<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">

	<section class="dashboard-page">
		<div class="container-fluid">
			<!-- title-wrapper start -->
			<div class="title-wrapper pt-30">
				<div class="row align-items-center">
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="title mb-30">
							<h2>College Departments</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="#0">Dashboard</a></li>
									<li class="breadcrumb-item active" aria-current="page">
										College Departments</li>
								</ol>
							</nav>
						</div>
					</div>
					<!-- end col -->
				</div>
				<!-- end row -->
			</div>
			<!-- title-wrapper end -->
			<div class="form-elements-wrapper custom_v_tab">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-style mb-30">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="inst_block simple-instruction">
										<strong>Instruction :</strong> If any of the value is not
										available or not applicable then please put it as 0
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>Institution Name :</label> <span class="value-bind">${roleloginName }</span>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>College Code :</label> <span class="value-bind">${username}</span>
									</div>

								</div>

							</div>
						</div>
					</div>
					<!-- ===========================
							College Departments Start
						=========================== -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="tabs content_h750">
								<!-- Computer & Printer Availability Start -->
								<div class="tab">
									<button class="tab-toggle" id="defaultOpen">Computer &
										Printer Availability</button>
								</div>
								<div class="content">
									<h4 class="heading">Computer & Printer Availability</h4>
									<form:form name="clg_reg_comp_printer" id="clg_reg_comp_printer" action="college_department_Action" method="post" class=""
										commandName="clg_reg_comp_printer_CMD">
										<div class="row">
										
											<input type="hidden" id="basic_info_id" name="basic_info_id" value="0">
										
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="comp_print_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>Department</h6></th>
																<th><h6>
																		Computer<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Printer<strong class="mandatory">*</strong>
																	</h6></th>
															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="">
														<c:forEach var="j" items="${getDepartmentList}" 
 																								varStatus="num"> 
 																								

														<tr>
																<td class="sr-no" >
																	<p></p>
																</td>
																<td>
																	<p>${j[1]}</p>
																	
																	<input type="hidden" id="inst_comp_printr_hidden_id${num.index+1}"
																		name="inst_comp_printr_hidden_id${num.index+1}" value="${j[1]}"
																		class="form-control ">
																	
																	
																		<input type="hidden" id="department${num.index+1}"
																		name="department${num.index+1}" value="${j[1]}"
																		class="form-control ">
																	
																</td>
															<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="computer${num.index+1}"
																				id="computer${num.index+1}" class="form-control">
																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Available</option>
																				<option value="2">Not Available</option>
																			</select>
																		</div>
																	</div>
																	<input type="hidden" id="inst_comp_printr_hidden${num.index+1}"
																		name="inst_comp_printr_hidden${num.index+1}" value="0"
																		class="form-control ">
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="printer${num.index+1}"
																				id="printer${num.index+1}" class="form-control">
																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Available</option>
																				<option value="2">Not Available</option>
																			</select>
																		</div>
																	</div>
																</td>
															</tr>
															<!-- end table row -->
															</c:forEach>
														</tbody>
													</table>
												</div>
												<!-- end row -->
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input
															class="main-btn info-btn btn-hover btnsave" type="button" id="comp_prit_save"
															value="Save"></li>
															<li><a 
																class="main-btn dark-btn-outline btn-hover btn-iconic-icon viewData d-none"><i
																	class="lni lni-eye"></i>View</a>
																	<input type='hidden' id="viewId${parent_id}" value="${institude}"></li>
																	
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Computer & Printer Availability End -->
								<!-- UG Start -->
								<div class="tab">
									<button class="tab-toggle">UG</button>
								</div>
								<div class="content">
									<h4 class="heading">UG</h4>
										<form:form name="clg_reg_ug" id="clg_reg_ug" action="" method="post" class=""
										commandName="college_depart_ug_CMD">
										
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="ug_depart_table">
														<thead>
															<tr>
																<th rowspan="2"><h6>
																		Sr No<strong class="mandatory">*</strong>
																	</h6></th>
																<th rowspan="2"><h6>
																		Department<strong class="mandatory">*</strong>
																	</h6></th>
																<th rowspan="2"><h6>
																		Computer/Printer Availability<strong class="mandatory">*</strong>
																	</h6></th>															
																<th rowspan="2"><h6>
																		Dept.Library Books<strong class="mandatory">*</strong>
																	</h6></th>
																<th rowspan="2"><h6>
																		Teaching/Training Material<strong class="mandatory">*</strong>
																	</h6></th>
																<th colspan="3"><h6>
																		Museum<strong class="mandatory">*</strong>
																	</h6></th>
																<th colspan="4"><h6>
																		Lectures / Practicals / Clinicals carried out online
																		and offline in last academic year<strong
																			class="mandatory">*</strong>
																	</h6></th>
															</tr>
															<tr>
																<th><h6>
																		Charts<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Models<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Specimens<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Theory<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Practical / Clinical<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Tutorial<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Seminar<strong class="mandatory">*</strong>
																	</h6></th>
															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="">
														
														<c:forEach var="j" items="${getDepartmentListofug}"
																								varStatus="num">
															<tr>
																<td class="sr-no">
																	<p></p>
																</td>
																<td>
																	<p>${j[1]}</p>
																</td>
																<td>																	
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="anatomy_computer_printer_${j[0]}"
																				id="anatomy_computer_printer_${j[0]}" class="form-control">
																				<option value="0" selected="selected">--
																					Select --</option>
																				<option value="1">Available</option>
																				<option value="2">Not Available</option>
																			</select>
																		</div>
																	</div>
																	<input type="hidden" id="hid_ug_${j[0]}" name="hid_ug_${j[0]}" value="0">
																	<input type="hidden" id="department_${j[0]}" name="department_${j[0]}" value="${j[1]}">
																<input type="hidden" id="department_id_${j[0]}" name="department_id_${j[0]}" value="${j[0]}">
																</td>															
																<td>
																	<div class="input-style-1">
																		<input type="text" name="anatomy_dept_library_${j[0]}"
																			id="anatomy_dept_library_${j[0]}" class="form-control"
																			placeholder="Total Dept.Library Books" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="anatomy_material_${j[0]}"
																			id="anatomy_material_${j[0]}" class="form-control"
																			placeholder="Total Teaching/Training material" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="anatomy_charts_${j[0]}"
																			id="anatomy_charts_${j[0]}" class="form-control"
																			placeholder="Total Charts" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="anatomy_models_${j[0]}"
																			id="anatomy_models_${j[0]}" class="form-control"
																			placeholder="Total Models" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="anatomy_specimens_${j[0]}"
																			id="anatomy_specimens_${j[0]}" class="form-control"
																			placeholder="Total Specimens" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="anatomy_theory_${j[0]}"
																			id="anatomy_theory_${j[0]}" class="form-control"
																			placeholder="Total Theory" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="anatomy_practical_${j[0]}"
																			id="anatomy_practical_${j[0]}" class="form-control"
																			placeholder="Total Practical / Clinical" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="anatomy_tutorial_${j[0]}"
																			id="anatomy_tutorial_${j[0]}" class="form-control"
																			placeholder="Total Tutorial" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="anatomy_seminar_${j[0]}"
																			id="anatomy_seminar_${j[0]}" class="form-control"
																			placeholder="Total Seminar" maxlength="10">
																	</div>
																</td>
															</tr>
															</c:forEach>
															<!-- end table row -->
														</tbody>
													</table>
												</div>
												<!-- end card -->
											</div>
											<!-- end col -->
										</div>
										<hr>
										<div class="row">
										<input type="hidden" id="hid_dept_ug_details_t" name="hid_dept_ug_details_t" value="0">
											<div class="col-lg-6 col-md-6 col-sm-12">
												<div class="input-style-1">
													<label for="fname">Number of educational tours
														conducted for teaching & practical purpose in last
														academic year<strong class="mandatory">*</strong>
													</label> <input type="text" name="tours" id="tours"
														class="form-control" placeholder="Total Tours" maxlength="10">
												</div>
											</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover btnsave" id="ug-save"
															type="button" value="Save"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- UG End-->
								<!-- PG Start -->
								<div class="tab">
									<button class="tab-toggle">PG</button>
								</div>
								<div class="content">
									<h4 class="heading">PG</h4>
									<form:form name="clg_reg_pg" id="clg_reg_pg" action="" method="post" class=""
										commandName="college_depart_pg_CMD">
										
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-12">
														<div class="table-wrapper table-responsive custom-table">
															<table class="table" id="pg_depart_table">
																<thead>
																	<tr>
																		<th rowspan="2"><h6>
																				Sr No<strong class="mandatory">*</strong>
																			</h6></th>
																		<th rowspan="2"><h6>
																				Subject<strong class="mandatory">*</strong>
																			</h6></th>

																		<th colspan="5"><h6>
																				Lectures / Practicals / Clinicals carried out
																				online and offline in last academic year<strong
																					class="mandatory">*</strong>
																			</h6></th>
																	</tr>
																	<tr>
																		<th><h6>
																				Theory<strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Practicals / Clinicals<strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Seminars<strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Case Presentation<strong class="mandatory">*</strong>
																			</h6></th>
																		<th><h6>
																				Journal Meeting<strong class="mandatory">*</strong>
																			</h6></th>
																	</tr>
																	<!-- end table row-->
																</thead>
																<tbody id="">
																

														<c:forEach var="j" items="${getDepartmentListofpg}"
															varStatus="num">


															<tr>
																<td class="sr-no">
																	<p></p>
																</td>
																<td>
																	<p>${j[1]}</p>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="philosophy_theory_${j[0]}"
																			id="philosophy_theory_${j[0]}" class="form-control"
																			placeholder="Total Theory" maxlength="10">
																	</div>
																	
																		<input type="hidden" id="hid_pg_${j[0]}" name="hid_pg_${j[0]}" value="0">
																	<input type="hidden" id="department_${j[0]}" name="department_${j[0]}" value="${j[1]}">
																	<input type="hidden" id="department_id_${j[0]}" name="department_id_${j[0]}" value="${j[0]}">
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="philosophy_practical_${j[0]}"
																			id="philosophy_practical_${j[0]}" class="form-control"
																			placeholder="Total Practical / Clinical" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="philosophy_seminars_${j[0]}"
																			id="philosophy_seminars_${j[0]}" class="form-control"
																			placeholder="Total Seminars" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="philosophy_presentation_${j[0]}"
																			id="philosophy_presentation_${j[0]}" class="form-control"
																			placeholder="Total Case Presentation" maxlength="10">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="philosophy_meeting_${j[0]}"
																			id="philosophy_meeting_${j[0]}" class="form-control"
																			placeholder="Total Journal Meeting" maxlength="10">
																	</div>
																</td>
															</tr>
															</c:forEach>

															<!-- end table row -->
													</tbody>
															</table>
														</div>
														<!-- end card -->
													</div>
													<!-- end col -->
												</div>
												<!-- end row -->
											
										
										<hr>
										<div class="row">
										<input type="hidden" id="hid_dept_pg_details_t" name="hid_dept_pg_details_t" value="0">
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="input-style-1">
													<label for="fname">Educational tours
														conducted for teaching &amp; practical purpose in last
														academic year<strong class="mandatory">*</strong>
													</label> <input type="text" name="tours_pg" id="tours_pg"
														class="form-control" placeholder="Total Tours" maxlength="10">
												</div>
											</div>
											<div class="col-lg-4 col-md-6 col-sm-12 col-12">
											<div class="input-style-1">
												<label>Educational tours conducted in last academic year<strong class="mandatory">*</strong></label>
												 <input type="file" id="edu_tour" name="edu_tour" class="form-control" accept="pdf">
												 
												<input type="hidden" id="hid_edu_tour" name="hid_edu_tour"
													class="form-control" value="">
												<div class="note-text">
													<span class="errorClass" id="edu_tour_lbl">${doc_path_doc1_msg}</span>
													<span class='tikClass' id="edu_tour_lbltik"></span>
												</div>
											</div>
										</div>
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input class="main-btn info-btn btn-hover btnsave"  id="pg-save"
															type="button" value="Save" ></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- PG End -->
								<!-- Academic Performance Start -->
								<div class="tab">
									<button class="tab-toggle">Academic Performance</button>
								</div>
								<div class="content">
									<h4 class="heading">Academic Performance</h4>
									<form:form name="clg_reg_ap" id="clg_reg_ap" action="" method="post" class=""
										commandName="college_depart_ap_CMD">		
										
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<div class="table-wrapper table-responsive custom-table">
														<table class="table" id="acade_per_table">
															<thead>
																<tr>
																<th><h6>
																			Sr No<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Department<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Advance Teaching Plan<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Teaching Diary<strong class="mandatory">*</strong>
																		</h6></th>
																	<th><h6>
																			Journal / Practical Notebook<strong class="mandatory">*</strong>
																		</h6></th>
																</tr>
																<!-- end table row-->
															</thead>
															<tbody id="">
															
																<c:forEach var="j" items="${getDepartmentListofap}"
															varStatus="num">
															
																<tr>
																<td class="sr-no">
																	<p></p>
																</td>
																	<td>
																			<p>${j[1]}</p>
																	</td>
																	<td>
																		<div class="select-style-1">
																			<div class="select-position">
																				<select name="anatomy_teaching_plan_${j[0]}"
																					id="anatomy_teaching_plan_${j[0]}" class="form-control">
																					<option value="0" selected="selected">--
																						Select --</option>
																					<option value="1">Available</option>
																					<option value="2">Not Available</option>
																				</select>
																			</div>
																		</div>
																		<input type="hidden" id="hid_ap_${j[0]}" name="hid_ap_${j[0]}" value="0">
																	<input type="hidden" id="department_${j[0]}" name="department_${j[0]}" value="${j[1]}">
																	<input type="hidden" id="department_id_${j[0]}" name="department_id_${j[0]}" value="${j[0]}">
																	</td>
																	<td>
																		<div class="select-style-1">
																			<div class="select-position">
																				<select name="anatomy__diary_${j[0]}" id="anatomy__diary_${j[0]}"
																					class="form-control">
																					<option value="0" selected="selected">--
																						Select --</option>
																					<option value="1">Available</option>
																					<option value="2">Not Available</option>

																				</select>
																			</div>
																		</div>
																	</td>
																	<td>
																		<div class="select-style-1">
																			<div class="select-position">
																				<select name="anatomy_journal_${j[0]}" id="anatomy_journal_${j[0]}"
																					class="form-control">
																					<option value="0" selected="selected">--
																						Select --</option>
																					<option value="1">Available</option>
																					<option value="2">Not Available</option>

																				</select>
																			</div>
																		</div>
																	</td>
																</tr>
																
																</c:forEach>
																
																<!-- end table row -->
															</tbody>
														</table>
													</div>
													<!-- end card -->
												</div>
												<!-- end col -->
											</div>
											<!-- end row -->
											<div class="footer_btn">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-12">
														<ul class="buttons-group mainbtn">
															<li><input class="main-btn success-btn  btn-hover btnsave" id="ap-save"
																type="button" value="Submit"></li>
														</ul>
													</div>
												</div>
											</div>
										
									</form:form>
								</div>
								<!-- Academic Performance End -->
							</div>
						</div>
					</div>
					<!-- ===========================
							College Departments End
						=========================== -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</section>
	<!-- regulation components end -->
<c:url value="View_Search_College_DepartmentUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="college_department_view_id">
<!-- 	<input type="hidden" name="id6" id="id6" value="0" /> -->
	<input type="hidden" name="college_department_id" id="college_department_view_id" value="0" />
	<input type="hidden" name="statusview" id="statusview" value="0" />
</form:form>
<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>

<script type="text/javascript" nonce="${cspNonce}">

	$(document).ready(function() {
		
		$.ajaxSetup({
			async : false
		});
		if ('${role}'=='NCH') {
			$(".viewData").addClass("d-none")
		}
		if ('${role}'=='Institute_NCH') {
			$(".viewData").removeClass("d-none")
		} 
		
		$("#basic_info_id").val('${basic_info_id}');

		getUg_Details();
		getPg_Details();
		getTours_Details();
		getAp_Details();
		getComp_PrinterDetails();
		
	});
	
	function saveDraft_Institution_clg_com_printer_detais() {
		
		var basic_info_id = $("#basic_info_id").val();
		if(basic_info_id == "0"){
			alert("Please Save Basic details First");
			return false;
		}
		
		<c:forEach var="j" items="${getDepartmentList}" varStatus="num">
		
		
		if ($("#computer"+${j[0]}).val() == "0") {
			alert("Please Select Computer Availability Of Row No. "+${j[0]});
			$("#computer"+${j[0]}).focus();
			return false;
		}
		
		if ($("#printer"+${j[0]}).val() == "0") {
			alert("Please Select Printer Availability Of Row No."+${j[0]});
			$("#printer"+${j[0]}).focus();
			return false;
		}
		
		</c:forEach>
		
		$.ajaxSetup({
		    async: false
		});	
		
		
		var form_data = new FormData(document.getElementById("clg_reg_comp_printer"));
		
	 		
			$.ajax({
		        url: 'college_department_Action?' + key + "=" + value,
		        type: "POST",
		        data: form_data,
		        enctype: 'multipart/form-data',
		        processData: false,
		        contentType: false
		      }).done(function(data) {
		    	  
		    	 alert(data.msg);
// 		    	  window.location.reload();
	       	
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("Something Went Wrong")
		         
		      }).fail(function(jqXHR, textStatus) {

		      });
			 
	 }
	
	function saveDraft_college_dept_ug_detais() {
		
		$.ajaxSetup({
		    async: false
		});	
		
			var form = $("#clg_reg_ug").serialize();
			console.log(form);
			$.post(
					'college_depart_ug_Action?' + key + "="
							+ value, form, function(j) {
						if(j>0){
			        		$("#hid_dept_ug_details_t").val(j);
			        	  alert("Data Save Sucessfully");
// 			        	  location.reload();
			          }
			          else{
			        	  alert(j);
			          }
				}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
				});
			 
	 }
	
	function getComp_PrinterDetails() {
		debugger;
		$.ajaxSetup({
			async : false
		});
		$.post("getcomp_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			var index=0;
			for (var i = 0; i < j.length; i++) {
				index=i+1;
				$("#inst_comp_printr_hidden"+index).val(j[i].id);
				$("#department"+index).val(j[i].department);
				$("#computer"+index).val(j[i].computer);
				$("#printer"+index).val(j[i].printer);
				
			}
			
		});
	}
	
	function getUg_Details() {
		debugger;
		$.ajaxSetup({
			async : false
		});
		$.post("getUg_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				$("#hid_ug_"+j[i].department_id).val(j[i].id);
				$("#anatomy_computer_printer_"+j[i].department_id).val(j[i].computer_printer_avail);
				$("#anatomy_dept_library_"+j[i].department_id).val(j[i].dept_library_books);
				$("#anatomy_material_"+j[i].department_id).val(j[i].teacher_training_material);
				$("#anatomy_charts_"+j[i].department_id).val(j[i].museum_charts);
				$("#anatomy_models_"+j[i].department_id).val(j[i].museum_models);
				$("#anatomy_specimens_"+j[i].department_id).val(j[i].museum_specimens);
				$("#anatomy_theory_"+j[i].department_id).val(j[i].theory);
				$("#anatomy_practical_"+j[i].department_id).val(j[i].practical);
				$("#anatomy_tutorial_"+j[i].department_id).val(j[i].tutorial);
				$("#anatomy_seminar_"+j[i].department_id).val(j[i].seminar);
			
			}
			
		});
	}
	
	function getTours_Details() {
		debugger;
		$.ajaxSetup({
			async : false
		});
		$.post("getTours_Details?" + key + "=" + value, {
			
		}, function(j) {
			
				$("#hid_dept_ug_details_t").val(j[0].id);
				$("#hid_dept_pg_details_t").val(j[0].id);
				$("#tours").val(j[0].edu_tours_teach_pract_purpose_ug);
				$("#tours_pg").val(j[0].edu_tours_teaching_pract_purpose_pg);
				$("#hid_edu_tour").val(j[0].edu_tours_pg);
			
		});
	}
	
	
	function getPg_Details() {
		debugger;
		$.ajaxSetup({
			async : false
		});
		$.post("getPg_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				$("#hid_pg_"+j[i].department_id).val(j[i].id);
				$("#philosophy_theory_"+j[i].department_id).val(j[i].theory);
				$("#philosophy_practical_"+j[i].department_id).val(j[i].practical);
				$("#philosophy_seminars_"+j[i].department_id).val(j[i].seminar);
				$("#philosophy_presentation_"+j[i].department_id).val(j[i].case_presentation);
				$("#philosophy_meeting_"+j[i].department_id).val(j[i].journal_meeting);
				$("#hid_edu_tour"+j[i].department_id).val(j[i].edu_tour);
				
			}
		});
	}
	
	function getAp_Details() {
		$.ajaxSetup({
			async : false
		});
		$.post("getAp_Details?" + key + "=" + value, {
			
		}, function(j) {
			
			for (var i = 0; i < j.length; i++) {
				
				$("#hid_ap_"+j[i].department_id).val(j[i].id);
				$("#anatomy_teaching_plan_"+j[i].department_id).val(j[i].advance_teaching_plan);
				$("#anatomy__diary_"+j[i].department_id).val(j[i].teaching_diary);
				$("#anatomy_journal_"+j[i].department_id).val(j[i].journal_practical);
			
			}
		});
	}
	
	function saveDraft_college_dept_pg_detais() {
		
		$.ajaxSetup({
		    async: false
		});	
		
	
		if($("#edu_tour").val()==''){
			if($("#hid_edu_tour").val()==''){
				alert("Please Upload The File In PDF Format");
					$("#edu_tour").focus();
					return false;
			}
		}
			
			var form_data = new FormData(document.getElementById("clg_reg_pg"));
			$.ajax({
				url : 'college_depart_pg_Action?_csrf=' + value,
				type : "POST",
				data : form_data,
				enctype : 'multipart/form-data',
				processData : false,
				contentType : false
			}).done(function(j){
	          if(j>0){
	        	  alert("Data Save Sucessfully");
	        	  location.reload();
	          }
	          else{
	        	  alert(j);
	          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});
			 
	 }
	
	
	function saveDraft_college_dept_ap_detais() {
		
		$.ajaxSetup({
		    async: false
		});	
		
			
			var form = $("#clg_reg_ap").serialize();
			console.log(form);
			$.post(
					'college_depart_aca_per_Action?' + key + "="
							+ value, form, function(j) {
						if(j>0){
			        	  alert("Data Save Sucessfully");
			        	  location.reload();
			          }
			          else{
			        	  alert(j);
			          }
				}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
				});
			
	 }

	
	function ValidationofUg() {
		
		var basic_info_id = $("#basic_info_id").val();
		if(basic_info_id == "0"){
			alert("Please Save Basic details First");
			return false;
		}
		
		<c:forEach var="j" items="${getDepartmentListofug}" varStatus="num">
		
		if ($("#anatomy_computer_printer_"+${j[0]}).val() == "0") {
			alert("Please Select Computer And Printer Availability");
			$("#anatomy_computer_printer_"+${j[0]}).focus();
			return false;
		}
		
		var res = CheckNullorBlank('anatomy_dept_library_'+${j[0]});
		if (res !== "true") {
			alert(res +"total Dept.Library Book"+${j[0]});
			$('#anatomy_dept_library_'+${j[0]}).focus();
			return false;
		}
		
		var res = CheckNullorBlank('anatomy_material_'+${j[0]});
		if (res !== "true") {
			alert(res +"Total Teaching/Training material"+${j[0]} );
			$('#anatomy_material_'+${j[0]}).focus();
			return false;
		}
		
		var res = CheckNullorBlank('anatomy_charts_'+${j[0]});
		if (res !== "true") {
			alert(res +"Total Charts"+${j[0]});
			$('#anatomy_charts_'+${j[0]}).focus();
			return false;
		}
		
		var res = CheckNullorBlank('anatomy_models_'+${j[0]});
		if (res !== "true") {
			alert(res +"Total Models"+${j[0]});
			$('#anatomy_models_'+${j[0]}).focus();
			return false;
		}
		
		var res = CheckNullorBlank('anatomy_specimens_'+${j[0]});
		if (res !== "true") {
			alert(res +"Total Specimens"+${j[0]});
			$('#anatomy_specimens_'+${j[0]}).focus();
			return false;
		}
		
		var res = CheckNullorBlank('anatomy_theory_'+${j[0]});
		if (res !== "true") {
			alert(res +"Total Theory"+${j[0]});
			$('#anatomy_theory_'+${j[0]}).focus();
			return false;
		}
		
		var res = CheckNullorBlank('anatomy_practical_'+${j[0]});
		if (res !== "true") {
			alert(res +"Total Practical / Clinical"+${j[0]});
			$('#anatomy_practical_'+${j[0]}).focus();
			return false;
		}

		var res = CheckNullorBlank('anatomy_tutorial_'+${j[0]});
		if (res !== "true") {
			alert(res +"Total Tutorial"+${j[0]});
			$('#anatomy_tutorial_'+${j[0]}).focus();
			return false;
		}
		
		var res = CheckNullorBlank('anatomy_seminar_'+${j[0]});
		if (res !== "true") {
			alert(res +"Total Seminar"+${j[0]});
			$('#anatomy_seminar_'+${j[0]}).focus();
			return false;
		}
		</c:forEach>
		
		return saveDraft_college_dept_ug_detais();	
	}
	
	function ValidationofPg() {
		
		var basic_info_id = $("#basic_info_id").val();
		if(basic_info_id == "0"){
			alert("Please Save Basic details First");
			return false;
		}
		
		
		<c:forEach var="j" items="${getDepartmentListofpg}" varStatus="num">
		
		var res = CheckNullorBlank('philosophy_theory_'+${j[0]});
		if (res !== "true") {
			alert(res +"Total Theory ");
			$('#philosophy_theory_'+${j[0]}).focus();
			return false;
		}
		
		var res = CheckNullorBlank('philosophy_practical_'+${j[0]});
		if (res !== "true") {
			alert(res +"Total Practical / Clinical ");
			$('#philosophy_practical_'+${j[0]}).focus();
			return false;
		}
		 
		var res = CheckNullorBlank('philosophy_seminars_'+${j[0]});
		if (res !== "true") {
			alert(res +"Total Seminars ");
			$('#philosophy_seminars_'+${j[0]}).focus();
			return false;
		}
		
		var res = CheckNullorBlank('philosophy_presentation_'+${j[0]});
		if (res !== "true") {
			alert(res +"Total Case Presentation ");
			$('#philosophy_presentation_'+${j[0]}).focus();
			return false;
		}
		
		var res = CheckNullorBlank('philosophy_meeting_'+${j[0]});
		if (res !== "true") {
			alert(res +"Total Journal Meeting ");
			$('#philosophy_meeting_'+${j[0]}).focus();
			return false;
		}
		</c:forEach>
		
		return saveDraft_college_dept_pg_detais();	
	}
	
	
	function ValidationofAcademicPerformance() {
		
		var basic_info_id = $("#basic_info_id").val();
		if(basic_info_id == "0"){
			alert("Please Save Basic details First");
			return false;
		}
		
		<c:forEach var="j" items="${getDepartmentListofap}" varStatus="num">
		
		if ($("#anatomy_teaching_plan_"+${j[0]}).val() == "0") {
			alert("Please Select Advance Teaching Plan ");
			$("#anatomy_teaching_plan_"+${j[0]}).focus();
			return false;
		}
		if ($("#anatomy__diary_"+${j[0]}).val() == "0") {
			alert("Please Select Teaching Diary ");
			$("#anatomy__diary_"+${j[0]}).focus();
			return false;
		}
		if ($("#anatomy_journal_"+${j[0]}).val() == "0") {
			alert("Please Select Journal / Practical Notebook ");
			$("#anatomy_journal_"+${j[0]}).focus();
			return false;
		}
		
		</c:forEach>
		

	return saveDraft_college_dept_ap_detais();	
		
	}
	
	function ValidationofTour() {
	
	if ($("#tours_pg").val().trim() == "") {
		alert("Please Enter Educational tours conducted for teaching & practical purpose in last academic year" );
		$("#tours_pg").focus();
		return false;
	}
	
	if ($("#tours").val().trim() == "") {
		alert("Please Enter Number of educational tours conducted for teaching & practical purpose in last academic year" );
		$("#tours").focus();
		return false;
	}
	}
	
	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('comp_prit_save').onclick = function() {
			 return	saveDraft_Institution_clg_com_printer_detais();
		};
		
		document.getElementById('ug-save').onclick = function() {
			return ValidationofUg();
// 			return saveDraft_college_dept_ug_detais();	
		};
		
		document.getElementById('pg-save').onclick = function() {
			return ValidationofPg();
// 			return saveDraft_college_dept_pg_detais();	
		};
		
		document.getElementById('ap-save').onclick = function() {
			return ValidationofAcademicPerformance();
// 			return saveDraft_college_dept_ap_detais();	
		};
		
		document.getElementById('edu_tour').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"edu_tour","200kb","edu_tour_lbltik","edu_tour_lbl",this.accept)
		};
		
		document.getElementById('tours_pg').onclick = function() {
			return ValidationofTour();
		};
		document.getElementById('tours').onclick = function() {
			return ValidationofTour();
		};
		
		
	<c:forEach var="j" items="${getDepartmentListofug}" varStatus="num">
	
		document.getElementById('anatomy_dept_library_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('anatomy_material_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('anatomy_charts_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('anatomy_models_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('anatomy_specimens_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('anatomy_theory_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('anatomy_practical_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('anatomy_tutorial_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('anatomy_seminar_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		</c:forEach>
		
		
	<c:forEach var="j" items="${getDepartmentListofpg}" varStatus="num">
	
		document.getElementById('philosophy_theory_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
			
		document.getElementById('philosophy_practical_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		
		document.getElementById('philosophy_seminars_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
	
		document.getElementById('philosophy_presentation_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		document.getElementById('philosophy_meeting_'+${j[0]}).onkeypress = function() {
			return isNumberKeydecimal(this, event);
		};
		</c:forEach>
		
	});
	
	document.querySelectorAll('.viewData').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('viewId${parent_id}').value;
			if (confirm('Are You Sure You Want to View Detail ?')) {
				ViewData(hid);
			} else {
				return false;
			}
		})
	});

function ViewData(id){
	
	$("#college_department_view_id").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
}

	
</script>



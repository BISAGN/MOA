<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="assets/vendor/vtab_with_accordion/vtab_with_acco_form_style.css">
<body>
	<section class="dashboard-page">
		<div class="container-fluid">
			<!-- title-wrapper start -->
			<div class="title-wrapper pt-30">
				<div class="row align-items-center">
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="title mb-30">
							<h2>Staff Details</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>

									<li class="breadcrumb-item active" aria-current="page">
										Staff Details</li>
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
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>Institution Name :</label> <span class="value-bind">${roleloginName }</span>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="custom-data-value custom-data-value-form">
										<label>Institution Name :</label> <span class="value-bind">${username}</span>
									</div>

								</div>
							</div>
						</div>
					</div>
					<!--    ===========================
		 Staff List Start
===========================     -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card-style mb-30">
							<div class="tabs content_h700">

								<!-- 	Add Medical Staff Start -->
								<div class="tab">
									<button class="tab-toggle" id="defaultOpen">Medical
										Staff</button>
								</div>
								<div class="content">
									<h4 class="heading">Medical Staff</h4>
									<form:form name="medical_staff" id="medical_staff" action=""
										method="post" class="" commandName="">
										<div class="row">
											<input type="hidden" id="hid_medical_staff1"
												name="hid_medical_staff1" value="0"> <input
												type="hidden" id="indno_library" name="indno_library"
												value="0">
												<input type="hidden" id="basic_info_id" name="basic_info_id" value="0">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="inst_block simple-instruction">
													<strong>Instruction :</strong> Upload educational,
													experience, appointment, joining, ESI, PF, Form 16, account
													statement in single zip/pdf
												</div>
											</div>
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="medicalstaff_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>
																		Name<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Post<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Department<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>Document</h6></th>
																<th><h6>
																		Remarks<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>Action</h6></th>

															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="medi_staff1">
															<tr>
																<td>
																	<p>1</p>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="name_dtls1" id="name_dtls1"
																			class="form-control" placeholder="Enter Name">
																	</div>
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="post_dtls1" id="post_dtls1">
																				<option value="0" selected="selected">--Select--</option>
																				<c:forEach var="item" items="${getMedicalPostList}"
																					varStatus="num">
																					<option value="${item.id}" name="${item.post}">${item.post}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="department_dtls1"
																			id="department_dtls1" class="form-control"
																			placeholder="Enter Department">
																	</div>
																</td>

																<td>
																	<div class="input-style-1">
																		<input type="file" id="medicalstaff_doc1"
																			name="medicalstaff_doc1" class="form-control"
																			accept=".pdf"> <input type="hidden"
																			id="hid_medicalstaff_doc1"
																			name="hid_medicalstaff_doc1" value="0">
																		<div class="note-text">
																			<span class="errorClass" id="medicalstaff_doc_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="medicalstaff_doc_lbltik"></span>
																		</div>

																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="remarks1" id="remarks1"
																			class="form-control" placeholder="Remarks">
																	</div>
																</td>
																<td class="min-width addminusbut">
																	<ul class="buttons-group mainbtn daobtn action">
																		<li><a
																			class="main-btn success-btn btn-hover btn-sm btnapprove"
																			value="Save" title="SAVE" id="family_save_library1">
																				<i class="lni lni-checkmark"></i>
																		</a></li>
																		<li><a
																			class="main-btn success-btn btn-hover btn-sm addminusbut custom-d-none"
																			value="ADD" title="ADD" id="add_medical1"> <i
																				class="lni lni-plus"></i>
																		</a></li>

																		<li><a
																			class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																			value="Delete" title="Delete" id="library_remove1"><i
																				class="lni lni-trash-can"></i></a></li>

																	</ul>
																</td>

															</tr>
															<!-- end table row -->
														</tbody>
													</table>
													<input type="hidden" id="family_save_libraryh1"
														name="family_save_libraryh1"
														class="form-control autocomplete" value="1">

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

														<li><a
															class="main-btn dark-btn-outline btn-hover btn-iconic-icon viewData d-none"><i
																class="lni lni-eye"></i>View</a> <input type='hidden'
															id="viewId${parent_id}" value="${institude}"></li>
													</ul>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<div class="tab">
									<button class="tab-toggle">Paramedical Staff</button>
								</div>
								<div class="content">
									<h4 class="heading">Paramedical Staff</h4>
									<form:form name="" id="paramedical_staff"
										action="paramedical_staff" method="post" class=""
										commandName="">

										<div class="row">
											<input type="hidden" id="hid_paramedical_staff1"
												name="hid_paramedical_staff1" value="0"> <input
												type="hidden" id="indno_para" name="indno_para" value="0">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="inst_block simple-instruction">
													<strong>Instruction :</strong> Upload educational,
													experience, appointment, joining, ESI, PF, Form 16, account
													statement in single zip/pdf
												</div>
											</div>
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="paramedical_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>
																		Name<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Post<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Department<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>Document</h6></th>
																<th><h6>
																		Remarks<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>Action</h6></th>

															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="para_staff1">
															<tr>
																<td>
																	<p>1</p>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="p_name_dtls1"
																			id="p_name_dtls1" class="form-control"
																			placeholder="Enter Name">
																	</div>
																</td>

																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="p_post_dtls1" id="p_post_dtls1">
																				<option value="0" selected="selected">--Select--</option>
																				<c:forEach var="item"
																					items="${getParaMedicalPostList}" varStatus="num">
																					<option value="${item.id}" name="${item.post}">${item.post}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="p_department_dtls1"
																			id="p_department_dtls1" class="form-control"
																			placeholder="Enter Department">
																	</div>
																</td>

																<td>
																	<div class="input-style-1">
																		<input type="file" id="paramedical_doc1"
																			name="paramedical_doc1" class="form-control"
																			accept=".pdf"> <input type="hidden"
																			id="hid_paramedicalstaff_doc1"
																			name="hid_paramedicalstaff_doc1" value="0">
																		<div class="note-text">
																			<span class="errorClass" id="paramedical_doc_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="paramedical_doc_lbltik"></span>
																		</div>

																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="p_remarks1" id="p_remarks1"
																			class="form-control" placeholder="Remarks">
																	</div>
																</td>
																<td class="min-width addminusbut">
																	<ul class="buttons-group mainbtn daobtn action">
																		<li><a
																			class="main-btn success-btn btn-hover btn-sm btnapprove"
																			value="Save" title="SAVE" id="p_family_save_library1">
																				<i class="lni lni-checkmark"></i>
																		</a></li>
																		<li><a
																			class="main-btn success-btn btn-hover btn-sm addminusbut custom-d-none"
																			value="ADD" title="ADD" id="add_para1"> <i
																				class="lni lni-plus"></i>
																		</a></li>

																		<li><a
																			class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																			value="Delete" title="Delete" id="p_library_remove1"><i
																				class="lni lni-trash-can"></i></a></li>

																	</ul>
																</td>

															</tr>

														</tbody>
													</table>
													<input type="hidden" id="p_family_save_libraryh1"
														name="p_family_save_libraryh1"
														class="form-control autocomplete" value="1">
												</div>
												<!-- end card -->
											</div>
											<!-- end col -->
										</div>
										<!-- end row -->
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12"></div>
											</div>
										</div>
									</form:form>
								</div>
								<div class="tab">
									<button class="tab-toggle">Auxillary Staff</button>
								</div>
								<div class="content">
									<h4 class="heading">Auxillary Staff</h4>
									<form:form name="" id="auxillary_staff"
										action="auxillary_staff" method="post" class="" commandName="">
										<div class="row">
											<input type="hidden" id="hid_auxillarystaff1"
												name="hid_auxillarystaff1" value="0"> <input
												type="hidden" id="indno_auxi" name="indno_auxi" value="0">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="inst_block simple-instruction">
													<strong>Instruction :</strong> Upload educational,
													experience, appointment, joining, ESI, PF, Form 16, account
													statement in single zip/pdf
												</div>
											</div>
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="auxillarystaff_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>
																<th><h6>
																		Name<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Post<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>Document</h6></th>
																<th><h6>
																		Remarks<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>Action</h6></th>

															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="auxi_staff1">
															<tr>
																<td>
																	<p>1</p>
																</td>


																<td>
																	<div class="input-style-1">
																		<input type="text" name="a_name_dtls1"
																			id="a_name_dtls1" class="form-control"
																			placeholder="Enter Name">
																	</div>
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="a_post_dtls1" id="a_post_dtls1">
																				<option value="0" selected="selected">--Select--</option>
																				<c:forEach var="item"
																					items="${getAuxillaryPostList}" varStatus="num">
																					<option value="${item.id}" name="${item.post}">${item.post}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																</td>


																<td>
																	<div class="input-style-1">
																		<input type="file" id="auxillarystaff_doc1"
																			name="auxillarystaff_doc1" class="form-control"
																			accept=".pdf"> <input type="hidden"
																			id="hid_auxillarystaff_doc1"
																			name="hid_auxillarystaff_doc1" value="0">
																		<div class="note-text">
																			<span class="errorClass" id="auxillarystaff_doc_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="auxillarystaff_doc_lbltik"></span>
																		</div>


																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="a_remarks1" id="a_remarks1"
																			class="form-control" placeholder="Remarks">
																	</div>
																</td>
																<td class="min-width addminusbut">
																	<ul class="buttons-group mainbtn daobtn action">
																		<li><a
																			class="main-btn success-btn btn-hover btn-sm btnapprove"
																			value="Save" title="SAVE" id="a_family_save_library1">
																				<i class="lni lni-checkmark"></i>
																		</a></li>
																		<li><a
																			class="main-btn success-btn btn-hover btn-sm addminusbut custom-d-none"
																			value="ADD" title="ADD" id="add_auxi1"> <i
																				class="lni lni-plus"></i>
																		</a></li>

																		<li><a
																			class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																			value="Delete" title="Delete" id="a_library_remove1"><i
																				class="lni lni-trash-can"></i></a></li>

																	</ul>
																</td>

															</tr>
															<!-- end table row -->
														</tbody>
													</table>
													<input type="hidden" id="a_family_save_libraryh1"
														name="a_family_save_libraryh1"
														class="form-control autocomplete" value="1">
												</div>
												<!-- end card -->
											</div>
											<!-- end col -->
										</div>
										<!-- end row -->
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12"></div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- 	Add Auxillary-Staff End -->


							</div>
						</div>
					</div>
					<!--    ===========================
		Staff List End
===========================     -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</section>

	<!--=========================================== -->

	<c:url value="hospital_staff_list_view" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewForm"
		name="viewForm" modelAttribute="hos_staff_list_view_id">
		<input type="hidden" name="hos_staff_list_id"
			id="hos_staff_list_view_id" value="0" />
		<input type="hidden" name="statusview" id="statusview" value="0" />
	</form:form>

	<!--===========================================-->

	<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>
</body>
<script type="text/javascript" nonce="${cspNonce}">		
// 	--------------------------------------------START-->COLLEGE COUNCIL------------------------------------------
	$(document).ready(function() {
		
		if ('${role}'=='NCH') {
			$(".viewData").addClass("d-none")
		}
		if ('${role}'=='Institute_NCH') {
			$(".viewData").removeClass("d-none")
		} 
		
		
		getMedical_Details();
		getParaMedical_Details();
		getAuxiMedical_Details();
		$("#basic_info_id").val('${basic_info_id}');
		
		var dataparent_medical = '${dataparent_medical}';
		if (dataparent_medical != "[]") {

			$("#hid_medical_staff").val(
					'${dataparent_medical[0].mainid}');
			$("#name_dtls").val(
			'${dataparent_medical[0].name}');
			$("#post_dtls").val(
			'${dataparent_medical[0].post}');
			$("#department_dtls").val(
			'${dataparent_medical[0].department}');
			$("#hid_medicalstaff_doc").val(
					'${dataparent_medical[0].document}');
			$("#remarks").val(
			'${dataparent_medical[0].remarks}');
		}
		
		
		var dataparent_paramedical = '${dataparent_paramedical}';
		if (dataparent_paramedical != "[]") {

			$("#hid_paramedical_staff").val(
					'${dataparent_paramedical[0].mainid}');
			$("#p_name_dtls").val(
			'${dataparent_paramedical[0].name}');
			$("#p_post_dtls").val(
			'${dataparent_paramedical[0].post}');
			$("#p_department_dtls").val(
			'${dataparent_paramedical[0].department}');
			$("#hid_paramedicalstaff_doc").val(
					'${dataparent_paramedical[0].document}');
			$("#p_remarks").val(
			'${dataparent_paramedical[0].remarks}');
		}
		
		
		
		var dataparent_auxillarystaff = '${dataparent_auxillarystaff}';
		if (dataparent_auxillarystaff != "[]") {

			$("#hid_auxillarystaff").val(
					'${dataparent_auxillarystaff[0].mainid}');
			$("#a_name_dtls").val(
			'${dataparent_auxillarystaff[0].name}');
			$("#a_post_dtls").val(
			'${dataparent_auxillarystaff[0].post}');
			$("#hid_auxillarystaff_doc").val(
					'${dataparent_auxillarystaff[0].document}');
			$("#a_remarks").val(
			'${dataparent_auxillarystaff[0].remarks}');
			
		}
	});


	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('name_dtls1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};

		document.getElementById('add_medical1').onclick = function() {
			return formOpen_medical(1);
		};

		document.getElementById('library_remove1').onclick = function() {
			return Delete_Medical_Details(1);
		};

		document.getElementById('family_save_library1').onclick = function() {
			return Save_As_Draft_Hospital_Medical_Details(1);
		};
		
		document.getElementById('add_para1').onclick = function() {
			return formOpen_para_medical(1);
		};

		document.getElementById('p_library_remove1').onclick = function() {
			return delete_ParaMedical_Details(1);
		};

		document.getElementById('p_family_save_library1').onclick = function() {
			return Save_As_Draft_Hospital_ParaMedi_Details(1);
		};
		
		document.getElementById('paramedical_doc1').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"paramedical_doc1","200kb","paramedical_doc_lbltik","paramedical_doc_lbl",this.accept)
		};
		
		document.getElementById('auxillarystaff_doc1').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"auxillarystaff_doc1","200kb","auxillarystaff_doc_lbltik","auxillarystaff_doc_lbl",this.accept)
		};
		
		document.getElementById('medicalstaff_doc1').onchange = function() {
			return pdfFileSizeValidation(this,this.value,"medicalstaff_doc1","200kb","medicalstaff_doc_lbltik","medicalstaff_doc_lbl",this.accept)
		};
		
		
		document.getElementById('add_auxi1').onclick = function() {
			return formOpen_auxi_medical(1);
		};

		document.getElementById('a_library_remove1').onclick = function() {
			return delete_auxiMedical_Details(1);
		};

		document.getElementById('a_family_save_library1').onclick = function() {
			return Save_As_Draft_Hospital_Auxillary_Details(1);
		};
		document.getElementById('p_name_dtls1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		document.getElementById('a_name_dtls1').onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		
	});

	//SAve Medical Details------------------------------------	
	function formOpen_medical(R) {

		$("#medicalstaff_table").show();

		att = 0;

		$("#add_medical" + R).hide();
		$("#library_remove" + R).hide();
		att = parseInt($("#family_save_libraryh1").val()) + 1;
		att = parseInt(R) + 1;
// 		alert(att);

		if (att < 21) {

			$("input#family_save_libraryh1").val(att);//current serial No
			$("table#medicalstaff_table")
					.append(
							'<tr id="medi_staff1'+att+'">'

									+ '<td class="min-width"><div class="lead"><div class="lead-text"><p>'
									+ att
									+ '</p></div></div></td>'
									+ '<td><div class="input-style-1"><input type="text" id="name_dtls'+att+'" name="name_dtls'+att+'" class="form-control"' 
					+'autocomplete="off" placeholder="Enter Name"></div></td>'

					+'<td><div class="select-style-1"><div class="select-position"><select name="post_dtls'+att+'" class="" id="post_dtls'+att+'">'
					+'<option value="0">--Select--</option><c:forEach var="item" items="${getMedicalPostList}" varStatus="num"><option value="${item.id}" name="${item.post}">${item.post}</option>'
					+'</c:forEach></select></div></div></td>'

									+ '<td><div class="input-style-1"><input type="text" id="department_dtls'+att+'" name="department_dtls'+att+'"'
					+'class="form-control" autocomplete="off" placeholder="Enter Department"></div></td>'

					+'<td><div class="input-style-1"><input type="file" id="medicalstaff_doc'+att+'" name="medicalstaff_doc'+att+'" class="form-control" accept=".pdf">'
					+'<input type=hidden id="hid_medicalstaff_doc'+att+'" name="hid_medicalstaff_doc'+att+'" class="form-control" value="">'
					+'<div class="note-text"><span class="errorClass" id="medicalstaff_doc_lbl'+att+'">${doc_path_doc1_msg}</span>'
				 	+'<span class="tikClass" id="medicalstaff_doc_lbltik'+att+'"></span></div></div></td>'
									
					
					+ '<td><div class="input-style-1"><input type="text" id="remarks'+att+'" name="remarks'+att+'"'
					+'class="form-control" autocomplete="off" placeholder="Enter Remarks"></div></td>'

									+ '<td class="addminusbut"><ul class="buttons-group mainbtn daobtn action"><li><a class="main-btn success-btn btn-hover btn-sm btnapprove"'
					+'value="Save" title="SAVE" id="family_save_library'+att+'"><i class="lni lni-checkmark"></i></a></li>'
									+ '<li><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" value="ADD" title="ADD" id="add_medical'+att+'">'
									+ '<i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"'
					+'value="Delete" title="Delete" id="library_remove'+att+'">'
									+ '<input type="hidden" id="family_save_libraryh'+att+'" name="family_save_libraryh'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
									+ '<input type="hidden" id="hid_medical_staff'+att+'" name="hid_medical_staff'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
									+ '<i class="lni lni-trash-can"></i></a></li></ul></td>'
									+ '</tr>');


			addOnclick_bankacct(att);

			$('#btnmodel' + att).click(function() {
				$('#modelWindow').modal('show');
				dynamicTable(att, 1);
			});

			$('#closeModel').click(function() {
				$('#modelWindow').modal('hide');
			});
			$('#closeModel2').click(function() {
				$('#modelWindow').modal('hide');
			});

			$("#family_save_libraryh1").val(att);

		} else {
			alert("You can not enter more than twenty times");
			if (att == 21) {
				att = att - 1;
				$("#rp_id_remove" + att).show();
			}
		}
	}

	function addOnclick_bankacct(att) {
		document.getElementById('add_medical' + att).onclick = function() {
			return formOpen_medical(att);
		};
		document.getElementById('library_remove' + att).onclick = function() {
			return Delete_Medical_Details(att);
		};
		document.getElementById('family_save_library' + att).onclick = function() {
			Save_As_Draft_Hospital_Medical_Details(att);
		};
		document.getElementById('medicalstaff_doc'+ att).onchange = function() {
			return pdfFileSizeValidation(this,this.value,"medicalstaff_doc"+ att,"200kb","medicalstaff_doc_lbltik"+ att,"medicalstaff_doc_lbl"+ att,this.accept)
		};
		
		document.getElementById('name_dtls'+ att).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
	}

	function Save_As_Draft_Hospital_Medical_Details(ps) {
		$.ajaxSetup({
			async : false
		});
		var basic_info_id = $("#basic_info_id").val();
		if(basic_info_id == "0"){
			alert("Please Save Basic details First");
			return false;
		}
		var res = CheckNullorBlank('name_dtls' + ps);
		if (res !== "true") {
			alert(res + "Medical Staff Name in Medical Staff Tab");
			$('#name_dtls' + ps).focus();
			return false;
		}
		if ($("#post_dtls" + ps).val().trim() == "0") {
			alert("Please Select Medical Staff Post in Medical Staff Tab");
			$("#post_dtls").focus();
			return false;
		}
		var res = CheckNullorBlank('department_dtls' + ps);
		if (res !== "true") {
			alert(res + "Medical Staff Department in Medical Staff Tab");
			$('#department_dtls' + ps).focus();
			return false;
		}
		var res = CheckNullorBlank('remarks' + ps);
		if (res !== "true") {
			alert(res + "Medical Staff Remarks in Medical Staff Tab");
			$('#remarks' + ps).focus();
			return false;
		}
		$("#indno_library").val(ps);
		var form_data = new FormData(document.getElementById("medical_staff"));
		$.ajax({
			url : 'hospital_medical_staff_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j) {
					if(j>0){
						$("#hid_medical_staff"+ps).val(j);
						alert("Data Saved Successfully");
						$("#add_medical" + ps).show();
						$("#library_remove" + ps).show();
		          }
		          else{
		        	  alert(j);
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});

	}

	function Delete_Medical_Details(R) {

		if (confirm('Are you sure you want to delete?')) {

			var hid_medical_staff = $('#hid_medical_staff' + R).val();

			$.post("delete_Medical_Details?" + key + "=" + value, {
				hid_medical_staff : hid_medical_staff
			}, function(j) {
				alert(j);
			});

			$("tr#medi_staff1" + R).remove();
			R = R - 1;
			$("input#count_hidden_att_name_med").val(att);
			$("#add_medical" + R).show();
			$("#library_remove" + R).show();
		} else {
			return false;
		}
	}

	function getMedical_Details() {

		$.ajaxSetup({
			async : false
		});

		var ser = 0;

		$.post("getMedical_Details?" + key + "=" + value, {

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				ser = i + 1;

				if (j.length > 0) {

					if (ser > 1) {

						$("#add_medical" + (ser - 1)).click();
					}

					$("#hid_medical_staff" + ser).val(j[i].id);
					$("#name_dtls" + ser).val(j[i].name);
// 					
					$("#post_dtls" + ser).val(j[i].post);
					$("#department_dtls" + ser).val(
							j[i].department);
// 					alert(j[i].account_no);
					$("#hid_medicalstaff_doc" + ser).val(j[i].document);
					$("#remarks" + ser).val(j[i].remarks);
					$("#add_medical" + ser).show();
					$("#library_remove" + ser).show();
				}
			}
		});
	}
	
	//SAve PARA Medical Details------------------------------------		
	
	function formOpen_para_medical(R) {

		$("#paramedical_table").show();

		att = 0;

		$("#add_para" + R).hide();
		$("#p_library_remove" + R).hide();
		att = parseInt($("#p_family_save_libraryh1").val()) + 1;
		att = parseInt(R) + 1;
// 		alert(att);

		if (att < 21) {

			$("input#p_family_save_libraryh1").val(att);//current serial No
			$("table#paramedical_table")
					.append(
							'<tr id="para_staff1'+att+'">'

									+ '<td class="min-width"><div class="lead"><div class="lead-text"><p>'
									+ att
									+ '</p></div></div></td>'
									+ '<td><div class="input-style-1"><input type="text" id="p_name_dtls'+att+'" name="p_name_dtls'+att+'" class="form-control"' 
					+'autocomplete="off" placeholder="Enter Name"></div></td>'

					+'<td><div class="select-style-1"><div class="select-position"><select name="p_post_dtls'+att+'" class="" id="p_post_dtls'+att+'">'
					+'<option value="0">--Select--</option><c:forEach var="item" items="${getParaMedicalPostList}" varStatus="num"><option value="${item.id}" name="${item.post}">${item.post}</option>'
					+'</c:forEach></select></div></div></td>'

									+ '<td><div class="input-style-1"><input type="text" id="p_department_dtls'+att+'" name="p_department_dtls'+att+'"'
					+'class="form-control" autocomplete="off" placeholder="Enter Department"></div></td>'

					+'<td><div class="input-style-1"><input type="file" id="paramedical_doc'+att+'" name="paramedical_doc'+att+'" class="form-control" accept=".pdf">'
					+'<input type=hidden id="hid_paramedicalstaff_doc'+att+'" name="hid_paramedicalstaff_doc'+att+'" class="form-control" value="">'
					+'<div class="note-text"><span class="errorClass" id="paramedical_doc_lbl'+att+'">${doc_path_doc1_msg}</span>'
				 	+'<span class="tikClass" id="paramedical_doc_lbltik'+att+'"></span></div></div></td>'
									
					
					+ '<td><div class="input-style-1"><input type="text" id="p_remarks'+att+'" name="p_remarks'+att+'"'
					+'class="form-control" autocomplete="off" placeholder="Enter Remarks"></div></td>'

									+ '<td class="addminusbut"><ul class="buttons-group mainbtn daobtn action"><li><a class="main-btn success-btn btn-hover btn-sm btnapprove"'
					+'value="Save" title="SAVE" id="p_family_save_library'+att+'"><i class="lni lni-checkmark"></i></a></li>'
									+ '<li><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" value="ADD" title="ADD" id="add_para'+att+'">'
									+ '<i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"'
					+'value="Delete" title="Delete" id="p_library_remove'+att+'">'
									+ '<input type="hidden" id="p_family_save_libraryh'+att+'" name="p_family_save_libraryh'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
									+ '<input type="hidden" id="hid_paramedical_staff'+att+'" name="hid_paramedical_staff'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
									+ '<i class="lni lni-trash-can"></i></a></li></ul></td>'
									+ '</tr>');


			addOnclick_para_medical(att);

			$('#btnmodel' + att).click(function() {
				$('#modelWindow').modal('show');
				dynamicTable(att, 1);
			});

			$('#closeModel').click(function() {
				$('#modelWindow').modal('hide');
			});
			$('#closeModel2').click(function() {
				$('#modelWindow').modal('hide');
			});

			$("#p_family_save_libraryh1").val(att);

		} else {
			alert("You can not enter more than twenty times");
			if (att == 21) {
				att = att - 1;
				$("#rp_id_remove" + att).show();
			}
		}
	}

	function addOnclick_para_medical(att) {
		document.getElementById('add_para' + att).onclick = function() {
			return formOpen_para_medical(att);
		};
		document.getElementById('p_library_remove' + att).onclick = function() {
			return delete_ParaMedical_Details(att);
		};
		document.getElementById('p_family_save_library' + att).onclick = function() {
			Save_As_Draft_Hospital_ParaMedi_Details(att);
		};
		document.getElementById('paramedical_doc'+ att).onchange = function() {
			return pdfFileSizeValidation(this,this.value,"paramedical_doc"+ att,"200kb","paramedical_doc_lbltik"+ att,"paramedical_doc_lbl"+ att,this.accept)
		};
		document.getElementById('p_name_dtls'+ att).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};

	}

	function Save_As_Draft_Hospital_ParaMedi_Details(ps) {
		
		$.ajaxSetup({
			async : false
		});
		var hid_medical_staff1 = $("#hid_medical_staff1").val();
		if(hid_medical_staff1 == "0"){
			
			alert("Please Save Medical Staff Information First");
		
			return false;
		}
		var res = CheckNullorBlank('p_name_dtls' + ps);
		if (res !== "true") {
			alert(res + "Paramedical Staff Name in Paramedical Staff Tab");
			$('#p_name_dtls' + ps).focus();
			return false;
		}
		if ($("#p_post_dtls" + ps).val().trim() == "0") {
			alert("Please Select Paramedical Staff Post in Paramedical Staff Tab");
			$("#p_post_dtls").focus();
			return false;
		}
		var res = CheckNullorBlank('p_department_dtls' + ps);
		if (res !== "true") {
			alert(res + "Paramedical Staff Department in Paramedical Staff Tab");
			$('#p_department_dtls' + ps).focus();
			return false;
		}
		var res = CheckNullorBlank('p_remarks' + ps);
		if (res !== "true") {
			alert(res + "Paramedical Staff Remarks in Paramedical Staff Tab");
			$('#p_remarks' + ps).focus();
			return false;
		}
		$("#indno_para").val(ps);
		var form_data = new FormData(document.getElementById("paramedical_staff"));
		$.ajax({
			url : 'hospital_Paramedical_Staff_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j) {
					if(j>0){
						$("#hid_paramedical_staff"+ps).val(j);
						alert("Data Saved Successfully");
						$("#add_para" + ps).show();
						$("#p_library_remove" + ps).show();
		          }
		          else{
		        	  alert(j);
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});

	}

	function delete_ParaMedical_Details(R) {

		if (confirm('Are you sure you want to delete?')) {

			var hid_paramedical_staff = $('#hid_paramedical_staff' + R).val();

			$.post("delete_ParaMedical_Details?" + key + "=" + value, {
				hid_paramedical_staff : hid_paramedical_staff
			}, function(j) {
				alert(j);
			});

			$("tr#para_staff1" + R).remove();
			R = R - 1;
			$("input#count_hidden_att_name_med").val(att);
			$("#add_para" + R).show();
			$("#p_library_remove" + R).show();
		} else {
			return false;
		}
	}

	function getParaMedical_Details() {

		$.ajaxSetup({
			async : false
		});

		var ser = 0;

		$.post("getParaMedical_Details?" + key + "=" + value, {

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				ser = i + 1;

				if (j.length > 0) {

					if (ser > 1) {

						$("#add_para" + (ser - 1)).click();
					}

					$("#hid_paramedical_staff" + ser).val(j[i].id);
					$("#p_name_dtls" + ser).val(j[i].name);
// 					
					$("#p_post_dtls" + ser).val(j[i].post);
					$("#p_department_dtls" + ser).val(
							j[i].department);
					$("#hid_paramedicalstaff_doc" + ser).val(j[i].document);
					$("#p_remarks" + ser).val(j[i].remarks);
					$("#add_para" + ser).show();
					$("#p_library_remove" + ser).show();
				}
			}
		});
	}
//SAve Auxillary Medical Details------------------------------------		
	
	function formOpen_auxi_medical(R) {

		$("#auxillarystaff_table").show();

		att = 0;

		$("#add_auxi" + R).hide();
		$("#a_library_remove" + R).hide();
		att = parseInt($("#a_family_save_libraryh1").val()) + 1;
		att = parseInt(R) + 1;
// 		alert(att);

		if (att < 21) {

			$("input#a_family_save_libraryh1").val(att);//current serial No
			$("table#auxillarystaff_table")
					.append(
							'<tr id="auxi_staff1'+att+'">'
									+ '<td class="min-width"><div class="lead"><div class="lead-text"><p>'
									+ att
									+ '</p></div></div></td>'
									+ '<td><div class="input-style-1"><input type="text" id="a_name_dtls'+att+'" name="a_name_dtls'+att+'" class="form-control"' 
					+'autocomplete="off" placeholder="Enter Name"></div></td>'

					+'<td><div class="select-style-1"><div class="select-position"><select name="a_post_dtls'+att+'" class="" id="a_post_dtls'+att+'">'
					+'<option value="0">--Select--</option><c:forEach var="item" items="${getAuxillaryPostList}" varStatus="num"><option value="${item.id}" name="${item.post}">${item.post}</option>'
					+'</c:forEach></select></div></div></td>'


					+'<td><div class="input-style-1"><input type="file" id="auxillarystaff_doc'+att+'" name="auxillarystaff_doc'+att+'" class="form-control" accept=".pdf">'
					+'<input type=hidden id="hid_auxillarystaff_doc'+att+'" name="hid_auxillarystaff_doc'+att+'" class="form-control" value="">'
					+'<div class="note-text"><span class="errorClass" id="auxillarystaff_doc_lbl'+att+'">${doc_path_doc1_msg}</span>'
				 	+'<span class="tikClass" id="auxillarystaff_doc_lbltik'+att+'"></span></div></div></td>'
									
					
					+ '<td><div class="input-style-1"><input type="text" id="a_remarks'+att+'" name="a_remarks'+att+'"'
					+'class="form-control" autocomplete="off" placeholder="Enter Remarks"></div></td>'

									+ '<td class="addminusbut"><ul class="buttons-group mainbtn daobtn action"><li><a class="main-btn success-btn btn-hover btn-sm btnapprove"'
					+'value="Save" title="SAVE" id="a_family_save_library'+att+'"><i class="lni lni-checkmark"></i></a></li>'
									+ '<li><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" value="ADD" title="ADD" id="add_auxi'+att+'">'
									+ '<i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"'
					+'value="Delete" title="Delete" id="a_library_remove'+att+'">'
									+ '<input type="hidden" id="a_family_save_libraryh'+att+'" name="a_family_save_libraryh'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
									+ '<input type="hidden" id="hid_auxillarystaff'+att+'" name="hid_auxillarystaff'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
									+ '<i class="lni lni-trash-can"></i></a></li></ul></td>'
									+ '</tr>');


			addOnclick_auxillary_medical(att);

			$('#btnmodel' + att).click(function() {
				$('#modelWindow').modal('show');
				dynamicTable(att, 1);
			});

			$('#closeModel').click(function() {
				$('#modelWindow').modal('hide');
			});
			$('#closeModel2').click(function() {
				$('#modelWindow').modal('hide');
			});

			$("#a_family_save_libraryh1").val(att);

		} else {
			alert("You can not enter more than twenty times");
			if (att == 21) {
				att = att - 1;
				$("#rp_id_remove" + att).show();
			}
		}
	}

	function addOnclick_auxillary_medical(att) {
		document.getElementById('add_auxi' + att).onclick = function() {
			return formOpen_auxi_medical(att);
		};
		document.getElementById('a_library_remove' + att).onclick = function() {
			return delete_auxiMedical_Details(att);
		};
		document.getElementById('a_family_save_library' + att).onclick = function() {
			Save_As_Draft_Hospital_Auxillary_Details(att);
		};
		document.getElementById('auxillarystaff_doc'+ att).onchange = function() {
			return pdfFileSizeValidation(this,this.value,"auxillarystaff_doc"+ att,"200kb","auxillarystaff_doc_lbltik"+ att,"auxillarystaff_doc_lbl"+ att,this.accept)
		};
		document.getElementById('a_name_dtls'+ att).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
	}

	function Save_As_Draft_Hospital_Auxillary_Details(ps) {
		$.ajaxSetup({
			async : false
		});
		
		
		var hid_medical_staff1 = $("#hid_medical_staff1").val();
		if(hid_medical_staff1 == "0"){
			
			alert("Please Save Medical Staff Information First");
		
			return false;
		}
		var res = CheckNullorBlank('a_name_dtls' + ps);
		if (res !== "true") {
			alert(res + "Auxillary Staff Name in Auxillary Staff Tab");
			$('#a_name_dtls' + ps).focus();
			return false;
		}
		if ($("#a_post_dtls" + ps).val().trim() == "0") {
			alert("Please Select Auxillary Staff Post in Auxillary Staff Tab");
			$("#a_post_dtls").focus();
			return false;
		}
		var res = CheckNullorBlank('a_remarks' + ps);
		if (res !== "true") {
			alert(res + "Auxillary Staff Remarks in Auxillary Staff Tab");
			$('#a_remarks' + ps).focus();
			return false;
		}
		$("#indno_auxi").val(ps);
		var form_data = new FormData(document.getElementById("auxillary_staff"));
		$.ajax({
			url : 'hospital_Auxillary_Staff_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j) {
					if(j>0){
						$("#hid_auxillarystaff"+ps).val(j);
						alert("Data Saved Successfully");
						$("#add_auxi" + ps).show();
						$("#a_library_remove" + ps).show();
		          }
		          else{
		        	  alert(j);
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});

	}

	
	function delete_auxiMedical_Details(R) {

		if (confirm('Are you sure you want to delete?')) {

			var hid_auxillarystaff = $('#hid_auxillarystaff' + R).val();

			$.post("delete_auxiMedical_Details?" + key + "=" + value, {
				hid_auxillarystaff : hid_auxillarystaff
			}, function(j) {
				alert(j);
			});

			$("tr#auxi_staff1" + R).remove();
			R = R - 1;
			$("input#count_hidden_att_name_med").val(att);
			$("#add_auxi" + R).show();
			$("#a_library_remove" + R).show();
		} else {
			return false;
		}
	}

	
	
	function getAuxiMedical_Details() {

		$.ajaxSetup({
			async : false
		});

		var ser = 0;

		$.post("getAuxiMedical_Details?" + key + "=" + value, {

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				ser = i + 1;

				if (j.length > 0) {

					if (ser > 1) {

						$("#add_auxi" + (ser - 1)).click();
					}

					$("#hid_auxillarystaff" + ser).val(j[i].id);
					$("#a_name_dtls" + ser).val(j[i].name);
// 					
					$("#a_post_dtls" + ser).val(j[i].post);
					$("#hid_auxillarystaff_doc" + ser).val(j[i].document);
					$("#a_remarks" + ser).val(j[i].remarks);
					$("#add_auxi" + ser).show();
					$("#a_library_remove" + ser).show();
				}
			}
		});
	}
	
	
	
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

		$("#hos_staff_list_view_id").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
	}
	

	</script>
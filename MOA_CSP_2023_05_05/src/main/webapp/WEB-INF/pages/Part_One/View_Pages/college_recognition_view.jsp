<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- hemburgermenu css start -->
<link rel="stylesheet" href="assets/vendor/hamburger-menu/hemburgermenu.css">
<!-- hemburgermenu css end -->

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>View Institution Information</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View Institution Information</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper tunnel-form preview-form custom-mobile-menu">

			<!-- humburger menu for collage regulation - part-1 start -->
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="hamburger-menu">
						<div class="bar" title="Click here for menu">
						</div>
						<div class="hm-title"><h4 class="hm-title-text">College regulation view form report</h4></div>
					</div>

					<nav class="mobile-menu">
						<h5 class="hm-menu-title">Form Reports Links</h5>
						<ul>
							<li><a href="basics_information_view">Institution Information</a></li>
<!-- 							<li class="has-children">About <span class="icon-arrow"></span> -->
<!-- 								<ul class="children"> -->
<!-- 									<li><a href="submenu1.html">Submenu #1</a></li> -->
<!-- 									<li><a href="submenu2.html">Submenu #2</a></li> -->
<!-- 									<li><a href="submenu3.html">Submenu #3</a></li> -->
<!-- 								</ul> -->
<!-- 							</li> -->
							<li><a href="college_infrastructure_view">College Infrastructure</a></li>
							<li><a href="college_department_view">College Department</a></li>
							<li><a href="college_financial_view">College Financial</a></li>
							<li><a href="student_details_view">Student Details</a></li>
							<li><a href="college_staff_info_view">College Staff Information</a></li>
							<li><a href="college_staff_list_view">College Staff List</a></li>
							<li><a href="department_equipments_view">Department Equipments</a></li>
							<li><a href="hospital_infrastructure_view">Hospital Infrastucture</a></li>
							<li><a href="hospital_ipdopd_view">Hospital IPD OPD</a></li>
							<li><a href="otherhospital_detail_view">Other Hospital Details</a></li>
							<li><a href="hospital_staffdetails_view">Hospital Staff Details</a></li>
							<li><a href="hospital_staff_list_view">Hospital Staff List</a></li>
							<li><a href="hospital_equipments_view">Hospital Equipments</a></li>
							<li><a href="declaration_view">Declaration</a></li>
						</ul>
					</nav>
				</div>
			</div>
			<!-- humburger menu for collage regulation - part-1 end -->

			<div class="card-style mb-30">
				<!-- ===========================
							Basic Information Start
						=========================== -->
				<div class="field-box">
					<form:form name="View_Search_Basic_Info"
						id="View_Search_Basic_Info"
						action="View_Search_Basic_InfoAction?${_csrf.parameterName}=${_csrf.token}"
						method="post" modelAttribute="View_Search_Basic_InfoCMD">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Institution Details</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Institution Name</label> <span class="value-bind"
										id="inst_name_label">${getInstname[0].institute_name}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>College Code</label> <span class="value-bind"
										id="clg_code_label"> ${getInstname[0].code}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Address Line 1</label> <span class="value-bind"
										id="per_add_line1label">
										${View_Search_Basic_InfoCMD.address_line1}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Address Line 2</label> <span class="value-bind"
										id="per_add_line2label">
										${View_Search_Basic_InfoCMD.address_line2}</span>
								</div>
							</div>
							<div class="form-group stepform-group custom-d-none">
								<select name="per_state" id="per_state" class="form-control">
									<option value="0" selected="selected">--Select State
										--</option>
									<c:forEach var="item" items="${getMedStateName}"
										varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>


							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>State</label> <span class="value-bind"
										id="per_statelabel">
										${View_Search_Basic_InfoCMD.state_id}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>City</label> <span class="value-bind" id="city_label">${View_Search_Basic_InfoCMD.city}
									</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Pincode</label> <span class="value-bind"
										id="per_pincode_label">
										${View_Search_Basic_InfoCMD.pincode}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>College Phone No.</label> <span class="value-bind"
										id="clgphone__label">
										${View_Search_Basic_InfoCMD.college_phone_no}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Hospital Phone No.</label> <span class="value-bind"
										id="hosphone__label">
										${View_Search_Basic_InfoCMD.hospital_phone_no	}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Mobile No.</label> <span class="value-bind"
										id="mobile_no_addr__label">
										${View_Search_Basic_InfoCMD.mobile_no}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Fax No.</label> <span class="value-bind"
										id="fax_no_label"> ${View_Search_Basic_InfoCMD.fax_no}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Email ID</label> <span class="value-bind"
										id="emailid_label">
										${View_Search_Basic_InfoCMD.email_id}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Website</label> <span class="value-bind"
										id="website_label">
										${View_Search_Basic_InfoCMD.website}</span>
								</div>
							</div>

						</div>
					</form:form>
				</div>

				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg">
								<h5 class="custom-title-tag">Management Details</h5>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Institution Type</label> <span class="value-bind"
									id="mang_inst_type_label">${View_Search_Basic_InfoCMD.institute_type}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Managing Body Name</label> <span class="value-bind"
									id="mang_managing_body_label">${View_Search_Basic_InfoCMD.name_of_managing_body}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Management Contact Name</label> <span class="value-bind"
									id="mang_managing_contact_label">${View_Search_Basic_InfoCMD.name_of_management_contact}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Institution Name</label> <span class="value-bind"
									id="mang_inst_name_label">${getInstname[0].institute_name}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>College Code</label> <span class="value-bind"
									id="mang_clg_code_label"> ${getInstname[0].code}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Address Line 1</label> <span class="value-bind"
									id="mang_per_add_line1label">
									${View_Search_Basic_InfoCMD.mngt_address_line1}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Address Line 2</label> <span class="value-bind"
									id="mang_per_add_line2label">
									${View_Search_Basic_InfoCMD.mngt_address_line2}</span>
							</div>
						</div>

						<div class="select-position custom-d-none">
							<select class="form-control" name="mang_per_state"
								id="mang_per_state">
								<!-- style="text-transform: uppercase" -->
								<option value="0" selected="selected">--Select State --</option>
								<c:forEach var="item" items="${getMedStateName}" varStatus="num">
									<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
							</select>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>State</label> <span class="value-bind"
									id="mang_per_statelabel">
									${View_Search_Basic_InfoCMD.mngt_state}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>City</label> <span class="value-bind"
									id="mang_city_label">
									${View_Search_Basic_InfoCMD.mngt_city}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Office Phone No.</label> <span class="value-bind"
									id="mang_office_phone_label">
									${View_Search_Basic_InfoCMD.mngt_office_phone_no}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Residence Phone No.</label> <span class="value-bind"
									id="mang_residence_phone_label">
									${View_Search_Basic_InfoCMD.mngt_residence_phone_no}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Mobile No.</label> <span class="value-bind"
									id="mang_mobile_label">
									${View_Search_Basic_InfoCMD.mngt_mobile_no}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Email ID</label> <span class="value-bind"
									id="mang_emailid_label">
									${View_Search_Basic_InfoCMD.mngt_email_id}</span>
							</div>
						</div>

					</div>
				</div>

				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg">
								<h5 class="custom-title-tag">Society/Trust/Government
									Information</h5>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Name of Society/Trust/Government</label> <span
									class="value-bind" id="">${View_Search_Basic_InfoCMD.name_of_society}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Society/Trust Registration No.</label>
								${View_Search_Basic_InfoCMD.society_reg_no}<span
									class="value-bind" id=""></span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Year of Establishment of the Society/Trust</label>
								${View_Search_Basic_InfoCMD.year_of_establish_society}<span
									class="value-bind" id=""></span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Date of the permission of the State Govt.</label> <span
									class="value-bind" id="">${View_Search_Basic_InfoCMD.dop_state_govn}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Date of First Permission of CCH/Central Govt.</label> <span
									class="value-bind" id="">${View_Search_Basic_InfoCMD.dop_central_govn}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Name of the University affiliated</label> <span
									class="value-bind" id="">${View_Search_Basic_InfoCMD.name_of_uni_affilate}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Date of First affiliation of University</label> <span
									class="value-bind" id="">${View_Search_Basic_InfoCMD.doa_university}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Year of Establishment of the College</label> <span
									class="value-bind" id="">${View_Search_Basic_InfoCMD.year_of_establish_college}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Date of consent of affiliation from the
									University</label> <span class="value-bind" id="">${View_Search_Basic_InfoCMD.doc_affilation_university}</span>
							</div>
						</div>


					</div>
				</div>

				<div class="field-box">
					<form:form name="View_Search_Basic_Infoch"
						id="View_Search_Basic_Infoch"
						action="View_Search_Basic_InfochAction?${_csrf.parameterName}=${_csrf.token}"
						method="post" modelAttribute="View_Search_Basic_InfochCMD">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg">
									<h5 class="custom-title-tag">Head of Institution Details</h5>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none" id="no_headofinsti_data"></span>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Name of Principal / Dean / Director</label> <span
										class="value-bind" id="name_pdd">
										${View_Search_Basic_InfochCMD.name_of_principal}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>CCH Registration Number</label> <span class="value-bind"
										id="cch_registration">
										${View_Search_Basic_InfochCMD.cch_reg_no}</span>
								</div>
							</div>

							<div class="select-position custom-d-none">
								<select class="form-control" name="state_rn_head1"
									id="state_rn_head1">

									<option value="0" selected="selected">--Select State
										--</option>
									<c:forEach var="item" items="${getMedStateName}"
										varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>State Registration</label> <span class="value-bind"
										id="state_rn_head">
										${View_Search_Basic_InfochCMD.state_reg}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>State Registration Number</label> <span
										class="value-bind" id="state_registration_no">
										${View_Search_Basic_InfochCMD.state_reg_no}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Date of Birth</label> <span class="value-bind"
										id="hoidob_id"> ${View_Search_Basic_InfochCMD.dob}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Date of Joining as Principal / Director</label> <span
										class="value-bind" id="doj_id">
										${View_Search_Basic_InfochCMD.date_of_join_princi}</span>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-subtitle">
									<h5 class="custom-subtitle-tag">Number of Years of
										Experience</h5>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>As Professor</label> <span class="value-bind"
										id="hoiprofessor">
										${View_Search_Basic_InfochCMD.professor_exp}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>As Reader / Associate Professor</label> <span
										class="value-bind" id="hoireader">
										${View_Search_Basic_InfochCMD.reader_exp}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>As Lecturer / Assistant professor</label> <span
										class="value-bind" id="hoilecturer">
										${View_Search_Basic_InfochCMD.lecturer_exp}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>As Demonstrator/ Tutor </label> <span class="value-bind"
										id="hoidemonstrator">
										${View_Search_Basic_InfochCMD.demonstraror_exp}</span>
								</div>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="custom-subtitle">
										<h5 class="custom-subtitle-tag">Qualification Details</h5>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="landdetails_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Qualification Type</h6></th>
													<th><h6>Awarding Authority</h6></th>
													<th><h6>Passing Year</h6></th>
												</tr>

											</thead>
											<tbody id="">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p id="institution_type_quali1">M.S.</p>
													</td>
													<td>
														<p id="awarding_authority1">GU</p>
													</td>
													<td>
														<p id="awarding_authority1">2021</p>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</section>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-subtitle">
									<h5 class="custom-subtitle-tag">Head of Institution
										Contact Details</h5>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Address Line 1</label> <span class="value-bind"
										id="hoiper_add_line1label">
										${View_Search_Basic_InfochCMD.address_line1}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Address Line 2</label> <span class="value-bind"
										id="hoiper_add_line2label">
										${View_Search_Basic_InfochCMD.address_line2}</span>
								</div>
							</div>

							<div class="select-position custom-d-none">
								<select class="form-control" name="per_state_head_inst"
									id="per_state_head_inst">
									<!-- style="text-transform: uppercase" -->
									<option value="0" selected="selected">--Select State
										--</option>
									<c:forEach var="item" items="${getMedStateName}"
										varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>State</label> <span class="value-bind"
										id="hoiper_statelabel">
										${View_Search_Basic_InfochCMD.state_add_detai}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>City</label> <span class="value-bind" id="hoicity_label">${View_Search_Basic_InfochCMD.city}
									</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Pincode</label> <span class="value-bind"
										id="hoipincode_label">
										${View_Search_Basic_InfochCMD.pincode}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Office Phone No.</label> <span class="value-bind"
										id="hoi_office_phone_label">
										${View_Search_Basic_InfochCMD.office_phone_no}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Residence Phone No.</label> <span class="value-bind"
										id="hoi_residence_phone_label">
										${View_Search_Basic_InfochCMD.residence_phone_no}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Mobile No.</label> <span class="value-bind"
										id="hoi_mobile_label">
										${View_Search_Basic_InfochCMD.mobile_no}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Email ID</label> <span class="value-bind"
										id="hoi_emailid_label">
										${View_Search_Basic_InfochCMD.email_id}</span>
								</div>
							</div>
						</div>
					</form:form>
				</div>

				<section class="single-detail-block">
					<div class="field-box">
						<div class="row">

							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg">
									<h5 class="custom-title-tag">Nearest Airport Details</h5>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_connectivity_data"></span>
							</div>


							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table" id="near_air_table">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>
												<th><h6>Nearest Airport Name</h6></th>
												<th><h6>Approx Distance(km)</h6></th>

											</tr>
											<!-- end table row-->
										</thead>
										<tbody id="doc_Tbbody">
											<c:forEach var="j" items="${View_Search_Basic_Infoch2}"
												varStatus="num">
												<tr id="tr_id_doc">
													<td class="sr-no">
														<p></p>
													</td>

													<td>
														<p id="nearest_airport">${View_Search_Basic_Infoch2[0].nearest_airport_name}</p>
													</td>

													<td>
														<p id="airport_distance">${View_Search_Basic_Infoch2[0].approx_distance}</p>
													</td>
												</tr>
												<!-- end table row -->
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</section>

				<section class="single-detail-block">
					<div class="field-box">
						<div class="row">

							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg">
									<h5 class="custom-title-tag">Nearest Railway Station</h5>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none" id="no_railway_data"></span>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table" id="near_railway_table">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>

												<th><h6>Nearest Railway Station Name</h6></th>
												<th><h6>Approx Distance(km)</h6></th>
											</tr>
										</thead>
										<tbody id="doc_Tbbody2">
											<c:forEach var="j" items="${View_Search_Basic_Infoch2}"
												varStatus="num">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>

													<td>
														<p id="nearest_railway">${View_Search_Basic_Infoch2[0].nearest_railway_station}</p>
													</td>

													<td>
														<p id="railway_distance">${View_Search_Basic_Infoch2[0].approx_dist_railway}</p>
													</td>
												</tr>
												<!-- end table row -->
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</section>

				<section class="single-detail-block">
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg">
									<h5 class="custom-title-tag">Other Relevant Institution
										Detail within the radius of 50km</h5>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none" id="no_relevant_data"></span>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table" id="near_otherinst_table">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>
												<th><h6>Nearest Institutions Name</h6></th>
												<th><h6>City</h6></th>
											</tr>
										</thead>
										<tbody id="doc_Tbbody3">
											<c:forEach var="j" items="${View_Search_Basic_Infoch2}"
												varStatus="num">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>

													<td>
														<p id="nearest_institutions">${View_Search_Basic_Infoch2[0].nearest_inst_name}</p>
													</td>

													<td>
														<p id="inst_city">${View_Search_Basic_Infoch2[0].city}</p>
													</td>
												</tr>
											</c:forEach>
											<!-- end table row -->
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</section>

				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg">
								<h5 class="custom-title-tag">Police Station Details</h5>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Name of Nearest Police Station</label> <span
									class="value-bind" id="nearest_police_lable">${View_Search_Basic_Infoch3[0].name_of_nearest_police_station}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Address Line 1</label> <span class="value-bind"
									id="police_per_add_line1label">
									${View_Search_Basic_Infoch3[0].addr_line1}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Address Line 2</label> <span class="value-bind"
									id="police_per_add_line2label">
									${View_Search_Basic_Infoch3[0].addr_line2}</span>
							</div>
						</div>

						<div class="select-position custom-d-none">
							<select class="form-control" name="police_per_state"
								id="police_per_state">
								<!-- style="text-transform: uppercase" -->
								<option value="0" selected="selected">--Select State --</option>
								<c:forEach var="item" items="${getMedStateName}" varStatus="num">
									<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
							</select>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>State</label> <span class="value-bind"
									id="police_per_statelabel">
									${View_Search_Basic_Infoch3[0].state}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>City</label> <span class="value-bind"
									id="police_city_label">
									${View_Search_Basic_Infoch3[0].city}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Pincode</label> <span class="value-bind"
									id="police_pin_label">
									${View_Search_Basic_Infoch3[0].pincode}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Phone No.</label> <span class="value-bind"
									id="police_phone_no_label">
									${View_Search_Basic_Infoch3[0].phone_number}</span>
							</div>
						</div>

					</div>
				</div>



				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg">
								<h5 class="custom-title-tag">Last 5 years permission with
									Intake Capacity</h5>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<span class="value-bind no-data d-none" id="no_intakecap_data"></span>
						</div>
						<section class="single-detail-block">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table" id="intakecap_table">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>

												<th><h6>Year</h6></th>
												<th><h6>Select</h6></th>
												<th><h6>Intake Capacity</h6></th>
											</tr>
										</thead>
										<tbody id="">
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p id="intake_year">UG- 2020-2021</p>
												</td>

												<td>
													<p id="intake_select">year</p>
												</td>

												<td>
													<p id="intake_capacity">5</p>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</section>
					</div>
				</div>


				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg">
								<h5 class="custom-title-tag">UG Course intake capacity</h5>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<span class="value-bind no-data d-none" id="no_ugintakecap_data"></span>
						</div>
						<section class="single-detail-block">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table" id="intakecap_table">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>

												<th><h6>Course</h6></th>
												<th><h6>Intake Capacity</h6></th>
											</tr>
										</thead>
										<tbody id="">
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p id="ug_course">UG - Homeopathy</p>
												</td>
												<td>
													<p id="ugintake_capacity">6</p>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</section>
					</div>
				</div>

				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg">
								<h5 class="custom-title-tag">PG Course intake capacity</h5>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<span class="value-bind no-data d-none" id="no_pgintakecap_data"></span>
						</div>
						<section class="single-detail-block">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table" id="intakecap_table">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>

												<th><h6>Course</h6></th>
												<th><h6>Intake Capacity</h6></th>
											</tr>
										</thead>
										<tbody id="">
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>M.D. (Hom.) Homoeopathic Philosophy</p>
												</td>

												<td>
													<p id="pgintake_capacity">5</p>
												</td>
											</tr>

											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>M.D. (Hom.) Homoeopathic Materia Medica</p>
												</td>
												<td>
													<p id="pgintake_capacity">5</p>

												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>M.D. (Hom.) Repertory</p>
												</td>

												<td>
													<p id="pgintake_capacity">6</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>M.D. (Hom.) Homoeopathic Pharmacy</p>
												</td>
												<td>
													<p id="pgintake_capacity">6</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>M.D. (Hom.) Practice of Medicine</p>
												</td>
												<td>
													<p id="pgintake_capacity">6</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>M.D. (Hom.) Pediatrics</p>
												</td>
												<td>
													<p id="pgintake_capacity">6</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>M.D. (Hom.) Psychiatry</p>
												</td>
												<td>
													<p id="pgintake_capacity">6</p>
												</td>
											</tr>
											<!-- end table row -->
										</tbody>
									</table>
								</div>
							</div>
						</section>
					</div>
				</div>

				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg">
								<h5 class="custom-title-tag">Details of Land</h5>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<span class="value-bind no-data d-none" id="no_landdetail_data"></span>
						</div>
						<section class="single-detail-block">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table" id="landdetails_table">
										<thead>
											<tr>
												<th colspan="3"><h6>Land Information</h6></th>
											</tr>
										</thead>
										<tbody id="">
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Total area of land with Society/Trust/ Govt. College
														(in acres)</p>
												</td>
												<td>
													<p id="stgarea">3</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Ownership of land</p>
												</td>
												<td>
													<p id="">6</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Land in the name of</p>
												</td>
												<td>
													<p id="">6</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Distribution of entire land</p>
												</td>
												<td>
													<p id="">8</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Land availability with the Society/Trust is entirely
														for concerned College and attached Hospital or for no
														other Institute</p>
												</td>
												<td>
													<p id="">8</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Does the same society/trust run any other
														institutions/colleges</p>
												</td>
												<td>
													<p id="">8</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Whether College and Hospital are constructed in
														separate buildings</p>
												</td>
												<td>
													<p id="">8</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Total area of land allotted to the college (in
														acres)</p>
												</td>
												<td>
													<p id="">8</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Total area of land allotted to the hospital (in
														acres)</p>
												</td>
												<td>
													<p id="">8</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Total area of land allotted to the hostels (in
														acres)</p>
												</td>
												<td>
													<p id="">5</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Whether the College and Hospital building have ever
														been shifted to some other place</p>
												</td>
												<td>
													<p id="">5</p>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Whether the management/society of college (in case
														of private College) has ever changed</p>
												</td>
												<td>
													<p id="">5</p>
												</td>
											</tr>
											<!-- end table row -->
										</tbody>
									</table>
								</div>
							</div>
						</section>
					</div>
				</div>


				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg">
								<h5 class="custom-title-tag">Undertakings and Reports</h5>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<span class="value-bind no-data d-none" id="no_uploaded_doc"></span>
						</div>
						<section class="single-detail-block">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table" id="table_uploaded_doc">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>
												<th><h6>Document Name</h6></th>
												<th><h6>View</h6></th>

											</tr>
										</thead>
										<tbody id="">
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Registration certificate of society/trust</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>A copy of the society/trust deed</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Undertaking letter to furnish Bank Guarantee</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>NOC document from the State Govt.</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Consent of affiliation document from concern
														University</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Last 5 year AYUSH Permission/Denial letter for UG
														and/or PG course</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>If admitted through court, Upload court order for
														last 5 year</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Land documents of the College</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Building plan (approved by the competent authority)
														of the college Document</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Area statement document certified by Architect</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Undertaking that Institution will not admit the
														students without permission from Government of India</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Audited Balance Sheet for last 3 years</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Annual Report</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Undertaking that selection of students for UG and PG
														Courses will be made only on academic merit as per CCH
														regulation</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Any additional document</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a href="#"
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</section>
					</div>
				</div>

				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg">
								<h5 class="custom-title-tag">Declaration</h5>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-choose-one">
								<div class="input-style-form-check_block check-multi-list">
									<label class="check-list">I, <b id="basicinfodec_name">Dr.
											Name</b> solemnly confirm that if any information provided by me
										in proforma and Annexures found false, I shall be held
										responsible in the matter. I shall have no objection if any
										legal action is taken by the CCH against me.
									</label>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="field-box">
					<div class="row justify-content-center">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg">
								<h5 class="custom-title-tag">Remarks</h5>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-12">
							<div class="input-style-1">
								<textarea placeholder="Enter valuable remarks" rows="5"></textarea>
							</div>
							<div class="btn-bottom">
								<ul class="buttons-group mainbtn">
									<li><a href="#"
										class="main-btn info-btn btn-hover btnsubmit">Submit</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				
				<!-- ===========================
							Basic Information End
						=========================== -->

			</div>
		</div>
	</div>
</section>

<!-- hemburgermenu js start -->
<script src="assets/vendor/hamburger-menu/hemburgermenu.js" type="text/javascript"></script>
<!-- hemburgermenu css end -->

<!-- Modal satrt -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="exampleModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->



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
					<c:if test="${role == 'NCH'}"> 
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="hamburger-menu">
						<div class="bar" title="Click here for menu">
						</div>
						<div class="hm-title"><h4 class="hm-title-text">Institution Information view form report</h4></div>
					</div>

					<nav class="mobile-menu">
						<h5 class="hm-menu-title">Form Reports Links</h5>
						<ul>
							<li><a id="basics_information_view">Institution Information</a></li>
<!-- 							<li class="has-children">About <span class="icon-arrow"></span> -->
<!-- 								<ul class="children"> -->
<!-- 									<li><a href="submenu1.html">Submenu #1</a></li> -->
<!-- 									<li><a href="submenu2.html">Submenu #2</a></li> -->
<!-- 									<li><a href="submenu3.html">Submenu #3</a></li> -->
<!-- 								</ul> -->
<!-- 							</li> -->



<!-- 							<li><a class="viewData">College Infrastructure</a> -->
<%-- 							<input type='hidden' id="viewId${getInstname[0].id}" value="${getInstname[0].id}"></li> --%>
<!-- 							<li><a href="college_department_view">College Department</a></li> -->
<!-- 							<li><a href="college_financial_view">College Financial</a></li> -->
<!-- 							<li><a href="student_details_view">Student Details</a></li> -->
<!-- 							<li><a href="college_staff_info_view">College Staff Information</a></li> -->
<!-- 							<li><a href="college_staff_list_view">College Staff List</a></li> -->
<!-- 							<li><a href="department_equipments_view">Department Equipments</a></li> -->
<!-- 							<li><a href="hospital_infrastructure_view">Hospital Infrastucture</a></li> -->
<!-- 							<li><a href="hospital_ipdopd_view">Hospital IPD OPD</a></li> -->
<!-- 							<li><a href="otherhospital_detail_view">Other Hospital Details</a></li> -->
<!-- 							<li><a href="hospital_staffdetails_view">Hospital Staff Details</a></li> -->
<!-- 							<li><a href="hospital_staff_list_view">Hospital Staff List</a></li> -->
<!-- 							<li><a href="hospital_equipments_view">Hospital Equipments</a></li> -->
<!-- 							<li><a href="declaration_view">Declaration</a></li> -->

							<li><a id="college_infrastructure_view">College Infrastructure</a></li>
							<li><a id="college_department_view">College Department</a></li>
							<li><a id="college_financial_view">College Financial</a></li>
							<li><a id="student_details_view_page">Student Details</a></li>
							<li><a id="college_staff_info_view">College Staff Information</a></li>
							<li><a id="college_staff_list_view">College Staff List</a></li>
							<li><a id="department_equipments_view">Department Equipments</a></li>
							<li><a id="hospital_infrastructure_view">Hospital Infrastucture</a></li>
							<li><a id="hospital_ipdopd_view">Hospital IPD OPD</a></li>
							<li><a id="otherhospital_detail_view">Other Hospital Details</a></li>
							<li><a id="hospital_staffdetails_view">Hospital Staff Details</a></li>
							<li><a id="hospital_staff_list_view">Hospital Staff List</a></li>
							<li><a id="hospital_equipments_view">Hospital Equipments</a></li>
							<li><a id="declaration_view">Declaration</a></li>
						</ul>
					</nav>
				</div>
			</div>
			</c:if>
			<!-- humburger menu for collage regulation - part-1 end -->

			<div class="card-style mb-30">
			<form:form name="View_Search_Basic_Info"
						id="View_Search_Basic_Info"
						action="View_Search_Basic_InfoAction?${_csrf.parameterName}=${_csrf.token}"
						method="post" modelAttribute="View_Search_Basic_InfoCMD">
				<!-- ===========================
							Basic Information Start
						=========================== -->
				<div class="field-box">
					
						<div class="row">
						<input type="hidden" id="hid_college_infrastructure_remark" name="hid_college_infrastructure_remark" value="0">
						<input type="hidden" id="institute_id" name="institute_id" value="0">
						<input type="hidden" id="main_view_id" name="main_view_id" value="0">
						
						
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Institution Details</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#instdetModal"><i class="lni lni-pencil-alt"></i></a>
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
										${getAllPersdetailsReport[0].address_line1}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Address Line 2</label> <span class="value-bind"
										id="per_add_line2label">
										${getAllPersdetailsReport[0].address_line2}</span>
								</div>
							</div>
<!-- 							<div class="form-group stepform-group custom-d-none"> -->
<!-- 								<select name="per_state" id="per_state" class="form-control"> -->
<!-- 									<option value="0" selected="selected">--Select State -->
<!-- 										--</option> -->
<%-- 									<c:forEach var="item" items="${getMedStateName}" --%>
<%-- 										varStatus="num"> --%>
<%-- 										<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 									</c:forEach> --%>
<!-- 								</select> -->
<!-- 							</div> -->


							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>State</label> <span class="value-bind"
										id="per_statelabel">
										${getAllPersdetailsReport[0].state_name}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>District</label> <span class="value-bind"
										id="per_districtlabel">
										${getAllPersdetailsReport[0].district_name}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>City</label> <span class="value-bind" id="city_label">${getAllPersdetailsReport[0].city}
									</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Pincode</label> <span class="value-bind"
										id="per_pincode_label">
										${getAllPersdetailsReport[0].pincode}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>College Phone No.</label> <span class="value-bind"
										id="clgphone__label">${getAllPersdetailsReport[0].college_phone_code} 
										${getAllPersdetailsReport[0].college_phone_no}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Hospital Phone No.</label> <span class="value-bind"
										id="hosphone__label">${getAllPersdetailsReport[0].hospital_phone_code} 
										${getAllPersdetailsReport[0].hospital_phone_no	}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Mobile No.</label> <span class="value-bind"
										id="mobile_no_addr__label">
										${getAllPersdetailsReport[0].mobile_no}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Fax No.</label> <span class="value-bind"
										id="fax_no_label"> ${getAllPersdetailsReport[0].fax_code} ${getAllPersdetailsReport[0].fax_no}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Email ID</label> <span class="value-bind"
										id="emailid_label">
										${getAllPersdetailsReport[0].email_id}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Website</label> <span class="value-bind"
										id="website_label">
										${getAllPersdetailsReport[0].website}</span>
								</div>
							</div>

						</div>
					
				</div>

				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
								<h5 class="custom-title-tag">Management Details</h5>
								<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#mngtModal"><i class="lni lni-pencil-alt"></i></a>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Institution Type</label> <span class="value-bind"
									id="mang_inst_type_label"></span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Managing Body Name</label> <span class="value-bind"
									id="mang_managing_body_label">${getAllPersdetailsReport[0].name_of_managing_body}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Management Contact Name</label> <span class="value-bind"
									id="mang_managing_contact_label">${getAllPersdetailsReport[0].name_of_management_contact}</span>
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
									${getAllPersdetailsReport[0].mngt_address_line1}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Address Line 2</label> <span class="value-bind"
									id="mang_per_add_line2label">
									${getAllPersdetailsReport[0].mngt_address_line2}</span>
							</div>
						</div>

<!-- 						<div class="select-position custom-d-none"> -->
<!-- 							<select class="form-control" name="mang_per_state" -->
<!-- 								id="mang_per_state"> -->
<!-- 								style="text-transform: uppercase" -->
<!-- 								<option value="0" selected="selected">--Select State --</option> -->
<%-- 								<c:forEach var="item" items="${getMedStateName}" varStatus="num"> --%>
<%-- 									<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</div> -->

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>State</label> <span class="value-bind"
									id="mang_per_statelabel">
									${getAllPersdetailsReport[0].state_name}</span>
							</div>
						</div>
						
						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>District</label> <span class="value-bind"
									id="mang_per_districtlabel">
									${getAllPersdetailsReport[0].district_name}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>City</label> <span class="value-bind"
									id="mang_city_label">
									${getAllPersdetailsReport[0].mngt_city}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Office Phone No.</label> <span class="value-bind"
									id="mang_office_phone_label"> ${getAllPersdetailsReport[0].mang_office_code} 
									${getAllPersdetailsReport[0].mngt_office_phone_no}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Residence Phone No.</label> <span class="value-bind"
									id="mang_residence_phone_label"> ${getAllPersdetailsReport[0].mang_residence_code} 
									${getAllPersdetailsReport[0].mngt_residence_phone_no}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Mobile No.</label> <span class="value-bind"
									id="mang_mobile_label">
									${getAllPersdetailsReport[0].mngt_mobile_no}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Email ID</label> <span class="value-bind"
									id="mang_emailid_label">
									${getAllPersdetailsReport[0].mngt_email_id}</span>
							</div>
						</div>

					</div>
				</div>

				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
								<h5 class="custom-title-tag">Society/Trust/Government
									Information</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#societyModal"><i class="lni lni-pencil-alt"></i></a>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Name of Society/Trust/Government</label> <span
									class="value-bind" id="">${getAllPersdetailsReport[0].name_of_society}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Society/Trust Registration No.</label>
								${getAllPersdetailsReport[0].society_reg_no}<span
									class="value-bind" id=""></span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Year of Establishment of the Society/Trust</label>
								${getAllPersdetailsReport[0].year_of_establish_society}<span
									class="value-bind" id=""></span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Date of the permission of the State Govt.</label> <span
									class="value-bind" id="">${getAllPersdetailsReport[0].date_of_permission}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Date of First Permission of CCH/NCH/Central Govt.</label> <span
									class="value-bind" id="">${getAllPersdetailsReport[0].date_of_central}</span>
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
									class="value-bind" id="">${getAllPersdetailsReport[0].date_of_first_affilia}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Year of Establishment of the College</label> <span
									class="value-bind" id="">${getAllPersdetailsReport[0].year_of_establish_college}</span>
							</div>
						</div>

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Date of consent of affiliation from the
									University</label> <span class="value-bind" id="">${getAllPersdetailsReport[0].date_of_consent_affilia}</span>
							</div>
						</div>
						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>Date of last continuous affiliation University</label> <span class="value-bind" id="">${getAllPersdetailsReport[0].doc_last_aff_universityd}</span>
							</div>
						</div>


					</div>
				</div>

				<div class="field-box">
					
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Registration Number with Registering authority Details</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#regauthModal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none" id="no_headofinsti_data"></span>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Teacher Code</label> <span
										class="value-bind" id="name_pdd">
										${getAllinfo_inst_dtlReport[0].teacher_code}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Name of Principal / Dean / Director</label> <span
										class="value-bind" id="name_pdd">
										${getAllinfo_inst_dtlReport[0].name_of_principal}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>CCH/NCH Registration Number</label> <span class="value-bind"
										id="cch_registration">
										${getAllinfo_inst_dtlReport[0].cch_reg_no}</span>
								</div>
							</div>

<!-- 							<div class="select-position custom-d-none"> -->
<!-- 								<select class="form-control" name="state_rn_head1" -->
<!-- 									id="state_rn_head1"> -->

<!-- 									<option value="0" selected="selected">--Select State -->
<!-- 										--</option> -->
<%-- 									<c:forEach var="item" items="${getMedStateName}" --%>
<%-- 										varStatus="num"> --%>
<%-- 										<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 									</c:forEach> --%>
<!-- 								</select> -->
<!-- 							</div> -->
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>State Registration</label> <span class="value-bind"
										id="state_rn_head">
										${getAllinfo_inst_dtlReport[0].state_name}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>State Registration Number</label> <span
										class="value-bind" id="state_registration_no">
										${getAllinfo_inst_dtlReport[0].state_reg_no}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Date of Birth</label> <span class="value-bind"
										id="hoidob_id"> ${getAllinfo_inst_dtlReport[0].date_of_birth}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Date of Joining as Principal / Director</label> <span
										class="value-bind" id="doj_id">
										${getAllinfo_inst_dtlReport[0].date_of_join}</span>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-subtitle-tag">Number of Years of
										Experience</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#expModal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>As Professor</label> <span class="value-bind"
										id="hoiprofessor">
										${getAllinfo_inst_dtlReport[0].professor_exp}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>As Reader / Associate Professor</label> <span
										class="value-bind" id="hoireader">
										${getAllinfo_inst_dtlReport[0].reader_exp}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>As Lecturer / Assistant professor</label> <span
										class="value-bind" id="hoilecturer">
										${getAllinfo_inst_dtlReport[0].lecturer_exp}</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>As Demonstrator/ Tutor </label> <span class="value-bind"
										id="hoidemonstrator">
										${getAllinfo_inst_dtlReport[0].demonstraror_exp}</span>
								</div>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
										<h5 class="custom-subtitle-tag">Qualification Details</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#qualModal"><i class="lni lni-pencil-alt"></i></a>
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
											<c:forEach var="j" items="${getAllinfo_quali_instReport}"
												varStatus="num">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p id="institution_type_quali1">${j.type_of_degree}</p>
													</td>
													<td>
														<p id="awarding_authority1">${j.awarding_authority}</p>
													</td>
													<td>
														<p id="awarding_authority1">${j.passing_year}</p>
													</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</section>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-subtitle-tag">Head of Institution
										Contact Details</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#headofinstModal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Address Line 1</label> <span class="value-bind"
										id="hoiper_add_line1label">
										${getAllinfo_inst_dtlReport[0].address_line1}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Address Line 2</label> <span class="value-bind"
										id="hoiper_add_line2label">
										${getAllinfo_inst_dtlReport[0].address_line2}</span>
								</div>
							</div>

<!-- 							<div class="select-position custom-d-none"> -->
<!-- 								<select class="form-control" name="per_state_head_inst" -->
<!-- 									id="per_state_head_inst"> -->
<!-- 									style="text-transform: uppercase" -->
<!-- 									<option value="0" selected="selected">--Select State -->
<!-- 										--</option> -->
<%-- 									<c:forEach var="item" items="${getMedStateName}" --%>
<%-- 										varStatus="num"> --%>
<%-- 										<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 									</c:forEach> --%>
<!-- 								</select> -->
<!-- 							</div> -->

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>State</label> <span class="value-bind"
										id="hoiper_statelabel">
										${getAllinfo_inst_dtlReport[0].state_name}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>City</label> <span class="value-bind" id="hoicity_label">${getAllinfo_inst_dtlReport[0].city}
									</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Pincode</label> <span class="value-bind"
										id="hoipincode_label">
										${getAllinfo_inst_dtlReport[0].pincode}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Office Phone No.</label> <span class="value-bind"
										id="hoi_office_phone_label">
										${getAllinfo_inst_dtlReport[0].office_phone_no}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Residence Phone No.</label> <span class="value-bind"
										id="hoi_residence_phone_label">
										${getAllinfo_inst_dtlReport[0].residence_phone_no}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Mobile No.</label> <span class="value-bind"
										id="hoi_mobile_label">
										${getAllinfo_inst_dtlReport[0].mobile_no}</span>
								</div>
							</div>

							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Email ID</label> <span class="value-bind"
										id="hoi_emailid_label">
										${getAllinfo_inst_dtlReport[0].email_id}</span>
								</div>
							</div>
						</div>
					
				</div>

				<section class="single-detail-block">
					<div class="field-box">
						<div class="row">

							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Nearest Airport Details</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#airportModal"><i class="lni lni-pencil-alt"></i></a>
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
														<p id="nearest_airport">${j.nearest_airport_name}</p>
													</td>

													<td>
														<p id="airport_distance">${j.approx_distance}</p>
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
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Nearest Railway Station</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#railwayModal"><i class="lni lni-pencil-alt"></i></a>
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
														<p id="nearest_railway">${j.nearest_railway_station}</p>
													</td>

													<td>
														<p id="railway_distance">${j.approx_dist_railway}</p>
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
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Other Relevant Institution
										Detail within the radius of 50km</h5>
										<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#radiusModal"><i class="lni lni-pencil-alt"></i></a>
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
												<th><h6>State</h6></th>
												<th><h6>District</h6></th>
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
														<p id="nearest_institutions">${j.nearest_inst_name}</p>
													</td>
													<td>
														<p id="inst_state">${j.state_name}</p>
													</td>
													<td>
														<p id="inst_district">${j.district_name}</p>
													</td>

													<td>
														<p id="inst_city">${j.city}</p>
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
							<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
								<h5 class="custom-title-tag">Police Station Details</h5>
								<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#policeModal"><i class="lni lni-pencil-alt"></i></a>
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

<!-- 						<div class="select-position custom-d-none"> -->
<!-- 							<select class="form-control" name="police_per_state" -->
<!-- 								id="police_per_state"> -->
<!-- 								style="text-transform: uppercase" -->
<!-- 								<option value="0" selected="selected">--Select State --</option> -->
<%-- 								<c:forEach var="item" items="${getMedStateName}" varStatus="num"> --%>
<%-- 									<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</div> -->

						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>State</label> <span class="value-bind"
									id="police_per_statelabel">
									${View_Search_Basic_Infoch3[0].state_name}</span>
							</div>
						</div>
						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>District</label> <span class="value-bind"
									id="police_per_districtlabel">
									${View_Search_Basic_Infoch3[0].district_name}</span>
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
								<label>Landline Number</label> <span class="value-bind"
									id="police_landline_no_label">
									${View_Search_Basic_Infoch3[0].phone_number}</span>
							</div>
						</div>
						<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
							<div class="custom-data-value">
								<label>S.P. Office Number/Landline Number</label> <span class="value-bind"
									id="police_sp_phone_no_label">
									${View_Search_Basic_Infoch3[0].sp_phone_number}</span>
							</div>
						</div>

					</div>
				</div>



				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
								<h5 class="custom-title-tag">Last 10 years permission with UG
									Intake Capacity</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#ugintakeModal"><i class="lni lni-pencil-alt"></i></a>
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
												<th><h6>Permission</h6></th>
												<th><h6>Intake Capacity</h6></th>
												<th><h6>Admitted Seat</h6></th>
												<th><h6>Permitted Seat</h6></th>
												<th><h6>Sanctioned Seat</h6></th>
												<th><h6>Total Students admitted with Govt Quota</h6></th>
												<th><h6>Total Students admitted with Management Quota</h6></th>
												<th><h6>Students admitted by Court order </h6></th>
												<th><h6>Name of the Last Student Admitted</h6></th>
												<th><h6>Date of Admission of the last Student</h6></th>
												<th><h6>Total Final Year students appeared for exams</h6></th>
												<th><h6>Total Final Year students passed out in exams</h6></th>
											</tr>
										</thead>
										<tbody id="">
										<c:forEach var="item" items="${getAllinfo_intake_capacity_Report}"
										varStatus="num">
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p id="ug_intake_year">${item.year}</p>
												</td>

												<td>
												<c:if test="${item.permission == '1'}">
															<p id="ug_permission_label">Yes</p>
														</c:if>
														<c:if test="${item.permission == '2'}">
															<p id="ug_permission_label">No</p>
														</c:if>
												</td>

												<td>
													<p id="ug_intake_capacity">${item.intake_capacity}</p>
												</td>
												<td>
													<p id="ug_admitted_seat">${item.amitted_seat}</p>
												</td>
												<td>
													<p id="ug_permitted_seat">${item.permitted_seat}</p>
												</td>
												<td>
													<p id="ug_sanctioned_seat">${item.sanctioned_seat}</p>
												</td>
												<td>
													<p id="ug_govt_quota">${item.govt_quota_ug}</p>
												</td>
												<td>
													<p id="ug_mang_quota">${item.mang_quota_ug}</p>
												</td>
												<td>
												<c:if test="${item.court_order == '1'}">
													<p id="ug_court_order_label1">Yes</p>
												</c:if>
												<c:if test="${item.court_order == '2'}">
													<p id="ug_court_order_label2">No</p>
												</c:if>
												</td>
												<td>
													<p id="ug_last_student">${item.last_student}</p>
												</td>
												<td>
													<p id="ug_last_stu_add_date">${item.last_stu_add_datel}</p>
												</td>
												<td>
													<p id="ug_appeared_stu">${item.appeared_stu_ug}</p>
												</td>
												<td>
													<p id="ug_passed_stu">${item.passed_stu_ug}</p>
												</td>
											</tr>
											</c:forEach>
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
							<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
								<h5 class="custom-title-tag">Last 10 years permission with PG
									Intake Capacity</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#pgintakeModal"><i class="lni lni-pencil-alt"></i></a>
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
												<th><h6>Permission</h6></th>
												<th><h6>Intake Capacity</h6></th>
												<th><h6>Admitted Seat</h6></th>
												<th><h6>Permitted Seat</h6></th>
												<th><h6>Sanctioned Seat</h6></th>
												<th><h6>Total Students admitted with Govt Quota</h6></th>
												<th><h6>Total Students admitted with Management Quota</h6></th>
												<th><h6>Students admitted by Court order </h6></th>
												<th><h6>Name of the Last Student Admitted</h6></th>
												<th><h6>Date of Admission of the last Student</h6></th>
												<th><h6>Total Final Year students appeared for exams</h6></th>
												<th><h6>Total Final Year students passed out in exams</h6></th>
											</tr>
										</thead>
										<tbody id="">
										<c:forEach var="item" items="${getAllinfo_intake_capacity_for_PG_Report}"
										varStatus="num">
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p id="pg_intake_year">${item.year}</p>
												</td>

												<td>
												<c:if test="${item.permission == '1'}">
													<p id="pg_permission_label1">Yes</p>
												</c:if>
												<c:if test="${item.permission == '2'}">
													<p id="pg_permission_label2">No</p>
												</c:if>
												</td>

												<td>
													<p id="pg_intake_capacity">${item.intake_capacity_pg}</p>
												</td>
												<td>
													<p id="pg_admitted_seat">${item.admitted_seat}</p>
												</td>
												<td>
													<p id="pg_permitted_seat">${item.permitted_seat}</p>
												</td>
												<td>
													<p id="pg_sanctioned_seat">${item.sanction_seat}</p>
												</td>
												<td>
													<p id="pg_govt_quota">${item.student_govt_quota}</p>
												</td>
												<td>
													<p id="pg_mang_quota">${item.student_mgmt_quota}</p>
												</td>
												<td>
												<c:if test="${item.admitted_court_order == '1'}">
													<p id="pg_court_order_label1">Yes</p>
												</c:if>
												<c:if test="${item.admitted_court_order == '2'}">
													<p id="pg_court_order_label2">No</p>
												</c:if>
												</td>
												<td>
													<p id="pg_last_student">${item.last_stu_admitted}</p>
												</td>
												<td>
													<p id="pg_last_stu_add_date">${item.date_stu_admittedl}</p>
												</td>
												<td>
													<p id="pg_appeared_stu">${item.total_stu_app_exam}</p>
												</td>
												<td>
													<p id="pg_passed_stu">${item.total_stu_pass_exam}</p>
												</td>
											</tr>
											</c:forEach>
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
							<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
								<h5 class="custom-title-tag">UG Course intake capacity</h5>
								<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#ugcourseintakeModal"><i class="lni lni-pencil-alt"></i></a>
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

												<th><h6>Course (Subject wise)</h6></th>
												<th><h6>Intake Capacity</h6></th>
											</tr>
										</thead>
										<tbody id="">
										<c:forEach var="item" items="${getAllinfo_UG_intake_capacity_Report}"
										varStatus="num">
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p id="ug_course">${item.course_name}</p>
												</td>
												<td>
													<p id="ugintake_capacity">${item.intake_capacity}</p>
												</td>
											</tr>
											</c:forEach>
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
							<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
								<h5 class="custom-title-tag">PG Course intake capacity</h5>
								<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#ugcourseintakeModal"><i class="lni lni-pencil-alt"></i></a>
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

												<th><h6>Course (Subject wise)</h6></th>
												<th><h6>Intake Capacity</h6></th>
											</tr>
										</thead>
										<tbody id="">
										<c:forEach var="item" items="${getAllinfo_PG_intake_capacity_Report}"
										varStatus="num">
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>${item.course_name}</p>
												</td>

												<td>
													<p id="pgintake_capacity">${item.intake_capacity}</p>
												</td>
											</tr>
											<!-- end table row -->
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</section>
					<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
						<div class="custom-data-value">
							<label>Whether college has intimated the students regarding the admission under court order</label> <span class="value-bind"
								id="intimatedcheck_label"></span>
						</div>
					</div>
					<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12" id="upload_undertakingcheck_div">
						<div class="custom-data-value">
							<label>Undertaking has been taken from students (If admitted though court order)</label> <span class="value-bind"
								id="undertakingcheck_label"></span>
						</div>
					</div>
					</div>
				</div>
				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
								<h5 class="custom-title-tag">Details of Land</h5>
								<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#landModal"><i class="lni lni-pencil-alt"></i></a>
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
													<p id="stgarea">${getAllinfo_dtl_landReport[0].total_area_of_land_society}</p>
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
												<c:if test="${getAllinfo_dtl_landReport[0].ownership_of_land == '1'}">

															<p id="ownership_of_land_label1">Government</p>
														</c:if>
														<c:if test="${getAllinfo_dtl_landReport[0].ownership_of_land == '2'}">
															<p id="ownership_of_land_label2">Private</p>
														</c:if>
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
													<c:if test="${getAllinfo_dtl_landReport[0].land_in_the_name == '1'}">

														<p id="land_in_the_name_label">Society/Trust</p>
													</c:if> <c:if
														test="${getAllinfo_dtl_landReport[0].land_in_the_name == '2'}">
														<p id="land_in_the_name_label">President/Secretary</p>
													</c:if> <c:if
														test="${getAllinfo_dtl_landReport[0].land_in_the_name == '3'}">
														<p id="land_in_the_name_label">Government</p>
													</c:if>
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
												<c:if test="${getAllinfo_dtl_landReport[0].distribution_of_entire_land == '1'}">

															<p id="distribution_of_entire_land_label">One Plot</p>
														</c:if>
														<c:if test="${getAllinfo_dtl_landReport[0].distribution_of_entire_land == '2'}">
															<p id="distribution_of_entire_land_label">More than one</p>
														</c:if>
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
													<c:if test="${getAllinfo_dtl_landReport[0].land_availability_with_society == '1'}">

														<p id="land_availability_with_society_label">Yes</p>
													</c:if> <c:if
														test="${getAllinfo_dtl_landReport[0].land_availability_with_society == '2'}">
														<p id="land_availability_with_society_label">No</p>
													</c:if> <c:if
														test="${getAllinfo_dtl_landReport[0].land_availability_with_society == '3'}">
														<p id="land_availability_with_society_label">Not Applicable</p>
													</c:if>
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
												<c:if test="${getAllinfo_dtl_landReport[0].same_society_trust == '1'}">

														<p id="same_society_trust_label">Yes</p>
													</c:if> <c:if
														test="${getAllinfo_dtl_landReport[0].same_society_trust == '2'}">
														<p id="same_society_trust_label">No</p>
													</c:if> <c:if
														test="${getAllinfo_dtl_landReport[0].same_society_trust == '3'}">
														<p id="same_society_trust_label">Not Applicable</p>
													</c:if>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Whether College and Hospital are constructed</p>
												</td>
												<td>
												<c:if test="${getAllinfo_dtl_landReport[0].college_and_hospital_constructed == '1'}">

															<p id="college_and_hospital_constructed_label">Yes</p>
														</c:if>
														<c:if test="${getAllinfo_dtl_landReport[0].college_and_hospital_constructed == '2'}">
															<p id="college_and_hospital_constructed_label">No</p>
														</c:if>
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
														<c:if test="${getAllinfo_dtl_landReport[0].college_and_hospital_building == '1'}">

															<p id="college_and_hospital_building_label">Yes</p>
														</c:if>
														<c:if test="${getAllinfo_dtl_landReport[0].college_and_hospital_building == '2'}">
															<p id="college_and_hospital_building_label">No</p>
														</c:if>
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
													<p id="">${getAllinfo_dtl_landReport[0].total_area_of_land_alloted_clg}</p>
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
													<p id="">${getAllinfo_dtl_landReport[0].total_area_of_land_alloted_clg_hospital}</p>
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
													<p id="">${getAllinfo_dtl_landReport[0].total_area_of_land_alloted_clg_hostels}</p>
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
												<c:if test="${getAllinfo_dtl_landReport[0].college_and_hospital_building_shifted == '1'}">

															<p id="college_and_hospital_building_shifted_label">Yes</p>
														</c:if>
														<c:if test="${getAllinfo_dtl_landReport[0].college_and_hospital_building_shifted == '0'}">
															<p id="college_and_hospital_building_shifted_label">No</p>
														</c:if>
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
														<c:if test="${getAllinfo_dtl_landReport[0].management_of_college_changed == '1'}">

															<p id="management_of_college_changed_label">Yes</p>
														</c:if>
														<c:if test="${getAllinfo_dtl_landReport[0].management_of_college_changed == '0'}">
															<p id="management_of_college_changed_label">No</p>
														</c:if>
													<p id="management_of_college_changed_label"></p>
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
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Internship & Housejob Details</h5>
									<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#exampleModal_internship_details">
									<i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Total No. of Interns (Upload List Batch Wise)</label> <span
										class="value-bind daobtn" id=""> <a
										class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="no_intern_view_btn"><i class="lni lni-eye"></i></a></span>
								</div>
								<input type="hidden" id="no_intern_hid" name="no_intern_hid" value="${getStu_Details_Upload_Doc_View[0].id}"/>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Rotation Programme ?</label> <span class="value-bind"
										id="rotationcheck_label"> </span>
								</div>
							</div>
<!-- 							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Total interns in O.P.D</label> <span class="value-bind" -->
<%-- 										id=""> ${getStu_Details_Upload_Doc_View[0].internsopd }</span> --%>
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Interns Duty hour/day in O.P.D</label> <span
										class="value-bind" id=""> ${getStu_Details_Upload_Doc_View[0].internsdutyopd }</span>
								</div>
							</div>
<!-- 							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Total interns in IPD</label><span class="value-bind" -->
<%-- 										id=""> ${getStu_Details_Upload_Doc_View[0].internsipd }</span> --%>
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Interns Duty hour/day in IPD</label> <span
										class="value-bind" id="">${getStu_Details_Upload_Doc_View[0].internsdutyipd } </span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Any migration of intern from/to College.</label> <span
										class="value-bind" id="migrationcheck_label"> </span>
								</div>
							</div>
<!-- 							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Migration List</label> <span class="value-bind daobtn" -->
<!-- 										id=""> <a href="#" -->
<!-- 										class="main-btn dark-btn btn-hover btn-sm btnview" -->
<!-- 										title="View"><i class="lni lni-eye"></i></a></span> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Whether intern students prescribe medicine</label> <span
										class="value-bind" id=""> ${getStu_Details_Upload_Doc_View[0].prescribe }</span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Seminar for Internee</label> <span class="value-bind"
										id=""> ${getStu_Details_Upload_Doc_View[0].seminar }</span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Whether they have Provisional Registration with
										State Homoeopathic Board/ Council ?</label> <span class="value-bind"
										id="pro_regcheck_label"> </span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Number of students who have not completed
										internship for more than two years of passing final BHMS
										examination</label> <span class="value-bind" id=""> ${getStu_Details_Upload_Doc_View[0].internship_not_completed }</span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>House Job</label> <span class="value-bind" id="house_job_label">${getStu_Details_Upload_Doc_View[0].house_job }
									</span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Number of Students undergoing House Job</label> <span
										class="value-bind" id="no_house_job_label"> ${getStu_Details_Upload_Doc_View[0].no_house_job }</span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Graded teaching work performed by P.G. Students</label>
									<span class="value-bind" id=""> ${getStu_Details_Upload_Doc_View[0].graded_teaching }</span>
								</div>
							</div>
						</div>
					</div>


				<div class="field-box">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
								<h5 class="custom-title-tag">Undertakings and Reports</h5>
<!-- 								<a href="#" class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#reportsModal"><i class="lni lni-pencil-alt"></i></a> -->
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
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View"  id="reg_certi_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a>
															<input type="hidden" id="clg_basic_info_doc_id" value="${getAllPersdetailsReport[0].id}"  
														name="clg_basic_info_doc_id" class="form-control"> 
															</li>
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
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="copy_of_soc_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
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
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="bank_gurantee_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
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
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="NOC_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
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
															<li><a
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="affilation_clg_basic_info_doc_view"><i
																	class="lni lni-eye"></i></a></li>
														</ul>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>University Approving Letter</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="uni_approve_clg_basic_info_doc_view"><i
																	class="lni lni-eye"></i></a></li>
														</ul>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>University Affiliation inspection of Last Academic Year</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="uni_affiliation_clg_basic_info_doc_view"><i
																	class="lni lni-eye"></i></a></li>
														</ul>
													</td>
												</tr>
												<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Last 10 year AYUSH Permission/Denial letter for UG
														and/or PG course</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="ayush_permission_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a>
															<input type="hidden" id="intake_loc_clg_basic_doc_id" value="${getAllinfo_intake_cap_childView[0].id}"  
														name="intake_loc_clg_basic_doc_id" class="form-control"> 
															</li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>If admitted through court, Upload court order for
														last 10 year</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="court_order_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Kindly Upload the undertaking that Institution will not admit the 
													students without permission from Government of India</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="inst_not_admit_stu_without_permission_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Kindly Upload the undertaking that selection of students for UG and PG Courses will be
													 made only on academic merit as per CCH regulation</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="selection_of_students_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Upload the list of PG students allotted to individual guide in the last 10 academic years</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="lastfiveguide_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Upload Name, Mobile No. and Email Id of all UG/PG admitted students with their
													 NEET/AIAPGET score/admission criteria in the last 10 academic years</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="neet_score_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
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
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="land_doc_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a>
															<input type="hidden" id="land_loc_clg_basic_info_doc_id" value="${getAllinfo_dtl_landReport[0].id}"  
														name="land_loc_clg_basic_info_doc_id" class="form-control"> 
															</li>
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
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="build_plan_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
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
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="statement_archi_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
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
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="audit_blnc_sheet_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
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
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="annual_rprt_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Upload the undertaking of the
														students (About the provisional nature of admission)</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="undertakingof_student_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
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
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="add_doc_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="sr-no">
													<p></p>
												</td>
												<td>
													<p>Upload PG students attendance register/Biometric attendance</p>
												</td>
												<td>
													<ul class="buttons-group">
														<li><a
															class="main-btn dark-btn btn-hover btn-sm btnview"
															title="View" id="bio_atten_clg_basic_info_doc_view"><i class="lni lni-eye"></i></a></li>
													</ul>
												</td>
											</tr>
											
											
											<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Duly notarized affidavits of place of working as
															per the format by all the full time teachers</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a
																class="main-btn dark-btn btn-hover btn-sm btnview6"
																title="View" id="teach_staff1_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="teach_staff1_hid" name="teach_staff1_hid" value="${getClg_Staff_List_Upload_Doc_View[0].id}"/>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Duly notarized affidavits of place of working as
															per the format by all Guest faculty teachers</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a
																class="main-btn dark-btn btn-hover btn-sm btnview7"
																title="View" id="teach_staff2_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Miscellaneous upload (documents related to
															teachers, which may be missed in teachers code portal)</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a class="main-btn dark-btn btn-hover btn-sm btnview8"
																title="View" id="teach_staff3_view_btn"><i class="lni lni-eye"></i></a></li>
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
									<label class="check-list">I, <b id="basicinfodec_name">${login_name }</b> solemnly confirm that if any information provided by me
										in proforma and Annexures found false, I shall be held
										responsible in the matter. I shall have no objection if any
										legal action is taken by the CCH/NCH against me.
									</label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- ===========================
							Basic Information End
						=========================== -->
						
						
						
												<!-- Modal start Institution Details-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="instdetModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_inst_addr_dtl_rmkData" name="bsc_info_inst_addr_dtl_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_inst_addr_dtl_rmk" name ="bsc_info_inst_addr_dtl_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_inst_dtl" name="submit_inst_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

		<!-- Modal start Management Details-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="mngtModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_mgmt_dtl_rmkData" name="bsc_info_mgmt_dtl_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_mgmt_dtl_rmk" name="bsc_info_mgmt_dtl_rmk" ></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_mgmt_dtl" name="submit_mgmt_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

		<!-- Modal start Society/Trust/Government Information-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="societyModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_basicinfo_dtl_rmkData" name="bsc_info_basicinfo_dtl_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_basicinfo_dtl_rmk" name="bsc_info_basicinfo_dtl_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_basic_dtl" name="submit_basic_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->


			<!-- Modal start Registration Number With Registering Authority Details-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="regauthModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_reg_num_dtl_rmkData" name="bsc_info_reg_num_dtl_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_reg_num_dtl_rmk" name="bsc_info_reg_num_dtl_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_reg_num_dtl" name="submit_reg_num_dtl" >Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->


			
<!-- Modal start Number Of Years Of Experience-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="expModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_num_yrs_exp_rmkData" name="bsc_info_num_yrs_exp_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_num_yrs_exp_rmk" name="bsc_info_num_yrs_exp_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_num_yrs_dtl" name="submit_num_yrs_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->


			<!-- Modal start Qualification Details-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="qualModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_quali_rmkData" name="bsc_info_quali_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_quali_rmk" name="bsc_info_quali_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_info_quali_dtl" name="submit_info_quali_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->


<!-- Modal start Head Of Institution Contact Details-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="headofinstModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_hoi_addr_rmkData" name="bsc_info_hoi_addr_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_hoi_addr_rmk" name="bsc_info_hoi_addr_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_info_hoi_addr_dtl" name="submit_info_hoi_addr_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->


			
<!-- Modal start Nearest Airport Details-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="airportModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_nr_airpo_dtl_rmkData" name="bsc_info_nr_airpo_dtl_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_nr_airpo_dtl_rmk" name="bsc_info_nr_airpo_dtl_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_nr_airpo_dtl" name="submit_nr_airpo_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->


		
<!-- Modal start Nearest Railway Station-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="railwayModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_nr_railwy_dtl_rmkData" name="bsc_info_nr_railwy_dtl_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_nr_railwy_dtl_rmk" name="bsc_info_nr_railwy_dtl_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_nr_railwy_dtl" name="submit_nr_railwy_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->


				<!-- Modal start Other Relevant Institution Detail Within The Radius Of 50km-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="radiusModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_other_relevant_dtl_rmkData" name="bsc_info_other_relevant_dtl_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_other_relevant_dtl_rmk" name="bsc_info_other_relevant_dtl_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_other_relevant_dtl" name="submit_other_relevant_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal start Police Station Details-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="policeModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_police_dtl_rmkData" name="bsc_info_police_dtl_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_police_dtl_rmk" name="bsc_info_police_dtl_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_info_police_dtl" name="submit_info_police_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->


<!-- Modal start Last 10 Years Permission With UG Intake Capacity-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="ugintakeModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_int_cap_ug_rmkData" name="bsc_info_int_cap_ug_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_int_cap_ug_rmk" name="bsc_info_int_cap_ug_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_int_cap_ug_dtl" name="submit_int_cap_ug_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->




<!-- Modal start Last 10 Years Permission With PG Intake Capacity-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="pgintakeModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_int_cap_pg_rmkData" name="bsc_info_int_cap_pg_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_int_cap_pg_rmk" name="bsc_info_int_cap_pg_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button"  id="submit_int_cap_pg_dtl" name="submit_int_cap_pg_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->



<!-- Modal start UG Course Intake Capacity-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="ugcourseintakeModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_course_int_cap_ug_rmkData" name="bsc_info_course_int_cap_ug_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_course_int_cap_ug_rmk" name="bsc_info_course_int_cap_ug_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_course_cap_ug_dtl" name="submit_course_cap_ug_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->


<!-- Modal start PG Course Intake Capacity-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="pgcourseintakeModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_course_int_cap_pg_rmkData" name="bsc_info_course_int_cap_pg_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_course_int_cap_pg_rmk" name="bsc_info_course_int_cap_pg_rmk"></textarea>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_course_cap_pg_dtl" name="submit_course_cap_pg_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->



<!-- Modal start Details Of Land-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="landModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Remarks</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="custom-modal-inner" id="bsc_info_dtl_of_land_rmkData" name="bsc_info_dtl_of_land_rmkData">
					<div class="row">

						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="input-style-1">
								<label>Enter Remarks</label>
								<textarea placeholder="Enter Remarks" rows="5" id="bsc_info_dtl_of_land_rmk" name="bsc_info_dtl_of_land_rmk"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a class="main-btn success-btn  btn-hover" type="button" id="submit_dtl_of_land_dtl" name="submit_dtl_of_land_dtl">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->


<!-- Modal start Undertakings And Reports-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="reportsModal">
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
		
						
						
				
						
						
						
</form:form>
			</div>
		</div>
	</div>
</section>

<!-- Canvas Modal start -->

<div class="modal fade custom-modal custom-modal-pdf" tabindex="-1"
	aria-labelledby="myLargeModalLabel" id="clg_basic_info_doc"
	aria-hidden="true">
	<div
		class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">Document Preview</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<div class="custom-modal-inner" id="headData1" name="headData1">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="alert alert-success alert-dismissible">
								<button type="button" class="btn-close" data-bs-dismiss="alert"></button>

							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-12 text-center">
                        <embed  src="" id="pdfmodelcanvas" width="1000" height="450">
<!-- 							<div id="pdfmodelcanvas"></div> -->

						</div>
					</div>
				</div>


			</div>
			<div class="modal-footer" id="modal-footer">
			<ul class="buttons-group">
								<li><a type="button" class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal">Close</a></li>
							
							</ul>
			
			</div>


		</div>
	</div>
</div>


<!---------canvas end========================= -->


<!-- hemburgermenu js start -->
<script src="assets/vendor/hamburger-menu/hemburgermenu.js" type="text/javascript"></script>
<!-- hemburgermenu css end -->

<!-- Modal satrt -->

<%-- <c:url value="View_Search_College_InfrastructureUrl" var="viewUrl" /> --%>
<%-- <form:form action="${viewUrl}" method="post" id="viewForm" --%>
<%-- 	name="viewForm" modelAttribute="id6"> --%>
<!-- 	<input type="hidden" name="id6" id="id6" value="0" /> -->
<!-- 	<input type="hidden" name="statusview" id="statusview" value="0" /> -->
<%-- </form:form> --%>

<c:url value="View_Search_Basic_InfoUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_basic_info"
	name="viewForm" modelAttribute="id6">
<!-- 	<input type="hidden" name="basic_info_id" id="basic_info_id" value="0" /> -->
	<input type="hidden" name="id6" id="id6" value="0" />
	<input type="hidden" name="id7" id="id7" value="0" />
</form:form>
<c:url value="View_Search_College_InfrastructureUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_college_infra"
	name="viewForm" modelAttribute="college_infra_id">
	<input type="hidden" name="college_infra_id" id="college_infra_id" value="0" />
	<input type="hidden" name="college_infra_view_id" id="college_infra_view_id" value="0" />
</form:form>
<c:url value="View_Search_College_DepartmentUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_college_department"
	name="viewForm" modelAttribute="college_department_id">
	<input type="hidden" name="college_department_id" id="college_department_id" value="0" />
	<input type="hidden" name="college_department_view_id" id="college_department_view_id" value="0" />
</form:form>
<c:url value="View_Search_College_FinancialUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_college_financial"
	name="viewForm" modelAttribute="college_financial_id">
	<input type="hidden" name="college_financial_id" id="college_financial_id" value="0" />
	<input type="hidden" name="college_financial_view_id" id="college_financial_view_id" value="0" />
</form:form>
<c:url value="View_Search_Student_DetailsUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_student_dtl"
	name="viewForm" modelAttribute="student_dtl_id">
	<input type="hidden" name="student_dtl_id" id="student_dtl_id" value="0" />
	<input type="hidden" name="student_dtl_view_id" id="student_dtl_view_id" value="0" />
</form:form>
<c:url value="View_College_Staff_InfoUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_clg_staff_info"
	name="viewForm" modelAttribute="clg_staff_info_id">
	<input type="hidden" name="clg_staff_info_id" id="clg_staff_info_id" value="0" />
	<input type="hidden" name="clg_staff_info_view_id" id="clg_staff_info_view_id" value="0" />
</form:form>
<c:url value="College_Staff_List_ViewUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_clg_staff_list"
	name="viewForm" modelAttribute="basic_clg_staff_list_id">
	<input type="hidden" name="basic_clg_staff_list_id" id="basic_clg_staff_list_id" value="0" />
	<input type="hidden" name="basic_clg_staff_list_view_id" id="basic_clg_staff_list_view_id" value="0" />
</form:form>
<c:url value="View_Department_Equipment_ReportUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_department_eqp"
	name="viewForm" modelAttribute="department_eqp_id">
	<input type="hidden" name="department_eqp_id" id="department_eqp_id" value="0" />
	<input type="hidden" name="department_eqp_view_id" id="department_eqp_view_id" value="0" />
</form:form>
<c:url value="hospital_infrastructure_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_infra"
	name="viewForm" modelAttribute="hos_infra_id">
	<input type="hidden" name="hos_infra_id" id="hos_infra_id" value="0" />
	<input type="hidden" name="hos_infra_view_id" id="hos_infra_view_id" value="0" />
</form:form>
<c:url value="hospital_ipdopd_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_ipd"
	name="viewForm" modelAttribute="hos_ipd_id">
	<input type="hidden" name="hos_ipd_id" id="hos_ipd_id" value="0" />
	<input type="hidden" name="hos_ipd_view_id" id="hos_ipd_view_id" value="0" />
</form:form>
<c:url value="otherhospital_detail_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_other_hos_dtl"
	name="viewForm" modelAttribute="other_hos_dtl_id">
	<input type="hidden" name="other_hos_dtl_id" id="other_hos_dtl_id" value="0" />
	<input type="hidden" name="other_hos_dtl_view_id" id="other_hos_dtl_view_id" value="0" />
</form:form>
<c:url value="hospital_staffdetails_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_staff_dtl"
	name="viewForm" modelAttribute="hos_staff_dtl_id">
	<input type="hidden" name="hos_staff_dtl_id" id="hos_staff_dtl_id" value="0" />
	<input type="hidden" name="hos_staff_dtl_view_id" id="hos_staff_dtl_view_id" value="0" />
</form:form>
<c:url value="hospital_staff_list_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_staff_list"
	name="viewForm" modelAttribute="hos_staff_list_id">
	<input type="hidden" name="hos_staff_list_id" id="hos_staff_list_id" value="0" />
	<input type="hidden" name="hos_staff_list_view_id" id="hos_staff_list_view_id" value="0" />
</form:form>
<c:url value="View_Hospital_Equipment_ReportUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_eqp"
	name="viewForm" modelAttribute="hos_eqp_id">
	<input type="hidden" name="hos_eqp_id" id="hos_eqp_id" value="0" />
	<input type="hidden" name="hos_eqp_view_id" id="hos_eqp_view_id" value="0" />
</form:form>
<c:url value="Declaration_ViewUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_declaration"
	name="viewForm" modelAttribute="declaration_id">
	<input type="hidden" name="declaration_id" id="declaration_id" value="0" />
	<input type="hidden" name="declaration_view_id" id="declaration_view_id" value="0" />
</form:form>

<input type='hidden' id="viewId${parent_id}" value="${getInstname[0].id}">

<!-- College Staff List View Merge -->

<c:url value="CommonDocPartOnezip" var="CommonDocPartOnezip" />
<form:form action="${CommonDocPartOnezip}" method="post" id="DownForm"
	name="DownForm" modelAttribute="kmlId2">
	<input type="hidden" name="kmlId2" id="kmlId2" value="0" />
	<input type="hidden" name="val444" id="val444" value="0" />
	<input type="hidden" name="fildname1" id="fildname1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	if('${role}'!="NCH"){
		$(".btnedit").css("visibility", "hidden");

	}
	$("#institute_id").val('${All_Institute_ID}');
	$("#main_view_id").val('${id6}');
				
				var institute_type ="";
				institute_type = "${getAllPersdetailsReport[0].institute_type}";
				if(institute_type == "2"){
					institute_type="Private"
				}
				if(institute_type == "1"){
					institute_type="Government"
				}
				$("#mang_inst_type_label").text(institute_type);
				
				var intimatedcheck ="";
				intimatedcheck = "${getAllinfo_intake_cap_childView[0].intimatedcheck}";
				if(intimatedcheck == "2"){
					intimatedcheck="No"
				$("#upload_undertakingcheck_div").hide();
				}
				if(intimatedcheck == "1"){
					intimatedcheck="Yes"
				$("#upload_undertakingcheck_div").show();
				}
				$("#intimatedcheck_label").text(intimatedcheck);
				
				var undertakingcheck ="";
				undertakingcheck = "${getAllinfo_dtl_landReport[0].undertakingcheck}";
				if(undertakingcheck == "2"){
					undertakingcheck="No"
				}
				if(undertakingcheck == "1"){
					undertakingcheck="Yes"
				}
				$("#undertakingcheck_label").text(undertakingcheck);
				
				var rotationcheck ="";
				rotationcheck = "${getStu_Details_Upload_Doc_View[0].rotationcheck}";
				if(rotationcheck == "2"){
					rotationcheck="No"
				}
				if(rotationcheck == "1"){
					rotationcheck="Yes"
				}
				$("#rotationcheck_label").text(rotationcheck);
				
				var pro_regcheck ="";
				pro_regcheck = "${getStu_Details_Upload_Doc_View[0].pro_regcheck}";
				if(pro_regcheck == "2"){
					pro_regcheck="No"
				}
				if(pro_regcheck == "1"){
					pro_regcheck="Yes"
				}
				$("#pro_regcheck_label").text(pro_regcheck);
				
				
				$("#emailid_label").css("text-transform", "lowercase");
				$("#website_label").css("text-transform", "lowercase");
				$("#mang_emailid_label").css("text-transform", "lowercase");
				$("#hoi_emailid_label").css("text-transform", "lowercase");
				
$.ajaxSetup({
		async : false
			});
		
		//GET MAIN ID AND REMARKS
		var getView_idFrom_Institute_id = '${getView_idFrom_Institute_id}';
	//	alert("tk"+'${getView_idFrom_Institute_id}');
		if(getView_idFrom_Institute_id != "[]"){
	//		alert('${getView_idFrom_Institute_id}');
			$("#hid_college_infrastructure_remark").val('${getView_idFrom_Institute_id[0].id}');
			$("#bsc_info_inst_addr_dtl_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_inst_addr_dtl_rmk}');
			$("#bsc_info_mgmt_dtl_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_mgmt_dtl_rmk}');
			$("#bsc_info_basicinfo_dtl_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_basicinfo_dtl_rmk}');
			$("#bsc_info_reg_num_dtl_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_reg_num_dtl_rmk}');
			$("#bsc_info_num_yrs_exp_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_num_yrs_exp_rmk}');
			$("#bsc_info_hoi_addr_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_hoi_addr_rmk}');
			$("#bsc_info_nr_airpo_dtl_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_nr_airpo_dtl_rmk}');
			$("#bsc_info_nr_railwy_dtl_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_nr_railwy_dtl_rmk}');
			$("#bsc_info_other_relevant_dtl_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_other_relevant_dtl_rmk}');
			$("#bsc_info_police_dtl_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_police_dtl_rmk}');
			$("#bsc_info_int_cap_ug_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_int_cap_ug_rmk}');
			$("#bsc_info_int_cap_pg_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_int_cap_pg_rmk}');
			$("#bsc_info_course_int_cap_ug_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_course_int_cap_ug_rmk}');
			$("#bsc_info_course_int_cap_pg_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_course_int_cap_pg_rmk}');
			$("#bsc_info_dtl_of_land_rmk").val('${getView_idFrom_Institute_id[0].bsc_info_dtl_of_land_rmk}');
			
		}

	
document.querySelectorAll('.viewData').forEach((items, index) => {
	items.addEventListener('click', event => {
		var val=parseInt(index)+1;
		var hid = document.getElementById('viewId${getInstname[0].id}').value;
		if (confirm('Are You Sure You Want to View Detail ?')) {
			ViewData(hid);
		} else {
			return false;
		}
	})
});

function ViewData(id){

$("#id6").val(id);
$("#statusview").val($("#statusA").val());
document.getElementById('viewForm').submit();
}


});

document.addEventListener('DOMContentLoaded', function() {
	
	
// 	document.getElementById('basics_information_view').onclick = function() {
// 		ViewData_basic_info();
// 	};

if('${role}'=="NCH"){
	
	document.getElementById('college_infrastructure_view').onclick = function() {
		ViewData_college_infra();
	};

	document.getElementById('college_department_view').onclick = function() {
		ViewData_college_department();
	};
	document.getElementById('college_financial_view').onclick = function() {
		ViewData_college_financial();
	};
	document.getElementById('student_details_view_page').onclick = function() {
		ViewData_student_dtl();
	};
	document.getElementById('college_staff_info_view').onclick = function() {
		ViewData_clg_staff_info();
	};
	document.getElementById('college_staff_list_view').onclick = function() {
		ViewData_clg_staff_list();
	};
	document.getElementById('department_equipments_view').onclick = function() {
		ViewData_department_eqp();
	};
	document.getElementById('hospital_infrastructure_view').onclick = function() {
		ViewData_hos_infra();
	};
	document.getElementById('hospital_ipdopd_view').onclick = function() {
		ViewData_hos_ipd();
	};
	document.getElementById('otherhospital_detail_view').onclick = function() {
		ViewData_other_hos_dtl();
	};
	document.getElementById('hospital_staffdetails_view').onclick = function() {
		ViewData_hos_staff_dtl();
	};
	document.getElementById('hospital_staff_list_view').onclick = function() {
		ViewData_hos_staff_list();
	};
	document.getElementById('hospital_equipments_view').onclick = function() {
		ViewData_hos_eqp();
	};
	document.getElementById('declaration_view').onclick = function() {
		ViewData_declaration();
	};
	
}
	
// 	document View

	document.getElementById('reg_certi_clg_basic_info_doc_view').onclick = function() {
		file_view($("#clg_basic_info_doc_id").val(),"clg_reg_inst_info_institution_basic_details","reg_certi_of_society");
	};
	document.getElementById('copy_of_soc_clg_basic_info_doc_view').onclick = function() {
		file_view($("#clg_basic_info_doc_id").val(),"clg_reg_inst_info_institution_basic_details","copy_of_society");
	};
	document.getElementById('bank_gurantee_clg_basic_info_doc_view').onclick = function() {
		file_view($("#clg_basic_info_doc_id").val(),"clg_reg_inst_info_institution_basic_details","undertaking_letter_furnish_bank_guarantee");
	};
	document.getElementById('NOC_clg_basic_info_doc_view').onclick = function() {
		file_view($("#clg_basic_info_doc_id").val(),"clg_reg_inst_info_institution_basic_details","noc_doc_state_gov");
	};
	document.getElementById('affilation_clg_basic_info_doc_view').onclick = function() {
		file_view($("#clg_basic_info_doc_id").val(),"clg_reg_inst_info_institution_basic_details","affiliaion_doc_concern_uni");
	};
	document.getElementById('uni_approve_clg_basic_info_doc_view').onclick = function() {
		file_view($("#clg_basic_info_doc_id").val(),"clg_reg_inst_info_institution_basic_details","uni_app_letter");
	};
	document.getElementById('uni_affiliation_clg_basic_info_doc_view').onclick = function() {
		file_view($("#clg_basic_info_doc_id").val(),"clg_reg_inst_info_institution_basic_details","inspection_last_academic_yr");
	};
	document.getElementById('ayush_permission_clg_basic_info_doc_view').onclick = function() {
		file_view($("#intake_loc_clg_basic_doc_id").val(),"clg_reg_inst_info_course_intake_capacity_child","ayush_permission_letter");
	};
	document.getElementById('court_order_clg_basic_info_doc_view').onclick = function() {
		file_view($("#intake_loc_clg_basic_doc_id").val(),"clg_reg_inst_info_course_intake_capacity_child","upload_court_order");
	};
	document.getElementById('inst_not_admit_stu_without_permission_clg_basic_info_doc_view').onclick = function() {
		file_view($("#intake_loc_clg_basic_doc_id").val(),"clg_reg_inst_info_course_intake_capacity_child","inst_not_admit_stu_without_permission");
	};
	document.getElementById('selection_of_students_clg_basic_info_doc_view').onclick = function() {
		file_view($("#intake_loc_clg_basic_doc_id").val(),"clg_reg_inst_info_course_intake_capacity_child","selection_of_students");
	};
	document.getElementById('lastfiveguide_clg_basic_info_doc_view').onclick = function() {
		file_view($("#intake_loc_clg_basic_doc_id").val(),"clg_reg_inst_info_course_intake_capacity_child","lastfiveguide");
	};
	document.getElementById('neet_score_clg_basic_info_doc_view').onclick = function() {
		file_view($("#intake_loc_clg_basic_doc_id").val(),"clg_reg_inst_info_course_intake_capacity_child","neet_score");
	};
	document.getElementById('land_doc_clg_basic_info_doc_view').onclick = function() {
		file_view($("#land_loc_clg_basic_info_doc_id").val(),"clg_reg_inst_info_details_of_land","land_document_clg");
	};
	document.getElementById('build_plan_clg_basic_info_doc_view').onclick = function() {
		file_view($("#land_loc_clg_basic_info_doc_id").val(),"clg_reg_inst_info_details_of_land","building_plan");
	};
	document.getElementById('statement_archi_clg_basic_info_doc_view').onclick = function() {
		file_view($("#land_loc_clg_basic_info_doc_id").val(),"clg_reg_inst_info_details_of_land","statement_doc_architect");
	};
	document.getElementById('audit_blnc_sheet_clg_basic_info_doc_view').onclick = function() {
		file_view($("#land_loc_clg_basic_info_doc_id").val(),"clg_reg_inst_info_details_of_land","audited_balance_sheet");
	};
	document.getElementById('annual_rprt_clg_basic_info_doc_view').onclick = function() {
		file_view($("#land_loc_clg_basic_info_doc_id").val(),"clg_reg_inst_info_details_of_land","annual_report");
	};
	document.getElementById('undertakingof_student_clg_basic_info_doc_view').onclick = function() {
		file_view($("#intake_loc_clg_basic_doc_id").val(),"clg_reg_inst_info_course_intake_capacity_child","undertakingofstudent");
	};
	document.getElementById('add_doc_clg_basic_info_doc_view').onclick = function() {
		file_view($("#land_loc_clg_basic_info_doc_id").val(),"clg_reg_inst_info_details_of_land","additional_document");
	};
	document.getElementById('bio_atten_clg_basic_info_doc_view').onclick = function() {
		file_view($("#intake_loc_clg_basic_doc_id").val(),"clg_reg_inst_info_course_intake_capacity_child","biometricattendance");
	};
	
	//Student Details View Merge
	
	document.getElementById('no_intern_view_btn').onclick = function() {
		file_view($("#no_intern_hid").val(),"clg_reg_student_dtl_upload_doc","total_intern");
	};
	
	//College Staff List Merge
	
	document.getElementById('teach_staff1_view_btn').onclick = function() {
		file_view1($("#teach_staff1_hid").val(),"clg_reg_college_staff_list_upload_doc","full_time_teacher_affidavit");
	};
	
	document.getElementById('teach_staff2_view_btn').onclick = function() {
		file_view1($("#teach_staff1_hid").val(),"clg_reg_college_staff_list_upload_doc","guest_teacher_affidavit");
	};

	document.getElementById('teach_staff3_view_btn').onclick = function() {
		file_view1($("#teach_staff1_hid").val(),"clg_reg_college_staff_list_upload_doc","miscellaneous_doc");
	};
	
	if('${role}'=="NCH"){
	document.getElementById('submit_inst_dtl').onclick = function() {
		
		Save_As_Draft_Remarks();
				
		};
		document.getElementById('submit_mgmt_dtl').onclick = function() {
			
			Save_As_Draft_Remarks();
					
			};
			document.getElementById('submit_basic_dtl').onclick = function() {
				
				Save_As_Draft_Remarks();
						
				};
			document.getElementById('submit_reg_num_dtl').onclick = function() {
					
				Save_As_Draft_Remarks();
							
				};
			document.getElementById('submit_num_yrs_dtl').onclick = function() {
						
					Save_As_Draft_Remarks();
								
				};	
			document.getElementById('submit_info_quali_dtl').onclick = function() {
							
						Save_As_Draft_Remarks();
									
				};	
				document.getElementById('submit_info_hoi_addr_dtl').onclick = function() {
					
					Save_As_Draft_Remarks();
								
					};	
					document.getElementById('submit_nr_airpo_dtl').onclick = function() {
						
						Save_As_Draft_Remarks();
									
						};
						
						document.getElementById('submit_nr_railwy_dtl').onclick = function() {
							
							Save_As_Draft_Remarks();
										
							};	
							
							
							document.getElementById('submit_other_relevant_dtl').onclick = function() {
								
								Save_As_Draft_Remarks();
											
								};	
								document.getElementById('submit_int_cap_ug_dtl').onclick = function() {
									
									Save_As_Draft_Remarks();
												
									};	
									
									document.getElementById('submit_int_cap_pg_dtl').onclick = function() {
										
										Save_As_Draft_Remarks();
													
										};
										
										document.getElementById('submit_course_cap_ug_dtl').onclick = function() {
											
											Save_As_Draft_Remarks();
														
											};					
											document.getElementById('submit_course_cap_pg_dtl').onclick = function() {
												
												Save_As_Draft_Remarks();
															
												};
												document.getElementById('submit_dtl_of_land_dtl').onclick = function() {
													
													Save_As_Draft_Remarks();
																
													};
	}
	
	
	

});

function ViewData_basic_info(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#id6").val(institute_id);
	$("#id7").val(institute_id);
	document.getElementById('viewForm_basic_info').submit();
}
function ViewData_college_infra(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#college_infra_id").val(institute_id);
	$("#college_infra_view_id").val(main_view_id);
	document.getElementById('viewForm_college_infra').submit();
}
function ViewData_college_department(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#college_department_id").val(institute_id);
	$("#college_department_view_id").val(main_view_id);
	document.getElementById('viewForm_college_department').submit();
}
function ViewData_college_financial(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#college_financial_id").val(institute_id);
	$("#college_financial_view_id").val(main_view_id);
	document.getElementById('viewForm_college_financial').submit();
}
function ViewData_student_dtl(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#student_dtl_id").val(institute_id);
	$("#student_dtl_view_id").val(main_view_id);
	document.getElementById('viewForm_student_dtl').submit();
}
function ViewData_clg_staff_info(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#clg_staff_info_id").val(institute_id);
	$("#clg_staff_info_view_id").val(main_view_id);
	document.getElementById('viewForm_clg_staff_info').submit();
}
function ViewData_clg_staff_list(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#basic_clg_staff_list_id").val(institute_id);
	$("#basic_clg_staff_list_view_id").val(main_view_id);
	document.getElementById('viewForm_clg_staff_list').submit();
}
function ViewData_department_eqp(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#department_eqp_id").val(institute_id);
	$("#department_eqp_view_id").val(main_view_id);
	document.getElementById('viewForm_department_eqp').submit();
}
function ViewData_hos_infra(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#hos_infra_id").val(institute_id);
	$("#hos_infra_view_id").val(main_view_id);
	document.getElementById('viewForm_hos_infra').submit();
}
function ViewData_hos_ipd(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#hos_ipd_id").val(institute_id);
	$("#hos_ipd_view_id").val(main_view_id);
	document.getElementById('viewForm_hos_ipd').submit();
}
function ViewData_other_hos_dtl(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#other_hos_dtl_id").val(institute_id);
	$("#other_hos_dtl_view_id").val(main_view_id);
	document.getElementById('viewForm_other_hos_dtl').submit();
}
function ViewData_hos_staff_dtl(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#hos_staff_dtl_id").val(institute_id);
	$("#hos_staff_dtl_view_id").val(main_view_id);
	document.getElementById('viewForm_hos_staff_dtl').submit();
}
function ViewData_hos_staff_list(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#hos_staff_list_id").val(institute_id);
	$("#hos_staff_list_view_id").val(main_view_id);
	document.getElementById('viewForm_hos_staff_list').submit();
}
function ViewData_hos_eqp(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#hos_eqp_id").val(institute_id);
	$("#hos_eqp_view_id").val(main_view_id);
	document.getElementById('viewForm_hos_eqp').submit();
}
function ViewData_declaration(){
	
	var institute_id  = $("#institute_id").val();
	var main_view_id  = $("#main_view_id").val();
	
	$("#declaration_id").val(institute_id);
	$("#declaration_view_id").val(main_view_id);
	document.getElementById('viewForm_declaration').submit();
}


function Save_As_Draft_Remarks() {

	$.ajaxSetup({
	    async: false
	});	
		
		var form_data = new FormData(document.getElementById("View_Search_Basic_Info"));
		$.ajax({
			url : 'College_Infrastructure_Remark_Save?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j){
	      if(j>0){
	    		$("#hid_college_infrastructure_remark").val(j);
	    	  	alert("Data Save Sucessfully");
	      }
	      else{
	    	  alert(j);
	      }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	}
	
//view Document

function file_view(id,val,field) {
	 $("#clg_basic_info_doc").modal('show');
	 var pdf1="CommonDocPartOne?kmlId2="+id+"&val444="+val+"&fildname1="+field+" ";
	 $("#pdfmodelcanvas") .attr('src',pdf1);
}

function file_view1(id,val,field) {
	
	 $("#kmlId2").val(id);
	 $("#val444").val(val);
	 $("#fildname1").val(field);
	 $("#DownForm").submit();
	
//	alert("1");
//	 $("#exampleModal1").modal('show');
//	 $("#exampleModal1").fadeOut().removeClass("active");
// 		window.location = 'CommonDocPartOne?kmlId2=' + id + '&val444=' + val + '&fildname1='+field;
//	 var pdf1="CommonDocPartOne?kmlId2="+id+"&val444="+val+"&fildname1="+field;
//	 $("#pdfmodelcanvas1") .attr('src',pdf1);
}



</script>



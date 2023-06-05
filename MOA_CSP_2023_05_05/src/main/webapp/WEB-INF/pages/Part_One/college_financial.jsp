<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/datePicketValidation.js"></script>
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
							<h2>College Financial</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="#0">Dashboard</a></li>

									<li class="breadcrumb-item active" aria-current="page">
										College Financial</li>
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
										<label>Institution Name :</label> <span class="value-bind">${username}</span>
									</div>

								</div>
							</div>
						</div>
					</div>
					<!-- ===========================
							College Financial Start
						=========================== -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-12">
						<!-- input style start -->
						<div class="card-style mb-30">
							<div class="tabs content_h900">
								<!-- Financial Details Start-->
								<div class="tab">
									<button class="tab-toggle" id="defaultOpen">Financial
										Details</button>
								</div>
								<div class="content">
									<h4 class="heading">Financial Details</h4>
									<form:form name="financial_college" id="financial_college"
										action="" method="post" class="" commandName="">
										<div class="row">
											<input type="hidden" id="hid_financial_council"
												name="hid_financial_council" value="0">
												<input type="hidden" id="basic_info_id" name="basic_info_id" value="0">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="accordion accordion-flush"
													id="accordionFlushExample">
													<div class="accordion-item accordion-itemflush">
														<h2 class="accordion-header" id="flush-headingOne">
															<button class="accordion-button accordion-secondary-btn"
																type="button" data-bs-toggle="collapse"
																data-bs-target="#flush-collapseOne" aria-expanded="true"
																aria-controls="flush-collapseOne">Financial
																Capability (in Lakhs)</button>
														</h2>
														<div id="flush-collapseOne"
															class="accordion-collapse collapse show"
															aria-labelledby="flush-headingOne"
															data-bs-parent="#accordionFlushExample">
															<div class="accordion-body">
																<div class="row">
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Fixed Deposits<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="fix_deposite" id="fix_deposite"
																				class="form-control" placeholder="Fixed Deposits"
																				maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Current Account<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="current_acct" id="current_acct"
																				class="form-control" placeholder="Current Account"
																				maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Saving Account<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="saving_acct" id="saving_acct"
																				class="form-control" placeholder="Saving Account"
																				maxlength="15">
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="accordion-item accordion-itemflush">
														<h2 class="accordion-header" id="flush-headingTwo">
															<button
																class="accordion-button accordion-secondary-btn collapsed"
																type="button" data-bs-toggle="collapse"
																data-bs-target="#flush-collapseTwo"
																aria-expanded="false" aria-controls="flush-collapseTwo">Proposed
																Budget for College & Hospital (in Lakhs)</button>
														</h2>
														<div id="flush-collapseTwo"
															class="accordion-collapse collapse"
															aria-labelledby="flush-headingTwo"
															data-bs-parent="#accordionFlushExample">
															<div class="accordion-body">
																<div class="row">
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Project Cost<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="project_cost" id="project_cost"
																				class="form-control" placeholder="Project Cost"
																				maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Capital Cost Of Land<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="capital_cost_land" id="capital_cost_land"
																				class="form-control"
																				placeholder="Capital Cost Of Land" maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Building Cost<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="building_cost" id="building_cost"
																				class="form-control" placeholder="Building Cost"
																				maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Plants & Machinery<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="plants_machinery" id="plants_machinery"
																				class="form-control" placeholder="Building Cost"
																				maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Equipments<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="equipments" id="equipments"
																				class="form-control" placeholder="Equipments"
																				maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Furniture & Fixture<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="furniture_fixer" id="furniture_fixer"
																				class="form-control"
																				placeholder="Furniture & Fixture" maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Preliminary & Pre
																				Operative Cost<strong class="mandatory">*</strong>
																			</label> <input type="text" name="preliminary_operative_cost"
																				id="preliminary_operative_cost" class="form-control"
																				placeholder="Preliminary & Pre Operative Cost"
																				maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Other<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="others" id="others" class="form-control"
																				placeholder="Others" maxlength="15">
																		</div>
																	</div>
																	<!-- end col -->
																	<!-- end row -->
																</div>
															</div>
														</div>
													</div>
													<div class="accordion-item accordion-itemflush">
														<h2 class="accordion-header" id="flush-headingthree">
															<button
																class="accordion-button accordion-secondary-btn collapsed"
																type="button" data-bs-toggle="collapse"
																data-bs-target="#flush-collapsethree"
																aria-expanded="false"
																aria-controls="flush-collapsethree">Means of
																Financing the Project (in Lakhs)</button>
														</h2>
														<div id="flush-collapsethree"
															class="accordion-collapse collapse"
															aria-labelledby="flush-headingthree"
															data-bs-parent="#accordionFlushExample">
															<div class="accordion-body">
																<div class="row">
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Contribution Of Applicant<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="contribution_applicants"
																				id="contribution_applicants" class="form-control"
																				placeholder="Contribution of Applicant"
																				maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Grants<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="grants" id="grants" class="form-control"
																				placeholder="Grants" maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Donation<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="donation" id="donation" class="form-control"
																				placeholder="Donation" maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Equity<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="equity" id="equity" class="form-control"
																				placeholder="Equity" maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Term Loan<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="term_loan" id="term_loan" class="form-control"
																				placeholder="Term Loan" maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Other Sources(if any)<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="other_source" id="other_source"
																				class="form-control"
																				placeholder="Other Sources information"
																				maxlength="15">
																		</div>
																	</div>
																	<!-- end col -->
																	<!-- end row -->
																</div>
															</div>
														</div>
													</div>
													<div class="accordion-item accordion-itemflush">
														<h2 class="accordion-header" id="flush-headingfour">
															<button
																class="accordion-button accordion-secondary-btn collapsed"
																type="button" data-bs-toggle="collapse"
																data-bs-target="#flush-collapsefour"
																aria-expanded="false" aria-controls="flush-collapsefour">Revenue
																Generated (in Lakhs)</button>
														</h2>
														<div id="flush-collapsefour"
															class="accordion-collapse collapse"
															aria-labelledby="flush-headingfour"
															data-bs-parent="#accordionFlushExample">
															<div class="accordion-body">
																<div class="row">
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Fee Structure<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="fee_structure" id="fee_structure"
																				class="form-control" placeholder="Fee Structure"
																				maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Hospital user Charges<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="hospital_charges" id="hospital_charges"
																				class="form-control"
																				placeholder="Hospital user Charges" maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Estimated Annual Revenue
																				from Other Sources<strong class="mandatory">*</strong>
																			</label> <input type="text" name="annual_revenue"
																				id="annual_revenue" class="form-control"
																				placeholder="Estimated Annual Revenue"
																				maxlength="15">
																		</div>
																	</div>
																	<!-- end col -->
																	<!-- end row -->
																</div>

															</div>
														</div>
													</div>
													<div class="accordion-item accordion-itemflush">
														<h2 class="accordion-header" id="flush-headingnine">
															<button
																class="accordion-button accordion-secondary-btn collapsed"
																type="button" data-bs-toggle="collapse"
																data-bs-target="#flush-collapsenine"
																aria-expanded="false" aria-controls="flush-collapsefive">Expenditure
																Incurred (in Lakhs)</button>
														</h2>
														<div id="flush-collapsenine"
															class="accordion-collapse collapse"
															aria-labelledby="flush-headingnine"
															data-bs-parent="#accordionFlushExample">
															<div class="accordion-body">
																<div class="row">
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Operating Expenses<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="operating_expenses" id="operating_expenses"
																				class="form-control"
																				placeholder="Operating Expenses" maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Depreciation<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="depreciation" id="depreciation"
																				class="form-control" placeholder="Depreciation"
																				maxlength="15">
																		</div>
																	</div>

																	<!-- end col -->
																	<!-- end row -->
																</div>
															</div>
														</div>
													</div>
													<div class="accordion-item accordion-itemflush">
														<h2 class="accordion-header" id="flush-headingfive">
															<button
																class="accordion-button accordion-secondary-btn collapsed"
																type="button" data-bs-toggle="collapse"
																data-bs-target="#flush-collapsefive"
																aria-expanded="false" aria-controls="flush-collapsefive">Operating
																Result (in Lakhs)</button>
														</h2>
														<div id="flush-collapsefive"
															class="accordion-collapse collapse"
															aria-labelledby="flush-headingfive"
															data-bs-parent="#accordionFlushExample">
															<div class="accordion-body">
																<div class="row">
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Income Statement<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="income_statement" id="income_statement"
																				class="form-control" placeholder="Income Statement"
																				maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Cash Flow Statement<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="cash_flow_statement" id="cash_flow_statement"
																				class="form-control"
																				placeholder="Cash Flow Statement" maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Projected Balance Sheets<strong
																				class="mandatory">*</strong></label> <input type="text"
																				name="balance_sheet" id="balance_sheet"
																				class="form-control"
																				placeholder="Projected Balance Sheets"
																				maxlength="15">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">College account statement<strong
																				class="mandatory">*</strong></label> <input type="file"
																				id="college_acct_statement1"
																				name="college_acct_statement1" class="form-control"
																				accept=".pdf"> <input type="hidden"
																				id="hid_college_acct_statement1"
																				name="hid_college_acct_statement1"
																				class="form-control" value="">

																			<div class="note-text">
																				<span class="errorClass"
																					id="college_acct_statement_lbl">${doc_path_doc1_msg}</span>
																				<span class='tikClass'
																					id="college_acct_statement_lbltik"></span>
																			</div>
																		</div>
																	</div>
																	<!-- end col -->
																	<!-- end row -->
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>

										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-12">
													<ul class="buttons-group mainbtn">
														<li><input
															class="main-btn info-btn btn-hover btnsave"
															id="college_financial_details_save" type="button"
															value="Save"></li>
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
								<!-- Financial Details End-->
								<!-- Bank Accounts Start-->
								<div class="tab">
									<button class="tab-toggle">Bank Accounts</button>
								</div>
								<div class="content">
									<h4 class="heading">Bank Accounts</h4>
									<form:form name="bank_college" id="bank_college"
										action="College_BankAccount_Details_Action" method="post"
										class="" commandName="">
										<div class="row">
											<input type="hidden" id="hid_bankdtl" name="hid_bankdtl"
												value="0"> <input type="hidden" id="indno_library"
												name="indno_library" value="0">
											<div class="col-lg-12 col-md-12 col-sm-12 col-12">
												<div class="table-wrapper table-responsive custom-table">
													<table class="table" id="bankaccount_table">
														<thead>
															<tr>
																<th><h6>Sr No</h6></th>

																<th><h6>
																		Account No<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Bank Name<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		IFSC<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		(Saving/Current)<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Branch Address<strong class="mandatory">*</strong>
																	</h6></th>
																<th><h6>
																		Bank Audit Statement of Account<strong
																			class="mandatory">*</strong>
																	</h6></th>
																<th><h6>Action</h6></th>
															</tr>
															<!-- end table row-->
														</thead>
														<tbody id="">
															<tr id="bankaccount1">
																<td>
																	<p>1</p>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="account_no1" id="account_no1"
																			class="form-control" placeholder="Account Number"
																			minlength="8" maxlength="20" required>
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="bank_name1" id="bank_name1"
																			class="form-control" placeholder="Bank Name"
																			maxlength="100">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="ifsc_code1" id="ifsc_code1"
																			class="form-control" placeholder="IFSC"
																			maxlength="25">
																	</div>
																</td>
																<td>
																	<div class="select-style-1">
																		<div class="select-position">
																			<select name="saving_current_acct1"
																				id="saving_current_acct1" class="form-control">
																				<option value="0" selected="selected">--
																					Select--</option>
																				<option value="1">Saving</option>
																				<option value="2">Current</option>
																			</select>
																		</div>
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="text" name="branch_addr1"
																			id="branch_addr1" class="form-control"
																			placeholder="Branch Address" maxlength="100">
																	</div>
																</td>
																<td>
																	<div class="input-style-1">
																		<input type="file" id="bank_audit1" name="bank_audit1"
																			class="form-control" accept=".pdf"> <input
																			type="hidden" id="hid_bank_audit1"
																			name="hid_bank_audit1" value="0">

																		<div class="note-text">
																			<span class="errorClass" id="bank_audit_lbl">${doc_path_doc1_msg}</span>
																			<span class='tikClass' id="bank_audit_lbltik"></span>
																		</div>

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
																			value="ADD" title="ADD" id="add_bankaccount1"> <i
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
														class="form-control autocomplete" value="1"> <input
														type="hidden" id="hid_bankdtl1" name="hid_bankdtl1"
														value="0" class="form-control autocomplete"
														autocomplete="off">
												</div>
												<!-- end card -->
											</div>
											<!-- end col -->
										</div>
										<div class="footer_btn">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12"></div>
											</div>
										</div>
									</form:form>
								</div>
								<!-- Bank Accounts End-->
								<!-- Last Financial Year Expenses Start-->
								<div class="tab">
									<button class="tab-toggle">Last Financial Year
										Expenses</button>
								</div>
								<div class="content">
									<h4 class="heading">Last Financial Year Expenses</h4>
									<form:form name="expenses_college" id="expenses_college"
										action="College_last_financial_expenses_Action" method="post"
										class="" commandName="">
										<div class="row">
											<input type="hidden" id="hid_lastfinancialYear"
												name="hid_lastfinancialYear" value="0">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<div class="accordion accordion-flush"
													id="accordionFlushExample1">

													<div class="accordion-item accordion-itemflush">
														<h2 class="accordion-header" id="flush-headingsix">
															<button class="accordion-button accordion-secondary-btn"
																type="button" data-bs-toggle="collapse"
																data-bs-target="#flush-collapsesix" aria-expanded="true"
																aria-controls="flush-collapsesix">Expenses on
																Staff</button>
														</h2>
														<div id="flush-collapsesix"
															class="accordion-collapse collapse show"
															aria-labelledby="flush-headingsix"
															data-bs-parent="#accordionFlushExample1">
															<div class="accordion-body">
																<div class="tables-wrapper">
																	<input type="hidden" id="hid_salarystaff1"
																		name="hid_salarystaff1" value="0"> <input
																		type="hidden" id="indno_auxi" name="indno_auxi"
																		value="0">
																	<div class="row">
																		<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																			<div
																				class="table-wrapper table-responsive custom-table">
																				<table class="table" id="expstaff_table">
																					<thead>
																						<tr>
																							<th><h6>Sr No</h6></th>
																							<th><h6>
																									Year<strong class="mandatory">*</strong>
																								</h6></th>
																							<th><h6>
																									Type of Staff Salary<strong class="mandatory">*</strong>
																								</h6></th>
																							<th><h6>
																									Total Salary<strong class="mandatory">*</strong>
																								</h6></th>
																							<th><h6>
																									Attachment<strong class="mandatory">*</strong>
																								</h6></th>
																							<th><h6>
																									Salary statement of Teaching, non-teaching and
																									hospital staff<strong class="mandatory">*</strong>
																								</h6></th>

																							<th><h6>Action</h6></th>
																						</tr>
																						<!-- end table row-->
																					</thead>
																					<tbody id="salary_staff1">

																						<tr>
																							<td>
																								<p>1</p>
																							</td>
																							<td>
																								<div class="select-style-1">

																									<div class="select-position">
																										<select class="form-control selectCustom" name="year1"
																											id="year1">
																											<!-- style="text-transform: uppercase" -->
																											<option value="0" selected="selected">--Select
																												Year --</option>
																											<c:forEach var="item" items="${year}"
																												varStatus="num">
																												<option value="${item[0]}" name="${item[0]}">${item[0]}</option>
																											</c:forEach>
																										</select>
																									</div>

																								</div>
																							</td>

																							<td>
																								<div class="select-style-1">
																									<div class="select-position">
																										<select name="staff_salary1"
																											id="staff_salary1" class="form-control">
																											<option value="0" selected="selected">--
																												Select --</option>
																											<option>Teaching Staff Salary</option>
																											<option>Non-Teaching Staff Salary</option>
																											<option>Hospital Staff Salary</option>

																										</select>
																									</div>
																								</div>
																							</td>
																							<td>
																								<div class="input-style-1">
																									<input type="text" name="total_salary1"
																										id="total_salary1"
																										class="form-control total_salry"
																										placeholder="Total Salary" value="0"
																										maxlength="15">
																								</div>
																							</td>

																							<td>
																								<div class="input-style-1">
																									<input type="file" id="attachment1"
																										name="attachment1" class="form-control"
																										accept=".pdf"> <input type="hidden"
																										id="hid_attachment1" name="hid_attachment1"
																										value="0">


																									<div class="note-text">
																										<span class="errorClass" id="attachment_lbl">${doc_path_doc1_msg}</span>
																										<span class='tikClass' id="attachment_lbltik"></span>
																									</div>

																								</div>
																							</td>
																							<td>
																								<div class="input-style-1">
																									<input type="file" id="salary_statment1"
																										name="salary_statment1" class="form-control"
																										accept=".pdf"> <input type="hidden"
																										id="hid_salary_statment1"
																										name="hid_salary_statment1"
																										class="form-control" value="0">
																									<div class="note-text">
																										<span class="errorClass"
																											id="salary_statment_lbl">${doc_path_doc1_msg}</span>
																										<span class='tikClass'
																											id="salary_statment_lbltik"></span>
																									</div>
																								</div>

																							</td>

																							<td class="min-width addminusbut">
																								<ul class="buttons-group mainbtn daobtn action">
																									<li><a
																										class="main-btn success-btn btn-hover btn-sm btnapprove"
																										value="Save" title="SAVE"
																										id="family_save_library_salary1"> <i
																											class="lni lni-checkmark"></i>
																									</a></li>
																									<li><a
																										class="main-btn success-btn btn-hover btn-sm addminusbut custom-d-none"
																										value="ADD" title="ADD"
																										id="add_salary_details1"> <i
																											class="lni lni-plus"></i>
																									</a></li>

																									<li><a
																										class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																										value="Delete" title="Delete"
																										id="library_remove_salary1"><i
																											class="lni lni-trash-can"></i></a></li>

																								</ul>
																							</td>

																						</tr>
																						<!-- end table row -->
																					</tbody>

																				</table>
																				<input type="hidden"
																					id="family_save_library__salaryh1"
																					name="family_save_libraryh1"
																					class="form-control autocomplete" value="1">
																			</div>
																			<!-- end card -->
																		</div>
																		<input type="hidden" id="hid_exp_doc_file"
																			name="hid_exp_doc_file" value="0">
																		<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																			<div class="input-style-1">
																				<label for="fname">Proof of ESI of Teaching,
																					non-teaching and hospital staff<strong
																					class="mandatory">*</strong>
																				</label> <input type="file" id="proof_esi" name="proof_esi"
																					class="form-control" accept=".pdf"> <input
																					type="hidden" id="hid_proof_esi"
																					name="hid_proof_esi" class="form-control" value="">
																				<div class="note-text">
																					<span class="errorClass" id="proof_esi_lbl">${doc_path_doc1_msg}</span>
																					<span class='tikClass' id="proof_esi_lbltik"></span>
																				</div>
																			</div>
																		</div>
																		<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																			<div class="input-style-1">
																				<label for="fname">Form 16 of Teaching,
																					non-teaching and hospital staff<strong
																					class="mandatory">*</strong>
																				</label> <input type="file" id="form_16" name="form_16"
																					class="form-control" accept=".pdf"> <input
																					type="hidden" id="hid_form_16" name="hid_form_16"
																					class="form-control" value="">
																				<div class="note-text">
																					<span class="errorClass" id="form_16_lbl">${doc_path_doc1_msg}</span>
																					<span class='tikClass' id="form_16_lbltik"></span>
																				</div>
																			</div>
																		</div>
																		<div class="col-lg-4 col-md-6 col-sm-12 col-12">
																			<div class="input-style-1">
																				<label for="fname">Proof of PF of Teaching,
																					non-teaching and hospital staff<strong
																					class="mandatory">*</strong>
																				</label> <input type="file" id="proof_pf" name="proof_pf"
																					class="form-control" accept=".pdf"> <input
																					type="hidden" id="hid_proof_pf" name="hid_proof_pf"
																					class="form-control" value="">
																				<div class="note-text">
																					<span class="errorClass" id="proof_pf_lbl">${doc_path_doc1_msg}</span>
																					<span class='tikClass' id="proof_pf_lbltik"></span>
																				</div>
																			</div>
																		</div>
																		<!-- end col -->
																	</div>
																	<!-- end row -->
																</div>



															</div>
														</div>

													</div>

													<div class="accordion-item accordion-itemflush">
														<h2 class="accordion-header" id="flush-headingseven">
															<button
																class="accordion-button accordion-secondary-btn collapsed"
																type="button" data-bs-toggle="collapse"
																data-bs-target="#flush-collapseseven"
																aria-expanded="false"
																aria-controls="flush-collapseseven">Expenses on
																Expenditure</button>
														</h2>
														<div id="flush-collapseseven"
															class="accordion-collapse collapse"
															aria-labelledby="flush-headingseven"
															data-bs-parent="#accordionFlushExample1">
															<div class="accordion-body">
																<div class="row">
																	<input type="hidden" id="hid_purchase1"
																		name="hid_purchase1" value="0"> <input
																		type="hidden" id="indno_pur" name="indno_pur"
																		value="0">
																	<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																		<div
																			class="table-wrapper table-responsive custom-table">
																			<table class="table" id="expenpurchase_table">
																				<thead>
																					<tr>
																						<th><h6>Sr No</h6></th>
																						<th><h6>
																								Year<strong class="mandatory">*</strong>
																							</h6></th>
																						<th><h6>
																								Type Of Expenditure<strong class="mandatory">*</strong>
																							</h6></th>
																						<th><h6>
																								Total Cost<strong class="mandatory">*</strong>
																							</h6></th>
																						<th><h6>
																								Attachment<strong class="mandatory">*</strong>
																							</h6></th>
																						<th><h6>Action</h6></th>

																					</tr>
																					<!-- end table row-->
																				</thead>
																				<tbody id="purchase_exp1">

																					<tr>
																						<td>
																							<p>1</p>
																						</td>

																						<td>
																							<div class="select-style-1">
																								<div class="select-position">
																									<select class="form-control remove_expyear" name="expyear1"
																										id="expyear1">
																										<!-- style="text-transform: uppercase" -->
																										<option value="0" selected="selected">--Select
																											Year --</option>
																										<c:forEach var="item" items="${year}"
																											varStatus="num">
																											<option value="${item[0]}" name="${item[0]}">${item[0]}</option>
																										</c:forEach>
																									</select>
																								</div>

																							</div>
																						</td>

																						<td>
																							<div class="select-style-1">
																								<div class="select-position">
																									<select name="purchase1" id="purchase1"
																										class="form-control">
																										<option value="0" selected="selected">--
																											Select --</option>
																										<option>New Books</option>
																										<option>Raw Drugs</option>
																										<option>Prepared Medicine</option>
																										<option>Lab Chemicals</option>
																										<option>Furniture & Fixture</option>
																										<option>Equipments & Instruments</option>
																										<option>Building Construction &
																											Others</option>
																									</select>
																								</div>
																							</div>
																						</td>
																						<td>
																							<div class="input-style-1">
																								<input type="text" name="total_purchase1"
																									id="total_purchase1"
																									class="form-control total_exp"
																									placeholder="Total Salary" value="0"
																									maxlength="15">
																							</div>
																						</td>

																						<td>
																							<div class="input-style-1">
																								<input type="file" id="purchase_attachment1"
																									name="purchase_attachment1"
																									class="form-control" accept=".pdf"> <input
																									type="hidden" id="hid_pur_attachment1"
																									name="hid_pur_attachment1" value="0">
																								<div class="note-text">
																									<span class="errorClass"
																										id="purchase_attachment_lbl">${doc_path_doc1_msg}</span>
																									<span class='tikClass'
																										id="purchase_attachment_lbltik"></span>
																								</div>

																							</div>
																						</td>

																						<td class="min-width addminusbut">
																							<ul class="buttons-group mainbtn daobtn action">
																								<li><a
																									class="main-btn success-btn btn-hover btn-sm btnapprove"
																									value="Save" title="SAVE"
																									id="family_save_library_pur1"> <i
																										class="lni lni-checkmark"></i>
																								</a></li>
																								<li><a
																									class="main-btn success-btn btn-hover btn-sm addminusbut custom-d-none"
																									value="ADD" title="ADD" id="add_pur_details1">
																										<i class="lni lni-plus"></i>
																								</a></li>

																								<li><a
																									class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"
																									value="Delete" title="Delete"
																									id="library_remove_pur1"><i
																										class="lni lni-trash-can"></i></a></li>

																							</ul>
																						</td>

																					</tr>

																				</tbody>
																			</table>
																			<input type="hidden" id="family_save_library_purch1"
																				name="family_save_library_purch1"
																				class="form-control autocomplete" value="1">
																		</div>

																		<!-- end card -->
																	</div>
																	<input type="hidden" id="hid_doc_file"
																		name="hid_doc_file" value="0">
																	<div class="col-lg-6 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Medicine purchase bill for
																				OPD/IPD<strong class="mandatory">*</strong>
																			</label> <input type="file" id="medicine_bill"
																				name="medicine_bill" class="form-control"
																				accept=".pdf"> <input type="hidden"
																				id="hid_medicine_bill" name="hid_medicine_bill"
																				class="form-control" value="">
																			<div class="note-text">
																				<span class="errorClass" id="medicine_bill_lbl">${doc_path_doc1_msg}</span>
																				<span class='tikClass' id="medicine_bill_lbltik"></span>
																			</div>
																		</div>
																	</div>
																	<div class="col-lg-6 col-md-6 col-sm-12 col-12">
																		<div class="input-style-1">
																			<label for="fname">Book purchase bill<strong
																				class="mandatory">*</strong></label> <input type="file"
																				id="book_bill" name="book_bill" class="form-control"
																				accept=".pdf"> <input type="hidden"
																				id="hid_book_bill" name="hid_book_bill"
																				class="form-control" value="">
																			<div class="note-text">
																				<span class="errorClass" id="book_bill_lbl">${doc_path_doc1_msg}</span>
																				<span class='tikClass' id="book_bill_lbltik"></span>
																			</div>
																		</div>
																	</div>
																	<!-- end col -->
																</div>

																<div class="row">
																	<input type="hidden" id="hid_misce_expenditure1"
																		name="hid_misce_expenditure1" value="0"> <input
																		type="hidden" id="indno_misce_expenditure"
																		name="indno_misce_expenditure" value="0">

																	<div class="row">
																		<div class="col-lg-6 col-md-6 col-sm-12 col-12">
																			<div class="input-style-1">
																				<label for="fname">Grand Total of
																					Expenditure in last financial year<strong
																					class="mandatory">*</strong>
																				</label> <input type="text" name="grand_total_expenditure"
																					id="grand_total_expenditure" class="form-control"
																					placeholder="Grand Total" value="0" readonly>
																			</div>
																		</div>
																		<div class="col-lg-6 col-md-6 col-sm-12 col-12">
																			<div class="input-style-1">
																				<label for="fname">Total Income in last
																					financial year<strong class="mandatory">*</strong>
																				</label> <input type="text" name="total_income_last_year"
																					id="total_income_last_year" class="form-control"
																					placeholder="Total Income" value="0" readonly>
																			</div>
																		</div>
																	</div>
																</div>

															</div>

														</div>

													</div>
													<div class="footer_btn">
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-12">
																<ul class="buttons-group mainbtn">
																	<li><input
																		class="main-btn info-btn btn-hover btnsave"
																		id="lastyear_expenses_details_save" type="button"
																		value="Save"></li>
																</ul>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>

									</form:form>
								</div>
							</div>
						</div>

					</div>

				</div>
			</div>
		</div>
	</section>
	<!--  components end -->

	<c:url value="View_Search_College_FinancialUrl" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewForm"
		name="viewForm" modelAttribute="college_financial_view_id">
		<input type="hidden" name="college_financial_id"
			id="college_financial_view_id" value="0" />
		<input type="hidden" name="statusview" id="statusview" value="0" />
	</form:form>
	<script type="text/javascript"
		src="assets/vendor/vtab_with_accordion/vtab_with_acco_form.js"></script>
</body>
<script type="text/javascript" nonce="${cspNonce}">
	$(document)
			.ready(
					function() {
						
						
						document.querySelectorAll('.total_exp').forEach((items, index) => {
							items.addEventListener('blur', event => {
								Total_Miscellanous_Expenses();
							})
						});
						document.querySelectorAll('.total_salry').forEach((items, index) => {
							items.addEventListener('blur', event => {
								Total_Income_lastYear();
							})
						});
						
						
						if ('${role}'=='NCH') {
							$(".viewData").addClass("d-none")
						}
						if ('${role}'=='Institute_NCH') {
							$(".viewData").removeClass("d-none")
						} 
						
						$.ajaxSetup({
							async : false
						});
						
						var valid_dt = '${valid_dt}'; 
						var y = valid_dt.substring(0, 4);
						var m = valid_dt.substring(5, 7);
						var d = valid_dt.substring(8, 10);
						var valid_dt1 = d + "/" + m + "/" + y;
						var today = new Date();
						var yyyy = today.getFullYear();
						var mm = today.getMonth() + 1; // Months start at 0!
						var dd = today.getDate();
						if (dd < 10)
							dd = '0' + dd;
						if (mm < 10)
							mm = '0' + mm;
						today = dd + '/' + mm + '/' + yyyy;
						var valid_dt2 = valid_dt.split('/').reverse().join('-');
						var today2 = today.split('/').reverse().join('-');
						if ("${hid}" != "3" && valid_dt2 <= today2) {
							datepicketDate('date_permission_s_govt');
							datepicketDate('date_permission_c_govt');
							datepicketDate('date_affili_uni');
							datepicketDate('date_consent_affili_uni');
							datepicketDate('dob_id');
							datepicketDate('doj_id');
						}
						
						
						getSalary_Details();
						getPurchase_Details();
						getBank_Details();
						$("#basic_info_id").val('${basic_info_id}');

						var dataparent_finance = '${dataparent_finance}';

						if (dataparent_finance != "[]") {

							//-------------Start----------------Data Fetch For Financial Details----------------
							$("#hid_financial_council").val(
									'${dataparent_finance[0].id}');
							$("#hid_doc_file").val('${dataparent_finance[0].id}');
							$("#fix_deposite").val(
									'${dataparent_finance[0].fix_deposite}');
							$("#current_acct").val(
									'${dataparent_finance[0].current_acct}');
							$("#saving_acct").val(
									'${dataparent_finance[0].saving_acct}');
							$("#project_cost").val(
									'${dataparent_finance[0].project_cost}');
							$("#capital_cost_land")
									.val(
											'${dataparent_finance[0].capital_cost_land}');
							$("#building_cost").val(
									'${dataparent_finance[0].building_cost}');
							$("#plants_machinery")
									.val(
											'${dataparent_finance[0].plants_machinery}');
							$("#equipments").val(
									'${dataparent_finance[0].equipments}');
							$("#furniture_fixer").val(
									'${dataparent_finance[0].furniture_fixer}');
							$("#preliminary_operative_cost")
									.val(
											'${dataparent_finance[0].preliminary_operative_cost}');
							$("#others").val('${dataparent_finance[0].others}');
							$("#contribution_applicants")
									.val(
											'${dataparent_finance[0].contribution_applicants}');
							$("#grants").val('${dataparent_finance[0].grants}');
							$("#donation").val(
									'${dataparent_finance[0].donation}');
							$("#equity").val('${dataparent_finance[0].equity}');
							$("#term_loan").val(
									'${dataparent_finance[0].term_loan}');
							$("#other_source").val(
									'${dataparent_finance[0].other_source}');
							$("#fee_structure").val(
									'${dataparent_finance[0].fee_structure}');
							$("#hospital_charges")
									.val(
											'${dataparent_finance[0].hospital_charges}');
							$("#annual_revenue").val(
									'${dataparent_finance[0].annual_revenue}');
							$("#operating_expenses")
									.val(
											'${dataparent_finance[0].operating_expenses}');
							$("#depreciation").val(
									'${dataparent_finance[0].depreciation}');
							$("#income_statement")
									.val(
											'${dataparent_finance[0].income_statement}');
							$("#cash_flow_statement")
									.val(
											'${dataparent_finance[0].cash_flow_statement}');
							$("#balance_sheet").val(
									'${dataparent_finance[0].balance_sheet}');
							$("#hid_college_acct_statement1")
							.val(
									'${dataparent_finance[0].college_acct_statement}');
							$("#hid_medicine_bill")
							.val(
									'${dataparent_finance[0].medicine_bill}');
							$("#hid_book_bill")
							.val(
									'${dataparent_finance[0].book_bill}');
							
							$("#hid_proof_esi")
							.val(
									'${dataparent_finance[0].proof_esi}');
							
							$("#hid_form_16")
							.val(
									'${dataparent_finance[0].form_16}');
							
							$("#hid_proof_pf")
							.val(
									'${dataparent_finance[0].proof_pf}');

							//---------EnD------------------Data Fetch For Basic Institution Details----------------		
						}

						Total_Miscellanous_Expenses();
						Total_Income_lastYear();
						
					});

						document.addEventListener('DOMContentLoaded',function() {
						
							//Abhishekbhai
							document.querySelectorAll('.selectCustom').forEach((items, index) => {
								items.addEventListener('change', event => {
									remove_year("selectCustom","year");
								})
							});
							
							document.querySelectorAll('.remove_expyear').forEach((items, index) => {
								items.addEventListener('change', event => {
									Remove_Expyear("remove_expyear","expyear");
								})
							});
							//Abhishekbhai
						document.getElementById('add_salary_details1').onclick = function() {
							return formOpen_salary_medical(1);
						};

						document.getElementById('library_remove_salary1').onclick = function() {
							return Delete_Salary_Details(1);
						};

						document.getElementById('family_save_library_salary1').onclick = function() {
							return Save_As_Draft_Salary_Details(1);
						};
						
						
						
						document.getElementById('add_pur_details1').onclick = function() {
							return formOpen_purchase_medical(1);
						};

						document.getElementById('library_remove_pur1').onclick = function() {
							return Delete_purchase_Details(1);
						};

						document.getElementById('family_save_library_pur1').onclick = function() {
							return Save_As_Draft_purchase_Details(1);
						};
						
						document.getElementById('college_acct_statement1').onchange = function() {
							return pdfFileSizeValidation(this,this.value,"college_acct_statement1","200kb","college_acct_statement_lbltik","college_acct_statement_lbl",this.accept)
						};
						

						//COLLEGE financial START
						document.getElementById('college_financial_details_save').onclick = function() {
							return Save_As_Draft_College_financial_Details();
						};
						
						document.getElementById('lastyear_expenses_details_save').onclick = function() {
						return Save_As_Draft_last_Finance_Year_Document_Details();
						};

						document.getElementById('add_bankaccount1').onclick = function() {
							return formOpen_bankacct(1);
						};

						document.getElementById('library_remove1').onclick = function() {
							return Delete_Bank_Details(1);
						};

						document.getElementById('family_save_library1').onclick = function() {
							return Save_As_Draft_bankdtls(1);
						};

						
						//-----------------------------Start Financial Details csp------------------	
						document.getElementById('fix_deposite').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('current_acct').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('saving_acct').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
					
						
						document.getElementById('project_cost').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('capital_cost_land').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('building_cost').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						
						document.getElementById('plants_machinery').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('equipments').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('furniture_fixer').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						
						document.getElementById('preliminary_operative_cost').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('others').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('fee_structure').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						
						document.getElementById('hospital_charges').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('annual_revenue').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('operating_expenses').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						
						document.getElementById('depreciation').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						
						document.getElementById('contribution_applicants').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('grants').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('donation').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						
						document.getElementById('equity').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('term_loan').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('other_source').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('income_statement').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('cash_flow_statement').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('balance_sheet').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						//-------------------------------End Financial Details csp------------------	
						
						
						//-------------------------------Start  Bank Accounts csp------------------	
						
						document.getElementById('account_no1').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('ifsc_code1').onkeypress = function() {
							return onlyAlphaNumericwithslash(event, this);
						};
						document.getElementById('bank_name1').onkeypress = function() {
							return onlyAlphabetsStringSpace(event, this);
						};
						
						document.getElementById('branch_addr1').onkeypress = function() {
							return onlyAlphaNumericwithslash(event, this);
						};
						document.getElementById('total_salary1').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						

						document.getElementById('attachment1').onchange = function() {
							return pdfFileSizeValidation(this,this.value,"attachment1","200kb","attachment_lbltik","attachment_lbl",this.accept)
						};
						document.getElementById('salary_statment1').onchange = function() {
							return pdfFileSizeValidation(this,this.value,"salary_statment1","200kb","salary_statment_lbltik","salary_statment_lbl",this.accept)
						};
						
						document.getElementById('proof_pf').onchange = function() {
							return pdfFileSizeValidation(this,this.value,"proof_pf","200kb","proof_pf_lbltik","proof_pf_lbl",this.accept)
						};
						
						document.getElementById('proof_esi').onchange = function() {
							return pdfFileSizeValidation(this,this.value,"proof_esi","200kb","proof_esi_lbltik","proof_esi_lbl",this.accept)
						};
						
						document.getElementById('form_16').onchange = function() {
							return pdfFileSizeValidation(this,this.value,"form_16","200kb","form_16_lbltik","form_16_lbl",this.accept)
						};
						document.getElementById('purchase_attachment1').onchange = function() {
							return pdfFileSizeValidation(this,this.value,"purchase_attachment1","200kb","purchase_attachment_lbltik","purchase_attachment_lbl",this.accept)
						};
						document.getElementById('medicine_bill').onchange = function() {
							return pdfFileSizeValidation(this,this.value,"proof_esi","200kb","medicine_bill_lbltik","medicine_bill_lbl",this.accept)
						};
						
						document.getElementById('book_bill').onchange = function() {
							return pdfFileSizeValidation(this,this.value,"form_16","200kb","book_bill_lbltik","book_bill_lbl",this.accept)
						};
						
						document.getElementById('total_purchase1').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						
						document.getElementById('grand_total_expenditure').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('total_income_last_year').onkeypress = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('bank_audit1').onchange = function() {
							return pdfFileSizeValidation(this,this.value,"bank_audit1","200kb","bank_audit_lbltik","bank_audit_lbl",this.accept)
						};
					});
	
	
//-------------------------------Start Bank Accounts  ADD MORE-------------------------------------------------------------------------

function Total_Miscellanous_Expenses(){
	
	var grantotal=0;
	const collection = document.getElementsByClassName("total_exp");
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#grand_total_expenditure").val(grantotal);
	
}

function Total_Income_lastYear(){
	var grantotal=0;
	const collection = document.getElementsByClassName("total_salry");
	for (let i = 0; i < collection.length; i++) {
	  if(collection[i].value!=''){
		  grantotal=grantotal+ parseInt(collection[i].value)
	  }
	}
	$("#total_income_last_year").val(grantotal);
	
}
//SAVE COLLEGE Bank Details-----------------------------------

	function formOpen_bankacct(R) {

		$("#bankaccount_table").show();

		att = 0;

		$("#add_bankaccount" + R).hide();
		$("#library_remove" + R).hide();
		att = parseInt($("#family_save_libraryh1").val()) + 1;
		att = parseInt(R) + 1;
// 		alert(att);

		if (att < 21) {

			$("input#family_save_libraryh1").val(att);//current serial No
			$("table#bankaccount_table")
					.append(
							'<tr id="bankaccount1'+att+'">'

									+ '<td class="min-width"><div class="lead"><div class="lead-text"><p>'
									+ att
									+ '</p></div></div></td>'
									+ '<td><div class="input-style-1"><input type="text" id="account_no'+att+'" name="account_no'+att+'" class="form-control"' 
					+'autocomplete="off" placeholder="Account Number" minlength="8"  maxlength="20"></div></td>'

									+ '<td><div class="input-style-1"><input type="text" id="bank_name'+att+'" name="bank_name'+att+'"'
					+'class="form-control" autocomplete="off" placeholder="Bank Name"></div></td>'

									+ '<td><div class="input-style-1"><input type="text" id="ifsc_code'+att+'" name="ifsc_code'+att+'"'
					+'class="form-control" autocomplete="off" placeholder="IFSC"></div></td>'

									+ '<td><div class="select-style-1"><div class="select-position"><select id="saving_current_acct'+att+'" name="saving_current_acct'+att+'" class=""><option value="0">--Select--</option><option value="1">Saving</option>'
									+ '<option value="2">Current</option></select></div></div></td>'

									+ '<td><div class="input-style-1"><input type="text" id="branch_addr'+att+'" name="branch_addr'+att+'"'
					+'class="form-control" autocomplete="off" placeholder="Branch Address"></div></td>'
					
					+'<td><div class="input-style-1"><input type="file" id="bank_audit'+att+'" name="bank_audit'+att+'" class="form-control" accept=".pdf">'
					+'<input type=hidden id="hid_bank_audit'+att+'" name="hid_bank_audit'+att+'" class="form-control" value="">'
					+'<div class="note-text"><span class="errorClass" id="bank_audit_lbl'+att+'">${doc_path_doc1_msg}</span>'
				 	+'<span class="tikClass" id="bank_audit_lbltik'+att+'"></span></div></div></td>'
				 	
									+ '<td class="addminusbut"><ul class="buttons-group mainbtn daobtn action"><li><a class="main-btn success-btn btn-hover btn-sm btnapprove"'
					+'value="Save" title="SAVE" id="family_save_library'+att+'"><i class="lni lni-checkmark"></i></a></li>'
									+ '<li><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" value="ADD" title="ADD" id="add_bankaccount'+att+'">'
									+ '<i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"'
					+'value="Delete" title="Delete" id="library_remove'+att+'">'
									+ '<input type="hidden" id="family_save_libraryh'+att+'" name="family_save_libraryh'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
									+ '<input type="hidden" id="hid_bankdtl'+att+'" name="hid_bankdtl'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
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
		document.getElementById('add_bankaccount' + att).onclick = function() {
			return formOpen_bankacct(att);
		};
		document.getElementById('library_remove' + att).onclick = function() {
			return Delete_Bank_Details(att);
		};
		document.getElementById('family_save_library' + att).onclick = function() {
			Save_As_Draft_bankdtls(att);
		};
		document.getElementById('account_no'+ att).onkeypress = function() {
			return isNumberKey0to9(event, this);
		};
		document.getElementById('ifsc_code'+ att).onkeypress = function() {
			return onlyAlphaNumericwithslash(event, this);
		};
		document.getElementById('bank_name'+ att).onkeypress = function() {
			return onlyAlphabetsStringSpace(event, this);
		};
		
		document.getElementById('branch_addr'+ att).onkeypress = function() {
			return onlyAlphaNumericwithslash(event, this);
		};
		document.getElementById('bank_audit'+ att).onchange = function() {
			return pdfFileSizeValidation(this,this.value,"bank_audit"+ att,"200kb","bank_audit_lbltik"+ att,"bank_audit_lbl"+ att,this.accept)
		};
		
	}
	
	
	function Save_As_Draft_bankdtls(ps) {
		$.ajaxSetup({
			async : false
		});
	 	var hid_financial_council = $("#hid_financial_council").val();
	 	if(hid_financial_council == "0"){
	 		alert("Please Save Financial Details First");
	 		return false;
	 	}
		var res = CheckNullorBlank('account_no' + ps);
		if (res !== "true") {
			alert(res + "Account Number in Bank Accounts Tab");
			$('#account_no' + ps).focus();
			return false;
		}
		var res = CheckNullorBlank('bank_name' + ps);
		if (res !== "true") {
			alert(res + "Bank Name in Bank Accounts Tab");
			$('#bank_name' + ps).focus();
			return false;
		}
		var res = CheckNullorBlank('ifsc_code' + ps);
		if (res !== "true") {
			alert(res + "IFSC in Bank Accounts Tab");
			$('#ifsc_code' + ps).focus();
			return false;
		}

		if ($("#saving_current_acct" + ps).val().trim() == "0") {
			alert("Please Select Saving/Current Account in Bank Accounts Tab ");
			$('#saving_current_acct' + ps).focus();
			return false;
		}
		var res = CheckNullorBlank('branch_addr' + ps);
		if (res !== "true") {
			alert(res + "Branch Address in Bank Accounts Tab");
			$('#branch_addr' + ps).focus();
			return false;
		}
		
		if ($("#bank_audit"+ ps).val().trim() == "") {
			alert("Please Upload Bank Audit Statement of Account in Bank Accounts Tab");
			$('#bank_audit' + ps).focus();
			return false;
		}

			$("#indno_library").val(ps);
		var form_data = new FormData(document.getElementById("bank_college"));
		$.ajax({
			url : 'College_BankAccount_Details_Action?_csrf=' + value,
			type : "POST",
			data : form_data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false
		}).done(function(j) {
					if(j>0){
						$("#hid_bankdtl"+ps).val(j);
						alert("Data Saved Successfully");
						$("#add_bankaccount" + ps).show();
						$("#library_remove" + ps).show();
		          }
		          else{
		        	  alert(j);
		          }
			}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			});

	}

	function Delete_Bank_Details(R) {

		if (confirm('Are you sure you want to delete?')) {

			var hid_bankdtl = $('#hid_bankdtl' + R).val();

			$.post("delete_bank_Details?" + key + "=" + value, {
				hid_bankdtl : hid_bankdtl
			}, function(j) {
				alert(j);
			});

			$("tr#bankaccount1" + R).remove();
			R = R - 1;
			$("input#count_hidden_att_name_med").val(att);
			$("#add_bankaccount" + R).show();
			$("#library_remove" + R).show();
		} else {
			return false;
		}
	}

	function getBank_Details() {

		$.ajaxSetup({
			async : false
		});

		var ser = 0;

		$.post("getBank_Details?" + key + "=" + value, {

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				ser = i + 1;

				if (j.length > 0) {

					if (ser > 1) {

						$("#add_bankaccount" + (ser - 1)).click();
					}

					$("#hid_bankdtl" + ser).val(j[i].id);
					$("#account_no" + ser).val(j[i].account_no);
// 					alert(j[i].account_no);
					$("#bank_name" + ser).val(j[i].bank_name);
					$("#ifsc_code" + ser).val(j[i].ifsc_code);
					$("#saving_current_acct" + ser).val(
							j[i].saving_current_acct);
					$("#branch_addr" + ser).val(j[i].branch_addr);
					$("#hid_bank_audit" + ser).val(j[i].bank_audit);
					$("#add_bankaccount" + ser).show();
					$("#library_remove" + ser).show();
				}
			}
		});
	}
	
	function inyear() {
		 var currentYear = new Date().getFullYear()
		    max = currentYear 
		    var option = "";
		    for (var year = currentYear-10 ; year <= max; year++) {
		        var option = document.createElement("option");
		        option.text = year;
		        option.value = year;	        
		        document.getElementById("passing_year").appendChild(option)	        
		    }
		   // document.getElementById("passing_year").value = currentYear;	
	}
	
//--------------------------------End Total of Last Financial Year Expenses------------------------------------------------

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
	
	$("#college_financial_view_id").val(id);
	$("#statusview").val($("#statusA").val());
	document.getElementById('viewForm').submit();
}


//SAVE COLLEGE Salary  Expenses-----------------------------------

function formOpen_salary_medical(R) {

	$("#expstaff_table").show();

	att = 0;

	$("#add_salary_details"+R).hide();
	$("#library_remove_salary"+R).hide();
	att = parseInt($("#family_save_library_salaryh1").val()) + 1;
	att = parseInt(R) + 1;
//		alert(att);

	if (att < 21) {

		$("input#family_save_library_salaryh1").val(att);//current serial No
		$("table#expstaff_table")
				.append(
						'<tr id="salary_staff1'+att+'"><td><p>'+att+'</p></td><td><div class="select-style-1">'
						+'<div class="select-position">'
						+'<select class="form-control selectCustom" name="year'+att+'" id="year'+att+'"><option value="0" selected="selected">--Select Year --</option>'
							+'<c:forEach var="item" items="${year}" varStatus="num"><option value="${item[0]}" name="${item[0]}">${item[0]}</option></c:forEach></select></div></div></td>'

						+'<td><div class="select-style-1"><div class="select-position"><select name="staff_salary'+att+'" id="staff_salary'+att+'" class="form-control"><option value="0" selected="selected">--Select --</option>'
						+'<option>Teaching Staff Salary</option><option>Non-Teaching Staff Salary</option><option>Hospital Staff Salary</option></select></div></div></td>'
						
						+ '<td><div class="input-style-1"><input type="text" id="total_salary'+att+'" name="total_salary'+att+'"'
						+'class="form-control total_salry" autocomplete="off" placeholder="Enter Total Salary" maxlength="15"></div></td>'
						
						+'<td><div class="input-style-1"><input type="file" id="attachment'+att+'" name="attachment'+att+'" class="form-control" accept=".pdf">'
						+'<input type=hidden id="hid_attachment'+att+'" name="hid_attachment'+att+'" class="form-control" value="">'
					 	
					 	+'<div class="note-text"><span class="errorClass" id="attachment_lbl'+att+'">${doc_path_doc1_msg}</span>'
					 	+'<span class="tikClass" id="attachment_lbltik'+att+'"></span></div></div></td>'
						
						+'<td><div class="input-style-1"><input type="file" id="salary_statment'+att+'" name="salary_statment'+att+'" class="form-control" accept=".pdf">'
						+'<input type=hidden id="hid_salary_statment'+att+'" name="hid_salary_statment'+att+'" class="form-control" value="">'
						
						+'<div class="note-text"><span class="errorClass" id="salary_statment_lbl'+att+'">${doc_path_doc1_msg}</span>'
					 	+'<span class="tikClass" id="salary_statment_lbltik'+att+'"></span></div></div></td>'

								+ '<td class="addminusbut"><ul class="buttons-group mainbtn daobtn action"><li><a class="main-btn success-btn btn-hover btn-sm btnapprove"'
						+'value="Save" title="SAVE" id="family_save_library_salary'+att+'"><i class="lni lni-checkmark"></i></a></li>'
								+ '<li><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" value="ADD" title="ADD" id="add_salary_details'+att+'">'
								+ '<i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"'
						+'value="Delete" title="Delete" id="library_remove_salary'+att+'">'
								+ '<input type="hidden" id="family_save_library_salaryh'+att+'" name="family_save_library_salaryh'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
								+ '<input type="hidden" id="hid_salarystaff'+att+'" name="hid_salarystaff'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
								+ '<i class="lni lni-trash-can"></i></a></li></ul></td>'
								+ '</tr>');

		//Abhishekbhai
			document.querySelectorAll('.selectCustom').forEach((items, index) => {
								items.addEventListener('change', event => {
									remove_year("selectCustom","year");
								})
							});
			remove_year("selectCustom","year");
		//Abhishekbhai
		addOnclick_salary_dtls(att);
		document.querySelectorAll('.total_salry').forEach((items, index) => {
			items.addEventListener('blur', event => {
				Total_Income_lastYear();
			})
		});
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

		$("#family_save_library_salaryh1").val(att);

	} else {
		alert("You can not enter more than twenty times");
		if (att == 21) {
			att = att - 1;
			$("#rp_id_remove" + att).show();
		}
	}
}

function addOnclick_salary_dtls(att) {
	document.getElementById('add_salary_details' + att).onclick = function() {
		return formOpen_salary_medical(att);
	};
	document.getElementById('library_remove_salary' + att).onclick = function() {
		return Delete_Salary_Details(att);
	};
	document.getElementById('family_save_library_salary' + att).onclick = function() {
		Save_As_Draft_Salary_Details(att);
	};
	document.getElementById('total_salary'+ att).onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	
	document.getElementById('attachment'+ att).onchange = function() {
		return pdfFileSizeValidation(this,this.value,"attachment"+ att,"200kb","attachment_lbltik"+ att,"attachment_lbl"+ att,this.accept)
	};
	
	document.getElementById('salary_statment'+ att).onchange = function() {
		return pdfFileSizeValidation(this,this.value,"salary_statment"+ att,"200kb","salary_statment_lbltik"+ att,"salary_statment_lbl"+ att,this.accept)
	};
	
}
//Abhishekbhai
function setTimeLoadForTable(){
	document.querySelectorAll('.selectCustom').forEach((items, index) => {
		items.addEventListener('change', event => {
			remove_year("selectCustom","year");
		})
	});
	document.querySelectorAll('.remove_expyear').forEach((items, index) => {
		items.addEventListener('change', event => {
			Remove_Expyear("remove_expyear","expyear");
		})
	});
}

//Abhishekbhai
function Save_As_Draft_Salary_Details(ps) {
	$.ajaxSetup({
		async : false
	});
 	var hid_financial_council = $("#hid_financial_council").val();
 	if(hid_financial_council == "0"){
 		alert("Please Save Financial Details First");
 		return false;
 	}
	if ($("#year" + ps).val().trim() == "0") {
		alert("Please Select Year in Expenses on Staff Tab.");
		$("#year"+ps).focus();
		return false;
	}
	if ($("#staff_salary" + ps).val().trim() == "0") {
		alert("Please Select Type of Staff Salary of Expenses on Staff");
		$("#staff_salary"+ps).focus();
		return false;
	}

	var res = CheckNullorBlank('total_salary' + ps);
	if (res !== "true") {
		alert(res + "Total Salary in Expenses on Staff Tab");
		$('#total_salary' + ps).focus();
		return false;
	}

	if ($("#attachment" + ps).val().trim() == "") {
		alert("Please Upload Expenses Attachment in Expenses on Staff Tab.");
		$("#attachment"+ps).focus();
		return false;
	}
	if ($("#salary_statment" + ps).val().trim() == "") {
		alert("Please Upload Salary statement of Teaching, non-teaching and hospital staff in Expenses on Staff Tab");
		$("#salary_statment"+ps).focus();
		return false;
	}

	
	$("#indno_auxi").val(ps);
	var form_data = new FormData(document.getElementById("expenses_college"));
	$.ajax({
		url : 'College_last_financial_expenses_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
				if(j>0){
					$("#hid_salarystaff"+ps).val(j);
					alert("Data Saved Successfully");
					$("#add_salary_details"+ps).show();
					$("#library_remove_salary"+ps).show();
	          }
	          else{
	        	  alert(j);
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});

}

function Delete_Salary_Details(R) {

	if (confirm('Are you sure you want to delete?')) {

		var hid_salarystaff = $('#hid_salarystaff' + R).val();

		$.post("Delete_Salary_Details?" + key + "=" + value, {
			hid_salarystaff : hid_salarystaff
		}, function(j) {
			alert(j);
		});

		$("tr#salary_staff1" + R).remove();
		R = R - 1;
		$("input#count_hidden_att_name_med").val(att);
		$("#add_salary_details" + R).show();
		$("#library_remove_salary" + R).show();
	} else {
		return false;
	}
}

function getSalary_Details() {

	$.ajaxSetup({
		async : false
	});
	
	var ser = 0;

	$.post("getSalary_Details?" + key + "=" + value, {

	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			ser = i + 1;

			if (j.length > 0) {

				if (ser > 1) {

					$("#add_salary_details" + (ser - 1)).click();
				}

				$("#hid_salarystaff" + ser).val(j[i].id);
				$("#year" + ser).val(j[i].year);
				$("#staff_salary" + ser).val(j[i].staff_salary);
				$("#total_salary" + ser).val(j[i].total_salary);
				$("#hid_attachment" + ser).val(j[i].attachment);
				$("#hid_salary_statment" + ser).val(j[i].salary_statment);
				$("#add_salary_details" + ser).show();
				$("#library_remove_salary" + ser).show();
			}
		}
	});
}

//SAVE COLLEGE Purchase Expenses-----------------------------------

function formOpen_purchase_medical(R) {

	$("#expenpurchase_table").show();

	att = 0;

	$("#add_pur_details"+R).hide();
	$("#library_remove_pur"+R).hide();
	att = parseInt($("#family_save_library_purch1").val()) + 1;
	att = parseInt(R) + 1;
//		alert(att);

	if (att < 21) {

		$("input#family_save_library_purch1").val(att);//current serial No
		$("table#expenpurchase_table")
				.append(
						'<tr id="purchase_exp1'+att+'"><td><p>'+att+'</p></td><td><div class="select-style-1">'
						+'<div class="select-position"><select class="form-control remove_expyear" name="expyear'+att+'" id="expyear'+att+'"><option value="0" selected="selected">--Select Year --</option>'
						+'<c:forEach var="item" items="${year}" varStatus="num"><option value="${item[0]}" name="${item[0]}">${item[0]}</option></c:forEach></select></div></div></td>'

						+'<td><div class="select-style-1"><div class="select-position"><select name="purchase'+att+'" id="purchase'+att+'" class="form-control"><option value="0" selected="selected">--'
						+'Select --</option><option>New Books</option><option>Raw Drugs</option><option>Prepared Medicine</option><option>Lab Chemicals</option><option>Furniture & Fixture</option>'
						+'<option>Equipments & Instruments</option><option>Building Construction & Others</option></select></div></div></td>'
						
						+ '<td><div class="input-style-1"><input type="text" id="total_purchase'+att+'" name="total_purchase'+att+'"'
						+'class="form-control total_exp" autocomplete="off" placeholder="Enter Total Salary" maxlength="15"></div></td>'
						
						+'<td><div class="input-style-1"><input type="file" id="purchase_attachment'+att+'" name="purchase_attachment'+att+'" class="form-control" accept=".pdf">'
						+'<input type=hidden id="hid_pur_attachment'+att+'" name="hid_pur_attachment'+att+'" class="form-control" value="">'
						+'<div class="note-text"><span class="errorClass" id="purchase_attachment_lbl'+att+'">${doc_path_doc1_msg}</span>'
					 	+'<span class="tikClass" id="purchase_attachment_lbltik'+att+'"></span></div></div></td>'

								+ '<td class="addminusbut"><ul class="buttons-group mainbtn daobtn action"><li><a class="main-btn success-btn btn-hover btn-sm btnapprove"'
						+'value="Save" title="SAVE" id="family_save_library_pur'+att+'"><i class="lni lni-checkmark"></i></a></li>'
								+ '<li><a class="main-btn success-btn btn-hover btn-sm btnaddmore custom-d-none" value="ADD" title="ADD" id="add_pur_details'+att+'">'
								+ '<i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm btnremove custom-d-none"'
						+'value="Delete" title="Delete" id="library_remove_pur'+att+'">'
								+ '<input type="hidden" id="family_save_library_purch'+att+'" name="family_save_library_purch'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
								+ '<input type="hidden" id="hid_purchase'+att+'" name="hid_purchase'+att+'" value="0" class="form-control autocomplete" autocomplete="off">'
								+ '<i class="lni lni-trash-can"></i></a></li></ul></td>'
								+ '</tr>');

		//Abhishekbhai
		document.querySelectorAll('.remove_expyear').forEach((items, index) => {
							items.addEventListener('change', event => {
								Remove_Expyear("remove_expyear","expyear");
							})
						});
		Remove_Expyear("remove_expyear","expyear");
	//Abhishekbhai
		addOnclick_pur_dtls(att);
		document.querySelectorAll('.total_exp').forEach((items, index) => {
			items.addEventListener('blur', event => {
				Total_Miscellanous_Expenses();
			})
		});

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

		$("#family_save_library_purch1").val(att);

	} else {
		alert("You can not enter more than twenty times");
		if (att == 21) {
			att = att - 1;
			$("#rp_id_remove" + att).show();
		}
	}
}

function addOnclick_pur_dtls(att) {
	document.getElementById('add_pur_details' + att).onclick = function() {
		return formOpen_purchase_medical(att);
	};
	document.getElementById('library_remove_pur' + att).onclick = function() {
		return Delete_purchase_Details(att);
	};
	document.getElementById('family_save_library_pur' + att).onclick = function() {
		Save_As_Draft_purchase_Details(att);
	};
	document.getElementById('total_purchase'+ att).onkeypress = function() {
		return isNumberKey0to9(event, this);
	};
	document.getElementById('purchase_attachment'+ att).onchange = function() {
		return pdfFileSizeValidation(this,this.value,"purchase_attachment"+ att,"200kb","purchase_attachment_lbltik"+ att,"purchase_attachment_lbl"+ att,this.accept)
	};
	

}

function Save_As_Draft_purchase_Details(ps) {
	$.ajaxSetup({
		async : false
	});
 	var hid_financial_council = $("#hid_financial_council").val();
 	if(hid_financial_council == "0"){
 		alert("Please Save Financial Details First");
 		return false;
 	}
	if ($("#expyear" + ps).val().trim() == "0") {
		alert("Please Select Expenditure Year in Expenses on Expenditure Tab.");
		$("#expyear"+ps).focus();
		return false;
	}

		if ($("#purchase" + ps).val().trim() == "0") {
			alert("Please Select Type of Expenditure in Expenses on Expenditure Tab.");
			$("#purchase"+ps).focus();
			return false;
		}
	var res = CheckNullorBlank('total_purchase' + ps);
	if (res !== "true") {
		alert(res + "Total Cost in Expenses on Expenditure Tab");
		$('#total_purchase' + ps).focus();
		return false;
	}
	if ($("#purchase_attachment" + ps).val().trim() == "") {
		alert("Please Upload Attachment in Expenses on Expenditure Tab.");
		$("#purchase_attachment"+ps).focus();
		return false;
	}

	$("#indno_pur").val(ps);
	var form_data = new FormData(document.getElementById("expenses_college"));
	$.ajax({
		url : 'College_last_financial_Purchase_expenses_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
				if(j>0){
					$("#hid_purchase"+ps).val(j);
					alert("Data Saved Successfully");
					$("#add_pur_details"+ps).show();
					$("#library_remove_pur"+ps).show();
	          }
	          else{
	        	  alert(j);
	          }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});

}

function Delete_purchase_Details(R) {

	if (confirm('Are you sure you want to delete?')) {

		var hid_purchase = $('#hid_purchase' + R).val();

		$.post("Delete_Purchase_Details?" + key + "=" + value, {
			hid_purchase : hid_purchase
		}, function(j) {
			alert(j);
		});

		$("tr#purchase_exp1" + R).remove();
		R = R - 1;
		$("input#count_hidden_att_name_med").val(att);
		$("#add_pur_details" + R).show();
		$("#library_remove_pur" + R).show();
	} else {
		return false;
	}
}

function getPurchase_Details() {

	$.ajaxSetup({
		async : false
	});

	var ser = 0;

	$.post("getPurchase_Details?" + key + "=" + value, {

	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			ser = i + 1;

			if (j.length > 0) {

				if (ser > 1) {

					$("#add_pur_details" + (ser - 1)).click();
				}

				$("#hid_purchase"+ser).val(j[i].id);
				$("#expyear"+ser).val(j[i].expyear);
				$("#purchase" + ser).val(j[i].purchase);
				$("#total_purchase" + ser).val(j[i].total_purchase);
				$("#hid_pur_attachment" + ser).val(j[i].purchase_attachment);
				$("#add_pur_details" + ser).show();
				$("#library_remove_pur" + ser).show();
			}
		}
	});
}

//SAVE COLLEGE Upload_Document_Details-----------------------------------
function Save_As_Draft_last_Finance_Year_Document_Details() {
	
	$.ajaxSetup({
		async : false
	});
	
	
	var hid_financial_council = $("#hid_financial_council").val();
 	if(hid_financial_council == "0"){
 		alert("Please Save Financial Details First");
 		return false;
 	}
	if ($("#proof_esi").val().trim() == "") {
		alert("Please Upload Proof of ESI of Teaching, non-teaching and hospital staff in Last Financial Year Expenses on Staff Tab.");
		$("#proof_esi").focus();
		return false;
	}
	if ($("#form_16").val().trim() == "") {
		alert("Please Upload Form 16 of Teaching, non-teaching and hospital staff in Last Financial Year Expenses on Staff Tab.");
		$("#form_16").focus();
		return false;
	}
	if ($("#proof_pf").val().trim() == "") {
		alert("Please Upload Proof of PF of Teaching, non-teaching and hospital staff in Last Financial Year Expenses on Staff Tab.");
		$("#proof_pf").focus();
		return false;
	}
	if ($("#medicine_bill").val().trim() == "") {
		alert("Please Upload Medicine purchase bill for OPD/IPD in Last Financial Year Expenses on Expenditure Tab.");
		$("#medicine_bill").focus();
		return false;
	}
	if ($("#book_bill").val().trim() == "") {
		alert("Please Upload Book purchase bill in Last Financial Year Expenses on Expenditure Tab.");
		$("#book_bill").focus();
		return false;
	}
	var form_data = new FormData(document
			.getElementById("expenses_college"));
	$.ajax({
		url : 'College_Last_Financial_Doc_Details_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
		if (j > 0) {

			$("#hid_doc_file").val(j);
			alert("Data Save Sucessfully");
			location.reload();
		} else {
			alert(j);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}
//SAVE COLLEGE Financial DETAILS---------------------------------------
function Save_As_Draft_College_financial_Details() {
	$.ajaxSetup({
		async : false
	});
	var basic_info_id = $("#basic_info_id").val();
	if(basic_info_id == "0"){
		alert("Please Save Basic details First");
		return false;
	}
	
	
	var res = CheckNullorBlank('fix_deposite');
	if (res !== "true") {
		alert(res + "Fix Deposits in Financial Capability Tab");
		$('#fix_deposite').focus();
		return false;
	}
	var res = CheckNullorBlank('current_acct');
	if (res !== "true") {
		alert(res + "Current Account in Financial Capability Tab");
		$('#current_acct').focus();
		return false;
	}
	var res = CheckNullorBlank('saving_acct');
	if (res !== "true") {
		alert(res + "Saving Account in Financial Capability Tab");
		$('#saving_acct').focus();
		return false;
	}
	var res = CheckNullorBlank('project_cost');
	if (res !== "true") {
		alert(res + "Project Cost in Proposed Budget for College and Hospital Tab");
		$('#project_cost').focus();
		return false;
	}
	var res = CheckNullorBlank('capital_cost_land');
	if (res !== "true") {
		alert(res + "Capital Cost Of Land in Proposed Budget for College and Hospital Tab");
		$('#capital_cost_land').focus();
		return false;
	}
	var res = CheckNullorBlank('building_cost');
	if (res !== "true") {
		alert(res + "Building Cost in Proposed Budget for College and Hospital Tab");
		$('#building_cost').focus();
		return false;
	}
	var res = CheckNullorBlank('plants_machinery');
	if (res !== "true") {
		alert(res + "Plants & Machinery in Proposed Budget for College and Hospital Tab");
		$('#plants_machinery').focus();
		return false;
	}
	var res = CheckNullorBlank('equipments');
	if (res !== "true") {
		alert(res + "Equipments in Proposed Budget for College and Hospital Tab");
		$('#equipments').focus();
		return false;
	}
	var res = CheckNullorBlank('furniture_fixer');
	if (res !== "true") {
		alert(res + "Furniture & Fixture in Proposed Budget for College and Hospital Tab");
		$('#furniture_fixer').focus();
		return false;
	}
	var res = CheckNullorBlank('preliminary_operative_cost');
	if (res !== "true") {
		alert(res + "Preliminary & Pre Operative Cost in Proposed Budget for College and Hospital Tab");
		$('#preliminary_operative_cost').focus();
		return false;
	}
	var res = CheckNullorBlank('others');
	if (res !== "true") {
		alert(res + "Others Information in Proposed Budget for College and Hospital Tab");
		$('#others').focus();
		return false;
	}

	var res = CheckNullorBlank('contribution_applicants');
	if (res !== "true") {
		alert(res + "Contribution Of Applicant in Means of Financing the Project Tab");
		$('#contribution_applicants').focus();
		return false;
	}
	var res = CheckNullorBlank('grants');
	if (res !== "true") {
		alert(res + "Grants in Means of Financing the Project Tab");
		$('#grants').focus();
		return false;
	}
	var res = CheckNullorBlank('donation');
	if (res !== "true") {
		alert(res + "Donation in Means of Financing the Project Tab");
		$('#donation').focus();
		return false;
	}
	var res = CheckNullorBlank('equity');
	if (res !== "true") {
		alert(res + "Equity in Means of Financing the Project Tab");
		$('#equity').focus();
		return false;
	}
	var res = CheckNullorBlank('term_loan');
	if (res !== "true") {
		alert(res + "Term Loan in Means of Financing the Project Tab");
		$('#term_loan').focus();
		return false;
	}
	var res = CheckNullorBlank('other_source');
	if (res !== "true") {
		alert(res + "Other Source in Means of Financing the Project Tab");
		$('#other_source').focus();
		return false;
	}
	var res = CheckNullorBlank('fee_structure');
	if (res !== "true") {
		alert(res + "Fee Structure in Revenue Generated Tab");
		$('#fee_structure').focus();
		return false;
	}
	var res = CheckNullorBlank('hospital_charges');
	if (res !== "true") {
		alert(res + "Hospital User Charges in Revenue Generated Tab");
		$('#hospital_charges').focus();
		return false;
	}
	var res = CheckNullorBlank('annual_revenue');
	if (res !== "true") {
		alert(res + "Estimated Annual Revenue from Other Sources in Revenue Generated Tab");
		$('#annual_revenue').focus();
		return false;
	}
	var res = CheckNullorBlank('operating_expenses');
	if (res !== "true") {
		alert(res + "Operating Expenses in Expenditure Incurred Tab");
		$('#operating_expenses').focus();
		return false;
	}
	var res = CheckNullorBlank('depreciation');
	if (res !== "true") {
		alert(res + "Depreciation in Expenditure Incurred Tab");
		$('#depreciation').focus();
		return false;
	}
	var res = CheckNullorBlank('income_statement');
	if (res !== "true") {
		alert(res + "Income Statement in Operating Result Tab");
		$('#income_statement').focus();
		return false;
	}
	var res = CheckNullorBlank('cash_flow_statement');
	if (res !== "true") {
		alert(res + "Cash Flow Statement in Operating Result Tab");
		$('#cash_flow_statement').focus();
		return false;
	}
	var res = CheckNullorBlank('balance_sheet');
	if (res !== "true") {
		alert(res + "Projected Balance Sheets in Operating Result Tab");
		$('#balance_sheet').focus();
		return false;
	}

	if ($("#college_acct_statement1").val().trim() == "") {
		alert("Please Upload College Account Statement in Operating Result Tab.");
		$("#college_acct_statement1").focus();
		return false;
	}
	
	var form_data = new FormData(document
			.getElementById("financial_college"));
	$.ajax({
		url : 'College_Financial_Details_Action?_csrf=' + value,
		type : "POST",
		data : form_data,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false
	}).done(function(j) {
		if (j > 0) {
			$("#hid_financial_council").val(j);
			alert("Data Save Sucessfully");
			location.reload();
		} else {
			alert(j);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}
//Abhishekbhai
function remove_year(selectCustom,year)
{
	const collection = document.getElementsByClassName(selectCustom);
	var temp=0;
	var count=1;
	var list=[];
	for (let i = 0; i < collection.length; i++) {
		const collection_inner = document.getElementsByClassName(selectCustom);
		temp=0;
		count=1;
		//const list=[];
		for (let j = 0; j < collection_inner.length; j++) {
				if(collection_inner[j].value=='0' && collection[i].value!='0'){
					if(collection[i].value!=0){
					list.push(collection[i].value);
					}
					$("#year"+count+" option[value='"+collection[i].value+"']").remove();
				}
				if(collection[i].value!=collection_inner[j].value && collection_inner[j].value!='0' ){
					if(collection[i].value!=0){
						list.push(collection[i].value);
						}
					$("#"+year+""+count+" option[value='"+collection[i].value+"']").remove();
				}
				temp++;
			count++;
		}
		
		}
	//var option=""; 
	 
// 	<c:forEach var="j" items="${year}" varStatus="num">
// // 	for (let j = 0; j < collection_inner.length; j++) {
// // 		if(collection[i].value!=${j[0]}){
			
// // 		}
// // 	} 
// debugger;
// // 	alert(document.getElementsByClassName("selectCustom"));
		
// 	</c:forEach>
// // 	alert(list);
	
}



function Remove_Expyear(remove_expyear,expyear)
{
	const collection = document.getElementsByClassName(remove_expyear);
	var temp=0;
	var count=1;
	var list=[];
	for (let i = 0; i < collection.length; i++) {
		const collection_inner = document.getElementsByClassName(remove_expyear);
		temp=0;
		count=1;
		//const list=[];
		for (let j = 0; j < collection_inner.length; j++) {
				if(collection_inner[j].value=='0' && collection[i].value!='0'){
					if(collection[i].value!=0){
					list.push(collection[i].value);
					}
					$("#expyear"+count+" option[value='"+collection[i].value+"']").remove();
				}
				if(collection[i].value!=collection_inner[j].value && collection_inner[j].value!='0' ){
					if(collection[i].value!=0){
						list.push(collection[i].value);
						}
					$("#"+expyear+""+count+" option[value='"+collection[i].value+"']").remove();
				}
				temp++;
			count++;
		}
		
		}
	//var option=""; 
	 
// 	<c:forEach var="j" items="${year}" varStatus="num">
// // 	for (let j = 0; j < collection_inner.length; j++) {
// // 		if(collection[i].value!=${j[0]}){
			
// // 		}
// // 	} 
// debugger;
// // 	alert(document.getElementsByClassName("selectCustom"));
		
// 	</c:forEach>
// // 	alert(list);
	
}
//Abhishekbhai
</script>


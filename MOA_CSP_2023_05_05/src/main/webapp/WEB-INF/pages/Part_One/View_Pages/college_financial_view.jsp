<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link rel="stylesheet" href="assets/vendor/hamburger-menu/hemburgermenu.css">
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>View College Financial</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									View College Financial</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper tunnel-form preview-form custom-mobile-menu">
		
					<!-- humburger menu for collage regulation - part-1 start -->
			<div class="row viewmenu">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="hamburger-menu">
						<div class="bar" title="Click here for menu">
						</div>
						<div class="hm-title"><h4 class="hm-title-text">College Financial View Form Report</h4></div>
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
			<form:form name="View_College_financial" id="View_College_financial" action="View_College_financial_Action?${_csrf.parameterName}=${_csrf.token}"
			 method="post" modelAttribute="View_College_financial_CMD">
				<div class="card-style mb-30">
					<div class="field-box">
						<div class="row">
						<input type="hidden" id="hid_college_infrastructure_remark" name="hid_college_infrastructure_remark" value="0">
						<input type="hidden" id="institute_id" name="institute_id" value="0">
						<input type="hidden" id="main_view_id" name="main_view_id" value="0">
						
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Financial Capability (in Lakhs)</h5>
   									<a  class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" title="Remarks" data-bs-toggle="modal" data-bs-target="#Financial_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Fixed Deposits</label> <span class="value-bind" id="">${getAllfinancialdetailsReport[0].fix_deposite }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Current Account</label> <span class="value-bind" id="">${getAllfinancialdetailsReport[0].current_acct }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Saving Account</label> <span class="value-bind" id="">${getAllfinancialdetailsReport[0].saving_acct }
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg  custom-title-btn">
									<h5 class="custom-title-tag">Proposed Budget for College &
										Hospital (in Lakhs)</h5>
										<a  class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
										title="Remarks" data-bs-toggle="modal" data-bs-target="#Budget_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Project Cost</label> <span class="value-bind" id="">${getAllfinancialdetailsReport[0].project_cost }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Capital Cost Of Land</label> <span class="value-bind"
										id=""> ${getAllfinancialdetailsReport[0].capital_cost_land }</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Building Cost</label> <span class="value-bind" id="">${getAllfinancialdetailsReport[0].building_cost }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Plants & Machinery</label> <span class="value-bind"
										id="">${getAllfinancialdetailsReport[0].plants_machinery } </span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Equipments</label>${getAllfinancialdetailsReport[0].equipments } <span class="value-bind" id="">
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Furniture & Fixture</label> <span class="value-bind"
										id="">${getAllfinancialdetailsReport[0].furniture_fixer } </span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Preliminary & Pre Operative Cost</label> <span
										class="value-bind" id=""> ${getAllfinancialdetailsReport[0].preliminary_operative_cost }</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Others</label> <span class="value-bind" id="">${getAllfinancialdetailsReport[0].others } </span>
								</div>
							</div>
						</div>
					</div>
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Means of Financing the
										Project (in Lakhs)</h5>
										<a  class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
										title="Remarks" data-bs-toggle="modal" data-bs-target="#Proj_finance_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Contribution of Applicant</label> <span
										class="value-bind" id="">${getAllfinancialdetailsReport[0].contribution_applicants } </span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Grants</label> <span class="value-bind" id=""> ${getAllfinancialdetailsReport[0].grants }</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Donation</label> <span class="value-bind" id="">${getAllfinancialdetailsReport[0].donation }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Equity</label> <span class="value-bind" id="">${getAllfinancialdetailsReport[0].equity } </span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Term Loan</label> <span class="value-bind" id="">${getAllfinancialdetailsReport[0].term_loan }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Other Sources if any</label> <span class="value-bind"
										id=""> ${getAllfinancialdetailsReport[0].other_source }</span>
								</div>
							</div>

						</div>
					</div>
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg  custom-title-btn">
									<h5 class="custom-title-tag">Revenue Generated (in Lakhs)</h5>
									<a  class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
										title="Remarks" data-bs-toggle="modal" data-bs-target="#Revenue_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Fee Structure</label> <span class="value-bind" id="">${getAllfinancialdetailsReport[0].fee_structure }
									</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Hospital user Charges</label> <span class="value-bind"
										id=""> ${getAllfinancialdetailsReport[0].hospital_charges }</span>
								</div>
							</div>
							<div class="col-xl-4 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Estimated Annual Revenue from Other Sources</label> <span
										class="value-bind" id=""> ${getAllfinancialdetailsReport[0].annual_revenue }</span>
								</div>
							</div>
						</div>
					</div>
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Expenditure Incurred (in Lakhs)</h5>
									<a  class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
										title="Remarks" data-bs-toggle="modal" data-bs-target="#Expenditure_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Operating Expenses</label> <span class="value-bind"
										id=""> ${getAllfinancialdetailsReport[0].operating_expenses }</span>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Depreciation</label> <span class="value-bind" id="">${getAllfinancialdetailsReport[0].depreciation }
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Operating Result (in Lakhs)</h5>
									<a  class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
										title="Remarks" data-bs-toggle="modal" data-bs-target="#operating_result_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Income Statement</label> <span class="value-bind" id="">${getAllfinancialdetailsReport[0].income_statement }
									</span>
								</div>
							</div>
							<div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Cash Flow Statement</label> <span class="value-bind"
										id="">${getAllfinancialdetailsReport[0].cash_flow_statement } </span>
								</div>
							</div>
							<div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>Projected Balance Sheets</label> <span
										class="value-bind" id="">${getAllfinancialdetailsReport[0].balance_sheet } </span>
								</div>
							</div>
								<div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12">
								<div class="custom-data-value">
									<label>College account statement</label> <span class="value-bind daobtn"
										id=""> <a 
										class="main-btn dark-btn btn-hover btn-sm btnview"
										title="View" id="college_acc_view_btn"><i class="lni lni-eye"></i></a></span>
								</div>
								<input type="hidden" id="college_acc_hid" name="college_acc_hid" value="${getAllfinancialdetailsReport[0].id}"/>
							</div>
						</div>
					</div>

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Bank Accounts (in Lakhs)</h5>
									<a  class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
										title="Remarks" data-bs-toggle="modal" data-bs-target="#bank_acct_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="bankaccount_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>

													<th><h6>Account No</h6></th>
													<th><h6>Bank Name</h6></th>
													<th><h6>IFSC</h6></th>
													<th><h6>(Saving/Current)</h6></th>
													<th><h6>Branch Address</h6></th>
													<th><h6>Bank Audit Statement of Account</h6></th>
												</tr>
											</thead>
											<tbody id="">
											<c:forEach var="j" items="${getAllbankdetailsReport}"
										varStatus="num">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.account_no }</p>
													</td>
													<td>
														<p>${j.bank_name }</p>
													</td>
													<td>
														<p>${j.ifsc_code }</p>
													</td>
													<td>
														<c:if test="${j.saving_current_acct == '1'}">
															<p id="saving_current_acct_label1">Saving</p>
														</c:if>
														<c:if test="${j.saving_current_acct == '2'}">
															<p id="saving_current_acct_label2">Current</p>
														</c:if>
													</td>
													<td>
														<p>${j.branch_addr }</p>
													</td>
													<td>
														<p></p>
													
														<ul class="buttons-group">
															<li><a
																class="main-btn dark-btn btn-hover btn-sm btnview_1"
																title="View"  id="audit_statement_acc${num.index}"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="audit_statement_acc_hid${num.index}" name="audit_statement_acc_hid${num.index}" value="${j.id}"/></td>
													</td>
												</tr>
												</c:forEach>
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
									<h5 class="custom-title-tag">Last Financial Year Expenses
										on Staff (in Lakhs)</h5>
										<a  class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
										title="Remarks" data-bs-toggle="modal" data-bs-target="#Staff_exp_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="expstaff_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Year</h6></th>
													<th><h6>Staff Salary</h6></th>
													<th><h6>Total Salary</h6></th>
													<th><h6>Attachment</h6></th>
													<th><h6>Salary statement of Teaching, non-teaching and hospital staff</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
											<c:forEach var="j" items="${getAllExpensesReport}"
										varStatus="num">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.year }</p>
													</td>
													
													<td><p>${j.staff_salary }</p></td>
													<td><p>${j.total_salary }</p></td>
													<td>
														<p></p>
													
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview_2"
																title="View" id="attachment_view${num.index}"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="attachment_view_hid${num.index}" name="attachment_view_hid${num.index}" value="${j.id}"/>
													</td>
														<td>
														<p></p>
													
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview_3"
																title="View" id="salary_statement_view${num.index}"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="salary_statement_hid${num.index}" name="salary_statement_hid${num.index}" value="${j.id}"/>
													</td>
												</tr>
												</c:forEach>
<!-- 																									<tr> -->
<!-- 													<td colspan="2"> -->
<!-- 														<p> -->
<!-- 															<b>Total</b> -->
<!-- 														</p> -->
<!-- 													</td> -->
<!-- 													<td><p>1659</p></td> -->
<!-- 													<td><p>1659</p></td> -->
<!-- 												</tr> -->
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
									<h5 class="custom-title-tag">Last Financial Year Expenses
										on Expenditure (in Lakhs)</h5>
										<a  class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
								title="Remarks" data-bs-toggle="modal" data-bs-target="#Purchase_exp_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>

							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="expenpurchase_table">
											<thead>
												<tr>
													<th><h6>Sr No</h6></th>
													<th><h6>Year</h6></th>
													<th><h6>Type of Expenditure</h6></th>
<!-- 													<th><h6>Expenditure Expenses</h6></th> -->
													<th><h6>Total Cost</h6></th>
													<th><h6>Attachment</h6></th>
												</tr>
												<!-- end table row-->
											</thead>
											<tbody id="">
											<c:forEach var="j" items="${getLastYearPurchaseExp}"
										varStatus="num">
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>${j.expyear }</p>
													</td>
													<td><p>${j.purchase }</p></td>
													<td><p>${j.total_purchase }</p></td>
													<td><p></p>
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview_4"
																title="View" id="expense_purchase_attachment_view${num.index}"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="expense_purchase_attachment_hid${num.index}" name="expense_purchase_attachment_hid${num.index}" value="${j.id}"/>
													</td>
												</tr>
												</c:forEach>
<!-- 												<tr> -->
<!-- 													<td colspan="2"> -->
<!-- 														<p> -->
<!-- 															<b>Total</b> -->
<!-- 														</p> -->
<!-- 													</td> -->
<!-- 													<td><p>1659</p></td> -->
<!-- 													<td><p>1659</p></td> -->
<!-- 												</tr> -->
												<!-- end table row -->
											</tbody>
										</table>
									</div>
								</div>
							</section>
							
						</div>
					</div>


<!-- 					<div class="field-box"> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-lg-12 col-md-12 col-sm-12 col-12"> -->
<!-- 								<div class="custom-data-value custom-title custom-title-bg custom-title-btn"> -->
<!-- 									<h5 class="custom-title-tag">Last Financial Year -->
<!-- 										Miscellanous Expenses (in Lakhs)</h5> -->
<!-- 										<a  class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag"  -->
<!-- 										title="Remarks" data-bs-toggle="modal" data-bs-target="#Misc_exp_Modal"><i class="lni lni-pencil-alt"></i></a> -->
<!-- 								</div> -->
<!-- 							</div> -->

<!-- 							<section class="single-detail-block"> -->
<!-- 								<div class="col-lg-12 col-md-12 col-sm-12 col-12"> -->
<!-- 									<div class="table-wrapper table-responsive custom-table"> -->
<!-- 										<table class="table" id="miscexp_table"> -->
<!-- 											<thead> -->
<!-- 												<tr> -->
<!-- 													<th><h6>Sr No</h6></th> -->
<!-- 													<th><h6>Year</h6></th> -->
<!-- 													<th><h6> Miscellaneous Expenditure</h6></th> -->
<!-- 													<th><h6>Total Miscellanous Expenses</h6></th> -->
<!-- 													<th><h6>Attachment</h6></th> -->
<!-- 												</tr> -->
<!-- 											</thead> -->
<!-- 											<tbody id=""> -->
<%-- 											<c:forEach var="j" items="${getLastYearExpenditureExp}" --%>
<%-- 										varStatus="num"> --%>
<!-- 												<tr> -->
<!-- 													<td class="sr-no"><p></p></td> -->
<%-- 													<td><p>${j.mis_year }</p></td> --%>
<%-- 													<td><p>${j.misce_expenditure }</p></td> --%>
<%-- 													<td><p>${j.total_expenditure }</p></td> --%>
<!-- 													<td><p></p> -->
<!-- 														<ul class="buttons-group"> -->
<!-- 															<li><a  -->
<!-- 																class="main-btn dark-btn btn-hover btn-sm btnview_5" -->
<%-- 																title="View" id="expense_miscellanous_attachment_view${num.index}"><i class="lni lni-eye"></i></a></li> --%>
<%-- 														</ul><input type="hidden" id="expense_miscellanous_attachment_hid${num.index}" name="expense_miscellanous_attachment_hid${num.index}" value="${j.id}"/> --%>
<!-- 													</td> -->
<!-- 												</tr> -->
<%-- 												</c:forEach> --%>
<!-- 											</tbody> -->
<!-- 										</table> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</section> -->
<!-- 							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Grand Total of Expenditure in last financial -->
<%-- 										year</label> <span class="value-bind" id="grand_total_expenditure"> ${getLastYearExpenditureExp[0].grand_total_expenditure }</span> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12"> -->
<!-- 								<div class="custom-data-value"> -->
<!-- 									<label>Total Income in last financial year</label> <span -->
<%-- 										class="value-bind" id="total_income_last_year">${getLastYearExpenditureExp[0].total_income_last_year } </span> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->

					<div class="field-box">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="custom-data-value custom-title custom-title-bg custom-title-btn">
									<h5 class="custom-title-tag">Expenses Undertakings and
										Reports</h5>
										<a  class="main-btn active-btn btn-hover btn-sm btnedit custom-btn-tag" 
										title="Remarks" data-bs-toggle="modal" data-bs-target="#Expenses_Report_Modal"><i class="lni lni-pencil-alt"></i></a>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-12">
								<span class="value-bind no-data d-none"
									id="no_uploaded_expancedoc"></span>
							</div>
							<section class="single-detail-block">
								<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="table-wrapper table-responsive custom-table">
										<table class="table" id="table_uploaded_expanxedoc">
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
														<p>Medicine purchase bill for OPD/IPD</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="medi_opd_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="medi_opd_hid" name="medi_opd_hid" value="${getAllfinancialdetailsReport[0].id}"/>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Book purchase bill</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="book_bill_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="book_bill_hid" name="book_bill_hid" value="${getAllfinancialdetailsReport[0].id}"/>
													</td>
												</tr>
<!-- 												<tr> -->
<!-- 													<td class="sr-no"> -->
<!-- 														<p></p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<p>College account statement</p> -->
<!-- 													</td> -->
<!-- 													<td> -->
<!-- 														<ul class="buttons-group"> -->
<!-- 															<li><a  -->
<!-- 																class="main-btn dark-btn btn-hover btn-sm btnview" -->
<!-- 																title="View"><i class="lni lni-eye"></i></a></li> -->
<!-- 														</ul> -->
<!-- 													</td> -->
<!-- 												</tr> -->
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Proof of ESI of Teaching, non-teaching and hospital
															staff</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="proof_esi_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="proof_esi_hid" name="proof_esi_hid" value="${getAllfinancialdetailsReport[0].id}"/>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Form 16 of Teaching, non-teaching and hospital
															staff.</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="form_16_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="form_16_hid" name="form_16_hid" value="${getAllfinancialdetailsReport[0].id}"/>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Proof of PF of Teaching, non-teaching and hospital
															staff.</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a 
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="proof_pf_view_btn"><i class="lni lni-eye"></i></a></li>
														</ul><input type="hidden" id="proof_pf_hid" name="proof_pf_hid" value="${getAllfinancialdetailsReport[0].id}"/>
													</td>
												</tr>
												<tr>
													<td class="sr-no">
														<p></p>
													</td>
													<td>
														<p>Salary statement of Teaching, non-teaching and
															hospital staff.</p>
													</td>
													<td>
														<ul class="buttons-group">
															<li><a href="#"
																class="main-btn dark-btn btn-hover btn-sm btnview"
																title="View" id="salary_statement_teach_view_btn"><i
																	class="lni lni-eye"></i></a></li>
														</ul>
														<input type="hidden" id="salary_statement_teach_hid"
														name="salary_statement_teach_hid"
														value="${getAllExpensesReport[0].id}" />
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</section>
						</div>
					</div>
					<!-- ===========================
							College Financial End
						=========================== -->

				</div>
				<!-- Modal satrt============1==================== -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="Financial_Modal">
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
								<textarea id="clg_fin_financial_capa_rmk" name="clg_fin_financial_capa_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_financial_capa_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->
<!-- Modal satrt -===================2====================-->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="Budget_Modal">
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
								<textarea id="clg_fin_budget_clg_hosp_rmk" name = "clg_fin_budget_clg_hosp_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_budget_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal satrt -------------------3--------------------------->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="Proj_finance_Modal">
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
								<textarea id="clg_fin_finan_the_proj_rmk" name = "clg_fin_finan_the_proj_rmk"placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_project_cost_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal satrt ------------4------------->

<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="Revenue_Modal">
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
								<textarea id ="clg_fin_revenue_gener_rmk" name="clg_fin_revenue_gener_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_revenue_remark"  class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>

<!-- Modal satrt ==================5===================-->


<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="Expenditure_Modal">
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
								<textarea id="clg_fin_expend_incurred_rmk" name="clg_fin_expend_incurred_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_expen_incurred_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal satrt -----------------6------------->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="operating_result_Modal">
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
								<textarea id="clg_fin_oper_rsult_rmk" name="clg_fin_oper_rsult_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_operating_result_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal satrt-----------------7 -->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="bank_acct_Modal">
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
								<textarea id="clg_fin_bank_account_rmk" name="clg_fin_bank_account_rmk"placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_bank_acct_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal satrt ------------------8-------->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="Staff_exp_Modal">
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
								<textarea id="clg_fin_expense_staff_rmk" name="clg_fin_expense_staff_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_fin_expense_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal satrt ---------------9---->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="Purchase_exp_Modal">
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
								<textarea id="clg_fin_expense_purchase_rmk" name="clg_fin_expense_purchase_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id ="clg_purchase_expense_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->

<!-- Modal satrt --------------10---->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="Misc_exp_Modal">
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
								<textarea id="clg_fin_misc_expense_rmk" name="clg_fin_misc_expense_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_misc_expense_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Modal satrt --------------10---->
<div class="modal fade custom-modal" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true"
	id="Expenses_Report_Modal">
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
								<textarea id="clg_document_rmk" name="clg_document_rmk" placeholder="Enter Remarks" rows="5"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<ul class="buttons-group">
					<li><a type="button" class="main-btn dark-btn n btn-hover"
						data-bs-dismiss="modal">Close</a></li>
					<li><a id="clg_Document_remark" class="main-btn success-btn  btn-hover" type="button">Submit</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
			</form:form>
		</div>
	</div>
</section>


<!-- Canvas Modal start -->

<div class="modal fade custom-modal custom-modal-pdf" tabindex="-1"
	aria-labelledby="myLargeModalLabel" id="exampleModal"
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

<c:url value="View_Search_Basic_InfoUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_basic_info"
	name="viewForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6" value="0" />
	<input type="hidden" name="id7" id="id7" value="0" />
</form:form>

<c:url value="View_Search_College_InfrastructureUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_college_infra"
	name="viewForm" modelAttribute="college_infra_id">
	<input type="hidden" name="college_infra_id" id="college_infra_id" value="0" />
</form:form>
<c:url value="View_Search_College_DepartmentUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_college_department"
	name="viewForm" modelAttribute="college_department_id">
	<input type="hidden" name="college_department_id" id="college_department_id" value="0" />
</form:form>
<c:url value="View_Search_College_FinancialUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_college_financial"
	name="viewForm" modelAttribute="college_financial_id">
	<input type="hidden" name="college_financial_id" id="college_financial_id" value="0" />
</form:form>
<c:url value="View_Search_Student_DetailsUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_student_dtl"
	name="viewForm" modelAttribute="student_dtl_id">
	<input type="hidden" name="student_dtl_id" id="student_dtl_id" value="0" />
</form:form>
<c:url value="View_College_Staff_InfoUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_clg_staff_info"
	name="viewForm" modelAttribute="clg_staff_info_id">
	<input type="hidden" name="clg_staff_info_id" id="clg_staff_info_id" value="0" />
</form:form>
<c:url value="College_Staff_List_ViewUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_basic_clg_staff_list"
	name="viewForm" modelAttribute="basic_clg_staff_list_id">
	<input type="hidden" name="basic_clg_staff_list_id" id="basic_clg_staff_list_id" value="0" />
</form:form>
<c:url value="View_Department_Equipment_ReportUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_department_eqp"
	name="viewForm" modelAttribute="department_eqp_id">
	<input type="hidden" name="department_eqp_id" id="department_eqp_id" value="0" />
</form:form>
<c:url value="hospital_infrastructure_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_infra"
	name="viewForm" modelAttribute="hos_infra_id">
	<input type="hidden" name="hos_infra_id" id="hos_infra_id" value="0" />
</form:form>
<c:url value="hospital_ipdopd_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_ipd"
	name="viewForm" modelAttribute="hos_ipd_id">
	<input type="hidden" name="hos_ipd_id" id="hos_ipd_id" value="0" />
</form:form>
<c:url value="otherhospital_detail_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_other_hos_dtl"
	name="viewForm" modelAttribute="other_hos_dtl_id">
	<input type="hidden" name="other_hos_dtl_id" id="other_hos_dtl_id" value="0" />
</form:form>
<c:url value="hospital_staffdetails_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_staff_dtl"
	name="viewForm" modelAttribute="hos_staff_dtl_id">
	<input type="hidden" name="hos_staff_dtl_id" id="hos_staff_dtl_id" value="0" />
</form:form>
<c:url value="hospital_staff_list_view" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_staff_list"
	name="viewForm" modelAttribute="hos_staff_list_id">
	<input type="hidden" name="hos_staff_list_id" id="hos_staff_list_id" value="0" />
</form:form>
<c:url value="View_Hospital_Equipment_ReportUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_hos_eqp"
	name="viewForm" modelAttribute="hos_eqp_id">
	<input type="hidden" name="hos_eqp_id" id="hos_eqp_id" value="0" />
</form:form>
<c:url value="Declaration_ViewUrl" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm_declaration"
	name="viewForm" modelAttribute="declaration_id">
	<input type="hidden" name="declaration_id" id="declaration_id" value="0" />
</form:form>





<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
// 	alert('${getInstname[0].institute_name}')
	if('${role}'!="NCH"){
		$(".btnedit").css("visibility", "hidden");
		$(".viewmenu").addClass("d-none");
		

	}			
$.ajaxSetup({
		async : false
	});
	
$("#institute_id").val('${inst_id}');
$("#main_view_id").val('${main_view_id}');
	
//GET MAIN ID AND REMARKS
var getView_idFrom_Institute_id ='${getView_idFrom_Institute_id}';
// alert("1");
if(getView_idFrom_Institute_id != "[]"){
// alert("2");
	$("#hid_college_infrastructure_remark").val('${getView_idFrom_Institute_id[0].id}');
	$("#clg_fin_financial_capa_rmk").val('${getView_idFrom_Institute_id[0].clg_fin_financial_capa_rmk}');
	$("#clg_fin_budget_clg_hosp_rmk").val('${getView_idFrom_Institute_id[0].clg_fin_budget_clg_hosp_rmk}');
	$("#clg_fin_finan_the_proj_rmk").val('${getView_idFrom_Institute_id[0].clg_fin_finan_the_proj_rmk}');
	$("#clg_fin_revenue_gener_rmk ").val('${getView_idFrom_Institute_id[0].clg_fin_revenue_gener_rmk }');
	$("#clg_fin_expend_incurred_rmk").val('${getView_idFrom_Institute_id[0].clg_fin_expend_incurred_rmk}');
	$("#clg_fin_oper_rsult_rmk").val('${getView_idFrom_Institute_id[0].clg_fin_oper_rsult_rmk}');
	$("#clg_fin_bank_account_rmk").val('${getView_idFrom_Institute_id[0].clg_fin_bank_account_rmk}');
	$("#clg_fin_expense_staff_rmk").val('${getView_idFrom_Institute_id[0].clg_fin_expense_staff_rmk}');
	$("#clg_fin_expense_purchase_rmk").val('${getView_idFrom_Institute_id[0].clg_fin_expense_purchase_rmk}');
	$("#clg_fin_misc_expense_rmk").val('${getView_idFrom_Institute_id[0].clg_fin_misc_expense_rmk}');
	$("#clg_document_rmk").val('${getView_idFrom_Institute_id[0].clg_document_rmk}');
}
	
});



function ViewData_basic_info(){
	
	var institute_id  = $("#institute_id").val();
	
	$("#id6").val(institute_id);
	$("#id7").val(institute_id);
	document.getElementById('viewForm_basic_info').submit();
}
function ViewData_college_infra(){
	
	var institute_id  = $("#institute_id").val();
	
	$("#college_infra_id").val(institute_id);
	document.getElementById('viewForm_college_infra').submit();
}
function ViewData_college_department(){
	
	var institute_id  = $("#institute_id").val();
	$("#college_department_id").val(institute_id);
	document.getElementById('viewForm_college_department').submit();
}
function ViewData_college_financial(){
	
	var institute_id  = $("#institute_id").val();
	$("#college_financial_id").val(institute_id);
	document.getElementById('viewForm_college_financial').submit();
}
function ViewData_student_dtl(){
	
	var institute_id  = $("#institute_id").val();
	$("#student_dtl_id").val(institute_id);
	document.getElementById('viewForm_student_dtl').submit();
}
function ViewData_clg_staff_info(){
	
	var institute_id  = $("#institute_id").val();
	$("#clg_staff_info_id").val(institute_id);
	document.getElementById('viewForm_clg_staff_info').submit();
}
function ViewData_clg_staff_list(){
	
	var institute_id  = $("#institute_id").val();
	$("#basic_clg_staff_list_id").val(institute_id);
	document.getElementById('viewForm_clg_staff_list').submit();
}
function ViewData_department_eqp(){
	
	var institute_id  = $("#institute_id").val();
	$("#department_eqp_id").val(institute_id);
	document.getElementById('viewForm_department_eqp').submit();
}
function ViewData_hos_infra(){
	
	var institute_id  = $("#institute_id").val();
	$("#hos_infra_id").val(institute_id);
	document.getElementById('viewForm_hos_infra').submit();
}
function ViewData_hos_ipd(){
	
	var institute_id  = $("#institute_id").val();
	$("#hos_ipd_id").val(institute_id);
	document.getElementById('viewForm_hos_ipd').submit();
}
function ViewData_other_hos_dtl(){
	
	var institute_id  = $("#institute_id").val();
	$("#other_hos_dtl_id").val(institute_id);
	document.getElementById('viewForm_other_hos_dtl').submit();
}
function ViewData_hos_staff_dtl(){
	
	var institute_id  = $("#institute_id").val();
	$("#hos_staff_dtl_id").val(institute_id);
	document.getElementById('viewForm_hos_staff_dtl').submit();
}
function ViewData_hos_staff_list(){
	
	var institute_id  = $("#institute_id").val();
	$("#hos_staff_list_id").val(institute_id);
	document.getElementById('viewForm_hos_staff_list').submit();
}
function ViewData_hos_eqp(){
	
	var institute_id  = $("#institute_id").val();
	$("#hos_eqp_id").val(institute_id);
	document.getElementById('viewForm_hos_eqp').submit();
}
function ViewData_declaration(){
	
	var institute_id  = $("#institute_id").val();
	$("#declaration_id").val(institute_id);
	document.getElementById('viewForm_declaration').submit();
}

document.addEventListener('DOMContentLoaded', function() {
	
	if('${role}'=="NCH"){
	document.getElementById('basics_information_view').onclick = function() {
		ViewData_basic_info();
	};
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
	

	
/////save 
	document.getElementById('clg_financial_capa_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_budget_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_project_cost_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_revenue_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_expen_incurred_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_operating_result_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_bank_acct_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_fin_expense_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_purchase_expense_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_misc_expense_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	document.getElementById('clg_Document_remark').onclick = function() {
		Save_As_Draft_Remarks();
	};
	
}	
	// document view
	
	document.getElementById('college_acc_view_btn').onclick = function() {
		file_view($("#college_acc_hid").val(),"clg_reg_college_financial_details","college_acct_statement");
	};
	
	document.querySelectorAll('.btnview_1').forEach((items, index) => {
		items.addEventListener('click', event => {
//	 		debugger;
			var val=parseInt(index);
			var cid = document.getElementById('audit_statement_acc_hid'+val).value;
			file_view(cid,"clg_reg_college_bank_account_details","bank_audit");
		})
	});
	
	document.querySelectorAll('.btnview_2').forEach((items, index) => {
		items.addEventListener('click', event => {
//	 		debugger;
			var val=parseInt(index);
			var cid = document.getElementById('attachment_view_hid'+val).value;
			file_view(cid,"clg_reg_last_financial_year_expenses","attachment");
		})
	});
	
	document.querySelectorAll('.btnview_3').forEach((items, index) => {
		items.addEventListener('click', event => {
//	 		debugger;
			var val=parseInt(index);
			var cid = document.getElementById('salary_statement_hid'+val).value;
			file_view(cid,"clg_reg_last_financial_year_expenses","salary_statment");
		})
	});
	
	document.querySelectorAll('.btnview_4').forEach((items, index) => {
		items.addEventListener('click', event => {
//	 		debugger;
			var val=parseInt(index);
			var cid = document.getElementById('expense_purchase_attachment_hid'+val).value;
			file_view(cid,"clg_reg_last_year_purchase_expenses","purchase_attachment");
		})
	});
	
	document.querySelectorAll('.btnview_5').forEach((items, index) => {
		items.addEventListener('click', event => {
//	 		debugger;
			var val=parseInt(index);
			var cid = document.getElementById('expense_miscellanous_attachment_hid'+val).value;
			file_view(cid,"clg_reg_last_year_expenditure_expenses","mis_attachment");
		})
	});

	document.getElementById('medi_opd_view_btn').onclick = function() {
		file_view($("#medi_opd_hid").val(),"clg_reg_college_financial_details","medicine_bill");
	};
	
	document.getElementById('book_bill_view_btn').onclick = function() {
		file_view($("#book_bill_hid").val(),"clg_reg_college_financial_details","book_bill");
	};
	
	document.getElementById('proof_esi_view_btn').onclick = function() {
		file_view($("#proof_esi_hid").val(),"clg_reg_college_financial_details","proof_esi");
	};
	
	document.getElementById('form_16_view_btn').onclick = function() {
		file_view($("#form_16_hid").val(),"clg_reg_college_financial_details","form_16");
	};
	
	document.getElementById('proof_pf_view_btn').onclick = function() {
		file_view($("#proof_pf_hid").val(),"clg_reg_college_financial_details","proof_pf");
	};
	
	document.getElementById('salary_statement_teach_view_btn').onclick = function() {
		file_view($("#salary_statement_teach_hid").val(),"clg_reg_last_financial_year_expenses","salary_statment");
	};

	
// 	document.getElementById('salary_statement_teach_view_btn').onclick = function() {
// 		file_view($("#salary_statement_teach_hid").val(),"clg_reg_college_financial_details","medicine_bill");
// 	};
	
});



	function Save_As_Draft_Remarks() {

	$.ajaxSetup({
	    async: false
	});	
		
		var form_data = new FormData(document.getElementById("View_College_financial"));
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
	    	  	$('#Financial_Modal').modal('hide');
	    	  	$('#Budget_Modal').modal('hide');
	    	  	$('#Proj_finance_Modal').modal('hide');
	    	  	$('#Revenue_Modal').modal('hide');
	    	  	$('#Expenditure_Modal').modal('hide');
	    	  	$('#operating_result_Modal').modal('hide');
	    	  	$('#bank_acct_Modal').modal('hide');
	    	  	$('#Staff_exp_Modal').modal('hide');
	    	  	$('#Purchase_exp_Modal').modal('hide');
	    	  	$('#Misc_exp_Modal').modal('hide');
	    		$('#Expenses_Report_Modal').modal('hide');
	      }
	      else{
	    	  alert(j);
	    	  	$('#Financial_Modal').modal('hide');
	    	  	$('#Budget_Modal').modal('hide');
	    	  	$('#Proj_finance_Modal').modal('hide');
	    	  	$('#Revenue_Modal').modal('hide');
	    	  	$('#Expenditure_Modal').modal('hide');
	    	  	$('#operating_result_Modal').modal('hide');
	    	  	$('#bank_acct_Modal').modal('hide');
	    	  	$('#Staff_exp_Modal').modal('hide');
	    	  	$('#Purchase_exp_Modal').modal('hide');
	    	  	$('#Misc_exp_Modal').modal('hide');
	    		$('#Expenses_Report_Modal').modal('hide');
	      }
		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	}

	//view Document

	function file_view(id,val,field) {
		 $("#exampleModal").modal('show');
		 var pdf1="CommonDocPartOne?kmlId2="+id+"&val444="+val+"&fildname1="+field+" ";
		 $("#pdfmodelcanvas") .attr('src',pdf1);
	}

</script>

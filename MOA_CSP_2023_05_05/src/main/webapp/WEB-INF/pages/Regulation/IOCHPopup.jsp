<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->


<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script src="js/Calender/jquery-ui.js"></script>
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
<!-- <link rel="stylesheet" href="js/assets/collapse/collapse.css"> -->
<!-- <link rel="stylesheet" href="admin/assets/db_css/db_custom_style.css"> -->
<script src="js/miso/commonJS/addmorefunctionality.js"
	type="text/javascript"></script>


<section class="dashboard-page regulation">
	<div class="container-fluid">
		<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12"></div>
			</div>
			<!-- end row -->
			
		</div>
		<!-- title-wrapper end -->

		<div class="form-elements-wrapper">
			<div class="row">

				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form action="Regulation_Action" method="POST"
					modelAttribute="RegulationCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">

							<h3 class="modal-title">University / office / clinic / hospital</h3>

							<section class="single-detail-block">
								<div class="tables-wrapper">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">


											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="addHospital">
													<thead>
														<tr>
															<th>Sr No.</th>
															<th>Place of working</th>
															<th>Name of place</th>
															<th>Landline No.</th>
															<th>Mobile No.</th>
															<th>Email</th>

															<th>State</th>
															<th>District</th>
															<th>Authority Type</th>
															<th>Name of responsible owner</th>
															<th>Address</th>

														</tr>
														<!-- end table row-->
													</thead>
													<tbody id="att_TbbodyNameMedhosp">
													<c:if test="${list.size() == '0'}">
														<td class="min-width" colspan="11">
																	<div class="input-style-2">
																		<p class="no-data">No Data Available</p>
																	</div>
																</td>
														</c:if>
														<c:forEach var="item" items="${list}" varStatus="num">
															<tr id="tr_id_attNameMed">
																<td class="min-width">
																	<div class="lead">
																		<div class="lead-text">
																			<p>${num.index+1}</p>
																		</div>
																	</div>
																</td>
																<td class="min-width">
																	<div class="input-style-2">
																		<p id="">${item.place_of_working_practitioner}</p>
																	</div>
																</td>
																<td class="min-width">
																	<div class="input-style-2">
																		<p id="">${item.place_of_working_name}</p>
																	</div>
																</td>
																<td class="min-width">
																	<div class="input-style-2">
																		<p id="">${item.landline}</p>
																	</div>
																</td>
																<td class="min-width">
																	<div class="input-style-2">
																		<p id="">${item.mobile_no}</p>
																	</div>
																</td>
																<td class="min-width">
																	<div class="input-style-2">
																		<p id="">${item.email}</p>
																	</div>
																</td>



																<td class="min-width">
																	<div class="input-style-2">
																		<p id="">${item.state_name}</p>
																	</div>
																</td>
																<td class="min-width">
																	<div class="input-style-2">
																		<p id="">${item.district_name}</p>
																	</div>
																</td>
																<td class="min-width">
																	<div class="input-style-2">
																		<p id="">${item.place_of_working_practitioner}</p>
																	</div>
																</td>
																<td class="min-width">
																	<div class="input-style-2">
																		<p id="">${item.name_of_res_p }</p>
																	</div>
																</td>

																<td class="min-width">
																	<div class="input-style-2">
																		<p id="">${item.hos_address1}</p> <p
																			id="">${item.hos_address2}</p> <p id="">${item.hos_address3}</p>
																	</div>
																</td>


															</tr>
															<!-- end table row -->
														</c:forEach>
														
														
													</tbody>
												</table>
												<!-- end table -->
											</div>

											<!-- end card -->
										</div>
										<!-- end col -->
									</div>
									<!-- end row -->

								</div>
							</section>

						</div>

						<!-- end card -->
					</form:form>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
	</div>
	<!-- end container -->
</section>
<!-- regulation components end -->


<script nonce="${cspNonce}" type="text/javascript">

$(document).ready(function() {
	$(".header").hide();
	$(".footer").hide();
	$("#menu-toggle").hide();
	$(".sidebar-nav-wrapper").hide();
	$(".main-wrapper").css("margin-left","0"); 

});

</script>


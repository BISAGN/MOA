<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
	
	
	 <link rel="stylesheet" href="layout_file/css/font-awesome.min.css">

	<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
	<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

	<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
	<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
	<script src="js/Calender/jquery-ui.js"></script>
	<script src="js/Calender/datePicketValidation.js"></script>

	<script src="js/Calender/jquery-ui.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
	<link rel="stylesheet" href="js/assets/collapse/collapse.css">
	<link rel="stylesheet" href="admin/assets/db_css/db_custom_style.css">
	<script src="js/miso/commonJS/addmorefunctionality.js"
	type="text/javascript"></script>

<%-- <form:form action="Url" method="post" class="form-horizontal" --%>
<%-- 	commandName="University_Institute_Stud_Report_PopupCMD"> --%>
<!-- 	<div class="" id="University_Institute_Stud_Result_View_Url" style="display: block;"> -->
<!-- 		<div class="watermarked" data-watermark="" id="divwatermark"> -->
<!-- 			<span id="ip"></span> -->
<!-- 			<table id="University_Institute_Stud_Result_View_Url" -->
<!-- 				class="table no-margin table-striped  table-hover  table-bordered "> -->
<!-- 				<thead style="text-align: center;"> -->
<!-- 					<tr> -->
<!-- 						<th style="font-size: 15px;">Ser No</th> -->
<!-- 						<th style="font-size: 15px">Course</th> -->
<!-- 						<th style="font-size: 15px">Video Time</th> -->
<!-- 						<th style="font-size: 15px">Result</th> -->
<!-- 					</tr> -->
<!-- 				</thead> -->
<!-- 				<tbody> -->
<%-- 					<c:if test="${list.size()==0}"> --%>
<!-- 						<tr> -->
<!-- 							<td style="font-size: 15px; text-align: center; color: red;" -->
<!-- 								colspan="10">Data Not Available</td> -->
<!-- 						</tr> -->
<%-- 					</c:if> --%>
<%-- 					<c:forEach var="item" items="${list}" varStatus="num"> --%>
<!-- 						<tr> -->
<%-- 							<td style="font-size: 15px; text-align: center;">${num.index+1}</td> --%>
<%-- 							<td style="font-size: 15px;">${item[0]}</td> --%>
<%-- 							<td style="font-size: 15px;">${item[1]}</td> --%>
<!-- 							<td style="font-size: 15px;">download Result</td> -->

<!-- 						</tr> -->
<%-- 					</c:forEach> --%>
<!-- 				</tbody> -->

<!-- 			</table> -->
<!-- 		</div> -->
<!-- 	</div> -->
<%-- </form:form> --%>

<section class="dashboard-page regulation">
		<div class="container-fluid">
			<!-- title-wrapper start -->
			<div class="title-wrapper pt-30">
				<div class="row align-items-center">
					<div class="col-md-6">
						<div class="title mb-30">
							<h2>STUDENT RESULT VIEW</h2>
						</div>
					</div>
				</div>
				<!-- end row -->
			</div>
			<!-- title-wrapper end -->


			<div class="form-elements-wrapper">
				<div class="row">

					<div class="col-lg-12 col-md-12 col-sm-12">
						<form:form action="Regulation_Action" method="POST"
						class="form-horizontal" modelAttribute="RegulationCMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">

							<div class="accordion" id="accordionPanelsStayOpenExample">
								
								<div class="accordion-item accordion-itemstyle">
									<h2 class="accordion-header" id="panelsStayOpen-headingThree">
										<button
											class="accordion-button accordion-itemstylena accordion-primary-button collapsed"
											type="button" data-bs-toggle="collapse"
											data-bs-target="#panelsStayOpen-collapseThree"
											aria-expanded="false"
											aria-controls="panelsStayOpen-collapseThree">STUDENT RESULT VIEW</button>
									</h2>
									<div id="panelsStayOpen-collapseThree"
										class="accordion-collapse collapse"
										aria-labelledby="panelsStayOpen-headingThree">
										<div class="accordion-body">
											<div class="card-style mb-30">

											

												<div class="tables-wrapper">
													<div class="row">
														<div class="col-lg-12">


															<div class="table-wrapper table-responsive custom-table">
																<table class="table" id="addNameOfMed">
																	<thead>
																		<tr>
																			<th><h6>Ser No</h6></th>
																			<th><h6>Set</h6></th>
																			<th><h6>Course</h6></th>
																			<th><h6>Obtain Marks</h6></th>
																			<th><h6>Total Marks</h6></th>
<!-- 																			<th>Name of Institute/Board</th> -->
<!-- 																			<th>Download</th> -->
																		</tr>
																		<!-- end table row-->
																	</thead>
																	<tbody id="att_TbbodyNameMed">
																	 <c:forEach var="item" items="${list}" varStatus="num" >
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
																					<label id="">${item[0]}</label>
																				</div> 
																			</td>
																			<td class="min-width">
																				<div class="input-style-2">
																					<label id="">${item[1]}</label>
																				</div> 
																			</td>
																			
																			<td class="min-width">
																				<div class="input-style-2">
																					<label id="">${item[2]}</label>
																				</div> 
																			</td> 
<!-- 																			<td class="min-width"> -->
<!-- 																				<div class="input-style-2"> -->
<%-- 																					<label id="">${item.name_of_institute}</label> --%>
<!-- 																				</div>  -->
<!-- 																			</td> -->
																			
<!-- 																			<td class="min-width"> -->
<!-- 																				<div class="input-style-2" id="downloadbtn"> -->

<!-- 																						<i id="downloadbtn" title="Download" -->
<!-- 																							class="fa fa-download" onclick="download_file();" -->
<!-- 																							style="color: #359ade;"></i> <input type="hidden" -->
<%-- 																							id="file_id" value="${item.file_id}"> --%>

<!-- 																					</div> -->
<!-- 																				</td> -->
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
											</div>
										</div>
									</div>
								</div>
							</div>
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


<script>
$(document).ready(function() {
	
// 	 colAdj("University_Institute_Stud_Result_View_Url");
});
	 
	 </script>
	 
<body>

</body>
</html>       

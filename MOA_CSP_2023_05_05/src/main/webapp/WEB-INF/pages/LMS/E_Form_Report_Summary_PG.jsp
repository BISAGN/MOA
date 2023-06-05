<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js\watermark\common.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>PG E Form Summary</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">PG E Form Summary
									    </li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
					<form:form name="Cast_Category_masterUrl" id="Cast_Category_masterUrl" action="Cast_Category_masterUrlaction"
						method="post" class="form-horizontal" modelAttribute="SystemCMD2" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<h6 class="mb-25">PG E Form Summary</h6>
							<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">PG E Form Summary Report</h6>
								<div class="row">

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label>Institute State </label>
											<div class="select-position">
													<select name="institute" id="institute"
														class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getMedStateName}"
															varStatus="num"> 
 															<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
													</c:forEach> 
													</select>
												</div>
										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label> Student category</label>
											<div class="select-position">
													<select name="category" id="category"
														class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getcategorylist}"
															varStatus="num"> 
 															<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
													</c:forEach> 
													</select>
												</div>
										</div>
									</div>

									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-1">
											<label>Student Counselling Authority</label>
											<div class="select-position">
													<select name="authority" id="authority"
														class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getCounselingAuthoList}"
															varStatus="num"> 
 															<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
													</c:forEach> 
													</select>
												</div>
										</div>
									</div>


<%-- 									<c:if test="${role != 'Faculty_NCH'}"> --%>
										<div class="col-lg-4 col-md-6 col-sm-12" id="ins">
											<div class="select-style-1">
												<label> Student Quota </label>
												<div class="select-position">
													<div class="select-position">
													<select name="quota" id="quota"
														class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getQuotaList}"
															varStatus="num"> 
 															<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
													</c:forEach> 
													</select>
												</div>
												</div>
											</div>
										</div>
										
										
									
									<div class="col-lg-4 col-md-6 col-sm-12" id="pg_degree_div">
										<div class="select-style-1">
											<label for="text-input">Degree</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="pg_degree" id="pg_degree">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getDegreePG}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-lg-4 col-md-6 col-sm-12" id="pg_sub_div">
										<div class="select-style-1">
											<label for="text-input">Subject</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="pg_subject" id="pg_subject">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-lg-4 col-md-6 col-sm-12" id="pg_intake_div">
										<div class="select-style-1">
											<label for="text-input">Intake Type</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg" name="intake_id"
														id="intake_id">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getintake_typelist}"
															varStatus="num">
															<option value="${item.id}" name="${item.intake}">${item.intake}</option>
														</c:forEach>
													</select>
											</div>
										</div>
									</div>
									
<%-- 									</c:if> --%>
								
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">

										<ul class="buttons-group mainbtn">
											
											<li><a type="button"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												id="btn-reload" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><a href="e_form_PG_summaryReportUrl"
												class="main-btn dark-btn btn-hover btnreset" value="Reset">Reset</a></li>
												<li><a class="main-btn success-btn btn-hover btn-iconic-icon btnexcel" id="btn-Excel">
													<i class="lni lni-printer" value="EXCEL" title="Export to EXCEL"></i> EXCEL </a></li>
													<li><a
												class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon btnpdf"
												id="pdfex"><i class="bi bi-file-pdf" id="printId"
													value="PDF" title="Export to PDF"></i>PDF</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->

						</div>
						</div>
						</div>
						</div>
						<!-- end card -->
					</form:form>
			</div>
			</div>
		</div>
								<div class="card-style mb-30">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">


											<div class="table-wrapper table-responsive custom-table">
												<table class="table" id="addNameOfMed">
													<thead>
														<tr>
															<th>Sr No</th>
															<th>System </th>
															<th>Total College</th>
															<th>Sanction Seats</th>
															<th>Alloted Seats</th>
															<th>Vacant Seats</th>
															
														</tr>
													</thead>
													<tbody id="att_TbbodyNameMed">
														<c:if test="${summaryReport.size()==0}">
														<tr id="tr_id_attNameMed">
														<td class="min-width" colspan="6">
																<div id="" >DATA NOT AVAILABLE</div>
															</td>
														</tr>
													</c:if>
													<c:forEach var="item" items="${summaryReport}"> 
														<tr id="tr_id_attNameMed">
														
															<td class="min-width">
																<div class="lead"> 

																	<div class="lead-text">
																		<p>${item[0]}</p>
																	</div>
																</div>
															</td>
															<td class="min-width">
																<div id="">${item[1]}</div>
															</td>
															<td class="min-width">
																<p id="">${item[2]}</p>
															</td>
															<td class="min-width">
																<p id="">${item[3]}</p>
															</td>
															<td class="min-width">
																<p id="">${item[4]}</p>
															</td>
															<td class="min-width">
																<p id="">${item[5]}</p>
															</td>
														

														</tr>
														</c:forEach> 
													</tbody>
												</table>

												
												<!-- end table -->
											</div>

											<!-- end card -->

											<!-- Bottom Button Start -->
											

										</div>
										<!-- end col -->
									</div>
								</div> 
	</div>
</section>
<c:url value="Search_e_form_PG_summaryReportUrl" var="Search_e_form_PG_summaryReportUrl" />
<form:form action="${Search_e_form_PG_summaryReportUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="searchUrl2"> 
	<input type="hidden" name="institute1" id="institute1" />
	<input type="hidden" name="category1" id="category1" value="0" />
	<input type="hidden" name="authority1" id="authority1" />
	<input type="hidden" name="quota1" id="quota1" value="0" />
	<input type="hidden" name="degree1" id="degree1" value="0" />
	<input type="hidden" name="subject1" id="subject1" />
	<input type="hidden" name="intake1" id="intake1" value="0" />
 </form:form> 
<c:url value="getSummaryReport_Eform_PG_Excel" var="getSummaryReport_Eform_PG_Excel" />
<form:form action="${getSummaryReport_Eform_PG_Excel}" method="post" id="ExcelDownload"
name="ExcelDownload" modelAttribute="id2"> 
		<input type="hidden" name="institute2" id="institute2" />
	<input type="hidden" name="category2" id="category2" value="0" />
	<input type="hidden" name="authority2" id="authority2" />
	<input type="hidden" name="quota2" id="quota2" value="0" />
	<input type="hidden" name="degree2" id="degree2" value="0" />
	<input type="hidden" name="subject2" id="subject2" />
	<input type="hidden" name="intake2" id="intake2" value="0" />
 </form:form> 
 <c:url value="getSummaryReport_Eform_PG_Pdf" var="getSummaryReport_Eform_PG_Pdf" />
<form:form action="${getSummaryReport_Eform_PG_Pdf}" method="post" id="PdfDownload"
name="ExcelDownload" modelAttribute="id2"> 
		<input type="hidden" name="institute3" id="institute3" />
	<input type="hidden" name="category3" id="category3" value="0" />
	<input type="hidden" name="authority3" id="authority3" />
	<input type="hidden" name="quota3" id="quota3" value="0" />
	<input type="hidden" name="degree3" id="degree3" value="0" />
	<input type="hidden" name="subject3" id="subject3" />
	<input type="hidden" name="intake3" id="intake3" value="0" />
 </form:form> 

<script nonce="${cspNonce}" type="text/javascript">
$(document).ready(function() { 
	
	if('${institute1}'!='' && '${institute1}'!='0'){
		$('select#institute').val('${institute1}')
	}
	if('${category1}'!='' && '${category1}'!='0'){
		$('select#category').val('${category1}')
	}
	if('${authority1}'!='' && '${authority1}'!='0'){
		$('select#authority').val('${authority1}')
	}
	if('${quota1}'!='' && '${quota1}'!='0'){
		$('select#quota').val('${quota1}')
	}
	if('${degree1}'!='' && '${degree1}'!='0'){
		$('select#pg_degree').val('${degree1}')
	}
	if('${subject1}'!='' && '${subject1}'!='0'){
		$('select#pg_subject').val('${subject1}')
	}
	if('${intake1}'!='' && '${intake1}'!='0'){
		$('select#intake_id').val('${intake1}')
	}

});

document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('btn-reload').onclick = function() {
		 Search();
	}; 
	document.getElementById('btn-Excel').onclick = function() {
		Excel();
	}; 
	document.getElementById('pdfex').onclick = function() {
		E_Form_PDF();
	};
	
	document.getElementById('pg_degree').onchange = function() {
		getPGSubjectsofDegree();
	};

});

function getPGSubjectsofDegree() {
	var degree = $("#pg_degree").val();
	$.post('getPGSubjectbyDegree?' + key + "=" + value,{  
		degree : degree
		}).done(function(j) {
						var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
						}
						$("select#pg_subject").html(options);
	});
}

function Search() {
	$("#institute1").val($('#institute').val());
	$("#category1").val($('#category').val());
	$("#authority1").val($('#authority').val());
	$("#quota1").val($('#quota').val());
	$("#degree1").val($('#pg_degree').val());
	$("#subject1").val($('#pg_subject').val());
	$("#intake1").val($('#intake_id').val());
	document.getElementById('searchForm').submit();
}
function Excel() {
	$("#institute2").val($('#institute').val());
	$("#category2").val($('#category').val());
	$("#authority2").val($('#authority').val());
	$("#quota2").val($('#quota').val());
	$("#degree2").val($('#pg_degree').val());
	$("#subject2").val($('#pg_subject').val());
	$("#intake2").val($('#intake_id').val());
	document.getElementById('ExcelDownload').submit();
}
function E_Form_PDF() {
	$("#institute3").val($('#institute').val());
	$("#category3").val($('#category').val());
	$("#authority3").val($('#authority').val());
	$("#quota3").val($('#quota').val());
	$("#degree3").val($('#pg_degree').val());
	$("#subject3").val($('#pg_subject').val());
	$("#intake3").val($('#intake_id').val());
	document.getElementById('PdfDownload').submit();
}


</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js\watermark\common.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
					<span id="lbladd"></span>
						<h2>Generate Curriculum Pdf</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Generate Curriculum Pdf</li>
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
					<form:form name="Newform" id="Newform" action="Gen_CC_pdf_action"
						method="post" class="form-horizontal" modelAttribute="Gen_CC_pdfCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Generate Curriculum Pdf</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
										<label for="text-input">Course<span
											class="mandatory">*</span></label>
										<div class="select-position">
											<select name="course_id" id="course_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCourseList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</div>

							<ul class="buttons-group mainbtn">

								<!-- <li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li> -->
								<!-- <li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save" />
								</li> -->
								<!-- <li><a href="Exam_Type_Mstr_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li> -->
								<li>
									 <a class="main-btn secondary-btn-outline  btn-hover btn-iconic-icon" onclick="getPDF( );"  ><i class="lni lni-printer" id="printId"  title="Export to PDF" ></i> PDF </a>
								</li>
							</ul>
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>



<c:url value="Gen_CC_pdf_action" var="Gen_CC_pdf_action" />
<form:form action="${Gen_CC_pdf_action}" method="post" id="printForm" name="printForm">
	<input type="hidden" id="course_id1" name="course_id1" value="0"/>
</form:form>

 <script nonce="${cspNonce}" type="text/javascript"> 
	$(document).ready(function() {

	});

	//start new pdf
	function getPDF() {
		var course_id = $("#course_id").val();
		$("#course_id1").val(course_id);
		document.getElementById('printForm').submit();
    }


</script>
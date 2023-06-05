<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
<script type="text/javascript" src="js/watermark/common.js"></script>
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


<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Update Type of Lecture Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">
									Update Type of Lecture Master</li>
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
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form action="edit_TypeofContent_action" method="POST"
						modelAttribute="edit_TypeofContent_cmd">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Update Type of Lecture Master</h6>
							<div class="row">
								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="input-style-1">
										<label>Type of Lecture<span class="mandatory">*</span></label>
										<input id="type_of_content" placeholder="Type of Lecture"
											name="type_of_content" class="form-control"
											autocomplete="off" maxlength="100" />
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="select-style-1">
										<label>Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status"
												class="singleselect form-control form-control-lg"
												id="status">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<div class="col-md-6">
												<input type="hidden" id="id" name="id" value="0"
													autocomplete="off" />
											</div>
										</div>
									</div>
								</div>
								</div>

								<!-- Bottom Button Start -->
								<div class="btn-bottom">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
												<li><a href="typeofcontent_url" id="typeofcontent_url"
													class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btnback"><i
														class="lni lni-chevron-left"></i>Back</a></li>
												<li><input type="submit"
													class="main-btn deactive-btn btn-hover btnupda"
													value="Update" id="update"></li>

											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>

	</div>
</section>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(
			function() {
				$('#id').val('${type_of_contentdetails.id}');
				$('input#type_of_content').val(
						'${type_of_contentdetails.type_of_content}');
				$('select#status').val('${type_of_contentdetails.status}');
				$('#status').trigger('change');

			});

	function Validation() {

		if ($("#type_of_content").val().trim() == "") {
			alert("Please Enter Type of Lecture.");
			$("input#type_of_content").focus();
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}
		return true;

	}
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('update').onclick = function() {
			return Validation();
		};

		document.getElementById('type_of_content').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
	});
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<script type="text/javascript" src="js/watermark/common.js"></script>



<section class="dashboard-page">
	<div class="container-fluid">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Update Term Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">
									Update Term Master</li>
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

					<form:form action="edit_term_Action" method="POST"
						modelAttribute="edit_termCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Update Term Master</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Term <span class="mandatory">*</span></label> <input
												type="text" id="term" name="term"
												class="form-control autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="1" placeholder="Term" />
										</div>
										<!-- end input -->
									</div>

									<!-- Start profession -->
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Profession Name<span class="mandatory">*</span></label>
											<input type="text" id="prof_name" name="prof_name"
												class="form-control autocomplete UpperClassName txt-transupp"
												autocomplete="off" placeholder="Profession Name" />
										</div>

									</div>
									<!-- end profession -->

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label>Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="status" id="status"
													class="singleselect form-control form-control-lg">
													<c:forEach var="item" items="${ActiveInActiveList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select> <input type="hidden" id="id" name="id" value="0"
													class="form-control" autocomplete="off" />
											</div>
										</div>

										<!-- end select -->
									</div>
								</div>
							</div>

							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a href="Term_url"
												class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btnback"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><input id="update"
												class="main-btn deactive-btn btn-hover btnupda" value="Update"
												type="submit"></li>
										</ul>
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
	$(document).ready(function() {
		$('#id').val('${term_Details.id}');
		$('#term').val('${term_Details.term}');
		$('#prof_name').val('${term_Details.prof_name}');
		$('select#status').val('${term_Details.status}');
		$('#status').trigger('change');
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});

	});

	function Validation() {
		if ($("#term").val() == "") {
			alert("Please Enter Term.");
			$("#term").focus();
			return false;
		}

		if ($("#prof_name").val() == "") {
			alert("Please Enter Profession.");
			$("#prof_name").focus();
			return false;
		}

		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}

		if ($("select#status").val() == "2") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		return true;
	}

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('update').onclick = function() {
			return Validation();
		};

		document.getElementById('term').onkeypress = function() {
			return isNumberKey0to9(event);
		};

	});
</script>
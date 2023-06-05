<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Update Academic Details Master</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update
									Academic Details Master</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="Academic_Master" id="Academic_Master"
						action="edit_Academic_Details_Action" method="POST"
						modelAttribute="edit_Academic_DetailsCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
<!-- 							<h6 class="mb-25">Update Academic Details Master</h6> -->							
                                 <div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Academic Details<span class="mandatory">*</span></label>
										<input type="text" id="academic_details_name"
											name="academic_details_name"
											class="autocomplete UpperClassName txt-transupp"
											maxlength="100" autocomplete="off"
											placeholder="Academic Details" />
									</div>
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Refer Code<span class="mandatory">*</span></label> <input
											type="number" id="refer_code" name="refer_code"
											class=" form-control UpperClassName txt-transupp"
											autocomplete="off" placeholder="Refer Code" readonly />
									</div>
								</div>

							</div>
						</div>

						<!-- Bottom Button Start -->
						<div class="btn-bottom">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<ul class="buttons-group d-flex justify-content-center">
										<li><a href="Academic_mstrUrl"
											class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
												<i class="lni lni-chevron-left"></i>Back
										</a></li>
										<li><input type="submit" id="update" value="Update"
											class="main-btn deactive-btn btn-hover" /> <input
											type="hidden" name="updateid" id="updateid"
											value="${updateid}" class="main-btn deactive-btn btn-hover btnupda" /></li>
									</ul>
								</div>
							</div>
						</div>
						<!-- Bottom Button End -->
				</div>
				</form:form>
			</div>
		</div>
	</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(
			function() {
				$('#id').val('${academic_Details.id}');
				$('#academic_details_name').val(
						'${academic_Details.academic_details_name}');
				$('#refer_code').val('${academic_Details.refer_code}');

				$('.UpperClassName').keyup(function() {
					this.value = this.value.toUpperCase();
				});

				$("#refer_code").prop("readonly", true);

			});

	function Validation() {

		if ($("#academic_details_name").val().trim() == "") {
			alert("Please Enter Academic Details Name.");
			$("input#academic_details_name").focus();
			return false;
		}

		if ($("#refer_code").val() == "") {
			alert("Please Enter Refer Code.");
			$("input#refer_code").focus();
			return false;
		}

		if ($("#refer_code").val() <= 0) {
			alert("Please Enter Proper Refer Code.");
			$("input#refer_code").focus();
			return false;
		}

		return true;

	}

	document
			.addEventListener(
					'DOMContentLoaded',
					function() {

						document.getElementById('academic_details_name').onkeypress = function() {
							return onlyAlphabetsStringSpace(this, event);
						};

						document.getElementById('refer_code').onkeyup = function() {
							return isNumberKey0to9(event, this);
						};
						document.getElementById('refer_code').onclick = function() {
							alert("Refer Code might be used so, it can't be Changed");
						};

						document.getElementById('update').onclick = function() {
							return Validation();
						};

					});
</script>



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
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Update Division Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update
									Division Master</li>
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
					<form:form name="Division" id="Division" action="edit_division_Action"
						method="POST" class="form-horizontal"
						modelAttribute="edit_divisionCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Update Division Master</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Division<span class="mandatory">*</span></label> <input
											type="text" id="division_name" name="division_name"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="100" placeholder="Division" />
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="input-style-2 mt-3">
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>

									<!-- end select -->
								</div>

							</div>
							<div class="row">
								<ul class="buttons-group d-flex justify-content-center">
									<li><a href="DivisionUrl"
										class="main-btn dark-btn-outline  btn-hover btn-iconic-icon">
											<i class="lni lni-chevron-left"></i>Back
									</a></li>
									<li><input type="submit" id="update" value="Update"
										class="main-btn deactive-btn btn-hover" /></li>
								</ul>
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
		$('#id').val('${division_Details.id}');
		$('#division_name').val('${division_Details.division_name}');
		$('select#status').val('${division_Details.status}');
		$('#status').trigger('change');
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});

	});


	function Validation() {

		if ($("#division_name").val() == "") {

			alert("Please Select Division");
			$("#division_name").focus();
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

		document.getElementById('division_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};

		document.getElementById('update').onclick = function() {
			return Validation();
		};

	});
</script>

	




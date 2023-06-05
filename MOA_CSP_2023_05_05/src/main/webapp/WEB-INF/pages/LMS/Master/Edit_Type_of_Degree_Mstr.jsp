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
						<h2>Update Type of Degree Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Update
									Type of Degree Master</li>
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
					<form:form action="edit_type_of_degree_action" method="POST"
						 modelAttribute="edit_type_of_degree_cmd">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Update Type of Degree Master</h6>
							<div class="row">

								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="input-style-1">
										<label for="username">Type of Degree<span
											class="mandatory">*</span></label> <input id="type_of_degree"
											name="type_of_degree" placeholder="Type of Degree"
											maxlength="100" /> <input type="hidden" id="id" name="id"
											value="0" class="form-control" placeholder="Type of Degree"
											autocomplete="off" />
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-lg-4 col-md-4 col-sm-12">
									<div class="select-style-1">
										<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status"
												class="singleselect form-control form-control-lg">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="input-style-1 m-0">
									<input type="hidden" id="id" name="id" value="0"
										autocomplete="off" />
								</div>
								</div>
								</div>
								<div class="btn-bottom">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
												<li><a href="type_of_degree_url"
													id="type_of_degree_url"
													class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btnback"><i
														class="lni lni-chevron-left"></i>Back</a></li>
												<li><input type="submit" value="Update" id="update"
													class="main-btn deactive-btn btn-hover btnupda"></li>
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
	$(document).ready(
			function() {
				$('#id').val('${Type_of_Degree_Details.id}');
				$('input#type_of_degree').val(
						'${Type_of_Degree_Details.type_of_degree}');
				$('select#status').val('${Type_of_Degree_Details.status}');
				$('#status').trigger('change');
			});

	function Validation() {

		if ($("#type_of_degree").val().trim() == "") {
			alert("Please Enter Type of Degree.");
			$("input#type_of_degree").focus();
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

		document.getElementById('type_of_degree').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};

	});
</script>
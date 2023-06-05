<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script src="js/miso/miso_js/jquery-2.2.3.min.js"></script> -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script> -->
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>


<section class="dashboard-page">
	<div class="container-fluid">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Update District Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="http://localhost:8024/admin/commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Update
									District Master</li>
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

					<form:form name="Edit_District" id="Edit_District"
						action="Edit_District_Action" method="post"
						commandName="Edit_DistrictCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Update District Master</h6>
								<div class="row">

									<div class="col-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label>Country <span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="country_id" id="country_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${country_id}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label>State <span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="state_id" id="state_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMedStateName}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>District <span class="mandatory">*</span></label> <input
												type="hidden" id="id" name="id" value="0" autocomplete="off">
											<input type="text" id="district_name" name="district_name"
												maxlength="50" class="form-control" autocomplete="off"
												placeholder="District">
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label>Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="status" id="status"
													class="singleselect form-control form-control-lg">
													<c:forEach var="item" items="${getStatusMasterList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
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
											<li><a href="District"
												class="main-btn dark-btn-outline  btn-hover btn-iconic-icon"><i
													class="lni lni-chevron-left"></i>Back</a></li>
											<li><input id="update"
												class="main-btn deactive-btn btn-hover" value="Update"
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
	function Validation() {
		if ($("select#country_id").val() == 0) {
			alert("Please Select Country");
			$("select#country_id").focus();
			return false;
		}

		if ($("select#state_id").val() == 0) {
			alert("Please Select State");
			$("select#state_id").focus();
			return false;
		}

		if ($("input#district_name").val().trim() == "") {
			alert("Please Enter District");
			$("input#district_name").focus();
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
	$(document).ready(function() {
		$("#district_name").val('${Edit_DistrictCMD.district_name}');
		$("#country_id").val('${Edit_DistrictCMD.country_id}');
		$('#country_id').trigger('change');
		$("#id").val('${Edit_DistrictCMD.district_id}');
		$('#district_id').trigger('change');
		$("#state_id").val('${Edit_DistrictCMD.state_id}');
		$('#state_id').trigger('change');
		$("#status").val('${Edit_DistrictCMD.status}');
		$('#status').trigger('change');
	});

	function fn_pre_domicile_Country() {
		var text = $("#country_id option:selected").text();

		var contry_id = $('select#country_id').val();
		$
				.post("getStateListFormcon1?" + key + "=" + value, {
					contry_id : contry_id
				})
				.done(
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#state_id").html(options);

						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('update').onclick = function() {
			return Validation();
		};

		document.getElementById('country_id').onchange = function() {
			fn_pre_domicile_Country();
		};

		document.getElementById('district_name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};

		document.getElementById('district_name').oninput = function() {
			this.value = this.value.toUpperCase();
		};

	});
</script>


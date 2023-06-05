<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <script src="js/miso/miso_js/jquery-2.2.3.min.js"></script> -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

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
						<h2>
							<span id="lbladd"></span>Update State Master
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Update
									State Master</li>
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
					<form:form name="editState" id="editState"
						action="Edit_State_Action" method="post"
						modelAttribute="Edit_StateCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Update State Master</h6>
							<div class="row">


								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="select-style-1">
										<label>Country<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="country_id" id="country_id" class="singleselect form-control form-control-lg">
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
									<div class="input-style-1">
										<label>State<span class="mandatory">*</span></label> <input
											type="text" id="state_name" name="state_name"
											class=" form-control" autocomplete="off" maxlength="50" placeholder="State" />
									</div>
									<!-- end input -->
								</div>
								
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-1">
										<label>Abbreviation<span class="mandatory">*</span></label> <input
											type="text" id="state_abbr" name="state_abbr"
											class=" form-control" autocomplete="off" maxlength="50" placeholder="Abbreviation" />
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="select-style-1">
										<label>Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">
												<!-- 												<option value="0">--Select--</option> -->
												<c:forEach var="item" items="${getStatusMasterList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<input type="hidden" id="state_id" name="state_id" value="0"
								autocomplete="off" />
								<input type="hidden" id="state_abbr" name="state_abbr" value="0"
								autocomplete="off" />
							<div class="row">
										</div>
									</div>

									<!-- end select -->
								</div>
								</div>
							</div>
							</div>
							
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
								<ul class="buttons-group d-flex justify-content-center">
									<li><a href="State"
										class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btnback">
											<i class="lni lni-chevron-left"></i>Back
									</a></li>
									<li><input type="submit" id="update" value="Update"
										class="main-btn deactive-btn btn-hover btnupda" /></li>
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
	if ($("#country_id").val() == "0") {
		alert("Please Select Country.");
		$("select#country_id").focus();
		return false;
	}
	if ($("input#state_name").val().trim() == "") {
		alert("Please Enter State.");
		$("input#state_name").focus();
		return false;
	}
	if ($("input#state_abbr").val().trim() == "") {
		alert("Please Enter Abbreviation.");
		$("input#state_abbr").focus();
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
	
}

$(document).ready(function() {
	$("#state_id").val('${Edit_StateCMD.state_id}');
	$("#country_id").val('${Edit_StateCMD.country_id}');
	$('#country_id').trigger('change'); 
	$("#state_name").val('${Edit_StateCMD.state_name}');

	$("#status").val('${Edit_StateCMD.status}');
	$('#status').trigger('change'); 
	$("#state_abbr").val('${Edit_StateCMD.state_abbr}');
	
});

document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('update').onclick = function() {
		return Validation();
	};

	document.getElementById('state_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	
	document.getElementById('state_name').oninput = function () {
		 this.value=this.value.toUpperCase()
	};
	document.getElementById('state_abbr').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	
	document.getElementById('state_abbr').oninput = function () {
		 this.value=this.value.toUpperCase()
	};
});
</script>


<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script src="js/miso/miso_js/jquery-2.2.3.min.js"></script> -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<script type="text/javascript" src="js\watermark\common.js"></script>
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
							Update Country Master<span class="text-red font-size12 enter_by"></span>
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
									Country Master</li>
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
					<form:form name="Edit_Country" id="Edit_Country"
						action="Edit_Country_Action" method="post"
						modelAttribute="Edit_CountryCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Update Country Master</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Country<span class="mandatory">*</span></label> <input
											type="text" id="name" name="name" maxlength="100"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" placeholder="Country">
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">
												<c:forEach var="item" items="${getStatusMasterList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
										<input type="hidden" id="id" name="id" value="0"
								autocomplete="off" />
									</div>
									<!-- end select -->
								</div>
							</div>
							</div>
							
						<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
								<ul class="buttons-group d-flex justify-content-center">
									<li><a href="Country"
										class="main-btn dark-btn-outline   btn-hover btn-iconic-icon btnback">
											<i class="lni lni-chevron-left"></i>Back
									</a></li>
									<li><input type="submit" id="btn-update" value="Update"
										class="main-btn deactive-btn btn-hover btnupda" /></li>
								</ul>
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
		
	
		$("#id").val('${countryDetails.id}');
		
		$("#name").val('${countryDetails.name}');
		$("#status").val('${countryDetails.status}');

		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});

	});

	
	function Validation() {
		if ($("input#name").val().trim() == "") {
			alert("Please Enter Country.");
			$("input#name").focus();
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

		document.getElementById('btn-update').onclick = function() {
			return Validation();
		};

		document.getElementById('name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};

	});
</script>


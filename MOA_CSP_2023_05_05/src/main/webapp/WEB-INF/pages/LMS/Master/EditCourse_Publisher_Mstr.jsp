<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- <script type="text/javascript" -->
<!-- 	src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
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
<script type="text/javascript" src="js\watermark\common.js"></script>


<section class="dashboard-page">
	<div class="container-fluid">

		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Update Course Publisher Master</h2>
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
									Update Course Publisher Master</li>
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

					<form:form name="edit_course_publisher" id="edit_course_publisher"
						action="Edit_course_publisherAction" method="post"
						modelAttribute="Edit_course_publisherCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Update Course Publisher Master</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label> Course Publisher<span class="mandatory">*</span></label>
										<input type="text" id="name" name="name"
											class="form-control autocomplete UpperClassName txt-transupp"
											placeholder="Publisher Name" autocomplete="off"
											maxlength="50" />
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
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
									<li><a href="course_publisher_url"
										class="main-btn dark-btn-outline  btn-hover btn-iconic-icon btnback"><i
											class="lni lni-chevron-left"></i>Back</a></li>
									<li><input class="main-btn deactive-btn btn-hover btnupda"
										value="Update" id="update" type="submit"></li>
								</ul>
								</div>
								</div>
							</div>
						</div>

						<!-- end card -->
						<div></div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		$('#id').val('${Course_Publisher_Details.id}');
		$('#name').val('${Course_Publisher_Details.name}');
		$('select#status').val('${Course_Publisher_Details.status}');
		$('#status').trigger('change');
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});

		if ('${msg}' != "") {
			alert('${msg}');
		}

	});

	function Validation() {

		if ($("#name").val().trim() == "") {
			alert("Please Enter Publisher Name .");
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

		document.getElementById('name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};

		document.getElementById('update').onclick = function() {
			return Validation();
		};

	});
</script>
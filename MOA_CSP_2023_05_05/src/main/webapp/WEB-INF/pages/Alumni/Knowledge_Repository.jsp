<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
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
						<span id="lbladd"></span>
						<h2>Repository</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Repository
								</li>
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
					<form:form name="KnowledgeRepoform" id="KnowledgeRepoform"
						action="KnowledgeRepoAction" method="post" class="form-horizontal"
						modelAttribute="KnowledgeRepoCMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<h6 class="mb-25">Repository</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Category<strong class="mandatory">*</strong></label>
										<div class="select-position">
											<select name="category_id" id="category_id" ><!-- onchange="getSemesterBYDegree(this);" -->
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCategoryList}"
													varStatus="num">
													<option value="${item.id}" name="${item.category_repo}">${item.category_repo}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- end select -->
								</div>


								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Title<span class="mandatory">*</span></label> <input
											type="text" id="title" name="title"
											class="autocomplete txt-transupp" autocomplete="off"
											maxlength="100" placeholder="Title" />

									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label for="username">Upload Doc<span class="mandatory">*</span></label> 
											<input type="file" accept=".pdf" id="upload_doc" name="upload_doc" class="form-control" autocomplete="off">
											<input type="hidden" accept=".pdf" id="u_file1h" name="u_file1h" class="form-control" autocomplete="off">
									</div>

									<!-- end select -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="text-input">Description<strong
											class="mandatory">* </strong></label>
										<textarea id="description" name="description" rows="5"
											cols="50" autocomplete="off" maxlength="250"
											placeholder="Description"></textarea>

										<input type="hidden" id="id" name="id" class="mt-3" value="0"
											autocomplete="off">
									</div>
								</div>

								



							</div>

							<ul class="buttons-group mainbtn">

								<!-- <li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li> -->
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save" />
								</li>
								<!-- <li><a href="Department_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li> -->
							</ul>
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

	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	$.ajaxSetup({
		async : false
	});
}
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

	});
	


	function Validation() {

		if ($("#category").val().trim() == "") {
			alert("Please Enter Category.");
			$("input#category").focus();
			return false;
		}
		
		if ($("#title").val().trim() == "") {
			alert("Please Enter Title.");
			$("input#title").focus();
			return false;
		}

		if ($("#upload_doc").val().trim() == "") {
			alert("Please Enter Document.");
			$("input#upload_doc").focus();
			return false;
		}
		
		if ($("#description").val().trim() == "") {
			alert("Please Enter Description.");
			$("input#description").focus();
			return false;
		}

		return true;
	}
	

</Script> 

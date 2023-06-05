<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- <script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js\watermark\common.js"></script>
 

<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="title mb-30">
					<h2> Update Religion  Master</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="commonDashboard">Dashboard</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page"> Update Religion Master</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>

	<div class="form-elements-wrapper">
		<div class="row">
			<div class="col-12">
                
 <form:form name="religion_master" id="religion_master" action="Edit_religionAction" method="post" class="form-horizontal" commandName="Edit_religionCMD">
				<div class="card-style mb-30">
					<h6 class="mb-25">Update Religion Master</h6>
						<div class="row">	
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
									<label>Religion<span class="mandatory">*</span></label> 
									<input type="text" id="religion" name="religion"
										class="form-control autocomplete UpperClassName txt-transupp" placeholder="Religion "
										autocomplete="off" maxlength="100" />
								</div>
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label>Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" class="singleselect form-control form-control-lg" id="status">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<input type="hidden" id="id" name="id" value="0"
											class="form-control" autocomplete="off" />
										</div>
									</div>								
							</div>
						</div>					
					<div class="row">
						<ul class="buttons-group mainbtn">
							<li>
							  <a href="Category_Url" class="main-btn dark-btn-outline  btn-hover btn-iconic-icon"><i class="lni lni-chevron-left"></i>Back</a>
							</li>
							<li>
								<input href="#0" class="main-btn deactive-btn btn-hover" value="Update"
								id="btn-save" type="submit">
							</li>
						</ul>
					</div>
				</div>
            <div>
		</div>
	 </form:form>
	</div>
  </div>
</div>
</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">

	$(document).ready(function() {
		$('#id').val('${religion_Details.id}');
		$('#religion').val('${religion_Details.religion}');
		$('select#status').val('${religion_Details.status}');
		$('#status').trigger('change');
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
// 		if ('${msg}' != "") {
// 			alert('${msg}');
// 		}
	});
	
	function Validation() {

		if ($("#religion").val() == "") {
			alert("Please Enter Religion ");
			$("input#religion").focus();
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

		document.getElementById('religion').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
	});
</script>
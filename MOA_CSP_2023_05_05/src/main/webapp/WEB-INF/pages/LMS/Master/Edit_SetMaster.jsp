<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->


<div class="container-fluid">
<section class="dashboard-page">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="title mb-30">
					<h2><span id="lbladd"></span>Update Set Master</h2>
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
							<li class="breadcrumb-item active" aria-current="page">Update Set Master</li>
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
                <form:form action="edit_setmaster_Action" method="POST"  modelAttribute="edit_SetmasterCMD">
				<div class="card-style mb-30">
				<div class="custom-field-block">
					<h6 class="mb-25">Update Set Master</h6>
						<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
									<label for="username">Set Name<span class="mandatory">*</span></label> 
									<input id="setname" name="setname"  autocomplete="off" maxlength="50" placeholder="Set" />
									<input type="hidden" id="id" name="id" value="0" class="mt-3" autocomplete="off" />
								</div>
								<!-- end input -->
							</div>

<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
<!-- 								<div class="select-style-1"> -->
<!-- 									<label for="username">Term<span class="mandatory">*</span></label> -->
<!-- 									<div class="select-position"> -->
<!-- 										<select name="term_id" id="term_id" class="singleselect form-control form-control-lg"> -->
<!-- 											<option value="0">--Select--</option> -->
<%-- 											<c:forEach var="item" items="${gettermListforSet}" varStatus="num"> --%>
<%-- 												<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 											</c:forEach> --%>
<!-- 										</select> -->
<!-- 									</div> -->
<!-- 								</div>								 -->
<!-- 								<div class="input-style-1 mt-3"> -->
<!-- 									<input type="hidden" id="id" name="id" value="0" autocomplete="off" /> -->
<!-- 								</div> -->
								
<!-- 								end select -->
<!-- 							</div> -->
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-1">
										<label for="username">Profession Name <span class="mandatory">*</span></label>
										<input id="prof_name" name="prof_name" autocomplete="off"
											maxlength="50" placeholder="Proffession Name" /> 
											<input type="hidden" id="id" name="id" value="0" class="mt-3"
											autocomplete="off" />
									</div>
									<!-- end input -->
								</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
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
								<div class="input-style-1 mt-3">
									<input type="hidden" id="id" name="id" value="0" autocomplete="off" />
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
									<li><a href="setmaster_url" class="main-btn dark-btn-outline   btn-hover btn-iconic-icon btnback">
									<i class="lni lni-chevron-left"></i>Back</a></li>
									<li><input type="submit" id="update" value="Update"
										class="main-btn deactive-btn btn-hover btnupda"/></li>
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
</section>
</div>

<script nonce="${cspNonce}" type="text/javascript">


$(document).ready(function() {

	$('#id').val('${Setname_Details.id}');
	$('input#setname').val('${Setname_Details.setname}');
	$('select#term_id').val('${Setname_Details.term_id}');
	$('input#prof_name').val('${Setname_Details.prof_name}');
	$('select#status').val('${Setname_Details.status}');
	$('#status').trigger('change');
});


function Validation(){
	
			
			if ($("#setname").val().trim() == "") {
				alert("Please Enter Set Name.");
				$("input#setname").focus();
				return false;
			}
			if ($("select#term_id").val() == "0") {
				alert("Please Select Term.");
				$("select#term_id").focus();
				return false;
			}
			if ($("#prof_name").val().trim() == "") {
				alert("Please Enter Profession Name.");
				$("input#prof_name").focus();
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
	
	document.getElementById('prof_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};

});

</script>

	




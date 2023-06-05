<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>


<!-- <style>
.row {
	justify-content: center;
}
.container {
	min-width: 90%;
}

</style> -->

<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>UPDATE CLASSROOM MASTER</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<a href="#">Institute</a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">Update Classroom Master</li>
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
               <form:form action="edit_classroom_Action" method="POST" class="form-horizontal" modelAttribute="edit_classroomCMD">
				<div class="card-style mb-30">
					<h6 class="mb-25">UPDATE CLASSROOM MASTER</h6>
						<div class="row">

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
            					     
            					      <label for="username"> Classroom Name <span class="mandatory">*</span></label>
                 					   <input type="text" id="classroom_name" name="classroom_name" class="form-control autocomplete" autocomplete="off" 
                 					   onkeypress="return onlyAlphabetsStringSpace(this,event); " oninput="this.value = this.value.toUpperCase()" maxlength="30" /> 
				                </div>
												
								<!-- end select -->
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="input-style-1">
	            					     
	            					      <label for="username"> Block Name <span class="mandatory">*</span></label>
	                 					   <input type="text" id="block_name" name="block_name" class="form-control autocomplete" autocomplete="off" 
	                 					   onkeypress="return onlyAlphabetsStringSpace(this,event); " oninput="this.value = this.value.toUpperCase()" maxlength="30" /> 
					            </div>
							</div>
							
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="input-style-1">
	            					     
	            					      <label for="username">Strength<span class="mandatory">*</span></label>
	                 					  <input type="text" id="strength" name="strength" class="form-control autocomplete" autocomplete="off"
	                 					   onkeypress="" oninput="this.value = this.value.toUpperCase()" maxlength="30" /> 
						                
					            </div>
								
								
							</div>
							
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									 <label for="username">Status<span class="mandatory">*</span></label>
									<div class="select-position">
										<input type="hidden" id="id" name="id" class="form-control"
									value="0" autocomplete="off"> 
												 <select name="status" class="form-control" id="status">
												<c:forEach var="item" items="${ActiveInActiveList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
			
											</select>
									</div>
								</div>								
								<div class="input-style-2 mt-3">
								<input type="hidden" id="id" name="id" value="0" class="form-control" autocomplete="off" />
								</div>
								
								<!-- end select -->
							</div>
							
			

						</div>	

						<ul class="buttons-group mainbtn">

							<li>
			                    <a href="ClassroomUrl" id="ClassroomUrl" class="main-btn dark-btn-outline  btn-hover btn-iconic-icon"><i class="lni lni-chevron-left"></i>Back</a>
			                </li>
							<li>
			                    <input type="submit" class="main-btn deactive-btn btn-hover" value="Update" onclick="return Validation();">
			                 </li>
							<li>
								<input type="reset" class="main-btn dark-btn n btn-hover btn-clear" type="button" value="Reset"  onclick="clearall();">
							</li>
						</ul>
	
				</div>
				<!-- end card -->
            </form:form>
			</div>
		</div>
	</div>


</div>
</section>

<%-- <form:form action="edit_classroom_Action" method="POST" class="form-horizontal" modelAttribute="edit_classroomCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header title">
				<h5>
					<span id="lbladd"></span><b>UPDATE CLASSROOM MASTER</b>
				</h5>
			</div>
		<div class="card-body card-block">
				<div class="row">
				<div class="col-md-12">
					<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-6">
								<label for="username"> CLASSROOM NAME<span class="star_design"></span> <span style="color:#ff0000">*</span> </label>
							</div>
							<div class="col-md-6">
								 <input type="text" id="classroom_name" name="classroom_name" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabetsStringSpace(this,event); " oninput="this.value = this.value.toUpperCase()" maxlength="30" /> 

								
							</div>
							
									
						</div>
						</div>
						<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-6">
								<label for="username"> BLOCK NAME<span class="star_design"></span> <span style="color:#ff0000">*</span> </label>
							</div>
							<div class="col-md-6">
								 <input type="text" id="block_name" name="block_name" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabetsStringSpace(this,event); " oninput="this.value = this.value.toUpperCase()" maxlength="30" /> 

								
							</div>
							
									
						</div>
						</div>
						<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-6">
								<label for="username"> STRENGTH<span class="star_design"></span> <span style="color:#ff0000">*</span> </label>
							</div>
							<div class="col-md-6">
								 <input type="text" id="strength" name="strength" class="form-control autocomplete" autocomplete="off" maxlength="30" /> 

								
							</div>
							
									
						</div>
						</div>
						
						<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-6">
								<label for="username">STATUS<span class="star_design"></span> <span style="color:#ff0000">*</span> </label>
							</div>
							<div class="col-md-6">
								<select name="status" id="status" class="form-control">
											<c:forEach var="item" items="${ActiveInActiveList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>

								</select>
								<div class="col-md-6">
									<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
							</div>
						</div>
					</div>
					
					</div>
					
				</div>
	
			</div>
			
				<input type="hidden" name="id" id="id" value="0" />
			
			<div class="card-footer" align="center">
			<a href="ClassroomUrl" id="ClassroomUrl"	class="btn btn-danger btn-sm">Back</a> 
				<button type="reset" class="btn btn-success btn-sm" value="Reset" onclick="clearall();"> Reset</button>
				 <input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validation();"> 
			</div>
			

		</div>
	</div>
</form:form> --%>



<script type="text/javascript">


$(document).ready(function() {
	$('#id').val('${classroom_Details.id}');
	$('#classroom_name').val('${classroom_Details.classroom_name}');
	$('#block_name').val('${classroom_Details.block_name}');
	$('#strength').val('${classroom_Details.strength}');
	$('select#status').val('${classroom_Details.status}');

});


	function Validation(){
		
		
		
		if ($("#classroom_name").val() == "") {
			
			alert("Please Select Classroom Name");
			$("#classroom_name").focus();
			return false;
		}
		

	if ($("#classroom_name").val() == "") {

			alert("Please Select Block Name");
			$("#classroom_name").focus();
			return false;
		}

		if ($("#strength").val() == "") {

			alert("Please Select Strength");
			$("#strength").focus();
			return false;
		}

		if ($("select#status").val() == "2") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}

		return true;

	}
</script>

	




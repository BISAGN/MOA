<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<style>
.row {
	justify-content: center;
}
.container {
	min-width: 90%;
}

</style>



<form:form action="edit_subcategory_Action" method="POST" class="form-horizontal" modelAttribute="edit_SubcategoryCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header title">
				<h5>
					<span id="lbladd"></span><b>UPDATE SUB POLICY CATEGORY MASTER</b>
				</h5>
			</div>
		<div class="card-body card-block">
				<div class="row">
				<div class="col-md-12">
				
<!-- 				<div class="col-md-6 left_content"> -->
<!-- 						<div class="col-md-6"> -->
						
<!-- 										<div class="col-md-6"> -->
<!-- 								<label for="username">POLICY CATEGORY<span class="star_design"></span> <span style="color:#ff0000">*</span> </label> -->
<!-- 										</div> -->
<!-- 										<div class="col-md-6"> -->

<!-- 								<select name="policycategory" class="form-control" -->
<%-- 									id="policycategory" value="${subpolicyinfo.get(0).get(0)}"> --%>
<!-- 									<option value="0">--Select--</option> -->
<%-- 									<c:forEach var="item" items="${PoicyCategoryList}" --%>
<%-- 										varStatus="num"> --%>
<%-- 										<option value="${item.id}">${item.policycategory}</option> --%>
<%-- 									</c:forEach> --%>
<!-- 								</select> -->
<!-- 										</div> -->
							
<!-- 								</div> -->
<!-- 					</div> -->
					<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-6">
								<label for="username"> POLICY CATEGORY<span class="star_design"></span> <span style="color:#ff0000">*</span> </label>
							</div>
							<div class="col-md-6">
								<select name="policycategory" class="form-control"
									id="policycategory" >
									<option value="0">--Select--</option>
												<c:forEach var="item" items="${policycat}" varStatus="num">
													<option value="${item.id}" name="${item.id}">${item.policycategory}</option>
												</c:forEach>

									
								</select>
<!-- 									<div class="col-md-6"> -->
<!-- 									<input type="text" id="cat_hid" name="cat_hid" value="0" class="form-control" value="0" -->
<!-- 										autocomplete="off" /> -->
<!-- 								</div> -->
								
							</div>
						</div>
						</div>
					<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-6">
								<label for="username">SUB POLICY CATEGORY<span class="star_design"></span> <span style="color:#ff0000">*</span> </label>
							</div>
							<div class="col-md-6">
								<input id="subcategory" name="subcategory"
									class="form-control" autocomplete="off" maxlength="25"
									placeholder="Maximum 25 Character" />
								<div class="col-md-6">
									<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
							</div>
						</div>
						</div>
					</div>
					
					<br></br><br>
					<div class="col-md-12">
					
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
			<a href="subcategory_url" id="PolicysearchUrl"	class="btn-cancel">Back</a> 
				<button type="reset" class="btn-clear" value="Reset" onclick="clearall();"> RESET</button>
				 <input type="submit" class="btn-update" value="UPDATE" onclick="return Validation();"> 
			</div>
			

		</div>
	</div>
</form:form>



<script type="text/javascript">


$(document).ready(function() {
	$('#id').val('${Subcategory_Details.id}');
	$('#policycategory').val('${Subcategory_Details.policycategory}');
	$('input#subcategory').val('${Subcategory_Details.subcategory}');
	$("#policy_category").change();
	$('select#status').val('${Subcategory_Details.status}');

});


	function Validation(){
		
		
		if ($("#policycategory").val() == "0") {
			alert("Please Select Policy Category");

			return false;
		} 


		if ($("#subcategory").val().trim() == "") {
			alert("Please Enter Subcategory ");
			$("input#subcategory").focus();
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

	




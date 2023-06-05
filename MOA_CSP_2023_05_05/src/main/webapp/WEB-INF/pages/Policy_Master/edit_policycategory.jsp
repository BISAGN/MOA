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

<form:form action="edit_policycategory_Action" method="POST" class="form-horizontal" modelAttribute="edit_PolicycategoryCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header title">
				<h5>
					<span id="lbladd"></span> <b>UPDATE POLICY CATEGORY MASTER</b>
				</h5>
			</div>



		<div class="card-body card-block">
				<div class="row">
					<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-6">
								<label for="username">POLICY CATEGORY<span class="star_design"></span> <span style="color:#ff0000">*</span> </label>
							</div>
							<div class="col-md-6">
								<input id="policycategory" name="policycategory"
									class="form-control" autocomplete="off" maxlength="25"
									placeholder="Maximum 25 Character" />
								<div class="col-md-6">
									<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
								</div>
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
			
				<input type="hidden" name="id" id="id" value="0" />
			
<!-- 			<div class="card-footer" align="center"> -->
<!-- 			<a href="" class="btn-clear">BACK</a> -->
<!-- <!-- 			<input type="reset" class="btn-clear" value="RESET"> --> 
			
<!-- 				<a href="Edit_policycategoryUrl"><button class="btn-clear" id="">RESET</button></a> -->
				
<!-- 			</div> -->
			<div class="card-footer" align="center">
	        		     <a href="policycategory_url" id=""	class="btn-cancel">BACK</a>
	        		     <button type="reset" class="btn-clear" value="Reset" onclick="clearall();"> RESET</button>
	        	       <!-- <a href="Edit_policycategoryUrl"> 	<button class="btn-clear" >RESET</button>	</a> -->
	              		 <input type="submit" class="btn-update" value="UPDATE" onclick="return Validation();"> 
	              	</div>
		</div>
	</div>
</form:form>



<script type="text/javascript">


$(document).ready(function() {


	
	$('#id').val('${Policycategory_Details.id}');
	$('input#policycategory').val('${Policycategory_Details.policycategory}');
	$('select#status').val('${Policycategory_Details.status}');
});



function Validation(){
	
			
			if ($("#policycategory").val().trim() == "") {
				alert("Please Enter Policycategory ");
				$("input#policycategory").focus();
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

	




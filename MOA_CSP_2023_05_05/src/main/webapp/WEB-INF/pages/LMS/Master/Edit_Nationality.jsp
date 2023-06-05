<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> 
<script type="text/javascript" src="js/watermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>

<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" />
</c:if>
<form:form name="Edit_Nationalityform" id="Edit_Nationalityform" action="edit_Nationality_Action" method='POST' modelAttribute="Edit_Nationality_cmd">
<div class="container-fluid" align="center">
	<div class="card">
		<div class="card-header"> <h5>Edit Nationality</h5></div>
		<div class="card-body card-block">
			<div class="row">
			<div class="col-lg-6">
	             	<div class="row form-group"> 
	             		<div class="col-6">
		                	<label for="text-input" class=" form-control-label">Nationality <strong style="color: red;">*</strong></label>
		                </div> 
	             		<div class="col-6">
	             		      <form:input id="nationality_id" path="nationality_id" type="hidden" value="${Edit_Nationality_cmd.nationality_id}"></form:input>
	             			  <form:input id="nationality" path="nationality" class="form-control" maxlength="50" autocomplete="off" onkeypress="return onlyAlphabetsString(event, this);" value="${Edit_Nationality_cmd.nationality}"     cssStyle="text-transform: uppercase;" ></form:input>
						 	 	<span class="errorClass" id="nationality_lbl"></span>
								<form:errors path="nationality" id="nationality" cssClass="errorClass1"></form:errors>
						</div>
						</div>
						</div>
			</div>
	   </div>
		<div class="card-footer" align="center">			
          <button class="btn btn-light btn-sm" value="Update" onclick="return isValidateClientSide();"><i class="fa fa-pencil-square-o"></i>Update </button>
            <button class="btn btn-danger btn-sm"><a href="NationalityUrl"><i class="fa fa-times"></i>  Cancel </a> </button>
        </div> 	
        </div>	
</div>
</form:form>
 <script>
$(document).ready(function() {
	 var myResponse = []; 
	 var myResponse1 = []; 
	var wepetext=$("#nationality");
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getnationalityList?"+key+"="+value,
	        data: {getcolumnname : document.getElementById('nationality').value},
	          success: function( data ) {
	        	  if(data.length > 1){
	        	  	var susval = [];
		  	      	var length = data.length-1;
		  	      	var enc = data[length].columnName1.substring(0,16);
		  	       	for(var i = 0;i<data.length-1;i++){
		  	        	susval.push(dec(enc,data[i].columnCode)+"::"+dec(enc,data[i].columnName));
		  	        }
		  	        var dataCountry1 = susval.join("|");
		            myResponse = [];
		            myResponse1 = []; 
		            var autoTextVal=wepetext.val();
					$.each(dataCountry1.toString().split("|"), function(i,e){
						var newE = e.split("::")[1].substring(0, autoTextVal.length);
						if (e.split("::")[1].toLowerCase().includes(autoTextVal.toLowerCase())) {
							myResponse.push(e.split("::")[1]);
						  	myResponse1.push(e.split("::")[0]);
						} 				 
					});      	          
					response( myResponse ); 
	        	  }
	          }
	        });
	      },
	      minLength: 1,
	      autoFill: true,
	    });
  });
</script> 

<script type="text/javascript">
function isValidateClientSide()
{	  
	
	if ($("input#nationality").val() == 0) {
		alert("Please Enter Nationality.");
		$("input#nationality").focus();
		return false;
	}
	
   return true;	
} 
</script>


<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="Pages_JS/Policy_Creation.js" type="text/javascript"></script>

<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> 
<script type="text/javascript" src="js/watermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link href="js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="js/dropDown/select2.min.js" type="text/javascript"></script>


<style>



div#ui-datepicker-div {
	width: min-content !important;
}

div#ui-datepicker-div table thead {
	width: 100% !important;
	background-color: #257758;
}
div#ui-datepicker-div table tbody {
	height: auto !important;
}

.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all
	{
	background: #198754 !important;
}

</style>
<style>

.card {
    background: #378d6c4f;
    border: 0;
    box-shadow: none;
}
.card label {
    display: inline-block;
    color: #257758;
    font-weight: bold;
    font-size: 16px;
}
/* .card .bgline{
    background: #b9cf6a;
       background: rgb(255 255 255 / 17%);
    border-color: #e3ebc3;
    border-color: rgb(37 119 88 / 45%);
    border-style: solid;
    border-width: 2px;
    -moz-border-radius: 5px;
    -webkit-border-radius: 5px;
    border-radius: 5px;
    line-height: 30px;
    list-style: none;
    padding: 5px 10px;
    margin-bottom: 0.5rem;
} */
.card-header {
    border-bottom: 3px solid rgb(18 77 48) !important;
/*     background: #257758 !important; */
background: linear-gradient(to right, #004547 0%, #175658 28%, #2e9970 91%, #2e9970 100%)!important;
    text-align: center;
    color: white;
    padding: 10px;
    box-shadow: inset 4px 1px 10px 3px #145133;
    
}
.card-footer{
   border-top: 3px solid rgb(18 77 48) !important;
   background: linear-gradient(to right, #004547 0%, #175658 28%, #2e9970 91%, #2e9970 100%)!important;
    box-shadow: inset 4px 1px 10px 3px #145133;
}
.card-header .h5, h5 {
    font-size: 1.75rem;
}
.card .form-control {
    font-weight: 600;
    line-height: 2;
    border: 3px solid #257758;
    border-radius: 0.25rem;
     color: #124c30;
}
/* .div-effect:hover{ transform: scale(1.1); } */
.effect-9 ~ .focus-border i:before, .effect-9 ~ .focus-border i:after {
    width: 3px;
    background-color:#fff;
}
.effect-9 ~ .focus-border:before, .effect-9 ~ .focus-border:after {
    height: 3px;
    background-color: #fff;
}
.form-control:focus {
 box-shadow: 0 0 0 0.25rem rgb(46 153 112 / 47%);
}
.card input::placeholder {
  color: #175658 !important;
  font-weight: bold !important;
}
.card input::placeholder {
    color: #175658 !important;
}
.section-heading {
        /* background: #c0e0d4; */
    margin: 10px 0;
    padding: 5px 15px;
    text-align: left;
    color: #257758;
    border-bottom: 5px double currentColor;
}


/* span#select2-policy_category-container { */
/*     font-weight: 600; */
/*     line-height: 2; */
/*     border: 3px solid #257758 !important; */
/*     border-radius: 0.25rem; */
/*     color: #124c30 !important; */
/*     margin-bottom: 5px; */
/* } */
span#select2-policy_sub_category-container {
    font-weight: 600;
    line-height: 2;
    border: 3px solid #257758 !important;
    border-radius: 0.25rem;
    color: #124c30 !important;
      margin-bottom: 5px;
}

.draftform {
    /* padding: 5px; */
    background: #257758;
    border-radius: 10px;
    margin: 0px 0px 20px; 
    background: linear-gradient( -45deg, #044648, #145456, #217362, #2c956e);
        box-shadow: 5px 5px 13px 2px #1b665d;
}

.card {
    background-color: #fff;
    border: 0;
    box-shadow: none;
    border-radius: 10% 0 0 0 !important;
}
.draftformleft {
    padding: 80% 0;
    margin: auto;
    width: 100%;
    text-align: center;
    color: white;
    font-family: system-ui;
}
.draftformleft h1{
   font-size: 3.5rem;
}
.section-heading h3 {
        font-size: 2rem;
    color: #257758;
    font-family: system-ui;
}
.section-heading {
    padding: 10px;
    margin: 10px 0px 5px;
/*     border-bottom:3px solid  #257758; */
        border-bottom: 5px double #257758;
    background: #e3fff4;
}
div[class*="col-"] {
    float: inherit;
/*     margin: auto; */
}
.draftformright {
   border: 5px solid #278669;
}

.card-footer {
    border-top: 5px solid #257758 !important;
    text-align: center;
    background: #fff !important;
     box-shadow: inset 4px 1px 10px 3px #145133;
}
.card label {
    display: inline-block;
    color: #257758;
    font-weight: bold;
    font-size: 20px;
}

.card .form-control {
    font-weight: 600;
    line-height: 2;
    border: 3px solid #257758;
    border-radius: 0.25rem;
     color: #124c30;
}
.card {
    margin: 0px 0px 0px;
}
label.form-control-label {
    float: left;
}
/* .middle_content { */
/* 	background: url(layout_file/images/bgimg.png) no-repeat; */
/*     background-size: contain; */
/* } */

img.ui-datepicker-trigger {
    height: 40px !important;
}
.middle_content {
	background: url(layout_file/images/bgimg.png) no-repeat;
    background-size: contain;
}
</style>

<br>
<!-- <div class="row">
     <div class="col-12">
     <div class="section-heading">
         <h2>UPDATE POLICY DRAFT</h2>
         <div class="line"></div>
	 </div>
	
     </div>
</div> -->

<div class="container" align="center">
		
		<div class="draftform">
<div class="row">
	     <div class="col-3">
    	   	<div class="draftformleft">
    	   	    <h2><u><b>UPDATE POLICY DRAFT</b></u></h2>
    	   	    </div>
    	   	    </div>
    	   	     <div class="col-9">
    	   	      <form:form name="edit_policy_form" id="edit_policy_form" action="edit_policy_Action" method='POST' commandName="editcmd" enctype="multipart/form-data">
         		
		
			<div class="draftformright">
    	   	<div class="card px-4 py-4" style="width:100%">
         		<div class="card-body card-block cue_text" >
         		<div class="col-12 mb-2"> 
	             	<div class="row form-group"> 
	             		<div class="col-md-7 col-lg-6 col-xl-4 mb-2">  
		                	<label for="text-input" class=" form-control-label">
		                		<strong style="color: red;">*</strong>
		                		Policy Type
		                		<span class="noteClass"></span>
		                	</label>
		                </div>
	             		<div class="col-md-5  mb-2">

									<label for="border_area1"><input type="radio"
												id="Initial" name="policy_type" value="1"
												onclick="old_new_policy(this.value);"checked="checked" >
												Initial</label>
									<label for="policy_type"><input type="radio"
												id="Renewal" name="policy_type" value="2"
												onclick="old_new_policy(this.value);"> Renewal </label>
						</div>
	             	</div>
             	</div>
             <div id="old_new">
             	<div class="col-12 mb-3"> 
	             	<div class="row form-group"> 
	             		<div class="col-md-7 col-lg-6 col-xl-4 mb-2">  
		                	<label class=" form-control-label"><strong
									style="color: red;"> *</strong>Policy Category</label>
		                </div>
	             		<div class="col-md-5 mb-2">
								<select hidden="true" name="policy_category" id="policy_category"    onchange="getSubpolicy();" class="" >
												<option value="0">--Select--</option>
												
										<c:forEach var="item" items="${policycat}" varStatus="num">
													<option value="${item.id}" name="${item.id}">${item.policycategory}</option>
											</c:forEach>

									</select>
									<label id="policy_cat_p"></label>
<!-- 									<span class="focus-border"><i></i></span> -->
						</div>
	             	</div>
             	</div>
             	
             	<div class="col-12 mb-3"> 
	             	<div class="row form-group"> 
	             		<div class="col-md-7 col-lg-6 col-xl-4 mb-2">  
		                	<label class=" form-control-label"><strong
										style="color: red;">* </strong>Policy Sub-Category</label>
		                </div>
	             		<div class="col-md-5 mb-2">
								<select hidden="true" name="policy_sub_category" id="policy_sub_category"  class="" >
												<option value="0">--Select--</option>
												
													<c:forEach var="item" items="${subpolicycat}" varStatus="num">
													<option value="${item.id}" name="${item.id}">${item.subcategory}</option>
												</c:forEach>
								</select>
								<label id="policy_sub_cat_p"></label>
<!-- 									<span class="focus-border"><i></i></span> -->
						</div>
	             	</div>
             	</div>
             	
             	<div class="col-12 mb-3"> 
	             	<div class="row form-group"> 
	             		<div class="col-md-7 col-lg-6 col-xl-4 mb-2">  
		                	<label class=" form-control-label"><strong
										style="color: red;">* </strong>Policy Unique ID</label>
		                </div>
	             		<div class="col-md-5 mb-2">
								<input hidden="true" id="policy_unique_id" name="policy_unique_id" class="form-control-sm form-control "
										autocomplete="off"  value="" maxlength="50" value="" placeholder="Enter Policy Unique ID"/>
										<div style="" class="col-md-8">
									<label id="policy_id_p">${editcmd.policy_unique_id}</label>
								</div>
										<span class="focus-border"><i></i></span>
						</div>
						
	             	</div>
             	</div>
             	
             </div>
<!--             </div> -->
<!--            </div> -->
<!--          </div> -->
         
<!--          <div class="d-flex justify-content-between align-items-center border-bottom"> -->
<!-- 	    	   	<div class="card" style="width:100%"> -->
			<div class="col-12">
<!-- 			     <div class="col-12"> -->
			     <div class="section-heading">
			         <h3>Meta-Data of Policy</h3>
<!-- 			         <div class="line"></div> -->
<!-- 				 </div> -->
				
			     </div>
			</div>
				<div class="col-12 "> 
	             	<div class="row form-group"> 
	             		<div class="col-md-7 col-lg-6 col-xl-4 mb-2">  
		                	<label class=" form-control-label"><strong
										style="color: red;">* </strong>Purpose</label>
		                </div>
	             		<div class="col-md-5 mb-2">
								<textarea   id="purpose" name="purpose"class="form-control "></textarea>
								<span class="focus-border"><i></i></span>
						</div>
	             	</div>
             	</div>
             	
             	 <div class="col-12 "> 
	             	<div class="row form-group"> 
	             		<div class="col-md-7 col-lg-6 col-xl-4 mb-2 ">  
		                	<label class=" form-control-label"><strong
										style="color: red;">* </strong>Scope</label>
		                </div>
	             		<div class="col-md-5 mb-2">
								<textarea   id="scope" name="scope"class="form-control "></textarea>
								<span class="focus-border"><i></i></span>
						</div>
	             	</div>
             	</div>
             	
					  	<div class="col-12">
			             <div class="section-heading"> 
						<h3>DRAFT</h3>		
					  </div>
		    	</div>
		    	
		    	<div class="col-12 "> 
	             	<div class="row form-group"> 
	             		<div class="col-md-7 col-lg-6 col-xl-4 mb-2">  
		                	<label class=" form-control-label"><strong
										style="color: red;">* </strong>Policy Title</label>
		                </div>
	             		<div class="col-md-5 mb-2">
								<input id="policy_title" name="policy_title" class="form-control-sm form-control "
										autocomplete="off"  value="" maxlength="50" placeholder="Enter Policy Title"/>
										<span class="focus-border"><i></i></span>
						</div>
	             	</div>
             	</div>
             	
<!--              	<div class="col-12 ">  -->
<!-- 	             	<div class="row form-group">  -->
<!-- 	             		<div class="col-md-7 col-lg-6 col-xl-4 mb-2">   -->
<!-- 		                	<label class=" form-control-label"><strong -->
<!-- 										style="color: red;">* </strong>Policy Number</label> -->
<!-- 		                </div> -->
<!-- 	             		<div class="col-md-5 mb-2"> -->
<!-- 								<input id="policy_no" name="policy_no" class="form-control-sm form-control " -->
<!-- 										autocomplete="off"  value="" maxlength="50" placeholder="Enter Policy No"/> -->
<!-- 										<span class="focus-border"><i></i></span> -->
<!-- 						</div> -->
<!-- 	             	</div> -->
<!--              	</div> -->
             	
             	
	
             	<div class="col-12 "> 
	             	<div class="row form-group"> 
	             		<div class="col-md-7 col-lg-6 col-xl-4 mb-2">  
		                	<label class=" form-control-label"><strong
										style="color: red;">* </strong>Initial Date</label>
		                </div>
	             		<div class="col-md-5 mb-2">


												<input type="text" name="initial_date" id="initial_date"
												maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control-sm form-control  " style="width: 86%; display: inline;"											
												onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
												onkeyup="clickclear(this, 'DD/MM/YYYY')"
												onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); "
												aria-required="true" autocomplete="off" " value="DD/MM/YYYY">
												<span class="focus-border"><i></i></span>
						</div>
	             	</div>
             	</div>
				
			
	    	   	<div class="col-12 "> 
	             	<div class="row form-group"> 
	             		<div class="col-md-7 col-lg-6 col-xl-4 mb-2 ">  
		                	<label class=" form-control-label"><strong
										style="color: red;">* </strong>Upload Policy Draft</label>
		                </div>
	             		<div class="col-md-5 mb-2">
								<input type="file" accept=".pdf" id="policy_draft_file" name="policy_draft_file"class="form-control ">
									<input type="hidden" id="policy_draft_hid" name="policy_draft_hid" class="form-control" >
										<input type="hidden" id="policy_no" name="policy_no" class="form-control" >
										<span class="focus-border"><i></i></span>
										
						</div>
	             	</div>
             	</div>


				<div class="col-12">
			             <div class="section-heading"> 
						<h3>FORWARD DRAFT</h3>		
					  </div>
		    	</div>
	    	  	<div class="col-12 " > 
	             	<div class="row form-group"> 
	             	<div class="col-md-2 col-2">
								 <input type = "checkbox" name = "forward_draft" id="forward_draft" onclick="myFunction()" />
        						
									<input type="hidden" id="policy_draft_hid" name="policy_draft_hid" class="form-control" >
										<span class="focus-border"><i></i></span>
										
						</div>
	             		<div class="col-md-10 col-10">  
		                	
					<label class=" form-control-label"><strong
										style="color: red;"></strong>FORWARD DRAFT ${forword}</label>
		                </div>
	             		
	             	</div>
             	</div>
             	
             	
<!-- 	    	   	</div> -->
<!-- 	    	</div> -->
				<div class="card-footer" align="center">
					<a href="PolicysearchUrl" id="PolicysearchUrl"
										class="btn-cancel">BACK</a>
										
										
					<input type="button" class="btn-update" id="update_btn" value="UPDATE DRAFT" onclick="return saveData();" >
	        		<input type="button" class="btn-save" id="forward_btn" value="FORWARD DRAFT" onclick="return saveData();">
	        		
<!-- 	        		<button type="submit" class="btn-update" id="update_btn" value="Update Draft" onclick="return isUpdate();">UPDATE DRAFT</button> -->
<!-- 	        		<button type="submit" class="btn-save" id="forward_btn" value="Forward Draft" onclick="return isUpdate();">FORWARD DRAFT</button> -->
	        		
<!-- 	        	        <a href="PolicysearchUrl" id="PolicysearchUrl"	class="btn-cancel">Back</a> 	 -->
<!-- 	              		<input type="submit" class="btn-save" value="Update Draft" onclick="return isValid();"> -->
<!-- 	              		<input type="submit" class="btn-save" id="update_btn" value="Update Draft" onclick="return isUpdate();" > -->
<!-- 	              		<input type="submit" class="btn-save" id="forward_btn" value="Forward Draft" onclick="return isUpdate();" > -->
<!-- 	              		<input type="button" class="btn btn-primary btn-sm" value="Forward to Executive committee" onclick=""> -->
									<input type="hidden" id="id_org" name="id_org" class="form-control" >

	            </div>
           </div>
	              	</div>
	              	</div>
	              
	              
	              </form:form>
	              	</div>
	              	
</div>
</div>
</div>

	
		
<script>

	 $(document).ready(function() {
			$("#id_org").val('${id_org}');

			 $("#update_btn").show();
			 $("#forward_btn").hide();
			datepicketDate('initial_date');

		 $(".col-3 input").val("");
		 $(".input-effect input select").focusout(function(){
				if($(this).val() != ""){
					$(this).addClass("has-content");
				}else{
					$(this).removeClass("has-content");
				}
			})
			

			var p_type ='${editcmd.policy_type}';
			if(p_type == 2){
				$( "#Renewal" ).prop( "checked", true );
				$( "#Initial" ).prop( "checked", false );				
			}else {
				$( "#Initial" ).prop( "checked", true );
				$( "#Renewal" ).prop( "checked", false );
			}
			
			$("#policy_type").val('${editcmd.policy_type}');
			$("#policy_category").val('${editcmd.policy_category}');
			 var cat_val = $('#policy_category :selected').text(); 
			 document.getElementById("policy_cat_p").innerHTML = cat_val;

			
			$("#policy_category").change();
			$("#policy_unique_id").val('${editcmd.policy_unique_id}');
			
			
			$("#purpose").val('${editcmd.purpose}');
			$("#scope").val('${editcmd.scope}');
			$("#policy_title").val('${editcmd.policy_title}');
			$("#policy_no").val('${editcmd.policy_no}');
		//	$("#initial_date").val('${editcmd.initial_date}');
			var initial_date = '${editcmd.initial_date}';
		
			var initial_date2 = initial_date.substring(0,10);
			var initial_date_i = initial_date2.split("-");
		    var new_initial_date = initial_date_i[2]+"/"+initial_date_i[1]+"/"+initial_date_i[0];
			$("#initial_date").val(new_initial_date);
			$("#policy_draft_hid").val('${hidden_draft}');
			
			$("#policy_sub_category").val('${editcmd.policy_sub_category}');
			 var sub_cat_val = $('#policy_sub_category :selected').text(); 
			 document.getElementById("policy_sub_cat_p").innerHTML = sub_cat_val;
			 
			$("#policy_sub_category").change();

			
	 });



// 	 function isUpdate() {
		 
// 		  var checkBox = document.getElementById("forward_draft");
		
		  

		
// 		  if (checkBox.checked == true){
// 			  alert("Draft Will Be Forwarded ")
// 		  } else {
// 			  alert("Data Updated Successfully")
// 		  }
// 		}

	 
	 function myFunction() {
		 
		  var checkBox = document.getElementById("forward_draft");
		  if (checkBox.checked == true){
			  $("#update_btn").hide();
			  $("#forward_btn").show();
		  } else {
			  $("#update_btn").show();
			  $("#forward_btn").hide();
		  }
		}


		function getSubpolicy(){
		var selval = $("#policy_category").val(); 

		 $.ajaxSetup({
	         async : false
		 }); 
	$.post("getSubpolicyUrl?"+key+"="+value,{selval:selval},function(j) {	
		
 	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
 	for ( var i = 0; i < j.length; i++) {

				options += '<option   value="' + j[i].id + '" name="'+j[i].id+'" >'+ j[i].subcategory + '</option>';
		}	
		$("select#policy_sub_category").html(options); 	
	});  
}

</script>

<script>


function isValidate() {


//		if ($("#purpose").val() == "") {
//			alert("Please Enter Purpose");
//			return false;
//		}

//		if ($("#scope").val() == "") {
//			alert("Please Enter scope");
//			return false;
//		}

//		if ($("#policy_title").val() == "") {
//			alert("Please Enter Policy Title");
//			return false;
//		}

//		if ($("#initial_date").val() == "") {
//			alert("Please Select Date");
//			return false;
//		}


//		if ($("#initial_date").val() == "DD/MM/YYYY") {
//			alert("Please Select Date");
//			return false;
//		}

		
		return true;
	}


function saveData(){
			if(isValidate()==true){
			var form = $('#edit_policy_form')[0];

				var data = new FormData(form);

				$.ajax({
					type : "POST",
					enctype : 'multipart/form-data',
					url : "edit_policy_Action?"+key+"="+value,
					data : data,
					processData : false,
					contentType : false,
					cache : false,
					timeout : 600000,
					success : function(data) {
						if(data=="purpose"){
							alert("Please Enter Purpose.");
							$("#purpose").focus();
							return false;
						}if(data=="scope"){
							alert("Please Enter Scope.");
							$("#scope").focus();
							return false;
						}if(data=="policy_title"){
							alert("Please Enter Policy Title.");
							$("#policy_title").focus();
							return false;
						}if(data=="initial_date"){
							alert("Please Enter Initial Date.");
							$("#initial_date").focus();
							return false;
						}
						//	alert(data);
								
							if( data=="Update Successfully"){
							
								 window.location = 'PolicysearchUrl?msg='+data;	
							}
						}
					//}
				});
			}
		}



</script>
		
		
		
	
	


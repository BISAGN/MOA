<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet" href="js/Calender/jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>


<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>

<form:form name="Formname" id="Formid" action="Part_b_examinationAction" method="POST" modelAttribute="ARM_SECTION_MASTERCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>EXAMINATION CENTRE</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
				 
				 <div class='col-md-12'>
					 <div class='col-md-6'>
						  <div class='row form-group'>
						      <div class='col col-md-4'>
						            <label for="text-input" class="form-control-label">Exam Name<strong style="color: red;">*</strong></label>
					          </div>
					          <div class="col-md-6 select-wrapper">
													<select id="type_of_allotment" name="type_of_allotment" class="form-control">
														<option value="0">--Select--</option>
														<option value="DSCC/TSOC">DSCC/TSOC</option>
														<option value="Part D">Part D</option>
														<option value="Part B">Part B</option>
													</select>
												</div>
					    </div>
					</div>
					 
				</div>


	<div class="container" align="center" >
		<div class="card container" id="no1" style="margin-top: 10px;">
			<div class="panel-group" id="accordion">
				<div class="panel panel-default" id="insp_div1">
					<div id="collapse1in" >
						<div class="card-body card-block">
							<div class="card-body card-block" id="total_table">
								<div class="card-body-header">
								</div>
								<input type="hidden" id="hdaddcountInsp" name="hdaddcountInsp"	value="1" autocomplete="off" class="form-control" />
								<table	class="table no-margin table-striped  table-hover  table-bordered "	id="insp_addQuantity">
									<thead	style="background-color: #2a4267; color: white; text-align: center;">
										<tr>
											<th align="center">Ser No</th>
											<th style="width: 30%;">Center Name</th>
											<th style="width: 20%;">Command Name</th>
											<th style="width: 20%;">Conduction Formation</th>
<!-- 											<th style="width: 15%;">APPLICABLE</th> -->
<!-- 											<th style="width: 15%;">TSOC</th> -->
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
											<tr id="tr_id1">
												<td align="center">1</td>
												<td align="center">
												
												<input type="text" id="description1" name="description1" onkeypress="return alpha_anb_isAllowKey(event);" autocomplete="off" class="form-control" maxlength="100"/></td>
												
												<td align="center"><input type="text" id="qty1" name="qty1" onkeypress="return isNumberKeyDouble2Decimal(this, event);" autocomplete="off" class="form-control" maxlength="10"/></td>
												<td align="center"><input type="text" id="rate1" name="rate1" onkeypress="return isNumberKeyDouble2Decimal(this, event);" autocomplete="off" class="form-control" maxlength="10"/></td>
												
												
												
<!-- 												<td align="center"> -->
<!-- 												  <input type="checkbox" id="unit1" name="unit1" value="Bike"></td> -->
												<td align="center"><a class="btn btn-success btn-sm" onclick="formopen(1)" id="id_add1" title='ADD'>
												<i class="fa fa-plus"></i></a></td>
											</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="insp_count" name="insp_count" value='1'>
			<input type="hidden" id="id" name="id" value="0">
		</div>
</div>	


				
				
				
				
				
				


<script>
var x=1;
function formopen(a){
// 		if(isValidate_for_store_rec()==false){
// 			 return false;
// 		}

$("#add_group"+x).hide();
	$("#group_remove"+x).hide();
	x= x+1;
	if(x < 26){
		 $("input#hdaddcountInsp").val(x);
	
	if(a == "0")
	{
		a=  $("#hdaddcountInsp").val();
	}	
	$("#total_table").show();
	 $("#id_add"+a).hide();
	 $("#id_remove"+a).hide();
	 x=0;
	 x= parseInt(a)+1;
		 $("input#insp_count").val(x);
		 $("table#insp_addQuantity").append('<tr align="center" id="tr_id'+x+'"><td>'+x+'</td>'
				+'<td align="center" style="width: 30%;"><input type="text" name="description'+x+'" id="description'+x+'"  onkeypress="return alpha_anb_isAllowKey(event);" class="form-control" maxlength="100" autocomplete="off" /></td>'
			 	+'<td align="center" style="width: 20%;"><input type="text" name="qty'+x+'" id="qty'+x+'"  class="form-control" autocomplete="off" maxlength="10"/></td>'
	 			
			 	
			 	+'<td align="center"><input type="text" name="rate'+x+'" id="rate'+x+'"   /></td>'
// 	 			+'<td align="center"><input type="checkbox" name="unit'+x+'" id="unit'+x+'"   /></td>'
	 			+'<td align="center" id = "h_insp_id'+x+'" style="display:none;"><input type="text" name="hdid'+x+'" id="hdid'+x+'" class="form-control" value="0" autocomplete="off" /></td>'
	   		    +'<td><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add'+x+'" onclick="formopen('+x+');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "id_remove'+x+'" onclick="formopen_re('+x+'); "><i class="fa fa-trash"></i></a></td>' 
	   		    +'</tr>');
		 datepicketDate("sub_dt"+x);
	}
	
	else{
		alert("Please Enter max 25 Quantity");
		 if ( x == 26){
			 x = x-1; 
			 $("#id_remove"+x).show();
		 }	   
}
	
	
	
	
	
}
 
function formopen_re(R){
	 $("tr#tr_id"+R).remove();
	 x = R-1;
	 $("input#insp_count").val(x);
	 $("#id_add"+x).show();
	 $("#id_remove"+x).show();
} 
 
 var InspData = "";
</script>
				

 















    </div>
       <div class='card-footer' align='center'>
           <input type='reset' class='btn btn-success btn-sm' value='Edit' onclick='clearall()'>
           <input type='reset' class='btn btn-success btn-sm' value='Cancel' onclick='clearall()'>
           <input type='submit' class='btn btn-primary btn-sm' value='Save'  onclick='return isValidateClientSide()'>
<!--            <input type='button' id='printId' class='btn btn-info btn-sm' value='Delete' onclick='printDiv();'> -->
           
           <a href="commonDashboard" class="btn btn-danger btn-sm">Close</a>
       </div>
    </div>
  </div>
</form:form>



 <script>


$(document).ready(function () {

	
});


 </script>

 
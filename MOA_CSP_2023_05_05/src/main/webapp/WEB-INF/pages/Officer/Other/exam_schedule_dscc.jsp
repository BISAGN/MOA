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
    <div class="card-header"> <h5>EXAMINATION SCHEDULE</h5></div>
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
					 <div class='col-md-6'>
						  <div class='row form-group'>
						      <div class='col col-md-4'>
						        <label for="text-input" class=" form-control-label">Begin Date</label>
					          </div>
					          <div class='col-12 col-md-6'>
						         <input type="text" id="opd_date_of_comm" name="opd_date_of_comm" maxlength="10" value="DD/MM/YYYY"  
						         class='form-control' style="width: 85%;display: inline;" autocomplete='off' 
						         onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" 
						         onkeyup="clickclear(this, 'DD/MM/YYYY')" 
													onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></input>
					
					        </div>
					    </div>
			       </div>
				</div>
				<div class="col-md-12"  style="margin-top: 10px;margin-bottom: 10px;">
<!-- 			           <div class="col-md-11">	 -->
							<div class="row form-group ">
<!-- 				             		<div class=" col-md-6" > -->
<!-- 				             			<label for="text-input" class=" form-control-label"></label> -->
<!-- 				             		</div> -->
					             	<div class=" col-md-6">
			               				<label for="type1" class="form-check-label ">
            									<input type="radio" id="type3" name="type5" class="form-check-input" onchange="radio_btn(this.value)" value="Manual Index Numbers" checked/>Manual Index Numbers</label>&nbsp;&nbsp;&nbsp;
										<label for="type2" class="form-check-label ">
										<input type="radio" id="type4" name="type5" class="form-check-input" onchange="radio_btn(this.value)"  value="Automatic Index Numbers" />Automatic Index Numbers</label>&nbsp;&nbsp;&nbsp;
									</div>
			             	</div>
<!-- 						</div> -->
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
											<th style="width: 30%;">Subject Name<span class="star_design">*</span></th>
											<th style="width: 20%;">Subject Cut-Off<span class="star_design">*</span></th>
											<th style="width: 20%;">Date</th>
											<th style="width: 15%;">DSCC</th>
											<th style="width: 15%;">TSOC</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
											<tr id="tr_id1">
												<td align="center">1</td>
												<td align="center">
												
												<input type="text" id="description1" name="description1" onkeypress="return alpha_anb_isAllowKey(event);" autocomplete="off" class="form-control" maxlength="100"/></td>
												
												<td align="center"><input type="text" id="qty1" name="qty1" onkeypress="return isNumberKeyDouble2Decimal(this, event);" autocomplete="off" class="form-control" maxlength="10"/></td>
												<td align="center"> 
														<input type="text" name="sub_dt1" id="sub_dt1" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
															style="width: 85%; display: inline;" onfocus="this.style.color='#000000'" 
															
															onkeyup="clickclear(this, 'DD/MM/YYYY');" 
															onchange="clickrecall(this,'DD/MM/YYYY');"
															 aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"/>
													</td>
												
												<td align="center">
												  <input type="checkbox" id="rate1" name="rate1" value="Bikew"></td>
												<td align="center">
												  <input type="checkbox" id="unit1" name="unit1" value="Bike"></td>
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

<div class='col-md-12'>
					 <div class='col-md-4'>
						  <div class='row form-group'>
						      <div class='col col-md-3'>
						        <label for="text-input" class=" form-control-label">DSSC Start Date</label>
					          </div>
					          <div class='col-md-7'>
						         <input type="text" id="opd_date_of_seniority" name="opd_date_of_seniority" maxlength="10" value="DD/MM/YYYY"  
						         class='form-control' style="width: 85%;display: inline;" autocomplete='off' 
						         onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" 
						         onkeyup="clickclear(this, 'DD/MM/YYYY')" 
													onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></input>
					
					        </div>
					    </div>
			       </div>
					 <div class='col-md-4'>
						  <div class='row form-group'>
						      <div class='col col-md-3'>
						        <label for="text-input" class=" form-control-label">TSOC Tech Start Date</label>
					          </div>
					          <div class=' col-md-7'>
						         <input type="text" id="opd_date_of" name="opd_date_of" maxlength="10" value="DD/MM/YYYY"  
						         class='form-control' style="width: 85%;display: inline;" autocomplete='off' 
						         onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" 
						         onkeyup="clickclear(this, 'DD/MM/YYYY')" 
													onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></input>
					
					        </div>
					    </div>
			       </div>
			       <div class='col-md-4'>
						  <div class='row form-group'>
						      <div class='col col-md-4'>
						        <label for="text-input" class=" form-control-label">TSOC Non-Tech Start Date</label>
					          </div>
					          <div class=' col-md-7'>
						         <input type="text" id="opd_date_of_n" name="opd_date_of_n" maxlength="10" value="DD/MM/YYYY"  
						         class='form-control' style="width: 85%;display: inline;" autocomplete='off' 
						         onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" 
						         onkeyup="clickclear(this, 'DD/MM/YYYY')" 
													onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></input>
					
					        </div>
					    </div>
			       </div>
			       
				</div>
				
				<div class='col-md-12' style="margin-top: 10px;">
						<div class="col-md-6 ">
						<div class="row form-group">
							<div class="col-md-5">
								<label for="username">DSSC Course Number</label>
							</div>
							<div class="col-md-6">
								<input type="text" id="tender_id" name="tender_id" class="form-control" autocomplete="off" maxlength="30" >
							</div>
						</div>
					</div>
					<div class="col-md-6 ">
						<div class="row form-group">
							<div class="col-md-5">
								<label for="username">TSOC Course Number</label>
							</div>
							<div class="col-md-6">
								<input type="text" id="tender_id" name="tender_id" class="form-control" autocomplete="off" maxlength="30" >
							</div>
						</div>
					</div>
				</div>
				
				<div class='col-md-12' style="margin-top: 10px;">
						<div class="col-md-6 ">
						<div class="row form-group">
							<div class="col-md-5">
								<label for="username">DSSC Vacancy</label>
							</div>
							<div class="col-md-6">
								<input type="text" id="tender_id" name="tender_id" class="form-control" autocomplete="off" maxlength="30" >
							</div>
						</div>
					</div>
					<div class="col-md-6 ">
						<div class="row form-group">
							<div class="col-md-5">
								<label for="username">TSOC Reserves</label>
							</div>
							<div class="col-md-6">
								<input type="text" id="tender_id" name="tender_id" class="form-control" autocomplete="off" maxlength="30" >
							</div>
						</div>
					</div>
				</div>
				
				<div class='col-md-12' style="margin-top: 10px;margin-bottom: 8px;">
						<div class="col-md-6 ">
						<div class="row form-group">
							<div class="col-md-5">
								<label for="username">TSOC Vacancy</label>
							</div>
							<div class="col-md-6">
								<input type="text" id="tender_id" name="tender_id" class="form-control" autocomplete="off" maxlength="30" >
							</div>
						</div>
					</div>
					<div class="col-md-6 ">
						<div class="row form-group">
							<div class="col-md-5">
								<label for="username">TSOC Reserves</label>
							</div>
							<div class="col-md-6">
								<input type="text" id="tender_id" name="tender_id" class="form-control" autocomplete="off" maxlength="30" >
							</div>
						</div>
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
	 			
	 			+'<td>' 
			    +' <input type="text" name="sub_dt'+x+'" id="sub_dt'+x+'" maxlength="10" value="DD/MM/YYYY"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
			    +'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
		     	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >'
			    + '</td>'
			   
			 	
			 	+'<td align="center"><input type="checkbox" name="rate'+x+'" id="rate'+x+'"   /></td>'
	 			+'<td align="center"><input type="checkbox" name="unit'+x+'" id="unit'+x+'"   /></td>'
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
           <input type='submit' class='btn btn-primary btn-sm' value='Save'  onclick='return isValidateClientSide()'>
<!--            <input type='button' id='printId' class='btn btn-info btn-sm' value='Delete' onclick='printDiv();'> -->
           <input type='reset' class='btn btn-success btn-sm' value='Delete Schedule' onclick='clearall()'>
           <a href="commonDashboard" class="btn btn-danger btn-sm">Close</a>
       </div>
    </div>
  </div>
</form:form>



 <script>


$(document).ready(function () {

	datepicketDate('sub_dt1');
	
	$('#sub_dt').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
	changeMonth: true,
	changeYear: true,
	yearRange: '1890:2099'
	});
	$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
	
	
	$('#opd_date_of_comm').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
	changeMonth: true,
	changeYear: true,
	yearRange: '1890:2099'
	});
	$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
	
	$('#opd_date_of').datepicker({
		showOn: 'both',
		buttonImageOnly: true,
		buttonImage: 'js/Calender/cal_ico.png',
		dateFormat: 'dd/mm/yy',
		changeMonth: true,
		changeYear: true,
		yearRange: '1890:2099'
		});
		$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
	
	$('#opd_date_of_seniority').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
	changeMonth: true,
	changeYear: true,
	yearRange: '1890:2099'
	});
	$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
	
	
	$('#opd_date_of_n').datepicker({
		showOn: 'both',
		buttonImageOnly: true,
		buttonImage: 'js/Calender/cal_ico.png',
		dateFormat: 'dd/mm/yy',
		changeMonth: true,
		changeYear: true,
		yearRange: '1890:2099'
		});
		$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
	
});


 </script>

 
 <script>
 function clickclear(a, b) {
		if (a.value == b) {
			a.value = "";
			a.style.color = "#000000"
		}else{
			a.addEventListener('keydown', function(event) {
	            const key = event.key; 
	            if (key === "Backspace" || key === "Delete") { }
	            else{
	            	var fvalue = a.value;
	    			if(fvalue.length == 2){
	    				a.value = a.value+"/"; 
	    			}
	    			if(fvalue.length == 5){
	    				a.value = a.value+"/"; 
	    			}
	            }
	        });
		} 
	}
 function clickrecall(a, b) {
		if (a.value == b || a.value == "") {
			a.value = b
		} else {
			a.style.color = "#000000"
		}
	}
 
 function datepicketDate(inpt){
		$('#'+inpt).datepicker({showOn: 'both', buttonImageOnly: true,
			buttonImage: 'js/Calender/cal_ico.png',
			dateFormat: 'dd/mm/yy',
			changeMonth: true,
			changeYear: true,
			yearRange: '1890:2099',
			maxDate: new Date()
		});
		
		$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
	}
	</script>
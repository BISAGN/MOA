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
    <div class="card-header"> <h5>JC COURSE DETAILS</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
				 
				 

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
											<th style="width: 30%;">JC COURSE NO</th>
											<th style="width: 20%;">BEG DATE</th>
											<th style="width: 20%;">END DATE</th>
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
												
												<td align="center"> 
														<input type="text" name="beg_dt1" id="beg_dt1" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
															style="width: 85%; display: inline;" onfocus="this.style.color='#000000'" 
															
															onkeyup="clickclear(this, 'DD/MM/YYYY');" 
															onchange="clickrecall(this,'DD/MM/YYYY');"
															 aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"/>
													</td>
													<td align="center"> 
														<input type="text" name="end_dt1" id="end_dt1" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
															style="width: 85%; display: inline;" onfocus="this.style.color='#000000'" 
															
															onkeyup="clickclear(this, 'DD/MM/YYYY');" 
															onchange="clickrecall(this,'DD/MM/YYYY');"
															 aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"/>
													</td>
												
												
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
				+'<td>' 
			    +' <input type="text" name="beg_dt'+x+'" id="beg_dt'+x+'" maxlength="10" value="DD/MM/YYYY"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
			    +'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
		     	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >'
			    + '</td>'
			   
			    +'<td>' 
			    +' <input type="text" name="end_dt'+x+'" id="end_dt'+x+'" maxlength="10" value="DD/MM/YYYY"  onclick="clickclear(this, \'DD/MM/YYYY\')"   class="form-control" style="width: 85%;display: inline;" '
			    +'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
		     	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >'
			    + '</td>'
			   
	 			+'<td align="center" id = "h_insp_id'+x+'" style="display:none;"><input type="text" name="hdid'+x+'" id="hdid'+x+'" class="form-control" value="0" autocomplete="off" /></td>'
	   		    +'<td><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add'+x+'" onclick="formopen('+x+');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "id_remove'+x+'" onclick="formopen_re('+x+'); "><i class="fa fa-trash"></i></a></td>' 
	   		    +'</tr>');
		 datepicketDate("beg_dt"+x);
		 datepicketDate("end_dt"+x);
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
           
           <a href="commonDashboard" class="btn btn-danger btn-sm">Close</a>
       </div>
    </div>
  </div>
</form:form>



 <script>


$(document).ready(function () {

datepicketDate('beg_dt1');
datepicketDate('end_dt1');

$('#end_dt').datepicker({
showOn: 'both',
buttonImageOnly: true,
buttonImage: 'js/Calender/cal_ico.png',
dateFormat: 'dd/mm/yy',
changeMonth: true,
changeYear: true,
yearRange: '1890:2099'
});
$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});

	
	$('#beg_dt').datepicker({
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
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
    <div class="card-header"> <h5>GENERATE Nomination Letters</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
				 
				 <div class='col-md-12'>
					 <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">Exam Schedule</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="date_1" name="date_1" maxlength="10" value="DD/MM/YYYY"  
							         class='form-control' style="width: 85%;display: inline;" autocomplete='off' 
							         onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" 
							         onkeyup="clickclear(this, 'DD/MM/YYYY')" 
														onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></input>
						
						        </div>
						    </div>
				       </div>
					
										 
				</div>
				
				
				<div class='col-md-12' style="margin-top: 10px;margin-bottom: 10px;">
					 <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">DSCC Letter No</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="tender" name="tender" class="form-control" autocomplete="off" maxlength="30" >
						
						        </div>
						    </div>
				       </div>
				       
				        <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">TSOC Letter No</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="tender" name="tender" class="form-control" autocomplete="off" maxlength="30" >
						
						        </div>
						    </div>
				       </div>
			      </div> 
				
				<div class='col-md-12' style="margin-top: 10px;">
				<div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">DSCC Letter Date</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="date_2" name="date_2" maxlength="10" value="DD/MM/YYYY"  
							         class='form-control' style="width: 85%;display: inline;" autocomplete='off' 
							         onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" 
							         onkeyup="clickclear(this, 'DD/MM/YYYY')" 
														onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></input>
						
						        </div>
						    </div>
				       </div>
					 <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">TSOC Letter Date</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="date_3" name="date_3" maxlength="10" value="DD/MM/YYYY"  
							         class='form-control' style="width: 85%;display: inline;" autocomplete='off' 
							         onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" 
							         onkeyup="clickclear(this, 'DD/MM/YYYY')" 
														onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></input>
						
						        </div>
						    </div>
				       </div>
				       
				       
			      </div> 
			      
			       <div class='col-md-12' style="margin-top: 10px;">
					 <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">DSSC Course No</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="tender" name="tender" class="form-control" autocomplete="off" maxlength="30" >
						
						        </div>
						    </div>
				       </div>
				       
				       <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">TSOC Course No</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="tender" name="tender" class="form-control" autocomplete="off" maxlength="30" >
						
						        </div>
						    </div>
				       </div>
				       
				       
			      </div> 
			      
			      
			      <div class='col-md-12' style="margin-top: 10px;">
					 <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">DSSC Start Date</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="date_4" name="date_4" maxlength="10" value="DD/MM/YYYY"  
							         class='form-control' style="width: 85%;display: inline;" autocomplete='off' 
							         onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" 
							         onkeyup="clickclear(this, 'DD/MM/YYYY')" 
														onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></input>
						
						        </div>
						    </div>
				       </div>
				       
				        <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">TSOC Tech Start Date</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="date_5" name="date_5" maxlength="10" value="DD/MM/YYYY"  
							         class='form-control' style="width: 85%;display: inline;" autocomplete='off' 
							         onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" 
							         onkeyup="clickclear(this, 'DD/MM/YYYY')" 
														onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></input>
						
						        </div>
						    </div>
				       </div>
				       
				       
			      </div> 
			      
			      <div class='col-md-12' style="margin-top: 10px;">
					 <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">Director Name</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="tender" name="tender" class="form-control" autocomplete="off" maxlength="30" >
						
						        </div>
						    </div>
				       </div>
				       <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">TSOC Non-Tech Start Date</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="date_6" name="date_6" maxlength="10" value="DD/MM/YYYY"  
							         class='form-control' style="width: 85%;display: inline;" autocomplete='off' 
							         onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" 
							         onkeyup="clickclear(this, 'DD/MM/YYYY')" 
														onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></input>
						
						        </div>
						    </div>
				       </div>
				       
				       
			      </div> 
			      
			       <div class='col-md-12' style="margin-top: 10px;margin-bottom: 10px;">
					 <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">DSCC DO Letter No</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="tender" name="tender" class="form-control" autocomplete="off" maxlength="30" >
						
						        </div>
						    </div>
				       </div>
				       <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">TSOC DO Letter No</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="tender" name="tender" class="form-control" autocomplete="off" maxlength="30" >
						
						        </div>
						    </div>
				       </div>
				       
			      </div> 
			      
			     


    </div>
       <div class='card-footer' align='center'>
           <input type='reset' class='btn btn-success btn-sm' value='Address' onclick='clearall()'>
           <input type='reset' class='btn btn-success btn-sm' value='Cancel' onclick='clearall()'>
           <input type='submit' class='btn btn-primary btn-sm' value='Generate Nomination Letters'  onclick='return isValidateClientSide()'>
           
<!--            <input type='button' id='printId' class='btn btn-info btn-sm' value='Delete' onclick='printDiv();'> -->
           
           <a href="commonDashboard" class="btn btn-danger btn-sm">Close</a>
           <input type='submit' class='btn btn-primary btn-sm' value='Generate DO Letters'  onclick='return isValidateClientSide()'>
       </div>
    </div>
  </div>
</form:form>



 <script>


$(document).ready(function () {

	
	$('#date_1').datepicker({
		showOn: 'both',
		buttonImageOnly: true,
		buttonImage: 'js/Calender/cal_ico.png',
		dateFormat: 'dd/mm/yy',
		changeMonth: true,
		changeYear: true,
		yearRange: '1890:2099'
		});
		$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
		
		$('#date_2').datepicker({
			showOn: 'both',
			buttonImageOnly: true,
			buttonImage: 'js/Calender/cal_ico.png',
			dateFormat: 'dd/mm/yy',
			changeMonth: true,
			changeYear: true,
			yearRange: '1890:2099'
			});
			$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
		
		$('#date_3').datepicker({
		showOn: 'both',
		buttonImageOnly: true,
		buttonImage: 'js/Calender/cal_ico.png',
		dateFormat: 'dd/mm/yy',
		changeMonth: true,
		changeYear: true,
		yearRange: '1890:2099'
		});
		$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
		
		$('#date_4').datepicker({
			showOn: 'both',
			buttonImageOnly: true,
			buttonImage: 'js/Calender/cal_ico.png',
			dateFormat: 'dd/mm/yy',
			changeMonth: true,
			changeYear: true,
			yearRange: '1890:2099'
			});
			$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
		
		$('#date_5').datepicker({
			showOn: 'both',
			buttonImageOnly: true,
			buttonImage: 'js/Calender/cal_ico.png',
			dateFormat: 'dd/mm/yy',
			changeMonth: true,
			changeYear: true,
			yearRange: '1890:2099'
			});
			$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
			
			$('#date_6').datepicker({
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

 
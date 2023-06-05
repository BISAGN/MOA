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
    <div class="card-header"> <h5>GENERATE BOOKLET</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
				 
				 <div class='col-md-12'>
					 <div class='col-md-6'>
						  <div class='row form-group'>
						      <div class='col col-md-4'>
						            <label for="text-input" class="form-control-label">Select Examination <strong style="color: red;">*</strong></label>
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
				
				<div class='col-md-12' style="margin-top: 10px;">
					 <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">Exam Begin Date</label>
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
				       
				       <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">Error Notification Date</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="opd_date_of" name="opd_date_of" maxlength="10" value="DD/MM/YYYY"  
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
							        <label for="text-input" class=" form-control-label">Exam Begin Date<br>(as on Reports)</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="opd_date" name="opd_date" maxlength="10" value="DD/MM/YYYY"  
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
							        <label for="text-input" class=" form-control-label">Duty officer Senabhavan</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="tender_id" name="tender_id" class="form-control" autocomplete="off" maxlength="30" >
						
						        </div>
						    </div>
				       </div>
			      </div> 
			      
			      <div class='col-md-12' style="margin-top: 10px;">
					 <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">Exam End Date<br>(as on Reports)</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="exm_end_date" name="exm_end_date" maxlength="10" value="DD/MM/YYYY"  
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
							        <label for="text-input" class=" form-control-label">Reception Senabhavan and South Block</label>
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
							        <label for="text-input" class=" form-control-label">Name of the Director</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="tender" name="tender" class="form-control" autocomplete="off" maxlength="30" >
						
						        </div>
						    </div>
				       </div>
				       
				       
			      </div> 
			      
			       <div class='col-md-12' style="margin-top: 10px;margin-bottom: 10px;">
					 <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">Letter No</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="tender" name="tender" class="form-control" autocomplete="off" maxlength="30" >
						
						        </div>
						    </div>
				       </div>
				       
				        <div class='col-md-6'>
							  <div class='row form-group'>
							      <div class='col col-md-4'>
							        <label for="text-input" class=" form-control-label">Letter Dated</label>
						          </div>
						          <div class='col-12 col-md-6'>
							         <input type="text" id="letter_date" name="letter_date" maxlength="10" value="DD/MM/YYYY"  
							         class='form-control' style="width: 85%;display: inline;" autocomplete='off' 
							         onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" 
							         onkeyup="clickclear(this, 'DD/MM/YYYY')" 
														onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></input>
						
						        </div>
						    </div>
				       </div>
			      </div> 


    </div>
       <div class='card-footer' align='center'>
<!--            <input type='reset' class='btn btn-success btn-sm' value='Edit' onclick='clearall()'> -->
<!--            <input type='reset' class='btn btn-success btn-sm' value='Cancel' onclick='clearall()'> -->
           <input type='submit' class='btn btn-primary btn-sm' value='Generate Booklet'  onclick='return isValidateClientSide()'>
<!--            <input type='button' id='printId' class='btn btn-info btn-sm' value='Delete' onclick='printDiv();'> -->
           
           <a href="commonDashboard" class="btn btn-danger btn-sm">Close</a>
       </div>
    </div>
  </div>
</form:form>



 <script>


$(document).ready(function () {

	
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
		
		$('#letter_date').datepicker({
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
		
		$('#exm_end_date').datepicker({
			showOn: 'both',
			buttonImageOnly: true,
			buttonImage: 'js/Calender/cal_ico.png',
			dateFormat: 'dd/mm/yy',
			changeMonth: true,
			changeYear: true,
			yearRange: '1890:2099'
			});
			$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
		
		$('#opd_date').datepicker({
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

 
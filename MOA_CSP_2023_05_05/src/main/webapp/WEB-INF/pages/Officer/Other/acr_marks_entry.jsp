<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>

<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="Formname" id="Formid" action="Part_b_examinationAction" method="POST" modelAttribute="ARM_SECTION_MASTERCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>ACR MARKS ENTRY</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Personal Number<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
<%-- 	             <form:input type="text" id="ac_arm_code" path="ac_arm_code"  class="form-control" autocomplete='off'  ></form:input> --%>
 					<input type="text" id="personal_number" name="personal_number" class="form-control" autocomplete='off' />
 <span class="errorClass" id="personal_number_lbl">${personal_number_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">ACR Marks<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
          <input type="text" id="OfficerName" name="OfficerName"  class="form-control" autocomplete='off' />
<%-- 	             <form:input type="text" id="ac_arm_description" path="ac_arm_description"  class="form-control" autocomplete='off'  ></form:input> --%>
 <span class="errorClass" id="OfficerName_lbl">${OfficerName_lbl}</span>
        </div>
    </div>
</div>



    </div>
       <div class='card-footer' align='center'>
           <input type='reset' class='btn btn-success btn-sm' value='Clear' onclick='clearall()'>
           <input type='submit' class='btn btn-primary btn-sm' value='Save'  onclick='return isValidateClientSide()'>
           <input type='button' id='printId' class='btn btn-info btn-sm' value='Print' onclick='printDiv();'>
       </div>
    </div>
  </div>
</form:form>



 <script>


$(document).ready(function () {


	$('#opd_dob').datepicker({
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
	

});

 function isValidateClientSide()
  {
    var errCount = 0;
//  document.getElementById("ac_arm_code_lbl").innerHTML="";
//  document.getElementById("ac_arm_description_lbl").innerHTML="";
//   if($("input#ac_arm_code").val().trim() == "")
//    {
//    errCount++;
//  document.getElementById("ac_arm_code_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Arm Code"; 
//  $("#ac_arm_code").focus(); 
//    } 
//   if($("input#ac_arm_description").val().trim() == "")
//    {
//    errCount++;
//  document.getElementById("ac_arm_description_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Arm Description"; 
//  $("#ac_arm_description").focus(); 
//    } 
//  if (errCount > 0) { 
// 	return false;
//  } else {
// 	return true;
//  }
 return true;
  } 

 </script>

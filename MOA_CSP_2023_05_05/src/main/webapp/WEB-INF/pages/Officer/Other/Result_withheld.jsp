<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>

<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="Formname" id="Formid" action="ExmschedulelockunlockAction" method="POST" modelAttribute="ARM_SECTION_MASTERCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>RESULTS WITHHELD</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
<div class="row">


 <div class='col-md-6 left_content'>
	 <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Select Examination<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-md-6'>
           <label for='' class="form-check-label"><input type="radio" id="dcc_exam_type1" path="dcc_exam_type"  value="0" />Part B  </label> &nbsp;&nbsp;
           <label for='' class="form-check-label"><input type="radio" id="dcc_exam_type2" path="dcc_exam_type"  value="1" />Part D  </label> &nbsp;&nbsp;
           <label for='' class="form-check-label"><input type="radio" id="dcc_exam_type3" path="dcc_exam_type"  value="2" />DSSC/TSOC  </label> &nbsp;&nbsp;
 <span class="errorClass" id="dcc_exam_type_lbl">${dcc_exam_type_lbl}</span>
        </div>
    </div>
</div>



 <div class='col-md-6 left_content'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Select Begin Date<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-md-6'>
	         <input type="text" id="dcc_area_leave_date" name="dcc_area_leave_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></input>
 <span class="errorClass" id="dcc_area_leave_date_lbl">${dcc_area_leave_date_lbl}</span>
        </div>
    </div>
</div>
</div>
&nbsp;&nbsp;
<div class="row">
<div class='col-md-6' >
	  <div class='row form-group'>
	     
          <div class='col-12 col-md-6'>
		    <label for='' class="form-check-label"><input type="radio" id="dcc_exam_type1" name="dcc_exam_type"  value="0" />With Held  </label> &nbsp;&nbsp;
           <label for='' class="form-check-label"><input type="radio" id="dcc_exam_type2" name="dcc_exam_type"  value="1" />Release  </label> &nbsp;&nbsp;
 <span class="errorClass" id="dcc_area_type_lbl">${dcc_area_type_lbl}</span>
        </div>
    </div>
</div>

 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Personal Code<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
<%-- 	             <form:input type="text" id="ac_arm_code" path="ac_arm_code"  class="form-control" autocomplete='off'  ></form:input> --%>
 					<input type="text" id="personal_number" name="personal_number" class="form-control" autocomplete='off' />
 <span class="errorClass" id="personal_number_lbl">${personal_number_lbl}</span>
        </div>
    </div>
</div>
</div>
&nbsp;&nbsp;
<div class="row" style="margin-bottom: 15px">
<div class='col-md-6' >
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Name<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-md-6'>
<%-- 	             <form:input type="text" id="ac_arm_code" path="ac_arm_code"  class="form-control" autocomplete='off'  ></form:input> --%>
 					<input type="text" id="personal_number" name="personal_number" class="form-control" autocomplete='off' />
 <span class="errorClass" id="personal_number_lbl">${personal_number_lbl}</span>
        </div>
    </div>
</div>

<div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Remarks<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-md-6'>
<%-- 	             <form:input type="text" id="ac_arm_code" path="ac_arm_code"  class="form-control" autocomplete='off'  ></form:input> --%>
 					<input type="text" id="personal_number" name="personal_number" class="form-control" autocomplete='off' />
 <span class="errorClass" id="personal_number_lbl">${personal_number_lbl}</span>
        </div>
    </div>
</div>
</div>
</div>

<div class='card-footer' align='center'>
       
           <input type='submit' class='btn btn-primary btn-sm' value='Lock'  onclick='return isValidateClientSide()'>
           <input type='button' id='printId' class='btn btn-info btn-sm' value='Close' onclick='printDiv();'>
       </div>

</div>
</div>
</form:form>

<script>

$(document).ready(function () {

	$('#dcc_area_leave_date').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
	changeMonth: true,
	changeYear: true,
	yearRange: '1890:2099'
	});
	$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
	$('#dcc_area_leave_date').datepicker({
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
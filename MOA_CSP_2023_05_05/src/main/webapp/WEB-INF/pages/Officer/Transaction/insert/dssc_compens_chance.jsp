<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<!-- <script type="text/javascript" src="js/jquery/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script> -->
<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="Formname" id="Formid" action="dssc_compens_chanceAction" method="POST" modelAttribute="dssc_compens_chanceCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>DSSC COMPENS CHANCE</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Select Examination<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="dcc_exam_type1" path="dcc_exam_type"  value="0" />DSSC  </label> &nbsp;&nbsp;
           <label for='' class="form-check-label"><form:radiobutton id="dcc_exam_type2" path="dcc_exam_type"  value="1" />TSOC  </label> &nbsp;&nbsp;
           <label for='' class="form-check-label"><form:radiobutton id="dcc_exam_type3" path="dcc_exam_type"  value="2" />Both  </label> &nbsp;&nbsp;
 <span class="errorClass" id="dcc_exam_type_lbl">${dcc_exam_type_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Personal No.<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="opd_personal_id" path="opd_personal_id"  class="form-control" autocomplete='off' maxlength="" ></form:input>
 <span class="errorClass" id="opd_personal_id_lbl">${opd_personal_id_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Name of Area/Fmn<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dcc_area" path="dcc_area"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="dcc_area_lbl">${dcc_area_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Type of Area<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="dcc_area_type" path="dcc_area_type"  class="form-control">
 		      <option value="">--Select--</option>
		      <option value="a">a</option>
		      <option value="b">b</option>
		      <option value="c">c</option>
	 </form:select>
 <span class="errorClass" id="dcc_area_type_lbl">${dcc_area_type_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Type of Entry<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="dcc_entry_type" path="dcc_entry_type"  class="form-control">
 		      <option value="">--Select--</option>
		      <option value="a">a</option>
		      <option value="b">b</option>
	 </form:select>
 <span class="errorClass" id="dcc_entry_type_lbl">${dcc_entry_type_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Year Part D Passed<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dcc_partd" path="dcc_partd"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="dcc_partd_lbl">${dcc_partd_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Year JC Course Done<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dcc_jc_year" path="dcc_jc_year"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="dcc_jc_year_lbl">${dcc_jc_year_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Year(s) chances availed for DSSC/TSOC<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dcc_chances" path="dcc_chances"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="dcc_chances_lbl">${dcc_chances_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Dispenasation granted earlier if any<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dcc_earlier_dispens" path="dcc_earlier_dispens"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="dcc_earlier_dispens_lbl">${dcc_earlier_dispens_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Dispensation asked for year<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dcc_dispes_year" path="dcc_dispes_year"  class="form-control" autocomplete='off' maxlength="" ></form:input>
 <span class="errorClass" id="dcc_dispes_year_lbl">${dcc_dispes_year_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Date of entry in the area<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dcc_area_entry_date" path="dcc_area_entry_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
 <span class="errorClass" id="dcc_area_entry_date_lbl">${dcc_area_entry_date_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Date of leaving the area<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dcc_area_leave_date" path="dcc_area_leave_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
 <span class="errorClass" id="dcc_area_leave_date_lbl">${dcc_area_leave_date_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Year Compensatory chance to be utilized<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dcc_compens_year" path="dcc_compens_year"  class="form-control" autocomplete='off' maxlength="" ></form:input>
 <span class="errorClass" id="dcc_compens_year_lbl">${dcc_compens_year_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Leave Duration<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dcc_leave" path="dcc_leave"  class="form-control" autocomplete='off' maxlength="" ></form:input>
 <span class="errorClass" id="dcc_leave_lbl">${dcc_leave_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Remarks<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dcc_remarks" path="dcc_remarks"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="dcc_remarks_lbl">${dcc_remarks_lbl}</span>
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

$('#dcc_area_entry_date').datepicker({
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
 function isValidateClientSide()
  {
    var errCount = 0;
 document.getElementById("opd_personal_id_lbl").innerHTML="";
 document.getElementById("dcc_exam_type_lbl").innerHTML="";
 document.getElementById("dcc_area_lbl").innerHTML="";
 document.getElementById("dcc_area_type_lbl").innerHTML="";
 document.getElementById("dcc_entry_type_lbl").innerHTML="";
 document.getElementById("dcc_partd_lbl").innerHTML="";
 document.getElementById("dcc_jc_year_lbl").innerHTML="";
 document.getElementById("dcc_chances_lbl").innerHTML="";
 document.getElementById("dcc_earlier_dispens_lbl").innerHTML="";
 document.getElementById("dcc_dispes_year_lbl").innerHTML="";
 document.getElementById("dcc_area_entry_date_lbl").innerHTML="";
 document.getElementById("dcc_area_leave_date_lbl").innerHTML="";
 document.getElementById("dcc_compens_year_lbl").innerHTML="";
 document.getElementById("dcc_leave_lbl").innerHTML="";
 document.getElementById("dcc_remarks_lbl").innerHTML="";
  if($("input#opd_personal_id").val().trim() == "")
   {
   errCount++;
 document.getElementById("opd_personal_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Personal No."; 
 $("#opd_personal_id").focus(); 
   } 
  if($("input[name='dcc_exam_type']:checked").val() == undefined  || $("input[name='dcc_exam_type']:checked").val() == "undefined")
   {
   errCount++;
 document.getElementById("dcc_exam_type_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Select Examination"; 
 $("#dcc_exam_type").focus(); 
   } 
  if($("input#dcc_area").val().trim() == "")
   {
   errCount++;
 document.getElementById("dcc_area_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Name of Area/Fmn"; 
 $("#dcc_area").focus(); 
   } 
  if($("select#dcc_area_type").val() == "")
   {
   errCount++;
 document.getElementById("dcc_area_type_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Type of Area"; 
 $("#dcc_area_type").focus(); 
   } 
  if($("select#dcc_entry_type").val() == "")
   {
   errCount++;
 document.getElementById("dcc_entry_type_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Type of Entry"; 
 $("#dcc_entry_type").focus(); 
   } 
  if($("input#dcc_partd").val().trim() == "")
   {
   errCount++;
 document.getElementById("dcc_partd_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Year Part D Passed"; 
 $("#dcc_partd").focus(); 
   } 
  if($("input#dcc_jc_year").val().trim() == "")
   {
   errCount++;
 document.getElementById("dcc_jc_year_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Year JC Course Done"; 
 $("#dcc_jc_year").focus(); 
   } 
  if($("input#dcc_chances").val().trim() == "")
   {
   errCount++;
 document.getElementById("dcc_chances_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Year(s) chances availed for DSSC/TSOC"; 
 $("#dcc_chances").focus(); 
   } 
  if($("input#dcc_earlier_dispens").val().trim() == "")
   {
   errCount++;
 document.getElementById("dcc_earlier_dispens_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Dispenasation granted earlier if any"; 
 $("#dcc_earlier_dispens").focus(); 
   } 
  if($("input#dcc_dispes_year").val().trim() == "")
   {
   errCount++;
 document.getElementById("dcc_dispes_year_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Dispensation asked for year"; 
 $("#dcc_dispes_year").focus(); 
   } 
  else if($("input#dcc_area_entry_date").val() == "DD/MM/YYYY")
   {
   errCount++;
 document.getElementById("dcc_area_entry_date_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Date of entry in the area"; 
 $("#dcc_area_entry_date").focus(); 
   } 
  else if($("input#dcc_area_leave_date").val() == "DD/MM/YYYY")
   {
   errCount++;
 document.getElementById("dcc_area_leave_date_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Date of leaving the area"; 
 $("#dcc_area_leave_date").focus(); 
   } 
  if($("input#dcc_compens_year").val().trim() == "")
   {
   errCount++;
 document.getElementById("dcc_compens_year_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Year Compensatory chance to be utilized"; 
 $("#dcc_compens_year").focus(); 
   } 
  if($("input#dcc_leave").val().trim() == "")
   {
   errCount++;
 document.getElementById("dcc_leave_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Leave Duration"; 
 $("#dcc_leave").focus(); 
   } 
  if($("input#dcc_remarks").val().trim() == "")
   {
   errCount++;
 document.getElementById("dcc_remarks_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Remarks"; 
 $("#dcc_remarks").focus(); 
   } 
 if (errCount > 0) { 
	return false;
 } else {
	return true;
 }
 return true;
  } 
 </script>

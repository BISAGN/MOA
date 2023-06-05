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
<form:form name="Formname" id="Formid" action="partbd_compens_chanceAction" method="POST" modelAttribute="partbd_compens_chanceCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>PARTBD COMPENS CHANCE</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Examination<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="es_id1" path="es_id"  value="0" />Part B  </label> &nbsp;&nbsp;
           <label for='' class="form-check-label"><form:radiobutton id="es_id2" path="es_id"  value="1" />Part D  </label> &nbsp;&nbsp;
 <span class="errorClass" id="es_id_lbl">${es_id_lbl}</span>
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
	            <label for="text-input" class="form-control-label">Qualifying Area/Operation<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="pcc_area" path="pcc_area"  class="form-control">
 		      <option value="">--Select--</option>
		      <option value="a">a</option>
		      <option value="b">b</option>
	 </form:select>
 <span class="errorClass" id="pcc_area_lbl">${pcc_area_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Date of posting into the area<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="pcc_area_entry_date" path="pcc_area_entry_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
 <span class="errorClass" id="pcc_area_entry_date_lbl">${pcc_area_entry_date_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Date of posting out<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="pcc_area_leave_date" path="pcc_area_leave_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
 <span class="errorClass" id="pcc_area_leave_date_lbl">${pcc_area_leave_date_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Absence Period (in Days)<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="pcc_absence" path="pcc_absence"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="pcc_absence_lbl">${pcc_absence_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Chance granted earlier, if any<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="pcc_chances" path="pcc_chances"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="pcc_chances_lbl">${pcc_chances_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Chance to be availed in year<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="pcc_avail_year" path="pcc_avail_year"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="pcc_avail_year_lbl">${pcc_avail_year_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Chance asked for (mention year)<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="pcc_ask_year" path="pcc_ask_year"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="pcc_ask_year_lbl">${pcc_ask_year_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Remarks<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="pcc_remarks" path="pcc_remarks"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="pcc_remarks_lbl">${pcc_remarks_lbl}</span>
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

$('#pcc_area_entry_date').datepicker({
showOn: 'both',
buttonImageOnly: true,
buttonImage: 'js/Calender/cal_ico.png',
dateFormat: 'dd/mm/yy',
changeMonth: true,
changeYear: true,
yearRange: '1890:2099'
});
$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});
$('#pcc_area_leave_date').datepicker({
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
 document.getElementById("es_id_lbl").innerHTML="";
 document.getElementById("pcc_area_lbl").innerHTML="";
 document.getElementById("pcc_chances_lbl").innerHTML="";
 document.getElementById("pcc_ask_year_lbl").innerHTML="";
 document.getElementById("pcc_area_entry_date_lbl").innerHTML="";
 document.getElementById("pcc_area_leave_date_lbl").innerHTML="";
 document.getElementById("pcc_avail_year_lbl").innerHTML="";
 document.getElementById("pcc_absence_lbl").innerHTML="";
 document.getElementById("pcc_remarks_lbl").innerHTML="";
  if($("input#opd_personal_id").val().trim() == "")
   {
   errCount++;
 document.getElementById("opd_personal_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Personal No."; 
 $("#opd_personal_id").focus(); 
   } 
  if($("input[name='es_id']:checked").val() == undefined  || $("input[name='es_id']:checked").val() == "undefined")
   {
   errCount++;
 document.getElementById("es_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Examination"; 
 $("#es_id").focus(); 
   } 
  if($("select#pcc_area").val() == "")
   {
   errCount++;
 document.getElementById("pcc_area_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Qualifying Area/Operation"; 
 $("#pcc_area").focus(); 
   } 
  if($("input#pcc_chances").val().trim() == "")
   {
   errCount++;
 document.getElementById("pcc_chances_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Chance granted earlier, if any"; 
 $("#pcc_chances").focus(); 
   } 
  if($("input#pcc_ask_year").val().trim() == "")
   {
   errCount++;
 document.getElementById("pcc_ask_year_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Chance asked for (mention year)"; 
 $("#pcc_ask_year").focus(); 
   } 
  else if($("input#pcc_area_entry_date").val() == "DD/MM/YYYY")
   {
   errCount++;
 document.getElementById("pcc_area_entry_date_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Date of posting into the area"; 
 $("#pcc_area_entry_date").focus(); 
   } 
  else if($("input#pcc_area_leave_date").val() == "DD/MM/YYYY")
   {
   errCount++;
 document.getElementById("pcc_area_leave_date_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Date of posting out"; 
 $("#pcc_area_leave_date").focus(); 
   } 
  if($("input#pcc_avail_year").val().trim() == "")
   {
   errCount++;
 document.getElementById("pcc_avail_year_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Chance to be availed in year"; 
 $("#pcc_avail_year").focus(); 
   } 
  if($("input#pcc_absence").val().trim() == "")
   {
   errCount++;
 document.getElementById("pcc_absence_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Absence Period (in Days)"; 
 $("#pcc_absence").focus(); 
   } 
  if($("input#pcc_remarks").val().trim() == "")
   {
   errCount++;
 document.getElementById("pcc_remarks_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Remarks"; 
 $("#pcc_remarks").focus(); 
   } 
 if (errCount > 0) { 
	return false;
 } else {
	return true;
 }
 return true;
  } 
 </script>

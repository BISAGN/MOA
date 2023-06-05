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
<form:form name="Formname" id="Formid" action="unfair_meansAction" method="POST" modelAttribute="unfair_meansCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>UNFAIR MEANS</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Personal Number<strong style="color: red;">*</strong></label>
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
	            <label for="text-input" class="form-control-label">Select Examination<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="es_id1" path="es_id"  value="0" />Part B  </label> &nbsp;&nbsp;
           <label for='' class="form-check-label"><form:radiobutton id="es_id2" path="es_id"  value="1" />Part D  </label> &nbsp;&nbsp;
           <label for='' class="form-check-label"><form:radiobutton id="es_id3" path="es_id"  value="2" />DSSC/TSOC  </label> &nbsp;&nbsp;
 <span class="errorClass" id="es_id_lbl">${es_id_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Subject<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="sc_subject_id" path="sc_subject_id"  class="form-control">
          <c:forEach var="item" items="${getscsubjectnameListDDL}" varStatus="num"  >
               <option value="${item.sc_subject_id}" name="${item.sc_subject_name}" >${item.sc_subject_name}</option>
         </c:forEach>
	 </form:select>
 <span class="errorClass" id="sc_subject_id_lbl">${sc_subject_id_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Remarks<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="um_remarks" path="um_remarks"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="um_remarks_lbl">${um_remarks_lbl}</span>
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

});
 function isValidateClientSide()
  {
    var errCount = 0;
 document.getElementById("opd_personal_id_lbl").innerHTML="";
 document.getElementById("es_id_lbl").innerHTML="";
 document.getElementById("sc_subject_id_lbl").innerHTML="";
 document.getElementById("um_remarks_lbl").innerHTML="";
  if($("input#opd_personal_id").val().trim() == "")
   {
   errCount++;
 document.getElementById("opd_personal_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Personal Number"; 
 $("#opd_personal_id").focus(); 
   } 
  if($("input[name='es_id']:checked").val() == undefined  || $("input[name='es_id']:checked").val() == "undefined")
   {
   errCount++;
 document.getElementById("es_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Select Examination"; 
 $("#es_id").focus(); 
   } 
  if($("select#sc_subject_id").val() == "")
   {
   errCount++;
 document.getElementById("sc_subject_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Subject"; 
 $("#sc_subject_id").focus(); 
   } 
  if($("input#um_remarks").val().trim() == "")
   {
   errCount++;
 document.getElementById("um_remarks_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Remarks"; 
 $("#um_remarks").focus(); 
   } 
 if (errCount > 0) { 
	return false;
 } else {
	return true;
 }
 return true;
  } 
 </script>

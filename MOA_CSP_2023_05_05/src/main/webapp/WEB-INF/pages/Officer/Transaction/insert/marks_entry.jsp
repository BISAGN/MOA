<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<script type="text/javascript" src="js/jquery/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="Formname" id="Formid" action="marks_entryAction" method="POST" modelAttribute="marks_entryCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>MARKS ENTRY</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
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
	            <label for="text-input" class="form-control-label">First Entry<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="ab_first_entry1" path="ab_first_entry"  value="0" />First  </label> &nbsp;&nbsp;
 <span class="errorClass" id="ab_first_entry_lbl">${ab_first_entry_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Second Entry<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="ab_second_entry1" path="ab_second_entry"  value="0" />Second  </label> &nbsp;&nbsp;
 <span class="errorClass" id="ab_second_entry_lbl">${ab_second_entry_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Index No.<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="oa_application_id" path="oa_application_id"  class="form-control" autocomplete='off' maxlength="" ></form:input>
 <span class="errorClass" id="oa_application_id_lbl">${oa_application_id_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Marks</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="ab_marks_obtained" path="ab_marks_obtained"  class="form-control" autocomplete='off' maxlength="" ></form:input>
 <span class="errorClass" id="ab_marks_obtained_lbl">${ab_marks_obtained_lbl}</span>
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
 document.getElementById("oa_application_id_lbl").innerHTML="";
 document.getElementById("sc_subject_id_lbl").innerHTML="";
 document.getElementById("ab_first_entry_lbl").innerHTML="";
 document.getElementById("ab_second_entry_lbl").innerHTML="";
  if($("input#oa_application_id").val().trim() == "")
   {
   errCount++;
 document.getElementById("oa_application_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Index No."; 
 $("#oa_application_id").focus(); 
   } 
  if($("select#sc_subject_id").val() == "")
   {
   errCount++;
 document.getElementById("sc_subject_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Subject"; 
 $("#sc_subject_id").focus(); 
   } 
  if($("input[name='ab_first_entry']:checked").val() == undefined  || $("input[name='ab_first_entry']:checked").val() == "undefined")
   {
   errCount++;
 document.getElementById("ab_first_entry_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter First Entry"; 
 $("#ab_first_entry").focus(); 
   } 
  if($("input[name='ab_second_entry']:checked").val() == undefined  || $("input[name='ab_second_entry']:checked").val() == "undefined")
   {
   errCount++;
 document.getElementById("ab_second_entry_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Second Entry"; 
 $("#ab_second_entry").focus(); 
   } 
 if (errCount > 0) { 
	return false;
 } else {
	return true;
 }
 return true;
  } 
 </script>

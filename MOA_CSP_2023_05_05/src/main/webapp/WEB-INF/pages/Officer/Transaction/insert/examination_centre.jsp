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
<form:form name="Formname" id="Formid" action="examination_centreAction" method="POST" modelAttribute="examination_centreCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>EXAMINATION CENTRE</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Command Name<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="cc_command_id" path="cc_command_id"  class="form-control">
          <c:forEach var="item" items="${getcccommandnameListDDL}" varStatus="num"  >
               <option value="${item.id}" name="${item.cc_command_name}" >${item.cc_command_name}</option>
         </c:forEach>
	 </form:select>
 <span class="errorClass" id="cc_command_id_lbl">${cc_command_id_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Exam Name<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="ec_exam_id" path="ec_exam_id"  class="form-control">
          <c:forEach var="item" items="${getecexamnameListDDL}" varStatus="num"  >
               <option value="${item.id}" name="${item.ec_exam_name}" >${item.ec_exam_name}</option>
         </c:forEach>
	 </form:select>
 <span class="errorClass" id="ec_exam_id_lbl">${ec_exam_id_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Exam Name<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="ecc_name" path="ecc_name"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="ecc_name_lbl">${ecc_name_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Conduction Formation<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="ecc_cond_formation" path="ecc_cond_formation"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="ecc_cond_formation_lbl">${ecc_cond_formation_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Centre Name<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="center_code" path="center_code"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="center_code_lbl">${center_code_lbl}</span>
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
 document.getElementById("cc_command_id_lbl").innerHTML="";
 document.getElementById("ec_exam_id_lbl").innerHTML="";
 document.getElementById("ecc_name_lbl").innerHTML="";
 document.getElementById("ecc_cond_formation_lbl").innerHTML="";
 document.getElementById("center_code_lbl").innerHTML="";
  if($("select#cc_command_id").val() == "")
   {
   errCount++;
 document.getElementById("cc_command_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Command Name"; 
 $("#cc_command_id").focus(); 
   } 
  if($("select#ec_exam_id").val() == "")
   {
   errCount++;
 document.getElementById("ec_exam_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Exam Name"; 
 $("#ec_exam_id").focus(); 
   } 
  if($("input#ecc_name").val().trim() == "")
   {
   errCount++;
 document.getElementById("ecc_name_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Exam Name"; 
 $("#ecc_name").focus(); 
   } 
  if($("input#ecc_cond_formation").val().trim() == "")
   {
   errCount++;
 document.getElementById("ecc_cond_formation_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Conduction Formation"; 
 $("#ecc_cond_formation").focus(); 
   } 
  if($("input#center_code").val().trim() == "")
   {
   errCount++;
 document.getElementById("center_code_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Centre Name"; 
 $("#center_code").focus(); 
   } 
 if (errCount > 0) { 
	return false;
 } else {
	return true;
 }
 return true;
  } 
 </script>

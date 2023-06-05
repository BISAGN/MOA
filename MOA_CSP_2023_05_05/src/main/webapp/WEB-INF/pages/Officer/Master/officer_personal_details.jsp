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
<form:form name="Formname" id="Formid" action="officer_personal_detailsAction" method="POST" modelAttribute="officer_personal_detailsCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>OFFICER PERSONAL DETAILS</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Officer Name</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="opd_officer_name" path="opd_officer_name"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="opd_officer_name_lbl">${opd_officer_name_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Unit Name</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="opd_unit" path="opd_unit"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="opd_unit_lbl">${opd_unit_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Command</label>
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
	            <label for="text-input" class="form-control-label">Commission Type<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="ct_comm_id" path="ct_comm_id"  class="form-control">
          <c:forEach var="item" items="${getctcommtypeListDDL}" varStatus="num"  >
               <option value="${item.id}" name="${item.ct_comm_type}" >${item.ct_comm_type}</option>
         </c:forEach>
	 </form:select>
 <span class="errorClass" id="ct_comm_id_lbl">${ct_comm_id_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Permanent Address</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="opd_permanent_address1" path="opd_permanent_address1"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="opd_permanent_address1_lbl">${opd_permanent_address1_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Permanent Address</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="opd_permanent_address2" path="opd_permanent_address2"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="opd_permanent_address2_lbl">${opd_permanent_address2_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Permanent Address</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="opd_permanent_address3" path="opd_permanent_address3"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="opd_permanent_address3_lbl">${opd_permanent_address3_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Unit Address</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="opd_unit_address1" path="opd_unit_address1"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="opd_unit_address1_lbl">${opd_unit_address1_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Unit Address</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="opd_unit_address2" path="opd_unit_address2"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="opd_unit_address2_lbl">${opd_unit_address2_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Unit Address</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="opd_unit_address3" path="opd_unit_address3"  class="form-control" autocomplete='off'  ></form:input>
 <span class="errorClass" id="opd_unit_address3_lbl">${opd_unit_address3_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Date of Birth</label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="opd_dob" path="opd_dob" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
 <span class="errorClass" id="opd_dob_lbl">${opd_dob_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Date of Commission</label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="opd_date_of_comm" path="opd_date_of_comm" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
 <span class="errorClass" id="opd_date_of_comm_lbl">${opd_date_of_comm_lbl}</span>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Date of Seniority</label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="opd_date_of_seniority" path="opd_date_of_seniority" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
 <span class="errorClass" id="opd_date_of_seniority_lbl">${opd_date_of_seniority_lbl}</span>
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
 document.getElementById("ct_comm_id_lbl").innerHTML="";
  if($("select#ct_comm_id").val() == "")
   {
   errCount++;
 document.getElementById("ct_comm_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Commission Type"; 
 $("#ct_comm_id").focus(); 
   } 
 if (errCount > 0) { 
	return false;
 } else {
	return true;
 }
 return true;
  } 
 </script>

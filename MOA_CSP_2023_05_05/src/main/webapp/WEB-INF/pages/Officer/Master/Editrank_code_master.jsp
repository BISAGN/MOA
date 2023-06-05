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
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></scri -->pt>
<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="EditFormname" id="EditFormid" action="Editrank_code_masterAction" method="POST" modelAttribute="Editrank_code_masterCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>EDIT RANK CODE MASTER</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
<form:input type="hidden" id="id" path="id" value="${Editrank_code_masterCMD1.id}" ></form:input>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Rank<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="rc_rank_name" path="rc_rank_name" value="${Editrank_code_masterCMD1.rc_rank_name}" class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
    </div>
       <div class='card-footer' align='center'>
           <input type='reset' class='btn btn-success btn-sm' value='Clear' onclick='clearall()'>
           <input type='submit' class='btn btn-primary btn-sm' value='Update'  onclick='return isValidateClientSide()'>
       </div>
    </div>
  </div>
</form:form>



 <script>


$(document).ready(function () {

});



function ParseDateColumncommission(data) {

	  var date="";
	  if(data!=null && data!=""){
		 var d = new Date(data);
		 var day=('0' + d.getDate()).slice(-2);
		 var month=('0' + (d.getMonth() + 1)).slice(-2);
		 var year=""+d.getFullYear();
		 date=day+"/"+month+"/"+year;
		   		}
		    return date;
	}
 function isValidateClientSide()
  {
    var errCount = 0;
 document.getElementById("rc_rank_name_lbl").innerHTML="";
  if($("input#rc_rank_name").val().trim() == "")
   {
   errCount++;
 document.getElementById("rc_rank_name_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Rank"; 
 $("#rc_rank_name").focus(); 
   } 
 if (errCount > 0) { 
	return false;
 } else {
	return true;
 }
 return true;
  } 
 </script>

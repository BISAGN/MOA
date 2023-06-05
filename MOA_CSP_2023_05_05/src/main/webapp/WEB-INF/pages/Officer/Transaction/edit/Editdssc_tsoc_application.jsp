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
<form:form name="EditFormname" id="EditFormid" action="Editdssc_tsoc_applicationAction" method="POST" modelAttribute="Editdssc_tsoc_applicationCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>EDIT DSSC_TSOC_APPLICATION</h5></div>
       <div class="card-body card-block cue_text">
<div class="errMsgClient"></div><div class="errMsgServer"></div>
<div class="col-md-12" id="divLine" style="display: none;"><span class="line"></span></div>
<form:input type="hidden" id="id" path="id" value="${Editdssc_tsoc_applicationCMD1.id}" ></form:input>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Personal No.<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="oa_application_id" path="oa_application_id" value="${Editdssc_tsoc_applicationCMD1.oa_application_id}" class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">DSSC<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="dta_dssc1" path="dta_dssc"  value="0" />null  </label> &nbsp;&nbsp;
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">TSOC<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="dta_tsoc1" path="dta_tsoc"  value="1" />null  </label> &nbsp;&nbsp;
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Both<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:radiobutton id="dta_course_pref1" path="dta_course_pref"  value="2" />null  </label> &nbsp;&nbsp;
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Type of Entry<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
		     <form:select id="dta_type_of_entry" path="dta_type_of_entry"  class="form-control">
 		      <option value="">--Select--</option>
		      <option value="a">a</option>
		      <option value="b">b</option>
	 </form:select>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	        <label for="text-input" class=" form-control-label">Regiment</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dta_regiment" path="dta_regiment" value="${Editdssc_tsoc_applicationCMD1.dta_regiment}" class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Address of Correspondence<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dta_address_corres1" path="dta_address_corres1" value="${Editdssc_tsoc_applicationCMD1.dta_address_corres1}" class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Address of Correspondence<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dta_address_coress2" path="dta_address_coress2" value="${Editdssc_tsoc_applicationCMD1.dta_address_coress2}" class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Medical Category (SHAPE)<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dta_medical_cat" path="dta_medical_cat" value="${Editdssc_tsoc_applicationCMD1.dta_medical_cat}" class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Ser. No.<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dta_jccourse_no" path="dta_jccourse_no" value="${Editdssc_tsoc_applicationCMD1.dta_jccourse_no}" class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Begin Date<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_jccourse_beg_date" path="dta_jccourse_beg_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">End Date<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_jccourse_end_date" path="dta_jccourse_end_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Course No.<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dta_182daycourse_no" path="dta_182daycourse_no" value="${Editdssc_tsoc_applicationCMD1.dta_182daycourse_no}" class="form-control" autocomplete='off'  ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Date of Commencement<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_182daycourse_init_date" path="dta_182daycourse_init_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Date of Termination<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	         <form:input type="text" id="dta_182daycourse_term_date" path="dta_182daycourse_term_date" maxlength="10" value="DD/MM/YYYY"  class='form-control' style="width: 85%;display: inline;" autocomplete='off' onclick="clickclear(this, 'DD/MM/YYYY')" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Waiver Granted<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
           <label for='' class="form-check-label"><form:checkbox id="dta_waiver1" path="dta_waiver"  value="0" />null</label> &nbsp;&nbsp;
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">First Year<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dta_firstchance_year" path="dta_firstchance_year" value="${Editdssc_tsoc_applicationCMD1.dta_firstchance_year}" class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">First Centre<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dta_firstchance_center" path="dta_firstchance_center" value="${Editdssc_tsoc_applicationCMD1.dta_firstchance_center}" class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Second Year<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dta_secondchance_year" path="dta_secondchance_year" value="${Editdssc_tsoc_applicationCMD1.dta_secondchance_year}" class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Second Centre<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dta_secondchance_center" path="dta_secondchance_center" value="${Editdssc_tsoc_applicationCMD1.dta_secondchance_center}" class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Third Year<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dta_thirdchance_year" path="dta_thirdchance_year" value="${Editdssc_tsoc_applicationCMD1.dta_thirdchance_year}" class="form-control" autocomplete='off' maxlength="" ></form:input>
        </div>
    </div>
</div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	            <label for="text-input" class="form-control-label">Third Centre<strong style="color: red;">*</strong></label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="dta_thirdchance_center" path="dta_thirdchance_center" value="${Editdssc_tsoc_applicationCMD1.dta_thirdchance_center}" class="form-control" autocomplete='off' maxlength="" ></form:input>
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

	if('${Editdssc_tsoc_applicationCMD1.dta_dssc}' == 'null')
	$("#dta_dssc1").prop("checked", true);

	if('${Editdssc_tsoc_applicationCMD1.dta_tsoc}' == 'null')
	$("#dta_tsoc1").prop("checked", true);

	if('${Editdssc_tsoc_applicationCMD1.dta_course_pref}' == 'null')
	$("#dta_course_pref1").prop("checked", true);

	$("select#dta_type_of_entry").val('${Editdssc_tsoc_applicationCMD1.dta_type_of_entry}');
	if('${Editdssc_tsoc_applicationCMD1.dta_jccourse_beg_date}' ==null){
	$("#dta_jccourse_beg_date").val("");
}
else{
	 $("#dta_jccourse_beg_date").val(ParseDateColumncommission('${Editdssc_tsoc_applicationCMD1.dta_jccourse_beg_date}'));



}
 $('#dta_jccourse_beg_date').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
changeMonth: true,
changeYear: true,
yearRange: '1890:2099'
});
$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});


	if('${Editdssc_tsoc_applicationCMD1.dta_jccourse_end_date}' ==null){
	$("#dta_jccourse_end_date").val("");
}
else{
	 $("#dta_jccourse_end_date").val(ParseDateColumncommission('${Editdssc_tsoc_applicationCMD1.dta_jccourse_end_date}'));



}
 $('#dta_jccourse_end_date').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
changeMonth: true,
changeYear: true,
yearRange: '1890:2099'
});
$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});


	if('${Editdssc_tsoc_applicationCMD1.dta_182daycourse_init_date}' ==null){
	$("#dta_182daycourse_init_date").val("");
}
else{
	 $("#dta_182daycourse_init_date").val(ParseDateColumncommission('${Editdssc_tsoc_applicationCMD1.dta_182daycourse_init_date}'));



}
 $('#dta_182daycourse_init_date').datepicker({
	showOn: 'both',
	buttonImageOnly: true,
	buttonImage: 'js/Calender/cal_ico.png',
	dateFormat: 'dd/mm/yy',
changeMonth: true,
changeYear: true,
yearRange: '1890:2099'
});
$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'});


	if('${Editdssc_tsoc_applicationCMD1.dta_182daycourse_term_date}' ==null){
	$("#dta_182daycourse_term_date").val("");
}
else{
	 $("#dta_182daycourse_term_date").val(ParseDateColumncommission('${Editdssc_tsoc_applicationCMD1.dta_182daycourse_term_date}'));



}
 $('#dta_182daycourse_term_date').datepicker({
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
 document.getElementById("oa_application_id_lbl").innerHTML="";
 document.getElementById("dta_dssc_lbl").innerHTML="";
 document.getElementById("dta_tsoc_lbl").innerHTML="";
 document.getElementById("dta_course_pref_lbl").innerHTML="";
 document.getElementById("dta_type_of_entry_lbl").innerHTML="";
 document.getElementById("dta_medical_cat_lbl").innerHTML="";
 document.getElementById("dta_address_corres1_lbl").innerHTML="";
 document.getElementById("dta_address_coress2_lbl").innerHTML="";
 document.getElementById("dta_jccourse_no_lbl").innerHTML="";
 document.getElementById("dta_jccourse_beg_date_lbl").innerHTML="";
 document.getElementById("dta_jccourse_end_date_lbl").innerHTML="";
 document.getElementById("dta_182daycourse_no_lbl").innerHTML="";
 document.getElementById("dta_182daycourse_init_date_lbl").innerHTML="";
 document.getElementById("dta_182daycourse_term_date_lbl").innerHTML="";
 document.getElementById("dta_waiver_lbl").innerHTML="";
 document.getElementById("dta_firstchance_year_lbl").innerHTML="";
 document.getElementById("dta_firstchance_center_lbl").innerHTML="";
 document.getElementById("dta_secondchance_year_lbl").innerHTML="";
 document.getElementById("dta_secondchance_center_lbl").innerHTML="";
 document.getElementById("dta_thirdchance_year_lbl").innerHTML="";
 document.getElementById("dta_thirdchance_center_lbl").innerHTML="";
  if($("input#oa_application_id").val().trim() == "")
   {
   errCount++;
 document.getElementById("oa_application_id_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Personal No."; 
 $("#oa_application_id").focus(); 
   } 
  if($("input[name='dta_dssc']:checked").val() == undefined  || $("input[name='dta_dssc']:checked").val() == "undefined")
   {
   errCount++;
 document.getElementById("dta_dssc_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter DSSC"; 
 $("#dta_dssc").focus(); 
   } 
  if($("input[name='dta_tsoc']:checked").val() == undefined  || $("input[name='dta_tsoc']:checked").val() == "undefined")
   {
   errCount++;
 document.getElementById("dta_tsoc_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter TSOC"; 
 $("#dta_tsoc").focus(); 
   } 
  if($("input[name='dta_course_pref']:checked").val() == undefined  || $("input[name='dta_course_pref']:checked").val() == "undefined")
   {
   errCount++;
 document.getElementById("dta_course_pref_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Both"; 
 $("#dta_course_pref").focus(); 
   } 
  if($("select#dta_type_of_entry").val() == "")
   {
   errCount++;
 document.getElementById("dta_type_of_entry_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Type of Entry"; 
 $("#dta_type_of_entry").focus(); 
   } 
  if($("input#dta_medical_cat").val().trim() == "")
   {
   errCount++;
 document.getElementById("dta_medical_cat_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Medical Category (SHAPE)"; 
 $("#dta_medical_cat").focus(); 
   } 
  if($("input#dta_address_corres1").val().trim() == "")
   {
   errCount++;
 document.getElementById("dta_address_corres1_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Address of Correspondence"; 
 $("#dta_address_corres1").focus(); 
   } 
  if($("input#dta_address_coress2").val().trim() == "")
   {
   errCount++;
 document.getElementById("dta_address_coress2_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Address of Correspondence"; 
 $("#dta_address_coress2").focus(); 
   } 
  if($("input#dta_jccourse_no").val().trim() == "")
   {
   errCount++;
 document.getElementById("dta_jccourse_no_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Ser. No."; 
 $("#dta_jccourse_no").focus(); 
   } 
  else if($("input#dta_jccourse_beg_date").val() == "DD/MM/YYYY")
   {
   errCount++;
 document.getElementById("dta_jccourse_beg_date_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Begin Date"; 
 $("#dta_jccourse_beg_date").focus(); 
   } 
  else if($("input#dta_jccourse_end_date").val() == "DD/MM/YYYY")
   {
   errCount++;
 document.getElementById("dta_jccourse_end_date_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter End Date"; 
 $("#dta_jccourse_end_date").focus(); 
   } 
  if($("input#dta_182daycourse_no").val().trim() == "")
   {
   errCount++;
 document.getElementById("dta_182daycourse_no_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Course No."; 
 $("#dta_182daycourse_no").focus(); 
   } 
  else if($("input#dta_182daycourse_init_date").val() == "DD/MM/YYYY")
   {
   errCount++;
 document.getElementById("dta_182daycourse_init_date_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Date of Commencement"; 
 $("#dta_182daycourse_init_date").focus(); 
   } 
  else if($("input#dta_182daycourse_term_date").val() == "DD/MM/YYYY")
   {
   errCount++;
 document.getElementById("dta_182daycourse_term_date_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Date of Termination"; 
 $("#dta_182daycourse_term_date").focus(); 
   } 
  if($("input[name='dta_waiver']:checked").val() == undefined  || $("input[name='dta_waiver']:checked").val() == "undefined")
   {
   errCount++;
 document.getElementById("dta_waiver_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Waiver Granted"; 
 $("#dta_waiver").focus(); 
   } 
  if($("input#dta_firstchance_year").val().trim() == "")
   {
   errCount++;
 document.getElementById("dta_firstchance_year_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter First Year"; 
 $("#dta_firstchance_year").focus(); 
   } 
  if($("input#dta_firstchance_center").val().trim() == "")
   {
   errCount++;
 document.getElementById("dta_firstchance_center_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter First Centre"; 
 $("#dta_firstchance_center").focus(); 
   } 
  if($("input#dta_secondchance_year").val().trim() == "")
   {
   errCount++;
 document.getElementById("dta_secondchance_year_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Second Year"; 
 $("#dta_secondchance_year").focus(); 
   } 
  if($("input#dta_secondchance_center").val().trim() == "")
   {
   errCount++;
 document.getElementById("dta_secondchance_center_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Second Centre"; 
 $("#dta_secondchance_center").focus(); 
   } 
  if($("input#dta_thirdchance_year").val().trim() == "")
   {
   errCount++;
 document.getElementById("dta_thirdchance_year_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Third Year"; 
 $("#dta_thirdchance_year").focus(); 
   } 
  if($("input#dta_thirdchance_center").val().trim() == "")
   {
   errCount++;
 document.getElementById("dta_thirdchance_center_lbl").innerHTML = "<i class='fa fa-exclamation'></i>Please Enter Third Centre"; 
 $("#dta_thirdchance_center").focus(); 
   } 
 if (errCount > 0) { 
	return false;
 } else {
	return true;
 }
 return true;
  } 
 </script>

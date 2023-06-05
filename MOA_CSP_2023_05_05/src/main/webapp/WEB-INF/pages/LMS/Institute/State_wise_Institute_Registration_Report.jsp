<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/NewSearch/newsearch.css" rel="stylesheet" />
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<style>

.s010 form {
    width: 100%;
    max-width: 100%;
    margin: 0;
}.s010 {
    /* min-height: 53vh !important; */
    display: -ms-flexbox;
    display: flex;
    -ms-flex-pack: center;
    justify-content: center;
    -ms-flex-align: center;
    align-items: center;
    background: transparent;
    padding: 15px;
    font-family: 'Lato', sans-serif;
}
.s010 form .inner-form .input-field input {
    background: linear-gradient(to right, #175658 0%, #175658 28%, #2e9970 91%, #2e9970 100%);
}
.s010 form .inner-form .basic-search:hover{
   transform: scale(1.01);
}
.inner-form label {
    display: inline-block;
    color: #175658;
    font-weight: bold;
}
.inner-form .form-control {
    color: #175658;
    border: 2px solid #175658;
}
.advance-search input::placeholder {
    color: #175658 !important;
}
.input-field input::placeholder {
  color: #fff !important;
  font-weight: bold !important;
}
.s010 form .inner-form .input-field .btn-search {
/*  background: linear-gradient(to right, #175658 0%, #175658 28%, #2e9970 91%, #2e9970 100%); */
}
.btn-select {
    background-color: #175658;
}
.btn-select:before {
    position: absolute;
    content: "";
    border-top: 5px solid #1f6e61;
    border-left: 5px solid #257d66;
    border-right: 5px solid #2e9970;
    border-bottom: 5px solid #2d976f;
    top: 0px;
    right: 0px;
    bottom: 0px;
    left: 0px;
    transition: 0.5s;
}
.btn-select:hover {
    box-shadow: 5px 5px #175658, -5px -5px #2e9970;
    cursor: pointer;
}
 .btn-select {
    min-width: max-content;
    position: relative;
    display: inline-block;
    text-align: center;
    text-transform: uppercase;
    padding: 8px 15px;
    margin: 0px 2%;
    font-size: 120%;
    font-weight: 600;
    color: #ecf0f1;
    transition: 0.5s;
    border: 0;
}

.btn-select:hover::before {
	border: 0px;
}
.section-heading {
    background: #2e9970;
    width: fit-content;
    margin: auto;
    padding: 5px 20px;
    color: white;
    box-shadow: 0px 0px 10px #175658;
}
.section-heading h2 {
    font-weight: bold;
}
.form-control:focus {
    box-shadow: 0 0 0 0.25rem rgb(46 153 112 / 47%);
}


</style>

<form:form name="" id="" action="Search_state_wise_inst_Reg_Report_Action" method="post" class="form-horizontal" commandName="Search_state_wise_inst_Reg_Report_CMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5> STATE WISE INSTITUTE REGISTRATION REPORT</h5></div>
	          			<div class="card-body card-block">
	          				            	
	              			<div class="col-md-12">	 
	              			    <div class="col-md-6">
	              					<div class="row form-group">
									   <div class="col-md-4">
											<label for="username">SYSTEM<span class="star_design"></span> <span style="color:#ff0000">*</span></label>
										</div>
										<div class="col-md-8">
										<select name="system_id" id="system_id" class="form-control">
								        <option value="0">---SELECT---</option>
											<c:forEach var="item" items="${getSystemList}" varStatus="num">
										<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
									</c:forEach>
								</select>
									</div>
									</div>
	              				</div> 
	              				<div class="col-md-6">
		              			  <div class="row form-group">
									<div class="col-md-4">
										<label for="username">INSTITUTE<span class="star_design"></span> <span style="color:#ff0000">*</span></label>
									</div>
									
									
									<div class="col-md-8">
											<select name="institute_id" id="institute_id" class="form-control">
								    <option value="0">---SELECT---</option>
											<c:forEach var="item" items="${getInstituteList}" varStatus="num">
										<option value="${item.id}" name="${item.institute_name}">${item.institute_name}</option>
									</c:forEach>
								            </select>
										</div>
								</div>
	              				</div>   
	              			</div>	
	              			<br><br>
	              			<div class="col-md-12">	 
	              			    <div class="col-md-6">
	              					<div class="row form-group">
										<div class="col-md-4">
											<label for="username">COURSE<span class="star_design"></span> <span style="color:#ff0000">*</span></label>
										</div>
										<div class="col-md-8">
											<select name="course_id" id="course_id" class="form-control">
								    <option value="0">---SELECT---</option>
											<c:forEach var="item" items="${getcoursenameList}" varStatus="num">
<%-- 										<option value="${item.id}" name="${item.course_name}">${item.course_name}</option> --%>
										<option value="${item[0]}" name="${item[0]}">${item[1]}</option> 
									</c:forEach>
								</select>
										</div>
									</div>
	              				</div> 

	              	</div>	      
            	</div>									
					<div class="card-footer" align="center" class="form-control">
					<a href="state_wise_inst_Reg_Report_url" class="btn-clear">Reset</a>	
					    <i class="action_icons searchButton"></i><input type="button"
					     class="btn-search" id="btn-reload" value="Search">
		            </div> 		
	        	  </div>
			</div>
	</div>
	
<div class="container" >
	<table id="getSearch_state_wise_inst_reg" class="display table no-margin table-striped  table-hover  table-bordered">
   		<thead>
       		<tr>
       		    <th align="center">SER NO</th>
       		    <th align="center">SYSTEM NAME</th>
            	<th align="center">INSTITUTE NAME</th>
            	<th align="center">COURSE NAME</th>
            </tr>
    	</thead>
	</table>
</div>
</form:form>

<c:url value="Search_institute_Approve_url" var="Search_institute_Approve_url" />
			<form:form action="${Search_institute_Approve_url}" method="post" id="AcceptRegistration"
			name="AcceptRegistration" modelAttribute="Acceptid">
			<input type="hidden" name="Acceptid" id="Acceptid" value="0" />
</form:form>

<c:url value="Search_institute_Reject_url" var="Search_institute_Reject_url" />
			<form:form action="${Search_institute_Reject_url}" method="post" id="RejectRegistration"
			name="RejectRegistration" modelAttribute="Rejecttid">
			<input type="hidden" name="Rejectid" id="Rejectid" value="0" />
</form:form>

<!-- ///// POPUP////////////////////////////////////// -->

<c:url value="st_inst_reg_report_popupurl" var="st_inst_reg_report_popupurl" />
<form:form action="${st_inst_reg_report_popupurl}" method="post" id="st_inst_reg_report_popup_Form" name="st_inst_reg_report_popup_Form"  target="result">
<input type="hidden" name="id2" id="id2" value="0"/>
 <input type="hidden" name="institute_id2" id="institute_id2" value="0"/> 
</form:form> 

<script>

$(document).ready(function() {

	mockjax1('getSearch_state_wise_inst_reg');
	table = dataTable('getSearch_state_wise_inst_reg');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
});

function data(getSearch_state_wise_inst_reg) {
	jsondata = [];
	var table = $('#' + getSearch_state_wise_inst_reg).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	
	var system_id = $("#system_id").val();
	var institute_id = $("#institute_id").val();
	var course_id = $("#course_id").val();

	$.post("getFilter_state_wise_institute_reg_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		
		system_id:system_id,
		institute_id:institute_id,
		course_id:course_id
	}, function(j) {

		for (var i = 0; i < j.length; i++) {
			jsondata.push([ i + 1,j[i].system_name,j[i].institute_name,j[i].course_name,j[i].action  ]);
			
		}
	});
	$.post("getTotal_state_wise_institute_reg_dataCount?" + key + "=" + value, {
		system_id:system_id,
		institute_id:institute_id,
		course_id:course_id
	}, function(j) {
		FilteredRecords = j;
		});
}

function Accepturl(id) {
	
	$("#Acceptid").val(id);
	document.getElementById('AcceptRegistration').submit();
		}

function Rejecturl(id) {

	$("#Rejectid").val(id);
	document.getElementById('RejectRegistration').submit();
	}
	
//////POPUP HISTORY/////////////////////////////////

function Pop_Up_History(id,institute_id,course_name) {

var x = screen.width/2 - 1100/2;
var y = screen.height/2 - 900/2;
popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
window.onfocus = function () { 
}

	$("#id2").val(id);
	$("#institute_id2").val(institute_id);
	document.getElementById('st_inst_reg_report_popup_Form').submit();

}

</script>

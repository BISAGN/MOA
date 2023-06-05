<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link href="js/NewSearch/newsearch.css" rel="stylesheet" />

<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css">


<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
	
	<style>
/* 	.thead2 { */
/* 		display: revert; */
/* 	   width: calc( 100% - 12px)!IMPORTANT; */
/* 	   font-size: 12px; */
/* 	   background-color: #175658; */
/* 	   color: white; */
/* 	} */
	</style>
	
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
.container {
	min-width: 90%;
}

.dataTables_scrollHeadInner {
    width: calc( 100% - 12px ) !important;
}
table.table{
    /* min-width: 100% !important; */
    width: 100% !important;
}


td.sorting_1 {
    text-align: center;
}
td:nth-child(3) {
  width: 100%;
  text-align: -webkit-center;
  
}
#container-table {
	box-shadow: none !important;
	margin-bottom: 25px;
	padding: none !important;
}
table.table{
    /* min-width: 100% !important; */
    width: 100% !important;
}
table td, table th {
         width: 525px !important;
}
</style>

<div id="full_div">
<form:form action="system_degree_dashboard_Action" method="POST" class="form-horizontal" commandName="sysdegdashCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header title">
				<h5>
					<span id="lbladd"></span> SELECT SYSTEM & DEGREE
				</h5>
			</div>
		</div>
			
			<div class="col-md-12" >
				<table id="addunitserved" class="display table no-margin table-striped  table-hover  table-bordered dataTable no-footer" > 
					<thead>
						<tr>
							<th style="">Ser No</th>
							<th style="">SYSTEM</th>
							<th style="">DEGREE</th>
							<th style="">Action</th>
						</tr>
					</thead>
				<tbody>
					<tr>
						<td align="center">1</td>
						<td align="center">
							<select name="system1" id="system1" class="form-control">
								<option value="0">--Select--</option>
								<c:forEach var="item" items="${getSystemList}" varStatus="num">
									<option value="${item.id}" name="${item.id}">${item.system_name}</option>
								</c:forEach>
							</select> 
						</td>
						<td align="center" >
							<select name="degree1" id="degree1" class="form-control">
								<option value="0">--Select--</option>
								<c:forEach var="item" items="${getDegreeList}" varStatus="num">
									<option value="${item.id}" name="${item.id}">${item.degree_name}</option>
								</c:forEach>
							</select>
				        </td>
						<td align="center"><a class="btn btn-success btn-sm" value="ADD" title="ADD" id="id_add_att1" onclick="formopen_att(1);">
										   <i class="fa fa-plus"></i></a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

			<div class="card-footer" align="center">
				<a href="selectSystemDegreeUrl" class="btn btn-success btn-sm">Reset</a>
				<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validation();"> 
				<input type="button" class="btn btn-primary btn-sm" id="final_submit" value="Final Submit" onclick="return Validation();">
			</div>
			
			<div class="card-body card-block" style="text-align: left;" id="qualification_tbl_div">
		<table id="system_degree_tb" class="display table no-margin table-striped  table-hover  table-bordered">
			<thead>
				<tr>
					<th align="center">Ser No</th>
					<th id="2">System</th>
					<th id="3">Degree</th>
					<th class="action">Action</th>
<!-- 					<th id="4">INSTITUTE NAME</th> -->
<!-- 					<th id="5">OBTAIN MARKS</th> -->
<!-- 					<th id="6">TOTAL MARKS</th> -->
<!-- 					<th id="7">GRADE</th> -->
<!-- 					<th  id="8">Download<br></th> -->
				</tr>
			</thead>
		</table>
	</div>
			
			
						<input type="hidden" id="count_hidden" name="count_hidden" class="form-control autocomplete" value="1">
						<input type="hidden" id="count_hidden_att" name="count_hidden_att" class="form-control autocomplete" value="1">
						<input type="hidden" id="forupdate_hidden" name="forupdate_hidden" class="form-control autocomplete" value="0">
						<input type="hidden" id="old_count_hidden" name="old_count_hidden" class="form-control autocomplete" value="0">
						<input type="hidden" id="new_count_hidden" name="new_count_hidden" class="form-control autocomplete" value="0">
						<input type="hidden" id="del_id_hidden" name="del_id_hidden" class="form-control autocomplete" value="0">
						<input type="hidden" id="p_id" name="p_id" class="form-control autocomplete" value="0">

		</div>
</form:form>
</div>	

<c:url value="editSysDegreeUrl" var="appUrl" />
<form:form action="${appUrl}" method="POST" id="editForm" name="editForm" modelAttribute="id2">
<input type="hidden" name="id2" id="id2" value="0"/>	
<input type="hidden" name="system2" id="system2" value="0"/>
<input type="hidden" name="degree2" id="degree2" value="0"/>
</form:form>

<c:url value="deleteSysDegreeUrl" var="appUrl" />
<form:form action="${appUrl}" method="POST" id="deleteForm" name="deleteForm" modelAttribute="id1">
<input type="hidden" name="id1" id="id1" value="0"/>	
</form:form>


<script type="text/javascript">

$(document).ready(function() {
	
	$("input#p_id").val('${p_id}');
	
	if('${msg}'!= ""){
		alert('${msg}');
	}
	
	if('${userid}' == "1"){
		$("#full_div").hide();
	}
	
	$("#final_submit").hide();
	
	mockjax1('system_degree_tb');
	table = dataTable('system_degree_tb');
	$('#srch').on('click', function(){	
		
    	table.ajax.reload();
    });
	
});

function data(system_degree_tb) {
	jsondata = [];
	var table = $('#' + system_degree_tb).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html()
			.toLowerCase();
	var orderType = order[0][1];
	var system = $("#system1").val();
	var degree = $("#degree1").val();
	
	$.post("getFilterSystemDegreeData?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		system : system,
		degree : degree
	}, function(j) {
	for (var i = 0; i < j.length; i++) {
		jsondata.push([j[i][0],j[i][2],j[i][3],j[i][4]]);
	}
});

	$.post("getTotalSysDegreeCount?" + key + "=" + value, {Search:Search,system:system,degree:degree}, function(j) {
		FilteredRecords = j;
	});
}

function Validation(){	
	
	for(at = 1; at <= $('#count_hidden_att').val(); at++){
		
		if($("#system"+at).val()=='0'){
			alert("Please Select System "+at+" Row");
			$("#system"+at).focus();
			return false;
		}
		if($("#degree"+at).val()=='0'){
			alert("Please Select Degree "+at+" Row");
			$("#degree"+at).focus();
			return false;
		}
		
	}
return true;
}

//Add-More-Add
var att=1;
function formopen_att(R){
	
	if($("select#system"+att).val() == "0"){
		alert("Please Select System");
		$("select#system"+att).focus();
		return false;
	}
	
	if($("select#degree"+att).val() == "0"){
		alert("Please Select Degree");
		$("select#degree"+att).focus();
		return false;
	}
	
	
		 $("#id_add_att"+R).hide();
		 $("#att_id_remove"+R).hide();
		 
		 att=0;
		 att= parseInt(R)+1;
		 
		 if(att < 51){
				 $("input#count_hidden_att").val(att);//current serial No
				 $("table#addunitserved").append('<tr align="center" id="tr_id_att'+att+'"><td>'+att+'</td>'
						+'<td><select name="system'+att+'" id="system'+att+'"  class="form-control" ><option value="0">--Select--</option><c:forEach var="item" items="${getSystemList}" varStatus="num"><option value="${item.id}" name="${item.id}">${item.system_name}</option></c:forEach></select></td>'
						 +'<td align="center"><select name="degree'+att+'" id="degree'+att+'"  class="form-control" ><option value="0">--Select--</option><c:forEach var="item" items="${getDegreeList}" varStatus="num"><option value="${item.id}" name="${item.id}">${item.degree_name}</option></c:forEach></select></td>'
						 +'<td align="center"><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_att'+att+'" onclick="formopen_att('+att+');" ><i class="fa fa-plus"></i></a> <a style="margin-right: 10px;"class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "att_id_remove'+att+'" onclick="formopen_re_att('+att+');"><i class="fa fa-trash"></a></td>'
			   		     +'</tr>');
		 
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( att == 51){
						 att = att-1; 
						 $("#rp_id_remove"+att).show();
					 }	   
			}
		 var curcnt = $("#count_hidden_att").val();
		 $("#new_count_hidden").val(curcnt);
	
}
//Add-More-Remove
function formopen_re_att(R){
	var del_index = $("#idofprocedure"+R).val();
	if(String(del_index) == "undefined"){
		del_index="0";
	}else{
		del_index = del_index;
	}
	 var del_field_val = $("#del_id_hidden").val();
	 
	 if(del_field_val == "0,undefined"){
		 $("#del_id_hidden").val(del_index);
	 }else{
		 $("#del_id_hidden").val(del_field_val+","+del_index);
	 }
	
	 $("tr#tr_id_att"+R).remove();
	 att = att-1;
	 $("input#count_hidden_att").val(att);
	 if(R > 2){
		 $("#id_add_att"+att).show();
		 $("#att_id_remove"+att).show();
	 }
	 if(R == 2){
		 $("#id_add_att"+att).show();
	 }
	 var ncc = $("#new_count_hidden").val();
	 ncc = ncc-1;
	 $("#new_count_hidden").val(ncc);
}

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}
	
function editData(id,system,degree){
	$("#id2").val(id);
	$("input#system2").val(system);
	$("input#degree2").val(degree);
	document.getElementById('editForm').submit();
}


</script>	
 
 
 
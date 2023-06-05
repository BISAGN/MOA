<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<link href="js/NewSearch/newsearch.css" rel="stylesheet" />

<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>

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

<form:form action="edit_system_degree_Action" method="POST" class="form-horizontal" commandName="editSysDegreeCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header title">
				<h5>
					<span id="lbladd"></span> EDIT SYSTEM & DEGREE
				</h5>
			</div>
		</div>
			
				<div class="card-body card-block">
				<div class="row">			
					<div class="col-md-6 left_content">
						<div class="row form-group">
						<input type="hidden" id="id" name="id" value="0" class="form-control"
										autocomplete="off" />
							<div class="col-md-6">
								<label for="username">SYSTEM <span class="star_design"></span> <span style="color:#ff0000">*</span></label>
							</div>
							<div class="col-md-6">
								<select name="system" id="system" class="form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getSystemList}" varStatus="num">
										<option value="${item.id}" name="${item.id}">${item.system_name}</option>
									</c:forEach>
								</select> 
							</div>
						</div>
					</div>		
				
					<div class="col-md-6 left_content">
						<div class="row form-group">
							<div class="col-md-6">
								<label for="username">DEGREE <span class="star_design"></span> <span style="color:#ff0000">*</span></label>
							</div>
							<div class="col-md-6">
									<select name="degree" id="degree" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getDegreeList}" varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.degree_name}</option>
										</c:forEach>
									</select>
							</div>
						</div>
					</div>
				</div>
								
			</div>
			
			

			<div class="card-footer" align="center">
				<a href="selectSystemDegreeUrl" class="btn btn-success btn-sm">Back</a>
				<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validation();"> 
			</div>

		</div>
</form:form>




<script type="text/javascript">

$(document).ready(function() {
	
	if('${msg}'!= ""){
		alert('${msg}');
	}
	
	$('#id').val('${Edit_system_degree_details.id}');
	$('select#degree').val('${Edit_system_degree_details.degree}');
	$('select#system').val('${Edit_system_degree_details.system}');
	
});

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
</script>	
 
 
 
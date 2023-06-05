<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="js/autoComplate/autoComplate.css">
<script type="text/javascript" src="js/common/commonmethod.js"></script>



<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/sweetalert/sweetalert.min.js"></script>

<link href="js/dropDown/select2.min.css" rel="Stylesheet"></link>
<script src="js/dropDown/select2.min.js" type="text/javascript"></script>
<link href="js/jquery/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/jquery/jquery-ui.js" type="text/javascript"></script>




<style>
/* table thead { */
/*    display: table-row; */
/*     } */
/*     tbody { */
/*     width: 100% !important; */
/*     display: table !important; */
/* } */
td.sorting_1 {
    text-align: center;
}
div#ui-datepicker-div {
	width: min-content !important;
}

div#ui-datepicker-div table thead {
	width: 100% !important;
	background-color: #257758;
}
div#ui-datepicker-div table tbody {
	height: auto !important;
}

.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all
	{
	background: #198754 !important;
}



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
#container-table {
	box-shadow: 3px 2px 5px 5px #d8dadd;
	margin-bottom: 25px;
	padding: 10px 10px;
}
.dataTables_scrollHeadInner {
    width: calc( 100% - 12px ) !important;
}
table.table{
    /* min-width: 100% !important; */
    width: 100% !important;
}
table td, table th {
    width: 324px !important;
    text-align: center;
    
}
.dataTables_scroll table thead {
/*    width: calc( 100% - 12px ) !important; */
}


span#select2-policy-container {
/* 	font-weight: 600; */
/* 	line-height: 2; */
	border: 2px solid #175658 !important;
/* 	border-radius: 0.25rem; */
 	color: #124c30 !important; 
/* 	margin-bottom: 5px; */
}

span#select2-policy_sub_category-container {
	font-weight: 600;
	line-height: 2;
	border: 3px solid #257758 !important;
	border-radius: 0.25rem;
	color: #124c30 !important;
	margin-bottom: 5px;
}
span.select2-selection.select2-selection--single {
    height: auto !important;
}

</style>
<div class="container">
	<div class="row">
		<div class="col-12">

			<div class="section-heading">
          <h2> POLICY SEARCH  <i class="fa fa-search"></i></h2>
		 </div>

		</div>
	</div>


	<div class="s010">
		<form>
			<div class="inner-form">
				<div class="basic-search">
					<div class="input-field">
					<input id="btn-gs" placeholder="Type Keywords"/>
						<div class="icon-wrap">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
								viewBox="0 0 24 24">
                  <path
									d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path>
                </svg>
						</div>
					</div>
				</div>
			<div class="advance-search">
					<div class="row">
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label ">Policy</label>
						</div>
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label">Initial
								Date</label>
						</div>
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label">Policy
								Number</label>
						</div>
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label">Status</label>
						</div>
						
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label">Policy Type</label>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">
<!-- 							<input class="form-control" name="policy" id="policy" type="text" autocomplete="off" -->
<!-- 								placeholder="Policy.."> -->

						<select name="policy" id="policy" class="form-control-sm form-control effect-9 select2 narrow wrap" >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${PoicyList}" varStatus="num">
													<option value="${item.policy_title}">${item.policy_title}</option>
												</c:forEach>

									</select>

						</div>
						<div class="col-md-2">
<!-- 							<input type="date" id="initial_date" name="initial_date" -->
<!-- 								class="form-control" placeholder="dd-mm-yyyy"> -->
										<input type="text" name="initial_date" id="initial_date"
												maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control-sm form-control effect-9 " style="    width: 76%;height: 40px;display: inline;"
												onfocus="this.style.color='#000000'"
												onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
												onkeyup="clickclear(this, 'DD/MM/YYYY')"
												onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); "
												aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">
												<span class="focus-border"><i></i></span>
						</div>
						<div class="col-md-2">
							<input class="form-control" name="policy_number"
								id="policy_number" type="text" placeholder="Policy Number" autocomplete="off"  onkeypress="AutoCompletePolicyNo(this);">
						</div>
						<div class="col-md-2">
							<select class="form-control" name="status" id="status" onchange="">
<!-- 							status seen by role wise in search on level 1,2,3,4......by ruler -->
<%-- 								<c:if test="${role=='level 1'}"> --%>
<!-- 									<option value="0">Initiated Draft</option> -->
<!-- 									<option value="1">Approved By Executive Committee</option> -->
<!-- 									<option value="2">Approved By Industry Expert</option> -->
<!-- 									<option value="3">Approved By MOA</option> -->
<!-- 									<option value="4">Rejected By Executive Committee</option> -->
<!-- <!-- 									<option value="5">Rejected By Industry Expert</option> --> 
<!-- 									<option value="6">Rejected By MOA</option> -->
<%-- 								</c:if> --%>

<%-- 								<c:if test="${role=='level 2'}"> --%>
<!-- 									<option value="7">Forwarded By MOA Entity</option> -->
<!-- 									<option value="8">Approved</option> -->
<!-- 									<option value="9">Rejected</option> -->

<%-- 								</c:if> --%>
								
								
<%-- 								<c:if test="${role=='level 3'}"> --%>
<!-- 									<option value="10">Forwarded By Executive Committee</option> -->
<!-- 									<option value="11">Approved</option> -->

<%-- 								</c:if> --%>
								
								
<%-- 								<c:if test="${role=='level 4'}"> --%>
<!-- 									<option value="12">Policy Draft Forwarded By MOA Entity</option> -->
<!-- 									<option value="13">Final Approved</option> -->
<!-- 									<option value="14">Rejected</option> -->

<%-- 								</c:if> --%>
								
								
									<c:forEach var="item" items="${DraftStatusList}" varStatus="num">
													<option value="${item[0]}">${item[1]}</option>
												</c:forEach>
								
							</select>
						</div>
						
						<div class="col-md-2">
							<select name="policy_type" id="policy_type" class="form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${policytypeList}" varStatus="num">
										<option value="${item[0]}">${item[1]}</option>
									</c:forEach>
									
<!-- 									<option value="old">Old</option> -->
<!-- 									<option value="new">New</option> -->

								</select>
						</div>
						
					</div>
				
					<!--             <div class="row second"> -->
					<!--               <div class="input-field"> -->
					<!--                 <div class="input-select"> -->
					<!--                   <select data-trigger="" name="choices-single-defaul"> -->
					<!--                     <option placeholder="" value="">Sale</option> -->
					<!--                     <option>Subject b</option> -->
					<!--                     <option>Subject c</option> -->
					<!--                   </select> -->
					<!--                 </div> -->
					<!--               </div> -->
					<!--               <div class="input-field"> -->
					<!--                 <div class="input-select"> -->
					<!--                   <select data-trigger="" name="choices-single-defaul"> -->
					<!--                     <option placeholder="" value="">Time</option> -->
					<!--                     <option>Last time</option> -->
					<!--                     <option>Today</option> -->
					<!--                     <option>This week</option> -->
					<!--                     <option>This month</option> -->
					<!--                     <option>This year</option> -->
					<!--                   </select> -->
					<!--                 </div> -->
					<!--               </div> -->
					<!--               <div class="input-field"> -->
					<!--                 <div class="input-select"> -->
					<!--                   <select data-trigger="" name="choices-single-defaul"> -->
					<!--                     <option placeholder="" value="">Type</option> -->
					<!--                     <option>Subject b</option> -->
					<!--                     <option>Subject c</option> -->
					<!--                   </select> -->
					<!--                 </div> -->
					<!--               </div> -->
					<!--             </div> -->
					<!-- <div class="row third">
						<div class="input-field">
							                <div class="result-count">
							                  <span>108 </span>results
							                 </div>
							<div class="group-btn">
								<button class="btn-delete" id="delete">RESET</button>
							</div>
							<div class="group-btn">
								<button class=" " id="newPolicyDraft">
									<a href="Policy_CreationUrl">INITIAL POLICY DRAFT</a>
								</button>
							</div>

							<div class="group-btn">
								<i class="btn-search" id="btn-reload"><i
									class="fa fa-search">Search</i></i>
								                  <button class="btn-search" id="btn-reload">SEARCH</button>
							</div>
						</div>
					</div> -->
					<div class="row third">
						<div class="input-field">
							<!--                 <div class="result-count"> -->
							<!--                   <span>108 </span>results -->
							<!--                  </div> -->
							<div class="group-btn">
<!-- 								<button class="btn-delete" id="delete">RESET</button> -->
<!-- 								<a href="PolicysearchUrl"><button class="btn-clear" id="delete">RESET</button></a> -->
 							<a href="PolicysearchUrl"><button type="button" class="btn-clear" value="Reset" > RESET</button></a>
<!-- 								class="btn-clear" -->
							</div>
							<div class="group-btn">
							<!-- 	<button class=" " id="newPolicyDraft">
									<a href="Policy_CreationUrl">INITIAL POLICY DRAFT</a>
									
								</button> -->
<!-- 								<button  class="btn-select" id="newPolicyDraft" ><a href="Policy_CreationUrl">INITIAL POLICY DRAFT</a></button> -->
<!-- 								<button type="button" class="btn-select" id="newPolicyDraft"> -->
							<a href="Policy_CreationUrl"><button type="button" class="btn-select" id="newPolicyDraft">INITIAL POLICY DRAFT	</button></a>
<!-- 									class=" btn-select" -->
							
							</div>

							<div class="group-btn">
<!-- 								<i class="btn-search" id="btn-reload"><i -->
<!-- 									class="fa fa-search">Search</i></i> -->
								<!--                   <button class="btn-search" id="btn-reload">SEARCH</button> -->
								 <button type="button" class="btn-search" id="btn-reload" ><i class="fa fa-search">SEARCH</i></button>
<!-- 								 class="btn-search" -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<br>

<div class="container"  id="container-table">
	<table id="search_policy_table"
		class="display table no-margin table-striped  table-hover  table-bordered" style="">
		<thead>
			<tr>
				<th align="center">SER NO</th>
				<th id="1">POLICY</th>
				<th id="2">INITIAL DATE</th>
				<th id="3">POLICY NUMBER</th>
				<th id="8">POLICY TYPE</th>
				<!-- 	<th id="4">ACTION</th> -->
<!-- show heading only in level one and hide in level 2,3,4........by ruler -->
				<c:if test="${role=='level 1'}">
				<c:if test="${s_status=='0'}">
				<th id="4">AMENDMENTS</th>
				</c:if>
				<th id="5">DOWNLOAD</th>
				</c:if>
				<th id="6">VIEW POLICY</th>
				
				<c:if test="${role=='level 1' && s_status=='0'}">
				<th id="7">DELETE POLICY</th>
				</c:if>
			</tr>
		</thead>
	</table>
</div>


<c:url value="delete_Url" var="delete_Url" />
<form:form action="${delete_Url}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>

<c:url value="edit_Policy_Url" var="edit_Policy_Url" />
<form:form action="${edit_Policy_Url}" method="post" id="editForm"
	name="editForm" modelAttribute="editid">
	<input type="hidden" name="editid" id="editid" value="0" />
</form:form>

<c:url value="Policy_ViewUrl" var="Policy_ViewUrl" />
<form:form action="${Policy_ViewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="viewid">
	<input type="hidden" name="viewid" id="viewid" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
	
</form:form>

<c:url value="getDownloadPdfUrlforeduDoc_policy" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="getDownloadPdfForm" name="getDownloadPdfForm" modelAttribute="doc_id1">
	<input type="hidden" name="pageUrl" id="pageUrl" value="redirect:PolicysearchUrl"/>       		
	<input type="hidden" name="doc_id1" id="doc_id1" value=""/>		
</form:form>

<c:url value="policy_search_url" var="search_url" />
<form:form action="${search_url}" method="post" id="searchForm" name="searchForm" modelAttribute="s_status">
	<input type="hidden" name="s_status" id="s_status" value="0" />	
	<input type="hidden" name="s_policy" id="s_policy" value="0" />	
	<input type="hidden" name="s_ini_date" id="s_ini_date" value="0" />	
	<input type="hidden" name="s_policyno" id="s_policyno" value="0" />	
	<input type="hidden" name="s_policytype" id="s_policytype" value="0" />	
</form:form>



<script>
	//       const customSelects = document.querySelectorAll("select");
	//       const deleteBtn = document.getElementById('delete')
	//       const choices = new Choices('select',
	//       {
	//         searchEnabled: false,
	//         itemSelectText: '',
	//         removeItemButton: true,
	//       });
	//      for (let i = 0; i < customSelects.length; i++)
	//       {
	//         customSelects[i].addEventListener('addItem', function(event)
	//         {
	//           if (event.detail.value)
	//           {
	//             let parent = this.parentNode.parentNode
	//             parent.classList.add('valid')
	//             parent.classList.remove('invalid')
	//           }
	//           else
	//           {
	//             let parent = this.parentNode.parentNode
	//             parent.classList.add('invalid')
	//             parent.classList.remove('valid')
	//           }
	//         }, false);
	//       }
	//       deleteBtn.addEventListener("click", function(e)
	//       {
	//         e.preventDefault()
	//         const deleteAll = document.querySelectorAll('.choices__button')
	//         for (let i = 0; i < deleteAll.length; i++)
	//         {
	//           deleteAll[i].click();
	//         }
	//       });
</script>

<script>
	var page = 0;
	var length_menu = 10;
	$(document).ready(function() {

		var s_status = '${s_status}';
		
		if(s_status!=""){
			$("#status").val('${s_status}');
			}
		var s_policy = '${s_policy}';
	
		if(s_policy!=""){
			$("#policy").val('${s_policy}');
			$("#policy").change();
			}
		var s_ini_date = '${s_ini_date}';
		if(s_ini_date!=""){
			$("#initial_date").val('${s_ini_date}');
			}
		var policy_number = '${s_policyno}';
		if(policy_number!=""){
			$("#policy_number").val('${s_policyno}');
			}

		var policytype = '${s_policytype}';
		if(policytype!=""){
			$("#policy_type").val('${s_policytype}');
			}
		var role = '${role}';
		if (role == "level 1") {
			$("#newPolicyDraft").show();
		} else {
			$("#newPolicyDraft").hide();
		}

		
		mockjax1('search_policy_table');
		table = dataTable('search_policy_table');
		$('#btn-reload').on('click', function() {
			
			status = $("#status").val();
// 			if(status!="0"){
				$("#s_status").val(status); 
				$("#s_policy").val($("#policy").val());
				$("#s_ini_date").val($("#initial_date").val());
				$("#s_policyno").val($("#policy_number").val());
				$("#s_policytype").val($("#policy_type").val());
				document.getElementById('searchForm').submit();
// 			}
			table.ajax.reload();
		});
		
		$('#btn-gs').change(function(){
			table.ajax.reload();
		});
		
var msg = '${s_msg}';

if(msg!=""){
	
	
		swal({
			  title: "success!",
			  text: msg,
			  icon: "success",
			})
		.then(function() {
			//debugger;
			//window.history.pushState('', 'Policy Search', '/admin/PolicysearchUrl');
 				 
 				if(window.location.href.includes("doc_id1="))
 				{
 					var url = window.location.href.split("?doc_id1=")[0];
 					window.location = url;
 				}
// 	 		 else if(window.location.href.includes("id2="))
// 	 			{
// 	 				var url = window.location.href.split("?id2")[0];
// 	 				window.location = url;
// 	 			}
 			 else if(window.location.href.includes("msg").value != "")
 				{
 				 var url = window.location.href.split("?msg")[0];
 				 window.location = url;
 				}
			});
}
		datepicketDate('initial_date');
		
		///////////////// rajdip new
// 		try{
// 			 if( document.getElementById("msg").value != "")
// 				{
// 				 var url = window.location.href.split("?msg")[0];
// 				 window.location = url;
// 				}
// 		}
// 		catch (e) {
// 			// TODO: handle exception
// 		} 
	});
</script>
<script>

function data() {
	var table = $('#search_policy_table').DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];
//		var orderType = "desc";

	jsondata = [];

	var policy = $("#policy").val();
	var initial_date = $("#initial_date").val();
	var policy_number = $("#policy_number").val();
	var status = $("#status").val();
	var policy_type = $("#policy_type").val();

	var globSearch = $("#btn-gs").val();

	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";

	$.post("getPolicy_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		policy : policy,
		initial_date : initial_date,
		policy_number : policy_number,
		status : status,
		policy_type : policy_type,
		globSearch : globSearch
	}, function(j) {
		
		for (var i = 0; i < j.length; i++) {
	<!-- show column only in level one and hide in level 2,3,4........by ruler -->
				
			jsondata.push([ j[i][0], j[i][1], j[i][2],j[i][3], j[i][4] 
			<c:if test="${role=='level 1' }">
				<c:if test="${s_status=='0'}">
				, j[i][6]
				</c:if>
			,j[i][7] 	
			</c:if>
			, j[i][8]
			<c:if test="${role=='level 1' && s_status=='0'}">
			, j[i][9]
			</c:if>
			]);
		}
	});
	$.post("getPolicy_dataCount?" + key + "=" + value, {
		Search : Search,
		policy : policy,
		initial_date : initial_date,
		policy_number : policy_number,
		status : status,
		policy_type : policy_type,
		globSearch : globSearch
	}, function(j) {
		FilteredRecords = j;
	});

	setTimeout(function(){
		$("td").each(function() {
			if ($(this).text()=='${policy_no}' && $(this).text()!='') {
				$(this).parent().css({"background-color": "yellow"});
			}
		});
     }, 1000);
}
</script>

<script>
	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}

	function editData(id) {
		document.getElementById('editid').value = id;
		document.getElementById('editForm').submit();
	}

	function ViewData(id,status) {
		//alert(f_status);
		$("#status1").val(status); 
		document.getElementById('viewid').value = id;
		document.getElementById('viewForm').submit();
	
	}

	function getDownloadPdfPolicy(id){   
		$("#doc_id1").val(id); 
		document.getElementById("getDownloadPdfForm").submit();
	} 

	
</script>

<script>


function AutoCompletePolicyNo(ele){
	
	var code = ele.value;
	var susNoAuto =$("#"+ele.id);
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getpolicynoAuto?" + key + "=" + value,
				data : {
					a : code
				},
				success : function(data) {
					var susval = [];
					var length = data.length - 1;
					for (var i = 0; i < data.length; i++) {
						susval.push(data[i]);
					}
					response(susval);
				}
			});
		},
		minLength : 1,
		autoFill : true,
		change : function(event, ui) {
			if (ui.item) {
				return true;
			} else {
				alert("Please Enter Policy Number ");			
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {

		}
	});
	
}

</script>

				<script>
// 				var msg1 = '${msg}'; 
// 				if(msg1 != null && !(msg1 ==""))
// 				{
// 				alert(msg1);
// 				}
				</script>


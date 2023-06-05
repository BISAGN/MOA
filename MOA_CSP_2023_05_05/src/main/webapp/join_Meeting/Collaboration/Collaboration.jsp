<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<script type="text/javascript" src="js/watermark/common.js"></script>

<!-- datatable style and js end-->
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
					<span id="lbladd"></span>
						<h2>Collaboration </h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Collaboration
									</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-12">
					<!-- input style start -->
					<form:form name="collaborationcolbform" id="collaborationcolbform"
						action="Collaborationcolb_Action" method="post" class="form-horizontal"
						modelAttribute="Collaborationcolb_CMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Collaboration</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">Collaboration Type<span
											class="mandatory">*</span></label>
										<div class="select-position">
												 <select class="singleselect form-control form-control-lg" name="collaborationtype" id="collaborationtype"  class="form-control qualification ">
														<option value="0">--Select--</option>
														 <c:forEach var="item" items="${CollaborationIntypeList}" varStatus="num"> 
														<option value="${item.id}" name="${item.collaboration_type}">${item.collaboration_type}</option> 
						 								 </c:forEach> 
														</select>
														
													
													 
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">Sector<span
											class="mandatory">*</span></label>
										<div class="select-position">
										<select class="singleselect form-control form-control-lg" name="collaborationsector" id="collaborationsector"  class="form-control qualification ">
										     <option value="0">--Select--</option>
										     <c:forEach var="item" items="${CollaborationInsectorList}" varStatus="num"> 
										      <option value="${item.id}" name="${item.sector_type}">${item.sector_type}</option> 
						 				     </c:forEach> 
														</select>
										</div>
									</div>
								</div>


							
							
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label>Categories<strong class="mandatory">*</strong></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="collaborationcategory" id="collaborationcategory"  class="form-control qualification ">
										     <option value="0">--Select--</option>
										     <c:forEach var="item" items="${CollaborationIncategoryList}" varStatus="num"> 
										      <option value="${item.id}" name="${item.collab_cate}">${item.collab_cate}</option> 
						 				     </c:forEach> 
														</select>
										</div>
									</div>
								</div>

	                        <div class="col-12 col-sm-12 col-md-12 col-lg-12">
									<div class="input-style-2">
										<label>Title<span class="mandatory">*</span></label> <input
											type="text" id="collaborationtitle" name="collaborationtitle"
											class="autocomplete UpperClassName txt-transupp" 
											autocomplete="off" maxlength="150" placeholder="Title"/>

								 	</div>
									
							  	</div>


                                  <div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label for="text-input">Description<strong
											class="mandatory">* </strong></label>
										<textarea id="collaborationdescription" name="collaborationdescription" rows="5"
											cols="20" autocomplete="off" placeholder="Description"></textarea>


									</div>
								</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="input-style-2">
												<label>From Date</label> 
													<input type="text" name="from_date" id="from_date" maxlength="10" class="form-control-sm form-control effect-9 "
																aria-required="true" autocomplete="off"
																value="DD/MM/YYYY" placeholder="DD/MM/YYYY">
<!-- 																onclick="clickclear(this, 'DD/MM/YYYY')" -->
<!-- 																onfocus="this.style.color='#000000'" -->
<!-- 																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" -->
<!-- 																onkeyup="clickclear(this, 'DD/MM/YYYY')" -->
<!-- 																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" -->
											</div>																											
								</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="input-style-2">
												<label>To Date</label> 
													<input type="text" name="to_date" id="to_date" maxlength="10" class="form-control-sm form-control effect-9 "
																aria-required="true" autocomplete="off"
																value="DD/MM/YYYY" placeholder="DD/MM/YYYY">
<!-- 																onclick="clickclear(this, 'DD/MM/YYYY')" -->
<!-- 																onfocus="this.style.color='#000000'" -->
<!-- 																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" -->
<!-- 																onkeyup="clickclear(this, 'DD/MM/YYYY')" -->
<!-- 																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" -->
											</div>																											
								</div>
								
								<input type="hidden" id="id" name="id" value="0"
									class="autocomplete UpperClassName txt-transupp" 
									autocomplete="off" maxlength="150" placeholder="Title"/>
								
								      <!--   <div class="col-12 col-sm-12 col-md-4 col-lg-4">
									   <div class="input-style-2">
										<label>Owner Name<span class="mandatory">*</span></label> <input
											type="text" id="collaborationsign" name="collaborationsign"
											class="autocomplete UpperClassName txt-transupp" 
											autocomplete="off" maxlength="150" placeholder="Owner Name"/>

								 	</div>
									
							  	</div> -->
							  										 
								</div>
							 <!-- end input -->
								</div>
						
							</div>

							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save" />
								</li>
								<li><a href="Collaboration_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
							  <!--  <li>
							    <a id="btn-view" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button" ><i class="lni lni-view-alt"></i>View</a>
							    </li> -->	
							</ul>
							
						</div>
<!-- end card -->
						</form:form>
				</div>
			</div>
		</div>
		</div>

		<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="collaboration_org">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th><h6>Collaboration Type</h6></th>
									<th><h6>Sector</h6></th>
									<th><h6>Categories</h6></th>
									<th><h6>Title</h6></th>
									<th><h6>Description</h6></th>
									<th><h6>From Date</h6></th>
									<th><h6>To Date</h6></th>
									<!-- <th><h6>Owner Name</h6></th> -->
									
									
									
									
									<%-- <th><h6>Professional</h6></th>
									<th><h6>Course</h6></th>
									<th id="${item.id}"><h6> Paper</h6></th>
									<th id="${item.id}"><h6> Time</h6></th>
									<th id="${item.id}"><h6> Instruction</h6></th>
									<th id="${item.id}"><h6> Question Type</h6></th>
									<th id="${item.id}"><h6> Number of Questions</h6></th>
									<th id="${item.id}"><h6> Marks per Question</h6></th>
<!-- 									<th><h6> Status</h6></th> --> --%>
									<th><h6>Action</h6></th>
								</tr>
								<!-- 						end table row -->
							</thead>
							<tbody>
							</tbody>
						</table>
						<!-- 				end table -->
					</div>
				</div>
				<!-- 		end card -->
			</div>
			<!-- 	end col -->
		</div>
</section>
  
<c:url value="getSearch_CollaborationcolbMaster" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Collaborationcolb_name1">
	<input type="hidden" name="Collaborationcolb_name1" id=Collaborationcolb_name1 />
	
</form:form>

<c:url value="Edit_Collaboration_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="Collaboration_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Collaborationcolbreport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		$("#pop").hide();
		$("#pop2").hide();
		mockjax1('collaboration_org');
		table = dataTable('collaboration_org');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		
		datepicketDate('from_date');
		datepicketDate('to_date');
		
		$( "#from_date").datepicker( "option", "maxDate", null);
		$( "#to_date").datepicker( "option", "maxDate", null);
		
	});
	
	document.addEventListener('DOMContentLoaded', function() {
		
// 		document.getElementById('collaborationtype').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(this, event);
// 		};
		
// 		document.getElementById('collaborationsector').onkeypress = function() {
// 			return isNumberKey0to9(event);
// 		};
		
// 		document.getElementById('collaborationcategory').onkeypress = function() {
// 			return isNumberKey0to9(event);
// 		};
		
// 		document.getElementById('collaborationtitle').onkeypress = function() {
// 			return isNumberKey0to9(event);
// 		};
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

// 		document.getElementById('btn-view').onclick = function() {
// 			getpop();
// 			getpop2();
// 		};
// 		document.getElementById('btn-view').onclick = function() {
// 			getpop2();
// 		};

	});
	
	function setTimeLoadForTable(){
		
		document.querySelectorAll('.ADDCollaboratoncolb').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apidID'+val).value;
				var hapt = document.getElementById('aptypeTYPE'+val).value;
				var haps = document.getElementById('apsectSECT'+val).value;
				var hapc = document.getElementById('apcateCATE'+val).value;
				var hapti = document.getElementById('aptitlTITL'+val).value;
				var hapde = document.getElementById('apdescDESC'+val).value;
				var hdafr = document.getElementById('apfrodFROD'+val).value;
				var hdato = document.getElementById('aptodTOD'+val).value;
				
				
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hapt,haps,hapc,hapti,hapde,hdafr,hdato);
				} else {
					return false;
				}
			})
		});
		document.querySelectorAll('.deleteOnclick').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('deleteID'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(hid);
				} else {
					return false;
				}
			})
		});
		
	}
	
	function data(collaboration_org) {
		
		jsondata = [];
		var table = $('#' + collaboration_org).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var collaborationtype = $("#collaborationtype").val();
		var collaborationsector = $("#collaborationsector").val();
		var collaborationcategory = $("#collaborationcategory").val();
		var collaborationtitle = $("#collaborationtitle").val();
		var collaborationdescription = $("#collaborationdescription").val();
		var from_date = $("#from_date").val();
		var to_date = $("#to_date").val();
		
		$.post("getFilterCollaboration_colbdata?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			collaborationtype : collaborationtype,
			collaborationsector : collaborationsector,
			collaborationcategory : collaborationcategory,
			collaborationtitle : collaborationtitle,
			collaborationdescription : collaborationdescription,
			from_date : from_date,
			to_date : to_date,
			

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,j[i].collaboration_type,j[i].sector_type,j[i].collab_cate, j[i].collaborationtitle, j[i].collaborationdescription, j[i].from_date, j[i].to_date, j[i].action]);
			}
			
		});
		$.post("getTotalCollaboration_colbdataCount?" + key + "=" + value, {
			Search : Search,
			collaborationtype : collaborationtype,
			collaborationsector : collaborationsector,
			collaborationcategory : collaborationcategory,
			collaborationtitle : collaborationtitle,
			collaborationdescription : collaborationdescription,
			from_date : from_date,
			to_date : to_date,
			

		
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id,collaborationtype,collaborationsector,collaborationcategory,collaborationtitle,collaborationdescription,from_date,to_date) {
		//debugger;
		document.getElementById('lbladd').innerHTML = "Update ";
		$("select#collaborationtype").val(collaborationtype);
		$('#collaborationtype').trigger('change');
		$("select#collaborationsector").val(collaborationsector);
		$('#collaborationsector').trigger('change');
		$("select#collaborationcategory").val(collaborationcategory);
		$('#collaborationcategory').trigger('change');
		$("input#collaborationtitle").val(collaborationtitle);
		$("#collaborationdescription").val(collaborationdescription);
		$("#from_date").val(from_date);
		$("#to_date").val(to_date);
		document.getElementById('id').value=id;
	}
	

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
 	function Search() {
 		$("#collaborationtype").val($('#collaborationtype').val());
 		$("#collaborationsector").val($('#collaborationsector').val());
 		$("#collaborationcategory").val($('#collaborationcategory').val());
 		$("#collaborationtitle").val($('#collaborationtitle').val());
 		$("#collaborationdescription").val($('#collaborationdescription').val());
 		$("#from_date").val($('#from_date').val());
 		$("#to_date").val($('#to_date').val());
		document.getElementById('searchForm').submit();
 	}

	function Validation() {
		return true;
		if ($("#collaborationtype").val().trim() == "0") {
			alert("Please Select Collaboration Type.");
			$("select#collaborationtype").focus();
			return false;
		}
		if ($("#collaborationsector").val().trim() == "0") {
			alert("Please Select Sector.");
			$("select#collaborationsector").focus();
			return false;
		}
		if ($("#collaborationcategory").val().trim() == "0") {
			alert("Please Select Category.");
			$("select#collaborationcategory").focus();
			return false;
		}
		if ($("#collaborationtitle").val().trim() == "") {
			alert("Please Enter Title .");
			$("input#collaborationtitle").focus();
			return false;
		}
		if ($("#collaborationdescription").val().trim() == "") {
			alert("Please Enter Description.");
			$("#collaborationdescription").focus();
			return false;
		}
		if ($("input#from_date").val() == "DD/MM/YYYY" || $("input#from_date").val() == "" ) {
			alert("Please Enter From Date.");
			$("#from_date").focus();
			return false;
		}
		
		if ($("input#to_date").val() == "DD/MM/YYYY" ||  $("input#to_date").val() == "" ) {
			alert("Please Enter To Date.");
			$("#to_date").focus();
			return false;
		}
		
		return true;
	}
	
</Script> 

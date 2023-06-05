<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- datatable style and js start-->
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Event/Holiday Master</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Event/Holiday
									Master</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form:form name="Event_Master" id="Event_Master"
						action="Event_Master_Action" method="post" 
						modelAttribute="Event_MasterCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
							<div class="inst1_hide">
								<div class="inst_block simple-instruction">
									<strong>Instruction :</strong> Restricted/Optional Holidays you can add letter.
								</div>
							</div>
								<!-- <h6 class="mb-25">Event Master</h6> -->
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label>Event Name<span class="mandatory">*</span></label> <input
												type="text" id="event_name" name="event_name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="1000"
												placeholder="Enter Event Name" />

										</div>
									</div>
									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-2">
											<label for="username">Event Date<span
												class="mandatory">*</span></label><input type="text"
												name="event_date" id="event_date" maxlength="10"
												class="form-control-sm form-control effect-9 "
												aria-required="true" autocomplete="off" placeholder="DD/MM/YYYY">
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Holiday<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg""
													name="holiday" id="holiday">
													<option value="0">---Select---</option>												
													<option value="1">Yes</option>
													<option value="2">No</option>
												</select>

											</div>
										</div>
									</div>
								</div>
							</div>
							
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="submit"
												value="Save" /></li>
											<li><a href="event_mstrUrl"
												class="main-btn dark-btn n btn-hover btnreset" type="button">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
					</form:form>
				</div>
			</div>
		<section class="single-detail-block">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="card-style mb-30">
						<div class="table-wrapper table-responsive custom-datatable-p">
							<table class="table" id="search_event">
								<thead>
									<tr>
										<th><h6>Sr No</h6></th>
										<th id="${item.id}"><h6>Event Name</h6></th>
										<th id="${item.id}"><h6>Event Date</h6></th>
										<th id="${item.id}"><h6>Holiday</h6></th>
										<th><h6>Action</h6></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</section>
		</div>
	</div>
</section>

<c:url value="getSearch_Event_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Event_name1">
	<input type="hidden" name="Event_name1" id="Event_name1" />
	<input type="hidden" name="Event_date1" id="Event_date1" value="0" />
	<input type="hidden" name="Holiday1" id="Holiday1" />
</form:form>

<c:url value="Edit_Event_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id8">
	<input type="hidden" name="id8" id="id8">
</form:form>

<c:url value="delete_Url9" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id9">
	<input type="hidden" name=id9 id="id9" value="0" />
</form:form>

<c:url value="Systemreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		
		datepicketDate('event_date');
		$( "#event_date").datepicker( "option", "maxDate", null);
		
		mockjax1('search_event');
		table = dataTable('search_event');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		//debugger;
		if(window.location.href.includes("msg"))
		{
// 			 if(confirm('${msg}')){
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
// 			 }
		}
	});
	
	function setTimeLoadForTable(){
		
		document.querySelectorAll('.ADDEvent').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid);
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
	document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};

	document.getElementById('event_name').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	
	document.getElementById('event_date').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	document.getElementById('event_date').onclick = function() {
		clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('event_date').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
	
		document.getElementById('event_date').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');
			validateDate_BackDate(this.value,this);
		};
		
		document.getElementById('event_date').onfocus = function() {
			this.style.color='#000000';
		};
		
		document.getElementById('event_date').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
	document.getElementById('holiday').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	});
	
	function data(search_event) {
		
		
		jsondata = [];
		var table = $('#' + search_event).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var event_name = $("#event_name").val();
		var event_date = $("#event_date").val();
		var holiday = $("#holiday").val();

		$.post("getFilter_Event_MSTR_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			event_name : event_name,
			event_date : event_date,
			holiday : holiday
			

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				if(j[i].holiday==1){
					j[i].holiday='Yes'
				}
				else{
					j[i].holiday='No'
				}
					
				jsondata.push([ j[i].sr_no, j[i].event_name, j[i].event_date, j[i].holiday, j[i].action ]);
			}
		});
		$.post("getTotal_Event_MSTR_dataCount?" + key + "=" + value, {
			Search : Search,
			event_name : event_name,
			event_date : event_date,
			holiday : holiday
		}, function(j) 
		{

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id) {

		$("#id8").val(id);
		document.getElementById('updateForm').submit();
	}


	function deleteData(id) {
		$("#id9").val(id);
		document.getElementById('deleteForm').submit();
	}
	
	function Search() {
		$("#Event_name1").val($('#event_name').val());
		$("#Event_date1").val($('#event_date').val());
		$("#Holiday1").val($('#holiday').val());
		document.getElementById('searchForm').submit();
	}

	function Validation() {

		if ($("#event_name").val().trim() == "") {
			alert("Please Enter Event Name.");
			$("input#event_name").focus();
			return false;
		}
		
		if($("#event_date").val() == "DD/MM/YYYY" || $("#event_date").val() == "") {
			alert("Please Enter Event Date.");
 			$("#event_date").focus();
			return false;
	 	}

		if ($("select#holiday").val().trim() == "0") {
			alert("Please Select Holiday.");
			$("Select#holiday").focus();
			return false;
		}
		
		return true;
	}
	

</Script>

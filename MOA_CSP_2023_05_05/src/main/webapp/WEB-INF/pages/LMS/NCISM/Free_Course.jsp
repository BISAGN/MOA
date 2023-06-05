<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
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
<!-- <link rel="stylesheet" href="js/assets/collapse/collapse.css"> -->
<!-- <link rel="stylesheet" href="admin/assets/db_css/db_custom_style.css"> -->
<script src="js/miso/commonJS/addmorefunctionality.js"
	type="text/javascript"></script>


	<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Skill Enhancement</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Skill
									Enhancement</li>
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
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form action="OnlineCoures_UrlAction" method="POST"
						modelAttribute="OnlineCourse_CMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Skill Enhancement</h6>
								<div class="row">
								
								
									
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="username">Course<span class="mandatory">*</span></label>
											<input type="text" id="coursename" name="coursename"
												placeholder="Course"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" />
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="username">File Upload<span
												class="mandatory">*</span></label> <input type="file" accept=".pdf"
												id="upload_file" name="upload_file" class="form-control"
												autocomplete="off" />
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="username">Url<span class="mandatory">*</span></label>
											<input id="url" name="url" placeholder="Url"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" />
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>
										<!-- end input -->
									</div>



									<div class="col-lg-4 col-md-6 col-sm-12">
										<div class="input-style-2">
											<label for="username">Start Date<span
												class="mandatory">*</span></label> <input type="text"
												name="start_date" id="start_date" maxlength="10"
												class="form-control-sm form-control effect-9 "
												aria-required="true" autocomplete="off" value="DD/MM/YYYY"
												placeholder="Select Start Date">
										</div>

									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-2">
											<label for="username">End Date <span
												class="mandatory">*</span></label> <input type="text"
												name="end_date" id="end_date" maxlength="10"
												class="form-control-sm form-control effect-9 "
												aria-required="true" autocomplete="off" value="DD/MM/YYYY"
												placeholder="Select End Date">

										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-1">
											<label for="username">Description<span
												class="mandatory">*</span></label>
											<textarea type="text" id="description" name="description"
												rows="5" cols="50" maxlength="250" placeholder="Description"
												autocomplete="off"></textarea>
										</div>
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" />
										</div>
										<!-- end input -->
									</div>


								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">

											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="submit"
												value="Save"></li>
											<li><a href="Online_Coures_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>


						<section class="single-detail-block">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="card-style mb-30">
										<div class="table-wrapper table-responsive custom-datatable-p">
											<table class="table" id="search_Free_Course">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th><h6>Course</h6></th>
														<th><h6>Description</h6></th>
														<th><h6>Url</h6></th>
														<th><h6>Action</h6></th>
													</tr>
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

					</form:form>
				</div>
			</div>
		</div>
</div>
</section>

<c:url value="Edit_Online_Coures_Url" var="Edit_Online_Coures_Url" />
<form:form action="${Edit_Online_Coures_Url}" method="post"
	id="updateForm" name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>


<c:url value="deleteonlinecourse_Url" var="deleteonlinecourse_Url" />
<form:form action="${deleteonlinecourse_Url}" method="post"
	id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>


<c:url value="getDownloadPdfonlineCourse" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="downloadPdf"
	name="downloadPdf" modelAttribute="doc_id1">
	<input type="hidden" name="pageUrl" id="pageUrl"
		value="redirect:Online_Coures_Url" />
	<input type="hidden" name="doc_id1" id="doc_id1" value="" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	

	$(document).ready(function() {

		mockjax1('search_Free_Course');
		table = dataTable('search_Free_Course');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

		if ('${list.size()}' == "") {
			$("div#search_Free_Course").hide();
		}
		$.ajaxSetup({
			async : false
		});
		datepicketDate('start_date');
		datepicketDate('end_date');
		
		$( "#start_date" ).datepicker("option", "minDate", 0);
	 	$( "#end_date").datepicker("option", "minDate", 0);
	 	
		$( "#start_date").datepicker( "option", "maxDate", null);
		$( "#end_date").datepicker( "option", "maxDate", null);
		
		
		
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
		if(window.location.href.includes("doc_id1"))
		{
			 var url = window.location.href.split("?doc_id1")[0];
			 window.location = url;
		}
	});


	

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('coursename').onkeypress = function() {
			onlyAlphabetsStringSpace(this,event);
		};

		document.getElementById('start_date').onclick = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		
		document.getElementById('start_date').onfocus = function() {
			this.style.color='#000000';
		};
		document.getElementById('start_date').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');
// 			validateDate_BackDate(this.value,this);
		};
		document.getElementById('start_date').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('start_date').onchange = function() {
			onchangeCount(this.value);
			clickrecall(this,'DD/MM/YYYY');
			//validateDate_FutureDate(this.value,this);
		};
		document.getElementById('end_date').onclick = function() {
			 clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('end_date').onfocus = function() {
			 this.style.color='#000000';
		};
		document.getElementById('end_date').onblur = function() {
			clickrecall(this,'DD/MM/YYYY');
         //validateDate_BackDate(this.value,this);
		};
		document.getElementById('end_date').onkeyup = function() {
			clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('end_date').onchange = function() {
			checkTodate(this,start_date);
			onchangeCount(this.value);
			clickrecall(this,'DD/MM/YYYY');
			//validateDate_FutureDate(this.value,this);
		};
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		
//  document.getElementById('start_date').onclick = function() {
// 		clickclear(this, 'DD/MM/YYYY');
// 	};
// 	document.getElementById('start_date').onfocus = function() {
// 		this.style.color='#000000';
// 	};
// 	document.getElementById('start_date').onblur = function() {
// 		clickrecall(this,'DD/MM/YYYY');
// 	};
// 	document.getElementById('start_date').onkeyup = function() {
// 		clickclear(this, 'DD/MM/YYYY');
// 	};
// 	document.getElementById('start_date').onchange = function() {
// 		onchangeCount(this.value);
// 		clickrecall(this,'DD/MM/YYYY');
// 	};
// 	document.getElementById('end_date').onclick = function() {
// 		clickclear(this, 'DD/MM/YYYY');
// 	};
// 	document.getElementById('end_date').onfocus = function() {
// 		this.style.color='#000000';
// 	};
// 	document.getElementById('end_date').onblur = function() {
// 		clickrecall(this,'DD/MM/YYYY');
// 	};
// 	document.getElementById('end_date').onkeyup = function() {
// 		clickclear(this, 'DD/MM/YYYY');
// 	};
	
// 	document.getElementById('end_date').onchange = function() {
// 		onchangeCount(this.value);
// 		clickrecall(this,'DD/MM/YYYY');
// 		checkTodate(this,start_date);
// 	};
		
		
	});
	
	function setTimeLoadForTable(){
		
		document.querySelectorAll('.download').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				var hid = document.getElementById('download_id'+val).value;
				if (confirm('Are You Sure You Want to Download Detail ?')) {
					download_file(hid);
				} else {
					return false;
				}
			})
		});
		document.querySelectorAll('.delete').forEach((items, index) => {
			items.addEventListener('click', event => {
// 				debugger;
				var val=parseInt(index)+1;
				var hid = document.getElementById('delete_id'+val).value;
				if (confirm('Are You Sure You Want to delete Detail ?')) {
					deleteData(hid);
				} else {
					return false;
				}
			})
		});
		document.querySelectorAll('.update').forEach((items, index) => {
			items.addEventListener('click', event => {
// 				debugger;
				var val=parseInt(index)+1;
				var hid = document.getElementById('update_id'+val).value;
				if (confirm('Are You Sure You Want to update Detail ?')) {
					editData(hid);
				} else {
					return false;
				}
			})
		});
		
// 		document.querySelectorAll('.Certificate').forEach((items, index) => {
// 			items.addEventListener('click', event => {
// // 				debugger;
// 				var val=parseInt(index)+1;
// 				var hid = document.getElementById('certi_id'+val).value;
// 				if (confirm('Are You Sure You Want to Download Certificate ?')) {
// 					download_file(hid);
// 				} else {
// 					return false;
// 				}
// 			})
// 		});
		
		
	}
	 

	
// 	function Validation() {

// 		if ($("#policy_no").val() == "") {
// 			alert("Please Enter Policy Number");
// 			return false;
// 		}

// 		if ($("#initial_date").val() == "DD/MM/YYYY") {
// 			alert("Please Select Date");
// 			return false;
// 		}

// 		return true;

// 	}

	function editData(id) {

		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}

	function download_file(id) {
		$("#doc_id1").val(id);
		document.getElementById("downloadPdf").submit();
	}

	function Validation() {

		if ($("#coursename").val().trim() == "") {
			alert("Please Enter Course Name.");
			$("input#coursename").focus();
			return false;
		}
		if ($("#upload_file").val().trim() == "") {
			alert("Please Upload the File");
			$("input#upload_file").focus();
			return false;
		}
		if ($("#url").val().trim() == "") {
			alert("Please Enter Url.");
			$("input#url").focus();
			return false;
		}
		if ($("#start_date").val() == "" || $("#start_date").val() == "DD/MM/YYYY") {
			alert("Please Select Start Date");
			$("#start_date").focus();
			return false;
		}
		if ($("#end_date").val() == "" || $("#end_date").val() == "DD/MM/YYYY") {
			alert("Please Select End Date");
			$("#end_date").focus();
			return false;
		}
		if ($("#description").val().trim() == "") {
			alert("Please Enter Description.");
			$("textarea#description").focus();
			return false;
		}

		return true;

	}

	function data(search_Free_Course) {
		jsondata = [];
		var table = $('#' + search_Free_Course).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var url = $("#url").val();
		var description = $("#description").val();
		var coursename = $("#coursename").val();

		url = null;
		description = null;

		$.post("getFilterFc_url_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			url : url,
			coursename : coursename,
			description : description

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i][5], j[i][0], j[i][1], j[i][2], j[i][3] ]);
			}
		});
		$.post("getTotalFc_url_dataCount?" + key + "=" + value, {
			Search : Search,
			url : url,
			coursename : coursename,
			description : description
		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
function checkTodate(obj,start_date) {
		
		
		if(start_date.value !=""){
			
			
			var Date1= $("#start_date").val();  
		 
				if(Date1 !=""){
					var id = obj.id;
					var myDate = document.getElementById(id).value;
					if ((Date.parse(myDate) < Date.parse(Date1)))
							{
						alert('To Date should not be before Start Date');	
						obj.value = "";
					}
				}				
		}
		else
		{
			alert("Please Select Start Date first.");
			$("#start_date").focus();
			obj.value = "";
		}	
	}
	
function onchangeCount(val) {
	if (document.getElementById("start_date").value != "DD/MM/YYYY"
			&& (document.getElementById("end_date").value != "DD/MM/YYYY")
			&& (document.getElementById("end_date").value != "")) {
// 		$("#course_fee").val("");
//		$("#demo_video").val("");
// 		$("#old_elective_name").val("");
// 		$("#course_switch_duration").val("");
// 		$("select#system_id").val("0");
// 		$("select#system_id").change();

		var start_date = document.getElementById("start_date").value.split(
				'/').reverse().join('-');
		var end_date = document.getElementById("end_date").value.split('/')
				.reverse().join('-');
		var start_dt = new Date(start_date);
		var end_dt = new Date(end_date);
		// To calculate the time difference of two dates
		var Difference_In_Time = end_dt.getTime() - start_dt.getTime();
		// To calculate the no. of days between two dates
		var Difference_In_Days = (Difference_In_Time / (1000 * 3600 * 24)) + 1;

		var Difference_In_Weeks = Math.floor(Difference_In_Days / 7);
		var remainday = (Difference_In_Days) - (Difference_In_Weeks * 7);
		if (Difference_In_Days < 0) {
			alert("Please Select Appropriate Dates!");
			$("input#start_date").val("DD/MM/YYYY");
			$("input#end_date").val("DD/MM/YYYY");
			$("#duration").html("");
			return false;
		}
		if (Difference_In_Days > 0) {
			if (Difference_In_Weeks == 0) {
				$("#duration").html(Difference_In_Days + " Days");
			} else if (remainday == 0) {
				$("#duration").html(Difference_In_Weeks + " Weeks ");
			} else {
				$("#duration").html(Difference_In_Weeks + " Weeks " + remainday + " Days");
			}
		}
	}
}
	
</script>

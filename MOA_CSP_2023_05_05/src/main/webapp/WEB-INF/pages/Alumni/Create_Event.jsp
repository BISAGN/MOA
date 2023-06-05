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
<script type="text/javascript" src="js\watermark\common.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<span id="lbladd"></span>
						<h2>Create Event</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Create Event
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
					<form:form name="Create_Event_form" id="Create_Event_form"
						action="Create_Event_Action" method="post" enctype="multipart/form-data"
						class="form-horizontal" modelAttribute="Create_Event_CMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Create Event</h6>
							<div class="row">
								<input type="hidden" name="id" id="id" value="0" />
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Title <span class="mandatory">*</span></label> <input
											type="text" id="title" name=title
											class="autocomplete xt-transupp" autocomplete="off"
											maxlength="100" placeholder="Event Title" />
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Description <span class="mandatory">*</span></label>
										<input type="text" id="description" name=description
											class="autocomplete xt-transupp" autocomplete="off"
											maxlength="100" placeholder="Event Description" />

									</div>

								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label for="text-input">Image<strong
											class="mandatory">*</strong></label> <input type="file"
											accept="image/*" id="upload_img" class="form-control"
											name="upload_img" accept=".jpg" class="form-control">
										<input type="hidden" id="upload_img_hid" name="upload_img_hid"
											class="form-control">

									</div>

								</div>


								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Venue <span class="mandatory">*</span></label> <input
											type="text" id="venue" name=venue
											class="autocomplete xt-transupp" autocomplete="off"
											maxlength="100" placeholder="Event Venue" />

									</div>
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>Date&Time <span class="mandatory">*</span></label>
										<input type="datetime-local" id="date_time"
											name="date_time" > 
											

									</div>
								</div>


								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<!-- <label>Event_Batch <span class="mandatory">*</span></label> <input
											type="date" name="pers_date_of_birth" id="pers_date_of_birth"
											maxlength="10" class="form-control-sm form-control"
											style="display: inline;" aria-required="true"
											autocomplete="off" style="color: rgb(0, 0, 0);"
											value="DD/MM/YYYY" /> -->
											
											<input type="hidden" id="yrr" name="yrr" maxlength="4" value="" >
											<label for="start">Batch</label>
											 <input type="number" id="batch" name="batch" placeholder="Event Batch" >
							
									</div>
								</div>



						</div>
						<div>
							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save" />
								</li>
								<li><a href="Create_event_Url"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>

							</ul>
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_Event">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th ><h6>Title</h6></th>
									<th><h6>Description</h6></th>
<!-- 									<th><h6>Event Iamge</h6></th> -->
									<th><h6>Venue</h6></th>
									<th><h6>Date&time</h6></th>
									<th><h6>Batch</h6></th>
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
	</div>


</section>

<c:url value="getSearch_Event_Master" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="Certificate_name1">
	<input type="hidden" name="Event_name1" id="Event_name1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>

<c:url value="Edit_Create_event_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0">
</form:form>

<c:url value="Create_event_Url_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Eventreport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_Event');
		table = dataTable('search_Event');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
	});
	
	function setTimeLoadForTable(){
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};

// 		document.getElementById('title').onkeypress = function() {
// 			return onlyAlphabetsStringSpace(this, event);
// 		};
		
		document.querySelectorAll('.ADDtitle').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				var htitle = document.getElementById('aptitleAGE'+val).value;
				var hdes = document.getElementById('apdesAGE'+val).value;
				var himg = document.getElementById('apimgAGE'+val).value;
				var hvenue = document.getElementById('apvenueAGE'+val).value;
				var hdt = document.getElementById('apdtAGE'+val).value;
				var hbetch = document.getElementById('apbetchAGE'+val).value;
			
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,htitle,hdes,hvenue,hdt,hbetch,himg);
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
	
	function data(search_Event) {
		
		jsondata = [];
		var table = $('#' + search_Event).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var title = $("#title").val();
		var description = $("#description").val();
		var upload_img = $("#upload_img").val();
		var venue = $("#venue").val();
		var date_time = $("#date_time").val();
		var batch = $("#batch").val();
		
		
		$.post("getFiltereventdata?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			title : title,
			description : description,
			upload_img : upload_img,
			venue : venue,
			date_time : date_time,
			batch : batch
			
			

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].title, j[i].description,  j[i].venue, j[i].date_time, j[i].batch,j[i].action ]);
			}
		});
		$.post("getTotaleventdataCount?" + key + "=" + value, {
			title : title,
			Search : Search,
			description : description,
			upload_img : upload_img,
			venue : venue,
			date_time : date_time,
			batch : batch
		
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id,title,description,venue,date_time,batch,himg) {
		debugger;
		document.getElementById('lbladd').innerHTML = "Update ";
		$("input#title").val(title);
		$("input#description").val(description);
		$("input#upload_img_hid").val(himg);
		$("input#venue").val(venue);
		$("input#date_time").val(date_time);
		$("input#batch").val(batch);
		$("input#id").val(id);
// 		document.getElementById('id').value=id;
	}
	

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	
// 	function Search() {
// 		$("#title1").val($('#title').val());
// 		$("#description1").val($('#description').val());
// 		$("#upload_img1").val($('#upload_img').val());
// 		$("#venue1").val($('#venue').val());
// 		$("#date_time1").val($('#date_time').val());
// 		$("#batch1").val($('#batch').val());
// 		document.getElementById('searchForm').submit();
// 	}

	function Validation() {

		if ($("#title").val().trim() == "") {
			alert("Please Enter Event Name.");
			$("input#title").focus();
			return false;
		}
		if ($("#description").val() == "2") {
			alert("Please Enter Description.");
			$("input#description").focus();
			return false;
		}
		if($("#upload_img_hid").val().trim() == "" && $("#upload_img").val().trim() == ""){
			alert("Please  Upload_img.");
			$("input#upload_img").focus();
			return false;
		}
		if ($("#venue").val().trim() == "") {
			alert("Please Enter venue.");
			$("input#venue").focus();
			return false;
		}
		if ($("#date_time").val().trim() == "") {
			alert("Please Enter date And time.");
			$("input#date_time").focus();
			return false;
		}
	
		if ($("#batch").val().trim() == "") {
			alert("Please Enter Batch.");
			$("input#batch").focus();
			return false;
		}
		return true;
	}
		
</Script>

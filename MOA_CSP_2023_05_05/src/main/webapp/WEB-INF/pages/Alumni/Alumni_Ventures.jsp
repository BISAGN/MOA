<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/watermark/common.js"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>Create Post</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Create Post</li>
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
					<%-- 					<form:form name="System" id="System" action="SystemAction" --%>
					<%-- 						method="post" class="form-horizontal" modelAttribute="SystemCMD"> --%>
					<div class="card-style mb-30">
						<h6 class="mb-25">Create Post</h6>
						<div class="row">

							<input type="hidden" id="actiontype" name="actiontype"
								value="add"> <input type="hidden" id="id" name="id"
								value="0">

							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
										<label>Categories<strong class="mandatory">*</strong></label>
										<div class="select-position">
											<select name="feed_cat" id="feed_cat" >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getFeedCategoryList}"
 													varStatus="num"> 
													<option value="${item.id}" name="${item.id}">${item.feed_category}</option> 
 												</c:forEach> 
											</select>
										</div>
									</div>
									</div>			

							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
									<label>Title<span class="mandatory">*</span></label> <input
										type="text" id="title" name="title"
										class="autocomplete UpperClassName txt-transupp"
										autocomplete="off" maxlength="150" placeholder="Title" />

								</div>
								<!-- end input -->
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-1">
									<label for="text-input">Description<strong
										class="mandatory">* </strong></label>
									<textarea id="description" name="description" rows="5"
										cols="50" autocomplete="off" 
										placeholder="Description"></textarea>


								</div>
							</div>

							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
									<label for="text-input">Upload Image<strong
										class="mandatory">*</strong></label> <input type="file"
										accept="image/*" id="upload_img" class="form-control"
										name="upload_img" accept=".jpg" class="form-control">
									<a href='#' id="viewimage" name="viewimage" >Upload Image</a>

								</div>
								<!-- end input -->
							</div>

<!-- 							<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
<!-- 								<div class="input-style-2"> -->
<!-- 									<label for="text-input">Post Date<span -->
<!-- 										class="mandatory">*</span></label> <input type="text" name="post_date" -->
<!-- 										id="post_date" maxlength="10" -->
<!-- 										class="form-control-sm form-control" aria-required="true" -->
<!-- 										autocomplete="off" style="color: rgb(0, 0, 0);" -->
<!-- 										value="DD/MM/YYYY" placeholder="Select Post Date"> -->
<!-- 								</div> -->
<!-- 							</div> -->

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="input-style-2">
									<label for="text-input">Batch<span class="mandatory">*</span></label>
									<input type="number" name="batch" id="batch" maxlength="4"
										class="form-control-sm form-control" 
										placeholder="YYYY" >
								</div>
							</div>

						</div>

						<ul class="buttons-group mainbtn">

							<li><a id="btn-reload"
								class="main-btn secondary-btn btn-hover btn-iconic-icon"
								type="button"><i class="lni lni-search-alt"></i>Search</a></li>
							<li><input id="save_id" class="main-btn info-btn btn-hover"
								type="button" value="Save" 
								name="save_id"/>
<!-- 								onclick="return SaveAlumVenturesData();" -->
							</li>
							<li><a href="Alumni_VenturesUrl" id="reset"
								class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
						</ul>
					</div>
					<!-- end card -->
					<%-- 					</form:form> --%>
				</div>
			</div>
		</div>
<div class="row">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="AlumniVenturestbl">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th><h6>Feed Categories</h6></th>
									<th><h6>Title</h6></th>
									<th><h6>Description</h6></th>
									<th><h6>Image</h6></th>
									<th><h6>Post Date</h6></th>
									<th><h6>Batch</h6></th>
									<th><h6>Edit|Delete</h6></th>
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



<script nonce="${cspNonce}" type="text/javascript">

$.ajaxSetup({
	async: false
});

	$(document).ready(function() {
		$("#viewimage").hide();
	//	datepicketDate('post_date');
		
	//$( "#batch" ).datepicker({dateFormat: 'yy'});
	
	
		mockjax1('AlumniVenturestbl');
		table = dataTable('AlumniVenturestbl');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

	});
	

	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('save_id').onclick = function() {
				return SaveAlumVenturesData();
			};
		document.getElementById('reset').onclick = function() {
			return ResetInput();
		};
		
	});

	
	function SaveAlumVenturesData() {

		var res = CheckNullorBlank('title');
		if (res !== "true") {
			alert(res + " Title");
			$('#title').focus();
			return false;
		}

		var res = CheckNullorBlank('description');
		if (res !== "true") {
			alert(res + " Description");
			$('#description').focus();
			return false;
		}

		var res = CheckNullorBlank('upload_img');
		if (res !== "true") {
			alert(res + " Upload image");
			$('#upload_img').focus();
			return false;
		}

// 		var res = CheckNullorBlank('post_date');
// 		if (res !== "true") {
// 			alert(res + " Post Date");
// 			$('#post_date').focus();
// 			return false;
// 		}

		var res = CheckNullorBlank('batch');
		if (res !== "true") {
			alert(res + " Batch");
			$('#batch').focus();
			return false;
		}

		var jsondata = {
			//"comtype": comtype,
			
			"feed_cat" : $('#feed_cat').val(),
			"title" : $('#title').val().trim(),
			"description" : $('#description').val().trim(),
			"upload_img" : $('#upload_img').val().trim(),
// 			"post_date" : $('#post_date').val().trim(),
			"batch" : $('#batch').val().trim(),

			"actiontype" : document.getElementById("actiontype").value,
			"id" : document.getElementById("id").value
		}
		
		var file_data = $('#upload_img').prop('files')[0];

		var stringifyjosn = JSON.stringify(jsondata);
		var formData = new FormData();
		formData.append('image', file_data);
		formData.append('jsondata', stringifyjosn);
		$.ajax({
			url : 'SaveAlumVenturesData?' +key+"="+value,
			type : "POST",
			data : formData,
			contentType: false,
			processData: false,
			//enctype : 'multipart/form-data',
			//contentType : 'application/json',
			cors : true,
			dataType : 'json',

		}).done(function(data) {

			if (data.status == '1') {
				alert(data.message);
				
				table.ajax.reload();
			} else {
				alert(data.message);
			}
		}).fail(function(jqXHR, textStatus) {
			alert(jqXHR.responseText);

		});

	}
	
	
	function dataTable(tableName) {
		var table = $('#' + tableName).DataTable({
			"order": [[0, "asc"]],
			//		"lengthMenu": [[10, 25, 50, 100, 200, -1], [10, 25, 50, 100, 200, "All"]],
			"lengthMenu": [[5, 10, 25, 50, 100, -1], [5, 10, 25, 50, 100, "All"]],
			"scrollY": "400px",
			"scrollX": true,
			"scrollCollapse": true,
			"sPaginationType": "full_numbers",
			"bLengthChange": true,
			'language': {
				'loadingRecords': '&nbsp;',
				'processing': '<div class="spinner"></div>'
			},
			ajax: '/test1',
			'processing': true,
			"serverSide": true
		});
		return table;
	}
	function mockjax1(tableName) {

		$.mockjax({
			url: '/test1',
			responseTime: 1000,
			response: function(settings) {
				$.ajaxSetup({
					async: false
				});
				data(tableName);
				this.responseText = {
					draw: settings.data.draw,
					data: jsondata,
					recordsTotal: jsondata.length,
					recordsFiltered: FilteredRecords
				};


			}
		});
	}

	function data(tableName) {

		var table = $('#' + tableName).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = '3';
		var orderColunm = order[0][0] + 1;
		//var orderColunm = "d.id"
		var orderType = order[0][1];
		var feed_cat =$("#feed_cat").val();
        var title=$("#title").val();
        var post_date=$("#post_date").val();
        var batch=$("#batch").val();
        
        
		
		jsondata = [];

		var jsondata1 = {

			"startPage" : startPage,
			"pageLength" : pageLength,
			"Search" : Search,
			"orderColunm" : orderColunm,
			"orderType" : orderType,
			"feed_cat":feed_cat,
			"title":title,
			"post_date":post_date,
			"batch":batch
			
		}
		
		$.ajax({
			url : 'LoadAlumVenturesData?' +key+"="+value,
			type : "POST",
			contentType : 'application/json',
			dataType : 'json',
			data : JSON.stringify(jsondata1)

		}).done(
				function(data) {
					if (data.status == '1') {

						var length = Object.keys(data.alumVentureslist).length;

						for (var i = 0; i < length; i++) {
							var statusData = data.alumVentureslist[i];
							jsondata.push([ statusData.srno, 
									statusData.feed_cat,
									statusData.title,
									statusData.description,
									statusData.Ventuimage,
									statusData.postdate,
									statusData.batch,
									statusData.action
							]);
							FilteredRecords = data.TotalCount;
						}
							setTimeout(setevents, 1000);

					} else {
					  alert(data.message);

					}

				}).fail(function(jqXHR, textStatus) {
			
					alert(jqXHR.responseText);

		});

	}
	
	function setevents() {
		
		document.querySelectorAll('.edtcls').forEach((items, index) => {
	
			items.addEventListener('click', event => {
				var val = parseInt(index) + 1;
				var hid = document.getElementById('hid' + val).value;

				 return updateAlumVentureData(hid);
			});
		});
		
		document.querySelectorAll('.delcls').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val = parseInt(index) + 1;
				var hid = document.getElementById('hid' + val).value;
				
					return DeleteAlumVentureData(hid);
				});
			});
		
	}
	
	function updateAlumVentureData(hid) {

		var jsondata = {
			"ventid": hid
		}
		
		$
			.ajax(
				{
					url: '/admin/GetAlumVenturesDataForUpdate?' +key+"="+value,
					type: "POST",
					data: JSON
						.stringify(jsondata),
					contentType: 'application/json',
					cors: true,
					dataType: 'json',

				})
			.done(
				function(data) {
					
					if (data.status == '1') {
						var feed_cat = data.feed_cat;
						var title = data.title;
						var description = data.description;
// 						var post_date = data.post_date;
						var batch = data.batch;
					
						$('#feed_cat').val(feed_cat);
						$('#title').val(title);
						$('#description').val(description);
// 						$('#post_date').val(post_date);
						$('#batch').val(batch);
						$('#id').val(data.vent_id);
						
						document.getElementById("actiontype").value = "Edit";
						$("#viewimage").show();
						document.getElementById('viewimage').onclick =
							function() {
								return ViewImage(data.imagedata);
							};

					} else {
						alert(data.message);

					}
				})
			.fail(function(jqXHR, textStatus) {
			
				alert(jqXHR.responseText);
			});
	}
	
	function DeleteAlumVentureData(hid) {
	
		var jsondata = {
			"ventid": hid
		}

		$
			.ajax(
				{
					url: '/admin/DeleteAlumVenturesData?' +key+"="+value,
					type: "POST",
					data: JSON
						.stringify(jsondata),
					contentType: 'application/json',
					cors: true,
					dataType: 'json',

				})
			.done(
				function(data) {
					
					if (data.status == '1') {
						
						alert(data.message);
					} else {
						alert(data.message);
					}
				})
			.fail(function(jqXHR, textStatus) {
				
				alert(jqXHR.responseText);

			});
	}
	
	function ViewImage(base64) {
		//alert(base64);
		var image = new Image();
		image.src = "data:image/jpg;base64," + base64;

		var w = window.open("");
		w.document.write(image.outerHTML);
	}
</Script>

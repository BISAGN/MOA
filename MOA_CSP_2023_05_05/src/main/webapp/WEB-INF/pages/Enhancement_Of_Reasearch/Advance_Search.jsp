<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd"></span>Any Research
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Any Research</li>
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
					<form:form name="advanced_search" id="advanced_search"
						action="Advanced_SearchAction" method='POST'
						modelAttribute="Advanced_SearchCMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Any Research</h6>
								<div class="row">

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Medicine System<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="medicine_system" id="medicine_system">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getsysList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
											<div class="input-style-1">
												<input type="hidden" id="id" name="id" class="mt-3"
													value="0" autocomplete="off">
											</div>
										</div>
										<!-- end select -->
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Category<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="category" id="category">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCategorylist}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>
                                   <div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Institute Name<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="institute_name" id="institute_name">
													<option value="0">--Select--</option>
<%-- 													<c:forEach var="item" items="${getInstituteList}" --%>
<%-- 														varStatus="num"> --%>
<%-- 														<option value="${item.id}" name="${item.institute_name}">${item.institute_name}</option> --%>
<%-- 													</c:forEach> --%>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>

<!-- 									<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
<!-- 										<div class="select-style-1"> -->
<!-- 											<label for="username">Search Field<span -->
<!-- 												class="mandatory">*</span></label> -->
<!-- 											<div class="select-position"> -->
<!-- 												<select class="singleselect form-control form-control-lg" -->
<!-- 													name="search_field" id="search_field"> -->
<!-- 													<option value="0">--Select--</option> -->
<%-- 													<c:forEach var="item" items="${getSearchFieldList}" --%>
<%-- 														varStatus="num"> --%>
<%-- 														<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 													</c:forEach> --%>
<!-- 												</select> -->
<!-- 											</div> -->
<!-- 											<div class="input-style-1"> -->
<!-- 												<input type="hidden" id="id" name="id" value="0" -->
<!-- 													class="mt-3" autocomplete="off" /> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										end select -->
<!-- 									</div> -->
                                 <div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Author Name<span class="mandatory">*</span>
											</label> <input id="author_name"
												name="author_name" maxlength="100"
												autocomplete="off"
												placeholder="Please Enter Author Name">
										</div>
										<!-- end select -->
									</div>
									 <div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Journal Name<span class="mandatory">*</span>
											</label> <input id="journal_name"
												name="journal_name" maxlength="100"
												autocomplete="off"
												placeholder="Please Enter Journal Name">
										</div>
										<!-- end select -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Title<span class="mandatory">*</span>
											</label> <input id="title"
												name="title" maxlength="200"
												autocomplete="off"
												placeholder="Please Enter Title">
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Hyperlink<span
												class="mandatory">*</span></label> <input id="hyperlink"
												name="hyperlink" autocomplete="off"
												placeholder="Please Enter Hyperlink" maxlength="200">
										</div>
										<!-- end select -->
									</div>
	                             <div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Upload Paper<span class="mandatory">*</span></label>
												<input type="file" id="upload_paper" class="form-control"
												name="upload_paper" accept=".pdf" class="form-control">
											<input type="hidden" id="upload_paper_hid"
												name="upload_paper_hid" class="form-control">
												
										</div>
										<!-- end select -->
									</div>
                                   <div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="status" id="status">
<!-- 													<option value="0">--Select--</option> -->
														<c:forEach var="item" items="${ActiveInActiveList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
											<div class="input-style-1">
												<input type="hidden" id="id" name="id" class="mt-3"
													value="0" autocomplete="off">
											</div>
										</div>
										<!-- end select -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Abstract<span
												class="mandatory">*</span></label> 
												<textarea id="abstract_content"
												name="abstract_content" autocomplete="off"
												placeholder="Please Enter Abstract" cols="45" rows="4" maxlength="250"></textarea>
										</div>
										<!-- end select -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Content<span
												class="mandatory">*</span></label> 
												<textarea id="desc_content"
												name="desc_content" autocomplete="off"
												placeholder="Please Enter Content" cols="45" rows="4" maxlength="250"></textarea>
										</div>
										<!-- end select -->
									</div>

								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
										<li  id="btn-reload"><a
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button" value="Search"><i
													class="lni lni-search-alt"></i>Search</a></li>
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="submit"
												value="Save" /></li>
											<li><a href="Advance_Search_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a>
											</li>
										</ul>
									</div>
								</div>
							</div>

						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
			
				<section class="single-detail-block">
				<div class="row">
					<div class="col-12 col-sm-12 col-md-12 col-lg-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="getSearch_Advance">
									<thead>
										<tr>
											<th><h6>Sr No</h6></th>
											<th><h6>Medicine System</h6></th>
											<th><h6>Category</h6></th>
											<th><h6>institute_name</h6></th>	
											<th><h6>Author Name</h6></th>
											<th><h6>Journal Name</h6></th>
											<th><h6>Title</h6></th>
											<th><h6>Hyperlink</h6></th>
											<th><h6>Abstract</h6></th>
											<th><h6>Content</h6></th>
											<th class="action"><h6>Action</h6></th>
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

<c:url value="delete_Advanced_SearchUrl" var="delete_Advanced_SearchUrl" />
<form:form action="${delete_Advanced_SearchUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>


<c:url value="Edit_Advanced_SearchUrl" var="Edit_Advanced_SearchUrl" />
<form:form action="${Edit_Advanced_SearchUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>
<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(
			function() {

				if (window.location.href.includes("msg")) {
					var url = window.location.href.split("?msg")[0];
					window.location = url;
				}
				
				mockjax1('getSearch_Advance');
				table = dataTable('getSearch_Advance');
				$('#btn-reload').on('click', function() {
					table.ajax.reload();
				});
			});
function getInstituteBy_system() {
	
		var medicine_system = $("#medicine_system").val();
		$
				.post(
						"getInstituteBy_systemList?" + key + "=" + value,
						{
							medicine_system : medicine_system
						},
						function(j) {
							if(j.length>0){
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#institute_name").html(options);
							}
						});
	}
	function data(getSearch_Advance) {
		jsondata = [];
		var table = $('#' + getSearch_Advance).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
		var orderType = order[0][1];
		
		var medicine_system = $("#medicine_system").val();
		var category = $("#category").val();
		var institute_name = $("#institute_name").val();
		var author_name = $("#author_name").val();
		var journal_name = $("#journal_name").val();
// 		var search_field = $("#search_field").val();
		var title = $("#title").val();
		var hyperlink = $("#hyperlink").val();
		var abstract_content = $("#abstract_content").val();
		var desc_content = $("#desc_content").val();
		var status = $("#status").val();
		

		$.post("getFilterSearch_Advance_Enhance_Research_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength: pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			
			medicine_system:medicine_system,
			category:category,
			institute_name:institute_name,
			author_name:author_name,
			journal_name:journal_name,
// 			search_field:search_field,
			title:title,
			hyperlink:hyperlink,
			abstract_content:abstract_content,
			desc_content:desc_content,
			status:status
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				
				jsondata.push([ j[i].ser,j[i].system_name,j[i].category_name,j[i].name,j[i].author_name,
					j[i].journal_name,j[i].title,j[i].hyperlink,j[i].abstract_content,j[i].desc_content,j[i].action ]);
			}
		});
		$.post("getTotalSearch_Advance_Enhance_ResearchCount?" + key + "=" + value, {
			Search : Search,
			medicine_system:medicine_system,
			category:category,
			institute_name:institute_name,
			author_name:author_name,
			journal_name:journal_name,
// 			search_field:search_field,
			title:title,
			hyperlink:hyperlink,
			abstract_content:abstract_content,
			desc_content:desc_content,
			status:status
			
		}, function(j) {
			FilteredRecords = j;
			});
		
		setTimeout(setTimeLoadForTable, 1000);
	}
	function setTimeLoadForTable(){
// 		document.querySelectorAll('.VIEWdetails').forEach((items, index) => {
// 			items.addEventListener('click', event => {
// 				debugger;
// 				var val=parseInt(index)+1;
// 				var hid = document.getElementById('viewId'+val).value;
// 				if (confirm('Are You Sure You Want to Show Detail ?')) {
// 					Pop_Up_Stu_Reg(hid);
// 				} else {
// 					return false;
// 				}
// 			})
// 		});


		document.querySelectorAll('.DeleteAdvanceSearch_Reasearch').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var gdid = document.getElementById('DELETE_AD_SEARCH'+val).value;
				
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(gdid);
				} else {
					return false;
				}
			})
		});
		document.querySelectorAll('.Edit_Advance_Reasearch').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
				
				var gid = document.getElementById('EDIT_ADVANCE_Id'+val).value;
 				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(gid);
				} else {
					return false;
				}
			})
		});
	}
	
	document.addEventListener('DOMContentLoaded',function() {
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		document.getElementById('medicine_system').onchange = function() {
			return getInstituteBy_system();
		};
	});

	
	
	function Validation() {
		$.ajaxSetup({
		    async: false
		});
		if ($("select#medicine_system").val() == 0) {
			alert("Please Select Medicine System");
			$("select#medicine_system").focus();
			return false;
	    }
		if ($("select#category").val() == 0) {
			alert("Please Select Category");
			$("select#category").focus();
			return false;
	    }
		if ($("select#institute_name").val() == 0) {
			alert("Please Select Institute Name");
			$("select#institute_name").focus();
			return false;
	    }
// 		if ($("select#search_field").val() == 0) {
// 			alert("Please Select Search Field");
// 			$("select#search_field").focus();
// 			return false;
// 	    }
		if ($("input#author_name").val().trim() == "") {
			alert("Please Enter Author Name");
			$("input#author_name").focus();
			return false;
		}
		if ($("input#journal_name").val().trim() == "") {
			alert("Please Enter Journal Name");
			$("input#journal_name").focus();
			return false;
		}
		if ($("input#title").val().trim() == "") {
			alert("Please Enter Title");
			$("input#title").focus();
			return false;
		}
		if ($("input#hyperlink").val().trim() == "") {
			alert("Please Enter Hyperlink");
			$("input#hyperlink").focus();
			return false;
		}
		if ($("select#status").val() == "0") {
			alert("Please Select Status.");
			$("select#status").focus();
			return false;
		}
		if ($("select#status").val() == "2") {
			alert("Only Select Active Status.");
			$("select#status").focus();
			return false;
		}
		if ($("textarea#abstract_content").val().trim() == "") {
			alert("Please Enter Abstract Content");
			$("textarea#abstract_content").focus();
			return false;
		}
		if ($("textarea#desc_content").val().trim() == "") {
			alert("Please Enter Content");
			$("textarea#desc_content").focus();
			return false;
		}
	
		return true;
	}
	
	function editData(id) {

		$("#id2").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}

</script>

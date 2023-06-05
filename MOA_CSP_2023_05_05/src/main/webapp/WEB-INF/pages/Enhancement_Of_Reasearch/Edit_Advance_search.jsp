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
							<span id="lbladd"></span>Update Any Research
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page"> Update Any Research</li>
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
					<form:form name="edit_advanced_search" id="edit_advanced_search"
						action="EditAdvanced_SearchAction" method='POST'
						modelAttribute="EditAdvanced_SearchCMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Update Any Research</h6>
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
<!--                                    	<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
<!-- 										<div class="select-style-1"> -->
<!-- 											<label for="text-input">Upload Paper<span class="mandatory">*</span></label> -->
<!-- 												<input type="file" -->
<!-- 												 id="upload_paper" class="form-control" -->
<!-- 												name="upload_paper" accept=".pdf" class="form-control"> -->
<!-- 											<input type="hidden" id="upload_paper_hid" -->
<!-- 												name="upload_paper_hid" class="form-control"> -->
												
<!-- 										</div> -->
<!-- 										end select -->
<!-- 									</div> -->
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
											<li><input id="update"
												class="main-btn info-btn btn-hover btnsave" type="submit"
												value="Update" /></li>
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
		
		</div>
	</div>
</section>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(
			function() {
				$('#id').val('${Advance_Search_Details.id}');
				$('select#medicine_system').val('${Advance_Search_Details.medicine_system}');
				$('#medicine_system').trigger('change');
				getInstituteBy_system();
				$('select#category').val('${Advance_Search_Details.category}');
				$('#category').trigger('change');
// 				$('select#search_field').val('${Advance_Search_Details.search_field}');
// 				$('#search_field').trigger('change');
//                 getInstituteBy_system();
                $('select#institute_name').val('${Advance_Search_Details.institute_name}');
				$('#institute_name').trigger('change');
				$('#author_name').val('${Advance_Search_Details.author_name}');
				$('#journal_name').val('${Advance_Search_Details.journal_name}');
				$('#title').val('${Advance_Search_Details.title}');
				$('#hyperlink').val('${Advance_Search_Details.hyperlink}');
				$('#abstract_content').val('${Advance_Search_Details.abstract_content}');
				$('#desc_content').val('${Advance_Search_Details.desc_content}');
				$('select#status').val('${Advance_Search_Details.status}');
				$('#status').trigger('change');
				
				$('.UpperClassName').keyup(function() {
					this.value = this.value.toUpperCase();
				});
				if (window.location.href.includes("msg")) {
					var url = window.location.href.split("?msg")[0];
					window.location = url;
				}
				
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
	function setTimeLoadForTable(){
	}
	
	document.addEventListener('DOMContentLoaded',function() {
			document.getElementById('update').onclick = function() {
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
	
	

</script>

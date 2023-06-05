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
						<h2>Active/Inactive Course Publisher Staff</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Active/Inactive
									Course Publisher Staff</li>
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
					<form:form name="course_publi_staff" id="course_publi_staff"
						action="course_publi_staffAction" method="post"
						modelAttribute="course_publi_staffCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Active/Inactive Course Publisher Staff</h6>
								<div class="row">
									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="input-style-1">
											<label>Course Publisher Staff</label> <input type="text"
												id="name" name="name" autocomplete="off" maxlength="50"
												class="form-control autocomplete UpperClassName txt-transupp"
												placeholder="Course Publisher Staff" />
										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-lg-4 col-md-4 col-sm-12">
										<div class="select-style-1">
											<label>Status</label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="status" id="status">
													<c:forEach var="item" items="${ActiveInActiveList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>

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
											<li><a type="button" id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch">
													<i class="lni lni-search-alt"></i>Search
											</a></li>
											<!-- 										<li><input type="submit" id="btn-save" id="btn-save" class="main-btn info-btn btn-hover" value="Save"></li> -->
											<li><a href="course_publisher_StaffUrl"
												class="main-btn dark-btn btn-hover btnreset">Reset</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<!-- end card -->
						<section class="single-detail-block">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="card-style mb-30">
										<div class="table-wrapper table-responsive custom-datatable-p"
											id="container-table">
											<table class="table" id="course_publisher_staff_Search">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th id="${item.id}"><h6>Name</h6></th>
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
					</form:form>
				</div>
			</div>
		</div>


	</div>
</section>

<%-- <c:url value="Edit_course_publisherUrl" var="Edit_course_publisherUrl" />
<form:form action="${Edit_course_publisherUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form> --%>

<c:url value="ActiveUserData_Staff" var="activeUrl" />
<form:form action="${activeUrl}" method="post" id="ActiveForm"
	name="ActiveForm" modelAttribute="">
	<input type="hidden" name="id51" id="id51" value="0" />
</form:form>

<c:url value="InactiveUserData_Staff" var="inactiveUrl" />
<form:form action="${inactiveUrl}" method="post" id="InactiveForm"
	name="InactiveForm" modelAttribute="">
	<input type="hidden" name="id61" id="id61" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('course_publisher_staff_Search');
		table = dataTable('course_publisher_staff_Search');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});

// 		if ('${msg}' != "") {
// 			alert('${msg}');
// 		}

    if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}

	});
	
function setTimeLoadForTable(){
		
		document.getElementById('btn-reload').onclick = function() {
			//return Validation();
		};
	
		document.getElementById('name').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};

		/* document.querySelectorAll('.ADDCoursepublisher').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var cpid = document.getElementById('Course_publisherId'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(cpid);
				} else {
					return false;
				}
			})
		}); */
		
		document.querySelectorAll('.ActiveOnclick1').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('ActiveID'+val).value;
				
				if (confirm('Are You Sure You Want to Active User ?')) {
					ActiveUserData1(hid);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.InactiveOnclick1').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('InactiveID'+val).value;
				
				if (confirm('Are You Sure You Want to Inactive User ?')) {
					InactiveUserData1(hid);
				} else {
					return false;
				}
			})
		});
		
	}

	function data(course_publisher_staff_Search) {
		jsondata = [];
		var table = $('#' + course_publisher_staff_Search).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var name = $("#name").val();
		var status = $("#status").val();

		$.post("getFiltercourse_publi_Staff_Act_Inact_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			name : name,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].name, j[i].action ]);
			}
		});
		$.post("getTotalcourse_publi_Staff_Act_Inact_dataCount?" + key + "=" + value, {
			Search : Search,
			name : name,
			status : status
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	/* function editData(id) {

		$("#id2").val(id);
		document.getElementById('updateForm').submit();
	} */

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}

// 	function Validation() {

// 		if ($("#name").val().trim() == "") {
// 			alert("Please Enter Course Publisher Staff Name.");
// 			$("input#name").focus();
// 			return false;
// 		}
// 		if ($("select#status").val() == "2") {
// 			alert("Only Select Active Status.");
// 			$("select#status").focus();
// 			return false;
// 		}
// 		return true;
// 	}
	
	function ActiveUserData1(user_id) {
		$("#id51").val(user_id);
		document.getElementById('ActiveForm').submit();
// 		$.post("ActiveUserData?" + key + "=" + value,
// 				{
			
// 				}
	}
	
	
	function InactiveUserData1(user_id) {
		
		$("#id61").val(user_id);
		document.getElementById('InactiveForm').submit();
// 		$.post("ActiveUserData?" + key + "=" + value,
// 				{
			
// 				}
	}


</Script>

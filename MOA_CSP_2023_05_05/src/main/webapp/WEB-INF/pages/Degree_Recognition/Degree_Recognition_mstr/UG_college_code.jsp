<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- <script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> -->
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
						<h2>College Code Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">college Code Master</li>
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
					<form:form name="collegeform" id="collegeform" action="CollegeAction_mstr"
						method="post" class="form-horizontal" modelAttribute="CollegeCMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">College CodeMaster</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>name of college<span class="mandatory">*</span></label> <input
											type="text" id="name_of_college" name="name_of_college"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="300" placeholder="name of college" />
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>state<span class="mandatory">*</span></label> <input
											type="text" id="state" name="state"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="300" placeholder="state" />
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-2">
										<label>college id <span class="mandatory">*</span></label> <input
											type="text" id="college_id" name="college_id"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="300" placeholder="college id" />
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>
									<!-- end input -->
								</div>

								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Status<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="status" id="status" class="singleselect form-control form-control-lg">
												<c:forEach var="item" items="${ActiveInActiveList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="input-style-2 mt-3">
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
									</div>

									<!-- end select -->
								</div>

							</div>

							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save" />
								</li>
								<li><a href="college_code_url"
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
						<table class="table" id="search_college_code">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th id="${item.id}"><h6>Name of College</h6></th>
									<th id="${item.id}"><h6>State</h6></th>
									<th id="${item.id}"><h6>College id </h6></th>
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

<script nonce="${cspNonce}" type="text/javascript">
	
	$(document).ready(function() {

		mockjax1('search_college_code');
		table = dataTable('search_college_code');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
	});
	
document.addEventListener('DOMContentLoaded', function () {	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	
	document.getElementById('term_id').onkeypress = function() {
		return onlyAlphabetsStringSpace(this, event);
	};
	document.getElementById('setname').onkeypress = function() {
		return onlyAlphabetsStringSpace(event,this);		
	};
	});

	function setTimeLoadForTable(){
		
		document.querySelectorAll('.ADDcollege_code').forEach((items, index) => {
			debugger;
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var sid = document.getElementById('SetId'+val).value;
 				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(sid);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.EditSet').forEach((items, index) => {
			items.addEventListener('click', event => {
				var val=parseInt(index)+1;
			
				var editid = document.getElementById('EditId'+val).value;
				if (confirm('Are You Sure You Want to Edit Detail?')) {
					editData(editid);
				} else {
					return false;
				}
			})
		});
		
		document.querySelectorAll('.deleteSet').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var sdid = document.getElementById('DSetId'+val).value;
				if (confirm('Are You Sure You Want to Delete Detail ?')) {
					deleteData(sdid);
				} else {
					return false;
				}
			})
		});
		
	}
	
	function data(search_college_code) {
		 jsondata = [];
			var table = $('#' + search_college_code).DataTable();
			var info = table.page.info();
			var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
			var orderType = order[0][1];
			
			var name_of_college = $("#name_of_college").val();
			var state = $("#state").val();
			var college_id = $("#college_id").val();

		$.post("getFiltercollege_code?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			name_of_college:name_of_college,
			state:state,
			college_id:college_id,
			status:status
			
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser, j[i].name_of_college, j[i].state,j[i].college_id,j[i].action ]);
			}
		});
		$.post("getFilter_Collegecode_ListCount?" + key + "=" + value, {
			Search : Search,
			name_of_college:name_of_college,
			state:state,
			college_id:college_id,
			status:status
		}, function(j) {
			
			FilteredRecords = j;

			});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
function editData(id) {
		debugger;
		$("#id1").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}

	function Validation(){
		
				if ($("#setname").val().trim() == "") {
					alert("Please Enter Set Name.");
					$("input#setname").focus();
					return false;
				}
			
				if ($("#term_id").val() == "0") {
					alert("Please Enter Profession Name.");
					$("#term_id").focus();
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
				return true;
		}

	</script>


<!-- </Script> -->

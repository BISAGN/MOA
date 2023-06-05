<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- <script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>	 -->
<script src="js/common/multicheck.js" type="text/javascript"></script>
<!-- <link rel="stylesheet" href="js/common/multicheck.css"> -->
<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->

<script nonce="${cspNonce}" type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
	$(document).ready(function () {
		$('#role').keyup(function() {
	    	this.value = this.value.toUpperCase();
	    }); 
		
// 		getsyslist();
		
	});
</script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>Link College System Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Link
									College System Master</li>
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
					<form:form name="roleMst" id="roleMst" action="Link_institute_System_Action" method='POST' modelAttribute="Link_institute_System_CMD">
						<div class="card-style mb-30">
							<h6 class="mb-25">Link College System Master</h6>
							<div class="row">

								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="select-style-1">
										<label for="text-input">College<span
											class="mandatory">*</span></label>
										<div class="select-position">
											<select name="institute_id" id="institute_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getInstituteList}" varStatus="num">
													<option value="${item.id}" name="${item.institute_name}">${item.institute_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- end select -->
								</div>
							</div>

							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="input-style-2 mb-0">
<!-- 										<label id="system_name">System (0)<span class="mandatory">*</span></label> -->
											<label><span id="system_name">System (0)</span><span class="mandatory">*</span></label>
											 <input type="text" id="search_data"  class=" " autocomplete="off" placeholder="Search System">
									</div>

									<div class="col-12 mb-30" id="checkboxes">
										<c:forEach var="item" items="${getsysList}" varStatus="num">
											<div class="form-check radio-style checkbox-style d-flex align-items-center">
												<input class="form-check-input mr-5" type="checkbox" name="multisub" value="${item[0]}"  />
												<label for="one" class="d-flex align-items-center">
													${item[1]} </label>
											</div>
										</c:forEach>
									</div>
									<!-- end input -->
								</div>


								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="input-style-2 mb-0">
										<label>Selected Systems<span class="mandatory">*</span></label>
										<input type="text" id="value" name="value" maxlength="70" />

									</div>
									<div class="badges-groups">
										<ul id="show_list" class="buttons-group">
										</ul>
									</div>
									<!-- end input -->
								</div>

							</div>

							<!-- <div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="form-check checkbox-style" id="checkboxes"></div>
									end input
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-6">
									<div class="form-check checkbox-style" id="submodulecheckboxes"></div>
									end input
								</div>
								<div class="row">
									<div class="col-12">
										<div id="screencheckboxes"></div>
										end input
									</div>
								</div>
							</div> -->

							<input type="hidden" name="old_system_name" id="old_system_name" />
							<input type="hidden" name="new_system_name" id="new_system_name" />
							<input type="hidden" name="add_system_name" id="add_system_name" />
							<input type="hidden" name="remove_system_name"
								id="remove_system_name" />

							<ul class="buttons-group mainbtn">
								<li><a id="btn-reload" class="main-btn secondary-btn btn-hover btn-iconic-icon"type="button">
								<i class="lni lni-search-alt"></i>Search</a></li>
								<li><input class="main-btn info-btn btn-hover" type="submit" value="Save" id="btn-save">
								</li>
								<li><a href="link_institute_system_registration_url"
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
						<table class="table" id="Link_Institute_System">
							<thead>
								<tr>
									<th><h6>Ser No</h6></th>
									<th><h6>College</h6></th>
									<th><h6>System</h6></th>
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
	$(document).ready(
			function() {
				mockjax1('Link_Institute_System');
				table = dataTable('Link_Institute_System');
				$('#btn-reload').on('click', function() {
					table.ajax.reload();
				});

				if ('${list.size()}' == "") {
					$("div#Link_Institute_System").hide();
				}

				initiateChkFn('new_system_name', 'old_system_name',
						'add_system_name', 'remove_system_name', 'System Name',
						'system_name');
			});
	
function setTimeLoadForTable(){
		
		document.getElementById('institute_id').onchange = function() {
			return InstituteChangeFn(this.value);
		};
	
		document.getElementById('search_data').onkeyup = function() {
			return fnFilterChk(this.value);
		};
		
		
		document.getElementById('btn-save').onclick = function() {
			return Validation();
		};
		
		document.getElementById('checkboxes').onclick = function() {
			 chkClick();
		};

	}

	function data(Link_Institute_System) {
		jsondata = [];
		var table = $('#' + Link_Institute_System).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var institute_id = $("#institute_id").val();

		$.post("getFilterLink_Institute_System_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			institute_id : institute_id

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ i + 1, j[i][0], j[i][1] ]);
			}
		});
		$.post("getTotalLink_Institute_System_dataCount?" + key + "=" + value,
				{
					institute_id : institute_id

				}, function(j) {
					FilteredRecords = j;
				});
		setTimeout(setTimeLoadForTable, 1000);
	}

	function InstituteChangeFn(institute_id) {
		$.ajaxSetup({
			async : false
		});
	
		$("input[type='checkbox'][name='multisub']").attr('checked', false);
		if (institute_id > 0) {
		
			$.post("getInstituteFromSystem?" + key + "=" + value, {
				institute_id : institute_id
			}, function(data) {
			}).done(function(data) {
				var v = data.length;

				if (v != 0) {
					setChk(data);
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		}

		chkClick();

	}
	
	function Validation() {

		if ($("select#institute_id").val() == 0) {
			alert("Please Select College");
			$("select#institute_id").focus();
			return false;
		}

		return true;

	}
</script>
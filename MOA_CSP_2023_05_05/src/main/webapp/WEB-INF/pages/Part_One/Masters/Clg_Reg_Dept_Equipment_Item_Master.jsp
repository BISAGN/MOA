<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
						<h2><span id="lbladd"></span> Hospital Department Master</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Hospital Department Master</li>
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
					<form:form name="ClgDeptEquipmentItem" id="ClgDeptEquipmentItem" action="ClgDeptEquipmentItemAction"
						method="post" modelAttribute="ClgDeptEquipmentItemCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25"> College Department Equipment Item Master</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="input-style-2">
											<label>Equipment Item Name<span class="mandatory">*</span></label> <input
												type="text" id="item_name" name="item_name"
												class="autocomplete UpperClassName txt-transupp"
												autocomplete="off" maxlength="100" placeholder="Hospital Department Name" />

										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Status<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="status" id="status"
													class="singleselect form-control form-control-lg">
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

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="hos_department_id">Equipment Item Name<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="dept_equip_item_id" id="dept_equip_item_id"
													class="singleselect form-control form-control-lg" id="hos_department_id">
														<option value="0" >--Select--</option>
														<option value="1" >Anatomy</option>
														<option value="2" >Community Medicine</option>
														<option value="3" >Forensic & Toxicology / Equipment</option>
														<option value="4" >Forensic & Toxicology / Acts / Legislative</option>
														<option value="5" >Homoeopathy Pharmacy</option>
														<option value="6" >Pathology & Microbiology</option>
														<option value="7" >Pathosiology & Biochemistry</option>
														<option value="8" >Biochemistry</option>
												</select>
											</div>
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

											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btn-save" type="submit"
												value="Save" /></li>
											<li><a href="ClgDeptEquipmentItemUrl"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
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
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="search_clg_dept_equipment">
							<thead>
								<tr>
									<th><h6>Sr No</h6></th>
									<th id="${item.id}"><h6>Item Name</h6></th>
									<th id="${item.id}"><h6>Equipment Item Id</h6></th>
									<th  id="${item.id}"><h6>Action</h6></th>
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

<c:url value="ClgDeptEquipmentItem_delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {

		mockjax1('search_clg_dept_equipment');
		table = dataTable('search_clg_dept_equipment');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
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



	});

	function setTimeLoadForTable(){


		document.querySelectorAll('.EditEquipmentItem').forEach((items, index) => {
			items.addEventListener('click', event => {

				var val=parseInt(index)+1;

				var hid = document.getElementById('apId'+val).value;
				var hequipid = document.getElementById('apequipid'+val).value;
				var hstatus = document.getElementById('apStatus'+val).value;
				var hdeptequip = document.getElementById('apdeptequipid'+val).value;
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hequipid,hdeptequip,hstatus,);
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

	function data(search_clg_dept_equipment) {

		jsondata = [];
		var table = $('#' + search_clg_dept_equipment).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var item_name = $("#item_name").val()
		var dept_equip_item_id = $("#dept_equip_item_id").val();
		var status = $("#status").val();

		 $.post("getFilterClgDeptEquipmentItem_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			item_name : item_name,
			status : status,
			dept_equip_item_id : dept_equip_item_id

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				console.log(j[i].action)
				jsondata.push([ j[i].ser, j[i].item_name,j[i].dept_equip_item_id,j[i].action ]);
			}
		});
		$.post("getTotalClgDeptEquipmentItem_dataCount?" + key + "=" + value, {
			Search : Search,
			item_name : item_name,
            			status : status,
            			dept_equip_item_id : dept_equip_item_id
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	function editData(id,item_name,dept_equip_item_id,status) {
		document.getElementById('lbladd').innerHTML = "Update ";
		document.getElementById('btn-save').value = "Update ";
		$("#item_name").val(item_name);
		$("select#dept_equip_item_id").val(dept_equip_item_id);
        $('#dept_equip_item_id').trigger('change');
		$("select#status").val(status);
		$('#status').trigger('change');
		document.getElementById('id').value=id;
	}

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}


	function Validation() {



		return true;
	}

</Script>

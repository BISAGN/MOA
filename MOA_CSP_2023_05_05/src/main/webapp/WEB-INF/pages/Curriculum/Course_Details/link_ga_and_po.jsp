<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/common/multicheck.js"></script>
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
							<span id="lbladd"></span> Link Graduate Attribute And Program
							Outcome
						</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Link
									Graduate Attribute And Program Outcome</li>
							</ol>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="linkmaster" id="linkmaster"
						action="link_ga_and_po_action" method="post"
						modelAttribute="link_ga_and_po_CMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Link Graduate Attribute And Program
									Outcome</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">System<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="system_id" id="system_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSystemList}"
														varStatus="num">
														<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="degree_id" id="degree_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									<!-- 								     TAKE REFERENCE FOR CHECKBOX-DD -->

									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Graduate Attribute<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="graduateattribute_id" id="graduateattribute_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Program Outcome <strong
												class="mandatory">* </strong></label>
											<div class="select-position">
												<select class="" id="div_iv_fluids" name="">
													<option value="0" id="programoutcome_id1"
														class="hida overSelect">--Select--</option>
												</select> <input type="hidden" id="programoutcome_id1"
													name="programoutcome_id" autocomplete="off"
													class="form-control-sm form-control" value="">
											</div>
											<div id="div_iv_fluids_2" class="multiselect">
												<div id="div_cb_dd"
													class="form-check radio-style checkbox-style align-items-center">
												</div>
											</div>
										</div>
										<input type="hidden" id="in_program_hid_ch"
											name="in_program_hid_ch" class="form-control autocomplete">
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="username">Status<span class="mandatory">*</span></label>
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
										<div class="input-style-1 mt-3">
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" /> <input type="hidden" id="Edit_ids"
												name="Edit_ids" value="0" autocomplete="off" />
										</div>
									</div>
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">

											<li id="btn-reload1"><a href="link_ga_and_po_url"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
													<i class="lni lni-chevron-left"></i>Back
											</a></li>

											<li id="btn-update"><input
												class="main-btn deactive-btn btn-hover btnupda"
												type="button" value="Update" /></li>
											<li id="btn-reload"><a
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>

											<li id="btn-save"><input class ="main-btn info-btn
													btn-hover btnsave" type="button" value="Save" /></li>

											<li id="btn-reload2"><a href="link_ga_and_po_url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>

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
				<div class="row" id="tbl">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="search_Ga_Po">
									<thead>
										<tr>
											<th id="1"><h6>Sr No</h6></th>
											<th id="4"><h6>System</h6></th>
											<th id="9"><h6>Degree</h6></th>
											<th id="10"><h6>Graduate Attribute</h6></th>
											<th><h6>View Program Outcome</h6></th>
											<th><h6>Action</h6></th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</section>
			<div class="modal fade custom-modal bd-example-modal-lg"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
				id="modelWindow" aria-hidden="true">
				<div class="modal-dialog modal-xl modal-dialog-scrollable">
					<div class="modal-content">
					<div class="modal-header">
							<h3 class="modal-title">Program Outcome</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div class="custom-modal-inner" id="headData1" name="headData1">
								<div class="table-wrapper table-responsive custom-table">
									<table class="table model-table" id="Pro_out_Data_Url">
										<thead>
											<tr>
												<th><h6>Sr No</h6></th>
												<th><h6>Program Outcome</h6></th>
											</tr>
										</thead>
										<tbody id="att_TbbodyNameMed">
										</tbody>
									</table>
								</div>
							</div>
							<div class="modal-footer">
								<ul class="buttons-group">
									<li><button type="button"
											class="main-btn dark-btn n btn-hover" data-bs-dismiss="modal"
											data-dismiss="modal" aria-label="Close">Close</button></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<c:url value="Ga_Po_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
	$(document).ready(function() {
		
		$("#btn-update").hide();
		$("#btn-reload1").hide();
		
		mockjax1('search_Ga_Po');
		table = dataTable('search_Ga_Po');
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
	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem();
		};
		document.getElementById('div_iv_fluids').onclick = function() {
			showCheckboxes(this);
		};
		document.getElementById('in_program_hid_ch').onclick = function() {
			mycheckindex1(this.value);
		};
		document.getElementById('degree_id').onchange = function() {
			getGAbyDegree();
			getPOlistbyDegree();
		};
	});

	function getGAbyDegree() {
		var degree_id = $("#degree_id").val();
		$
				.post('getGA_by_Degree?' + key + "=" + value, {
					degree_id : degree_id
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#graduateattribute_id").html(options);
						});
	}
	function getdegreelistbysystem() {
		var system_name = $("#system_id").val();
		$
				.post('getDegreeListbysystem1?' + key + "=" + value, {
					system_name : system_name
				})
				.done(
						function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_id").html(options);
						});
	}

	function getPOlistbyDegree() {
		var degree_id = $("#degree_id").val();
		$.post('getPO_listby_Degree?' + key + "=" + value, {
			degree_id : degree_id
				}).done(function(j) {
					$("div#div_cb_dd").empty();
							for(var p=0;p<j.length;p++){
								var q = p+1;
								$("div#div_cb_dd")
								.append(
									'<input class="multi form-check-input mr-5" type="checkbox" id="programOutcome_id'+q+'" name="programOutcome_id" value="'+j[p][0]+'"/>'
									+'<label class="lbl"  for="first">'+j[p][1]+'</label>');
								setonclickofCBDD(q);
							}
				});
	}

	var show = true;
	var temp;
	function showCheckboxes(obj) {

		var checkboxes = obj.id + "_2";
		var checkboxRead = checkboxes.substring(4, checkboxes.length);
		checkboxRead = checkboxRead.substring(0, checkboxRead.length - 2);
		var data_check = $("#" + checkboxRead).is('[readonly]');
		if (show && data_check == false) {
			$("#" + checkboxes).show();
			temp = checkboxes;
			show = false;
		} else {
			$("#" + checkboxes).hide();
			show = true;
		}
		window.addEventListener('mouseup', function(event) {
			var pol = document.getElementById(temp)
			if (event.target != pol
					&& event.target.parentNode.parentNode != pol) {
				pol.style.display = 'none';
			}
		});
	}
	
	function mycheckindex(myindex) {
		var gsida = [];
		var ele = document.getElementsByName("programOutcome_id");

		console.log("ele.length - " + ele.length);
		for (var i = 0; i < ele.length; i++) {
			if (ele[i].checked) {
				gsida.push(ele[i].value);
			}
		}
		console.log(myindex);
		document.getElementById('in_program_hid_ch').value = gsida
				.toString();
	}

	function setonclickofCBDD(obj){
		document.getElementById('programOutcome_id'+obj).onclick = function() {
			mycheckindex(obj);
		};
	}
	
	function data(search_Ga_Po) {
		jsondata = [];
		var table = $('#' + search_Ga_Po).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id')
				.toLowerCase();
		var orderType = order[0][1];
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		var graduateattribute_id = $("#graduateattribute_id").val();
		var programoutcome_id = $("#in_program_hid_ch").val();
		var status = $("#status").val();
		
		
		$.post("getFilterGa_Po_Data_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			system_id :system_id,
			degree_id : degree_id,
			graduateattribute_id : graduateattribute_id,
			programoutcome_id : programoutcome_id,
			status : status
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,j[i].system_name,j[i].degree_name, j[i].graduate_attributes, j[i].vd, j[i].action ]);
			}
		});
		$.post("getTotalGa_Po_Data_dataCount?" + key + "=" + value, {
			Search : Search,
			system_id :system_id,
			degree_id : degree_id,
			graduateattribute_id : graduateattribute_id,
			programoutcome_id : programoutcome_id,
			status : status
		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
function setTimeLoadForTable(){
	document.getElementById('btn-save').onclick = function() {
		if(Validation()){
			 $("#linkmaster").submit();
		}
	};

	document.getElementById('btn-update').onclick = function() {
		
		if(Validation()){
			 $("#linkmaster").submit();
			 return UpdateFn();
		}
		
	};
	
	document.querySelectorAll('.ADDGa').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('apId'+val).value;
			var hsys = document.getElementById('apsys'+val).value;
			var hdeg = document.getElementById('apd'+val).value;
			var hga = document.getElementById('apga'+val).value;
			var hpo = document.getElementById('appo'+val).value;
			var hstatus = document.getElementById('apstatus'+val).value;
			
			if (confirm('Are You Sure You Want to Edit Detail ?')) {
				editData(hid,hsys,hdeg,hga,hpo,hstatus);
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
	
	document.querySelectorAll('.VIEWdetails').forEach((items, index) => {
		items.addEventListener('click', event => {
			var val=parseInt(index)+1;
			var hid = document.getElementById('viewId'+val).value;
			if (confirm('Are You Sure You Want to Show Detail ?')) {
				get_Po_Datalist(hid);
			} else {
				return false;
			}
		})
	});
}
		
function editData(id,system_id,degree_id,graduateattribute_id,programoutcome_id,status) {
	
	$("#btn-update").show();
	$("#btn-save").hide();
	$("#tbl").hide();
	$("#btn-reload").hide();
	$("#btn-reload2").hide();
	$("#btn-reload1").show();
	
	document.getElementById('lbladd').innerHTML = "Update ";
	$("select#system_id").val(system_id);
	$('#system_id').trigger('change');
	getdegreelistbysystem();
	$("select#degree_id").val(degree_id);
	getGAbyDegree();
	$("select#graduateattribute_id").val(graduateattribute_id);
	$('#graduateattribute_id').trigger('change');
	checkoptioninedit(programoutcome_id);
	$("select#status").val(status);
	$("#Edit_ids").val(id);
	document.getElementById('id').value=id;
}

function checkoptioninedit(sids){
	
	var len = "";
	var degree_id = $("#degree_id").val();
	$.post('getPO_listby_Degree?' + key + "=" + value, {
		degree_id : degree_id
			}).done(function(j) {
				len = j.length;
				$("div#div_cb_dd").empty();
						for(var p=0;p<j.length;p++){
							var q = p+1;
							$("div#div_cb_dd")
							.append(
								'<input class="multi form-check-input mr-5" type="checkbox" id="programOutcome_id'+q+'" name="programOutcome_id" value="'+j[p][0]+'"/>'
								+'<label class="lbl"  for="first">'+j[p][1]+'</label>');
							setonclickofCBDD(q);
						}
			});
	
	var match = sids.split(":");
	for (var a in match)
	{
	    var variable = match[a];
	    	for(var i = 1;i<= len ; i++){
	    		
				var temp_data = $('#programOutcome_id' + i).val();
				if(variable.trim() == temp_data.trim()){
					$('#programOutcome_id' + i).click();
				}
	    	}
	}
}
		
function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}
		
function UpdateFn(){
	var system_id = $("#system_id").val();
	var degree_id = $("#degree_id").val();
	var graduateattribute_id = $("#graduateattribute_id").val();
	var sd_old = $("#Edit_ids").val();
	var sd_new = $("#in_program_hid_ch").val();
	
	$.post('update_Ga_Po_Action?' + key + "=" + value, {
		system_id : system_id,
		degree_id : degree_id,
		graduateattribute_id : graduateattribute_id,
		sd_old : sd_old,
		sd_new : sd_new
	}).done(function(j) {
				alert(j);
				window.location.reload();
	});
}
		
		function Validation() {
			if ($("#system_id").val().trim() == "0") {
				alert("Please Select System.");
				$("select#system_id").focus();
				return false;
			}
			if ($("#degree_id").val().trim() == "0") {
				alert("Please Select Degree.");
				$("select#degree_id").focus();
				return false;
			}
			if ($("#graduateattribute_id").val().trim() == "0") {
				alert("Please Select Graduate Attribute.");
				$("select#graduateattribute_id").focus();
				return false;
			}
			if ($("#in_program_hid_ch").val().trim() == "" || $("#in_program_hid_ch").val().trim() == "0") {
				alert("Please Select Program Outcome.");
				$("select#div_iv_fluids").focus();
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
		
		
		
		function get_Po_Datalist(hid) {
			$('tbody#att_TbbodyNameMed').empty();
			var poidar = hid.split(":");
			for(var p=0;p<poidar.length;p++){
				var poid = poidar[p];
				var ser = parseInt(p) + 1;

				$.post("Pro_out_Data_Url?" + key+ "=" + value,
						{ hid : poid },function(j) {
							for (var i = 0; i < j.length; i++) {
								$("tbody#att_TbbodyNameMed")
									.append(
										'<tr id="tr_id_attNameMed'+ser+'"><td class="min-width">'
												+ ser
												+ '</td>'
												+ '<td class="min-width">'
												+ j[i][0]
												+ '</td>'
												+ '</tr>');
							}
						});
			}
				  $('#modelWindow').modal('show');
		}
	</script>
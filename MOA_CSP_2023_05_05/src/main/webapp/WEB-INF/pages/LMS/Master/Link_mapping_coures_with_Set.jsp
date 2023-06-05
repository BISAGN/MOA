<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<!-- <script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script>	 -->
<script src="js/miso/commonJS/addmorefunctionality.js"></script>
<script src="js/common/multicheck.js"></script>	
<link rel="stylesheet" href="js/common/multicheck.css">
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
	$(document).ready(function() {
		$('#role').keyup(function() {
			this.value = this.value.toUpperCase();
		});

		$(".lbl").click(function() {
			$(".test").show();
		});
	});
</script>

<form:form name="linkmaster" id="linkmaster"
	action="Link_Coures_With_Set_mstr_Action" method='POST'
	modelAttribute="LcsmCMD">
	<div class="container">
		<div class="card">
			<div class="card-header">
				<h5>LINK ELECTIVE COURSE AND SET AND MODULE MASTER</h5>
			</div>
			<!-- end of card-header -->
			<div class="card-body card-block">
				<div class="row mb-6">

					<div class="col-md-3">
						<label for="text-input"> SYSTEM <strong
							style="color: red;">*</strong></label>
					</div>
					<div class="col-md-3">
						<select name="system_id" class="singleselect form-control form-control-lg" id="system_id"
							>
							<option value="0">--Select--</option>
							<c:forEach var="item" items="${getSystemList}" varStatus="num">
								<option value="${item.id}" name="${item.id}">${item.system_name}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-md-3">
						<label for="text-input"> COURSE <strong
							style="color: red;">*</strong></label>
					</div>
					<div class="col-md-3">
						<select name="course_id" class="singleselect form-control form-control-lg" id="course_id"
							>
							<option value="0">--Select--</option>
							<c:forEach var="item" items="${getcoursenameList}"
								varStatus="num">
								<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
							</c:forEach>
						</select>
					</div>

				</div>

				<div id="setdiv"></div>

				<table
					class="table no-margin table-striped  table-hover  table-bordered"
					id="table_cbc">
					<thead>
						<tr>
							<th align="center">SER NO</th>
							<th align="center">SET <span class="star_design"
								style="color: red;">*</span></th>
							<th align="center">MODULE <span class="star_design"
								style="color: red;">*</span></th>
							<th align="center">ACTION</th>
						</tr>
					</thead>
					<tbody id="tbody_cbc">
						<tr id="tr_id_cbc">
							<td align="center">1</td>

							<td align="center"><select id="set_id1" name="set_id1" 
								class="singleselect form-control form-control-lg" onclick="getset(this.value,1)"
								>
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getSetListALL}" varStatus="num">
										<option value="${item[0]}">${item[1]}</option>
									</c:forEach>
							</select></td>

							<td align="center"><div class="multipleSelection">
									<div class="selectBox" id="div_iv_fluids1"
										onclick="showCheckboxes(this)">
										<span id="module_id1" class="hida"><b>--Select--</b></span>
										<p class="multiSel"></p>
										<div class="overSelect"></div>
										<input type="hidden" id="module_idx1" name="module_idx1"
											autocomplete="off" class="form-control-sm form-control"
											value="">
									</div>
									<div id="div_iv_fluids1_2" class="test">
										<c:forEach var="item" items="${getModulnameList}"
											varStatus="num">
											<label class="lbl" value="module_id${num.index+1}"
												for="first"> <input class="multi" type="checkbox"
												id="in_module_id1${num.index+1}" name="in_module_id1"
												value="${item[0]}" onclick="mycheckindex(1)" /> ${item[1]}
											</label>
										</c:forEach>

									</div>

								</div></td>

							<td style="width: 10%;"><a class="btn btn-success btn-sm"
								value="ADD" title="ADD" id="id_add_cbc1"
								onclick="Getinput_tbody_cbc(1);"> <i class="fa fa-plus"></i></a>

							</td>
						</tr>
					</tbody>
				</table>


				<input type="hidden" id="checkboxindex1" name="checkboxindex1">
				<input type="hidden" id="in_module_id_hid_ch1"
					name="in_module_id_hid_ch1"> <input type="hidden"
					id="count_hidden_cbc" name="count_hidden_cbc"
					class="form-control autocomplete" value="1"> <input
					type="hidden" name="data_f" id="data_f" value="0" />

			</div>
			<!-- end of card -->
			<div class="card-footer">
				<a href="Link_Coures_With_Set_Url" class="btn btn-success btn-sm">Reset</a>
				<input type="submit" class="btn btn-primary btn-sm" value="Save"
					onclick="return Validate();">
			</div>
		</div>


		<div class="container-fluid" id="container-table">
			<table id="search_Lcwsc"
				class="display table no-margin table-striped  table-hover  table-bordered dataTable no-footer">
				<thead>
					<tr>
						<th align="center">SER NO</th>
						<th>SYSTEM</th>
						<th>COURSE</th>
						<th>SET</th>
						<th>MODULE</th>
					</tr>
				</thead>

				<tbody>

				</tbody>

			</table>
		</div>

		<!-- end of card-footer -->
	</div>
	<!-- end of container -->
</form:form>

<script nonce="${cspNonce}" type="text/javascript">
function setTimeLoadForTable(){
	document.getElementById('system_id').onchange = function() {
		return getcourseBYSystem(this);
	};
	document.getElementById('course_id').onchange = function() {
		return changecourseDetails();
	};

	function changecourseDetails() {

		$("#div_iv_fluids1_2").empty();

		var ct = $("#ser").val();

		var course_id = $("select#course_id").val();

		$
				.post(
						"getModuleData?" + key + "=" + value,
						{
							course_id : course_id
						},
						function(k) {

							for (var i = 0; i < k.length; i++) {
								var v1 = k[i][0];
								var v2 = k[i][1];

								$("#div_iv_fluids1_2")
										.append(
												"<label class='lbl' value='module_id"+i+"' for='first'> <input class='multi' type='checkbox' id='in_module_id1"
														+ i
														+ "' name='in_module_id1' value='"
														+ v1
														+ "' onclick='mycheckindex(1)'/> "
														+ v2 + "</label>");

							}

						});

		if (ct > 1) {
			for (j = 1; j <= ct; j++) {
				$("#div_iv_fluids" + j + "_2").empty();
				var course_id = $("select#course_id").val();
				$
						.post(
								"getModuleData?" + key + "=" + value,
								{
									course_id : course_id
								},
								function(k) {
									for (var i = 0; i < k.length; i++) {
										var v1 = k[i][0];
										var v2 = k[i][1];
										$("#div_iv_fluids" + j + "_2")
												.append(
														"<label class='lbl' value='module_id"+i+"' for='first'> <input class='multi' type='checkbox' id='in_module_id1"
																+ i
																+ "' name='in_module_id1' value='"
																+ v1
																+ "' onclick='mycheckindex(1)'/> "
																+ v2
																+ "</label>");
									}
								});
			}
		}

	}

	function getcourseBYSystem(obj) {

		var system_id = $("#system_id").val();

		$
				.post(
						"getcourseBYSystem?" + key + "=" + value,
						{
							system_id : system_id
						},
						function(k) {
							// 		alert(k);
							var options = '<option value="-1">--Select--</option>';
							for (var i = 0; i < k.length; i++) {
								options += '<option value="'+k[i][0]+'" name="' + k[i][0]+ '" >'
										+ k[i][1] + '</option>';
							}
							$("#course_id").html(options);
						});
	}

	function Validate() {
		
		if ($("select#system_id").val() == "0") {
			alert("Please Select Course Name.");
			$("select#system_id").focus();
			return false;
		}

		if ($("select#course_id").val() == "0") {
			alert("Please Select Course Name.");
			$("select#course_id").focus();
			return false;
		}

		if ($("select#set_id1").val() == "0") {
			alert("Please Select Set.");
			$("select#set_id1").focus();
			return false;
		}

		if ($("select#div_iv_fluids1").val() == "0") {
			alert("Please Select module.");
			$("select#div_iv_fluids1").focus();
			return false;
		}

	}

	function isValid() {
		data();
		return true;
	}
	function Getinput_tbody_cbc(ser) {

		ser = parseInt(ser) + 1;

		var label = "";
		var tbody = '<td align="center"><select name="set_id'
				+ ser
				+ '" id="set_id'
				+ ser
				+ '" class="form-control" onclick="getset(this.value,'
				+ ser
				+ ')" onchange="getData(this,'
				+ ser
				+ ');"><option value="0">--Select--</option><c:forEach var="item" items="${getSetListALL}" varStatus="num"><option value="${item[0]}">${item[1]}</option></c:forEach></select></td>'
				+ '<td align="center"><div class="multipleSelection"><div class="selectBox" id="div_iv_fluids'
				+ ser
				+ '"onclick="showCheckboxes(this,'
				+ ser
				+ ')"><span id="module_id'+ser+'" class="hida" value="0"><b>--Select--</b></span><p class="multiSel"></p><div class="overSelect"></div><input type="hidden" id="module_idx'+ser+'" name="module_idx'+ser+'"  autocomplete="off" class="form-control-sm form-control"value=""></div><div id="div_iv_fluids'+ser+'_2" class="test"></div></div></td>'
				+ '<input type="hidden" id="checkboxindex'+ser+'" name="checkboxindex'+ser+'">'
				+ '<input type="hidden" id="in_module_id_hid_ch'+ser+'" name="in_module_id_hid_ch'+ser+'">';
		formopen_add(ser, 'cbc', 'g_cbc', tbody);

		//$("#table_cbc").append(tbody);

		var course_id = $("select#course_id").val();
		$
				.post(
						"getModuleData?" + key + "=" + value,
						{
							course_id : course_id
						},
						function(k) {
							for (var i = 0; i < k.length; i++) {
								var v1 = k[i][0];
								var v2 = k[i][1];
								$("#div_iv_fluids" + ser + "_2")
										.append(
												"<label class='lbl' value='module_id"+i+"' for='first'> <input class='multi' type='checkbox' id='in_module_id1"
														+ i
														+ "' name='in_module_id"
														+ ser
														+ "' value='"
														+ v1
														+ "' onclick='mycheckindex("
														+ ser
														+ ")'/> "
														+ v2
														+ "</label>");
							}
						});
		$("#count_hidden_cbc").val(ser);

		$("#ser").val(ser);

	}

	function mycheckindex(myindex) {
		var gsida = [];
		var ele = document.getElementsByName("in_module_id" + myindex);

		console.log("ele.length - " + ele.length);
		for (var i = 0; i < ele.length; i++) {
			if (ele[i].checked) {
				/* gsida.push(gsid[i].value); */
				gsida.push(ele[i].value);

				/* remarksa.push(remarks[i].value); */
			}
		}

		// 	 	alert(myindex)

		console.log(myindex);
		document.getElementById('in_module_id_hid_ch' + myindex).value = gsida
				.toString();

	}

	function getset(val, ind) {

	}

	function getData(obj) {
		$.ajaxSetup({
			async : false
		});
		let dta_id = obj.id;
		dta_id = dta_id.replace('set_id', '');

		var course_id = $("#course_id").val();

		var set_id = $("#" + obj.id).val();

		var k1 = 0;
		$.post("getmodulbycourseListchack?" + key + "=" + value, {
			course_id : course_id,
			set_id : set_id
		}, function(k) {

			if (k != "") {

				var modules = k;
				for (var c = 0; c < modules.length; c++) {
					$(":checkbox[value=" + modules[c] + "]").click();
				}
				$("#data_f").val("1");
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
	}

	// 	window.addEventListener('mouseup', function(event) {
	// 		var pol = document.getElementById(temp)
	// 		if (event.target != pol && event.target.parentNode != pol) {
	// 			pol.style.display = 'none';
	// 		}
	// 	});

	function data() {

		var hc = $("#count_hidden_cbc").val();
		for (var j = 1; j <= hc; j++) {
			for (var i = 1; i <= '${getModulnameList.size()}'; i++) {

				if ('${getModulnameList.size()}' > 0) {
					if ($('#in_module_id' + j + i).is(":checked")) {
						var temp = $("#module_idx" + j).val();
						if (temp != "") {
							$("input#module_idx" + j).val(
									temp
											+ ","
											+ $('#in_module_id' + j + i).prop(
													"name"));
						} else {
							$("input#module_idx" + j).val(
									$('#in_module_id' + j + i).prop("name"));
						}
					}
					// 					alert($("input#module_idx"+j).val());
				}
			}
		}

	}

	$(document).ready(function() {

		mockjax1('search_Lcwsc');
		table = dataTable('search_Lcwsc');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

		if ('${list.size()}' == "") {
			$("div#search_Lcwsc").hide();
		}
	});

	function data(search_Lcwsc) {
		//debugger;
		jsondata = [];
		var table = $('#' + search_Lcwsc).DataTable();
		var info = table.page.info();
		//		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];

		$.post("getFilter_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ i + 1, j[i][0], j[i][1], j[i][2], j[i][3] ]);
			}
		});
		$.post("getTotal_dataCount?" + key + "=" + value, {

		}, function(j) {
			FilteredRecords = j;
		});
	}
</script>







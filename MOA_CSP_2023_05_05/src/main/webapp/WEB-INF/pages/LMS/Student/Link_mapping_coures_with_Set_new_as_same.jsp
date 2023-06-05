<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<!-- <script src="js/amin_module/rbac/jquery-2.2.3.min.js"></script> -->
<script src="js/miso/commonJS/addmorefunctionality.js"></script>
<script src="js/common/multicheck.js"></script>
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet"
	href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>



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
		//setTimeLoadForTable();
	});
</script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span>Link Elective Course and Set and Degree
							Master

						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Link
									Elective Course and Set and Degree Master</li>
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
					<form:form name="linkmaster" id="linkmaster"
						action="linkelectivecourseandsetandmodule_action" method='POST'
						modelAttribute="linkelectivecourseandsetandmodule_CMD"
						enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Link Elective Course and Set and Degree
									Master</h6>
								<div class="row">

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">System<strong
												class="mandatory">* </strong></label>
											<div class="select-position">
												<select name="system_id" id="system_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSystemList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.system_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Degree <strong
												class="mandatory">* </strong></label>
											<div class="select-position">
												<select name="degree_id"
													class="singleselect form-control form-control-lg"
													id="degree_id">
													<!-- onchange="changecourseDetails();" -->
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getDegreeList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.degree_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Course Video<strong
												class="mandatory">* </strong></label> <input type="file"
												accept=".mp4" id="course_video" name="course_video"
												class="form-control" /> <input type="hidden"
												id="hid_course_video" value="0" name="hid_course_video"
												class="mt-3" /> <div class="note-text"><span class="errorClass"
												id="doc_upload_lbl"></span> <span class='tikClass'
												id="doc_upload_lbltik"></span></div>
										</div>

									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Description<strong
												class="mandatory">* </strong></label>
											<textarea id="description" name="description"
												autocomplete="off" maxlength="250" placeholder="Description" /></textarea>
											<input type="hidden" id="id" name="id" value="0"
												autocomplete="off" class="mt-3" />
										</div>

									</div>
								</div>

							</div>

							<div id="setdiv"></div>
							
							<div class="inst_block">
								<h6 class="mb-1">Instruction</h6>
								<ul class="inst_list">
									<li><p class="inst_text">FA, FB, FC</p></li>
									<li><p class="inst_text">SA, SB, SC</p></li>
									<li><p class="inst_text">TA, TB, TC</p></li>
									<li><p class="inst_text">Choose either from above option
														but enter Set together in table (like Set FA,FB,FC)</p></li>
								</ul>
							</div>
							<section class="single-detail-block">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<div class="table-wrapper table-responsive custom-table b-top">
											<table class="table" id="table_cbc">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th><h6>
																Set<strong class="mandatory">* </strong>
															</h6></th>
														<th><h6>
																Course<strong class="mandatory">* </strong>
															</h6></th>
														<th><h6>
																Set demo video<strong class="mandatory">* </strong>
															</h6></th>
														<th><h6>Action</h6></th>
													</tr>
													<!-- 						end table row -->
												</thead>
												<tbody id="tbody_cbc">
													<tr id="tr_id_cbc">
														<td><p>1</p></td>
														<td><div class="select-style-1">
																<div class="select-position">
																	<select id="set_id1" name="set_id1"
																		class="singleselect form-control form-control-lg">

																		<option value="0">--Select--</option>
																		<c:forEach var="item" items="${getSetListALL}"
																			varStatus="num">
																			<option value="${item[0]}">${item[1]}</option>
																		</c:forEach>
																	</select>
																</div>
															</div></td>

														<td>
															<div class="select-style-1">
																<div class="select-position">
																	<select class="form-control form-control-lg"
																		id="div_iv_fluids1" name="">
																		<option value="0" id="course_id1"
																			class="hida overSelect">--Select--</option>

																	</select>
																</div>
																<input type="hidden" id="course_idx1" name="course_idx1"
																	autocomplete="off" class="form-control-sm form-control"
																	value="" />
															</div>

															<div id="div_iv_fluids1_2" class="multiselect">
																<c:forEach var="item" items="${getcoursenameList}"
																	varStatus="num">

																	<div
																		class="form-check radio-style checkbox-style d-flex align-items-center">
																		<input class="multi form-check-input mr-5"
																			type="checkbox" id="in_course_id1${num.index+1}"
																			name="in_course_id1" value="${item[0]}" /> <label
																			class="lbl" value="course_id${num.index+1}"
																			for="first">${item[1]}</label>
																	</div>
																</c:forEach>
															</div>

														</td>



														<td>
															<div class="input-style-1">
																<input type="file" accept=".mp4" id="set_demo_video1"
																	name="set_demo_video1" class="form-control"><div class="note-text"> <span
																	class="errorClass" id="set_demo_lbl"></span> <span
																	class='tikClass' id="set_demo_lbltik"></span></div>
															</div>
														</td>


														<td>
															<ul class="buttons-group">
																<li value="ADD" title="ADD" id="id_add_cbc1"><a
																	class="main-btn success-btn btn-hover btn-sm btnaddmore"><i
																		class="lni lni-plus"></i></a></li>
																		
															</ul>
														</td>
													</tr>
												</tbody>
											</table>


											<!--                                     <div class="row"> -->
<!-- 											<div class="col-lg-12 col-md-12 col-sm-12 d-flex flex-column"> -->
<!-- 												<h5 class="mb-2"> -->
<!-- 													<label for="text-input" class="co d-inline"></label>Note: -->
<!-- 												</h5> -->
<!-- 												<ol> -->
<!-- 													<li class="mandatory">1. FA, FB, FC</li> -->
<!-- 													<br> -->
<!-- 													<li class="mandatory">2. SA, SB, SC</li> -->
<!-- 													<br> -->
<!-- 													<li class="mandatory">3. TA, TB, TC</li> -->
<!-- 													<br> -->
<!-- 													<li class="mandatory">Choose either from above option -->
<!-- 														but enter Set together in table (like Set FA,FB,FC)</li> -->

<!-- 												</ol> -->
<!-- 												 <h2>You have submitted form successfully!</h2>
<!--                                             <p>Thank you very much for you information. we will procceed accordingly. -->
<!--                                             </p> --> 
<!-- 											</div> -->
											<!--                                     </div> -->

											<input type="hidden" id="checkboxindex1"
												name="checkboxindex1"> <input type="hidden"
												id="in_course_id_hid_ch1" name="in_course_id_hid_ch1">
											<input type="hidden" id="count_hidden_cbc"
												name="count_hidden_cbc" class="form-control autocomplete"
												value="1">
											<!-- 				end table -->
										</div>
									</div>
								</div>
							</section>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">
											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"> <i class="lni lni-search-alt"></i>Search
											</a></li>
											<li><input type="submit" value="Save" id="btn-save"
												class="main-btn info-btn btn-hover btnsave"/></li>
											<li><a href="Link_Coures_With_Set_Url"
												class="main-btn dark-btn n btn-hover btnreset" type="button">Reset</a></li>
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
											<table class="table" id="search_Lcwsc">
												<thead>
													<tr>
														<th><h6>Sr No</h6></th>
														<th><h6>System</h6></th>
														<th><h6>Degree</h6></th>
														<th><h6>Set</h6></th>
														<th><h6>Course</h6></th>
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
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- The Modal -->
<div class="modal image-modal" id="videoModal">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
		
<div class="modal-header">
<!-- 							<h3 class="modal-title">Modal title</h3> -->
							<button type="button" class="btn-close close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>




<!-- 			<span class="close">&times;</span> -->
			<!-- Modal body -->
			<div class="modal-body">
				<div class="modal-img">
					<div id="videodiv">
						<div class="d-flex justify-content-center">
							<div class="content-title">
								Current Time : <span id="">0</span>
							</div>
							<div class="content-title">
								Total time : <span id="totalTime">0</span>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<script nonce="${cspNonce}" type="text/javascript">


function addMoreCspDrop(obj){
	
	document.getElementById('div_iv_fluids'+obj).onclick = function() {
		showCheckboxes(this,obj);
	};
}
function addMoreCsp(obj){
	
	document.getElementById('id_add_cbc'+obj).onclick = function() {
		Getinput_tbody_cbc(obj);
	};
}
function removeMoreCsp(obj){

	document.getElementById('id_remove_cbc'+obj).onclick = function() {
		formopen_remove(obj,'cbc','g_cbc');
	};
}
function setTimeLoadForTable(){
	
	document.getElementById('btn-save').onclick = function() {
		return Validation();
	};
	document.getElementById('system_id').onchange = function() {
		getdegreelistbysystem();
	};
	
	document.getElementById('set_id1').onclick = function() {
		 getset(this.value,1);
	};
	
	document.getElementById('set_id1').onchange = function() {
		getData(this,1);
	};
	
	document.getElementById('div_iv_fluids1').onclick = function() {
		showCheckboxes(this,'1');
	};
	document.getElementById('id_add_cbc1').onclick = function() {
		Getinput_tbody_cbc('1');
	};
	document.getElementById('course_video').onchange = function() {
		pdfFileSizeValidation(this,this.value,'course_video','100mb','doc_upload_lbltik','doc_upload_lbl',this.accept);
	};
	
	document.getElementById('set_demo_video1').onchange = function() {
		pdfFileSizeValidation(this,this.value,'set_demo_video1','100mb','set_demo_lbltik','set_demo_lbl',this.accept);
	};
	
	document.querySelectorAll('.ocVC').forEach((items, index) => {
		items.addEventListener('click', event => {
			
			var val=parseInt(index)+1;
			var cid = document.getElementById('cId'+val).value;
			var sid = document.getElementById('SetId'+val).value;
			if (confirm('Are You Sure You Want to Play video ?')) {
				videotopicCall(cid,sid);
			} else {
				return false;
			}
		})
	});
}

function Addmore(index){
	
	document.getElementById('set_demo_video'+index).onchange = function() {
		pdfFileSizeValidation(this,this.value,'set_demo_video'+index+'','200kb','upload_file_lbltik'+index+'','upload_file_lbl'+index+'',this.accept);
	};
}

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
										"<div class='form-check radio-style checkbox-style d-flex align-items-center'><input class='multi multi form-check-input mr-5' type='checkbox' id='in_course_id1"
										+ i
										+ "' name='in_course_id1' value='"
										+ v1
										+ "' onclick='mycheckindex(1)'/><label class='lbl' value='course_id"+i+"' for='first'>" + v2 + "</label></div>");
								
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
												"<div class='form-check radio-style checkbox-style d-flex align-items-center'><input class='multi multi form-check-input mr-5' type='checkbox' id='in_course_id1"
												+ i
												+ "' name='in_course_id1' value='"
												+ v1
												+ "' onclick='mycheckindex(1)'/><label class='lbl' value='course_id"+i+"' for='first'>" + v2 + "</label></div>");

							}
						});
				 
			}
		}
		
	}

	

		function Validation() {
			
		if ($("#system_id").val() == "0") {
			alert("Please Select System");
			$("select#system_id").focus();
			return false;
		}

		if ($("#degree_id").val() == "0") {
			alert("Please Select Degree");
			$("select#degree_id").focus();
			return false;
		}

		if ($("#course_video").val() == "") {
			alert("Please Upload Course Video");
			$("#course_video").focus();
			return false;
		}
		
		if ($("textarea#description").val().trim() == "") {
			alert("Please Enter Description");
			$("textarea#description").focus();
			return false;
		}

		for(cbc = 1; cbc <= $('#count_hidden_cbc').val(); cbc++){

			if($("#set_id"+cbc).val()=='0'){
				alert("Please Select Set In "+cbc+" Row");
				$("#set_id"+cbc).focus();
				return false;
			}
			if($("#in_course_id_hid_ch"+cbc).val()==''){
				alert("Please Select Course In "+cbc+" Row");
				$("#div_iv_fluids"+cbc).focus();
				return false;
			}
			if($("#set_demo_video"+cbc).val()==''){
				alert("Please Upload Demo Video In "+cbc+" Row");
				$("#set_demo_video"+cbc).focus();
				return false;
			}
		
		if($("#set_id"+cbc).val() != "0" && $("#set_id"+cbc).val() != "1")
		
		if($("#set_id"+cbc).val() != "1"){
			if($("#set_id"+cbc).val()=='0'){
				alert("Please Select Set In "+cbc+" Row");
				$("#set_id"+cbc).focus();
				return false;
			}
			if($("#in_course_id_hid_ch"+cbc).val()==''){
				alert("Please Select Course In "+cbc+" Row");
				$("#div_iv_fluids"+cbc).focus();
				return false;
			}
			if($("#set_demo_video"+cbc).val()==''){
				alert("Please Upload Demo Video In "+cbc+" Row");
				$("#set_demo_video"+cbc).focus();
				return false;
			}
		}	
	}

	}

	function isValid() {
		data();
		return true;
	}
	function Getinput_tbody_cbc(ser) {

		var degree_id = $("select#degree_id").val();

		if (degree_id == "0") {
			alert("Please Select Degree.");
			$("select#degree_id").focus();
			return false;
		}

		ser = parseInt(ser) + 1;

		var label = "";
		var tbody = '<td><div class="select-style-1"><div class="select-position"><select name="set_id'
			+ ser
			+ '" id="set_id'
			+ ser
			+ '" onclick="getset(this.value,'
			+ ser
			+ ')" onchange="getData(this,'
			+ ser
			+ ');"><option value="0">--Select--</option><c:forEach var="item" items="${getSetListALL}" varStatus="num"><option value="${item[0]}">${item[1]}</option></c:forEach></select></div></div></td>'

			+ '<td> <div class="select-style-1"><div class="select-position"><select class="selectBox" id="div_iv_fluids'
			+ ser
			+ '"><option id="course_id'+ser+'" class="hida" value="0">--Select--</option><input type="hidden" id="course_idx'+ser+'" name="course_idx'+ser+'"  autocomplete="off" class="form-control-sm form-control"value=""></select></div></div><div id="div_iv_fluids'+ser+'_2" class="multiselect"></div></td>'
			+ '<input type="hidden" id="checkboxindex'+ser+'" name="checkboxindex'+ser+'">'
			+ '<input type="hidden" id="in_course_id_hid_ch'+ser+'" name="in_course_id_hid_ch'+ser+'">'
			+ '  <td>'
			+ '<div class="input-style-1"><input type="file" accept=".mp4" id="set_demo_video'+ser+'" name="set_demo_video'+ser+'"'
                +'class="form-control"><div class="note-text"><span class="errorClass" id="set_demo_lbl'+ser+'">${exp_path_msg}</span> <span class="tikClass" id="set_demo_lbltik'+ser+'"></span></div>'
			+'</div>'
			+ '</td>';
	formopen_add(ser, 'cbc', 'g_cbc', tbody);
	addMoreCspDrop(ser);
	addMoreCsp(ser);
	removeMoreCsp(ser);
	Addmore(ser);

		$
				.post(
						"getcourselistby_degree?" + key + "=" + value,
						{
							degree_id : degree_id
						},
						function(k) {

							for (var i = 0; i < k.length; i++) {
								var v1 = k[i][0];
								var v2 = k[i][1];
								$("#div_iv_fluids" + ser + "_2")
								.append(
										"<div class='form-check radio-style checkbox-style d-flex align-items-center'><input class='multi form-check-input mr-5' type='checkbox' id='in_course_id1"
												+ i
												+ "' name='in_course_id"
												+ ser
												+ "' value='"
												+ v1
												+ "' onclick='mycheckindex("
												+ ser
												+ ")'/><label class='lbl' value='course_id"+i+ "' for='first'>"+ v2 + "</label></div>");
						
						
					}
				});
		$("#count_hidden_cbc").val(ser);
		$("#ser").val(ser);
	}

	function mycheckindex(myindex) {
		var gsida = [];
		var ele = document.getElementsByName("in_course_id" + myindex);

		console.log("ele.length - " + ele.length);
		for (var i = 0; i < ele.length; i++) {
			if (ele[i].checked) {
				gsida.push(ele[i].value);

			}
		}
		console.log(myindex);
		document.getElementById('in_course_id_hid_ch' + myindex).value = gsida
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
	function showCheckboxes(obj,val) {
		
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

	function data() {

		var hc = $("#count_hidden_cbc").val();
		for (var j = 1; j <= hc; j++) {
			for (var i = 1; i <= '${getcoursenameList.size()}'; i++) {
				if ('${getcoursenameList.size()}' > 0) {
					if ($('#in_course_id' + j + i).is(":checked")) {
						var temp = $("#course_idx" + j).val();
						if (temp != "") {
							$("input#course_idx" + j).val(
									temp
											+ ","
											+ $('#in_course_id' + j + i).prop(
													"name"));
						} else {
							$("input#course_idx" + j).val(
									$('#in_course_id' + j + i).prop("name"));
						}
					}
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
		
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
		
	});

	function data(search_Lcwsc) {
		jsondata = [];
		var table = $('#' + search_Lcwsc).DataTable();
		var info = table.page.info();
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).html()
				.toLowerCase();
		var orderType = order[0][1];
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		var set = $("#set_id1").val();
		var course = $("#course_idx1").val();

		$.post("getFilter_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			system_id : system_id,
			degree_id : degree_id,
			set : set,
			course : course

		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i][5] , j[i][0], j[i][1], j[i][2], j[i][3],
						j[i][4] ]);
			}
		});
		$.post("getTotal_dataCount?" + key + "=" + value, {
			Search : Search,
			system_id : system_id,
			degree_id : degree_id,
			set : set,
			course : course

		}, function(j) {
			FilteredRecords = j;
		});
		
		setTimeout(setTimeLoadForTable, 1000);
	}
</script>
<script nonce="${cspNonce}" type="text/javascript">

	function getdegreelistbysystem() {
		var system_name = $("#system_id").val();
		$
				.post('getdegreelistby_system1?' + key + "=" + value, {
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

	function videotopicCall(p_id, s_id) {
		var id = p_id;
		var set_id = s_id;

		$('#video_id').val(id);
		$('#id').val(id);
		$('div#videodiv').empty();
		$("div#videodiv")
				.append(
						'<div class="d-flex justify-content-center"><div class="content-title">Current Time : <span  id="currentTime">0</span></div><div class="content-title">Total time : <span id="totalTime">0</span></div></div><video  id="my_video" controls width="100%" height="100%"><source id="sourceid" src="kmlLOFileDownload666?kmlFileId65='
						+ id
						+ '&fildname='
						+ set_id
						+ '" type="video/mp4"></video>' + '</tr>');
		var modal = document.getElementById("videoModal");
		var span = document.getElementsByClassName("close")[0];
		document.getElementById('videoModal').style.display = 'block';
		span.onclick = function() {
			modal.style.display = "none";
		}

		
		var vid = document.getElementById("my_video");
		function setCurTime() {
			var myVid = document.getElementById("my_video");
			myVid.currentTime = "5";
		}
		function timev(obj) {
			var curtime = obj.currentTime;
		}
		setInterval(function() {
			var vid = document.getElementById("my_video");

			const element = document.getElementById("currentTime");
			element.innerHTML = vid.currentTime;

			const ele = document.getElementById("totalTime");
			ele.innerHTML = vid.duration;
		}, 1000);
	}
	document.getElementById('degree_id').onchange = function() {
		var degree_id = this.value;
		$
				.post(
						"getcourselistby_degree?" + key + "=" + value,
						{
							degree_id : degree_id
						},
						function(k) {
							$("#div_iv_fluids1_2").empty();
							for (var i = 0; i < k.length; i++) {
								var v1 = k[i][0];
								var v2 = k[i][1];
								$("#div_iv_fluids1_2")
								.append(
										"<div class='form-check radio-style checkbox-style d-flex align-items-center'><input class='multi multi form-check-input mr-5' type='checkbox' id='in_course_id1"
												+ i
												+ "' name='in_course_id1' value='"
												+ v1
												+ "' onclick='mycheckindex(1)'/><label class='lbl' value='course_id"+i+"' for='first'>" + v2 + "</label></div>");
							}
						});
	}
	
	function getcourselistbydegree() {
		var system_id = $("#system_id").val();
		var degree_id = $("#degree_id").val();
		$
				.post('getcourselistby_degree_andsystem?' + key + "=" + value,
						{
							degree_id : degree_id,
							system_id : system_id
						})
				.done(
						function(j) {
							
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_id").html(options);
						});
	}
</script>
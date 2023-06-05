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

						<h2>
							<span id="lbladd"></span> Add Paper Layout
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Add Paper Layout</li>
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
					<form:form name="nchPaperLayoutform" id="nchPaperLayoutform"
						action="NchPaperLayoutAction" method="post" class="form-horizontal"
						modelAttribute="NchPaperLayoutCMD">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Add Paper Layout</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
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
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="degree_id" id="degree_id" class="form-control">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Professional<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="professional_id" id="professional_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getprofessionalList}"
														varStatus="num">
														<option value="${item.id}" name="${item.professional}">${item.professional}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Subject<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="course_id" class="form-control" id="course_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label>Paper<strong class="mandatory">*</strong></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="paper_id" id="paper_id">
													<!-- onchange="getSemesterBYDegree(this);" -->
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getPaperList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.paper}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>


<!-- 									<div class="col-12 col-sm-12 col-md-6 col-lg-3"> -->
<!-- 										<div class="input-style-1"> -->
<!-- 											<label>Time<span class="mandatory">*</span></label> <input -->
<!-- 												type="text" id="time" name=time -->
<!-- 												class="autocomplete xt-transupp" autocomplete="off" -->
<!-- 												maxlength="3" placeholder="Enter Time (In Hours)" /> -->


<!-- 										</div> -->
<!-- 										end input -->
<!-- 									</div> -->

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>Instructions<span class="mandatory">*</span></label> <input
												type="text" id="instructions" name=instructions
												class="autocomplete xt-transupp" autocomplete="off"
												maxlength="100" placeholder="Enter Instructions" />

										</div>
										<!-- end input -->
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label>Question Type<strong class="mandatory">*</strong></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="question_type_id" id="question_type_id">
													<!-- onchange="getSemesterBYDegree(this);" -->
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getQuestionList}"
														varStatus="num">
														<option value="${item.id}" name="${item.id}">${item.question_type}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>Number of Questions<span class="mandatory">*</span></label>
											<input type="text" id="num_questions" name="num_questions"
												class="autocomplete xt-transupp" autocomplete="off"
												maxlength="3" placeholder="Enter Number of Questions" />

										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label>Marks per Question<span class="mandatory">*</span></label>
											<input type="text" id="marks_questions"
												name="marks_questions" class="autocomplete xt-transupp"
												autocomplete="off" maxlength="3"
												placeholder="Enter Marks per Question" />

										</div>
										<!-- end input -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
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
												autocomplete="off" />
										</div>

										<!-- end select -->
									</div>
									<!-- 								<input type="hidden" id="id" name="id" value="0"> -->
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
										<ul class="buttons-group mainbtn">

											<li><a id="btn-reload1" href="Nch_Paper_Layout_Url"
												class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback">
													<i class="lni lni-chevron-left"></i>Back
											</a></li>

											<li><input id="btn-update"
												class="main-btn deactive-btn btn-hover btnupda"
												type="button" value="Update" /></li>

											<li><a id="btn-reload"
												class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
												type="button"><i class="lni lni-search-alt"></i>Search</a></li>
											<li><input id="btn-save"
												class="main-btn info-btn btn-hover btnsave" type="button"
												value="Save" /></li>

											<li><a id="btn-reload2" href="Nch_Paper_Layout_Url"
												class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>

											<li><a id="btn-view"
												class="main-btn dark-btn btn-hover btn-iconic-icon btnview"
												type="button"><i class="lni lni-eye"></i>View</a></li>


										</ul>
									</div>
								</div>
							</div>
							<!-- Bottom Button End -->
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
			<div id="pop">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
							<div class="custom-multi-table">
								<h6> Paper Layout</h6>
								<h6>PAPER-I Time: 3 Hours INSTRUCTIONS: All questions
									compulsory TOTAL MARKS : 100</h6>
								<div
									class="table-wrapper table-responsive custom-table custom-table-v2">
									<!-- id="container-table" -->
									<table class="table table-striped" id="pop">

										<!-- 					<h3>6 E - Paper Layout</h3> -->


										<!-- 								<table class="table"> -->

										<!-- 								<tr class="learncount middle-center"> -->
										<!-- 									<td colspan="10"><span><label class="bold"> <br><h4>PAPER-I -->
										<!-- 										Time: 3 Hours Maximum Marks: 100 -->
										<!-- 										INSTRUCTIONS: All questions compulsory -->
										<!-- 										TOTAL MARKS : 100</h4></label></span></td> -->
										<!-- 								</tr> -->
										<thead>
											<tr>
												<th><h6>SR NO.</h6></th>
												<th><h6>Type Of Question</h6></th>
												<th><h6>Number of Questions</h6></th>
												<th><h6>Marks per question</h6></th>
												<th><h6>Total Marks</h6></th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="pop2">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
							<div class="custom-multi-table">
								<h6 class="mb-10">PAPER-II Time: 3 Hours INSTRUCTIONS: All
									questions compulsory TOTAL MARKS : 100</h6>

								<div
									class="table-wrapper table-responsive custom-table custom-table-v2">
									<!-- id="container-table" -->
									<table class="table table-striped" id="pop2">



										<thead>

											<tr>
												<th><h6>SR NO.</h6></th>
												<th><h6>Type Of Question</h6></th>
												<th><h6>Number of Questions</h6></th>
												<th><h6>Marks per question</th>
												<th><h6>Total Marks</h6></th>
											</tr>

										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<section class="single-detail-block">
				<div class="row" id="view_tbl">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card-style mb-30">
							<div class="table-wrapper table-responsive custom-datatable-p">
								<table class="table" id="search_Paper_Layout">
									<thead>
										<tr>
											<th id="1"><h6>Sr No</h6></th>
											<th id="16"><h6>System</h6></th>
											<th id="17"><h6>degree</h6></th>
											<th id="18"><h6>Professional</h6></th>
											<th id="19"><h6>Subject</h6></th>
											<th id="7"><h6>Paper</h6></th>
<!-- 											<th id="3"><h6>Time</h6></th> -->
											<th id="4"><h6>Instruction</h6></th>
											<th id="9"><h6>Question Type</h6></th>
											<th id="5"><h6>Number of Questions</h6></th>
											<th id="6"><h6>Marks per Question</h6></th>
											<!-- 									<th><h6> Status</h6></th> -->
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

		</div>
	</div>
</section>

<%-- <c:url value="getSearch_Paper_Layout" var="searchUrl" /> --%>
<%-- <form:form action="${searchUrl}" method="post" id="searchForm" --%>
<%-- 	name="searchForm" modelAttribute="Certificate_name1"> --%>
<!-- 	<input type="hidden" name="Certificate_name1" id="Certificate_name1" /> -->
<!-- 	<input type="hidden" name="status1" id="status1" value="0" /> -->
<%-- </form:form> --%>

<c:url value="Edit_Nch_Paper_Layout_Url" var="Edit_Url" />
<form:form action="${Edit_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="Nch_Paper_Layout_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="PaperLayoutport2" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>

<script nonce="${cspNonce}" type="text/javascript">

	$(document).ready(function() {
		$("#pop").hide();
		$("#btn-update").hide();
		$("#btn-reload1").hide();

		$("#pop").hide();
		$("#pop2").hide();
		mockjax1('search_Paper_Layout');
		table = dataTable('search_Paper_Layout');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('.UpperClassName').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		
	});
	
	
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
	function getcourselistby_professional() {
		var degree_id = $("#degree_id").val();
		var professional_id = $("#professional_id").val();
		
		$.post('getCourseList_for_Curri?' + key + "=" + value,{  
			degree_id : degree_id,
			professional_id : professional_id
			}).done(function(j) {
							var options = '<option value="' + "0" + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#course_id").html(options);
		});
	}
	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('instructions').onkeypress = function() {
			return onlyAlphabetsStringSpace(this, event);
		};
		
// 		document.getElementById('time').onkeypress = function() {
// 			return isNumberKey0to9(event);
// 		};
		
		document.getElementById('num_questions').onkeypress = function() {
			return isNumberKey0to9(event);
		};
		
		document.getElementById('marks_questions').onkeypress = function() {
			return isNumberKey0to9(event);
		};
		
		document.getElementById('btn-save').onclick = function() {
			if(Validation()){
				 $("#nchPaperLayoutform").submit();
			}
		};
		document.getElementById('btn-update').onclick = function() {
			if(Validation()){
				 $("#nchPaperLayoutform").submit();
			}
		};
		document.getElementById('btn-view').onclick = function() {
			return View_Validation();
		};
		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem();
		};
		document.getElementById('professional_id').onchange = function() {
			getcourselistby_professional();
		};
// 		document.getElementById('btn-view').onclick = function() {
// 			getpop();
// 			getpop2();
// 		};
// 		document.getElementById('btn-view').onclick = function() {
// 			getpop2();
// 		};

	});
	
	function setTimeLoadForTable(){
		
		document.querySelectorAll('.ADDpaper_layout').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				var haps = document.getElementById('aps'+val).value;
				var hapd = document.getElementById('apd'+val).value;
				var happ = document.getElementById('app'+val).value;
				var hapc = document.getElementById('apc'+val).value;
				var hpaper = document.getElementById('appaperAGE'+val).value;
// 				var htime = document.getElementById('aptimeAGE'+val).value;
				var hins = document.getElementById('apinsAGE'+val).value;
				var hqtype = document.getElementById('apqtypeAGE'+val).value;
				var hnumq = document.getElementById('apnumqAGE'+val).value;
				var hmarq = document.getElementById('apmarqAGE'+val).value;
				var hstatus = document.getElementById('apstatusAGE'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,haps,hapd,happ,hapc,hpaper,hins,hqtype,hnumq,hmarq,hstatus);
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
	
	function data(search_Paper_Layout) {
		
		jsondata = [];
		var table = $('#' + search_Paper_Layout).DataTable();
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
		var professional_id = $("#professional_id").val();
		var course_id = $("#course_id").val();
		var paper_id = $("#paper_id").val();
// 		var time = $("#time").val();
		var instructions = $("#instructions").val();
		var question_type_id = $("#question_type_id").val();
		var num_questions = $("#num_questions").val();
		var marks_questions = $("#marks_questions").val();
		var status = $("#status").val();

		$.post("getFilterNchpaper_layout_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			system_id : system_id,
			degree_id : degree_id,
			professional_id : professional_id,
			course_id : course_id,
			paper_id : paper_id,
// 			time : time,
			instructions : instructions,
			question_type_id : question_type_id,
			num_questions : num_questions,
			marks_questions : marks_questions,
			status : status

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,j[i].system_name,j[i].degree_name,j[i].professional, j[i].course_name, j[i].paper, j[i].instructions, j[i].question_type, j[i].num_questions, j[i].marks_questions, j[i].action ]);
			}
		});
		$.post("getTotalNChpaper_layout_dataCount?" + key + "=" + value, {
			Search : Search,
			system_id : system_id,
			degree_id : degree_id,
			professional_id : professional_id,
			course_id : course_id,
			paper_id : paper_id,
// 			time : time,
			instructions : instructions,
			question_type_id : question_type_id,
			num_questions : num_questions,
			marks_questions : marks_questions,
			status : status
			
		
		}, function(j) {

			FilteredRecords = j;

		});
		setTimeout(setTimeLoadForTable, 1000);
	}

	
	function editData(id,system_id,degree_id,professional_id,course_id,paper_id,instructions,question_type_id,num_questions,marks_questions,status) {
		
		
		$("#btn-update").show();
		$("#btn-save").hide();
		$("#btn-reload").hide();
		$("#btn-reload2").hide();
		$("#btn-reload1").show();
		$("#view_tbl").hide();
		$("#btn-view").hide();
		
		
		document.getElementById('lbladd').innerHTML = "Update ";
		$("select#system_id").val(system_id);
		$('#system_id').trigger('change');
		getdegreelistbysystem();
		$("select#degree_id").val(degree_id);
		$("select#professional_id").val(professional_id);
		$('#professional_id').trigger('change');
		getcourselistby_professional();
		$("select#course_id").val(course_id);
		$("select#paper_id").val(paper_id);
		$('#paper_id').trigger('change');
// 		$("input#time").val(time);
		$("input#instructions").val(instructions);
		$("select#question_type_id").val(question_type_id);
		$('#question_type_id').trigger('change');
		$("input#num_questions").val(num_questions);
		$("input#marks_questions").val(marks_questions);
		$("select#status").val(status);
		document.getElementById('id').value=id;
	}
	

	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
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
		if ($("#professional_id").val().trim() == "0") {
			alert("Please Select professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#course_id").val().trim() == "0") {
			alert("Please Select Subject.");
			$("select#course_id").focus();
			return false;
		}
		if ($("#paper_id").val().trim() == "0") {
			alert("Please Enter Paper.");
			$("select#paper_id").focus();
			return false;
		}
// 		if ($("#time").val().trim() == "") {
// 			alert("Please Enter Time.");
// 			$("input#time").focus();
// 			return false;
// 		}
		
// 		if ($("#instructions").val().trim() == "") {
// 			alert("Please Enter Instructions.");
// 			$("input#instructions").focus();
// 			return false;
// 		}
		
// 		if ($("#question_type_id").val().trim() == "0") {
// 			alert("Please Enter Question Type.");
// 			$("select#question_type_id").focus();
// 			return false;
// 		}
// 		if ($("#num_questions").val().trim() == "") {
// 			alert("Please Enter Number of Question.");
// 			$("input#num_questions").focus();
// 			return false;
// 		}
// 		if ($("#marks_questions").val().trim() == "") {
// 			alert("Please Enter Question Marks.");
// 			$("input#marks_questions").focus();
// 			return false;
// 		}
// 		if ($("select#status").val() == "2") {
// 			alert("Only Select Active Status.");
// 			$("select#status").focus();
// 			return false;
// 		}
		return true;
	}
	function View_Validation() {
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
		if ($("#professional_id").val().trim() == "0") {
			alert("Please Select professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#course_id").val().trim() == "0") {
			alert("Please Select Subject.");
			$("select#course_id").focus();
			return false;
		}
		getpop();
		getpop2();
		return true;	
	}
	
	function getpop() {
		$("#view_tbl").hide();
		var course_id = $("#course_id").val();
		var temp_count = 0;
		$.post("getNCHPaperLayout0viewdata?"+key+"="+value,{course_id:course_id},function(j) {

					$("table#pop").append('<tr>' 
								+'<td colspan="10"><p id="sr_no">'+j[0]["course_code"]+'</p></td>'
								+'</tr>');

			
	  });
		$.post("getNCHPaperLayoutviewdata?"+key+"="+value,{course_id:course_id},function(j) {
			for(var i=0;i<j.length;i++){
//	 			paper = j[i][4];
//	 			if(paper == "PAPER I"){
					$("table#pop").append('<tr>' 
								+'<td><p id="sr_no">'+j[i][0]+'</p></td>'
								+'<td><p id="question_type">'+j[i][1]+'</p></td>'
								+'<td><p id="num_questions">'+j[i][2]+'</p></td>'
								+'<td><p id="marks_questions">'+j[i][3]+'</p></td>'
								+'<td><p id="total">'+j[i][4]+'</p></td>'
								+'</tr>');
					temp_count += parseInt(j[i][4]);
//	 			}
			}
			$("table#pop").append('<tr>' 
					+'<td><p id="sr_no"></p></td>'
					+'<td><p id="question_type"></p></td>'
					+'<td><p id="num_questions"></p></td>'
					+'<td><p id="marks_questions"></p></td>'
					+'<td><p id="total">'+temp_count+'</p></td>'
					+'</tr>');
	  });
		$("#pop").show();
	}
	
	function getpop2() {
		$("#view_tbl").hide();
		var course_id = $("#course_id").val();
		var temp_count2 = 0;
		$.post("getNCHPaperLayout2viewdata?"+key+"="+value,{course_id:course_id},function(j) {
			
			for(var i=0;i<j.length;i++){
//	 			paper = j[i][4];
//	 			if(paper == "PAPER I"){
					$("table#pop2").append('<tr>' 
								+'<td><p id="sr_no">'+j[i][0]+'</p></td>'
								+'<td><p id="question_type">'+j[i][1]+'</p></td>'
								+'<td><p id="num_questions">'+j[i][2]+'</p></td>'
								+'<td><p id="marks_questions">'+j[i][3]+'</p></td>'
								+'<td><p id="total">'+j[i][4]+'</p></td>'
								+'</tr>');
					temp_count2 += parseInt(j[i][4]);
//	 			}
			}
			$("table#pop2").append('<tr>' 
					+'<td><p id="sr_no"></p></td>'
					+'<td><p id="question_type"></p></td>'
					+'<td><p id="num_questions"></p></td>'
					+'<td><p id="marks_questions"></p></td>'
					+'<td><p id="total">'+temp_count2+'</p></td>'
					+'</tr>');
	  });
		$("#pop2").show();
	}
</Script>

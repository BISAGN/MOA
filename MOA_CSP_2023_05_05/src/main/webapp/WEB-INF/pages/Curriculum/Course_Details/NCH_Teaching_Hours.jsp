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
<script type="text/javascript" src="js\watermark\common.js"></script>


<section class="dashboard-page">
	<div class="container-fluid">
	<!-- title-wrapper start -->
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="title mb-30">

						<h2>
							<span id="lbladd"></span> Teaching Hours
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Teaching Hours</li>
							</ol>
						</nav>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- title-wrapper end -->
		<div class="form-elements-wrapper">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<!-- input style start -->
					<form:form name="nch_teaching_hoursform" id="nch_teaching_hoursform"
						action="nch_teaching_hoursAction" method="post"
						 modelAttribute="nch_teaching_hoursCMD">
						<div class="card-style mb-30">
						<div class="custom-field-block">
							<h6 class="mb-25">Teaching Hours</h6>
							<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
								<div class="select-style-1">
									<label for="text-input">System<span class="mandatory">*</span></label>
									<div class="select-position">
									<select class="singleselect form-control form-control-lg" name="system_id" id="system_id">
									  <option value="0">--Select--</option>
										<c:forEach var="item" items="${getSystemList}" varStatus="num">
													<option value="${item.id}" name="${item.system_name}">${item.system_name}</option>
										</c:forEach>
								   </select>
							     </div>
								</div>					
								<!-- end select -->
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
										<div class="select-style-1">
											<label for="text-input">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select name="degree_id" id="degree_id"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>
										<!-- end select -->
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-4">
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
										<!-- end select -->
									</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="username">Subject<span class="mandatory">*</span></label>
										<div class="select-position">
											<select class="singleselect form-control form-control-lg" name="course_id" id="course_id">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${CourseList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Theoretical Lecture<span class="mandatory">*</span></label>
										<input type="text" id="theoretical_lecture" name="theoretical_lecture"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="500"
											placeholder="Theoretical Lecture" />
									</div>
									<!-- end input -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="input-style-1">
										<label>Practical/Tutorial/Seminar/Clinical Posting<span class="mandatory">*</span></label>
										<input type="text" id="pract_tutor_semi_clinic_post" name="pract_tutor_semi_clinic_post"
											class="autocomplete UpperClassName txt-transupp"
											autocomplete="off" maxlength="500"
											placeholder="Practical/Tutorial/Seminar/Clinical Posting" />
									</div>
									<div class="input-style-1 mt-3">
										<input type="hidden" id="id" name="id" value="0"
											autocomplete="off" />
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
							<li id="btn-reload1">
						        <a href="NCH_Teaching_Hours_Url" class="main-btn dark-btn-outline btn-hover btn-iconic-icon btnback"><i class="lni lni-chevron-left"></i>Back</a>
						        </li>
						        <li id="btn-update"><input
									class="main-btn deactive-btn btn-hover btnupda" type="button"
									value="Update" /></li>
									
								<li id="btn-reload"><a 
									class="main-btn secondary-btn btn-hover btn-iconic-icon btnsearch"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li id="btn-save"><input
									class="main-btn info-btn btn-hover btnsave" type="button" value="Save" /></li>
								
								<li id="btn-reload2" ><a href="NCH_Teaching_Hours_Url"
									class="main-btn dark-btn btn-hover btnreset" type="button">Reset</a></li>
								
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

		<section class="single-detail-block">
		<div class="row" id="tbl">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-datatable-p">
						<table class="table" id="Search_NCH_Teaching_Hours">
							<thead>
								<tr>
									<th id="1"><h6>Sr No</h6></th>
									<th id="9"><h6>System</h6></th>
									<th id="3"><h6>Degree</h6></th>
									<th id="3"><h6>Professional</h6></th>
									<th id="5"><h6>Subject</h6></th>
									<th id="5"><h6>Theoretical Lecture</h6></th>
									<th id="5"><h6>Practical/Tutorial/Seminar/Clinical Posting</h6></th>
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

<c:url value="NCH_Teaching_Hours_Delete_Url" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>


<script nonce="${cspNonce}" type="text/javascript">

	$(document).ready(function() {
		$("#btn-update").hide();
		$("#btn-reload1").hide();

		mockjax1('Search_NCH_Teaching_Hours');
		table = dataTable('Search_NCH_Teaching_Hours');
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
			if(Validation()){
				 $("#nch_teaching_hoursform").submit();
			}
		};
		document.getElementById('btn-update').onclick = function() {
			if(Validation()){
				 $("#nch_teaching_hoursform").submit();
			}
		};
// 		document.getElementById('co_code').onkeypress = function() {
// 	 		return OnlyAlphaNumericTrim(event, this);
// 		};
// 		document.getElementById('course_outcome').onkeypress = function() {
// 	 		return noSpace(event, this);
// 		};
// 		document.getElementById('system_id').onchange = function() {
// 			getcourselistbysystem();
// 		};
		
		document.getElementById('system_id').onchange = function() {
			getdegreelistbysystem();
		};
		document.getElementById('professional_id').onchange = function() {
			getcourselistby_professional();
		};
	});
	
	function getcourselistbysystem() {
		var system_id = $("#system_id").val();
		$
				.post('getCourseList_for_Curri?' + key + "=" + value, {
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
	
	function data(Search_NCH_Teaching_Hours) {
		
		jsondata = [];
		var table = $('#' + Search_NCH_Teaching_Hours).DataTable();
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
		var theoretical_lecture = $("#theoretical_lecture").val();
		var pract_tutor_semi_clinic_post = $("#pract_tutor_semi_clinic_post").val();
		

		$.post("getFilter_NCH_Teaching_Hours_data?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			system_id : system_id,
			degree_id : degree_id,
			professional_id : professional_id,
			course_id : course_id,
			theoretical_lecture : theoretical_lecture,
			pract_tutor_semi_clinic_post : pract_tutor_semi_clinic_post
			

		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].ser,j[i].system_name,j[i].degree_name,j[i].professional,j[i].course_name, j[i].theoretical_lecture,j[i].pract_tutor_semi_clinic_post,j[i].action ]);
			}
		});
		$.post("getFilter_NCH_Teaching_Hours_dataCount?" + key + "=" + value, {
			Search : Search,
			system_id : system_id,
			degree_id : degree_id,
			professional_id : professional_id,
			course_id : course_id,
			theoretical_lecture : theoretical_lecture,
			pract_tutor_semi_clinic_post : pract_tutor_semi_clinic_post
			
		}, function(j) {
			FilteredRecords = j;
		});
		setTimeout(setTimeLoadForTable, 1000);
	}
	
	
	function setTimeLoadForTable(){
		
		document.querySelectorAll('.ADDSystem').forEach((items, index) => {
			items.addEventListener('click', event => {
				
				var val=parseInt(index)+1;
				
				var hid = document.getElementById('apIdAGE'+val).value;
				var hsid = document.getElementById('hidsystem'+val).value;
				var hdeg = document.getElementById('hprodegree'+val).value;
				var hprof = document.getElementById('hidprofessional'+val).value;
				var hcour = document.getElementById('hcourse'+val).value;
				var htheoretical = document.getElementById('dptheoretical'+val).value;
				var hpract_tutor = document.getElementById('hpract_tutor'+val).value;
				
				if (confirm('Are You Sure You Want to Edit Detail ?')) {
					editData(hid,hsid,hdeg,hprof,hcour,htheoretical,hpract_tutor);
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
	function editData(id,system_id,degree_id,professional_id,course_id,theoretical_lecture,pract_tutor_semi_clinic_post) {
		$("#btn-update").show();
		$("#btn-save").hide();
		$("#tbl").hide();
		$("#btn-reload").hide();
		$("#btn-reload2").hide();
		$("#btn-reload1").show();
		
		document.getElementById('lbladd').innerHTML = "Update ";
		$("select#system_id").val(system_id);
		$('#system_id').trigger('change');
		$("select#degree_id").val(degree_id);
		$('#degree_id').trigger('change');
		$("select#professional_id").val(professional_id);
		$('#professional_id').trigger('change');
		getcourselistbysystem();
		$("select#course_id").val(course_id);
		$('#course_id').trigger('change');
		$("input#theoretical_lecture").val(theoretical_lecture);
		$("input#pract_tutor_semi_clinic_post").val(pract_tutor_semi_clinic_post);
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
			alert("Please Select Professional.");
			$("select#professional_id").focus();
			return false;
		}
		if ($("#course_id").val().trim() == "0") {
			alert("Please Select Subject.");
			$("select#course_id").focus();
			return false;
		}
		if ($("#theoretical_lecture").val().trim() == "") {
			alert("Please Enter Theoretical Lecture.");
			$("input#theoretical_lecture").focus();
			return false;
		}
		if ($("#pract_tutor_semi_clinic_post").val().trim() == "") {
			alert("Please Enter Practical/Tutorial/Seminar/Clinical Posting.");
			$("input#pract_tutor_semi_clinic_post").focus();
			return false;
		}
		return true;
	}
</Script>

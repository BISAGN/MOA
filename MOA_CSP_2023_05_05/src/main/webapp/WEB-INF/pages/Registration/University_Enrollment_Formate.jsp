<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- datatable style and js start-->
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>
<!-- datatable style and js end-->
<script type="text/javascript" src="js/watermark/common.js"></script>

<section class="dashboard-page">
<div class="container-fluid">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>University Enrollment Format</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">University Enrollment Format</li>
						</ol>
					</nav>
				</div>
			</div>
			<!-- end col -->
		</div>
		<!-- end row -->
	</div>
	</div>

	<div class="form-elements-wrapper">
		<div class="row">
			<div class="col-12">
				<!-- input style start -->
				
				<form:form action="UniversityEnrollmentAction" method="POST"
					class="form-horizontal" modelAttribute="UniversityEnrollmentCMD"
					enctype="multipart/form-data">
					<div class="card-style mb-30">
					
						<h6 class="mb-25">University Enrollment Format</h6>
						<div class="row">
						<div class="col-12 col-sm-12 col-md-6 col-lg-4" id="institute_div">
								<div class="select-style-1">
									<label for="text-input">System<span class="mandatory"></span></label>
									<div class="select-position">
										<select name="system_id" id="system_id" >
								        	<option value=" ">---Select---</option>
											<c:forEach var="item" items="${getSystemList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>								
							</div>
							
							<div class="col-12 col-sm-12 col-md-6 col-lg-4">
									<div class="select-style-1">
										<label for="text-input">Degree<span class="mandatory"></span></label>
										<div class="select-position">
											<select name="degree_id" id="degree_id">
												<option value=" ">--Select--</option>
											</select>
										</div>
									</div>
								</div>
						
							<div class="col-12 col-sm-12 col-md-6 col-lg-4" id="institute_div">
								<div class="select-style-1">
									<label for="text-input">Institute<span class="mandatory"></span></label>
									<div class="select-position">
										<select name="institute_id" id="institute_id">
								        	<option value=" ">---Select---</option>
											<c:forEach var="item" items="${getinstitutelist}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option> 
											</c:forEach>
										</select>
									</div>
								</div>								
							</div>
						
							<div class="col-lg-12 col-md-12 col-sm-12 d-none">
								<div class="form-check checkbox-style mb-20">
									<input class="form-check-radio" type="radio"
										id="universityenrollment_no1"
										name="university_enrollment_formate" checked="checked" value="Manually_Id">
									<label class="form-check-label"> <span
										class="text-heighlight"> Manually Id</span>
									</label> <input class="form-check-radio" type="radio"
										id="universityenrollment_no2"
										name="university_enrollment_formate" value="Ayush_Id">
									<label class="form-check-label"> <span
										class="text-heighlight">Ayush Id</span>
									</label> <input class="form-check-radio" type="radio" 
										id="universityenrollment_no3"
										name="university_enrollment_formate" value="Auto_Generate_Id">
									<label class="form-check-label"> <span
										class="text-heighlight">Auto Generate Id</span>
									</label>
								</div>
								<input class="form-check-radio" type="hidden"
									id="chk_enroll_val" name="chk_enroll_val" value="">
							</div>
						</div>
						<div class="input-style-2 mt-3">
							<input type="hidden" id="id" name="id" value="0"
								autocomplete="off" />
								<input type="hidden" id="university_id" name="university_id" value=""
								autocomplete="off"/>
						</div>
						<ul class="buttons-group mainbtn">
							<li><a id="btn-search"
								class="main-btn secondary-btn btn-hover btn-iconic-icon"
								type="button"><i class="lni lni-search-alt"></i>Search</a></li>
							<li><input id="btn-save" class="main-btn info-btn btn-hover"
								type="submit" value="Save" /></li>
							<li><a href="University_Enrollment_Formate_Url"
								class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
<!-- 								<li><a href="University_Enrollment_Formate_Url" id="update_btn" -->
<!-- 								class="main-btn deactive-btn btn-hover" type="button">Update</a></li> -->
						</ul>
					</div>
					<c:set var="flag" value="" />
					<div class="row">
						<div class="col-12">
							<div class="card-style mb-30">
								<div class="custom-table-filter">
							<div class="row">
								<div class="col-lg-12">
									<div class="custom-table-search">
										<label>Search: <input type="search" class="" id="searchInput"
											placeholder="" aria-controls="search_system"></label>
									</div>
								</div>
							</div>
						</div>
								<div class="table-wrapper table-responsive  custom-table" id="att_Tb">
									<table class="table" id="ScreenReport">
										<thead>
											<tr>
												<th><h6>Ser No</h6></th> 
												<th><h6>Student Name</h6></th>
												<th><h6>Date Of Birth</h6></th> 
												<th><h6>Email Id</h6></th>
												<th><h6>Aadhaar Number</h6></th> 
												<th><h6>Degree</h6></th>
												<th><h6>Institute</h6></th>
												<th><h6>University Enrollment No</h6></th>
											</tr>
										</thead>
										<tbody>
										<c:if test="${list.size()==0}">
										<tr>
										<td colspan="8">No Data Available</td>
										</tr>
										</c:if>
										<c:if test="${list.size()>0}">
											<c:forEach var="item" items="${list}" varStatus="num">
											<c:if test="${num.index == 0}">
												<c:set var="flag" value="${item.sd_id}" />
											</c:if>
											<c:if test="${num.index > 0}">
												<c:set var="flag" value="${flag},${item.sd_id}" />
											</c:if>
												<tr>
													<td>${num.index+1}</td>
													<td>${item.student_name}</td>
													<td>${item.pers_date_of_birth}</td>							
													<td>${item.pers_email}</td>
													<td>${item.pers_aadhar_no}</td>
													<td>${item.degree_name}</td>
													<td>${item.institute_name}</td>
													<td>${item.enroll}</td>
												</tr>
											</c:forEach>
											</c:if>
										</tbody>
									</table>
									<input type="hidden"   id="stu_ids" name="stu_ids" value="${flag}">
									<!-- 				end table -->
								</div>
							</div>
							<!-- 		end card -->
						</div>
						<!-- 	end col -->
					</div>
				</form:form>
			</div>
		</div>
	</div>
</section>

<c:url value="University_Enrollment_Formate_Search" var="University_Enrollment_Formate_Search" />
	<form:form action="${University_Enrollment_Formate_Search}" method="post" id="searchForm" name="searchForm" modelAttribute="">
		<input type="hidden" name="academic_year1" id="academic_year1" value=""/>		
		<input type="hidden" name="system_id1" id="system_id1" value=""/>
		<input type="hidden" name="degree_id1" id="degree_id1" value=""/>
		<input type="hidden" name="institute_id1" id="institute_id1" value=""/>
		<input type="hidden" name="university_id1" id="university_id1" value=""/>
		<!-- <input type="hidden" name="unit_name1" id="unit_name1"  /> -->
	</form:form>
<script  nonce="${cspNonce}" type="text/javascript">
	$(document).ready(
			function() {
				console.log('${MaxUEID}');
				console.log('${university_id}');
				if ('${udata}' != "[]") {
					console.log('${udata[0][0]}');
					$('input:radio[name="university_enrollment_formate"]')
							.filter('[value="${udata[0][0]}"]').attr('checked',
									true);
					getenrollsameasayushid('${udata[0][0]}');
					$.ajaxSetup({
						async : false
					});

// 					$('#btn-save').show();
					// 	$('#update_btn').hide();
					$(':radio,:checkbox').click(function(){
					    return false;
					});
				} else {
// 					$('#btn-save').show();
					// 		$('#update_btn').hide();
				}
				
				var university_id = '${university_id}';
				if (university_id != "" && university_id != null
						&& university_id != "null") {
					$("#university_id").val(university_id);
				}
				var system_id = '${system_id}';
				if (system_id != "" && system_id != null
						&& system_id != "null") {
					$("#system_id").val(system_id);
					GetSystemFromDegree();					
				}
				var degree_id = '${degree_id}';
				if (degree_id != "" && degree_id != null
						&& degree_id != "null") {
					$("#degree_id").val(degree_id);
				}
				var institute_id = '${institute_id}';
				if (institute_id != "" && institute_id != null
						&& institute_id != "null") {
					$("#institute_id").val(institute_id);
					if (('${role}').toUpperCase().includes("INSTITUTE") || ('${role}').toUpperCase().includes("PRINCIPAL")  ) {
						$("#institute_id").attr('readonly', true);
					}
				}
				var academic_year = '${academic_year}';
				if (academic_year != "" && academic_year != null
						&& academic_year != "null") {
					$("#academic_year").val(academic_year);
				}
				$("#searchInput").on("keyup", function() {
					var value = $(this).val().toLowerCase();
					$("#ScreenReport tbody tr").filter(function() {
					$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
					});
		  	});
			});

	//----csp----------------------------
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btn-save').onclick = function() {
			return isvalidclientside();
		};
		document.getElementById('system_id').onchange = function() {
			return GetSystemFromDegree();
		};
		document.getElementById('btn-search').onclick = function() {
			Searchdata();
		};
		$('input[type=radio][name=university_enrollment_formate]').change(function() {
			return getenrollsameasayushid(this.value);
		});
	});
	function getenrollsameasayushid(val) {

		if (val == 'Manually_Id') {
			<c:forEach var="item" items="${list}" varStatus="num">
			if ('${item.university_enrollment_no != ""}') {
				$('#university_enrollment' + '${item.sd_id}').val(
				'${item.university_enrollment_no}');
				$('#university_enrollment' + '${item.sd_id}').attr('readonly', true);
			}else {
				$('#university_enrollment' + '${item.sd_id}').val(
				'');
			}
			</c:forEach>
		} else if (val == 'Ayush_Id') {
			<c:forEach var="item" items="${list}" varStatus="num">
			$('#university_enrollment' + '${item.sd_id}').val(
					'${item.ayush_id}');
			</c:forEach>
		} else if (val == 'Auto_Generate_Id') {
			var automax = '${MaxUEID}';
			const myArray = automax.split("/");
			var x = myArray[2];
			<c:forEach var="item" items="${list}" varStatus="num">
			
			if ('${item.university_enrollment_no != ""}') {
				$('#university_enrollment' + '${item.sd_id}').val(
				'${item.university_enrollment_no}');
			}else {
				$('#university_enrollment' + '${item.sd_id}').val(
				'');
			}
			var enno = myArray[0] + "/" + myArray[1] + "/"
					+ String(x).padStart(5, '0');
			if ($('#university_enrollment' + '${item.sd_id}').val().trim() == "") {
				$('#university_enrollment' + '${item.sd_id}').val(enno);
				x++
			}
			</c:forEach>
		}
	}
	function GetSystemFromDegree() {
		$.ajaxSetup({
			async : false
		});
		var system_id = $("select#system_id").val();
		$
				.post(
						"getSystemlistFromDegree?" + key + "=" + value,
						{
							system_id : system_id
						},
						function(j) {
							var options = '<option value="' + " " + '">'
									+ "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {

								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
										+ j[i][1] + '</option>';
							}
							$("select#degree_id").html(options);
						});
	}
	function Searchdata() {
		$("#academic_year1").val($("#academic_year").val());
		$("#system_id1").val($("select#system_id").val());
		$("#degree_id1").val($("select#degree_id").val());
		$("#institute_id1").val($("select#institute_id").val());
		$("#university_id1").val($("#university_id").val());
		document.getElementById('searchForm').submit();
	}
	function isvalidclientside() {
			var radioval =  $('input[type=radio][name=university_enrollment_formate]:checked').val();
		  if (radioval == undefined || radioval == "undefined") {
			  alert("Please Choose Any University Enrollment Format")
			  return false
		}
	}
</script>

 
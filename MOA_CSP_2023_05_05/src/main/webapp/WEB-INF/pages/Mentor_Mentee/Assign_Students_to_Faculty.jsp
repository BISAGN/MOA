<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
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

<script src="js/common/multicheck.js" type="text/javascript"></script>

<section class="dashboard-page">
	<div class="container-fluid">
		<div class="title-wrapper pt-30">
			<div class="row align-items-center">
				<div class="col-md-6">
					<div class="title mb-30">
						<h2>
							<span id="lbladd1"></span>Assign Mentors to Students
						</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-md-6">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Assign Mentors to Students</li>
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
					<form:form name="AssStutoFac" id="AssStutoFac" action="AssStutoFacAction" method='POST'
						 commandName="AssStutoFacCMD" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<h6 class="mb-25">Assign Mentors to Students</h6>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="select-style-1">
										<label for="text-input">Degree<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="degree_id" id="degree_id" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>
									<!-- end select -->
								</div>
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
									<div class="select-style-1">
										<label for="text-input">Professional<span
											class="mandatory">*</span></label>
										<div class="select-position">
											<select name="professional_id" id="professional_id" class="singleselect form-control form-control-lg">
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
										<label for="text-input">Mentor<span
											class="mandatory">*</span></label>
										<div class="select-position">
											<select name="mentor" id="mentor" class="singleselect form-control form-control-lg">
												<option value="0">--Select--</option>
												 <c:forEach var="item" items="${faclist}"
													varStatus="num">
													<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
												</c:forEach> 
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-with-selection">
											<div class="input-style-2 mb-0">
												<label id="programoutcome_name"> Mentee's Name (0)<span
													class="mandatory">*</span></label> <input type="text"
													id="search_data" autocomplete="off"
													placeholder="Search Mentee's Name">
											</div>
											<div class="col-two" id="checkboxes" class="chklist"></div>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-with-selection">
											<div class="input-style-2 mb-0">
												<label>Selected Mentee's Name<span
													class="mandatory">*</span></label> <input type="text" id="value"
													name="value" maxlength="70"
													placeholder="Selected Mentee's Name" />
											</div>
											<div class="badges-groups">
												<ul id="show_list" class="buttons-group">
												</ul>
											</div>
										</div>
										
										<input type="hidden" name="old_mentee" id="old_mentee" />
										<input type="hidden" name="new_mentee" id="new_mentee" /> 
										<input type="hidden" name="add_mentee" id="add_mentee" /> 
										<input type="hidden" name="remove_mentee" id="remove_mentee" />
											
									</div>
							</div>

							<ul class="buttons-group mainbtn">
								<li><input value="Save" id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" />
								</li>
								<li><a href="InstStuAsstoFacUrl"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a>
								</li>
							</ul>
						</div>
						<!-- end card -->
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>


<script nonce="${cspNonce}" type="text/javascript">
	
	$(document).ready(function() {
		
		var role = '${role}';
		
		getDegreeListFromInstitute();
		
		initiateChkFn('new_mentee','old_mentee','add_mentee','remove_mentee');
		
		if(window.location.href.includes("msg"))
		{
			 var url = window.location.href.split("?msg")[0];
			 window.location = url;
		}
		
	});
	
	document.addEventListener('DOMContentLoaded', function() {
		
		document.getElementById('professional_id').onchange = function() {
			getMenteeList();
		};
		
		document.getElementById('btn-save').onclick = function() {
			return validation();
		};
		
	});
	
	function chxboxOnclick(ser){
	 	document.getElementById('multisub'+ser).onclick = function(){
	 		chkClick();
		};
	}
	
	function validation(){
		if($("#degree_id").val() == "0"){
			alert("Please Select Degree");
			$("#degree_id").focus();
			return false;
		}
		if($("#professional_id").val() == "0"){
			alert("Please Select Professional");
			$("#professional_id").focus();
			return false;
		}
		if($("#mentor").val() == "0"){
			alert("Please Select Mentor");
			$("#mentor").focus();
			return false;
		}
		if($("#new_mentee").val() == ""){
			alert("Please Select atleast one Mentee");
			$("#search_data").focus();
			return false;
		}
		return true;
	}

	function getDegreeListFromInstitute() {
		
		$.post("getDegreeListFromInstituteExam?" + key + "=" + value,{
				
		},function(j) {
								
			var options = '<option value="' + "0" + '">'
					+ "--Select--" + '</option>';
			for (var i = 0; i < j.length; i++) {

				options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
						+ j[i][1] + '</option>';
			}
			$("select#degree_id").html(options);
		});
		
	}
	
	function getMenteeList(){
		
		$('div#checkboxes').empty();
		
		var prof = $("#professional_id").val();
		
		if(prof == "15"){
			prof = "1";
		}
		if(prof == "16"){
			prof = "2";
		}
		if(prof == "17"){
			prof = "3";
		}
		
		$.post("getMenteeList?" + key + "=" + value,{
			prof : prof,
			degree : $("#degree_id").val()
		},function(data) {
			
			var options = '';
			for (var i = 0; i < data.length; i++) {
				options += '<label for="one"  class="chklist"> <input class="customeCheck" type="checkbox" name="multisub"'
						+'id="multisub'+ data[i][0] + '" value="' + data[i][0] + '" />' + data[i][1] + '</label><br>';
			}
			$("div#checkboxes").append(options);
			
			for (var i = 0; i < data.length; i++) {
				chxboxOnclick(data[i][0]);
			}
			
		});
		
	}
	
</script>
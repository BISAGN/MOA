<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/custom_datatable.css">
<link rel="stylesheet" href="assets/vendor/datatable_InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/vendor/datatable_InfiniteScroll/js/jquery.mockjax.js"></script>

<style>
.simple-table .table thead th:first-child, .simple-table .table tbody td:first-child {
     width: auto !important; 
     min-width: auto !important; 
     max-width: auto !important; 
     text-align: center !important; 
}
table#teaching_hours_summary_report td {
    border-color: black !important;
    border: 1px solid black;
}

.middle-center{
	vertical-align: middle !important;
	text-align: center;
}

.bold{
	 font-weight: bold;
}
</style>

<section class="dashboard-page">
<div class="container-fluid">
	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2><span id="lbladd1"></span>Distribution Of Theory Exam (6-F)</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">Distribution Of Theory Exam (6-F)</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>

	<div class="form-elements-wrapper">
		<div class="row">
			<div class="col-12">
				<!-- input style start -->
                <form:form  name="course" id="SixF_Distribution_Theory_Action" action="SixF_Distribution_Theory_Action" method='POST' commandName="SixF_Distribution_Theory_CMD"   enctype="multipart/form-data">
				<div class="card-style mb-30">
					<h6 class="mb-25">Distribution Of Theory Exam (6-F)</h6>
						<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">System<span class="mandatory">*</span></label>
									<div class="select-position">
									<select name="system_id" id="system_id">
									  <option value="0">--Select--</option>
										<c:forEach var="item" items="${getSystemList}" varStatus="num">
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
											<select name="degree_id" id="degree_id" class="form-control">
												<option value="0">--Select--</option>
											</select>
										</div>
									</div>								
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Professional<span class="mandatory">*</span></label>
									  <div class="select-position">
											<select name="professional_id" id="professional_id">
									      <option value="0">--Select--</option>
										<c:forEach var="item" items="${getprofessionalList}" varStatus="num">
											<option value="${item.id}" name="${item.professional}">${item.professional}</option>
										</c:forEach>
								     </select>										
								 </div>
								</div>								
							</div>
							<div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label for="text-input">Course<span class="mandatory">*</span></label>
										<div class="select-position">
											<select name="course_id" class="form-control" id="course_id">
												<option value="0">--Select--</option>
											</select>
									   </div>
									</div>								
							</div>
						</div>
				
						<input type="hidden" id="count_hidden_att" name="count_hidden_att" class="form-control autocomplete" value="1">
						
						<ul class="buttons-group mainbtn">
							<li>
							    <a id="btn-view" class="main-btn secondary-btn btn-hover btn-iconic-icon" type="button" ><i class="lni lni-view-alt"></i>View</a>
							</li>
							
						</ul>
				</div>
				<div class="row" id="pop">
			<div class="col-12">
				<div class="card-style mb-30">
					<div class="table-wrapper table-responsive custom-table b-top"
						id="container-table">
						<table class="table" id="popT1">
							<thead>
							
								<tr class="learncount middle-center">
									<td colspan="7"><span><label class="bold"> <br><h4>Distribution Of Theory Exam (6-F)</h4></label></span></td>
								</tr>
										
	                            <tr>
	                            <th colspan="4" ><label class="ml-5 bold" >Paper - 1</label></th>
	                            <th  colspan="3" ><label class="ml-5 bold">D - Type of Questions Yes can be asked. No should not be asked.</label>
	                            </th>
	                            </tr>
								
  								<tr>  
  								<th  class="middle-center"><label class="ml-5 bold">Sr No.</label></th>
								<th  class="middle-center"><label class="ml-5 bold">List of Topics</label></th>
								<th class="middle-center"><label class="ml-5 bold">Term</label></th>
								<th class="middle-center"><label class="ml-5 bold">Marks</label></th>
								<th  class="middle-center"><label class="ml-5 bold">MCQ</label></th>
								<th class="middle-center"><label class="ml-5 bold">SAQ</label></th>
								<th class="middle-center"><label class="ml-5 bold">LAQ</label></th>
  								</tr> 

							</thead>
						</table>
<!-- 										end table -->

						<table class="table" id="popT2">
							<thead>
										
	                            <tr>
	                            <th colspan="4" ><label class="ml-5 bold" >Paper - 2</label></th>
	                            <th  colspan="3" ><label class="ml-5 bold">D - Type of Questions Yes can be asked. No should not be asked.</label>
	                            </th>
	                            </tr>
								
  								<tr>  
  								<th  class="middle-center"><label class="ml-5 bold">Sr No.</label></th>
								<th  class="middle-center"><label class="ml-5 bold">List of Topics</label></th>
								<th class="middle-center"><label class="ml-5 bold">Term</label></th>
								<th class="middle-center"><label class="ml-5 bold">Marks</label></th>
								<th  class="middle-center"><label class="ml-5 bold">MCQ</label></th>
								<th class="middle-center"><label class="ml-5 bold">SAQ</label></th>
								<th class="middle-center"><label class="ml-5 bold">LAQ</label></th>
  								</tr> 

							</thead>
						</table>

					</div>
				</div>
<!-- 						end card -->
			</div>
<!-- 				end col -->
		</div>
            </form:form>
			</div>
		</div>
	</div>

</div>
</section>

	
<script nonce='r02122i021210p215a12455l12411' type="text/javascript">

$(document).ready(function() {
		$("#pop").hide();
		$("#popT2").hide();
});

function getdegreelistbysystem() {
	var system_name = $("#system_id").val();
	$.post('getDegreeListbysystem1?' + key + "=" + value, {
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

function getcourselistbysystemdegreeprof() {
	var system_id = $("#system_id").val();
	var degree_id = $("#degree_id").val();
	var professional_id = $("#professional_id").val();
	$.post('getCourseList?' + key + "=" + value,
					{
						degree_id : degree_id,
						system_id : system_id,
						professional_id:professional_id
					})
			.done(function(j) {
						
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

	document.getElementById('system_id').onclick = function() {
		getdegreelistbysystem();
	};
	document.getElementById('degree_id').onclick = function() {
		getcourselistbysystemdegreeprof();
	};
	document.getElementById('professional_id').onclick = function() {
		getcourselistbysystemdegreeprof();
	};
	document.getElementById('btn-view').onclick = function() {
		getpop();
	};
	
});


function getpop() {
	var course_id = $("#course_id").val();
	var ser=1;
	var paper = "";
	var noofpaper = 1;
	var paperIIser = 1;
	var x="";
	var y="";
	var z="";
	$.post("get_SixF_viewdata?"+key+"="+value,{course_id:course_id},function(j) {
		$("#count_hidden_att").val(j.length);
		$("tr#popTR").empty();
		for(var i=0;i<j.length;i++){
			if(j[i][6]==1){
				x="Yes";
			}if(j[i][6]==0){
				x="No";
			}
			if(j[i][7]==1){
				y="Yes";
			}if(j[i][7]==0){
				y="No";
			}
			if(j[i][8]==1){
				z="Yes";
			}if(j[i][8]==0){
				z="No";
			}
			
			paper = j[i][4];
			if(paper == "PAPER I"){
				$("table#popT1").append('<tr id="popT1R">' 
						+'<td><label id="serno">'+ser+'</label></td>'
							+'<td><label id="topic'+ser+'">'+j[i][1]+'</label><input type="hidden" id="topicid'+ser+'" name="topicid'+ser+'" value="'+j[i][5]+'"></td>'
							+'<td><label id="term'+ser+'">'+j[i][2]+'</label></td>'
							+'<td><label id="marks'+ser+'">'+j[i][3]+'</label></td>'
							+'<td><label id="mcq'+ser+'">'+x+'</label></td>'
							+'<td><label id="saq'+ser+'">'+y+'</label></td>'
							+'<td><label id="laq'+ser+'">'+z+'</label></td>'
							+'</tr>');
			}
			if(paper == "PAPER II"){
				noofpaper++;
				$("table#popT2").append('<tr id="popT2R">' 
						+'<td><label id="serno">'+paperIIser+'</label></td>'
							+'<td><label id="topic'+ser+'">'+j[i][1]+'</label><input type="hidden" id="topicid'+ser+'" name="topicid'+ser+'" value="'+j[i][5]+'"></td>'
							+'<td><label id="term'+ser+'">'+j[i][2]+'</label></td>'
							+'<td><label id="marks'+ser+'">'+j[i][3]+'</label></td>'
							+'<td><label id="mcq'+ser+'">'+x+'</label></td>'
							+'<td><label id="saq'+ser+'">'+y+'</label></td>'
							+'<td><label id="laq'+ser+'">'+z+'</label></td>'
							+'</tr>');
				paperIIser++;
			}
			setcbsonclick(ser);
			ser++;
		}
});
	$("#pop").show();
	if(paper == "PAPER II"){
		$("#popT2").show();
		$("#noofpaper").val(noofpaper);
	}
}

function setcbsonclick(ser) {
		
		document.getElementById('mcq'+ser).onclick = function() {
			onclicksetval('mcq',ser);
		};
		document.getElementById('saq'+ser).onclick = function() {
			onclicksetval('saq',ser);
		};
		document.getElementById('laq'+ser).onclick = function() {
			onclicksetval('laq',ser);
		};
		
}

function onclicksetval(qt,ser) {
	
	if(qt == "mcq"){
		if($("#mcq"+ser).is(":checked") == true){
			$("#mcqcbval"+ser).val("1");
		}
		if($("#mcq"+ser).is(":checked") == false){
			$("#mcqcbval"+ser).val("0");
		}
	}
	if(qt == "saq"){
		if($("#saq"+ser).is(":checked") == true){
			$("#saqcbval"+ser).val("1");
		}
		if($("#saq"+ser).is(":checked") == false){
			$("#saqcbval"+ser).val("0");
		}
	}
	if(qt == "laq"){
		if($("#laq"+ser).is(":checked") == true){
			$("#laqcbval"+ser).val("1");
		}
		if($("#laq"+ser).is(":checked") == false){
			$("#laqcbval"+ser).val("0");
		}
	}
	
}


</script>

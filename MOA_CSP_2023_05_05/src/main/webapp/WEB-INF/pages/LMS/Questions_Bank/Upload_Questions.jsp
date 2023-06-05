<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- <script type="text/javascript" src="js/InfiniteScroll/etc/buttonCOLVIS.js"></script> -->
<!--  <script type="text/javascript" src="js/InfiniteScroll/etc/button.min.js"></script> -->
<!-- <script type="text/javascript" src="js/InfiniteScroll/etc/buttonhtml.js"></script> -->
<!-- <link rel="stylesheet" href="js/InfiniteScroll/etc/butonCOL&PRINT.css"> -->
<!-- <link rel="stylesheet" href="js/InfiniteScroll/etc/responsive@colvis.css"> -->
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
						<h2>Question Bank</h2>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="breadcrumb-wrapper mb-30">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
								<li class="breadcrumb-item active" aria-current="page">Question
									Bank</li>
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
					<form:form name="Mpostform" id="Mpostform"
						action="question_act?${_csrf.parameterName}=${_csrf.token}"
						method='POST' commandName="Mpet_cmd" enctype="multipart/form-data">
						<div class="card-style mb-30">
							<div class="custom-field-block">
								<h6 class="mb-25">Question Bank</h6>
								<div class="row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">System<span class="mandatory">*</span></label>
											<div class="select-position">
												<input type="hidden" id="id" name="id" value="0"
													autocomplete="off">
												<!-- 									onchange="GetSystemFromCourse();" -->
												<select class="singleselect form-control form-control-lg"
													name="system_id" id="system_id">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getSystemList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- end select -->
									</div>


									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Degree<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="degree_id" id="degree_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>


										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Set<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="set_id" id="set_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>


										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Course<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="course_id" id="course_id">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>


										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Module<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													name="module_id" id="module_id" class="form-control">
													<option value="0">--Select--</option>
												</select>
											</div>
										</div>


										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Exam Name<span
												class="mandatory">*</span></label> <input id="exam_name"
												name="exam_name"
												class="autocomplete UpperClassName txt-transupp"
												placeholder="Enter Exam Name" maxlength="10"
												autocomplete="off"></input>
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Marks<span class="mandatory">*</span></label>


											<input id="marks" name="marks"
												class="autocomplete UpperClassName txt-transupp"
												placeholder="Enter Marks " maxlength="2" autocomplete="off"></input>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="input-style-1">
											<label for="text-input">Time<span class="mandatory">*</span></label>


											<input id="time" name="time"
												class="autocomplete UpperClassName txt-transupp"
												maxlength="5" autocomplete="off"></input>
										</div>
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Difficulty Level<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													id="level_id" name="level_id">
													<option value="0">--Select--</option>
													<option value="1">High</option>
													<option value="2">Medium</option>
													<option value="3">Low</option>
												</select>
											</div>
										</div>


										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3">
										<div class="select-style-1">
											<label for="text-input">Type<span class="mandatory">*</span></label>
											<div class="select-position">
												<select class="singleselect form-control form-control-lg"
													id="type_id" name="type_id">
													<option value="0">--Select--</option>
													<option value="1">Multiple Choice</option>
													<!-- 									<option value="2">Long Answer</option> -->
													<option value="3">True/False</option>
												</select>
											</div>
										</div>


										<!-- end select -->
									</div>
									<!-- 							</div> -->
									<!-- 								<div class="row"> -->
									<div class="col-12 col-sm-12 col-md-9 col-lg-9 custom-d-none"
										id="ob_question">
										<div class="input-style-1">
											<label for="text-input">Question<span
												class="mandatory">*</span></label>

											<textarea name="question" id="question" maxlength="200"
												class="form-control" rows="1" placeholder="Enter Question"></textarea>

										</div>


										<!-- end select -->
									</div>

									<div class="col-12 col-sm-12 col-md-6 col-lg-3 custom-d-none"
										id="obj_correct_ans">

										<div class="select-style-1">
											<label for="userName">Correct Answer<span
												class="mandatory">*</span></label>
											<div class="select-position">
												<select id="correct_ans" name="correct_ans"
													class="singleselect form-control form-control-lg">
													<option value="0">--Select--</option>
												</select>

											</div>
										</div>

									</div>


									<!-- 							</div> -->
									<!-- 							<div class="row"> -->
									<input type="hidden" value="0" id="count_img" name="count_img">

									<div class="col-12 col-sm-12 col-md-12 col-lg-12 custom-d-none"
										id="obj_ans">
										<div class="input-style-1">
											<label>Answer<span class="mandatory">*</span></label>
											<div class="col-9">
												<div class="row addoptions" id="addunitserved"></div>
											</div>

										</div>
									</div>
								</div>
							</div>
							<!-- Bottom Button Start -->
							<div class="btn-bottom">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12">
							<ul class="buttons-group mainbtn">

								<li><a id="btn-reload"
									class="main-btn secondary-btn btn-hover btn-iconic-icon"
									type="button"><i class="lni lni-search-alt"></i>Search</a></li>
								<li><input id="btn-save"
									class="main-btn info-btn btn-hover" type="submit" value="Save">
								</li>
								<li><a href="QuestionUploadUrl"
									class="main-btn dark-btn n btn-hover" type="button">Reset</a></li>
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
										<table class="table" id="search_Question_Bank">
											<thead>
												<tr>
													<th><h6>Sr no</h6></th>
													<th id="${item.id}"><h6>System</h6></th>
													<th id="2"><h6>Course</h6></th>
													<th id="3"><h6>Set</h6></th>
													<th id="4"><h6>Module</h6></th>
													<th id="5"><h6>Exam name</h6></th>
													<th id="7"><h6>Mark</h6></th>
													<th id="8"><h6>Time</h6></th>
													<th id="9"><h6>Question</h6></th>
													<th id="10"><h6>Answer</h6></th>
													<th id="11"><h6>Correct Answer</h6></th>
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



<script nonce="${cspNonce}" type="text/javascript">
// function PreviewImage(a) {
//     var oFReader = new FileReader();
//     oFReader.readAsDataURL(document.getElementById("doc_name"+a).files[0]);

//     oFReader.onload = function (oFREvent) {
//         document.getElementById("queimage"+a).src = oFREvent.target.result;
//     };
// };


$(document).ready(function() {

	mockjax1('search_Question_Bank');
	table = dataTable('search_Question_Bank');
	$('#btn-reload').on('click', function() {
		table.ajax.reload();
	});
	
	if(window.location.href.includes("msg"))
	{
		 var url = window.location.href.split("?msg")[0];
		 window.location = url;
	}
	
	 if('${msg}' != ""){
			window.alert = function(al, $){
			    return function(msg) {
			        al.call(window,msg);
			        $(window).trigger("okbuttonclicked");
			    };
			}(window.alert, window.jQuery);

			$(window).on("okbuttonclicked", function() {
			    console.log("you clicked ok");
			    window.location = window.location.href.split("?")[0];
			});
			//alert('${msg}');
			jQuery("div#errorDiv").show();
		}	
});

function data(search_Question_Bank) {
	jsondata = [];
	var table = $('#' + search_Question_Bank).DataTable();
	var info = table.page.info();
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase();
	var orderType = order[0][1];
	var system_id = $("#system_id").val();
 	var course_id = $("#course_id").val();
	var module_id = $("#module_id").val();
	var set_id = $("#set_id").val();
	var exam_name = $("#exam_name").val();
	var marks = $("#marks").val();
	var time  = $("#time").val();
	var type_id = $("#type_id").val();
	var level_id = $("#level_id").val();

	$.post("getFilterQuestion_Bank_data?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		system_id:system_id,
		course_id:course_id,
		set_id:set_id,
		module_id:module_id,
		exam_name:exam_name,
		marks:marks,
		time:time,
		type_id:type_id,
		level_id:level_id
	}, function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([ j[i].sr_no,j[i].system_name,j[i].course_name,j[i].setname,j[i].module_name,j[i].exam_name,j[i].marks,
				            j[i].time,j[i].question,j[i].answer,j[i].correct_ans,j[i].action ]);
		}
	});
	$.post("getTotalQuestion_Bank__dataCount?" + key + "=" + value, {
		system_id:system_id,
		course_id:course_id,
		set_id:set_id,
		module_id:module_id,
		exam_name:exam_name,
		marks:marks,
		time:time,
		type_id:type_id,
		level_id:level_id
	}, function(j) {
		FilteredRecords = j;
		});
}

function healthimage(a){
var modal = document.getElementById("previewModal");

//Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById("queimage"+a);
var modalImg = document.getElementById("img01");
//var captionText = document.getElementById("caption");

modal.style.display = "block";
modalImg.src = img.src;
//captionText.innerHTML = img.alt;

}
	

jsonObj = [];
jsondata = [];
var FilteredRecords = 0;



function answer_show(val){
	
	if(val=="1"){
		abhiForm(4);
		
		 $("div#obj_correct_ans").show();
		 $("div#obj_ans").show();
		 $("div#ob_question").show();
	}
	
 if(val == "2"){
		abhiForm(1);
		$("div#ob_question").show();
		 $("div#obj_correct_ans").show();
		 $("div#obj_ans").show();
 }
	
 
  if (val == "3"){
		abhiForm(2);
		 $("div#obj_ans").show();
		$("div#ob_question").show();
		 $("div#obj_correct_ans").show();
	}

}


x=1;
function formopen(x){ 
// 	debugger;
	
	$("#add_group"+x).hide();
	$("#group_remove"+x).hide();
	x= x+1;
	 
	var e = x;

	$("input#count_img").val(x);
	$("#addunitserved").append('<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12 position-relative text-center" id="tr_img'+x+'"><div class="input-style-1">'+getNextChar(alphabaeticchar)+'<input type="text"  id="answer'+x+'" name="answer'+x+'" class="form-control input_class " autocomplete="off" maxlength="50" onchange="holiday_dt('+x+');" /></div>'
		
	    +'<ul class="buttons-group"  align="center"><li><a class="main-btn success-btn btn-hover btn-sm" value = "ADD" title = "ADD" id = "add_group'+x+'" onclick="formopen('+x+');" ><i class="lni lni-plus"></i></a></li><li><a class="main-btn danger-btn btn-hover btn-sm" value="REMOVE" title = "REMOVE" id = "group_remove'+x+'" onclick="formopen_re('+x+');"><i class="lni lni-trash-can"></i></a></li></ul>' 
	    +'</div>');
	var options = '';
	
	options += '<option value='+getNextChar(alphabaeticchar)+'>'+getNextChar(alphabaeticchar)+'</option>'
	$("select#correct_ans").append(options);
	alphabaeticchar=getNextChar(alphabaeticchar);
}

var alphabaeticchar='@';

function abhiForm(m){ 
	
	
	$("#add_group"+x).hide();
	$("#group_remove"+x).hide();
	x= x+1;
	$("#addunitserved").empty();
	var e = x;
	 alphabaeticchar='@';
	$("input#count_img").val(m);
	$("select#correct_ans").empty();
	
	for (var x = 1; x <= m; x++) {
		
		if(x==m){
		$("#addunitserved").append('<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12 position-relative  text-center " id="tr_img'+x+'"><div class="input-style-1">'+getNextChar(alphabaeticchar)+'<input type="text"  id="answer'+x+'" name="answer'+x+'" class="form-control input_class" autocomplete="off" maxlength="50" /></div>'
		
	    +'<ul class="buttons-group" id="act'+x+'"><li><a class="main-btn success-btn btn-hover btn-sm" value = "ADD" title = "ADD" id = "add_group'+x+'" ><i class="lni lni-plus"></i></a></li></ul>' //onclick="formopen('+m+');" 
	    +'</div>');
		onclickplusbtn(m);
		}else{
			$("#addunitserved").append('<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12" align="center" id="tr_img'+x+'"><div class="input-style-1">'+getNextChar(alphabaeticchar)+'<input type="text"  id="answer'+x+'" name="answer'+x+'" class="form-control input_class" autocomplete="off" onchange="holiday_dt('+x+');" maxlength="50" /></div>'
				    +'<div id="action'+x+'" align="center"></div>' 
				    +'</div>');
		}
		var options = '';
		
	if(m!=4){
	$("#act"+x).hide();
	$("#action"+x).hide();
	}
		options += '<option value="answer'+x+'">'+getNextChar(alphabaeticchar)+'</option>'
		$("select#correct_ans").append(options);
		alphabaeticchar=getNextChar(alphabaeticchar);
		
		
	}
	
	
}

function onclickplusbtn(m) {
	  document.getElementById('add_group'+m).onclick = function () {
      	return formopen(m); 
      };
}	

function formopen_re(R){
	
	 $("#tr_img"+R).remove();
	 x = R-1;
	 $("input#count_img").val(x);
	 $("#add_group"+x).show();
	 $("#group_remove"+x).show();

	  $("#correct_ans").find('option[value='+alphabaeticchar+']').remove();
	  alphabaeticchar=getPrevChar(alphabaeticchar);
}

function getNextChar(char) {
	  return String.fromCharCode(char.charCodeAt(0) + 1);
	}
function getPrevChar(char) {
	  return String.fromCharCode(char.charCodeAt(0) - 1);
	}
	
function getDegreeNamebysystem() {

	var system_name = $("select#system_id").val();
	
	$
			.post(
					"getDegreeListBySystem?" + key + "=" + value,
					{
						system_name : system_name
					
					},
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

function getSetNamebyDegree() {

	var system_name = $("select#system_id").val();
	var degree = $("select#degree_id").val();
	$
			.post(
					"getSetListByDegree?" + key + "=" + value,
					{
						system_name : system_name,
						degree : degree
					},
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#set_id").html(options);
					});
}




function getCourseNamebySet() {

	var system_name = $("select#system_id").val();
	var degree = $("select#degree_id").val();
	var setname = $("select#set_id").val();
	$
			.post(
					"getCourseListBySet?" + key + "=" + value,
					{
						system_name : system_name,
						degree : degree,
						setname : setname
						
					},
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


function getModuleNamebycourse() {

	var course_name = $("select#course_id").val();
	
	$
			.post(
					"getModuleListByCourse?" + key + "=" + value,
					{
						
						course_name : course_name
					},
					function(j) {
						var options = '<option value="' + "0" + '">'
								+ "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';
						}
						$("select#module_id").html(options);
					});
}


document.addEventListener('DOMContentLoaded', function () {	
	
	        document.getElementById('btn-save').onclick = function () {
	        	return Validation(); 
	        };
			document.getElementById('system_id').onchange = function () {
				getDegreeNamebysystem(); 
			};
			document.getElementById('degree_id').onchange = function () {
				getSetNamebyDegree();
			};
			document.getElementById('set_id').onchange = function () {
				getCourseNamebySet();
			};
			document.getElementById('course_id').onchange = function () {
				getModuleNamebycourse();
			};
			document.getElementById('marks').onkeypress = function () {
				return isNumberKey1to9(event);
			};
			document.getElementById('time').onkeypress = function () {
				return isNumberKey1to9(event);
			};
 			document.getElementById('type_id').onchange = function () {
 				answer_show(this.value);
 			//	return isNumberKey1to9(event);

 			};
 			
 			document.getElementById('marks').onkeypress = function () {
				return isNumberKey0to9(event);
			};
		

	});
	
	
function Validation(){
	
	
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
	
	if ($("#set_id").val() == "0") {
		alert("Please Select Set.");
		$("select#set_id").focus();
		return false;
	} 
	
	if ($("#course_id").val() == "0") {
		alert("Please Select Course.");
		$("select#course_id").focus();
		return false;
	} 
	if ($("#module_id").val() == "0") {
		alert("Please Select Module.");
		$("select#module_id").focus();
		return false;
	} 
	if ($("#exam_name").val().trim() == "") {
		alert("Please Enter Exam Name.");
		$("input#exam_name").focus();
		return false;
	}
	if ($("#marks").val().trim() == "") {
		alert("Please Enter Marks.");
		$("input#marks").focus();
		return false;
	}
	if ($("#time").val().trim() == "") {
		alert("Please Enter Total Time.");
		$("input#time").focus();
		return false;
	}
// 	if ($("#que_upload").val().trim() == "") {
// 		alert("Please Upload Questions.");
// 		$("input#que_upload").focus();
// 		return false;
// 	}
	
	if ($("#level_id").val().trim() == "0") {
		alert("Please Enter Difficulty Level.");
		$("select#level_id").focus();
		return false;
	}
	if ($("#type_id").val().trim() == "0") {
		alert("Please Enter Type.");
		$("select#type_id").focus();
		return false;
	}
	
	if ($("#type_id").val() != "1" || $("#type_id").val() != "3" ) {
		
		if ($("#question").val().trim() == "") {
			alert("Please Enter Question.");
			$("#question").focus();
			return false;
		}
		
		if ($("select#correct_ans").val() == "0") {
			alert("Please Enter Correct Answer.");
			$("select#correct_ans").focus();
			return false;
		}
		
		if (ans_function() ==1) {
			return false;
		}
		
	}
	
	return true;
}

$('#marks').keypress(function(evt) {
	  if (evt.which == "0".charCodeAt(0) && $(this).val().trim() == "") {
		  alert("Please Do Not Enter First Digit 0");
		  $("input#marks").focus();
	  return false;
	   }
	});
	
	$('#time').keypress(function(evt) {
		  if (evt.which == "0".charCodeAt(0) && $(this).val().trim() == "") {
			  alert("Please Do Not Enter First Digit 0");
			  $("input#time").focus();
		  return false;
		   }
		});

function ans_function(){
	
	var flag=0
	$(".input_class" ).each(function() {
		
		if ($(this).val() == "") {
			alert("Please Enter Answer.");
			$("#"+this.id).focus();
			flag=1;
			
			return false;
		}
	});
	
	return flag;
}
	
</script>


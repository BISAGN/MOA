<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link href="js/NewSearch/newsearch.css" rel="stylesheet" /> -->
<script type="text/javascript" src="js/watermark/common.js"></script>
<!-- <link rel="stylesheet" href="js/InfiniteScroll_old/css/site.css"> -->
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
<link rel="stylesheet" href="assets/vendor/multistep_form/multistep.css">

<body>
	<section class="dashboard-page">
		<div class="container-fluid">

			<div class="title-wrapper pt-30">
				<div class="row align-items-center">
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="title mb-30">
							<h2>Exam Paper</h2>
						</div>
					</div>
					<!-- end col -->
					<div class="col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="breadcrumb-wrapper mb-30">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
									<li class="breadcrumb-item active" aria-current="page">Exam
										Paper</li>
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
						<%--                 <form:form action="Exam_Paper_action?${_csrf.parameterName}=${_csrf.token}" method="POST" class="form-horizontal" modelAttribute="Exam_Paper_cmd"> --%>

						<form
							action="Exam_Paper_action?${_csrf.parameterName}=${_csrf.token}"
							name="epf" id="epf" method='POST'
							modelAttribute="Exam_Paper_cmd">

							<div class="card-style mb-30">
								<div class="custom-field-block">
									<h6 class="mb-25">Exam Paper</h6>
									<div class="row">

										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="select-style-1">
												<label>Course<span class="mandatory">*</span></label>
												<div class="select-position">
													<select class="singleselect form-control form-control-lg"
														name="course_id" id="course_id">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${courselist}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<!-- end select -->
										</div>

										
										<div class="col-12 col-sm-12 col-md-6 col-lg-4">
											<div class="select-style-1">
												<label>Module<span class="mandatory">*</span></label>
												<div class="select-position">
													<select class="singleselect form-control form-control-lg"
														name="module_id" id="module_id" readonly="true">
														<option value="0">---Select---</option>
													</select>
												</div>
											</div>

											<!-- end select -->
										</div>

										<!-- <div class="col-12 col-sm-12 col-md-6 col-lg-3">
								<div class="select-style-1">
									<label>Exam<span class="mandatory">*</span></label>
									<div class="select-position">
										<select name="exam_name" id="exam_name">
									       <option value="0">--Select--</option>
										</select>
									</div>
								</div>								
								
								end select
							</div> -->
									</div>
								</div>
								<!-- Bottom Button Start -->
								<div class="btn-bottom">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<ul class="buttons-group mainbtn">
												<li><input class="main-btn active-btn btn-hover"
													type="button" id="save" value="Start"></li>
											</ul>
										</div>
									</div>
								</div>
								<!-- Bottom Button End -->
							</div>

							<div id="quiz">
								<div class="card-style exam-paper-card mb-30">
									<h3 class="mb-25 text-center">
										<b>START YOUR EXAM</b>
									</h3>
									<div class="row">
										<div class="col-md-12">
											<div class="instructions-detail">
												<h6 class="instructions-head">IMPORTANT INSTRUCTIONS</h6>
												<p>1) Please create module wise MCQs in English and
													Roman Hindi.</p>
												<p>2) All fields are mandatory.</p>
												<p>3) Minimum 20 MCQs for each difficulty level should
													be entered.</p>
											</div>
										</div>
										<div class="col-12 col-sm-12 col-md-12 col-lg-12">
											<div class="cont">
												<div class="progress-line">
													<div class="step">
														<span class="step-count">1 to 5</span>
														<div class="bullet">
															<span>1</span>
														</div>
														<div class="check bi bi-check"></div>
													</div>
													<div class="step">
														<span class="step-count">6 to 10</span>
														<div class="bullet">
															<span>2</span>
														</div>
														<div class="check bi bi-check"></div>
													</div>
													<div class="step">
														<span class="step-count">11 to 15</span>
														<div class="bullet">
															<span>3</span>
														</div>
														<div class="check bi bi-check"></div>
													</div>
													<div class="step">
														<span class="step-count">16 to 20</span>
														<div class="bullet">
															<span>4</span>
														</div>
														<div class="check bi bi-check"></div>
													</div>
												</div>
												<div class="form-outer">
													<form action="#">
														<div class="page slide-page qbox">
															<div id="getExa_Paper1" class="quepage"></div>
															<div class="field">
																<button
																	class="firstNext next main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left">
																	Next<i class="lni lni-chevron-right"></i>
																</button>
															</div>
														</div>
														<div class="page qbox">
															<div id="getExa_Paper2" class="quepage"></div>
															<div class="field btns">
																<button
																	class="prev-1 prev main-btn dark-btn-outline  btn-hover btn-iconic-icon">
																	<i class="lni lni-chevron-left"></i>Previous
																</button>
																<button
																	class="next-1 next main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left">
																	Next<i class="lni lni-chevron-right"></i>
																</button>
															</div>
														</div>
														<div class="page qbox">
															<div id="getExa_Paper3" class="quepage"></div>
															<div class="field btns">
																<button
																	class="prev-1 prev main-btn dark-btn-outline  btn-hover btn-iconic-icon">
																	<i class="lni lni-chevron-left"></i>Previous
																</button>
																<button
																	class="next-1 next main-btn dark-btn-outline  btn-hover btn-iconic-icon btn-iconic-left">
																	Next<i class="lni lni-chevron-right"></i>
																</button>
															</div>
														</div>
														<div class="page qbox">
															<div id="getExa_Paper4" class="quepage"></div>
															<div class="field btns">
																<button
																	class="prev-2 prev main-btn dark-btn-outline  btn-hover btn-iconic-icon">
																	<i class="lni lni-chevron-left"></i>Previous
																</button>
																<button class="submit main-btn info-btn btn-hover"
																	type="submit" value="Submit">Submit</button>
															</div>
														</div>
													</form>
												</div>
											</div>
										</div>

										<input type="hidden" name="cap1" id="cap1" value="${capcha}" />
										<input type="hidden" id="searchLOcount" name="searchLOcount"
											class="form-control" value="0" autocomplete="off"> <input
											type="hidden" id="subjecth" name="subjecth"
											class="form-control" value="0" autocomplete="off">

										<!-- <ul class="buttons-group mainbtn">
													<li>
														<input class="main-btn info-btn btn-hover" type="submit" value="Submit">
													</li>													
												</ul> -->
									</div>
								</div>
							</div>
						</form>
						<%-- 				</form:form> --%>

					</div>
				</div>
			</div>
		</div>
	</section>

</body>

<script nonce="${cspNonce}" type="text/javascript"
	src="assets/vendor/multistep_form/multistep.js"></script>

<script nonce="${cspNonce}" type="text/javascript">
	$(document)
			.ready(
					function() {
						
						$.ajaxSetup({
							async : false
						});
						if('${course_id}' != null && '${course_id}' != '' && '${module_id}' != null && '${module_id}' != ''){
														
						$("#course_id").val('${course_id}');
						$("#course_id").change();
						getModuleNamebycourse_id();
						$("#module_id").val('${module_id}');
						  $("#module_id").change();
						}
					
						if('${course_id}' != null && '${course_id}' != ''){
							  $("#course_id").attr('readonly',true);
                              $("#course_id").attr('tabindex','-1');
                              
							  $("#module_id").attr('readonly',true);
							  $("#module_id").attr('tabindex','-1');
							  $("div#quiz").show();
							  $("#save").hide();
							  $("#save").hide();
							  getQuizList();

						}else {
							$("div#quiz").hide();
// 							 $("#save").show();
						}
						
						if(window.location.href.includes("msg"))
						{
							 var url = window.location.href.split("?msg")[0];
							 window.location = url;
						}
						
					});

function getQuizList() {

	
	
		var course_id = $("#course_id").val();
		var module_id = $("#module_id").val();

		$("#subjecth").val(course_id);
		
		$.post("getquestionByCourse_mod_ctrl?" + key + "=" + value,
		{course_id : course_id,
		 module_id : module_id},function(j) {
						 
		var c = 1;
		var div_count=1;
		var count=0;

		
		for(var p=0;p<j.length;p++){
			
			
			
			var op1 = j[p][1].split(":")[0];
			var op2 = j[p][1].split(":")[1];
			var op3 = j[p][1].split(":")[2];
			var op4 = j[p][1].split(":")[3];
			
			
			console.log("op1  "+op1+  " op2 "+op2+ "  op3  "+op3+ "  op4  "+op4);
			
			
			
			$("#getExa_Paper"+div_count) 
					.append(
							'<div class="input-style-form-check">'
									+ '<p class="que"><span class="que-no">Q - '
									+ c 
									+ '</span>'+j[p][0]+'</p>'
									+ '<div class="q-mcq"><div class="row"><div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="'+op1+'" id="check_'+j[p][3]+'" name="check_'+j[p][3]+'" type="radio" class="form-check-input" /> <label for="radio1" class="form-check-label">(A)'  
									+ op1
									+ '</label></div></div>'
									+ '<div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="'+op2+'" id="check_'+j[p][3]+'" name="check_'+j[p][3]+'" type="radio" class="form-check-input" /> <label  for="radio1" class="form-check-label">(B)'
									+ op2
									+ '</label></div></div>'
									+ '<div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="'+op3+'" id="check_'+j[p][3]+'" name="check_'+j[p][3]+'" type="radio" class="form-check-input" /> <label  for="radio1" class="form-check-label">(C)'
									+ op3
									+ '</label></div></div>'
									+ '<div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="'+op4+'" id="check_'+j[p][3]+'" name="check_'+j[p][3]+'" type="radio" class="form-check-input" /> <label  for="radio1" class="form-check-label">(D)'
									+ op4
									+ '</label></div></div></div></div></div>');
				count+=1;
				c = parseInt(c) + 1;
					if(count%5==0){
						div_count+=1;
					}
		}
	});
	var t = exam_start();
	QuizValid(t);
}

	function QuizValid(c) {

		if ($("#course_id").val() == "0") {
			alert("Please Select Course ");
			return false;
		}
		if ($("#module_id").val() == "0") {
			alert("Please Select Module  ");
			return false;
		}

		var course_id = $("#course_id").val();
// 		var set_id = $("#set_id").val();
		var module_id = $("#module_id").val();
// 		var exam_name = $("#exam_name").val();

		if (c == 0) {
			$("div#quiz").show();
		} else {
			alert("You Have Finished This Exam")
		}

// 		Quizsave();

	}
		


	function getModuleNamebycourse_id() {
		$.ajaxSetup({
			async : false
		});
		var course_id = $("select#course_id").val();
		$
				.post(
						"getModuleListByCourse_data?" + key + "=" + value,
						{
							course_id : course_id
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

// 	function getexam_namebyModuleName() {

// 		var course_id = $("select#course_id").val();
// 		var set_id = $("select#set_id").val();
// 		var module_id = $("select#module_id").val();
// 		$
// 				.post(
// 						"getExamlistFromModule?" + key + "=" + value,
// 						{
// 							set_id : set_id,
// 							course_id : course_id,
// 							module_id : module_id
// 						},
// 						function(j) {
// 							var options = '<option value="' + "0" + '">'
// 									+ "--Select--" + '</option>';
// 							for (var i = 0; i < j.length; i++) {
// 								options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
// 										+ j[i][1] + '</option>';
// 							}
// 							$("select#exam_name").html(options);
// 						});
// 	}
	function exam_start() {
		var temp = 0;
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		var course_id = $("#course_id").val();
		var module_id = $("#module_id").val();
		$.post('course_exam?' + key + "=" + value, {
				course_id : course_id,module_id : module_id
		}).done(function(j) {
			temp = j;
		});
		return temp;
	}
	
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('course_id').onchange = function() {
			 getModuleNamebycourse_id();
		};
		
		document.getElementById('save').onclick = function() {
			 getQuizList();
		};
	});
	
</script>
